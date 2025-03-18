![banner](./android.png)
# OkHttp / Retrofit
OkHttp 과 Retrofit 은 Square 에서 개발한 HTTP 통신을 위한 라이브러리이다.<br/>
OkHttp 는 네트워크 요청을 보다 세밀하게 제어할 수 있으며, 인터셉터를 활용하여 모든 네트워크 요청과 응답을 모니터링하거나 로깅하는 등 다양한 기능을 제공한다.<br/>
Retrofit 은 REST API 호출을 더 간결하고 직관적으로 작성할 수 있게 도와주며, OkHttp 는 내부적으로 Retrofit 의 네트워크 통신을 담당하는 역할을 수행한다.<br/>
Retrofit 은 어노테이션 기반의 설정을 통해 코드의 가독성을 높이고, 직관적인 API 설계를 가능하게 한다.<br/>
OkHttp 과 Retrofit 은 함께 사용하여 더 나은 네트워크 성능과 확장성을 확보하는 것이 일반적이다.<br/>
<br/>
<br/>

## Setup
```toml
# /gradle/libs/versions.toml
[versions]
kotlinx-serialization-json = "1.7.0"
okhttp = "4.12.0"
retrofit = "2.11.0"
retrofit-kotlinx-serialization-json = "1.0.0"

[plugins]
kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }

[libraries]
kotlinx-serialization-json = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinx-serialization-json" }
okhttp-logging = { group = "com.squareup.okhttp3", name = "logging-interceptor", version.ref = "okhttp" }
retrofit-core = { group = "com.squareup.retrofit2", name = "retrofit", version.ref = "retrofit" }
retrofit-kotlin-serialization = { group = "com.jakewharton.retrofit", name = "retrofit2-kotlinx-serialization-converter", version.ref = "retrofit-kotlinx-serialization-json" }
```
```groovy
// 모듈 단 build.gradle.kts
plugins {
    alias(libs.plugins.kotlin.serialization)
}
dependencies {
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.okhttp.logging)
}
```
<br/>
<br/>
<br/>



# OkHttp
OkHttp 는 네트워크 요청을 보다 세밀하게 제어할 수 있으며, Retrofit 의 내부 네트워크 처리 엔진으로 사용된다.<br/>
또한, 인터셉터를 활용하여 네트워크 요청과 응답을 모니터링하거나 변형하는 기능을 제공한다.<br/>
<br/>
<br/>

## OkHttpClient 생성
`OkHttpClient` 는 네트워크 요청을 세밀하게 조정할 수 있는 클라이언트이다.<br/>
이를 활용해 `Interceptor` 를 추가하면 요청 및 응답을 가로채어 로깅, 헤더 추가 등의 작업을 수행할 수 있다.<br/>

```kotlin
val client = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer YOUR_TOKEN") // 헤더 추가
            .build()
        chain.proceed(request)
    }
    .addInterceptor(
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // 전체 응답 로그 출력
        }
    )
    .connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃 설정
    .readTimeout(30, TimeUnit.SECONDS) // 읽기 타임아웃 설정
    .build()
```
<br/>
<br/>

## OkHttp 요청
OkHttp 를 단독으로 사용하여 HTTP 요청을 보낼 수도 있지만, 보통 Retrofit 과 함께 사용하여 처리한다.<br/>

```kotlin
fun exampleRequestWithOkHttp() {
    val request = Request.Builder()
        .url("https://api.example.com/users/1")
        .build()

    val call = client.newCall(request)
    call.enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            println("Request failed: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            response.body?.let { responseBody ->
                println("Response: $responseBody")
            }
        }
    })
}
```
<br/>
<br/>

## OkHttp Interceptor
`Interceptor` 는 요청(Request)과 응답(Response)을 가로채서 수정하거나 로깅, 캐싱, 인증 등의 부가적인 처리를 수행할 수 있도록 한다.<br/>
<br/>

### Application Interceptor
요청 전후로 헤더 추가, 로깅, 공통 로직을 처리하며, 캐시된 응답에도 적용된다.<br/>
리다이렉트 및 재시도 동작을 제어할 수 있다.<br/>
<br/>

### Network Interceptor
네트워크 요청을 보내기 직전에 호출된다.<br/>
오직 네트워크를 통한 요청에만 작동하며, 캐시된 데이터에는 영향이 없다.<br/>
<br/>

### 예제 코드
```kotlin
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .header("User-Agent", "MyApp")
            .build()
        return chain.proceed(request)
    }
}
```
<br/>
<br/>

## OkHttp Authenticator
JWT 토큰이 만료되었을 때는 `Interceptor` 또는 `Authenticator` 를 사용하여 자동으로 토큰을 갱신하는 방식을 적용할 수 있다.<br/>
가장 일반적인 방법은 `Authenticator` 를 활용하여 401 응답이 발생했을 때 토큰을 갱신하고, 이를 새로운 요청과 함께 다시 시도하는 방식이다.<br/>

```kotlin
interface AuthApiService {
    @POST("api/v1/auth/token/{refreshToken}")
    suspend fun getNewAccessToken(
        @Path("refreshToken")
        refreshToken: String
    ): Response<TokenResponse>
}
```
```kotlin
data class TokenResponse(
    val newAccessToken: String
)
```
```kotlin
class TokenAuthenticator(
    private val tokenManager: TokenManager
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val newAccessToken: String? = try {
            runBlocking {
                withTimeout(10_000) {
                    tokenManager.refreshTokenSafely()
                }
            }
        } catch (_: TimeoutCancellationException) {
            null
        }

        if (newAccessToken == null) {
            // RefreshToken 도 만료된 경우, 로그인 페이지로 리디렉션
            return null
        }

        // 새로운 AccessToken 으로 헤더를 업데이트하여 재시도
        return response.request.newBuilder()
            .header("Authorization", "Bearer $newAccessToken")
            .build()
    }
}
```
```kotlin
class TokenManager(
    private val authApiService: AuthApiService
) {
    private val mutex = Mutex()

    // 갱신된 토큰을 안전하게 반환
    suspend fun refreshTokenSafely(): String? {
        return if (mutex.isLocked) {
            // 이미 갱신 중이면 기존 토큰을 반환
            mutex.withLock { getAccessToken() }
        } else {
            // 토큰 갱신 중에 다른 요청이 오지 않도록 동기화
            mutex.withLock {
                refreshToken() // 실제 토큰 갱신 수행
            }
        }
    }

    // 실제로 토큰을 갱신하는 메서드
    private suspend fun refreshToken(): String? {
        // 저장된 refresh token 을 가져와 새로운 access token 을 갱신
        val refreshToken = getRefreshToken()
        val newAccessToken = authApiService.getNewAccessToken(refreshToken)
        return newAccessToken.body()?.newAccessToken
    }

    // 토큰을 얻는 메서드
    private suspend fun getAccessToken(): String {
        // 이미 갱신된 토큰 반환
        return "access_token"
    }

    // RefreshToken 을 반환하는 메서드
    private suspend fun getRefreshToken(): String {
        // 실제로 저장된 Refresh Token 을 반환하는 로직
        return "refresh_token"
    }
}
```
<br/>
<br/>
<br/>



# Retrofit
Retrofit 은 REST API 호출을 간결하고 직관적으로 작성할 수 있도록 도와주는 라이브러리로, 어노테이션 기반 설정을 통해 API 요청을 추상화한다.<br/>
<br/>
<br/>

## Retrofit 객체 생성
Retrofit 을 사용하려면 먼저 `Retrofit.Builder()` 를 사용해 객체를 생성해야 한다.<br/>

```kotlin
val json: Json = Json {
    prettyPrint = true // 읽기 쉽게 포맷팅
    ignoreUnknownKeys = true // 정의되지 않은 키 무시
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/") // 기본 URL 설정
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .client(OkHttpClient.Builder().build()) // OkHttp 클라이언트 추가
    .build()
```
<br/>
<br/>

## API 인터페이스 정의
Retrofit 에서는 API 호출을 정의하기 위해 인터페이스를 사용한다.<br/>

```kotlin
data class User(
    val name: String,
    val age: String
)
```
```kotlin
interface ApiService {
    @GET("user")
    suspend fun getUser(): Response<User>
}
```
```kotlin
suspend fun fetchUser() {
    val userApiService = retrofit.create(UserApiService::class.java)
    val response = userApiService.getUser()
    if (response.isSuccessful) {
        val user = response.body()
        println("Response: $user")
    } else {
        println("Error: ${response.errorBody()?.string()}")
    }
}
```
<br/>
<br/>

## CallAdapter
`CallAdapter` 는 Retrofit 의 네트워크 요청 결과를 변환하는 역할을 한다.<br/>
기본적으로 Retrofit 은 `Call<T>` 타입을 반환하지만, 이를 `LiveData`, `Flow`, `Result` 등의 형태로 변환할 수 있다.<br/>
특히 커스텀 `CallAdapter` 는 API 응답을 `Result<T>` 형태로 감싸고 싶을 때 유용하며, 예를 들어, 성공과 실패를 명확히 구분하려면 `Result<T>` 를 활용하는 것이 좋다.<br/>

```kotlin
package com.ymg.retrofit

sealed interface ApiCallback<out T> {
    data class Success<T>(
        val body: T
    ) : ApiCallback<T>

    sealed interface Failure : ApiCallback<Nothing> {
        // API 응답 실패
        data class HttpError(
            val code: Int,
            val message: String,
            val body: String
        ) : Failure

        // Network 에러
        data class NetworkError(
            val throwable: Throwable
        ) : Failure

        // 알 수 없는 에러
        data class UnknownError(
            val throwable: Throwable
        ) : Failure
    }
}
```
```kotlin
class ApiCallAdapter<T>(
    private val successType: Type
) : CallAdapter<T, Call<ApiCallback<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(
        call: Call<T>
    ): Call<ApiCallback<T>> = ApiCall(call, successType)
}
```
```kotlin
class ApiCall<T>(
    private val delegate: Call<T>,
    private val successType: Type
) : Call<ApiCallback<T>> {
    override fun enqueue(
        callback: Callback<ApiCallback<T>>
    ) = delegate.enqueue(
        object : Callback<T> {
            override fun onResponse(
                call: Call<T>,
                response: Response<T>
            ) {
                callback.onResponse(this@ApiCall, Response.success(response.toApiCallback()))
            }

            override fun onFailure(
                call: Call<T?>,
                throwable: Throwable
            ) {
                val error = if (throwable is IOException) {
                    ApiCallback.Failure.NetworkError(throwable)
                } else {
                    ApiCallback.Failure.UnknownError(throwable)
                }
                callback.onResponse(this@ApiCall, Response.success(error))
            }

            private fun Response<T>.toApiCallback(): ApiCallback<T> {
                val body = body()
                val errorBody = errorBody()
                val code = code()
                val message = message()

                return if (isSuccessful) {
                    body?.let {
                        ApiCallback.Success(it)
                    } ?: run {
                        ApiCallback.Failure.UnknownError(IllegalStateException("Body is null."))
                    }
                } else {
                    errorBody?.let {
                        ApiCallback.Failure.HttpError(
                            code = code,
                            message = message,
                            body = it.string()
                        )
                    } ?: run {
                        ApiCallback.Failure.UnknownError(IllegalStateException("ErrorBody is null."))
                    }
                }
            }
        }
    )

    override fun timeout(): Timeout = delegate.timeout()

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun clone(): Call<ApiCallback<T>> = ApiCall(delegate.clone(), successType)

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<ApiCallback<T>> = throw UnsupportedOperationException("This adapter does not support sync execution")

    override fun request(): Request = delegate.request()
}
```
```kotlin
class ApiCallAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (Call::class.java != getRawType(returnType)) {
            return null
        }
        check(returnType is ParameterizedType) {
            "returnType must be parameterized as ApiCallback<Foo> or ApiCallback<out Foo>"
        }

        val wrapperType = getParameterUpperBound(0, returnType)
        if (ApiCallback::class.java != getRawType(wrapperType)) {
            return null
        }
        check(wrapperType is ParameterizedType) {
            "wrapperType must be parameterized as ApiCallback<ResponseBody>"
        }

        val bodyType = getParameterUpperBound(0, wrapperType)
        return ApiCallAdapter<Any>(bodyType)
    }
}
```
```kotlin
val retrofitWithCallAdapter: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.example.com/") // 기본 URL 설정
    .addCallAdapterFactory(ApiCallAdapterFactory()) // CallAdapter 추가
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .client(OkHttpClient.Builder().build()) // OkHttp 클라이언트 추가
    .build()
```
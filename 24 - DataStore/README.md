![banner](./jetpack.png)
# DataStore
`DataStore` 는 안드로이드에서 Key-Value 형태로 데이터를 저장하고 관리할 수 있는 라이브러리이다.<br/>
기존 `SharedPreferences` 와 비교하여 비동기 처리 및 데이터 무결성 보장 측면에서 개선된 방식이며, `Flow` 기반으로 동작하여 데이터 변경을 감지하고 구독할 수 있다.<br/>
<br/>
<br/>

## Preferences DataStore
Key-Value 형태의 데이터를 저장하는 방식으로, 기존 `SharedPreferences` 와 유사하지만 비동기적으로 동작하며 `Flow` 를 통해 데이터를 구독할 수 있다.<br/>
저장할 데이터의 스키마가 정해져 있지 않으며, 단순한 설정 값을 저장할 때 적합하다.<br/>
<br/>

### Setup
```toml
# gradle/libs.versions.toml
[versions]
androidx-datastore = "1.1.3"

[libraries]
androidx-datastore = { group = "androidx.datastore", name = "datastore-preferences", version.ref = "androidx-datastore" }
```
```groovy
// 모듈 단 build.gradle.kts
dependencies {
    implementation(libs.androidx.datastore)
}
```
<br/>

### 예제 코드
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object PreferencesDataStoreModule {
    private val Context.userPreferencesDataStore by preferencesDataStore(
        name = "user.preferences_pb"
    )

    @Singleton
    @Provides
    fun provideUserPreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.userPreferencesDataStore

    @Singleton
    @Provides
    fun provideUserPreferencesDataStoreRepository(
        userPreferencesDataStore: DataStore<Preferences>
    ): UserPreferencesDataStoreRepository = UserPreferencesDataStoreRepository(
        userPreferencesDataStore = userPreferencesDataStore
    )
}
```
```kotlin
class UserPreferencesDataStoreRepository @Inject constructor(
    private val userPreferencesDataStore: DataStore<Preferences>
) {
    private val accessTokenKey = stringPreferencesKey("ACCESS_TOKEN")
    private val refreshTokenKey = stringPreferencesKey("REFRESH_TOKEN")

    val accessToken: Flow<String> = userPreferencesDataStore.data.map {
        it[accessTokenKey] ?: ""
    }
    val refreshToken: Flow<String> = userPreferencesDataStore.data.map {
        it[refreshTokenKey] ?: ""
    }

    suspend fun setAccessToken(
        accessToken: String
    ) = userPreferencesDataStore.edit {
        it[accessTokenKey] = accessToken
    }

    suspend fun setRefreshToken(
        refreshToken: String
    ) = userPreferencesDataStore.edit {
        it[refreshTokenKey] = refreshToken
    }
}
```
```kotlin
@HiltViewModel
class UserPreferencesDataStoreViewModel @Inject constructor(
    private val repository: UserPreferencesDataStoreRepository
) : ViewModel() {
    fun run() = viewModelScope.launch {
        println("AccessToken: ${repository.accessToken.first()}")
        println("RefreshToken: ${repository.refreshToken.first()}")
        repository.setAccessToken("ACCESS")
        repository.setRefreshToken("REFRESH")
        println("AccessToken: ${repository.accessToken.first()}")
        println("RefreshToken: ${repository.refreshToken.first()}")
    }
}
```
<br/>
<br/>

## Proto DataStore
`.proto` 파일을 사용하여 명확한 데이터 스키마를 정의하고, 이를 기반으로 직렬화하여 데이터를 저장하는 방식이다.<br/>
객체 단위의 데이터를 저장할 때 적합하며, 데이터 모델을 명확히 정의할 수 있다.<br/>
<br/>

### Setup
```toml
# gradle/libs.versions.toml
[versions]
androidx-datastore = "1.1.3"
protobuf = "0.9.4"
protobuf-javalite = "3.21.12"

[plugins]
google-protobuf = { id = "com.google.protobuf", version.ref = "protobuf" }

[libraries]
androidx-datastore = { group = "androidx.datastore", name = "datastore-preferences", version.ref = "androidx-datastore" }
protobuf-javalite = { group = "com.google.protobuf", name = "protobuf-javalite", version.ref = "protobuf-javalite" }
```
```groovy
// 모듈 단 build.gradle.kts
plugins {
    alias(libs.plugins.google.protobuf)
}

androidComponents {
    onVariants(selector().all()) { variant ->
        afterEvaluate {
            val protoTask =
                    project.tasks.getByName("generate" + variant.name.replaceFirstChar { it.uppercaseChar() } + "Proto") as GenerateProtoTask

            project.tasks.getByName("ksp" + variant.name.replaceFirstChar { it.uppercaseChar() } + "Kotlin") {
                dependsOn(protoTask)
                (this as org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompileTool<*>).setSource(
                        protoTask.outputBaseDir
                )
            }
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.12"
    }
    plugins {
        generateProtoTasks {
            all().forEach {
                it.builtins {
                    create("java") {
                        option("lite")
                    }
                }
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.datastore)
    implementation(libs.protobuf.javalite)
}
```
<br/>

### 예제 코드
```protobuf
// proto 파일 생성
// 모듈 단 /src/main/proto/user.proto
syntax = "proto3";

option java_package = "com.ymg.datastore";
option java_multiple_files = true;

message UserPreferences {
    string user_name = 1;
    string user_email = 2;
}
```
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object ProtoDataStoreModule {
    private val Context.userProtoDataStore: DataStore<UserPreferences> by dataStore(
        fileName = "user_prefs.pb",
        serializer = UserProtoSerializer
    )

    @Provides
    @Singleton
    fun provideUserProtoDataStore(
        @ApplicationContext context: Context
    ): DataStore<UserPreferences> {
        return context.userProtoDataStore
    }

    @Provides
    @Singleton
    fun provideUserProtoDataStoreRepository(
        userProtoDataStore: DataStore<UserPreferences>
    ): UserProtoDataStoreRepository = UserProtoDataStoreRepository(
        userProtoDataStore = userProtoDataStore
    )
}
```
```kotlin
class UserProtoDataStoreRepository @Inject constructor(
    private val userProtoDataStore: DataStore<UserPreferences>
) {
    val userName: Flow<String> = userProtoDataStore.data.map {
        it.userName ?: ""
    }
    val userEmail: Flow<String> = userProtoDataStore.data.map {
        it.userEmail ?: ""
    }

    suspend fun setUserName(
        userName: String
    ) = userProtoDataStore.updateData {
        it.toBuilder().setUserName(userName).build()
    }

    suspend fun setEmail(
        userEmail: String
    ) = userProtoDataStore.updateData {
        it.toBuilder().setUserEmail(userEmail).build()
    }
}
```
```kotlin
@HiltViewModel
class UserProtoDataStoreViewModel @Inject constructor(
    private val repository: UserProtoDataStoreRepository
) : ViewModel() {
    fun run() = viewModelScope.launch {
        println("이름: ${repository.userName.first()}")
        println("이메일: ${repository.userEmail.first()}")
        repository.setUserName("홍길동")
        repository.setEmail("gdh@google.com")
        println("이름: ${repository.userName.first()}")
        println("이메일: ${repository.userEmail.first()}")
    }
}
```
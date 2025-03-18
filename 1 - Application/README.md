![banner](./android.png)
# Intent
- - -
`Application` 클래스는 애플리케이션의 전역 상태를 관리하는 기본 클래스이다.<br/>
애플리케이션 프로세스가 시작될 때 가장 먼저 생성되며, 프로세스 내에서 단일 인스턴스만 존재한다.<br/>
<br/>
<br/>

## 전역 상태 관리
애플리케이션의 상태를 전역적으로 유지하고 여러 컴포넌트에서 공유해야 할 때 유용하다.<br/>
예를 들어, 네트워크 상태나 로그인 정보를 애플리케이션 내에서 공유할 수 있다.<br/>
<br/>
<br/>

## 공통 리소스 제공
애플리케이션의 여러 컴포넌트에서 공통적으로 접근해야 하는 리소스를 제공하는 데 사용된다.<br/>
예를 들어, `SharedPreferences` 나 `Database` 인스턴스를 `Application` 에서 관리하고 이를 여러 액티비티에서 참조할 수 있다.<br/>
<br/>
<br/>

## 전역 생명 주기 처리
애플리케이션이 포그라운드로 돌아오거나 백그라운드로 전환될 때와 같은 전역적인 생명 주기 이벤트를 처리할 수 있다.<br/>
예를 들어, 애플리케이션이 종료될 때 데이터를 저장하거나 리소스를 정리하는 등의 작업을 처리할 수 있다.<br/>
<br/>
<br/>

## 주의사항
`Application` 객체는 애플리케이션의 프로세스가 종료되기 전까지 메모리에 상주하므로, 과도한 데이터를 저장하면 메모리 누수가 발생할 수 있다.<br/> 
따라서 필요한 데이터만 저장하고, 메모리 관리에 주의해야 한다.<br/>
<br/>
<br/>

## 예제 코드
```kotlin
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 애플리케이션 시작 시 초기화 작업
    }
}
```
<br/>
<br/>
<br/>



# Context
- - -
`Context` 는 애플리케이션과 시스템에 대한 다양한 정보를 제공하는 추상 클래스이다.<br/>
애플리케이션의 리소스, 시스템 서비스, 환경 설정 등에 접근하는 주요 인터페이스를 제공한다.<br/>
<br/>
<br/>

## 애플리케이션 리소스 접근
`Context` 를 사용하면 애플리케이션의 레이아웃, 문자열, 이미지 등의 리소스를 불러올 수 있습니다.<br/>

```kotlin
@Test
fun `애플리케이션_리소스_접근`() {
    val appName = context.getString(R.string.app_name)

    println("AppName: $appName")
    assertEquals("Application", appName)
}
```
<br/>
<br/>

## 시스템 서비스 접근
`Context` 를 사용하여 위치 정보, 알림, 네트워크 연결 등 시스템 서비스에 접근할 수 있다.<br/>

```kotlin
@Test
fun `시스템_서비스_접근`() {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
}
```
<br/>
<br/>

## 애플리케이션 환경 정보 접근
애플리케이션의 패키지명, 로케일, 앱 버전 등의 정보를 조회할 수 있다.<br/>

```kotlin
@Test
fun `애플리케이션_환경_정보_접근`() {
    val packageName = context.packageName

    println("AppName: $packageName")
    assertEquals("com.ymg.application", packageName)
}
```
<br/>
<br/>

## Application Context vs Activity Context
### Application Context
애플리케이션의 전반적인 상태 및 리소스에 접근하는 데 사용된다.<br/>
애플리케이션이 종료될 때까지 생명 주기가 지속되므로, UI 와 관련 없는 작업에 주로 사용된다.<br/>
예를 들어, 데이터베이스 연결을 `Application` 에서 관리하고, 이를 여러 액티비티에서 사용할 수 있다.<br/>
<br/>

### Activity Context
개별 액티비티의 생명 주기와 함께 소멸되며, UI 관련 작업에 주로 사용된다.<br/>
액티비티가 종료되면 더 이상 유효하지 않으므로, 메모리 누수를 방지하기 위해 액티비티 외부에서 보관하지 않아야 한다.<br/>
![banner](./android.png)
# Activity
액티비티(`Activity`)는 안드로이드 애플리케이션에서 UI 를 제공하고 사용자와 상호작용하는 주요 컴포넌트이다.<br/>
애플리케이션에는 적어도 하나의 액티비티가 필요하며, 화면 구성 요소로 프래그먼트(`Fragment`)를 사용할 수 있다.<br/>
<br/>
<br/>
<br/>



# Activity Lifecycle
액티비티는 다양한 생명주기 단계에 따라 관리되며, 각 단계에서 호출되는 메서드가 다르다.<br/>
액티비티의 생명주기를 정확하게 이해하는 것은 안드로이드 앱을 효과적으로 개발하는 데 중요한 부분이다.<br/>
![activity-lifecycle](./activity-lifecycle.png)
<br/>
<br/>

## onCreate()
액티비티가 처음 생성될 때 호출된다.<br/>
초기화 작업(예: 뷰 바인딩, 데이터 초기화 등)을 수행한다.<br/>
`savedInstanceState` 를 사용해 이전 상태를 복원할 수 있다.<br/>

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d("MainActivity", "onCreate 호출")

    enableEdgeToEdge()
    setContentView(R.layout.activity_main)
    
    // 상태 복원 예시
    savedInstanceState?.getString("user_name")?.let {
    // 복원된 데이터 처리
    }

    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
    }
}
```
<br/>
<br/>

## onStart()
액티비티가 화면에 보이기 직전에 호출된다.<br/>
이 시점에서 액티비티는 UI 를 보여주지만, 사용자와 상호작용이 불가능하다.<br/>

```kotlin
override fun onStart() {
    super.onStart()
    Log.d("MainActivity", "onStart 호출")
}
```
<br/>
<br/>

## onResume()
액티비티가 사용자와 상호작용 가능한 상태가 되었을 때 호출된다.<br/>
UI 가 사용자와 상호작용을 시작할 수 있다.<br/>

```kotlin
override fun onResume() {
    super.onResume()
    // 사용자와의 상호작용이 가능한 상태에서 필요한 작업 실행
    Log.d("MainActivity", "onResume 호출")
}
```
<br/>
<br/>

## onPause()
액티비티가 사용자와 상호작용할 수 없게 되면 호출된다.<br/>
화면에는 여전히 보일 수 있으나, 더 이상 상호작용할 수 없는 상태이다.<br/>
데이터 저장 또는 중요한 상태 변화를 처리할 때 유용하다.<br/>

```kotlin
override fun onPause() {
    super.onPause()
    // 중요한 데이터 저장 작업 처리
    Log.d("MainActivity", "onPause 호출")
}
```
<br/>
<br/>

## onStop()
액티비티가 화면에서 완전히 사라지면 호출된다.<br/>
액티비티가 백그라운드로 이동하거나 화면에서 제거될 때 호출된다.<br/>
UI 관련 리소스를 해제하거나 데이터를 저장하는 등의 작업을 할 수 있다.<br/>
사용자가 다시 액티비티로 돌아올 때는 `onRestart()` 가 호출되며, 이후 `onStart()` 가 호출된다.<br/>

```kotlin
override fun onStop() {
    super.onStop()
    // 리소스 해제 또는 백그라운드 작업 처리
    Log.d("MainActivity", "onStop 호출")
}
```
<br/>
<br/>

## onDestroy()
액티비티가 완전히 종료될 때 호출된다.<br/>
시스템 메모리 부족 등으로 액티비티가 종료될 때도 호출될 수 있다.<br/>
이 메서드는 호출되지 않을 수 있다.(시스템에서 메모리 부족으로 종료될 때 등)<br/>

```kotlin
override fun onDestroy() {
    super.onDestroy()
    // 리소스 해제 및 마지막 정리 작업
    Log.d("MainActivity", "onDestroy 호출")
}
```
![banner](./android.png)
# LifecycleScope
- - -
`LifecycleScope` 는 안드로이드의 `LifecycleOwner`(예: `Activity`, `Fragment`)에 종속된 코루틴 스코프이다.<br/>
이를 활용하면 `LifecycleOwner` 가 `Destroyed` 상태가 될 때 자동으로 코루틴이 취소되어 메모리 누수를 방지할 수 있다.<br/>
일반적으로 코루틴을 사용할 때는 `CoroutineContext.cancel()` 을 명시적으로 호출해야 하지만, `lifecycleScope` 를 사용하면 생명주기에 따라 자동으로 코루틴이 관리된다.<br/>

```kotlin
private fun exampleLifecycleScope() {
    lifecycleScope.launch {

    }
}
```
<br/>
<br/>

## LifecycleScope 내부 구현
`LifecycleCoroutineScopeImpl` 클래스는 내부적으로 `LifecycleEventObserver` 를 구현하여 생명주기에 따라 코루틴을 자동으로 제어한다.<br/>

```kotlin
internal class LifecycleCoroutineScopeImpl(
    override val lifecycle: Lifecycle,
    override val coroutineContext: CoroutineContext
) : LifecycleCoroutineScope(), LifecycleEventObserver {
    init {
        // in case we are initialized on a non-main thread, make a best effort check before
        // we return the scope. This is not sync but if developer is launching on a non-main
        // dispatcher, they cannot be 100% sure anyways.
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            coroutineContext.cancel()
        }
    }

    fun register() {
        launch(Dispatchers.Main.immediate) {
            if (lifecycle.currentState >= Lifecycle.State.INITIALIZED) {
                lifecycle.addObserver(this@LifecycleCoroutineScopeImpl)
            } else {
                coroutineContext.cancel()
            }
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (lifecycle.currentState <= Lifecycle.State.DESTROYED) {
            lifecycle.removeObserver(this)
            coroutineContext.cancel()
        }
    }
}
```
<br/>
<br/>

## ViewModelScope
`ViewModelScope` 는 ViewModel 의 생명주기에 맞춰 코루틴을 안전하게 관리하는 스코프이다.<br/>
`viewModelScope` 내에서 실행된 코루틴은 ViewModel 의 `onCleared()` 가 호출될 때 자동으로 취소된다.<br/>
<br/>

### Setup
```toml
# gradle/libs.versions.toml
[versions]
androidx-lifecycle = "2.8.7"

[libraries]
androidx-lifecycle-viewmodel = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-ktx", version.ref = "androidx-lifecycle" }
```
```groovy
// 모듈 단 build.gradle.kts
dependencies {
    implementation(libs.androidx.lifecycle.viewmodel)
}
```
<br/>

### 예제 코드
```kotlin
class MainViewModel : ViewModel() {
    init {
        viewModelScope.launch {
            // ViewModel 내에서 실행할 코루틴 작업
        }
    }
}
```
<br/>
<br/>

## repeatOnLifecycle
`repeatOnLifecycle` 은 특정 생명주기 상태에서만 코루틴을 실행하고, 해당 상태에서 벗어나면 자동으로 정지했다가 다시 재개하는 방식으로 동작한다.<br/>
백그라운드 상태에서 불필요한 작업을 중단하고, 다시 포그라운드로 복귀했을 때 처음부터 다시 시작한다는 점에서 최적화된 방식이다.<br/>
특히 `Flow` 를 사용할 때 데이터 스트림을 효과적으로 수집할 수 있다.<br/>

```kotlin
private fun exampleRepeatOnLifecycle() {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {

        }
    }
}
```
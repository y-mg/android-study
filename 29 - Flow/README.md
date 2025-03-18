![banner](./android.png)
# Flow
`Flow` 는 데이터를 발행하지만 저장하지 않는 Cold Stream 이다.<br/>
이는 구독자가 있을 때만 데이터를 방출하며, 구독자가 없으면 데이터가 흐르지 않는다.<br/>
안드로이드에서 `Flow` 를 UI 에 바인딩하면 화면이 재구성될 때 데이터를 다시 가져오는 문제가 발생할 수 있다.<br/>
이를 방지하려면 `StateFlow` 또는 `SharedFlow` 를 사용하여 UI 상태를 관리하거나, Flow 를 적절히 캐싱하는 방식으로 구성할 수 있다.<br/>
<br/>
<br/>

## Cold Stream vs Hot Stream
Cold Stream 은 구독이 시작될 때마다 새롭게 데이터를 생성한다.(예: `Flow`)<br/>
Hot Stream 은 이미 데이터를 방출하고 있으며, 구독자는 중간부터 데이터를 수신한다.(예: `SharedFlow`,`StateFlow`)<br/>
<br/>
<br/>

## Flow 의 Cold Stream 에서 Hot Stream 으로 전환
기본적으로 `Flow` 는 Cold Stream 으로, 구독이 시작될 때마다 새롭게 데이터를 생성한다.<br/>
이를 Hot Stream 으로 전환하기 위해서는 `StateFlow`는 `stateIn()`, `SharedFlow` 는 `sharedIn()` 을 사용하면 된다.<br/>
<br/>
<br/>

## 예제 코드
```kotlin
private fun flowObserver() {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flowViewModel.flow.collect { value ->
                println("Received Flow: $value")
            }
        }
    }
}
```
```kotlin
class FlowViewModel : ViewModel() {
    val flow = flow {
        for (i in 1..5) {
            emit(i) // 데이터 방출
            delay(1000) // 1초마다 방출
        }
    }

    val stateflow: StateFlow<Int> = flow.stateIn(
        scope = viewModelScope, // scope 는 StateFlow 가 Flow 로부터 데이터를 구독할 CoroutineScope 를 지정
        started = SharingStarted.WhileSubscribed(), // started 는 언제부터 Flow 를 구독할지 설정하는 옵션
        initialValue = 0 // 초기값을 설정하는 옵션
    )

    val sharedFow: SharedFlow<Int> = flow.shareIn(
        scope = viewModelScope, // scope 는 StateFlow 가 Flow 로부터 데이터를 구독할 CoroutineScope 를 지정
        started = SharingStarted.WhileSubscribed(), // started 는 언제부터 Flow 를 구독할지 설정하는 옵션
        replay = 0 // 이전 데이터를 몇 개까지 재전송할지를 설정하는 옵션
    )
}
```
<br/>
<br/>
<br/>



# StateFlow
`StateFlow` 는 상태 관리에 특화된 `Flow` 로, 항상 현재 상태를 하나만 유지하며, 구독 시점에 즉시 최신 상태를 제공하고 이후 상태 변화를 관찰할 수 있는 Hot Stream 이다.<br/>
내부적으로 중복된 상태를 무시하는 `distinctUntilChanged()` 기능이 내장되어 있으며, 초기값 설정이 필수이며, 항상 최신 상태를 유지한다.<br/>
주로 ViewModel 에서 UI 상태를 관리하거나 앱 전반의 상태를 관리하는 데 사용된다.<br/>

```kotlin
private fun stateFlowObserver() {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            stateFlowViewModel.stateFlow.collect { value ->
                println("Received StateFlow: $value")
            }
        }
    }
}
```
```kotlin
class StateFlowViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(0) // 초기값 설정 필수
    val stateFlow: StateFlow<Int> = _stateFlow

    init {
        increment()
    }

    private fun increment() {
        _stateFlow.value = 100 // 최신 상태 갱신
    }
}
```
<br/>
<br/>
<br/>



# SharedFlow
`SharedFlow` 는 초기값 없이 시작할 수 있으며, 상태를 유지하지 않고 여러 이벤트를 발행할 수 있어서 데이터 전달 방식을 유연하게 구성할 수 있는 Hot Stream 이다.<br/>
설정에 따라 다수의 구독자가 독립적으로 데이터를 소비할 수 있어, 여러 구독자가 동시에 처리해야 하는 데이터를 전달하는 데 사용된다.<br/>
주로 일회성 이벤트, UI 이벤트(알림, 네비게이션)와 같은 이벤트 스트림에 적합하다.<br/>

```kotlin
private fun sharedFlowObserver() {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            sharedFlowViewModel.sharedFlow.collect { value ->
                println("Received SharedFlow: $value")
            }
        }
    }
}
```
```kotlin
class SharedFlowViewModel : ViewModel() {
    private val _sharedFlow = MutableSharedFlow<String>(
        // 이전 데이터의 개수를 설정하여 새로 구독한 구독자가 받을 수 있는 직전 데이터를 지정한다.
        // 예를 들어 replay = 1 이라면, 구독자가 가장 최근 데이터를 받을 수 있다.
        replay = 0,
        // 버퍼의 추가 크기를 설정하여, 설정된 replay 외에 버퍼에 추가로 저장할 수 있는 데이터의 양을 지정한다.
        extraBufferCapacity = 0,
        // 버퍼가 가득 찼을 때의 동작을 정의한다.
        // BufferOverflow.DROP_OLDEST -> 가장 오래된 데이터를 버리고 새로운 데이터를 수용한다.
        // BufferOverflow.DROP_LATEST -> 최신 데이터를 버리고 새 데이터를 수용하지 않는다.
        // BufferOverflow.SUSPEND -> 버퍼가 가득 찼을 때 더 이상 데이터를 받지 않고 대기한다.
        onBufferOverflow = BufferOverflow.SUSPEND
    )
    val sharedFlow: SharedFlow<String> = _sharedFlow

    init {
        sendEvent("Hello SharedFlow")
    }

    private fun sendEvent(
        message: String
    ) = viewModelScope.launch {
        _sharedFlow.emit(message)
    }
}
```
<br/>
<br/>
<br/>



# CallbackFlow
`CallbackFlow` 는 콜백 기반의 비동기 작업을 `Flow` 로 변환하는 데 사용되는 `Flow` 빌더이다.<br/>
주로 리스너를 통해 이벤트를 수신할 때 유용하며, 이러한 리스너 내부의 데이터를 `Flow` 로 전달하는 기능을 제공한다.<br/>
일반적으로 `Flow` 와 리스너의 스코프가 다르기 때문에 리스너 내부에서 `emit()` 을 직접 호출하여 데이터를 보낼 수 없다.<br/>
그러나 `callbackFlow` 를 사용하면 리스너 내부의 값을 `Flow` 로 안전하게 전달할 수 있다.<br/>
<br/>
<br/>

## 특징 및 동작 방식
### trySend 를 사용한 데이터 전송
`callbackFlow` 는 `trySend` 함수를 사용하여 데이터를 전송한다.<br/>
이는 `emit()` 과 달리 비동기 호출이 아니며, 반환 값을 통해 성공 여부를 확인할 수 있다.<br/>
<br/>

### awaitClose를 사용한 리소스 정리
콜백 리스너는 `awaitClose` 블록에서 해제해야 한다.<br/>
`awaitClose` 는 `callbackFlow` 가 닫힐 때 호출되며, 이 내부에서 리스너를 제거하여 메모리 누수를 방지할 수 있다.<br/>
<br/>

### 버퍼링 및 Backpressure 처리
`callbackFlow` 는 `BufferOverflow` 전략을 설정할 수 있으며, 기본적으로 `SUSPEND` 모드를 사용하여 버퍼가 가득 차면 보낸 측이 일시 중단된다.<br/>
<br/>

### 예제 코드
```kotlin
fun EditText.textChanges(): Flow<CharSequence?> = callbackFlow {
    // TextWatcher 리스너를 정의하여 EditText의 텍스트 변경 이벤트를 감지
    val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
        override fun afterTextChanged(s: Editable?) = Unit
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            trySend(s).isSuccess // 실패 여부를 처리할 필요가 없는 경우 무시 가능
        }
    }

    // EditText 에 TextWatcher 추가
    addTextChangedListener(textWatcher)

    // 초기값 방출 (현재 EditText 에 입력된 값 반영)
    awaitClose { removeTextChangedListener(textWatcher) }
}.onStart { emit(text) }
```
```kotlin
@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun exampleCallbackFlow() {
        binding.editText.textChanges()
            .debounce(300) // 300ms 동안 입력이 없을 때만 데이터 방출
            .distinctUntilChanged() // 중복 값 방출 방지
            .mapLatest { text ->
                Log.d("EditTextFlow", "입력된 텍스트: $text")
            }
            .launchIn(lifecycleScope)
    }
```
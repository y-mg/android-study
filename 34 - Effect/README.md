![banner](./jetpack.png)
# Effect
컴포즈의 Effect 는 컴포저블이 관리하지 않는 외부 상태를 변경하거나, 비동기 작업을 처리하는 데 사용되는 컴포저블 함수들이다.<br/>
주로 코루틴을 활용하여 외부 시스템과 상호작용하거나, 컴포저블 외부의 상태를 변화시키는 작업을 수행할 때 사용된다.<br/>
<br/>
<br/>

## LaunchedEffect
`LaunchedEffect` 는 컴포저블 내에서 코루틴을 실행할 때 사용되며, `supsend` 함수 실행과 같은 비동기 작업에 적합하다.<br/>
지정된 `key` 가 변경되면 새로운 코루틴을 시작하며, `key` 가 변경될 때마다 코루틴이 재시작되고 컴포지션에서 제거되면 자동으로 코루틴이 취소된다.<br/>

```kotlin
@Composable
fun ExampleLaunchedEffect() {
    val data = remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        // 비동기 작업 수행
        data.value = fetchDataFromNetwork()
    }

    // UI에 데이터 표시
    Text(text = data.value ?: "Loading...")
}

// 네트워크 요청 등의 비동기 작업
private suspend fun fetchDataFromNetwork(): String {
    delay(2000)
    return "Fetched Data"
}
```
<br/>
<br/>

## DisposableEffect
`DisposableEffect` 는 `onDispose` 블록을 제공하여 컴포지션에서 벗어날 때 자원을 정리할 수 있다.<br/>
예를 들어, `BroadcastReceiver` 등록과 해제와 같은 외부 리소스의 초기화 및 정리 작업에 유용하다.<br/>

```kotlin
class MyReceiver : BroadcastReceiver() {
    // onReceive 메서드는 브로드캐스트를 수신할 때 호출된다.
    override fun onReceive(context: Context, intent: Intent) {
        // 수신한 인텐트의 액션을 확인
        val action = intent.action
        if (action == "MY_ACTION") {
            // "MY_ACTION" 액션이 수신되면 처리할 작업을 정의한다.
            Log.d("MyReceiver", "MY_ACTION received!")
            
            // 예를 들어, 수신된 데이터 처리
            val data = intent.getStringExtra("data_key")
            Log.d("MyReceiver", "Received data: $data")
        }
    }
}
```
```kotlin
@Composable
fun ExampleDisposableEffect() {
    val context = LocalContext.current

    DisposableEffect(context) {
        // MyReceiver 인스턴스 생성
        val receiver = MyReceiver()

        // MY_ACTION 을 수신할 수 있도록 인텐트 필터 등록
        val filter = IntentFilter("MY_ACTION")
        ContextCompat.registerReceiver(
            context,
            receiver,
            filter,
            ContextCompat.RECEIVER_NOT_EXPORTED
        )

        onDispose {
            // 컴포지션에서 벗어날 때 receiver 해제
            context.unregisterReceiver(receiver)
        }
    }
}
```
<br/>
<br/>

## SideEffect
`SideEffect` 는 컴포저블 내에서 컴포즈 상태를 비컴포즈 상태로 전달하는 작업을 수행하며, 리컴포지션 시마다 호출된다.<br/>
주로 컴포저블이 리컴포지션된 후, 컴포즈 외부의 상태를 변경해야 할 때 사용된다.<br/>

```kotlin
@Composable
fun ExampleSideEffect() {
    var count by remember { mutableIntStateOf(0) }

    SideEffect {
        // 외부 상태를 변경
        Log.d("SideEffect", "Count: $count")
    }

    Button(
        onClick = { 
            count++ 
        }
    ) {
        Text("Increment")
    }
}
```
<br/>
<br/>

## rememberCoroutineScope
`rememberCoroutineScope` 는 컴포저블 내에서 코루틴 스코프를 생성하여, 컴포지션이 변경되더라도 스코프를 유지할 수 있도록 한다.<br/>
이를 통해 컴포지션 범위에서 코루틴 스코프를 기억하고, 컴포저블 외부에서 코루틴을 실행할 수 있게 해준다.<br/>

```kotlin
@Composable
fun ExampleRememberCoroutineScope() {
    val scope = rememberCoroutineScope()

    Button(
        onClick = {
            scope.launch {
                // 코루틴 내부에서 비동기 작업
                delay(1000)
                println("Task completed!")
            }
        }
    ) {
        Text("Start Task")
    }
}
```
<br/>
<br/>

## rememberUpdatedState
`rememberUpdatedState` 는 값이 변경되더라도 특정 Side Effect 가 다시 시작되지 않도록 할 때 사용된다.<br/>
리컴포지션이 발생하더라도 최신 값을 항상 참조하도록 보장하며, 주로 `LaunchedEffect` 나 `SideEffect` 와 같이 오래 실행되는 효과에서 유용하다.<br/>

```kotlin
@Composable
fun ExampleRememberUpdatedState() {
    var count by remember { mutableIntStateOf(0) }
    val updatedCount = rememberUpdatedState(count)

    // LaunchedEffect 는 코루틴이 시작된 후 상태가 변경되더라도 코루틴 내부에서는 초기 상태만 참조한다.
    // 이때 rememberUpdatedState 를 사용하면 항상 최신 상태를 참조할 수 있어 예기치 않은 버그를 방지할 수 있다.
    LaunchedEffect(updatedCount.value) {
        // 최신 값 참조
        println("Count: ${updatedCount.value}")
    }

    Button(
        onClick = {
            count++
        }
    ) {
        Text("Increment Count")
    }
}
```
<br/>
<br/>

## produceState
`produceState` 는 비컴포즈 상태를 컴포즈 상태로 변환하여 코루틴을 제공하는 함수이다.<br/>
네트워크 요청과 같은 비동기 작업을 수행하고, 그 결과를 `State` 로 반환할 수 있다.<br/>

```kotlin
@Composable
fun ExampleProduceState() {
    val result = produceState<String?>(initialValue = null) {
        value = fetchDataFromNetwork()
    }

    Text(text = result.value ?: "Loading...")
}

// 네트워크 요청 등의 비동기 작업
private suspend fun fetchDataFromNetwork(): String {
    delay(2000)
    return "Fetched Data"
}
```
<br/>
<br/>

## derivedStateOf
`derivedStateOf` 는 하나 이상의 상태를 다른 상태로 변환하는 데 사용되며, 기존 상태로부터 계산된 값인 파생 상태를 효율적으로 관리할 때 유용하다.<br/>
예를 들어, 리스트의 사이즈나 특정 조건을 만족하는 항목들의 개수 등이 있다.<br/>
값이 변경되지 않으면 불필요한 리컴포지션이 발생하지 않기 때문에, 계산 비용이 높은 작업이 불필요하게 자주 수행되는 것을 방지하고, 의존하는 상태가 변경될 때만 리컴포지션을 발생시킨다.<br/>

```kotlin
// count 가 변경되지 않는다면 count * 2 를 다시 계산하지 않는다.
// count 가 변경될 때만 count * 2 를 새롭게 계산한다.
@Composable
fun ExampleDerivedStateOf() {
    // 원본 상태
    var count by remember { mutableIntStateOf(0) }

    // 파생 상태(derivedStateOf 사용): count * 2
    val derivedValue = remember {
        derivedStateOf { count * 2 }
    }

    // UI 표시
    Text("Derived Value: ${derivedValue.value}")

    Button(
        onClick = {
            count++
        }
    ) {
        Text("Increment")
    }
}
```
<br/>
<br/>

## snapshotFlow
`snapshotFlow` 는 컴포즈 상태를 `Flow` 로 변환하며, 이를 통해 컴포즈의 상태 변화를 감지하고 코루틴 내에서 이를 비동기적으로 처리할 수 있다.<br/>
이전에 방출된 값과 다를 경우에만 값을 방출하여 리소스를 절약할 수 있다.<br/>

```kotlin
@Composable
fun ExampleSnapshotFlow() {
    var count by remember { mutableIntStateOf(0) }
    val flow = snapshotFlow { count }

    LaunchedEffect(flow) {
        flow.collect { newCount ->
            // 상태 변경을 처리
            println("Count changed: $newCount")
        }
    }

    Button(
        onClick = { 
            count++ 
        }
    ) {
        Text("Increment")
    }
}
```
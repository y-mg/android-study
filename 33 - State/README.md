![image](./jetpack.png)
# State
안드로이드 컴포즈에서 상태(State)는 UI 가 사용자와의 상호작용을 통해 변화를 나타내는 개념이다.<br/>
예를 들어, "네트워크 연결이 실패했을 때의 메시지 표시", "사용자가 버튼을 클릭할 때 발생하는 애니메이션" 등이 이에 해당하며, 상태 관리는 컴포즈의 핵심 요소 중 하나이다.<br/>
<br/>
<br/>

## remember
컴포저블 함수는 리컴포지션(Recomposition)이 발생하면 상태가 사라질 수 있다.<br/>
이때 `remember` 를 사용해서 단일 객체나 값을 저장하고, 리컴포지션 중에 저장된 값을 반환해 상태를 유지할 수 있다.<br/>

```kotlin
@Composable
fun ExampleNoRemember() {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        // 상태가 없으므로 입력한 텍스트가 유지되지 않음
        Text(text = "Hello")
        OutlinedTextField(
            value = "",
            onValueChange = {},
            label = { 
                Text("Name") 
            }
        )
    }
}

@Composable
fun ExampleRemember() {
    Column(modifier = Modifier.padding(10.dp)) {
        // remember 를 사용해 상태를 저장하고 유지
        var name by remember { mutableStateOf("") }

        Text(text = "Hello")
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )
    }
}
```
<br/>
<br/>

## mutableStateOf 
`mutableStateOf` 는 컴포즈에서 상태를 관리하는 데 사용된다.<br/>
상태가 변경되면, 해당 상태를 읽고 있는 컴포저블 함수가 자동으로 리컴포지션되며 UI 가 갱신된다.<br/>

```kotlin
@Composable
fun ExampleMutableStateOf() {
    // mutableStateOf 를 사용하여 상태를 생성한다.
    // 버튼 클릭 시 카운트 값이 증가하거나 감소한다.
    var count by remember { mutableIntStateOf(0) } // 초기 값 0

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // 현재 카운트 값을 표시
        Text(
            text = "Count: $count"
        )

        // 버튼을 눌러 카운트를 증가시키는 함수
        Button(
            onClick = { 
                count++ 
            },
            modifier = Modifier.padding(
                top = 16.dp
            )
        ) {
            Text(text = "Increase Count")
        }

        // 버튼을 눌러 카운트를 감소시키는 함수
        Button(
            onClick = { 
                count-- 
            },
            modifier = Modifier.padding(
                top = 8.dp
            )
        ) {
            Text(text = "Decrease Count")
        }
    }
}
```
<br/>
<br/>

## rememberSaveable
`rememberSaveable` 은 화면 회전 등 구성 변경이 일어날 때에도 상태를 유지할 수 있도록 도와주는 함수이다.<br/>
`Bundle` 에 상태값을 저장하여, 구성 변경 후에도 이전 상태를 복원한다.<br/>

```kotlin
@Composable
fun ExampleRememberSaveable() {
    // rememberSaveable 을 사용하여 상태를 저장한다.
    // 화면 회전 시에도 상태가 유지된다.
    var text by rememberSaveable { mutableStateOf("Hello, World!") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        // 상태값을 표시하는 텍스트
        Text(
            text = "Current Text: $text"
        )

        // 텍스트를 변경하는 입력 필드
        OutlinedTextField(
            value = text,
            onValueChange = { newText -> 
                text = newText 
            },
            label = { 
                Text("Enter text") 
            }
        )

        // 버튼 클릭 시 텍스트 초기화
        Button(
            onClick = { 
                text = ""
            },
            modifier = Modifier.padding(
                top = 16.dp
            )
        ) {
            Text(text = "Clear Text")
        }
    }
}
```
<br/>
<br/>

## State Hosting
State Hosting 은 컴포저블의 상태를 외부로 이동시켜 UI 컴포저블이 상태를 직접 관리하지 않도록 하는 패턴이다.<br/>
이는 컴포저블을 stateless 하게 만들어, 상태를 외부에서 관리하게 하고, 상태 변경 로직을 부모나 외부 컴포저블에서 처리하도록 한다.<br/>
이 패턴을 사용하면 UI 컴포저블은 단지 UI를 그리는 역할만 하게 되어 테스트 용이성 및 재사용성이 높아진다.<br/>

```kotlin
@Composable
fun ExampleStateHosting() {
    // 상태 정의: 평과 제곱미터 값을 저장하는 상태 변수
    var pyeong by rememberSaveable { mutableStateOf("23") }
    var squareMeter by rememberSaveable { mutableStateOf((23 * 3.306).toString()) }

    // 상태를 Stateless 컴포저블에 전달
    ExampleStateless(
        pyeong = pyeong,
        squareMeter = squareMeter,
        onPyeongChange = { newPyeong ->
            // 사용자가 입력한 값이 비어 있거나 숫자가 아닌 경우 처리
            if (newPyeong.isBlank()) {
                pyeong = ""
                squareMeter = ""
                return@ExampleStateless
            }
            val numericValue = newPyeong.toFloatOrNull() ?: return@ExampleStateless
            pyeong = newPyeong
            squareMeter = (numericValue * 3.306).toString() // 평을 제곱미터로 변환
        }
    )
}

@Composable
fun ExampleStateless(
    pyeong: String,
    squareMeter: String,
    onPyeongChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        // 평을 입력받는 OutlinedTextField
        OutlinedTextField(
            value = pyeong,
            onValueChange = onPyeongChange, // 부모 컴포저블에서 전달된 콜백을 통해 상태 변경
            label = { Text("평") }
        )
        // 제곱미터는 읽기 전용으로 표시
        OutlinedTextField(
            value = squareMeter,
            onValueChange = {}, // 상태를 변경하지 않음
            label = { Text("제곱미터") },
            readOnly = true // 제곱미터는 읽기 전용
        )
    }
}
```
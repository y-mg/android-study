![banner](./android.png)
# Memory
- - -
안드로이드 OS 에서 메모리는 힙(Heap)과 스택(Stack)을 통해 관리된다.<br/>
앱의 성능을 최적화하고 메모리 누수(Memory Leak)를 방지하려면 이 두 영역을 효율적으로 관리하는 것이 중요하다.<br/>
<br/>
<br/>

## Heap
힙은 런타임 동안 동적으로 메모리를 할당하는 공간으로, 객체와 배열 같은 데이터 구조가 저장된다.<br/>
안드로이드 앱에서 대부분의 객체가 힙에 할당되며, GC(Garbage Collection)를 통해 자동으로 해제된다.<br/>
과도한 힙 사용 시 OutOfMemoryError(OOM) 가 발생할 수 있다.<br/>

```kotlin
class LargeObject {
    private val data = ByteArray(10 * 1024 * 1024) // 10MB 크기의 배열
}
```
```kotlin
// 실행 시 java.lang.OutOfMemoryError: Java heap space
@Test
fun `힙 사용`() {
    val list = mutableListOf<LargeObject>()
    for (i in 0 until 100) {
        list.add(LargeObject()) // 힙 메모리를 빠르게 소비
    }
}
```
<br/>
<br/>

## Stack
스택은 함수 호출 시 관련된 로컬 변수, 매개변수, 반환 주소 등을 저장하는 공간이다.<br/>
LIFO(Last In First Out, 후입선출) 방식으로 동작하며, 함수가 호출될 때 데이터가 저장되고, 함수 실행이 종료되면 자동으로 제거된다.<br/>
함수 호출이 깊어지면 StackOverflowError 가 발생할 수 있다.<br/>

```kotlin
// count 파라미터에 100000 를 넣고 실행 시 java.lang.StackOverflowError
fun exampleUsedStack(count: Int) {
    if (count == 0) {
        return
    }
    exampleUsedStack(count - 1) // 깊은 재귀 호출
}
```
```kotlin
@Test
fun `스택 사용`() {
    exampleUsedStack(100000)
}
```
<br/>
<br/>

## Garbage Collection(GC)
GC는 JVM 에서 제공하는 기능으로, 더 이상 사용되지 않는 객체를 자동으로 해제하여 메모리를 확보한다.<br/>
자동으로 메모리를 관리하기에 개발자가 명시적으로 메모리를 해제할 필요 없으나, GC 빈도가 높으면 성능 저하가 일어난다.<br/>
따라서 객체 생성을 최소화하고 재사용 가능한 객체를 만들어서 사용되는 것이 권장된다.<br/>
<br/>
<br/>

## Memory Leak
Memory Leak 은 사용이 끝난 객체가 GC 에 의해 해제되지 않아 불필요한 메모리 사용이 지속되는 현상이다.<br/>
Memory Leak 이 심해지면 OutOfMemoryError 가 발생할 수 있다.<br/>
Memory Leak 을 방지하기 위해서는 사용이 끝난 리소스를 해제하여 메모리를 정리해야 한다.<br/>

```kotlin
class MainActivity : AppCompatActivity() {
    private var myHandler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myHandler = Handler(Looper.getMainLooper())
    }

    override fun onDestroy() {
        super.onDestroy()
        myHandler?.removeCallbacksAndMessages(null) // 핸들러에서 메시지 제거하여 메모리 누수 방지
        myHandler = null
    }
}
```
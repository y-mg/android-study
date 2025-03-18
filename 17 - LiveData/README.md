![banner](./jetpack.png)
# LiveData
`LiveData` 는 데이터 변경에 대한 이벤트를 수신하는 데이터 홀더 클래스이다.<br/>
`LiveData` 는 생명주기를 인식하고, `Activity`, `Fragment`, `ViewModel` 등의 생명주기에 맞춰 데이터를 자동으로 구독하고 해제하여 메모리 누수를 방지한다.<br/>
<br/>
<br/>

## LifecycleOwner
`LifecycleOwner` 는 `Activity` 나 `Fragment` 의 생명주기를 관리하는 객체이다.<br/>
`LiveData` 는 `LifecycleOwner` 를 통해 생명주기를 인식하고, 해당 객체가 활성화되어 있을 때만 데이터를 구독하고 업데이트를 처리한다.<br/>
즉, `LifecycleOwner` 를 통해 `LiveData` 가 관찰하는 동안, `LiveData` 는 관찰자의 생명주기 상태에 맞춰 구독을 자동으로 관리한다.<br/>
예를 들어, `Activity` 가 `onStart()` 상태일 때만 데이터를 구독하고, `onStop()` 상태에서는 자동으로 구독을 해제하여 메모리 누수를 방지한다.<br/>
<br/>
<br/>

## LiveData 의 동작 방식
### setValue()
`setValue()` 는 메인(UI) 스레드에서 실행되어 값을 즉시 반영한다.<br/>
또한, 메인 스레드에서 호출되어야 하며, UI 의 변화를 반영할 때 적합하다.<br/>
<br/>

### postValue()
`postValue()` 는 백그라운드 스레드에서 값을 설정할 수 있으며, 메인 스레드로 값을 전달하여 업데이트된다.<br/>
또한, 백그라운드 작업 중에도 안전하게 값을 할당할 수 있게 해준다.<br/>
그러나 `postValue()` 는 호출한 직후에 `getValue()` 를 통해 값을 읽을 수 없을 수 있는데, 이는 값이 메인 스레드로 전달되는 데 시간이 걸리기 때문이다.<br/>
<br/>

### 예제 코드
```kotlin
class MainViewModel : ViewModel() {
    private val _liveData = MutableLiveData<String>()
    val liveData: LiveData<String> get() = _liveData

    fun exampleSetValue() {
        _liveData.value = "1"
        println("Value: ${_liveData.value}") // 1
        _liveData.value = "2"
        println("Value: ${_liveData.value}") // 2
    }

    fun examplePostValue() {
        _liveData.postValue("1")
        println("Value: ${_liveData.value}") // null(메인 스레드 반영 전)
        _liveData.postValue("2")
        println("Value: ${_liveData.value}") // null(메인 스레드 반영 전)
    }
}
```
<br/>
<br/>

## LiveData 구독
```kotlin
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // LiveData 구독
        viewModel.liveData.observe(this) {
            println(it)
        }
    }
}
```
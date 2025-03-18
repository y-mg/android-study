![banner](./jetpack.png)
# Data Binding
- - -
Data Binding 은 XML 레이아웃 파일과 데이터 소스 간에 자동으로 바인딩을 설정해주는 기능이다.<br/>
이 기능을 통해 UI 와 데이터의 결합을 코드에서 직접적으로 하지 않고도 양방향 데이터와 UI 의 동기화가 가능하다.<br/>
Data Binding 은 MVVM 패턴에서 주로 활용되며, View 와 ViewModel 간의 의존성을 줄여 코드의 가독성과 유지보수성을 높여준다.<br/>
XML 파일에서 ViewModel 의 데이터를 참조하거나, 변경 사항이 UI에 자동으로 반영되도록 설정할 수 있기 때문에 더 직관적이고 효율적인 UI 업데이트를 제공한다.<br/>
<br/>
<br/>

## Setup
```groovy
// 모듈 단의 build.gradle.kts
android {
    viewBinding {
        enable = true
    }
    dataBinding {
        enable = true
    }
}
```
<br/>
<br/>

## 예제 코드
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main"
    tools:context=".MainActivity">

    <data>
        <variable
            name="viewModel"
            type="com.ymg.databinding.MainViewModel" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.text}" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:onClick="@{() -> viewModel.onButtonClicked()}"
            app:addText="@{`Button`}" />

    </LinearLayout>

</layout>
```
```kotlin
class MainViewModel : ViewModel() {
    private val _text = MutableLiveData("Title")
    val text: LiveData<String> get() = _text

    fun onButtonClicked() {
        println("클릭")
    }
}
```
```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            lifecycleOwner = this@MainActivity
            viewModel = this@MainActivity.viewModel
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
```
<br/>
<br/>
<br/>



# BindingAdapter
- - -
BindingAdapter 는 XML 에서 커스텀 속성을 정의하고 이를 처리할 수 있는 어노테이션 기능입니다.<br/>
Data Binding 을 사용하는 프로젝트에서 커스텀 속성을 만들어 뷰의 특정 동작이나 스타일을 설정하는 로직을 캡슐화할 수 있다.<br/>
예를 들어, 이미지 로딩 라이브러리를 이용해 URL 을 이미지로 표시하거나, 특정 조건에 따라 UI 요소를 동적으로 변경하고자 할 때 BindingAdapter 를 사용하여 XML 파일에서 해당 속성을 설정할 수 있다.<br/>

## Setup
```toml
# gradle/libs.versions.toml
[plugins]
kotlin-kapt = { id = "org.jetbrains.kotlin.kapt", version.ref = "kotlin" }
```
```groovy
// 최상위 build.gradle.kts
plugins {
    alias(libs.plugins.kotlin.kapt) apply false
}
```
```groovy
// 모듈 단 build.gradle.kts
plugins {
    alias(libs.plugins.kotlin.kapt)
}
```
<br/>
<br/>

## 예제 코드
```kotlin
// 커스텀 BindingAdapter 함수 정의
@BindingAdapter("addText")
fun TextView.addText(text: String) {
    this.text = "$text BindingAdapter"
}
```
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main"
    tools:context=".MainActivity">

    <data>
        <variable
            name="viewModel"
            type="com.ymg.databinding.MainViewModel" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="@{viewModel.text}" />

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:onClick="@{() -> viewModel.onButtonClicked()}"
            app:addText="@{`Button`}" />

    </LinearLayout>

</layout>
```
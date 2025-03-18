![banner](./android.png)
# Fragment
- - -
프래그먼트(`Fragment`)는 하나의 액티비티 내에서 독립적으로 동작하는 UI 구성 요소로, 화면을 분할하거나 재사용성을 높이는 데 사용된다.<br/>
프래그먼트는 항상 액티비티에 종속되며 독립적으로 실행될 수 없으나, 여러 액티비티에서 동일한 프래그먼트를 재사용할 수 있기 때문에 UI 를 효율적으로 관리하는 데 유용하다.<br/>
프래그먼트는 보통 프래그먼트 트랜잭션을 통해 액티비티와 상호작용하며, 뷰 계층을 관리하거나 액티비티와 데이터를 공유하는 등의 작업을 한다.<br/>
<br/>
<br/>

## FragmentManager
`FragmentManager` 는 프래그먼트를 추가, 삭제, 교체하는 트랜잭션을 관리하는 클래스로, 이를 통해 프래그먼트를 동적으로 추가하거나 제거할 수 있으며, 백스택을 관리하여 사용자가 이전 프래그먼트로 돌아갈 수 있게 한다.<br/>
프래그먼트 트랜잭션을 수행할 때는 `FragmentTransaction` 을 사용하며, 트랜잭션을 안전하게 처리하려면 `FragmentContainerView` 를 사용하는 것이 권장된다.<br/>
이는 프래그먼트가 액티비티의 생명 주기를 따르며, 호스트 액티비티나 다른 프래그먼트의 생명 주기를 초과할 수 없도록 보장하기 때문이다.<br/>
<br/>
<br/>
<br/>



# Fragment Lifecycle
- - -
프래그먼트는 액티비티와 마찬가지로 여러 상태를 거치며, 이에 따라 특정 생명 주기 메서드가 호출된다.<br/>
프래그먼트의 생명 주기는 액티비티의 생명 주기와 밀접하게 연관되어 있다.<br/>
![fragment-lifecycle](./fragment-lifecycle.png)
<br/>
<br/>

## onAttach()
프래그먼트가 액티비티에 처음 연결될 때 호출된다.<br/>

```kotlin
override fun onAttach(context: Context) {
    super.onAttach(context)
    Log.d("MainFragment", "onAttach() 호출")
}
```
<br/>
<br/>

## onCreate()
프래그먼트가 생성될 때 호출되며, 이때 UI 관련 작업은 이루어지지 않는다.<br/>

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d("MainFragment", "onCreate() 호출")
}
```
<br/>
<br/>

## onCreateView()
프래그먼트의 UI 를 그리기 위해 레이아웃을 inflate 할 때 호출되며, XML 레이아웃을 실제 뷰 객체로 변환한다.<br/>

```kotlin
override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
): View {
    Log.d("MainFragment", "onCreateView() 호출")
    _binding = FragmentMainBinding.inflate(inflater, container, false)
    return binding.root
}
```
<br/>
<br/>

## onViewCreated()
`onCreateView()` 에서 생성된 뷰가 반환되면 호출되며, 이 시점부터 뷰에 대한 로직을 처리할 수 있다.<br/>

```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    Log.d("MainFragment", "onViewCreated() 호출")

    binding.text.setOnClickListener {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SubFragment())
            .addToBackStack(null)
            .commit()
    }
}
```
<br/>
<br/>

## onViewStateRestored()
저장된 상태가 복원될 때 호출되며, 이 시점에서 뷰의 상태를 복구할 수 있다.<br/>

```kotlin
override fun onViewStateRestored(savedInstanceState: Bundle?) {
    super.onViewStateRestored(savedInstanceState)
    Log.d("MainFragment", "onViewStateRestored() 호출")
}
```
<br/>
<br/>

## onStart()
프래그먼트가 사용자에게 보여지기 직전에 호출된다.<br/>

```kotlin
override fun onStart() {
    super.onStart()
    Log.d("MainFragment", "onStart() 호출")
}
```
<br/>
<br/>

## onResume()
프래그먼트가 사용자와 상호작용 가능한 상태가 되면 호출된다.<br/>

```kotlin
override fun onResume() {
    super.onResume()
    Log.d("MainFragment", "onResume() 호출")
}
```
<br/>
<br/>

## onPause()
프래그먼트가 더 이상 사용자와 상호작용할 수 없을 때 호출된다.<br/>

```kotlin
override fun onPause() {
    super.onPause()
    Log.d("MainFragment", "onPause() 호출")
}
```
<br/>
<br/>

## onStop()
프래그먼트가 완전히 화면에서 사라지면 호출된다.<br/>

```kotlin
override fun onStop() {
    super.onStop()
    Log.d("MainFragment", "onStop() 호출")
}
```
<br/>
<br/>

## onSaveInstanceState()
생명 주기 메서드는 아니지만, 화면 회전이나 일시적인 상태 저장을 위해 호출됩니다.<br/>

```kotlin
override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    Log.d("MainFragment", "onSaveInstanceState() 호출")
}
```
<br/>
<br/>

## onDestroyView()
프래그먼트의 뷰가 제거될 때 호출된다.<br/>

```kotlin
override fun onDestroyView() {
    super.onDestroyView()
    Log.d("MainFragment", "onDestroyView() 호출")
}
```
<br/>
<br/>

## onDestroy()
프래그먼트가 완전히 제거될 때 호출된다.<br/>

```kotlin
override fun onDestroy() {
    super.onDestroy()
    Log.d("MainFragment", "onDestroy() 호출")
}
```
<br/>
<br/>

## onDetach()
프래그먼트가 액티비티에서 완전히 분리될 때 호출된다.<br/>

```kotlin
override fun onDetach() {
    super.onDetach()
    Log.d("MainFragment", "onDetach() 호출")
}
```
<br/>
<br/>
<br/>



# Fragment 전환 예시
- - -
## A → B 화면 이동
프래그먼트 A 가 onPause() 와 onStop() 을 호출한 후, 프래그먼트 B 가 생성된다.<br/>
프래그먼트 B 가 사용자에게 보이면 프래그먼트 A 의 뷰는 제거되고 프래그먼트 B 와 상호작용이 가능해진다.<br/>

```text
A: onPause()
A: onStop()
B: onAttach()
B: onCreate()
B: onCreateView()
B: onViewCreated()
B: onViewStateRestored()
B: onStart()
A: onDestroyView()
B: onResume()
```
<br/>
<br/>

## B → A 뒤로 가기
프래그먼트 B 가 제거되면서 다시 프래그먼트 A 의 생명 주기 메서드들이 호출되어 화면에 나타난다.<br/>
프래그먼트 B 는 뒤로 가기 동작이 완료되면 완전히 제거되고, 프래그먼트 A 는 다시 상호작용이 가능해진다.<br/>

```text
B: onPause()
B: onStop()
A: onCreateView()
A: onViewCreated()
A: onViewStateRestored()
A: onStart()
B: onDestroyView()
B: onDestroy()
B: onDetach()
A: onResume()
```
<br/>
<br/>
<br/>



# Fragment View Lifecycle
- - -
프래그먼트의 뷰 생명 주기는 `onCreateView()` 에서 뷰가 생성될 때 시작되며, `onDestroyView()` 에서 뷰가 제거될 때 끝난다.<br/>
프래그먼트 전체의 생명 주기와는 독립적으로 관리되며, `ViewLifecycleOwner` 를 통해 뷰의 생명 주기를 따르는 리소스나 작업을 관리할 수 있다.<br/>
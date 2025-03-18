![banner](./android.png)
# RecyclerView
- - -
`RecyclerView` 는 리스트 형태로 데이터를 효율적으로 보여주는 컨테이너로, 화면에 보이는 뷰를 재활용하여 성능을 최적화한다.<br/>
기존의 `ListView` 는 스크롤 시 보이는 만큼만 뷰를 생성하고 보이지 않으면 삭제하는 방식이었으며, 이로 인해 스크롤 시 성능 저하 문제가 발생할 수 있었다.<br/>
반면, `RecyclerView` 는 `ViewHolder` 패턴을 사용하여 재사용 가능한 뷰를 미리 생성하고, 필요할 때만 바인딩하여 성능을 개선한다.<br/>
즉, `RecyclerView` 는 화면에서 보이지 않게 된 아이템 뷰를 재사용하고, 필요할 때만 새로운 `ViewHolder` 를 생성하여 관리한다.<br/>
<br/>
<br/>

## RecyclerView 구성 요소
### LayoutManager
`RecyclerView` 의 아이템 배치 방식을 결정하는 컴포넌트로, `RecyclerView` 는 자체적으로 아이템 배치를 하지 않고, `LayoutManager` 에 의해 배치된다.<br/>
<br/>

### Adapter
`RecyclerView.Adapter` 는 데이터를 `ViewHolder` 에 전달하고, 뷰를 생성 및 바인딩하는 역할을 한다.<br/>
<br/>

### ViewHolder
`RecyclerView.ViewHolder` 는 아이템 뷰를 저장하고, `onBindViewHolder()` 호출 시 데이터와 함께 바인딩된다.<br/>
<br/>

### 예제 코드
```kotlin
data class Item(
    val title: String,
    val description: String
)

class ItemAdapter(
    private val items: List<Item>
) : RecyclerView.Adapter<ItemViewHolder>() {
    // 새로운 ViewHolder 생성
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    // 뷰에 데이터를 바인딩
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    // 데이터 개수 반환
    override fun getItemCount(): Int = items.size

    override fun getItemId(position: Int): Long = items[position].hashCode().toLong()
}

class ItemViewHolder(
    private val binding: ItemsViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Item) {
        binding.title.text = item.title
        binding.description.text = item.description
    }
}
```
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:id="@+id/title"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <TextView
        android:id="@+id/description"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</LinearLayout>
```
```kotlin
private fun setupRecyclerView() {
    binding.recyclerView.apply {
        adapter = ItemAdapter(listOf(
            Item("Title 1", "Description 1"),
            Item("Title 2", "Description 2"),
            Item("Title 3", "Description 3")
        ))
    }
}
```
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```
<br/>
<br/>

## RecyclerView 성능 향상 기법
### setHasStableIds() 
각 아이템에 고유 ID 를 부여하면 불필요한 `onBindViewHolder()` 호출을 줄여 성능을 향상할 수 있다.<br/>
다만, 데이터 리스트의 항목이 고유한 ID 를 가질 때만 적용 가능하다.<br/>

```kotlin
private fun setupRecyclerViewSetHasStableIds() {
    binding.recyclerView.apply {
        adapter = ItemAdapter(listOf(
            Item("Title 1", "Description 1"),
            Item("Title 2", "Description 2"),
            Item("Title 3", "Description 3")
        )).apply {
            setHasStableIds(true) // ID 가 고정되었음을 명시
        }
    }
}
```
<br/>

### setItemViewCacheSize()
스크롤 시 보이지 않는 뷰를 캐시에 저장하여 onBindViewHolder() 호출을 줄일 수 있다.<br/>
다만, 캐시 크기가 너무 크면 메모리 사용량이 증가할 수 있다.<br/>

```kotlin
private fun setupRecyclerViewSetItemViewCacheSize() {
    binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = ItemAdapter(listOf(
            Item("Title 1", "Description 1"),
            Item("Title 2", "Description 2"),
            Item("Title 3", "Description 3")
        ))
        setItemViewCacheSize(10) // 10개의 뷰를 캐시에 유지
    }
}
```
<br/>

### setHasFixedSize()
아이템의 크기가 변경되지 않는 경우 레이아웃을 다시 계산하지 않도록 설정하여 성능을 향상할 수 있다.<br/>
다만, 아이템의 크기가 동적으로 변하지 않을 때 사용할 수 있다.<br/>

```kotlin
private fun setupRecyclerViewSetHasFixedSize() {
    binding.recyclerView.apply {
        layoutManager = LinearLayoutManager(context)
        adapter = ItemAdapter(listOf(
            Item("Title 1", "Description 1"),
            Item("Title 2", "Description 2"),
            Item("Title 3", "Description 3")
        ))
        setHasFixedSize(true) // 크기가 고정된 경우 성능 최적화
    }
}
```
<br/>

### setRecycledViewPool()
여러 개의 `RecyclerView` 가 있을 때, `ViewHolder` 를 공유하여 성능을 향상할 수 있다.<br/>
다만, 동일한 `ViewType` 을 가진 `RecyclerView` 가 여러 개 있을 때 효과적이다.<br/>

```kotlin
data class ParentItem(
    val category: String,
    val children: List<ChildItem>
)
class ParentAdapter(
    private val items: List<ParentItem>
) : RecyclerView.Adapter<ParentViewHolder>() {
    // 모든 Child RecyclerView 가 공유할 ViewPool 생성
    private val sharedPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {
        val binding = ItemParentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ParentViewHolder(binding, sharedPool)
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
class ParentViewHolder(
    private val binding: ItemParentBinding,
    private val pool: RecyclerView.RecycledViewPool
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(parentItem: ParentItem) {
        binding.category.text = parentItem.category // 상위 제목

        // 가로 Child RecyclerView 설정
        binding.childRecyclerView.apply {
            adapter = ChildAdapter(parentItem.children)
            setRecycledViewPool(pool) // 부모 RecyclerView 에서 공유하는 ViewPool 설정
        }
    }
}
```
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <TextView
        android:id="@+id/category"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/child_recycler_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal"
        app:layoutManager="androidx.recyclerview.widget.LinearLayoutManager" />

</LinearLayout>
```
```kotlin
data class ChildItem(
    val title: String
)

class ChildAdapter(
    private val items: List<ChildItem>
) : RecyclerView.Adapter<ChildViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
        val binding = ItemChildBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

class ChildViewHolder(
    private val binding: ItemChildBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ChildItem) {
        binding.text.text = item.title
    }
}
```
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

</LinearLayout>
```
```kotlin
private fun setupParentRecyclerView() {
    val parentList = listOf(
        ParentItem("Category 1", listOf(ChildItem("Item A"), ChildItem("Item B"), ChildItem("Item C"))),
        ParentItem("Category 2", listOf(ChildItem("Item D"), ChildItem("Item E"), ChildItem("Item F"))),
        ParentItem("Category 3", listOf(ChildItem("Item G"), ChildItem("Item H"), ChildItem("Item I")))
    )

    binding.recyclerView.apply {
        adapter = ParentAdapter(parentList)
    }
}
```
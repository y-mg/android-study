![banner](./android.png)
# DiffUtil
`DiffUtil` 은 `RecyclerView` 의 데이터가 변경될 때, 불필요한 `ViewHolder` 갱신을 최소화하여 성능을 최적화하는 유틸리티이다.<br/>
일반적으로 `RecyclerView.Adapter` 의 `notify···()` 함수로 전체 아이템을 갱신할 수 있지만, 변경이 필요하지 않은 항목까지 갱신되면서 리소스 낭비가 발생할 수 있다.<br/>
`DiffUtil` 은 두 데이터 셋을 비교하여 변경된 부분만을 `RecyclerView` 에 반영하며, 이를 통해 성능을 크게 향상시킬 수 있다.<br/>
`DiffUtil.Callback` 을 상속하여 구현하며, 비교 작업은 백그라운드 스레드에서 실행된다.<br/>
<br/>
<br/>

## 주요 메서드
### areItemsTheSame(oldItem: T, newItem: T)
두 객체가 동일한지 비교하여 동일한 항목인지 확인한다.<br/>
예를 들어, 아이템의 고유 ID를 기준으로 비교한다.<br/>
<br/>

### areContentsTheSame(oldItem: T, newItem: T)
객체의 내용이 같은지 확인한다.<br/>
예를 들어, 데이터 객체의 모든 속성이 동일한지 비교한다.<br/>
<br/>

### 예제 코드
```kotlin
data class Data(
    val id: Int,
    val title: String,
    val description: String
)
```
```kotlin
val diffUtil = object : DiffUtil.ItemCallback<Data>() {
    override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
        return oldItem == newItem
    }
}
```
<br/>
<br/>
<br/>



# AsyncListDiffer
`AsyncListDiffer` 는 `DiffUtil` 을 쉽게 사용할 수 있게 해주는 클래스로, 데이터 갱신 및 참조를 위한 효율적인 방법을 제공한다.<br/>
`DiffUtil.Callback` 을 전달하여 초기화하며, `submitList()` 로 데이터를 갱신하고 `currentList()` 로 데이터에 접근할 수 있다.<br/>
`AsyncListDiffer` 는 백그라운드에서 차이를 계산하고 UI 스레드에서 안전하게 갱신을 처리한다.<br/>

```kotlin
class AsyncListDifferDataAdapter : RecyclerView.Adapter<DataViewHolder>() {
    // AsyncListDiffer 객체 초기화
    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        differ.currentList.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    // 외부에서 데이터를 갱신할 때 사용하는 메서드
    fun submitData(list: List<Data>) {
        differ.submitList(list)
    }
}
```
<br/>
<br/>
<br/>



# ListAdapter
`ListAdapter` 는 `AsyncListDiffer` 를 내부적으로 사용하여, `RecyclerView` 데이터 갱신을 더욱 간편하게 할 수 있게 한 어댑터 클래스이다.<br/>
`DiffUtil.ItemCallback` 을 사용하여 데이터 비교를 처리하므로, `ListAdapter` 를 활용하면 `RecyclerView.Adapter` 보다 더 효율적인 방식으로 데이터 변경 사항을 처리할 수 있다.<br/>
기본적으로 `ListAdapter` 는 `AsyncListDiffer` 를 사용하며, `submitList()` 로 데이터를 갱신하고 자동으로 변경된 부분만을 UI 에 반영한다.<br/>

```kotlin
class DataAdapter : ListAdapter<Data, DataViewHolder>(
    object : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDataBinding.inflate(inflater, parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        // ListAdapter 에서는 getItem(position) 을 사용하여 리스트에서 아이템을 가져옴
        // currentList[position] 와 동일하지만, getItem(position) 을 사용하는 것이 더 직관적이며 가독성이 좋음
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    // 외부에서 데이터를 갱신할 때 사용하는 메서드
    fun submitData(list: List<Data>) {
        submitList(list)
    }
}
```
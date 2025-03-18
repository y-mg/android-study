![banner](./android.png)
# Serializable
- - -
`Serializable` 은 Java 의 표준 인터페이스로, 객체를 바이트 스트림으로 변환하여 저장하거나, 바이트 스트림을 객체로 복원하는 작업을 수행한다.<br/>
추가적인 메소드 구현 없이 직렬화를 처리할 수 있지만, Reflection 을 사용하여 직렬화를 처리하기 때문에 성능 저하가 발생할 수 있으며, 안드로이드 환경에서는 성능에 영향을 미칠 수 있다.<br/>

```kotlin
data class MySerializableObject(
    val data: Int,
    val name: String
) : Serializable {
    // 직렬화할 필드 정의
}
```
<br/>
<br/>
<br/>



# Parcelable
- - -
`Parcelable` 은 안드로이드에서 제공하는 인터페이스로, 객체를 `Parcel` 형태로 직렬화하여 안드로이드 컴포넌트 간에 효율적으로 데이터를 전송하는 방식이다.<br/>
직렬화 및 역직렬화 과정을 명시적으로 작성해야 하지만, 안드로이드의 IPC(Inter-Process Communication)에 최적화되어 성능 면에서 매우 유리하다.<br/>

```kotlin
data class MyParcelableObject(
    val data: Int,
    val name: String
) : Parcelable {
    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MyParcelableObject> = object : Parcelable.Creator<MyParcelableObject> {
            override fun createFromParcel(parcel: Parcel): MyParcelableObject {
                return MyParcelableObject(parcel)
            }

            override fun newArray(size: Int): Array<MyParcelableObject?> {
                return arrayOfNulls(size)
            }
        }
    }

    constructor(parcel: Parcel) : this(
        data = parcel.readInt(),
        name = parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(data)
        parcel.writeString(name)
    }

    override fun describeContents(): Int = 0
}
```
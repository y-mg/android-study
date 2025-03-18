package com.ymg.serialization

import android.os.Parcel
import android.os.Parcelable

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
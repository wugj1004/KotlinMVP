package com.wugj.mykotlin.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * description:
 * </br>
 * author: wugj
 * </br>
 * date: 2019/6/5
 * </br>
 * version:
 */

class LatlngEntity() : Parcelable {

    var longitude: String? = null
    var latitude: String? = null

    constructor(parcel: Parcel) : this() {
        longitude = parcel.readString()
        latitude = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(longitude)
        parcel.writeString(latitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LatlngEntity> {
        override fun createFromParcel(parcel: Parcel): LatlngEntity {
            return LatlngEntity(parcel)
        }

        override fun newArray(size: Int): Array<LatlngEntity?> {
            return arrayOfNulls(size)
        }
    }


}
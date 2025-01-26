package com.taylanozgurozdemir.yemektariflerim

import android.os.Parcel
import android.os.Parcelable

data class Yemek(
    val isim: String,
    val tarif: String,
    val malzemeler: String
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(isim)
        parcel.writeString(tarif)
        parcel.writeString(malzemeler)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Yemek> {
        override fun createFromParcel(parcel: Parcel): Yemek {
            return Yemek(parcel)
        }

        override fun newArray(size: Int): Array<Yemek?> {
            return arrayOfNulls(size)
        }
    }
}
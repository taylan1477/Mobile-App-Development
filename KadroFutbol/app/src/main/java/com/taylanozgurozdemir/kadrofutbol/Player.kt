package com.taylanozgurozdemir.kadrofutbol

import android.os.Parcel
import android.os.Parcelable

data class Player(
    val name: String,
    val age: Int,
    val position: String,
    val jerseyNumber: Int,
    val matchesPlayed: Int,
    val goalsScored: Int,
    val photoUrl: Int
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeString(position)
        parcel.writeInt(jerseyNumber)
        parcel.writeInt(matchesPlayed)
        parcel.writeInt(goalsScored)
        parcel.writeInt(photoUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Player> {
        override fun createFromParcel(parcel: Parcel): Player {
            return Player(parcel)
        }

        override fun newArray(size: Int): Array<Player?> {
            return arrayOfNulls(size)
        }
    }
}
package com.example.primeiroapp

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Local(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    var lat: Double,
    var lng: Double
) : Parcelable
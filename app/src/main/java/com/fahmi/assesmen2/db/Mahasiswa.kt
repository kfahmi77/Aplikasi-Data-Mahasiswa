package com.fahmi.assesmen2.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "tabel_mahasiswa")
data class Mahasiswa(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nim: String,
    val namaDepan: String,
    val namaBelakang: String,
    val usia: Int
) : Parcelable
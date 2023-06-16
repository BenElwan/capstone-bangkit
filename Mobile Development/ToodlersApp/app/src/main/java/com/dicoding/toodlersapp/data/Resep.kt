package com.dicoding.toodlersapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Resep(
    val photo: String,
    val namaResep: String,
    val deskripsi: String,
    val porsi : String,
    val bahan: String,
    val cara: String,
    val nilai: String
): Parcelable

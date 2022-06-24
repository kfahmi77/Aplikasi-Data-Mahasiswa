package com.fahmi.assesmen2.db

import com.squareup.moshi.Json

data class Picture(
    val id: String,
    @Json(name = "img_src")
    val pictureUrl: String
)
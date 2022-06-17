package com.fahmi.assesmen2.network

import com.squareup.moshi.Json

data class Picture (
    val id: String,
    @Json(name = "img_src")
    val imgSrcUrl: String
        )
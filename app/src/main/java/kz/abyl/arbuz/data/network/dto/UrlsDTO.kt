package kz.abyl.arbuz.data.network.dto

import com.squareup.moshi.Json

data class UrlsDTO(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
    @field:Json(name = "small_s3")
    val smallS3: String,
)
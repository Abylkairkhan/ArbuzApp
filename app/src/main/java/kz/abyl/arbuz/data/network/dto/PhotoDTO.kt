package kz.abyl.arbuz.data.network.dto

import com.squareup.moshi.Json

data class PhotoDTO(
    val id: String,
    val width: Long,
    val height: Long,
    val color: String,
    val description: String?,
    @field:Json(name = "alt_description")
    val altDescription: String?,
    val urls: UrlsDTO,
    val likes: Long,
)

package kz.abyl.arbuz.domain.model

data class Photo(
    val id: String,
    val width: Long,
    val height: Long,
    val color: String,
    val description: String?,
    val altDescription: String?,
    val likes: Long,
    val urlFull: String,
    val urlRegular: String,
    val urlSmall: String,

    var countInBucket: Int = 0
)

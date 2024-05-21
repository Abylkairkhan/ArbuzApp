package kz.abyl.arbuz.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo")
data class PhotoEntity(
    @PrimaryKey
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
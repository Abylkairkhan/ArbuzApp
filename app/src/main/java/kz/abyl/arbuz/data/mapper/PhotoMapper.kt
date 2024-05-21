package kz.abyl.arbuz.data.mapper

import kz.abyl.arbuz.data.local.entity.PhotoEntity
import kz.abyl.arbuz.data.network.dto.PhotoDTO
import kz.abyl.arbuz.domain.model.Photo

fun PhotoDTO.toPhoto(): Photo {
    return Photo(
        id = id,
        width = width,
        height = height,
        color = color,
        description = description,
        altDescription = altDescription,
        likes = likes,
        urlFull = urls.full,
        urlRegular = urls.regular,
        urlSmall = urls.small
    )
}

fun Photo.toPhotoEntity(): PhotoEntity {
    return PhotoEntity(
        id = id,
        width = width,
        height = height,
        color = color,
        description = description,
        altDescription = altDescription,
        likes = likes,
        urlFull = urlFull,
        urlRegular = urlRegular,
        urlSmall = urlSmall
    )
}

fun PhotoEntity.toPhoto(): Photo {
    return Photo(
        id = id,
        width = width,
        height = height,
        color = color,
        description = description,
        altDescription = altDescription,
        likes = likes,
        urlFull = urlFull,
        urlRegular = urlRegular,
        urlSmall = urlSmall,
        countInBucket = countInBucket
    )
}
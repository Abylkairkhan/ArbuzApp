package kz.abyl.arbuz.domain.repository

import kotlinx.coroutines.flow.Flow
import kz.abyl.arbuz.data.local.entity.PhotoEntity
import kz.abyl.arbuz.domain.model.Photo
import kz.abyl.arbuz.util.Resource

interface PhotoRepository {

    suspend fun getListOfPhotos(page: Int): Flow<Resource<List<Photo>>>

    suspend fun insertOrUpdatePhotoToDatabase(photo: Photo)

    suspend fun decreaseOrDeletePhotoToDatabase(photo: Photo)

    suspend fun getAllPhotosFromDatabase(): Flow<Resource<List<Photo>>>

    suspend fun getPhotoCountFromDatabase(): Int

}
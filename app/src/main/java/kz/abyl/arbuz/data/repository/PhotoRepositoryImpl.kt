package kz.abyl.arbuz.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kz.abyl.arbuz.data.local.PhotoDatabase
import kz.abyl.arbuz.data.mapper.toPhoto
import kz.abyl.arbuz.data.mapper.toPhotoEntity
import kz.abyl.arbuz.data.network.PhotoAPI
import kz.abyl.arbuz.domain.model.Photo
import kz.abyl.arbuz.domain.repository.PhotoRepository
import kz.abyl.arbuz.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoAPI: PhotoAPI,
    private val photoDatabase: PhotoDatabase
) : PhotoRepository {
    override suspend fun getListOfPhotos(page: Int): Flow<Resource<List<Photo>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val response = photoAPI.getListOfPhotos(page)
                val photos = response.map { it.toPhoto() }
                emit(Resource.Success(photos))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data: ${e.message}"))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data: ${e.message()}"))
            }
            emit(Resource.Loading(false))
        }
    }

    override suspend fun decreaseOrDeletePhotoToDatabase(photo: Photo) {
        val existingPhoto = photoDatabase.dao.getPhotoByID(photo.id)
        if (existingPhoto != null  && existingPhoto.countInBucket != 1) {
            photoDatabase.dao.decrementPhotoCountInBucket(photo.id)
        } else {
            photoDatabase.dao.deletePhotoById(photo.id)
        }
    }

    override suspend fun insertOrUpdatePhotoToDatabase(photo: Photo) {
        val existingPhoto = photoDatabase.dao.getPhotoByID(photo.id)
        if (existingPhoto != null) {
            photoDatabase.dao.incrementPhotoCountInBucket(photo.id)
        } else {
            photoDatabase.dao.insertPhoto(photo.toPhotoEntity())
        }
    }

    override suspend fun getAllPhotosFromDatabase(): Flow<Resource<List<Photo>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val photoEntities = photoDatabase.dao.getAllPhotos()
                val photos = photoEntities.map { it.toPhoto() }
                emit(Resource.Success(photos))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data: ${e.message}"))
            }
            emit(Resource.Loading(false))
        }
    }
}
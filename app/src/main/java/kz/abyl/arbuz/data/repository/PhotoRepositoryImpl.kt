package kz.abyl.arbuz.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kz.abyl.arbuz.data.mapper.toPhoto
import kz.abyl.arbuz.data.network.PhotoAPI
import kz.abyl.arbuz.domain.model.Photo
import kz.abyl.arbuz.domain.repository.PhotoRepository
import kz.abyl.arbuz.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoAPI: PhotoAPI
): PhotoRepository {
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

}
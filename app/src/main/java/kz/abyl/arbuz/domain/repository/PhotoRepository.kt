package kz.abyl.arbuz.domain.repository

interface PhotoRepository {

    suspend fun getListOfPhotos()

}
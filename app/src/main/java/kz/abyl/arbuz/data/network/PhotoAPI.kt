package kz.abyl.arbuz.data.network

import kz.abyl.arbuz.data.network.dto.PhotoDTO
import kz.abyl.arbuz.util.Credentials
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoAPI {

    @GET("photos")
    suspend fun getListOfPhotos(
        @Query("page") page: Int,
        @Query("client_id") apiKey: String = Credentials.API_KEY
    ): List<PhotoDTO>

}
package kz.abyl.arbuz.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.abyl.arbuz.data.local.entity.PhotoEntity

@Dao
interface PhotoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(
        photoEntity: PhotoEntity
    )

    @Query("UPDATE photo SET countInBucket = countInBucket + 1 WHERE id = :photoId")
    suspend fun incrementPhotoCountInBucket(photoId: String)

    @Query("UPDATE photo SET countInBucket = countInBucket - 1 WHERE id = :photoId")
    suspend fun decrementPhotoCountInBucket(photoId: String)

    @Query("SELECT * FROM photo WHERE id = :photoId")
    suspend fun getPhotoByID(photoId: String): PhotoEntity?

    @Query("DELETE FROM photo WHERE id = :photoId")
    suspend fun deletePhotoById(
        photoId: String
    )

    @Query("SELECT * FROM photo")
    suspend fun getAllPhotos(): List<PhotoEntity>

}
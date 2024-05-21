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

    @Query("DELETE FROM photo WHERE id = :photoId")
    suspend fun deletePhotoById(
        photoId: String
    )

}
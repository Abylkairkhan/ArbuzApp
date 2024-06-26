package kz.abyl.arbuz.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.abyl.arbuz.data.local.entity.PhotoEntity

@Database(entities = [PhotoEntity::class], version = 1)
abstract class PhotoDatabase: RoomDatabase() {
    abstract val dao: PhotoDAO
}
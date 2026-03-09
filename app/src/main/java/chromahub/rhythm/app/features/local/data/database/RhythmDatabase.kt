package chromahub.rhythm.app.features.local.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import chromahub.rhythm.app.features.local.data.database.dao.SongDao
import chromahub.rhythm.app.features.local.data.database.entity.SongEntity

@Database(entities = [SongEntity::class], version = 1, exportSchema = false)
abstract class RhythmDatabase : RoomDatabase() {
    abstract fun songDao(): SongDao

    companion object {
        @Volatile
        private var INSTANCE: RhythmDatabase? = null

        fun getInstance(context: Context): RhythmDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RhythmDatabase::class.java,
                    "rhythm_database"
                ).fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}

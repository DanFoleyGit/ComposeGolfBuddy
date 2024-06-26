package com.example.composegolfbuddy.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.composegolfbuddy.data.dao.ClubDao
import com.example.composegolfbuddy.data.dao.RangeLogsDao
import com.example.composegolfbuddy.model.Club
import com.example.composegolfbuddy.model.RangeLog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Club class
@Database(
    entities = arrayOf(
        Club::class,
        RangeLog::class
    ), version = 1, exportSchema = false)
abstract class ClubRoomDatabase : RoomDatabase() {

    abstract fun getClubDao(): ClubDao
    abstract fun getRangeLogsDao(): RangeLogsDao

    private class ClubDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.getClubDao())
                }
            }
        }

        suspend fun populateDatabase(clubDao: ClubDao) {
            // Delete all content here.
            clubDao.deleteAll()

            // Add sample clubs.
            var club = Club("7i","28","TaylorMade","144","100", "80")
            clubDao.insert(club)
            club = Club( "8i","34", "TaylorMade","132", "100", "80")
            clubDao.insert(club)

        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ClubRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): ClubRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ClubRoomDatabase::class.java,
                    "club_database"
                ).addCallback(ClubDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

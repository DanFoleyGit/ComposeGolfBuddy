package composegolfbuddy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import composegolfbuddy.model.Club
import kotlinx.coroutines.flow.Flow

@Dao
interface ClubDao {

    @Query("SELECT * FROM club_table ORDER BY distance DESC")
    fun getAll(): Flow<List<Club>>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Club)

    @Query("DELETE FROM club_table")
    suspend fun deleteAll()


    @Query("SELECT * FROM club_table where club_name = :clubName")
    suspend fun retrieveClubByName(clubName: String): Club

    @Query("DELETE FROM club_table where club_name = :clubName")
    suspend fun deleteClubByName(clubName: String)
}
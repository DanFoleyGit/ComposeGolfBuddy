package composegolfbuddy.repositories

import androidx.annotation.WorkerThread
import composegolfbuddy.data.dao.ClubDao
import composegolfbuddy.model.Club
import kotlinx.coroutines.flow.Flow

class ClubsRepositoryImpl(private val clubDao: ClubDao) : ClubsRepository {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    override fun getAllClubs(): Flow<List<Club>> {
        return clubDao.getAll()
    }

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    override suspend fun insert(club: Club) {
        clubDao.insert(club)
    }

    override suspend fun retrieveClubByName(clubName: String): Club {
        return clubDao.retrieveClubByName(clubName)
    }

    override suspend fun deleteClub(club: Club) {
        clubDao.deleteClubByName(club.clubName)
    }
}
package com.example.composegolfbuddy.screens.data

import com.example.composegolfbuddy.model.Club
import com.example.composegolfbuddy.repositories.ClubsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ClubsRepositoryFake : ClubsRepository {

    override fun getAllClubs(): Flow<List<Club>> {
        return flow {
            val clubs = listOf(
                Club("D", "Brand", "Loft", "10", "20", "30"),
                Club("3i", "Brand", "Loft", "10", "20", "30"),
                Club("4i", "Brand", "Loft", "10", "20", "30")
            )
            emit(clubs) // Emit the list of clubs
        }
    }

    override suspend fun insert(club: Club) {
        TODO("Not yet implemented")
    }

    override suspend fun retrieveClubByName(clubName: String): Club {
        TODO("Not yet implemented")
    }

    override suspend fun deleteClub(club: Club) {
        TODO("Not yet implemented")
    }
}
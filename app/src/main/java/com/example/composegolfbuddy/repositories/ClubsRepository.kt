package com.example.composegolfbuddy.repositories
import com.example.composegolfbuddy.model.Club
import kotlinx.coroutines.flow.Flow

interface ClubsRepository {
    fun getAllClubs(): Flow<List<Club>>

    suspend fun insert(club : Club)

    suspend fun retrieveClubByName(clubName : String): Club

    suspend fun deleteClub(club : Club)
}
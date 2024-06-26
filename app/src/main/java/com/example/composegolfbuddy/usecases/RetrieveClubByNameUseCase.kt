package com.example.composegolfbuddy.usecases

import com.example.composegolfbuddy.model.Club
import com.example.composegolfbuddy.repositories.ClubsRepository
import javax.inject.Inject

class RetrieveClubByNameUseCase @Inject constructor(
    private val clubsRepository: ClubsRepository
) {
    suspend fun invoke(clubName: String): Club? {
        return clubsRepository.retrieveClubByName(clubName)
    }
}
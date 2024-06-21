package com.example.composegolfbuddy.usecases

import com.example.composegolfbuddy.repositories.ClubsRepository
import com.multiplatform.clubdistances.homeScreen.model.Club
import javax.inject.Inject

class AddClubUseCase @Inject constructor(
    private val clubsRepository: ClubsRepository
) {

    suspend fun invoke(club : Club) {
        clubsRepository.insert(club)
    }
}
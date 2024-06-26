package com.example.composegolfbuddy.usecases

import com.example.composegolfbuddy.model.Club
import com.example.composegolfbuddy.repositories.ClubsRepository
import javax.inject.Inject

class DeleteClubByNameUseCase @Inject constructor(
    private val clubRepository: ClubsRepository
) {
    suspend fun invoke(club: Club) {
        clubRepository.deleteClub(club)
    }
}

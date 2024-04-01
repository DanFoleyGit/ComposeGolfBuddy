package com.example.composegolfbuddy.usecases

import com.example.composegolfbuddy.repositories.ClubsRepository
import com.multiplatform.clubdistances.homeScreen.model.Club
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClubsStaticUseCase @Inject constructor(
    private val clubsRepository: ClubsRepository
) {

    fun invoke(): Flow<List<Club>> {
        // TODO convert this from flow to normal list
        return clubsRepository.getAllClubs()
    }

//    suspend fun invoke(): List<Club> {
//        // Create a MutableStateFlow with an initial value (empty list or any default value)
//        var list = emptyList<Club>()
//
//        // Launch a coroutine to collect the flow and update the MutableStateFlow
//        clubsRepository.getAllClubs().collect { clubsList ->
//            list = clubsList
//        }
//
//        // Return the MutableStateFlow
//        return list
//    }
}
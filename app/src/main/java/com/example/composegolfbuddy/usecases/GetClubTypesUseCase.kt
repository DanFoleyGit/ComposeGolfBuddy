package com.example.composegolfbuddy.usecases

import com.example.composegolfbuddy.repositories.ClubTypesRepository
import javax.inject.Inject

class GetClubTypesUseCase @Inject constructor(
    private val clubTypesRepository: ClubTypesRepository
) {
    fun invoke() : List<String> {
        return clubTypesRepository.getClubTypes()
    }
}
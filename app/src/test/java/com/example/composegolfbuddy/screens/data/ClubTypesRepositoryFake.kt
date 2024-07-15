package com.example.composegolfbuddy.screens.data

import com.example.composegolfbuddy.repositories.ClubTypesRepository

open class ClubTypesRepositoryFake: ClubTypesRepository {
    override fun getClubTypes(): List<String> {
        return listOf("D, 3i, 4i, 5i, 6i, 7i, 8i, 9i")
    }
}
package com.example.composegolfbuddy.di

import com.example.composegolfbuddy.data.dao.ClubDao
import com.example.composegolfbuddy.data.dao.RangeLogsDao
import com.example.composegolfbuddy.repositories.ClubTypesRepository
import com.example.composegolfbuddy.repositories.ClubsRepository
import com.example.composegolfbuddy.repositories.ClubsRepositoryImpl
import com.example.composegolfbuddy.repositories.RangeLogsRepository
import com.example.composegolfbuddy.repositories.RangeLogsRepositoryImpl
import com.multiplatform.clubdistances.homeScreen.model.ClubTypes
import com.multiplatform.clubdistances.updateClubs.repositories.ClubTypesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun provideClubsRepository(clubDao: ClubDao): ClubsRepository {
        return ClubsRepositoryImpl(clubDao)
    }

    @Provides
    fun provideClubTypesRepository(): ClubTypesRepository {
        return ClubTypesRepositoryImpl(ClubTypes())
    }

    @Provides
    fun provideRangeLogsRepository(rangeLogsDao: RangeLogsDao): RangeLogsRepository {
        return RangeLogsRepositoryImpl(rangeLogsDao)
    }
}

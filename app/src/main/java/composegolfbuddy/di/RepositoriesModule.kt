package composegolfbuddy.di

import com.multiplatform.clubdistances.homeScreen.model.ClubTypes
import com.multiplatform.clubdistances.updateClubs.repositories.ClubTypesRepositoryImpl
import composegolfbuddy.data.dao.ClubDao
import composegolfbuddy.data.dao.RangeLogsDao
import composegolfbuddy.repositories.ClubTypesRepository
import composegolfbuddy.repositories.ClubsRepository
import composegolfbuddy.repositories.ClubsRepositoryImpl
import composegolfbuddy.repositories.RangeLogsRepository
import composegolfbuddy.repositories.RangeLogsRepositoryImpl
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

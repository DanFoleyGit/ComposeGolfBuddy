package com.example.composegolfbuddy.di

import android.content.Context
import androidx.room.Room
import com.example.composegolfbuddy.data.ClubRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideClubDao(clubRooDatabase: ClubRoomDatabase) = clubRooDatabase.getClubDao()

    @Provides
    fun provideRangeLogsDao(clubRooDatabase: ClubRoomDatabase) = clubRooDatabase.getRangeLogsDao()

    @Singleton
    @Provides
    fun provideClubRoomDatabase(@ApplicationContext appContext: Context): ClubRoomDatabase {
        return Room.databaseBuilder(
            appContext,
            ClubRoomDatabase::class.java,
            "RssReader"
        ).build()
    }

}

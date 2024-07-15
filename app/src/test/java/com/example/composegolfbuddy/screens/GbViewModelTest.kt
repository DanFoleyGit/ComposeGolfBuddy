package com.example.composegolfbuddy.screens

import com.example.composegolfbuddy.screens.data.ClubTypesRepositoryFake
import com.example.composegolfbuddy.screens.data.ClubsRepositoryFake
import com.example.composegolfbuddy.usecases.AddClubUseCase
import com.example.composegolfbuddy.usecases.DeleteClubByNameUseCase
import com.example.composegolfbuddy.usecases.GetClubTypesUseCase
import com.example.composegolfbuddy.usecases.GetClubsStaticUseCase
import com.example.composegolfbuddy.usecases.RetrieveClubByNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GbViewModelTest {

    private lateinit var viewModel: GbViewModel
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {

        Dispatchers.setMain(testDispatcher)

        val getClubTypesUseCase = GetClubTypesUseCase(ClubTypesRepositoryFake())
        val retrieveClubByNameUseCase = RetrieveClubByNameUseCase(ClubsRepositoryFake())
        val addClubUseCase = AddClubUseCase(ClubsRepositoryFake())
        val getClubsUseCase = GetClubsStaticUseCase(ClubsRepositoryFake())
        val deleteClubByNameUseCase = DeleteClubByNameUseCase(ClubsRepositoryFake())

        viewModel = GbViewModel(
            getClubTypesUseCase,
            retrieveClubByNameUseCase,
            addClubUseCase,
            getClubsUseCase,
            deleteClubByNameUseCase
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test Update Variables and verify state`() = runTest {
        //TODO
    }

    @Test
    fun `test validation with fields`() = runTest {// Assert initial value
        //TODO
    }
}
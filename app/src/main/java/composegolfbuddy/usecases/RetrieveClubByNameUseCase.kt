package composegolfbuddy.usecases

import composegolfbuddy.model.Club
import composegolfbuddy.repositories.ClubsRepository
import javax.inject.Inject

class RetrieveClubByNameUseCase @Inject constructor(
    private val clubsRepository: ClubsRepository
) {
    suspend fun invoke(clubName: String): Club? {
        return clubsRepository.retrieveClubByName(clubName)
    }
}
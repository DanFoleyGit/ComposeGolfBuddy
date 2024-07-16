package composegolfbuddy.usecases

import composegolfbuddy.model.Club
import composegolfbuddy.repositories.ClubsRepository
import javax.inject.Inject

class AddClubUseCase @Inject constructor(
    private val clubsRepository: ClubsRepository
) {

    suspend fun invoke(club : Club) {
        clubsRepository.insert(club)
    }
}
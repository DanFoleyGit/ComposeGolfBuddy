package composegolfbuddy.usecases

import composegolfbuddy.model.Club
import composegolfbuddy.repositories.ClubsRepository
import javax.inject.Inject

class DeleteClubByNameUseCase @Inject constructor(
    private val clubRepository: ClubsRepository
) {
    suspend fun invoke(club: Club) {
        clubRepository.deleteClub(club)
    }
}

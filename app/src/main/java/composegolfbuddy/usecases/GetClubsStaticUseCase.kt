package composegolfbuddy.usecases

import composegolfbuddy.model.Club
import composegolfbuddy.repositories.ClubsRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetClubsStaticUseCase @Inject constructor(
    private val clubsRepository: ClubsRepository
) {

    suspend fun invoke(): List<Club> {
        return clubsRepository.getAllClubs().first().sortedByDescending {
            it.distance.toIntOrNull() ?: Int.MIN_VALUE
        }
    }
}
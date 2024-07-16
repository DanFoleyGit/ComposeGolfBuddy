package composegolfbuddy.usecases

import composegolfbuddy.repositories.ClubTypesRepository
import javax.inject.Inject

class GetClubTypesUseCase @Inject constructor(
    private val clubTypesRepository: ClubTypesRepository
) {
    fun invoke() : List<String> {
        return clubTypesRepository.getClubTypes()
    }
}
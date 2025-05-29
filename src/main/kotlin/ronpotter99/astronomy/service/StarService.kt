package ronpotter99.astronomy.service

import org.springframework.stereotype.Service
import ronpotter99.astronomy.repository.StarRepository
import ronpotter99.astronomy.entity.Star

@Service
class StarService(
    private val starRepository: StarRepository
) {

    fun getAllStars(): List<Star> {
        return starRepository.findAll()
    }
}
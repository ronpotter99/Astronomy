package ronpotter99.astronomy.service

import org.springframework.stereotype.Service
import ronpotter99.astronomy.entity.Star
import ronpotter99.astronomy.repository.StarRepository

@Service
class StarService(private val starRepository: StarRepository) : IStarService {

    override fun getAllStars(): List<Star> {
        return starRepository.findAll()
    }
}

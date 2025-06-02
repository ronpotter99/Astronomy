package ronpotter99.astronomy.service

import org.springframework.stereotype.Service
import ronpotter99.astronomy.repository.PlanetRepository
import ronpotter99.astronomy.entity.Planet

@Service
class PlanetService(
    private val planetRepository: PlanetRepository
) {

    fun getAllPlanets(): List<Planet> {
        return planetRepository.findAll()
    }
}
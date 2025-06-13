package ronpotter99.astronomy.service

import org.springframework.stereotype.Service
import ronpotter99.astronomy.entity.Planet
import ronpotter99.astronomy.repository.PlanetRepository

@Service
class PlanetService(private val planetRepository: PlanetRepository) : IPlanetService {

    override fun getAllPlanets(): List<Planet> {
        return planetRepository.findAll()
    }
}

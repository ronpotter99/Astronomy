package ronpotter99.astronomy.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import ronpotter99.astronomy.entity.Planet
import ronpotter99.astronomy.service.PlanetService

@RestController
class PlanetController(private val planetService: PlanetService) : IPlanetController {

    private val logger = KotlinLogging.logger {}

    override fun getAllPlanet(): List<Planet> {
        return planetService.getAllPlanets()
    }
}

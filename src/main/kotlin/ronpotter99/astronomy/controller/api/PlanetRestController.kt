package ronpotter99.astronomy.controller.api

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import ronpotter99.astronomy.controller.api.interfaces.IPlanetRestController
import ronpotter99.astronomy.entity.Planet
import ronpotter99.astronomy.service.PlanetService

@RestController
class PlanetRestController(private val planetService: PlanetService) : IPlanetRestController {

    private val logger = KotlinLogging.logger {}

    override fun getAllPlanet(): List<Planet> {
        return planetService.getAllPlanets()
    }
}

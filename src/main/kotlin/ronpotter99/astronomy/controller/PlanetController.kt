package ronpotter99.astronomy.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ronpotter99.astronomy.entity.Planet
import ronpotter99.astronomy.service.PlanetService

@RestController
@RequestMapping("/planet")
class PlanetController(private val planetService: PlanetService) {

    @GetMapping("/list")
    fun getAllPlanet(): List<Planet> {
        return planetService.getAllPlanets()
    }
}

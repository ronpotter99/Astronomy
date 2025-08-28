package ronpotter99.astronomy.controller.api.interfaces

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ronpotter99.astronomy.entity.Planet

@RequestMapping("/api/planet")
interface IPlanetRestController {

    @GetMapping("/list")
    fun getAllPlanet(): List<Planet>
}

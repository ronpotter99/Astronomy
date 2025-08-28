package ronpotter99.astronomy.controller.interfaces

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ronpotter99.astronomy.entity.Planet

@RequestMapping("/api/planet")
interface IPlanetController {

    @GetMapping("/list")
    fun getAllPlanet(): List<Planet>
}

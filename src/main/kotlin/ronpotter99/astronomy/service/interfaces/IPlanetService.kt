package ronpotter99.astronomy.service.interfaces

import ronpotter99.astronomy.entity.Planet

interface IPlanetService {

    fun getAllPlanets(): List<Planet>
}

package ronpotter99.astronomy.service.interfaces

import ronpotter99.astronomy.entity.Star

interface IStarService {

    fun getAllStars(): List<Star>
}

package ronpotter99.astronomy.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import ronpotter99.astronomy.entity.Star
import ronpotter99.astronomy.service.StarService

@RestController
class StarController(private val starService: StarService) : IStarController {

    private val logger = KotlinLogging.logger {}

    override fun getAllStars(): List<Star> {
        return starService.getAllStars()
    }
}

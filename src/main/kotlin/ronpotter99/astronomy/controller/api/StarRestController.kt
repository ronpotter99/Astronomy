package ronpotter99.astronomy.controller.api

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import ronpotter99.astronomy.controller.api.interfaces.IStarRestController
import ronpotter99.astronomy.entity.Star
import ronpotter99.astronomy.service.StarService

@RestController
class StarRestController(private val starService: StarService) : IStarRestController {

    private val logger = KotlinLogging.logger {}

    override fun getAllStars(): List<Star> {
        return starService.getAllStars()
    }
}

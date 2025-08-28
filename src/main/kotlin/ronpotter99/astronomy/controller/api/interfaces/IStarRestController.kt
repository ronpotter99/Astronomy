package ronpotter99.astronomy.controller.api.interfaces

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ronpotter99.astronomy.entity.Star

@RequestMapping("/api/star")
interface IStarRestController {

    @GetMapping("/list")
    fun getAllStars(): List<Star>
}

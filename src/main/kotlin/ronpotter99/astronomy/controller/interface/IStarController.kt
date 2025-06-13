package ronpotter99.astronomy.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import ronpotter99.astronomy.entity.Star

@RequestMapping("/star")
interface IStarController {

    @GetMapping("/list")
    fun getAllStars(): List<Star>
}

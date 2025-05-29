package ronpotter99.astronomy.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import ronpotter99.astronomy.entity.Star
import ronpotter99.astronomy.service.StarService


@RestController
@RequestMapping("/star")
class StarController(
    private val starService: StarService
) {
    
    @GetMapping("/list")
    fun getAllStars(): List<Star> {
        return starService.getAllStars();
    }
}
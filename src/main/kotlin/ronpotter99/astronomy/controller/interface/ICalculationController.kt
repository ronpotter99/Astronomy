package ronpotter99.astronomy.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import ronpotter99.astronomy.DTO.ScientificNumber

@RequestMapping("/calculate")
interface ICalculationController {

    @GetMapping("/{equationReference}/variables")
    fun getVariables(@PathVariable equationReference: String): Map<String, String>?

    @PostMapping("/{equationReference}")
    fun calculate(
            @PathVariable equationReference: String,
            @RequestBody equationVariables: Map<String, ScientificNumber>
    ): ResponseEntity<out Any>
}

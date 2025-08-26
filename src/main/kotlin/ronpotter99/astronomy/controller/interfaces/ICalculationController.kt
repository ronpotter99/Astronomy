package ronpotter99.astronomy.controller.interfaces

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ronpotter99.astronomy.dto.ScientificNumber

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

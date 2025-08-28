package ronpotter99.astronomy.controller.interfaces

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("/calculate")
interface ICalculationController {

    @GetMapping("/")
    fun calculationSelection(
        model: Model
    ): String

    @GetMapping("/{equationReference}")
    fun calculate(
        @PathVariable equationReference: String, model: Model
    ): String
}

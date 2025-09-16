package ronpotter99.astronomy.controller.interfaces

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import ronpotter99.astronomy.dto.EquationCalculationForm

@RequestMapping("/calculate")
interface ICalculationController {

    @GetMapping("", "/")
    fun calculationSelection(
        model: Model
    ): String

    @GetMapping("/{equationReferenceString}")
    fun calculateEquation(
        @PathVariable equationReferenceString: String, model: Model
    ): String

    @PostMapping("/{equationReferenceString}")
    fun calculateEquationSubmission(
        @PathVariable equationReferenceString: String,
        @ModelAttribute equationCalculationForm: EquationCalculationForm,
        model: Model
    ): String
}

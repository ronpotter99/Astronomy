package ronpotter99.astronomy.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import ronpotter99.astronomy.controller.interfaces.ICalculationController
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.service.CalculationService

@Controller
class CalculationController(private val calculationService: CalculationService) : ICalculationController {

    private val logger = KotlinLogging.logger {}

    override fun calculationSelection(model: Model): String {
        val equationCategories: Map<String, List<EquationReference>> = calculationService.getEquationCategoryMap()

        model.addAttribute("equationCategories", equationCategories)

        return "calculate/calculationSelection"
    }

    override fun calculate(equationReferenceString: String, model: Model): String {
        val equationReference: EquationReference? = calculationService.getEquationReference(equationReferenceString)
        val equationVariables: Map<String, String>? = calculationService.getEquationVariables(equationReferenceString)

        model.addAttribute("errorMessage", "test error message")
        model.addAttribute("equationReference", equationReference)
        model.addAttribute("equationVariables", equationVariables)

        return "calculate/calculateEquation"
    }
}

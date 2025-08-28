package ronpotter99.astronomy.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import ronpotter99.astronomy.controller.interfaces.ICalculationController
import ronpotter99.astronomy.service.CalculationService

@Controller
class CalculationController(private val calculationService: CalculationService) : ICalculationController {

    private val logger = KotlinLogging.logger {}

    override fun calculationSelection(model: Model): String {
        val equationCategories: Map<String, List<String>> = calculationService.getEquationCategoryMap()

        logger.warn { equationCategories }

        model.addAttribute("equationCategories", equationCategories)
        return "calculationSelection"
    }

    override fun calculate(equationReference: String, model: Model): String {
        model.addAttribute("message", "testing model attributes for '$equationReference'")
        return "calculateEquation"
    }
}

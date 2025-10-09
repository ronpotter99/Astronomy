package ronpotter99.astronomy.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import ronpotter99.astronomy.controller.interfaces.ICalculationController
import ronpotter99.astronomy.dto.EquationCalculationForm
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

    override fun calculateEquation(equationReferenceString: String, model: Model): String {
        val equationReference: EquationReference? = calculationService.getEquationReference(equationReferenceString)
        val equationVariables: Map<String, String>? = calculationService.getEquationVariables(equationReferenceString)
        val equationLatex: String? = calculationService.getLaTeXString(equationReferenceString)

        if (equationReference == null || equationVariables == null || equationLatex == null) {
            model.addAttribute("errorMessage", "No equation found")
        } else {
            model.addAttribute("equationReference", equationReference)
            model.addAttribute("equationVariables", equationVariables)
            model.addAttribute("equationLatex", equationLatex)

            if (!model.containsAttribute("equationCalculationForm")) {
                model.addAttribute("equationCalculationForm", EquationCalculationForm())
            }
        }

        return "calculate/calculateEquation"
    }

    override fun calculateEquationSubmission(
        equationReferenceString: String,
        equationCalculationForm: EquationCalculationForm,
        model: Model
    ): String {
        logger.warn { equationCalculationForm }

        equationCalculationForm.calculatedAnswer = Pair("test", "123u45")

        model.addAttribute("successMessage", "submission successful")
        model.addAttribute("equationCalculationForm", equationCalculationForm)

        return calculateEquation(equationReferenceString, model)
    }
}

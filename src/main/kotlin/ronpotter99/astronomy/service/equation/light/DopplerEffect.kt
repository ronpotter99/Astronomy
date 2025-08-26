package ronpotter99.astronomy.service.equation.light

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class DopplerEffect : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "DopplerEffect"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "lambda" to "original wavelength (m)",
            "Delta lambda" to "change in wavelength (m)",
            "v_r" to "radial velocity of source relative to observer (m/s)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("lambda") -> {
                ((variables.getValue("Delta lambda") * Constants.SPEED_OF_LIGHT) / variables.getValue("v_r"))
            }

            !variables.containsKey("Delta lambda") -> {
                ((variables.getValue("lambda") * variables.getValue("v_r")) / Constants.SPEED_OF_LIGHT)
            }

            !variables.containsKey("v_r") -> {
                ((variables.getValue("Delta lambda") * Constants.SPEED_OF_LIGHT) / variables.getValue("lambda"))
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

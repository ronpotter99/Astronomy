package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class EscapeVelocity : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "EscapeVelocity"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "v_e" to "escape velocity (m/s)",
            "M" to "mass of heavier object (kg)",
            "r" to "distance between lighter and heavier objects (m)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("v_e") -> {
                (ScientificNumber.multiply(
                    ScientificNumber("2"), Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT, variables.getValue("M")
                ) / variables.getValue("r")).sqrt()
            }

            !variables.containsKey("M") -> {
                ((variables.getValue("v_e")
                    .pow(BigDecimal("2")) * variables.getValue("r")) / (ScientificNumber("2") * Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT))
            }

            !variables.containsKey("r") -> {
                (ScientificNumber.multiply(
                    ScientificNumber("2"), Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT, variables.getValue("M")
                ) / variables.getValue("v_e").pow(BigDecimal("2")))
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

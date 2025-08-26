package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal

@Component
class EllipseAphelion : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "EllipseAphelion"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "r_a" to "distance of orbit at the aphelion (m)",
            "a" to "semi-major axis (m)",
            "e" to "eccentricity"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
            if (!variables.containsKey("r_a")) {
                (variables.get("a")!! * (ScientificNumber("1") + variables.get("e")!!))
            } else if (!variables.containsKey("a")) {
                (variables.get("r_a")!! / (ScientificNumber("1") + variables.get("e")!!))
            } else if (!variables.containsKey("e")) {
                ((variables.get("r_a")!! / variables.get("a")!!) - ScientificNumber("1"))
            } else {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }

        return toReturn
    }

    override fun validateInputVariables(variables: Map<String, ScientificNumber>) {
        super.validateInputVariables(variables)

        if (variables.containsKey("e") &&
            (variables.get("e")!!.number < BigDecimal("0") ||
                    variables.get("e")!!.number >= BigDecimal("1"))
        ) {
            throw IllegalArgumentException("Variable 'e' must fit the range 0 <= e < 1.")
        }
    }
}

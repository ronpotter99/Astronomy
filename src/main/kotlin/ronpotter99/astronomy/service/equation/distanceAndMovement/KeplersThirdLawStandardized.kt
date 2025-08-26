package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal

@Component
class KeplersThirdLawStandardized : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "KeplersThirdLawStandardized"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "P" to "orbital period (yr)",
            "M" to "mass of object being orbited (M_sun)",
            "a" to "orbital semi-major axis (AU)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
            if (!variables.containsKey("P")) {
                (variables.get("a")!!.pow(BigDecimal("3")) / variables.get("M")!!).sqrt()
            } else if (!variables.containsKey("M")) {
                (variables.get("a")!!.pow(BigDecimal("3")) /
                        variables.get("P")!!.pow(BigDecimal("2")))
            } else if (!variables.containsKey("a")) {
                (variables.get("P")!!.pow(BigDecimal("2")) * variables.get("M")!!).cbrt()
            } else {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }

        return toReturn
    }
}

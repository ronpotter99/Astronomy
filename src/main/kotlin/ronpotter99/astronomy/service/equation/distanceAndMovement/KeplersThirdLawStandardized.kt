package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal

@Component
class KeplersThirdLawStandardized : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "KeplersThirdLawStandardized",
            "Kepler's Third Law (Standardized)",
            """
                Kepler's Third Law, standardized for units based around year,
                solar mass, and AU.
            """.trimIndent()
        )
    }

    private val logger = KotlinLogging.logger {}

    override fun getCategory(): IEquation.EquationCategory {
        return IEquation.EquationCategory.DISTANCE_AND_MOVEMENT
    }

    override fun getEquationReference(): EquationReference {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "P" to "orbital period (yr)",
            "M" to "mass of object being orbited (M_sun)",
            "a" to "orbital semi-major axis (AU)"
        )
    }

    // TODO Finish string
    override fun getLaTeXString(): String {
        return ""
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("P") -> {
                (variables.getValue("a").pow(BigDecimal("3")) / variables.getValue("M")).sqrt()
            }

            !variables.containsKey("M") -> {
                (variables.getValue("a").pow(BigDecimal("3")) / variables.getValue("P").pow(BigDecimal("2")))
            }

            !variables.containsKey("a") -> {
                (variables.getValue("P").pow(BigDecimal("2")) * variables.getValue("M")).cbrt()
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

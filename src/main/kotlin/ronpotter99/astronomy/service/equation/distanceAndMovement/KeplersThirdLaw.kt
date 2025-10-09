package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class KeplersThirdLaw : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "KeplersThirdLaw",
            "Kepler's Third Law",
            """
                The orbital period of a planet, squared, is directly proportional 
                to the semi-major axes of its orbit, cubed. Kepler's third law 
                implies that the period for a planet to orbit its star increases 
                rapidly with the radius of its orbit.
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
            "P" to "orbital period (s)",
            "M" to "mass of object being orbited (kg)",
            "a" to "orbital semi-major axis (m)"
        )
    }

    override fun getLaTeXString(): String {
        return "P^2 = \\frac{4 \\pi^2}{G M} a^3"
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("P") -> {
                (((ScientificNumber("4") * ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.getValue(
                    "M"
                ))) * variables.getValue("a").pow(BigDecimal("3"))).sqrt()
            }

            !variables.containsKey("M") -> {
                (((ScientificNumber("4") * ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.getValue(
                    "P"
                ).pow(BigDecimal("2")))) * variables.getValue("a").pow(BigDecimal("3")))
            }

            !variables.containsKey("a") -> {
                (ScientificNumber.multiply(
                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                    variables.getValue("M"),
                    variables.getValue("P").pow(BigDecimal("2"))
                ) / (ScientificNumber("4") * ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))).cbrt())
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class VisVisa : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "VisVisa",
            "Vis-Visa (Planetary Orbital Velocity)",
            """
                Find the velocity of a planet using the mass of its star, the 
                distance between the planet and star, and the semi-major axis 
                of the planet's orbit.
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
            "v" to "orbital velocity (m/s)",
            "M" to "mass of heavier object (kg)",
            "r" to "distance between lighter and heavier objects (m)",
            "a" to "semi-major axis of lighter object's orbit (m)"
        )
    }

    // TODO Finish string
    override fun getLaTeXString(): String {
        return ""
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("v") -> {
                (ScientificNumber.multiply(
                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                    variables.getValue("M"),
                    ((ScientificNumber("2") / variables.getValue("r")) - (ScientificNumber("1") / variables.getValue("a")))
                )).sqrt()
            }

            !variables.containsKey("M") -> {
                (variables.getValue("v")
                    .pow(BigDecimal("2")) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * (ScientificNumber("2") / variables.getValue(
                    "r"
                )) - (ScientificNumber("1") / variables.getValue("a"))))
            }

            !variables.containsKey("r") -> {
                (ScientificNumber("2") / ((variables.getValue("v")
                    .pow(BigDecimal("2")) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.getValue("M"))) + (ScientificNumber(
                    "1"
                ) / variables.getValue("a"))))
            }

            !variables.containsKey("a") -> {
                (ScientificNumber("1") / ((ScientificNumber("2") / variables.getValue("r")) - (variables.getValue("v")
                    .pow(BigDecimal("2")) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.getValue("M")))))
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

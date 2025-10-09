package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal

@Component
class EllipsePerihelion : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "EllipsePerihelion",
            "Elliptical Orbit - Perihelion",
            """
                Find the perihelion (closest point from primary focus) of an elliptical orbit
                using the elliptical semi-major axis and eccentricity.
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
            "r_p" to "distance of orbit at the perihelion (m)", "a" to "semi-major axis (m)", "e" to "eccentricity"
        )
    }

    // TODO Finish string
    override fun getLaTeXString(): String {
        return ""
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("r_p") -> {
                (variables.getValue("a") * (ScientificNumber("1") - variables.getValue("e")))
            }

            !variables.containsKey("a") -> {
                (variables.getValue("r_p") / (ScientificNumber("1") - variables.getValue("e")))
            }

            !variables.containsKey("e") -> {
                (ScientificNumber("1") - (variables.getValue("r_p") / variables.getValue("a")))
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }

    override fun validateInputVariables(variables: Map<String, ScientificNumber>) {
        super.validateInputVariables(variables)

        require(
            !(variables.containsKey("e") && (variables.getValue("e").number < BigDecimal("0") || variables.getValue("e").number >= BigDecimal(
                "1"
            )))
        ) { "Variable 'e' must fit the range 0 <= e < 1." }
    }
}

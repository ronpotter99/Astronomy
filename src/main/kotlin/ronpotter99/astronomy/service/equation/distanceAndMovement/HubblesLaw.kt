package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class HubblesLaw : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "HubblesLaw",
            "Hubble's Law",
            """
                Find the distance of an object using the redshift of its light.
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
        return mapOf("z" to "interstellar redshift", "d" to "distance of observed object (m)")
    }

    // TODO Finish string
    override fun getLaTeXString(): String {
        return ""
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("z") -> {
                (Constants.HUBBLE_CONSTANT * variables.getValue("d") / Constants.SPEED_OF_LIGHT)
            }

            !variables.containsKey("d") -> {
                (variables.getValue("z") * Constants.SPEED_OF_LIGHT / Constants.HUBBLE_CONSTANT)
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

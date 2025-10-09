package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation

@Component
class AngularSeparation : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "AngularSeparation",
            "Angular Separation",
            """
                The radians of angular separation between two objects can be calculated 
                using the distance between the two objects and the distance from the 
                objects to the viewer.
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
            "alpha" to "angular separation (rad)",
            "a" to "distance between objects (m)",
            "d" to "distance from viewer to objects (m)"
        )
    }

    // TODO Finish string
    override fun getLaTeXString(): String {
        return ""
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("alpha") -> {
                (variables.getValue("a") / variables.getValue("d"))
            }

            !variables.containsKey("a") -> {
                (variables.getValue("alpha") * variables.getValue("d"))
            }

            !variables.containsKey("d") -> {
                (variables.getValue("a") / variables.getValue("alpha"))
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

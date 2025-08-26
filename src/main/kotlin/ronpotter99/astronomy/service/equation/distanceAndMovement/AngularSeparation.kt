package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation

@Component
class AngularSeparation : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "AngularSeparation"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "alpha" to "angular separation (rad)",
            "a" to "distance between objects (m)",
            "d" to "distance from viewer to objects (m)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
            if (!variables.containsKey("alpha")) {
                (variables.get("a")!! / variables.get("d")!!)
            } else if (!variables.containsKey("a")) {
                (variables.get("alpha")!! * variables.get("d")!!)
            } else if (!variables.containsKey("d")) {
                (variables.get("a")!! / variables.get("alpha")!!)
            } else {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }

        return toReturn
    }
}

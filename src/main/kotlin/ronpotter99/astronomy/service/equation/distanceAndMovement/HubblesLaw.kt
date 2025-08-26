package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class HubblesLaw : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "HubblesLaw"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf("z" to "interstellar redshift", "d" to "distance of observed object (m)")
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
            if (!variables.containsKey("z")) {
                (Constants.HUBBLE_CONSTANT * variables.get("d")!! / Constants.SPEED_OF_LIGHT)
            } else if (!variables.containsKey("d")) {
                (variables.get("z")!! * Constants.SPEED_OF_LIGHT / Constants.HUBBLE_CONSTANT)
            } else {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }

        return toReturn
    }
}

package ronpotter99.astronomy.service.equation.massAndGravitation

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation

@Component
class EllipticalBinaryMass : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "EllipticalBinaryMass"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "m_1" to "mass of star 1 (kg/M_sun)",
            "m_2" to "mass of star 2 (kg/M_sun)",
            "alpha_1" to "semi-major angular axis of star 1 (rad)",
            "alpha_2" to "semi-major angular axis of star 2 (rad)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
            if (!variables.containsKey("m_1")) {
                ((variables.get("m_2")!! * variables.get("alpha_2")!!) /
                        variables.get("alpha_1")!!)
            } else if (!variables.containsKey("m_2")) {
                ((variables.get("m_1")!! * variables.get("alpha_1")!!) /
                        variables.get("alpha_2")!!)
            } else if (!variables.containsKey("alpha_1")) {
                ((variables.get("m_2")!! * variables.get("alpha_2")!!) / variables.get("m_1")!!)
            } else if (!variables.containsKey("alpha_2")) {
                ((variables.get("m_1")!! * variables.get("alpha_1")!!) / variables.get("m_2")!!)
            } else {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }

        return toReturn
    }
}

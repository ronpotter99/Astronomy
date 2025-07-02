package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation

@Component
class KeplersSecondLaw : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "KeplersSecondLaw"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
                "r" to "radius of orbit (m)",
                "v_t" to "tangential velocity of planet (m/s)",
                "L" to "angular momentum (kg m^2 s^-1)",
                "m" to "mass of the planet (kg)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
                if (!variables.containsKey("r")) {
                    (variables.get("L")!! / (variables.get("m")!! * variables.get("v_t")!!))
                } else if (!variables.containsKey("v_t")) {
                    (variables.get("L")!! / (variables.get("m")!! * variables.get("r")!!))
                } else if (!variables.containsKey("L")) {
                    ScientificNumber.multiply(
                            variables.get("r")!!,
                            variables.get("v_t")!!,
                            variables.get("m")!!
                    )
                } else if (!variables.containsKey("m")) {
                    (variables.get("L")!! / (variables.get("r")!! * variables.get("v_t")!!))
                } else {
                    logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                    null
                }

        return toReturn
    }
}

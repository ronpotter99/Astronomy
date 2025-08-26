package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class UniversalLawOfGravitation : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "UniversalLawOfGravitation"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "F" to "force of gravity (N)",
            "M" to "mass of object 1 (kg)",
            "m" to "mass of object 2 (kg)",
            "r" to "radius between objects (m)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
            if (!variables.containsKey("F")) {
                Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                        ((variables.get("M")!! * variables.get("m")!!) /
                                variables.get("r")!!.pow(BigDecimal("2")))
            } else if (!variables.containsKey("M")) {
                (variables.get("F")!! * variables.get("r")!!.pow(BigDecimal("2"))) /
                        (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.get("m")!!)
            } else if (!variables.containsKey("m")) {
                (variables.get("F")!! * variables.get("r")!!.pow(BigDecimal("2"))) /
                        (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.get("M")!!)
            } else if (!variables.containsKey("r")) {
                (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                        ((variables.get("M")!! * variables.get("m")!!) /
                                variables.get("F")!!))
                    .sqrt()
            } else {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }

        return toReturn
    }
}

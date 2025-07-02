package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import org.springframework.stereotype.Component
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class VisVisa : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "VisVisa"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
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

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
                if (!variables.containsKey("v")) {
                    (ScientificNumber.multiply(
                                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                                    variables.get("M")!!,
                                    ((ScientificNumber("2") / variables.get("r")!!) -
                                            (ScientificNumber("1") / variables.get("a")!!))
                            ))
                            .sqrt()
                } else if (!variables.containsKey("M")) {
                    (variables.get("v")!!.pow(BigDecimal("2")) /
                            (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                    (ScientificNumber("2") / variables.get("r")!!) -
                                    (ScientificNumber("1") / variables.get("a")!!)))
                } else if (!variables.containsKey("r")) {
                    (ScientificNumber("2") /
                            ((variables.get("v")!!.pow(BigDecimal("2")) /
                                    (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                            variables.get("M")!!)) +
                                    (ScientificNumber("1") / variables.get("a")!!)))
                } else if (!variables.containsKey("a")) {
                    (ScientificNumber("1") /
                            ((ScientificNumber("2") / variables.get("r")!!) -
                                    (variables.get("v")!!.pow(BigDecimal("2")) /
                                            (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                                    variables.get("M")!!))))
                } else {
                    logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                    null
                }

        return toReturn
    }
}

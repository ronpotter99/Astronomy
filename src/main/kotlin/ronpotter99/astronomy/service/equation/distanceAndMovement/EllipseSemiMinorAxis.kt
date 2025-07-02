package ronpotter99.astronomy.service.equation

import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import org.springframework.stereotype.Component
import ronpotter99.astronomy.DTO.ScientificNumber

@Component
class EllipseSemiMinorAxis : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "EllipseSemiMinorAxis"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
                "b" to "semi-minor axis (m)",
                "a" to "semi-major axis (m)",
                "e" to "eccentricity"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
                if (!variables.containsKey("b")) {
                    (variables.get("a")!!.pow(BigDecimal("2")) * (ScientificNumber("1") - variables.get("e")!!.pow(BigDecimal("2")))).sqrt()
                } else if (!variables.containsKey("a")) {
                    (variables.get("b")!!.pow(BigDecimal("2")) / (ScientificNumber("1") - variables.get("e")!!.pow(BigDecimal("2")))).sqrt()
                } else if (!variables.containsKey("e")) {
                    (ScientificNumber("1") - (variables.get("b")!!.pow(BigDecimal("2")) / variables.get("a")!!.pow(BigDecimal("2")))).sqrt()
                } else {
                    logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                    null
                }

        return toReturn
    }

    override fun validateInputVariables(variables: Map<String, ScientificNumber>) {
        super.validateInputVariables(variables)

        if (variables.containsKey("e") &&
                        (variables.get("e")!!.number < BigDecimal("0") ||
                                variables.get("e")!!.number >= BigDecimal("1"))
        ) {
            throw IllegalArgumentException("Variable 'e' must fit the range 0 <= e < 1.")
        }
    }
}

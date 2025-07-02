package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import org.springframework.stereotype.Component
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation

@Component
class TransitingExoplanetRadius : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "TransitingExoplanetRadius"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
                "R_P" to "radius of the planet (m)",
                "R_star" to "radius of the star (m)",
                "Delta F" to "change in flux during the planet's transit (W m^-2)",
                "F" to "baseline flux of the star not during transit (W m^-2)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
                if (!variables.containsKey("R_P")) {
                    (variables.get("R_star")!! *
                            (variables.get("Delta F")!! / variables.get("F")!!).sqrt())
                } else if (!variables.containsKey("R_star")) {
                    (variables.get("R_P")!! /
                            (variables.get("Delta F")!! / variables.get("F")!!).sqrt())
                } else if (!variables.containsKey("Delta F")) {
                    ((variables.get("R_P")!! / variables.get("R_star")!!).pow(BigDecimal("2")) *
                            variables.get("F")!!)
                } else if (!variables.containsKey("F")) {
                    (variables.get("Delta F")!! /
                            (variables.get("R_P")!! / variables.get("R_star")!!).pow(
                                    BigDecimal("2")
                            ))
                } else {
                    logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                    null
                }

        return toReturn
    }
}

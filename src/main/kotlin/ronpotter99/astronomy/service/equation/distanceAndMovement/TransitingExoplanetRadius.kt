package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal

@Component
class TransitingExoplanetRadius : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "TransitingExoplanetRadius",
            "",
            """""".trimIndent()
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
            "R_P" to "radius of the planet (m)",
            "R_star" to "radius of the star (m)",
            "Delta F" to "change in flux during the planet's transit (W m^-2)",
            "F" to "baseline flux of the star not during transit (W m^-2)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("R_P") -> {
                (variables.getValue("R_star") * (variables.getValue("Delta F") / variables.getValue("F")).sqrt())
            }

            !variables.containsKey("R_star") -> {
                (variables.getValue("R_P") / (variables.getValue("Delta F") / variables.getValue("F")).sqrt())
            }

            !variables.containsKey("Delta F") -> {
                ((variables.getValue("R_P") / variables.getValue("R_star")).pow(BigDecimal("2")) * variables.getValue("F"))
            }

            !variables.containsKey("F") -> {
                (variables.getValue("Delta F") / (variables.getValue("R_P") / variables.getValue("R_star")).pow(
                    BigDecimal("2")
                ))
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

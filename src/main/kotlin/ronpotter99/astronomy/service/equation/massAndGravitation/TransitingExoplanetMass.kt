package ronpotter99.astronomy.service.equation.massAndGravitation

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class TransitingExoplanetMass : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "TransitingExoplanetMass"
    }

    private val logger = KotlinLogging.logger {}

    override fun getCategory(): IEquation.EquationCategory {
        return IEquation.EquationCategory.MASS_AND_GRAVITATION
    }

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "M_P" to "mass of the planet (kg)",
            "M_star" to "mass of the star (kg)",
            "P" to "orbital period (s)",
            "v_r" to "radial velocity of the planet measured in stellar spectrum (m/s)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("M_P") -> {
                ((ScientificNumber.multiply(
                    variables.getValue("M_star").pow(BigDecimal("2")),
                    variables.getValue("P"),
                    variables.getValue("v_r").pow(BigDecimal("3"))
                )) / (ScientificNumber.multiply(
                    ScientificNumber("2"), ScientificNumber(BDMath.pi()), Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT
                ))).cbrt()
            }

            !variables.containsKey("M_star") -> {
                ((ScientificNumber.multiply(
                    ScientificNumber("2"),
                    ScientificNumber(BDMath.pi()),
                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                    variables.getValue("M_P").pow(BigDecimal("3"))
                )) / (ScientificNumber.multiply(
                    variables.getValue("P"), variables.getValue("v_r").pow(BigDecimal("3"))
                ))).sqrt()
            }

            !variables.containsKey("P") -> {
                ((ScientificNumber.multiply(
                    ScientificNumber("2"),
                    ScientificNumber(BDMath.pi()),
                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                    variables.getValue("M_P").pow(BigDecimal("3"))
                )) / (ScientificNumber.multiply(
                    variables.getValue("M_star").pow(BigDecimal("2")), variables.getValue("v_r").pow(BigDecimal("3"))
                )))
            }

            !variables.containsKey("v_r") -> {
                ((ScientificNumber.multiply(
                    ScientificNumber("2"),
                    ScientificNumber(BDMath.pi()),
                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                    variables.getValue("M_P").pow(BigDecimal("3"))
                )) / (ScientificNumber.multiply(
                    variables.getValue("M_star").pow(BigDecimal("2")), variables.getValue("P")
                ))).cbrt()
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

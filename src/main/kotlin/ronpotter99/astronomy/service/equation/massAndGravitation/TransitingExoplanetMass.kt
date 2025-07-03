package ronpotter99.astronomy.service.equation.massAndGravitation

import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath
import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import org.springframework.stereotype.Component
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class TransitingExoplanetMass : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "TransitingExoplanetMass"
    }

    private val logger = KotlinLogging.logger {}

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

        val toReturn: ScientificNumber? =
                if (!variables.containsKey("M_P")) {
                    ((ScientificNumber.multiply(
                                    variables.get("M_star")!!.pow(BigDecimal("2")),
                                    variables.get("P")!!,
                                    variables.get("v_r")!!.pow(BigDecimal("3"))
                            )) /
                                    (ScientificNumber.multiply(
                                            ScientificNumber("2"),
                                            ScientificNumber(BDMath.pi()),
                                            Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT
                                    )))
                            .cbrt()
                } else if (!variables.containsKey("M_star")) {
                    ((ScientificNumber.multiply(
                                    ScientificNumber("2"),
                                    ScientificNumber(BDMath.pi()),
                                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                                    variables.get("M_P")!!.pow(BigDecimal("3"))
                            )) /
                                    (ScientificNumber.multiply(
                                            variables.get("P")!!,
                                            variables.get("v_r")!!.pow(BigDecimal("3"))
                                    )))
                            .sqrt()
                } else if (!variables.containsKey("P")) {
                    ((ScientificNumber.multiply(
                            ScientificNumber("2"),
                            ScientificNumber(BDMath.pi()),
                            Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                            variables.get("M_P")!!.pow(BigDecimal("3"))
                    )) /
                            (ScientificNumber.multiply(
                                    variables.get("M_star")!!.pow(BigDecimal("2")),
                                    variables.get("v_r")!!.pow(BigDecimal("3"))
                            )))
                } else if (!variables.containsKey("v_r")) {
                    ((ScientificNumber.multiply(
                                    ScientificNumber("2"),
                                    ScientificNumber(BDMath.pi()),
                                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                                    variables.get("M_P")!!.pow(BigDecimal("3"))
                            )) /
                                    (ScientificNumber.multiply(
                                            variables.get("M_star")!!.pow(BigDecimal("2")),
                                            variables.get("P")!!
                                    )))
                            .cbrt()
                } else {
                    logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                    null
                }

        return toReturn
    }
}

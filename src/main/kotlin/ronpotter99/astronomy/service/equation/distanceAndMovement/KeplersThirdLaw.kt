package ronpotter99.astronomy.service.equation.distanceAndMovement

import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath
import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import org.springframework.stereotype.Component
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class KeplersThirdLaw : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "KeplersThirdLaw"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
                "P" to "orbital period (s)",
                "M" to "mass of object being orbited (kg)",
                "a" to "orbital semi-major axis (m)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
                if (!variables.containsKey("P")) {
                    (((ScientificNumber("4") * ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))) /
                                    (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                            variables.get("M")!!)) *
                                    variables.get("a")!!.pow(BigDecimal("3")))
                            .sqrt()
                } else if (!variables.containsKey("M")) {
                    (((ScientificNumber("4") * ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))) /
                            (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                    variables.get("P")!!.pow(BigDecimal("2")))) *
                            variables.get("a")!!.pow(BigDecimal("3")))
                } else if (!variables.containsKey("a")) {
                    (ScientificNumber.multiply(
                            Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                            variables.get("M")!!,
                            variables.get("P")!!.pow(BigDecimal("2"))
                    ) /
                            (ScientificNumber("4") *
                                            ScientificNumber(BDMath.pi()).pow(BigDecimal("2")))
                                    .cbrt())
                } else {
                    logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                    null
                }

        return toReturn
    }
}

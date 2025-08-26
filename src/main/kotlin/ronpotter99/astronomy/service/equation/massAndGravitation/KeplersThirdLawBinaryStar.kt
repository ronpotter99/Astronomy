package ronpotter99.astronomy.service.equation.massAndGravitation

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class KeplersThirdLawBinaryStar : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "KeplersThirdLawBinaryStar"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "P" to "orbital period (s)",
            "M_1" to "mass of object 1 orbiting each other (kg)",
            "M_2" to "mass of object 2 orbiting each other (kg)",
            "a" to "orbital semi-major axis (m)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
            if (!variables.containsKey("P")) {
                (((ScientificNumber("4") * ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))) /
                        (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                (variables.get("M_1")!! + variables.get("M_2")!!))) *
                        variables.get("a")!!.pow(BigDecimal("3")))
                    .sqrt()
            } else if (!variables.containsKey("M_1")) {
                ((((ScientificNumber("4") *
                        ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))) /
                        (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                variables.get("P")!!.pow(BigDecimal("2")))) *
                        variables.get("a")!!.pow(BigDecimal("3"))) - variables.get("M_2")!!)
            } else if (!variables.containsKey("M_2")) {
                ((((ScientificNumber("4") *
                        ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))) /
                        (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                variables.get("P")!!.pow(BigDecimal("2")))) *
                        variables.get("a")!!.pow(BigDecimal("3"))) - variables.get("M_1")!!)
            } else if (!variables.containsKey("a")) {
                (ScientificNumber.multiply(
                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                    (variables.get("M_1")!! + variables.get("M_2")!!),
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

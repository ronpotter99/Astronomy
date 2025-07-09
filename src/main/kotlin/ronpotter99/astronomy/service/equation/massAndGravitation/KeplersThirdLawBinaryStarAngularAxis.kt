package ronpotter99.astronomy.service.equation.massAndGravitation

import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath
import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import org.springframework.stereotype.Component
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class KeplersThirdLawBinaryStarAngularAxis : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "KeplersThirdLawBinaryStarAngularAxis"
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
                "d" to "distance of stars from viewer (m)",
                "alpha" to "angular semi-major axis (rad)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
                if (!variables.containsKey("P")) {
                    (ScientificNumber.multiply(
                                    ScientificNumber("4"),
                                    ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                                    variables.get("d")!!.pow(BigDecimal("3")),
                                    variables.get("alpha")!!.pow(BigDecimal("3"))
                            ) /
                                    (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                            (variables.get("M_1")!! + variables.get("M_2")!!)))
                            .sqrt()
                } else if (!variables.containsKey("M_1")) {
                    ((ScientificNumber.multiply(
                            ScientificNumber("4"),
                            ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                            variables.get("d")!!.pow(BigDecimal("3")),
                            variables.get("alpha")!!.pow(BigDecimal("3"))
                    ) /
                            (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                    variables.get("P")!!.pow(BigDecimal("2")))) -
                            variables.get("M_2")!!)
                } else if (!variables.containsKey("M_2")) {
                    ((ScientificNumber.multiply(
                            ScientificNumber("4"),
                            ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                            variables.get("d")!!.pow(BigDecimal("3")),
                            variables.get("alpha")!!.pow(BigDecimal("3"))
                    ) /
                            (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                    variables.get("P")!!.pow(BigDecimal("2")))) -
                            variables.get("M_1")!!)
                } else if (!variables.containsKey("d")) {
                    ((ScientificNumber.multiply(
                                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                                    variables.get("P")!!.pow(BigDecimal("2")),
                                    (variables.get("M_1")!! + variables.get("M_2")!!)
                            )) /
                                    ScientificNumber.multiply(
                                            ScientificNumber("4"),
                                            ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                                            variables.get("alpha")!!.pow(BigDecimal("3"))
                                    ))
                            .cbrt()
                } else if (!variables.containsKey("alpha")) {
                    ((ScientificNumber.multiply(
                                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                                    variables.get("P")!!.pow(BigDecimal("2")),
                                    (variables.get("M_1")!! + variables.get("M_2")!!)
                            )) /
                                    ScientificNumber.multiply(
                                            ScientificNumber("4"),
                                            ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                                            variables.get("d")!!.pow(BigDecimal("3"))
                                    ))
                            .cbrt()
                } else {
                    logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                    null
                }

        return toReturn
    }
}

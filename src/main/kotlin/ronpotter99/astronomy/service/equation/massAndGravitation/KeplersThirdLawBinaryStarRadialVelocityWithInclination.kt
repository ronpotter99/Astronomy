package ronpotter99.astronomy.service.equation.massAndGravitation

import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath
import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import org.springframework.stereotype.Component
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class KeplersThirdLawBinaryStarRadialVelocityWithInclination : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "KeplersThirdLawBinaryStarRadialVelocityWithInclination"
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
                "v_r1" to "radial velocity of object 1 (m/s)",
                "v_r2" to "radial velocity of object 2 (m/s)",
                "i" to "angle of inclination between orbital plane and plane of sky (rad)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        // TODO create trig methods to support uncertainty calculations
        val toReturn: ScientificNumber? =
                if (!variables.containsKey("P")) {
                    val i = variables.get("i")!!.number

                    (ScientificNumber.multiply(
                                    ScientificNumber("4"),
                                    ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                                    (variables.get("d")!! / ScientificNumber(BDMath.cos(i))).pow(
                                            BigDecimal("3")
                                    ),
                                    variables.get("alpha")!!.pow(BigDecimal("3"))
                            ) /
                                    (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                            (variables.get("M_1")!! + variables.get("M_2")!!)))
                            .sqrt()
                } else if (!variables.containsKey("M_1")) {
                    val i = variables.get("i")!!.number

                    ((ScientificNumber.multiply(
                            ScientificNumber("4"),
                            ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                            (variables.get("d")!! / ScientificNumber(BDMath.cos(i))).pow(
                                    BigDecimal("3")
                            ),
                            variables.get("alpha")!!.pow(BigDecimal("3"))
                    ) /
                            (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                    variables.get("P")!!.pow(BigDecimal("2")))) -
                            variables.get("M_2")!!)
                } else if (!variables.containsKey("M_2")) {
                    val i = variables.get("i")!!.number

                    ((ScientificNumber.multiply(
                            ScientificNumber("4"),
                            ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                            (variables.get("d")!! / ScientificNumber(BDMath.cos(i))).pow(
                                    BigDecimal("3")
                            ),
                            variables.get("alpha")!!.pow(BigDecimal("3"))
                    ) /
                            (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT *
                                    variables.get("P")!!.pow(BigDecimal("2")))) -
                            variables.get("M_1")!!)
                } else if (!variables.containsKey("d")) {
                    val i = variables.get("i")!!.number

                    ((((ScientificNumber.multiply(
                                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                                    variables.get("P")!!.pow(BigDecimal("2")),
                                    (variables.get("M_1")!! + variables.get("M_2")!!)
                            )) /
                                    ScientificNumber.multiply(
                                            ScientificNumber("4"),
                                            ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                                            variables.get("alpha")!!.pow(BigDecimal("3"))
                                    ))
                            .cbrt()) * ScientificNumber(BDMath.cos(i)))
                } else if (!variables.containsKey("i")) {
                    ScientificNumber(
                        BDMath.acos(
                            (variables.get("d")!! /
                                ((ScientificNumber.multiply(
                                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                                    variables.get("P")!!.pow(BigDecimal("2")),
                                    (variables.get("M_1")!! + variables.get("M_2")!!)
                                )) / ScientificNumber.multiply(
                                    ScientificNumber("4"),
                                    ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                                    variables.get("alpha")!!.pow(BigDecimal("3"))
                                ))
                                .cbrt()
                            ).number
                        )
                    )
                } else if (!variables.containsKey("alpha")) {
                    val i = variables.get("i")!!.number

                    ((ScientificNumber.multiply(
                                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                                    variables.get("P")!!.pow(BigDecimal("2")),
                                    (variables.get("M_1")!! + variables.get("M_2")!!)
                            )) /
                                    ScientificNumber.multiply(
                                            ScientificNumber("4"),
                                            ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                                            (variables.get("d")!! / ScientificNumber(BDMath.cos(i)))
                                                    .pow(BigDecimal("3"))
                                    ))
                            .cbrt()
                } else {
                    logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                    null
                }

        return toReturn
    }

    override fun validateInputVariables(variables: Map<String, ScientificNumber>) {
        super.validateInputVariables(variables)

        // TODO create trig methods to support uncertainty calculations
        if (variables.containsKey("i") && variables.get("i")!!.uncertainty != null) {
            throw IllegalArgumentException("Uncertainties of 'i' are not currently supported.")
        }
    }
}

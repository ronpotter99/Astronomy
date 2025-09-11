package ronpotter99.astronomy.service.equation.massAndGravitation

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class KeplersThirdLawBinaryStarAngularAxisWithInclination : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "KeplersThirdLawBinaryStarAngularAxisWithInclination",
            "",
            """""".trimIndent()
        )
    }

    private val logger = KotlinLogging.logger {}

    override fun getCategory(): IEquation.EquationCategory {
        return IEquation.EquationCategory.MASS_AND_GRAVITATION
    }

    override fun getEquationReference(): EquationReference {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "P" to "orbital period (s)",
            "M_1" to "mass of object 1 orbiting each other (kg)",
            "M_2" to "mass of object 2 orbiting each other (kg)",
            "d" to "distance of stars from viewer (m)",
            "i" to "angle of inclination between orbital plane and plane of sky (rad)",
            "alpha" to "angular semi-major axis (rad)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        // TODO create trig methods to support uncertainty calculations
        val toReturn: ScientificNumber? = when {
            !variables.containsKey("P") -> {
                val i = variables.getValue("i").number

                (ScientificNumber.multiply(
                    ScientificNumber("4"),
                    ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                    (variables.getValue("d") / ScientificNumber(BDMath.cos(i))).pow(
                        BigDecimal("3")
                    ),
                    variables.getValue("alpha").pow(BigDecimal("3"))
                ) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * (variables.getValue("M_1") + variables.getValue("M_2")))).sqrt()
            }

            !variables.containsKey("M_1") -> {
                val i = variables.getValue("i").number

                ((ScientificNumber.multiply(
                    ScientificNumber("4"),
                    ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                    (variables.getValue("d") / ScientificNumber(BDMath.cos(i))).pow(
                        BigDecimal("3")
                    ),
                    variables.getValue("alpha").pow(BigDecimal("3"))
                ) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.getValue("P")
                    .pow(BigDecimal("2")))) - variables.getValue("M_2"))
            }

            !variables.containsKey("M_2") -> {
                val i = variables.getValue("i").number

                ((ScientificNumber.multiply(
                    ScientificNumber("4"),
                    ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                    (variables.getValue("d") / ScientificNumber(BDMath.cos(i))).pow(
                        BigDecimal("3")
                    ),
                    variables.getValue("alpha").pow(BigDecimal("3"))
                ) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.getValue("P")
                    .pow(BigDecimal("2")))) - variables.getValue("M_1"))
            }

            !variables.containsKey("d") -> {
                val i = variables.getValue("i").number

                ((((ScientificNumber.multiply(
                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                    variables.getValue("P").pow(BigDecimal("2")),
                    (variables.getValue("M_1") + variables.getValue("M_2"))
                )) / ScientificNumber.multiply(
                    ScientificNumber("4"),
                    ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                    variables.getValue("alpha").pow(BigDecimal("3"))
                )).cbrt()) * ScientificNumber(BDMath.cos(i)))
            }

            !variables.containsKey("i") -> {
                ScientificNumber(
                    BDMath.acos(
                        (variables.getValue("d") / ((ScientificNumber.multiply(
                            Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                            variables.getValue("P").pow(BigDecimal("2")),
                            (variables.getValue("M_1") + variables.getValue("M_2"))
                        )) / ScientificNumber.multiply(
                            ScientificNumber("4"),
                            ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                            variables.getValue("alpha").pow(BigDecimal("3"))
                        )).cbrt()).number
                    )
                )
            }

            !variables.containsKey("alpha") -> {
                val i = variables.getValue("i").number

                ((ScientificNumber.multiply(
                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                    variables.getValue("P").pow(BigDecimal("2")),
                    (variables.getValue("M_1") + variables.getValue("M_2"))
                )) / ScientificNumber.multiply(
                    ScientificNumber("4"),
                    ScientificNumber(BDMath.pi()).pow(BigDecimal("2")),
                    (variables.getValue("d") / ScientificNumber(BDMath.cos(i))).pow(BigDecimal("3"))
                )).cbrt()
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }

    override fun validateInputVariables(variables: Map<String, ScientificNumber>) {
        super.validateInputVariables(variables)

        // TODO create trig methods to support uncertainty calculations
        require(!(variables.containsKey("i") && variables.getValue("i").uncertainty != null)) { "Uncertainties of 'i' are not currently supported." }
    }
}

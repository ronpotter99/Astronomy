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
class KeplersThirdLawBinaryStar : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "KeplersThirdLawBinaryStar",
            "Kepler's Third Law - Binary Star Systems",
            """
                Kepler's Third Law, modified for binary star systems.
            """.trimIndent()
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
            "a" to "orbital semi-major axis (m)"
        )
    }

    // TODO Finish string
    override fun getLaTeXString(): String {
        return ""
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("P") -> {
                (((ScientificNumber("4") * ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * (variables.getValue(
                    "M_1"
                ) + variables.getValue("M_2")))) * variables.getValue("a").pow(BigDecimal("3"))).sqrt()
            }

            !variables.containsKey("M_1") -> {
                ((((ScientificNumber("4") * ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.getValue(
                    "P"
                ).pow(BigDecimal("2")))) * variables.getValue("a").pow(BigDecimal("3"))) - variables.getValue("M_2"))
            }

            !variables.containsKey("M_2") -> {
                ((((ScientificNumber("4") * ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.getValue(
                    "P"
                ).pow(BigDecimal("2")))) * variables.getValue("a").pow(BigDecimal("3"))) - variables.getValue("M_1"))
            }

            !variables.containsKey("a") -> {
                (ScientificNumber.multiply(
                    Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT,
                    (variables.getValue("M_1") + variables.getValue("M_2")),
                    variables.getValue("P").pow(BigDecimal("2"))
                ) / (ScientificNumber("4") * ScientificNumber(BDMath.pi()).pow(BigDecimal("2"))).cbrt())
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

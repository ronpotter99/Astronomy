package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class UniversalLawOfGravitation : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "UniversalLawOfGravitation",
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
            "F" to "force of gravity (N)",
            "M" to "mass of object 1 (kg)",
            "m" to "mass of object 2 (kg)",
            "r" to "radius between objects (m)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("F") -> {
                Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * ((variables.getValue("M") * variables.getValue("m")) / variables.getValue(
                    "r"
                ).pow(BigDecimal("2")))
            }

            !variables.containsKey("M") -> {
                (variables.getValue("F") * variables.getValue("r")
                    .pow(BigDecimal("2"))) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.getValue("m"))
            }

            !variables.containsKey("m") -> {
                (variables.getValue("F") * variables.getValue("r")
                    .pow(BigDecimal("2"))) / (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * variables.getValue("M"))
            }

            !variables.containsKey("r") -> {
                (Constants.UNIVERSAL_GRAVITATIONAL_CONSTANT * ((variables.getValue("M") * variables.getValue("m")) / variables.getValue(
                    "F"
                ))).sqrt()
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

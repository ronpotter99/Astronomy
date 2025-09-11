package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath

@Component
class EllipseRadius : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "EllipseRadius",
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
            "r" to "radius (m)",
            "a" to "semi-major axis (m)",
            "e" to "eccentricity",
            "theta" to "angle of planet in its orbit, measured from perihelion (rad)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        // TODO create trig methods to support uncertainty calculations
        val toReturn: ScientificNumber? = when {
            !variables.containsKey("r") -> {
                val theta: BigDecimal = variables.getValue("theta").number

                (variables.getValue("a") * (ScientificNumber("1") - variables.getValue("e")
                    .pow(BigDecimal("2")))) / (ScientificNumber("1") + (variables.getValue("e") * ScientificNumber(
                    BDMath.cos(
                        theta
                    ).toPlainString()
                )))
            }

            !variables.containsKey("a") -> {
                val theta: BigDecimal = variables.getValue("theta").number

                (variables.getValue("r") * (ScientificNumber("1") + (variables.getValue("e") * ScientificNumber(
                    BDMath.cos(
                        theta
                    ).toPlainString()
                )))) / (ScientificNumber("1") - variables.getValue("e").pow(BigDecimal("2")))
            }

            !variables.containsKey("e") -> {
                val theta: BigDecimal = variables.getValue("theta").number

                ((((variables.getValue("a") * (ScientificNumber("1") - variables.getValue("e")
                    .pow(BigDecimal("2")))) / variables.getValue("r")) - ScientificNumber("1")) / ScientificNumber(
                    BDMath.cos(
                        theta
                    ).toPlainString()
                ))
            }

            !variables.containsKey("theta") -> {
                ScientificNumber(
                    BDMath.acos(
                        ((((variables.getValue("a") * (ScientificNumber("1") - variables.getValue("e").pow(
                            BigDecimal("2")
                        ))) / variables.getValue("r")) - ScientificNumber("1")) / variables.getValue("e")).number
                    ).toPlainString()
                )
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
        require(!(variables.containsKey("theta") && variables.getValue("theta").uncertainty != null)) { "Uncertainties of 'theta' are not currently supported." }

        require(
            !(variables.containsKey("e") && (variables.getValue("e").number < BigDecimal("0") || variables.getValue("e").number >= BigDecimal(
                "1"
            )))
        ) { "Variable 'e' must fit the range 0 <= e < 1." }
    }
}

package ronpotter99.astronomy.service.equation.distanceAndMovement

import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath
import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import org.springframework.stereotype.Component
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation

@Component
class EllipseRadius : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "EllipseRadius"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
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
        val toReturn: ScientificNumber? =
                if (!variables.containsKey("r")) {
                    val theta: BigDecimal = variables.get("theta")!!.number

                    (variables.get("a")!! *
                            (ScientificNumber("1") - variables.get("e")!!.pow(BigDecimal("2")))) /
                            (ScientificNumber("1") +
                                    (variables.get("e")!! *
                                            ScientificNumber(BDMath.cos(theta).toPlainString())))
                } else if (!variables.containsKey("a")) {
                    val theta: BigDecimal = variables.get("theta")!!.number

                    (variables.get("r")!! *
                            (ScientificNumber("1") +
                                    (variables.get("e")!! *
                                            ScientificNumber(BDMath.cos(theta).toPlainString())))) /
                            (ScientificNumber("1") - variables.get("e")!!.pow(BigDecimal("2")))
                } else if (!variables.containsKey("e")) {
                    val theta: BigDecimal = variables.get("theta")!!.number

                    ((((variables.get("a")!! *
                            (ScientificNumber("1") - variables.get("e")!!.pow(BigDecimal("2")))) /
                            variables.get("r")!!) - ScientificNumber("1")) /
                            ScientificNumber(BDMath.cos(theta).toPlainString()))
                } else if (!variables.containsKey("theta")) {
                    ScientificNumber(
                            BDMath.acos(
                                            ((((variables.get("a")!! *
                                                            (ScientificNumber("1") -
                                                                    variables.get("e")!!.pow(
                                                                            BigDecimal("2")
                                                                    ))) / variables.get("r")!!) -
                                                            ScientificNumber("1")) /
                                                            variables.get("e")!!)
                                                    .number
                                    )
                                    .toPlainString()
                    )
                } else {
                    logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                    null
                }

        return toReturn
    }

    override fun validateInputVariables(variables: Map<String, ScientificNumber>) {
        super.validateInputVariables(variables)

        // TODO create trig methods to support uncertainty calculations
        if (variables.containsKey("theta") && variables.get("theta")!!.uncertainty != null) {
            throw IllegalArgumentException("Uncertainties of 'theta' are not currently supported.")
        }

        if (variables.containsKey("e") &&
                        (variables.get("e")!!.number < BigDecimal("0") ||
                                variables.get("e")!!.number >= BigDecimal("1"))
        ) {
            throw IllegalArgumentException("Variable 'e' must fit the range 0 <= e < 1.")
        }
    }
}

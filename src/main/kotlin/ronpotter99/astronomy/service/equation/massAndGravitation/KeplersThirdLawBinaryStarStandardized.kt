package ronpotter99.astronomy.service.equation.massAndGravitation

import io.github.oshai.kotlinlogging.KotlinLogging
import java.math.BigDecimal
import org.springframework.stereotype.Component
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation

@Component
class KeplersThirdLawBinaryStarStandardized : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "KeplersThirdLawBinaryStarStandardized"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
                "P" to "orbital period (yr)",
                "M_1" to "mass of object 1 orbiting each other (M_sun)",
                "M_2" to "mass of object 2 orbiting each other (M_sun)",
                "a" to "orbital semi-major axis (AU)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
                if (!variables.containsKey("P")) {
                    (variables.get("a")!!.pow(BigDecimal("3")) /
                                    (variables.get("M_1")!! + variables.get("M_2")!!))
                            .sqrt()
                } else if (!variables.containsKey("M_1")) {
                    ((variables.get("a")!!.pow(BigDecimal("3")) /
                            variables.get("P")!!.pow(BigDecimal("2"))) - variables.get("M_2")!!)
                } else if (!variables.containsKey("M_2")) {
                    (variables.get("a")!!.pow(BigDecimal("3")) /
                            variables.get("P")!!.pow(BigDecimal("2")) - variables.get("M_1")!!)
                } else if (!variables.containsKey("a")) {
                    (variables.get("P")!!.pow(BigDecimal("2")) *
                                    (variables.get("M_1")!! + variables.get("M_2")!!))
                            .cbrt()
                } else {
                    logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                    null
                }

        return toReturn
    }
}

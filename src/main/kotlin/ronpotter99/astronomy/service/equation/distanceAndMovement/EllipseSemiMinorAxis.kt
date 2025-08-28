package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal

@Component
class EllipseSemiMinorAxis : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "EllipseSemiMinorAxis"
    }

    private val logger = KotlinLogging.logger {}

    override fun getCategory(): IEquation.EquationCategory {
        return IEquation.EquationCategory.DISTANCE_AND_MOVEMENT
    }

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "b" to "semi-minor axis (m)", "a" to "semi-major axis (m)", "e" to "eccentricity"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("b") -> {
                (variables.getValue("a").pow(BigDecimal("2")) * (ScientificNumber("1") - variables.getValue("e")
                    .pow(BigDecimal("2")))).sqrt()
            }

            !variables.containsKey("a") -> {
                (variables.getValue("b").pow(BigDecimal("2")) / (ScientificNumber("1") - variables.getValue("e")
                    .pow(BigDecimal("2")))).sqrt()
            }

            !variables.containsKey("e") -> {
                (ScientificNumber("1") - (variables.getValue("b").pow(BigDecimal("2")) / variables.getValue("a")
                    .pow(BigDecimal("2")))).sqrt()
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

        require(
            !(variables.containsKey("e") && (variables.getValue("e").number < BigDecimal("0") || variables.getValue("e").number >= BigDecimal(
                "1"
            )))
        ) { "Variable 'e' must fit the range 0 <= e < 1." }
    }
}

package ronpotter99.astronomy.service.equation.light

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class FluxDensityConversion : IEquation {

    companion object {
        const val EQUATION_REFERENCE: String = "FluxDensityConversion"
    }

    private val logger = KotlinLogging.logger {}

    override fun getEquationReference(): String {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "F_v" to "Flux per unit of frequency (W m^-2 Hz^-1)",
            "F_lambda" to "Flux per unit of wavelength (W m^-2 nm^-1)",
            "lambda" to "wavelength (nm)"
        )
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? =
            if (!variables.containsKey("F_v")) {
                (variables.get("F_lambda")!! *
                        (variables.get("lambda")!!.pow(BigDecimal("2")) /
                                Constants.SPEED_OF_LIGHT))
            } else if (!variables.containsKey("F_lambda")) {
                ((variables.get("F_v")!! * Constants.SPEED_OF_LIGHT) /
                        variables.get("lambda")!!.pow(BigDecimal("2")))
            } else if (!variables.containsKey("lambda")) {
                ((variables.get("F_v")!! * Constants.SPEED_OF_LIGHT) /
                        variables.get("F_lambda")!!)
            } else {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }

        return toReturn
    }
}

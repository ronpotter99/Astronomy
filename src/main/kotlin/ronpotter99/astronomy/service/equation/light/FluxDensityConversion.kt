package ronpotter99.astronomy.service.equation.light

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import java.math.BigDecimal
import ronpotter99.astronomy.utils.EquationConstants as Constants

@Component
class FluxDensityConversion : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "FluxDensityConversion",
            "Flux-Density Conversion",
            """
                Convert between flux per unit of frequency and flux per 
                unit of wavelength.
            """.trimIndent()
        )
    }

    private val logger = KotlinLogging.logger {}

    override fun getCategory(): IEquation.EquationCategory {
        return IEquation.EquationCategory.LIGHT
    }

    override fun getEquationReference(): EquationReference {
        return EQUATION_REFERENCE
    }

    override fun getVariableList(): Map<String, String> {
        return mapOf(
            "F_v" to "Flux per unit of frequency (W m^-2 Hz^-1)",
            "F_lambda" to "Flux per unit of wavelength (W m^-2 nm^-1)",
            "lambda" to "wavelength (nm)"
        )
    }

    // TODO Finish string
    override fun getLaTeXString(): String {
        return ""
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("F_v") -> {
                (variables.getValue("F_lambda") * (variables.getValue("lambda")
                    .pow(BigDecimal("2")) / Constants.SPEED_OF_LIGHT))
            }

            !variables.containsKey("F_lambda") -> {
                ((variables.getValue("F_v") * Constants.SPEED_OF_LIGHT) / variables.getValue("lambda")
                    .pow(BigDecimal("2")))
            }

            !variables.containsKey("lambda") -> {
                ((variables.getValue("F_v") * Constants.SPEED_OF_LIGHT) / variables.getValue("F_lambda"))
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

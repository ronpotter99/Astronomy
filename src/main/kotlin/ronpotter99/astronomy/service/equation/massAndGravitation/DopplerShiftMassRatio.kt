package ronpotter99.astronomy.service.equation.massAndGravitation

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation

@Component
class DopplerShiftMassRatio : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "DopplerShiftMassRatio",
            "Doppler Shift - Mass Ratio",
            """
                The mass ratio of two orbiting objects is related inversely to 
                their radial velocity.
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
            "m_1" to "mass of star 1 (kg/M_sun)",
            "m_2" to "mass of star 2 (kg/M_sun)",
            "v_r1" to "radial velocity of star 1 (m/s)",
            "v_r2" to "radial velocity of star 2 (m/s)"
        )
    }

    // TODO Finish string
    override fun getLaTeXString(): String {
        return ""
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("m_1") -> {
                ((variables.getValue("m_2") * variables.getValue("v_r2")) / variables.getValue("v_r1"))
            }

            !variables.containsKey("m_2") -> {
                ((variables.getValue("m_1") * variables.getValue("v_r1")) / variables.getValue("v_r2"))
            }

            !variables.containsKey("v_r1") -> {
                ((variables.getValue("m_2") * variables.getValue("v_r2")) / variables.getValue("m_1"))
            }

            !variables.containsKey("v_r2") -> {
                ((variables.getValue("m_1") * variables.getValue("v_r1")) / variables.getValue("m_2"))
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

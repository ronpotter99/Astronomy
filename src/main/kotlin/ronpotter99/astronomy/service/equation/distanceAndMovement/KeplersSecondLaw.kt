package ronpotter99.astronomy.service.equation.distanceAndMovement

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation

@Component
class KeplersSecondLaw : IEquation {

    companion object {
        val EQUATION_REFERENCE: EquationReference = EquationReference(
            "KeplersSecondLaw",
            "Kepler's Second Law",
            """
                The imaginary line joining a planet and its star sweeps out- or covers-
                equal areas of space during equal time intervals as the planet orbits.
                Simplified, planets do not move with constant speed along their orbits. 
                Rather, their speed varies so that the line joining the centers of the 
                star and planet star sweeps out equal parts of an area in equal times.
                The point of nearest approach of the planet to its star is termed perihelion.
                The point of greatest separation is aphelion, hence by Kepler's Second Law, 
                a planet is moving fastest when it is at perihelion and slowest at aphelion.
            """.trimIndent()
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
            "r" to "radius of orbit (m)",
            "v_t" to "tangential velocity of planet (m/s)",
            "L" to "angular momentum (kg m^2 s^-1)",
            "m" to "mass of the planet (kg)"
        )
    }

    // TODO Finish string
    override fun getLaTeXString(): String {
        return ""
    }

    override fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber? {
        validateInputVariables(variables)

        val toReturn: ScientificNumber? = when {
            !variables.containsKey("r") -> {
                (variables.getValue("L") / (variables.getValue("m") * variables.getValue("v_t")))
            }

            !variables.containsKey("v_t") -> {
                (variables.getValue("L") / (variables.getValue("m") * variables.getValue("r")))
            }

            !variables.containsKey("L") -> {
                ScientificNumber.multiply(
                    variables.getValue("r"), variables.getValue("v_t"), variables.getValue("m")
                )
            }

            !variables.containsKey("m") -> {
                (variables.getValue("L") / (variables.getValue("r") * variables.getValue("v_t")))
            }

            else -> {
                logger.warn { "$EQUATION_REFERENCE: Unknown variable to calculate." }
                null
            }
        }

        return toReturn
    }
}

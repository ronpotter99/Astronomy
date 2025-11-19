package ronpotter99.astronomy.service.equation.distanceAndMovement

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class EllipseAphelionTests {

    @Test
    fun getCategory() {
        val equation = EllipseAphelion()
        val distanceAndMovementCategory = IEquation.EquationCategory.DISTANCE_AND_MOVEMENT

        val toCheck = equation.getCategory()
        assertEquals(distanceAndMovementCategory, toCheck)
    }

    @Test
    fun getEquationReference_referenceCorrect() {
        val equation = EllipseAphelion()
        val referenceString = "EllipseAphelion"

        val toCheck: String = equation.getEquationReference().reference
        assertEquals(referenceString, toCheck)
    }

    @Test
    fun getVariableList() {
        val equation = EllipseAphelion()

        val toCheck: Map<String, String> = equation.getVariableList()
        assertFalse(toCheck.isEmpty())
    }

    @Test
    fun getLaTeXString() {
        val equation = EllipseAphelion()

        val toCheck: String = equation.getLaTeXString()
        assertFalse(toCheck.isEmpty())
    }

    @Test
    fun calculate_allVariablesPresent() {
        val equation = EllipseAphelion()
        val variables = mapOf(
            "r_a" to ScientificNumber("100", "5"),
            "a" to ScientificNumber("25", "5"),
            "e" to ScientificNumber("0.5", "0.05")
        )

        val thrownException = assertThrows(IllegalArgumentException::class.java) {
            equation.calculate(variables)
        }
        assertNotNull(thrownException.message)
        assertFalse(thrownException.message!!.isEmpty())
    }

    @Test
    fun calculate_missingTwoOrMoreVariables() {
        val equation = EllipseAphelion()
        val variables: Map<String, ScientificNumber> = mapOf()

        val thrownException = assertThrows(IllegalArgumentException::class.java) {
            equation.calculate(variables)
        }
        assertNotNull(thrownException.message)
        assertFalse(thrownException.message!!.isEmpty())
    }

    @Test
    fun calculate_r_a() {
        val equation = EllipseAphelion()
        val variables = mapOf(
            "a" to ScientificNumber("100", "10"),
            "e" to ScientificNumber("0.50", "0.005")
        )
        val answer = ScientificNumber("150", "20")

        val toCheck = equation.calculate(variables)

        assertEquals(answer, toCheck)
    }

    @Test
    fun calculate_a() {
        val equation = EllipseAphelion()
        val variables = mapOf(
            "e" to ScientificNumber("0.50", "0.005"),
            "r_a" to ScientificNumber("150", "20")
        )
        val answer = ScientificNumber("100", "10")

        val toCheck = equation.calculate(variables)

        assertEquals(answer, toCheck)
    }

    @Test
    // TODO: Fix rounding on answer's number
    fun calculate_e() {
        val equation = EllipseAphelion()
        val variables = mapOf(
            "a" to ScientificNumber("100", "10"),
            "r_a" to ScientificNumber("150", "20")
        )
        val answer = ScientificNumber("0.50", "0.25")

        val toCheck = equation.calculate(variables)

        assertEquals(answer, toCheck)
    }
}

package ronpotter99.astronomy.service.equation.distanceAndMovement

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNotNull
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class AngularSeparationTests {

    @Test
    fun getCategory() {
        val equation = AngularSeparation()
        val distanceAndMovementCategory = IEquation.EquationCategory.DISTANCE_AND_MOVEMENT

        val toCheck = equation.getCategory()
        assertEquals(distanceAndMovementCategory, toCheck)
    }

    @Test
    fun getEquationReference_referenceCorrect() {
        val equation = AngularSeparation()
        val referenceString = "AngularSeparation"

        val toCheck: String = equation.getEquationReference().reference
        assertEquals(referenceString, toCheck)
    }

    @Test
    fun getVariableList() {
        val equation = AngularSeparation()

        val toCheck: Map<String, String> = equation.getVariableList()
        assertFalse(toCheck.isEmpty())
    }

    @Test
    fun getLaTeXString() {
        val equation = AngularSeparation()

        val toCheck: String = equation.getLaTeXString()
        assertFalse(toCheck.isEmpty())
    }

    @Test
    fun calculate_allVariablesPresent() {
        val equation = AngularSeparation()
        val variables = mapOf(
            "alpha" to ScientificNumber("0.050", "0.005"),
            "a" to ScientificNumber("100", "10"),
            "d" to ScientificNumber("2000", "20")
        )

        val thrownException = assertThrows(IllegalArgumentException::class.java) {
            equation.calculate(variables)
        }
        assertNotNull(thrownException.message)
        assertFalse(thrownException.message!!.isEmpty())
    }

    @Test
    fun calculate_missingTwoOrMoreVariables() {
        val equation = AngularSeparation()
        val variables: Map<String, ScientificNumber> = mapOf()

        val thrownException = assertThrows(IllegalArgumentException::class.java) {
            equation.calculate(variables)
        }
        assertNotNull(thrownException.message)
        assertFalse(thrownException.message!!.isEmpty())
    }

    @Test
    fun calculate_alpha() {
        val equation = AngularSeparation()
        val variables = mapOf(
            "a" to ScientificNumber("100", "10"),
            "d" to ScientificNumber("2000", "20")
        )
        val answer = ScientificNumber("0.050", "0.005")

        val toCheck = equation.calculate(variables)

        assertEquals(answer, toCheck)
    }

    @Test
    fun calculate_a() {
        val equation = AngularSeparation()
        val variables = mapOf(
            "alpha" to ScientificNumber("0.050", "0.005"),
            "d" to ScientificNumber("2000", "20")
        )
        val answer = ScientificNumber("100", "10")

        val toCheck = equation.calculate(variables)

        assertEquals(answer, toCheck)
    }

    @Test
    fun calculate_d() {
        val equation = AngularSeparation()
        val variables = mapOf(
            "a" to ScientificNumber("100", "10"),
            "alpha" to ScientificNumber("0.050", "0.005")
        )
        val answer = ScientificNumber("2000", "300")

        val toCheck = equation.calculate(variables)

        assertEquals(answer, toCheck)
    }
}

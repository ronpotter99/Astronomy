package ronpotter99.astronomy.service.equation

import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber

interface IEquation {

    enum class EquationCategory(val categoryName: String) {
        DISTANCE_AND_MOVEMENT("Distance and Movement"),
        LIGHT("Light"),
        MASS_AND_GRAVITATION("Mass and Gravitation")
    }

    fun getCategory(): EquationCategory

    fun getEquationReference(): EquationReference

    fun getVariableList(): Map<String, String>

    fun getLaTeXString(): String

    fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber?

    fun validateInputVariables(variables: Map<String, ScientificNumber>) {
        val validationVariableKeys = getVariableList().keys
        val intersectingVariableKeys = validationVariableKeys.intersect(variables.keys)

        if (intersectingVariableKeys.size != validationVariableKeys.size - 1) {
            val missingVariableKeys = validationVariableKeys - intersectingVariableKeys

            throw IllegalArgumentException(
                "${validationVariableKeys.size - 1} variables required, but only ${intersectingVariableKeys.size} variables received. Provide ${missingVariableKeys.size - 1} of the following variables: $missingVariableKeys."
            )
        }
    }
}

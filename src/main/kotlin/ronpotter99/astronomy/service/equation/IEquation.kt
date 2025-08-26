package ronpotter99.astronomy.service.equation

import ronpotter99.astronomy.dto.ScientificNumber

interface IEquation {

    fun getEquationReference(): String

    fun getVariableList(): Map<String, String>

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

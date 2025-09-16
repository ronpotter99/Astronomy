package ronpotter99.astronomy.dto

data class EquationCalculationForm(
    var equationVariableValues: Map<String, String> = mutableMapOf()
)
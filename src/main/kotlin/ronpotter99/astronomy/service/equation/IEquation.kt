package ronpotter99.astronomy.service.equation

import ronpotter99.astronomy.DTO.ScientificNumber

interface IEquation {
    
    fun getEquationReference(): String

    fun getVariableList(): Map<String, String>

    fun calculate(variables: Map<String, ScientificNumber>): ScientificNumber?
}
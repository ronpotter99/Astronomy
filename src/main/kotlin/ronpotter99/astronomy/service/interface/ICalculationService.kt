package ronpotter99.astronomy.service

import ronpotter99.astronomy.DTO.ScientificNumber

interface ICalculationService {

    fun getEquationVariables(equationReference: String): Map<String, String>?

    fun calculate(
            equationReference: String,
            equationVariables: Map<String, ScientificNumber>
    ): ScientificNumber?
}

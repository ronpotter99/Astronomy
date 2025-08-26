package ronpotter99.astronomy.service.interfaces

import ronpotter99.astronomy.dto.ScientificNumber

interface ICalculationService {

    fun getEquationVariables(equationReference: String): Map<String, String>?

    fun calculate(
        equationReference: String,
        equationVariables: Map<String, ScientificNumber>
    ): ScientificNumber?
}

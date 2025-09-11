package ronpotter99.astronomy.service.interfaces

import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber

interface ICalculationService {

    fun getEquationCategoryMap(): Map<String, List<EquationReference>>

    fun getEquationVariables(equationReference: String): Map<String, String>?

    fun calculate(
        equationReference: String, equationVariables: Map<String, ScientificNumber>
    ): ScientificNumber?
}

package ronpotter99.astronomy.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import ronpotter99.astronomy.dto.EquationReference
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.service.interfaces.ICalculationService

@Service
class CalculationService(private val equations: HashMap<String, IEquation>) : ICalculationService {

    private val logger = KotlinLogging.logger {}

    override fun getEquationCategoryMap(): Map<String, List<EquationReference>> {
        val toReturn = HashMap<String, MutableList<EquationReference>>()

        for (equationKey in equations.keys) {
            val categoryName: String = equations[equationKey]?.getCategory()!!.categoryName
            val equationReference: EquationReference = equations[equationKey]?.getEquationReference()!!

            if (!toReturn.containsKey(categoryName)) {
                toReturn[categoryName] = mutableListOf()
            }

            toReturn[categoryName]?.add(equationReference)
        }

        return toReturn
    }

    override fun getEquationReference(equationReference: String): EquationReference? {
        return equations[equationReference.uppercase()]?.getEquationReference()
    }

    override fun getEquationVariables(equationReference: String): Map<String, String>? {
        return equations[equationReference.uppercase()]?.getVariableList()
    }

    override fun getLaTeXString(equationReference: String): String? {
        return equations[equationReference.uppercase()]?.getLaTeXString()
    }

    override fun calculate(
        equationReference: String, equationVariables: Map<String, ScientificNumber>
    ): ScientificNumber? {
        return equations[equationReference.uppercase()]?.calculate(equationVariables)
    }
}

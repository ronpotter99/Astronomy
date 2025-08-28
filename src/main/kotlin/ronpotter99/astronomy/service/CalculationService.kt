package ronpotter99.astronomy.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.service.interfaces.ICalculationService

@Service
class CalculationService(private val equations: HashMap<String, IEquation>) : ICalculationService {

    private val logger = KotlinLogging.logger {}

    override fun getEquationCategoryMap(): Map<String, List<String>> {
        val toReturn = HashMap<String, MutableList<String>>()

        for (equationKey in equations.keys) {
            val categoryName = equations[equationKey]?.getCategory()!!.categoryName

            if (!toReturn.containsKey(categoryName)) {
                toReturn[categoryName] = mutableListOf()
            }

            toReturn[categoryName]?.add(equationKey)
        }

        return toReturn
    }

    override fun getEquationVariables(equationReference: String): Map<String, String>? {
        return equations[equationReference.uppercase()]?.getVariableList()
    }

    override fun calculate(
        equationReference: String, equationVariables: Map<String, ScientificNumber>
    ): ScientificNumber? {
        return equations[equationReference.uppercase()]?.calculate(equationVariables)
    }
}

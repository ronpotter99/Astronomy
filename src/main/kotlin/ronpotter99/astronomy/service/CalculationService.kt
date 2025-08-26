package ronpotter99.astronomy.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.equation.IEquation
import ronpotter99.astronomy.service.interfaces.ICalculationService

@Service
class CalculationService(private val equations: HashMap<String, IEquation>) : ICalculationService {

    private val logger = KotlinLogging.logger {}

    override fun getEquationVariables(equationReference: String): Map<String, String>? {
        return equations.get(equationReference.uppercase())?.getVariableList()
    }

    override fun calculate(
        equationReference: String,
        equationVariables: Map<String, ScientificNumber>
    ): ScientificNumber? {
        return equations.get(equationReference.uppercase())?.calculate(equationVariables)
    }
}

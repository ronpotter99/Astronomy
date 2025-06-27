package ronpotter99.astronomy.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.service.CalculationService

@RestController
class CalculationController(private val calculationService: CalculationService) :
        ICalculationController {

    private val logger = KotlinLogging.logger {}

    override fun calculate(
            equationReference: String,
            equationVariables: Map<String, ScientificNumber>
    ): ScientificNumber? {
        logger.info { "calculate method entered with equationReference '$equationReference'" }
        logger.info { "provided variables: '$equationVariables'" }

        return calculationService.calculate(equationReference, equationVariables)
    }

    override fun getVariables(equationReference: String): Map<String, String>? {
        return calculationService.getEquationVariables(equationReference)
    }
}

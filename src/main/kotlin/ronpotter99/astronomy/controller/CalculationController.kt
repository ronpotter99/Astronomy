package ronpotter99.astronomy.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.controller.interfaces.ICalculationController
import ronpotter99.astronomy.service.CalculationService

@RestController
class CalculationController(private val calculationService: CalculationService) :
    ICalculationController {

    private val logger = KotlinLogging.logger {}

    override fun getVariables(equationReference: String): Map<String, String>? {
        return calculationService.getEquationVariables(equationReference)
    }

    override fun calculate(
        equationReference: String,
        equationVariables: Map<String, ScientificNumber>
    ): ResponseEntity<out Any> {
        val calculatedValue =
            try {
                calculationService.calculate(equationReference, equationVariables)
            } catch (ex: IllegalArgumentException) {
                logger.warn { "$equationReference: ${ex.localizedMessage}" }
                ex.localizedMessage
            }

        val toReturn =
            if (calculatedValue == null) {
                ResponseEntity("Equation not found.", HttpStatus.NOT_FOUND)
            } else if (calculatedValue is String) {
                ResponseEntity(calculatedValue, HttpStatus.BAD_REQUEST)
            } else {
                ResponseEntity(calculatedValue, HttpStatus.OK)
            }

        return toReturn
    }
}

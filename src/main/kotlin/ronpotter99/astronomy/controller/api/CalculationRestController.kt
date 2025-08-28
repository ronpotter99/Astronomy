package ronpotter99.astronomy.controller.api

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import ronpotter99.astronomy.controller.api.interfaces.ICalculationRestController
import ronpotter99.astronomy.dto.ScientificNumber
import ronpotter99.astronomy.service.CalculationService

@RestController
class CalculationRestController(private val calculationService: CalculationService) :
    ICalculationRestController {

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
            when (calculatedValue) {
                null -> {
                    ResponseEntity("Equation not found.", HttpStatus.NOT_FOUND)
                }

                is String -> {
                    ResponseEntity(calculatedValue, HttpStatus.BAD_REQUEST)
                }

                else -> {
                    ResponseEntity(calculatedValue, HttpStatus.OK)
                }
            }

        return toReturn
    }
}

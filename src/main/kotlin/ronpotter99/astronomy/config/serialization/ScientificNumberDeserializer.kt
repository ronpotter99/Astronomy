package ronpotter99.astronomy.config.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.math.BigDecimal
import ronpotter99.astronomy.DTO.ScientificNumber

class ScientificNumberDeserializer : JsonDeserializer<ScientificNumber>() {

    override fun deserialize(
            parser: JsonParser,
            context: DeserializationContext
    ): ScientificNumber {
        val numParts = parser.text.split("u", limit = 2)

        val number: BigDecimal = BigDecimal(numParts[0])
        val uncertainty: BigDecimal? =
                if (numParts.size > 1) {
                    BigDecimal(numParts[1])
                } else {
                    null
                }

        return ScientificNumber(number, uncertainty)
    }
}

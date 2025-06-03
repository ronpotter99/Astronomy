package ronpotter99.astronomy.config.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.math.BigDecimal
import ronpotter99.astronomy.DTO.UBigDecimal

class UBigDecimalDeserializer : JsonDeserializer<UBigDecimal>() {

    override fun deserialize(parser: JsonParser, context: DeserializationContext): UBigDecimal {
        val numParts = parser.text.split("u", limit = 2)

        val number: BigDecimal = BigDecimal(numParts[0])
        var uncertainty: BigDecimal? = null
        if (numParts.size > 1) {
            uncertainty = BigDecimal(numParts[1])
        }

        return UBigDecimal(number, uncertainty)
    }
}

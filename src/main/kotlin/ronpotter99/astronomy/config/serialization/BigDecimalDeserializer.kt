package ronpotter99.astronomy.config.serialization

import java.math.BigDecimal
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.core.JsonParser

class BigDecimalDeserializer: JsonDeserializer<BigDecimal>() {

    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): BigDecimal {
        return parser.text.toBigDecimal()
    }
}
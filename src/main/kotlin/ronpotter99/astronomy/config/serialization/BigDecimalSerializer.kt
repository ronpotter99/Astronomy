package ronpotter99.astronomy.config.serialization

import java.math.BigDecimal
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonGenerator

class BigDecimalSerializer: JsonSerializer<BigDecimal>() {

    override fun serialize(
        value: BigDecimal,
        gen: JsonGenerator,
        serializers: SerializerProvider
    ) {
        return gen.writeString(value.toString())
    }
}
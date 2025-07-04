package ronpotter99.astronomy.config.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import ronpotter99.astronomy.DTO.UBigInteger

class UBigIntegerSerializer : JsonSerializer<UBigInteger>() {

    override fun serialize(
            value: UBigInteger,
            gen: JsonGenerator,
            serializers: SerializerProvider
    ) {
        return gen.writeString(value.number.toString() + (value.uncertainty?.let { "u$it" } ?: ""))
    }
}

package ronpotter99.astronomy.config.serialization

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonGenerator

import ronpotter99.astronomy.DTO.UBigDecimal
import jakarta.persistence.AttributeConverter

class UBigDecimalSerializer: JsonSerializer<UBigDecimal>() {

    override fun serialize(
        value: UBigDecimal,
        gen: JsonGenerator,
        serializers: SerializerProvider
    ) {
        return gen.writeString(
            value.number.toString() +
            if (value.uncertainty != null) "u" + value.uncertainty.toString() else ""
        )
    }


}
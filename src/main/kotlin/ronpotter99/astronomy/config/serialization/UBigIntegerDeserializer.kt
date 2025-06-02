package ronpotter99.astronomy.config.serialization

import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.core.JsonParser
import java.math.BigInteger
import java.math.BigDecimal

import ronpotter99.astronomy.DTO.UBigInteger

class UBigIntegerDeserializer: JsonDeserializer<UBigInteger>() {

    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): UBigInteger {
        val numParts = parser.text.split("u", limit = 2)

        var number: BigInteger
        try {
            number = numParts[0].toBigInteger()
        } catch (ex: NumberFormatException) {
            number = BigDecimal(numParts[0]).toBigInteger()
        }

        var uncertainty: BigInteger? = null
        if (numParts.size > 1) {
            try {
                uncertainty = numParts[1].toBigInteger()
            } catch (ex: NumberFormatException) {
                uncertainty = BigDecimal(numParts[1]).toBigInteger()
            }
        }

        return UBigInteger(number, uncertainty)
    }
}
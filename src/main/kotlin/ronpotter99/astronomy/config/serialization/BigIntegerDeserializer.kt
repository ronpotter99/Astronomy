package ronpotter99.astronomy.config.serialization

import java.math.BigInteger
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.core.JsonParser

class BigIntegerDeserializer: JsonDeserializer<BigInteger>() {

    override fun deserialize(
        parser: JsonParser,
        context: DeserializationContext
    ): BigInteger {
        return parser.text.toBigInteger()
    }
}
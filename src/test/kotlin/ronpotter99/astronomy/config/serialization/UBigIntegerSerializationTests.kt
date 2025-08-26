package ronpotter99.astronomy.config.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import ronpotter99.astronomy.dto.UBigInteger
import java.math.BigInteger

class UBigIntegerSerializationTests {

    @Test
    fun withNumber_noUncertainty() {
        val serializer: UBigIntegerSerializer = UBigIntegerSerializer()
        val jsonGeneratorMock: JsonGenerator = mock()
        val serializersMock: SerializerProvider = mock()
        val uBigInteger = UBigInteger(BigInteger("123"))

        serializer.serialize(uBigInteger, jsonGeneratorMock, serializersMock)
        verify(jsonGeneratorMock).writeString("123")
    }

    @Test
    fun withNumber_withUncertainty() {
        val serializer: UBigIntegerSerializer = UBigIntegerSerializer()
        val jsonGeneratorMock: JsonGenerator = mock()
        val serializersMock: SerializerProvider = mock()
        val uBigInteger = UBigInteger(BigInteger("123"), BigInteger("456"))

        serializer.serialize(uBigInteger, jsonGeneratorMock, serializersMock)
        verify(jsonGeneratorMock).writeString("123u456")
    }
}

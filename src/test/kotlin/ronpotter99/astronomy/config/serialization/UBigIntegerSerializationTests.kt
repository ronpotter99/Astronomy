package ronpotter99.astronomy

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigInteger
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ronpotter99.astronomy.DTO.UBigInteger
import ronpotter99.astronomy.config.serialization.UBigIntegerSerializer

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

package ronpotter99.astronomy

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigDecimal
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ronpotter99.astronomy.DTO.UBigDecimal
import ronpotter99.astronomy.config.serialization.UBigDecimalSerializer

class UBigDecimalSerializationTests {

    @Test
    fun withNumber_noUncertainty() {
        val serializer: UBigDecimalSerializer = UBigDecimalSerializer()
        val jsonGeneratorMock: JsonGenerator = mock()
        val serializersMock: SerializerProvider = mock()
        val uBigDecimal = UBigDecimal(BigDecimal("1.23"))

        serializer.serialize(uBigDecimal, jsonGeneratorMock, serializersMock)
        verify(jsonGeneratorMock).writeString("1.23")
    }

    @Test
    fun withNumber_withUncertainty() {
        val serializer: UBigDecimalSerializer = UBigDecimalSerializer()
        val jsonGeneratorMock: JsonGenerator = mock()
        val serializersMock: SerializerProvider = mock()
        val uBigDecimal = UBigDecimal(BigDecimal("1.23"), BigDecimal("4.56"))

        serializer.serialize(uBigDecimal, jsonGeneratorMock, serializersMock)
        verify(jsonGeneratorMock).writeString("1.23u4.56")
    }
}

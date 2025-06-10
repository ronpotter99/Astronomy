package ronpotter99.astronomy

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigDecimal
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.config.serialization.ScientificNumberSerializer

class ScientificNumberSerializationTests {

    @Test
    fun withNumber_noUncertainty() {
        val serializer: ScientificNumberSerializer = ScientificNumberSerializer()
        val jsonGeneratorMock: JsonGenerator = mock()
        val serializersMock: SerializerProvider = mock()
        val scientificNumber = ScientificNumber(BigDecimal("1.23"))

        serializer.serialize(scientificNumber, jsonGeneratorMock, serializersMock)
        verify(jsonGeneratorMock).writeString("1.23")
    }

    @Test
    fun withNumber_withUncertainty() {
        val serializer: ScientificNumberSerializer = ScientificNumberSerializer()
        val jsonGeneratorMock: JsonGenerator = mock()
        val serializersMock: SerializerProvider = mock()
        val scientificNumber = ScientificNumber(BigDecimal("1.23"), BigDecimal("4.56"))

        serializer.serialize(scientificNumber, jsonGeneratorMock, serializersMock)
        verify(jsonGeneratorMock).writeString("1.23u4.56")
    }
}

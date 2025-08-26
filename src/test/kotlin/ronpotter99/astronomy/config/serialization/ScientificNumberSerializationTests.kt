package ronpotter99.astronomy.config.serialization

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import ronpotter99.astronomy.dto.ScientificNumber

class ScientificNumberSerializationTests {

    @Test
    fun withNumber_noUncertainty() {
        val serializer: ScientificNumberSerializer = ScientificNumberSerializer()
        val jsonGeneratorMock: JsonGenerator = mock()
        val serializersMock: SerializerProvider = mock()
        val scientificNumber = ScientificNumber("1.23")

        serializer.serialize(scientificNumber, jsonGeneratorMock, serializersMock)
        verify(jsonGeneratorMock).writeString("1.23")
    }

    @Test
    fun withNumber_withUncertainty() {
        val serializer: ScientificNumberSerializer = ScientificNumberSerializer()
        val jsonGeneratorMock: JsonGenerator = mock()
        val serializersMock: SerializerProvider = mock()
        val scientificNumber = ScientificNumber("1.23", "4.56")

        serializer.serialize(scientificNumber, jsonGeneratorMock, serializersMock)
        verify(jsonGeneratorMock).writeString("1.23u4.56")
    }
}

package ronpotter99.astronomy

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import java.math.BigDecimal
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ronpotter99.astronomy.DTO.ScientificNumber
import ronpotter99.astronomy.config.serialization.ScientificNumberDeserializer

class ScientificNumberDeserializationTests {

    @Test
    fun withNumber_noUncertainty() {
        val deserializer: ScientificNumberDeserializer = ScientificNumberDeserializer()
        val parserMock: JsonParser = mock()
        val contextMock: DeserializationContext = mock()
        val scientificNumber = ScientificNumber("1.23")

        whenever(parserMock.text).thenReturn("1.23")

        val toCheck: ScientificNumber = deserializer.deserialize(parserMock, contextMock)
        assertEquals(scientificNumber, toCheck)
    }

    @Test
    fun withNumber_withUncertainty() {
        val deserializer: ScientificNumberDeserializer = ScientificNumberDeserializer()
        val parserMock: JsonParser = mock()
        val contextMock: DeserializationContext = mock()
        val scientificNumber = ScientificNumber("1.23", "4.56")

        whenever(parserMock.text).thenReturn("1.23u4.56")

        val toCheck: ScientificNumber = deserializer.deserialize(parserMock, contextMock)
        assertEquals(scientificNumber, toCheck)
    }
}

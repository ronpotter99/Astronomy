package ronpotter99.astronomy

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import java.math.BigDecimal
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ronpotter99.astronomy.DTO.UBigDecimal
import ronpotter99.astronomy.config.serialization.UBigDecimalDeserializer

class UBigDecimalDeserializationTests {

    @Test
    fun withNumber_noUncertainty() {
        val deserializer: UBigDecimalDeserializer = UBigDecimalDeserializer()
        val parserMock: JsonParser = mock()
        val contextMock: DeserializationContext = mock()
        val uBigDecimal = UBigDecimal(BigDecimal("1.23"))

        whenever(parserMock.text).thenReturn("1.23")

        val toCheck: UBigDecimal = deserializer.deserialize(parserMock, contextMock)
        assertEquals(uBigDecimal, toCheck)
    }

    @Test
    fun withNumber_withUncertainty() {
        val deserializer: UBigDecimalDeserializer = UBigDecimalDeserializer()
        val parserMock: JsonParser = mock()
        val contextMock: DeserializationContext = mock()
        val uBigDecimal = UBigDecimal(BigDecimal("1.23"), BigDecimal("4.56"))

        whenever(parserMock.text).thenReturn("1.23u4.56")

        val toCheck: UBigDecimal = deserializer.deserialize(parserMock, contextMock)
        assertEquals(uBigDecimal, toCheck)
    }
}

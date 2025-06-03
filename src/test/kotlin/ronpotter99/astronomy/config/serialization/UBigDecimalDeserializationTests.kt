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
        val uBigDecimal = UBigDecimal(BigDecimal(123))

        whenever(parserMock.text).thenReturn("123")

        val toCheck: UBigDecimal = deserializer.deserialize(parserMock, contextMock)
        assertEquals(uBigDecimal, toCheck)
    }

    @Test
    fun withNumber_withUncertainty() {
        val deserializer: UBigDecimalDeserializer = UBigDecimalDeserializer()
        val parserMock: JsonParser = mock()
        val contextMock: DeserializationContext = mock()
        val uBigDecimal = UBigDecimal(BigDecimal(123), BigDecimal(456))

        whenever(parserMock.text).thenReturn("123u456")

        val toCheck: UBigDecimal = deserializer.deserialize(parserMock, contextMock)
        assertEquals(uBigDecimal, toCheck)
    }
}

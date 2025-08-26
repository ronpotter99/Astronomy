package ronpotter99.astronomy.config.serialization

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import ronpotter99.astronomy.dto.UBigInteger
import java.math.BigInteger
import kotlin.test.assertEquals

class UBigIntegerDeserializationTests {

    @Test
    fun withNumber_noUncertainty() {
        val deserializer: UBigIntegerDeserializer = UBigIntegerDeserializer()
        val parserMock: JsonParser = mock()
        val contextMock: DeserializationContext = mock()
        val uBigInteger = UBigInteger(BigInteger("123"))

        whenever(parserMock.text).thenReturn("123")

        val toCheck: UBigInteger = deserializer.deserialize(parserMock, contextMock)
        assertEquals(uBigInteger, toCheck)
    }

    @Test
    fun withNumber_withUncertainty() {
        val deserializer: UBigIntegerDeserializer = UBigIntegerDeserializer()
        val parserMock: JsonParser = mock()
        val contextMock: DeserializationContext = mock()
        val uBigInteger = UBigInteger(BigInteger("123"), BigInteger("456"))

        whenever(parserMock.text).thenReturn("123u456")

        val toCheck: UBigInteger = deserializer.deserialize(parserMock, contextMock)
        assertEquals(uBigInteger, toCheck)
    }
}

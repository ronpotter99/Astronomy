package ronpotter99.astronomy

import com.fasterxml.jackson.databind.ObjectMapper
import java.math.BigInteger
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ronpotter99.astronomy.DTO.UBigInteger
import ronpotter99.astronomy.config.UBigIntegerAttributeConverter

class UBigIntegerAttributeConverterTests {

    @Test
    fun convertToDatabaseColumn_null() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigIntegerAttributeConverter =
                UBigIntegerAttributeConverter(objectMapperMock)

        val toCheck: String? = attributeConverter.convertToDatabaseColumn(null)
        assertEquals(null, toCheck)
    }

    @Test
    fun convertToDatabaseColumn_withNumber_noUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigIntegerAttributeConverter =
                UBigIntegerAttributeConverter(objectMapperMock)
        val uBigInteger = UBigInteger(BigInteger("123"))
        val uBigIntegerString = "123"

        whenever(objectMapperMock.writeValueAsString(uBigInteger)).thenReturn(uBigIntegerString)

        val toCheck: String? = attributeConverter.convertToDatabaseColumn(uBigInteger)
        assertEquals(uBigIntegerString, toCheck)
    }

    @Test
    fun convertToDatabaseColumn_withNumber_withUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigIntegerAttributeConverter =
                UBigIntegerAttributeConverter(objectMapperMock)
        val uBigInteger = UBigInteger(BigInteger("123"), BigInteger("456"))
        val uBigIntegerString = "123u456"

        whenever(objectMapperMock.writeValueAsString(uBigInteger)).thenReturn(uBigIntegerString)

        val toCheck: String? = attributeConverter.convertToDatabaseColumn(uBigInteger)
        assertEquals(uBigIntegerString, toCheck)
    }

    @Test
    fun convertToEntityAttribute_null() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigIntegerAttributeConverter =
                UBigIntegerAttributeConverter(objectMapperMock)

        val toCheck: UBigInteger? = attributeConverter.convertToEntityAttribute(null)
        assertEquals(null, toCheck)
    }

    @Test
    fun convertToEntityAttribute_emptyString() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigIntegerAttributeConverter =
                UBigIntegerAttributeConverter(objectMapperMock)

        val toCheck: UBigInteger? = attributeConverter.convertToEntityAttribute("")
        assertEquals(null, toCheck)
    }

    @Test
    fun convertToEntityAttribute_withNumber_noUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigIntegerAttributeConverter =
                UBigIntegerAttributeConverter(objectMapperMock)
        val uBigInteger = UBigInteger(BigInteger("123"))
        val uBigIntegerString = "123"

        whenever(objectMapperMock.readValue(uBigIntegerString, UBigInteger::class.java))
                .thenReturn(uBigInteger)

        val toCheck: UBigInteger? = attributeConverter.convertToEntityAttribute(uBigIntegerString)
        assertEquals(uBigInteger, toCheck)
    }

    @Test
    fun convertToEntityAttribute_withNumber_withUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigIntegerAttributeConverter =
                UBigIntegerAttributeConverter(objectMapperMock)
        val uBigInteger = UBigInteger(BigInteger("123"), BigInteger("456"))
        val uBigIntegerString = "123u456"

        whenever(objectMapperMock.readValue(uBigIntegerString, UBigInteger::class.java))
                .thenReturn(uBigInteger)

        val toCheck: UBigInteger? = attributeConverter.convertToEntityAttribute(uBigIntegerString)
        assertEquals(uBigInteger, toCheck)
    }
}

package ronpotter99.astronomy

import com.fasterxml.jackson.databind.ObjectMapper
import java.math.BigDecimal
import kotlin.test.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import ronpotter99.astronomy.DTO.UBigDecimal
import ronpotter99.astronomy.config.UBigDecimalAttributeConverter

class UBigDecimalAttributeConverterTests {

    @Test
    fun convertToDatabaseColumn_null() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigDecimalAttributeConverter =
                UBigDecimalAttributeConverter(objectMapperMock)

        val toCheck: String? = attributeConverter.convertToDatabaseColumn(null)
        assertEquals(null, toCheck)
    }

    @Test
    fun convertToDatabaseColumn_withNumber_noUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigDecimalAttributeConverter =
                UBigDecimalAttributeConverter(objectMapperMock)
        val uBigDecimal = UBigDecimal(BigDecimal(123))
        val uBigDecimalString = "123"

        whenever(objectMapperMock.writeValueAsString(uBigDecimal)).thenReturn(uBigDecimalString)

        val toCheck: String? = attributeConverter.convertToDatabaseColumn(uBigDecimal)
        assertEquals(uBigDecimalString, toCheck)
    }

    @Test
    fun convertToDatabaseColumn_withNumber_withUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigDecimalAttributeConverter =
                UBigDecimalAttributeConverter(objectMapperMock)
        val uBigDecimal = UBigDecimal(BigDecimal(123), BigDecimal(456))
        val uBigDecimalString = "123u456"

        whenever(objectMapperMock.writeValueAsString(uBigDecimal)).thenReturn(uBigDecimalString)

        val toCheck: String? = attributeConverter.convertToDatabaseColumn(uBigDecimal)
        assertEquals(uBigDecimalString, toCheck)
    }

    @Test
    fun convertToEntityAttribute_null() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigDecimalAttributeConverter =
                UBigDecimalAttributeConverter(objectMapperMock)

        val toCheck: UBigDecimal? = attributeConverter.convertToEntityAttribute(null)
        assertEquals(null, toCheck)
    }

    @Test
    fun convertToEntityAttribute_emptyString() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigDecimalAttributeConverter =
                UBigDecimalAttributeConverter(objectMapperMock)

        val toCheck: UBigDecimal? = attributeConverter.convertToEntityAttribute("")
        assertEquals(null, toCheck)
    }

    @Test
    fun convertToEntityAttribute_withNumber_noUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigDecimalAttributeConverter =
                UBigDecimalAttributeConverter(objectMapperMock)
        val uBigDecimal = UBigDecimal(BigDecimal(123))
        val uBigDecimalString = "123"

        whenever(objectMapperMock.readValue(uBigDecimalString, UBigDecimal::class.java))
                .thenReturn(uBigDecimal)

        val toCheck: UBigDecimal? = attributeConverter.convertToEntityAttribute(uBigDecimalString)
        assertEquals(uBigDecimal, toCheck)
    }

    @Test
    fun convertToEntityAttribute_withNumber_withUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: UBigDecimalAttributeConverter =
                UBigDecimalAttributeConverter(objectMapperMock)
        val uBigDecimal = UBigDecimal(BigDecimal(123), BigDecimal(456))
        val uBigDecimalString = "123u456"

        whenever(objectMapperMock.readValue(uBigDecimalString, UBigDecimal::class.java))
                .thenReturn(uBigDecimal)

        val toCheck: UBigDecimal? = attributeConverter.convertToEntityAttribute(uBigDecimalString)
        assertEquals(uBigDecimal, toCheck)
    }
}

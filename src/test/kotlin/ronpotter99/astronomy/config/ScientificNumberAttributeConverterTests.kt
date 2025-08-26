package ronpotter99.astronomy.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import ronpotter99.astronomy.dto.ScientificNumber
import kotlin.test.assertEquals

class ScientificNumberAttributeConverterTests {

    @Test
    fun convertToDatabaseColumn_null() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: ScientificNumberAttributeConverter =
            ScientificNumberAttributeConverter(objectMapperMock)

        val toCheck: String? = attributeConverter.convertToDatabaseColumn(null)
        assertEquals(null, toCheck)
    }

    @Test
    fun convertToDatabaseColumn_withNumber_noUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: ScientificNumberAttributeConverter =
            ScientificNumberAttributeConverter(objectMapperMock)
        val scientificNumber = ScientificNumber("1.23")
        val scientificNumberString = "1.23"

        whenever(objectMapperMock.convertValue(scientificNumber, String::class.java))
            .thenReturn(scientificNumberString)

        val toCheck: String? = attributeConverter.convertToDatabaseColumn(scientificNumber)
        assertEquals(scientificNumberString, toCheck)
    }

    @Test
    fun convertToDatabaseColumn_withNumber_withUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: ScientificNumberAttributeConverter =
            ScientificNumberAttributeConverter(objectMapperMock)
        val scientificNumber = ScientificNumber("1.23", "4.56")
        val scientificNumberString = "1.23u4.56"

        whenever(objectMapperMock.convertValue(scientificNumber, String::class.java))
            .thenReturn(scientificNumberString)

        val toCheck: String? = attributeConverter.convertToDatabaseColumn(scientificNumber)
        assertEquals(scientificNumberString, toCheck)
    }

    @Test
    fun convertToEntityAttribute_null() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: ScientificNumberAttributeConverter =
            ScientificNumberAttributeConverter(objectMapperMock)

        val toCheck: ScientificNumber? = attributeConverter.convertToEntityAttribute(null)
        assertEquals(null, toCheck)
    }

    @Test
    fun convertToEntityAttribute_emptyString() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: ScientificNumberAttributeConverter =
            ScientificNumberAttributeConverter(objectMapperMock)

        val toCheck: ScientificNumber? = attributeConverter.convertToEntityAttribute("")
        assertEquals(null, toCheck)
    }

    @Test
    fun convertToEntityAttribute_withNumber_noUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: ScientificNumberAttributeConverter =
            ScientificNumberAttributeConverter(objectMapperMock)
        val scientificNumber = ScientificNumber("1.23")
        val scientificNumberString = "1.23"

        whenever(objectMapperMock.convertValue(scientificNumberString, ScientificNumber::class.java))
            .thenReturn(scientificNumber)

        val toCheck: ScientificNumber? = attributeConverter.convertToEntityAttribute(scientificNumberString)
        assertEquals(scientificNumber, toCheck)
    }

    @Test
    fun convertToEntityAttribute_withNumber_withUncertainty() {
        val objectMapperMock: ObjectMapper = mock()
        val attributeConverter: ScientificNumberAttributeConverter =
            ScientificNumberAttributeConverter(objectMapperMock)
        val scientificNumber = ScientificNumber("1.23", "4.56")
        val scientificNumberString = "1.23u4.56"

        whenever(objectMapperMock.convertValue(scientificNumberString, ScientificNumber::class.java))
            .thenReturn(scientificNumber)

        val toCheck: ScientificNumber? = attributeConverter.convertToEntityAttribute(scientificNumberString)
        assertEquals(scientificNumber, toCheck)
    }
}

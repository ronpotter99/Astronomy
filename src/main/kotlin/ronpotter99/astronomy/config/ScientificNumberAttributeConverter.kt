package ronpotter99.astronomy.config

import com.fasterxml.jackson.databind.ObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import ronpotter99.astronomy.dto.ScientificNumber

@Converter(autoApply = true)
open class ScientificNumberAttributeConverter(private val objectMapper: ObjectMapper) :
        AttributeConverter<ScientificNumber, String> {

    private val logger = KotlinLogging.logger {}

    override fun convertToDatabaseColumn(scientificNumber: ScientificNumber?): String? {
        if (scientificNumber == null) {
            return null
        }

        try {
            return objectMapper.convertValue(scientificNumber, String::class.java)
        } catch (ex: Exception) {
            logger.warn { "error in convertToDatabaseColumn($scientificNumber)" }
            logger.warn { ex }
            return null
        }
    }

    override fun convertToEntityAttribute(dbData: String?): ScientificNumber? {
        if (dbData == null) {
            return null
        }

        try {
            return objectMapper.convertValue(dbData, ScientificNumber::class.java)
        } catch (ex: Exception) {
            logger.warn { "error in convertToEntityAttribute($dbData)" }
            logger.warn { ex }
            return null
        }
    }
}

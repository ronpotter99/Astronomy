package ronpotter99.astronomy.config

import io.github.oshai.kotlinlogging.KotlinLogging
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import ronpotter99.astronomy.dto.UBigInteger

@Converter(autoApply = true)
open class UBigIntegerAttributeConverter(private val objectMapper: ObjectMapper) :
        AttributeConverter<UBigInteger, String> {

    private val logger = KotlinLogging.logger {}

    override fun convertToDatabaseColumn(uBigInteger: UBigInteger?): String? {
        if (uBigInteger == null) {
            return null
        }

        try {
            return objectMapper.convertValue(uBigInteger, String::class.java)
        } catch (ex: Exception) {
            logger.warn { "error in convertToDatabaseColumn($uBigInteger)" }
            logger.warn { ex }
            return null
        }
    }

    override fun convertToEntityAttribute(dbData: String?): UBigInteger? {
        if (dbData == null) {
            return null
        }

        try {
            return objectMapper.convertValue(dbData, UBigInteger::class.java)
        } catch (ex: Exception) {
            logger.warn { "error in convertToEntityAttribute($dbData)" }
            logger.warn { ex }
            return null
        }
    }
}

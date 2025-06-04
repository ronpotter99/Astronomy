package ronpotter99.astronomy.config

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import ronpotter99.astronomy.DTO.UBigInteger

@Converter(autoApply = true)
open class UBigIntegerAttributeConverter(private val objectMapper: ObjectMapper) :
        AttributeConverter<UBigInteger, String> {

    override fun convertToDatabaseColumn(uBigInteger: UBigInteger?): String? {
        if (uBigInteger == null) {
            return null
        }

        try {
            return objectMapper.convertValue(uBigInteger, String::class.java)
        } catch (ex: Exception) {
            println("error in convertToDatabaseColumn($uBigInteger)")
            println(ex)
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
            println("error in convertToEntityAttribute($dbData)")
            println(ex)
            return null
        }
    }
}

package ronpotter99.astronomy.config

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import ronpotter99.astronomy.DTO.UBigDecimal

@Converter(autoApply = true)
open class UBigDecimalAttributeConverter(private val objectMapper: ObjectMapper) :
        AttributeConverter<UBigDecimal, String> {

    override fun convertToDatabaseColumn(uBigDecimal: UBigDecimal?): String? {
        if (uBigDecimal == null) {
            return null
        }

        try {
            return objectMapper.convertValue(uBigDecimal, String::class.java)
        } catch (ex: Exception) {
            println("error in convertToDatabaseColumn($uBigDecimal)")
            println(ex)
            return null
        }
    }

    override fun convertToEntityAttribute(dbData: String?): UBigDecimal? {
        if (dbData == null) {
            return null
        }

        try {
            return objectMapper.convertValue(dbData, UBigDecimal::class.java)
        } catch (ex: Exception) {
            println("error in convertToEntityAttribute($dbData)")
            println(ex)
            return null
        }
    }
}

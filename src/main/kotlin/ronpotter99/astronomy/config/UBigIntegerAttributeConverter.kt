package ronpotter99.astronomy.config

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import com.fasterxml.jackson.databind.ObjectMapper
import ronpotter99.astronomy.DTO.UBigInteger

@Converter(autoApply = true)
open class UBigIntegerAttributeConverter(
    private val objectMapper: ObjectMapper
): AttributeConverter<UBigInteger, String> {

    override fun convertToDatabaseColumn(uBigInteger: UBigInteger?): String? {
        if (uBigInteger == null) {
            return null
        }

        try {
            println()
            println("-----> DB")
            println("convertToDatabaseColumn($uBigInteger)")
            val uBigIntegerString = objectMapper.writeValueAsString(uBigInteger)
            println("uBigIntegerString: $uBigIntegerString")
            return uBigIntegerString
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
            println()
            println("App <-----")
            println("convertToEntityAttribute($dbData)")
            val dbObject = objectMapper.readValue(dbData, UBigInteger::class.java)
            println("dbObject: $dbObject")
            return dbObject
        } catch (ex: Exception) {
            println("error in convertToEntityAttribute($dbData)")
            println(ex)
            return null
        }
    }
}
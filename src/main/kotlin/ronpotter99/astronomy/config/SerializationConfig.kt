package ronpotter99.astronomy.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule

import ronpotter99.astronomy.config.serialization.*
import ronpotter99.astronomy.DTO.UBigDecimal
import ronpotter99.astronomy.DTO.UBigInteger

@Configuration
class SerializationConfig {
    @Bean
    fun objectMapper(): ObjectMapper {
        val serializationModule = SimpleModule()
        serializationModule.addSerializer(UBigDecimal::class.java, UBigDecimalSerializer())
        serializationModule.addSerializer(UBigInteger::class.java, UBigIntegerSerializer())
        serializationModule.addDeserializer(UBigDecimal::class.java, UBigDecimalDeserializer())
        serializationModule.addDeserializer(UBigInteger::class.java, UBigIntegerDeserializer())

        val objectMapper = ObjectMapper()
        objectMapper.registerModule(serializationModule)
        return objectMapper
    }
}
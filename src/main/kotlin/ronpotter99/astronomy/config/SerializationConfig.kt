package ronpotter99.astronomy.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ronpotter99.astronomy.DTO.UBigDecimal
import ronpotter99.astronomy.DTO.UBigInteger
import ronpotter99.astronomy.config.serialization.*

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

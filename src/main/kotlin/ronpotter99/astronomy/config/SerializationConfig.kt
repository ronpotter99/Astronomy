package ronpotter99.astronomy.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Bean
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import java.math.BigInteger
import java.math.BigDecimal
import ronpotter99.astronomy.config.serialization.*

@Configuration
class SerializationConfig {
    @Bean
    fun objectMapper(): ObjectMapper {
        val serializationModule = SimpleModule()
        serializationModule.addSerializer(BigDecimal::class.java, BigDecimalSerializer())
        serializationModule.addSerializer(BigInteger::class.java, BigIntegerSerializer())
        serializationModule.addDeserializer(BigDecimal::class.java, BigDecimalDeserializer())
        serializationModule.addDeserializer(BigInteger::class.java, BigIntegerDeserializer())

        val objectMapper = ObjectMapper()
        objectMapper.registerModule(serializationModule)
        return objectMapper
    }
}
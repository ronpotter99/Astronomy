package ronpotter99.astronomy.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ronpotter99.astronomy.config.serialization.*
import ronpotter99.astronomy.service.equation.IEquation

@Configuration
class EquationConfig(private val equations: List<IEquation>) {

    @Bean
    fun equationMap(): HashMap<String, IEquation> {
        var equationMap = HashMap<String, IEquation>()

        for (equation in equations) {
            equationMap.put(equation.getEquationReference().uppercase(), equation)
        }

        return equationMap
    }
}

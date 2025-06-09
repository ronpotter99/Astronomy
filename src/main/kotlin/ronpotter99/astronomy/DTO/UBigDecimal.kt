package ronpotter99.astronomy.DTO

import java.math.BigDecimal

data class UBigDecimal(
    var number: BigDecimal,
    var uncertainty: BigDecimal? = null
) {
    operator fun unaryPlus(): UBigDecimal {
        return UBigDecimal(number, uncertainty)
    }

    operator fun unaryMinus(): UBigDecimal {
        return UBigDecimal(-number, uncertainty)
    }
    
    operator fun plus(toAdd: UBigDecimal): UBigDecimal {
        // TODO
        return UBigDecimal(number, uncertainty)
    }
    
    operator fun minus(toSubtract: UBigDecimal): UBigDecimal {
        // TODO
        return UBigDecimal(number, uncertainty)
    }
    
    operator fun times(toMultiply: UBigDecimal): UBigDecimal {
        // TODO
        return UBigDecimal(number, uncertainty)
    }
    
    operator fun div(toDivide: UBigDecimal): UBigDecimal {
        // TODO
        return UBigDecimal(number, uncertainty)
    }
    
    operator fun rem(divisor: BigDecimal): BigDecimal {
        return number % divisor
    }
    
    operator fun rem(divisor: Long): BigDecimal {
        return rem(BigDecimal.valueOf(divisor))
    }
    
    operator fun rem(divisor: Double): BigDecimal {
        return rem(BigDecimal.valueOf(divisor))
    }
}

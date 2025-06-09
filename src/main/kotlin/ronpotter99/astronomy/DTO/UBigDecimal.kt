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
    
    operator fun inc(): UBigDecimal {
        return (UBigDecimal(number, uncertainty) + UBigDecimal(BigDecimal("1")))
    }
    
    operator fun dec(): UBigDecimal {
        return (UBigDecimal(number, uncertainty) - UBigDecimal(BigDecimal("1")))
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
    
    operator fun rem(divisor: UBigDecimal): UBigDecimal {
        return UBigDecimal(number % divisor.number, null)
    }
}

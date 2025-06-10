package ronpotter99.astronomy.DTO

import java.math.BigDecimal
import kotlin.math.*

data class ScientificNumber(var number: BigDecimal, var uncertainty: BigDecimal? = null) {

    constructor(
            numberString: String,
            uncertaintyString: String? = null
    ) : this(BigDecimal(numberString), uncertaintyString?.let { BigDecimal(it) })

    operator fun unaryPlus(): ScientificNumber {
        return ScientificNumber(number, uncertainty)
    }

    operator fun unaryMinus(): ScientificNumber {
        return ScientificNumber(-number, uncertainty)
    }

    operator fun plus(toAdd: ScientificNumber): ScientificNumber {
        // TODO
        return ScientificNumber(number, uncertainty)
    }

    operator fun minus(toSubtract: ScientificNumber): ScientificNumber {
        // TODO
        return ScientificNumber(number, uncertainty)
    }

    operator fun times(toMultiply: ScientificNumber): ScientificNumber {
        // TODO
        return ScientificNumber(number, uncertainty)
    }

    operator fun div(toDivide: ScientificNumber): ScientificNumber {
        // TODO
        return ScientificNumber(number, uncertainty)
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

    fun fractionalUncertainty(): BigDecimal? {
        return uncertainty?.let { uncertainty as BigDecimal / number.abs() }
    }

    fun significantFigures(): Pair<Int, Int> {
        val numberToCheck: BigDecimal = number
        val uncertaintyToCheck: BigDecimal = uncertainty?.let { it } ?: BigDecimal("0")

        val numberSigFigs =
                if (numberToCheck.compareTo(BigDecimal("0")) == 0) {
                    0
                } else {
                    numberToCheck.precision()
                }

        val uncertaintySigFigs =
                if (uncertaintyToCheck.compareTo(BigDecimal("0")) == 0) {
                    0
                } else {
                    uncertaintyToCheck.precision()
                }

        return Pair(numberSigFigs, uncertaintySigFigs)
    }
}

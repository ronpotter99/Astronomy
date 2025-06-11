package ronpotter99.astronomy.DTO

import ch.obermuhlner.math.big.DefaultBigDecimalMath.*
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
        val newNumber = add(number, toAdd.number)

        val baseUncertainty = uncertainty?.let { pow(it, 2) } ?: BigDecimal("0")
        val inputUncertainty = toAdd.uncertainty?.let { pow(it, 2) } ?: BigDecimal("0")
        val newUncertainty = sqrt(add(baseUncertainty, inputUncertainty))

        return ScientificNumber(newNumber, newUncertainty)
    }

    operator fun minus(toSubtract: ScientificNumber): ScientificNumber {
        val newNumber = subtract(number, toSubtract.number)

        val baseUncertainty = uncertainty?.let { pow(it, 2) } ?: BigDecimal("0")
        val inputUncertainty = toSubtract.uncertainty?.let { pow(it, 2) } ?: BigDecimal("0")
        val newUncertainty = sqrt(add(baseUncertainty, inputUncertainty))

        return ScientificNumber(newNumber, newUncertainty)
    }

    operator fun times(toMultiply: ScientificNumber): ScientificNumber {
        val newNumber = multiply(number, toMultiply.number)

        val baseUncertainty = uncertainty?.let { pow(divide(it, number), 2) } ?: BigDecimal("0")
        val inputUncertainty = toMultiply.uncertainty?.let { pow(divide(it, toMultiply.number), 2) } ?: BigDecimal("0")
        val newUncertainty = multiply(newNumber.abs(), sqrt(add(baseUncertainty, inputUncertainty)))

        return ScientificNumber(newNumber, newUncertainty)
    }

    operator fun div(toDivide: ScientificNumber): ScientificNumber {
        val newNumber = divide(number, toDivide.number)

        val baseUncertainty = uncertainty?.let { pow(divide(it, number), 2) } ?: BigDecimal("0")
        val inputUncertainty = toDivide.uncertainty?.let { pow(divide(it, toDivide.number), 2) } ?: BigDecimal("0")
        val newUncertainty = multiply(newNumber.abs(), sqrt(add(baseUncertainty, inputUncertainty)))

        return ScientificNumber(newNumber, newUncertainty)
    }

    operator fun rem(divisor: BigDecimal): BigDecimal {
        return remainder(number, divisor)
    }

    operator fun rem(divisor: Long): BigDecimal {
        return rem(BigDecimal.valueOf(divisor))
    }

    operator fun rem(divisor: Double): BigDecimal {
        return rem(BigDecimal.valueOf(divisor))
    }

    fun fractionalUncertainty(): BigDecimal? {
        return uncertainty?.let { divide(it, number.abs()) }
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

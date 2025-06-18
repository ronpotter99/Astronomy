package ronpotter99.astronomy.DTO

import ch.obermuhlner.math.big.DefaultBigDecimalMath.*
import java.math.BigDecimal
import java.util.IllegalFormatException
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

        val newUncertainty =
                if (uncertainty != null || toAdd.uncertainty != null) {
                    val baseUncertainty = uncertainty?.let { pow(it, 2) } ?: BigDecimal("0")
                    val inputUncertainty = toAdd.uncertainty?.let { pow(it, 2) } ?: BigDecimal("0")
                    sqrt(add(baseUncertainty, inputUncertainty))
                } else {
                    null
                }

        return ScientificNumber(newNumber, newUncertainty)
    }

    operator fun minus(toSubtract: ScientificNumber): ScientificNumber {
        val newNumber = subtract(number, toSubtract.number)

        val newUncertainty =
                if (uncertainty != null || toSubtract.uncertainty != null) {
                    val baseUncertainty = uncertainty?.let { pow(it, 2) } ?: BigDecimal("0")
                    val inputUncertainty =
                            toSubtract.uncertainty?.let { pow(it, 2) } ?: BigDecimal("0")
                    sqrt(add(baseUncertainty, inputUncertainty))
                } else {
                    null
                }

        return ScientificNumber(newNumber, newUncertainty)
    }

    operator fun times(toMultiply: ScientificNumber): ScientificNumber {
        val newNumber = multiply(number, toMultiply.number)

        val newUncertainty =
                if (uncertainty != null || toMultiply.uncertainty != null) {
                    val baseUncertainty =
                            uncertainty?.let { pow(divide(it, number), 2) } ?: BigDecimal("0")
                    val inputUncertainty =
                            toMultiply.uncertainty?.let { pow(divide(it, toMultiply.number), 2) }
                                    ?: BigDecimal("0")
                    multiply(newNumber.abs(), sqrt(add(baseUncertainty, inputUncertainty)))
                } else {
                    null
                }

        return ScientificNumber(newNumber, newUncertainty)
    }

    operator fun div(toDivide: ScientificNumber): ScientificNumber {
        val newNumber = divide(number, toDivide.number)

        val newUncertainty =
                if (uncertainty != null || toDivide.uncertainty != null) {
                    val baseUncertainty =
                            uncertainty?.let { pow(divide(it, number), 2) } ?: BigDecimal("0")
                    val inputUncertainty =
                            toDivide.uncertainty?.let { pow(divide(it, toDivide.number), 2) }
                                    ?: BigDecimal("0")
                    multiply(newNumber.abs(), sqrt(add(baseUncertainty, inputUncertainty)))
                } else {
                    null
                }

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

    fun pow(exponent: BigDecimal): ScientificNumber {
        val newNumber = pow(number, exponent)

        val newUncertainty =
                uncertainty?.let {
                    multiply(
                            multiply(
                                    exponent.abs(),
                                    pow(number, subtract(exponent, BigDecimal("1")))
                            ),
                            it
                    )
                }

        return ScientificNumber(newNumber, newUncertainty)
    }

    fun fractionalUncertainty(): BigDecimal? {
        return uncertainty?.let { divide(it, number.abs()) }
    }

    /**
     * The ScientificNumber is used for exact calculations.
     * For exact significant digit management, always use scientific notation.
     * Thus, ScientificNumber uses the following rules for significant digits:
     * 
     * 1. All non-zero numbers are significant.
     * 2. Zeros between two non-zero numbers are always significant.
     * 3. Zeros that only set the decimal point to the left are not significant.
     *      Both 0.000035 and 0.35 contain two significant figures.
     * 4. Zeros that aren't needed to hold the decimal point are significant.
     *      35.0 has three significant digits.
     * 5. Zeros following a whole number with no decimal ARE significant if 
     *      entered as a whole number. 3500 has FOUR significant digits.
     * 6. Scientific notation explicitly defines the number of significant digits.
     *      3500 has FOUR significant digits, but 3.5e3 has TWO significant digits.
     * 7. Exactly defined values with explicitly defined 0 uncertainty have an 
     *      infinite number of significant figures. 0 uncertainty and null uncertainty
     *      are NOT the same thing. Exactly defined values are either numbers defined 
     *      by their definitions (1 AU = 149,597,870,700 m) or by counting objects
     *      where the process contains no inherent uncertainty (3 telescopes).
     *      Note: counting in Astronomy often contains inherent uncertainty.
     * 
     * Warning: Running the 'stripTrailingZeros()' function on BigDecimal will ruin 
     *      significant figure calculation.
     */
    fun significantFigures(): Pair<Int, Int> {

        val numberSigFigs =
                if (uncertainty != null && uncertainty!!.compareTo(BigDecimal("0")) == 0) {
                    Int.MAX_VALUE
                } else if (number.compareTo(BigDecimal("0")) == 0) {
                    0
                } else {
                    number.precision()
                }

        val uncertaintySigFigs =
                if (uncertainty == null) {
                    0
                } else if (uncertainty!!.compareTo(BigDecimal("0")) == 0) {
                    Int.MAX_VALUE
                } else {
                    uncertainty!!.precision()
                }

        return Pair(numberSigFigs, uncertaintySigFigs)
    }

    /**
     * The ScientificNumber is used for exact calculations.
     * 
     * This method is used for counting the number of decimal places a number and uncertainty 
     * have. A null uncertainty has 0 decimal places.
     */
    fun fractionalLength(): Pair<Int, Int> {

        val plainNumber: BigDecimal = BigDecimal(number.toPlainString())
        val plainUncertainty: BigDecimal = BigDecimal(uncertainty?.let { it.toPlainString() } ?: "0")
        
        val fractionalNumberSize: Int = plainNumber.scale()
        val fractionalUncertaintySize: Int = plainUncertainty.scale()

        return Pair(fractionalNumberSize, fractionalUncertaintySize)
    }
}

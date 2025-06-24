package ronpotter99.astronomy.DTO

import ch.obermuhlner.math.big.DefaultBigDecimalMath as BDMath
import java.math.BigDecimal
import java.math.RoundingMode
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
        return add(this, toAdd)
    }

    operator fun minus(toSubtract: ScientificNumber): ScientificNumber {
        val newNumber = BDMath.subtract(number, toSubtract.number)

        val newUncertainty =
                if (uncertainty != null || toSubtract.uncertainty != null) {
                    val baseUncertainty = uncertainty?.let { BDMath.pow(it, 2) } ?: BigDecimal("0")
                    val inputUncertainty =
                            toSubtract.uncertainty?.let { BDMath.pow(it, 2) } ?: BigDecimal("0")
                    BDMath.sqrt(BDMath.add(baseUncertainty, inputUncertainty))
                } else {
                    null
                }

        return ScientificNumber(newNumber, newUncertainty)
    }

    operator fun times(toMultiply: ScientificNumber): ScientificNumber {
        val newNumber = BDMath.multiply(number, toMultiply.number)

        val newUncertainty =
                if (uncertainty != null || toMultiply.uncertainty != null) {
                    val baseUncertainty =
                            uncertainty?.let { BDMath.pow(BDMath.divide(it, number), 2) } ?: BigDecimal("0")
                    val inputUncertainty =
                            toMultiply.uncertainty?.let { BDMath.pow(BDMath.divide(it, toMultiply.number), 2) }
                                    ?: BigDecimal("0")
                    BDMath.multiply(newNumber.abs(), BDMath.sqrt(BDMath.add(baseUncertainty, inputUncertainty)))
                } else {
                    null
                }

        return ScientificNumber(newNumber, newUncertainty)
    }

    operator fun div(toDivide: ScientificNumber): ScientificNumber {
        val newNumber = BDMath.divide(number, toDivide.number)

        val newUncertainty =
                if (uncertainty != null || toDivide.uncertainty != null) {
                    val baseUncertainty =
                            uncertainty?.let { BDMath.pow(BDMath.divide(it, number), 2) } ?: BigDecimal("0")
                    val inputUncertainty =
                            toDivide.uncertainty?.let { BDMath.pow(BDMath.divide(it, toDivide.number), 2) }
                                    ?: BigDecimal("0")
                    BDMath.multiply(newNumber.abs(), BDMath.sqrt(BDMath.add(baseUncertainty, inputUncertainty)))
                } else {
                    null
                }

        return ScientificNumber(newNumber, newUncertainty)
    }

    operator fun rem(divisor: BigDecimal): BigDecimal {
        return BDMath.remainder(number, divisor)
    }

    operator fun rem(divisor: Long): BigDecimal {
        return rem(BigDecimal.valueOf(divisor))
    }

    operator fun rem(divisor: Double): BigDecimal {
        return rem(BigDecimal.valueOf(divisor))
    }

    fun pow(exponent: BigDecimal): ScientificNumber {
        val newNumber = BDMath.pow(number, exponent)

        val newUncertainty =
                uncertainty?.let {
                    BDMath.multiply(
                            BDMath.multiply(
                                    exponent.abs(),
                                    BDMath.pow(number, BDMath.subtract(exponent, BigDecimal("1")))
                            ),
                            it
                    )
                }

        return ScientificNumber(newNumber, newUncertainty)
    }

    fun fractionalUncertainty(): BigDecimal? {
        return uncertainty?.let { BDMath.divide(it, number.abs()) }
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

    companion object {
        /**
         * This method is used for adding multiple ScientificNumbers together. By using this static method instead of 
         * using the kotlin plus operator, numbers are correctly rounded after addition of more than two values.
         * 
         * This is the same as using the kotlin plus operator if only two numbers are added.
         */
        fun add(vararg scientificNumbers: ScientificNumber, roundingMode: RoundingMode = RoundingMode.HALF_EVEN): ScientificNumber {
            var newNumber: BigDecimal = BigDecimal("0")
            var newUncertainty: BigDecimal? = null

            var numberFractionalLengths: MutableList<Int> = mutableListOf()
            var uncertaintyFractionalLengths: MutableList<Int> = mutableListOf()

            if (scientificNumbers.isEmpty()) {
                throw IllegalArgumentException("Must pass in a non-empty list of numbers to add.")
            }

            scientificNumbers.forEach {
                val (numberFractionalLength: Int, uncertaintyFractionalLength: Int) = it.fractionalLength()
                
                newNumber = BDMath.add(newNumber, it.number)
                numberFractionalLengths.add(numberFractionalLength)

                it.uncertainty?.let {
                    newUncertainty = BDMath.add(newUncertainty ?: BigDecimal("0"), BDMath.pow(it, 2))
                    uncertaintyFractionalLengths.add(uncertaintyFractionalLength)
                }
            }

            newNumber = newNumber.setScale(numberFractionalLengths.min(), roundingMode)

            if (newUncertainty != null) {
                newUncertainty = BDMath.sqrt(newUncertainty)
                newUncertainty = newUncertainty!!.setScale(uncertaintyFractionalLengths.min(), roundingMode)
            }

            return ScientificNumber(newNumber, newUncertainty)
        }
    }
}

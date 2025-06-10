package ronpotter99.astronomy.DTO

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class ScientificNumberTests {

    @Test
    fun significantFigures_zeroValue() {
        val toCheck = ScientificNumber(BigDecimal("0"), BigDecimal("0"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(0, numberSigFigs)
        assertEquals(0, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_zeroValue_trailingZeros() {
        val toCheck = ScientificNumber(BigDecimal("0.000"), BigDecimal("0.00000"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(0, numberSigFigs)
        assertEquals(0, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_zeroValue_leadingZeros() {
        val toCheck = ScientificNumber(BigDecimal("000000"), BigDecimal("0000000"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(0, numberSigFigs)
        assertEquals(0, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_noUncertainty() {
        val toCheck = ScientificNumber(BigDecimal("0"), null)
        val uncertaintySigFigs = toCheck.significantFigures().second

        assertEquals(0, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_wholeNumbers() {
        val toCheck = ScientificNumber(BigDecimal("12345"), BigDecimal("123456789"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(9, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_wholeNumbers_negativeValue() {
        val toCheck = ScientificNumber(BigDecimal("-35"), BigDecimal("-34672"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(2, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_wholeNumbers_leadingZeros() {
        val toCheck = ScientificNumber(BigDecimal("000000000012345"), BigDecimal("0000123"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(3, uncertaintySigFigs)
    }

    /**
     * Whole numbers with trailing zeros are treated as if they have an implicit decimal point. If a
     * different significant figure precision is required, use scientific notation.
     */
    @Test
    fun significantFigures_wholeNumbers_trailingZeros() {
        val toCheck =
                ScientificNumber(BigDecimal("35000000000000"), BigDecimal("3467200000000000000000"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(14, numberSigFigs)
        assertEquals(22, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_wholeNumbers_trailingZeros_withDecimalPoint_noDecimalValue() {
        val toCheck =
                ScientificNumber(
                        BigDecimal("35000000000000."),
                        BigDecimal("3467200000000000000000.")
                )
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(14, numberSigFigs)
        assertEquals(22, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_wholeNumbers_trailingZeros_withDecimalPoint_withZeroDecimalValue() {
        val toCheck =
                ScientificNumber(
                        BigDecimal("35000000000000.0"),
                        BigDecimal("3467200000000000000000.0")
                )
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(15, numberSigFigs)
        assertEquals(23, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers() {
        val toCheck = ScientificNumber(BigDecimal("12.345"), BigDecimal("12.3"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(3, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers_negativeValue() {
        val toCheck = ScientificNumber(BigDecimal("-1.23"), BigDecimal("-3.4672"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(3, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers_firstCharacterDecimalPoint() {
        val toCheck = ScientificNumber(BigDecimal(".12345"), BigDecimal(".123"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(3, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers_firstCharacterDecimalPoint_negativeValue() {
        val toCheck = ScientificNumber(BigDecimal("-.12345"), BigDecimal("-.123"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(3, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers_leadingZeros_withWholeNumber() {
        val toCheck = ScientificNumber(BigDecimal("00001.2345"), BigDecimal("001.23"))
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(3, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers_leadingZeros_noWholeNumber() {
        val toCheck =
                ScientificNumber(
                        BigDecimal("0.00000000000035"),
                        BigDecimal("0.0000000000000000034672")
                )
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(2, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }
}

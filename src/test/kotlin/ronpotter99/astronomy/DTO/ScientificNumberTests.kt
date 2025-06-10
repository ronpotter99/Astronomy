package ronpotter99.astronomy.DTO

import kotlin.test.Test
import kotlin.test.assertEquals

class ScientificNumberTests {

    @Test
    fun significantFigures_zeroValue() {
        val toCheck = ScientificNumber("0", "0")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(0, numberSigFigs)
        assertEquals(0, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_zeroValue_trailingZeros() {
        val toCheck = ScientificNumber("0.000", "0.00000")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(0, numberSigFigs)
        assertEquals(0, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_zeroValue_leadingZeros() {
        val toCheck = ScientificNumber("000000", "0000000")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(0, numberSigFigs)
        assertEquals(0, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_noUncertainty() {
        val toCheck = ScientificNumber("0", null)
        val uncertaintySigFigs = toCheck.significantFigures().second

        assertEquals(0, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_wholeNumbers() {
        val toCheck = ScientificNumber("12345", "123456789")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(9, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_wholeNumbers_negativeValue() {
        val toCheck = ScientificNumber("-35", "-34672")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(2, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_wholeNumbers_leadingZeros() {
        val toCheck = ScientificNumber("000000000012345", "0000123")
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
        val toCheck = ScientificNumber("35000000000000", "3467200000000000000000")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(14, numberSigFigs)
        assertEquals(22, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_wholeNumbers_trailingZeros_withDecimalPoint_noDecimalValue() {
        val toCheck = ScientificNumber("35000000000000.", "3467200000000000000000.")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(14, numberSigFigs)
        assertEquals(22, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_wholeNumbers_trailingZeros_withDecimalPoint_withZeroDecimalValue() {
        val toCheck = ScientificNumber("35000000000000.0", "3467200000000000000000.0")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(15, numberSigFigs)
        assertEquals(23, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers() {
        val toCheck = ScientificNumber("12.345", "12.3")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(3, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers_negativeValue() {
        val toCheck = ScientificNumber("-1.23", "-3.4672")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(3, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers_firstCharacterDecimalPoint() {
        val toCheck = ScientificNumber(".12345", ".123")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(3, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers_firstCharacterDecimalPoint_negativeValue() {
        val toCheck = ScientificNumber("-.12345", "-.123")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(3, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers_leadingZeros_withWholeNumber() {
        val toCheck = ScientificNumber("00001.2345", "001.23")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(5, numberSigFigs)
        assertEquals(3, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_decimalNumbers_leadingZeros_noWholeNumber() {
        val toCheck = ScientificNumber("0.00000000000035", "0.0000000000000000034672")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(2, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_scientificNotation_wholeNumber() {
        val toCheck = ScientificNumber("3.5e4", "3.4672e7")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(2, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_scientificNotation_decimalNumber() {
        val toCheck = ScientificNumber("3.524e1", "3.4672e2")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(4, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_scientificNotation_largePositiveExponent() {
        val toCheck = ScientificNumber("3.5e137", "3.4672e674")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(2, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_scientificNotation_largePositiveExponent_trailingZeros() {
        val toCheck = ScientificNumber("3.500e137", "3.46720000e674")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(4, numberSigFigs)
        assertEquals(9, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_scientificNotation_largePositiveExponent_leadingZeros() {
        val toCheck = ScientificNumber("0.0035e137", "0.0000034672e674")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(2, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_scientificNotation_largeNegativeExponent() {
        val toCheck = ScientificNumber("3.5e-137", "3.4672e-674")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(2, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_scientificNotation_largeNegativeExponent_trailingZeros() {
        val toCheck = ScientificNumber("3.500e-137", "3.46720000e-674")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(4, numberSigFigs)
        assertEquals(9, uncertaintySigFigs)
    }

    @Test
    fun significantFigures_scientificNotation_largeNegativeExponent_leadingZeros() {
        val toCheck = ScientificNumber("0.0035e-137", "0.00034672e-674")
        val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

        assertEquals(2, numberSigFigs)
        assertEquals(5, uncertaintySigFigs)
    }
}

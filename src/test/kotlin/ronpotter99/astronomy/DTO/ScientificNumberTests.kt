package ronpotter99.astronomy.DTO

import java.math.RoundingMode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

/**
 * This is an abstract class to encapsulate all ScientificNumber method tests. Individual method
 * tests are encapsulated in subclasses for better organization.
 */
abstract class ScientificNumberTests {

    /**
     * This is a class to encapsulate all significantFigure method tests within the ScientificNumber
     * class.
     */
    class SNSignificantFigures : ScientificNumberTests() {
        /** This test verifies a null uncertainty has 0 significant figures. */
        @Test
        fun significantFigures_ignoreNumber_noUncertainty() {
            val toCheck = ScientificNumber("0", null)
            val uncertaintySigFigs = toCheck.significantFigures().second

            assertEquals(0, uncertaintySigFigs)
        }

        /** This test verifies number 0 with unknown uncertainty has 0 significant figures. */
        @Test
        fun significantFigures_zeroNumber_noUncertainty() {
            val toCheck = ScientificNumber("0", null)
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(0, numberSigFigs)
            assertEquals(0, uncertaintySigFigs)
        }

        /**
         * This test verifies that for any number value, including zero, if the uncertainty is zero
         * then that number is exactly known and has infinite significant figures.
         *
         * See significantFigure method documentation for full list of rules followed.
         */
        @Test
        fun significantFigures_anyNumber_zeroUncertainty() {
            val toCheck = ScientificNumber("0", "0")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(Int.MAX_VALUE, numberSigFigs)
            assertEquals(Int.MAX_VALUE, uncertaintySigFigs)
        }

        /**
         * This test verifies trailing zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun significantFigures_zeroNumber_noUncertainty_trailingZeros() {
            val toCheck = ScientificNumber("0.000", null)
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(0, numberSigFigs)
            assertEquals(0, uncertaintySigFigs)
        }

        /**
         * This test verifies trailing zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun significantFigures_zeroNumber_zeroUncertainty_trailingZeros() {
            val toCheck = ScientificNumber("0.000", "0.00000")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(Int.MAX_VALUE, numberSigFigs)
            assertEquals(Int.MAX_VALUE, uncertaintySigFigs)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun significantFigures_zeroNumber_noUncertainty_leadingZeros() {
            val toCheck = ScientificNumber("000000", null)
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(0, numberSigFigs)
            assertEquals(0, uncertaintySigFigs)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun significantFigures_zeroNumber_zeroUncertainty_leadingZeros() {
            val toCheck = ScientificNumber("000000", "0000000")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(Int.MAX_VALUE, numberSigFigs)
            assertEquals(Int.MAX_VALUE, uncertaintySigFigs)
        }

        /**
         * This test verifies whole numbers with no decimal portion are correctly converted to
         * significant figures.
         *
         * This explicitly does not include zeros.
         */
        @Test
        fun significantFigures_wholeNumbers() {
            val toCheck = ScientificNumber("12345", "123456789")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(5, numberSigFigs)
            assertEquals(9, uncertaintySigFigs)
        }

        /**
         * This test verifies negative whole numbers with no decimal portion are correctly converted
         * to significant figures.
         *
         * This explicitly does not include zeros.
         */
        @Test
        fun significantFigures_wholeNumbers_negativeValue() {
            val toCheck = ScientificNumber("-35", "-34672")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(2, numberSigFigs)
            assertEquals(5, uncertaintySigFigs)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun significantFigures_wholeNumbers_leadingZeros() {
            val toCheck = ScientificNumber("000000000012345", "0000123")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(5, numberSigFigs)
            assertEquals(3, uncertaintySigFigs)
        }

        /**
         * This test verifies whole numbers with trailing zeros are treated as if they have an
         * implicit decimal point. If a different significant figure precision is required, use
         * scientific notation.
         *
         * See significantFigure method documentation for full list of rules followed.
         */
        @Test
        fun significantFigures_wholeNumbers_trailingZeros() {
            val toCheck = ScientificNumber("35000000000000", "3467200000000000000000")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(14, numberSigFigs)
            assertEquals(22, uncertaintySigFigs)
        }

        /** This test verifies all whole numbers with a decimal point are significant figures. */
        @Test
        fun significantFigures_wholeNumbers_trailingZeros_withDecimalPoint_noDecimalValue() {
            val toCheck = ScientificNumber("35000000000000.", "3467200000000000000000.")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(14, numberSigFigs)
            assertEquals(22, uncertaintySigFigs)
        }

        /**
         * This test verifies zeros not required to hold a decimal point are counted as significant
         * figures.
         */
        @Test
        fun significantFigures_wholeNumbers_trailingZeros_withDecimalPoint_withZeroDecimalValue() {
            val toCheck = ScientificNumber("35000000000000.0", "3467200000000000000000.0")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(15, numberSigFigs)
            assertEquals(23, uncertaintySigFigs)
        }

        /**
         * This test verifies numbers with a decimal portion are correctly converted to significant
         * figures.
         *
         * This explicitly does not include zeros.
         */
        @Test
        fun significantFigures_decimalNumbers() {
            val toCheck = ScientificNumber("12.345", "12.3")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(5, numberSigFigs)
            assertEquals(3, uncertaintySigFigs)
        }

        /**
         * This test verifies negative numbers with a decimal portion are correctly converted to
         * significant figures.
         *
         * This explicitly does not include zeros.
         */
        @Test
        fun significantFigures_decimalNumbers_negativeValue() {
            val toCheck = ScientificNumber("-1.23", "-3.4672")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(3, numberSigFigs)
            assertEquals(5, uncertaintySigFigs)
        }

        /**
         * This test verifies numbers with only a decimal portion are correctly converted to
         * significant figures.
         *
         * This explicitly does not include zeros.
         */
        @Test
        fun significantFigures_decimalNumbers_firstCharacterDecimalPoint() {
            val toCheck = ScientificNumber(".12345", ".123")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(5, numberSigFigs)
            assertEquals(3, uncertaintySigFigs)
        }

        /**
         * This test verifies negative numbers with only a decimal portion are correctly converted
         * to significant figures.
         *
         * This explicitly does not include zeros.
         */
        @Test
        fun significantFigures_decimalNumbers_firstCharacterDecimalPoint_negativeValue() {
            val toCheck = ScientificNumber("-.12345", "-.123")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(5, numberSigFigs)
            assertEquals(3, uncertaintySigFigs)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun significantFigures_decimalNumbers_leadingZeros_withWholeNumber() {
            val toCheck = ScientificNumber("00001.2345", "001.23")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(5, numberSigFigs)
            assertEquals(3, uncertaintySigFigs)
        }

        /**
         * This test verifies trailing zeros in decimal numbers are counted as having significant
         * figure precision.
         *
         * See significantFigure method documentation for full list of rules followed.
         */
        @Test
        fun significantFigures_decimalNumbers_trailingZeros_withWholeNumber() {
            val toCheck = ScientificNumber("1.23450000", "1.2300")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(9, numberSigFigs)
            assertEquals(5, uncertaintySigFigs)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun significantFigures_decimalNumbers_leadingZeros_noWholeNumber() {
            val toCheck = ScientificNumber("0.00000000000035", "0.0000000000000000034672")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(2, numberSigFigs)
            assertEquals(5, uncertaintySigFigs)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests, while trailing zeros in decimals work as expected.
         *
         * See significantFigure method documentation for full list of rules followed.
         */
        @Test
        fun significantFigures_decimalNumbers_leadingAndTrailingZeros_noWholeNumber() {
            val toCheck = ScientificNumber("0.0000000000003500", "0.000000000000000003467200")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(4, numberSigFigs)
            assertEquals(7, uncertaintySigFigs)
        }

        /** This test verifies scientific notation sets significant figures. */
        @Test
        fun significantFigures_scientificNotation_wholeNumber() {
            val toCheck = ScientificNumber("3.5e4", "3.4672e7")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(2, numberSigFigs)
            assertEquals(5, uncertaintySigFigs)
        }

        /** This test verifies scientific notation sets significant figures. */
        @Test
        fun significantFigures_scientificNotation_decimalNumber() {
            val toCheck = ScientificNumber("3.524e1", "3.4672e2")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(4, numberSigFigs)
            assertEquals(5, uncertaintySigFigs)
        }

        /** This test verifies scientific notation sets significant figures. */
        @Test
        fun significantFigures_scientificNotation_largePositiveExponent() {
            val toCheck = ScientificNumber("3.5e137", "3.4672e674")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(2, numberSigFigs)
            assertEquals(5, uncertaintySigFigs)
        }

        /** This test verifies scientific notation sets significant figures. */
        @Test
        fun significantFigures_scientificNotation_largePositiveExponent_trailingZeros() {
            val toCheck = ScientificNumber("3.500e137", "3.46720000e674")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(4, numberSigFigs)
            assertEquals(9, uncertaintySigFigs)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun significantFigures_scientificNotation_largePositiveExponent_leadingZeros() {
            val toCheck = ScientificNumber("0.0035e137", "0.0000034672e674")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(2, numberSigFigs)
            assertEquals(5, uncertaintySigFigs)
        }

        /** This test verifies scientific notation sets significant figures. */
        @Test
        fun significantFigures_scientificNotation_largeNegativeExponent() {
            val toCheck = ScientificNumber("3.5e-137", "3.4672e-674")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(2, numberSigFigs)
            assertEquals(5, uncertaintySigFigs)
        }

        /** This test verifies scientific notation sets significant figures. */
        @Test
        fun significantFigures_scientificNotation_largeNegativeExponent_trailingZeros() {
            val toCheck = ScientificNumber("3.500e-137", "3.46720000e-674")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(4, numberSigFigs)
            assertEquals(9, uncertaintySigFigs)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun significantFigures_scientificNotation_largeNegativeExponent_leadingZeros() {
            val toCheck = ScientificNumber("0.0035e-137", "0.00034672e-674")
            val (numberSigFigs, uncertaintySigFigs) = toCheck.significantFigures()

            assertEquals(2, numberSigFigs)
            assertEquals(5, uncertaintySigFigs)
        }
    }

    /**
     * This is a class to encapsulate all fractionalLength method tests within the ScientificNumber
     * class.
     */
    class SNFractionalLength : ScientificNumberTests() {
        /** This test verifies a null uncertainty has 0 decimals. */
        @Test
        fun fractionalLength_ignoreNumber_noUncertainty() {
            val toCheck = ScientificNumber("0", null)
            val uncertaintyFractionalLength = toCheck.fractionalLength().second

            assertEquals(0, uncertaintyFractionalLength)
        }

        /** This test verifies number 0 with unknown uncertainty has 0 decimals. */
        @Test
        fun fractionalLength_zeroNumber_noUncertainty() {
            val toCheck = ScientificNumber("0", null)
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
        }

        /** This test verifies any whole number with 0 uncertainty has 0 decimals. */
        @Test
        fun fractionalLength_anyWholeNumber_zeroUncertainty() {
            val toCheck = ScientificNumber("5", "0")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun fractionalLength_wholeNumbers_leadingZeros() {
            val toCheck = ScientificNumber("000000000012345", "0000123")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
        }

        /**
         * This test verifies trailing zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun fractionalLength_wholeNumbers_trailingZeros() {
            val toCheck = ScientificNumber("35000000000000", "3467200000000000000000")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
        }

        /**
         * This test verifies trailing zeros do not have different outputs from expected based on
         * previous tests, and a decimal point with no decimals does not change expected decimal
         * value.
         */
        @Test
        fun fractionalLength_wholeNumbers_trailingZeros_withDecimalPoint_noDecimalValue() {
            val toCheck = ScientificNumber("35000000000000.", "3467200000000000000000.")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
        }

        /** This test verifies zeros count as decimals. */
        @Test
        fun fractionalLength_decimalNumbers_decimalZeros() {
            val toCheck = ScientificNumber("10.000", "1.00000")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(3, numberFractionalLength)
            assertEquals(5, uncertaintyFractionalLength)
        }

        /**
         * This test verifies numbers with a decimal portion are correctly counted.
         *
         * This explicitly does not include zeros.
         */
        @Test
        fun fractionalLength_decimalNumbers() {
            val toCheck = ScientificNumber("12.345", "12.3")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(3, numberFractionalLength)
            assertEquals(1, uncertaintyFractionalLength)
        }

        /**
         * This test verifies negative numbers with a decimal portion are correctly counted.
         *
         * This explicitly does not include zeros.
         */
        @Test
        fun fractionalLength_decimalNumbers_negativeValue() {
            val toCheck = ScientificNumber("-1.23", "-3.4672")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(2, numberFractionalLength)
            assertEquals(4, uncertaintyFractionalLength)
        }

        /**
         * This test verifies numbers with only a decimal portion are correctly counted.
         *
         * This explicitly does not include zeros.
         */
        @Test
        fun fractionalLength_decimalNumbers_firstCharacterDecimalPoint() {
            val toCheck = ScientificNumber(".12345", ".123")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(5, numberFractionalLength)
            assertEquals(3, uncertaintyFractionalLength)
        }

        /**
         * This test verifies negative numbers with only a decimal portion are correctly counted.
         *
         * This explicitly does not include zeros.
         */
        @Test
        fun fractionalLength_decimalNumbers_firstCharacterDecimalPoint_negativeValue() {
            val toCheck = ScientificNumber("-.12345", "-.123")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(5, numberFractionalLength)
            assertEquals(3, uncertaintyFractionalLength)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun fractionalLength_decimalNumbers_leadingZeros_withWholeNumber() {
            val toCheck = ScientificNumber("00001.2345", "001.23")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(4, numberFractionalLength)
            assertEquals(2, uncertaintyFractionalLength)
        }

        /** This test verifies trailing zeros in decimal numbers are counted. */
        @Test
        fun fractionalLength_decimalNumbers_trailingZeros_withWholeNumber() {
            val toCheck = ScientificNumber("1.23450000", "1.2300")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(8, numberFractionalLength)
            assertEquals(4, uncertaintyFractionalLength)
        }

        /**
         * This test verifies leading zeros do not have different outputs from expected based on
         * previous tests.
         */
        @Test
        fun fractionalLength_decimalNumbers_leadingZeros_noWholeNumber() {
            val toCheck = ScientificNumber("0.00000000000035", "0.0000000000000000034672")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(14, numberFractionalLength)
            assertEquals(22, uncertaintyFractionalLength)
        }

        /**
         * This test verifies leading zeros and trailing zeros in the decimal place do not have
         * different outputs from expected based on previous tests.
         */
        @Test
        fun fractionalLength_decimalNumbers_leadingAndTrailingZeros_noWholeNumber() {
            val toCheck = ScientificNumber("0.0000000000003500", "0.000000000000000003467200")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(16, numberFractionalLength)
            assertEquals(24, uncertaintyFractionalLength)
        }

        /** This test verifies scientific notation does not change expected results. */
        @Test
        fun fractionalLength_scientificNotation_wholeNumber() {
            val toCheck = ScientificNumber("3.5e4", "3.4672e7")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
        }

        /** This test verifies scientific notation does not change expected results. */
        @Test
        fun fractionalLength_scientificNotation_decimalNumber() {
            val toCheck = ScientificNumber("3.524e1", "3.46732e2")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(2, numberFractionalLength)
            assertEquals(3, uncertaintyFractionalLength)
        }

        /** This test verifies scientific notation does not change expected results. */
        @Test
        fun fractionalLength_scientificNotation_largePositiveExponent() {
            val toCheck = ScientificNumber("3.5e137", "3.4672e674")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
        }

        /** This test verifies scientific notation does not change expected results. */
        @Test
        fun fractionalLength_scientificNotation_largePositiveExponent_trailingZeros() {
            val toCheck = ScientificNumber("3.500e137", "3.46720000e674")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
        }

        /** This test verifies scientific notation does not change expected results. */
        @Test
        fun fractionalLength_scientificNotation_largePositiveExponent_leadingZeros() {
            val toCheck = ScientificNumber("0.0035e137", "0.0000034672e674")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
        }

        /** This test verifies scientific notation does not change expected results. */
        @Test
        fun fractionalLength_scientificNotation_largeNegativeExponent() {
            val toCheck = ScientificNumber("3.5e-137", "3.4672e-674")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(138, numberFractionalLength)
            assertEquals(678, uncertaintyFractionalLength)
        }

        /** This test verifies scientific notation does not change expected results. */
        @Test
        fun fractionalLength_scientificNotation_largeNegativeExponent_trailingZeros() {
            val toCheck = ScientificNumber("3.500e-137", "3.46720000e-674")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(140, numberFractionalLength)
            assertEquals(682, uncertaintyFractionalLength)
        }

        /** This test verifies scientific notation does not change expected results. */
        @Test
        fun fractionalLength_scientificNotation_largeNegativeExponent_leadingZeros() {
            val toCheck = ScientificNumber("0.0035e-137", "0.00034672e-674")
            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()

            assertEquals(141, numberFractionalLength)
            assertEquals(682, uncertaintyFractionalLength)
        }
    }

    /**
     * This is a class to encapsulate all kotlin plus and add static method tests within the
     * ScientificNumber class.
     */
    class SNAddition : ScientificNumberTests() {
        /** This is a basic test of ScientificNumber number addition. */
        @Test
        fun add_twoNumbers_wholeNumbers_noUncertainty() {
            val addOne = ScientificNumber("1", null)
            val addTwo = ScientificNumber("3", null)
            val toCheck = addOne + addTwo

            assertEquals(ScientificNumber("4", null), toCheck)
        }

        /** This is a basic test of ScientificNumber number addition. */
        @Test
        fun add_twoNumbers_decimalNumbers_noUncertainty() {
            val addOne = ScientificNumber("1.123", null)
            val addTwo = ScientificNumber("3.23456", null)
            val toCheck = addOne + addTwo

            assertEquals(ScientificNumber("4.358", null), toCheck)
        }

        /** This is a basic test of ScientificNumber uncertainty addition. */
        @Test
        fun add_twoNumbers_wholeNumbers_withUncertainty() {
            val addOne = ScientificNumber("145", "3")
            val addTwo = ScientificNumber("287", "92")
            val toCheck = addOne + addTwo

            assertEquals(ScientificNumber("432", "92"), toCheck)
        }

        /** This is a basic test of ScientificNumber uncertainty addition. */
        @Test
        fun add_twoNumbers_decimalNumbers_withUncertainty() {
            val addOne = ScientificNumber("145.273", "3.1234")
            val addTwo = ScientificNumber("287.19", "92.871")
            val toCheck = addOne + addTwo

            assertEquals(ScientificNumber("432.46", "92.924"), toCheck)
        }

        /** This verifies a kotlin plus and the static add method have the same output. */
        @Test
        fun add_twoNumbers_defaultPlusEqualsAddStaticFunction() {
            val addOne = ScientificNumber("145.273", "3.1234")
            val addTwo = ScientificNumber("287.19", "92.871")
            val toCheckOne = addOne + addTwo
            val toCheckTwo = ScientificNumber.add(addOne, addTwo)

            assertEquals(toCheckTwo, toCheckOne)
        }

        /** This verifies adding no numbers throws an exception. */
        @Test
        fun add_noNumbers_getException() {
            assertFailsWith<IllegalArgumentException> { ScientificNumber.add() }
        }

        /** This verifies adding one number returns itself. */
        @Test
        fun add_oneNumber_wholeNumbers_withUncertainty() {
            val addOne = ScientificNumber("145", "3")
            val toCheck = ScientificNumber.add(addOne)

            assertEquals(ScientificNumber("145", "3"), toCheck)
        }

        /** This verifies adding one number returns itself. */
        @Test
        fun add_oneNumber_decimalNumbers_withUncertainty() {
            val addOne = ScientificNumber("145.273", "3.1234")
            val toCheck = ScientificNumber.add(addOne)

            assertEquals(ScientificNumber("145.273", "3.1234"), toCheck)
        }

        /** This verifies adding three numbers returns the intended result. */
        @Test
        fun add_threeNumbers_wholeNumbers_withUncertainty() {
            val addOne = ScientificNumber("145", "3")
            val addTwo = ScientificNumber("12", "2")
            val addThree = ScientificNumber("382", "4")
            val toCheck = ScientificNumber.add(addOne, addTwo, addThree)

            assertEquals(ScientificNumber("539", "5"), toCheck)
        }

        /**
         * This verifies adding three numbers returns the correct addition and rounds decimal places
         * appropriately according to scientific uncertainty standards.
         */
        @Test
        fun add_threeNumbers_decimalNumbers_withUncertainty() {
            val addOne = ScientificNumber("145.273", "3.1234")
            val addTwo = ScientificNumber("12.3852", "2.36")
            val addThree = ScientificNumber("382.14", "4.836")
            val toCheck = ScientificNumber.add(addOne, addTwo, addThree)

            assertEquals(ScientificNumber("539.80", "6.22"), toCheck)
        }

        /** This verifies the default rounding mode rounds up and down appropriately. */
        @Test
        fun add_twoNumbers_decimalNumbers_withUncertainty_roundEvenDefault_roundUp() {
            val addOne = ScientificNumber("145.285", "0.0")
            val addTwo = ScientificNumber("287.19", "0.35")
            val toCheck = addOne + addTwo

            assertEquals(ScientificNumber("432.48", "0.4"), toCheck)
        }

        /** This verifies the default rounding mode rounds up and down appropriately. */
        @Test
        fun add_twoNumbers_decimalNumbers_withUncertainty_roundEvenDefault_roundDown() {
            val addOne = ScientificNumber("145.275", "0.0")
            val addTwo = ScientificNumber("287.19", "0.25")
            val toCheck = addOne + addTwo

            assertEquals(ScientificNumber("432.46", "0.2"), toCheck)
        }

        /** This verifies the default rounding mode is able to be overridden. */
        @Test
        fun add_twoNumbers_decimalNumbers_withUncertainty_setRoundingMode() {
            val addOne = ScientificNumber("145.275", "0.0")
            val addTwo = ScientificNumber("287.19", "0.25")
            val toCheck = ScientificNumber.add(addOne, addTwo, roundingMode = RoundingMode.HALF_UP)

            assertEquals(ScientificNumber("432.47", "0.3"), toCheck)
        }

        /**
         * This verifies addition does not mess up results of fractional length and significant
         * figure methods.
         */
        @Test
        fun add_twoNumbers_wholeNumbers_withUncertainty_verifyFractionalLengthAndSigFigs() {
            val addOne = ScientificNumber("145", "3")
            val addTwo = ScientificNumber("287", "92")
            val toCheck = addOne + addTwo

            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()
            val (numberSigFig, uncertaintySigFig) = toCheck.significantFigures()

            assertEquals(ScientificNumber("432", "92"), toCheck)
            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
            assertEquals(3, numberSigFig)
            assertEquals(2, uncertaintySigFig)
        }

        /**
         * This verifies addition does not mess up results of fractional length and significant
         * figure methods.
         */
        @Test
        fun add_twoNumbers_decimalNumbers_withUncertainty_verifyFractionalLengthAndSigFigs() {
            val addOne = ScientificNumber("145.273", "3.1234")
            val addTwo = ScientificNumber("287.19", "92.871")
            val toCheck = addOne + addTwo

            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()
            val (numberSigFig, uncertaintySigFig) = toCheck.significantFigures()

            assertEquals(ScientificNumber("432.46", "92.924"), toCheck)
            assertEquals(2, numberFractionalLength)
            assertEquals(3, uncertaintyFractionalLength)
            assertEquals(5, numberSigFig)
            assertEquals(5, uncertaintySigFig)
        }
    }

    /**
     * This is a class to encapsulate all kotlin minus and subtract static method tests within the
     * ScientificNumber class.
     */
    class SNSubtraction : ScientificNumberTests() {
        /** This is a basic test of ScientificNumber number subtraction. */
        @Test
        fun subtract_twoNumbers_wholeNumbers_noUncertainty() {
            val baseNumber = ScientificNumber("3", null)
            val subtractOne = ScientificNumber("1", null)
            val toCheck = baseNumber - subtractOne

            assertEquals(ScientificNumber("2", null), toCheck)
        }

        /** This is a basic test of ScientificNumber number subtraction. */
        @Test
        fun subtract_twoNumbers_decimalNumbers_noUncertainty() {
            val baseNumber = ScientificNumber("3.23456", null)
            val subtractOne = ScientificNumber("1.123", null)
            val toCheck = baseNumber - subtractOne

            assertEquals(ScientificNumber("2.112", null), toCheck)
        }

        /** This is a basic test of ScientificNumber uncertainty subtraction. */
        @Test
        fun subtract_twoNumbers_wholeNumbers_withUncertainty() {
            val baseNumber = ScientificNumber("145", "3")
            val subtractOne = ScientificNumber("287", "92")
            val toCheck = baseNumber - subtractOne

            assertEquals(ScientificNumber("-142", "92"), toCheck)
        }

        /** This is a basic test of ScientificNumber uncertainty subtraction. */
        @Test
        fun subtract_twoNumbers_decimalNumbers_withUncertainty() {
            val baseNumber = ScientificNumber("287.19", "92.871")
            val subtractOne = ScientificNumber("145.273", "3.1234")
            val toCheck = baseNumber - subtractOne

            assertEquals(ScientificNumber("141.92", "92.924"), toCheck)
        }
        /** This verifies a kotlin minus and the static subtract method have the same output. */
        @Test
        fun subtract_twoNumbers_defaultMinusEqualsSubtractStaticFunction() {
            val baseNumber = ScientificNumber("287.19", "3.1234")
            val subtractOne = ScientificNumber("145.273", "92.871")
            val toCheckOne = baseNumber - subtractOne
            val toCheckTwo = ScientificNumber.subtract(baseNumber, subtractOne)

            assertEquals(toCheckTwo, toCheckOne)
        }

        /** This verifies subtracting no numbers throws an exception. */
        @Test
        fun subtract_noNumbers_getException() {
            assertFailsWith<IllegalArgumentException> { ScientificNumber.subtract() }
        }

        /** This verifies subtracting one number returns itself. */
        @Test
        fun subtract_oneNumber_wholeNumbers_withUncertainty() {
            val baseNumber = ScientificNumber("145", "3")
            val toCheck = ScientificNumber.subtract(baseNumber)

            assertEquals(ScientificNumber("145", "3"), toCheck)
        }

        /** This verifies subtracting one number returns itself. */
        @Test
        fun subtract_oneNumber_decimalNumbers_withUncertainty() {
            val baseNumber = ScientificNumber("145.273", "3.1234")
            val toCheck = ScientificNumber.subtract(baseNumber)

            assertEquals(ScientificNumber("145.273", "3.1234"), toCheck)
        }

        /** This verifies subtracting three numbers returns the intended result. */
        @Test
        fun subtract_threeNumbers_wholeNumbers_withUncertainty() {
            val baseNumber = ScientificNumber("145", "3")
            val subtractOne = ScientificNumber("12", "2")
            val subtractTwo = ScientificNumber("382", "4")
            val toCheck = ScientificNumber.subtract(baseNumber, subtractOne, subtractTwo)

            assertEquals(ScientificNumber("-249", "5"), toCheck)
        }

        /**
         * This verifies subtracting three numbers returns the correct subtraction and rounds
         * decimal places appropriately according to scientific uncertainty standards.
         */
        @Test
        fun subtract_threeNumbers_decimalNumbers_withUncertainty() {
            val baseNumber = ScientificNumber("145.273", "3.1234")
            val subtractOne = ScientificNumber("12.3852", "2.36")
            val subtractTwo = ScientificNumber("382.14", "4.836")
            val toCheck = ScientificNumber.subtract(baseNumber, subtractOne, subtractTwo)

            assertEquals(ScientificNumber("-249.25", "6.22"), toCheck)
        }

        /** This verifies the default rounding mode rounds up and down appropriately. */
        @Test
        fun subtract_twoNumbers_decimalNumbers_withUncertainty_roundEvenDefault_roundUp() {
            val baseNumber = ScientificNumber("287.195", "0.0")
            val subtractOne = ScientificNumber("145.28", "0.35")
            val toCheck = baseNumber - subtractOne

            assertEquals(ScientificNumber("141.92", "0.4"), toCheck)
        }

        /** This verifies the default rounding mode rounds up and down appropriately. */
        @Test
        fun subtract_twoNumbers_decimalNumbers_withUncertainty_roundEvenDefault_roundDown() {
            val baseNumber = ScientificNumber("287.19", "0.0")
            val subtractOne = ScientificNumber("145.285", "0.25")
            val toCheck = baseNumber - subtractOne

            assertEquals(ScientificNumber("141.90", "0.2"), toCheck)
        }

        /** This verifies the default rounding mode is able to be overridden. */
        @Test
        fun subtract_twoNumbers_decimalNumbers_withUncertainty_setRoundingMode() {
            val baseNumber = ScientificNumber("287.19", "0.0")
            val subtractOne = ScientificNumber("145.285", "0.25")
            val toCheck =
                    ScientificNumber.subtract(
                            baseNumber,
                            subtractOne,
                            roundingMode = RoundingMode.HALF_UP
                    )

            assertEquals(ScientificNumber("141.91", "0.3"), toCheck)
        }

        /**
         * This verifies subtraction does not mess up results of fractional length and significant
         * figure methods.
         */
        @Test
        fun subtract_twoNumbers_wholeNumbers_withUncertainty_verifyFractionalLengthAndSigFigs() {
            val baseNumber = ScientificNumber("287", "3")
            val subtractOne = ScientificNumber("145", "92")
            val toCheck = baseNumber - subtractOne

            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()
            val (numberSigFig, uncertaintySigFig) = toCheck.significantFigures()

            assertEquals(ScientificNumber("142", "92"), toCheck)
            assertEquals(0, numberFractionalLength)
            assertEquals(0, uncertaintyFractionalLength)
            assertEquals(3, numberSigFig)
            assertEquals(2, uncertaintySigFig)
        }

        /**
         * This verifies subtraction does not mess up results of fractional length and significant
         * figure methods.
         */
        @Test
        fun subtract_twoNumbers_decimalNumbers_withUncertainty_verifyFractionalLengthAndSigFigs() {
            val baseNumber = ScientificNumber("287.19", "92.871")
            val subtractOne = ScientificNumber("145.273", "3.1234")
            val toCheck = baseNumber - subtractOne

            val (numberFractionalLength, uncertaintyFractionalLength) = toCheck.fractionalLength()
            val (numberSigFig, uncertaintySigFig) = toCheck.significantFigures()

            assertEquals(ScientificNumber("141.92", "92.924"), toCheck)
            assertEquals(2, numberFractionalLength)
            assertEquals(3, uncertaintyFractionalLength)
            assertEquals(5, numberSigFig)
            assertEquals(5, uncertaintySigFig)
        }
    }
}

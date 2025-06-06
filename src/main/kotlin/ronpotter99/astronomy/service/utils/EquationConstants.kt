package ronpotter99.astronomy.service.utils

import java.math.BigInteger
import java.math.BigDecimal
import ronpotter99.astronomy.DTO.UBigInteger
import ronpotter99.astronomy.DTO.UBigDecimal

object EquationConstants {
    @JvmField val SPEED_OF_LIGHT: UBigInteger = UBigInteger(BigInteger("2.998e8"))
    @JvmField val UNIVERSAL_GRAVITATIONAL_CONSTANT: UBigDecimal = UBigDecimal(BigDecimal("6.673e-11"))
    @JvmField val HUBBLE_CONSTANT: UBigDecimal = UBigDecimal(BigDecimal("70.49"), BigDecimal("3.59"))
    @JvmField val PLANCK_CONSTANT_J: UBigDecimal = UBigDecimal(BigDecimal("6.62e-34"))
    @JvmField val PLANCK_CONSTANT_EV: UBigDecimal = UBigDecimal(BigDecimal("4.136e-15"))
    @JvmField val REDUCED_PLANCK_CONSTANT_J: UBigDecimal = UBigDecimal(BigDecimal("1.054e-34"))
    @JvmField val REDUCED_PLANCK_CONSTANT_EV: UBigDecimal = UBigDecimal(BigDecimal("6.583e-16"))
    @JvmField val BOLTZMANN_CONSTANT_J: UBigDecimal = UBigDecimal(BigDecimal("1.381e-23"))
    @JvmField val BOLTZMANN_CONSTANT_EV: UBigDecimal = UBigDecimal(BigDecimal("8.617e-16"))
    @JvmField val STEFAN_BOLTZMANN_CONSTANT: UBigDecimal = UBigDecimal(BigDecimal("5.670e-8"))
    @JvmField val MASS_OF_ELECTRON: UBigDecimal = UBigDecimal(BigDecimal("9.109e-31"))
    @JvmField val ELEMENTARY_CHARGE: UBigDecimal = UBigDecimal(BigDecimal("1.602e-19"))
    @JvmField val ATOMIC_MASS_UNIT: UBigDecimal = UBigDecimal(BigDecimal("1.6605e-27"))
    @JvmField val MASS_OF_HYDROGEN: UBigDecimal = UBigDecimal(BigDecimal("1.674e-27"))
    @JvmField val IONIZATION_ENERGY_OF_HYDROGEN: UBigDecimal = UBigDecimal(BigDecimal("13.6"))
    @JvmField val RYDBERG_CONSTANT: UBigInteger = UBigInteger(BigInteger("1.097e7"))
    @JvmField val BOHR_RADIUS: UBigDecimal = UBigDecimal(BigDecimal("5.295e-11"))
}

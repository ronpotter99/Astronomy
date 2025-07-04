package ronpotter99.astronomy.utils

import java.math.BigInteger
import java.math.BigDecimal
import ronpotter99.astronomy.DTO.ScientificNumber

object EquationConstants {
    @JvmField val SPEED_OF_LIGHT: ScientificNumber = ScientificNumber("2.998e8")
    @JvmField val UNIVERSAL_GRAVITATIONAL_CONSTANT: ScientificNumber = ScientificNumber("6.673e-11")
    @JvmField val HUBBLE_CONSTANT: ScientificNumber = ScientificNumber("70.49", "3.59")
    @JvmField val PLANCK_CONSTANT_J: ScientificNumber = ScientificNumber("6.62e-34")
    @JvmField val PLANCK_CONSTANT_EV: ScientificNumber = ScientificNumber("4.136e-15")
    @JvmField val REDUCED_PLANCK_CONSTANT_J: ScientificNumber = ScientificNumber("1.054e-34")
    @JvmField val REDUCED_PLANCK_CONSTANT_EV: ScientificNumber = ScientificNumber("6.583e-16")
    @JvmField val BOLTZMANN_CONSTANT_J: ScientificNumber = ScientificNumber("1.381e-23")
    @JvmField val BOLTZMANN_CONSTANT_EV: ScientificNumber = ScientificNumber("8.617e-16")
    @JvmField val STEFAN_BOLTZMANN_CONSTANT: ScientificNumber = ScientificNumber("5.670e-8")
    @JvmField val MASS_OF_ELECTRON: ScientificNumber = ScientificNumber("9.109e-31")
    @JvmField val ELEMENTARY_CHARGE: ScientificNumber = ScientificNumber("1.602e-19")
    @JvmField val ATOMIC_MASS_UNIT: ScientificNumber = ScientificNumber("1.6605e-27")
    @JvmField val MASS_OF_HYDROGEN: ScientificNumber = ScientificNumber("1.674e-27")
    @JvmField val IONIZATION_ENERGY_OF_HYDROGEN: ScientificNumber = ScientificNumber("13.6")
    @JvmField val RYDBERG_CONSTANT: ScientificNumber = ScientificNumber("1.097e7")
    @JvmField val BOHR_RADIUS: ScientificNumber = ScientificNumber("5.295e-11")
}

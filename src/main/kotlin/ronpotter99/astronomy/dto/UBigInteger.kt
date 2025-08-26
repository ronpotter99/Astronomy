package ronpotter99.astronomy.dto

import java.math.BigInteger

data class UBigInteger(
    var number: BigInteger,
    var uncertainty: BigInteger? = null
)

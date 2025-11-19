package ronpotter99.astronomy.dto

import java.math.BigInteger

@Deprecated("Use ScientificNumber instead.")
data class UBigInteger(
    var number: BigInteger,
    var uncertainty: BigInteger? = null
)

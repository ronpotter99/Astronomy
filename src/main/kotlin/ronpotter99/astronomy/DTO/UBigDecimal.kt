package ronpotter99.astronomy.DTO

import java.math.BigDecimal
import org.hibernate.usertype.UserType

data class UBigDecimal(
    var number: BigDecimal,
    var uncertainty: BigDecimal? = null
)

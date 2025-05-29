package ronpotter99.astronomy.entity

import jakarta.persistence.*
import java.math.BigInteger

@Entity
@Table(name = "star")
class Star {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "full_name")
    var fullName: String = ""

    @Column(name = "mass")
    var mass: Long? = null


}
package ronpotter99.astronomy.entity

import jakarta.persistence.*
import java.math.BigInteger
import com.fasterxml.jackson.annotation.JsonManagedReference

@Entity
@Table(name = "star")
class Star {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @JsonManagedReference
    @OneToMany(mappedBy = "star")
    var designations: MutableList<StarDesignation> = mutableListOf()

    @Column(name = "mass")
    var mass: BigInteger? = null

    @Column(name = "radius")
    var radius: BigInteger? = null

    @Column(name = "luminosity")
    var luminosity: BigInteger? = null

    @Column(name = "effective_temperature")
    var effectiveTemperature: Long? = null


}
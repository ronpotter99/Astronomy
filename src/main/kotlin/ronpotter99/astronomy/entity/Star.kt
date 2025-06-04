package ronpotter99.astronomy.entity

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonManagedReference
import ronpotter99.astronomy.DTO.UBigInteger

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
    var mass: UBigInteger? = null

    @Column(name = "radius")
    var radius: UBigInteger? = null

    @Column(name = "luminosity")
    var luminosity: UBigInteger? = null

    @Column(name = "effective_temperature")
    var effectiveTemperature: UBigInteger? = null

    @JsonManagedReference
    @OneToMany(mappedBy = "star")
    var planets: MutableList<Planet> = mutableListOf()

    override fun toString(): String {
        return (
            "Star("
            + "id=$id, "
            + "designations=$designations, "
            + "mass=$mass, "
            + "radius=$radius, "
            + "luminosity=$luminosity, "
            + "effectiveTemperature=$effectiveTemperature, "
            + "planets=$planets"
            + ")"
        )
    }
}

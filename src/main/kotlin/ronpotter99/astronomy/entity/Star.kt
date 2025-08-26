package ronpotter99.astronomy.entity

import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import ronpotter99.astronomy.dto.ScientificNumber

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
    var mass: ScientificNumber? = null

    @Column(name = "radius")
    var radius: ScientificNumber? = null

    @Column(name = "luminosity")
    var luminosity: ScientificNumber? = null

    @Column(name = "effective_temperature")
    var effectiveTemperature: ScientificNumber? = null

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

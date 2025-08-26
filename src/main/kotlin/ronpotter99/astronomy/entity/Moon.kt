package ronpotter99.astronomy.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import jakarta.persistence.*
import ronpotter99.astronomy.dto.ScientificNumber

@Entity
@Table(name = "moon")
class Moon {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id")
    var planet: Planet? = null

    @Column(name = "common_name")
    var commonName: String = ""

    /**
     * Units in 'kg'
     */
    @Column(name = "mass")
    var mass: ScientificNumber? = null

    /**
     * Units in 'm'
     */
    @Column(name = "orbit_semimajor_axis")
    var orbitSemimajorAxis: ScientificNumber? = null

    /**
     * Units in 's'
     */
    @Column(name = "orbit_period")
    var orbitPeriod: ScientificNumber? = null

    @Column(name = "source")
    var source: String? = null

    override fun toString(): String {
        return (
                "Planet("
                        + "id=$id, "
                        + "commonName=$commonName, "
                        + "mass=$mass, "
                        + "orbitSemimajorAxis=$orbitSemimajorAxis, "
                        + "orbitPeriod=$orbitPeriod, "
                        + "source=$source"
                        + ")"
                )
    }
}
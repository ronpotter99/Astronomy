package ronpotter99.astronomy.entity

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import jakarta.persistence.*
import ronpotter99.astronomy.dto.ScientificNumber

@Entity
@Table(name = "planet")
class Planet {

    enum class ClassificationType {
        TERRESTRIAL,
        SUPER_EARTH,
        NEPTUNE_LIKE,
        GAS_GIANT,
        DWARF,
        UNKNOWN
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "star_id")
    var star: Star? = null

    @Column(name = "common_name")
    var commonName: String = ""

    @Column(name = "classification_type")
    @Enumerated(EnumType.STRING)
    var classificationType: ClassificationType? = null

    /**
     * Units in 'kg'
     */
    @Column(name = "mass")
    var mass: ScientificNumber? = null

    /**
     * Units in 'm'
     */
    @Column(name = "radius")
    var radius: ScientificNumber? = null

    /**
     * Units in 'g/cm^3'
     */
    @Column(name = "density")
    var density: ScientificNumber? = null

    /**
     * Units in 'm'
     */
    @Column(name = "orbit_semimajor_axis")
    var orbitSemimajorAxis: ScientificNumber? = null

    /**
     * Unitless
     */
    @Column(name = "orbit_eccentricity")
    var orbitEccentricity: ScientificNumber? = null

    /**
     * Units in 's'
     */
    @Column(name = "orbit_period")
    var orbitPeriod: ScientificNumber? = null

    /**
     * Units in 'rad'
     */
    @Column(name = "orbit_inclination")
    var orbitInclination: ScientificNumber? = null

    /**
     * Units in 's'
     */
    @Column(name = "rotational_period")
    var rotationalPeriod: ScientificNumber? = null

    /**
     * Units in 'K'
     */
    @Column(name = "surface_temperature")
    var surfaceTemperature: ScientificNumber? = null

    @Column(name = "source")
    var source: String? = null

    @JsonManagedReference
    @OneToMany(mappedBy = "planet")
    var moons: MutableList<Moon> = mutableListOf()

    override fun toString(): String {
        return (
                "Planet("
                        + "id=$id, "
                        + "commonName=$commonName, "
                        + "classificationType=$classificationType, "
                        + "mass=$mass, "
                        + "radius=$radius, "
                        + "density=$density, "
                        + "orbitSemimajorAxis=$orbitSemimajorAxis, "
                        + "orbitEccentricity=$orbitEccentricity, "
                        + "orbitPeriod=$orbitPeriod, "
                        + "orbitInclination=$orbitInclination, "
                        + "rotationalPeriod=$rotationalPeriod, "
                        + "surfaceTemperature=$surfaceTemperature, "
                        + "source=$source, "
                        + "moons=$moons"
                        + ")"
                )
    }
}
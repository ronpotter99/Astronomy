package ronpotter99.astronomy.entity

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonBackReference
import ronpotter99.astronomy.DTO.UBigInteger
import ronpotter99.astronomy.DTO.UBigDecimal

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

    @Column(name = "mass")
    var mass: UBigInteger? = null

    @Column(name = "radius")
    var radius: UBigInteger? = null

    @Column(name = "density")
    var density: UBigDecimal? = null

    @Column(name = "orbit_semimajor_axis")
    var orbitSemimajorAxis: UBigInteger? = null

    @Column(name = "orbit_eccentricity")
    var orbitEccentricity: UBigDecimal? = null

    @Column(name = "orbit_period")
    var orbitPeriod: UBigInteger? = null

    @Column(name = "orbit_inclination")
    var orbitInclination: UBigDecimal? = null

    @Column(name = "rotational_period")
    var rotationalPeriod: UBigInteger? = null

    @Column(name = "surface_temperature")
    var surfaceTemperature: UBigInteger? = null

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
            + "surfaceTemperature=$surfaceTemperature"
            + ")"
        )
    }
}
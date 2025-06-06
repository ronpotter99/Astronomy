package ronpotter99.astronomy.entity

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonBackReference
import ronpotter99.astronomy.DTO.UBigInteger
import ronpotter99.astronomy.DTO.UBigDecimal

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

    @Column(name = "mass")
    var mass: UBigInteger? = null

    @Column(name = "orbit_semimajor_axis")
    var orbitSemimajorAxis: UBigInteger? = null

    @Column(name = "orbit_period")
    var orbitPeriod: UBigInteger? = null

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
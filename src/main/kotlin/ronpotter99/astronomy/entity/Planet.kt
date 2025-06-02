package ronpotter99.astronomy.entity

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonBackReference
import ronpotter99.astronomy.DTO.UBigInteger

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
}
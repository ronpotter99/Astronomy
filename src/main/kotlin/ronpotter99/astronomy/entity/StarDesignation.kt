package ronpotter99.astronomy.entity

import jakarta.persistence.*
import com.fasterxml.jackson.annotation.JsonBackReference

@Entity
@Table(name = "star_designation")
class StarDesignation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "star_id")
    var star: Star? = null

    @Column(name = "designation")
    var designation: String = ""

    @Column(name = "source")
    var source: String = ""

    override fun toString(): String {
        return "StarDesignation(id=$id, designation=$designation, source=$source)"
    }
}
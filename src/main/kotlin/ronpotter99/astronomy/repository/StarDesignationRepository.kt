package ronpotter99.astronomy.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ronpotter99.astronomy.entity.StarDesignation

@Repository
interface StarDesignationRepository : JpaRepository<StarDesignation, Long> {
}

package ronpotter99.astronomy.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import ronpotter99.astronomy.entity.Moon


@Repository
interface MoonRepository: JpaRepository<Moon, Long> {
    
}
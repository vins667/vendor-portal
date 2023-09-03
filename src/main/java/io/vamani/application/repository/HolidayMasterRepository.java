package io.vamani.application.repository;

import io.vamani.application.domain.HolidayMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the HolidayMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HolidayMasterRepository extends JpaRepository<HolidayMaster, Long> {
    @Query("select holidayMaster from HolidayMaster holidayMaster left join fetch holidayMaster.factoryMaster factoryMaster where factoryMaster.factoryCode=?1 and holidayMaster.flag='Y' order by holidayMaster.holidayDate")
    List<HolidayMaster> findAllByFactoryMaster(String subCode);
}

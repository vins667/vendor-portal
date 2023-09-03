package io.vamani.application.repository;

import io.vamani.application.domain.DoctorSchedule;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the DoctorSchedule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
    @Query("select doctorSchedule from DoctorSchedule doctorSchedule where doctorSchedule.unitId=?1")
    Optional<DoctorSchedule> findByUnitId(Long factoryCode);
}

package io.vamani.application.repository;

import io.vamani.application.domain.StitchManpowerBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data  repository for the SmsRegistration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StitchManpowerBudgetRepository extends JpaRepository<StitchManpowerBudget, Long> {
    @Query("select stitchManpowerBudget from StitchManpowerBudget stitchManpowerBudget where stitchManpowerBudget.factCode = ?1 and stitchManpowerBudget.deptCode= ?2 and stitchManpowerBudget.attendanceDate = ?3 and stitchManpowerBudget.attendanceType = ?4")
    List<StitchManpowerBudget> findAllByFactCodeAndDeptCodeAndAttendDateAndAttendanceType(String factCode, String deptCode, Instant attendandceDate, String attendanceType);
}

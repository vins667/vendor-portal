package io.vamani.application.repository;

import io.vamani.application.domain.ManpowerBudgetEntry;
import io.vamani.application.domain.ManpowerBudgetEntryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data  repository for the MachineMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManpowerBudgetEntryRepository extends JpaRepository<ManpowerBudgetEntry, ManpowerBudgetEntryId> {

    @Query("select manpowerBudgetEntry from ManpowerBudgetEntry manpowerBudgetEntry where manpowerBudgetEntry.id.factCode = ?1 and manpowerBudgetEntry.id.deptCode = ?2 and manpowerBudgetEntry.id.attendanceDate = ?3 and manpowerBudgetEntry.id.attendanceType = ?4")
    List<ManpowerBudgetEntry> findAllByFactoryCodeAndDeptCodeAndAttendanceDateAndType(String factoryCode, String department, Instant instant, String type);

    @Modifying
    @Transactional
    @Query("delete from ManpowerBudgetEntry manpowerBudgetEntry where manpowerBudgetEntry.id.factCode = ?1 and manpowerBudgetEntry.id.deptCode = ?2 and manpowerBudgetEntry.id.attendanceDate = ?3 and manpowerBudgetEntry.id.attendanceType = ?4")
    void deleteManpowerBudgetEntryBy(String factCode, String deptCode, Instant attendanceDate, String attendanceType);
}

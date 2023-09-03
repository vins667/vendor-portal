package io.vamani.application.repository;

import io.vamani.application.domain.MobileAttendance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;


/**
 * Spring Data  repository for the MobileAttendance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MobileAttendanceRepository extends JpaRepository<MobileAttendance, Long> {
    @Query("select mobileAttendance from MobileAttendance mobileAttendance where mobileAttendance.cardNo = ?1")
    Page<MobileAttendance> findAll(String cardNo, Pageable pageable);

    @Query("select mobileAttendance from MobileAttendance mobileAttendance where mobileAttendance.cardNo = ?1 and mobileAttendance.attendanceDate between ?2 AND ?3")
    List<MobileAttendance> findAllByMonth(String cardNo, Instant first, Instant last);

    @Query("select mobileAttendance from MobileAttendance mobileAttendance where mobileAttendance.cardNo = ?1 and CAST(mobileAttendance.attendanceDate AS date) between ?2 and ?3 and mobileAttendance.leaveMasterId is null order by mobileAttendance.id")
    List<MobileAttendance> findAllByAttendanceDate(String cardNo, Date dateFrom, Date dateTo);



    @Query("select mobileAttendance from MobileAttendance mobileAttendance where mobileAttendance.leaveMasterId=?1")
    List<MobileAttendance> findAllByLeaveMasterId(Long leaveMasterId);
}

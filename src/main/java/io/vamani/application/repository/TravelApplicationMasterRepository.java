package io.vamani.application.repository;

import io.vamani.application.domain.TravelApplicationMaster;

import java.time.Instant;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TravelApplicationMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TravelApplicationMasterRepository extends JpaRepository<TravelApplicationMaster, Long> {

    @Query("select travelApplicationMaster from TravelApplicationMaster travelApplicationMaster where travelApplicationMaster.empCode=?1")
    Page<TravelApplicationMaster> findAll(String empCode, Pageable pageable);

    @Query("select travelApplicationMaster from TravelApplicationMaster travelApplicationMaster where travelApplicationMaster.hodCode=?1 and travelApplicationMaster.status='E' ")
    Page<TravelApplicationMaster> findAllByHODApprovedByPending(String empCode, Pageable var1);

    @Query("select travelApplicationMaster from TravelApplicationMaster travelApplicationMaster where travelApplicationMaster.travelFromdate between ?1 and ?2 and travelApplicationMaster.hodCode = ?3  and travelApplicationMaster.status='A' ")
    Page<TravelApplicationMaster> findAllByHODApproved(Instant dateFrom, Instant dateTo, String empCode, Pageable var1);

    @Query("select  travelApplicationMaster from TravelApplicationMaster travelApplicationMaster where travelApplicationMaster.travelFromdate between ?1 and ?2  and travelApplicationMaster.hodCode = ?3 and travelApplicationMaster.hrApprovedDate is null and travelApplicationMaster.status='R'")
    Page<TravelApplicationMaster> findAllByHODApprovedByRejected(Instant dateFrom, Instant dateTo, String empCode, Pageable var1);

    @Query("select travelApplicationMaster from TravelApplicationMaster travelApplicationMaster where travelApplicationMaster.status='A' ")
    Page<TravelApplicationMaster> findAllByHrApprovedByPending(Pageable var1);

    @Query("select travelApplicationMaster from TravelApplicationMaster travelApplicationMaster where travelApplicationMaster.travelFromdate between ?1 and ?2 and travelApplicationMaster.hrApprovedBy like ?3  and travelApplicationMaster.status='C' ")
    Page<TravelApplicationMaster> findAllByHrApproved(Instant dateFrom, Instant dateTo, String empCode, Pageable var1);

    @Query("select  travelApplicationMaster from TravelApplicationMaster travelApplicationMaster where travelApplicationMaster.travelFromdate between ?1 and ?2  and travelApplicationMaster.hrApprovedBy like ?3 and travelApplicationMaster.hrApprovedDate is not null and travelApplicationMaster.status='R'")
    Page<TravelApplicationMaster> findAllByHrApprovedByRejected(Instant dateFrom, Instant dateTo, String empCode, Pageable var1);


    @Query("select travelApplicationMaster from TravelApplicationMaster travelApplicationMaster where travelApplicationMaster.travelFromdate between ?1 and ?2 and travelApplicationMaster.empCode= ?3 order by travelApplicationMaster.travelFromdate")
    List<TravelApplicationMaster> findByMonthYear(Instant fromDate, Instant toDate, String createdBy);

}

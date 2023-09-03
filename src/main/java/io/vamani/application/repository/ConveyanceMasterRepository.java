package io.vamani.application.repository;

import io.vamani.application.domain.ConveyanceMaster;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import io.vamani.application.domain.LeaveMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConveyanceMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConveyanceMasterRepository extends JpaRepository<ConveyanceMaster, Long> {

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.conveyanceDate between ?1 and ?2 and conveyanceMaster.empCode= ?3")
    Page<ConveyanceMaster> findByMonthYear(Instant fromDate, Instant toDate, String createdBy, Pageable page);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.id =:id")
    Optional<ConveyanceMaster> findConveyanceMasterId(@Param("id") Long id);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.createdBy = ?1")
    ConveyanceMaster findCreatedBy(String loginUser);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.approvedBy=?1 and conveyanceMaster.flag='E'")
    Page<ConveyanceMaster> findAllByHodApprovedByPending(String hodApprovedBy, Pageable var1);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.approvedBy=?1 and conveyanceMaster.conveyanceDate between ?2 and ?3  and conveyanceMaster.flag in('A', 'C', 'P')")
    Page<ConveyanceMaster> findAllByHodApprovedByApproved(String hodApprovedBy, Instant dateFrom, Instant dateTo, Pageable var1);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.approvedBy=?1 and conveyanceMaster.conveyanceDate between ?2 and ?3  and conveyanceMaster.flag=?4 and conveyanceMaster.hrApproved is null")
    Page<ConveyanceMaster> findAllByHodApprovedByRejected(String hodApprovedBy, Instant dateFrom, Instant dateTo, String flag, Pageable var1);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.id =:id")
    ConveyanceMaster findAllById(@Param("id") Long id);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where  conveyanceMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?1) and conveyanceMaster.flag='A'")
    Page<ConveyanceMaster> findAllByHrApprovedByPending(String factory, Pageable var1);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where  conveyanceMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?1) and conveyanceMaster.flag='C'")
    Page<ConveyanceMaster> findAllByProcessApprovedByPending(String factory, Pageable var1);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where  conveyanceMaster.empCode = ?1 and conveyanceMaster.conveyanceDate = ?2")
    ConveyanceMaster findByEmpCodeAndConveyanceDate(String empCode, Instant conveyanceDate);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.conveyanceDate between ?1 and ?2 and conveyanceMaster.factory in(select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?3) and conveyanceMaster.flag IN ('C', 'P') ")
    Page<ConveyanceMaster> findAllByHrApproved(Instant dateFrom, Instant dateTo, String factory, Pageable var1);

    @Query("select  conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.conveyanceDate between ?1 and ?2  and conveyanceMaster.hrApproved like ?3 and conveyanceMaster.hrApprovedDate is not null and conveyanceMaster.flag='R' and conveyanceMaster.factory in (select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?4) ")
    Page<ConveyanceMaster> findAllByHrApprovedByRejected(Instant dateFrom, Instant dateTo, String empCode, String factory, Pageable var1);


    @Query("select  conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.id in ?1")
    List<ConveyanceMaster> findAllByIds(List<Long> processIds);

    @Query(value = "SELECT nextval('conveyance_master_control_seq')", nativeQuery = true)
    Long getNextControlNo();

    @Query("select distinct conveyanceMaster.controlNo from ConveyanceMaster conveyanceMaster where conveyanceMaster.factory in (select factoryMerge.id.factory1 from FactoryMerge factoryMerge where factoryMerge.id.factory=?1) and conveyanceMaster.controlNo is not null order by conveyanceMaster.controlNo desc")
    List<Long> getControlNosByFactory(String factoryNo);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.controlNo=?1")
    Page<ConveyanceMaster> findAllByControlNo(Long controlNo, Pageable var1);

    @Query("select conveyanceMaster.controlNo, conveyanceMaster.empCode, conveyanceMaster.conveyanceDate, conveyanceMaster.rate, coalesce(conveyanceMasterDetails.tripStart, 0), coalesce(conveyanceMasterDetails.tripEnd, 0), coalesce(conveyanceMasterDetails.fromLocation, ''), coalesce(conveyanceMasterDetails.toLocation, ''), coalesce(conveyanceMasterDetails.reason, ''), coalesce(conveyanceMasterDetails.miscAmount, 0), conveyanceMaster.totalDistance from ConveyanceMaster conveyanceMaster left outer join ConveyanceMasterDetails conveyanceMasterDetails on conveyanceMaster.id = conveyanceMasterDetails.conveyanceMaster.id where conveyanceMaster.controlNo = ?1 order by conveyanceMaster.empCode, conveyanceMaster.conveyanceDate")
    List<Object[]> findAllByControlNo(Long controlNo);

    @Query("select conveyanceMaster.empCode from ConveyanceMaster conveyanceMaster where conveyanceMaster.controlNo =2")
    List<String> findAllCardNoByControlNo();


    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.approvedBy=?1 and conveyanceMaster.flag='E' order by conveyanceMaster.conveyanceDate")
    List<ConveyanceMaster> findAllByHodApprovedByPendingMobile(String hodApprovedBy);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.approvedBy=?1 and conveyanceMaster.conveyanceDate between ?2 and ?3  and conveyanceMaster.flag in('A', 'C', 'P') order by conveyanceMaster.conveyanceDate")
    List<ConveyanceMaster> findAllByHodApprovedByApprovedMobile(String hodApprovedBy, Instant dateFrom, Instant dateTo);

    @Query("select conveyanceMaster from ConveyanceMaster conveyanceMaster where conveyanceMaster.approvedBy = ?1 and conveyanceMaster.conveyanceDate between ?2 AND ?3  order by conveyanceMaster.conveyanceDate")
    List<ConveyanceMaster> findAllByEmpCodeAndMonthApproval(String empCode, Instant first, Instant last);

}

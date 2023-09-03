package io.vamani.application.repository;

import io.vamani.application.domain.VehicleMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;


/**
 * Spring Data  repository for the VehicleMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleMasterRepository extends JpaRepository<VehicleMaster, Long> {

    @Query("select vehicleMaster from VehicleMaster vehicleMaster where vehicleMaster.user.login=?1")
    Page<VehicleMaster> findAll(String empCode, Pageable pageable);

    @Query("select vehicleMaster from VehicleMaster vehicleMaster where vehicleMaster.hodApprovedBy=?1 and CAST(vehicleMaster.vehicleDate AS date) between ?2 and ?3 and vehicleMaster.user.login like ?4 and vehicleMaster.flag='E' ")
    Page<VehicleMaster> findAllByHodApprovedByPending(String hodApprovedBy, Date dateFrom, Date dateTo, String empCode, Pageable var1);

    @Query("select vehicleMaster from VehicleMaster vehicleMaster where vehicleMaster.hodApprovedBy=?1 and CAST(vehicleMaster.vehicleDate AS date) between ?2 and ?3 and vehicleMaster.user.login like ?4 and vehicleMaster.flag in('A', 'C')")
    Page<VehicleMaster> findAllByHodApprovedByApproved(String hodApprovedBy, Date dateFrom, Date dateTo, String empCode, Pageable var1);

    @Query("select vehicleMaster from VehicleMaster vehicleMaster where vehicleMaster.hodApprovedBy=?1 and CAST(vehicleMaster.vehicleDate AS date) between ?2 and ?3 and vehicleMaster.user.login like ?4 and vehicleMaster.flag=?5")
    Page<VehicleMaster> findAllByHodApprovedByRejected(String hodApprovedBy, Date dateFrom, Date dateTo, String empCode, String flag, Pageable var1);

    @Query("select vehicleMaster from VehicleMaster vehicleMaster where CAST(vehicleMaster.vehicleDate AS date) between ?1 and ?2 and vehicleMaster.user.login like ?3 and vehicleMaster.flag='A' ")
    Page<VehicleMaster> findAllByHrApprovedByPending(Date dateFrom, Date dateTo, String empCode, Pageable var1);

    @Query("select vehicleMaster from VehicleMaster vehicleMaster where CAST(vehicleMaster.vehicleDate AS date) between ?1 and ?2 and vehicleMaster.user.login like ?3 and vehicleMaster.flag=?4 ")
    Page<VehicleMaster> findAllByHrApprovedByApproved(Date dateFrom, Date dateTo, String empCode, String flag, Pageable var1);

}

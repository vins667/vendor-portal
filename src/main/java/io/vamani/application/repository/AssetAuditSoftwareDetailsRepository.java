package io.vamani.application.repository;

import io.vamani.application.domain.AssetAuditSoftwareDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Spring Data  repository for the AssetAuditDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetAuditSoftwareDetailsRepository extends JpaRepository<AssetAuditSoftwareDetails, Long> {
    @Modifying
    @Transactional
    @Query("delete from AssetAuditSoftwareDetails")
    void truncateTable();

    @Query("select assetAuditSoftwareDetails from AssetAuditSoftwareDetails assetAuditSoftwareDetails where assetAuditSoftwareDetails.systemId=?1 and assetAuditSoftwareDetails.createdDate IN (select runTime from AssetAuditLastRunTime) order by assetAuditSoftwareDetails.publisher, assetAuditSoftwareDetails.name")
    List<AssetAuditSoftwareDetails> findAllBySystemId(Long systemId);

    @Query(value = "select a.uuid, b.name, b.publisher, b.installed_on from asset_audit_details a, asset_audit_software_details b where a.system_id=b.system_id and a.created_date=b.created_date and a.created_date=?1"
        +" except  select a.uuid, b.name, b.publisher, b.installed_on from asset_audit_details a, asset_audit_software_details b where a.system_id=b.system_id and a.created_date=b.created_date and a.created_date=?2", nativeQuery = true)
    List<Object[]> fetchDifferenceSoftware(Date dateFrom, Date dateTo);
}

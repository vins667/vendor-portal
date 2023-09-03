package io.vamani.application.repository;

import io.vamani.application.domain.AssetAuditSoftwareKeyDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * Spring Data  repository for the AssetAuditSoftwareKeyDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetAuditSoftwareKeyDetailsRepository extends JpaRepository<AssetAuditSoftwareKeyDetails, Long> {
    @Modifying
    @Transactional
    @Query("delete from AssetAuditSoftwareKeyDetails")
    void truncateTable();

    @Query("select assetAuditSoftwareKeyDetails from AssetAuditSoftwareKeyDetails assetAuditSoftwareKeyDetails where assetAuditSoftwareKeyDetails.systemId=?1 and assetAuditSoftwareKeyDetails.createdDate IN (select runTime from AssetAuditLastRunTime) order by assetAuditSoftwareKeyDetails.name, assetAuditSoftwareKeyDetails.key")
    List<AssetAuditSoftwareKeyDetails> findAllBySystemId(Long systemId);

    @Query(value = "select a.uuid, b.name, b.jhi_key from asset_audit_details a, asset_audit_software_key_details b where a.system_id=b.system_id and a.created_date=b.created_date and a.created_date=?1"
        +" except  select a.uuid, b.name, b.jhi_key from asset_audit_details a, asset_audit_software_key_details b where a.system_id=b.system_id and a.created_date=b.created_date and a.created_date=?2", nativeQuery = true)
    List<Object[]> fetchDifferenceSoftwareKey(Date dateFrom, Date dateTo);
}

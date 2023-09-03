package io.vamani.application.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import io.vamani.application.domain.AssetAuditDetails;
import io.vamani.application.domain.AssetAuditSoftwareDetails;
import io.vamani.application.model.AssetAuditDetailsBean;
import io.vamani.application.model.HardwareQueryBean;


/**
 * Spring Data  repository for the AssetAuditDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetAuditDetailsRepository extends JpaRepository<AssetAuditDetails, Long> {
    @Modifying
    @Transactional
    @Query("delete from AssetAuditDetails")
    void truncateTable();

    @Modifying
    @Transactional
    @Query("delete from AssetMasterAssetAuditDetails assetMasterAssetAuditDetails where assetMasterAssetAuditDetails.assetMastersId=?1")
    void deleteAssetUUIDByMasterId(Long masterId);

    @Query("select assetAuditDetails from AssetAuditDetails assetAuditDetails where COALESCE(assetAuditDetails.uuid,'N') like ?1 and COALESCE(assetAuditDetails.name,'N') like ?2 and COALESCE(assetAuditDetails.ip,'N') like ?3 and COALESCE(assetAuditDetails.serial,'N') like ?4 and COALESCE(assetAuditDetails.assetCode,'N') like ?5 and assetAuditDetails.createdDate IN (select runTime from AssetAuditLastRunTime)")
    Page<AssetAuditDetails> findByFilter(String uuid, String name, String ip, String serial, String assetCode, Pageable pageable);
    
    @Query("select assetAuditSoftwareDetails from AssetAuditSoftwareDetails assetAuditSoftwareDetails where assetAuditSoftwareDetails.systemId= ?1")
    List<AssetAuditSoftwareDetails> findSoftwareByFilter(Long systemId);

    @Query(value = "select uuid, name, ip, storage_count, memory_count, system_id, hostname from asset_audit_details where asset_audit_details.created_date=?1"
        +" except  select uuid, name, ip, storage_count, memory_count, system_id, hostname from asset_audit_details where asset_audit_details.created_date=?2 order by uuid", nativeQuery = true)
    List<Object[]> fetchDifferenceHardWare(Date dateFrom, Date dateTo);
    
    @Query("select A.assetCode, A.name, B.publisher, B.name, A.ip FROM AssetAuditDetails A, AssetAuditSoftwareDetails B where A.systemId=B.systemId and A.createdDate = B.createdDate and A.createdDate IN (select runTime from AssetAuditLastRunTime) and coalesce(A.assetCode, 'N') like ?1 and  coalesce(B.publisher, 'N') like ?2 and coalesce(B.name, 'N') like ?3 order by 1, 2, 3, 4")
    Page<Object[]> findAllByFilter(String assetCode, String publisher, String software, Pageable pageable);

    @Query("select assetAuditDetails.assetCode,assetAuditDetails.hostname,assetAuditDetails.storageCount,assetAuditDetails.memoryCount,assetAuditDetails.osName from AssetAuditDetails assetAuditDetails where createdDate IN (select runTime from AssetAuditLastRunTime) and (assetAuditDetails.memoryCount/1048576)>=?1 and (assetAuditDetails.memoryCount/1048576)<=?2 and (assetAuditDetails.storageCount/1024)>=?3 and (assetAuditDetails.storageCount/1024)<=?4 order by 1, 2")
    Page<Object[]> findAllHardwareByFilter(Long minMemory, Long maxMemory, Long minStorage, Long maxStorage, Pageable pageable);
    
    @Query("select A.assetCode, B.name, B.key FROM AssetAuditDetails A, AssetAuditSoftwareKeyDetails B where A.systemId=B.systemId and A.createdDate = B.createdDate and A.createdDate IN (select runTime from AssetAuditLastRunTime) and B.name like ?1 and B.key like ?2  order by 1, 2")
    Page<Object[]> findAllSoftwareKeyByFilter(String name, String key, Pageable pageable);
}

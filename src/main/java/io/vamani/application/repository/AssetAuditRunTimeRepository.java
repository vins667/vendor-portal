package io.vamani.application.repository;

import io.vamani.application.domain.AssetAuditRunTime;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * Spring Data  repository for the AssetAuditDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetAuditRunTimeRepository extends JpaRepository<AssetAuditRunTime, Instant> {
    @Query("select assetAuditRunTime from AssetAuditRunTime assetAuditRunTime where assetAuditRunTime.runTime>=?1 order by assetAuditRunTime.runTime desc")
    List<AssetAuditRunTime> findAllGreaterThanId(Instant dateFrom);
}

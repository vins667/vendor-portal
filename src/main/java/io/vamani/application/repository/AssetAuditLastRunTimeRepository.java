package io.vamani.application.repository;

import io.vamani.application.domain.AssetAuditLastRunTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

/**
 * Spring Data  repository for the AssetAuditDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetAuditLastRunTimeRepository extends JpaRepository<AssetAuditLastRunTime, Instant> {
    @Modifying
    @Transactional
    @Query("delete from AssetAuditLastRunTime")
    void truncateTable();
}

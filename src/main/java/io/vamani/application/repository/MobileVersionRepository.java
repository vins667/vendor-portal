package io.vamani.application.repository;

import io.vamani.application.domain.MobileVersion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MobileVersion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MobileVersionRepository extends JpaRepository<MobileVersion, Long> {
    @Query("select mobileVersion from MobileVersion mobileVersion where mobileVersion.mobileType='A' and mobileVersion.closedDate is null")
    MobileVersion findLatestAndroidVersion();
    @Query("select mobileVersion from MobileVersion mobileVersion where mobileVersion.mobileType='I' and mobileVersion.closedDate is null")
    MobileVersion findLatestIphoneVersion();
}

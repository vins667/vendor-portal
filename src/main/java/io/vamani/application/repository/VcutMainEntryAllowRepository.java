package io.vamani.application.repository;

import io.vamani.application.domain.VcutMainEntryAllow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VcutDeviceLineMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutMainEntryAllowRepository extends JpaRepository<VcutMainEntryAllow, Long> {

    @Query("select vcutMainEntryAllow from VcutMainEntryAllow vcutMainEntryAllow where vcutMainEntryAllow.id in(select max(vcutMainEntryAllow.id) from VcutMainEntryAllow vcutMainEntryAllow)")
    VcutMainEntryAllow findByMaxValue();
}

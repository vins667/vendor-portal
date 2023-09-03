package io.vamani.application.repository;

import io.vamani.application.domain.AssetMaster;
import io.vamani.application.domain.VcutDeviceLineMaster;

import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.icu.util.StringTrieBuilder.Option;


/**
 * Spring Data  repository for the VcutDeviceLineMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutDeviceLineMasterRepository extends JpaRepository<VcutDeviceLineMaster, Long> {
    @Query("select vcutDeviceLineMaster from VcutDeviceLineMaster vcutDeviceLineMaster where vcutDeviceLineMaster.id=?1")
    VcutDeviceLineMaster findByVcutDeviceLine(Long id);

    @Query("select vcutDeviceLineMaster from VcutDeviceLineMaster vcutDeviceLineMaster where vcutDeviceLineMaster.line =?1")
    VcutDeviceLineMaster findByLine(String line);

    @Query("select vcutDeviceLineMaster from VcutDeviceLineMaster vcutDeviceLineMaster where vcutDeviceLineMaster.deviceId =?1")
    VcutDeviceLineMaster findByDeviceId(String deviceId);
}

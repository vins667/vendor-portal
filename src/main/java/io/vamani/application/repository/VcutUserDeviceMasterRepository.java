package io.vamani.application.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import io.vamani.application.domain.VcutDeviceLineMaster;
import io.vamani.application.domain.VcutUserDeviceMaster;

/**
 * Spring Data  repository for the VcutUserDeviceMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutUserDeviceMasterRepository extends JpaRepository<VcutUserDeviceMaster, Long> {
    Set<VcutUserDeviceMaster> findByVcutDeviceLineMaster(VcutDeviceLineMaster vcutDeviceLineMaster);
	List<VcutUserDeviceMaster> findAllByUserId(Long Id);
	 @Query("select vcutUserDeviceMaster from VcutUserDeviceMaster vcutUserDeviceMaster where vcutUserDeviceMaster.vcutDeviceLineMaster.id = ?1")
	 Set<VcutUserDeviceMaster> findByVcutDeviceLineMaster(Long deviceId);

    @Modifying
    @Transactional
    @Query("delete from VcutUserDeviceMaster vcutUserDeviceMaster where vcutUserDeviceMaster.vcutDeviceLineMaster.id =?1")
    void deleteAllDetailById(Long masterId);
}

package io.vamani.application.repository;

import io.vamani.application.domain.VcutFactoryAccess;
import io.vamani.application.domain.VcutFactoryAccessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VcutFactoryAccessRepository extends JpaRepository<VcutFactoryAccess, VcutFactoryAccessId> {
    @Query("select vcutFactoryAccess from VcutFactoryAccess vcutFactoryAccess where vcutFactoryAccess.id.cardNo = ?1 and vcutFactoryAccess.appType = ?2 order by vcutFactoryAccess.id.factoryCode")
    List<VcutFactoryAccess> findAllByCardNoAndAppType(String cardNo, String appType);
}

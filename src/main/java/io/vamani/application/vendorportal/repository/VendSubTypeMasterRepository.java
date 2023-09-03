package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendSubTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the VendSubTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendSubTypeMasterRepository extends JpaRepository<VendSubTypeMaster, Long> {
    @Query("select vendSubTypeMaster from VendSubTypeMaster vendSubTypeMaster where vendSubTypeMaster.vendTypeMaster.id=?1 order by vendSubTypeMaster.description")
    List<VendSubTypeMaster> findAllByVendTypeMaster(Long vendorTypeId);
}

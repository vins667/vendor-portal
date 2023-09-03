package io.vamani.application.repository;

import io.vamani.application.domain.VendorMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the VendorMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VendorMasterRepository extends JpaRepository<VendorMaster, Long> {
    @Query("select vendorMaster from VendorMaster vendorMaster where vendorMaster.code like ?1 or vendorMaster.description like ?2")
    Page<VendorMaster> findAllByCodeAndDesc(String code, String description, Pageable pageable);

    @Query("select vendorMaster from VendorMaster vendorMaster where vendorMaster.code = ?1")
    VendorMaster findByCode(String code);
}

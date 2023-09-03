package io.vamani.application.repository;

import io.vamani.application.domain.OrderpartnerUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the OrderpartnerUpload entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderpartnerUploadRepository extends JpaRepository<OrderpartnerUpload, Long> {
    @Query("select orderpartnerUpload from OrderpartnerUpload orderpartnerUpload where orderpartnerUpload.companycode = ?1 and orderpartnerUpload.customersuppliertype = ?2 and orderpartnerUpload.customersuppliercode = ?3 order by orderpartnerUpload.id desc")
    List<OrderpartnerUpload> findAllByCustomersuppliercode(String companycode, String customersuppliertype, String customersuppliercode);
}

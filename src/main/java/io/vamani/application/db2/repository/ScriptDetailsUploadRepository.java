package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.ScriptDetailsUpload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;


/**
 * Spring Data  repository for the ScriptDetailsUpload entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ScriptDetailsUploadRepository extends JpaRepository<ScriptDetailsUpload, Long> {
    @Query("select scriptDetailsUpload from ScriptDetailsUpload  scriptDetailsUpload where scriptDetailsUpload.shippingBillDate between ?1 and ?2 and scriptDetailsUpload.shippingBillNo like ?3")
    Page<ScriptDetailsUpload> findByDateAndShippingBillNo(LocalDate dateFrom, LocalDate dateto, String sNo, Pageable pageable);

    @Query("select scriptDetailsUpload from ScriptDetailsUpload scriptDetailsUpload where scriptDetailsUpload.shippingBillNo like ?1")
    Page<ScriptDetailsUpload> findByShippingBillNo(String sNo, Pageable pageable);

    @Query("select scriptDetailsUpload from ScriptDetailsUpload scriptDetailsUpload where scriptDetailsUpload.shippingBillNo = ?1")
    List<ScriptDetailsUpload> findByShippingBillNo(String sNo);
}

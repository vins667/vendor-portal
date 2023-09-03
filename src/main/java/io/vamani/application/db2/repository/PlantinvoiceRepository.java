package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Plantinvoice;
import io.vamani.application.db2.domain.PlantinvoiceId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface PlantinvoiceRepository extends JpaRepository<Plantinvoice, PlantinvoiceId> {
    @Query("select plantinvoice from Plantinvoice plantinvoice where plantinvoice.challanno like ?1")
    Page<Plantinvoice> findAllByPlantinvoiceIgnoreCaseLike(String plantinvoicecode, Pageable pageable);

    @Query(value="SELECT OP.CUSTOMERSUPPLIERCODE, OP.LEGALNAME1, NVL((SELECT TRIM(SUBCODE01) FROM PLANTINVOICELINE WHERE PLANTINVOICECODE = PI.CODE FETCH FIRST ROW ONLY), ''), NVL((SELECT sum(PRIMARYQTY) PRIMARYQTY FROM PLANTINVOICELINE WHERE PLANTINVOICECODE = PI.CODE), 0) FROM PLANTINVOICE PI, VIEWORDERPARTNER OP WHERE PI.COMPANYCODE = OP.CUSTOMERSUPPLIERCOMPANYCODE AND PI.CONSIGNEECUSTOMERSUPPLIERTYPE = OP.CUSTOMERSUPPLIERTYPE AND PI.CONSIGNEECUSTOMERSUPPLIERCODE = OP.CUSTOMERSUPPLIERCODE AND PI.CHALLANNO=?1", nativeQuery = true)
    List<Object[]> fetchDetailsByInvoiceCode(String plantinvoicecode);

}

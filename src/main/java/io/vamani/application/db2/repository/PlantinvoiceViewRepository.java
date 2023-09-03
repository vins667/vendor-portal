package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Plantinvoice;
import io.vamani.application.db2.domain.PlantinvoiceId;
import io.vamani.application.db2.domain.PlantinvoiceView;
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
public interface PlantinvoiceViewRepository extends JpaRepository<PlantinvoiceView, PlantinvoiceId> {
    @Query("select plantinvoice from PlantinvoiceView plantinvoice where upper(plantinvoice.challanno) like ?1")
    Page<PlantinvoiceView> findAllByPlantinvoiceIgnoreCaseLike(String plantinvoicecode, Pageable pageable);

    @Query(value="SELECT OP.CUSTOMERSUPPLIERCODE, OP.LEGALNAME1, NVL((SELECT TRIM(SUBCODE01) FROM PLANTINVOICELINE WHERE PLANTINVOICECODE = PI.CODE FETCH FIRST ROW ONLY), '') FROM PLANTINVOICE PI, VIEWORDERPARTNER OP WHERE PI.COMPANYCODE = OP.CUSTOMERSUPPLIERCOMPANYCODE AND PI.CONSIGNEECUSTOMERSUPPLIERTYPE = OP.CUSTOMERSUPPLIERTYPE AND PI.CONSIGNEECUSTOMERSUPPLIERCODE = OP.CUSTOMERSUPPLIERCODE AND PI.CHALLANNO=?1", nativeQuery = true)
    List<Object[]> fetchDetailsByInvoiceCode(String plantinvoicecode);

}

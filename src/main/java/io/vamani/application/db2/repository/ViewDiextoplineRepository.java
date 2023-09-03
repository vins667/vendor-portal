package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.ViewDiextopline;
import io.vamani.application.db2.domain.ViewDiextoplineId;
import io.vamani.application.db2.domain.ViewDiextopline;
import io.vamani.application.db2.domain.ViewDiextoplineId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ViewDiextoplineRepository extends JpaRepository<ViewDiextopline, ViewDiextoplineId> {
    @Query("select viewDiextopline from ViewDiextopline viewDiextopline, Volextopapprovalhistory volextopapprovalhistory "
        + " where viewDiextopline.id.companycode = volextopapprovalhistory.id.companycode and viewDiextopline.id.code = volextopapprovalhistory.id.code and "
        + " volextopapprovalhistory.approverstatus = 'A' and viewDiextopline.ordprncustomersuppliercode = ?1 and upper(viewDiextopline.id.code) like ?2")
    Page<ViewDiextopline> findAllByViewDiextoplineIgnoreCaseLike(String ordprncustomersuppliercode, String viewDiextoplinecode, Pageable pageable);
}

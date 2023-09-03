package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.DiDirbillorederpartner;
import io.vamani.application.db2.domain.Vieworderpartner;
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
public interface DiDirbillorederpartnerRepository extends JpaRepository<DiDirbillorederpartner, String> {
    @Query("select didirbillorederpartner from DiDirbillorederpartner didirbillorederpartner where didirbillorederpartner.customersuppliertype = ?1 and UPPER(didirbillorederpartner.addressee) like ?2")
    Page<DiDirbillorederpartner> findAllByLegalname1IgnoreCaseLike(String customersuppliertype, String legalname1, Pageable pageable);
}

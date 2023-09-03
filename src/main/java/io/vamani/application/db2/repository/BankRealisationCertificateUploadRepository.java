package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.BankRealisationCertificateUpload;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.PersistenceContext;
import java.time.LocalDate;

@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface BankRealisationCertificateUploadRepository extends JpaRepository<BankRealisationCertificateUpload, Long> {

    @Query("select bankRealisationCertificateUpload from BankRealisationCertificateUpload bankRealisationCertificateUpload where bankRealisationCertificateUpload.brcDate between ?1 and ?2 and bankRealisationCertificateUpload.brcNo like ?3")
    Page<BankRealisationCertificateUpload> findByBrcDateAndBrcNo(LocalDate dateFrom, LocalDate dateTo, String brcNo, Pageable pageable);

    @Query("select bankRealisationCertificateUpload from BankRealisationCertificateUpload bankRealisationCertificateUpload where bankRealisationCertificateUpload.brcNo like ?1")
    Page<BankRealisationCertificateUpload> findByBrcNo(String brcNo, Pageable pageable);

    @Query("select bankRealisationCertificateUpload from BankRealisationCertificateUpload  bankRealisationCertificateUpload where bankRealisationCertificateUpload.sbNo=?1")
    BankRealisationCertificateUpload findByBrcDateAndBrcNos(String sbNo);
}

package io.vamani.application.repository;

import io.vamani.application.domain.TransactionUpload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionUploadRepository extends JpaRepository<TransactionUpload, Long> {


    @Query("select transactionUpload from TransactionUpload transactionUpload where transactionUpload.chequeNo  like ?1")
    Page<TransactionUpload> findByChequeNo(String chequeNo, Pageable pageable);

}

package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Paymentmethod;
import io.vamani.application.db2.domain.Paymentmethod;
import io.vamani.application.db2.domain.PaymentmethodId;
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
public interface PaymentmethodRepository extends JpaRepository<Paymentmethod, PaymentmethodId> {
    @Query("select paymentmethod from Paymentmethod paymentmethod where paymentmethod.longdescription like ?1")
    Page<Paymentmethod> findAllByLongdescriptionIgnoreCaseLike(String longdescription, Pageable pageable);
}

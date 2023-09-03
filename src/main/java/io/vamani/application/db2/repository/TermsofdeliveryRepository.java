package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Termsofdelivery;
import io.vamani.application.db2.domain.TermsofdeliveryId;
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
public interface TermsofdeliveryRepository extends JpaRepository<Termsofdelivery, TermsofdeliveryId> {
    @Override
    @Query("select termsofdelivery from Termsofdelivery termsofdelivery order by termsofdelivery.longdescription")
    List<Termsofdelivery> findAll();
}

package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Termsofdelivery;
import io.vamani.application.db2.domain.Termsofshipping;
import io.vamani.application.db2.domain.TermsofshippingId;
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
public interface TermsofshippingRepository extends JpaRepository<Termsofshipping, TermsofshippingId> {
    @Override
    @Query("select termsofshipping from Termsofshipping termsofshipping order by termsofshipping.longdescription")
    List<Termsofshipping> findAll();
}

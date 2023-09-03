package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.ViewDitaxglmapping;
import io.vamani.application.db2.domain.ViewDitaxglmappingId;
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
public interface ViewDitaxglmappingRepository extends JpaRepository<ViewDitaxglmapping, ViewDitaxglmappingId> {
    @Query("select viewDitaxglmapping from ViewDitaxglmapping viewDitaxglmapping where viewDitaxglmapping.id.taxcode like ?1 and viewDitaxglmapping.longdescription like ?1")
    Page<ViewDitaxglmapping> findAllByLongdescriptionIgnoreCaseLike(String taxcode, String longdescription, Pageable pageable);

    @Query("select viewDitaxglmapping from ViewDitaxglmapping viewDitaxglmapping where viewDitaxglmapping.id.eventcode = ?1 and viewDitaxglmapping.id.taxcode = ?2")
    ViewDitaxglmapping findAllByTaxCode(String eventcode, String taxCode);
}

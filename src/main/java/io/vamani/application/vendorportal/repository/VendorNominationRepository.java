package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.VendorNomination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the VendorNomination entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface VendorNominationRepository extends JpaRepository<VendorNomination, Long> {

    @Query(value = "select distinct vendor_nomination from VendorNomination vendor_nomination left join fetch vendor_nomination.buyerMasters",
        countQuery = "select count(distinct vendor_nomination) from VendorNomination vendor_nomination")
    Page<VendorNomination> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct vendor_nomination from VendorNomination vendor_nomination left join fetch vendor_nomination.buyerMasters")
    List<VendorNomination> findAllWithEagerRelationships();

    @Query("select vendor_nomination from VendorNomination vendor_nomination left join fetch vendor_nomination.buyerMasters where vendor_nomination.id =:id")
    Optional<VendorNomination> findOneWithEagerRelationships(@Param("id") Long id);
    
    @Query("select vendor_nomination from VendorNomination vendor_nomination left join fetch vendor_nomination.buyerMasters where vendor_nomination.vendorId =?1")
    VendorNomination findByVendorId(Long vendorId);

}

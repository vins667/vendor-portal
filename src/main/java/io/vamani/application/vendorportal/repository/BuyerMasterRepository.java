package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.BuyerMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the BuyerMaster entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface BuyerMasterRepository extends JpaRepository<BuyerMaster, Long> {
    @Query("select buyerMaster from BuyerMaster buyerMaster where buyerMaster.buyerCode like ?1 or buyerMaster.buyerName like ?2")
    Page<BuyerMaster> findAllByCodeAndDesc(String code, String description, Pageable pageable);

    @Query("select buyerMaster from BuyerMaster buyerMaster where buyerMaster.buyerCode in ?1")
    List<BuyerMaster> findAllByCode(List<String> buyerCodes);

    @Query("select buyerMaster from BuyerMaster buyerMaster where buyerMaster.buyerCode = ?1")
    BuyerMaster findByCode(String buyerCode);
}

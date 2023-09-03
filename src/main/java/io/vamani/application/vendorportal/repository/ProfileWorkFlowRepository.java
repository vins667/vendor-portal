package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.ProfileWorkFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the ProfileWorkFlow entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface ProfileWorkFlowRepository extends JpaRepository<ProfileWorkFlow, Long> {

    @Query("select profileWorkFlow from ProfileWorkFlow profileWorkFlow where profileWorkFlow.vendorId = ?1 order by id")
    List<ProfileWorkFlow> findAllByVendorId(Long vendorId);
}

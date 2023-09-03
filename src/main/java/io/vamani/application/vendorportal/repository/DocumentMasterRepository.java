package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.DocumentMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the DocumentMaster entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface DocumentMasterRepository extends JpaRepository<DocumentMaster, Long> {
    @Query("select documentMaster.id from DocumentMaster documentMaster where documentMaster.requiredField = true")
    List<Object> countAllByRequiredField();

    @Query("select documentMaster.id from DocumentMaster documentMaster where documentMaster.requiredField = true and documentMaster.docCountry =?1")
    List<Object> countAllByRequiredField(String docCountry);

    @Query("select documentMaster from DocumentMaster documentMaster where documentMaster.docCountry =?1 order by documentMaster.id")
    List<DocumentMaster> findAllByCountry(String docCountry);
}

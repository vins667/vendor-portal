package io.vamani.application.repository;
import io.vamani.application.domain.TermsConditionMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TermsConditionMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TermsConditionMasterRepository extends JpaRepository<TermsConditionMaster, Long> {
	
	 @Query("select termsConditionMaster from TermsConditionMaster termsConditionMaster where termsConditionMaster.id =:id")
	 TermsConditionMaster findAllById(@Param("id") Long id);

}

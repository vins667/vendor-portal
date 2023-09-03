package io.vamani.application.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.CostingValueMaster;


/**
 * Spring Data  repository for the CostingValueMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CostingValueMasterRepository extends JpaRepository<CostingValueMaster, Long> {
	 @Query("select costingValueMaster from CostingValueMaster costingValueMaster where costingValueMaster.processname like ?1 and costingValueMaster.valuetype like ?2 ")
	 Page<CostingValueMaster> findAllByCodeAndDsc(String processname, String valuetype, Pageable pageable);
}

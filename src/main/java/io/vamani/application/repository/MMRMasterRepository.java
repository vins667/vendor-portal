package io.vamani.application.repository;
import io.vamani.application.domain.JobProfile;
import io.vamani.application.domain.MMRMaster;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MMRMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MMRMasterRepository extends JpaRepository<MMRMaster, Long> {
	@Query("select distinct mmrMaster.factory, mmrMaster.monthYear from MMRMaster mmrMaster")
	Page<Object[]> findAllByFactory(Pageable pageable);
	
    @Query("select mmrMaster from MMRMaster mmrMaster where mmrMaster.factory= ?1 and CAST(mmrMaster.monthYear AS date) = ?2 order by 1,2") 
	List<MMRMaster> findAllByMonthYear(String factory, Date monthYear);
	 
	@Query("select mmrMaster from MMRMaster mmrMaster where mmrMaster.monthYear in (select max(mmrMaster.monthYear) from MMRMaster mmrMaster where mmrMaster.factory= ?1 and  CAST( mmrMaster.monthYear AS date) = ?2)")
	List<MMRMaster> findAllMaxDeatils(String factory, Date monthYear);
    @Query("select mmrMaster from MMRMaster mmrMaster where mmrMaster.factory= ?1 and mmrMaster.monthYear = ?2 order by 1,2")
	List<MMRMaster> findAllByMonthYear(String factory, Instant monthYear);
	 
	@Query("select mmrMaster from MMRMaster mmrMaster where mmrMaster.factory= ?1 and mmrMaster.monthYear in (select max(mmrMaster.monthYear) from MMRMaster mmrMaster where mmrMaster.factory= ?2)")
	List<MMRMaster> findAllMaxDetails(String factory, String factory2);

    @Query("SELECT  mmrMaster FROM MMRMaster mmrMaster WHERE  mmrMaster.factory=?1 AND mmrMaster.monthYear between ?2 and ?3 ")
    List<MMRMaster> GetDeptCodeByDate(String factoryCode, Instant dateFrom, Instant dateTo);

    @Query(value = "select mMRMaster from MMRMaster mMRMaster where (mMRMaster.factory, mMRMaster.monthYear) IN(select mMRMaster.factory, max(mMRMaster.monthYear) from MMRMaster mMRMaster group by  mMRMaster.factory) order by mMRMaster.factory, mMRMaster.monthYear")
    List<MMRMaster> GetFactoryGroupByDate();
}

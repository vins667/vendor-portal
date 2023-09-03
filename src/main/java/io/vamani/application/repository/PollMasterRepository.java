package io.vamani.application.repository;

import io.vamani.application.domain.FactoryMaster;
import io.vamani.application.domain.PollMaster;
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
 * Spring Data  repository for the PollMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PollMasterRepository extends JpaRepository<PollMaster, Long> {

    @Query(value = "select distinct poll_master from PollMaster poll_master left join fetch poll_master.factoryMasters",
        countQuery = "select count(distinct poll_master) from PollMaster poll_master")
    Page<PollMaster> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct poll_master from PollMaster poll_master left join fetch poll_master.factoryMasters")
    List<PollMaster> findAllWithEagerRelationships();

    @Query("select poll_master from PollMaster poll_master left join fetch poll_master.factoryMasters where poll_master.id =:id")
    Optional<PollMaster> findOneWithEagerRelationships(@Param("id") Long id);

    @Query("select distinct poll_master from PollMaster poll_master left join fetch poll_master.factoryMasters factory_masters where factory_masters.id=?1 and poll_master.endDate is null")
    List<PollMaster> findAllByFactoryMasters(long id);

    @Query("select distinct poll_master from PollMaster poll_master left join fetch poll_master.factoryMasters factory_masters where factory_masters.factoryCode=?1 and poll_master.endDate is null")
    List<PollMaster> findAllByFactoryMasterscodeName(String subSname);


    @Query("select poll_master from PollMaster poll_master where poll_master.endDate is null")
    List<PollMaster> findAllWithoutFactory();

    @Query("select poll_master.pollText, poll_details.pollOption, count(poll_employee_details.createdBy) from PollMaster poll_master inner join PollDetails poll_details on poll_master.id = poll_details.pollMaster.id " +
        " left outer join PollEmployeeDetails poll_employee_details on poll_details.pollMaster.id=poll_employee_details.pollMaster.id and poll_details.id = poll_employee_details.pollDetails.id" +
        " where poll_master.id=?1 group by poll_master.pollText, poll_details.pollOption")
    List<Object[]> countCreatedBy(long id);

}

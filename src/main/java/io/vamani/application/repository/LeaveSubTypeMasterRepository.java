package io.vamani.application.repository;

import io.vamani.application.domain.LeaveSubTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the LeaveSubTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveSubTypeMasterRepository extends JpaRepository<LeaveSubTypeMaster, Long> {
    @Query("select leaveSubTypeMaster from LeaveSubTypeMaster leaveSubTypeMaster where leaveSubTypeMaster.leaveTypeMaster.id=?1 order by leaveSubTypeMaster.id")
    List<LeaveSubTypeMaster> findByLeaveTypeMasterId(Long id);
}

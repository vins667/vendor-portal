package io.vamani.application.repository;

import io.vamani.application.domain.LeaveTypeMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the LeaveTypeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveTypeMasterRepository extends JpaRepository<LeaveTypeMaster, Long> {
    @Query("select leaveTypeMaster from LeaveTypeMaster leaveTypeMaster where leaveTypeMaster.leaveType = ?1 order by leaveTypeMaster.leaveName")
    List<LeaveTypeMaster> findAllByLeaveType(String leaveType);
}

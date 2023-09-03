package io.vamani.application.repository;

import io.vamani.application.domain.ManpowerTypeMapping;
import io.vamani.application.domain.ManpowerTypeMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the MachineMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManpowerTypeMappingRepository extends JpaRepository<ManpowerTypeMapping, ManpowerTypeMappingId> {
    @Query("select manpowertypemapping.id.deptCode from ManpowerTypeMapping manpowertypemapping where manpowertypemapping.id.manpowerType = ?1")
    List<String> findDepartmentsByType(String manpowerType);
}

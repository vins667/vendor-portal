package io.vamani.application.repository;

import io.vamani.application.domain.VcutTvConfigration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the VcutPlanChangeMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VcutTvConfigrationRepository extends JpaRepository<VcutTvConfigration, Long> {
    @Query("select vcutTvConfigration from VcutTvConfigration vcutTvConfigration where vcutTvConfigration.line = ?1 order by id")
    List<VcutTvConfigration> findByLine(String line);
}

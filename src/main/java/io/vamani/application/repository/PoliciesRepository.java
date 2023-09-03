package io.vamani.application.repository;

import io.vamani.application.domain.Policies;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the Policies entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PoliciesRepository extends JpaRepository<Policies, Long> {
    @Override
    @Query("select policies from Policies policies order by policies.policyName")
    List<Policies> findAll();

    @Query("select policies from Policies policies order by policies.policiesGroup.description, policies.ordering")
    List<Policies> findAllBySort();

    @Query("select policies from Policies policies where policies.policiesGroup.id=?1 order by policies.ordering")
    List<Policies> findAllByPolicyGroup(Long policiesGroupId);
}

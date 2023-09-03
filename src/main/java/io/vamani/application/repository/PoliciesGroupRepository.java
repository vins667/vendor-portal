package io.vamani.application.repository;

import io.vamani.application.domain.PoliciesGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PoliciesGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PoliciesGroupRepository extends JpaRepository<PoliciesGroup, Long> {

}

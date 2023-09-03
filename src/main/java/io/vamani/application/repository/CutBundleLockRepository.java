package io.vamani.application.repository;

import io.vamani.application.domain.CutBundleLock;
import io.vamani.application.domain.CutBundleLockId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
/**
 * Spring Data  repository for the CutBundleLock entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CutBundleLockRepository extends JpaRepository<CutBundleLock, CutBundleLockId> {

}

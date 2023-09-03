package io.vamani.application.repository;

import io.vamani.application.domain.EventAccess;
import io.vamani.application.domain.EventAccessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Event entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventAccessRepository extends JpaRepository<EventAccess, EventAccessId> {
    @Query("select eventAccess from EventAccess eventAccess where eventAccess.id.cardNo=?1")
    List<EventAccess> findByCardNo(String cardNo);
}

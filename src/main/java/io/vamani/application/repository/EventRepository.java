package io.vamani.application.repository;

import io.vamani.application.domain.Event;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * Spring Data  repository for the Event entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("select event from Event event where event.createdBy=?1 and EXTRACT (month FROM event.eventFrom) = ?2 AND EXTRACT (year FROM event.eventFrom) = ?3 order by event.eventFrom")
    List<Event> getEventMonthList(String userId, int month, int year);
}

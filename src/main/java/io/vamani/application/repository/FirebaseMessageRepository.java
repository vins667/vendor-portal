package io.vamani.application.repository;

import io.vamani.application.domain.FirebaseMessage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FirebaseMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FirebaseMessageRepository extends JpaRepository<FirebaseMessage, Long> {

}

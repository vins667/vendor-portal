package io.vamani.application.repository;

import io.vamani.application.domain.SmsRegistration;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the SmsRegistration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SmsRegistrationRepository extends JpaRepository<SmsRegistration, Long> {
    SmsRegistration findOneByCardNo(String cardNo);
}

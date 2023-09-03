package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.EmailInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


/**
 * Spring Data  repository for the EmailInvitation entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface EmailInvitationRepository extends JpaRepository<EmailInvitation, Long> {
    @Query("select emailInvitation from EmailInvitation emailInvitation where emailInvitation.emailId = ?1")
    EmailInvitation findByEmailId(String emailId);
}

package io.vamani.application.repository;

import io.vamani.application.domain.MobileAuthenticator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MenuMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MobileAuthenticatorRepository extends JpaRepository<MobileAuthenticator, String> {
    @Query("select mobileAuthenticator from MobileAuthenticator mobileAuthenticator where mobileAuthenticator.jwtKey = ?1")
    MobileAuthenticator findByJwtKey(String jwtKey);
}

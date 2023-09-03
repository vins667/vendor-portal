package io.vamani.application.vendorportal.repository;

import io.vamani.application.vendorportal.domain.UserVendor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the UserVendor entity.
 */
@Repository
@Transactional("vendorportalTransactionManager")
@PersistenceContext(name = "vendorportalEntityManagerFactory")
public interface UserVendorRepository extends JpaRepository<UserVendor, Long> {

    @Query("select user from UserVendor user where user.vendors.id = ?1")
    List<UserVendor> findAllByVendorId(Long vendorId);

    @Query("select user from UserVendor user where user.email = ?1")
    UserVendor findByEmail(String email);

}

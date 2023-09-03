package io.vamani.application.repository;

import io.vamani.application.domain.User;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.time.Instant;

/**
 * Spring Data JPA repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesById(Long id);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByLogin(String login);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByEmailIgnoreCase(String email);

    Page<User> findAllByLoginNot(Pageable pageable, String login);

    @Query("select user.login from User user where user.activated=true")
    List<String> findAllActivatedUser();

    @Query("select user from User user where user.login like ?1 and upper(CONCAT(user.firstName, '' , user.lastName)) like ?2")
    Page<User> findAllCodeAndName(String code, String name, Pageable page);

    @Query("select upper(CONCAT(user.firstName, '' , user.lastName)) from User user where user.login=?1 ")
	String GetLoginFullName(String ids);
    
    //@Query("select user from User user where user.login like ?1 and upper(CONCAT(user.firstName, '' , user.lastName)) like ?2")
    //String<desc> getUserNameByLogin(String code);
}

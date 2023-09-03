package io.vamani.application.repository;

import io.vamani.application.domain.JobProfileAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobProfileAccessRepository extends JpaRepository<JobProfileAccess, Long> {

    @Query("select jobProfileAccess from JobProfileAccess jobProfileAccess where jobProfileAccess.login=?1")
    JobProfileAccess findByLogin(String login);
}

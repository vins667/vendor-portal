package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Project;
import io.vamani.application.db2.domain.ProjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface ProjectRepository extends JpaRepository<Project, ProjectId> {
    @Query("select project from Project project where upper(project.id.code) like ?1")
    Page<Project> findAllByProjectIgnoreCaseLike(String projectcode, Pageable pageable);

}

package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Division;
import io.vamani.application.db2.domain.DivisionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface DivisionRepository extends JpaRepository<Division, DivisionId> {

	@Query("select division from Division division where division.id.companycode = ?1 and division.id.code<>'ALL' order by division.longdescription")
    List<Division> findAllByCompanycode(String companycode);
}

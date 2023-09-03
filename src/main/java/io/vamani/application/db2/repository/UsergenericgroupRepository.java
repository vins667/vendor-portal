package io.vamani.application.db2.repository;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.vamani.application.db2.domain.Usergenericgroup;
import io.vamani.application.db2.domain.UsergenericgroupId;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface UsergenericgroupRepository extends JpaRepository<Usergenericgroup, UsergenericgroupId> {
	@Query("select usergenericgroup from Usergenericgroup usergenericgroup where  usergenericgroup.id.usergenericgrouptypecode =?1")
	List<Usergenericgroup> findByCode(String code);
}

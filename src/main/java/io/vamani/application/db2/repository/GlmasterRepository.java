package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Glmaster;
import io.vamani.application.db2.domain.GlmasterId;
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
public interface GlmasterRepository extends JpaRepository<Glmaster, GlmasterId> {
    @Query("select glmaster from Glmaster glmaster where (glmaster.id.code like ?1 or glmaster.longdescription like ?1) AND glmaster.chartofaccountcode='18' ")
    Page<Glmaster> findAllByLongdescriptionIgnoreCaseLike(String code, String longdescription, Pageable pageable);

    @Query("select glmaster from Glmaster glmaster where glmaster.id.code like ?1 ")
    Page<Glmaster> findAllByCodeIgnoreCaseLike(String code, Pageable pageable);
}

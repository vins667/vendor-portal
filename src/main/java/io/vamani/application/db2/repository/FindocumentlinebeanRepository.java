package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Findocumentlinebean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface FindocumentlinebeanRepository extends JpaRepository<Findocumentlinebean, Long> {
    @Modifying
    @javax.transaction.Transactional
    @Query("delete from Findocumentlinebean findocumentlinebean where findocumentlinebean.fatherid=?1")
    void deleteAllByFindocumentId(Long masterId);
}

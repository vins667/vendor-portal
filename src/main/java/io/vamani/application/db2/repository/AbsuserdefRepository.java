package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Absuserdef;
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
public interface AbsuserdefRepository extends JpaRepository<Absuserdef, String> {
    @Query("select absuserdef from Absuserdef absuserdef where absuserdef.sendersmtpid = ?1")
    List<Absuserdef> findAllBySendersmtpid(String sendersmtpid);
}

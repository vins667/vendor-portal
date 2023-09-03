package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Findocument;
import io.vamani.application.db2.domain.FindocumentId;
import io.vamani.application.db2.domain.Findocumentline;
import io.vamani.application.db2.domain.FindocumentlineId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface FindocumentlineRepository extends JpaRepository<Findocumentline, FindocumentlineId> {

    @Query("select findocumentline from Findocumentline findocumentline where findocumentline.id.findocumentcompanycode = ?1 and findocumentline.id.findocumentbusinessunitcode = ?2 and findocumentline.id.findocumentfinancialyearcode = ?3 and findocumentline.id.findocumentcode = ?4 and findocumentline.id.linenumber = ?5")
    Findocumentline findById(String findocumentcompanycode, String findocumentbusinessunitcode, Integer findocumentfinancialyearcode, String findocumentcode, Integer linenumber);
}

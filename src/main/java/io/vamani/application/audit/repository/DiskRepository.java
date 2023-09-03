package io.vamani.application.audit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import io.vamani.application.audit.domain.Disk;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;
import java.util.List;
/**
 * Spring Data  repository for the Catgory entity.
 */
@SuppressWarnings("unused")
@Repository
@Transactional("auditTransactionManager")
@PersistenceContext(name = "auditEntityManagerFactory")
public interface DiskRepository extends JpaRepository<Disk, Integer>, JpaSpecificationExecutor<Disk> {
    @Query("select sum(disk.size) from Disk disk where disk.systemId = ?1 and disk.current = 'y'")
    Long sumSizeBySystemId(Integer systemId);
}

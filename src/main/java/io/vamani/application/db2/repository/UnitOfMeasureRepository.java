package io.vamani.application.db2.repository;

import java.util.List;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import io.vamani.application.db2.domain.UnitOfMeasure;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, String>{
    @Query("select unitOfMeasure from UnitOfMeasure unitOfMeasure where unitOfMeasure.code in(?1)")
    List<UnitOfMeasure> findAllByCode(List<String> code);

    @Query("select unitOfMeasure from UnitOfMeasure unitOfMeasure order by unitOfMeasure.code")
    List<UnitOfMeasure> findAll();
}

package io.vamani.application.db2.repository;

import io.vamani.application.db2.domain.Plant;
import io.vamani.application.db2.domain.PlantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.PersistenceContext;

@SuppressWarnings("unused")
@Repository
@Transactional("db2TransactionManager")
@PersistenceContext(name = "db2EntityManagerFactory")
public interface PlantRepository extends JpaRepository<Plant, PlantId> {
	
	@Query("select plant from Plant plant")
    List<Plant> findAllPlant(String divisioncode);
}

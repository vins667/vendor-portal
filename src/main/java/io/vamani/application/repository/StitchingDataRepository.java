package io.vamani.application.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.StitchingDataEntity;

@Repository
public interface StitchingDataRepository extends JpaRepository<StitchingDataEntity, Long>{

}

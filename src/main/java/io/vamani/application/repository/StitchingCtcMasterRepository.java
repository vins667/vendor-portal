package io.vamani.application.repository;

import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.StitchingCtcMaster;

@Repository
public interface StitchingCtcMasterRepository extends JpaRepository<StitchingCtcMaster, Long> {
	@Query("select sum(master.empCtc) from StitchingCtcMaster master where master.factoryCode=?1 and master.DayNo='2023-10-03'")
	BigDecimal findTotalCTC(String factory);

}

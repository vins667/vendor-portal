package io.vamani.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.vamani.application.domain.PreviousEmploymentDetails;

/**
 * Spring Data  repository for the PreviousEmploymentDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PreviousEmploymentDetailsRepository extends JpaRepository<PreviousEmploymentDetails, Long> {
	@Query("select previousEmploymentDetails from PreviousEmploymentDetails previousEmploymentDetails where previousEmploymentDetails.cardNo = ?1 and previousEmploymentDetails.financeYear = ?2")
    List<PreviousEmploymentDetails> findAllByCardNoAndYear(String cardNo, String year);

    @Query("select previousEmploymentDetails from PreviousEmploymentDetails previousEmploymentDetails where previousEmploymentDetails.cardNo = ?1")
    List<PreviousEmploymentDetails> findAllByCardNo(String cardNo);
    
    @Query("select previousEmploymentDetails from PreviousEmploymentDetails previousEmploymentDetails where previousEmploymentDetails.financeYear = ?1 and previousEmploymentDetails.cardNo like ?2  order by previousEmploymentDetails.cardNo")
    Page<PreviousEmploymentDetails> findAllByYearAndCardNo(String year,String cardNo,Pageable pageable);
}

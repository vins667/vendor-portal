package io.vamani.application.repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import io.vamani.application.domain.TdsDeclarationUpload;

/**
 * Spring Data  repository for the TdsDeclarationUpload entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TdsDeclarationUploadRepository extends JpaRepository<TdsDeclarationUpload, Long> {

	  @Query("select tdsDeclarationUpload from TdsDeclarationUpload tdsDeclarationUpload where tdsDeclarationUpload.cardNo =?1 and tdsDeclarationUpload.financialYear= ?2")
	   List<TdsDeclarationUpload> findByCardNo(String cardNo, String financialYear);

	   @Query(value = " select distinct card_no,financial_year from tds_declaration_upload where card_no like ?1 and financial_year=?2 ORDER BY card_no",
			   countQuery = "select COUNT(DISTINCT card_no) from tds_declaration_upload where card_no like ?1 and financial_year=?2", nativeQuery = true)
	   Page<Object[]> findAllCardNo(String cardNo,String financialYear, Pageable page);

    @Query(value = "SELECT TDU.CARD_NO, TDU.FINANCIAL_YEAR, COALESCE(MAX(TDU.APPROVAL_FLAG), '') APPROVAL_FLAG FROM TDS_DECLARATION_UPLOAD TDU, JHI_USER JU WHERE TDU.CARD_NO = JU.LOGIN AND TDU.CARD_NO LIKE ?1 AND JU.FIRST_NAME LIKE ?2 AND TDU.FINANCIAL_YEAR = ?3 GROUP BY TDU.CARD_NO, TDU.FINANCIAL_YEAR ORDER BY TDU.CARD_NO",
        countQuery = "SELECT COUNT(DISTINCT TDU.CARD_NO) FROM TDS_DECLARATION_UPLOAD TDU, JHI_USER JU WHERE TDU.CARD_NO = JU.LOGIN AND TDU.CARD_NO LIKE ?1 AND JU.FIRST_NAME LIKE ?2 AND TDU.FINANCIAL_YEAR = ?3", nativeQuery = true)
    Page<Object[]> findAllCardNoAndName(String cardNo, String name,String financialYear, Pageable page);


	   @Query(value = "select distinct card_no,financial_year from tds_declaration_upload where card_no like ?1 and financial_year=?2 ORDER BY card_no", nativeQuery = true)
	   List<Object[]> findByAllCardNo(String cardNo,String financialYear);


	   @Query("select tdsDeclarationUpload from TdsDeclarationUpload tdsDeclarationUpload where tdsDeclarationUpload.cardNo like ?1 and tdsDeclarationUpload.financialYear= ?2")
	   List<TdsDeclarationUpload> findAllByCardAndYear(String cardNo, String financialYear);

}

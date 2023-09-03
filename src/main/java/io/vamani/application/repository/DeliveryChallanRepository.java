package io.vamani.application.repository;

import io.vamani.application.domain.DeliveryChallan;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
/**
 * Spring Data  repository for the DeliveryChallan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryChallanRepository extends JpaRepository<DeliveryChallan, Long> {
	@Query("select deliveryChallan from DeliveryChallan deliveryChallan where deliveryChallan.factCode like ?1")
    Page<DeliveryChallan> findAllByfactory(String code,Pageable pageable);
	
	@Query("select deliveryChallan from DeliveryChallan deliveryChallan where deliveryChallan.factCode like ?1 and deliveryChallan.flag =?2")
    Page<DeliveryChallan> findAllByfactoryAndStatus(String code,String status,Pageable pageable);
	
	@Query(value="select fact_code, fact_description,(TRIM((COALESCE(f_address_line_1, '')|| ' '|| COALESCE(f_address_line_2, '')|| ' '|| COALESCE(f_address_line_3, '')|| ' '|| COALESCE(f_address_line_4, '')|| ' '|| COALESCE(district, '')|| '( '|| COALESCE(TRIM(postal_code), '')|| ')'))) faddress,"
			+ " f_gst_number,(TRIM((COALESCE(b_address_line_1, '')|| ' '|| COALESCE(b_address_line_2, '')|| ' '|| COALESCE(b_address_line_3, '')|| ' '|| COALESCE(b_address_line_4, '')|| ' '|| COALESCE(b_district, '')|| '( '|| COALESCE(TRIM(b_postal_code), '')|| ')'))) baddress,"
			+ " b_gst_number,(trim(product_name))product,triff_code, quantity,rate,amount,taxper,(case when trim(state_code) = trim(b_state_code) then 'CGST' else 'IGS' end) taxtype,taxval,total_amt,COALESCE(b_legalname_1,'NA')b_legalname_1,"
			+ " case when challan_type ='R' then 'Retunable' else 'Non Returnable' end challan_type,challan_date,e_way_bill_no, e_way_bill_date,exp_return_date,COALESCE(trim(remarks),'')remarks"
			+ " from delivery_challan h,delivery_challan_details d where h.id=d.delivery_challan_id and h.id =?1",nativeQuery=true)
    List<Object[]> findByDeliveryChallanReport(Long id);
	
}

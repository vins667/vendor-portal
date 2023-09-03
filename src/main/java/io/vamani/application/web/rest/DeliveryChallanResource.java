package io.vamani.application.web.rest;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.domain.Tariff;
import io.vamani.application.db2.model.OrderPartnerBean;
import io.vamani.application.db2.repository.FactoryRepository;
import io.vamani.application.db2.repository.TariffRepository;
import io.vamani.application.domain.DeliveryChallan;
import io.vamani.application.domain.DeliveryChallanDetails;
import io.vamani.application.model.DeliveryChallanBean;
import io.vamani.application.model.DeliveryChallanReportBean;
import io.vamani.application.model.DeliveryChallanSearchBean;
import io.vamani.application.repository.DeliveryChallanDetailsRepository;
import io.vamani.application.repository.DeliveryChallanRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import io.vamani.application.db2.domain.Factory;
/**
 * REST controller for managing DeliveryChallan.
 */
@RestController
@RequestMapping("/api")
public class DeliveryChallanResource {

    private final Logger log = LoggerFactory.getLogger(DeliveryChallanResource.class);

    private static final String ENTITY_NAME = "deliveryChallan";

    @Autowired
    private FactoryRepository factoryRepository;

    @Autowired
    private TariffRepository tariffRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private  DeliveryChallanDetailsRepository deliveryChallanDetailsRepository;

    private final DeliveryChallanRepository deliveryChallanRepository;

    public DeliveryChallanResource(DeliveryChallanRepository deliveryChallanRepository) {
        this.deliveryChallanRepository = deliveryChallanRepository;
    }

    /**
     * POST  /delivery-challans : Create a new deliveryChallan.
     *
     * @param deliveryChallan the deliveryChallan to create
     * @return the ResponseEntity with status 201 (Created) and with body the new deliveryChallan, or with status 400 (Bad Request) if the deliveryChallan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/delivery-challans")
    @Timed
    public ResponseEntity<DeliveryChallan> createDeliveryChallan(@Valid @RequestBody DeliveryChallanBean deliveryChallanBean) throws URISyntaxException {
        log.debug("REST request to save DeliveryChallan : {}", deliveryChallanBean);
        if (deliveryChallanBean.getId() != null) {
            throw new BadRequestAlertException("A new deliveryChallan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeliveryChallan deliveryChallan = new DeliveryChallan();
        BeanUtils.copyProperties(deliveryChallanBean, deliveryChallan);
        if(deliveryChallan.getRemarks()!=null) {
           deliveryChallan.setRemarks(deliveryChallan.getRemarks().trim().toUpperCase());
        }
        deliveryChallan.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        deliveryChallan.setFlag("E");
        deliveryChallan.setCreatedDate(Instant.now());
        DeliveryChallan result = deliveryChallanRepository.save(deliveryChallan);
        for(DeliveryChallanDetails bean:deliveryChallanBean.getDeliveryChallanDetails()) {
        	if(bean.getProductName() !=null) {
	        	bean.setDeliveryChallan(result);
	        	bean.setProductName(bean.getProductName().trim().toUpperCase());
	        	bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
	        	bean.setCreatedDate(Instant.now());
	        	deliveryChallanDetailsRepository.save(bean);
        	}
        }
        return ResponseEntity.created(new URI("/api/delivery-challans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /delivery-challans : Updates an existing deliveryChallan.
     *
     * @param deliveryChallan the deliveryChallan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deliveryChallan,
     * or with status 400 (Bad Request) if the deliveryChallan is not valid,
     * or with status 500 (Internal Server Error) if the deliveryChallan couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/delivery-challans")
    @Timed
    public ResponseEntity<DeliveryChallan> updateDeliveryChallan(@Valid @RequestBody DeliveryChallanBean deliveryChallanBean) throws URISyntaxException {
        log.debug("REST request to update DeliveryChallan : {}", deliveryChallanBean);
        if (deliveryChallanBean.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DeliveryChallan  deliveryChallan = deliveryChallanRepository.findById(deliveryChallanBean.getId()).orElse(null);
        BeanUtils.copyProperties(deliveryChallanBean, deliveryChallan);
        if(deliveryChallan.getRemarks() !=null) {
        	deliveryChallan.setRemarks(deliveryChallan.getRemarks().trim().toUpperCase());
        }
        deliveryChallan.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        deliveryChallan.setLastUpdatedDate(Instant.now());
        DeliveryChallan result = deliveryChallanRepository.save(deliveryChallan);
        for(DeliveryChallanDetails bean:deliveryChallanBean.getDeliveryChallanDetails()) {
        	if(bean.getId()!=null) {
        		DeliveryChallanDetails deliveryChallanDetails =deliveryChallanDetailsRepository.findById(bean.getId()).orElse(null);
        		BeanUtils.copyProperties(bean, deliveryChallanDetails);
        		deliveryChallanDetails.setDeliveryChallan(result);
        		deliveryChallanDetails.setProductName(deliveryChallanDetails.getProductName().trim().toUpperCase());
        		deliveryChallanDetails.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        		deliveryChallanDetails.setLastUpdatedDate(Instant.now());
        		deliveryChallanDetailsRepository.save(deliveryChallanDetails);
        	} else {
            	bean.setDeliveryChallan(result);
            	bean.setProductName(bean.getProductName().trim().toUpperCase());
            	bean.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            	bean.setCreatedDate(Instant.now());
            	deliveryChallanDetailsRepository.save(bean);
        	}
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deliveryChallan.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /delivery-challans-aprv : Updates an existing deliveryChallanApproval.
     *
     * @param deliveryChallan the deliveryChallan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated deliveryChallan,
     * or with status 400 (Bad Request) if the deliveryChallan is not valid,
     * or with status 500 (Internal Server Error) if the deliveryChallan couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/delivery-challans-aprv")
    @Timed
    public ResponseEntity<DeliveryChallan> updateDeliveryChallanApproval(@Valid @RequestBody DeliveryChallan deliveryChallan) throws URISyntaxException {
        log.debug("REST request to update DeliveryChallan : {}", deliveryChallan);
        if (deliveryChallan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        deliveryChallan.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        deliveryChallan.setApprovedDate(Instant.now());
        DeliveryChallan result = deliveryChallanRepository.save(deliveryChallan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, deliveryChallan.getId().toString()))
            .body(result);
    }

    /**
     * GET  /delivery-challans : get all the deliveryChallans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of deliveryChallans in body
     */
    @PostMapping("/delivery-challans-qry")
    @Timed
    public ResponseEntity<List<DeliveryChallan>> getAllDeliveryChallans(@Valid @RequestBody DeliveryChallanSearchBean search) {
        log.debug("REST request to get a page of DeliveryChallans");
        String code = "%";
        if (search.getFactCode() != null) {
            code = search.getFactCode().toUpperCase().trim()+ "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        Page<DeliveryChallan> page = deliveryChallanRepository.findAllByfactory(code, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-challans");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /delivery-challans : get all the deliveryChallans.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of deliveryChallans in body
     */
    @PostMapping("/delivery-challans-aprvqry")
    @Timed
    public ResponseEntity<List<DeliveryChallan>> getAllDeliveryChallansAprv(@Valid @RequestBody DeliveryChallanSearchBean search) {
        log.debug("REST request to get a page of DeliveryChallans");
        String code = "%";
        if (search.getFactCode() != null) {
            code = search.getFactCode().toUpperCase().trim()+ "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        Page<DeliveryChallan> page = deliveryChallanRepository.findAllByfactoryAndStatus(code,search.getStatus(), search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-challans");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /delivery-challans/:id : get the "id" deliveryChallan.
     *
     * @param id the id of the deliveryChallan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the deliveryChallan, or with status 404 (Not Found)
     */
    @GetMapping("/delivery-challans/{id}")
    @Timed
    public ResponseEntity<DeliveryChallanBean> getDeliveryChallan(@PathVariable Long id) {
        log.debug("REST request to get DeliveryChallan : {}", id);
        String tempFAddress="";
        String tempSAddress="";
        DeliveryChallanBean deliveryChallanBean = new DeliveryChallanBean();
        DeliveryChallan deliveryChallan = deliveryChallanRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(deliveryChallan, deliveryChallanBean);
        if(deliveryChallan.getfAddressLine1()!=null) {
        	 tempFAddress+=deliveryChallan.getfAddressLine1().trim();
        }
        if(deliveryChallan.getfAddressLine2()!=null) {
        	 tempFAddress+=deliveryChallan.getfAddressLine2().trim();
        }
        if(deliveryChallan.getfAddressLine3()!=null) {
        	tempFAddress+=deliveryChallan.getfAddressLine3().trim();
        }
        if(deliveryChallan.getfAddressLine4()!=null) {
        	tempFAddress+=deliveryChallan.getfAddressLine4().trim();
        }
        if(deliveryChallan.getfAddressLine5()!=null) {
        	 tempFAddress+=deliveryChallan.getfAddressLine5().trim();
        }
        if(deliveryChallan.getDistrict()!=null) {
        	tempFAddress+= "  "+deliveryChallan.getDistrict().trim();
        }
        if(deliveryChallan.getPostalCode()!=null) {
        	tempFAddress+= " ("+deliveryChallan.getPostalCode().trim() +")";
        }
        tempFAddress+= " GST Number("+deliveryChallan.getfGSTNumber() +")";
        deliveryChallanBean.setShowAddress1(tempFAddress);
        if(deliveryChallan.getbAddressLine1()!=null) {
        	tempSAddress+=deliveryChallan.getbAddressLine1().trim();
        }
        if(deliveryChallan.getbAddressLine2()!=null) {
        	tempSAddress+=deliveryChallan.getbAddressLine2().trim();
        }
        if(deliveryChallan.getbAddressLine3()!=null) {
        	tempSAddress+=deliveryChallan.getbAddressLine3().trim();
        }
        if(deliveryChallan.getbAddressLine4()!=null) {
        	 tempSAddress+=deliveryChallan.getbAddressLine4().trim();
        }
        if(deliveryChallan.getbAddressLine5()!=null) {
        	tempSAddress+=deliveryChallan.getbAddressLine5().trim();
        }
        if(deliveryChallan.getbDistrict()!=null) {
        	 tempSAddress+= "  "+deliveryChallan.getbDistrict().trim();
        }
        if(deliveryChallan.getbPostalCode()!=null) {
        	 tempSAddress+= "  ("+deliveryChallan.getbPostalCode().trim() +")";
        }
        tempSAddress+= " GST Number("+deliveryChallan.getbGSTNumber() +")";
        deliveryChallanBean.setShowAddress2(tempSAddress);
        deliveryChallanBean.setDeliveryChallanDetails(deliveryChallanDetailsRepository.findByDeliveryChallanId(id));
        return ResponseUtil.wrapOrNotFound(Optional.of(deliveryChallanBean));
    }

    @PostMapping("/delivery-challans-factory")
    @Timed
    public ResponseEntity<List<Factory>> getAllDeliveryChallansFactory(@Valid @RequestBody DeliveryChallanSearchBean search) {
        log.debug("REST request to get a page of DeliveryChallans");
        String code = "%";
        if (search.getFactCode() != null) {
        	code = search.getFactCode().toUpperCase().trim()+ "%";
        }
        Page<Factory> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("factCode").descending()));
        page = factoryRepository.findAllFactory(code, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-challans-factory");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/delivery-challans-tariff")
    @Timed
    public ResponseEntity<List<Tariff>> getAllDeliveryChallansTariff(@Valid @RequestBody DeliveryChallanSearchBean search) {
        log.debug("REST request to get a page of DeliveryChallans");
        String code = "%";
        if (search.getFactCode() != null) {
        	code = search.getFactCode().toUpperCase().trim()+ "%";
        }
        Page<Tariff> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("code").descending()));
        page = tariffRepository.findAllTariff(code, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-challans-tariff");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @PostMapping("/delivery-challans-partner")
    @Timed
    public ResponseEntity<List<OrderPartnerBean>> getAllDeliveryChallansOrderPartner(@Valid @RequestBody DeliveryChallanSearchBean search) {
        log.debug("REST request to get a page of DeliveryChallans");
        String code = "%";
        if (search.getFactCode() != null) {
        	code = search.getFactCode().toUpperCase().trim()+ "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("CUSTOMERSUPPLIERCODE").descending()));
	    Page<Object[]> page = factoryRepository.findByAllOrderPartner(code,search.getPage());
	    List<OrderPartnerBean> rderPartnerBeanList = new ArrayList<OrderPartnerBean>();
	    for(Object[] objects:page.getContent()) {
	    	OrderPartnerBean bean = new OrderPartnerBean();
	    	bean.setNumberid((objects[0].toString()));
	    	bean.setCustomersuppliercode((objects[1].toString()));
	    	bean.setLegalname1((objects[2].toString()));
	    	rderPartnerBeanList.add(bean);
	    }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/delivery-challans-partner");
        return ResponseEntity.ok().headers(headers).body(rderPartnerBeanList);
    }


    @GetMapping("/delivery-challans-factory/{code}")
    @Timed
    public ResponseEntity<DeliveryChallanBean> getFactoryDeatils(@PathVariable String code) {
    	DeliveryChallanBean deliveryChallanBean =new DeliveryChallanBean();
    	List<Object[]> objects= factoryRepository.findByFactCode(code);
        if(objects!=null && objects.size()>0) {
        	for(Object[] object:objects) {
        		 String tempFAddress="";
        		 deliveryChallanBean.setFactCode(object[0].toString());
                 deliveryChallanBean.setFactDescription(object[1].toString());
                 if(object[2]!=null) {
                	 deliveryChallanBean.setfAddressLine1(object[2].toString());
                	 tempFAddress+=object[2].toString().trim();
                 }
                 if(object[3]!=null){
                	deliveryChallanBean.setfAddressLine2(object[3].toString());
                	tempFAddress+=object[3].toString().trim();
                 }
                 if(object[4]!=null){
                	deliveryChallanBean.setfAddressLine3(object[4].toString());
                	tempFAddress+=object[4].toString().trim();
                 }
                 if(object[5]!=null){
                	deliveryChallanBean.setfAddressLine4(object[5].toString());
                	tempFAddress+=object[5].toString().trim();
                 }
                 if(object[6]!=null){
                    deliveryChallanBean.setfAddressLine5(object[6].toString());
                    tempFAddress+=object[6].toString().trim();
                 }

                 if(object[8]!=null){
                	 deliveryChallanBean.setDistrict(object[8].toString());
                	 tempFAddress+=" "+object[8].toString().trim();
                 }
                 if(object[7]!=null){
                	 deliveryChallanBean.setPostalCode(object[7].toString());
                	 tempFAddress+="("+object[7].toString().trim()+") ";
                 }
                 deliveryChallanBean.setStateCode(object[9].toString());
                 deliveryChallanBean.setfGSTNumber(object[10].toString());
                 tempFAddress+=" GST Number ("+object[10].toString().trim()+")";
                 deliveryChallanBean.setShowAddress1(tempFAddress);
        	}

        }
        return ResponseUtil.wrapOrNotFound(Optional.of(deliveryChallanBean));
    }

    @GetMapping("/delivery-challans-partner/{absuniqueid}")
    @Timed
    public ResponseEntity<DeliveryChallanBean> getOrderPartnerDeatils(@PathVariable String absuniqueid) {
    	DeliveryChallanBean deliveryChallanBean =new DeliveryChallanBean();
    	List<Object[]> objects= factoryRepository.findOrderPartnerBySupplirecode(absuniqueid);
        if(objects!=null && objects.size()>0) {
        	for(Object[] object:objects) {
        		 String tempSAddress="";
        		 deliveryChallanBean.setSuppliercode(object[0].toString());
        		 deliveryChallanBean.setbLegalname1(object[1].toString());
                 if(object[2]!=null) {
                	 deliveryChallanBean.setbAddressLine1(object[2].toString());
                	 tempSAddress+=object[2].toString().trim();
                 }
                 if(object[3]!=null){
                	deliveryChallanBean.setbAddressLine2(object[3].toString());
                	tempSAddress+=object[3].toString().trim();
                 }
                 if(object[4]!=null){
                	deliveryChallanBean.setbAddressLine3(object[4].toString());
                    tempSAddress+=object[4].toString().trim();
                 }
                 if(object[5]!=null){
                	deliveryChallanBean.setbAddressLine4(object[5].toString());
                    tempSAddress+=object[5].toString().trim();
                 }

                 if(object[6]!=null){
                    deliveryChallanBean.setbAddressLine5(object[6].toString());
                    tempSAddress+=object[6].toString().trim();
                 }
                 if(object[8]!=null){
                	 deliveryChallanBean.setbDistrict(object[8].toString());
                	 tempSAddress+=" "+object[8].toString().trim();
                 }
                 if(object[7]!=null){
                	 deliveryChallanBean.setbPostalCode(object[7].toString());
                	 tempSAddress+="("+object[7].toString().trim()+")";
                 }
                 deliveryChallanBean.setbGSTNumber(object[9].toString());
                 tempSAddress+="  GST Number ("+object[9].toString().trim()+")";
                 deliveryChallanBean.setShowAddress2(tempSAddress);
                 deliveryChallanBean.setbStateCode(object[10].toString());
        	}

        }
        return ResponseUtil.wrapOrNotFound(Optional.of(deliveryChallanBean));
    }

    @GetMapping("/delivery-challans-tax/{tarrifcode}/{isState}")
    @Timed
    public ResponseEntity<DeliveryChallanDetails> getDeliveryChallansTax(@PathVariable String tarrifcode,@PathVariable String isState) {
    	DeliveryChallanDetails bean =new DeliveryChallanDetails();
    	Double taxPers;
    	if(isState.equalsIgnoreCase("Y")) {
    		taxPers= factoryRepository.findByTaxCGST(tarrifcode); // If State code is same then CGST And SGST
    	} else {
    		taxPers= factoryRepository.findByTaxIGST(tarrifcode); // else IGST
    	}
        bean.setTaxper(taxPers);
        bean.setTriffCode(tarrifcode);
        return ResponseUtil.wrapOrNotFound(Optional.of(bean));
    }

    /**
     * DELETE  /delivery-challans/:id : delete the "id" deliveryChallan.
     *
     * @param id the id of the deliveryChallan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delivery-challans/{id}")
    @Timed
    public ResponseEntity<Void> deleteDeliveryChallan(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryChallan : {}", id);
        deliveryChallanDetailsRepository.deleteAllByDeliveryChallan(id);
        deliveryChallanRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }


    /**
     * DELETE  /delivery-challans-delete/:id : delete the "id" deliveryChallanDetails.
     *
     * @param id the id of the deliveryChallanDetails to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/delivery-challans-delete/{id}")
    @Timed
    public ResponseEntity<Void> deleteDeliveryChallanDeatils(@PathVariable Long id) {
        log.debug("REST request to delete DeliveryChallanDetails : {}", id);
        deliveryChallanDetailsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/delivery-challans-report/{id}")
    @Timed
    public @ResponseBody void getDeliveryChallansReport(@PathVariable Long id, HttpServletResponse response) throws JRException, IOException {
        List<DeliveryChallanReportBean> list = new ArrayList<>();
        List<Object[]> objects= deliveryChallanRepository.findByDeliveryChallanReport(id);
        for(Object[] object:objects) {
        	DeliveryChallanReportBean bean = new DeliveryChallanReportBean();
        	bean.setFactCode(object[0].toString());
        	bean.setFactDescription(object[1].toString().trim());
        	bean.setShowAddress1(object[2].toString());
        	bean.setfGSTNumber(object[3].toString());
        	bean.setShowAddress2(object[4].toString());
        	bean.setbGSTNumber(object[5].toString());
        	bean.setProductName(object[6].toString());
        	bean.setTriffCode(object[7].toString());
        	bean.setQuantity(Double.parseDouble(object[8].toString()));
        	bean.setRate(Double.parseDouble(object[9].toString()));
        	bean.setAmount(Double.parseDouble(object[10].toString()));
        	bean.setTaxper(Double.parseDouble(object[11].toString()));
        	if(object[12].toString().equalsIgnoreCase("CGST")) {
        		bean.setCgst(Double.parseDouble(object[11].toString())/2);
            	bean.setSgst(Double.parseDouble(object[11].toString())/2);
        	}
        	else {
            	bean.setIgst(Double.parseDouble(object[11].toString()));
        	}
        	bean.setTaxval(Double.parseDouble(object[13].toString()));
        	bean.setTotalAmt(Double.parseDouble(object[14].toString()));
        	bean.setbLegalname1(object[15].toString());
        	bean.setChallanType(object[16].toString());
        	bean.setChallanDate(object[17].toString());
        	if(object[18]!=null) {
        		bean.seteWayBillNo(object[18].toString());
        	}
        	if(object[19]!=null) {
        		bean.seteWayBillDate(object[19].toString());
        	}
        	if(object[20]!=null) {
        		bean.setExpReturnDate(object[20].toString());
        	}
        	if(object[21]!=null) {
        		bean.setRemarks(object[21].toString().trim());
        	}
        	list.add(bean);
        }
        String path = applicationProperties.getTemplatePath()+"jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path+"/DeliveryChallanReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String,Object> parameters = new HashMap<>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(list);
        parameters.put("datasource", jrDataSource);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, jrDataSource);
        response.setContentType("application/x-pdf");
        response.setHeader("Content-Disposition","attachment; filename=DeliveryChallanReport.pdf");
        final OutputStream outputStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
    }

}

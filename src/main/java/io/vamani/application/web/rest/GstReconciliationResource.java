package io.vamani.application.web.rest;

import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.domain.GstGovtUpload;
import io.vamani.application.domain.GstReconciliation;
import io.vamani.application.domain.GstVoplUpload;
import io.vamani.application.model.GstReconciliationBean;
import io.vamani.application.model.Message;
import io.vamani.application.model.ParameterListBean;
import io.vamani.application.repository.GstGovtUploadRepository;
import io.vamani.application.repository.GstReconciliationRepository;
import io.vamani.application.repository.GstVoplUploadRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing
 * {@link io.vamani.application.domain.GstReconciliation}.
 */
@RestController
@RequestMapping("/api")
public class GstReconciliationResource {

	private final Logger log = LoggerFactory.getLogger(GstReconciliationResource.class);

	private static final String ENTITY_NAME = "gstReconciliation";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	private final GstReconciliationRepository gstReconciliationRepository;

	@Autowired
	private GstGovtUploadRepository gstGovtUploadRepository;

	@Autowired
	private GstVoplUploadRepository gstVoplUploadRepository;

	public GstReconciliationResource(GstReconciliationRepository gstReconciliationRepository) {
		this.gstReconciliationRepository = gstReconciliationRepository;
	}

	/**
	 * {@code POST  /gst-reconciliations} : Create a new gstReconciliation.
	 *
	 * @param gstReconciliation the gstReconciliation to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new gstReconciliation, or with status
	 *         {@code 400 (Bad Request)} if the gstReconciliation has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/gst-reconciliations")
	public ResponseEntity<Message> createGstReconciliation(@Valid @RequestBody GstReconciliation[] gstReconciliations)
			throws URISyntaxException {
		int ctr = 0;
		for (GstReconciliation gstReconciliation : gstReconciliations) {
			gstReconciliation.setUnitCode("A");
			gstReconciliation.setInvoiceType("E");
			gstReconciliation.setCreationDate(Instant.now());
			GstReconciliation result = gstReconciliationRepository.save(gstReconciliation);
			GstVoplUpload vopllist = gstVoplUploadRepository.getVoplrow(result.getInvoiceNo(), result.getGstin(),
					result.getSupplierName(), result.getInvoiceAmount(), result.getCgstAmount(), result.getSgstAmount(),
					result.getIgstAmount());
			vopllist.setStatus("1");
			gstVoplUploadRepository.save(vopllist);
			GstGovtUpload govtlist = gstGovtUploadRepository.getAllGovtList(result.getGstin(), result.getInvoiceNo(),
					result.getGovtCgstAmount(), result.getGovtSgstAmount(), result.getGovtIgstAmount(), result.getGovtInvoiceAmount(),
					result.getSupplierName());
			govtlist.setgStatus("1");
			gstGovtUploadRepository.save(govtlist);
			++ctr;
		}
		if (ctr == gstReconciliations.length) {
			return ResponseEntity.ok().body(new Message("success", "Records Save Successfully!"));
		} else {
			return ResponseEntity.ok().body(new Message("error", "Records Not Saved "));
		}
	}

	/**
	 * {@code PUT  /gst-reconciliations} : Updates an existing gstReconciliation.
	 *
	 * @param gstReconciliation the gstReconciliation to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated gstReconciliation, or with status
	 *         {@code 400 (Bad Request)} if the gstReconciliation is not valid, or
	 *         with status {@code 500 (Internal Server Error)} if the
	 *         gstReconciliation couldn't be updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/gst-reconciliations")
	public ResponseEntity<GstReconciliation> updateGstReconciliation(
			@Valid @RequestBody GstReconciliation gstReconciliation) throws URISyntaxException {
		log.debug("REST request to update GstReconciliation : {}", gstReconciliation);
		if (gstReconciliation.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		GstReconciliation result = gstReconciliationRepository.save(gstReconciliation);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
				gstReconciliation.getId().toString())).body(result);
	}

	/**
	 * {@code GET  /gst-reconciliations} : get all the gstReconciliations.
	 *
	 * 
	 * @param pageable the pagination information.
	 * 
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of gstReconciliations in body.
	 */
	@GetMapping("/gst-reconciliations")
	public ResponseEntity<List<GstReconciliation>> getAllGstReconciliations(Pageable pageable) {
		log.debug("REST request to get a page of GstReconciliations");
		Page<GstReconciliation> page = gstReconciliationRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil
				.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	@PostMapping("/gst-reconciliations-check")
	public ResponseEntity<List<GstReconciliationBean>> reconsileData(@Valid @RequestBody ParameterListBean search)
			throws IllegalAccessException, InvocationTargetException {
		log.debug("REST request to get a page of GstReconciliations");
		Double reqDiffernece = search.getParameter1().doubleValue();
		Double reqDifferneceMinus = search.getParameter1().doubleValue() * -1;
		String voplGstin = "%";
		String voplInvoice = "%";
		String voplSupplier = "%";
		if (search.getParameter2() != null) {
			voplGstin = "%" + search.getParameter2().toUpperCase() + "%";
		}
		if (search.getParameter3() != null) {
			voplInvoice = "%" + search.getParameter3().toUpperCase() + "%";
		}
		if (search.getParameter4() != null) {
			voplSupplier = "%" + search.getParameter4().toUpperCase() + "%";
		}
		List<GstVoplUpload> vopllist = gstVoplUploadRepository.getAllVoplListByStatus();
		List<GstGovtUpload> glist = gstGovtUploadRepository.getAllGovtListByParm();
		Map<String, GstGovtUpload> govtMap= new HashMap<>(); 
		for(GstGovtUpload newlist: glist) {
			govtMap.put(newlist.getgGstin()+newlist.getgInvno(), newlist);
		}
		List<GstReconciliationBean> list = new ArrayList<GstReconciliationBean>();
		if (vopllist.size() > 0) {
			for (GstVoplUpload mylist : vopllist) {
				if(govtMap.containsKey(mylist.getvGstin()+mylist.getvInvoiceno())) {
					GstGovtUpload govtlistCount = govtMap.get(mylist.getvGstin()+mylist.getvInvoiceno());
					Double voplValue = mylist.getvCgst() + mylist.getvSgst() + mylist.getvIgst();
					Double govtValue = govtlistCount.getgCgst() + govtlistCount.getgSgst() + govtlistCount.getgIgst();
					if((voplValue - govtValue)>=-reqDiffernece && (voplValue - govtValue)<=reqDiffernece) {
						GstReconciliationBean bean = new GstReconciliationBean();
						Double voplTax = 0.0;
						Double govtTax = 0.0;
						Double taxDifference = 0.0;
						bean.setGstin(mylist.getvGstin());
						bean.setInvoiceNo(mylist.getvInvoiceno());
						bean.setInvoiceDate(mylist.getvInvoicedate());
						bean.setSupplierName(mylist.getvSupplierName());
						bean.setInvoiceAmount(mylist.getvInvamt());
						bean.setCgstAmount(mylist.getvCgst());
						bean.setSgstAmount(mylist.getvSgst());
						bean.setIgstAmount(mylist.getvIgst());
						bean.setGovtCgstAmount(govtlistCount.getgCgst());
						bean.setGovtSgstAmount(govtlistCount.getgSgst());
						bean.setGovtIgstAmount(govtlistCount.getgIgst());
						voplTax = (mylist.getvCgst() + mylist.getvSgst() + mylist.getvIgst());
						govtTax = (govtlistCount.getgCgst() + govtlistCount.getgSgst() + govtlistCount.getgIgst());
						taxDifference = (voplTax - govtTax);
						bean.setDifferenceAmt(taxDifference);
						if (taxDifference != 0) {
							bean.setStatus("2");
						} else {
							bean.setStatus("1");
						}
						// BeanUtils.copyProperties(mylist, bean);
						list.add(bean);
					}
				}
			}
		}
		return ResponseUtil.wrapOrNotFound(Optional.of(list));
	}

	@PostMapping("/gst-reconciliations-misc")
	public ResponseEntity<List<GstReconciliationBean>> reconsileDataMisc(@Valid @RequestBody ParameterListBean search)
			throws IllegalAccessException, InvocationTargetException {
		log.debug("REST request to get a page of GstReconciliations");
		String Gstin = "%";
		String Invoice = "%";
		String Supplier = "%";

		if (search.getParameter2() != null) {
			Gstin = "%" + search.getParameter2().toUpperCase() + "%";
		}
		if (search.getParameter3() != null) {
			Invoice = "%" + search.getParameter3().toUpperCase() + "%";
		}
		if (search.getParameter4() != null) {
			Supplier = "%" + search.getParameter4().toUpperCase() + "%";
		}

		List<Object[]> list1 = gstReconciliationRepository.getMiscList(Gstin, Invoice, Supplier);
		List<GstReconciliationBean> myBean = new ArrayList<GstReconciliationBean>();
		for (Object[] mylist : list1) {
			Object[] objects = (Object[]) mylist;
			GstReconciliationBean bean = new GstReconciliationBean();
			bean.setGstin(objects[1].toString());
			bean.setSupplierName(objects[2].toString());
			bean.setInvoiceNo(objects[3].toString());
			if (objects[0].toString().contains("VOPL")) {
				bean.setInvoiceAmount(Double.parseDouble(objects[5].toString()));
			} else {
				bean.setGovtInvoiceAmount(Double.parseDouble(objects[5].toString()));
			}
			if (objects[0].toString().contains("VOPL")) {
				bean.setCgstAmount(Double.parseDouble(objects[6].toString()));
			} else {
				bean.setGovtCgstAmount(Double.parseDouble(objects[6].toString()));
			}
			if (objects[0].toString().contains("VOPL")) {
				bean.setSgstAmount(Double.parseDouble(objects[7].toString()));
			} else {
				bean.setGovtSgstAmount(Double.parseDouble(objects[7].toString()));
			}
			if (objects[0].toString().contains("VOPL")) {
				bean.setIgstAmount(Double.parseDouble(objects[8].toString()));
			} else {
				bean.setGovtIgstAmount(Double.parseDouble(objects[8].toString()));
			}
			// BeanUtils.copyProperties(mylist, bean);
			myBean.add(bean);
		}
		return ResponseUtil.wrapOrNotFound(Optional.of(myBean));
	}

	/**
	 * {@code GET  /gst-reconciliations/:id} : get the "id" gstReconciliation.
	 *
	 * @param id the id of the gstReconciliation to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the gstReconciliation, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/gst-reconciliations/{id}")
	public ResponseEntity<GstReconciliation> getGstReconciliation(@PathVariable Long id) {
		log.debug("REST request to get GstReconciliation : {}", id);
		Optional<GstReconciliation> gstReconciliation = gstReconciliationRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(gstReconciliation);
	}

	/**
	 * {@code DELETE  /gst-reconciliations/:id} : delete the "id" gstReconciliation.
	 *
	 * @param id the id of the gstReconciliation to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/gst-reconciliations/{id}")
	public ResponseEntity<Void> deleteGstReconciliation(@PathVariable Long id) {
		log.debug("REST request to delete GstReconciliation : {}", id);
		gstReconciliationRepository.deleteById(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}
}

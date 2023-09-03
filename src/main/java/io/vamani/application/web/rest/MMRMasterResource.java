package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.JobProfile;
import io.vamani.application.domain.MMRMaster;
import io.vamani.application.model.*;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.JobProfileRepository;
import io.vamani.application.repository.MMRMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

/**
 * REST controller for managing MMRMaster.
 */
@RestController
@RequestMapping("/api")
public class MMRMasterResource {

    private final Logger log = LoggerFactory.getLogger(MMRMasterResource.class);

    private static final String ENTITY_NAME = "mMRMaster";

    private final MMRMasterRepository mMRMasterRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;
    
    @Autowired
    private JobProfileRepository jobProfileRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public MMRMasterResource(MMRMasterRepository mMRMasterRepository) {
        this.mMRMasterRepository = mMRMasterRepository;
    }

    /**
     * POST  /mmr-masters : Create a new mMRMaster.
     *
     * @param mMRMaster the mMRMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mMRMaster, or with status 400 (Bad Request) if the mMRMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws ParseException 
     */
    @PostMapping("/mmr-masters")
    @Timed
    public ResponseEntity<MMRMaster> createMMRMaster(@Valid @RequestBody MMRMasterBean mMRMasterBean) throws URISyntaxException, ParseException {
        log.debug("REST request to save MMRMaster : {}", mMRMasterBean);
        Instant currentDate = mMRMasterBean.getMonthYear();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(currentDate));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        currentDate = calendar.toInstant();
        List<MMRMaster> mmrMasterList = mMRMasterRepository.findAllByMonthYear(mMRMasterBean.getFactory(),currentDate);

        MMRMaster result =null;
        if(mmrMasterList.size()>0) {
            MMRMaster masterBean=mmrMasterList.get(0);
        	for(MMRDepartmentBean bean:mMRMasterBean.getMmrDepartmentBean()) {
            	for(MMRDesignationBean bean2:bean.getMmrDesignationBean()) {
            		 BeanUtils.copyProperties(mMRMasterBean, masterBean);
            	     masterBean.setMonthYear(currentDate);
            	     BeanUtils.copyProperties(bean, masterBean);
            		 BeanUtils.copyProperties(bean2, masterBean);
            		 masterBean.setFactory(masterBean.getFactory());
            		 masterBean.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            		 masterBean.setLastUpdatedDate(Instant.now());
            		 result = mMRMasterRepository.save(masterBean);
            	}
            }
        }else {
        	for(MMRDepartmentBean bean:mMRMasterBean.getMmrDepartmentBean()) {
            	for(MMRDesignationBean bean2:bean.getMmrDesignationBean()) {
            		 MMRMaster mMRMaster =new MMRMaster();
            		 BeanUtils.copyProperties(mMRMasterBean, mMRMaster);
            		 mMRMaster.setMonthYear(currentDate);
            	     BeanUtils.copyProperties(bean, mMRMaster);
            		 BeanUtils.copyProperties(bean2, mMRMaster);
            		 mMRMaster.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            		 mMRMaster.setCreatedDate(Instant.now());
            		 result = mMRMasterRepository.save(mMRMaster);
            	}
            }
        }
        return ResponseEntity.created(new URI("/api/mmr-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mmr-masters : Updates an existing mMRMaster.
     *
     * @param mMRMaster the mMRMaster to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mMRMaster,
     * or with status 400 (Bad Request) if the mMRMaster is not valid,
     * or with status 500 (Internal Server Error) if the mMRMaster couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mmr-masters")
    @Timed
    public ResponseEntity<MMRMaster> updateMMRMaster(@Valid @RequestBody MMRMaster mMRMaster) throws URISyntaxException {
        log.debug("REST request to update MMRMaster : {}", mMRMaster);
        if (mMRMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MMRMaster result = mMRMasterRepository.save(mMRMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mMRMaster.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mmr-masters : get all the mMRMasters.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mMRMasters in body
     */
    @GetMapping("/mmr-masters")
    @Timed
    public ResponseEntity<List<MMRMaster>> getAllMMRMasters(Pageable pageable) {
        log.debug("REST request to get a page of MMRMasters");
        Pageable pageable1 = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(
            new Sort.Order(Sort.Direction.DESC, "monthYear"),
            new Sort.Order(Sort.Direction.ASC, "factory")));
        Page<Object[]> list=mMRMasterRepository.findAllByFactory(pageable1);
        List<MMRMaster> mmrMasterList= new ArrayList<MMRMaster>();
        for(Object[] objects:list.getContent()) {
        	MMRMaster bean=new MMRMaster();
        	bean.setFactory(objects[0].toString());
        	bean.setMonthYear((Instant) objects[1]);
        	mmrMasterList.add(bean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(list, "/api/mmr-masters");
        return ResponseEntity.ok().headers(headers).body(mmrMasterList);
    }

    /**
     * GET  /mmr-masters/:id : get the "id" mMRMaster.
     *
     * @param id the id of the mMRMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mMRMaster, or with status 404 (Not Found)
     * @throws ParseException
     */
    @PostMapping("/mmr-masters-custom")
    @Timed
    public ResponseEntity<MMRMasterBean> getMMRMasterDetails(@Valid @RequestBody MMRSearchBean search) throws ParseException {
        log.debug("REST request to get MMRMaster : {}", search);
        MMRMasterBean mmrMasterBean = new MMRMasterBean();
        mmrMasterBean.setFactory(search.getFactory());
        mmrMasterBean.setMonthYear(search.getMonthYear());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(search.getMonthYear()));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        search.setMonthYear(calendar.toInstant());
        List<MMRMaster> mmrMasterList = mMRMasterRepository.findAllByMonthYear(search.getFactory(), search.getMonthYear());
        if (mmrMasterList != null && mmrMasterList.size() > 0) {
            mmrMasterBean.setExist(true);
        } else {
            mmrMasterBean.setExist(false);
        }
        List<JobProfile> jobProfile = jobProfileRepository.findAllByFactory(search.getFactory());
        List<MMRDepartmentBean> mmrDepartmentList = new ArrayList<MMRDepartmentBean>();
        Map<String, MMRDepartmentBean> mapDepartment = new LinkedHashMap<>();
        if (mmrMasterList.size() > 0) {
            Map<String, JobProfile> mapDesignation = new LinkedHashMap<>();
            for (JobProfile profile : jobProfile) {
                mapDesignation.put(profile.getDesignation(), profile);
            }

            for (MMRMaster mmrMaster : mmrMasterList) {
                if (mapDepartment.containsKey(mmrMaster.getDepartment())) {
                    MMRDepartmentBean bean = mapDepartment.get(mmrMaster.getDepartment());
                    JobProfile profileBean = mapDesignation.get(mmrMaster.getDesignation());
                    List<MMRDesignationBean> mmrDesignationBeanList = bean.getMmrDesignationBean();
                    MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
                    BeanUtils.copyProperties(profileBean, mmrDesignationBean);
                    BeanUtils.copyProperties(mmrMaster, mmrDesignationBean);
                    mmrDesignationBeanList.add(mmrDesignationBean);
                    bean.setMmrDesignationBean(mmrDesignationBeanList);
                    mapDepartment.put(mmrMaster.getDepartment(), bean);
                } else {
                    MMRDepartmentBean bean = new MMRDepartmentBean();
                    JobProfile profileBean = mapDesignation.get(mmrMaster.getDesignation());
                    BeanUtils.copyProperties(profileBean, bean);
                    BeanUtils.copyProperties(mmrMaster, bean);
                    List<MMRDesignationBean> mmrDesignationBeanList = new ArrayList<>();
                    MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
                    BeanUtils.copyProperties(profileBean, mmrDesignationBean);
                    BeanUtils.copyProperties(mmrMaster, mmrDesignationBean);
                    mmrDesignationBeanList.add(mmrDesignationBean);
                    bean.setMmrDesignationBean(mmrDesignationBeanList);
                    mapDepartment.put(mmrMaster.getDepartment(), bean);
                }
            }
        } else {
            for (JobProfile profile : jobProfile) {
                if (mapDepartment.containsKey(profile.getDepartment())) {
                    MMRDepartmentBean bean = mapDepartment.get(profile.getDepartment());
                    List<MMRDesignationBean> mmrDesignationBeanList = bean.getMmrDesignationBean();
                    MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
                    BeanUtils.copyProperties(profile, mmrDesignationBean);
                    mmrDesignationBeanList.add(mmrDesignationBean);
                    bean.setMmrDesignationBean(mmrDesignationBeanList);
                    mapDepartment.put(profile.getDepartment(), bean);
                } else {
                    MMRDepartmentBean bean = new MMRDepartmentBean();
                    BeanUtils.copyProperties(profile, bean);
                    List<MMRDesignationBean> mmrDesignationBeanList = new ArrayList<>();
                    MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
                    BeanUtils.copyProperties(profile, mmrDesignationBean);
                    mmrDesignationBeanList.add(mmrDesignationBean);
                    bean.setMmrDesignationBean(mmrDesignationBeanList);
                    mapDepartment.put(profile.getDepartment(), bean);
                }
            }
        }
        for (MMRDepartmentBean department : mapDepartment.values()) {
            if (department.getMmrDesignationBean() != null && department.getMmrDesignationBean().size() > 1) {
                Collections.sort(department.getMmrDesignationBean(),
                    Comparator.comparing(MMRDesignationBean::getDesignationDesc)
                        .thenComparing(MMRDesignationBean::getDesignation));
            }
            mmrDepartmentList.add(department);
        }

        if (mmrDepartmentList != null && mmrDepartmentList.size() > 0) {
            Collections.sort(mmrDepartmentList,
                Comparator.comparing(MMRDepartmentBean::getDepartmentDesc)
                    .thenComparing(MMRDepartmentBean::getDepartment));
        }

        mmrMasterBean.setMmrDepartmentBean(mmrDepartmentList);
        return ResponseUtil.wrapOrNotFound(Optional.of(mmrMasterBean));
    }

    /**
     * GET  /mmr-masters/:id : get the "id" mMRMaster.
     *
     * @param id the id of the mMRMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mMRMaster, or with status 404 (Not Found)
     * @throws ParseException
     */
    @PostMapping("/mmr-masters-custom-copy")
    @Timed
    public ResponseEntity<MMRMasterBean> getMMRMasterDetailsCopy(@Valid @RequestBody MMRSearchBean search) throws ParseException {
        log.debug("REST request to get MMRMaster : {}", search);
        MMRMasterBean mmrMasterBean = new MMRMasterBean();
        mmrMasterBean.setFactory(search.getFactory());
        mmrMasterBean.setMonthYear(search.getMonthYear());
        List<MMRMaster> mmrMasterList = mMRMasterRepository.findAllMaxDetails(search.getFactory(), search.getFactory());
        if(mmrMasterList.size()>0) {
            mmrMasterBean.setExist(true);
        } else {
            mmrMasterBean.setExist(false);
        }
        List<JobProfile> jobProfile = jobProfileRepository.findAllByFactory(search.getFactory());
        List<MMRDepartmentBean> mmrDepartmentList = new ArrayList<MMRDepartmentBean>();
        Map<String, MMRDepartmentBean> mapDepartment = new LinkedHashMap<>();
        if (mmrMasterList.size() > 0) {
            Map<String, JobProfile> mapDesignation = new LinkedHashMap<>();
            for (JobProfile profile : jobProfile) {
                mapDesignation.put(profile.getDesignation(), profile);
            }

            for (MMRMaster mmrMaster : mmrMasterList) {
                if (mapDepartment.containsKey(mmrMaster.getDepartment())) {
                    MMRDepartmentBean bean = mapDepartment.get(mmrMaster.getDepartment());
                    JobProfile profileBean = mapDesignation.get(mmrMaster.getDesignation());
                    List<MMRDesignationBean> mmrDesignationBeanList = bean.getMmrDesignationBean();
                    MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
                    BeanUtils.copyProperties(profileBean, mmrDesignationBean);
                    BeanUtils.copyProperties(mmrMaster, mmrDesignationBean);
                    mmrDesignationBean.setId(null);
                    mmrDesignationBeanList.add(mmrDesignationBean);
                    bean.setMmrDesignationBean(mmrDesignationBeanList);
                    mapDepartment.put(mmrMaster.getDepartment(), bean);
                } else {
                    MMRDepartmentBean bean = new MMRDepartmentBean();
                    JobProfile profileBean = mapDesignation.get(mmrMaster.getDesignation());
                    BeanUtils.copyProperties(profileBean, bean);
                    BeanUtils.copyProperties(mmrMaster, bean);
                    List<MMRDesignationBean> mmrDesignationBeanList = new ArrayList<>();
                    MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
                    BeanUtils.copyProperties(profileBean, mmrDesignationBean);
                    BeanUtils.copyProperties(mmrMaster, mmrDesignationBean);
                    mmrDesignationBean.setId(null);
                    mmrDesignationBeanList.add(mmrDesignationBean);
                    bean.setMmrDesignationBean(mmrDesignationBeanList);
                    mapDepartment.put(mmrMaster.getDepartment(), bean);
                }
            }
        } else {
            for (JobProfile profile : jobProfile) {
                if (mapDepartment.containsKey(profile.getDepartment())) {
                    MMRDepartmentBean bean = mapDepartment.get(profile.getDepartment());
                    List<MMRDesignationBean> mmrDesignationBeanList = bean.getMmrDesignationBean();
                    MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
                    BeanUtils.copyProperties(profile, mmrDesignationBean);
                    mmrDesignationBean.setId(null);
                    mmrDesignationBeanList.add(mmrDesignationBean);
                    bean.setMmrDesignationBean(mmrDesignationBeanList);
                    mapDepartment.put(profile.getDepartment(), bean);
                } else {
                    MMRDepartmentBean bean = new MMRDepartmentBean();
                    BeanUtils.copyProperties(profile, bean);
                    List<MMRDesignationBean> mmrDesignationBeanList = new ArrayList<>();
                    MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
                    BeanUtils.copyProperties(profile, mmrDesignationBean);
                    mmrDesignationBean.setId(null);
                    mmrDesignationBeanList.add(mmrDesignationBean);
                    bean.setMmrDesignationBean(mmrDesignationBeanList);
                    mapDepartment.put(profile.getDepartment(), bean);
                }
            }
        }
        for (MMRDepartmentBean department : mapDepartment.values()) {
            if (department.getMmrDesignationBean() != null && department.getMmrDesignationBean().size() > 1) {
                Collections.sort(department.getMmrDesignationBean(),
                    Comparator.comparing(MMRDesignationBean::getDesignationDesc)
                        .thenComparing(MMRDesignationBean::getDesignation));
            }
            mmrDepartmentList.add(department);
        }

        if (mmrDepartmentList != null && mmrDepartmentList.size() > 0) {
            Collections.sort(mmrDepartmentList,
                Comparator.comparing(MMRDepartmentBean::getDepartmentDesc)
                    .thenComparing(MMRDepartmentBean::getDepartment));
        }

        mmrMasterBean.setMmrDepartmentBean(mmrDepartmentList);
        return ResponseUtil.wrapOrNotFound(Optional.of(mmrMasterBean));
    }
	
    @PostMapping("/mmr-masters-no")
	@Timed
	public ResponseEntity<MMRMasterBean> getNotOverride(@Valid @RequestBody MMRSearchBean search) throws ParseException {
		log.debug("REST request to get MMRMaster : {}", search);
		MMRMasterBean mMRMaster = new MMRMasterBean();
		List<JobProfile> jobProfile = jobProfileRepository.findAllByFactory(search.getFactory());
		List<MMRDepartmentBean> mmrDepartmentList = new ArrayList<MMRDepartmentBean>();
		Map<String, MMRDepartmentBean> map = new HashMap<>();
		for (JobProfile profile : jobProfile) {
			if (map.containsKey(profile.getDepartment())) {
				MMRDepartmentBean bean = map.get(profile.getDepartment());
				List<MMRDesignationBean> mmrDesignationBeanList = bean.getMmrDesignationBean();
				MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
				BeanUtils.copyProperties(profile, mmrDesignationBean);
				mmrDesignationBeanList.add(mmrDesignationBean);
				bean.setMmrDesignationBean(mmrDesignationBeanList);
				map.put(profile.getDepartment(), bean);
			} else {
				MMRDepartmentBean bean = new MMRDepartmentBean();
				BeanUtils.copyProperties(profile, bean);
				List<MMRDesignationBean> mmrDesignationBeanList = new ArrayList<>();
				MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
				BeanUtils.copyProperties(profile, mmrDesignationBean);
				mmrDesignationBeanList.add(mmrDesignationBean);
				bean.setMmrDesignationBean(mmrDesignationBeanList);
				map.put(profile.getDepartment(), bean);
			}
		}
		for (MMRDepartmentBean department : map.values()) {
            if (department.getMmrDesignationBean() != null && department.getMmrDesignationBean().size() > 1) {
                Collections.sort(department.getMmrDesignationBean(),
                    Comparator.comparing(MMRDesignationBean::getDesignationDesc)
                        .thenComparing(MMRDesignationBean::getDesignation));
            }
			 mmrDepartmentList.add(department);
		}

        if (mmrDepartmentList != null && mmrDepartmentList.size() > 0) {
            Collections.sort(mmrDepartmentList,
                Comparator.comparing(MMRDepartmentBean::getDepartmentDesc)
                    .thenComparing(MMRDepartmentBean::getDepartment));
        }

		mMRMaster.setMmrDepartmentBean(mmrDepartmentList);
		return ResponseUtil.wrapOrNotFound(Optional.of(mMRMaster));
	}
	
	
	 /**
     * POST  /mmr-masters : Create a new mMRMaster.
     *
     * @param mMRMaster the mMRMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mMRMaster, or with status 400 (Bad Request) if the mMRMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws ParseException 
     */
    @PostMapping("/mmr-masters-edit")
    @Timed
    public ResponseEntity<MMRMasterBean> getMMRMasterByEdit(@Valid @RequestBody MMRSearchBean search) throws ParseException {
        log.debug("REST request to save MMRMaster : {}", search);
        MMRMasterBean mMRMaster = new MMRMasterBean();

        Instant currentDate = search.getMonthYear();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date.from(currentDate));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        currentDate = calendar.toInstant();
		List<MMRMaster> mmrMasterList = mMRMasterRepository.findAllByMonthYear(search.getFactory(),currentDate);
		List<JobProfile> jobProfile = jobProfileRepository.findAllByFactory(search.getFactory());
		Map<String, JobProfile> map2 = new LinkedHashMap<>();
		for(JobProfile profile:jobProfile) {
			map2.put(profile.getDesignation(), profile);
		}
		if(mmrMasterList.size()>0) {
			MMRMaster mmrBean=mmrMasterList.get(0);
			mMRMaster.setFactory(mmrBean.getFactory());
			mMRMaster.setMonthYear(mmrBean.getMonthYear());
		}
		List<MMRDepartmentBean> mmrDepartmentList = new ArrayList<MMRDepartmentBean>();
		Map<String, MMRDepartmentBean> map = new LinkedHashMap<>();
		for (MMRMaster mmrMasterBean : mmrMasterList) {
			if (map.containsKey(mmrMasterBean.getDepartment())) {
				MMRDepartmentBean bean = map.get(mmrMasterBean.getDepartment());
				JobProfile profileBean=map2.get(mmrMasterBean.getDesignation());
				List<MMRDesignationBean> mmrDesignationBeanList = bean.getMmrDesignationBean();
				MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
				BeanUtils.copyProperties(profileBean, mmrDesignationBean);
				BeanUtils.copyProperties(mmrMasterBean, mmrDesignationBean);
				mmrDesignationBeanList.add(mmrDesignationBean);
				bean.setMmrDesignationBean(mmrDesignationBeanList);
				map.put(mmrMasterBean.getDepartment(), bean);
			} else {
				MMRDepartmentBean bean = new MMRDepartmentBean();
				JobProfile profileBean=map2.get(mmrMasterBean.getDesignation());
				BeanUtils.copyProperties(profileBean, bean);
				BeanUtils.copyProperties(mmrMasterBean, bean);
				List<MMRDesignationBean> mmrDesignationBeanList = new ArrayList<>();
				MMRDesignationBean mmrDesignationBean = new MMRDesignationBean();
				BeanUtils.copyProperties(profileBean, mmrDesignationBean);
				BeanUtils.copyProperties(mmrMasterBean, mmrDesignationBean);
				mmrDesignationBeanList.add(mmrDesignationBean);
				bean.setMmrDesignationBean(mmrDesignationBeanList);
				map.put(mmrMasterBean.getDepartment(), bean);
			}
		}
		for (MMRDepartmentBean department : map.values()) {
            if (department.getMmrDesignationBean() != null && department.getMmrDesignationBean().size() > 1) {
                Collections.sort(department.getMmrDesignationBean(),
                    Comparator.comparing(MMRDesignationBean::getDesignationDesc)
                        .thenComparing(MMRDesignationBean::getDesignation));
            }
			mmrDepartmentList.add(department);
		}

        if (mmrDepartmentList != null && mmrDepartmentList.size() > 0) {
            Collections.sort(mmrDepartmentList,
                Comparator.comparing(MMRDepartmentBean::getDepartmentDesc)
                    .thenComparing(MMRDepartmentBean::getDepartment));
        }

		mMRMaster.setMmrDepartmentBean(mmrDepartmentList);
        return ResponseUtil.wrapOrNotFound(Optional.of(mMRMaster));
    }
    
    /**
     * GET  /mmr-masters/:id : get the "id" mMRMaster.
     *
     * @param id the id of the mMRMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mMRMaster, or with status 404 (Not Found)
     */
    @GetMapping("/mmr-masters/{id}")
    @Timed
    public ResponseEntity<MMRMaster> getMMRMaster(@PathVariable Long id) {
        log.debug("REST request to get MMRMaster : {}", id);
        Optional<MMRMaster> mMRMaster = mMRMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mMRMaster);
    }


    /**
     * DELETE  /mmr-masters/:id : delete the "id" mMRMaster.
     *
     * @param id the id of the mMRMaster to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mmr-masters/{id}")
    @Timed
    public ResponseEntity<Void> deleteMMRMaster(@PathVariable Long id) {
        log.debug("REST request to delete MMRMaster : {}", id);
        mMRMasterRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * Get Employee Present and absent MMR Detail report Based on Date and factory
     * code
     *
     * @param mmrReportDate and Factory Code
     * @param response
     * @throws JRException
     * @throws IOException
     * @throws ParseException
     */
    @PostMapping("/mmr-report-detail")
    @Timed
    public @ResponseBody void getMmrMasterReportByDate(@Valid @RequestBody(required = false) MmrReport search, HttpServletResponse response) throws JRException, IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");
        ArrayList<MmrReport> mmrReportList = new ArrayList<MmrReport>();
        if (search.getDateFrom() != null && search.getDateTo() != null && search.getFactoryCode() != null) {
            Map<String, MMRMaster> map = new HashMap<>();
            List<MMRMaster> mmRMasterList = mMRMasterRepository.GetDeptCodeByDate(search.getFactoryCode().trim(), search.getDateFrom(), search.getDateTo());

            for (MMRMaster master : mmRMasterList) {
                map.put(format.format(java.util.Date.from(master.getMonthYear())) + "D" + master.getDepartment() + "D" + master.getDesignation(), master);
            }

            String factDesc = "";
            Map<String, MmrReport> mmrMasterMap = new HashMap<>();
            List<Object[]> objectList = employeeViewRepository.GetAllMMRAttnByDeptCode(search.getDateFrom(), search.getDateTo(), search.getFactoryCode());
            for (Object obj : objectList) {
                Object[] objects = (Object[]) obj;
                factDesc =  objects[10].toString();
                java.util.Date date = java.util.Date.from(((Timestamp) objects[0]).toInstant());
                MmrReport bean = new MmrReport();
                bean.setDayno(format2.format(date));
                bean.setDepCodeDesc(objects[2].toString());
                bean.setDesCodeDesc(objects[4].toString());
                bean.setSubSname(objects[5].toString());
                bean.setPcountOnroll(Double.parseDouble(objects[6].toString()));
                bean.setAbcountOnroll(Double.parseDouble(objects[7].toString()));
                bean.setPcountCutpic(Double.parseDouble(objects[8].toString()));
                bean.setAbcountCutpic(Double.parseDouble(objects[9].toString()));
                bean.setFactoryCode(objects[10].toString());
                mmrMasterMap.put(format.format(date) + "D" + objects[1].toString() + "D" + objects[3].toString(), bean);
            }

            for (String key : map.keySet()) {
                MMRMaster master = map.get(key);
                if (mmrMasterMap.containsKey(key)) {
                    MmrReport bean = mmrMasterMap.get(key);
                    if (master.getSalary() != null) {
                        bean.setSalary(master.getSalary());
                    } else {
                        bean.setSalary(0.0);
                    }
                    if (master.getPcsRate() != null) {
                        bean.setPcsRate(master.getPcsRate());
                    } else {
                        bean.setPcsRate(0.0);
                    }
                    if (master != null && master.getSwCode().equalsIgnoreCase("2")) {
                        bean.setSwcode("STAFF");
                    } else {
                        bean.setSwcode("WORKER");
                    }
                    if (master.getSalary() != null && master.getSalary() > 0) {
                        double percentage = (bean.getPcountOnroll() * 100) / master.getSalary();
                        bean.setPcountOnrollPrs(percentage);
                    } else {
                        bean.setPcountOnrollPrs(0.0);
                    }

                    if (master.getSalary() != null && master.getSalary() > 0) {
                        double percentage = (bean.getAbcountOnroll() * 100) / master.getSalary();
                        bean.setAbcountOnrollPrs(percentage);
                    } else {
                        bean.setAbcountOnrollPrs(0.0);
                    }

                    if (master.getPcsRate() != null && master.getPcsRate() > 0) {
                        double percentage = (bean.getPcountCutpic() * 100) / master.getPcsRate();
                        bean.setPcountCutpicPrs(percentage);
                    } else {
                        bean.setPcountCutpicPrs(0.0);
                    }

                    if (master.getPcsRate() != null && master.getPcsRate() > 0) {
                        double percentage = (bean.getAbcountCutpic() * 100) / master.getPcsRate();
                        bean.setAbcountCutpicPrs(percentage);
                    } else {
                        bean.setAbcountCutpicPrs(0.0);
                    }
                    mmrReportList.add(bean);
                } else {
                    MmrReport bean = new MmrReport();
                    bean.setDayno(format2.format(Date.from(master.getMonthYear())));
                    if (master.getSalary() != null) {
                        bean.setSalary(master.getSalary());
                        bean.setAbcountOnrollPrs(100.0);
                    } else {
                        bean.setSalary(0.0);
                        bean.setAbcountOnrollPrs(0.0);
                    }
                    if (master.getPcsRate() != null) {
                        bean.setPcsRate(master.getPcsRate());
                        bean.setAbcountCutpicPrs(100.0);
                    } else {
                        bean.setPcsRate(0.0);
                        bean.setAbcountCutpicPrs(0.0);
                    }
                    if (master != null && master.getSwCode().equalsIgnoreCase("2")) {
                        bean.setSwcode("STAFF");
                    } else {
                        bean.setSwcode("WORKER");
                    }

                    bean.setPcountOnrollPrs(0.0);
                    bean.setPcountCutpicPrs(0.0);
                    JobProfile jobProfile = jobProfileRepository.getDistinctByDepartmentAndDesignationAndSwCode(master.getDepartment(), master.getDesignation(), master.getSwCode(), master.getFactory());
                    bean.setDepCodeDesc(jobProfile.getDepartmentDesc());
                    bean.setDesCodeDesc(jobProfile.getDesignationDesc());
                    bean.setFactoryCode(factDesc);
                    mmrReportList.add(bean);
                }
            }
        }

        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/MmrReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(mmrReportList);
        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=MmrReport.xlsx");
        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }
}

package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.db2.model.ResourcesBean;
import io.vamani.application.domain.*;
import io.vamani.application.model.LeaveMasterBean;
import io.vamani.application.model.ManpowerBudgetingBean;
import io.vamani.application.mssql.model.ManpowerBean;
import io.vamani.application.repository.ManpowerBudgetEntryRepository;
import io.vamani.application.repository.ManpowerTypeMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;

/**
 * REST controller for managing MachineMaster.
 */
@RestController
@RequestMapping("/api")
public class ManpowerEntryResource {
    private final Logger log = LoggerFactory.getLogger(ManpowerEntryResource.class);

    private static final String ENTITY_NAME = "manpowerEntry";

    @Autowired
    private ManpowerTypeMasterRepository manpowerTypeMasterRepository;

    @Autowired
    private ManpowerBudgetEntryRepository manpowerBudgetEntryRepository;

    /**
     * {@code GET  /marker-master-entries} : get all the markerMasterEntries.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of markerMasterEntries in body.
     */
    @GetMapping("/manpower-type-masters")
    public List<ManpowerTypeMaster> getAllManpowerTypeMasters() {
        log.debug("REST request to get a page of MarkerMasterEntries");
        return manpowerTypeMasterRepository.findAll();
    }


    @PostMapping("/manpower-budgetings")
    @Timed
    public ResponseEntity<ManpowerBudgetingBean> createManpowerBudgetEntry(@Valid @RequestBody ManpowerBudgetingBean manpowerBudgeting) throws URISyntaxException, ParseException {
        if (manpowerBudgeting != null) {
            Instant now  = Instant.now();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Instant attendanceDate = simpleDateFormat.parse(simpleDateFormat.format(Date.from(manpowerBudgeting.getDateFrom()))).toInstant();
            manpowerBudgetEntryRepository.deleteManpowerBudgetEntryBy(manpowerBudgeting.getFactoryCode(), manpowerBudgeting.getDepartmentCode(), attendanceDate, manpowerBudgeting.getType());
            for (ManpowerBean manpowerBean : manpowerBudgeting.getManpowerBeans()) {
                for (ResourcesBean resourcesBean : manpowerBean.getResources()) {
                    ManpowerBudgetEntry manpowerBudgetEntry = new ManpowerBudgetEntry();

                    manpowerBudgetEntry.setId(new ManpowerBudgetEntryId(manpowerBudgeting.getFactoryCode(), manpowerBudgeting.getDepartmentCode(), attendanceDate, manpowerBudgeting.getType(), manpowerBean.getDesCode(), manpowerBean.getSdepCode(), manpowerBean.getCatCode(), resourcesBean.getCode()));
                    manpowerBudgetEntry.setNowFactCode(manpowerBudgeting.getNowFactoryName());
                    manpowerBudgetEntry.setFactDesc(manpowerBudgeting.getFactoryName());
                    manpowerBudgetEntry.setDeptDesc(manpowerBudgeting.getDepartmentName());
                    manpowerBudgetEntry.setDesgDesc(manpowerBean.getDesCodeDesc());
                    manpowerBudgetEntry.setSubdesgDesc(manpowerBean.getSubDeptDesc());
                    manpowerBudgetEntry.setSkillDesc(manpowerBean.getCatName());
                    manpowerBudgetEntry.setPresentCount(manpowerBean.getEmployeeCount().longValue());
                    manpowerBudgetEntry.setActualPresentCount(manpowerBean.getEmployeeCount().longValue());
                    manpowerBudgetEntry.setBalanaceCount(manpowerBean.getEmployeeBalance().longValue());
                    manpowerBudgetEntry.setLineDesc(resourcesBean.getLongdescription());
                    manpowerBudgetEntry.setLineCount(resourcesBean.getResourceAllocate());
                    manpowerBudgetEntry.setFlag("E");
                    manpowerBudgetEntry.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                    manpowerBudgetEntry.setCreatedDate(now);
                    manpowerBudgetEntryRepository.save(manpowerBudgetEntry);
                }
            }
        }
        return ResponseEntity.ok().body(manpowerBudgeting);
    }
}

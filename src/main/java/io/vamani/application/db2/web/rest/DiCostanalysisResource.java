package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.repository.DiCostanalysisAverageRepository;
import io.vamani.application.db2.repository.DiCostanalysisRepository;
import io.vamani.application.db2.repository.DivisionRepository;
import io.vamani.application.db2.repository.ViewcostanalysisselectionicsRepository;
import io.vamani.application.model.Master;
import io.vamani.application.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for managing Division.
 */
@RestController
@RequestMapping("/api")
public class DiCostanalysisResource {
    private final Logger log = LoggerFactory.getLogger(DiCostanalysisResource.class);

    private static final String ENTITY_NAME = "division";

    private final DiCostanalysisRepository diCostanalysisRepository;

    private final DiCostanalysisAverageRepository diCostanalysisAverageRepository;

    @Autowired
    private ViewcostanalysisselectionicsRepository viewcostanalysisselectionicsRepository;

    public DiCostanalysisResource(DiCostanalysisRepository diCostanalysisRepository, DiCostanalysisAverageRepository diCostanalysisAverageRepository)
    {
        this.diCostanalysisRepository = diCostanalysisRepository;
        this.diCostanalysisAverageRepository = diCostanalysisAverageRepository;
    }

    /**
     * GET  /company-masters/:id : get the "id" companyMaster.
     *
     * @param id the id of the companyMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companyMaster, or with status 404 (Not Found)
     */
    @PostMapping("/di-costanalysis-fetch")
    @Timed
    public List<DiCostanalysis> getDiCostanalysis(@RequestBody Master master) {
        log.debug("REST request to get projectcode : {}", master.getCode());
        List<DiCostanalysis> diCostanalyses = new ArrayList<>();
        List<Object[]> objects = viewcostanalysisselectionicsRepository.findAllByProjectcode(master.getCode().trim().toUpperCase());
        if (objects != null) {
            for (Object[] object : objects) {
                DiCostanalysisId diCostanalysisId = new DiCostanalysisId();
                diCostanalysisId.setProjectcode(master.getCode().trim().toUpperCase());
                diCostanalysisId.setFatherproductcode(object[0].toString());
                diCostanalysisId.setWorkattributes(object[1].toString());
                diCostanalysisId.setItemnature(object[2].toString());
                diCostanalysisId.setItemtypecode(object[3].toString());
                diCostanalysisId.setItemsubcode01(object[4].toString());
                diCostanalysisId.setItemsubcode02(object[5] != null ? object[5].toString() : null);
                diCostanalysisId.setItemsubcode03(object[6] != null ? object[6].toString() : null);
                diCostanalysisId.setItemsubcode04(object[7] != null ? object[7].toString() : null);
                diCostanalysisId.setItemsubcode05(object[8] != null ? object[8].toString() : null);
                diCostanalysisId.setItemsubcode06(object[9] != null ? object[9].toString() : null);
                diCostanalysisId.setItemsubcode07(object[10] != null ? object[10].toString() : null);
                diCostanalysisId.setItemsubcode08(object[11] != null ? object[11].toString() : null);
                diCostanalysisId.setItemsubcode09(object[12] != null ? object[12].toString() : null);
                diCostanalysisId.setItemsubcode10(object[13] != null ? object[13].toString() : null);
                DiCostanalysis diCostanalysis = diCostanalysisRepository.findById(diCostanalysisId).orElse(null);
                if (diCostanalysis != null) {
                } else {
                    diCostanalysis = new DiCostanalysis();
                    diCostanalysis.setId(diCostanalysisId);

                    diCostanalysis.setDescription(object[14].toString());
                    diCostanalysis.setUomcode(object[15].toString());
                    diCostanalysis.setCostline(new BigDecimal(object[16].toString()));
                    diCostanalysis.setReqqty(new BigDecimal(object[17].toString()));
                    diCostanalysis.setPrice(new BigDecimal(object[18].toString()));
                }
                diCostanalyses.add(diCostanalysis);
            }
        }
        return diCostanalyses;
    }/**
     * GET  /company-masters/:id : get the "id" companyMaster.
     *
     * @param id the id of the companyMaster to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the companyMaster, or with status 404 (Not Found)
     */
    @PostMapping("/di-costanalysis-avg-fetch")
    @Timed
    public List<DiCostanalysisAverage> getDiCostanalysisAvg(@RequestBody Master master) {
        log.debug("REST request to get projectcode : {}", master.getCode());
        List<DiCostanalysisAverage> diCostanalyses = new ArrayList<>();
        List<Object[]> objects = viewcostanalysisselectionicsRepository.findAllAvgByProjectcode(master.getCode().trim().toUpperCase());
        if (objects != null) {
            DecimalFormat df = new DecimalFormat("#.####");
            for (Object[] object : objects) {
                DiCostanalysisAverageId diCostanalysisId = new DiCostanalysisAverageId();
                diCostanalysisId.setProjectcode(master.getCode().trim().toUpperCase());
                diCostanalysisId.setFatherproductcode(object[0].toString());
                diCostanalysisId.setWorkattributes(object[1].toString());
                diCostanalysisId.setItemnature(object[2].toString());
                diCostanalysisId.setItemtypecode(object[3].toString());
                diCostanalysisId.setItemsubcode01(object[4].toString());
                diCostanalysisId.setItemsubcode02(object[5] != null ? object[5].toString() : null);
                diCostanalysisId.setItemsubcode03(object[6] != null ? object[6].toString() : null);
                diCostanalysisId.setItemsubcode04(object[7] != null ? object[7].toString() : null);
                diCostanalysisId.setItemsubcode05(object[8] != null ? object[8].toString() : null);
                diCostanalysisId.setItemsubcode06(object[9] != null ? object[9].toString() : null);
                diCostanalysisId.setItemsubcode07(object[10] != null ? object[10].toString() : null);
                diCostanalysisId.setItemsubcode08(object[11] != null ? object[11].toString() : null);
                diCostanalysisId.setItemsubcode09(object[12] != null ? object[12].toString() : null);
                diCostanalysisId.setItemsubcode10(object[13] != null ? object[13].toString() : null);
                DiCostanalysisAverage diCostanalysis = diCostanalysisAverageRepository.findById(diCostanalysisId).orElse(null);
                if (diCostanalysis != null) {
                } else {
                    diCostanalysis = new DiCostanalysisAverage();
                    diCostanalysis.setId(diCostanalysisId);

                    diCostanalysis.setDescription(object[14].toString());
                    diCostanalysis.setUomcode(object[15].toString());
                    diCostanalysis.setCostline(new BigDecimal(object[16].toString()));
                    diCostanalysis.setReqqty(new BigDecimal(df.format(new Double(object[17].toString()))));
                    diCostanalysis.setActualreqqty(new BigDecimal(df.format(new Double(object[17].toString()))));
                    diCostanalysis.setPrice(new BigDecimal(object[18].toString()));
                }
                diCostanalyses.add(diCostanalysis);
            }
        }
        return diCostanalyses;
    }

    @PostMapping("/di-costanalysis")
    @Timed
    public List<DiCostanalysis> getDiCostanalysis(@RequestBody List<DiCostanalysis> diCostanalyses) {
        for (DiCostanalysis diCostanalysis : diCostanalyses) {
            diCostanalysis.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
            diCostanalysis.setCreateddate(Timestamp.from(Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toInstant()));
            diCostanalysis = diCostanalysisRepository.save(diCostanalysis);
        }
        return diCostanalyses;
    }

    @PostMapping("/di-costanalysis-avg")
    @Timed
    public List<DiCostanalysisAverage> getDiCostanalysisAverage(@RequestBody List<DiCostanalysisAverage> diCostanalyses) {
        for (DiCostanalysisAverage diCostanalysis : diCostanalyses) {
            diCostanalysis.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
            diCostanalysis.setCreateddate(Timestamp.from(Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toInstant()));
            diCostanalysis = diCostanalysisAverageRepository.save(diCostanalysis);
        }
        return diCostanalyses;
    }
}

package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.MobileAttendance;
import io.vamani.application.model.LeaveSearch;
import io.vamani.application.model.MobileAttendanceBean;
import io.vamani.application.model.MobileDate;
import io.vamani.application.repository.MobileAttendanceRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.MD5UrlEncryption;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

/**
 * REST controller for managing MobileAttendance.
 */
@RestController
@RequestMapping("/api")
public class MobileAttendanceResource {

    private final Logger log = LoggerFactory.getLogger(MobileAttendanceResource.class);

    private static final String ENTITY_NAME = "mobileAttendance";

    private final MobileAttendanceRepository mobileAttendanceRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public MobileAttendanceResource(MobileAttendanceRepository mobileAttendanceRepository) {
        this.mobileAttendanceRepository = mobileAttendanceRepository;
    }

    /**
     * POST  /mobile-attendances : Create a new mobileAttendance.
     *
     * @param !mobileAttendance the mobileAttendance to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mobileAttendance, or with status 400 (Bad Request) if the mobileAttendance has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/mobile-attendances", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<MobileAttendanceBean> createMobileAttendance(@RequestParam(required = false) MultipartFile file, String latitude, String longitude, String factoryCode, String remarks) throws URISyntaxException, IOException {
        MobileAttendanceBean mobileAttendanceBean = new MobileAttendanceBean();
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        MobileAttendance mobileAttendance = new MobileAttendance();
        log.debug("REST request to save MobileAttendance : {}", mobileAttendance);
        mobileAttendance.setLatitude(latitude);
        mobileAttendance.setLongitude(longitude);
        if (factoryCode != null && factoryCode.length() > 0) {
            mobileAttendance.setFactoryCode(factoryCode);
            mobileAttendance.setAttendanceType("Y");
        } else {
            mobileAttendance.setAttendanceType("N");
        }
        mobileAttendance.setRemarks(remarks != null ? remarks.toUpperCase() : "");
        mobileAttendance.setAttendanceDate(Instant.now());
        mobileAttendance.setCardNo(SecurityUtils.getCurrentUserLogin().orElse(null));
        mobileAttendance.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
        mobileAttendance.setCreatedDate(Instant.now());
        MobileAttendance result = mobileAttendanceRepository.save(mobileAttendance);
        if (file != null && !file.isEmpty()) {
            String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
            String fileName = result.getId() + extn;
            result.setFileName(fileName);
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + "mobile-attendance/" + fileName);
            Files.write(path, bytes);
            result = mobileAttendanceRepository.save(result);
        }
        if (result != null && result.getId() != null && result.getId().longValue() > 0) {
            BeanUtils.copyProperties(result, mobileAttendanceBean);
            mobileAttendanceBean.setExist(true);
        } else {
            mobileAttendanceBean.setExist(true);
            mobileAttendanceBean.setErrorMessage("Record not saved");
        }
        return ResponseEntity.created(new URI("/api/mobile-attendances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(mobileAttendanceBean);
    }

    /**
     * PUT  /mobile-attendances : Updates an existing mobileAttendance.
     *
     * @param mobileAttendance the mobileAttendance to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mobileAttendance,
     * or with status 400 (Bad Request) if the mobileAttendance is not valid,
     * or with status 500 (Internal Server Error) if the mobileAttendance couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mobile-attendances")
    @Timed
    public ResponseEntity<MobileAttendance> updateMobileAttendance(@Valid @RequestBody MobileAttendance mobileAttendance) throws URISyntaxException {
        log.debug("REST request to update MobileAttendance : {}", mobileAttendance);
        if (mobileAttendance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MobileAttendance result = mobileAttendanceRepository.save(mobileAttendance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mobileAttendance.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mobile-attendances : get all the mobileAttendances.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mobileAttendances in body
     */
    @GetMapping("/mobile-attendances")
    @Timed
    public ResponseEntity<List<MobileAttendance>> getAllMobileAttendances(Pageable pageable) {
        log.debug("REST request to get a page of MobileAttendances");
        Page<MobileAttendance> page = mobileAttendanceRepository.findAll(SecurityUtils.getCurrentUserLogin().orElse(null), pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mobile-attendances");
        List<MobileAttendance> mobileAttendances = new ArrayList<>();
        for (MobileAttendance mobileAttendance : page.getContent()) {
            try {
                mobileAttendance.setFileName(MD5UrlEncryption.fakeUploadUrl("mobile-attendance/" + mobileAttendance.getFileName()));
            } catch(Exception e) {}
            mobileAttendances.add(mobileAttendance);
        }
        return ResponseEntity.ok().headers(headers).body(mobileAttendances);
    }

    /**
     * GET  /mobile-attendances : get all the mobileAttendances.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mobileAttendances in body
     */
    @GetMapping("/mobile-attendances-month/{monthYear}")
    @Timed
    public ResponseEntity<List<MobileDate>> getAllMobileAttendancesByMonth(@PathVariable String monthYear) throws ParseException {
        log.debug("REST request to get a page of MobileAttendances");
        String[] arr = monthYear.split("-");
        YearMonth yearMonth = YearMonth.of( Integer.parseInt(arr[1]), Integer.parseInt(arr[0]) );  // January of 2015.
        Instant firstOfMonth = yearMonth.atDay( 1 ).atStartOfDay(ZoneId.systemDefault()).toInstant();
        Instant lastOfMonth = yearMonth.atEndOfMonth().atStartOfDay(ZoneId.systemDefault()).toInstant();
        List<MobileAttendance> page = mobileAttendanceRepository.findAllByMonth(SecurityUtils.getCurrentUserLogin().orElse(null), firstOfMonth, lastOfMonth);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat simpleDateFormatLine = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Map<String, List<MobileAttendanceBean>> listMap = new HashMap<>();
        Map<String, Set<String>> flagMap = new HashMap<>();
        for (MobileAttendance mobileAttendance : page) {
            String date = simpleDateFormat.format(Date.from(mobileAttendance.getAttendanceDate()));
            if (listMap.containsKey(date)) {
                List<MobileAttendanceBean> mobileAttendances = listMap.get(date);
                MobileAttendanceBean mobileAttendanceBean = new MobileAttendanceBean();
                BeanUtils.copyProperties(mobileAttendance, mobileAttendanceBean);
                try {
                    mobileAttendanceBean.setFileName(MD5UrlEncryption.fakeUploadUrl("mobile-attendance/" + mobileAttendanceBean.getFileName()));
                } catch (Exception e) {
                }
                mobileAttendanceBean.setAttendanceDateView(simpleDateFormatLine.format(Date.from(mobileAttendanceBean.getAttendanceDate())));
                mobileAttendances.add(mobileAttendanceBean);
                listMap.put(date, mobileAttendances);

                Set<String> strings = flagMap.get(date);
                if (mobileAttendance.getLeaveMasterId() != null && mobileAttendance.getLeaveMasterId().longValue() > 0) {
                    strings.add("Y");
                } else {
                    strings.add("N");
                }
                flagMap.put(date, strings);
            } else {
                List<MobileAttendanceBean> mobileAttendances = new ArrayList<>();
                MobileAttendanceBean mobileAttendanceBean = new MobileAttendanceBean();
                BeanUtils.copyProperties(mobileAttendance, mobileAttendanceBean);
                try {
                    mobileAttendanceBean.setFileName(MD5UrlEncryption.fakeUploadUrl("mobile-attendance/" + mobileAttendanceBean.getFileName()));
                } catch (Exception e) {
                }
                mobileAttendanceBean.setAttendanceDateView(simpleDateFormatLine.format(Date.from(mobileAttendanceBean.getAttendanceDate())));
                mobileAttendances.add(mobileAttendanceBean);
                listMap.put(date, mobileAttendances);

                Set<String> strings = new HashSet<>();
                if (mobileAttendance.getLeaveMasterId() != null && mobileAttendance.getLeaveMasterId().longValue() > 0) {
                    strings.add("Y");
                } else {
                    strings.add("N");
                }
                flagMap.put(date, strings);
            }
        }
        List<MobileDate> mobileDates = new ArrayList<>();
        for (String key : listMap.keySet()) {
            MobileDate mobileDate = new MobileDate();
            mobileDate.setAttendanceDate(key);
            mobileDate.setMobileAttendances(listMap.get(key));
            if(flagMap.get(key).contains("Y") && flagMap.get(key).contains("N")) {
                mobileDate.setFlag("O");
            } else if(flagMap.get(key).contains("Y") && !flagMap.get(key).contains("N")) {
                mobileDate.setFlag("Y");
            } else {
                mobileDate.setFlag("N");
            }
            mobileDates.add(mobileDate);
        }
        return ResponseEntity.ok().body(mobileDates);
    }

    /**
     * GET  /mobile-attendances/:id : get the "id" mobileAttendance.
     *
     * @param id the id of the mobileAttendance to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mobileAttendance, or with status 404 (Not Found)
     */
    @GetMapping("/mobile-attendances/{id}")
    @Timed
    public ResponseEntity<MobileAttendance> getMobileAttendance(@PathVariable Long id) {
        log.debug("REST request to get MobileAttendance : {}", id);
        Optional<MobileAttendance> mobileAttendance = mobileAttendanceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(mobileAttendance);
    }

    /**
     * DELETE  /mobile-attendances/:id : delete the "id" mobileAttendance.
     *
     * @param id the id of the mobileAttendance to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mobile-attendances/{id}")
    @Timed
    public ResponseEntity<Void> deleteMobileAttendance(@PathVariable Long id) {
        log.debug("REST request to delete MobileAttendance : {}", id);

        mobileAttendanceRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/mobile-attendances-date")
    @Timed
    public ResponseEntity<List<MobileAttendanceBean>> findAllMobileAttendanceByDate(@RequestBody LeaveSearch leaveSearch) {
        List<MobileAttendanceBean> mobileAttendanceBeans = new ArrayList<>();
        List<MobileAttendance> mobileAttendances = mobileAttendanceRepository.findAllByAttendanceDate(SecurityUtils.getCurrentUserLogin().orElse(null), Date.from(leaveSearch.getLeaveDateFrom()), Date.from(leaveSearch.getLeaveDateTo()));
        SimpleDateFormat simpleDateFormatLine = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        for (MobileAttendance mobileAttendance : mobileAttendances) {
            MobileAttendanceBean mobileAttendanceBean = new MobileAttendanceBean();
            BeanUtils.copyProperties(mobileAttendance, mobileAttendanceBean);
            try {
                mobileAttendanceBean.setFileName(MD5UrlEncryption.fakeUploadUrl("mobile-attendance/" + mobileAttendanceBean.getFileName()));
            } catch (Exception e) {
            }
            mobileAttendanceBean.setAttendanceDateView(simpleDateFormatLine.format(Date.from(mobileAttendanceBean.getAttendanceDate())));
            mobileAttendanceBeans.add(mobileAttendanceBean);
        }
        return ResponseEntity.ok().body(mobileAttendanceBeans);
    }
}

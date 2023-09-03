package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.domain.BankRealisationCertificateUpload;
import io.vamani.application.db2.domain.ScriptDetailsUpload;
import io.vamani.application.db2.repository.ScriptDetailsUploadRepository;
import io.vamani.application.model.BankRealisationCertificateUploadSearch;
import io.vamani.application.model.Message;
import io.vamani.application.model.ScriptDetailsUploadSearch;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.DateUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ScriptDetailsUploadResource {

    private final Logger log = LoggerFactory.getLogger(ScriptDetailsUploadResource.class);

    private static final String ENTITY_NAME = "scriptDEtailsUpload";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private ApplicationProperties applicationProperties;

    private final ScriptDetailsUploadRepository scriptDetailsUploadRepository;

    public ScriptDetailsUploadResource(ScriptDetailsUploadRepository scriptDetailsUploadRepository) {
        this.scriptDetailsUploadRepository = scriptDetailsUploadRepository;
    }

    @PutMapping("/script-details-uploads")
    public ResponseEntity<ScriptDetailsUpload> updateBankRealisationCertificateUpload(@Valid @RequestBody ScriptDetailsUpload scriptDetailsUpload) throws URISyntaxException {
        log.debug("A REST request to update ScriptDeatilsUpload", scriptDetailsUpload);
        if (scriptDetailsUpload.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ScriptDetailsUpload result = scriptDetailsUploadRepository.save(scriptDetailsUpload);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, scriptDetailsUpload.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /script-details-uploads} : get all the scriptDetailsUpload.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @GetMapping("/script-details-uploads")
    public ResponseEntity<List<ScriptDetailsUpload>> getAllBankRealisationCertificateUpload(Pageable pageable) {
        log.debug("A REST request to get all BankRealisateCertificateUpload");
        Page<ScriptDetailsUpload> page = scriptDetailsUploadRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/script-details-uploads/{id}")
    public ResponseEntity<ScriptDetailsUpload> getScriptDetailsUpload(@PathVariable Long id) {
        log.debug("REST request to get scriptDetailsUpload");
        Optional<ScriptDetailsUpload> scriptDetailsUpload = scriptDetailsUploadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(scriptDetailsUpload);
    }

    @DeleteMapping("/script-details-uploads/{id}")
    public ResponseEntity<Void> deleteScriptDetailsUploadById(@PathVariable Long id) {
        log.debug("REST request to delete scriptDetailsUpload", id);
        scriptDetailsUploadRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/script-details-uploads-qry")
    public ResponseEntity<List<ScriptDetailsUpload>> getAllBankRealisationCertificateByBrcDateAndBrcNo(@Valid @RequestBody ScriptDetailsUploadSearch search) {
        log.debug("A REST request to get a page of BankRealisationCertificateUpload");
        String sNo = "%";
        if (search.getsNo() != null) {
            sNo = "%" + search.getsNo() + "%";
        }
        search.setPage(
            PageRequest.of(
                search.getPageNo(),
                search.getSize(),
                search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc")
                    ? Sort.by(search.getSort()).descending()
                    : Sort.by(search.getSort()).ascending()
            )
        );
        Page<ScriptDetailsUpload> page = null;
        if (search.getDate() != null && search.getDateTo() != null) {
            page = scriptDetailsUploadRepository.findByDateAndShippingBillNo(search.getDate(), search.getDateTo(), sNo, search.getPage());
        } else {
            page = scriptDetailsUploadRepository.findByShippingBillNo(sNo, search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping(value = "/script-details-uploads-excel", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<Message> createAssetFileUploadMaster(@RequestParam(required = false) MultipartFile[] file) throws IOException, ParseException {
        String errorLog = "";
        log.debug("REST request to upload scriptDetailsUpload :{}");
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        List<ScriptDetailsUpload> scriptDetailsUploadList = new ArrayList<>();
        try {
            if (file != null && file.length > 0) {
                for (MultipartFile multipartFile : file) {
                    byte[] bytes = multipartFile.getBytes();
                    Path path = Paths.get(UPLOADED_FOLDER + "styles/temp/" + multipartFile.getOriginalFilename());
                    Files.write(path, bytes);
                    String fileName = multipartFile.getOriginalFilename();
                    File readFile = new File(UPLOADED_FOLDER + "styles/temp/" + fileName);
                    FileInputStream inputStream = new FileInputStream(readFile);
                    Workbook workbook = null;
                    //Find the file extension by splitting file name in substring  and getting only extension name
                    String fileExtensionName = fileName.substring(fileName.indexOf("."));
                    if (fileExtensionName.equals(".xlsx")) {
                        workbook = new XSSFWorkbook(inputStream);
                    } else {
                        workbook = new HSSFWorkbook(inputStream);
                    }
                    Sheet sheet = workbook.getSheetAt(0);
                    int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
                    int rowUpdated = 0;
                    for (int i = 1; i < rowCount; i++) {
                        Row row = sheet.getRow(i);
                        row.getCell(0).setCellType(CellType.STRING);
                        if (row.getCell(0).getStringCellValue() != null && row.getCell(0).getStringCellValue().length() > 0) {
                            ScriptDetailsUpload scriptDetailsUpload = new ScriptDetailsUpload();
                            if (row.getCell(0) != null) {
                                scriptDetailsUpload.setsNo(row.getCell(0).getStringCellValue().trim());
                            }

                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(CellType.STRING);
                                scriptDetailsUpload.setShippingBillNo(row.getCell(1).getStringCellValue().trim());
                            }

                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(CellType.STRING);
                                scriptDetailsUpload.setInvoiceNo(row.getCell(2).getStringCellValue().trim());
                            }

                            if (row.getCell(3) != null) {
                                row.getCell(3).setCellType(CellType.STRING);
                                String dt = row.getCell(3).getStringCellValue();
                                Date format = new SimpleDateFormat("dd.mm.yyyy").parse(dt);
                                scriptDetailsUpload.setShippingBillDate(DateUtils.asLocalDate(format));
                            }

                            if (row.getCell(4) != null) {
                                row.getCell(4).setCellType(CellType.STRING);
                                scriptDetailsUpload.setScrollNo(row.getCell(4).getStringCellValue().trim());
                            }

                            if (row.getCell(5) != null) {
                                row.getCell(5).setCellType(CellType.STRING);
                                scriptDetailsUpload.setPortCode(row.getCell(5).getStringCellValue().trim());
                            }

                            if (row.getCell(6) != null) {
                                row.getCell(6).setCellType(CellType.NUMERIC);
                                scriptDetailsUpload.setSanctionedValue(new BigDecimal(row.getCell(6).getNumericCellValue()));
                            }

                            if (row.getCell(7) != null) {
                                row.getCell(7).setCellType(CellType.NUMERIC);
                                scriptDetailsUpload.setFobInFc(new BigDecimal(row.getCell(7).getNumericCellValue()));
                            }

                            if (row.getCell(8) != null) {
                                row.getCell(8).setCellType(CellType.NUMERIC);
                                scriptDetailsUpload.setFobInInr(new BigDecimal(row.getCell(8).getNumericCellValue()));
                            }

                            if (row.getCell(9) != null) {
                                row.getCell(9).setCellType(CellType.STRING);
                                scriptDetailsUpload.setBrcNumber(row.getCell(9).getStringCellValue().trim());
                            }

                            if (row.getCell(10) != null) {
                                row.getCell(10).setCellType(CellType.STRING);
                                scriptDetailsUpload.setCustomerName(row.getCell(10).getStringCellValue().trim());
                            }

                            if (row.getCell(11) != null) {
                                row.getCell(11).setCellType(CellType.STRING);
                                String dt = row.getCell(11).getStringCellValue();
                                Date format = new SimpleDateFormat("dd.mm.yyyy").parse(dt);
                                scriptDetailsUpload.setEntryDate(DateUtils.asLocalDate(format));
                            }

                            if (row.getCell(12) != null) {
                                row.getCell(12).setCellType(CellType.NUMERIC);
                                scriptDetailsUpload.setBrcRealisedValue(new BigDecimal(row.getCell(12).getNumericCellValue()));
                            }

                            if (row.getCell(13) != null) {
                                row.getCell(13).setCellType(CellType.STRING);
                                scriptDetailsUpload.setIfscCode(row.getCell(13).getStringCellValue().trim());
                            }

                            if (row.getCell(14) != null) {
                                row.getCell(14).setCellType(CellType.STRING);
                                scriptDetailsUpload.setScriptNo(row.getCell(14).getStringCellValue().trim());
                            }

                            if (row.getCell(15) != null) {
                                row.getCell(15).setCellType(CellType.NUMERIC);
                                scriptDetailsUpload.setScriptAmount(new BigDecimal(row.getCell(15).getNumericCellValue()));
                            }
                            scriptDetailsUpload.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                            scriptDetailsUpload.setCreateddate(Instant.now());
                            scriptDetailsUploadRepository.save(scriptDetailsUpload);

                            /* List<ScriptDetailsUpload> scriptDetailsUploads = scriptDetailsUploadRepository.findByShippingBillNo(row.getCell(1).getStringCellValue().trim());
                            if (scriptDetailsUploads != null && scriptDetailsUploads.size() > 0) {
                            } else {
                                scriptDetailsUpload.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                                scriptDetailsUpload.setCreateddate(Instant.now());
                                scriptDetailsUploadRepository.save(scriptDetailsUpload);
                            }*/
                        }
                    }
                    inputStream.close();
                    workbook.close();
                }
            }
        } catch (Exception ea) {
            return ResponseEntity.badRequest().body(new Message("ERROR", ea.getMessage()));
        }
        return ResponseEntity.ok().body(new Message("SUCCESS", errorLog));
    }
}

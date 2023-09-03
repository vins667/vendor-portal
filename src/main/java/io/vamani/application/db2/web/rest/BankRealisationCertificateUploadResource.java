package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.domain.BankRealisationCertificateUpload;
import io.vamani.application.model.BankRealisationCertificateUploadSearch;
import io.vamani.application.model.Message;
import io.vamani.application.db2.repository.BankRealisationCertificateUploadRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.DateUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
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

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;


import java.util.*;

@RestController
@RequestMapping("api")
public class BankRealisationCertificateUploadResource {

    private final Logger log = LoggerFactory.getLogger(BankRealisationCertificateUploadResource.class);

    private static final String ENTITY_NAME = "bankRealisationCertificateUpload";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private ApplicationProperties applicationProperties;

    private final BankRealisationCertificateUploadRepository bankRealisationCertificateUploadRepository;

    public BankRealisationCertificateUploadResource(BankRealisationCertificateUploadRepository bankRealisationCertificateUploadRepository) {
        this.bankRealisationCertificateUploadRepository = bankRealisationCertificateUploadRepository;
    }

    @PutMapping("/bank-realisation-certificate-uploads")
    public ResponseEntity<BankRealisationCertificateUpload> updateBankRealisationCertificateUpload(@Valid @RequestBody BankRealisationCertificateUpload bankRealisationCertificateUpload) throws URISyntaxException {
        log.debug("A REST request to update BankRealisationCertificateUpload", bankRealisationCertificateUpload);
        if (bankRealisationCertificateUpload.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        bankRealisationCertificateUpload.setSbNo(bankRealisationCertificateUpload.getSbNo());
        bankRealisationCertificateUpload.setSbDate(bankRealisationCertificateUpload.getSbDate());
        bankRealisationCertificateUpload.setBrcNo(bankRealisationCertificateUpload.getBrcNo());
        bankRealisationCertificateUpload.setBrcDate(bankRealisationCertificateUpload.getBrcDate());
        bankRealisationCertificateUpload.setPortCode(bankRealisationCertificateUpload.getPortCode());
        bankRealisationCertificateUpload.setFob(bankRealisationCertificateUpload.getFob());
        bankRealisationCertificateUpload.setCurrency(bankRealisationCertificateUpload.getCurrency());
        bankRealisationCertificateUpload.setRealisationDate(bankRealisationCertificateUpload.getRealisationDate());
        bankRealisationCertificateUpload.setStatus(bankRealisationCertificateUpload.getStatus());
        BankRealisationCertificateUpload result = bankRealisationCertificateUploadRepository.save(bankRealisationCertificateUpload);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, bankRealisationCertificateUpload.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /bank-realisation-certificate-uploads} : get all the bankRealisationCertificate.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStylePlanUploads in body.
     */
    @GetMapping("/bank-realisation-certificate-uploads")
    public ResponseEntity<List<BankRealisationCertificateUpload>> getAllBankRealisationCertificateUpload(Pageable pageable) {
        log.debug("A REST request to get all BankRealisateCertificateUpload");
        Page<BankRealisationCertificateUpload> page = bankRealisationCertificateUploadRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /bank-realisation-certificate-uploads/:id} : get the "id" vcutStylePlanUpload.
     *
     * @param id the id of the bankRealisationCertificateUpload to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bankRealisationCertificateUpload, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/bank-realisation-certificate-uploads/{id}")
    public ResponseEntity<BankRealisationCertificateUpload> getBankRealisationCertificateUploadById(@PathVariable Long id) {
        log.debug("REST request to get BankRealisationCertificateById", id);
        Optional<BankRealisationCertificateUpload> bankRealisationCertificateUpload = bankRealisationCertificateUploadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bankRealisationCertificateUpload);
    }

    @DeleteMapping("/bank-realisation-certificate-uploads/{id}")
    public ResponseEntity<Void> DeleteBankRealisationCertificateById(@PathVariable Long id) {
        log.debug("REST request to delete BankRealisationCertificateUpload : {}", id);
        bankRealisationCertificateUploadRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/bank-realisation-certificate-uploads-qry")
    public ResponseEntity<List<BankRealisationCertificateUpload>> getAllBankRealisationCertificateByBrcDateAndBrcNo(@Valid @RequestBody BankRealisationCertificateUploadSearch search) {
        log.debug("A REST request to get a page of BankRealisationCertificateUpload");
        String brcNo = "%";
        if (search.getBrcNo() != null) {
            brcNo = "%" + search.getBrcNo() + "%";
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
        Page<BankRealisationCertificateUpload> page = null;
        if (search.getBrcDate() != null && search.getBrcDateTo() != null) {
            page = bankRealisationCertificateUploadRepository.findByBrcDateAndBrcNo(search.getBrcDate(), search.getBrcDateTo(), brcNo, search.getPage());
        } else {
            page = bankRealisationCertificateUploadRepository.findByBrcNo(brcNo, search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping(value = "/bank-realisation-certificate-uploads-excel", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<Message> createAssetFileUploadMaster(@RequestParam(required = false) MultipartFile[] file) throws IOException, ParseException {
        String errorLog = "";
        log.debug("REST request to save BankRealisationCertificateExcelUpload : {}");
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        List<BankRealisationCertificateUpload> bankRealisationCertificateUploadList = new ArrayList<>();
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
                    //Check condition if the file is xlsx file
                    if (fileExtensionName.equals(".xlsx")) {
                        //If it is xlsx file then create object of XSSFWorkbook class
                        workbook = new XSSFWorkbook(inputStream);
                    } else {
                        workbook = new HSSFWorkbook(inputStream);
                    }
                    //Read sheet inside the workbook by its name
                    Sheet sheet = workbook.getSheetAt(0);
                    //Find number of rows in excel file
                    int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
                    int rowUpdated = 0;
                    for (int i = 1; i < rowCount; i++) {
                        Row row = sheet.getRow(i);
                        row.getCell(0).setCellType(CellType.STRING);
                        if (row.getCell(0).getStringCellValue() != null && row.getCell(0).getStringCellValue().length() > 0) {
                            BankRealisationCertificateUpload bankRealisationCertificateUpload = new BankRealisationCertificateUpload();
                            if (row.getCell(0) != null) {
                                bankRealisationCertificateUpload.setSbNo(row.getCell(0).getStringCellValue().trim());
                            }
                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(CellType.STRING);
                               String sbDateString = row.getCell(1).getStringCellValue();
                                Date format = new SimpleDateFormat("dd.mm.yyyy").parse(sbDateString);
                          //      bankRealisationCertificateUpload.setSbDate(DateUtils.asLocalDate(row.getCell(1).getDateCellValue()));
                            //    Date sbDate = row.getCell(1).getDateCellValue();
                              //  bankRealisationCertificateUpload.setSbDate(sbDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                               bankRealisationCertificateUpload.setSbDate(DateUtils.asLocalDate(format));
                            }
                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(CellType.STRING);
                                bankRealisationCertificateUpload.setBrcNo(row.getCell(2).getStringCellValue().trim());
                            }
                            if (row.getCell(3) != null) {
                              row.getCell(3).setCellType(CellType.STRING);
                                String brcDateString = row.getCell(3).getStringCellValue();
                                Date format = new SimpleDateFormat("dd.mm.yyyy").parse(brcDateString);
                                bankRealisationCertificateUpload.setBrcDate(DateUtils.asLocalDate(format));
                                //bankRealisationCertificateUpload.setBrcDate(DateUtils.asLocalDate(row.getCell(3).getDateCellValue()));
                            // Date brcDate = row.getCell(3).getDateCellValue();
                             //bankRealisationCertificateUpload.setBrcDate(brcDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            }
                            if (row.getCell(4) != null) {
                                row.getCell(4).setCellType(CellType.STRING);
                                bankRealisationCertificateUpload.setPortCode(row.getCell(4).getStringCellValue().trim());
                            }
                            if (row.getCell(5) != null) {
                                row.getCell(5).setCellType(CellType.NUMERIC);
                                bankRealisationCertificateUpload.setFob(BigDecimal.valueOf(row.getCell(5).getNumericCellValue()));
                            }
                            if (row.getCell(6) != null) {
                                row.getCell(6).setCellType(CellType.STRING);
                                bankRealisationCertificateUpload.setCurrency(row.getCell(6).getStringCellValue().trim());
                            }
                            if (row.getCell(7) != null) {
                                row.getCell(7).setCellType(CellType.STRING);
                                String realisationDateString = row.getCell(7).getStringCellValue();
                                Date format = new SimpleDateFormat("dd.mm.yyyy").parse(realisationDateString);
                                bankRealisationCertificateUpload.setRealisationDate(DateUtils.asLocalDate(format));
                            //    bankRealisationCertificateUpload.setRealisationDate(DateUtils.asLocalDate(row.getCell(7).getDateCellValue()));
                                //Date realisationDate = row.getCell(7).getDateCellValue();
                                //bankRealisationCertificateUpload.setRealisationDate(realisationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                            }
                            if (row.getCell(8) != null) {
                                row.getCell(8).setCellType(CellType.STRING);
                                bankRealisationCertificateUpload.setStatus(row.getCell(8).getStringCellValue().trim());
                            }
                            BankRealisationCertificateUpload bankRealisationCertificateUpload1 = bankRealisationCertificateUploadRepository.findByBrcDateAndBrcNos(bankRealisationCertificateUpload.getSbNo());

                            if (bankRealisationCertificateUpload1 != null) {
                            } else {
                                bankRealisationCertificateUpload.setCreatedby(SecurityUtils.getCurrentUserLogin().orElse(null));
                                bankRealisationCertificateUpload.setCreateddate(Instant.now());
                                bankRealisationCertificateUploadRepository.save(bankRealisationCertificateUpload);
                            }
                        }
                    }
                    inputStream.close();
                    workbook.close();
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Message("ERROR", e.getMessage()));
        }
        return ResponseEntity.ok().body(new Message("SUCCESS", errorLog));

    }
}

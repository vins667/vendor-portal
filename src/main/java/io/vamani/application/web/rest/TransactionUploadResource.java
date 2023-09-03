package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.domain.BankRealisationCertificateUpload;
import io.vamani.application.domain.TransactionUpload;
import io.vamani.application.model.Message;
import io.vamani.application.model.TransactionUploadSearchBean;
import io.vamani.application.repository.TransactionUploadRepository;
import io.vamani.application.util.DateUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransactionUploadResource {
    private final Logger log = LoggerFactory.getLogger(TransactionUploadResource.class);
    private static final String ENTITY_NAME = "transactionUpload";
    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private static BigDecimal df = new BigDecimal("0.00");

    @Autowired
    private ApplicationProperties applicationProperties;

    private final TransactionUploadRepository transactionUploadRepository;

    public TransactionUploadResource(TransactionUploadRepository transactionUploadRepository) {
        this.transactionUploadRepository = transactionUploadRepository;
    }

    @PutMapping("transaction-uploads")
    public ResponseEntity<TransactionUpload> updateTransactionUpload(@Valid @RequestBody TransactionUpload transactionUpload) throws URISyntaxException {
        log.debug("A REST request to update TransactionUpload", transactionUpload);
        if (transactionUpload.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        transactionUpload.setTransactionPostedDate(transactionUpload.getTransactionPostedDate());
        transactionUpload.setChequeNo(transactionUpload.getChequeNo());
        transactionUpload.setDescription(transactionUpload.getDescription());
        transactionUpload.setMode(transactionUpload.getMode());
        transactionUpload.setTransactionAmount(transactionUpload.getTransactionAmount());
        TransactionUpload result = transactionUploadRepository.save(transactionUpload);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, transactionUpload.getId().toString()))
            .body(result);
    }

    @GetMapping("/transaction-uploads")
    public ResponseEntity<List<TransactionUpload>> getAllTransactionUpload(Pageable pageable) {
        log.debug("A REST request to get all BankRealisateCertificateUpload");
        Page<TransactionUpload> page = transactionUploadRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/transaction-uploads/{id}")
    public ResponseEntity<TransactionUpload> getTransactionUploadById(@PathVariable Long id) {
        log.debug("REST request to get TransactionUpload", id);
        Optional<TransactionUpload> transactionUpload = transactionUploadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(transactionUpload);
    }

    @DeleteMapping("/transaction-uploads/{id}")
    public ResponseEntity<Void> DeleteTransactionDetailsById(@PathVariable Long id) {
        log.debug("REST request to delete TransactionUpload : {}", id);
        transactionUploadRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @PostMapping("/transaction-uploads-qry")
    public ResponseEntity<List<TransactionUpload>> getAllTransactionUploadByChequeNo(@Valid @RequestBody TransactionUploadSearchBean search) {
        log.debug("A REST request to get a page of TransactionUpload");
        String chequeNo = "%";
        if (search.getChequeNo() != null) {
            chequeNo = "%" + search.getChequeNo() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), search.getSortType() != null && search.getSortType().equalsIgnoreCase("desc") ? Sort.by(search.getSort()).descending() : Sort.by(search.getSort()).ascending()));
        //  TransactionUpload transactionUpload = new TransactionUpload();
        Page<TransactionUpload> page = null;
        if (search.getChequeNo() != null) {
            page = transactionUploadRepository.findByChequeNo(chequeNo, search.getPage());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
    @PostMapping(value = "/transaction-uploads-excel", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<Message> createTransactionUploadMaster(@RequestParam(required = false) MultipartFile[] file) throws IOException, ParseException {
        String errorLog = "";
        log.debug("REST request to save TransactionUpload : {}");
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        List<TransactionUpload> transactionUploadList = new ArrayList<>();
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
                        //If it is xlsx file then create object of XSSFWorkbook class
                        workbook = new XSSFWorkbook(inputStream);
                    } else {
                        workbook = new HSSFWorkbook(inputStream);
                    }
                    Sheet sheet = workbook.getSheetAt(0);
                    //Find number of rows in excel file
                    int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
                    int rowUpdated = 0;
                    for (int i = 1; i < rowCount; i++) {
                        Row row = sheet.getRow(i);
                        if (row.getCell(0) != null) {
                            TransactionUpload transactionUpload = new TransactionUpload();
                            if (row.getCell(0) != null) {
                            //    row.getCell(0).setCellType(CellType.STRING);
//                                String transactionPostedDateString = row.getCell(0).getStringCellValue();
//                               Date format = new SimpleDateFormat("DD/MM/YYYY HH:mm:ss a").parse(transactionPostedDateString);
//                                transactionUpload.setTransactionPostedDate(format);
                                transactionUpload.setTransactionPostedDate(row.getCell(0).getDateCellValue().toInstant());
                            }
                            if (row.getCell(1) != null ) {
                                row.getCell(1).setCellType(CellType.STRING);
                                transactionUpload.setChequeNo( row.getCell(1).getStringCellValue());
                            }
                            if (row.getCell(2).getStringCellValue() != null) {
                                row.getCell(2).setCellType(CellType.STRING);
                                transactionUpload.setDescription(row.getCell(2).getStringCellValue().trim());
                            }
                            if (row.getCell(3).getStringCellValue() != null) {
                                row.getCell(3).setCellType(CellType.STRING);
                                transactionUpload.setMode(row.getCell(3).getStringCellValue().trim());
                            }
                            if (row.getCell(4) != null) {
                                row.getCell(2).setCellType(CellType.NUMERIC);
                                transactionUpload.setTransactionAmount(new BigDecimal(row.getCell(4).getNumericCellValue()));
                            }
                     transactionUploadRepository.save(transactionUpload);
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

package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.repository.FindocumentRepository;
import io.vamani.application.domain.FormsDownload;
import io.vamani.application.domain.VcutPlanChangeMaster;
import io.vamani.application.domain.VcutStylePlanUpload;
import io.vamani.application.model.Message;
import io.vamani.application.repository.FormsDownloadRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.MD5UrlEncryption;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing FormsDownload.
 */
@RestController
@RequestMapping("/api")
public class BankReconcilationUploadResource {

    private final Logger log = LoggerFactory.getLogger(BankReconcilationUploadResource.class);

    private static final String ENTITY_NAME = "bankReconcilationUpload";

    @Autowired
    private ApplicationProperties applicationProperties;

    private final FindocumentRepository findocumentRepository;

    public BankReconcilationUploadResource(FindocumentRepository findocumentRepository) {
        this.findocumentRepository = findocumentRepository;
    }

    /**
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/bank-reconciliation-uploads-download")
    @Timed
    public ResponseEntity<Object> downloadSample() throws FileNotFoundException, IOException {
        log.debug("REST request to get Feedback : {}");
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        File file = new File(UPLOADED_FOLDER + "sample/BANK_RECO_SAMPLE.xlsx");
        Path path = Paths.get(UPLOADED_FOLDER + "sample/BANK_RECO_SAMPLE.xlsx");
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        String mimeType = Files.probeContentType(path);
        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType(mimeType)).body(resource);
        return responseEntity;
    }

    /**
     * POST  /asset-file-upload-masters : Create a new assetFileUploadMaster.
     *
     * @param !assetFileUploadMaster the assetFileUploadMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetFileUploadMaster, or with status 400 (Bad Request) if the assetFileUploadMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/bank-reconciliation-uploads", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<Message> updateBankReconciliation(@RequestParam(required = false) MultipartFile[] file) throws IOException, ParseException {
        String errorLog = "";
        log.debug("REST request to save StyleExcelUpload : {}");
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        try {
            if (file != null && file.length > 0) {
                for (MultipartFile multipartFile : file) {
                    byte[] bytes = multipartFile.getBytes();
                    Path path = Paths.get(UPLOADED_FOLDER + "sample/temp/" + multipartFile.getOriginalFilename());
                    Files.write(path, bytes);

                    String fileName = multipartFile.getOriginalFilename();

                    File readFile = new File(UPLOADED_FOLDER + "sample/temp/" + fileName);

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
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    int rowUpdated = 0;
                    for (int i = 1; i < rowCount + 1; i++) {
                        Row row = sheet.getRow(i);
                        if (row.getCell(0) != null) {
                            String companycode = null;
                            String businessUnitCode = null;
                            String finYear = null;
                            String finDocCode = null;
                            String glCode = null;
                            String reconBy = null;
                            String reconDate = null;

                            if (row.getCell(0) != null) {
                                row.getCell(0).setCellType(CellType.STRING);
                                companycode = row.getCell(0).getStringCellValue().trim();
                            }

                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(CellType.STRING);
                                businessUnitCode = row.getCell(1).getStringCellValue().trim();
                            }

                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(CellType.STRING);
                                finYear = row.getCell(2).getStringCellValue().trim();
                            }

                            if (row.getCell(3) != null) {
                                row.getCell(3).setCellType(CellType.STRING);
                                finDocCode = row.getCell(3).getStringCellValue().trim();
                            }

                            if (row.getCell(4) != null) {
                                row.getCell(4).setCellType(CellType.STRING);
                                glCode = row.getCell(4).getStringCellValue().trim();
                            }

                            if (row.getCell(5) != null) {
                                row.getCell(5).setCellType(CellType.STRING);
                                reconDate = row.getCell(5).getStringCellValue().trim();
                            }

                            if (row.getCell(6) != null) {
                                row.getCell(6).setCellType(CellType.STRING);
                                reconBy = row.getCell(6).getStringCellValue();
                            }
                            if (companycode != null && businessUnitCode != null && finYear != null && finDocCode != null && glCode != null && reconDate != null && reconBy != null) {
                                reconDate = format1.format(format.parse(reconDate));
                                try {
                                    findocumentRepository.updateReconcileByGLCode(reconDate, reconBy, simpleDateFormat2.format(new Date()), companycode, businessUnitCode, finYear, finDocCode, glCode);
                                    ++rowUpdated;
                                } catch(Exception e) {}
                            }
                        }
                    }
                    errorLog = "No of Rows "+ rowUpdated+" updated.";
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Message("ERROR", e.getMessage()));
        }
        return ResponseEntity.ok().body(new Message("SUCCESS", errorLog));
    }
}

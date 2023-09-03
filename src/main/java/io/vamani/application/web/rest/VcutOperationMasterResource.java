package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.VcutOperationMaster;
import io.vamani.application.model.Master;
import io.vamani.application.model.Message;
import io.vamani.application.model.VcutOperationMasterBean;
import io.vamani.application.model.VcutStylePlanUploadSearch;
import io.vamani.application.repository.VcutOperationMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link VcutOperationMaster}.
 */
@RestController
@RequestMapping("/api")
public class VcutOperationMasterResource {

    private final Logger log = LoggerFactory.getLogger(VcutOperationMasterResource.class);

    private static final String ENTITY_NAME = "vcutOperationMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VcutOperationMasterRepository vcutOperationMasterRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    public VcutOperationMasterResource(VcutOperationMasterRepository vcutOperationMasterRepository) {
        this.vcutOperationMasterRepository = vcutOperationMasterRepository;
    }

    /**
     * {@code POST  /vcut-operation-masters} : Create a new vcutOperationMaster.
     *
     * @param vcutOperationMaster the vcutOperationMaster to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vcutOperationMaster, or with status {@code 400 (Bad Request)} if the vcutOperationMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/vcut-operation-masters")
    public ResponseEntity<VcutOperationMaster> createVcutOperationMaster(@Valid @RequestBody VcutOperationMasterBean vcutOperationMaster) throws URISyntaxException {
        log.debug("REST request to save VcutOperationMaster : {}", vcutOperationMaster);
        VcutOperationMaster result = null;
        if (vcutOperationMaster != null && vcutOperationMaster.getItemName() != null && vcutOperationMaster.getStyle() != null) {
            String itemName = vcutOperationMaster.getItemName();
            String style = vcutOperationMaster.getStyle();
            for (VcutOperationMaster operationMaster : vcutOperationMaster.getVcutOperationMasters()) {
                operationMaster.setStyle(style.toUpperCase());
                operationMaster.setItemName(itemName.toUpperCase());
                result = vcutOperationMasterRepository.save(operationMaster);
            }
            return ResponseEntity.created(new URI("/api/vcut-operation-masters/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
                .body(result);
        } else {
            return ResponseEntity.badRequest()
                .headers(HeaderUtil.createFailureAlert(applicationName, false, ENTITY_NAME, "400", "Record Not save")).build();
        }
    }

    /**
     * {@code PUT  /vcut-operation-masters} : Updates an existing vcutOperationMaster.
     *
     * @param vcutOperationMaster the vcutOperationMaster to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vcutOperationMaster,
     * or with status {@code 400 (Bad Request)} if the vcutOperationMaster is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vcutOperationMaster couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/vcut-operation-masters")
    public ResponseEntity<VcutOperationMaster> updateVcutOperationMaster(@Valid @RequestBody VcutOperationMaster vcutOperationMaster) throws URISyntaxException {
        log.debug("REST request to update VcutOperationMaster : {}", vcutOperationMaster);
        if (vcutOperationMaster.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VcutOperationMaster result = vcutOperationMasterRepository.save(vcutOperationMaster);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vcutOperationMaster.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vcut-operation-masters} : get all the vcutOperationMasters.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutOperationMasters in body.
     */
    @GetMapping("/vcut-operation-masters")
    public ResponseEntity<List<VcutOperationMaster>> getAllVcutOperationMasters(Pageable pageable) {
        log.debug("REST request to get a page of VcutOperationMasters");
        Page<VcutOperationMaster> page = vcutOperationMasterRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/vcut-operation-masters-qry")
    @Timed
    public ResponseEntity<List<VcutOperationMaster>> getAllVcutStylePlans(@Valid @RequestBody VcutStylePlanUploadSearch search)  {
        log.debug("REST request to get a page of VcutStylePlanUploads");
        String style = "%";
        String poNo = "%";
        if (search.getStyle() != null) {
            style = search.getStyle().toUpperCase() + "%";
        }
        if (search.getPoNo() != null) {
            poNo = search.getPoNo().toUpperCase() + "%";
        }
        Page<Object[]> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("style").descending()));
        page = vcutOperationMasterRepository.findByStyleNoAndItem(style,poNo,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        List<VcutOperationMaster> vcutOperationMasters = new ArrayList<>();
        if(page != null && page.getContent() != null) {
            for(Object[] objects : page.getContent()) {
                VcutOperationMaster vcutOperationMaster = new VcutOperationMaster();
                vcutOperationMaster.setItemName(objects[0].toString());
                vcutOperationMaster.setStyle(objects[1].toString());
                vcutOperationMasters.add(vcutOperationMaster);
            }
        }
        return ResponseEntity.ok().headers(headers).body(vcutOperationMasters);
    }

    /**
     * {@code GET  /vcut-operation-masters/:id} : get the "id" vcutOperationMaster.
     *
     * @param id the id of the vcutOperationMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vcutOperationMaster, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vcut-operation-masters/{id}")
    public ResponseEntity<VcutOperationMaster> getVcutOperationMaster(@PathVariable Long id) {
        log.debug("REST request to get VcutOperationMaster : {}", id);
        Optional<VcutOperationMaster> vcutOperationMaster = vcutOperationMasterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(vcutOperationMaster);
    }

    /**
     * {@code GET  /vcut-operation-masters/:id} : get the "id" vcutOperationMaster.
     *
     * @param id the id of the vcutOperationMaster to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vcutOperationMaster, or with status {@code 404 (Not Found)}.
     */
    @PostMapping("/vcut-operation-masters-style")
    public ResponseEntity<VcutOperationMasterBean> getVcutOperationMaster(@RequestBody Master master) {
        List<VcutOperationMaster> vcutOperationMasters = vcutOperationMasterRepository.findByStyle(master.getDesc());
        VcutOperationMasterBean vcutOperationMaster = new VcutOperationMasterBean();
        if (vcutOperationMasters != null && vcutOperationMasters.size() > 0) {
            vcutOperationMaster.setStyle(vcutOperationMasters.get(0).getStyle());
            vcutOperationMaster.setItemName(vcutOperationMasters.get(0).getItemName());
            vcutOperationMaster.setVcutOperationMasters(vcutOperationMasters);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vcutOperationMaster));
    }

    /**
     * {@code DELETE  /vcut-operation-masters/:id} : delete the "id" vcutOperationMaster.
     *
     * @param id the id of the vcutOperationMaster to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vcut-operation-masters/{id}")
    public ResponseEntity<Void> deleteVcutOperationMaster(@PathVariable Long id) {
        log.debug("REST request to delete VcutOperationMaster : {}", id);
        vcutOperationMasterRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * POST  /asset-file-upload-masters : Create a new assetFileUploadMaster.
     *
     * @param !assetFileUploadMaster the assetFileUploadMaster to create
     * @return the ResponseEntity with status 201 (Created) and with body the new assetFileUploadMaster, or with status 400 (Bad Request) if the assetFileUploadMaster has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping(value = "/vcut-operation-masters-excel", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<Message> createVcutOperationMaster(@RequestParam(required = false) MultipartFile[] file) throws IOException, ParseException {
        String errorLog = "";
        log.debug("REST request to save StyleExcelUpload : {}");
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        try {
            if (file != null && file.length > 0) {
                for (MultipartFile multipartFile : file) {
                    byte[] bytes = multipartFile.getBytes();
                    Path path = Paths.get(UPLOADED_FOLDER + "styles/" + multipartFile.getOriginalFilename());
                    Files.write(path, bytes);

                    String fileName = multipartFile.getOriginalFilename();

                    File readFile = new File(UPLOADED_FOLDER + "styles/" + fileName);

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
                    Row rowItem = sheet.getRow(3);
                    String item = "";
                    if (rowItem.getCell(2) != null) {
                        rowItem.getCell(2).setCellType(CellType.STRING);
                        item = rowItem.getCell(2).getStringCellValue();
                    }

                    Row rowStyle = sheet.getRow(4);
                    String style = "";
                    if (rowStyle.getCell(2) != null) {
                        rowStyle.getCell(2).setCellType(CellType.STRING);
                        style = rowStyle.getCell(2).getStringCellValue();
                    }
                    if(style != null && style.length()>0) {
                        for (int i = 7; i < rowCount + 1; i++) {
                            Row row = sheet.getRow(i);
                            if (row.getCell(3) != null && row.getCell(3).getStringCellValue() != null && row.getCell(3).getStringCellValue().length() > 0) {
                                VcutOperationMaster vcutOperationMaster = new VcutOperationMaster();
                                vcutOperationMaster.setStyle(style);
                                vcutOperationMaster.setItemName(item);
                                vcutOperationMaster.setDescription(row.getCell(3).getStringCellValue());
                                vcutOperationMaster.setDescriptionLocal(row.getCell(4).getStringCellValue());
                                vcutOperationMaster.setMachine(row.getCell(5).getStringCellValue());


                                if (row.getCell(6) != null) {
                                    row.getCell(6).setCellType(CellType.NUMERIC);
                                    vcutOperationMaster.setSmv(row.getCell(6).getNumericCellValue());
                                }

                                if (row.getCell(7) != null) {
                                    row.getCell(7).setCellType(CellType.BOOLEAN);
                                    vcutOperationMaster.setInspection(row.getCell(7).getBooleanCellValue());
                                }

                                VcutOperationMaster operationMaster = vcutOperationMasterRepository.findByStyleAndDescription(vcutOperationMaster.getStyle().toUpperCase(), vcutOperationMaster.getDescription().toUpperCase());
                                if (operationMaster != null) {
                                    vcutOperationMaster.setId(operationMaster.getId());
                                    vcutOperationMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                    vcutOperationMaster.setCreatedDate(Instant.now());
                                } else {
                                    vcutOperationMaster.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                    vcutOperationMaster.setLastUpdatedDate(Instant.now());
                                }
                                vcutOperationMasterRepository.save(vcutOperationMaster);
                            }
                        }
                    } else {
                        return ResponseEntity.badRequest().body(new Message("ERROR", "Style not available"));
                    }
                }
            }
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(new Message("ERROR", e.getMessage()));
        }
        return ResponseEntity.ok().body(new Message("SUCCESS", errorLog));
    }
}

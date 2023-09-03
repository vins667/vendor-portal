package io.vamani.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.HeaderUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.config.Constants;
import io.vamani.application.domain.OrderpartnerUpload;
import io.vamani.application.domain.TdsDeclarationUpload;
import io.vamani.application.domain.TdsGroupMaster;
import io.vamani.application.repository.OrderpartnerUploadRepository;
import io.vamani.application.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

/**
 * REST controller for managing Orderpartner.
 */
@RestController
@RequestMapping("/api")
public class OrderpartnerUploadResource {

    private final Logger log = LoggerFactory.getLogger(OrderpartnerUploadResource.class);

    private static final String ENTITY_NAME = "orderpartnerUpload";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private ApplicationProperties applicationProperties;

    private final OrderpartnerUploadRepository orderpartnerUploadRepository;

    public OrderpartnerUploadResource(OrderpartnerUploadRepository orderpartnerUploadRepository) {
        this.orderpartnerUploadRepository = orderpartnerUploadRepository;
    }/**
     * {@code POST  /tds-declaration-uploads} : Create a new tdsDeclarationUpload.
     *
     * @param tdsDeclarationUpload the tdsDeclarationUpload to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tdsDeclarationUpload, or with status {@code 400 (Bad Request)} if the tdsDeclarationUpload has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException
     */
    @PostMapping(value = "/orderpartner-uploads", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<OrderpartnerUpload> createOrderpartnerUpload(@RequestParam(required = false) MultipartFile[] file, String documentType, String customersuppliertype, String customersuppliercode) throws URISyntaxException, IOException {
        log.debug("REST request to save TdsDeclarationUpload : {}");
        OrderpartnerUpload result = null;
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        if (file != null && file.length > 0) {
            for (MultipartFile myFile : file) {
                OrderpartnerUpload orderpartnerUpload = new OrderpartnerUpload();
                orderpartnerUpload.setCompanycode(Constants.COMPANY_CODE);
                orderpartnerUpload.setCustomersuppliertype(customersuppliertype);
                orderpartnerUpload.setCustomersuppliercode(customersuppliercode);
                orderpartnerUpload.setDocumentType(documentType);
                orderpartnerUpload.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                orderpartnerUpload.setCreatedDate(Instant.now());
                result = orderpartnerUploadRepository.save(orderpartnerUpload);
                if (result != null) {
                    String extn = myFile.getOriginalFilename().substring(myFile.getOriginalFilename().lastIndexOf("."), myFile.getOriginalFilename().length());
                    String fileName = result.getId() + extn;
                    result.setFileName(fileName);
                    result.setOriginalFileName(myFile.getOriginalFilename());
                    byte[] bytes = myFile.getBytes();
                    Path path = Paths.get(UPLOADED_FOLDER + "orderpartner/" + fileName);
                    Files.write(path, bytes);
                    orderpartnerUploadRepository.save(result);
                }
            }
        }
        return ResponseEntity.created(new URI("/api/orderpartner-uploads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/orderpartner-uploads-download/{id}")
    @Timed
    public ResponseEntity<Object> getOrderpartnerUploadsDownload(@PathVariable Long id) throws FileNotFoundException, IOException {
        log.debug("REST request to get Feedback : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        OrderpartnerUpload orderpartnerUpload = orderpartnerUploadRepository.findById(id).orElse(null);
        File file = new File(UPLOADED_FOLDER + "orderpartner/" + orderpartnerUpload.getFileName());
        Path path = Paths.get(UPLOADED_FOLDER + "orderpartner/" + orderpartnerUpload.getFileName());
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
}

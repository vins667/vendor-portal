package io.vamani.application.web.rest;

import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.MonthlyNewsData;
import io.vamani.application.repository.MonthlyNewsDataRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import com.codahale.metrics.annotation.Timed;
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
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.vamani.application.domain.MonthlyNewsData}.
 */
@RestController
@RequestMapping("/api")
public class MonthlyNewsDataResource {

    private final Logger log = LoggerFactory.getLogger(MonthlyNewsDataResource.class);

    private static final String ENTITY_NAME = "monthlyNewsData";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private ApplicationProperties applicationProperties;
    
    private final MonthlyNewsDataRepository monthlyNewsDataRepository;

    public MonthlyNewsDataResource(MonthlyNewsDataRepository monthlyNewsDataRepository) {
        this.monthlyNewsDataRepository = monthlyNewsDataRepository;
    }

    /**
     * {@code POST  /monthly-news-data} : Create a new monthlyNewsData.
     *
     * @param monthlyNewsData the monthlyNewsData to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new monthlyNewsData, or with status {@code 400 (Bad Request)} if the monthlyNewsData has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException 
     */
    @PostMapping( value="/monthly-news-data" , consumes = {"multipart/form-data"})
    public ResponseEntity<MonthlyNewsData> createMonthlyNewsData(@RequestParam(required = false) MultipartFile file) throws URISyntaxException, IOException {
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        MonthlyNewsData result =null;
        MonthlyNewsData  monthlyNews = monthlyNewsDataRepository.findAllClosedDate();
        if(monthlyNews!=null) {
        	monthlyNews.setClosedDate(Instant.now());
        	monthlyNewsDataRepository.save(monthlyNews);
        }
        if (file != null) {
            	MonthlyNewsData  monthlyNewsData =  new MonthlyNewsData();
            	monthlyNewsData.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
            	monthlyNewsData.setCreatedDate(Instant.now());
                monthlyNewsData.setFileName("Demo");
                result = monthlyNewsDataRepository.save(monthlyNewsData);
                String extn = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
                result.setFileName(result.getId() + extn);
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + "news-letter/" + result.getId() + extn);
                Files.write(path, bytes);
                result = monthlyNewsDataRepository.save(monthlyNewsData);
        }
        return ResponseEntity.created(new URI("/api/monthly-news-data/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /monthly-news-data} : Updates an existing monthlyNewsData.
     *
     * @param monthlyNewsData the monthlyNewsData to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated monthlyNewsData,
     * or with status {@code 400 (Bad Request)} if the monthlyNewsData is not valid,
     * or with status {@code 500 (Internal Server Error)} if the monthlyNewsData couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/monthly-news-data")
    public ResponseEntity<MonthlyNewsData> updateMonthlyNewsData(@Valid @RequestBody MonthlyNewsData monthlyNewsData) throws URISyntaxException {
        log.debug("REST request to update MonthlyNewsData : {}", monthlyNewsData);
        if (monthlyNewsData.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MonthlyNewsData result = monthlyNewsDataRepository.save(monthlyNewsData);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, monthlyNewsData.getId().toString()))
            .body(result);
    }
    
    
    @GetMapping("/monthly-news-data-download/{id}")
    @Timed
    public ResponseEntity<Object> getMonthlyNewsDataDownload(@PathVariable Long id) throws FileNotFoundException, IOException{
        log.debug("REST request to get Feedback : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        MonthlyNewsData monthlyNewsData = monthlyNewsDataRepository.findById(id).orElse(null);;
        File file = new File(UPLOADED_FOLDER + "news-letter/"+monthlyNewsData.getFileName());
        Path path = Paths.get(UPLOADED_FOLDER + "news-letter/" + monthlyNewsData.getFileName());
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
     * {@code GET  /monthly-news-data} : get all the monthlyNewsData.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of monthlyNewsData in body.
     */
    @GetMapping("/monthly-news-data")
    public ResponseEntity<List<MonthlyNewsData>> getAllMonthlyNewsData(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of MonthlyNewsData");
        Page<MonthlyNewsData> page = monthlyNewsDataRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /monthly-news-data/:id} : get the "id" monthlyNewsData.
     *
     * @param id the id of the monthlyNewsData to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the monthlyNewsData, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/monthly-news-data/{id}")
    public ResponseEntity<MonthlyNewsData> getMonthlyNewsData(@PathVariable Long id) {
        log.debug("REST request to get MonthlyNewsData : {}", id);
        Optional<MonthlyNewsData> monthlyNewsData = monthlyNewsDataRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(monthlyNewsData);
    }

    /**
     * {@code DELETE  /monthly-news-data/:id} : delete the "id" monthlyNewsData.
     *
     * @param id the id of the monthlyNewsData to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/monthly-news-data/{id}")
    public ResponseEntity<Void> deleteMonthlyNewsData(@PathVariable Long id) {
        log.debug("REST request to delete MonthlyNewsData : {}", id);
        monthlyNewsDataRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

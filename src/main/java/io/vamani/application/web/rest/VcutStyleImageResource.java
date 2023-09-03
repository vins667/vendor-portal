package io.vamani.application.web.rest;

import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.VcutStyleImage;
import io.vamani.application.model.VcutStylePlanUploadSearch;
import io.vamani.application.repository.VcutStyleImageRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.MD5UrlEncryption;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import com.codahale.metrics.annotation.Timed;
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
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link VcutStyleImage}.
 */
@RestController
@RequestMapping("/api")
public class VcutStyleImageResource {

    private final Logger log = LoggerFactory.getLogger(VcutStyleImageResource.class);

    private static final String ENTITY_NAME = "vcutStyleImage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VcutStyleImageRepository vcutStyleImageRepository;
    
    @Autowired
    private ApplicationProperties applicationProperties;

    public VcutStyleImageResource(VcutStyleImageRepository vcutStyleImageRepository) {
        this.vcutStyleImageRepository = vcutStyleImageRepository;
    }

    /**
     * {@code POST  /vcut-style-images} : Create a new vcutStyleImage.
     *
     * @param vcutStyleImage the vcutStyleImage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new vcutStyleImage, or with status {@code 400 (Bad Request)} if the vcutStyleImage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException 
     */
    @PostMapping(value="/vcut-style-images",consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<VcutStyleImage> createVcutStyleImage(@RequestParam(required = false) MultipartFile frontImage,MultipartFile backImage, String style) throws URISyntaxException, IOException {
        if (vcutStyleImageRepository.findByStyle(style.toUpperCase()) != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,Boolean.TRUE,"","Style Already Exists", "")).body(null);
        }
    	String UPLOADED_FOLDER = applicationProperties.getUploadPath();
    	VcutStyleImage vcutStyleImage =new VcutStyleImage();
    	vcutStyleImage.setStyle(style.toUpperCase());
    	vcutStyleImage.setFrontImage("Demo1");
    	vcutStyleImage.setBackImage("Demo2");
    	vcutStyleImage.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
    	vcutStyleImage.setCreatedDate(Instant.now());
        VcutStyleImage result = vcutStyleImageRepository.save(vcutStyleImage);
        if(frontImage!=null &&  backImage!=null && !frontImage.isEmpty() && !backImage.isEmpty()) {
            String extn1 = frontImage.getOriginalFilename().substring(frontImage.getOriginalFilename().lastIndexOf("."), frontImage.getOriginalFilename().length());
            String extn2 = backImage.getOriginalFilename().substring(backImage.getOriginalFilename().lastIndexOf("."), backImage.getOriginalFilename().length());
            String fileName1 = result.getId()+"_F" + extn1;
            String fileName2 = result.getId()+"_B" + extn2;
            result.setFrontImage(fileName1);
            result.setBackImage(fileName2);
            byte[] bytes1 = frontImage.getBytes();
            byte[] bytes2 = backImage.getBytes();
            Path path1 = Paths.get(UPLOADED_FOLDER + "style-sketch/" + fileName1);
            Path path2 = Paths.get(UPLOADED_FOLDER + "style-sketch/" + fileName2);
            Files.write(path1, bytes1);
            Files.write(path2, bytes2);
            result = vcutStyleImageRepository.save(result);
        }
        return ResponseEntity.created(new URI("/api/vcut-style-images/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /vcut-style-images} : Updates an existing vcutStyleImage.
     *
     * @param vcutStyleImage the vcutStyleImage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated vcutStyleImage,
     * or with status {@code 400 (Bad Request)} if the vcutStyleImage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the vcutStyleImage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException 
     */
    @PostMapping(value="/vcut-style-images-update",consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<VcutStyleImage> updateVcutStyleImage(@RequestParam(required = false) MultipartFile frontImage,MultipartFile backImage, String style,String id) throws URISyntaxException, IOException {
        if (id == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
    	VcutStyleImage vcutStyleImage =vcutStyleImageRepository.findById(Long.parseLong(id)).orElse(null);
    	vcutStyleImage.setId(vcutStyleImage.getId());
    	vcutStyleImage.setStyle(style.toUpperCase());
    	vcutStyleImage.setLastUpdatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
    	vcutStyleImage.setLastUpdatedDate(Instant.now());
    	VcutStyleImage result = vcutStyleImageRepository.save(vcutStyleImage);
        if(frontImage!=null &&  backImage!=null && !frontImage.isEmpty() && !backImage.isEmpty()) {
            String extn1 = frontImage.getOriginalFilename().substring(frontImage.getOriginalFilename().lastIndexOf("."), frontImage.getOriginalFilename().length());
            String extn2 = backImage.getOriginalFilename().substring(backImage.getOriginalFilename().lastIndexOf("."), backImage.getOriginalFilename().length());
            String fileName1 = result.getId()+"_F" + extn1;
            String fileName2 = result.getId()+"_B" + extn2;
            result.setFrontImage(fileName1);
            result.setBackImage(fileName2);
            byte[] bytes1 = frontImage.getBytes();
            byte[] bytes2 = backImage.getBytes();
            Path path1 = Paths.get(UPLOADED_FOLDER + "style-sketch/" + fileName1);
            Path path2 = Paths.get(UPLOADED_FOLDER + "style-sketch/" + fileName2);
            Files.write(path1, bytes1);
            Files.write(path2, bytes2);
            result = vcutStyleImageRepository.save(result);
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, vcutStyleImage.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /vcut-style-images} : get all the vcutStyleImages.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStyleImages in body.
     */
    @GetMapping("/vcut-style-images")
    public ResponseEntity<List<VcutStyleImage>> getAllVcutStyleImages(Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get a page of VcutStyleImages");
        Page<VcutStyleImage> page = vcutStyleImageRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }



    /**
     * {@code GET  /vcut-style-images} : get all the vcutStyleImages.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of vcutStyleImages in body.
     */
    @PostMapping("/vcut-style-images-qry")
    @Timed
    public ResponseEntity<List<VcutStyleImage>> getAllVcutStyleImages(@Valid @RequestBody VcutStylePlanUploadSearch search) {
        String style = "%";
        if (search.getStyle() != null) {
            style = search.getStyle().trim().toUpperCase() + "%";
        }
        Page<VcutStyleImage> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("id").ascending()));
        page = vcutStyleImageRepository.findAllByStyle(style,search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /vcut-style-images/:id} : get the "id" vcutStyleImage.
     *
     * @param id the id of the vcutStyleImage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the vcutStyleImage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/vcut-style-images/{id}")
    public ResponseEntity<VcutStyleImage> getVcutStyleImage(@PathVariable Long id) throws NoSuchAlgorithmException, ParseException {
        log.debug("REST request to get VcutStyleImage : {}", id);
        VcutStyleImage vcutStyleImage = vcutStyleImageRepository.findById(id).orElse(null);
        if(vcutStyleImage != null) {
            vcutStyleImage.setBackImage(MD5UrlEncryption.fakeUploadUrl("style-sketch/" + vcutStyleImage.getBackImage()));
            vcutStyleImage.setFrontImage(MD5UrlEncryption.fakeUploadUrl("style-sketch/" + vcutStyleImage.getFrontImage()));
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vcutStyleImage));
    }

    /**
     * {@code DELETE  /vcut-style-images/:id} : delete the "id" vcutStyleImage.
     *
     * @param id the id of the vcutStyleImage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/vcut-style-images/{id}")
    public ResponseEntity<Void> deleteVcutStyleImage(@PathVariable Long id) {
        log.debug("REST request to delete VcutStyleImage : {}", id);
        vcutStyleImageRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

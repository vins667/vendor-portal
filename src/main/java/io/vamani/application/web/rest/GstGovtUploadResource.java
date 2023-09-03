package io.vamani.application.web.rest;

import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.GstGovtUpload;
import io.vamani.application.domain.GstVoplUpload;
import io.vamani.application.model.Message;
import io.vamani.application.repository.GstGovtUploadRepository;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing
 * {@link io.vamani.application.domain.GstGovtUpload}.
 */
@RestController
@RequestMapping("/api")
public class GstGovtUploadResource {

	private static DecimalFormat df = new DecimalFormat("0.00"); 
	
	private final Logger log = LoggerFactory.getLogger(GstGovtUploadResource.class);

	private static final String ENTITY_NAME = "gstGovtUpload";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	@Autowired
	private ApplicationProperties applicationProperties;

	private final GstGovtUploadRepository gstGovtUploadRepository;

	public GstGovtUploadResource(GstGovtUploadRepository gstGovtUploadRepository) {
		this.gstGovtUploadRepository = gstGovtUploadRepository;
	}

	/**
	 * {@code POST  /gst-govt-uploads} : Create a new gstGovtUpload.
	 *
	 * @param gstGovtUpload the gstGovtUpload to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new gstGovtUpload, or with status {@code 400 (Bad Request)}
	 *         if the gstGovtUpload has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/gst-govt-uploads")
	public ResponseEntity<GstGovtUpload> createGstGovtUpload(@Valid @RequestBody GstGovtUpload gstGovtUpload)
			throws URISyntaxException {
		log.debug("REST request to save GstGovtUpload : {}", gstGovtUpload);
		if (gstGovtUpload.getId() != null) {
			throw new BadRequestAlertException("A new gstGovtUpload cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		GstGovtUpload result = gstGovtUploadRepository.save(gstGovtUpload);
		return ResponseEntity
				.created(new URI("/api/gst-govt-uploads/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code PUT  /gst-govt-uploads} : Updates an existing gstGovtUpload.
	 *
	 * @param gstGovtUpload the gstGovtUpload to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated gstGovtUpload, or with status {@code 400 (Bad Request)}
	 *         if the gstGovtUpload is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the gstGovtUpload couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/gst-govt-uploads")
	public ResponseEntity<GstGovtUpload> updateGstGovtUpload(@Valid @RequestBody GstGovtUpload gstGovtUpload)
			throws URISyntaxException {
		log.debug("REST request to update GstGovtUpload : {}", gstGovtUpload);
		if (gstGovtUpload.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		GstGovtUpload result = gstGovtUploadRepository.save(gstGovtUpload);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
				gstGovtUpload.getId().toString())).body(result);
	}

	@PostMapping(value = "/gst-govt-uploads-excel", consumes = { "multipart/form-data" })
	public ResponseEntity<Message> LoadExcel(@RequestParam(required = false) MultipartFile[] file)
			throws URISyntaxException {
		String UPLOADED_FOLDER = applicationProperties.getUploadPath();
		String errorLog = "";

		List<GstGovtUpload> lists = new ArrayList<GstGovtUpload>();
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
					// Find the file extension by splitting file name in substring and getting only
					// extension name
					String fileExtensionName = fileName.substring(fileName.indexOf("."));
					// Check condition if the file is xlsx file
					if (fileExtensionName.equals(".xlsx")) {
						// If it is xlsx file then create object of XSSFWorkbook class
						workbook = new XSSFWorkbook(inputStream);
					} else {
						workbook = new HSSFWorkbook(inputStream);
					}
					// Read sheet inside the workbook by its name
					Sheet sheet = workbook.getSheetAt(0);
					// Find number of rows in excel file
					int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

					for (int i = 1; i < rowCount + 1; i++) {
						Row row = sheet.getRow(i);
						if (row.getCell(0) != null) {
							GstGovtUpload gstGovtUpload = new GstGovtUpload();

							if (row.getCell(0) != null) {
								row.getCell(0).setCellType(CellType.STRING);
								gstGovtUpload.setgGstin(row.getCell(0).getStringCellValue().trim());
							}

							if (row.getCell(1) != null) {
								row.getCell(1).setCellType(CellType.STRING);
								gstGovtUpload.setgSupplier(row.getCell(1).getStringCellValue().trim());
							}

							if (row.getCell(2) != null) {
								row.getCell(2).setCellType(CellType.STRING);
								// System.out.println("cell 02: "+row.getCell(2).getStringCellValue().trim());
								gstGovtUpload.setgInvno(row.getCell(2).getStringCellValue().trim());
							}

							if (row.getCell(5) != null) {
								row.getCell(5).setCellType(CellType.NUMERIC);
								gstGovtUpload.setgInvamt(new Double(df.format(row.getCell(5).getNumericCellValue())));
							}

							if (row.getCell(10) != null) {
								row.getCell(10).setCellType(CellType.NUMERIC);
								gstGovtUpload.setgIgst(new Double(df.format(row.getCell(10).getNumericCellValue())));
							}

							if (row.getCell(11) != null) {
								row.getCell(11).setCellType(CellType.NUMERIC);
								gstGovtUpload.setgCgst(new Double(df.format(row.getCell(11).getNumericCellValue())));
							}

							if (row.getCell(12) != null) {
								row.getCell(12).setCellType(CellType.NUMERIC);
								gstGovtUpload.setgSgst(new Double(df.format(row.getCell(12).getNumericCellValue())));
							}

							gstGovtUpload.setgStatus("0");
							gstGovtUpload.setgConfirmdate(Instant.now());
							gstGovtUpload.setgReverse("100");
							gstGovtUpload.setgInvtype("T1");
							gstGovtUpload.setgState("UP");
							gstGovtUpload.setgSrlno("12345");
							gstGovtUpload.setgLocation("test loc 1");
							// lists.add(gstVoplUpload);
							GstGovtUpload result = gstGovtUploadRepository.save(gstGovtUpload);
						}
					}
				}
			}
		} catch (Exception e) {
			return null;
		}
		return ResponseEntity.ok().body(new Message("SUCCESS", errorLog));
	}

	/**
	 * {@code GET  /gst-govt-uploads} : get all the gstGovtUploads.
	 *
	 * 
	 * @param pageable the pagination information.
	 * 
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of gstGovtUploads in body.
	 */
	@GetMapping("/gst-govt-uploads")
	public ResponseEntity<List<GstGovtUpload>> getAllGstGovtUploads(Pageable pageable) {
		log.debug("REST request to get a page of GstGovtUploads");
		Page<GstGovtUpload> page = gstGovtUploadRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil
				.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /gst-govt-uploads/:id} : get the "id" gstGovtUpload.
	 *
	 * @param id the id of the gstGovtUpload to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the gstGovtUpload, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/gst-govt-uploads/{id}")
	public ResponseEntity<GstGovtUpload> getGstGovtUpload(@PathVariable Long id) {
		log.debug("REST request to get GstGovtUpload : {}", id);
		Optional<GstGovtUpload> gstGovtUpload = gstGovtUploadRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(gstGovtUpload);
	}

	/**
	 * {@code DELETE  /gst-govt-uploads/:id} : delete the "id" gstGovtUpload.
	 *
	 * @param id the id of the gstGovtUpload to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/gst-govt-uploads/{id}")
	public ResponseEntity<Void> deleteGstGovtUpload(@PathVariable Long id) {
		log.debug("REST request to delete GstGovtUpload : {}", id);
		gstGovtUploadRepository.deleteById(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}
}

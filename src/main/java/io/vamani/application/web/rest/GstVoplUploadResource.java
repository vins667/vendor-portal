package io.vamani.application.web.rest;

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

import javax.validation.Valid;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.GstVoplUpload;
import io.vamani.application.model.Message;
import io.vamani.application.repository.GstVoplUploadRepository;
import io.vamani.application.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing
 * {@link io.vamani.application.domain.GstVoplUpload}.
 */
@RestController
@RequestMapping("/api")
public class GstVoplUploadResource {

	private static DecimalFormat df = new DecimalFormat("0.00"); 
	
	private final Logger log = LoggerFactory.getLogger(GstVoplUploadResource.class);

	private static final String ENTITY_NAME = "gstVoplUpload";

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	@Autowired
	private GstVoplUploadRepository gstVoplUploadRepository;

	@Autowired
	private ApplicationProperties applicationProperties;

	public GstVoplUploadResource(GstVoplUploadRepository gstVoplUploadRepository) {
		this.gstVoplUploadRepository = gstVoplUploadRepository;
	}

	/**
	 * {@code POST  /gst-vopl-uploads} : Create a new gstVoplUpload.
	 *
	 * @param gstVoplUpload the gstVoplUpload to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new gstVoplUpload, or with status {@code 400 (Bad Request)}
	 *         if the gstVoplUpload has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/gst-vopl-uploads")
	public ResponseEntity<GstVoplUpload> createGstVoplUpload(@Valid @RequestBody GstVoplUpload gstVoplUpload)
			throws URISyntaxException {
		log.debug("REST request to save GstVoplUpload : {}", gstVoplUpload);
		if (gstVoplUpload.getId() != null) {
			throw new BadRequestAlertException("A new gstVoplUpload cannot already have an ID", ENTITY_NAME,
					"idexists");
		}

		GstVoplUpload result = gstVoplUploadRepository.save(gstVoplUpload);
		return ResponseEntity
				.created(new URI("/api/gst-vopl-uploads/" + result.getId())).headers(HeaderUtil
						.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
				.body(result);
	}

	/**
	 * {@code POST  /gst-vopl-uploads} : Create a new gstVoplUpload.
	 *
	 * @param gstVoplUpload the gstVoplUpload to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new gstVoplUpload, or with status {@code 400 (Bad Request)}
	 *         if the gstVoplUpload has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping(value = "/gst-vopl-uploads-excel", consumes = { "multipart/form-data" })
	 public ResponseEntity<Message> LoadExcel(@RequestParam(required = false) MultipartFile[] file)
			throws URISyntaxException {
		String UPLOADED_FOLDER = applicationProperties.getUploadPath();
		
		String errorLog = "";

		List<GstVoplUpload> lists = new ArrayList<GstVoplUpload>();
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
							GstVoplUpload gstVoplUpload = new GstVoplUpload();

							if (row.getCell(1) != null) {
								row.getCell(1).setCellType(CellType.STRING);
								gstVoplUpload.setvSupplierCode(row.getCell(1).getStringCellValue().trim());
							}

							if (row.getCell(2) != null) {
								row.getCell(2).setCellType(CellType.STRING);
								// System.out.println("cell 02: "+row.getCell(2).getStringCellValue().trim());
								gstVoplUpload.setvVchno(row.getCell(2).getStringCellValue().trim());
							}

							if (row.getCell(3) != null) {
								Date uploadDate = row.getCell(3).getDateCellValue();
								gstVoplUpload.setvVchdate(uploadDate.toInstant());
							}

							if (row.getCell(5) != null) {
								row.getCell(5).setCellType(CellType.STRING);
								gstVoplUpload.setvGstin(row.getCell(5).getStringCellValue().trim());
							}

							if (row.getCell(6) != null) {
								row.getCell(6).setCellType(CellType.STRING);
								gstVoplUpload.setvSupplierName(row.getCell(6).getStringCellValue().trim());
							}

							if (row.getCell(7) != null) {
								row.getCell(7).setCellType(CellType.STRING);
								gstVoplUpload.setvInvoiceno(row.getCell(7).getStringCellValue().trim());
							}

							if (row.getCell(9) != null) {
								row.getCell(9).setCellType(CellType.NUMERIC);
								gstVoplUpload.setvInvamt(new Double(df.format(row.getCell(9).getNumericCellValue())));
							}

							if (row.getCell(10) != null) {
								row.getCell(10).setCellType(CellType.NUMERIC);
								gstVoplUpload.setvInvnet(new Double(df.format(row.getCell(10).getNumericCellValue())));
							}

							if (row.getCell(11) != null) {
								row.getCell(11).setCellType(CellType.NUMERIC);
								gstVoplUpload.setvCgst(new Double(df.format(row.getCell(11).getNumericCellValue())));
							}

							if (row.getCell(12) != null) {
								row.getCell(12).setCellType(CellType.NUMERIC);
								gstVoplUpload.setvSgst(new Double(df.format(row.getCell(12).getNumericCellValue())));
							}

							if (row.getCell(13) != null) {
								row.getCell(13).setCellType(CellType.NUMERIC);
								gstVoplUpload.setvIgst(new Double(df.format(row.getCell(13).getNumericCellValue())));
							}
							gstVoplUpload.setStatus("0");
							gstVoplUpload.setUploadDate(Instant.now());
							// lists.add(gstVoplUpload);
							GstVoplUpload result = gstVoplUploadRepository.save(gstVoplUpload);
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
	 * {@code PUT  /gst-vopl-uploads} : Updates an existing gstVoplUpload.
	 *
	 * @param gstVoplUpload the gstVoplUpload to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated gstVoplUpload, or with status {@code 400 (Bad Request)}
	 *         if the gstVoplUpload is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the gstVoplUpload couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/gst-vopl-uploads")
	public ResponseEntity<GstVoplUpload> updateGstVoplUpload(@Valid @RequestBody GstVoplUpload gstVoplUpload)
			throws URISyntaxException {
		log.debug("REST request to update GstVoplUpload : {}", gstVoplUpload);
		if (gstVoplUpload.getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		GstVoplUpload result = gstVoplUploadRepository.save(gstVoplUpload);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME,
				gstVoplUpload.getId().toString())).body(result);
	}

	/**
	 * {@code GET  /gst-vopl-uploads} : get all the gstVoplUploads.
	 *
	 * 
	 * @param pageable the pagination information.
	 * 
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of gstVoplUploads in body.
	 */
	@GetMapping("/gst-vopl-uploads")
	public ResponseEntity<List<GstVoplUpload>> getAllGstVoplUploads(Pageable pageable) {
		log.debug("REST request to get a page of GstVoplUploads");
		Page<GstVoplUpload> page = gstVoplUploadRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil
				.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
		return ResponseEntity.ok().headers(headers).body(page.getContent());
	}

	/**
	 * {@code GET  /gst-vopl-uploads/:id} : get the "id" gstVoplUpload.
	 *
	 * @param id the id of the gstVoplUpload to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the gstVoplUpload, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/gst-vopl-uploads/{id}")
	public ResponseEntity<GstVoplUpload> getGstVoplUpload(@PathVariable Long id) {
		log.debug("REST request to get GstVoplUpload : {}", id);
		Optional<GstVoplUpload> gstVoplUpload = gstVoplUploadRepository.findById(id);
		return ResponseUtil.wrapOrNotFound(gstVoplUpload);
	}

	/**
	 * {@code DELETE  /gst-vopl-uploads/:id} : delete the "id" gstVoplUpload.
	 *
	 * @param id the id of the gstVoplUpload to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/gst-vopl-uploads/{id}")
	public ResponseEntity<Void> deleteGstVoplUpload(@PathVariable Long id) {
		log.debug("REST request to delete GstVoplUpload : {}", id);
		gstVoplUploadRepository.deleteById(id);
		return ResponseEntity.noContent()
				.headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
				.build();
	}
}

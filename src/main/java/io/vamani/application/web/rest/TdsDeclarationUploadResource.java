package io.vamani.application.web.rest;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.vamani.application.model.*;
import io.vamani.application.service.MailService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.engine.jdbc.StreamUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.TdsDeclarationUpload;
import io.vamani.application.domain.TdsGroupMaster;
import io.vamani.application.domain.TdsYearMaster;
import io.vamani.application.mssql.domain.EmployeeMatView;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeMatViewRepository;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.mssql.repository.EmployeeAllViewRepository;
import io.vamani.application.repository.TdsDeclarationUploadRepository;
import io.vamani.application.repository.TdsGroupMasterRepository;
import io.vamani.application.repository.TdsYearMasterRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.thymeleaf.context.Context;

/**
 * REST controller for managing {@link TdsDeclarationUpload}.
 */
@RestController
@RequestMapping("/api")
public class TdsDeclarationUploadResource {

    private final Logger log = LoggerFactory.getLogger(TdsDeclarationUploadResource.class);

    private static final String ENTITY_NAME = "tdsDeclarationUpload";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private TdsGroupMasterRepository tdsGroupMasterRepository;

    @Autowired
    private TdsYearMasterRepository tdsYearMasterRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private EmployeeAllViewRepository employeeAllViewRepository;

    @Autowired
    private EmployeeMatViewRepository employeeMatViewRepository;

    private final TdsDeclarationUploadRepository tdsDeclarationUploadRepository;

    @Autowired
    private MailService mailService;

    public TdsDeclarationUploadResource(TdsDeclarationUploadRepository tdsDeclarationUploadRepository) {
        this.tdsDeclarationUploadRepository = tdsDeclarationUploadRepository;
    }

    /**
     * {@code POST  /tds-declaration-uploads} : Create a new tdsDeclarationUpload.
     *
     * @param tdsDeclarationUpload the tdsDeclarationUpload to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tdsDeclarationUpload, or with status {@code 400 (Bad Request)} if the tdsDeclarationUpload has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException
     */
    @PostMapping(value = "/tds-declaration-uploads", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<TdsDeclarationUpload> createTdsDeclarationUpload(@RequestParam(required = false) MultipartFile[] file, String cardNo, String financialYear, String tdsGroupMaster) throws URISyntaxException, IOException {
        log.debug("REST request to save TdsDeclarationUpload : {}");
        TdsDeclarationUpload result = null;
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        if (file != null && file.length > 0) {
            for (MultipartFile myFile : file) {
                TdsDeclarationUpload tdsDeclarationUpload = new TdsDeclarationUpload();
                tdsDeclarationUpload.setCardNo(cardNo);
                tdsDeclarationUpload.setFinancialYear(financialYear);
                TdsGroupMaster tdsGroupMasters = new TdsGroupMaster();
                tdsGroupMasters.setId(Long.parseLong(tdsGroupMaster));
                tdsDeclarationUpload.setTdsGroupMaster(tdsGroupMasters);
                tdsDeclarationUpload.setCreatedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                tdsDeclarationUpload.setCreatedDate(Instant.now());
                result = tdsDeclarationUploadRepository.save(tdsDeclarationUpload);
                String extn = myFile.getOriginalFilename().substring(myFile.getOriginalFilename().lastIndexOf("."), myFile.getOriginalFilename().length());
                String fileName = result.getId() + extn;
                result.setFileName(fileName);
                result.setOriginalFileName(myFile.getOriginalFilename());
                byte[] bytes = myFile.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + "tds/" + fileName);
                Files.write(path, bytes);
                tdsDeclarationUploadRepository.save(result);
            }
        }
        return ResponseEntity.created(new URI("/api/tds-declaration-uploads/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tds-declaration-uploads} : Updates an existing tdsDeclarationUpload.
     *
     * @param tdsDeclarationUpload the tdsDeclarationUpload to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tdsDeclarationUpload,
     * or with status {@code 400 (Bad Request)} if the tdsDeclarationUpload is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tdsDeclarationUpload couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tds-declaration-uploads")
    public ResponseEntity<TdsDeclarationUpload> updateTdsDeclarationUpload(@Valid @RequestBody TdsDeclarationUpload tdsDeclarationUpload) throws URISyntaxException {
        log.debug("REST request to update TdsDeclarationUpload : {}", tdsDeclarationUpload);
        if (tdsDeclarationUpload.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TdsDeclarationUpload result = tdsDeclarationUploadRepository.save(tdsDeclarationUpload);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, tdsDeclarationUpload.getId().toString()))
            .body(result);
    }


    /**
     * {@code GET  /tds-declaration-uploads} : get all the tdsDeclarationUploads.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tdsDeclarationUploads in body.
     */
    @GetMapping("/tds-declaration-uploads-custom")
    @Timed
    public ResponseEntity<TdsDeclarationUploadBean> getAllTdsDeclarationUploadsbyCard() {
        log.debug("REST request to get a page of TdsDeclarationUploads");
        String cardNo = SecurityUtils.getCurrentUserLogin().orElse(null);
        List<TdsYearMaster> tdsYearMasters = tdsYearMasterRepository.findByActive();
        String year = null;
        if (tdsYearMasters != null && tdsYearMasters.size() > 0) {
            year = tdsYearMasters.get(0).getCode();
        }
        EmployeeView employeeView = employeeViewRepository.findById(cardNo).orElse(null);
        List<TdsGroupMaster> tdsGroupmaster = tdsGroupMasterRepository.findAllYear(Integer.parseInt(year));
        List<TdsGroupMasterBeans> tdsGroupList = new ArrayList<TdsGroupMasterBeans>();
        List<TdsDeclarationUpload> tdsDeclarationUploadList = tdsDeclarationUploadRepository.findByCardNo(cardNo, year);
        TdsDeclarationUploadBean tdsDeclarationBean = new TdsDeclarationUploadBean();
        tdsDeclarationBean.setFinancialYear(year);
        tdsDeclarationBean.setCardNo(employeeView.getCardNo());
        tdsDeclarationBean.setName(employeeView.getName());
        tdsDeclarationBean.setDesignation(employeeView.getDesCodeDesc());
        tdsDeclarationBean.setPanNo(employeeView.getPan());
        tdsDeclarationBean.setDateOfBirth(employeeView.getDob());
        tdsDeclarationBean.setContactNumber(employeeView.getPhone());
        tdsDeclarationBean.setEmailId(employeeView.getEmail());
        tdsDeclarationBean.setAddress(employeeView.getAdd1());
        for (TdsGroupMaster bean : tdsGroupmaster) {
            TdsGroupMasterBeans tdsGroupMasterbean = new TdsGroupMasterBeans();
            if (tdsDeclarationUploadList != null) {
                List<TdsDeclarationUploadDetailBean> tdsDeclarationUploadDetailBeanList = new ArrayList<>();
                for (TdsDeclarationUpload tdsDeclarationUploadTemp : tdsDeclarationUploadList) {
                    if (bean.getId() == tdsDeclarationUploadTemp.getTdsGroupMaster().getId()) {
                        TdsDeclarationUploadDetailBean tdsDeclarationUploadDetailBean = new TdsDeclarationUploadDetailBean();
                        BeanUtils.copyProperties(tdsDeclarationUploadTemp, tdsDeclarationUploadDetailBean);
                        tdsDeclarationUploadDetailBeanList.add(tdsDeclarationUploadDetailBean);
                    }
                }
                tdsGroupMasterbean.setTdsDeclarationUploadDetailBean(tdsDeclarationUploadDetailBeanList);
            }
            BeanUtils.copyProperties(bean, tdsGroupMasterbean);
            tdsGroupList.add(tdsGroupMasterbean);
        }
        tdsDeclarationBean.setTdsGroupMasterbean(tdsGroupList);
        return ResponseUtil.wrapOrNotFound(Optional.of(tdsDeclarationBean));
    }

    /**
     * {@code POST  /tds-declaration-uploads} : Create a new tdsDeclarationUpload.
     *
     * @param tdsDeclarationUpload the tdsDeclarationUpload to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tdsDeclarationUpload, or with status {@code 400 (Bad Request)} if the tdsDeclarationUpload has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException
     */

    @PostMapping("/tds-declaration-uploads-edit")
    @Timed
    public ResponseEntity<TdsDeclarationUploadBean> getAllTdsDeclarationUploadsbyCardAndYear(@Valid @RequestBody TdsDeclarationUploadSearch search) {
        log.debug("REST request to get a page of TdsDeclarationUploads");

        EmployeeView employeeView = employeeViewRepository.findById(search.getCardNo()).orElse(null);
        List<TdsGroupMaster> tdsGroupmaster = tdsGroupMasterRepository.findAllYear(Integer.parseInt(search.getFinancialYear()));
        List<TdsGroupMasterBeans> tdsGroupList = new ArrayList<TdsGroupMasterBeans>();
        List<TdsDeclarationUpload> tdsDeclarationUploadList = tdsDeclarationUploadRepository.findByCardNo(search.getCardNo(), search.getFinancialYear());
        TdsDeclarationUploadBean tdsDeclarationBean = new TdsDeclarationUploadBean();
        tdsDeclarationBean.setFinancialYear(search.getFinancialYear());
        tdsDeclarationBean.setCardNo(employeeView.getCardNo());
        tdsDeclarationBean.setName(employeeView.getName());
        tdsDeclarationBean.setDesignation(employeeView.getDesCodeDesc());
        tdsDeclarationBean.setPanNo(employeeView.getPan());
        tdsDeclarationBean.setDateOfBirth(employeeView.getDob());
        tdsDeclarationBean.setContactNumber(employeeView.getPhone());
        tdsDeclarationBean.setEmailId(employeeView.getEmail());
        tdsDeclarationBean.setAddress(employeeView.getAdd1());
        for (TdsGroupMaster bean : tdsGroupmaster) {
            TdsGroupMasterBeans tdsGroupMasterbean = new TdsGroupMasterBeans();
            if (tdsDeclarationUploadList != null) {
                List<TdsDeclarationUploadDetailBean> tdsDeclarationUploadDetailBeanList = new ArrayList<>();
                for (TdsDeclarationUpload tdsDeclarationUploadTemp : tdsDeclarationUploadList) {
                    if (bean.getId() == tdsDeclarationUploadTemp.getTdsGroupMaster().getId()) {
                        TdsDeclarationUploadDetailBean tdsDeclarationUploadDetailBean = new TdsDeclarationUploadDetailBean();
                        BeanUtils.copyProperties(tdsDeclarationUploadTemp, tdsDeclarationUploadDetailBean);
                        tdsDeclarationUploadDetailBeanList.add(tdsDeclarationUploadDetailBean);
                    }
                }
                tdsGroupMasterbean.setTdsDeclarationUploadDetailBean(tdsDeclarationUploadDetailBeanList);
            }
            BeanUtils.copyProperties(bean, tdsGroupMasterbean);
            tdsGroupList.add(tdsGroupMasterbean);
        }
        tdsDeclarationBean.setTdsGroupMasterbean(tdsGroupList);
        return ResponseUtil.wrapOrNotFound(Optional.of(tdsDeclarationBean));
    }

    /**
     * {@code GET  /tds-declaration-uploads} : get all the tdsDeclarationUploads.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tdsDeclarationUploads in body.
     */
    @PostMapping("/tds-declaration-uploads-qry")
    @Timed
    public ResponseEntity<List<TdsDeclarationUploadBean>> getAllTdsDeclarationUploads(@Valid @RequestBody TdsDeclarationUploadSearch search) {
        log.debug("REST request to get a page of TdsDeclarationUploads");
        List<TdsDeclarationUploadBean> tdsDeclarationUploadBeanList = new ArrayList<>();
        String cardNo = "%";
        if (search.getCardNo() != null) {
            cardNo = search.getCardNo().trim() + "%";
        }
        Page<Object[]> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("card_no").descending()));
        page = tdsDeclarationUploadRepository.findAllCardNo(cardNo, search.getFinancialYear(), search.getPage());
        Map<String, TdsDeclarationUpload> map = new HashMap<>();
        for (Object[] objects : page.getContent()) {
            TdsDeclarationUpload bean = new TdsDeclarationUpload();
            bean.setCardNo(objects[0].toString());
            bean.setFinancialYear(objects[1].toString());
            map.put(objects[0].toString(), bean);
        }
        if (map.keySet() != null && !map.keySet().isEmpty()) {
            List<EmployeeMatView> employeeViewList = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
            for (EmployeeMatView employeeView : employeeViewList) {
                TdsDeclarationUploadBean tdsDeclarationBean = new TdsDeclarationUploadBean();
                TdsDeclarationUpload tdsDeclarationUpload = map.get(employeeView.getLogin());
                tdsDeclarationBean.setFinancialYear(tdsDeclarationUpload.getFinancialYear());
                tdsDeclarationBean.setCardNo(employeeView.getCardNo());
                tdsDeclarationBean.setName(employeeView.getName());
                tdsDeclarationBean.setEmailId(employeeView.getEmail());
                tdsDeclarationBean.setContactNumber(employeeView.getPhone());
                tdsDeclarationBean.setFactory(employeeView.getFactoryDesc());
                tdsDeclarationBean.setDepartment(employeeView.getDepCodeDesc());
                tdsDeclarationBean.setDesignation(employeeView.getDesCodeDesc());
                tdsDeclarationUploadBeanList.add(tdsDeclarationBean);
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(tdsDeclarationUploadBeanList);
    }

    /**
     * {@code GET  /tds-declaration-uploads} : get all the tdsDeclarationUploads.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tdsDeclarationUploads in body.
     */
    @PostMapping("/tds-document-query")
    @Timed
    public ResponseEntity<List<TdsDeclarationUploadBean>> getAllTdsDocumentQuery(@Valid @RequestBody TdsDeclarationUploadSearch search) {
        log.debug("REST request to get a page of TdsDeclarationUploads");
        List<TdsDeclarationUploadBean> tdsDeclarationUploadBeanList = new ArrayList<>();
        String cardNo = "%";
        if (search.getCardNo() != null) {
            cardNo = search.getCardNo().trim() + "%";
        }
        String name = "%";
        if (search.getName() != null) {
            name = search.getName().trim() + "%";
        }
        Page<Object[]> page = null;
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("card_no").descending()));
        page = tdsDeclarationUploadRepository.findAllCardNoAndName(cardNo, name, search.getFinancialYear(), search.getPage());
        Map<String, TdsDeclarationUploadBean> map = new TreeMap<>();
        for (Object[] objects : page.getContent()) {
            TdsDeclarationUploadBean bean = new TdsDeclarationUploadBean();
            bean.setCardNo(objects[0].toString());
            bean.setFinancialYear(objects[1].toString());
            bean.setStatus(objects[2].toString());
            map.put(objects[0].toString(), bean);
        }
        if (map.keySet() != null && !map.keySet().isEmpty()) {
            List<EmployeeMatView> employeeViewList = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
            for (EmployeeMatView employeeView : employeeViewList) {
                TdsDeclarationUploadBean tdsDeclarationBean = map.get(employeeView.getLogin());
                tdsDeclarationBean.setCardNo(employeeView.getCardNo());
                tdsDeclarationBean.setName(employeeView.getName());
                tdsDeclarationBean.setEmailId(employeeView.getEmail());
                tdsDeclarationBean.setContactNumber(employeeView.getPhone());
                tdsDeclarationBean.setFactory(employeeView.getFactoryDesc());
                tdsDeclarationBean.setDepartment(employeeView.getDepCodeDesc());
                tdsDeclarationBean.setDesignation(employeeView.getDesCodeDesc());
                tdsDeclarationUploadBeanList.add(tdsDeclarationBean);
            }
        }
        if (tdsDeclarationUploadBeanList != null && tdsDeclarationUploadBeanList.size() > 0) {
            tdsDeclarationUploadBeanList = tdsDeclarationUploadBeanList.stream().map(x -> x).sorted((a, b) -> Integer.parseInt(a.getCardNo()) - Integer.parseInt(b.getCardNo())).collect(Collectors.toList());
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(tdsDeclarationUploadBeanList);
    }

    /**
     * {@code GET  /tds-declaration-uploads} : get all the tdsDeclarationUploads.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tdsDeclarationUploads in body.
     */
    @PostMapping("/tds-document-query/xlsx")
    @Timed
    public ResponseEntity<InputStreamResource> getAllTdsDocumentQueryExport(@Valid @RequestBody TdsDeclarationUploadSearch search) throws IOException {
        log.debug("REST request to get a page of TdsDeclarationUploads");
        List<TdsDeclarationUploadBean> tdsDeclarationUploadBeanList = new ArrayList<>();
        String cardNo = "%";
        if (search.getCardNo() != null) {
            cardNo = search.getCardNo().trim() + "%";
        }
        String name = "%";
        if (search.getName() != null) {
            name = search.getName().trim() + "%";
        }
        Page<Object[]> page = null;
        search.setPage(PageRequest.of(0, 10000, Sort.by("card_no").descending()));
        page = tdsDeclarationUploadRepository.findAllCardNoAndName(cardNo, name, search.getFinancialYear(), search.getPage());
        Map<String, TdsDeclarationUploadBean> map = new TreeMap<>();
        for (Object[] objects : page.getContent()) {
            TdsDeclarationUploadBean bean = new TdsDeclarationUploadBean();
            bean.setCardNo(objects[0].toString());
            bean.setFinancialYear(objects[1].toString());
            bean.setStatus(objects[2].toString());
            map.put(objects[0].toString(), bean);
        }
        if (map.keySet() != null && !map.keySet().isEmpty()) {
            List<EmployeeMatView> employeeViewList = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
            for (EmployeeMatView employeeView : employeeViewList) {
                TdsDeclarationUploadBean tdsDeclarationBean = map.get(employeeView.getLogin());
                tdsDeclarationBean.setCardNo(employeeView.getCardNo());
                tdsDeclarationBean.setName(employeeView.getName());
                tdsDeclarationBean.setEmailId(employeeView.getEmail());
                tdsDeclarationBean.setContactNumber(employeeView.getPhone());
                tdsDeclarationBean.setFactory(employeeView.getFactoryDesc());
                tdsDeclarationBean.setDepartment(employeeView.getDepCodeDesc());
                tdsDeclarationBean.setDesignation(employeeView.getDesCodeDesc());
                tdsDeclarationUploadBeanList.add(tdsDeclarationBean);
            }
        }
        if (tdsDeclarationUploadBeanList != null && tdsDeclarationUploadBeanList.size() > 0) {
            tdsDeclarationUploadBeanList = tdsDeclarationUploadBeanList.stream().map(x -> x).sorted((a, b) -> Integer.parseInt(a.getCardNo()) - Integer.parseInt(b.getCardNo())).collect(Collectors.toList());
        }
        ByteArrayInputStream in = tdsDocumentToExcel(tdsDeclarationUploadBeanList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=tdsDocumentQuery.xlsx");
        headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    }

    private ByteArrayInputStream tdsDocumentToExcel(List<TdsDeclarationUploadBean> tdsDeclarationUploadBeans) throws IOException {
        String[] COLUMNs = { "Sl.No", "Card No", "Name", "Phone", "Email", "Factory", "Department", "Designation" };
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.createSheet("TDS Query");

            Font bodyHighlightFont = workbook.createFont();
            bodyHighlightFont.setColor(IndexedColors.WHITE.getIndex());
            CellStyle highlightCellStyle = workbook.createCellStyle();
            highlightCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            highlightCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            highlightCellStyle.setFont(bodyHighlightFont);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();


            headerCellStyle.setFont(headerFont);

            // Row for Header
            Row headerRow = sheet.createRow(0);

            // Header
            for (int col = 0; col < COLUMNs.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(COLUMNs[col]);
                cell.setCellStyle(headerCellStyle);
            }

            int rowIdx = 1;
            int nos = 0;
            Map<String, String> hodNames = new HashMap<>();
            for (TdsDeclarationUploadBean tdsDeclarationUploadBean : tdsDeclarationUploadBeans) {
                Row row = sheet.createRow(rowIdx++);
                ++nos;
                if(tdsDeclarationUploadBean.getStatus() != null && tdsDeclarationUploadBean.getStatus().length()>0) {
                    Cell cell0 = row.createCell(0);
                    cell0.setCellStyle(highlightCellStyle);
                    cell0.setCellValue(nos + "");

                    Cell cell1 = row.createCell(1);
                    cell1.setCellStyle(highlightCellStyle);
                    cell1.setCellValue(tdsDeclarationUploadBean.getCardNo());

                    Cell cell2 = row.createCell(2);
                    cell2.setCellStyle(highlightCellStyle);
                    cell2.setCellValue(tdsDeclarationUploadBean.getName());

                    Cell cell3 = row.createCell(3);
                    cell3.setCellStyle(highlightCellStyle);
                    cell3.setCellValue(tdsDeclarationUploadBean.getContactNumber());

                    Cell cell4 = row.createCell(4);
                    cell4.setCellStyle(highlightCellStyle);
                    cell4.setCellValue(tdsDeclarationUploadBean.getEmailId());

                    Cell cell5 = row.createCell(5);
                    cell5.setCellStyle(highlightCellStyle);
                    cell5.setCellValue(tdsDeclarationUploadBean.getFactory());

                    Cell cell6 = row.createCell(6);
                    cell6.setCellStyle(highlightCellStyle);
                    cell6.setCellValue(tdsDeclarationUploadBean.getDepartment());

                    Cell cell7 = row.createCell(7);
                    cell7.setCellStyle(highlightCellStyle);
                    cell7.setCellValue(tdsDeclarationUploadBean.getDesignation());
                } else {
                    row.createCell(0).setCellValue(nos + "");
                    row.createCell(1).setCellValue(tdsDeclarationUploadBean.getCardNo());
                    row.createCell(2).setCellValue(tdsDeclarationUploadBean.getName());
                    row.createCell(3).setCellValue(tdsDeclarationUploadBean.getContactNumber());
                    row.createCell(4).setCellValue(tdsDeclarationUploadBean.getEmailId());
                    row.createCell(5).setCellValue(tdsDeclarationUploadBean.getFactory());
                    row.createCell(6).setCellValue(tdsDeclarationUploadBean.getDepartment());
                    row.createCell(7).setCellValue(tdsDeclarationUploadBean.getDesignation());
                }
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    /**
     * {@code GET  /tds-declaration-uploads} : get all the tdsDeclarationUploads.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tdsDeclarationUploads in body.
     */
    @GetMapping("/tds-document-query/{cardNo}/{year}")
    @Timed
    public ResponseEntity<TdsDeclarationUploadBean> getAllTdsDocumentQuery(@PathVariable String cardNo, @PathVariable String year) {
        log.debug("REST request to get a page of TdsDeclarationUploads");
        EmployeeMatView employeeView = employeeMatViewRepository.findByCardNo(cardNo).orElse(null);
        List<TdsGroupMaster> tdsGroupmaster = tdsGroupMasterRepository.findAllYear(Integer.parseInt(year));
        List<TdsGroupMasterBeans> tdsGroupList = new ArrayList<TdsGroupMasterBeans>();
        List<TdsDeclarationUpload> tdsDeclarationUploadList = tdsDeclarationUploadRepository.findByCardNo(cardNo, year);
        TdsDeclarationUploadBean tdsDeclarationBean = new TdsDeclarationUploadBean();
        tdsDeclarationBean.setFinancialYear(year);
        tdsDeclarationBean.setCardNo(employeeView.getCardNo());
        tdsDeclarationBean.setName(employeeView.getName());
        tdsDeclarationBean.setDesignation(employeeView.getDesCodeDesc());
        tdsDeclarationBean.setPanNo(employeeView.getPan());
        tdsDeclarationBean.setDateOfBirth(employeeView.getDob());
        tdsDeclarationBean.setContactNumber(employeeView.getPhone());
        tdsDeclarationBean.setEmailId(employeeView.getEmail());
        tdsDeclarationBean.setAddress(employeeView.getAdd1());
        for (TdsGroupMaster bean : tdsGroupmaster) {
            TdsGroupMasterBeans tdsGroupMasterbean = new TdsGroupMasterBeans();
            if (tdsDeclarationUploadList != null) {
                List<TdsDeclarationUploadDetailBean> tdsDeclarationUploadDetailBeanList = new ArrayList<>();
                for (TdsDeclarationUpload tdsDeclarationUploadTemp : tdsDeclarationUploadList) {
                    if (bean.getId() == tdsDeclarationUploadTemp.getTdsGroupMaster().getId()) {
                        TdsDeclarationUploadDetailBean tdsDeclarationUploadDetailBean = new TdsDeclarationUploadDetailBean();
                        BeanUtils.copyProperties(tdsDeclarationUploadTemp, tdsDeclarationUploadDetailBean);
                        if (tdsDeclarationUploadTemp.getApprovalFlag() != null && tdsDeclarationUploadTemp.getApprovalFlag().equalsIgnoreCase("Y")) {
                            tdsDeclarationUploadDetailBean.setAccept(true);
                        } else if (tdsDeclarationUploadTemp.getApprovalFlag() != null && tdsDeclarationUploadTemp.getApprovalFlag().equalsIgnoreCase("N")) {
                            tdsDeclarationUploadDetailBean.setReject(true);
                        }
                        tdsDeclarationUploadDetailBeanList.add(tdsDeclarationUploadDetailBean);
                    }
                }
                tdsGroupMasterbean.setTdsDeclarationUploadDetailBean(tdsDeclarationUploadDetailBeanList);
            }
            BeanUtils.copyProperties(bean, tdsGroupMasterbean);
            tdsGroupList.add(tdsGroupMasterbean);
        }
        tdsDeclarationBean.setTdsGroupMasterbean(tdsGroupList);
        return ResponseUtil.wrapOrNotFound(Optional.of(tdsDeclarationBean));
    }

    /**
     * {@code GET  /tds-declaration-uploads} : get all the tdsDeclarationUploads.
     *
     * @param pageable    the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder  a {@link UriComponentsBuilder} URI builder.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tdsDeclarationUploads in body.
     */
    @PostMapping("/tds-document-query-save")
    @Timed
    public ResponseEntity<TdsDeclarationUploadBean> getAllTdsDocumentSave(@RequestBody TdsDeclarationUploadBean tdsDeclarationUploadBean) {
        log.debug("REST request to get a page of TdsDeclarationUploads");

        int approval = 0;
        int reject = 0;
        for (TdsGroupMasterBeans tdsGroupMaster : tdsDeclarationUploadBean.getTdsGroupMasterbean()) {
            if (tdsGroupMaster.getTdsDeclarationUploadDetailBean() != null && tdsGroupMaster.getTdsDeclarationUploadDetailBean().size() > 0) {
                for (TdsDeclarationUploadDetailBean tdsDeclarationUploadDetailBean : tdsGroupMaster.getTdsDeclarationUploadDetailBean()) {
                    if (tdsDeclarationUploadDetailBean.getAccept() != null || tdsDeclarationUploadDetailBean.getReject() != null) {
                        if (tdsDeclarationUploadDetailBean.getAccept() != null && tdsDeclarationUploadDetailBean.getAccept().booleanValue() == true) {
                            TdsDeclarationUpload declarationUpload = tdsDeclarationUploadRepository.findById(tdsDeclarationUploadDetailBean.getId()).orElse(null);
                            if (declarationUpload != null && declarationUpload.getApprovalFlag() == null) {
                                ++approval;
                                declarationUpload.setApprovalFlag("Y");
                                declarationUpload.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                declarationUpload.setApprovedDate(Instant.now());
                                tdsDeclarationUploadRepository.save(declarationUpload);
                            }
                        } else if (tdsDeclarationUploadDetailBean.getReject() != null && tdsDeclarationUploadDetailBean.getReject().booleanValue() == true) {
                            TdsDeclarationUpload declarationUpload = tdsDeclarationUploadRepository.findById(tdsDeclarationUploadDetailBean.getId()).orElse(null);
                            if (declarationUpload != null && declarationUpload.getApprovalFlag() == null) {
                                ++reject;
                                declarationUpload.setApprovalFlag("N");
                                declarationUpload.setApprovedBy(SecurityUtils.getCurrentUserLogin().orElse(null));
                                declarationUpload.setApprovedDate(Instant.now());
                                tdsDeclarationUploadRepository.save(declarationUpload);
                            }
                        }
                    }
                }
            }
        }
        if (reject > 0) {
            EmployeeView user = employeeViewRepository.findById(tdsDeclarationUploadBean.getCardNo()).orElse(null);
            if (user != null) {
                String subject = "Rejected Tax related Document";
                String content = "<html>"
                    +"<body>"
                    +"<p>Dear "+user.getName()+",</p>"
                    +"<p>Your Tax related document has been rejected.</p>"
                    +"<p>Please upload revised document.</p>"
                    +"<p>Regards,</p>"
                    +"<p>Finance Team</p>"
                    +"</body>";
                try {
                    mailService.sendEmail(user.getEmail(), subject, content, false, true);
                } catch (Exception e) {
                }
            }
        } else if (approval > 0) {
            EmployeeView user = employeeViewRepository.findById(tdsDeclarationUploadBean.getCardNo()).orElse(null);
            if (user != null) {
                String subject = "Approved Tax related Document";
                String content = "<html>"
                    + "<body>"
                    + "<p>Dear " + user.getName() + ",</p>"
                    + "<p>Your Tax related document has been approved.</p>"
                    + "<p>Please upload revised document.</p>"
                    + "<p>Regards,</p>"
                    + "<p>Finance Team</p>"
                    + "</body>";
                try {
                    mailService.sendEmail(user.getEmail(), subject, content, false, true);
                } catch (Exception e) {
                }
            }
        }
        return this.getAllTdsDocumentQuery(tdsDeclarationUploadBean.getCardNo(), tdsDeclarationUploadBean.getFinancialYear());
    }


    @PostMapping("/tds-declaration-uploads-report")
    @Timed
    public @ResponseBody void getDownloadDeclarationUploadsReport(@Valid @RequestBody TdsDeclarationUploadSearch search, HttpServletResponse response) throws Exception {
        log.debug("REST request to get a page of TdsDeclarationUploads");
        List<TdsDeclarationUploadBean> tdsDeclarationUploadBeanList = new ArrayList<>();
        String cardNo = "%";
        if (search.getCardNo() != null) {
            cardNo = search.getCardNo().trim() + "%";
        }
        List<Object[]> list = tdsDeclarationUploadRepository.findByAllCardNo(cardNo, search.getFinancialYear());
        Map<String, TdsDeclarationUpload> map = new HashMap<>();
        for (Object[] objects : list) {
            TdsDeclarationUpload bean = new TdsDeclarationUpload();
            bean.setCardNo(objects[0].toString());
            bean.setFinancialYear(objects[1].toString());
            map.put(objects[0].toString(), bean);
        }
        if (map.keySet() != null && !map.keySet().isEmpty()) {
            List<EmployeeMatView> employeeViewList = employeeMatViewRepository.findAllByLogins(new ArrayList(map.keySet()));
            for (EmployeeMatView employeeView : employeeViewList) {
                TdsDeclarationUploadBean tdsDeclarationBean = new TdsDeclarationUploadBean();
                TdsDeclarationUpload tdsDeclarationUpload = map.get(employeeView.getLogin());
                tdsDeclarationBean.setFinancialYear(tdsDeclarationUpload.getFinancialYear());
                tdsDeclarationBean.setFactoryCode(employeeView.getFactory());
                tdsDeclarationBean.setFactory(employeeView.getFactoryDesc());
                tdsDeclarationBean.setCardNo(employeeView.getCardNo());
                tdsDeclarationBean.setName(employeeView.getName());
                tdsDeclarationBean.setEmailId(employeeView.getEmail());
                tdsDeclarationBean.setContactNumber(employeeView.getPhone());
                tdsDeclarationBean.setDepartment(employeeView.getDepCodeDesc());
                tdsDeclarationBean.setDesignation(employeeView.getDesCodeDesc());
                tdsDeclarationUploadBeanList.add(tdsDeclarationBean);
            }
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/TdsDeclarationUploadReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(tdsDeclarationUploadBeanList);
        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=EsiStatementMonthwise.xlsx");
        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exporter.exportReport();
    }


    /**
     * {@code GET  /tds-declaration-uploads/:id} : get the "id" tdsDeclarationUpload.
     *
     * @param id the id of the tdsDeclarationUpload to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tdsDeclarationUpload, or with status {@code 404 (Not Found)}.
     * @throws IOException
     */
    @GetMapping(value = "tds-declaration-uploads-zipdownload/{cardNo}/{year}", produces = "application/zip")
    public void getTdsDeclarationUploadDownload(@PathVariable String cardNo, @PathVariable String year, HttpServletResponse response) throws IOException {
        List<TdsDeclarationUpload> tdsDeclarationUpload = tdsDeclarationUploadRepository.findByCardNo(cardNo, year);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"tds.zip\"");
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        for (TdsDeclarationUpload tdsDeclaration : tdsDeclarationUpload) {
            Path path = Paths.get(UPLOADED_FOLDER + "tds/" + tdsDeclaration.getFileName());
            FileSystemResource resource = new FileSystemResource(path);
            ZipEntry zipEntry = new ZipEntry(cardNo + "-" + resource.getFilename());
            zipEntry.setSize(resource.contentLength());
            zipOut.putNextEntry(zipEntry);
            StreamUtils.copy(resource.getInputStream(), zipOut);
            zipOut.closeEntry();
        }
        zipOut.finish();
        zipOut.close();
    }

    /**
     * {@code POST  /tds-declaration-uploads-alldownloadzip} :
     *
     * @param tdsDeclarationUpload the tdsDeclarationUpload to download all files into zip.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException
     */
    @PostMapping(value = "tds-declaration-uploads-alldownloadzip", produces = "application/zip")
    @Timed
    public void getTdsDeclarationUploadDownloadAll(@Valid @RequestBody TdsDeclarationUploadSearch search, HttpServletResponse response) throws IOException {
        String cardNo = "%";
        if (search.getCardNo() != null) {
            cardNo = search.getCardNo().trim() + "%";
        }
        List<TdsDeclarationUpload> tdsDeclarationUpload = tdsDeclarationUploadRepository.findAllByCardAndYear(cardNo, search.getFinancialYear());
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        response.setStatus(HttpServletResponse.SC_OK);
        response.addHeader("Content-Disposition", "attachment; filename=\"tds.zip\"");
        ZipOutputStream zipOut = new ZipOutputStream(response.getOutputStream());
        for (TdsDeclarationUpload tdsDeclaration : tdsDeclarationUpload) {
            Path path = Paths.get(UPLOADED_FOLDER + "tds/" + tdsDeclaration.getFileName());
            FileSystemResource resource = new FileSystemResource(path);
            ZipEntry zipEntry = new ZipEntry(tdsDeclaration.getCardNo() + "-" + resource.getFilename());
            zipEntry.setSize(resource.contentLength());
            zipOut.putNextEntry(zipEntry);
            StreamUtils.copy(resource.getInputStream(), zipOut);
            zipOut.closeEntry();
        }
        zipOut.finish();
        zipOut.close();
    }


    /**
     * {@code POST  /tds-declaration-uploads-alldownloadzip} :
     *
     * @param tdsDeclarationUpload the tdsDeclarationUpload to download all files into zip.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException
     */
    @GetMapping("/tds-form16-download/{finYear}")
    @Timed
    public ResponseEntity<Object> getTdsForm16Download(@PathVariable String finYear, HttpServletResponse response) throws IOException {
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        EmployeeView employeeView = employeeViewRepository.findByLogin(SecurityUtils.getCurrentUserLogin().orElse(null));
        String directory = UPLOADED_FOLDER + "FORM16/"+finYear;
        File dir = new File(directory);
        File[] files = dir.listFiles((dir1, name) -> name.startsWith(employeeView.getPan()) && name.endsWith(".pdf"));
        if (files != null && files.length > 0) {
            Path path = Paths.get(UPLOADED_FOLDER + "FORM16/" + finYear + "/" + files[0].getName());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(files[0]));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", files[0].getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            String mimeType = Files.probeContentType(path);
            ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(files[0].length()).contentType(MediaType.parseMediaType(mimeType)).body(resource);
            return responseEntity;
        } else {
            ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body(null);
            return responseEntity;
        }
    }


    /**
     * {@code POST  /tds-declaration-uploads-alldownloadzip} :
     *
     * @param tdsDeclarationUpload the tdsDeclarationUpload to download all files into zip.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     * @throws IOException
     */
    @GetMapping("/tds-computation-download")
    @Timed
    public ResponseEntity<Object> getTdsComputationDownload(HttpServletResponse response) throws IOException {
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        EmployeeView employeeView = employeeViewRepository.findByLogin(SecurityUtils.getCurrentUserLogin().orElse(null));
        String directory = UPLOADED_FOLDER + "computation/2223";
        File dir = new File(directory);
        File[] files = dir.listFiles((dir1, name) -> name.startsWith(employeeView.getPan()) && name.endsWith(".pdf"));
        if (files != null && files.length > 0) {
            Path path = Paths.get(UPLOADED_FOLDER + "computation/2223/" + files[0].getName());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(files[0]));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", files[0].getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            String mimeType = Files.probeContentType(path);
            ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(files[0].length()).contentType(MediaType.parseMediaType(mimeType)).body(resource);
            return responseEntity;
        } else {
            ResponseEntity<Object> responseEntity = ResponseEntity.badRequest().body(null);
            return responseEntity;
        }
    }

    /**
     * {@code GET  /tds-declaration-uploads/:id} : get the "id" tdsDeclarationUpload.
     *
     * @param id the id of the tdsDeclarationUpload to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tdsDeclarationUpload, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tds-declaration-uploads/{id}")
    public ResponseEntity<TdsDeclarationUpload> getTdsDeclarationUpload(@PathVariable Long id) {
        log.debug("REST request to get TdsDeclarationUpload : {}", id);
        Optional<TdsDeclarationUpload> tdsDeclarationUpload = tdsDeclarationUploadRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(tdsDeclarationUpload);
    }

    /**
     * {@code DELETE  /tds-declaration-uploads/:id} : delete the "id" tdsDeclarationUpload.
     *
     * @param id the id of the tdsDeclarationUpload to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tds-declaration-uploads/{id}")
    public ResponseEntity<Void> deleteTdsDeclarationUpload(@PathVariable Long id) {
        log.debug("REST request to delete TdsDeclarationUpload : {}", id);
        tdsDeclarationUploadRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    @GetMapping("/tds-declaration-uploads-download/{id}")
    @Timed
    public ResponseEntity<Object> getTdsDeclarationDownload(@PathVariable Long id) throws FileNotFoundException, IOException {
        log.debug("REST request to get Feedback : {}", id);
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        TdsDeclarationUpload tdsDeclarationUpload = tdsDeclarationUploadRepository.findById(id).orElse(null);
        File file = new File(UPLOADED_FOLDER + "tds/" + tdsDeclarationUpload.getFileName());
        Path path = Paths.get(UPLOADED_FOLDER + "tds/" + tdsDeclarationUpload.getFileName());
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

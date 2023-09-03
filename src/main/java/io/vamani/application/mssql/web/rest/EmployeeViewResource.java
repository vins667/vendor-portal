package io.vamani.application.mssql.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.model.ResourcesBean;
import io.vamani.application.domain.ManpowerBudgetEntry;
import io.vamani.application.domain.MonthDaysMaster;
import io.vamani.application.domain.StitchManpowerBudget;
import io.vamani.application.model.*;
import io.vamani.application.mssql.domain.EmployeeHierarchy;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.model.*;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.ManpowerBudgetEntryRepository;
import io.vamani.application.repository.ManpowerTypeMappingRepository;
import io.vamani.application.repository.MonthDaysMasterRepository;
import io.vamani.application.repository.StitchManpowerBudgetRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.web.rest.errors.BadRequestAlertException;
import io.vamani.application.web.rest.util.HeaderUtil;
import io.vamani.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * REST controller for managing EmployeeView.
 */
@RestController
@RequestMapping("/api")
public class EmployeeViewResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeViewResource.class);

    private static final String ENTITY_NAME = "employeeView";

    private final EmployeeViewRepository employeeViewRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private MonthDaysMasterRepository monthDaysMasterRepository;

    @Autowired
    private StitchManpowerBudgetRepository stitchManpowerBudgetRepository;

    @Autowired
    private ManpowerTypeMappingRepository manpowerTypeMappingRepository;

    @Autowired
    private ManpowerBudgetEntryRepository manpowerBudgetEntryRepository;

    public EmployeeViewResource(EmployeeViewRepository employeeViewRepository) {
        this.employeeViewRepository = employeeViewRepository;
    }

    /**
     * POST  /employee-views : Create a new employeeView.
     *
     * @param employeeView the employeeView to create
     * @return the ResponseEntity with status 201 (Created) and with body the new employeeView, or with status 400 (Bad Request) if the employeeView has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/employee-views")
    @Timed
    public ResponseEntity<EmployeeView> createEmployeeView(@Valid @RequestBody EmployeeView employeeView) throws URISyntaxException {
        log.debug("REST request to save EmployeeView : {}", employeeView);
        if (employeeView.getLogin() != null) {
            throw new BadRequestAlertException("A new employeeView cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EmployeeView result = employeeViewRepository.save(employeeView);
        return ResponseEntity.created(new URI("/api/employee-views/" + result.getLogin()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getLogin().toString()))
            .body(result);
    }

    /**
     * PUT  /employee-views : Updates an existing employeeView.
     *
     * @param employeeView the employeeView to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated employeeView,
     * or with status 400 (Bad Request) if the employeeView is not valid,
     * or with status 500 (Internal Server Error) if the employeeView couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/employee-views")
    @Timed
    public ResponseEntity<EmployeeView> updateEmployeeView(@Valid @RequestBody EmployeeView employeeView) throws URISyntaxException {
        log.debug("REST request to update EmployeeView : {}", employeeView);
        if (employeeView.getLogin() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EmployeeView result = employeeViewRepository.save(employeeView);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, employeeView.getLogin().toString()))
            .body(result);
    }

    /**
     * GET  /employee-views : get all the employeeViews.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeViews in body
     */
    @GetMapping("/employee-views")
    @Timed
    public ResponseEntity<List<EmployeeView>> getAllEmployeeViews(Pageable pageable) {
        log.debug("REST request to get a page of EmployeeViews");
        Page<EmployeeView> page = employeeViewRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employee-views");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * POST  /employee-views-custom : get all the employeeViews.
     *
     * @param search the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeViews in body
     */
    @PostMapping("/employee-views-custom")
    @Timed
    public ResponseEntity<List<EmployeeView>> getAllEmployeeViewsCustom(@Valid @RequestBody EmployeeSearch search) {
        log.debug("REST request to get a page of EmployeeViews");
        String empCode = "%";
        String name = "%";
        String department = "%";
        String designation = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }
        if (search.getName() != null) {
            name = "%" + search.getName().toUpperCase() + "%";
        }
        if (search.getDepartment() != null) {
            department = "%" + search.getDepartment().toUpperCase() + "%";
        }
        if (search.getDesignation() != null) {
            designation = "%" + search.getDesignation().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("name").ascending()));
        Page<EmployeeView> page = employeeViewRepository.findAllByFilter(empCode, name, department, designation, search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employee-views-custom");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    /**
     * POST  /employee-views-custom : get all the employeeViews.
     *
     * @param search the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeViews in body
     */
    @GetMapping("/employee-views-smart-search/{searchText}")
    @Timed
    public List<EmployeeView> getAllEmployeeViewsCustom(@PathVariable String searchText) {
        log.debug("REST request to get a page of EmployeeViews");
        List<EmployeeView> employeeViews = null;
        String search = "%";
        if (searchText != null && searchText.length() > 3) {
            search = "%" + searchText + "%";
            employeeViews = employeeViewRepository.findAllBySmartSearch(search.toUpperCase());
        }
        if (employeeViews == null) {
            employeeViews = new ArrayList<>();
        }
        return employeeViews;
    }

    /**
     * POST  /employee-views-custom : get all the employeeViews.
     *
     * @param search the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeViews in body
     */
    @PostMapping("/employee-views-custom-factory")
    @Timed
    public ResponseEntity<List<EmployeeView>> getAllEmployeeViewsCustomFactory(@Valid @RequestBody EmployeeSearch search) {
        log.debug("REST request to get a page of EmployeeViews");
        EmployeeView employeeView = employeeViewRepository.findByCardNo(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        String empCode = "%";
        String name = "%";
        String department = "%";
        String designation = "%";
        if (search.getEmpCode() != null) {
            empCode = search.getEmpCode() + "%";
        }
        if (search.getName() != null) {
            name = "%" + search.getName().toUpperCase() + "%";
        }
        if (search.getDepartment() != null) {
            department = "%" + search.getDepartment().toUpperCase() + "%";
        }
        if (search.getDesignation() != null) {
            designation = "%" + search.getDesignation().toUpperCase() + "%";
        }
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("name").ascending()));
        Page<EmployeeView> page = employeeViewRepository.findAllByFilter(empCode, name, department, designation, employeeView.getFactory(), search.getPage());
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employee-views-custom");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /employee-views/:id : get the "id" employeeView.
     *
     * @param id the id of the employeeView to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeView, or with status 404 (Not Found)
     */
    @GetMapping("/employee-views/{id}")
    @Timed
    public ResponseEntity<EmployeeView> getEmployeeView(@PathVariable String id) {
        log.debug("REST request to get EmployeeView : {}", id);
        Optional<EmployeeView> employeeView = employeeViewRepository.findById(id.toUpperCase());
        return ResponseUtil.wrapOrNotFound(employeeView);
    }

    /**
     * GET  /employee-views/:id : get the "id" employeeView.
     *
     * @param id the id of the employeeView to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeView, or with status 404 (Not Found)
     */
    @GetMapping("/employee-views-card/{id}")
    @Timed
    public ResponseEntity<io.vamani.application.mssql.mobile.EmployeeView> getEmployeeViewByCard(@PathVariable String id) {
        log.debug("REST request to get EmployeeView : {}", id);
        EmployeeView employeeView = employeeViewRepository.findByCardNo(id.toUpperCase()).orElse(new EmployeeView());
        io.vamani.application.mssql.mobile.EmployeeView employee = new io.vamani.application.mssql.mobile.EmployeeView();
        if (employeeView != null && employeeView.getName() != null && employeeView.getName().length() > 0) {
            BeanUtils.copyProperties(employeeView, employee);
            employee.setExist(true);
        } else {
            employee.setExist(false);
            employee.setErrorMessage("In-valid card No!");
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(employee));
    }

    /**
     * GET  /employee-views/:id : get the "id" employeeView.
     *
     * @param id the id of the employeeView to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeView, or with status 404 (Not Found)
     */
    @GetMapping("/employee-views-login/{id}")
    @Timed
    public ResponseEntity<EmployeeLogin> getEmployeeViewByCardLogin(@PathVariable String id) {
        log.debug("REST request to get EmployeeView : {}", id);
        EmployeeView employeeView = employeeViewRepository.findByCardNo(id.toUpperCase()).orElse(new EmployeeView());
        EmployeeLogin employee = new EmployeeLogin();
        if (employeeView != null && employeeView.getName() != null && employeeView.getName().length() > 0) {
            BeanUtils.copyProperties(employeeView, employee);
            if (employee.getPhone() != null && employee.getPhone().length() > 0) {
                char[] phoneArrayTemp = employee.getPhone().toCharArray();
                char[] phoneArray = new char[phoneArrayTemp.length];
                if (phoneArrayTemp.length > 2) {
                    for (int i = 0; i < phoneArrayTemp.length; i++) {
                        if (i < 2) {
                            phoneArray[i] = phoneArrayTemp[i];
                        } else if (i == phoneArrayTemp.length - 3 || i == phoneArrayTemp.length - 2 || i == phoneArrayTemp.length - 1) {
                            phoneArray[i] = ' ';
                        } else {
                            phoneArray[i] = 'x';
                        }
                    }
                    employee.setPhone(String.valueOf(phoneArray));
                }
            }
            if (employee.getName() != null && employee.getName().length() > 0) {
                char[] nameArrayTemp = employee.getName().toCharArray();
                char[] nameArray = new char[nameArrayTemp.length];
                if (nameArrayTemp.length > 2) {
                    for (int i = 0; i < nameArrayTemp.length; i++) {
                        if (i < 2) {
                            nameArray[i] = nameArrayTemp[i];
                        } else {
                            nameArray[i] = 'x';
                        }
                    }
                    employee.setName(String.valueOf(nameArray));
                }
            }
            employee.setExist(true);
        } else {
            employee.setExist(false);
            employee.setErrorMessage("In-valid card No!");
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(employee));
    }

    /**
     * DELETE  /employee-views/:id : delete the "id" employeeView.
     *
     * @param id the id of the employeeView to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/employee-views/{id}")
    @Timed
    public ResponseEntity<Void> deleteEmployeeView(@PathVariable String id) {
        log.debug("REST request to delete EmployeeView : {}", id);

        employeeViewRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * GET  /employee-views/:id : get the "id" employeeView.
     *
     * @param !id the id of the employeeView to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeView, or with status 404 (Not Found)
     */
    @GetMapping("/employee-views-hierarchy")
    @Timed
    public ResponseEntity<HierarchyBean> getHierarchyView() {
        String currentUser = SecurityUtils.getCurrentUserLogin().orElse(null);
        List<EmployeeView> employeeViews = employeeViewRepository.findAll();
        HierarchyBean hierarchyBean = new HierarchyBean();
        for (EmployeeView employeeparentView : employeeViews) {
            if (employeeparentView.getSupervisor() != null && employeeparentView.getSupervisor().trim().equalsIgnoreCase("corporate")) {
                hierarchyBean.setName(employeeparentView.getName());
                hierarchyBean.setCardNo(employeeparentView.getCardNo());
                hierarchyBean.setImageUrl(employeeparentView.getImagePath());
                hierarchyBean.setPositionName(employeeparentView.getDesCodeDesc());
                hierarchyBean.setArea(employeeparentView.getPhone());
                if (employeeparentView.getCardNo().equalsIgnoreCase(currentUser)) {
                    hierarchyBean.setLoggedUser(true);
                } else {
                    hierarchyBean.setLoggedUser(false);
                }
                hierarchyBean.setTags(employeeparentView.getCardNo() + ", " + employeeparentView.getDesCodeDesc());
                hierarchyBean.setUnit(new HierarchyUnit("Department", employeeparentView.getDepCode(), employeeparentView.getDepCodeDesc()));
                List<HierarchyBean> children = getChildren(currentUser, employeeparentView.getCardNo(), employeeViews);
                if (children != null && children.size() > 0) {
                } else {
                    children = null;
                }
                hierarchyBean.setChildren(children);
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(hierarchyBean));
    }

    private List<HierarchyBean> getChildren(String currentUser, String cardNo, List<EmployeeView> employeeViews) {
        List<HierarchyBean> hierarchyBeans = new ArrayList<>();
        for (EmployeeView employeeChildView : employeeViews) {
            if (employeeChildView.getSupervisor().startsWith(cardNo)) {
                HierarchyBean hierarchyBean = new HierarchyBean();
                hierarchyBean.setCardNo(employeeChildView.getCardNo());
                hierarchyBean.setName(employeeChildView.getName());
                hierarchyBean.setImageUrl(employeeChildView.getImagePath());
                hierarchyBean.setPositionName(employeeChildView.getDesCodeDesc());
                hierarchyBean.setArea(employeeChildView.getPhone());
                if (employeeChildView.getCardNo().equalsIgnoreCase(currentUser)) {
                    hierarchyBean.setLoggedUser(true);
                } else {
                    hierarchyBean.setLoggedUser(false);
                }
                hierarchyBean.setTags(employeeChildView.getCardNo() + ", " + employeeChildView.getDesCodeDesc());
                hierarchyBean.setUnit(new HierarchyUnit("Department", employeeChildView.getDepCode(), employeeChildView.getDepCodeDesc()));
                List<HierarchyBean> children = getChildren(currentUser, employeeChildView.getCardNo(), employeeViews);
                if (children != null && children.size() > 0) {
                } else {
                    children = null;
                }
                hierarchyBean.setChildren(children);
                hierarchyBeans.add(hierarchyBean);
            }
        }
        return hierarchyBeans;
    }

    /**
     * GET  /employee-views/:id : get the "id" employeeView.
     *
     * @param !id the id of the employeeView to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the employeeView, or with status 404 (Not Found)
     */
    @GetMapping("/employee-views-hierarchy/{cardNo}")
    @Timed
    public @ResponseBody void getHierarchyView(@PathVariable String cardNo, HttpServletResponse response) throws JRException, IOException {
        List<EmployeeHierarchy> employeeHierarchies = new ArrayList<>();
        EmployeeHierarchy employeeHierarchy = new EmployeeHierarchy();
        EmployeeView employeeView = employeeViewRepository.findByCardNo(cardNo).orElse(null);
        employeeHierarchy.setCardNo(employeeView.getCardNo());
        employeeHierarchy.setName(employeeView.getName());
        employeeHierarchy.setDepartment(employeeView.getDepCodeDesc());
        employeeHierarchy.setDesignation(employeeView.getDesCodeDesc());
        employeeHierarchy.setContact(employeeView.getPhone());
        if (exists(employeeView.getSharedImagePath())) {
            employeeHierarchy.setImage(employeeView.getSharedImagePath());
        }
        if (employeeView != null && employeeView.getSupervisor() != null && employeeView.getSupervisor().indexOf("(") != -1) {
            String superVisor = employeeView.getSupervisor();
            EmployeeView employeeViewParent = employeeViewRepository.findByCardNo(superVisor.substring(0, superVisor.indexOf("("))).orElse(null);
            employeeHierarchy.setParentCardNo(employeeViewParent.getCardNo());
            employeeHierarchy.setParentName(employeeViewParent.getName());
            employeeHierarchy.setParentDepartment(employeeViewParent.getDepCodeDesc());
            employeeHierarchy.setParentDesignation(employeeViewParent.getDesCodeDesc());
            employeeHierarchy.setParentContact(employeeViewParent.getPhone());
            if (exists(employeeViewParent.getSharedImagePath())) {
                employeeHierarchy.setParentImage(employeeViewParent.getSharedImagePath());
            }
        }
        List<EmployeeView> employeeViewList = employeeViewRepository.findAllBySupervisor(employeeView.getCardNo() + "(%");
        List<EmployeeHierarchy> children = new ArrayList<>();
        for (EmployeeView employeeView1 : employeeViewList) {
            EmployeeHierarchy employeeHierarchy1 = new EmployeeHierarchy();
            employeeHierarchy1.setCardNo(employeeView1.getCardNo());
            employeeHierarchy1.setName(employeeView1.getName());
            employeeHierarchy1.setDepartment(employeeView1.getDepCodeDesc());
            employeeHierarchy1.setDesignation(employeeView1.getDesCodeDesc());
            employeeHierarchy1.setContact(employeeView1.getPhone());
            if (exists(employeeView1.getSharedImagePath())) {
                employeeHierarchy1.setImage(employeeView1.getSharedImagePath());
            }
            children.add(employeeHierarchy1);
        }
        employeeHierarchy.setChildren(children);
        employeeHierarchies.add(employeeHierarchy);

        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/employeeHierarchy.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(employeeHierarchies);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=employeeHierarchy.pdf");

        final OutputStream outputStream = response.getOutputStream();

        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    public static boolean exists(String URLName) {
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            //        HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con =
                (HttpURLConnection) new URL(URLName).openConnection();
            con.setRequestMethod("HEAD");
            return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @PostMapping("/leave-masters-strength-report")
    @Timed
    public @ResponseBody void getLeavePendingReport(@Valid @RequestBody(required = false) StrengthReportBean search, HttpServletResponse response) throws JRException, IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        search.setDateFrom(removeTime(Date.from(search.getDateFrom())).toInstant());
        List<StrengthReportBean> strengthReportBean = new ArrayList<>();
        System.out.println(format1.format(Date.from(search.getDateFrom())) + "%");
        List<Object[]> objectList = employeeViewRepository.getStatusAndDepart(Timestamp.from(search.getDateFrom()).toString(), search.getFactory(), search.getSubComp(), search.getLine());
        for (Object object : objectList) {
            StrengthReportBean bean = new StrengthReportBean();
            Object[] objects = (Object[]) object;
            bean.setEmpCode(objects[0].toString());
            bean.setName(objects[1].toString());
            bean.setDepCodeDesc(objects[2].toString());
            bean.setDesCodeDesc(objects[3].toString());
            bean.setSwCode((objects[4].toString() != null && objects[4].toString().equalsIgnoreCase("2")) ? "Staff" : "Worker");
            bean.setPayCode(objects[5].toString());
            bean.setStatus(objects[6].toString());
            bean.setDayNo((Timestamp) objects[7]);
            strengthReportBean.add(bean);
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/StrengthReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(strengthReportBean);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=StrengthReport.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    @PostMapping("/leave-masters-ctc-report")
    @Timed
    public @ResponseBody void getCTC29Report(@Valid @RequestBody(required = false) StrengthReportBean search, HttpServletResponse response) throws JRException, IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        List<CTC29Bean> ctc29Bean = new ArrayList<>();
        MonthDaysMaster monthDaysMaster = monthDaysMasterRepository.findById(dateFormat.format(Date.from(search.getDateFrom()))).orElse(null);
        List<Object[]> objectList = employeeViewRepository.getCTC29(search.getFactory().trim(), search.getSubComp(), search.getLine(), Timestamp.from(search.getDateFrom()).toString());
        Map<String, CTC29Bean> beanMap = new HashMap<>();
        for (Object object : objectList) {
            Object[] objects = (Object[]) object;
            if (beanMap.containsKey(objects[0].toString())) {
                CTC29Bean bean = beanMap.get(objects[0].toString());
                bean.setTotalStaff(bean.getTotalStaff() + Integer.parseInt(objects[2].toString()));
                bean.setTotalWorker(bean.getTotalWorker() + Integer.parseInt(objects[3].toString()));
                bean.setTotalDesig(bean.getTotalStaff() + bean.getTotalWorker());

                Double grossSalStaff = (Double.parseDouble(objects[4].toString()) * 26) / monthDaysMaster.getDay();
                Double grossSalWorker = (Double.parseDouble(objects[5].toString()) * 26) / monthDaysMaster.getDay();


                Double basicSalStaff = (Double.parseDouble(objects[13].toString()) * 26) / monthDaysMaster.getDay();
                Double basicSalWorker = (Double.parseDouble(objects[14].toString()) * 26) / monthDaysMaster.getDay();

                double pfStaff = 0L;
                double pfWorker = 0L;

                if (objects[7].toString() != null && objects[7].toString().equalsIgnoreCase("1")) {
                    double pfStaffT = (basicSalStaff * 13) / 100;
                    pfStaff = Math.round(pfStaffT);
                }

                if (objects[8].toString() != null && objects[8].toString().equalsIgnoreCase("1")) {
                    double pfWorkerT = (basicSalWorker * 13) / 100;
                    pfWorker = Math.round(pfWorkerT);
                }

                Double overTimeStaff = Double.parseDouble(objects[9].toString());
                Double overTimeWorker = Double.parseDouble(objects[10].toString());

                Double esiStaff = 0.0;
                Double esiWorker = 0.0;

                if (objects[11].toString() != null && objects[11].toString().equalsIgnoreCase("1")) {
                    esiStaff = ((grossSalStaff + overTimeStaff) * 3.25) / 100;
                }

                if (objects[12].toString() != null && objects[12].toString().equalsIgnoreCase("1")) {
                    esiWorker = ((grossSalWorker + overTimeWorker) * 3.25) / 100;
                }

                Double foodAmtStaff = Double.parseDouble(objects[15].toString());
                Double foodAmtWorker = Double.parseDouble(objects[16].toString());

                // System.out.println(objects[0].toString() + "\t" + objects[17].toString() + " \t " + objects[18].toString() + "\t" + (Math.round(grossSalStaff) + Math.round(grossSalWorker)) + "\t" + (Math.round(pfStaff) + Math.round(pfWorker)) + "\t" + (Math.round(esiStaff) + Math.round(esiWorker)) + "\t" + (Math.round(overTimeStaff) + Math.round(overTimeWorker)) + "\t" + (foodAmtStaff + foodAmtWorker));

                Double staffCTC = (Math.round(grossSalStaff) + Math.round(pfStaff) + Math.round(esiStaff) + Math.round(overTimeStaff) + foodAmtStaff);
                bean.setStaffCTC(bean.getStaffCTC() + staffCTC);

                Double workerCTC = (Math.round(grossSalWorker) + Math.round(pfWorker) + Math.round(esiWorker) + Math.round(overTimeWorker) + foodAmtWorker);
                bean.setWorkerCTC(bean.getWorkerCTC() + workerCTC);
                bean.setTotalCTC(bean.getStaffCTC() + bean.getWorkerCTC());

                if ((staffCTC + workerCTC) > 0) {
                    beanMap.put(objects[0].toString(), bean);
                }
            } else {
                CTC29Bean bean = new CTC29Bean();
                bean.setDepartment(objects[0].toString());
                bean.setTotalStaff(Integer.parseInt(objects[2].toString()));
                bean.setTotalWorker(Integer.parseInt(objects[3].toString()));
                bean.setTotalDesig(bean.getTotalStaff() + bean.getTotalWorker());
                Double grossSalStaff = (Double.parseDouble(objects[4].toString()) * 26) / monthDaysMaster.getDay();
                Double grossSalWorker = (Double.parseDouble(objects[5].toString()) * 26) / monthDaysMaster.getDay();


                Double basicSalStaff = (Double.parseDouble(objects[13].toString()) * 26) / monthDaysMaster.getDay();
                Double basicSalWorker = (Double.parseDouble(objects[14].toString()) * 26) / monthDaysMaster.getDay();

                double pfStaff = 0L;
                double pfWorker = 0L;

                if (objects[7].toString() != null && objects[7].toString().equalsIgnoreCase("1")) {
                    double pfStaffT = (basicSalStaff * 13) / 100;
                    pfStaff = Math.round(pfStaffT);
                }

                if (objects[8].toString() != null && objects[8].toString().equalsIgnoreCase("1")) {
                    double pfWorkerT = (basicSalWorker * 13) / 100;
                    pfWorker = Math.round(pfWorkerT);
                }

                Double overTimeStaff = Double.parseDouble(objects[9].toString());
                Double overTimeWorker = Double.parseDouble(objects[10].toString());

                Double esiStaff = 0.0;
                Double esiWorker = 0.0;

                if (objects[11].toString() != null && objects[11].toString().equalsIgnoreCase("1")) {
                    esiStaff = ((grossSalStaff + overTimeStaff) * 3.25) / 100;
                }

                if (objects[12].toString() != null && objects[12].toString().equalsIgnoreCase("1")) {
                    esiWorker = ((grossSalWorker + overTimeWorker) * 3.25) / 100;
                }

                Double foodAmtStaff = Double.parseDouble(objects[15].toString());
                Double foodAmtWorker = Double.parseDouble(objects[16].toString());

                // System.out.println(objects[0].toString() + "\t" + objects[17].toString() + " \t " + objects[18].toString() + "\t" + (Math.round(grossSalStaff) + Math.round(grossSalWorker)) + "\t" + (Math.round(pfStaff) + Math.round(pfWorker)) + "\t" + (Math.round(esiStaff) + Math.round(esiWorker)) + "\t" + (Math.round(overTimeStaff) + Math.round(overTimeWorker)) + "\t" + (foodAmtStaff + foodAmtWorker));

                Double staffCTC = Math.round(grossSalStaff) + Math.round(pfStaff) + Math.round(esiStaff) + Math.round(overTimeStaff) + foodAmtStaff;
                bean.setStaffCTC(staffCTC);

                Double workerCTC = Math.round(grossSalWorker) + Math.round(pfWorker) + Math.round(esiWorker) + Math.round(overTimeWorker) + foodAmtWorker;
                bean.setWorkerCTC(workerCTC);
                bean.setTotalCTC(bean.getStaffCTC() + bean.getWorkerCTC());

                if ((staffCTC + workerCTC) > 0) {
                    beanMap.put(objects[0].toString(), bean);
                }
            }
        }
        for (CTC29Bean ctc29Bean1 : beanMap.values()) {
            ctc29Bean.add(ctc29Bean1);
        }
        Collections.sort(ctc29Bean, Comparator.comparing(CTC29Bean::getDepartment));
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/CTC29Report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(ctc29Bean);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        parameters.put("dateFrom", format.format(Date.from(search.getDateFrom())));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=CTC29Report.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    @PostMapping("/leave-masters-ctc-detail-report")
    @Timed
    public @ResponseBody void getCTCDetailsReport(@Valid @RequestBody(required = false) StrengthReportBean search, HttpServletResponse response) throws JRException, IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
        List<CTCDetailBean> ctc29DetailBeans = new ArrayList<>();
        MonthDaysMaster monthDaysMaster = monthDaysMasterRepository.findById(dateFormat.format(Date.from(search.getDateFrom()))).orElse(null);
        List<Object[]> objectList = employeeViewRepository.getCTC29Details(search.getFactory().trim(), search.getSubComp(), search.getLine(), Timestamp.from(search.getDateFrom()).toString());
        for (Object object : objectList) {
            Object[] objects = (Object[]) object;

            CTCDetailBean bean = new CTCDetailBean();
            bean.setPayCode(objects[0].toString());
            bean.setName(objects[1].toString());
            bean.setDesignation(objects[2].toString());
            bean.setDept(objects[3].toString());
            if (objects[17] != null) {
                bean.setSubDept(objects[17].toString());
            }
            bean.setCadre(objects[4].toString());
            bean.setDoj((Date) objects[5]);
            bean.setAbsent(objects[6].toString() != null && objects[6].toString().equalsIgnoreCase("1") ? "NO" : "YES");
            bean.setPaidDay(objects[7].toString());
            bean.setBasicRate(Math.round(new Double(objects[8].toString())));
            bean.setGrossRate(Math.round(new Double(objects[9].toString())));
            bean.setEarnBasic(Math.round(new Double(objects[10].toString())));
            bean.setEarnGross(Math.round(new Double(objects[11].toString())));
            bean.setOtHrs(Math.round(new Double(objects[12].toString()) / 60));
            bean.setOtAmt(Math.round(new Double(objects[13].toString())));
            bean.setFooding(Math.round(new Double(objects[14].toString())));
            Long earnBasic = Math.round(new Double(objects[10].toString()));
            Long earnGross = Math.round(new Double(objects[11].toString()));
            Long otAmt = Math.round(new Double(objects[13].toString()));
            Long fooding = Math.round(new Double(objects[14].toString()));
            Long pf = 0L;
            Long esi = 0L;
            if (objects[15] != null && objects[15].toString().length() > 0 && !objects[15].toString().equalsIgnoreCase("0")) {
                bean.setPf("Yes");
                pf = Math.round(new Double(((earnBasic * 13) / 100)));
                bean.setPfAmt(pf);
            } else {
                bean.setPf("No");
                bean.setPfAmt(0L);
            }

            if (objects[16] != null && objects[16].toString().length() > 0 && !objects[16].toString().equalsIgnoreCase("0")) {
                bean.setEsi("Yes");
                esi = Math.round(new Double(((earnGross + otAmt) * 3.25) / 100));
                bean.setEsiAmt(esi);
            } else {
                bean.setEsi("No");
                bean.setEsiAmt(0L);
            }
            Long ctc = earnGross + otAmt + fooding + pf + esi;
            bean.setCtc(ctc);
            ctc29DetailBeans.add(bean);
        }
        Collections.sort(ctc29DetailBeans, Comparator.comparing(CTCDetailBean::getPayCode));
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/CTCDetailsReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(ctc29DetailBeans);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        parameters.put("dateFrom", format.format(Date.from(search.getDateFrom())));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=CTC29Report.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    @PostMapping("/fetch-manpowers-details")
    public List<ManpowerBean> fetchManpowers(@RequestBody ManpowerSearch search) {
        List<ManpowerBean> manpowerBeans = new ArrayList<>();
        Calendar cal = Calendar.getInstance(); // locale-specific
        cal.setTime(Date.from(search.getDateFrom()));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
        List<String> departments = manpowerTypeMappingRepository.findDepartmentsByType(search.getDeptCode());
        List<Object[]> objects = employeeViewRepository.fetchManPower(search.getFactoryCode(), departments, date);

        List<ManpowerBudgetEntry> manpowerBudgetEntries = manpowerBudgetEntryRepository.findAllByFactoryCodeAndDeptCodeAndAttendanceDateAndType(search.getFactoryCode(), search.getDeptCode(), cal.getTime().toInstant(), search.getType());
        Map<String, Long> map = new HashMap<>();
        for (ManpowerBudgetEntry manpowerBudgetEntry : manpowerBudgetEntries) {
            String item = manpowerBudgetEntry.getId().getFactCode() + "#" + manpowerBudgetEntry.getId().getDeptCode() + "#" + manpowerBudgetEntry.getId().getDesgCode() + "#" + manpowerBudgetEntry.getId().getSubdesgCode() + "#" + manpowerBudgetEntry.getId().getSkillCode() + "#" + manpowerBudgetEntry.getId().getLineNo();
            map.put(item, manpowerBudgetEntry.getLineCount());
        }

        for (Object[] object : objects) {
            ManpowerBean manpowerBean = new ManpowerBean();
            manpowerBean.setDesCode(object[0].toString());
            manpowerBean.setDesCodeDesc(object[1].toString());
            manpowerBean.setSdepCode(object[2].toString());
            manpowerBean.setSubDeptDesc(object[3].toString());
            manpowerBean.setCatCode(object[4].toString());
            manpowerBean.setCatName(object[5].toString());
            manpowerBean.setEmployeeCount((Integer) object[6]);
            manpowerBean.setEmployeeCountActual((Integer) object[6]);
            manpowerBean.setEmployeeBalance((Integer) object[6]);
            List<ResourcesBean> resources = new ArrayList<>();
            for (ResourcesBean resource : search.getResourcesBeans()) {
                ResourcesBean resourcesBean = new ResourcesBean();
                BeanUtils.copyProperties(resource, resourcesBean);
                String key = search.getFactoryCode() + "#" + search.getDeptCode() + "#" + object[0].toString() + "#" + object[2].toString() + "#" + object[4].toString() + "#" + resource.getCode();
                if (map.containsKey(key)) {
                    resourcesBean.setResourceAllocate(map.get(key));
                } else {
                    resourcesBean.setResourceAllocate(0L);
                }
                resources.add(resourcesBean);
            }
            manpowerBean.setResources(resources);
            /* List<StitchManpowerBudget> manpowerBudgets = new ArrayList<>();
            if(stitchManpowerBudgets != null) {
                StitchManpowerBudget manpowerBudget = null;
                for(ResourcesBean resourcesBean : search.getResourcesBeans()) {
                    for(StitchManpowerBudget stitchManpowerBudget : stitchManpowerBudgets) {
                        if (stitchManpowerBudget.getLineNo().equalsIgnoreCase(resourcesBean.getCode()) && stitchManpowerBudget.getDesgCode().equalsIgnoreCase(object[0].toString()) && stitchManpowerBudget.getSubdesgCode().equalsIgnoreCase(object[2].toString()) && stitchManpowerBudget.getSkillCode().equalsIgnoreCase(object[4].toString())) {
                            manpowerBudget = stitchManpowerBudget;
                        }
                    }
                }
                if (manpowerBudget != null) {
                    manpowerBudgets.add(manpowerBudget);
                } else {
                    manpowerBudget = new StitchManpowerBudget();

                }
            } */
            manpowerBeans.add(manpowerBean);
        }
        return manpowerBeans;
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    @PostMapping("/employee-salary")
    @Timed
    public @ResponseBody void getAllEmployeeBySalary(@Valid @RequestBody(required = false) SalarySearch search, HttpServletResponse response) throws JRException, IOException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateFrom = simpleDateFormat.parse(simpleDateFormat.format(Date.from(search.getDateFrom())));
        ZonedDateTime dateFromZoned = ZonedDateTime.ofInstant(dateFrom.toInstant(),
            ZoneId.systemDefault());

        Date dateTo = simpleDateFormat.parse(simpleDateFormat.format(Date.from(search.getDateTo())));
        ZonedDateTime dateToZoned = ZonedDateTime.ofInstant(dateTo.toInstant(),
            ZoneId.systemDefault());
        List<EmployeeViewBean> employeeViewBeans = new ArrayList<>();
            List<EmployeeView> employeeViews = employeeViewRepository.findAllEmployeeBySalary(search.getSalary(), dateFromZoned, dateToZoned);
        for (EmployeeView employeeView : employeeViews) {
            EmployeeViewBean bean = new EmployeeViewBean();
            bean.setCardNo(employeeView.getCardNo());
            bean.setName(employeeView.getName());
            bean.setfName(employeeView.getfName());
            bean.setFactoryDesc(employeeView.getFactoryDesc());
            if (employeeView.getDoj() != null) {
                bean.setDojFormat(simpleDateFormat.format(Date.from(employeeView.getDoj().toInstant())));
            }
            bean.setPan(employeeView.getPan());
            bean.setAdhNo(employeeView.getAdhNo());
            bean.setSalary(new BigDecimal(employeeView.getTotSal()));
            employeeViewBeans.add(bean);
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/employeeSalaryDetails.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(employeeViewBeans);

        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=employeeSalary.xlsx");

        final OutputStream outputStream = response.getOutputStream();

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }
}

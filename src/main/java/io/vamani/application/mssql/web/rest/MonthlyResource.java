package io.vamani.application.mssql.web.rest;
import com.codahale.metrics.annotation.Timed;
import com.ibm.icu.text.SimpleDateFormat;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.model.Message;
import io.vamani.application.mssql.domain.EmpHist;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.domain.Monthly;
import io.vamani.application.mssql.model.SalaryBean;
import io.vamani.application.mssql.model.SalaryReportBean;
import io.vamani.application.mssql.model.SalarySearch;
import io.vamani.application.mssql.model.SalarySummaryBean;
import io.vamani.application.mssql.repository.EmpHistRepository;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.mssql.repository.MonthlyRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.service.MailService;
import io.vamani.application.web.rest.util.PaginationUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;
/**
 * REST controller for managing Monthly.
 */
@RestController
@RequestMapping("/api")
public class MonthlyResource {
    private final Logger log = LoggerFactory.getLogger(EmployeeViewResource.class);

    private static final String ENTITY_NAME = "monthly";

    private final MonthlyRepository monthlyRepository;

    @Autowired
    private EmployeeViewRepository employeeViewRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailService mailService;

    @Autowired
    private EmpHistRepository empHistRepository;

    public MonthlyResource(MonthlyRepository monthlyRepository) {
        this.monthlyRepository = monthlyRepository;
    }

    /**
     * GET  /employee-views : get all the employeeViews.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeViews in body
     */
    @GetMapping("/monthly-salary")
    @Timed
    public ResponseEntity<List<SalaryBean>> getAllMonthlySalary(Pageable pageable) throws ParseException {
        log.debug("REST request to get a page of monthly");
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        Page<Monthly> page = monthlyRepository.findAllByEmpCode(employeeView.getEmpCode(), pageable);
        List<SalaryBean> salaryBeans = new ArrayList<SalaryBean>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM-yyyy");
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/employee-views");
        for (Monthly monthly : page.getContent()) {
            if (simpleDateFormat1.format(simpleDateFormat.parse(monthly.getId().getMonthYear())).equalsIgnoreCase(simpleDateFormat1.format(new Date()))) {
            } else {
                SalaryBean salaryBean = new SalaryBean();
                salaryBean.setEmpCode(monthly.getId().getEmpCode());
                salaryBean.setMonthNo(monthly.getId().getMonthNo());
                salaryBean.setMonthYear(monthly.getId().getMonthYear());
                salaryBean.setDisplayMonthYear(simpleDateFormat1.format(simpleDateFormat.parse(monthly.getId().getMonthYear())));
                salaryBean.setDayNo(monthly.getDayNo());
                salaryBean.setTotSal(monthly.getTotSal());
                salaryBean.setArrAmt(monthly.getArrAmt());
                salaryBean.setTotDed(monthly.getTotDed());
                salaryBean.setNetSal(monthly.getNetSal());
                salaryBeans.add(salaryBean);
            }
        }
        return ResponseEntity.ok().headers(headers).body(salaryBeans);
    }

    /**
     * GET  /employee-views : get all the employeeViews.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of employeeViews in body
     */
    @GetMapping("/monthly-salary/{monthYear}")
    @Timed
    public ResponseEntity<Monthly> getByMonthlySalary(@PathVariable String monthYear) throws ParseException {
        log.debug("REST request to get a page of monthly");
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        Monthly monthly = monthlyRepository.findByEmpCodeAndMonthYear(employeeView.getEmpCode(), monthYear);
        return ResponseEntity.ok().body(monthly);
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @GetMapping("/monthly-salary-report/{monthYear}")
    @Timed
    public @ResponseBody void downloadSalarySlip(@PathVariable String monthYear, HttpServletResponse response) throws JRException, IOException, ParseException {
        log.debug("REST request to get a page of LeaveMasters");
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        Monthly monthly = monthlyRepository.findByEmpCodeAndMonthYear(employeeView.getEmpCode(), monthYear);
        EmpHist empHist = empHistRepository.findByEmpCodeAndMonthYear(employeeView.getEmpCode(), monthYear);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM-yyyy");
        List<SalaryReportBean> salaryReportBeans = new ArrayList<>();
        SalaryReportBean salaryReportBean = new SalaryReportBean();
        salaryReportBean.setCardNo(employeeView.getCardNo());
        salaryReportBean.setName(employeeView.getName());
        salaryReportBean.setfName(employeeView.getfName());
        salaryReportBean.setDesCodeDesc(employeeView.getDesCodeDesc());
        salaryReportBean.setDepCodeDesc(employeeView.getDepCodeDesc());
        salaryReportBean.setDoj(Timestamp.from(employeeView.getDoj().toInstant()));
        salaryReportBean.setBankName(employeeView.getBankName());
        salaryReportBean.setBankNo(employeeView.getBankNo());
        salaryReportBean.setPfNo(employeeView.getPfNo());
        salaryReportBean.setEsiNo(employeeView.getEsiNo());
        salaryReportBean.setUan(employeeView.getUan());
        salaryReportBean.setPayMode(employeeView.getPayMode());
        salaryReportBean.setPan(employeeView.getPan());
        salaryReportBean.setAdhNo(employeeView.getAdhNo());
        salaryReportBean.setSubCodeDesc(employeeView.getSubCodeDesc());
        salaryReportBean.setSubCodeAddress(employeeView.getSubCodeAddress());

        if (monthly != null) {
            salaryReportBean.setMonthYear(simpleDateFormat1.format(simpleDateFormat.parse(monthly.getId().getMonthYear())));
            salaryReportBean.setAll1(monthly.getAll1());
            salaryReportBean.setAll2(monthly.getAll2());
            salaryReportBean.setAll3(monthly.getAll3());
            salaryReportBean.setAll4(monthly.getAll4());
            salaryReportBean.setAll5(monthly.getAll5());
            salaryReportBean.setAll6(monthly.getAll6());
            salaryReportBean.setAll7(monthly.getAll7());
            salaryReportBean.setAll8(monthly.getAll8());
            salaryReportBean.setAll9(monthly.getAll9());
            salaryReportBean.setAll10(monthly.getAll10());

            salaryReportBean.setRat1(monthly.getRat1());
            salaryReportBean.setRat2(monthly.getRat2());
            salaryReportBean.setRat3(monthly.getRat3());
            salaryReportBean.setRat4(monthly.getRat4());
            salaryReportBean.setRat5(monthly.getRat5());
            salaryReportBean.setRat6(monthly.getRat6());
            salaryReportBean.setRat7(monthly.getRat7());
            salaryReportBean.setRat8(monthly.getRat8());
            salaryReportBean.setRat9(monthly.getRat9());
            salaryReportBean.setRat10(monthly.getRat10());

            salaryReportBean.setEarn1(monthly.getEarn1());
            salaryReportBean.setEarn2(monthly.getEarn2());
            salaryReportBean.setEarn3(monthly.getEarn3());
            salaryReportBean.setEarn4(monthly.getEarn4());
            salaryReportBean.setEarn5(monthly.getEarn5());
            salaryReportBean.setEarn6(monthly.getEarn6());
            salaryReportBean.setEarn7(monthly.getEarn7());
            salaryReportBean.setEarn8(monthly.getEarn8());
            salaryReportBean.setEarn9(monthly.getEarn9());
            salaryReportBean.setEarn10(monthly.getEarn10());

            salaryReportBean.setArr1(monthly.getArr1());
            salaryReportBean.setArr2(monthly.getArr2());
            salaryReportBean.setArr3(monthly.getArr3());
            salaryReportBean.setArr4(monthly.getArr4());
            salaryReportBean.setArr5(monthly.getArr5());
            salaryReportBean.setArr6(monthly.getArr6());
            salaryReportBean.setArr7(monthly.getArr7());
            salaryReportBean.setArr8(monthly.getArr8());
            salaryReportBean.setArr9(monthly.getArr9());
            salaryReportBean.setArr10(monthly.getArr10());

            salaryReportBean.setDall1(monthly.getDall1());
            salaryReportBean.setDall2(monthly.getDall2());
            salaryReportBean.setDall3(monthly.getDall3());
            salaryReportBean.setDall4(monthly.getDall4());
            salaryReportBean.setDall5(monthly.getDall5());
            salaryReportBean.setDall6(monthly.getDall6());

            salaryReportBean.setDed1(monthly.getDed1());
            salaryReportBean.setDed2(monthly.getDed2());
            salaryReportBean.setDed3(monthly.getDed3());
            salaryReportBean.setDed4(monthly.getDed4());
            salaryReportBean.setDed5(monthly.getDed5());
            salaryReportBean.setDed6(monthly.getDed6());

            salaryReportBean.setPtotSal(monthly.getPtotSal());
            salaryReportBean.setTotSal(monthly.getTotSal());
            salaryReportBean.setArrAmt(monthly.getArrAmt());
            salaryReportBean.setTotDed(monthly.getTotDed());
            salaryReportBean.setNetSal(monthly.getNetSal());
        }

        if (empHist != null) {
            salaryReportBean.setWday(empHist.getWday());
            salaryReportBean.setEl(empHist.getEl());
            salaryReportBean.setCl(empHist.getCl());
            salaryReportBean.setSl(empHist.getSl());
            salaryReportBean.setMl(empHist.getMl());
            salaryReportBean.setCoff(empHist.getCoff());
            salaryReportBean.setPday(empHist.getPday());
            salaryReportBean.setWf(empHist.getWf());
            salaryReportBean.setHd(empHist.getHd());
            salaryReportBean.setEs(empHist.getEs());
        }

        salaryReportBeans.add(salaryReportBean);
        String path = applicationProperties.getTemplatePath()+"jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path+"/SalaryReport.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String,Object> parameters = new HashMap<>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(salaryReportBeans);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR",path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, jrDataSource);
        response.setContentType("application/x-pdf");
        response.setHeader("Content-Disposition","attachment; filename=SalaryReport.pdf");

        final OutputStream outputStream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
    }

    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @GetMapping("/monthly-salary-report-mail/{monthYear}")
    @Timed
    public ResponseEntity<Message> sendSalarySlip(@PathVariable String monthYear, HttpServletResponse response) throws JRException, IOException, ParseException {
        log.debug("REST request to get a page of LeaveMasters");
        EmployeeView employeeView = employeeViewRepository.findById(SecurityUtils.getCurrentUserLogin().orElse(null)).orElse(null);
        if (employeeView != null && employeeView.getEmail() != null && employeeView.getEmail().length()>0) {
            Monthly monthly = monthlyRepository.findByEmpCodeAndMonthYear(employeeView.getEmpCode(), monthYear);
            EmpHist empHist = empHistRepository.findByEmpCodeAndMonthYear(employeeView.getEmpCode(), monthYear);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yyyy");
            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM-yyyy");
            List<SalaryReportBean> salaryReportBeans = new ArrayList<>();
            SalaryReportBean salaryReportBean = new SalaryReportBean();
            salaryReportBean.setCardNo(employeeView.getCardNo());
            salaryReportBean.setName(employeeView.getName());
            salaryReportBean.setfName(employeeView.getfName());
            salaryReportBean.setDesCodeDesc(employeeView.getDesCodeDesc());
            salaryReportBean.setDepCodeDesc(employeeView.getDepCodeDesc());
            salaryReportBean.setDoj(Timestamp.from(employeeView.getDoj().toInstant()));
            salaryReportBean.setBankName(employeeView.getBankName());
            salaryReportBean.setBankNo(employeeView.getBankNo());
            salaryReportBean.setPfNo(employeeView.getPfNo());
            salaryReportBean.setEsiNo(employeeView.getEsiNo());
            salaryReportBean.setUan(employeeView.getUan());
            salaryReportBean.setPayMode(employeeView.getPayMode());
            salaryReportBean.setPan(employeeView.getPan());
            salaryReportBean.setAdhNo(employeeView.getAdhNo());
            salaryReportBean.setSubCodeDesc(employeeView.getSubCodeDesc());
            salaryReportBean.setSubCodeAddress(employeeView.getSubCodeAddress());
            if (monthly != null) {
                salaryReportBean.setMonthYear(simpleDateFormat1.format(simpleDateFormat.parse(monthly.getId().getMonthYear())));
                salaryReportBean.setAll1(monthly.getAll1());
                salaryReportBean.setAll2(monthly.getAll2());
                salaryReportBean.setAll3(monthly.getAll3());
                salaryReportBean.setAll4(monthly.getAll4());
                salaryReportBean.setAll5(monthly.getAll5());
                salaryReportBean.setAll6(monthly.getAll6());
                salaryReportBean.setAll7(monthly.getAll7());
                salaryReportBean.setAll8(monthly.getAll8());
                salaryReportBean.setAll9(monthly.getAll9());
                salaryReportBean.setAll10(monthly.getAll10());

                salaryReportBean.setRat1(monthly.getRat1());
                salaryReportBean.setRat2(monthly.getRat2());
                salaryReportBean.setRat3(monthly.getRat3());
                salaryReportBean.setRat4(monthly.getRat4());
                salaryReportBean.setRat5(monthly.getRat5());
                salaryReportBean.setRat6(monthly.getRat6());
                salaryReportBean.setRat7(monthly.getRat7());
                salaryReportBean.setRat8(monthly.getRat8());
                salaryReportBean.setRat9(monthly.getRat9());
                salaryReportBean.setRat10(monthly.getRat10());

                salaryReportBean.setEarn1(monthly.getEarn1());
                salaryReportBean.setEarn2(monthly.getEarn2());
                salaryReportBean.setEarn3(monthly.getEarn3());
                salaryReportBean.setEarn4(monthly.getEarn4());
                salaryReportBean.setEarn5(monthly.getEarn5());
                salaryReportBean.setEarn6(monthly.getEarn6());
                salaryReportBean.setEarn7(monthly.getEarn7());
                salaryReportBean.setEarn8(monthly.getEarn8());
                salaryReportBean.setEarn9(monthly.getEarn9());
                salaryReportBean.setEarn10(monthly.getEarn10());

                salaryReportBean.setArr1(monthly.getArr1());
                salaryReportBean.setArr2(monthly.getArr2());
                salaryReportBean.setArr3(monthly.getArr3());
                salaryReportBean.setArr4(monthly.getArr4());
                salaryReportBean.setArr5(monthly.getArr5());
                salaryReportBean.setArr6(monthly.getArr6());
                salaryReportBean.setArr7(monthly.getArr7());
                salaryReportBean.setArr8(monthly.getArr8());
                salaryReportBean.setArr9(monthly.getArr9());
                salaryReportBean.setArr10(monthly.getArr10());

                salaryReportBean.setDall1(monthly.getDall1());
                salaryReportBean.setDall2(monthly.getDall2());
                salaryReportBean.setDall3(monthly.getDall3());
                salaryReportBean.setDall4(monthly.getDall4());
                salaryReportBean.setDall5(monthly.getDall5());
                salaryReportBean.setDall6(monthly.getDall6());

                salaryReportBean.setDed1(monthly.getDed1());
                salaryReportBean.setDed2(monthly.getDed2());
                salaryReportBean.setDed3(monthly.getDed3());
                salaryReportBean.setDed4(monthly.getDed4());
                salaryReportBean.setDed5(monthly.getDed5());
                salaryReportBean.setDed6(monthly.getDed6());

                salaryReportBean.setPtotSal(monthly.getPtotSal());
                salaryReportBean.setTotSal(monthly.getTotSal());
                salaryReportBean.setArrAmt(monthly.getArrAmt());
                salaryReportBean.setTotDed(monthly.getTotDed());
                salaryReportBean.setNetSal(monthly.getNetSal());
            }

            if (empHist != null) {
                salaryReportBean.setWday(empHist.getWday());
                salaryReportBean.setEl(empHist.getEl());
                salaryReportBean.setCl(empHist.getCl());
                salaryReportBean.setSl(empHist.getSl());
                salaryReportBean.setMl(empHist.getMl());
                salaryReportBean.setCoff(empHist.getCoff());
                salaryReportBean.setPday(empHist.getPday());
                salaryReportBean.setWf(empHist.getWf());
                salaryReportBean.setHd(empHist.getHd());
                salaryReportBean.setEs(empHist.getEs());
            }

            salaryReportBeans.add(salaryReportBean);
            String path = applicationProperties.getTemplatePath() + "jasper/";
            JasperDesign jasperDesign = JRXmlLoader.load(path + "/SalaryReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Map<String, Object> parameters = new HashMap<>();
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(salaryReportBeans);


            parameters.put("datasource", jrDataSource);
            parameters.put("SUBREPORT_DIR", path);

            String fileName = path + "/" + employeeView.getCardNo() + monthYear + ".pdf";
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
            JRPdfExporter jrPdfExporter = new JRPdfExporter();
            ExporterInput exportInput = (ExporterInput) new SimpleExporterInput(jasperPrint);

            jrPdfExporter.setExporterInput(exportInput);
            OutputStreamExporterOutput jasperOutputPdf = new SimpleOutputStreamExporterOutput(fileName); //specify the path where we want to save the report

            jrPdfExporter.setExporterOutput(jasperOutputPdf);

            SimplePdfExporterConfiguration simple = new SimplePdfExporterConfiguration();

            jrPdfExporter.setConfiguration(simple);

            jrPdfExporter.exportReport();
            Locale locale = Locale.forLanguageTag("en");
            Context context = new Context(locale);
            context.setVariable("name", employeeView.getName());
            String content = null;
            String subject = "Pay Slip";
            try {
                content = templateEngine.process("mail/payslip_mail", context);
                mailService.sendEmail(employeeView.getEmail(), subject, content, true, true, fileName);
            } catch (Exception e) {
            }
            return ResponseEntity.ok().body(new Message("success", "Mail Sent"));
        } else {
            return ResponseEntity.ok().body(new Message("error", "Mail not exist"));
        }
        /*response.setContentType("application/x-pdf");
        response.setHeader("Content-Disposition","attachment; filename=SalaryReport.pdf");

        final OutputStream outputStream = response.getOutputStream();

        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);*/
    }


    /**
     * GET  /leave-masters : get all the leaveMasters.
     *
     * @param !pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of leaveMasters in body
     */
    @PostMapping("/monthly-salary-report-summary")
    @Timed
    public @ResponseBody void getAllMonthlySummaryReport(@Valid @RequestBody(required = false) SalarySearch search, HttpServletResponse response) throws JRException, IOException, ParseException {
        String cardNo = "%";
        String fromDate=null;
        String toDate=null;
        if (search.getCardNo() != null && search.getCardNo().length() > 0) {
            cardNo = "%" + search.getCardNo().trim() + "%";
        }
        if(search.getDateFrom() !=null && search.getDateTo() !=null) {
          SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
          fromDate=simpleDateFormat.format(Date.from(search.getDateFrom()))+"-01";
          toDate=simpleDateFormat.format(Date.from(search.getDateTo()))+"-01";
         }
        List<SalarySummaryBean> salarySummaryBeans = new ArrayList<>();
        List<Object> salarySummaries = null;
        if(search.getFactory() != null && search.getFactory().equalsIgnoreCase("ALL")) {
            salarySummaries = monthlyRepository.findAllByMonths(cardNo,fromDate,toDate);
        } else {
            salarySummaries = monthlyRepository.findAllByMonths(cardNo,fromDate,toDate, search.getFactory());
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-yyyy");
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MMM-yyyy");
        for (Object object : salarySummaries) {
            Object[] objects = (Object[]) object;
            SalarySummaryBean salarySummaryBean = new SalarySummaryBean();
            salarySummaryBean.setMonth(simpleDateFormat1.format(simpleDateFormat.parse(objects[0].toString())));
            salarySummaryBean.setCardNo(objects[1].toString());
            salarySummaryBean.setName(objects[2].toString());
           /* ZonedDateTime zonedDateTime = (ZonedDateTime) objects[3];
            salarySummaryBean.setDoj(Timestamp.from(zonedDateTime.toInstant())); */
            salarySummaryBean.setDoj((Timestamp) objects[3]);
            salarySummaryBean.setPanNo(objects[4].toString());
            salarySummaryBean.setPaidDays(Double.parseDouble(objects[5].toString()));

            // Basic Salary
            if (objects[6] != null && objects[6].toString().trim().equalsIgnoreCase("Basic")) {
                salarySummaryBean.setBasic(Double.parseDouble(objects[13].toString())+Double.parseDouble(objects[32].toString()));
            } else if (objects[7] != null && objects[7].toString().trim().equalsIgnoreCase("Basic")) {
                salarySummaryBean.setBasic(Double.parseDouble(objects[14].toString())+Double.parseDouble(objects[33].toString()));
            } else if (objects[8] != null && objects[8].toString().trim().equalsIgnoreCase("Basic")) {
                salarySummaryBean.setBasic(Double.parseDouble(objects[15].toString())+Double.parseDouble(objects[34].toString()));
            } else if (objects[9] != null && objects[9].toString().trim().equalsIgnoreCase("Basic")) {
                salarySummaryBean.setBasic(Double.parseDouble(objects[16].toString())+Double.parseDouble(objects[35].toString()));
            } else if (objects[10] != null && objects[10].toString().trim().equalsIgnoreCase("Basic")) {
                salarySummaryBean.setBasic(Double.parseDouble(objects[17].toString())+Double.parseDouble(objects[36].toString()));
            } else if (objects[11] != null && objects[11].toString().trim().equalsIgnoreCase("Basic")) {
                salarySummaryBean.setBasic(Double.parseDouble(objects[18].toString())+Double.parseDouble(objects[37].toString()));
            } else if (objects[12] != null && objects[12].toString().trim().equalsIgnoreCase("Basic")) {
                salarySummaryBean.setBasic(Double.parseDouble(objects[19].toString())+Double.parseDouble(objects[38].toString()));
            }

            // HRA
            if (objects[6] != null && objects[6].toString().trim().equalsIgnoreCase("HRA")) {
                salarySummaryBean.setHra(Double.parseDouble(objects[13].toString())+Double.parseDouble(objects[32].toString()));
            } else if (objects[7] != null && objects[7].toString().trim().equalsIgnoreCase("HRA")) {
                salarySummaryBean.setHra(Double.parseDouble(objects[14].toString())+Double.parseDouble(objects[33].toString()));
            } else if (objects[8] != null && objects[8].toString().trim().equalsIgnoreCase("HRA")) {
                salarySummaryBean.setHra(Double.parseDouble(objects[15].toString())+Double.parseDouble(objects[34].toString()));
            } else if (objects[9] != null && objects[9].toString().trim().equalsIgnoreCase("HRA")) {
                salarySummaryBean.setHra(Double.parseDouble(objects[16].toString())+Double.parseDouble(objects[35].toString()));
            } else if (objects[10] != null && objects[10].toString().trim().equalsIgnoreCase("HRA")) {
                salarySummaryBean.setHra(Double.parseDouble(objects[17].toString())+Double.parseDouble(objects[36].toString()));
            } else if (objects[11] != null && objects[11].toString().trim().equalsIgnoreCase("HRA")) {
                salarySummaryBean.setHra(Double.parseDouble(objects[18].toString())+Double.parseDouble(objects[37].toString()));
            } else if (objects[12] != null && objects[12].toString().trim().equalsIgnoreCase("HRA")) {
                salarySummaryBean.setHra(Double.parseDouble(objects[19].toString())+Double.parseDouble(objects[38].toString()));
            }

            // Conv Allow
            if (objects[6] != null && objects[6].toString().trim().equalsIgnoreCase("Conv. Allow.")) {
                salarySummaryBean.setConvAllow(Double.parseDouble(objects[13].toString())+Double.parseDouble(objects[32].toString()));
            } else if (objects[7] != null && objects[7].toString().trim().equalsIgnoreCase("Conv. Allow.")) {
                salarySummaryBean.setConvAllow(Double.parseDouble(objects[14].toString())+Double.parseDouble(objects[33].toString()));
            } else if (objects[8] != null && objects[8].toString().trim().equalsIgnoreCase("Conv. Allow.")) {
                salarySummaryBean.setConvAllow(Double.parseDouble(objects[15].toString())+Double.parseDouble(objects[34].toString()));
            } else if (objects[9] != null && objects[9].toString().trim().equalsIgnoreCase("Conv. Allow.")) {
                salarySummaryBean.setConvAllow(Double.parseDouble(objects[16].toString())+Double.parseDouble(objects[35].toString()));
            } else if (objects[10] != null && objects[10].toString().trim().equalsIgnoreCase("Conv. Allow.")) {
                salarySummaryBean.setConvAllow(Double.parseDouble(objects[17].toString())+Double.parseDouble(objects[36].toString()));
            } else if (objects[11] != null && objects[11].toString().trim().equalsIgnoreCase("Conv. Allow.")) {
                salarySummaryBean.setConvAllow(Double.parseDouble(objects[18].toString())+Double.parseDouble(objects[37].toString()));
            } else if (objects[12] != null && objects[12].toString().trim().equalsIgnoreCase("Conv. Allow.")) {
                salarySummaryBean.setConvAllow(Double.parseDouble(objects[19].toString())+Double.parseDouble(objects[38].toString()));
            }

            // Others
            if (objects[6] != null && objects[6].toString().trim().equalsIgnoreCase("Others")) {
                salarySummaryBean.setOthers(Double.parseDouble(objects[13].toString())+Double.parseDouble(objects[32].toString()));
            } else if (objects[7] != null && objects[7].toString().trim().equalsIgnoreCase("Others")) {
                salarySummaryBean.setOthers(Double.parseDouble(objects[14].toString())+Double.parseDouble(objects[33].toString()));
            } else if (objects[8] != null && objects[8].toString().trim().equalsIgnoreCase("Others")) {
                salarySummaryBean.setOthers(Double.parseDouble(objects[15].toString())+Double.parseDouble(objects[34].toString()));
            } else if (objects[9] != null && objects[9].toString().trim().equalsIgnoreCase("Others")) {
                salarySummaryBean.setOthers(Double.parseDouble(objects[16].toString())+Double.parseDouble(objects[35].toString()));
            } else if (objects[10] != null && objects[10].toString().trim().equalsIgnoreCase("Others")) {
                salarySummaryBean.setOthers(Double.parseDouble(objects[17].toString())+Double.parseDouble(objects[36].toString()));
            } else if (objects[11] != null && objects[11].toString().trim().equalsIgnoreCase("Others")) {
                salarySummaryBean.setOthers(Double.parseDouble(objects[18].toString())+Double.parseDouble(objects[37].toString()));
            } else if (objects[12] != null && objects[12].toString().trim().equalsIgnoreCase("Others")) {
                salarySummaryBean.setOthers(Double.parseDouble(objects[19].toString())+Double.parseDouble(objects[38].toString()));
            }

            // Others
            if (objects[6] != null && !objects[6].toString().trim().equalsIgnoreCase("Basic") &&
            		!objects[6].toString().trim().equalsIgnoreCase("HRA") &&
            		!objects[6].toString().trim().equalsIgnoreCase("Conv. Allow.") &&
            		!objects[6].toString().trim().equalsIgnoreCase("Spl Allow") &&
            		!objects[6].toString().trim().equalsIgnoreCase("Spl Allow.") &&
            		!objects[6].toString().trim().equalsIgnoreCase("Medical") &&
                    !objects[6].toString().trim().equalsIgnoreCase("Others")) {
                if(salarySummaryBean.getOthers() != null && salarySummaryBean.getOthers()>0) {
                    salarySummaryBean.setOthers(salarySummaryBean.getOthers() + Double.parseDouble(objects[13].toString()) + Double.parseDouble(objects[32].toString()));
                } else {
                    salarySummaryBean.setOthers(Double.parseDouble(objects[13].toString()) + Double.parseDouble(objects[32].toString()));
                }
            }
            if (objects[7] != null && !objects[7].toString().trim().equalsIgnoreCase("Basic") &&
            		!objects[7].toString().trim().equalsIgnoreCase("HRA") &&
            		!objects[7].toString().trim().equalsIgnoreCase("Conv. Allow.") &&
            		!objects[7].toString().trim().equalsIgnoreCase("Spl Allow") &&
            		!objects[7].toString().trim().equalsIgnoreCase("Spl Allow.") &&
            		!objects[7].toString().trim().equalsIgnoreCase("Medical") &&
                    !objects[7].toString().trim().equalsIgnoreCase("Others")) {
                if(salarySummaryBean.getOthers() != null && salarySummaryBean.getOthers()>0) {
                    salarySummaryBean.setOthers(salarySummaryBean.getOthers() + Double.parseDouble(objects[14].toString())+Double.parseDouble(objects[33].toString()));
                } else {
                    salarySummaryBean.setOthers(Double.parseDouble(objects[14].toString())+Double.parseDouble(objects[33].toString()));
                }
            }
            if (objects[8] != null && !objects[8].toString().trim().equalsIgnoreCase("Basic") &&
            		!objects[8].toString().trim().equalsIgnoreCase("HRA") &&
            		!objects[8].toString().trim().equalsIgnoreCase("Conv. Allow.") &&
            		!objects[8].toString().trim().equalsIgnoreCase("Spl Allow") &&
            		!objects[8].toString().trim().equalsIgnoreCase("Spl Allow.") &&
            		!objects[8].toString().trim().equalsIgnoreCase("Medical") &&
                    !objects[8].toString().trim().equalsIgnoreCase("Others")) {
                if(salarySummaryBean.getOthers() != null && salarySummaryBean.getOthers()>0) {
                    salarySummaryBean.setOthers(salarySummaryBean.getOthers() + Double.parseDouble(objects[15].toString())+Double.parseDouble(objects[34].toString()));
                } else {
                    salarySummaryBean.setOthers(Double.parseDouble(objects[15].toString())+Double.parseDouble(objects[34].toString()));
                }
            }
            if (objects[9] != null && !objects[9].toString().trim().equalsIgnoreCase("Basic") &&
            		!objects[9].toString().trim().equalsIgnoreCase("HRA") &&
            		!objects[9].toString().trim().equalsIgnoreCase("Conv. Allow.") &&
            		!objects[9].toString().trim().equalsIgnoreCase("Spl Allow") &&
            		!objects[9].toString().trim().equalsIgnoreCase("Spl Allow.") &&
            		!objects[9].toString().trim().equalsIgnoreCase("Medical") &&
                    !objects[9].toString().trim().equalsIgnoreCase("Others")) {
                if(salarySummaryBean.getOthers() != null && salarySummaryBean.getOthers()>0) {
                    salarySummaryBean.setOthers(salarySummaryBean.getOthers() + Double.parseDouble(objects[16].toString())+Double.parseDouble(objects[35].toString()));
                } else {
                    salarySummaryBean.setOthers(Double.parseDouble(objects[16].toString())+Double.parseDouble(objects[35].toString()));
                }
            }
            if (objects[10] != null && !objects[10].toString().trim().equalsIgnoreCase("Basic") &&
            		!objects[10].toString().trim().equalsIgnoreCase("HRA") &&
            		!objects[10].toString().trim().equalsIgnoreCase("Conv. Allow.") &&
            		!objects[10].toString().trim().equalsIgnoreCase("Spl Allow") &&
            		!objects[10].toString().trim().equalsIgnoreCase("Spl Allow.") &&
            		!objects[10].toString().trim().equalsIgnoreCase("Medical") &&
                    !objects[10].toString().trim().equalsIgnoreCase("Others")) {
                if(salarySummaryBean.getOthers() != null && salarySummaryBean.getOthers()>0) {
                    salarySummaryBean.setOthers(salarySummaryBean.getOthers() + Double.parseDouble(objects[17].toString())+Double.parseDouble(objects[36].toString()));
                } else {
                    salarySummaryBean.setOthers(Double.parseDouble(objects[17].toString())+Double.parseDouble(objects[36].toString()));
                }
            }
            if (objects[11] != null && !objects[11].toString().trim().equalsIgnoreCase("Basic") &&
            		!objects[11].toString().trim().equalsIgnoreCase("HRA") &&
            		!objects[11].toString().trim().equalsIgnoreCase("Conv. Allow.") &&
            		!objects[11].toString().trim().equalsIgnoreCase("Spl Allow") &&
            		!objects[11].toString().trim().equalsIgnoreCase("Spl Allow.") &&
            		!objects[11].toString().trim().equalsIgnoreCase("Medical") &&
                    !objects[11].toString().trim().equalsIgnoreCase("Others")) {
                if(salarySummaryBean.getOthers() != null && salarySummaryBean.getOthers()>0) {
                    salarySummaryBean.setOthers(salarySummaryBean.getOthers() + Double.parseDouble(objects[18].toString())+Double.parseDouble(objects[37].toString()));
                } else {
                    salarySummaryBean.setOthers(Double.parseDouble(objects[18].toString())+Double.parseDouble(objects[37].toString()));
                }
            }
            if (objects[12] != null && !objects[12].toString().trim().equalsIgnoreCase("Basic") &&
                !objects[12].toString().trim().equalsIgnoreCase("HRA") &&
                !objects[12].toString().trim().equalsIgnoreCase("Conv. Allow.") &&
                !objects[12].toString().trim().equalsIgnoreCase("Spl Allow") &&
                !objects[12].toString().trim().equalsIgnoreCase("Spl Allow.") &&
                !objects[12].toString().trim().equalsIgnoreCase("Medical") &&
                !objects[12].toString().trim().equalsIgnoreCase("Others")) {
                if(salarySummaryBean.getOthers() != null && salarySummaryBean.getOthers()>0) {
                    salarySummaryBean.setOthers(salarySummaryBean.getOthers() + Double.parseDouble(objects[19].toString())+Double.parseDouble(objects[38].toString()));
                } else {
                    salarySummaryBean.setOthers(Double.parseDouble(objects[19].toString())+Double.parseDouble(objects[38].toString()));
                }
            }

            // Spl Allow
            if (objects[6] != null && (objects[6].toString().trim().equalsIgnoreCase("Spl Allow") || objects[6].toString().trim().equalsIgnoreCase("Spl Allow."))) {
                salarySummaryBean.setSplAllow(Double.parseDouble(objects[13].toString())+Double.parseDouble(objects[32].toString()));
            } else if (objects[7] != null && (objects[7].toString().trim().equalsIgnoreCase("Spl Allow") || objects[7].toString().trim().equalsIgnoreCase("Spl Allow."))) {
                salarySummaryBean.setSplAllow(Double.parseDouble(objects[14].toString())+Double.parseDouble(objects[33].toString()));
            } else if (objects[8] != null  && (objects[8].toString().trim().equalsIgnoreCase("Spl Allow") || objects[8].toString().trim().equalsIgnoreCase("Spl Allow."))) {
                salarySummaryBean.setSplAllow(Double.parseDouble(objects[15].toString())+Double.parseDouble(objects[34].toString()));
            } else if (objects[9] != null && (objects[9].toString().trim().equalsIgnoreCase("Spl Allow") || objects[9].toString().trim().equalsIgnoreCase("Spl Allow."))) {
                salarySummaryBean.setSplAllow(Double.parseDouble(objects[16].toString())+Double.parseDouble(objects[35].toString()));
            } else if (objects[10] != null && (objects[10].toString().trim().equalsIgnoreCase("Spl Allow") || objects[10].toString().trim().equalsIgnoreCase("Spl Allow."))) {
                salarySummaryBean.setSplAllow(Double.parseDouble(objects[17].toString())+Double.parseDouble(objects[36].toString()));
            } else if (objects[11] != null && (objects[11].toString().trim().equalsIgnoreCase("Spl Allow") || objects[11].toString().trim().equalsIgnoreCase("Spl Allow."))) {
                salarySummaryBean.setSplAllow(Double.parseDouble(objects[18].toString())+Double.parseDouble(objects[37].toString()));
            } else if (objects[12] != null && (objects[12].toString().trim().equalsIgnoreCase("Spl Allow") || objects[12].toString().trim().equalsIgnoreCase("Spl Allow."))) {
                salarySummaryBean.setSplAllow(Double.parseDouble(objects[19].toString())+Double.parseDouble(objects[38].toString()));
            }

            // Medical
            if (objects[6] != null && objects[6].toString().trim().equalsIgnoreCase("Medical")) {
                salarySummaryBean.setMedical(Double.parseDouble(objects[13].toString())+Double.parseDouble(objects[32].toString()));
            } else if (objects[7] != null && objects[7].toString().trim().equalsIgnoreCase("Medical")) {
                salarySummaryBean.setMedical(Double.parseDouble(objects[14].toString())+Double.parseDouble(objects[33].toString()));
            } else if (objects[8] != null && objects[8].toString().trim().equalsIgnoreCase("Medical")) {
                salarySummaryBean.setMedical(Double.parseDouble(objects[15].toString())+Double.parseDouble(objects[34].toString()));
            } else if (objects[9] != null && objects[9].toString().trim().equalsIgnoreCase("Medical")) {
                salarySummaryBean.setMedical(Double.parseDouble(objects[16].toString())+Double.parseDouble(objects[35].toString()));
            } else if (objects[10] != null && objects[10].toString().trim().equalsIgnoreCase("Medical")) {
                salarySummaryBean.setMedical(Double.parseDouble(objects[17].toString())+Double.parseDouble(objects[36].toString()));
            } else if (objects[11] != null && objects[11].toString().trim().equalsIgnoreCase("Medical")) {
                salarySummaryBean.setMedical(Double.parseDouble(objects[18].toString())+Double.parseDouble(objects[37].toString()));
            } else if (objects[12] != null && objects[12].toString().trim().equalsIgnoreCase("Medical")) {
                salarySummaryBean.setMedical(Double.parseDouble(objects[19].toString())+Double.parseDouble(objects[38].toString()));
            }

            salarySummaryBean.setGrossSalary(Double.parseDouble(objects[20].toString()));

            // PF
            if (objects[21] != null && objects[21].toString().trim().equalsIgnoreCase("P F")) {
                salarySummaryBean.setPf(Double.parseDouble(objects[26].toString()));
            } else if (objects[22] != null && objects[22].toString().trim().equalsIgnoreCase("P F")) {
                salarySummaryBean.setPf(Double.parseDouble(objects[27].toString()));
            } else if (objects[23] != null && objects[23].toString().trim().equalsIgnoreCase("P F")) {
                salarySummaryBean.setPf(Double.parseDouble(objects[28].toString()));
            } else if (objects[24] != null && objects[24].toString().trim().equalsIgnoreCase("P F")) {
                salarySummaryBean.setPf(Double.parseDouble(objects[29].toString()));
            } else if (objects[25] != null && objects[25].toString().trim().equalsIgnoreCase("P F")) {
                salarySummaryBean.setPf(Double.parseDouble(objects[30].toString()));
            }

            // LWF
            if (objects[21] != null && objects[21].toString().trim().equalsIgnoreCase("LWF")) {
                salarySummaryBean.setLwf(Double.parseDouble(objects[26].toString()));
            } else if (objects[22] != null && objects[22].toString().trim().equalsIgnoreCase("LWF")) {
                salarySummaryBean.setLwf(Double.parseDouble(objects[27].toString()));
            } else if (objects[23] != null && objects[23].toString().trim().equalsIgnoreCase("LWF")) {
                salarySummaryBean.setLwf(Double.parseDouble(objects[28].toString()));
            } else if (objects[24] != null && objects[24].toString().trim().equalsIgnoreCase("LWF")) {
                salarySummaryBean.setLwf(Double.parseDouble(objects[29].toString()));
            } else if (objects[25] != null && objects[25].toString().trim().equalsIgnoreCase("LWF")) {
                salarySummaryBean.setLwf(Double.parseDouble(objects[30].toString()));
            }

            // TDS

            if (objects[21] != null && objects[21].toString().trim().equalsIgnoreCase("T D S")) {
                salarySummaryBean.setTds(Double.parseDouble(objects[26].toString()));
            } else if (objects[22] != null && objects[22].toString().trim().equalsIgnoreCase("T D S")) {
                salarySummaryBean.setTds(Double.parseDouble(objects[27].toString()));
            } else if (objects[23] != null && objects[23].toString().trim().equalsIgnoreCase("T D S")) {
                salarySummaryBean.setTds(Double.parseDouble(objects[28].toString()));
            } else if (objects[24] != null && objects[24].toString().trim().equalsIgnoreCase("T D S")) {
                salarySummaryBean.setTds(Double.parseDouble(objects[29].toString()));
            } else if (objects[25] != null && objects[25].toString().trim().equalsIgnoreCase("T D S")) {
                salarySummaryBean.setTds(Double.parseDouble(objects[30].toString()));
            }

            // TDS

            if (objects[21] != null && objects[21].toString().trim().equalsIgnoreCase("VPF")) {
                salarySummaryBean.setVpf(Double.parseDouble(objects[26].toString()));
            } else if (objects[22] != null && objects[22].toString().trim().equalsIgnoreCase("VPF")) {
                salarySummaryBean.setVpf(Double.parseDouble(objects[27].toString()));
            } else if (objects[23] != null && objects[23].toString().trim().equalsIgnoreCase("VPF")) {
                salarySummaryBean.setVpf(Double.parseDouble(objects[28].toString()));
            } else if (objects[24] != null && objects[24].toString().trim().equalsIgnoreCase("VPF")) {
                salarySummaryBean.setVpf(Double.parseDouble(objects[29].toString()));
            } else if (objects[25] != null && objects[25].toString().trim().equalsIgnoreCase("VPF")) {
                salarySummaryBean.setVpf(Double.parseDouble(objects[30].toString()));
            }

            salarySummaryBean.setNetSalary(Double.parseDouble(objects[31].toString()));
            salarySummaryBean.setFactoryDesc(objects[39] != null ? objects[39].toString().trim() : "");
            salarySummaryBean.setSubCodeDesc(objects[40].toString().trim());
            if(objects[38] != null) {
                salarySummaryBean.setRdate((Timestamp) objects[41]);
            }

            Double othersArrear = salarySummaryBean.getGrossSalary() -
                ((salarySummaryBean.getBasic() != null ? salarySummaryBean.getBasic() : 0.0)
                    + (salarySummaryBean.getHra() != null ? salarySummaryBean.getHra() : 0.0)
                    + (salarySummaryBean.getConvAllow() != null ? salarySummaryBean.getConvAllow() : 0.0)
                    + (salarySummaryBean.getOthers() != null ? salarySummaryBean.getOthers() : 0.0)
                    + (salarySummaryBean.getSplAllow() != null ? salarySummaryBean.getSplAllow() : 0.0)
                    + (salarySummaryBean.getMedical() != null ? salarySummaryBean.getMedical() : 0.0));

            salarySummaryBean.setArrear(othersArrear);

            salarySummaryBeans.add(salarySummaryBean);
        }
        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/SalarySummary.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(salarySummaryBeans);


        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=SalarySummary.xlsx");

        final OutputStream outputStream = response.getOutputStream();

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }
}

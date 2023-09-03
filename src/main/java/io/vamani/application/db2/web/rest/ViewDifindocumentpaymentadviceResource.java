package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.model.*;
import io.vamani.application.db2.repository.AdstorageRepository;
import io.vamani.application.db2.repository.FindocumentlineRepository;
import io.vamani.application.db2.repository.ViewDifindocumentpaymentadviceRepository;
import io.vamani.application.model.MasterSearch;
import io.vamani.application.model.Message;
import io.vamani.application.service.MailService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.*;
import numWordConv.EnglishNumberToWords;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * REST controller for managing {@link ViewDifindocumentpaymentadvice}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ViewDifindocumentpaymentadviceResource {
    private final Logger log = LoggerFactory.getLogger(ViewDifindocumentpaymentadviceResource.class);

    private static final String ENTITY_NAME = "vieworderpartner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private Environment env;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private FindocumentlineRepository findocumentlineRepository;

    @Autowired
    private AdstorageRepository adstorageRepository;

    @Autowired
    private MailService mailService;

    private final ViewDifindocumentpaymentadviceRepository viewDifindocumentpaymentadviceRepository;

    public ViewDifindocumentpaymentadviceResource(ViewDifindocumentpaymentadviceRepository viewDifindocumentpaymentadviceRepository) {
        this.viewDifindocumentpaymentadviceRepository = viewDifindocumentpaymentadviceRepository;
    }

    @PostMapping("/viewdifindocumentpaymentadvice-filter")
    @Timed
    public ResponseEntity<List<ViewDifindocumentpaymentadvice>> getViewDifindocumentpaymentadvicefilter(@RequestBody PaymentadviceSearch search) throws ParseException {
        log.debug("REST request to get a page of viewDifindocumentpaymentadvice");
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by(Sort.Order.asc("postingdate"), Sort.Order.asc("chequenumber"))));
        String code = "%";
        if (search.getChequenumber() != null) {
            code = "%" + search.getChequenumber().toUpperCase() + "%";
        }

        String suppliercode = "%";
        if (search.getSupplier() != null && search.getSupplier().length()>0) {
            suppliercode = "%" + search.getSupplier().toUpperCase() + "%";
        }
        Page<ViewDifindocumentpaymentadvice> page = null;
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("A")) {
            if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("P") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByPostingDateAndChequeNumber("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("U") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByUtrDateAndChequeNumber("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else {
                page = viewDifindocumentpaymentadviceRepository.findAllByChequeNumber("100", code, suppliercode, suppliercode, search.getPage());
            }
        } else if(search.getStatus() != null && search.getStatus().equalsIgnoreCase("S")) {
            if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("P") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByPostingDateAndChequeNumberSent("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("U") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByUtrDateAndChequeNumberSent("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else {
                page = viewDifindocumentpaymentadviceRepository.findAllByChequeNumberSent("100", code, suppliercode, suppliercode, search.getPage());
            }
        } else if(search.getStatus() != null && search.getStatus().equalsIgnoreCase("U")) {
            if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("P") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByPostingDateAndChequeNumberUnsent("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("U") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByUtrDateAndChequeNumberUnsent("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else {
                page = viewDifindocumentpaymentadviceRepository.findAllByChequeNumberUnsent("100", code, suppliercode, suppliercode, search.getPage());
            }
        } else if(search.getStatus() != null && search.getStatus().equalsIgnoreCase("M")) {
            if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("P") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByPostingDateAndChequeNumberMissing("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("U") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByUtrDateAndChequeNumberMissing("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else {
                page = viewDifindocumentpaymentadviceRepository.findAllByChequeNumberMissing("100", code, suppliercode, suppliercode, search.getPage());
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        for(ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice : page.getContent()) {
            if(viewDifindocumentpaymentadvice.getUtrdate() != null) {
                java.util.Date utilDate = java.util.Date.from(viewDifindocumentpaymentadvice.getUtrdate().toInstant());
                utilDate = this.addHoursToJavaUtilDate(utilDate, -5);
                utilDate = this.addMinutesToJavaUtilDate(utilDate, -30);
                viewDifindocumentpaymentadvice.setUtrdate(new Timestamp(utilDate.getTime()));
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping("/viewdifindocumentpaymentadvice-report")
    @Timed
    public @ResponseBody void getViewDifindocumentpaymentadviceReport(@RequestBody PaymentadviceSearch search, HttpServletResponse response) throws ParseException, JRException, IOException {
        log.debug("REST request to get a page of viewDifindocumentpaymentadvice");
        search.setPage(PageRequest.of(0, 10000, Sort.by(Sort.Order.asc("postingdate"), Sort.Order.asc("chequenumber"))));
        String code = "%";
        if (search.getChequenumber() != null) {
            code = "%" + search.getChequenumber().toUpperCase() + "%";
        }

        String suppliercode = "%";
        if (search.getSupplier() != null && search.getSupplier().length()>0) {
            suppliercode = "%" + search.getSupplier().toUpperCase() + "%";
        }
        List<ViewDifindocumentpaymentadviceReport> viewDifindocumentpaymentadviceReports = new ArrayList<>();
        Page<ViewDifindocumentpaymentadvice> page = null;
        if (search.getStatus() != null && search.getStatus().equalsIgnoreCase("A")) {
            if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("P") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByPostingDateAndChequeNumber("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("U") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByUtrDateAndChequeNumber("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else {
                page = viewDifindocumentpaymentadviceRepository.findAllByChequeNumber("100", code, suppliercode, suppliercode, search.getPage());
            }
        } else if(search.getStatus() != null && search.getStatus().equalsIgnoreCase("S")) {
            if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("P") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByPostingDateAndChequeNumberSent("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("U") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByUtrDateAndChequeNumberSent("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else {
                page = viewDifindocumentpaymentadviceRepository.findAllByChequeNumberSent("100", code, suppliercode, suppliercode, search.getPage());
            }
        } else if(search.getStatus() != null && search.getStatus().equalsIgnoreCase("U")) {
            if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("P") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByPostingDateAndChequeNumberUnsent("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("U") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByUtrDateAndChequeNumberUnsent("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else {
                page = viewDifindocumentpaymentadviceRepository.findAllByChequeNumberUnsent("100", code, suppliercode, suppliercode, search.getPage());
            }
        } else if(search.getStatus() != null && search.getStatus().equalsIgnoreCase("M")) {
            if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("P") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByPostingDateAndChequeNumberMissing("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else if (search.getDateType() != null && search.getDateType().equalsIgnoreCase("U") && search.getDateFrom() != null && search.getDateTo() != null) {
                page = viewDifindocumentpaymentadviceRepository.findAllByUtrDateAndChequeNumberMissing("100", new java.sql.Date(Date.from(search.getDateFrom()).getTime()), new java.sql.Date(Date.from(search.getDateTo()).getTime()), code, suppliercode, suppliercode, search.getPage());
            } else {
                page = viewDifindocumentpaymentadviceRepository.findAllByChequeNumberMissing("100", code, suppliercode, suppliercode, search.getPage());
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
        for(ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice : page.getContent()) {
            ViewDifindocumentpaymentadviceReport viewDifindocumentpaymentadviceReport = new ViewDifindocumentpaymentadviceReport();
            BeanUtils.copyProperties(viewDifindocumentpaymentadvice, viewDifindocumentpaymentadviceReport);
            if(viewDifindocumentpaymentadvice.getUtrdate() != null) {
                java.util.Date utilDate = java.util.Date.from(viewDifindocumentpaymentadvice.getUtrdate().toInstant());
                utilDate = this.addHoursToJavaUtilDate(utilDate, -5);
                utilDate = this.addMinutesToJavaUtilDate(utilDate, -30);
                viewDifindocumentpaymentadvice.setUtrdate(new Timestamp(utilDate.getTime()));
                viewDifindocumentpaymentadviceReport.setUtrdate(simpleDateFormat.format(utilDate));
            }
            viewDifindocumentpaymentadviceReport.setCompanycode(viewDifindocumentpaymentadvice.getId().getCompanycode());
            viewDifindocumentpaymentadviceReport.setBusinessunitcode(viewDifindocumentpaymentadvice.getId().getBusinessunitcode());
            viewDifindocumentpaymentadviceReport.setFinancialyearcode(viewDifindocumentpaymentadvice.getId().getFinancialyearcode());
            viewDifindocumentpaymentadviceReport.setCode(viewDifindocumentpaymentadvice.getId().getCode());
            viewDifindocumentpaymentadviceReport.setPostingdate(simpleDateFormat2.format(new java.util.Date(viewDifindocumentpaymentadvice.getPostingdate().getTime())));
            viewDifindocumentpaymentadviceReports.add(viewDifindocumentpaymentadviceReport);
        }

        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/payment_advice.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(viewDifindocumentpaymentadviceReports);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=payment_advice.xlsx");

        final OutputStream outputStream = response.getOutputStream();

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    @PostMapping("/viewdifindocumentpaymentadvice-save")
    @Timed
    public ResponseEntity<List<ViewDifindocumentpaymentadvice>> getViewDifindocumentpaymentadvicefilter(@RequestBody List<ViewDifindocumentpaymentadviceBean> viewDifindocumentpaymentadviceBeans) throws ParseException, SQLException {
        log.debug("REST request to get a page of viewDifindocumentpaymentadvice");
        List<ViewDifindocumentpaymentadvice> viewDifindocumentpaymentadvices = new ArrayList<>();
        for (ViewDifindocumentpaymentadviceBean viewDifindocumentpaymentadvice : viewDifindocumentpaymentadviceBeans) {
            ViewDifindocumentpaymentadvice difindocumentpaymentadvice = viewDifindocumentpaymentadviceRepository.findById(viewDifindocumentpaymentadvice.getId()).orElse(null);
            if (viewDifindocumentpaymentadvice.getUtrnumber() != null && (viewDifindocumentpaymentadvice.getFlag() != null && viewDifindocumentpaymentadvice.getFlag().booleanValue() == true) && (difindocumentpaymentadvice.getAdvicesent() == null || difindocumentpaymentadvice.getAdvicesent().booleanValue() == false)) {
                Findocumentline findocumentline = findocumentlineRepository.findById(difindocumentpaymentadvice.getId().getCompanycode(), difindocumentpaymentadvice.getId().getBusinessunitcode(), difindocumentpaymentadvice.getId().getFinancialyearcode(), difindocumentpaymentadvice.getId().getCode(), difindocumentpaymentadvice.getId().getLinenumber());
                Adstorage result = null;
                if (findocumentline != null) {
                    try {
                        Adstorage adstorage = adstorageRepository.findById(new AdstorageId(findocumentline.getAbsuniqueid(), "FINDocumentLine", "adviceSent", "adviceSent")).orElse(null);
                        if (adstorage != null) {
                            adstorage.setValueboolean((short) 1);
                            result = adstorageRepository.save(adstorage);
                        } else {
                            adstorage = new Adstorage();
                            adstorage.setId(new AdstorageId(findocumentline.getAbsuniqueid(), "FINDocumentLine", "adviceSent", "adviceSent"));
                            adstorage.setKeysequence(0);
                            adstorage.setShared((short) 0);
                            adstorage.setDatatype(2);
                            adstorage.setValuestring(null);
                            adstorage.setValueint(0);
                            adstorage.setValueboolean((short) 1);
                            adstorage.setValuedate(null);
                            adstorage.setValuedecimal(null);
                            adstorage.setValuelong(0L);
                            adstorage.setValuetime(null);
                            adstorage.setValuetimestamp(null);
                            adstorage.setAbsuniqueid(0L);
                            result = adstorageRepository.save(adstorage);
                        }
                    } catch (Exception e) {
                    }
                }
                if (result != null && difindocumentpaymentadvice.getEmailaddress() != null && difindocumentpaymentadvice.getEmailaddress().length()>0) {
                    Connection conn = null;
                    try {
                        conn = db2DataSource().getConnection();
                        List<FinBankPaymentDocBean> finBankPaymentDocBeans = this.finBankPaymentDocument(conn, difindocumentpaymentadvice.getId().getCompanycode(), difindocumentpaymentadvice.getId().getBusinessunitcode(), difindocumentpaymentadvice.getId().getFinancialyearcode() + "", difindocumentpaymentadvice.getId().getCode());
                        String path = applicationProperties.getTemplatePath() + "jasper/";
                        JasperDesign jasperDesign = JRXmlLoader.load(path + "/FinDocumentBP.jrxml");
                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                        Map<String, Object> parameters = new HashMap<>();
                        JRDataSource jrDataSource = new JRBeanCollectionDataSource(finBankPaymentDocBeans);
                        parameters.put("datasource", jrDataSource);
                        parameters.put("SUBREPORT_DIR", path);
                        parameters.put("SUBREPORT_CONN", conn);
                        parameters.put("paymentReq", "Y");

                        String fileName = path + "/" + difindocumentpaymentadvice.getId().getCode() + ".pdf";
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                        JRPdfExporter jrPdfExporter = new JRPdfExporter();
                        ExporterInput exportInput = (ExporterInput) new SimpleExporterInput(jasperPrint);

                        jrPdfExporter.setExporterInput(exportInput);
                        OutputStreamExporterOutput jasperOutputPdf = new SimpleOutputStreamExporterOutput(fileName); //specify the path where we want to save the report

                        jrPdfExporter.setExporterOutput(jasperOutputPdf);

                        SimplePdfExporterConfiguration simple = new SimplePdfExporterConfiguration();

                        jrPdfExporter.setConfiguration(simple);

                        jrPdfExporter.exportReport();

                        String subject = "Payment Advice from Vamani Overseas ; Cheque number " + difindocumentpaymentadvice.getChequenumber() + " ; " + difindocumentpaymentadvice.getCustomersuppliername() + " ; Code " + difindocumentpaymentadvice.getCustomersuppliercode();

                        String content = "<!DOCTYPE html>"
                            + " <html>"
                            + " <head>"
                            + " <meta charset=\"ISO-8859-1\">"
                            + " <title>" + subject + "</title>"
                            + " </head>"
                            + " <body>"
                            + " <p></p>"
                            + " <div>"
                            + " <p>Dear Receiver;</p>"
                            + " <p>Please find attached payment advise as subjected having invoice detail for your records.</p>"
                            + " <p>Requesting not to revert back on this email address as this is a system generated message.</p>"
                            + " <br/>"
                            + " </div>"
                            + " <p>Regards</p>"
                            + " <p>Finance Team</p>"
                            + " <p>C/o Vamani Overseas Pvt Ltd</p>"
                            + " </body>"
                            + " </html>";
                        mailService.sendEmailWithCC(difindocumentpaymentadvice.getEmailaddress(), "account1@vamanioverseas.com", subject, content, true, true, fileName);
                        // mailService.sendEmailWithCC("manishbhutani@vamanioverseas.com", "manishbhutani@vamanioverseas.com", subject, content, true, true, fileName);
                    } catch (Exception e) {
                        if(conn != null) { conn.close();} conn = null;
                    }
                }
            }
        }
        return ResponseEntity.ok().body(viewDifindocumentpaymentadvices);
    }

    @PostMapping("/viewdifindocumentpaymentadvice-resend")
    @Timed
    public ResponseEntity<List<ViewDifindocumentpaymentadvice>> getViewDifindocumentpaymentadviceResend(@RequestBody List<ViewDifindocumentpaymentadviceBean> viewDifindocumentpaymentadviceBeans) throws ParseException, SQLException {
        log.debug("REST request to get a page of viewDifindocumentpaymentadvice");
        List<ViewDifindocumentpaymentadvice> viewDifindocumentpaymentadvices = new ArrayList<>();
        for (ViewDifindocumentpaymentadviceBean viewDifindocumentpaymentadvice : viewDifindocumentpaymentadviceBeans) {
            ViewDifindocumentpaymentadvice difindocumentpaymentadvice = viewDifindocumentpaymentadviceRepository.findById(viewDifindocumentpaymentadvice.getId()).orElse(null);
            if (viewDifindocumentpaymentadvice.getUtrnumber() != null) {
                if (difindocumentpaymentadvice.getEmailaddress() != null && difindocumentpaymentadvice.getEmailaddress().length()>0) {
                    Connection conn = null;
                    try {
                        conn = db2DataSource().getConnection();
                        List<FinBankPaymentDocBean> finBankPaymentDocBeans = this.finBankPaymentDocument(conn, difindocumentpaymentadvice.getId().getCompanycode(), difindocumentpaymentadvice.getId().getBusinessunitcode(), difindocumentpaymentadvice.getId().getFinancialyearcode() + "", difindocumentpaymentadvice.getId().getCode());
                        String path = applicationProperties.getTemplatePath() + "jasper/";
                        JasperDesign jasperDesign = JRXmlLoader.load(path + "/FinDocumentBP.jrxml");
                        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                        Map<String, Object> parameters = new HashMap<>();
                        JRDataSource jrDataSource = new JRBeanCollectionDataSource(finBankPaymentDocBeans);
                        parameters.put("datasource", jrDataSource);
                        parameters.put("SUBREPORT_DIR", path);
                        parameters.put("SUBREPORT_CONN", conn);
                        parameters.put("paymentReq", "Y");

                        String fileName = path + "/" + difindocumentpaymentadvice.getId().getCode() + ".pdf";
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
                        JRPdfExporter jrPdfExporter = new JRPdfExporter();
                        ExporterInput exportInput = (ExporterInput) new SimpleExporterInput(jasperPrint);

                        jrPdfExporter.setExporterInput(exportInput);
                        OutputStreamExporterOutput jasperOutputPdf = new SimpleOutputStreamExporterOutput(fileName); //specify the path where we want to save the report

                        jrPdfExporter.setExporterOutput(jasperOutputPdf);

                        SimplePdfExporterConfiguration simple = new SimplePdfExporterConfiguration();

                        jrPdfExporter.setConfiguration(simple);

                        jrPdfExporter.exportReport();

                        String subject = "Payment Advice from Vamani Overseas ; Cheque number " + difindocumentpaymentadvice.getChequenumber() + " ; " + difindocumentpaymentadvice.getCustomersuppliername() + " ; Code " + difindocumentpaymentadvice.getCustomersuppliercode();

                        String content = "<!DOCTYPE html>"
                            + " <html>"
                            + " <head>"
                            + " <meta charset=\"ISO-8859-1\">"
                            + " <title>" + subject + "</title>"
                            + " </head>"
                            + " <body>"
                            + " <p></p>"
                            + " <div>"
                            + " <p>Dear Receiver;</p>"
                            + " <p>Please find attached payment advise as subjected having invoice detail for your records.</p>"
                            + " <p>Requesting not to revert back on this email address as this is a system generated message.</p>"
                            + " <br/>"
                            + " </div>"
                            + " <p>Regards</p>"
                            + " <p>Finance Team</p>"
                            + " <p>C/o Vamani Overseas Pvt Ltd</p>"
                            + " </body>"
                            + " </html>";
                        mailService.sendEmailWithCC(difindocumentpaymentadvice.getEmailaddress(), "account1@vamanioverseas.com", subject, content, true, true, fileName);
                        // mailService.sendEmailWithCC("manishbhutani@vamanioverseas.com", "manishbhutani@vamanioverseas.com", subject, content, true, true, fileName);
                    } catch (Exception e) {
                        if(conn != null) { conn.close();} conn = null;
                    }
                }
            }
        }
        return ResponseEntity.ok().body(viewDifindocumentpaymentadvices);
    }

    @PostMapping("/viewdifindocumentpaymentadvice-download")
    @Timed
    void getViewDifindocumentpaymentadviceDownload(@RequestBody MasterSearch search, HttpServletResponse response) throws SQLException {
        log.debug("REST request to get a page of viewDifindocumentpaymentadvice");

        Connection conn = null;
        try {
            conn = db2DataSource().getConnection();
            List<FinBankPaymentDocBean> finBankPaymentDocBeans = this.finBankPaymentDocument(conn, search.getCompanyCode(), search.getBusinessunitcode(), search.getFinyearcode(), search.getCode());
            String path = applicationProperties.getTemplatePath() + "jasper/";
            JasperDesign jasperDesign = JRXmlLoader.load(path + "/FinDocumentBP.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Map<String, Object> parameters = new HashMap<>();
            JRDataSource jrDataSource = new JRBeanCollectionDataSource(finBankPaymentDocBeans);
            parameters.put("datasource", jrDataSource);
            parameters.put("SUBREPORT_DIR", path);
            parameters.put("SUBREPORT_CONN", conn);
            parameters.put("paymentReq", "Y");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "attachment; filename=PurchaseOrderGeneral.pdf");
            final OutputStream outputStream = response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (Exception e) {
            System.out.println("ViewDifindocumentpaymentadviceResource getViewDifindocumentpaymentadviceDownload()" + e.getMessage());
        } finally {
            if (conn != null) {
                conn.close();
            }
            conn = null;
        }
    }

    public List<FinBankPaymentDocBean> finBankPaymentDocument(Connection conn, String companycode, String businessunitcode, String finyearcode, String findoccode) throws Exception {
        DecimalFormat df = new DecimalFormat("#0.00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        PreparedStatement stmt = null;
        ResultSet result = null;
        List<FinBankPaymentDocBean> finBankPaymentDocBeans = new ArrayList<>();

        String query = "";
        query = "SELECT " + " (SELECT MAX(C1.LONGDESCRIPTION) FROM FINDOCUMENTLINE F1, FINPROFITCENTER C1 WHERE F1.PROFITCENTERCODE = C1.CODE AND F1.FINDOCUMENTCODE=FD.CODE) PROFITCENTERDESC, " + " (SELECT MAX(F1.PROFITCENTERCODE) FROM FINDOCUMENTLINE F1, FINPROFITCENTER C1 WHERE F1.PROFITCENTERCODE = C1.CODE AND F1.FINDOCUMENTCODE=FD.CODE) PROFITCENTERCODE," + " FD.BUSINESSUNITCODE, FD.FINANCIALYEARCODE, NVL(FY.SHORTDESCRIPTION,'') FINYEARDESC, NVL(FM.CODE,'') FINANCIALMONTH," + " CASE WHEN FD.BUSINESSUNITCODE='B01' THEN 'GARMENT DIVISION - FARIDABAD' WHEN FD.BUSINESSUNITCODE='B02' THEN 'GARMENT DIVISION - NOIDA' END HEADER_TEMPLATE," + " FD.DOCUMENTTEMPLATECODE, FDT.LONGDESCRIPTION TEMPLATENAME, FDT.SHORTDESCRIPTION TEMPLATESHORTNAME, NVL((SELECT SHORTDESCRIPTION FROM GLMASTER WHERE CODE=FD.GLCODE),'') GLDESCRIPTION," + " FD.DOCUMENTTYPECODE, NVL(FT.LONGDESCRIPTION,'') DOCUMENTTYPE," + " NVL(FD.CODE,'') DOCUMENTNO, TO_CHAR(FD.POSTINGDATE,'DD/MM/YYYY') DOCUMENTDATE, TO_CHAR(FD.FINANCEDOCUMENTDATE,'DD/MM/YYYY') NEWDOCUMENTDATE," + " TO_CHAR(FD.POSTINGDATE,'dd/MM/yyyy') POSTINGDATE," + " NVL(FD.DOCCOMPANYCURRENCYCODE,'') CURRENCTCODE, NVL(FD.DOCUMENTCURRENCYCODE,'') DOCCURRENCTCODE, FD.EXCHANGERATE, " + " NVL(CASE WHEN FD.DOCUMENTTEMPLATECODE='RV2' THEN FD.INVOICENO ELSE   FD.CHEQUENUMBER END,fd.proposalrefno) CHEQUENUMBER, " + " nvl(CASE WHEN FD.DOCUMENTTEMPLATECODE='RV2' THEN to_char(FD.INVOICEDATE,'dd/mm/yyyy') ELSE TO_CHAR(FD.CHEQUEDATE,'dd/MM/yyyy') END,to_char(fd.postingdate,'dd/mm/yyyy')) CHEQUEDATE, FD.REMARK," + " DECODE(FD.CURRENTSTATUS,'0','SUSPENDED','1','ACTIVE') CURRENTSTATUS," + " NVL((SELECT VALUESTRING FROM ADSTORAGE WHERE NAMEENTITYNAME='FINDocument' AND NAMENAME='Remarks2' AND FIELDNAME='Remarks2' AND UNIQUEID=FD.ABSUNIQUEID), '') NARRATION, " + " UPPER(FD.CREATIONUSER) CREATIONUSER, TO_CHAR(FD.CREATIONDATETIME,'DD/MM/YYYY HH24:MI:SS') CREATIONDATETIME, " + " (SELECT MAX(NVL(CASE WHEN FD1.DOCUMENTTEMPLATECODE = 'OB' THEN FD1.REFERENCETEXT1 " + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'P01' THEN (SELECT TRIM(CODE) FROM PURCHASEINVOICE WHERE FINDOCBUSINESSUNITCODE = FD1.BUSINESSUNITCODE AND FINDOCFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FINDOCCODE=FD1.CODE FETCH FIRST ROW ONLY) " + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'P02' THEN NVL((SELECT CODE FROM DIRECTINVOICE WHERE FINDOCBUSINESSUNITCODE = FD1.BUSINESSUNITCODE AND FINDOCFINANCIALYEARCODE = FD1.FINANCIALYEARCODE AND FINDOCCODE=FD1.CODE  FETCH FIRST ROW ONLY), FD1.INVOICENO) " + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'B03' THEN FD1.CHEQUENUMBER" + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'VD1' THEN (SELECT TRIM(MH.INVOICENO) FROM MRNREJECTIONDETAIL MD, MRNHEADER MH WHERE MH.CODE=MD.MDMRNHEADERCODE  AND MD.MDMRNHEADERCODE=FD1.MRNREJMDMRNHEADERCODE FETCH FIRST ROW ONLY) " + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'M01' THEN (SELECT INVOICENO FROM MRNHEADER WHERE CODE=FD1.MRNCODE)" + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'S01' THEN (SELECT CASE WHEN INVOICETYPECODE IN('D02', 'D03') THEN CHALLANNO ELSE CODE END CODE FROM PLANTINVOICE WHERE DIVISIONCODE = FD1.PLANTINVOICEDIVISIONCODE AND CODE=FD1.PLANTINVOICECODE)" + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'S02' THEN (SELECT CODE FROM COMMERCIALINVOICE WHERE CODE=FD1.COMMERCIALINVOICECODE) ELSE FD1.CODE END, FD1.CODE)) " + " FROM FINDOCUMENT FD1 WHERE FD1.BUSINESSUNITCODE = FD.BUSINESSUNITCODE AND FD1.FINANCIALYEARCODE = FD.FINANCIALYEARCODE AND FD1.CODE = FD.CODE) INVOICENO," + " (SELECT MAX(NVL(CASE WHEN FD1.DOCUMENTTEMPLATECODE = 'OB' THEN CAST(TRIM(FD1.REFERENCETEXT2) AS DATE)" + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'P01' THEN (SELECT INVOICEDATE FROM PURCHASEINVOICE WHERE FINDOCBUSINESSUNITCODE = FD1.BUSINESSUNITCODE AND FINDOCFINANCIALYEARCODE=FD.FINANCIALYEARCODE AND FINDOCCODE=FD1.CODE FETCH FIRST ROW ONLY)" + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'P02' THEN NVL((SELECT DOCUMENTDATE FROM DIRECTINVOICE WHERE FINDOCBUSINESSUNITCODE = FD1.BUSINESSUNITCODE AND FINDOCFINANCIALYEARCODE = FD1.FINANCIALYEARCODE AND FINDOCCODE=FD1.CODE FETCH FIRST ROW ONLY), FD1.INVOICEDATE)" + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'B03' THEN FD1.CHEQUEDATE" + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'VD1' THEN (SELECT MH.INVOICEDATE FROM MRNREJECTIONDETAIL MD, MRNHEADER MH WHERE MH.CODE=MD.MDMRNHEADERCODE AND MD.MDMRNHEADERCODE=FD1.MRNREJMDMRNHEADERCODE FETCH FIRST ROW ONLY)" + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'M01' THEN (SELECT INVOICEDATE FROM MRNHEADER WHERE CODE=FD1.MRNCODE)" + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'S01' THEN (SELECT CASE WHEN INVOICETYPECODE IN('D02', 'D03') THEN CHALLANDATE ELSE INVOICEDATE END INVOICEDATE FROM PLANTINVOICE WHERE DIVISIONCODE = FD1.PLANTINVOICEDIVISIONCODE AND CODE=FD1.PLANTINVOICECODE)" + "                      WHEN FD1.DOCUMENTTEMPLATECODE = 'S02' THEN (SELECT INVOICEDATE FROM COMMERCIALINVOICE WHERE CODE=FD1.COMMERCIALINVOICECODE FETCH FIRST ROW ONLY) ELSE FD1.POSTINGDATE END, FD1.POSTINGDATE)) " + " FROM FINDOCUMENT FD1 WHERE FD1.BUSINESSUNITCODE = FD.BUSINESSUNITCODE AND FD1.FINANCIALYEARCODE = FD.FINANCIALYEARCODE AND FD1.CODE = FD.CODE)  INVOICEDATE," + " NVL(O1.LEGALNAME1,'') PARTYNAME, NVL(ST1.LONGDESCRIPTION,'') STATENAME, AG.GSTINNUMBER, FD.SUPPLIERCODE, FD.BUSINESSUNITCODE,  " + " NVL((SELECT MAX(BC.DIVISIONCODE) FROM BUSINESSUNITVSCOMPANY BC WHERE BC.BUSINESSUNITCODE = FD.BUSINESSUNITCODE), '')  DIVISION, FD.REFFINDOCCODE" + " FROM FINDOCUMENT FD LEFT OUTER JOIN VIEWORDERPARTNER O1 ON FD.COMPANYCODE = O1.CUSTOMERSUPPLIERCOMPANYCODE AND O1.CUSTOMERSUPPLIERTYPE = '2' AND FD.SUPPLIERCODE = O1.CUSTOMERSUPPLIERCODE " + " LEFT OUTER JOIN ADDRESSGST AG ON O1.BPABSUNIQUEID = AG.UNIQUEID LEFT OUTER JOIN STATE ST1 ON AG.STATECODE = ST1.CODE, " + " FINDOCUMENTTEMPLATE FDT, FINANCEMONTH FM, FINDOCUMENTTYPE FT, FINFINANCIALYEAR FY " + " WHERE FDT.COMPANYCODE = FD.COMPANYCODE AND FDT.DOCUMENTTYPECODE = FD.DOCUMENTTYPECODE AND FDT.CODE = FD.DOCUMENTTEMPLATECODE AND" + " FD.COMPANYCODE = FM.COMPANYCODE AND FD.FINANCIALYEARCODE = FM.FINANCIALYEARCODE AND FD.BUSINESSUNITCODE = FM.BUSINESSUNITCODE AND FD.FINANCEMONTHCODE = FM.CODE AND" + " FD.DOCUMENTTYPECODE = FT.CODE AND FD.COMPANYCODE = FY.COMPANYCODE AND FD.FINANCIALYEARCODE = FY.CODE AND" + " FD.COMPANYCODE = ? AND FD.BUSINESSUNITCODE = ? AND FD.FINANCIALYEARCODE = ? AND FD.CODE = ?";
        query += " ORDER BY FD.CODE";
        try {
            stmt = conn.prepareStatement(query);
            stmt.setString(1, companycode);
            stmt.setString(2, businessunitcode);
            stmt.setString(3, finyearcode);
            stmt.setString(4, findoccode);
            result = stmt.executeQuery();
            while (result.next()) {
                String narrationBR = result.getString("DOCCURRENCTCODE");
                Double totalValue = 0.0;
                FinBankPaymentDocBean finBankPaymentDocBean = new FinBankPaymentDocBean();
                finBankPaymentDocBean.setHeaderTemplate(result.getString("HEADER_TEMPLATE"));
                finBankPaymentDocBean.setFinancialyearcode(result.getString("finYearDesc"));
                if (result.getString("FINANCIALMONTH") != null && result.getString("FINANCIALMONTH").length() > 0) {
                    finBankPaymentDocBean.setFinancialmonth(new SimpleDateFormat("MMM-yyyy").format(new SimpleDateFormat("yyMM").parse(result.getString("FINANCIALMONTH").trim())));
                }
                finBankPaymentDocBean.setReffindoccode(result.getString("REFFINDOCCODE"));
                finBankPaymentDocBean.setDivision(result.getString("DIVISION"));
                finBankPaymentDocBean.setBusinessunit(result.getString("BUSINESSUNITCODE"));
                finBankPaymentDocBean.setNewdocumentdate(result.getString("newdocumentdate"));
                finBankPaymentDocBean.setDocumentno(result.getString("DOCUMENTNO"));
                finBankPaymentDocBean.setDocumenttype(result.getString("DOCUMENTTYPE"));
                finBankPaymentDocBean.setDoccurrenctcode(result.getString("DOCCURRENCTCODE"));
                finBankPaymentDocBean.setChequenumber(result.getString("CHEQUENUMBER"));
                finBankPaymentDocBean.setChequedate(result.getString("CHEQUEDATE"));
                finBankPaymentDocBean.setDocumentdate(result.getString("DOCUMENTDATE"));
                finBankPaymentDocBean.setExchangerate(result.getBigDecimal("EXCHANGERATE"));
                finBankPaymentDocBean.setPostingdate(result.getString("POSTINGDATE"));
                finBankPaymentDocBean.setCurrenctcode(result.getString("CURRENCTCODE"));
                finBankPaymentDocBean.setRemark(result.getString("REMARK"));
                finBankPaymentDocBean.setCreationdatetime(result.getString("creationdatetime"));
                finBankPaymentDocBean.setPartyname(result.getString("partyname"));
                finBankPaymentDocBean.setStatename(result.getString("statename"));
                finBankPaymentDocBean.setGstinnumber(result.getString("gstinnumber"));
                finBankPaymentDocBean.setSuppliercode(result.getString("suppliercode"));
                finBankPaymentDocBean.setCurrentstatus(result.getString("CURRENTSTATUS"));
                finBankPaymentDocBean.setNarration(result.getString("NARRATION") != null && result.getString("NARRATION").length() > 0 ? result.getString("NARRATION") : (result.getString("REMARK") != null ? result.getString("REMARK") : ""));
                finBankPaymentDocBean.setPreparedBy(result.getString("CREATIONUSER").toUpperCase());
                finBankPaymentDocBean.setInvoiceno(result.getString("INVOICENO"));
                finBankPaymentDocBean.setInvoicedate(result.getString("INVOICEDATE") != null ? simpleDateFormat.format(result.getDate("INVOICEDATE")) : "");
                finBankPaymentDocBean.setProfitcentercode(result.getString("PROFITCENTERCODE"));
                finBankPaymentDocBean.setProfitcenterdesc(result.getString("PROFITCENTERDESC"));

                List<FinBankPaymentCreditBean> finBankPaymentCreditBeans = new ArrayList<>();

                PreparedStatement stmt2 = conn.prepareStatement("SELECT FDL.GLCODE, NVL(GM.LONGDESCRIPTION,'') SEARCHDESCRIPTION, " + " OP.CUSTOMERSUPPLIERCODE ORDERPARTNER, NVL(OP.LEGALNAME1,'') ORDERPARTNERDESC," + " TO_CHAR(FDL.CREATIONDATETIME, 'DD/MM/YYYY') ENTRYDATE, FDL.PROFITCENTERCODE, FDL.COSTCENTERCODE, SUM(FDL.AMOUNTINDC) AMOUNTINDC, " + " MAX(NVL(case when FDL.GLCODE ='33101' then (nvl(fd.EXCHANGERATE,0) - (select MIN(EXCHANGERATE) from findocumentline where FINDOCUMENTCODE=fdl.FINDOCUMENTCODE AND GLCODE <> '33101')) ELSE fdl.EXCHANGERATE END,0)) exchangerate,  " + " SUM(FDL.AMOUNTINCC) AMOUNTINCC, FDL.COMMENTS, CC.LONGDESCRIPTION COSTCENTER, " + " MAX(NVL((SELECT VALUESTRING FROM ADSTORAGE" + " WHERE NAMEENTITYNAME='FINDocumentLine' AND NAMENAME='ChequeUTR' AND FIELDNAME='ChequeUTR' AND UNIQUEID=FDL.ABSUNIQUEID),'')) CHEQUEREF, " + " MAX((SELECT max(VALUESTRING) FROM ADSTORAGE AD WHERE AD.NAMEENTITYNAME='FINDocumentLine' AND AD.NAMENAME='PayTo' AND AD.FIELDNAME='PayTo' " + " AND AD.UNIQUEID in (select ABSUNIQUEID from FINDOCUMENTLINE WHERE FINDOCUMENTBUSINESSUNITCODE=FDL.FINDOCUMENTBUSINESSUNITCODE " + " AND FINDOCUMENTFINANCIALYEARCODE=FDL.FINDOCUMENTFINANCIALYEARCODE AND FINDOCUMENTCODE=FDL.FINDOCUMENTCODE))) PAYTO, AG.GSTINNUMBER " + " FROM findocument fd, FINDOCUMENTLINE FDL, GLMASTER GM left outer join COSTCENTER CC on FDL.COSTCENTERCODE = CC.CODE" + " LEFT OUTER JOIN VIEWORDERPARTNER OP ON FDL.SLCUSTOMERSUPPLIERCODE = OP.CUSTOMERSUPPLIERCODE" + " LEFT OUTER JOIN ADDRESSGST AG on AG.UNIQUEID = OP.BPABSUNIQUEID" + " WHERE fd.code = fdl.FINDOCUMENTCODE and fd.BUSINESSUNITCODE=fdl.FINDOCUMENTBUSINESSUNITCODE and fd.FINANCIALYEARCODE=fdl.FINDOCUMENTFINANCIALYEARCODE " + " and  FDL.GLCODE = GM.CODE AND FDL.FINDOCUMENTBUSINESSUNITCODE = ? AND FDL.FINDOCUMENTFINANCIALYEARCODE = ?" + " AND FDL.FINDOCUMENTCODE = ? GROUP BY FDL.GLCODE, NVL(GM.LONGDESCRIPTION,''), OP.CUSTOMERSUPPLIERCODE, NVL(OP.LEGALNAME1,''), TO_CHAR(FDL.CREATIONDATETIME, 'DD/MM/YYYY')," + " FDL.PROFITCENTERCODE, FDL.COSTCENTERCODE, FDL.COMMENTS, CC.LONGDESCRIPTION, AG.GSTINNUMBER ORDER BY 10 DESC");
                stmt2.setString(1, result.getString("BUSINESSUNITCODE"));
                stmt2.setString(2, result.getString("FINANCIALYEARCODE"));
                stmt2.setString(3, result.getString("DOCUMENTNO"));
                ResultSet result2 = stmt2.executeQuery();
                Double narrationValueBR = 0.0;
                while (result2.next()) {
                    FinBankPaymentCreditBean finBankPaymentCreditBean = new FinBankPaymentCreditBean();
                    if (result.getString("DOCUMENTTYPECODE") != null && result.getString("DOCUMENTTYPECODE").trim().equalsIgnoreCase("BP")) {
                        finBankPaymentDocBean.setTemplatename(result.getString("GLDESCRIPTION").toUpperCase());
                        if (result2.getString("ORDERPARTNERDESC") != null && result2.getString("ORDERPARTNERDESC").length() > 0) {
                            finBankPaymentCreditBean.setGlcode(result2.getString("ORDERPARTNER"));
                            if (result2.getString("GSTINNUMBER") != null) {
                                finBankPaymentCreditBean.setDescriptions(result2.getString("ORDERPARTNERDESC").trim() + " @ " + result2.getString("GSTINNUMBER"));
                            } else {
                                finBankPaymentCreditBean.setDescriptions(result2.getString("ORDERPARTNERDESC"));
                            }
                            finBankPaymentDocBean.setPayto(result2.getString("payto"));
                        } else {
                            finBankPaymentCreditBean.setGlcode(result2.getString("GLCODE"));
                            finBankPaymentCreditBean.setDescriptions(result2.getString("SEARCHDESCRIPTION"));
                            finBankPaymentDocBean.setPayto(result2.getString("payto"));
                        }
                    } else if (result.getString("DOCUMENTTYPECODE") != null && result.getString("DOCUMENTTYPECODE").trim().equalsIgnoreCase("JV")) {
                        finBankPaymentDocBean.setTemplatename(result.getString("DOCUMENTTYPE").toUpperCase());
                        if (result2.getString("ORDERPARTNERDESC") != null && result2.getString("ORDERPARTNERDESC").length() > 0) {
                            finBankPaymentCreditBean.setGlcode(result2.getString("ORDERPARTNER"));
                            if (result2.getString("GSTINNUMBER") != null) {
                                finBankPaymentCreditBean.setDescriptions(result2.getString("ORDERPARTNERDESC").trim() + " @ " + result2.getString("GSTINNUMBER"));
                            } else {
                                finBankPaymentCreditBean.setDescriptions(result2.getString("ORDERPARTNERDESC"));
                            }
                        } else {
                            finBankPaymentCreditBean.setGlcode(result2.getString("GLCODE"));
                            finBankPaymentCreditBean.setDescriptions(result2.getString("SEARCHDESCRIPTION"));
                        }
                    } else {
                        if (result2.getString("ORDERPARTNERDESC") != null && result2.getString("ORDERPARTNERDESC").length() > 0) {
                            finBankPaymentCreditBean.setGlcode(result2.getString("ORDERPARTNER"));
                            if (result2.getString("GSTINNUMBER") != null) {
                                finBankPaymentCreditBean.setDescriptions(result2.getString("ORDERPARTNERDESC").trim() + " @ " + result2.getString("GSTINNUMBER"));
                            } else {
                                finBankPaymentCreditBean.setDescriptions(result2.getString("ORDERPARTNERDESC"));
                            }
                            narrationValueBR += result2.getDouble("AMOUNTINDC");
                        } else {
                            finBankPaymentCreditBean.setGlcode(result2.getString("GLCODE"));
                            finBankPaymentCreditBean.setDescriptions(result2.getString("SEARCHDESCRIPTION"));
                        }
                        finBankPaymentDocBean.setTemplatename(result.getString("DOCUMENTTYPE").toUpperCase());
                    }
                    finBankPaymentCreditBean.setOrderpartner(result2.getString("ORDERPARTNER"));
                    if (result2.getString("GSTINNUMBER") != null) {
                        finBankPaymentCreditBean.setOrderpartnerdesc(result2.getString("ORDERPARTNERDESC").trim() + " @ " + result2.getString("GSTINNUMBER"));
                    } else {
                        finBankPaymentCreditBean.setOrderpartnerdesc(result2.getString("ORDERPARTNERDESC"));
                    }

                    finBankPaymentCreditBean.setProfitcentercode(result2.getString("PROFITCENTERCODE"));
                    finBankPaymentCreditBean.setCostcentercode(result2.getString("COSTCENTERCODE"));
                    finBankPaymentCreditBean.setCostcenterdescription(result2.getString("COSTCENTER"));
                    if (finBankPaymentDocBean.getCostcentercode() == null) {
                        finBankPaymentDocBean.setCostcentercode(result2.getString("COSTCENTERCODE"));
                        finBankPaymentDocBean.setCostcenterdescription(result2.getString("COSTCENTER"));
                    }
                    finBankPaymentCreditBean.setAmountindc(result2.getBigDecimal("AMOUNTINDC"));
                    finBankPaymentCreditBean.setAmountincc(result2.getBigDecimal("AMOUNTINCC"));
                    finBankPaymentCreditBean.setExchangerate(result2.getBigDecimal("exchangerate"));
                    finBankPaymentCreditBean.setComments(result2.getString("COMMENTS"));
                    finBankPaymentCreditBean.setChequeRef(result2.getString("CHEQUEREF"));
                    finBankPaymentCreditBean.setEntrydate(result2.getString("ENTRYDATE"));

                    if (result2.getBigDecimal("AMOUNTINCC").doubleValue() > 0) {
                        totalValue += result2.getBigDecimal("AMOUNTINCC").doubleValue();
                    }
                    finBankPaymentCreditBeans.add(finBankPaymentCreditBean);
                }
                if (result2 != null) result2.close();
                if (stmt2 != null) stmt2.close();
                if (result.getString("DOCUMENTTEMPLATECODE") != null && result.getString("DOCUMENTTEMPLATECODE").trim().equalsIgnoreCase("B12")) {
                    narrationBR = narrationBR + df.format(narrationValueBR.doubleValue()) + " @ " + df.format(result.getDouble("EXCHANGERATE"));
                    finBankPaymentDocBean.setNarration(narrationBR);
                }

                if (result.getString("DOCUMENTTEMPLATECODE") != null && result.getString("DOCUMENTTEMPLATECODE").trim().equalsIgnoreCase("B04")) {
                    PreparedStatement stat22 = conn.prepareStatement("SELECT NVL (PD.REMARK, '') REMARK" + " FROM FINPaymentProposal PD, FINPaymentProposalLine PDL" + " WHERE PD.COMPANYCODE = PDL.FINPAYMENTPROPOSALCOMPANYCODE AND PD.CODE = PDL.FINPAYMENTPROPOSALCODE AND" + " PDLBUSINESSUNITCODE =? AND PDLFINANCIALYEARCODE =? AND PDLCode = ?");
                    stat22.setString(1, result.getString("BUSINESSUNITCODE"));
                    stat22.setString(2, result.getString("FINANCIALYEARCODE"));
                    stat22.setString(3, result.getString("DOCUMENTNO"));
                    ResultSet result22 = stat22.executeQuery();
                    while (result22.next()) {
                        String NewNarration = result22.getString("REMARK");
                        String tempNarration = result.getString("NARRATION") != null && result.getString("NARRATION").length() > 0 ? result.getString("NARRATION") : (result.getString("REMARK") != null ? result.getString("REMARK") : "");
                        if (tempNarration != null && tempNarration.length() > 0) {
                            NewNarration = NewNarration + ". " + tempNarration;
                        }
                        finBankPaymentDocBean.setNarration(NewNarration);
                    }
                    if (result22 != null) result22.close();
                    if (stat22 != null) stat22.close();
                }
                finBankPaymentDocBean.setFinBankPaymentCreditBeans(finBankPaymentCreditBeans);
                if (totalValue != null && totalValue.doubleValue() > 0) {
                    String numbertoWord = EnglishNumberToWords.callNumToWord(df.format(totalValue), 0);
                    if (numbertoWord.contains(" And ")) {
                        finBankPaymentDocBean.setAmountInWord(numbertoWord + " Paisa Only");
                    } else {
                        finBankPaymentDocBean.setAmountInWord(numbertoWord + " Only");
                    }
                }
                finBankPaymentDocBeans.add(finBankPaymentDocBean);
            }
        } catch (Exception e) {
            // e.printStackTrace();
            log.error("FinBankPaymentImpl.class (M: FinBankPaymentImpl; Exception: " + e.getMessage() + ")");
        } finally {
            if (result != null) {
                result.close();
            }
            result = null;

            if (stmt != null) {
                stmt.close();
            }
            stmt = null;
        }
        return finBankPaymentDocBeans;
    }

    private DataSource db2DataSource() {
        SingleConnectionDataSource dataSource = new SingleConnectionDataSource();
        dataSource.setDriverClassName(env.getProperty("db2.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("db2.datasource.url"));
        dataSource.setUsername(env.getProperty("db2.datasource.username"));
        dataSource.setPassword(env.getProperty("db2.datasource.password"));
        dataSource.setAutoCommit(true);
        return dataSource;
    }

    /**
     * GET  /feedbacks/:id : get the "id" feedback.
     *
     * @param id the id of the feedback to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the feedback, or with status 404 (Not Found)
     */
    @GetMapping("/viewdifindocumentpaymentadvice-sample")
    @Timed
    public ResponseEntity<Object> downloadSample() throws FileNotFoundException, IOException {
        log.debug("REST request to get Feedback : {}");
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        File file = new File(UPLOADED_FOLDER + "sample/UTR_SAMPLE.xlsx");
        Path path = Paths.get(UPLOADED_FOLDER + "sample/UTR_SAMPLE.xlsx");
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
    @PostMapping(value = "/viewdifindocumentpaymentadvice-upload", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<Message> viewdifindocumentpaymentadviceUpload(@RequestParam(required = false) MultipartFile[] file) throws IOException, ParseException {
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
                    int rowUpdated = 0;
                    for (int i = 1; i < rowCount + 1; i++) {
                        Row row = sheet.getRow(i);
                        if (row.getCell(0) != null) {
                            String chequenumber = null;
                            String utrnumber = null;
                            java.util.Date utrDate = null;

                            if (row.getCell(0) != null) {
                                row.getCell(0).setCellType(CellType.STRING);
                                chequenumber = row.getCell(0).getStringCellValue().trim();
                            }

                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(CellType.STRING);
                                utrnumber = row.getCell(1).getStringCellValue().trim();
                            }

                            if (row.getCell(2) != null) {
                                utrDate = row.getCell(2).getDateCellValue();
                            }
                            if ((chequenumber != null && chequenumber.length() > 0) && (utrnumber != null && utrnumber.length() > 0) &&
                                utrDate != null) {
                                List<ViewDifindocumentpaymentadvice> viewDifindocumentpaymentadvices = viewDifindocumentpaymentadviceRepository.findAllByChequeNumber("100", chequenumber);
                                if (viewDifindocumentpaymentadvices != null && viewDifindocumentpaymentadvices.size() > 0) {
                                    utrDate = this.addHoursToJavaUtilDate(utrDate, 5);
                                    utrDate = this.addMinutesToJavaUtilDate(utrDate, 30);
                                    for (ViewDifindocumentpaymentadvice viewDifindocumentpaymentadvice : viewDifindocumentpaymentadvices) {
                                        Findocumentline findocumentline = findocumentlineRepository.findById(viewDifindocumentpaymentadvice.getId().getCompanycode(), viewDifindocumentpaymentadvice.getId().getBusinessunitcode(), viewDifindocumentpaymentadvice.getId().getFinancialyearcode(), viewDifindocumentpaymentadvice.getId().getCode(), viewDifindocumentpaymentadvice.getId().getLinenumber());
                                        if (findocumentline != null) {
                                            try {
                                                Adstorage adstorage = adstorageRepository.findById(new AdstorageId(findocumentline.getAbsuniqueid(), "FINDocumentLine", "utrNumber", "utrNumber")).orElse(null);
                                                if (adstorage != null && adstorage.getValuestring() == null) {
                                                    adstorage.setValuestring(utrnumber);
                                                    adstorageRepository.save(adstorage);
                                                } else {
                                                    adstorage = new Adstorage();
                                                    adstorage.setId(new AdstorageId(findocumentline.getAbsuniqueid(), "FINDocumentLine", "utrNumber", "utrNumber"));
                                                    adstorage.setKeysequence(0);
                                                    adstorage.setShared((short) 0);
                                                    adstorage.setDatatype(0);
                                                    adstorage.setValuestring(utrnumber);
                                                    adstorage.setValueint(0);
                                                    adstorage.setValueboolean((short) 0);
                                                    adstorage.setValuedate(null);
                                                    adstorage.setValuedecimal(null);
                                                    adstorage.setValuelong(0L);
                                                    adstorage.setValuetime(null);
                                                    adstorage.setValuetimestamp(null);
                                                    adstorage.setAbsuniqueid(0L);
                                                    adstorageRepository.save(adstorage);
                                                }
                                            } catch (Exception e) {
                                            }

                                            try {
                                                Adstorage adstorage = adstorageRepository.findById(new AdstorageId(findocumentline.getAbsuniqueid(), "FINDocumentLine", "utrDate", "utrDate")).orElse(null);
                                                if (adstorage != null && adstorage.getValuetimestamp() == null) {
                                                    adstorage.setValuetimestamp(new Timestamp(utrDate.getTime()));
                                                    adstorageRepository.save(adstorage);
                                                } else {
                                                    adstorage = new Adstorage();
                                                    adstorage.setId(new AdstorageId(findocumentline.getAbsuniqueid(), "FINDocumentLine", "utrDate", "utrDate"));
                                                    adstorage.setKeysequence(0);
                                                    adstorage.setShared((short) 0);
                                                    adstorage.setDatatype(7);
                                                    adstorage.setValuestring(null);
                                                    adstorage.setValueint(0);
                                                    adstorage.setValueboolean((short) 0);
                                                    adstorage.setValuedate(null);
                                                    adstorage.setValuedecimal(null);
                                                    adstorage.setValuelong(0L);
                                                    adstorage.setValuetime(null);
                                                    adstorage.setValuetimestamp(new Timestamp(utrDate.getTime()));
                                                    adstorage.setAbsuniqueid(0L);
                                                    adstorageRepository.save(adstorage);
                                                }
                                            } catch (Exception e) {
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    errorLog = "No of Rows "+ rowUpdated+" updated.";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(new Message("ERROR", e.getMessage()));
        }
        return ResponseEntity.ok().body(new Message("SUCCESS", errorLog));
    }

    public java.util.Date addHoursToJavaUtilDate(java.util.Date date, int hours) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hours);
        return calendar.getTime();
    }

    public java.util.Date addMinutesToJavaUtilDate(java.util.Date date, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
}

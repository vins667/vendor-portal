package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.PaginationUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.model.*;
import io.vamani.application.db2.repository.*;
import io.vamani.application.model.Master;
import io.vamani.application.model.MasterSearch;
import io.vamani.application.model.Message;
import io.vamani.application.repository.OrderpartnerUploadRepository;
import io.vamani.application.security.SecurityUtils;
import io.vamani.application.util.DateUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

/**
 * REST controller for managing {@link Vieworderpartner}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class VieworderpartnerResource {
    private final Logger log = LoggerFactory.getLogger(VieworderpartnerResource.class);

    private static final String ENTITY_NAME = "vieworderpartner";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VieworderpartnerRepository vieworderpartnerRepository;

    @Autowired
    private DiDirbillorederpartnerRepository diDirbillorederpartnerRepository;

    @Autowired
    private ViewDipurchaseorderdetailsRepository viewDipurchaseorderdetailsRepository;

    @Autowired
    private OrderpartnerbankRepository orderpartnerbankRepository;

    @Autowired
    private OrderpartnerUploadRepository orderpartnerUploadRepository;

    @Autowired
    private ApplicationProperties applicationProperties;
    private final OrderpartnerieRepository orderpartnerieRepository;
    private final AdstorageRepository adstorageRepository;

    public VieworderpartnerResource(VieworderpartnerRepository vieworderpartnerRepository,
                                    OrderpartnerieRepository orderpartnerieRepository,
                                    AdstorageRepository adstorageRepository) {
        this.vieworderpartnerRepository = vieworderpartnerRepository;
        this.orderpartnerieRepository = orderpartnerieRepository;
        this.adstorageRepository = adstorageRepository;
    }

    @GetMapping("/vieworderpartners/{customersuppliertype}/{legalname1}")
    @Timed
    public ResponseEntity<List<DiDirbillorederpartner>> getAllEstprdemployeedetails(@PathVariable String customersuppliertype, @PathVariable String legalname1) {
        log.debug("REST request to get a page of vieworderpartner");
        Pageable pageable = PageRequest.of(0, 50, Sort.by("addressee").ascending());
        Page<DiDirbillorederpartner> page = diDirbillorederpartnerRepository.findAllByLegalname1IgnoreCaseLike(customersuppliertype, "%" + legalname1.toUpperCase() + "%", pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/vieworderpartners-state/{numberId}")
    @Timed
    public ResponseEntity<Master> getAllEstprdemployeedetails(@PathVariable Long numberId) {
        log.debug("REST request to get a page of vieworderpartner");
        String state = vieworderpartnerRepository.getStateCode(numberId);
        Master master = new Master("", state);
        return ResponseEntity.ok().body(master);
    }

    @GetMapping("order-partner-details/{suppliercode}")
    @Timed
    public ResponseEntity<OrderPartnerDetail> getOrderPartnerDetails(@PathVariable String suppliercode) {
        log.debug("REST request to get a page of vieworderpartner");
        OrderPartnerDetail orderPartnerDetail = new OrderPartnerDetail();
        List<Object[]> objects = vieworderpartnerRepository.getOrderPartnerDetails(suppliercode);
        if (objects != null) {
            Object[] object = objects.get(0);
            orderPartnerDetail.setMsmeNo(object[0].toString());
            orderPartnerDetail.setGstr1(object[1].toString());
            orderPartnerDetail.setGstr3B(object[2].toString());
        }
        return ResponseEntity.ok().body(orderPartnerDetail);
    }

    @GetMapping("tds-partner-detail/{customersuppliercode}")
    @Timed
    public ResponseEntity<List<TdsDetailBean>> getTdsDetails(@PathVariable String customersuppliercode) {
        log.debug("REST request to get a page of TdsDetails");
        List<TdsDetailBean> tdsDetailBeans = new ArrayList<>();
        List<Object[]> objects = vieworderpartnerRepository.getTdsDetails(customersuppliercode);
        if (objects != null) {
            for (Object[] tdsDetailBean1 : objects) {
                TdsDetailBean tdsDetailBean = new TdsDetailBean();
                tdsDetailBean.setTdsCode(tdsDetailBean1[0].toString());
                tdsDetailBean.setTdsiTexCode(tdsDetailBean1[1].toString());
                tdsDetailBean.setValue((BigDecimal) tdsDetailBean1[2]);
                tdsDetailBeans.add(tdsDetailBean);
            }
        }
        return ResponseEntity.ok().body(tdsDetailBeans);
    }

    @GetMapping("purchase-order-detail/{purchaseorder}")
    @Timed
    public ResponseEntity<PoDetailBean> getPurchaseOrderDetails(@PathVariable String purchaseorder) {
        log.debug("REST request to get a page of vieworderpartner");
        PoDetailBean poDetailBean = new PoDetailBean();
        ViewDipurchaseorderdetails viewDipurchaseorderdetails = viewDipurchaseorderdetailsRepository.findByCode(purchaseorder);
        if (viewDipurchaseorderdetails != null) {
            poDetailBean.setPaymentMethodcode(viewDipurchaseorderdetails.getPaymentmethodcode());
            poDetailBean.setPaymentMethodDescription(viewDipurchaseorderdetails.getPaymentmethoddescription());
            poDetailBean.setPoBasicValue(viewDipurchaseorderdetails.getPobasicvalue());
            poDetailBean.setPoGstValue(viewDipurchaseorderdetails.getPogstvalue());
        }
        return ResponseEntity.ok().body(poDetailBean);
    }

    @GetMapping("db2-fetch-exchange-rate/{currencycode}")
    @Timed
    public ResponseEntity<Currencydailyexchangerate> fetchExchangeRate(@PathVariable String currencycode) {
        log.debug("REST request to get a page of vieworderpartner");
        Currencydailyexchangerate currencydailyexchangerate = new Currencydailyexchangerate();
        BigDecimal purchaseexchangerate = vieworderpartnerRepository.fetchExchangeRate(currencycode);
        if (purchaseexchangerate != null) {
            currencydailyexchangerate.setPurchaseexchangerate(purchaseexchangerate);
        }
        return ResponseEntity.ok().body(currencydailyexchangerate);
    }

    @PostMapping("/vieworderpartners-filter")
    @Timed
    public ResponseEntity<List<ViewOrderPartnerBean>> getVieworderpartnerfilter(@RequestBody MasterSearch search) {
        log.debug("REST request to get a page of vieworderpartner");
        search.setPage(PageRequest.of(search.getPageNo(), search.getSize(), Sort.by("customersuppliercode")));
        String code = "%";
        if (search.getCode() != null) {
            code = "%" + search.getCode().toUpperCase() + "%";
        }
        String name = "%";
        if (search.getDescription() != null) {
            name = "%" + search.getDescription().toUpperCase() + "%";
        }
        Page<Object[]> page = vieworderpartnerRepository.findAllByOrderPartnerLikeAndLegalname1Like(search.getCompanyCode(), code, name, search.getPage());
        List<ViewOrderPartnerBean> viewOrderPartnerBeans = new ArrayList<>();
        for(Object[] object : page.getContent()) {
            ViewOrderPartnerBean viewOrderPartnerBean = new ViewOrderPartnerBean();
            viewOrderPartnerBean.setCustomersuppliercompanycode(object[0].toString());
            viewOrderPartnerBean.setCustomersuppliertype(object[1].toString());
            viewOrderPartnerBean.setCustomersuppliercode(object[2].toString());
            viewOrderPartnerBean.setLegalname1(object[3].toString());
            viewOrderPartnerBean.setCommissionerate(object[4].toString());
            viewOrderPartnerBean.setEccno(object[5].toString());
            viewOrderPartnerBean.setGstinnumber(object[6].toString());
            viewOrderPartnerBeans.add(viewOrderPartnerBean);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(viewOrderPartnerBeans);
    }

    @GetMapping("/vieworderpartners-document/{customersuppliertype}/{customersuppliercode}")
    @Timed
    public ResponseEntity<ViewOrderPartnerBean> getVieworderpartner(@PathVariable String customersuppliertype, @PathVariable String customersuppliercode) {
        log.debug("REST request to get a page of vieworderpartner");
        List<Object[]> objects = vieworderpartnerRepository.findAllByOrderPartner("100", customersuppliertype, customersuppliercode);
        ViewOrderPartnerBean viewOrderPartnerBean = new ViewOrderPartnerBean();
        for (Object[] object : objects) {
            viewOrderPartnerBean.setCustomersuppliercompanycode(object[0].toString());
            viewOrderPartnerBean.setCustomersuppliertype(object[1].toString());
            viewOrderPartnerBean.setCustomersuppliercode(object[2].toString());
            viewOrderPartnerBean.setLegalname1(object[3].toString());
            viewOrderPartnerBean.setCommissionerate(object[4].toString());
            viewOrderPartnerBean.setEccno(object[5].toString());
            if (object[5].toString() != null && object[5].toString().trim().length() > 0) {
                viewOrderPartnerBean.setEccnoAllow(false);
            } else {
                viewOrderPartnerBean.setEccnoAllow(true);
            }
            viewOrderPartnerBean.setGstinnumber(object[6].toString());
            viewOrderPartnerBean.setGlcode(object[7].toString());
            viewOrderPartnerBean.setGlname(object[8].toString());
            viewOrderPartnerBean.setGst3b(object[9].toString());
            viewOrderPartnerBean.setGst1(object[10].toString());
            viewOrderPartnerBean.setGst2a(object[11].toString());
            viewOrderPartnerBean.setGst2aRemark(object[12].toString());
            viewOrderPartnerBean.setEmail(object[13].toString());
            if (object[13].toString() != null && object[13].toString().trim().length() > 0) {
                viewOrderPartnerBean.setEmailAllow(false);
            } else {
                viewOrderPartnerBean.setEmailAllow(true);
            }
            viewOrderPartnerBean.setPhone(object[14].toString());
            if (object[14].toString() != null && object[14].toString().trim().length() > 0) {
                viewOrderPartnerBean.setPhoneAllow(false);
            } else {
                viewOrderPartnerBean.setPhoneAllow(true);
            }
            viewOrderPartnerBean.setOrderpartnerbanks(orderpartnerbankRepository.findAllBySuppliercode(object[0].toString(), object[1].toString(), object[2].toString()));
            viewOrderPartnerBean.setOrderpartnerUploads(orderpartnerUploadRepository.findAllByCustomersuppliercode(object[0].toString(), object[1].toString(), object[2].toString()));
        }
        return ResponseEntity.ok().body(viewOrderPartnerBean);
    }
    @PostMapping("/vieworderpartners-document-save")
    @Timed
    public ResponseEntity<ViewOrderPartnerBean> getVieworderpartner(@RequestBody ViewOrderPartnerBean viewOrderPartnerBean) {
        Vieworderpartner vieworderpartner = vieworderpartnerRepository.findByCustomersuppliercode(viewOrderPartnerBean.getCustomersuppliercompanycode(), viewOrderPartnerBean.getCustomersuppliertype(), viewOrderPartnerBean.getCustomersuppliercode());
        if(vieworderpartner != null) {
            if (viewOrderPartnerBean.getEccnoAllow() != null && viewOrderPartnerBean.getEccnoAllow().booleanValue() == true && viewOrderPartnerBean.getEccno() != null && viewOrderPartnerBean.getEccno().length() > 0) {
                orderpartnerieRepository.updateMsmeNumber(viewOrderPartnerBean.getEccno(), viewOrderPartnerBean.getCustomersuppliercompanycode(), viewOrderPartnerBean.getCustomersuppliertype(), viewOrderPartnerBean.getCustomersuppliercode());
            }
            if (viewOrderPartnerBean.getEmailAllow() != null && viewOrderPartnerBean.getEmailAllow().booleanValue() == true && viewOrderPartnerBean.getEmail() != null && viewOrderPartnerBean.getEmail().length() > 0) {
                orderpartnerieRepository.updateEmail(viewOrderPartnerBean.getEmail(), vieworderpartner.getNumberid());
            }
            if (viewOrderPartnerBean.getPhoneAllow() != null && viewOrderPartnerBean.getPhoneAllow().booleanValue() == true && viewOrderPartnerBean.getPhone() != null && viewOrderPartnerBean.getPhone().length() > 0) {
                orderpartnerieRepository.updatePhone(viewOrderPartnerBean.getPhone(), vieworderpartner.getNumberid());
            }
        }
        return this.getVieworderpartner(viewOrderPartnerBean.getCustomersuppliertype(), viewOrderPartnerBean.getCustomersuppliercode());
    }

    @GetMapping("/vieworderpartners-exports/{customersuppliertype}")
    @Timed
    public @ResponseBody void getVieworderpartnerExports(@PathVariable String customersuppliertype, HttpServletResponse response) throws JRException, ParseException, IOException {
        log.debug("REST request to get a page of vieworderpartner");

        List<Object[]> page = vieworderpartnerRepository.findAllByCustomersuppliertype(customersuppliertype);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-yyyy");
        List<ViewOrderPartnerReportBean> viewOrderPartnerBeans = new ArrayList<>();
        for(Object[] object : page) {
            ViewOrderPartnerReportBean viewOrderPartnerBean = new ViewOrderPartnerReportBean();
            viewOrderPartnerBean.setCustomersuppliercompanycode(object[0].toString());
            viewOrderPartnerBean.setCustomersuppliertype(object[1].toString());
            viewOrderPartnerBean.setCustomersuppliercode(object[2].toString());
            viewOrderPartnerBean.setLegalname1(object[3].toString());
            viewOrderPartnerBean.setCommissionerate(object[4].toString());
            viewOrderPartnerBean.setEccno(object[5].toString());
            viewOrderPartnerBean.setGstinnumber(object[6].toString());
            viewOrderPartnerBean.setGlcode(object[7].toString());
            viewOrderPartnerBean.setGlname(object[8].toString());
            if (object[9].toString() != null && object[9].toString().length() > 0) {
                try {
                    viewOrderPartnerBean.setGst3b(simpleDateFormat.parse(object[9].toString()));
                } catch (Exception e) {
                }
            }
            if (object[10].toString() != null && object[10].toString().length() > 0) {
                try {
                    viewOrderPartnerBean.setGst1(simpleDateFormat.parse(object[10].toString()));
                } catch (Exception e) {
                }
            }
            viewOrderPartnerBean.setGst2a(object[11].toString());
            viewOrderPartnerBean.setGst2aRemark(object[12].toString());
            viewOrderPartnerBean.setEmail(object[13].toString());
            viewOrderPartnerBean.setPhone(object[14].toString());
            viewOrderPartnerBeans.add(viewOrderPartnerBean);
        }

        String path = applicationProperties.getTemplatePath() + "jasper/";
        JasperDesign jasperDesign = JRXmlLoader.load(path + "/vieworderpartner.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        Map<String, Object> parameters = new HashMap<String, Object>();
        JRDataSource jrDataSource = new JRBeanCollectionDataSource(viewOrderPartnerBeans);

        parameters.put("datasource", jrDataSource);
        parameters.put("SUBREPORT_DIR", path);
        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=vieworderpartner.xlsx");

        final OutputStream outputStream = response.getOutputStream();
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.exportReport();
    }

    @PostMapping(value = "/vieworderpartners-excel", consumes = {"multipart/form-data"})
    @Timed
    public ResponseEntity<Message> createAssetFileUploadMaster(@RequestParam(required = false) MultipartFile[] file) throws IOException, ParseException {
        String errorLog = "";
        log.debug("REST request to save BankRealisationCertificateExcelUpload : {}");
        String UPLOADED_FOLDER = applicationProperties.getUploadPath();
        List<ViewOrderPartnerReportBean> viewOrderPartnerReportBeans = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM-yyyy");
        try {
            if (file != null && file.length > 0) {
                for (MultipartFile multipartFile : file) {
                    byte[] bytes = multipartFile.getBytes();
                    Path path = Paths.get(UPLOADED_FOLDER + "styles/temp/" + multipartFile.getOriginalFilename());
                    Files.write(path, bytes);
                    String fileName = multipartFile.getOriginalFilename();
                    File readFile = new File(UPLOADED_FOLDER + "styles/temp/" + fileName);
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
                    for (int i = 1; i <= rowCount; i++) {
                        Row row = sheet.getRow(i);
                        row.getCell(0).setCellType(CellType.STRING);
                        if (row.getCell(0).getStringCellValue() != null && row.getCell(0).getStringCellValue().length() > 0) {
                            ViewOrderPartnerReportBean viewOrderPartnerReportBean = new ViewOrderPartnerReportBean();
                            if (row.getCell(0) != null) {
                                viewOrderPartnerReportBean.setCustomersuppliercompanycode(row.getCell(0).getStringCellValue().trim());
                            }

                            if (row.getCell(1) != null) {
                                row.getCell(1).setCellType(CellType.STRING);
                                viewOrderPartnerReportBean.setCustomersuppliertype(row.getCell(1).getStringCellValue().trim());
                            }

                            if (row.getCell(2) != null) {
                                row.getCell(2).setCellType(CellType.STRING);
                                viewOrderPartnerReportBean.setGlcode(row.getCell(2).getStringCellValue().trim());
                            }

                            if (row.getCell(3) != null) {
                                row.getCell(3).setCellType(CellType.STRING);
                                viewOrderPartnerReportBean.setGlname(row.getCell(3).getStringCellValue().trim());
                            }

                            if (row.getCell(4) != null) {
                                row.getCell(4).setCellType(CellType.STRING);
                                viewOrderPartnerReportBean.setCustomersuppliercode(row.getCell(4).getStringCellValue().trim());
                            }

                            if (row.getCell(5) != null) {
                                row.getCell(5).setCellType(CellType.STRING);
                                viewOrderPartnerReportBean.setLegalname1(row.getCell(5).getStringCellValue().trim());
                            }

                            Vieworderpartner vieworderpartner = vieworderpartnerRepository.findByCustomersuppliercode(viewOrderPartnerReportBean.getCustomersuppliercompanycode(), viewOrderPartnerReportBean.getCustomersuppliertype(), viewOrderPartnerReportBean.getCustomersuppliercode());
                            if (vieworderpartner != null) {
                                //Email Update
                                if (row.getCell(6) != null) {
                                    row.getCell(6).setCellType(CellType.STRING);
                                    viewOrderPartnerReportBean.setEmail(row.getCell(6).getStringCellValue().trim());
                                }

                                if (viewOrderPartnerReportBean.getEmail() != null && viewOrderPartnerReportBean.getEmail().length() > 0) {
                                    try {
                                        orderpartnerieRepository.updateEmail(viewOrderPartnerReportBean.getEmail(), vieworderpartner.getNumberid());
                                    } catch (Exception e) {
                                    }
                                }

                                if (row.getCell(7) != null) {
                                    row.getCell(7).setCellType(CellType.STRING);
                                    viewOrderPartnerReportBean.setPhone(row.getCell(7).getStringCellValue().trim());
                                }

                                if (viewOrderPartnerReportBean.getPhone() != null && viewOrderPartnerReportBean.getPhone().length() > 0) {
                                    try {
                                        orderpartnerieRepository.updatePhone(viewOrderPartnerReportBean.getPhone(), vieworderpartner.getNumberid());
                                    } catch (Exception e) {
                                    }
                                }

                                if (row.getCell(8) != null) {
                                    row.getCell(8).setCellType(CellType.STRING);
                                    viewOrderPartnerReportBean.setCommissionerate(row.getCell(8).getStringCellValue().trim());
                                }

                                if (row.getCell(9) != null) {
                                    row.getCell(9).setCellType(CellType.STRING);
                                    viewOrderPartnerReportBean.setEccno(row.getCell(9).getStringCellValue().trim());
                                }

                                if (viewOrderPartnerReportBean.getEccno() != null && viewOrderPartnerReportBean.getEccno().length() > 0) {
                                    try {
                                        orderpartnerieRepository.updateMsmeNumber(viewOrderPartnerReportBean.getEccno(), viewOrderPartnerReportBean.getCustomersuppliercompanycode(), viewOrderPartnerReportBean.getCustomersuppliertype(), viewOrderPartnerReportBean.getCustomersuppliercode());
                                    } catch (Exception e) {
                                    }
                                }

                                if (row.getCell(10) != null) {
                                    row.getCell(10).setCellType(CellType.STRING);
                                    viewOrderPartnerReportBean.setGstinnumber(row.getCell(10).getStringCellValue().trim());
                                }

                                if (row.getCell(11) != null) {
                                    try {
                                        try {
                                            viewOrderPartnerReportBean.setGst3b(row.getCell(11).getDateCellValue());
                                        } catch(Exception e) {
                                            row.getCell(11).setCellType(CellType.STRING);
                                            viewOrderPartnerReportBean.setGst3b(simpleDateFormat.parse(row.getCell(11).getStringCellValue().trim()));
                                        }
                                        Adstorage adstorage = adstorageRepository.findById(new AdstorageId(vieworderpartner.getAbsuniqueid(), "OrderPartner", "gstr3BFiledMonth", "gstr3BFiledMonth")).orElse(null);
                                        if(adstorage != null) {
                                            adstorage.setValuestring(simpleDateFormat.format(viewOrderPartnerReportBean.getGst3b()));
                                            adstorageRepository.save(adstorage);
                                        } else {
                                            adstorage = new Adstorage();
                                            adstorage.setId(new AdstorageId(vieworderpartner.getAbsuniqueid(), "OrderPartner", "gstr3BFiledMonth", "gstr3BFiledMonth"));
                                            adstorage.setKeysequence(0);
                                            adstorage.setShared((short) 0);
                                            adstorage.setDatatype(0);
                                            adstorage.setValuestring(simpleDateFormat.format(viewOrderPartnerReportBean.getGst3b()));
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
                                }

                                if (row.getCell(12) != null) {
                                    try {
                                        try {
                                            viewOrderPartnerReportBean.setGst1(row.getCell(12).getDateCellValue());
                                        } catch(Exception e) {
                                            row.getCell(12).setCellType(CellType.STRING);
                                            viewOrderPartnerReportBean.setGst1(simpleDateFormat.parse(row.getCell(12).getStringCellValue().trim()));
                                        }
                                        Adstorage adstorage = adstorageRepository.findById(new AdstorageId(vieworderpartner.getAbsuniqueid(), "OrderPartner", "gstrR1FiledMonth", "gstrR1FiledMonth")).orElse(null);
                                        if(adstorage != null) {
                                            adstorage.setValuestring(simpleDateFormat.format(viewOrderPartnerReportBean.getGst1()));
                                            adstorageRepository.save(adstorage);
                                        } else {
                                            adstorage = new Adstorage();
                                            adstorage.setId(new AdstorageId(vieworderpartner.getAbsuniqueid(), "OrderPartner", "gstrR1FiledMonth", "gstrR1FiledMonth"));
                                            adstorage.setKeysequence(0);
                                            adstorage.setShared((short) 0);
                                            adstorage.setDatatype(0);
                                            adstorage.setValuestring(simpleDateFormat.format(viewOrderPartnerReportBean.getGst1()));
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
                                }

                                if (row.getCell(13) != null) {
                                    try {
                                        row.getCell(13).setCellType(CellType.STRING);
                                        viewOrderPartnerReportBean.setGst2a(row.getCell(13).getStringCellValue().trim());
                                        Adstorage adstorage = adstorageRepository.findById(new AdstorageId(vieworderpartner.getAbsuniqueid(), "OrderPartner", "gstr2AMonth", "gstr2AMonth")).orElse(null);
                                        if(adstorage != null) {
                                            adstorage.setValuestring(viewOrderPartnerReportBean.getGst2a());
                                            adstorageRepository.save(adstorage);
                                        } else {
                                            adstorage = new Adstorage();
                                            adstorage.setId(new AdstorageId(vieworderpartner.getAbsuniqueid(), "OrderPartner", "gstr2AMonth", "gstr2AMonth"));
                                            adstorage.setKeysequence(0);
                                            adstorage.setShared((short) 0);
                                            adstorage.setDatatype(0);
                                            adstorage.setValuestring(viewOrderPartnerReportBean.getGst2a());
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
                                }

                                if (row.getCell(14) != null) {
                                    try {
                                        row.getCell(14).setCellType(CellType.STRING);
                                        viewOrderPartnerReportBean.setGst2aRemark(row.getCell(14).getStringCellValue().trim());
                                        Adstorage adstorage = adstorageRepository.findById(new AdstorageId(vieworderpartner.getAbsuniqueid(), "OrderPartner", "gstr2ARemark", "gstr2ARemark")).orElse(null);
                                        if(adstorage != null) {
                                            adstorage.setValuestring(viewOrderPartnerReportBean.getGst2aRemark());
                                            adstorageRepository.save(adstorage);
                                        } else {
                                            adstorage = new Adstorage();
                                            adstorage.setId(new AdstorageId(vieworderpartner.getAbsuniqueid(), "OrderPartner", "gstr2ARemark", "gstr2ARemark"));
                                            adstorage.setKeysequence(0);
                                            adstorage.setShared((short) 0);
                                            adstorage.setDatatype(0);
                                            adstorage.setValuestring(viewOrderPartnerReportBean.getGst2aRemark());
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
                                }
                                viewOrderPartnerReportBeans.add(viewOrderPartnerReportBean);
                            }
                        }
                    }
                    inputStream.close();
                    workbook.close();
                }
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Message("ERROR", e.getMessage()));
        }
        return ResponseEntity.ok().body(new Message("SUCCESS", errorLog));

    }
}

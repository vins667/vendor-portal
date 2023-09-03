package io.vamani.application.db2.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.web.util.ResponseUtil;
import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.config.Constants;
import io.vamani.application.db2.domain.*;
import io.vamani.application.db2.model.BankReconciliationBean;
import io.vamani.application.db2.model.BankReconciliationdetailBean;
import io.vamani.application.db2.model.MasterParameters;
import io.vamani.application.db2.repository.AbsuserdefRepository;
import io.vamani.application.db2.repository.BankReconciliationDetailRepository;
import io.vamani.application.db2.repository.BankReconciliationRepository;
import io.vamani.application.db2.repository.FindocumentRepository;
import io.vamani.application.model.ConveyanceReportBean;
import io.vamani.application.mssql.domain.EmployeeMatView;
import io.vamani.application.security.SecurityUtils;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
@Transactional
public class BankReconciliationResource {

    private final Logger log = LoggerFactory.getLogger(BankReconciliationResource.class);

    private static final String ENTITY_NAME = "bankreconcilation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    @Autowired
    private BankReconciliationRepository bankReconciliationRepository;

    @Autowired
    private BankReconciliationDetailRepository bankReconciliationDetailRepository;

    @Autowired
    private AbsuserdefRepository absuserdefRepository;

    @Autowired
    private FindocumentRepository findocumentRepository;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Autowired
    private Environment env;

    public BankReconciliationResource(BankReconciliationRepository bankReconciliationRepository) {
        this.bankReconciliationRepository = bankReconciliationRepository;
    }

    @PostMapping("/bank-reconcilation-list")
    @Timed
    public List<BankReconciliationdetailBean> getAllView(@RequestBody MasterParameters masterParameters) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of bankreconcilation");
        String fromDate = "";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        if (masterParameters.getParastring02() != null && masterParameters.getParastring02().length() > 0) {
            LocalDate date1 = LocalDate.parse(masterParameters.getParastring02(), inputFormatter);
            fromDate = outputFormatter.format(date1.plusDays(1));
        }

        List<BankReconciliationdetailBean> bankReconciliationdetails = new ArrayList<>();
        List<Object[]> list = null;
        if (masterParameters.getParastring03() != null && masterParameters.getParastring03().equalsIgnoreCase("4")) {
            list = bankReconciliationRepository.findAllBankReconcilations4(masterParameters.getParastring01(), fromDate);
        } else if (masterParameters.getParastring03() != null && masterParameters.getParastring03().equalsIgnoreCase("5")) {
            list = bankReconciliationRepository.findAllBankReconcilations5(masterParameters.getParastring01(), fromDate);
        } else if (masterParameters.getParastring03() != null && masterParameters.getParastring03().equalsIgnoreCase("6")) {
            list = bankReconciliationRepository.findAllBankReconcilations6(masterParameters.getParastring01(), fromDate);
        } else if (masterParameters.getParastring03() != null && masterParameters.getParastring03().equalsIgnoreCase("7")) {
            list = bankReconciliationRepository.findAllBankReconcilations7(masterParameters.getParastring01(), fromDate);
        } else if (masterParameters.getParastring03() != null && masterParameters.getParastring03().equalsIgnoreCase("8")) {
            list = bankReconciliationRepository.findAllBankReconcilations8(masterParameters.getParastring01(), fromDate);
        } else {
            list = bankReconciliationRepository.findAllBankReconcilations4(masterParameters.getParastring01(), fromDate);
        }
        int iCOunt = 0;
        if (list != null && list.size() > 0) {
            for (Object[] obj : list) {
                BankReconciliationdetailBean bean = new BankReconciliationdetailBean();
                bean.setRowcount(obj[0].toString());
                bean.setCompanycode(obj[1].toString());
                bean.setBusinessunitcode(obj[2].toString());
                bean.setCode(obj[3].toString());

                String glcode = obj[4] != null ? obj[4].toString() : null;
                String glname = obj[5] != null ? obj[5].toString() : null;
                String suppcode = obj[6] != null ? obj[6].toString() : null;
                String suppname = obj[7] != null ? obj[7].toString() : null;
                Integer suppcounts = (Integer) obj[8];
                if (suppcounts > 1) {
                    bean.setGlcode("BULK");
                    bean.setGlname("BULK");
                } else if (suppcode != null && suppcode.length() > 0) {
                    bean.setGlcode(suppcode);
                    bean.setGlname(suppname);
                } else {
                    bean.setGlcode(glcode);
                    bean.setGlname(glname);
                }
                bean.setChequeno(obj[9].toString());
                bean.setChequedate(obj[10] != null ? new Date(((java.sql.Date) obj[10]).getTime()).toInstant() : null);
                bean.setDebitamit((BigDecimal) obj[11]);
                bean.setCreditamt((BigDecimal) obj[12]);
                bean.setReconciliationdate(obj[13] != null ? new Date(((java.sql.Date) obj[13]).getTime()).toInstant() : null);
                bean.setProfitcentercode(obj[14] != null ? obj[14].toString() : null);
                bean.setNarration(obj[15].toString());
                bean.setLinenumber(((BigDecimal) obj[16]).intValue());
                bean.setFinyearcode(obj[17].toString());
                bean.setDocumentype(obj[18].toString());
                bean.setDocumentdate(obj[19] != null ? new Date(((java.sql.Date) obj[19]).getTime()).toInstant() : null);
                bankReconciliationdetails.add(bean);
            }
        }
        return bankReconciliationdetails;
    }

    /* @PostMapping("/bank-reconcilation-search")
    @Timed
    public List<BankReconciliationdetailBean> getreconsearch(@RequestBody MasterParameters masterParameters) throws UnsupportedEncodingException {
        log.debug("REST request to get a page of bankreconcilation");
        String fromDate = "";
        String unit = "%";
        String findocument = "%";
        String glcode = "%";
        String glname = "%";
        String chekno = "%";
        String chkdate = "%";
        String profitcenter = "%";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        if (masterParameters.getParastring10() != null && masterParameters.getParastring10().length() > 0) {
            LocalDate date1 = LocalDate.parse(masterParameters.getParastring10(), inputFormatter);
            fromDate = outputFormatter.format(date1.plusDays(1));
        }
        if(masterParameters.getParastring01() != null && masterParameters.getParastring01().length()>0){
            unit = "%" +masterParameters.getParastring01().trim().toUpperCase()+"%";
        }
        if(masterParameters.getParastring02() != null && masterParameters.getParastring02().length()>0){
            findocument = "%" + masterParameters.getParastring02().trim().toUpperCase() +"%";
        }
        if(masterParameters.getParastring03() != null && masterParameters.getParastring03().length()>0){
            glcode = "%" + masterParameters.getParastring03().trim().toUpperCase()+"%";
        }
        if(masterParameters.getParastring04() != null && masterParameters.getParastring04().length()>0){
            glname = "%" + masterParameters.getParastring04().trim().toUpperCase() +"%";
        }
        if(masterParameters.getParastring05() != null && masterParameters.getParastring02().length()>0){
            chekno = "%" + masterParameters.getParastring05().trim().toUpperCase() +"%";
        }
        if(masterParameters.getParastring06() != null && masterParameters.getParastring06().length()>0){
            LocalDate date1 = LocalDate.parse(masterParameters.getParastring06(), inputFormatter);
            chkdate ="%" + outputFormatter.format(date1.plusDays(1)) + "%";
        }
        if(masterParameters.getParastring08() != null && masterParameters.getParastring08().length()>0){
            profitcenter = "%" + masterParameters.getParastring08().trim().toUpperCase() +"%";
        }

        List<BankReconciliationdetailBean> bankReconciliationdetails = new ArrayList<>();
        List<Object[]> list = bankReconciliationRepository.findAllBankReconcilationsearch(masterParameters.getParastring09(), fromDate, unit, findocument, glcode, glname, chekno, chkdate, profitcenter);
        int iCOunt = 0;
        if(list != null && list.size()>0){
            for(Object[] obj: list){
                String glcode2 = obj[6].toString();
                String glname2 = obj[7].toString();
                String suppcode = "";
                String suppname = "";
                if(obj[8] != null && obj[8] != ""){
                    suppcode = obj[8].toString();
                }
                if(obj[9] != null && obj[9] != ""){
                    suppname = obj[9].toString();
                }
                int suppcounts = (int) obj[10];
                System.out.println(iCOunt++);
                BankReconciliationdetailBean bean = new BankReconciliationdetailBean();
                bean.setRowcount(obj[0].toString());
                bean.setCompanycode(obj[1].toString());
                bean.setBusinessunitcode(obj[2].toString());
                bean.setCode(obj[3].toString());
                if(suppcounts>1){
                    bean.setGlcode("BULK");
                    bean.setGlname("BULK");
                }else if(suppcode != null && suppcode.length()>0){
                    bean.setGlcode(suppcode);
                    bean.setGlname(suppname);
                }else{
                    bean.setGlcode(glcode2);
                    bean.setGlname(glname2);
                }
                bean.setChequeno(obj[11].toString());
                if(obj[12] != null){
                    bean.setChequedate(obj[12].toString());
                }
                if(obj[13] != null){
                    bean.setChequeclearingdate(obj[13].toString());
                }
                bean.setChequeamount((BigDecimal) obj[14]);
                bean.setDebitamit((BigDecimal) obj[15]);
                bean.setCreditamt((BigDecimal) obj[16]);
                bean.setDocumentyype(obj[17].toString());
                bean.setProfitcentercode(obj[18].toString());
                bean.setCheckdepositnotclear((BigDecimal) obj[19]);
                bean.setCheckissuenotclear((BigDecimal) obj[20]);
                if(obj[21] != null) {
                    bean.setNarration(obj[21].toString());
                }
                if(obj[22] != null) {
                    bean.setReconciliationdate(obj[22].toString());
                }
                bankReconciliationdetails.add(bean);
            }
        }
        return bankReconciliationdetails;
    } */

    @PostMapping("/bank-reconcilation-save")
    @Timed
    public ResponseEntity<BankReconciliation> createbankReconEntry(@Valid @RequestBody BankReconciliationBean bankReconciliationbean) {
        log.debug("REST request to save AssetMaster : {}", bankReconciliationbean);
        String USER_DETAILS = "system";
        List<Absuserdef> absuserdefs = absuserdefRepository.findAllBySendersmtpid(SecurityUtils.getCurrentUserLogin().orElse(null));
        if (absuserdefs != null && absuserdefs.size() > 0) {
            USER_DETAILS = absuserdefs.get(0).getUserid();
        }
        BankReconciliation res = new BankReconciliation();
        if (bankReconciliationbean != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BankReconciliation bankReconciliation = new BankReconciliation();
            BeanUtils.copyProperties(bankReconciliationbean, bankReconciliation);
            bankReconciliation.setEntrydate(simpleDateFormat2.format(Date.from(Instant.now())));
            bankReconciliation.setEntryby(USER_DETAILS);
            res = bankReconciliationRepository.save(bankReconciliation);
            if (res != null && bankReconciliationbean.getBankReconciliationdetails() != null) {
                for (BankReconciliationdetailBean bankReconciliationdetailBean : bankReconciliationbean.getBankReconciliationdetails()) {
                    BankReconciliationdetail bankReconciliationdetail = new BankReconciliationdetail();
                    BeanUtils.copyProperties(bankReconciliationdetailBean, bankReconciliationdetail);
                    if (bankReconciliationdetailBean.getReconciliationdate() != null) {
                        bankReconciliationdetail.setReconciliationdate(simpleDateFormat.format(Date.from(bankReconciliationdetailBean.getReconciliationdate())));
                    }
                    if (bankReconciliationdetailBean.getChequedate() != null) {

                        bankReconciliationdetail.setChequedate(simpleDateFormat.format(Date.from(bankReconciliationdetailBean.getChequedate())));
                    }
                    bankReconciliationdetail.setBankReconciliation(res);
                    bankReconciliationDetailRepository.save(bankReconciliationdetail);
                }
            }
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(res));
    }

    @PostMapping("/bank-reconcilation-post")
    @Timed
    public ResponseEntity<List<BankReconciliationdetailBean>> postbankReconEntry(@Valid @RequestBody List<BankReconciliationdetailBean> bankReconciliationdetailBeans) {
        log.debug("REST request to save AssetMaster : {}", bankReconciliationdetailBeans);
        String USER_DETAILS = "system";
        List<Absuserdef> absuserdefs = absuserdefRepository.findAllBySendersmtpid(SecurityUtils.getCurrentUserLogin().orElse(null));
        if (absuserdefs != null && absuserdefs.size() > 0) {
            USER_DETAILS = absuserdefs.get(0).getUserid();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (BankReconciliationdetailBean bankReconciliationdetailBean : bankReconciliationdetailBeans) {
            Date reconcileDate = Date.from(Instant.now().atZone(ZoneId.of("Asia/Kolkata")).toInstant());
            findocumentRepository.updateReconcile(simpleDateFormat.format(reconcileDate), USER_DETAILS, simpleDateFormat2.format(reconcileDate), Constants.COMPANY_CODE, bankReconciliationdetailBean.getBusinessunitcode().trim(), bankReconciliationdetailBean.getFinyearcode().trim(), bankReconciliationdetailBean.getCode().trim(), bankReconciliationdetailBean.getLinenumber());
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(bankReconciliationdetailBeans));
    }

    @GetMapping("/bank-reconcilation-report/{id}")
    @Timed
    public @ResponseBody
    void getBankReconcilationReport(@Valid @PathVariable Long id, HttpServletResponse response) throws JRException, IOException, ParseException {

        Connection conn = null;
        try {
            conn = db2DataSource().getConnection();
            String path = applicationProperties.getTemplatePath() + "jasper/";
            JasperDesign jasperDesign = JRXmlLoader.load(path + "/BankReconciliationRpt.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            Map<String, Object> parameters = new HashMap<String, Object>();

            parameters.put("documentid", id);
            parameters.put("SUBREPORT_DIR", path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            response.setContentType("application/x-pdf");
            response.setHeader("Content-Disposition", "attachment; filename=bundle_piece_print.pdf");

            final OutputStream outputStream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    System.out.println("error");
                }
            }
            conn = null;
        }
    }

    public DataSource db2DataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("db2.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("db2.datasource.url"));
        dataSource.setUsername(env.getProperty("db2.datasource.username"));
        dataSource.setPassword(env.getProperty("db2.datasource.password"));
        return dataSource;
    }
}

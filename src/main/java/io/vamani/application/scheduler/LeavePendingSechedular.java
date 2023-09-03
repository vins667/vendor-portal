package io.vamani.application.scheduler;

import io.vamani.application.config.ApplicationProperties;
import io.vamani.application.domain.LeaveMaster;
import io.vamani.application.model.LeavePendingReportBean;
import io.vamani.application.mssql.domain.EmployeeMatView;
import io.vamani.application.mssql.domain.EmployeeView;
import io.vamani.application.mssql.repository.EmployeeMatViewRepository;
import io.vamani.application.mssql.repository.EmployeeViewRepository;
import io.vamani.application.repository.LeaveMasterRepository;
import io.vamani.application.service.MailService;
import io.vamani.application.web.rest.LeaveMasterResource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@EnableScheduling
public class LeavePendingSechedular {
    @Autowired
    private EmployeeMatViewRepository employeeViewRepository;
    @Autowired
    private LeaveMasterRepository leaveMasterRepository;
    @Autowired
    private ApplicationProperties applicationProperties;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 0 14 28-31 * ?")
    public void pushLeaveData() throws JRException{
        Calendar c = Calendar.getInstance();
        if (c.get(5) == c.getActualMaximum(5)) {
            List<LeaveMaster> leaveMasters = this.leaveMasterRepository.findAllByLeaveStatusHOD("E");
            Map<String, List<LeaveMaster>> leaveMap = new HashMap<String, List<LeaveMaster>>();
            for (LeaveMaster leaveMaster : leaveMasters) {
                if (leaveMap.containsKey(leaveMaster.getHodApprovedBy())) {
                    List<LeaveMaster> masters = (List) leaveMap.get(leaveMaster.getHodApprovedBy());
                    masters.add(leaveMaster);
                    leaveMap.put(leaveMaster.getHodApprovedBy(), masters);
                    continue;
                }
                List<LeaveMaster> masters = new ArrayList<LeaveMaster>();
                masters.add(leaveMaster);
                leaveMap.put(leaveMaster.getHodApprovedBy(), masters);
            }

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
            for (String key : leaveMap.keySet()) {
                EmployeeMatView employeeView = (EmployeeMatView) this.employeeViewRepository.findByCardNo(key).orElse(null);
                if (employeeView != null && employeeView.getEmail() != null && employeeView.getEmail().length() > 0 && LeaveMasterResource.VALID_EMAIL_ADDRESS_REGEX.matcher(employeeView.getEmail()).find() == true) {
                    List<LeaveMaster> masters = (List) leaveMap.get(key);
                    Collections.sort(masters, Comparator.comparing(LeaveMaster::getLeaveDateFrom));
                    List<LeavePendingReportBean> leavePendingReportBean = new ArrayList<LeavePendingReportBean>();
                    if (masters.size() > 0) {
                        for (LeaveMaster leaveMaster : masters) {
                            LeavePendingReportBean bean = new LeavePendingReportBean();
                            bean.setHodCardNo(leaveMaster.getHodApprovedBy());
                            bean.setHodName(employeeView.getName());
                            bean.setEmpCode(leaveMaster.getUserCode().getLogin());
                            bean.setName(leaveMaster.getUserCode().getFirstName());
                            bean.setLeaveFrom(format.format(Date.from(leaveMaster.getLeaveDateFrom())) + " " + formatTime.format(Date.from(leaveMaster.getLeaveTimeFrom())));
                            bean.setLeaveTo(format.format(Date.from(leaveMaster.getLeaveDateTo())) + "  " + formatTime.format(Date.from(leaveMaster.getLeaveTimeTo())));
                            bean.setLeaveType(leaveMaster.getLeaveTypeMaster().getLeaveName());
                            bean.setLeaveSubType(leaveMaster.getLeaveSubTypeMaster().getSubTypeName());
                            bean.setFlag((leaveMaster.getFlag() != null && leaveMaster.getFlag().equalsIgnoreCase("A")) ? "Pending with HR" : "Pending with HOD");
                            bean.setReqDate(format.format(Date.from(leaveMaster.getCreatedDate())));
                            bean.setReqTime(formatTime.format(Date.from(leaveMaster.getCreatedDate())));
                            leavePendingReportBean.add(bean);
                        }
                    }

                    String filename = this.applicationProperties.getUploadPath() + "leavePendingReport_" + employeeView.getCardNo() + ".xlsx";
                    String path = this.applicationProperties.getTemplatePath() + "jasper/";
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    parameters.put("SUBREPORT_DIR", path);

                    JasperDesign jasperDesign = JRXmlLoader.load(path + "/LeavePendingReport.jrxml");
                    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                    JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(leavePendingReportBean));
                    File file = new File(filename);
                    if (file.exists()) {
                        file.delete();
                    }
                    JRXlsxExporter xlsExporter = new JRXlsxExporter();

                    xlsExporter.setExporterInput(new SimpleExporterInput(print));
                    xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filename));
                    SimpleXlsxReportConfiguration xlsReportConfiguration = new SimpleXlsxReportConfiguration();
                    xlsReportConfiguration.setOnePagePerSheet(Boolean.valueOf(false));
                    xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.valueOf(true));
                    xlsReportConfiguration.setDetectCellType(Boolean.valueOf(false));
                    xlsReportConfiguration.setWhitePageBackground(Boolean.valueOf(false));
                    xlsExporter.setConfiguration(xlsReportConfiguration);

                    xlsExporter.exportReport();

                    Locale locale = Locale.forLanguageTag("en");
                    Context context = new Context(locale);
                    context.setVariable("name", employeeView.getName());
                    String subject = "Leave Pending Report";
                    String content = null;
                    try {
                        content = this.templateEngine.process("mail/leave_pending_mail", context);
                        this.mailService.sendEmail(employeeView.getEmail(), subject, content, true, true, filename);
                    } catch (Exception exception) {
                    }
                }
            }
        }
    }


    @Scheduled(cron = "0 0 14 1 * ?")
    public void pushLeaveDataDayOne() throws JRException{
        List<LeaveMaster> leaveMasters = this.leaveMasterRepository.findAllByLeaveStatusHOD("E");
        Map<String, List<LeaveMaster>> leaveMap = new HashMap<String, List<LeaveMaster>>();
        for (LeaveMaster leaveMaster : leaveMasters) {
            if (leaveMap.containsKey(leaveMaster.getHodApprovedBy())) {
                List<LeaveMaster> masters = (List) leaveMap.get(leaveMaster.getHodApprovedBy());
                masters.add(leaveMaster);
                leaveMap.put(leaveMaster.getHodApprovedBy(), masters);
                continue;
            }
            List<LeaveMaster> masters = new ArrayList<LeaveMaster>();
            masters.add(leaveMaster);
            leaveMap.put(leaveMaster.getHodApprovedBy(), masters);
        }

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
        for (String key : leaveMap.keySet()) {
            EmployeeMatView employeeView = (EmployeeMatView) this.employeeViewRepository.findByCardNo(key).orElse(null);
            if (employeeView != null && employeeView.getEmail() != null && employeeView.getEmail().length() > 0 && LeaveMasterResource.VALID_EMAIL_ADDRESS_REGEX.matcher(employeeView.getEmail()).find() == true) {
                List<LeaveMaster> masters = (List) leaveMap.get(key);
                Collections.sort(masters, Comparator.comparing(LeaveMaster::getLeaveDateFrom));
                List<LeavePendingReportBean> leavePendingReportBean = new ArrayList<LeavePendingReportBean>();
                if (masters.size() > 0) {
                    for (LeaveMaster leaveMaster : masters) {
                        LeavePendingReportBean bean = new LeavePendingReportBean();
                        bean.setHodCardNo(leaveMaster.getHodApprovedBy());
                        bean.setHodName(employeeView.getName());
                        bean.setEmpCode(leaveMaster.getUserCode().getLogin());
                        bean.setName(leaveMaster.getUserCode().getFirstName());
                        bean.setLeaveFrom(format.format(Date.from(leaveMaster.getLeaveDateFrom())) + " " + formatTime.format(Date.from(leaveMaster.getLeaveTimeFrom())));
                        bean.setLeaveTo(format.format(Date.from(leaveMaster.getLeaveDateTo())) + "  " + formatTime.format(Date.from(leaveMaster.getLeaveTimeTo())));
                        bean.setLeaveType(leaveMaster.getLeaveTypeMaster().getLeaveName());
                        bean.setLeaveSubType(leaveMaster.getLeaveSubTypeMaster().getSubTypeName());
                        bean.setFlag((leaveMaster.getFlag() != null && leaveMaster.getFlag().equalsIgnoreCase("A")) ? "Pending with HR" : "Pending with HOD");
                        bean.setReqDate(format.format(Date.from(leaveMaster.getCreatedDate())));
                        bean.setReqTime(formatTime.format(Date.from(leaveMaster.getCreatedDate())));
                        leavePendingReportBean.add(bean);
                    }
                }

                String filename = this.applicationProperties.getUploadPath() + "leavePendingReport_" + employeeView.getCardNo() + ".xlsx";
                String path = this.applicationProperties.getTemplatePath() + "jasper/";
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("SUBREPORT_DIR", path);

                JasperDesign jasperDesign = JRXmlLoader.load(path + "/LeavePendingReport.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(leavePendingReportBean));
                File file = new File(filename);
                if (file.exists()) {
                    file.delete();
                }
                JRXlsxExporter xlsExporter = new JRXlsxExporter();

                xlsExporter.setExporterInput(new SimpleExporterInput(print));
                xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filename));
                SimpleXlsxReportConfiguration xlsReportConfiguration = new SimpleXlsxReportConfiguration();
                xlsReportConfiguration.setOnePagePerSheet(Boolean.valueOf(false));
                xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.valueOf(true));
                xlsReportConfiguration.setDetectCellType(Boolean.valueOf(false));
                xlsReportConfiguration.setWhitePageBackground(Boolean.valueOf(false));
                xlsExporter.setConfiguration(xlsReportConfiguration);

                xlsExporter.exportReport();

                Locale locale = Locale.forLanguageTag("en");
                Context context = new Context(locale);
                context.setVariable("name", employeeView.getName());
                String subject = "Leave Pending Report";
                String content = null;
                try {
                    content = this.templateEngine.process("mail/leave_pending_mail", context);
                    this.mailService.sendEmail(employeeView.getEmail(), subject, content, true, true, filename);
                } catch (Exception exception) {
                }
            }
        }
    }


    @Scheduled(cron = "0 30 17 28-31 * ?")
    public void pushLeaveDataHR() throws JRException{
        Calendar c = Calendar.getInstance();
        if (c.get(5) == c.getActualMaximum(5)) {
            List<LeaveMaster> leaveMasters = this.leaveMasterRepository.findAllByLeaveStatus("E");
            Map<String, Map<String, List<LeaveMaster>>> factoryMap = new HashMap<String, Map<String, List<LeaveMaster>>>();
            for (LeaveMaster leaveMaster : leaveMasters) {
                if (factoryMap.containsKey(leaveMaster.getFactory())) {
                    Map<String, List<LeaveMaster>> leaveMap = (Map) factoryMap.get(leaveMaster.getFactory());
                    if (leaveMap.containsKey(leaveMaster.getHodApprovedBy())) {
                        List<LeaveMaster> masters = (List) leaveMap.get(leaveMaster.getHodApprovedBy());
                        masters.add(leaveMaster);
                        leaveMap.put(leaveMaster.getHodApprovedBy(), masters);
                    } else {
                        List<LeaveMaster> masters = new ArrayList<LeaveMaster>();
                        masters.add(leaveMaster);
                        leaveMap.put(leaveMaster.getHodApprovedBy(), masters);
                    }
                    factoryMap.put(leaveMaster.getFactory(), leaveMap);
                    continue;
                }
                Map<String, List<LeaveMaster>> leaveMap = new HashMap<String, List<LeaveMaster>>();
                List<LeaveMaster> masters = new ArrayList<LeaveMaster>();
                masters.add(leaveMaster);
                leaveMap.put(leaveMaster.getHodApprovedBy(), masters);
                factoryMap.put(leaveMaster.getFactory(), leaveMap);
            }

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

            for (String key : factoryMap.keySet()) {
                List<LeavePendingReportBean> leavePendingReportBean = new ArrayList<LeavePendingReportBean>();
                Map<String, List<LeaveMaster>> leaveMap = (Map) factoryMap.get(key);
                for (String approvalKey : leaveMap.keySet()) {
                    EmployeeMatView employeeView = (EmployeeMatView) this.employeeViewRepository.findByCardNo(approvalKey).orElse(null);
                    List<LeaveMaster> masters = (List) leaveMap.get(approvalKey);
                    if (masters.size() > 0) {
                        for (LeaveMaster leaveMaster : masters) {
                            LeavePendingReportBean bean = new LeavePendingReportBean();
                            bean.setHodCardNo(leaveMaster.getHodApprovedBy());
                            bean.setHodName((employeeView != null) ? employeeView.getName() : "");
                            bean.setEmpCode(leaveMaster.getUserCode().getLogin());
                            bean.setName(leaveMaster.getUserCode().getFirstName());
                            bean.setLeaveFrom(format.format(Date.from(leaveMaster.getLeaveDateFrom())) + " " + formatTime.format(Date.from(leaveMaster.getLeaveTimeFrom())));
                            bean.setLeaveFromDt(Date.from(leaveMaster.getLeaveDateFrom()));
                            bean.setLeaveTo(format.format(Date.from(leaveMaster.getLeaveDateTo())) + "  " + formatTime.format(Date.from(leaveMaster.getLeaveTimeTo())));
                            bean.setLeaveType(leaveMaster.getLeaveTypeMaster().getLeaveName());
                            bean.setLeaveSubType(leaveMaster.getLeaveSubTypeMaster().getSubTypeName());
                            bean.setFlag((leaveMaster.getFlag() != null && leaveMaster.getFlag().equalsIgnoreCase("A")) ? "Pending with HR" : "Pending with HOD");
                            bean.setReqDate(format.format(Date.from(leaveMaster.getCreatedDate())));
                            bean.setReqTime(formatTime.format(Date.from(leaveMaster.getCreatedDate())));
                            leavePendingReportBean.add(bean);
                        }
                    }
                }
                Collections.sort(leavePendingReportBean, Comparator.comparing(LeavePendingReportBean::getHodCardNo).thenComparing(LeavePendingReportBean::getLeaveFromDt));
                String filename = this.applicationProperties.getUploadPath() + "leavePendingReport_" + key + ".xlsx";
                String path = this.applicationProperties.getTemplatePath() + "jasper/";
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.put("SUBREPORT_DIR", path);

                JasperDesign jasperDesign = JRXmlLoader.load(path + "/LeavePendingReport.jrxml");
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(leavePendingReportBean));
                File file = new File(filename);
                if (file.exists()) {
                    file.delete();
                }
                JRXlsxExporter xlsExporter = new JRXlsxExporter();

                xlsExporter.setExporterInput(new SimpleExporterInput(print));
                xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filename));
                SimpleXlsxReportConfiguration xlsReportConfiguration = new SimpleXlsxReportConfiguration();
                xlsReportConfiguration.setOnePagePerSheet(Boolean.valueOf(false));
                xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.valueOf(true));
                xlsReportConfiguration.setDetectCellType(Boolean.valueOf(false));
                xlsReportConfiguration.setWhitePageBackground(Boolean.valueOf(false));
                xlsExporter.setConfiguration(xlsReportConfiguration);

                xlsExporter.exportReport();

                Locale locale = Locale.forLanguageTag("en");
                Context context = new Context(locale);
                String subject = "Leave Pending Report";
                String content = null;
                try {
                    content = this.templateEngine.process("mail/leave_pending_mail", context);
                    if (key != null && (key.equalsIgnoreCase("102") || key.equalsIgnoreCase("349"))) {
                        this.mailService.sendEmail("hrd169@vamanioverseas.com", subject, content, true, true, filename);
                        continue;
                    }
                    if (key != null && key.equalsIgnoreCase("101")) {
                        this.mailService.sendEmail("hrd@vamanioverseas.com", subject, content, true, true, filename);
                        continue;
                    }
                    if (key != null && (key.equalsIgnoreCase("190") || key.equalsIgnoreCase("191"))) {
                        this.mailService.sendEmail("samayyadav@vamanioverseas.com", subject, content, true, true, filename);
                        continue;
                    }
                    if (key != null && key.equalsIgnoreCase("106")) {
                        this.mailService.sendEmail("bhushankumar@vamanioverseas.com", subject, content, true, true, filename);
                        continue;
                    } if (key != null && key.equalsIgnoreCase("107")) {
                        mailService.sendEmail("ramavatar@vamanioverseas.com", subject, content, false, true, filename);
                    }
                } catch (Exception exception) {
                }
            }
        }
    }


    @Scheduled(cron = "0 30 17 1 * ?")
    public void pushLeaveDataHROne() throws JRException{
        List<LeaveMaster> leaveMasters = this.leaveMasterRepository.findAllByLeaveStatus("E");
        Map<String, Map<String, List<LeaveMaster>>> factoryMap = new HashMap<String, Map<String, List<LeaveMaster>>>();
        for (LeaveMaster leaveMaster : leaveMasters) {
            if (factoryMap.containsKey(leaveMaster.getFactory())) {
                Map<String, List<LeaveMaster>> leaveMap = (Map) factoryMap.get(leaveMaster.getFactory());
                if (leaveMap.containsKey(leaveMaster.getHodApprovedBy())) {
                    List<LeaveMaster> masters = (List) leaveMap.get(leaveMaster.getHodApprovedBy());
                    masters.add(leaveMaster);
                    leaveMap.put(leaveMaster.getHodApprovedBy(), masters);
                } else {
                    List<LeaveMaster> masters = new ArrayList<LeaveMaster>();
                    masters.add(leaveMaster);
                    leaveMap.put(leaveMaster.getHodApprovedBy(), masters);
                }
                factoryMap.put(leaveMaster.getFactory(), leaveMap);
                continue;
            }
            Map<String, List<LeaveMaster>> leaveMap = new HashMap<String, List<LeaveMaster>>();
            List<LeaveMaster> masters = new ArrayList<LeaveMaster>();
            masters.add(leaveMaster);
            leaveMap.put(leaveMaster.getHodApprovedBy(), masters);
            factoryMap.put(leaveMaster.getFactory(), leaveMap);
        }

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

        for (String key : factoryMap.keySet()) {
            List<LeavePendingReportBean> leavePendingReportBean = new ArrayList<LeavePendingReportBean>();
            Map<String, List<LeaveMaster>> leaveMap = (Map) factoryMap.get(key);
            for (String approvalKey : leaveMap.keySet()) {
                EmployeeMatView employeeView = (EmployeeMatView) this.employeeViewRepository.findByCardNo(approvalKey).orElse(null);
                List<LeaveMaster> masters = (List) leaveMap.get(approvalKey);
                if (masters.size() > 0) {
                    for (LeaveMaster leaveMaster : masters) {
                        LeavePendingReportBean bean = new LeavePendingReportBean();
                        bean.setHodCardNo(leaveMaster.getHodApprovedBy());
                        bean.setHodName((employeeView != null) ? employeeView.getName() : "");
                        bean.setEmpCode(leaveMaster.getUserCode().getLogin());
                        bean.setName(leaveMaster.getUserCode().getFirstName());
                        bean.setLeaveFrom(format.format(Date.from(leaveMaster.getLeaveDateFrom())) + " " + formatTime.format(Date.from(leaveMaster.getLeaveTimeFrom())));
                        bean.setLeaveFromDt(Date.from(leaveMaster.getLeaveDateFrom()));
                        bean.setLeaveTo(format.format(Date.from(leaveMaster.getLeaveDateTo())) + "  " + formatTime.format(Date.from(leaveMaster.getLeaveTimeTo())));
                        bean.setLeaveType(leaveMaster.getLeaveTypeMaster().getLeaveName());
                        bean.setLeaveSubType(leaveMaster.getLeaveSubTypeMaster().getSubTypeName());
                        bean.setFlag((leaveMaster.getFlag() != null && leaveMaster.getFlag().equalsIgnoreCase("A")) ? "Pending with HR" : "Pending with HOD");
                        bean.setReqDate(format.format(Date.from(leaveMaster.getCreatedDate())));
                        bean.setReqTime(formatTime.format(Date.from(leaveMaster.getCreatedDate())));
                        leavePendingReportBean.add(bean);
                    }
                }
            }
            Collections.sort(leavePendingReportBean, Comparator.comparing(LeavePendingReportBean::getHodCardNo).thenComparing(LeavePendingReportBean::getLeaveFromDt));
            String filename = this.applicationProperties.getUploadPath() + "leavePendingReport_" + key + ".xlsx";
            String path = this.applicationProperties.getTemplatePath() + "jasper/";
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("SUBREPORT_DIR", path);

            JasperDesign jasperDesign = JRXmlLoader.load(path + "/LeavePendingReport.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, new JRBeanCollectionDataSource(leavePendingReportBean));
            File file = new File(filename);
            if (file.exists()) {
                file.delete();
            }
            JRXlsxExporter xlsExporter = new JRXlsxExporter();

            xlsExporter.setExporterInput(new SimpleExporterInput(print));
            xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(filename));
            SimpleXlsxReportConfiguration xlsReportConfiguration = new SimpleXlsxReportConfiguration();
            xlsReportConfiguration.setOnePagePerSheet(Boolean.valueOf(false));
            xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(Boolean.valueOf(true));
            xlsReportConfiguration.setDetectCellType(Boolean.valueOf(false));
            xlsReportConfiguration.setWhitePageBackground(Boolean.valueOf(false));
            xlsExporter.setConfiguration(xlsReportConfiguration);

            xlsExporter.exportReport();

            Locale locale = Locale.forLanguageTag("en");
            Context context = new Context(locale);
            String subject = "Leave Pending Report";
            String content = null;
            try {
                content = this.templateEngine.process("mail/leave_pending_mail", context);
                if (key != null && (key.equalsIgnoreCase("102") || key.equalsIgnoreCase("349"))) {
                    this.mailService.sendEmail("hrd169@vamanioverseas.com", subject, content, true, true, filename);
                    continue;
                }
                if (key != null && key.equalsIgnoreCase("101")) {
                    this.mailService.sendEmail("hrd@vamanioverseas.com", subject, content, true, true, filename);
                    continue;
                }
                if (key != null && (key.equalsIgnoreCase("190") || key.equalsIgnoreCase("191"))) {
                    this.mailService.sendEmail("samayyadav@vamanioverseas.com", subject, content, true, true, filename);
                    continue;
                }
                if (key != null && key.equalsIgnoreCase("106")) {
                    this.mailService.sendEmail("bhushankumar@vamanioverseas.com", subject, content, true, true, filename);
                    continue;
                }
            } catch (Exception exception) {
            }
        }
    }

    @Scheduled(cron = "0 19 11 8 * ?")
    public void pushLeaveDataFlagUpdate() throws JRException{
        Calendar aCalendar = Calendar.getInstance();
        // add -1 month to current month
        aCalendar.add(Calendar.MONTH, -1);
        aCalendar.set(Calendar.HOUR, 11);
        aCalendar.set(Calendar.MINUTE, 59);
        aCalendar.set(Calendar.AM_PM, Calendar.PM);

        // set actual maximum date of previous month
        aCalendar.set(Calendar.DATE, aCalendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        //read it
        java.util.Date lastDateOfPreviousMonth = aCalendar.getTime();
        List<LeaveMaster> leaveMasters = this.leaveMasterRepository.findAllByLeaveStatus("E", lastDateOfPreviousMonth.toInstant());
        Map<String, List<LeaveMaster>> leaveMap = new HashMap<String, List<LeaveMaster>>();
        for (LeaveMaster leaveMaster : leaveMasters) {
            leaveMaster.setFlag("P");
            leaveMasterRepository.save(leaveMaster);
        }
    }
}

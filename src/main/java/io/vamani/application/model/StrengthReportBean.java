package io.vamani.application.model;

import java.sql.Timestamp;
import java.time.Instant;

public class StrengthReportBean {
    private Timestamp dayNo;
    private String factory;
    private String subComp;
    private String line;
    private Instant dateFrom;
    private String empCode;
    private String name;
    private String depCodeDesc;
    private String desCodeDesc;
    private String payCode;
    private String swCode;
    private String status;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }


    public Instant getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Instant dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepCodeDesc() {
        return depCodeDesc;
    }

    public void setDepCodeDesc(String depCodeDesc) {
        this.depCodeDesc = depCodeDesc;
    }

    public String getDesCodeDesc() {
        return desCodeDesc;
    }

    public void setDesCodeDesc(String desCodeDesc) {
        this.desCodeDesc = desCodeDesc;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getSwCode() {
        return swCode;
    }

    public void setSwCode(String swCode) {
        this.swCode = swCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDayNo() {
        return dayNo;
    }

    public void setDayNo(Timestamp dayNo) {
        this.dayNo = dayNo;
    }

    public String getSubComp() {
        return subComp;
    }

    public void setSubComp(String subComp) {
        this.subComp = subComp;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }
}

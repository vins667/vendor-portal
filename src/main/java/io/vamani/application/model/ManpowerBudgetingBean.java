package io.vamani.application.model;

import io.vamani.application.mssql.model.ManpowerBean;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class ManpowerBudgetingBean implements Serializable {
    private String factoryCode;
    private String factoryName;
    private String nowFactoryName;
    private String departmentCode;
    private String departmentName;
    private String type;
    private Instant dateFrom;
    private List<ManpowerBean> manpowerBeans;

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getNowFactoryName() {
        return nowFactoryName;
    }

    public void setNowFactoryName(String nowFactoryName) {
        this.nowFactoryName = nowFactoryName;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instant getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Instant dateFrom) {
        this.dateFrom = dateFrom;
    }

    public List<ManpowerBean> getManpowerBeans() {
        return manpowerBeans;
    }

    public void setManpowerBeans(List<ManpowerBean> manpowerBeans) {
        this.manpowerBeans = manpowerBeans;
    }
}

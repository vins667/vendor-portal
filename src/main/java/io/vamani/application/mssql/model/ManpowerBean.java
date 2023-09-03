package io.vamani.application.mssql.model;

import io.vamani.application.db2.model.ResourcesBean;

import java.io.Serializable;
import java.util.List;

public class ManpowerBean implements Serializable {
    private String desCode;
    private String desCodeDesc;
    private String sdepCode;
    private String subDeptDesc;
    private String catCode;
    private String catName;
    private Integer employeeBalance;
    private Integer employeeCount;
    private Integer employeeCountActual;
    private List<ResourcesBean> resources;

    public String getDesCode() {
        return desCode;
    }

    public void setDesCode(String desCode) {
        this.desCode = desCode;
    }

    public String getDesCodeDesc() {
        return desCodeDesc;
    }

    public void setDesCodeDesc(String desCodeDesc) {
        this.desCodeDesc = desCodeDesc;
    }

    public String getSdepCode() {
        return sdepCode;
    }

    public void setSdepCode(String sdepCode) {
        this.sdepCode = sdepCode;
    }

    public String getSubDeptDesc() {
        return subDeptDesc;
    }

    public void setSubDeptDesc(String subDeptDesc) {
        this.subDeptDesc = subDeptDesc;
    }

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public Integer getEmployeeBalance() {
        return employeeBalance;
    }

    public void setEmployeeBalance(Integer employeeBalance) {
        this.employeeBalance = employeeBalance;
    }

    public Integer getEmployeeCountActual() {
        return employeeCountActual;
    }

    public void setEmployeeCountActual(Integer employeeCountActual) {
        this.employeeCountActual = employeeCountActual;
    }

    public List<ResourcesBean> getResources() {
        return resources;
    }

    public void setResources(List<ResourcesBean> resources) {
        this.resources = resources;
    }
}

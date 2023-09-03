package io.vamani.application.model;

import java.math.BigDecimal;
import java.util.List;

public class SalesOrderClosingHeaderBean {
    private String projectCode;
    private String companycode;
    private String divisioncode;
    private String salesordercode;
    private String salesorderdate;
    private String customerCode;
    private String customerName;
    private String style;
    private BigDecimal orderQuantity;
    private BigDecimal tolerance;
    private BigDecimal totalQuantity;
    private BigDecimal shippedQuantity;
    private BigDecimal shippedPercentage;
    private Boolean status;

    private List<SalesOrderClosingDetailsBean> salesOrderClosingDetailsBeans;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getDivisioncode() {
        return divisioncode;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
    }

    public String getSalesordercode() {
        return salesordercode;
    }

    public void setSalesordercode(String salesordercode) {
        this.salesordercode = salesordercode;
    }

    public String getSalesorderdate() {
        return salesorderdate;
    }

    public void setSalesorderdate(String salesorderdate) {
        this.salesorderdate = salesorderdate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public BigDecimal getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(BigDecimal orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getTolerance() {
        return tolerance;
    }

    public void setTolerance(BigDecimal tolerance) {
        this.tolerance = tolerance;
    }

    public BigDecimal getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(BigDecimal totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getShippedQuantity() {
        return shippedQuantity;
    }

    public void setShippedQuantity(BigDecimal shippedQuantity) {
        this.shippedQuantity = shippedQuantity;
    }

    public BigDecimal getShippedPercentage() {
        return shippedPercentage;
    }

    public void setShippedPercentage(BigDecimal shippedPercentage) {
        this.shippedPercentage = shippedPercentage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<SalesOrderClosingDetailsBean> getSalesOrderClosingDetailsBeans() {
        return salesOrderClosingDetailsBeans;
    }

    public void setSalesOrderClosingDetailsBeans(List<SalesOrderClosingDetailsBean> salesOrderClosingDetailsBeans) {
        this.salesOrderClosingDetailsBeans = salesOrderClosingDetailsBeans;
    }
}

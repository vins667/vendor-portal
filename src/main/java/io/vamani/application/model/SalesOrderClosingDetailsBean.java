package io.vamani.application.model;

import java.math.BigDecimal;

public class SalesOrderClosingDetailsBean {
    private String projectCode;
    private String companycode;
    private String divisioncode;
    private String salesordercode;
    private String orderline;
    private String ordersubline;
    private String salesorderdate;
    private String customerCode;
    private String customerName;
    private String style;
    private String styleColor;
    private String styleSize;
    private BigDecimal orderQuantity;
    private BigDecimal tolerance;
    private BigDecimal totalQuantity;
    private BigDecimal shippedQuantity;
    private BigDecimal shippedPercentage;
    private String status;

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

    public String getOrderline() {
        return orderline;
    }

    public void setOrderline(String orderline) {
        this.orderline = orderline;
    }

    public String getOrdersubline() {
        return ordersubline;
    }

    public void setOrdersubline(String ordersubline) {
        this.ordersubline = ordersubline;
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

    public String getStyleColor() {
        return styleColor;
    }

    public void setStyleColor(String styleColor) {
        this.styleColor = styleColor;
    }

    public String getStyleSize() {
        return styleSize;
    }

    public void setStyleSize(String styleSize) {
        this.styleSize = styleSize;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

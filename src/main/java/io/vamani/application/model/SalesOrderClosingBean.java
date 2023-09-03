package io.vamani.application.model;

import java.math.BigDecimal;
import java.util.List;

public class SalesOrderClosingBean {
    private String projectCode;
    private String style;
    private String customerCode;
    private String customerName;
    private String status;
    private BigDecimal orderQuantity;
    private BigDecimal tolerance;
    private BigDecimal totalQuantity;
    private BigDecimal shippedQuantity;
    private BigDecimal shippedPercentage;

    private List<SalesOrderClosingHeaderBean> salesOrderClosingHeaderBeans;

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
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

    public List<SalesOrderClosingHeaderBean> getSalesOrderClosingHeaderBeans() {
        return salesOrderClosingHeaderBeans;
    }

    public void setSalesOrderClosingHeaderBeans(List<SalesOrderClosingHeaderBean> salesOrderClosingHeaderBeans) {
        this.salesOrderClosingHeaderBeans = salesOrderClosingHeaderBeans;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

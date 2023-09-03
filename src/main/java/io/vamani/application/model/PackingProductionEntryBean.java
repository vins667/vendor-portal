package io.vamani.application.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class PackingProductionEntryBean implements Serializable {
    private Long id;

    private String companycode;

    private String countercode;

    private String productionordercode;

    private String plantCode;

    private String plantDesc;

    private String projectcode;

    private String style;

    private String color;

    private String colorDesc;

    private String destination;

    private String destinationDesc;

    private Double orderQty;

    private Double tolerance;

    private Double netOrderQty;

    private String createdby;

    private Instant createddate;

    private String updatedby;

    private Instant updateddate;

    private String progressPostedBy;

    private Instant progressPostedDate;

    private PackingProgressEntryBean packingProgressEntry;

    private List<PackingProgressEntryBean> packingProgressEntries;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getCountercode() {
        return countercode;
    }

    public void setCountercode(String countercode) {
        this.countercode = countercode;
    }

    public String getProductionordercode() {
        return productionordercode;
    }

    public void setProductionordercode(String productionordercode) {
        this.productionordercode = productionordercode;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getPlantDesc() {
        return plantDesc;
    }

    public void setPlantDesc(String plantDesc) {
        this.plantDesc = plantDesc;
    }

    public String getProjectcode() {
        return projectcode;
    }

    public void setProjectcode(String projectcode) {
        this.projectcode = projectcode;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColorDesc() {
        return colorDesc;
    }

    public void setColorDesc(String colorDesc) {
        this.colorDesc = colorDesc;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationDesc() {
        return destinationDesc;
    }

    public void setDestinationDesc(String destinationDesc) {
        this.destinationDesc = destinationDesc;
    }

    public Double getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public Double getTolerance() {
        return tolerance;
    }

    public void setTolerance(Double tolerance) {
        this.tolerance = tolerance;
    }

    public Double getNetOrderQty() {
        return netOrderQty;
    }

    public void setNetOrderQty(Double netOrderQty) {
        this.netOrderQty = netOrderQty;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Instant getUpdateddate() {
        return updateddate;
    }

    public void setUpdateddate(Instant updateddate) {
        this.updateddate = updateddate;
    }

    public String getProgressPostedBy() {
        return progressPostedBy;
    }

    public void setProgressPostedBy(String progressPostedBy) {
        this.progressPostedBy = progressPostedBy;
    }

    public Instant getProgressPostedDate() {
        return progressPostedDate;
    }

    public void setProgressPostedDate(Instant progressPostedDate) {
        this.progressPostedDate = progressPostedDate;
    }

    public PackingProgressEntryBean getPackingProgressEntry() {
        return packingProgressEntry;
    }

    public void setPackingProgressEntry(PackingProgressEntryBean packingProgressEntry) {
        this.packingProgressEntry = packingProgressEntry;
    }

    public List<PackingProgressEntryBean> getPackingProgressEntries() {
        return packingProgressEntries;
    }

    public void setPackingProgressEntries(List<PackingProgressEntryBean> packingProgressEntries) {
        this.packingProgressEntries = packingProgressEntries;
    }
}

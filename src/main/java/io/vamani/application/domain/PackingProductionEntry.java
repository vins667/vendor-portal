package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "packing_production_entry")
public class PackingProductionEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="packingProductionEntrySeq", sequenceName="packing_production_entry_seq", allocationSize=1)
    @GeneratedValue(strategy= GenerationType.AUTO, generator="packingProductionEntrySeq")
    private Long id;

    @NotNull
    @Size(max = 3)
    @Column(name = "companycode", length = 3, nullable = false)
    private String companycode;

    @NotNull
    @Size(max = 8)
    @Column(name = "countercode", length = 8, nullable = false)
    private String countercode;


    @NotNull
    @Size(max = 15)
    @Column(name = "productionordercode", length = 15, nullable = false)
    private String productionordercode;

    @NotNull
    @Size(max = 20)
    @Column(name = "plant_code", length = 20, nullable = false)
    private String plantCode;

    @Size(max = 100)
    @Column(name = "plant_desc", length = 100)
    private String plantDesc;

    @NotNull
    @Size(max = 50)
    @Column(name = "projectcode", length = 50, nullable = false)
    private String projectcode;

    @NotNull
    @Size(max = 20)
    @Column(name = "style", length = 20, nullable = false)
    private String style;

    @NotNull
    @Size(max = 20)
    @Column(name = "color", length = 20, nullable = false)
    private String color;

    @Size(max = 100)
    @Column(name = "color_desc", length = 100)
    private String colorDesc;

    @Size(max = 20)
    @Column(name = "destination", length = 20, nullable = false)
    private String destination;

    @Size(max = 100)
    @Column(name = "destination_desc", length = 100)
    private String destinationDesc;

    @Column(name = "order_qty")
    private Double orderQty;

    @Column(name = "tolerance")
    private Double tolerance;

    @Column(name = "net_order_qty")
    private Double netOrderQty;

    @Size(max = 20)
    @Column(name = "createdby", length = 20)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;

    @Size(max = 20)
    @Column(name = "updatedby", length = 20)
    private String updatedby;

    @Column(name = "updateddate")
    private Instant updateddate;

    @Size(max = 20)
    @Column(name = "progress_posted_by", length = 20)
    private String progressPostedBy;

    @Column(name = "progress_posted_date")
    private Instant progressPostedDate;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackingProductionEntry that = (PackingProductionEntry) o;
        return Objects.equals(id, that.id) && Objects.equals(companycode, that.companycode) && Objects.equals(countercode, that.countercode) && Objects.equals(productionordercode, that.productionordercode) && Objects.equals(plantCode, that.plantCode) && Objects.equals(plantDesc, that.plantDesc) && Objects.equals(projectcode, that.projectcode) && Objects.equals(style, that.style) && Objects.equals(color, that.color) && Objects.equals(colorDesc, that.colorDesc) && Objects.equals(destination, that.destination) && Objects.equals(destinationDesc, that.destinationDesc) && Objects.equals(orderQty, that.orderQty) && Objects.equals(tolerance, that.tolerance) && Objects.equals(netOrderQty, that.netOrderQty) && Objects.equals(createdby, that.createdby) && Objects.equals(createddate, that.createddate) && Objects.equals(updatedby, that.updatedby) && Objects.equals(updateddate, that.updateddate) && Objects.equals(progressPostedBy, that.progressPostedBy) && Objects.equals(progressPostedDate, that.progressPostedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companycode, countercode, productionordercode, plantCode, plantDesc, projectcode, style, color, colorDesc, destination, destinationDesc, orderQty, tolerance, netOrderQty, createdby, createddate, updatedby, updateddate, progressPostedBy, progressPostedDate);
    }
}

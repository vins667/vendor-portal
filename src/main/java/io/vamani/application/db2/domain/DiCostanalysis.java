package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "DI_COSTANALYSIS")
public class DiCostanalysis implements Serializable {
    @EmbeddedId
    private DiCostanalysisId id;
    private String description;
    private String uomcode;
    private BigDecimal costline;
    private BigDecimal reqqty;
    private BigDecimal price;
    private BigDecimal actualprice;
    private String createdby;
    private Timestamp createddate;

    public DiCostanalysisId getId() {
        return id;
    }

    public void setId(DiCostanalysisId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = true, length = 200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "UOMCODE", nullable = true, length = 3)
    public String getUomcode() {
        return uomcode;
    }

    public void setUomcode(String uomcode) {
        this.uomcode = uomcode;
    }

    @Basic
    @Column(name = "COSTLINE", nullable = true, precision = 5)
    public BigDecimal getCostline() {
        return costline;
    }

    public void setCostline(BigDecimal costline) {
        this.costline = costline;
    }

    @Basic
    @Column(name = "REQQTY", nullable = true, precision = 5)
    public BigDecimal getReqqty() {
        return reqqty;
    }

    public void setReqqty(BigDecimal reqqty) {
        this.reqqty = reqqty;
    }

    @Basic
    @Column(name = "PRICE", nullable = true, precision = 5)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "ACTUALPRICE", nullable = true, precision = 5)
    public BigDecimal getActualprice() {
        return actualprice;
    }

    public void setActualprice(BigDecimal actualprice) {
        this.actualprice = actualprice;
    }

    @Basic
    @Column(name = "CREATEDBY", nullable = true, length = 50)
    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    @Basic
    @Column(name = "CREATEDDATE", nullable = true)
    public Timestamp getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Timestamp createddate) {
        this.createddate = createddate;
    }
}

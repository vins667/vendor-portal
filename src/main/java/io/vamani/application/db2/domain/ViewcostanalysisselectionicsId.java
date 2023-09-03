package io.vamani.application.db2.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

public class ViewcostanalysisselectionicsId implements Serializable {
    private String salordlinesalordercompanycode;
    private String salordlinesalordercountercode;
    private String salesorderlinesalesordercode;
    private Integer salesorderlineorderline;
    private Integer salesorderlineordersubline;
    private Integer salordlinecomponentorderline;
    private Date approvaldate;
    private Short approved;
    private Timestamp creationdatetime;
    private Integer costsheetno;
    private Long uniqueid;
    private Long tableindex;

    @Basic
    @Column(name = "SALORDLINESALORDERCOMPANYCODE", nullable = false, length = 3)
    public String getSalordlinesalordercompanycode() {
        return salordlinesalordercompanycode;
    }

    public void setSalordlinesalordercompanycode(String salordlinesalordercompanycode) {
        this.salordlinesalordercompanycode = salordlinesalordercompanycode;
    }

    @Basic
    @Column(name = "SALORDLINESALORDERCOUNTERCODE", nullable = false, length = 8)
    public String getSalordlinesalordercountercode() {
        return salordlinesalordercountercode;
    }

    public void setSalordlinesalordercountercode(String salordlinesalordercountercode) {
        this.salordlinesalordercountercode = salordlinesalordercountercode;
    }

    @Basic
    @Column(name = "SALESORDERLINESALESORDERCODE", nullable = false, length = 15)
    public String getSalesorderlinesalesordercode() {
        return salesorderlinesalesordercode;
    }

    public void setSalesorderlinesalesordercode(String salesorderlinesalesordercode) {
        this.salesorderlinesalesordercode = salesorderlinesalesordercode;
    }

    @Basic
    @Column(name = "SALESORDERLINEORDERLINE", nullable = false, precision = 0)
    public Integer getSalesorderlineorderline() {
        return salesorderlineorderline;
    }

    public void setSalesorderlineorderline(Integer salesorderlineorderline) {
        this.salesorderlineorderline = salesorderlineorderline;
    }

    @Basic
    @Column(name = "SALESORDERLINEORDERSUBLINE", nullable = false, precision = 0)
    public Integer getSalesorderlineordersubline() {
        return salesorderlineordersubline;
    }

    public void setSalesorderlineordersubline(Integer salesorderlineordersubline) {
        this.salesorderlineordersubline = salesorderlineordersubline;
    }

    @Basic
    @Column(name = "SALORDLINECOMPONENTORDERLINE", nullable = false, precision = 0)
    public Integer getSalordlinecomponentorderline() {
        return salordlinecomponentorderline;
    }

    public void setSalordlinecomponentorderline(Integer salordlinecomponentorderline) {
        this.salordlinecomponentorderline = salordlinecomponentorderline;
    }

    @Basic
    @Column(name = "APPROVALDATE", nullable = true)
    public Date getApprovaldate() {
        return approvaldate;
    }

    public void setApprovaldate(Date approvaldate) {
        this.approvaldate = approvaldate;
    }

    @Basic
    @Column(name = "APPROVED", nullable = false)
    public Short getApproved() {
        return approved;
    }

    public void setApproved(Short approved) {
        this.approved = approved;
    }

    @Basic
    @Column(name = "CREATIONDATETIME", nullable = true)
    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    @Basic
    @Column(name = "COSTSHEETNO", nullable = false)
    public Integer getCostsheetno() {
        return costsheetno;
    }

    public void setCostsheetno(Integer costsheetno) {
        this.costsheetno = costsheetno;
    }

    @Basic
    @Column(name = "UNIQUEID", nullable = false)
    public Long getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(Long uniqueid) {
        this.uniqueid = uniqueid;
    }

    @Basic
    @Column(name = "TABLEINDEX", nullable = false)
    public Long getTableindex() {
        return tableindex;
    }

    public void setTableindex(Long tableindex) {
        this.tableindex = tableindex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewcostanalysisselectionicsId that = (ViewcostanalysisselectionicsId) o;
        return Objects.equals(salordlinesalordercompanycode, that.salordlinesalordercompanycode) && Objects.equals(salordlinesalordercountercode, that.salordlinesalordercountercode) && Objects.equals(salesorderlinesalesordercode, that.salesorderlinesalesordercode) && Objects.equals(salesorderlineorderline, that.salesorderlineorderline) && Objects.equals(salesorderlineordersubline, that.salesorderlineordersubline) && Objects.equals(salordlinecomponentorderline, that.salordlinecomponentorderline) && Objects.equals(approvaldate, that.approvaldate) && Objects.equals(approved, that.approved) && Objects.equals(creationdatetime, that.creationdatetime) && Objects.equals(costsheetno, that.costsheetno) && Objects.equals(uniqueid, that.uniqueid) && Objects.equals(tableindex, that.tableindex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salordlinesalordercompanycode, salordlinesalordercountercode, salesorderlinesalesordercode, salesorderlineorderline, salesorderlineordersubline, salordlinecomponentorderline, approvaldate, approved, creationdatetime, costsheetno, uniqueid, tableindex);
    }
}

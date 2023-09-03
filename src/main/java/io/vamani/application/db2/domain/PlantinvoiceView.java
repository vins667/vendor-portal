package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "plantinvoice")
public class PlantinvoiceView {
    @EmbeddedId
    private PlantinvoiceId id;

    @Basic
    @Column(name = "INVOICEDATE", nullable = true)
    private Date invoicedate;

    @Basic
    @Column(name = "INVOICETYPECODE", nullable = true, length = 3)
    private String invoicetypecode;
    @Basic
    @Column(name = "CHALLANNO", nullable = true, length = 15)
    private String challanno;

    @Basic
    @Column(name = "CHALLANDATE", nullable = true)
    private Date challandate;

    public PlantinvoiceId getId() {
        return id;
    }

    public void setId(PlantinvoiceId id) {
        this.id = id;
    }

    public Date getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(Date invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getChallanno() {
        return challanno.trim();
    }

    public void setChallanno(String challanno) {
        this.challanno = challanno;
    }

    public Date getChallandate() {
        return challandate;
    }

    public void setChallandate(Date challandate) {
        this.challandate = challandate;
    }

    public String getInvoicetypecode() {
        return invoicetypecode;
    }

    public void setInvoicetypecode(String invoicetypecode) {
        this.invoicetypecode = invoicetypecode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlantinvoiceView that = (PlantinvoiceView) o;
        return Objects.equals(id, that.id) && Objects.equals(invoicedate, that.invoicedate) && Objects.equals(challanno, that.challanno) && Objects.equals(challandate, that.challandate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invoicedate, challanno, challandate);
    }
}

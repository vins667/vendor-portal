package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A GstVoplUpload.
 */
@Entity
@Table(name = "gst_vopl_upload")
public class GstVoplUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="gstVoplUploadSeq", sequenceName="gst_vopl_upload_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="gstVoplUploadSeq")
    private Long id;

    @NotNull
    @Size(max = 15)
    @Column(name = "v_vchno", length = 15, nullable = false)
    private String vVchno;

    @Column(name = "v_vchdate")
    private Instant vVchdate;

    @NotNull
    @Size(max = 20)
    @Column(name = "v_gstin", length = 20, nullable = false)
    private String vGstin;

    @NotNull
    @Size(max = 20)
    @Column(name = "v_supplier_code", length = 20, nullable = false)
    private String vSupplierCode;

    @NotNull
    @Size(max = 200)
    @Column(name = "v_supplier_name", length = 200, nullable = false)
    private String vSupplierName;

    @NotNull
    @Size(max = 25)
    @Column(name = "v_invoiceno", length = 25, nullable = false)
    private String vInvoiceno;

    @Column(name = "v_invoicedate")
    private Instant vInvoicedate;

    @Column(name = "v_invamt")
    private Double vInvamt;

    @Column(name = "v_invnet")
    private Double vInvnet;

    @Column(name = "v_cgst")
    private Double vCgst;

    @Column(name = "v_sgst")
    private Double vSgst;

    @Column(name = "v_igst")
    private Double vIgst;

    @Column(name = "upload_date")
    private Instant uploadDate;

    @Column(name = "confirm_date")
    private Instant confirmDate;

    @Size(max = 2)
    @Column(name = "status", length = 2)
    private String status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getvVchno() {
        return vVchno;
    }

    public GstVoplUpload vVchno(String vVchno) {
        this.vVchno = vVchno;
        return this;
    }

    public void setvVchno(String vVchno) {
        this.vVchno = vVchno;
    }

    public Instant getvVchdate() {
        return vVchdate;
    }

    public GstVoplUpload vVchdate(Instant vVchdate) {
        this.vVchdate = vVchdate;
        return this;
    }

    public void setvVchdate(Instant vVchdate) {
        this.vVchdate = vVchdate;
    }

    public String getvGstin() {
        return vGstin;
    }

    public GstVoplUpload vGstin(String vGstin) {
        this.vGstin = vGstin;
        return this;
    }

    public void setvGstin(String vGstin) {
        this.vGstin = vGstin;
    }

    public String getvSupplierCode() {
        return vSupplierCode;
    }

    public GstVoplUpload vSupplierCode(String vSupplierCode) {
        this.vSupplierCode = vSupplierCode;
        return this;
    }

    public void setvSupplierCode(String vSupplierCode) {
        this.vSupplierCode = vSupplierCode;
    }

    public String getvSupplierName() {
        return vSupplierName;
    }

    public GstVoplUpload vSupplierName(String vSupplierName) {
        this.vSupplierName = vSupplierName;
        return this;
    }

    public void setvSupplierName(String vSupplierName) {
        this.vSupplierName = vSupplierName;
    }

    public String getvInvoiceno() {
        return vInvoiceno;
    }

    public GstVoplUpload vInvoiceno(String vInvoiceno) {
        this.vInvoiceno = vInvoiceno;
        return this;
    }

    public void setvInvoiceno(String vInvoiceno) {
        this.vInvoiceno = vInvoiceno;
    }

    public Instant getvInvoicedate() {
        return vInvoicedate;
    }

    public GstVoplUpload vInvoicedate(Instant vInvoicedate) {
        this.vInvoicedate = vInvoicedate;
        return this;
    }

    public void setvInvoicedate(Instant vInvoicedate) {
        this.vInvoicedate = vInvoicedate;
    }

    public Double getvInvamt() {
        return vInvamt;
    }

    public GstVoplUpload vInvamt(Double vInvamt) {
        this.vInvamt = vInvamt;
        return this;
    }

    public void setvInvamt(Double vInvamt) {
        this.vInvamt = vInvamt;
    }

    public Double getvInvnet() {
        return vInvnet;
    }

    public GstVoplUpload vInvnet(Double vInvnet) {
        this.vInvnet = vInvnet;
        return this;
    }

    public void setvInvnet(Double vInvnet) {
        this.vInvnet = vInvnet;
    }

    public Double getvCgst() {
        return vCgst;
    }

    public GstVoplUpload vCgst(Double vCgst) {
        this.vCgst = vCgst;
        return this;
    }

    public void setvCgst(Double vCgst) {
        this.vCgst = vCgst;
    }

    public Double getvSgst() {
        return vSgst;
    }

    public GstVoplUpload vSgst(Double vSgst) {
        this.vSgst = vSgst;
        return this;
    }

    public void setvSgst(Double vSgst) {
        this.vSgst = vSgst;
    }

    public Double getvIgst() {
        return vIgst;
    }

    public GstVoplUpload vIgst(Double vIgst) {
        this.vIgst = vIgst;
        return this;
    }

    public void setvIgst(Double vIgst) {
        this.vIgst = vIgst;
    }

    public Instant getUploadDate() {
        return uploadDate;
    }

    public GstVoplUpload uploadDate(Instant uploadDate) {
        this.uploadDate = uploadDate;
        return this;
    }

    public void setUploadDate(Instant uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Instant getConfirmDate() {
        return confirmDate;
    }

    public GstVoplUpload confirmDate(Instant confirmDate) {
        this.confirmDate = confirmDate;
        return this;
    }

    public void setConfirmDate(Instant confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getStatus() {
        return status;
    }

    public GstVoplUpload status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GstVoplUpload)) {
            return false;
        }
        return id != null && id.equals(((GstVoplUpload) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GstVoplUpload{" +
            "id=" + getId() +
            ", vVchno='" + getvVchno() + "'" +
            ", vVchdate='" + getvVchdate() + "'" +
            ", vGstin='" + getvGstin() + "'" +
            ", vSupplierCode='" + getvSupplierCode() + "'" +
            ", vSupplierName='" + getvSupplierName() + "'" +
            ", vInvoiceno='" + getvInvoiceno() + "'" +
            ", vInvoicedate='" + getvInvoicedate() + "'" +
            ", vInvamt=" + getvInvamt() +
            ", vInvnet=" + getvInvnet() +
            ", vCgst=" + getvCgst() +
            ", vSgst=" + getvSgst() +
            ", vIgst=" + getvIgst() +
            ", uploadDate='" + getUploadDate() + "'" +
            ", confirmDate='" + getConfirmDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}

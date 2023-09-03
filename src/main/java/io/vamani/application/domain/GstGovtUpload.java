package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

/**
 * A GstGovtUpload.
 */
@Entity
@Table(name = "gst_govt_upload")
public class GstGovtUpload implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="gstGovtUploadSeq", sequenceName="gst_govt_upload_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="gstGovtUploadSeq")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "g_gstin", length = 20, nullable = false)
    private String gGstin;

    @NotNull
    @Size(max = 200)
    @Column(name = "g_supplier", length = 200, nullable = false)
    private String gSupplier;

    @NotNull
    @Size(max = 20)
    @Column(name = "g_invno", length = 20, nullable = false)
    private String gInvno;

    @Column(name = "g_invdate")
    private Instant gInvdate;

    @NotNull
    @Size(max = 5)
    @Column(name = "g_invtype", length = 5, nullable = false)
    private String gInvtype;

    @NotNull
    @Size(max = 40)
    @Column(name = "g_state", length = 40, nullable = false)
    private String gState;

    @NotNull
    @Size(max = 5)
    @Column(name = "g_reverse", length = 5, nullable = false)
    private String gReverse;

    @Column(name = "g_invamt")
    private Double gInvamt;

    @Column(name = "g_invnet")
    private Double gInvnet;

    @Column(name = "g_rate")
    private Double gRate;

    @Column(name = "g_cgst")
    private Double gCgst;

    @Column(name = "g_sgst")
    private Double gSgst;

    @Column(name = "g_igst")
    private Double gIgst;

    @Column(name = "g_cess")
    private Double gCess;

    @Column(name = "g_month")
    private LocalDate gMonth;

    @NotNull
    @Size(max = 40)
    @Column(name = "g_location", length = 40, nullable = false)
    private String gLocation;

    @NotNull
    @Size(max = 20)
    @Column(name = "g_srlno", length = 20, nullable = false)
    private String gSrlno;

    @Size(max = 5)
    @Column(name = "g_status", length = 5)
    private String gStatus;

    @Column(name = "g_confirmdate")
    private Instant gConfirmdate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getgGstin() {
        return gGstin;
    }

    public GstGovtUpload gGstin(String gGstin) {
        this.gGstin = gGstin;
        return this;
    }

    public void setgGstin(String gGstin) {
        this.gGstin = gGstin;
    }

    public String getgSupplier() {
        return gSupplier;
    }

    public GstGovtUpload gSupplier(String gSupplier) {
        this.gSupplier = gSupplier;
        return this;
    }

    public void setgSupplier(String gSupplier) {
        this.gSupplier = gSupplier;
    }

    public String getgInvno() {
        return gInvno;
    }

    public GstGovtUpload gInvno(String gInvno) {
        this.gInvno = gInvno;
        return this;
    }

    public void setgInvno(String gInvno) {
        this.gInvno = gInvno;
    }

    public Instant getgInvdate() {
        return gInvdate;
    }

    public GstGovtUpload gInvdate(Instant gInvdate) {
        this.gInvdate = gInvdate;
        return this;
    }

    public void setgInvdate(Instant gInvdate) {
        this.gInvdate = gInvdate;
    }

    public String getgInvtype() {
        return gInvtype;
    }

    public GstGovtUpload gInvtype(String gInvtype) {
        this.gInvtype = gInvtype;
        return this;
    }

    public void setgInvtype(String gInvtype) {
        this.gInvtype = gInvtype;
    }

    public String getgState() {
        return gState;
    }

    public GstGovtUpload gState(String gState) {
        this.gState = gState;
        return this;
    }

    public void setgState(String gState) {
        this.gState = gState;
    }

    public String getgReverse() {
        return gReverse;
    }

    public GstGovtUpload gReverse(String gReverse) {
        this.gReverse = gReverse;
        return this;
    }

    public void setgReverse(String gReverse) {
        this.gReverse = gReverse;
    }

    public Double getgInvamt() {
        return gInvamt;
    }

    public GstGovtUpload gInvamt(Double gInvamt) {
        this.gInvamt = gInvamt;
        return this;
    }

    public void setgInvamt(Double gInvamt) {
        this.gInvamt = gInvamt;
    }

    public Double getgInvnet() {
        return gInvnet;
    }

    public GstGovtUpload gInvnet(Double gInvnet) {
        this.gInvnet = gInvnet;
        return this;
    }

    public void setgInvnet(Double gInvnet) {
        this.gInvnet = gInvnet;
    }

    public Double getgRate() {
        return gRate;
    }

    public GstGovtUpload gRate(Double gRate) {
        this.gRate = gRate;
        return this;
    }

    public void setgRate(Double gRate) {
        this.gRate = gRate;
    }

    public Double getgCgst() {
        return gCgst;
    }

    public GstGovtUpload gCgst(Double gCgst) {
        this.gCgst = gCgst;
        return this;
    }

    public void setgCgst(Double gCgst) {
        this.gCgst = gCgst;
    }

    public Double getgSgst() {
        return gSgst;
    }

    public GstGovtUpload gSgst(Double gSgst) {
        this.gSgst = gSgst;
        return this;
    }

    public void setgSgst(Double gSgst) {
        this.gSgst = gSgst;
    }

    public Double getgIgst() {
        return gIgst;
    }

    public GstGovtUpload gIgst(Double gIgst) {
        this.gIgst = gIgst;
        return this;
    }

    public void setgIgst(Double gIgst) {
        this.gIgst = gIgst;
    }

    public Double getgCess() {
        return gCess;
    }

    public GstGovtUpload gCess(Double gCess) {
        this.gCess = gCess;
        return this;
    }

    public void setgCess(Double gCess) {
        this.gCess = gCess;
    }

    public LocalDate getgMonth() {
        return gMonth;
    }

    public GstGovtUpload gMonth(LocalDate gMonth) {
        this.gMonth = gMonth;
        return this;
    }

    public void setgMonth(LocalDate gMonth) {
        this.gMonth = gMonth;
    }

    public String getgLocation() {
        return gLocation;
    }

    public GstGovtUpload gLocation(String gLocation) {
        this.gLocation = gLocation;
        return this;
    }

    public void setgLocation(String gLocation) {
        this.gLocation = gLocation;
    }

    public String getgSrlno() {
        return gSrlno;
    }

    public GstGovtUpload gSrlno(String gSrlno) {
        this.gSrlno = gSrlno;
        return this;
    }

    public void setgSrlno(String gSrlno) {
        this.gSrlno = gSrlno;
    }

    public String getgStatus() {
        return gStatus;
    }

    public GstGovtUpload gStatus(String gStatus) {
        this.gStatus = gStatus;
        return this;
    }

    public void setgStatus(String gStatus) {
        this.gStatus = gStatus;
    }

    public Instant getgConfirmdate() {
        return gConfirmdate;
    }

    public GstGovtUpload gConfirmdate(Instant gConfirmdate) {
        this.gConfirmdate = gConfirmdate;
        return this;
    }

    public void setgConfirmdate(Instant gConfirmdate) {
        this.gConfirmdate = gConfirmdate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GstGovtUpload)) {
            return false;
        }
        return id != null && id.equals(((GstGovtUpload) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "GstGovtUpload{" +
            "id=" + getId() +
            ", gGstin='" + getgGstin() + "'" +
            ", gSupplier='" + getgSupplier() + "'" +
            ", gInvno='" + getgInvno() + "'" +
            ", gInvdate='" + getgInvdate() + "'" +
            ", gInvtype='" + getgInvtype() + "'" +
            ", gState='" + getgState() + "'" +
            ", gReverse='" + getgReverse() + "'" +
            ", gInvamt=" + getgInvamt() +
            ", gInvnet=" + getgInvnet() +
            ", gRate=" + getgRate() +
            ", gCgst=" + getgCgst() +
            ", gSgst=" + getgSgst() +
            ", gIgst=" + getgIgst() +
            ", gCess=" + getgCess() +
            ", gMonth='" + getgMonth() + "'" +
            ", gLocation='" + getgLocation() + "'" +
            ", gSrlno='" + getgSrlno() + "'" +
            ", gStatus='" + getgStatus() + "'" +
            ", gConfirmdate='" + getgConfirmdate() + "'" +
            "}";
    }
}

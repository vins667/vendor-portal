package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.vamani.application.model.OrderpartnertdsBean;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DirectBookingTdsDetails.
 */
@Entity
@Table(name = "direct_booking_tds_details")
public class DirectBookingTdsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="directBookingTdsDetailsSeq", sequenceName="direct_booking_tds_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="directBookingTdsDetailsSeq")
    private Long id;

    @Size(max = 100)
    @Column(name = "tds_desc", length = 100)
    private String tdsDesc;

    @Size(max = 20)
    @Column(name = "tds_code", length = 20)
    private String tdsCode;

    @Size(max = 20)
    @Column(name = "tds_tax_code", length = 20)
    private String tdsTaxCode;

    @Size(max = 20)
    @Column(name = "tds_type_code", length = 20)
    private String tdsTypeCode;

    @Size(max = 100)
    @Column(name = "tds_perc_desc", length = 100)
    private String tdsPercDesc;

    @Column(name = "tds_perc")
    private Double tdsPerc;

    @Column(name = "tds_applicable")
    private Boolean tdsApplicable;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("directBookingTdsDetails")
    @JoinColumn(name = "direct_booking_entry_id")
    private DirectBookingEntry directBookingEntry;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTdsDesc() {
        return tdsDesc;
    }

    public DirectBookingTdsDetails tdsDesc(String tdsDesc) {
        this.tdsDesc = tdsDesc;
        return this;
    }

    public void setTdsDesc(String tdsDesc) {
        this.tdsDesc = tdsDesc;
    }

    public String getTdsCode() {
        return tdsCode;
    }

    public DirectBookingTdsDetails tdsCode(String tdsCode) {
        this.tdsCode = tdsCode;
        return this;
    }

    public void setTdsCode(String tdsCode) {
        this.tdsCode = tdsCode;
    }

    public String getTdsTaxCode() {
        return tdsTaxCode;
    }

    public DirectBookingTdsDetails tdsTaxCode(String tdsTaxCode) {
        this.tdsTaxCode = tdsTaxCode;
        return this;
    }

    public void setTdsTaxCode(String tdsTaxCode) {
        this.tdsTaxCode = tdsTaxCode;
    }

    public String getTdsTypeCode() {
        return tdsTypeCode;
    }

    public DirectBookingTdsDetails tdsTypeCode(String tdsTypeCode) {
        this.tdsTypeCode = tdsTypeCode;
        return this;
    }

    public void setTdsTypeCode(String tdsTypeCode) {
        this.tdsTypeCode = tdsTypeCode;
    }

    public String getTdsPercDesc() {
        return tdsPercDesc;
    }

    public DirectBookingTdsDetails tdsPercDesc(String tdsPercDesc) {
        this.tdsPercDesc = tdsPercDesc;
        return this;
    }

    public void setTdsPercDesc(String tdsPercDesc) {
        this.tdsPercDesc = tdsPercDesc;
    }

    public Double getTdsPerc() {
        return tdsPerc;
    }

    public DirectBookingTdsDetails tdsPerc(Double tdsPerc) {
        this.tdsPerc = tdsPerc;
        return this;
    }

    public void setTdsPerc(Double tdsPerc) {
        this.tdsPerc = tdsPerc;
    }

    public Boolean getTdsApplicable() {
        return tdsApplicable;
    }

    public DirectBookingTdsDetails tdsApplicable(Boolean tdsApplicable) {
        this.tdsApplicable = tdsApplicable;
        return this;
    }

    public void setTdsApplicable(Boolean tdsApplicable) {
        this.tdsApplicable = tdsApplicable;
    }

    public DirectBookingEntry getDirectBookingEntry() {
        return directBookingEntry;
    }

    public DirectBookingTdsDetails directBookingEntry(DirectBookingEntry directBookingEntry) {
        this.directBookingEntry = directBookingEntry;
        return this;
    }

    public void setDirectBookingEntry(DirectBookingEntry directBookingEntry) {
        this.directBookingEntry = directBookingEntry;
    }

    public DirectBookingTdsDetails() {
    }

    public DirectBookingTdsDetails(OrderpartnertdsBean orderpartnertdsBean) {
        this.id = orderpartnertdsBean.getParentId();
        this.tdsDesc = orderpartnertdsBean.getLongdescription();
        this.tdsTypeCode = orderpartnertdsBean.getId().getTdstypecode();
        this.tdsCode = orderpartnertdsBean.getId().getTdscode();
        this.tdsTaxCode = orderpartnertdsBean.getId().getTdsitaxcode();
        this.tdsPerc = orderpartnertdsBean.getValue().doubleValue();
        this.tdsApplicable = orderpartnertdsBean.getTdsApplicable();
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DirectBookingTdsDetails)) {
            return false;
        }
        return id != null && id.equals(((DirectBookingTdsDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DirectBookingTdsDetails{" +
            "id=" + getId() +
            ", tdsDesc='" + getTdsDesc() + "'" +
            ", tdsCode='" + getTdsCode() + "'" +
            ", tdsTaxCode='" + getTdsTaxCode() + "'" +
            ", tdsPercDesc='" + getTdsPercDesc() + "'" +
            ", tdsPerc=" + getTdsPerc() +
            ", tdsApplicable='" + getTdsApplicable() + "'" +
            "}";
    }
}

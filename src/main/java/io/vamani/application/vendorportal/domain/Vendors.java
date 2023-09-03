package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Vendors.
 */
@Entity
@Table(name = "vendors")
public class Vendors implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendorsSeq", sequenceName="vendors_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorsSeq")
    private Long id;

    @Size(max = 45)
    @Column(name = "vendor_code", length = 45)
    private String vendorCode;

    @Size(max = 45)
    @Column(name = "vendor_name", length = 45)
    private String vendorName;

    @Size(max = 45)
    @Column(name = "vendor_short_name", length = 45)
    private String vendorShortName;

    @Column(name = "approval_status", length = 1)
    private String approvalStatus;

    @Column(name = "requested_by", length = 50)
    private String requestedBy;

    @Column(name = "requested_date")
    private Instant requestedDate;

    @Column(name = "approved_by", length = 50)
    private String approvedBy;

    @Column(name = "approved_date")
    private Instant approvedDate;

    @Column(name = "email_domain")
    private String emailDomain;

    @Column(name = "is_approved")
    private boolean approved = false;

    @Column(name = "delivery_term_master_id")
    private Long deliveryTermMasterId;

    @Column(name = "pay_term_master_id")
    private Long payTermMasterId;

    @Column(name = "shipment_term_master_id")
    private Long shipmentTermMasterId;

    @Column(name = "currency_master_id")
    private Long currencyMasterId;

    @Column(name = "order_allowed")
    private Boolean orderAllowed = false;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public Vendors vendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
        return this;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorName() {
        return vendorName;
    }

    public Vendors vendorName(String vendorName) {
        this.vendorName = vendorName;
        return this;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Instant getRequestedDate() {
        return requestedDate;
    }

    public void setRequestedDate(Instant requestedDate) {
        this.requestedDate = requestedDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }

    public boolean isApproved() {
        return approved;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getVendorShortName() {
        return vendorShortName;
    }

    public void setVendorShortName(String vendorShortName) {
        this.vendorShortName = vendorShortName;
    }

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }

    public Long getDeliveryTermMasterId() {
        return deliveryTermMasterId;
    }

    public void setDeliveryTermMasterId(Long deliveryTermMasterId) {
        this.deliveryTermMasterId = deliveryTermMasterId;
    }

    public Long getPayTermMasterId() {
        return payTermMasterId;
    }

    public void setPayTermMasterId(Long payTermMasterId) {
        this.payTermMasterId = payTermMasterId;
    }

    public Long getShipmentTermMasterId() {
        return shipmentTermMasterId;
    }

    public void setShipmentTermMasterId(Long shipmentTermMasterId) {
        this.shipmentTermMasterId = shipmentTermMasterId;
    }

    public Long getCurrencyMasterId() {
        return currencyMasterId;
    }

    public void setCurrencyMasterId(Long currencyMasterId) {
        this.currencyMasterId = currencyMasterId;
    }

    public Boolean getOrderAllowed() {
        return orderAllowed;
    }

    public void setOrderAllowed(Boolean orderAllowed) {
        this.orderAllowed = orderAllowed != null ? orderAllowed : false;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vendors vendors = (Vendors) o;
        if (vendors.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendors.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Vendors{" +
            "id=" + getId() +
            ", vendorCode='" + getVendorCode() + "'" +
            ", vendorName='" + getVendorName() + "'" +
            "}";
    }
}

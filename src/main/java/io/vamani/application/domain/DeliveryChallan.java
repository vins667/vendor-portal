package io.vamani.application.domain;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DeliveryChallan.
 */
@Entity
@Table(name = "delivery_challan")
public class DeliveryChallan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="deliveryChallanSeq", sequenceName="delivery_challan_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="deliveryChallanSeq")
    private Long id;

    @NotNull
    @Size(max = 4)
    @Column(name = "fact_code", length = 4, nullable = false)
    private String factCode;

    @NotNull
    @Size(max = 100)
    @Column(name = "fact_description", length = 100, nullable = false)
    private String factDescription;

    @Size(max = 100)
    @Column(name = "f_address_line_1", length = 100)
    private String fAddressLine1;

    @Size(max = 100)
    @Column(name = "f_address_line_2", length = 100)
    private String fAddressLine2;

    @Size(max = 100)
    @Column(name = "f_address_line_3", length = 100)
    private String fAddressLine3;

    @Size(max = 100)
    @Column(name = "f_address_line_4", length = 100)
    private String fAddressLine4;

    @Size(max = 100)
    @Column(name = "f_address_line_5", length = 100)
    private String fAddressLine5;

    @Size(max = 20)
    @Column(name = "postal_code", length = 20)
    private String postalCode;

    @Size(max = 100)
    @Column(name = "town", length = 100)
    private String town;

    @Size(max = 100)
    @Column(name = "district", length = 100)
    private String district;

    @Size(max = 100)
    @Column(name = "state", length = 100)
    private String state;
    
    @Size(max = 3)
    @Column(name = "state_code", length = 3)
    private String stateCode;

    @Size(max = 18)
    @Column(name = "f_gst_number", length = 18)
    private String fGSTNumber;
   
    @Column(name = "suppliercode", length = 500)
    private String suppliercode;
    
    @Size(max = 500)
    @Column(name = "b_legalname_1", length = 500)
    private String bLegalname1;

    @Size(max = 100)
    @Column(name = "b_address_line_1", length = 100)
    private String bAddressLine1;

    @Size(max = 100)
    @Column(name = "b_address_line_2", length = 100)
    private String bAddressLine2;

    @Size(max = 100)
    @Column(name = "b_address_line_3", length = 100)
    private String bAddressLine3;

    @Size(max = 100)
    @Column(name = "b_address_line_4", length = 100)
    private String bAddressLine4;

    @Size(max = 100)
    @Column(name = "b_address_line_5", length = 100)
    private String bAddressLine5;

    @Size(max = 20)
    @Column(name = "b_postal_code", length = 20)
    private String bPostalCode;

    @Size(max = 100)
    @Column(name = "b_town", length = 100)
    private String bTown;

    @Size(max = 100)
    @Column(name = "b_district", length = 100)
    private String bDistrict;

    @Size(max = 100)
    @Column(name = "b_state", length = 100)
    private String bState;

    @Size(max = 3)
    @Column(name = "b_state_code", length = 3)
    private String bStateCode;

    @Size(max = 18)
    @Column(name = "b_gst_number", length = 18)
    private String bGSTNumber;

    @Size(max = 2)
    @Column(name = "challan_type", length = 2)
    private String challanType;

    @Column(name = "challan_date")
    private LocalDate challanDate;

    @Size(max = 14)
    @Column(name = "e_way_bill_no", length = 14)
    private String eWayBillNo;

    @Column(name = "e_way_bill_date")
    private LocalDate eWayBillDate;

    @Column(name = "exp_return_date")
    private LocalDate expReturnDate;

    @Column(name = "ac_return_date")
    private LocalDate acReturnDate;

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

    @Size(max = 1)
    @Column(name = "flag", length = 1)
    private String flag;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @Size(max = 50)
    @Column(name = "approved_by", length = 50)
    private String approvedBy;

    @Column(name = "approved_date")
    private Instant approvedDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFactCode() {
        return factCode;
    }

    public DeliveryChallan factCode(String factCode) {
        this.factCode = factCode;
        return this;
    }

    public void setFactCode(String factCode) {
        this.factCode = factCode;
    }

    public String getFactDescription() {
        return factDescription;
    }

    public DeliveryChallan factDescription(String factDescription) {
        this.factDescription = factDescription;
        return this;
    }

    public void setFactDescription(String factDescription) {
        this.factDescription = factDescription;
    }

    public String getfAddressLine1() {
        return fAddressLine1;
    }

    public DeliveryChallan fAddressLine1(String fAddressLine1) {
        this.fAddressLine1 = fAddressLine1;
        return this;
    }

    public void setfAddressLine1(String fAddressLine1) {
        this.fAddressLine1 = fAddressLine1;
    }

    public String getfAddressLine2() {
        return fAddressLine2;
    }

    public DeliveryChallan fAddressLine2(String fAddressLine2) {
        this.fAddressLine2 = fAddressLine2;
        return this;
    }

    public void setfAddressLine2(String fAddressLine2) {
        this.fAddressLine2 = fAddressLine2;
    }

    public String getfAddressLine3() {
        return fAddressLine3;
    }

    public DeliveryChallan fAddressLine3(String fAddressLine3) {
        this.fAddressLine3 = fAddressLine3;
        return this;
    }

    public void setfAddressLine3(String fAddressLine3) {
        this.fAddressLine3 = fAddressLine3;
    }

    public String getfAddressLine4() {
        return fAddressLine4;
    }

    public DeliveryChallan fAddressLine4(String fAddressLine4) {
        this.fAddressLine4 = fAddressLine4;
        return this;
    }

    public void setfAddressLine4(String fAddressLine4) {
        this.fAddressLine4 = fAddressLine4;
    }

    public String getfAddressLine5() {
        return fAddressLine5;
    }

    public DeliveryChallan fAddressLine5(String fAddressLine5) {
        this.fAddressLine5 = fAddressLine5;
        return this;
    }

    public void setfAddressLine5(String fAddressLine5) {
        this.fAddressLine5 = fAddressLine5;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public DeliveryChallan postalCode(String postalCode) {
        this.postalCode = postalCode;
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public DeliveryChallan town(String town) {
        this.town = town;
        return this;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getDistrict() {
        return district;
    }

    public DeliveryChallan district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStateCode() {
        return stateCode;
    }

    public DeliveryChallan stateCode(String stateCode) {
        this.stateCode = stateCode;
        return this;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getfGSTNumber() {
        return fGSTNumber;
    }

    public DeliveryChallan fGSTNumber(String fGSTNumber) {
        this.fGSTNumber = fGSTNumber;
        return this;
    }

    public void setfGSTNumber(String fGSTNumber) {
        this.fGSTNumber = fGSTNumber;
    }


    public String getSuppliercode() {
		return suppliercode;
	}

	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}

	public String getbLegalname1() {
        return bLegalname1;
    }

    public DeliveryChallan bLegalname1(String bLegalname1) {
        this.bLegalname1 = bLegalname1;
        return this;
    }

    public void setbLegalname1(String bLegalname1) {
        this.bLegalname1 = bLegalname1;
    }

    public String getbAddressLine1() {
        return bAddressLine1;
    }

    public DeliveryChallan bAddressLine1(String bAddressLine1) {
        this.bAddressLine1 = bAddressLine1;
        return this;
    }

    public void setbAddressLine1(String bAddressLine1) {
        this.bAddressLine1 = bAddressLine1;
    }

    public String getbAddressLine2() {
        return bAddressLine2;
    }

    public DeliveryChallan bAddressLine2(String bAddressLine2) {
        this.bAddressLine2 = bAddressLine2;
        return this;
    }

    public void setbAddressLine2(String bAddressLine2) {
        this.bAddressLine2 = bAddressLine2;
    }

    public String getbAddressLine3() {
        return bAddressLine3;
    }

    public DeliveryChallan bAddressLine3(String bAddressLine3) {
        this.bAddressLine3 = bAddressLine3;
        return this;
    }

    public void setbAddressLine3(String bAddressLine3) {
        this.bAddressLine3 = bAddressLine3;
    }

    public String getbAddressLine4() {
        return bAddressLine4;
    }

    public DeliveryChallan bAddressLine4(String bAddressLine4) {
        this.bAddressLine4 = bAddressLine4;
        return this;
    }

    public void setbAddressLine4(String bAddressLine4) {
        this.bAddressLine4 = bAddressLine4;
    }

    public String getbAddressLine5() {
        return bAddressLine5;
    }

    public DeliveryChallan bAddressLine5(String bAddressLine5) {
        this.bAddressLine5 = bAddressLine5;
        return this;
    }

    public void setbAddressLine5(String bAddressLine5) {
        this.bAddressLine5 = bAddressLine5;
    }

    public String getbPostalCode() {
        return bPostalCode;
    }

    public DeliveryChallan bPostalCode(String bPostalCode) {
        this.bPostalCode = bPostalCode;
        return this;
    }

    public void setbPostalCode(String bPostalCode) {
        this.bPostalCode = bPostalCode;
    }

    public String getbTown() {
        return bTown;
    }

    public DeliveryChallan bTown(String bTown) {
        this.bTown = bTown;
        return this;
    }

    public void setbTown(String bTown) {
        this.bTown = bTown;
    }

    public String getbDistrict() {
        return bDistrict;
    }

    public DeliveryChallan bDistrict(String bDistrict) {
        this.bDistrict = bDistrict;
        return this;
    }

    public void setbDistrict(String bDistrict) {
        this.bDistrict = bDistrict;
    }

    public String getbState() {
        return bState;
    }

    public DeliveryChallan bState(String bState) {
        this.bState = bState;
        return this;
    }

    public void setbState(String bState) {
        this.bState = bState;
    }

    public String getbStateCode() {
        return bStateCode;
    }

    public DeliveryChallan bStateCode(String bStateCode) {
        this.bStateCode = bStateCode;
        return this;
    }

    public void setbStateCode(String bStateCode) {
        this.bStateCode = bStateCode;
    }

    public String getState() {
        return state;
    }

    public DeliveryChallan state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getbGSTNumber() {
        return bGSTNumber;
    }

    public DeliveryChallan bGSTNumber(String bGSTNumber) {
        this.bGSTNumber = bGSTNumber;
        return this;
    }

    public void setbGSTNumber(String bGSTNumber) {
        this.bGSTNumber = bGSTNumber;
    }

    public String getChallanType() {
        return challanType;
    }

    public DeliveryChallan challanType(String challanType) {
        this.challanType = challanType;
        return this;
    }

    public void setChallanType(String challanType) {
        this.challanType = challanType;
    }

    public LocalDate getChallanDate() {
        return challanDate;
    }

    public DeliveryChallan challanDate(LocalDate challanDate) {
        this.challanDate = challanDate;
        return this;
    }

    public void setChallanDate(LocalDate challanDate) {
        this.challanDate = challanDate;
    }

    public String geteWayBillNo() {
        return eWayBillNo;
    }

    public DeliveryChallan eWayBillNo(String eWayBillNo) {
        this.eWayBillNo = eWayBillNo;
        return this;
    }

    public void seteWayBillNo(String eWayBillNo) {
        this.eWayBillNo = eWayBillNo;
    }

    public LocalDate geteWayBillDate() {
        return eWayBillDate;
    }

    public DeliveryChallan eWayBillDate(LocalDate eWayBillDate) {
        this.eWayBillDate = eWayBillDate;
        return this;
    }

    public void seteWayBillDate(LocalDate eWayBillDate) {
        this.eWayBillDate = eWayBillDate;
    }

    public LocalDate getExpReturnDate() {
        return expReturnDate;
    }

    public DeliveryChallan expReturnDate(LocalDate expReturnDate) {
        this.expReturnDate = expReturnDate;
        return this;
    }

    public void setExpReturnDate(LocalDate expReturnDate) {
        this.expReturnDate = expReturnDate;
    }

    public LocalDate getAcReturnDate() {
        return acReturnDate;
    }

    public DeliveryChallan acReturnDate(LocalDate acReturnDate) {
        this.acReturnDate = acReturnDate;
        return this;
    }

    public void setAcReturnDate(LocalDate acReturnDate) {
        this.acReturnDate = acReturnDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public DeliveryChallan remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFlag() {
        return flag;
    }

    public DeliveryChallan flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public DeliveryChallan createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public DeliveryChallan createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public DeliveryChallan lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public DeliveryChallan lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public DeliveryChallan approvedBy(String approvedBy) {
        this.approvedBy = approvedBy;
        return this;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public DeliveryChallan approvedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
        return this;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
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
        DeliveryChallan deliveryChallan = (DeliveryChallan) o;
        if (deliveryChallan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), deliveryChallan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DeliveryChallan{" +
            "id=" + getId() +
            ", factCode='" + getFactCode() + "'" +
            ", factDescription='" + getFactDescription() + "'" +
            ", fAddressLine1='" + getfAddressLine1() + "'" +
            ", fAddressLine2='" + getfAddressLine2() + "'" +
            ", fAddressLine3='" + getfAddressLine3() + "'" +
            ", fAddressLine4='" + getfAddressLine4() + "'" +
            ", fAddressLine5='" + getfAddressLine5() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", town='" + getTown() + "'" +
            ", district='" + getDistrict() + "'" +
            ", stateCode='" + getStateCode() + "'" +
            ", fGSTNumber='" + getfGSTNumber() + "'" +
            ", bLegalname1='" + getbLegalname1() + "'" +
            ", bAddressLine1='" + getbAddressLine1() + "'" +
            ", bAddressLine2='" + getbAddressLine2() + "'" +
            ", bAddressLine3='" + getbAddressLine3() + "'" +
            ", bAddressLine4='" + getbAddressLine4() + "'" +
            ", bAddressLine5='" + getbAddressLine5() + "'" +
            ", bPostalCode='" + getbPostalCode() + "'" +
            ", bTown='" + getbTown() + "'" +
            ", bDistrict='" + getbDistrict() + "'" +
            ", bState='" + getbState() + "'" +
            ", bStateCode='" + getbStateCode() + "'" +
            ", state='" + getState() + "'" +
            ", bGSTNumber='" + getbGSTNumber() + "'" +
            ", challanType='" + getChallanType() + "'" +
            ", challanDate='" + getChallanDate() + "'" +
            ", eWayBillNo='" + geteWayBillNo() + "'" +
            ", eWayBillDate='" + geteWayBillDate() + "'" +
            ", expReturnDate='" + getExpReturnDate() + "'" +
            ", acReturnDate='" + getAcReturnDate() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", approvedBy='" + getApprovedBy() + "'" +
            ", approvedDate='" + getApprovedDate() + "'" +
            "}";
    }
}

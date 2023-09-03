package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A VendorAdditionalInfo.
 */
@Entity
@Table(name = "vendor_additional_info")
public class VendorAdditionalInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendorAdditionalInfoSeq", sequenceName="vendor_additional_info_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorAdditionalInfoSeq")
    private Long id;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Size(max = 45)
    @Column(name = "esi_code", length = 45)
    private String esiCode;

    @Size(max = 45)
    @Column(name = "pf_code", length = 45)
    private String pfCode;

    @NotNull
    @Column(name = "is_ssi_unit", nullable = false)
    private Boolean isSsiUnit = false;

    @Size(max = 100)
    @Column(name = "prop_partner_director", length = 100)
    private String propPartnerDirector;

    @Size(max = 45)
    @Column(name = "prop_ptrn_dtr_tele_no", length = 45)
    private String propPtrnDtrTeleNo;

    @Size(max = 100)
    @Column(name = "associate_sister_concern", length = 100)
    private String associateSisterConcern;

    @Size(max = 100)
    @Column(name = "introduced_by", length = 100)
    private String introducedBy;

    @Size(max = 100)
    @Column(name = "comp_ref", length = 100)
    private String comp_ref;

    @Size(max = 45)
    @Column(name = "product_service_items_of_party", length = 45)
    private String productServiceItemsOfParty;

    @Size(max = 300)
    @Column(name = "manufacturig_unit_address", length = 300)
    private String manufacturigUnitAddress;

    @Size(max = 45)
    @Column(name = "manufacturing_unit_capicity", length = 45)
    private String manufacturingUnitCapicity;

    @Column(name = "number_of_machines")
    private Long numberOfMachines;

    @Column(name = "number_of_employees")
    private Long numberOfEmployees;

    @Size(max = 100)
    @Column(name = "dealing_with_oth_expt_party", length = 100)
    private String dealingWithOthExptParty;

    @Column(name = "turnover_id")
    private Long turnoverId;

    @Size(max = 45)
    @Column(name = "auditor", length = 45)
    private String auditor;

    @Size(max = 300)
    @Column(name = "auditor_address", length = 300)
    private String auditorAddress;

    @Size(max = 100)
    @Column(name = "person_dealing_with_company", length = 100)
    private String personDealingWithCompany;

    @Size(max = 45)
    @Column(name = "department", length = 45)
    private String department;

    @Size(max = 500)
    @Column(name = "remarks", length = 500)
    private String remarks;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public VendorAdditionalInfo vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getEsiCode() {
        return esiCode;
    }

    public VendorAdditionalInfo esiCode(String esiCode) {
        this.esiCode = esiCode;
        return this;
    }

    public void setEsiCode(String esiCode) {
        this.esiCode = esiCode != null ? esiCode.toUpperCase() : "";
    }

    public String getPfCode() {
        return pfCode;
    }

    public VendorAdditionalInfo pfCode(String pfCode) {
        this.pfCode = pfCode;
        return this;
    }

    public void setPfCode(String pfCode) {
        this.pfCode = pfCode != null ? pfCode.toUpperCase() : "";
    }

    public Boolean isIsSsiUnit() {
        return isSsiUnit;
    }

    public Boolean getSsiUnit() {
        return isSsiUnit;
    }

    public void setSsiUnit(Boolean ssiUnit) {
        isSsiUnit = ssiUnit;
    }

    public VendorAdditionalInfo isSsiUnit(Boolean isSsiUnit) {
        this.isSsiUnit = isSsiUnit;
        return this;
    }

    public void setIsSsiUnit(Boolean isSsiUnit) {
        this.isSsiUnit = isSsiUnit;
    }

    public String getPropPartnerDirector() {
        return propPartnerDirector;
    }

    public VendorAdditionalInfo propPartnerDirector(String propPartnerDirector) {
        this.propPartnerDirector = propPartnerDirector;
        return this;
    }

    public void setPropPartnerDirector(String propPartnerDirector) {
        this.propPartnerDirector = propPartnerDirector != null ? propPartnerDirector.toUpperCase() : "";
    }

    public String getPropPtrnDtrTeleNo() {
        return propPtrnDtrTeleNo;
    }

    public VendorAdditionalInfo propPtrnDtrTeleNo(String propPtrnDtrTeleNo) {
        this.propPtrnDtrTeleNo = propPtrnDtrTeleNo;
        return this;
    }

    public void setPropPtrnDtrTeleNo(String propPtrnDtrTeleNo) {
        this.propPtrnDtrTeleNo = propPtrnDtrTeleNo != null ? propPtrnDtrTeleNo.toUpperCase() : "";
    }

    public String getAssociateSisterConcern() {
        return associateSisterConcern;
    }

    public VendorAdditionalInfo associateSisterConcern(String associateSisterConcern) {
        this.associateSisterConcern = associateSisterConcern;
        return this;
    }

    public void setAssociateSisterConcern(String associateSisterConcern) {
        this.associateSisterConcern = associateSisterConcern != null ? associateSisterConcern.toUpperCase() : "";
    }

    public String getIntroducedBy() {
        return introducedBy;
    }

    public VendorAdditionalInfo introducedBy(String introducedBy) {
        this.introducedBy = introducedBy;
        return this;
    }

    public void setIntroducedBy(String introducedBy) {
        this.introducedBy = introducedBy != null ? introducedBy.toUpperCase() : "";
    }

    public String getComp_ref() {
        return comp_ref;
    }

    public VendorAdditionalInfo comp_ref(String comp_ref) {
        this.comp_ref = comp_ref;
        return this;
    }

    public void setComp_ref(String comp_ref) {
        this.comp_ref = comp_ref != null ? comp_ref.toUpperCase() : "";
    }

    public String getProductServiceItemsOfParty() {
        return productServiceItemsOfParty;
    }

    public VendorAdditionalInfo productServiceItemsOfParty(String productServiceItemsOfParty) {
        this.productServiceItemsOfParty = productServiceItemsOfParty;
        return this;
    }

    public void setProductServiceItemsOfParty(String productServiceItemsOfParty) {
        this.productServiceItemsOfParty = productServiceItemsOfParty != null ? productServiceItemsOfParty.toUpperCase() : "";
    }

    public String getManufacturigUnitAddress() {
        return manufacturigUnitAddress;
    }

    public VendorAdditionalInfo manufacturigUnitAddress(String manufacturigUnitAddress) {
        this.manufacturigUnitAddress = manufacturigUnitAddress;
        return this;
    }

    public void setManufacturigUnitAddress(String manufacturigUnitAddress) {
        this.manufacturigUnitAddress = manufacturigUnitAddress != null ? manufacturigUnitAddress.toUpperCase() : "";
    }

    public String getManufacturingUnitCapicity() {
        return manufacturingUnitCapicity;
    }

    public VendorAdditionalInfo manufacturingUnitCapicity(String manufacturingUnitCapicity) {
        this.manufacturingUnitCapicity = manufacturingUnitCapicity;
        return this;
    }

    public void setManufacturingUnitCapicity(String manufacturingUnitCapicity) {
        this.manufacturingUnitCapicity = manufacturingUnitCapicity != null ? manufacturingUnitCapicity.toUpperCase() : "";
    }

    public Long getNumberOfMachines() {
        return numberOfMachines;
    }

    public VendorAdditionalInfo numberOfMachines(Long numberOfMachines) {
        this.numberOfMachines = numberOfMachines;
        return this;
    }

    public void setNumberOfMachines(Long numberOfMachines) {
        this.numberOfMachines = numberOfMachines;
    }

    public Long getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public VendorAdditionalInfo numberOfEmployees(Long numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
        return this;
    }

    public void setNumberOfEmployees(Long numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getDealingWithOthExptParty() {
        return dealingWithOthExptParty;
    }

    public VendorAdditionalInfo dealingWithOthExptParty(String dealingWithOthExptParty) {
        this.dealingWithOthExptParty = dealingWithOthExptParty;
        return this;
    }

    public void setDealingWithOthExptParty(String dealingWithOthExptParty) {
        this.dealingWithOthExptParty = dealingWithOthExptParty != null ? dealingWithOthExptParty.toUpperCase() : "";
    }

    public Long getTurnoverId() {
        return turnoverId;
    }

    public VendorAdditionalInfo turnoverId(Long turnoverId) {
        this.turnoverId = turnoverId;
        return this;
    }

    public void setTurnoverId(Long turnoverId) {
        this.turnoverId = turnoverId;
    }

    public String getAuditor() {
        return auditor;
    }

    public VendorAdditionalInfo auditor(String auditor) {
        this.auditor = auditor;
        return this;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor != null ? auditor.toUpperCase() : "";
    }

    public String getAuditorAddress() {
        return auditorAddress;
    }

    public VendorAdditionalInfo auditorAddress(String auditorAddress) {
        this.auditorAddress = auditorAddress;
        return this;
    }

    public void setAuditorAddress(String auditorAddress) {
        this.auditorAddress = auditorAddress != null ? auditorAddress.toUpperCase() : "";
    }

    public String getPersonDealingWithCompany() {
        return personDealingWithCompany;
    }

    public VendorAdditionalInfo personDealingWithCompany(String personDealingWithCompany) {
        this.personDealingWithCompany = personDealingWithCompany;
        return this;
    }

    public void setPersonDealingWithCompany(String personDealingWithCompany) {
        this.personDealingWithCompany = personDealingWithCompany != null ? personDealingWithCompany.toUpperCase() : "";
    }

    public String getDepartment() {
        return department;
    }

    public VendorAdditionalInfo department(String department) {
        this.department = department;
        return this;
    }

    public void setDepartment(String department) {
        this.department = department != null ? department.toUpperCase() : "";
    }

    public String getRemarks() {
        return remarks;
    }

    public VendorAdditionalInfo remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks != null ? remarks.toUpperCase() : "";
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorAdditionalInfo createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorAdditionalInfo createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorAdditionalInfo lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorAdditionalInfo lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
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
        VendorAdditionalInfo vendorAdditionalInfo = (VendorAdditionalInfo) o;
        if (vendorAdditionalInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorAdditionalInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorAdditionalInfo{" +
            "id=" + getId() +
            ", vendorId=" + getVendorId() +
            ", esiCode='" + getEsiCode() + "'" +
            ", pfCode='" + getPfCode() + "'" +
            ", isSsiUnit='" + isIsSsiUnit() + "'" +
            ", propPartnerDirector='" + getPropPartnerDirector() + "'" +
            ", propPtrnDtrTeleNo='" + getPropPtrnDtrTeleNo() + "'" +
            ", associateSisterConcern='" + getAssociateSisterConcern() + "'" +
            ", introducedBy='" + getIntroducedBy() + "'" +
            ", comp_ref='" + getComp_ref() + "'" +
            ", productServiceItemsOfParty='" + getProductServiceItemsOfParty() + "'" +
            ", manufacturigUnitAddress='" + getManufacturigUnitAddress() + "'" +
            ", manufacturingUnitCapicity='" + getManufacturingUnitCapicity() + "'" +
            ", numberOfMachines=" + getNumberOfMachines() +
            ", numberOfEmployees=" + getNumberOfEmployees() +
            ", dealingWithOthExptParty='" + getDealingWithOthExptParty() + "'" +
            ", turnoverId=" + getTurnoverId() +
            ", auditor='" + getAuditor() + "'" +
            ", auditorAddress='" + getAuditorAddress() + "'" +
            ", personDealingWithCompany='" + getPersonDealingWithCompany() + "'" +
            ", department='" + getDepartment() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}

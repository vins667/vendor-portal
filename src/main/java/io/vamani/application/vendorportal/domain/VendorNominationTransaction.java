package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A VendorNominationTransaction.
 */
@Entity
@Table(name = "vendor_nomination_transaction")
public class VendorNominationTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendorNominationTransSeq", sequenceName="vendor_nomination_trans_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorNominationTransSeq")
    private Long id;

    @Column(name = "vendor_id")
    private Long vendorId;

    @Column(name = "is_nominated")
    private Boolean isNominated;

    @Size(max = 100)
    @Column(name = "nominated_by_buyer", length = 100)
    private String nominatedByBuyer;

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

    @Column(name = "approved_date")
    private Instant approvedDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vendor_nomination_trans_buyer_master",
        joinColumns = @JoinColumn(name = "vendor_nomination_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "buyer_master_id", referencedColumnName = "id"))
    private Set<BuyerMaster> buyerMasters = new HashSet<>();

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

    public VendorNominationTransaction vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Boolean isIsNominated() {
        return isNominated;
    }

    public VendorNominationTransaction isNominated(Boolean isNominated) {
        this.isNominated = isNominated;
        return this;
    }

    public void setIsNominated(Boolean isNominated) {
        this.isNominated = isNominated;
    }

    public String getNominatedByBuyer() {
        return nominatedByBuyer;
    }

    public VendorNominationTransaction nominatedByBuyer(String nominatedByBuyer) {
        this.nominatedByBuyer = nominatedByBuyer;
        return this;
    }

    public void setNominatedByBuyer(String nominatedByBuyer) {
        this.nominatedByBuyer = nominatedByBuyer;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorNominationTransaction createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorNominationTransaction createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorNominationTransaction lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorNominationTransaction lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public VendorNominationTransaction approvedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
        return this;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }

    public Set<BuyerMaster> getBuyerMasters() {
        return buyerMasters;
    }

    public void setBuyerMasters(Set<BuyerMaster> buyerMasters) {
        this.buyerMasters = buyerMasters;
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
        VendorNominationTransaction vendorNominationTransaction = (VendorNominationTransaction) o;
        if (vendorNominationTransaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorNominationTransaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorNominationTransaction{" +
            "id=" + getId() +
            ", vendorId=" + getVendorId() +
            ", isNominated='" + isIsNominated() + "'" +
            ", nominatedByBuyer='" + getNominatedByBuyer() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", approvedDate='" + getApprovedDate() + "'" +
            "}";
    }
}

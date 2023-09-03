package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A VendorNomination.
 */
@Entity
@Table(name = "vendor_nomination")
public class VendorNomination implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendorNominationSeq", sequenceName="vendor_nomination_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorNominationSeq")
    private Long id;

    @NotNull
    @Column(name = "vendor_id", nullable = false)
    private Long vendorId;

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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "vendor_nomination_buyer_master",
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

    public VendorNomination vendorId(Long vendorId) {
        this.vendorId = vendorId;
        return this;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VendorNomination createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VendorNomination createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VendorNomination lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VendorNomination lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Set<BuyerMaster> getBuyerMasters() {
        return buyerMasters;
    }

    public VendorNomination buyerMasters(Set<BuyerMaster> buyerMasters) {
        this.buyerMasters = buyerMasters;
        return this;
    }

    public VendorNomination addBuyerMaster(BuyerMaster buyerMaster) {
        this.buyerMasters.add(buyerMaster);
        return this;
    }

    public VendorNomination removeBuyerMaster(BuyerMaster buyerMaster) {
        this.buyerMasters.remove(buyerMaster);
        return this;
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
        VendorNomination vendorNomination = (VendorNomination) o;
        if (vendorNomination.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorNomination.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorNomination{" +
            "id=" + getId() +
            ", vendorId=" + getVendorId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}

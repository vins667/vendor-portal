package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A MarkerEntryDetails.
 */
@Entity
@Table(name = "marker_entry_details")
public class MarkerEntryDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="markerEntryDetailsSeq", sequenceName="marker_entry_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="markerEntryDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "size_code", length = 20, nullable = false)
    private String sizeCode;

    @Column(name = "size_qty")
    private Integer sizeQty;

    @Column(name = "order_qty")
    private Double orderQty;

    @Size(max = 20)
    @Column(name = "created_by", length = 20)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 20)
    @Column(name = "last_updated_by", length = 20)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("markerEntryDetails")
    @JoinColumn(name = "marker_master_entry_id", referencedColumnName = "id")
    private MarkerMasterEntry markerMasterEntry;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSizeCode() {
        return sizeCode;
    }

    public MarkerEntryDetails sizeCode(String sizeCode) {
        this.sizeCode = sizeCode != null ? sizeCode.trim() : sizeCode;
        return this;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode != null ? sizeCode.trim() : sizeCode;
    }

    public Integer getSizeQty() {
        return sizeQty;
    }

    public MarkerEntryDetails sizeQty(Integer sizeQty) {
        this.sizeQty = sizeQty;
        return this;
    }

    public void setSizeQty(Integer sizeQty) {
        this.sizeQty = sizeQty;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MarkerEntryDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public MarkerEntryDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public MarkerEntryDetails lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public MarkerEntryDetails lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Double getOrderQty() {
        return orderQty;
    }

    public MarkerEntryDetails orderQty(Double orderQty) {
        this.orderQty = orderQty;
        return this;
    }

    public void setOrderQty(Double orderQty) {
        this.orderQty = orderQty;
    }

    public MarkerMasterEntry getMarkerMasterEntry() {
        return markerMasterEntry;
    }

    public MarkerEntryDetails markerMasterEntry(MarkerMasterEntry markerMasterEntry) {
        this.markerMasterEntry = markerMasterEntry;
        return this;
    }

    public void setMarkerMasterEntry(MarkerMasterEntry markerMasterEntry) {
        this.markerMasterEntry = markerMasterEntry;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MarkerEntryDetails)) {
            return false;
        }
        return id != null && id.equals(((MarkerEntryDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MarkerEntryDetails{" +
            "id=" + getId() +
            ", sizeCode='" + getSizeCode() + "'" +
            ", sizeQty=" + getSizeQty() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            ", orderQty=" + getOrderQty() +
            "}";
    }
}

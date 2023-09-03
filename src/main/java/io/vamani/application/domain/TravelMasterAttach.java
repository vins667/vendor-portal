package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A TravelMasterAttach.
 */
@Entity
@Table(name = "travel_master_attach")
public class TravelMasterAttach implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="travelMasterAttachSeq", sequenceName="travel_master_attach_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="travelMasterAttachSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "attach_file", length = 100, nullable = false)
    private String attachFile;

    @NotNull
    @Size(max = 100)
    @Column(name = "attach_display_file", length = 100, nullable = false)
    private String attachDisplayFile;

    @NotNull
    @Size(max = 100)
    @Column(name = "attch_type", length = 100, nullable = false)
    private String attchType;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "travel_application_master_id")
    private TravelApplicationMaster travelApplicationMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public TravelMasterAttach attachFile(String attachFile) {
        this.attachFile = attachFile;
        return this;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getAttachDisplayFile() {
        return attachDisplayFile;
    }

    public TravelMasterAttach attachDisplayFile(String attachDisplayFile) {
        this.attachDisplayFile = attachDisplayFile;
        return this;
    }

    public void setAttachDisplayFile(String attachDisplayFile) {
        this.attachDisplayFile = attachDisplayFile;
    }

    public String getAttchType() {
        return attchType;
    }

    public TravelMasterAttach attchType(String attchType) {
        this.attchType = attchType != null ? attchType.trim().toUpperCase() : "";
        return this;
    }

    public void setAttchType(String attchType) {
        this.attchType = attchType != null ? attchType.trim().toUpperCase() : "";
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TravelMasterAttach createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TravelMasterAttach createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public TravelApplicationMaster getTravelApplicationMaster() {
        return travelApplicationMaster;
    }

    public TravelMasterAttach travelApplicationMaster(TravelApplicationMaster travelApplicationMaster) {
        this.travelApplicationMaster = travelApplicationMaster;
        return this;
    }

    public void setTravelApplicationMaster(TravelApplicationMaster travelApplicationMaster) {
        this.travelApplicationMaster = travelApplicationMaster;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TravelMasterAttach)) {
            return false;
        }
        return id != null && id.equals(((TravelMasterAttach) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TravelMasterAttach{" +
            "id=" + getId() +
            ", attachFile='" + getAttachFile() + "'" +
            ", attachDisplayFile='" + getAttachDisplayFile() + "'" +
            ", attchType='" + getAttchType() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}

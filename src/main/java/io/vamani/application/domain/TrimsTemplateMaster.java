package io.vamani.application.domain;
import javax.persistence.*;
import javax.validation.constraints.*;

import io.vamani.application.vendorportal.domain.TemplateDetails;
import io.vamani.application.vendorportal.domain.TemplateMaster;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A TrimsTemplateMaster.
 */
@Entity
@Table(name = "trims_template_master")
public class TrimsTemplateMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="trimsTemplateMasterSeq", sequenceName="trims_template_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="trimsTemplateMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 5)
    @Column(name = "accessories_code", length = 5, nullable = false)
    private String accessoriesCode;

    @NotNull
    @Size(max = 100)
    @Column(name = "description", length = 100, nullable = false)
    private String description;

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
    
    @OneToMany(mappedBy = "trimsTemplateMaster", fetch = FetchType.EAGER)
    private Set<TrimsTemplateDetails> trimsTemplateDetails = new HashSet<>();
    
    public TrimsTemplateMaster addTemplateDetails(TrimsTemplateDetails trimsTemplateDetails) {
        this.trimsTemplateDetails.add(trimsTemplateDetails);
        trimsTemplateDetails.setTrimsTemplateMaster(this);
        return this;
    }

    public TrimsTemplateMaster removeTemplateDetails(TrimsTemplateDetails trimsTemplateDetails) {
        this.trimsTemplateDetails.remove(trimsTemplateDetails);
        trimsTemplateDetails.setTrimsTemplateMaster(null);
        return this;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessoriesCode() {
        return accessoriesCode;
    }

    public TrimsTemplateMaster accessoriesCode(String accessoriesCode) {
        this.accessoriesCode = accessoriesCode != null ? accessoriesCode.trim().toUpperCase() : "";
        return this;
    }

    public void setAccessoriesCode(String accessoriesCode) {
        this.accessoriesCode = accessoriesCode != null ? accessoriesCode.trim().toUpperCase() : "";
    }

    public String getDescription() {
        return description;
    }

    public TrimsTemplateMaster description(String description) {
        this.description = description != null ? description.trim().toUpperCase() : "";
        return this;
    }

    public void setDescription(String description) {
        this.description = description != null ? description.trim().toUpperCase() : "";
    }

    public String getFlag() {
        return flag;
    }

    public TrimsTemplateMaster flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public TrimsTemplateMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public TrimsTemplateMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public TrimsTemplateMaster lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public TrimsTemplateMaster lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Set<TrimsTemplateDetails> getTrimsTemplateDetails() {
		return trimsTemplateDetails;
	}

	public void setTrimsTemplateDetails(Set<TrimsTemplateDetails> trimsTemplateDetails) {
		this.trimsTemplateDetails = trimsTemplateDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrimsTemplateMaster)) {
            return false;
        }
        return id != null && id.equals(((TrimsTemplateMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TrimsTemplateMaster{" +
            "id=" + getId() +
            ", accessoriesCode='" + getAccessoriesCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}

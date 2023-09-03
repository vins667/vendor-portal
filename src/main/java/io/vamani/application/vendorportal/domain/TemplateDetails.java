package io.vamani.application.vendorportal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A TemplateDetails.
 */
@Entity
@Table(name = "template_details")
public class TemplateDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="templateDetailsSeq", sequenceName="template_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="templateDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "jhi_specification", length = 200, nullable = false)
    private String specification;

    @NotNull
    @Column(name = "required", nullable = false)
    private Boolean required;

    @NotNull
    @Column(name = "show_in_bid", nullable = false)
    private Boolean showInBid;

    @NotNull
    @Size(max = 1)
    @Column(name = "field_type", length = 1, nullable = false)
    private String fieldType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "template_master_id")
    @JsonIgnoreProperties("templateDetails")
    private TemplateMaster templateMaster;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "template_details_id", referencedColumnName = "id")
    private Set<TemplateDetailsBreakup> templateDetailsBreakups = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpecification() {
        return specification;
    }

    public TemplateDetails specification(String specification) {
        this.specification = specification;
        return this;
    }

    public void setSpecification(String specification) {
        this.specification = specification != null ? specification.toUpperCase() : "";
    }

    public Boolean isRequired() {
        return required;
    }

    public TemplateDetails required(Boolean required) {
        this.required = required;
        return this;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public Boolean isShowInBid() {
        return showInBid;
    }

    public TemplateDetails showInBid(Boolean showInBid) {
        this.showInBid = showInBid;
        return this;
    }

    public void setShowInBid(Boolean showInBid) {
        this.showInBid = showInBid;
    }

    public String getFieldType() {
        return fieldType;
    }

    public TemplateDetails fieldType(String fieldType) {
        this.fieldType = fieldType;
        return this;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public TemplateMaster getTemplateMaster() {
        return templateMaster;
    }

    public TemplateDetails templateMaster(TemplateMaster templateMaster) {
        this.templateMaster = templateMaster;
        return this;
    }

    public void setTemplateMaster(TemplateMaster templateMaster) {
        this.templateMaster = templateMaster;
    }

    public Set<TemplateDetailsBreakup> getTemplateDetailsBreakups() {
        return templateDetailsBreakups;
    }

    public void setTemplateDetailsBreakups(Set<TemplateDetailsBreakup> templateDetailsBreakups) {
        this.templateDetailsBreakups = templateDetailsBreakups;
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
        TemplateDetails templateDetails = (TemplateDetails) o;
        if (templateDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), templateDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TemplateDetails{" +
            "id=" + id +
            ", specification='" + specification + '\'' +
            ", required=" + required +
            ", showInBid=" + showInBid +
            ", fieldType='" + fieldType + '\'' +
            ", templateMaster=" + templateMaster +
            '}';
    }
}

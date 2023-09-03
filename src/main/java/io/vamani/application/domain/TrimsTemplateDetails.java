package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A TrimsTemplateDetails.
 */
@Entity
@Table(name = "trims_template_details")
public class TrimsTemplateDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "trimsTemplateDetailsSeq", sequenceName = "trims_template_details_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "trimsTemplateDetailsSeq")
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(name = "specification", length = 100, nullable = false)
	private String specification;

	@Column(name = "required")
	private Boolean required;

	@Column(name = "display")
	private Boolean display;

	@Column(name = "field_type", length = 1)
	private String fieldType;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "trims_template_master_id")
	@JsonIgnoreProperties("trimsTemplateDetails")
	private TrimsTemplateMaster trimsTemplateMaster;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "trim_template_details_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Set<TrimsTemplateDetailsBreakup> trimTemplateDetailsBreakup = new HashSet<>();

	// jhipster-needle-entity-add-field - JHipster will add fields here, do not
	// remove
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSpecification() {
		return specification;
	}

	public TrimsTemplateMaster getTrimsTemplateMaster() {
		return trimsTemplateMaster;
	}

	public void setTrimsTemplateMaster(TrimsTemplateMaster trimsTemplateMaster) {
		this.trimsTemplateMaster = trimsTemplateMaster;
	}

	public Set<TrimsTemplateDetailsBreakup> getTrimTemplateDetailsBreakup() {
		return trimTemplateDetailsBreakup;
	}

	public void setTrimTemplateDetailsBreakup(Set<TrimsTemplateDetailsBreakup> trimTemplateDetailsBreakup) {
		this.trimTemplateDetailsBreakup = trimTemplateDetailsBreakup;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public TrimsTemplateDetails specification(String specification) {
		this.specification = specification;
		return this;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Boolean getRequired() {
		return required;
	}

	public TrimsTemplateDetails required(Boolean required) {
		this.required = required;
		return this;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}


    public void setDisplay(Boolean display) {
        this.display = display;
    }

    public TrimsTemplateDetails display(Boolean display) {
        this.display = display;
        return this;
    }

    public Boolean getDisplay() { return display; }

    public String getFieldType() {
		return fieldType;
	}

	public TrimsTemplateDetails fieldType(String fieldType) {
		this.fieldType = fieldType;
		return this;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	// jhipster-needle-entity-add-getters-setters - JHipster will add getters and
	// setters here, do not remove

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof TrimsTemplateDetails)) {
			return false;
		}
		return id != null && id.equals(((TrimsTemplateDetails) o).id);
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return "TrimsTemplateDetails [id=" + id + ", specification=" + specification + ", required=" + required
				+ ", display=" + display + ", fieldType=" + fieldType + ", trimsTemplateMaster=" + trimsTemplateMaster
				+ ", trimTemplateDetailsBreakup=" + trimTemplateDetailsBreakup + "]";
	}
}

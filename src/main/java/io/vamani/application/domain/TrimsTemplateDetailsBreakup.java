package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A TrimsTemplateDetailsBreakup.
 */
@Entity
@Table(name = "trims_template_details_breakup")
public class TrimsTemplateDetailsBreakup implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private TrimTemplateDetailsBreakupId id;

    @Size(max = 400)
    @Column(name = "description", length = 400)
    private String description;


    public TrimTemplateDetailsBreakupId getId() {
		return id;
	}

	public void setId(TrimTemplateDetailsBreakupId id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDescription() {
        return description;
    }

    public TrimsTemplateDetailsBreakup description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TrimsTemplateDetailsBreakup)) {
            return false;
        }
        return id != null && id.equals(((TrimsTemplateDetailsBreakup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TrimsTemplateDetailsBreakup{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Suggestion.
 */
@Entity
@Table(name = "suggestion")
public class Suggestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="suggestionSeq", sequenceName="suggestion_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="suggestionSeq")
    private Long id;

    @NotNull
    @Size(max = 2000)
    @Column(name = "suggestion_text", length = 2000, nullable = false)
    private String suggestionText;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuggestionText() {
        return suggestionText;
    }

    public Suggestion suggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
        return this;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Suggestion createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Suggestion createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
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
        Suggestion suggestion = (Suggestion) o;
        if (suggestion.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), suggestion.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Suggestion{" +
            "id=" + getId() +
            ", suggestionText='" + getSuggestionText() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}

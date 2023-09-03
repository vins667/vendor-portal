package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A VcutStyleImage.
 */
@Entity
@Table(name = "vcut_style_image")
public class VcutStyleImage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutStyleImageSeq", sequenceName="vcut_style_image_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutStyleImageSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "style", length = 100, nullable = false)
    private String style;

    @NotNull
    @Size(max = 20)
    @Column(name = "front_image", length = 20, nullable = false)
    private String frontImage;

    @NotNull
    @Size(max = 20)
    @Column(name = "back_image", length = 20, nullable = false)
    private String backImage;

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

    public String getStyle() {
        return style;
    }

    public VcutStyleImage style(String style) {
        this.style = style !=null ? style.trim().toUpperCase() : "";
        return this;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getFrontImage() {
        return frontImage;
    }

    public VcutStyleImage frontImage(String frontImage) {
        this.frontImage = frontImage;
        return this;
    }

    public void setFrontImage(String frontImage) {
        this.frontImage = frontImage;
    }

    public String getBackImage() {
        return backImage;
    }

    public VcutStyleImage backImage(String backImage) {
        this.backImage = backImage;
        return this;
    }

    public void setBackImage(String backImage) {
        this.backImage = backImage;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public VcutStyleImage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public VcutStyleImage createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public VcutStyleImage lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public VcutStyleImage lastUpdatedDate(Instant lastUpdatedDate) {
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
        if (!(o instanceof VcutStyleImage)) {
            return false;
        }
        return id != null && id.equals(((VcutStyleImage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VcutStyleImage{" +
            "id=" + getId() +
            ", style='" + getStyle() + "'" +
            ", frontImage='" + getFrontImage() + "'" +
            ", backImage='" + getBackImage() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", lastUpdatedBy='" + getLastUpdatedBy() + "'" +
            ", lastUpdatedDate='" + getLastUpdatedDate() + "'" +
            "}";
    }
}

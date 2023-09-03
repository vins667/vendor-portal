package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MenuAccessMaster.
 */
@Entity
@Table(name = "menu_access_master")
public class MenuAccessMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="menuAccessMasterSeq", sequenceName="menu_access_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="menuAccessMasterSeq")
    private Long id;

    @Size(max = 100)
    @Column(name = "authority_name", length = 100)
    private String authorityName;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @ManyToOne
    @JsonIgnoreProperties("")
    @JoinColumn(name = "menu_master_id")
    private MenuMaster menuMaster;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public MenuAccessMaster authorityName(String authorityName) {
        this.authorityName = authorityName;
        return this;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MenuAccessMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public MenuAccessMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public MenuMaster getMenuMaster() {
        return menuMaster;
    }

    public MenuAccessMaster menuMaster(MenuMaster menuMaster) {
        this.menuMaster = menuMaster;
        return this;
    }

    public void setMenuMaster(MenuMaster menuMaster) {
        this.menuMaster = menuMaster;
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
        MenuAccessMaster menuAccessMaster = (MenuAccessMaster) o;
        if (menuAccessMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuAccessMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuAccessMaster{" +
            "id=" + getId() +
            ", authorityName='" + getAuthorityName() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}

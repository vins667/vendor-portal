package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A VendorMaster.
 */
@Entity
@Table(name = "vendor_master")
public class VendorMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vendorMasterSeq", sequenceName="vendor_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vendorMasterSeq")
    private Long id;

    @Size(max = 15)
    @Column(name = "code", length = 15)
    private String code;

    @Size(max = 200)
    @Column(name = "description", length = 200)
    private String description;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public VendorMaster code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public VendorMaster description(String description) {
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
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        VendorMaster vendorMaster = (VendorMaster) o;
        if (vendorMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), vendorMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VendorMaster{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

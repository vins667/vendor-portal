package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A Policies.
 */
@Entity
@Table(name = "policies")
public class Policies implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="policiesSeq", sequenceName="policies_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="policiesSeq")
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "policy_name", length = 200, nullable = false)
    private String policyName;

    @NotNull
    @Size(max = 100)
    @Column(name = "policy_file", length = 100, nullable = false)
    private String policyFile;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "ordering")
    private Integer ordering;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("")
    @JoinColumn(name = "policies_group_id")
    private PoliciesGroup policiesGroup;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPolicyName() {
        return policyName;
    }

    public Policies policyName(String policyName) {
        this.policyName = policyName;
        return this;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getPolicyFile() {
        return policyFile;
    }

    public Policies policyFile(String policyFile) {
        this.policyFile = policyFile;
        return this;
    }

    public void setPolicyFile(String policyFile) {
        this.policyFile = policyFile;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public Policies createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Policies createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public PoliciesGroup getPoliciesGroup() {
        return policiesGroup;
    }

    public Policies policiesGroup(PoliciesGroup policiesGroup) {
        this.policiesGroup = policiesGroup;
        return this;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public void setPoliciesGroup(PoliciesGroup policiesGroup) {
        this.policiesGroup = policiesGroup;
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
        Policies policies = (Policies) o;
        if (policies.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), policies.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Policies{" +
            "id=" + getId() +
            ", policyName='" + getPolicyName() + "'" +
            ", policyFile='" + getPolicyFile() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}

package io.vamani.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A VcutMainEntryIssueDetails.
 */
@Entity
@Table(name = "vcut_main_entry_issue_details")
public class VcutMainEntryIssueDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutMainEntryIssueDetailsSeq", sequenceName="vcut_main_entry_issue_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutMainEntryIssueDetailsSeq")
    private Long id;

    @Size(max = 100)
    @Column(name = "coordinate_x", length = 100)
    private String coordinateX;

    @Size(max = 100)
    @Column(name = "coordinate_y", length = 100)
    private String coordinateY;

    @Size(max = 1)
    @Column(name = "coordinate_type", length = 1)
    private String coordinateType;

    @NotNull
    @Column(name = "vcut_operation_issue_master_id")
    private Long vcutOperationIssueMasterId;

    @NotNull
    @Column(name = "vcut_main_entry_master_id")
    private Long vcutMainEntryMasterId;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCoordinateX() {
        return coordinateX;
    }

    public VcutMainEntryIssueDetails coordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
        return this;
    }

    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }

    public String getCoordinateY() {
        return coordinateY;
    }

    public VcutMainEntryIssueDetails coordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
        return this;
    }

    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }

    public String getCoordinateType() {
        return coordinateType;
    }

    public void setCoordinateType(String coordinateType) {
        this.coordinateType = coordinateType;
    }

    public Long getVcutOperationIssueMasterId() {
        return vcutOperationIssueMasterId;
    }

    public VcutMainEntryIssueDetails vcutOperationIssueMasterId(Long vcutOperationIssueMasterId) {
        this.vcutOperationIssueMasterId = vcutOperationIssueMasterId;
        return this;
    }

    public void setVcutOperationIssueMasterId(Long vcutOperationIssueMasterId) {
        this.vcutOperationIssueMasterId = vcutOperationIssueMasterId;
    }

    public Long getVcutMainEntryMasterId() {
        return vcutMainEntryMasterId;
    }

    public VcutMainEntryIssueDetails vcutMainEntryMasterId(Long vcutMainEntryMasterId) {
        this.vcutMainEntryMasterId = vcutMainEntryMasterId;
        return this;
    }

    public void setVcutMainEntryMasterId(Long vcutMainEntryMasterId) {
        this.vcutMainEntryMasterId = vcutMainEntryMasterId;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VcutMainEntryIssueDetails)) {
            return false;
        }
        return id != null && id.equals(((VcutMainEntryIssueDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VcutMainEntryIssueDetails{" +
            "id=" + getId() +
            ", coordinateX='" + getCoordinateX() + "'" +
            ", coordinateY='" + getCoordinateY() + "'" +
            "}";
    }
}

package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A CostingProcessMaster.
 */
@Entity
@Table(name = "costing_process_master")
public class CostingProcessMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="costingProcessMasterSeq", sequenceName="costing_process_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="costingProcessMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "processcode", length = 20, nullable = false)
    private String processcode;

    @NotNull
    @Size(max = 100)
    @Column(name = "processdesc", length = 100, nullable = false)
    private String processdesc;

    @Size(max = 60)
    @Column(name = "createdby", length = 60)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;

    @Column(name = "updatedby")
    private String updatedby;

    @Column(name = "updateddate")
    private Instant updateddate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcesscode() {
        return processcode;
    }

    public CostingProcessMaster processcode(String processcode) {
        this.processcode = processcode;
        return this;
    }

    public void setProcesscode(String processcode) {
        this.processcode = processcode;
    }

    public String getProcessdesc() {
        return processdesc;
    }

    public CostingProcessMaster processdesc(String processdesc) {
        this.processdesc = processdesc;
        return this;
    }

    public void setProcessdesc(String processdesc) {
        this.processdesc = processdesc;
    }

    public String getCreatedby() {
        return createdby;
    }

    public CostingProcessMaster createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public CostingProcessMaster createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public CostingProcessMaster updatedby(String updatedby) {
        this.updatedby = updatedby;
        return this;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Instant getUpdateddate() {
        return updateddate;
    }

    public CostingProcessMaster updateddate(Instant updateddate) {
        this.updateddate = updateddate;
        return this;
    }

    public void setUpdateddate(Instant updateddate) {
        this.updateddate = updateddate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CostingProcessMaster)) {
            return false;
        }
        return id != null && id.equals(((CostingProcessMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CostingProcessMaster{" +
            "id=" + getId() +
            ", processcode='" + getProcesscode() + "'" +
            ", processdesc='" + getProcessdesc() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", updatedby='" + getUpdatedby() + "'" +
            ", updateddate='" + getUpdateddate() + "'" +
            "}";
    }
}

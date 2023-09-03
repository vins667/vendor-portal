package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;

/**
 * A CostingValueMaster.
 */
@Entity
@Table(name = "costing_value_master")
public class CostingValueMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="costingValueMasterSeq", sequenceName="costing_value_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="costingValueMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "processname", length = 20, nullable = false)
    private String processname;

    @NotNull
    @Size(max = 10)
    @Column(name = "valuetype", length = 10, nullable = false)
    private String valuetype;

    @NotNull
    @Size(max = 100)
    @Column(name = "value", length = 100, nullable = false)
    private String value;

    @Size(max = 60)
    @Column(name = "createdby", length = 60)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;

    @Size(max = 60)
    @Column(name = "updatedby", length = 60)
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

    public String getProcessname() {
        return processname;
    }

    public CostingValueMaster processname(String processname) {
        this.processname = processname;
        return this;
    }

    public void setProcessname(String processname) {
        this.processname = processname;
    }

    public String getValuetype() {
        return valuetype;
    }

    public CostingValueMaster valuetype(String valuetype) {
        this.valuetype = valuetype;
        return this;
    }

    public void setValuetype(String valuetype) {
        this.valuetype = valuetype;
    }

    public String getValue() {
        return value;
    }

    public CostingValueMaster value(String value) {
        this.value = value;
        return this;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreatedby() {
        return createdby;
    }

    public CostingValueMaster createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public CostingValueMaster createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public CostingValueMaster updatedby(String updatedby) {
        this.updatedby = updatedby;
        return this;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Instant getUpdateddate() {
        return updateddate;
    }

    public CostingValueMaster updateddate(Instant updateddate) {
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
        if (!(o instanceof CostingValueMaster)) {
            return false;
        }
        return id != null && id.equals(((CostingValueMaster) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "CostingValueMaster{" +
            "id=" + getId() +
            ", processname='" + getProcessname() + "'" +
            ", valuetype='" + getValuetype() + "'" +
            ", value='" + getValue() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", updatedby='" + getUpdatedby() + "'" +
            ", updateddate='" + getUpdateddate() + "'" +
            "}";
    }
}

package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WorkerForwardTypeMaster.
 */
@Entity
@Table(name = "worker_forward_type_master")
public class WorkerForwardTypeMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerForwardTypeMasterSeq", sequenceName="worker_forward_type_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerForwardTypeMasterSeq")
    private Long id;

    @Size(max = 10)
    @Column(name = "code", length = 10)
    private String code;

    @Size(max = 100)
    @Column(name = "description", length = 100)
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

    public WorkerForwardTypeMaster code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public WorkerForwardTypeMaster description(String description) {
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
        WorkerForwardTypeMaster workerForwardTypeMaster = (WorkerForwardTypeMaster) o;
        if (workerForwardTypeMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerForwardTypeMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerForwardTypeMaster{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

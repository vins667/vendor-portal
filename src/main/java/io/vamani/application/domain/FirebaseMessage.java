package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A FirebaseMessage.
 */
@Entity
@Table(name = "firebase_message")
public class FirebaseMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="firebaseMessageSeq", sequenceName="firebase_message_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="firebaseMessageSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "jhi_type", length = 100, nullable = false)
    private String type;

    @Size(max = 100)
    @Column(name = "status", length = 100)
    private String status;

    @Size(max = 2000)
    @Column(name = "jhi_body", length = 2000)
    private String body;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 1)
    @Column(name = "success_status", length = 1)
    private String successStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public FirebaseMessage type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public FirebaseMessage status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public FirebaseMessage body(String body) {
        this.body = body;
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public FirebaseMessage createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public FirebaseMessage createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getSuccessStatus() {
        return successStatus;
    }

    public FirebaseMessage successStatus(String successStatus) {
        this.successStatus = successStatus;
        return this;
    }

    public void setSuccessStatus(String successStatus) {
        this.successStatus = successStatus;
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
        FirebaseMessage firebaseMessage = (FirebaseMessage) o;
        if (firebaseMessage.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), firebaseMessage.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FirebaseMessage{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", status='" + getStatus() + "'" +
            ", body='" + getBody() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", successStatus='" + getSuccessStatus() + "'" +
            "}";
    }
}

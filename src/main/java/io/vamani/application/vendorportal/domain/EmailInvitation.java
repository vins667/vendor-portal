package io.vamani.application.vendorportal.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A EmailInvitation.
 */
@Entity
@Table(name = "email_invitation")
public class EmailInvitation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="emailInvitationSeq", sequenceName="email_invitation_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="emailInvitationSeq")
    private Long id;

    @Size(max = 45)
    @Column(name = "email_id", length = 45)
    private String emailId;

    @NotNull
    @Column(name = "registered", nullable = false)
    private Boolean registered;

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

    public String getEmailId() {
        return emailId;
    }

    public EmailInvitation emailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Boolean isRegistered() {
        return registered;
    }

    public EmailInvitation registered(Boolean registered) {
        this.registered = registered;
        return this;
    }

    public void setRegistered(Boolean registered) {
        this.registered = registered;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public EmailInvitation createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public EmailInvitation createdDate(Instant createdDate) {
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
        EmailInvitation emailInvitation = (EmailInvitation) o;
        if (emailInvitation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), emailInvitation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EmailInvitation{" +
            "id=" + getId() +
            ", emailId='" + getEmailId() + "'" +
            ", registered='" + isRegistered() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}

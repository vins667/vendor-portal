package io.vamani.application.vendorportal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * A EmailInvitation.
 */
@Entity
@Table(name = "email_invitation_skip")
public class EmailInvitationSkip implements Serializable {
    @Id
    @Column(name = "email_domain")
    private String emailDomain;

    public String getEmailDomain() {
        return emailDomain;
    }

    public void setEmailDomain(String emailDomain) {
        this.emailDomain = emailDomain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailInvitationSkip that = (EmailInvitationSkip) o;
        return Objects.equals(emailDomain, that.emailDomain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailDomain);
    }

    @Override
    public String toString() {
        return "EmailInvitationSkip{" +
            "emailDomain='" + emailDomain + '\'' +
            '}';
    }
}

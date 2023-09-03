package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * A JobProfileAccess.
 */
@Entity
@Table(name = "job_profile_access")
public class JobProfileAccess {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Size(max = 50)
    @Column(name = "login", length = 50)
    private String login;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobProfileAccess)) return false;
        JobProfileAccess that = (JobProfileAccess) o;
        return Objects.equals(getId(), that.getId()) &&
            Objects.equals(getLogin(), that.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin());
    }

    @Override
    public String toString() {
        return "JobProfileAccess{" +
            "id=" + id +
            ", login='" + login + '\'' +
            '}';
    }
}

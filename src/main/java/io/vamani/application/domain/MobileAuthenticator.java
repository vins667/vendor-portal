package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "mobile_authenticator")
public class MobileAuthenticator implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "jwt_user")
    private String jwtUser;

    @Column(name = "jwt_key")
    private String jwtKey;

    public String getJwtUser() {
        return jwtUser;
    }

    public void setJwtUser(String jwtUser) {
        this.jwtUser = jwtUser;
    }

    public String getJwtKey() {
        return jwtKey;
    }

    public void setJwtKey(String jwtKey) {
        this.jwtKey = jwtKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MobileAuthenticator that = (MobileAuthenticator) o;
        return Objects.equals(jwtUser, that.jwtUser) &&
            Objects.equals(jwtKey, that.jwtKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jwtUser, jwtKey);
    }

    @Override
    public String toString() {
        return "MobileAuthenticator{" +
            "jwtUser='" + jwtUser + '\'' +
            ", jwtKey='" + jwtKey + '\'' +
            '}';
    }
}

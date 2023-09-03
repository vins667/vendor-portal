package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UsergenericgroupId implements Serializable {
    private String usergengrouptypecompanycode;
    private String usergenericgrouptypecode;
    private String code;

    @Column(name = "USERGENGROUPTYPECOMPANYCODE", nullable = false, length = 3)
    public String getUsergengrouptypecompanycode() {
        return usergengrouptypecompanycode;
    }

    public void setUsergengrouptypecompanycode(String usergengrouptypecompanycode) {
        this.usergengrouptypecompanycode = usergengrouptypecompanycode;
    }

    @Column(name = "USERGENERICGROUPTYPECODE", nullable = false, length = 3)
    public String getUsergenericgrouptypecode() {
        return usergenericgrouptypecode;
    }

    public void setUsergenericgrouptypecode(String usergenericgrouptypecode) {
        this.usergenericgrouptypecode = usergenericgrouptypecode;
    }

    @Column(name = "CODE", nullable = false, length = 10)
    public String getCode() {
        return code != null ? code.trim() : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UsergenericgroupId() {
    }

    public UsergenericgroupId(String usergengrouptypecompanycode, String usergenericgrouptypecode, String code) {
        this.usergengrouptypecompanycode = usergengrouptypecompanycode;
        this.usergenericgrouptypecode = usergenericgrouptypecode;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsergenericgroupId that = (UsergenericgroupId) o;
        return Objects.equals(usergengrouptypecompanycode, that.usergengrouptypecompanycode) &&
            Objects.equals(usergenericgrouptypecode, that.usergenericgrouptypecode) &&
            Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usergengrouptypecompanycode, usergenericgrouptypecode, code);
    }

    @Override
    public String toString() {
        return "UsergenericgroupId{" +
            "usergengrouptypecompanycode='" + usergengrouptypecompanycode + '\'' +
            ", usergenericgrouptypecode='" + usergenericgrouptypecode + '\'' +
            ", code='" + code + '\'' +
            '}';
    }
}

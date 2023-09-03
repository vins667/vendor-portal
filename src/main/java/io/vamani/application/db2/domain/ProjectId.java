package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProjectId implements Serializable {
    private String companycode;
    private String code;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "CODE", nullable = false, length = 20)
    @Id
    public String getCode() {
        return code != null ? code.trim() : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ProjectId() {
    }

    public ProjectId(String companycode, String code) {
        this.companycode = companycode;
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectId projectId = (ProjectId) o;
        return Objects.equals(companycode, projectId.companycode) && Objects.equals(code, projectId.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, code);
    }
}

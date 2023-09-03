package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class ManpowerTypeMappingId implements Serializable {
    @Column(name = "manpower_type", length = 50, nullable = false)
    private String manpowerType;

    @Column(name = "dept_code", length = 20, nullable = false)
    private String deptCode;

    public String getManpowerType() {
        return manpowerType;
    }

    public void setManpowerType(String manpowerType) {
        this.manpowerType = manpowerType;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ManpowerTypeMappingId that = (ManpowerTypeMappingId) o;
        return Objects.equals(manpowerType, that.manpowerType) && Objects.equals(deptCode, that.deptCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(manpowerType, deptCode);
    }
}

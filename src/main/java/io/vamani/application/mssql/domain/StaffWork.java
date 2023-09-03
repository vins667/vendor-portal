package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "staff_work_view")
public class StaffWork implements Serializable {
    @Id
    @Column(name = "sw_code")
    private Long swCode;

    @Column(name = "sw_desc")
    private String swDesc;

    @Override
    public String toString() {
        return "StaffWork{" +
            "swCode=" + swCode +
            ", swDesc='" + swDesc + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StaffWork staffWork = (StaffWork) o;
        return Objects.equals(swCode, staffWork.swCode) &&
            Objects.equals(swDesc, staffWork.swDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(swCode, swDesc);
    }

    public Long getSwCode() {
        return swCode;
    }

    public void setSwCode(Long swCode) {
        this.swCode = swCode;
    }

    public String getSwDesc() {
        return swDesc;
    }

    public void setSwDesc(String swDesc) {
        this.swDesc = swDesc;
    }
}

package io.vamani.application.mssql.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "subcomp_view")
public class Subcomp {
    @Id
    @Column(name = "sub_code", nullable = true)
    private Long subCode;

    @Column(name = "sub_sname")
    private String subSname;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public Long getSubCode() {
        return subCode;
    }

    public void setSubCode(Long subCode) {
        this.subCode = subCode;
    }

    public String getSubSname() {
        return subSname;
    }

    public void setSubSname(String subSname) {
        this.subSname = subSname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subcomp subcomp = (Subcomp) o;
        return Objects.equals(subCode, subcomp.subCode) &&
            Objects.equals(subSname, subcomp.subSname) &&
            Objects.equals(name, subcomp.name) &&
            Objects.equals(address, subcomp.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCode, subSname, name, address);
    }
}

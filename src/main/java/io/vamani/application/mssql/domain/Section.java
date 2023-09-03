package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "section_view")
public class Section implements Serializable {
    @Id
    @Column(name = "sec_code")
    private Long secCode;

    @Column(name = "desc1")
    private String desc1;

    @Override
    public String toString() {
        return "Section{" +
            "secCode=" + secCode +
            ", desc1='" + desc1 + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Section section = (Section) o;
        return Objects.equals(secCode, section.secCode) &&
            Objects.equals(desc1, section.desc1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secCode, desc1);
    }

    public Long getSecCode() {
        return secCode;
    }

    public void setSecCode(Long secCode) {
        this.secCode = secCode;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }
}

package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "grade_view")
public class Grade implements Serializable {
    @Id
    @Column(name = "g_code")
    private Long gCode;

    @Column(name = "desc1")
    private String desc1;

    @Override
    public String toString() {
        return "Grade{" +
            "gCode=" + gCode +
            ", desc1='" + desc1 + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return Objects.equals(gCode, grade.gCode) &&
            Objects.equals(desc1, grade.desc1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gCode, desc1);
    }

    public Long getgCode() {
        return gCode;
    }

    public void setgCode(Long gCode) {
        this.gCode = gCode;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }
}

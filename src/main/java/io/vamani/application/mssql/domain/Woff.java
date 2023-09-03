package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "woff_view")
public class Woff implements Serializable {
    @Id
    @Column(name = "w_code")
    private Long wCode;

    @Column(name = "desc1")
    private String desc1;

    @Override
    public String toString() {
        return "Woff{" +
            "wCode=" + wCode +
            ", desc1='" + desc1 + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Woff woff = (Woff) o;
        return Objects.equals(wCode, woff.wCode) &&
            Objects.equals(desc1, woff.desc1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wCode, desc1);
    }

    public Long getwCode() {
        return wCode;
    }

    public void setwCode(Long wCode) {
        this.wCode = wCode;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }
}

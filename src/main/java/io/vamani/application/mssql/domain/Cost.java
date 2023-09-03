package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cost_view")
public class Cost  implements Serializable {
    @Id
    @Column(name = "cost_code")
    private Long costCode;

    @Column(name = "desc1")
    private String desc1;

    @Column(name = "tmp")
    private Long tmp;

    public Long getCostCode() {
        return costCode;
    }

    public void setCostCode(Long costCode) {
        this.costCode = costCode;
    }

    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    public Long getTmp() {
        return tmp;
    }

    public void setTmp(Long tmp) {
        this.tmp = tmp;
    }

    @Override
    public String toString() {
        return "Cost{" +
            "costCode=" + costCode +
            ", desc1='" + desc1 + '\'' +
            ", tmp=" + tmp +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cost cost = (Cost) o;
        return Objects.equals(costCode, cost.costCode) &&
            Objects.equals(desc1, cost.desc1) &&
            Objects.equals(tmp, cost.tmp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(costCode, desc1, tmp);
    }
}

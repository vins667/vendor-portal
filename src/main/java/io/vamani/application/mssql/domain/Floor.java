package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "floor_view")
public class Floor implements Serializable {
    @Id
    @Column(name = "fl_code")
    private Long flCode;

    @Column(name = "fl_desc")
    private String flDesc;

    @Override
    public String toString() {
        return "Floor{" +
            "flCode=" + flCode +
            ", flDesc='" + flDesc + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Floor floor = (Floor) o;
        return Objects.equals(flCode, floor.flCode) &&
            Objects.equals(flDesc, floor.flDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flCode, flDesc);
    }

    public Long getFlCode() {
        return flCode;
    }

    public void setFlCode(Long flCode) {
        this.flCode = flCode;
    }

    public String getFlDesc() {
        return flDesc;
    }

    public void setFlDesc(String flDesc) {
        this.flDesc = flDesc;
    }
}

package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "catgory_view")
public class Catgory implements Serializable {
    @Id
    @Column(name = "cat_code")
    private Long catCode;

    @Column(name = "cat_name")
    private String catName;

    @Override
    public String toString() {
        return "Catgory{" +
            "catCode=" + catCode +
            ", catName='" + catName + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catgory catgory = (Catgory) o;
        return Objects.equals(catCode, catgory.catCode) &&
            Objects.equals(catName, catgory.catName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(catCode, catName);
    }

    public Long getCatCode() {
        return catCode;
    }

    public void setCatCode(Long catCode) {
        this.catCode = catCode;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}

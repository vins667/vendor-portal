package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "company")
public class Company {
    private String code;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;

    @Id
    @Column(name = "CODE", nullable = false, length = 3)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "LONGDESCRIPTION", nullable = true, length = 200)
    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    @Basic
    @Column(name = "SHORTDESCRIPTION", nullable = true, length = 80)
    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    @Basic
    @Column(name = "SEARCHDESCRIPTION", nullable = true, length = 120)
    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(code, company.code) && Objects.equals(longdescription, company.longdescription) && Objects.equals(shortdescription, company.shortdescription) && Objects.equals(searchdescription, company.searchdescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, longdescription, shortdescription, searchdescription);
    }
}

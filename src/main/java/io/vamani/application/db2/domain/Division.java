package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "division")
public class Division {
    @EmbeddedId
    private DivisionId id;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;

    public DivisionId getId() {
        return id;
    }

    public void setId(DivisionId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LONGDESCRIPTION", nullable = false, length = 200)
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
        Division division = (Division) o;
        return Objects.equals(id, division.id) && Objects.equals(longdescription, division.longdescription) && Objects.equals(shortdescription, division.shortdescription) && Objects.equals(searchdescription, division.searchdescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longdescription, shortdescription, searchdescription);
    }
}

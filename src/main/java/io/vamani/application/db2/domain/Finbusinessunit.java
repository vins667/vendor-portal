package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "finbusinessunit")
public class Finbusinessunit {
    @EmbeddedId
    private FinbusinessunitId id;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private Short groupflag;

    public FinbusinessunitId getId() {
        return id;
    }

    public void setId(FinbusinessunitId id) {
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

    @Basic
    @Column(name = "GROUPFLAG", nullable = false)
    public Short getGroupflag() {
        return groupflag;
    }

    public void setGroupflag(Short groupflag) {
        this.groupflag = groupflag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Finbusinessunit that = (Finbusinessunit) o;
        return Objects.equals(id, that.id) && Objects.equals(longdescription, that.longdescription) && Objects.equals(shortdescription, that.shortdescription) && Objects.equals(searchdescription, that.searchdescription) && Objects.equals(groupflag, that.groupflag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longdescription, shortdescription, searchdescription, groupflag);
    }
}

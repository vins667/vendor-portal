package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "sizes")
public class Sizes implements Serializable {
    @EmbeddedId
    private SizesId id;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private Integer sequence;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;

    public SizesId getId() {
        return id;
    }

    public void setId(SizesId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LONGDESCRIPTION", nullable = true, length = 100)
    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    @Basic
    @Column(name = "SHORTDESCRIPTION", nullable = true, length = 40)
    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    @Basic
    @Column(name = "SEARCHDESCRIPTION", nullable = true, length = 60)
    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    @Basic
    @Column(name = "SEQUENCE", nullable = false)
    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    @Basic
    @Column(name = "CREATIONDATETIME", nullable = true)
    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    @Basic
    @Column(name = "CREATIONUSER", nullable = true, length = 25)
    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIME", nullable = true)
    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    @Basic
    @Column(name = "LASTUPDATEUSER", nullable = true, length = 25)
    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sizes sizes = (Sizes) o;
        return Objects.equals(id, sizes.id) &&
            Objects.equals(longdescription, sizes.longdescription) &&
            Objects.equals(shortdescription, sizes.shortdescription) &&
            Objects.equals(searchdescription, sizes.searchdescription) &&
            Objects.equals(sequence, sizes.sequence) &&
            Objects.equals(creationdatetime, sizes.creationdatetime) &&
            Objects.equals(creationuser, sizes.creationuser) &&
            Objects.equals(lastupdatedatetime, sizes.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, sizes.lastupdateuser) &&
            Objects.equals(absuniqueid, sizes.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longdescription, shortdescription, searchdescription, sequence, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid);
    }

    @Override
    public String toString() {
        return "Sizes{" +
            "id=" + id +
            ", longdescription='" + longdescription + '\'' +
            ", shortdescription='" + shortdescription + '\'' +
            ", searchdescription='" + searchdescription + '\'' +
            ", sequence=" + sequence +
            ", creationdatetime=" + creationdatetime +
            ", creationuser='" + creationuser + '\'' +
            ", lastupdatedatetime=" + lastupdatedatetime +
            ", lastupdateuser='" + lastupdateuser + '\'' +
            ", absuniqueid=" + absuniqueid +
            '}';
    }
}

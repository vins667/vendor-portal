package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class AdstorageId implements Serializable {
    private Long uniqueid;
    private String nameentityname;
    private String namename;
    private String fieldname;

    @Column(name = "UNIQUEID", nullable = false)
    @Id
    public Long getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(Long uniqueid) {
        this.uniqueid = uniqueid;
    }

    @Column(name = "NAMEENTITYNAME", nullable = false, length = 50)
    @Id
    public String getNameentityname() {
        return nameentityname;
    }

    public void setNameentityname(String nameentityname) {
        this.nameentityname = nameentityname;
    }

    @Column(name = "NAMENAME", nullable = false, length = 50)
    @Id
    public String getNamename() {
        return namename;
    }

    public void setNamename(String namename) {
        this.namename = namename;
    }

    @Column(name = "FIELDNAME", nullable = false, length = 120)
    @Id
    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public AdstorageId() {
    }

    public AdstorageId(Long uniqueid, String nameentityname, String namename, String fieldname) {
        this.uniqueid = uniqueid;
        this.nameentityname = nameentityname;
        this.namename = namename;
        this.fieldname = fieldname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdstorageId that = (AdstorageId) o;
        return Objects.equals(uniqueid, that.uniqueid) && Objects.equals(nameentityname, that.nameentityname) && Objects.equals(namename, that.namename) && Objects.equals(fieldname, that.fieldname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueid, nameentityname, namename, fieldname);
    }
}

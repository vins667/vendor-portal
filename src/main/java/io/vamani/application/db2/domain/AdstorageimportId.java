package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class AdstorageimportId implements Serializable {
    private String ownerentityname;
    private Long owneraduniqueid;
    private String nameentityname;
    private String namename;
    private String fieldname;

    @Column(name = "OWNERENTITYNAME", nullable = false, length = 50)
    @Id
    public String getOwnerentityname() {
        return ownerentityname;
    }

    public void setOwnerentityname(String ownerentityname) {
        this.ownerentityname = ownerentityname;
    }

    @Column(name = "OWNERADUNIQUEID", nullable = false)
    @Id
    public Long getOwneraduniqueid() {
        return owneraduniqueid;
    }

    public void setOwneraduniqueid(Long owneraduniqueid) {
        this.owneraduniqueid = owneraduniqueid;
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

    @Column(name = "FIELDNAME", nullable = false, length = 50)
    @Id
    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdstorageimportId that = (AdstorageimportId) o;
        return Objects.equals(ownerentityname, that.ownerentityname) && Objects.equals(owneraduniqueid, that.owneraduniqueid) && Objects.equals(nameentityname, that.nameentityname) && Objects.equals(namename, that.namename) && Objects.equals(fieldname, that.fieldname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerentityname, owneraduniqueid, nameentityname, namename, fieldname);
    }

    public AdstorageimportId() {
    }

    public AdstorageimportId(String ownerentityname, Long owneraduniqueid, String nameentityname, String namename, String fieldname) {
        this.ownerentityname = ownerentityname;
        this.owneraduniqueid = owneraduniqueid;
        this.nameentityname = nameentityname;
        this.namename = namename;
        this.fieldname = fieldname;
    }
}

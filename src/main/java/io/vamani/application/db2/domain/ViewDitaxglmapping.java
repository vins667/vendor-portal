package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "VIEW_DITAXGLMAPPING")
public class ViewDitaxglmapping {
    @EmbeddedId
    private ViewDitaxglmappingId id;

    @Column(name = "GLCODE")
    private String glcode;

    @Column(name = "LONGDESCRIPTION")
    private String longdescription;

    @Column(name = "VALUE")
    private Double value;

    public ViewDitaxglmappingId getId() {
        return id;
    }

    public void setId(ViewDitaxglmappingId id) {
        this.id = id;
    }

    public String getGlcode() {
        return glcode;
    }

    public void setGlcode(String glcode) {
        this.glcode = glcode;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewDitaxglmapping that = (ViewDitaxglmapping) o;
        return Objects.equals(id, that.id) && Objects.equals(glcode, that.glcode) && Objects.equals(longdescription, that.longdescription) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, glcode, longdescription, value);
    }
}

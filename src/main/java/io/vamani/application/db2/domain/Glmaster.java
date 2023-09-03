package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "glmaster")
public class Glmaster {
    @EmbeddedId
    private GlmasterId id;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private String gltype;
    private String status;
    private String chartofaccountcode;

    public GlmasterId getId() {
        return id;
    }

    public void setId(GlmasterId id) {
        this.id = id;
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

    @Basic
    @Column(name = "GLTYPE", nullable = true, length = 1)
    public String getGltype() {
        return gltype;
    }

    public void setGltype(String gltype) {
        this.gltype = gltype;
    }

    @Basic
    @Column(name = "STATUS", nullable = false, length = 1)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CHARTOFACCOUNTCODE")
    public String getChartofaccountcode() {
        return chartofaccountcode;
    }

    public void setChartofaccountcode(String chartofaccountcode) {
        this.chartofaccountcode = chartofaccountcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Glmaster glmaster = (Glmaster) o;
        return Objects.equals(id, glmaster.id) && Objects.equals(longdescription, glmaster.longdescription) && Objects.equals(shortdescription, glmaster.shortdescription) && Objects.equals(searchdescription, glmaster.searchdescription) && Objects.equals(gltype, glmaster.gltype) && Objects.equals(status, glmaster.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longdescription, shortdescription, searchdescription, gltype, status);
    }
}

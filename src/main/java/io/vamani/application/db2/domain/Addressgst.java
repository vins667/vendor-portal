package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ADDRESSGST")
public class Addressgst {
    @Id
    @Column(name = "UNIQUEID")
    private Long uniqueid;
    @Basic
    @Column(name = "GSTINNUMBER")
    private String gstinnumber;
    @Basic
    @Column(name = "GSTDATE")
    private Date gstdate;
    @Basic
    @Column(name = "STATECODE")
    private String statecode;
    @Basic
    @Column(name = "PROVISIONALGSTINNUMBER")
    private String provisionalgstinnumber;
    @Basic
    @Column(name = "PROVISIONALGSTDATE")
    private Date provisionalgstdate;
    @Basic
    @Column(name = "ABSUNIQUEID")
    private Long absuniqueid;

    public Long getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(Long uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getGstinnumber() {
        return gstinnumber;
    }

    public void setGstinnumber(String gstinnumber) {
        this.gstinnumber = gstinnumber;
    }

    public Date getGstdate() {
        return gstdate;
    }

    public void setGstdate(Date gstdate) {
        this.gstdate = gstdate;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getProvisionalgstinnumber() {
        return provisionalgstinnumber;
    }

    public void setProvisionalgstinnumber(String provisionalgstinnumber) {
        this.provisionalgstinnumber = provisionalgstinnumber;
    }

    public Date getProvisionalgstdate() {
        return provisionalgstdate;
    }

    public void setProvisionalgstdate(Date provisionalgstdate) {
        this.provisionalgstdate = provisionalgstdate;
    }

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
        Addressgst that = (Addressgst) o;
        return Objects.equals(uniqueid, that.uniqueid) && Objects.equals(gstinnumber, that.gstinnumber) && Objects.equals(gstdate, that.gstdate) && Objects.equals(statecode, that.statecode) && Objects.equals(provisionalgstinnumber, that.provisionalgstinnumber) && Objects.equals(provisionalgstdate, that.provisionalgstdate) && Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uniqueid, gstinnumber, gstdate, statecode, provisionalgstinnumber, provisionalgstdate, absuniqueid);
    }
}

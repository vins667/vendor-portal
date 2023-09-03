package io.vamani.application.mssql.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "department")
public class Department {
    private int depCode;
    private String desc1;
    private String tmp;
    private Integer depHead;
    private Integer mdepCode;
    private Integer intive;
    private Integer pro254;

    @Id
    @Column(name = "dep_code")
    public int getDepCode() {
        return depCode;
    }

    public void setDepCode(int depCode) {
        this.depCode = depCode;
    }

    @Basic
    @Column(name = "desc1")
    public String getDesc1() {
        return desc1;
    }

    public void setDesc1(String desc1) {
        this.desc1 = desc1;
    }

    @Basic
    @Column(name = "tmp")
    public String getTmp() {
        return tmp;
    }

    public void setTmp(String tmp) {
        this.tmp = tmp;
    }

    @Basic
    @Column(name = "dep_head")
    public Integer getDepHead() {
        return depHead;
    }

    public void setDepHead(Integer depHead) {
        this.depHead = depHead;
    }

    @Basic
    @Column(name = "mdep_code")
    public Integer getMdepCode() {
        return mdepCode;
    }

    public void setMdepCode(Integer mdepCode) {
        this.mdepCode = mdepCode;
    }

    @Basic
    @Column(name = "intive")
    public Integer getIntive() {
        return intive;
    }

    public void setIntive(Integer intive) {
        this.intive = intive;
    }

    @Basic
    @Column(name = "pro_254")
    public Integer getPro254() {
        return pro254;
    }

    public void setPro254(Integer pro254) {
        this.pro254 = pro254;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return depCode == that.depCode && Objects.equals(desc1, that.desc1) && Objects.equals(tmp, that.tmp) && Objects.equals(depHead, that.depHead) && Objects.equals(mdepCode, that.mdepCode) && Objects.equals(intive, that.intive) && Objects.equals(pro254, that.pro254);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depCode, desc1, tmp, depHead, mdepCode, intive, pro254);
    }
}

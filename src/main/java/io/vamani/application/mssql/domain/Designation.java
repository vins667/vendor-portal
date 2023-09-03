package io.vamani.application.mssql.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "designation")
public class Designation {
    private int desCode;
    private String desc1;
    private Short amt;
    private Double tea;
    private Integer intive;
    private Integer intive1;
    private Integer pro254;

    @Id
    @Column(name = "des_code")
    public int getDesCode() {
        return desCode;
    }

    public void setDesCode(int desCode) {
        this.desCode = desCode;
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
    @Column(name = "amt")
    public Short getAmt() {
        return amt;
    }

    public void setAmt(Short amt) {
        this.amt = amt;
    }

    @Basic
    @Column(name = "tea")
    public Double getTea() {
        return tea;
    }

    public void setTea(Double tea) {
        this.tea = tea;
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
    @Column(name = "intive1")
    public Integer getIntive1() {
        return intive1;
    }

    public void setIntive1(Integer intive1) {
        this.intive1 = intive1;
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
        Designation that = (Designation) o;
        return desCode == that.desCode && Objects.equals(desc1, that.desc1) && Objects.equals(amt, that.amt) && Objects.equals(tea, that.tea) && Objects.equals(intive, that.intive) && Objects.equals(intive1, that.intive1) && Objects.equals(pro254, that.pro254);
    }

    @Override
    public int hashCode() {
        return Objects.hash(desCode, desc1, amt, tea, intive, intive1, pro254);
    }
}

package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Productspecializedsize {
    @EmbeddedId
    private ProductspecializedsizeId id;
    private String productsubcode09;
    private String productsubcode10;
    private String sizetypecode;
    private String sizetypematrixrowcode;
    private Integer valuescombinations;
    private Long absuniqueid;

    public ProductspecializedsizeId getId() {
        return id;
    }

    public void setId(ProductspecializedsizeId id) {
        this.id = id;
    }

    @Column(name = "PRODUCTSUBCODE09", nullable = false, length = 10)
    public String getProductsubcode09() {
        return productsubcode09;
    }

    public void setProductsubcode09(String productsubcode09) {
        this.productsubcode09 = productsubcode09;
    }

    @Column(name = "PRODUCTSUBCODE10", nullable = false, length = 10)
    public String getProductsubcode10() {
        return productsubcode10;
    }

    public void setProductsubcode10(String productsubcode10) {
        this.productsubcode10 = productsubcode10;
    }

    @Basic
    @Column(name = "SIZETYPECODE", nullable = true, length = 3)
    public String getSizetypecode() {
        return sizetypecode != null ? sizetypecode.trim() : sizetypecode;
    }

    public void setSizetypecode(String sizetypecode) {
        this.sizetypecode = sizetypecode;
    }

    @Basic
    @Column(name = "SIZETYPEMATRIXROWCODE", nullable = true, length = 3)
    public String getSizetypematrixrowcode() {
        return sizetypematrixrowcode;
    }

    public void setSizetypematrixrowcode(String sizetypematrixrowcode) {
        this.sizetypematrixrowcode = sizetypematrixrowcode;
    }

    @Basic
    @Column(name = "VALUESCOMBINATIONS", nullable = false)
    public Integer getValuescombinations() {
        return valuescombinations;
    }

    public void setValuescombinations(Integer valuescombinations) {
        this.valuescombinations = valuescombinations;
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
        Productspecializedsize that = (Productspecializedsize) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(productsubcode09, that.productsubcode09) &&
            Objects.equals(productsubcode10, that.productsubcode10) &&
            Objects.equals(sizetypecode, that.sizetypecode) &&
            Objects.equals(sizetypematrixrowcode, that.sizetypematrixrowcode) &&
            Objects.equals(valuescombinations, that.valuescombinations) &&
            Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productsubcode09, productsubcode10, sizetypecode, sizetypematrixrowcode, valuescombinations, absuniqueid);
    }

    @Override
    public String toString() {
        return "Productspecializedsize{" +
            "id=" + id +
            ", productsubcode09='" + productsubcode09 + '\'' +
            ", productsubcode10='" + productsubcode10 + '\'' +
            ", sizetypecode='" + sizetypecode + '\'' +
            ", sizetypematrixrowcode='" + sizetypematrixrowcode + '\'' +
            ", valuescombinations=" + valuescombinations +
            ", absuniqueid=" + absuniqueid +
            '}';
    }
}

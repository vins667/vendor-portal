package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ProductiondemandstepId implements Serializable {
    private String productiondemandcompanycode;
    private String productiondemandcountercode;
    private String productiondemandcode;
    private Integer stepnumber;

    @Column(name = "PRODUCTIONDEMANDCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getProductiondemandcompanycode() {
        return productiondemandcompanycode;
    }

    public void setProductiondemandcompanycode(String productiondemandcompanycode) {
        this.productiondemandcompanycode = productiondemandcompanycode;
    }

    @Column(name = "PRODUCTIONDEMANDCOUNTERCODE", nullable = false, length = 8)
    @Id
    public String getProductiondemandcountercode() {
        return productiondemandcountercode;
    }

    public void setProductiondemandcountercode(String productiondemandcountercode) {
        this.productiondemandcountercode = productiondemandcountercode;
    }

    @Column(name = "PRODUCTIONDEMANDCODE", nullable = false, length = 15)
    @Id
    public String getProductiondemandcode() {
        return productiondemandcode;
    }

    public void setProductiondemandcode(String productiondemandcode) {
        this.productiondemandcode = productiondemandcode;
    }

    @Column(name = "STEPNUMBER", nullable = false, precision = 0)
    @Id
    public Integer getStepnumber() {
        return stepnumber;
    }

    public void setStepnumber(Integer stepnumber) {
        this.stepnumber = stepnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductiondemandstepId that = (ProductiondemandstepId) o;
        return Objects.equals(productiondemandcompanycode, that.productiondemandcompanycode) &&
            Objects.equals(productiondemandcountercode, that.productiondemandcountercode) &&
            Objects.equals(productiondemandcode, that.productiondemandcode) &&
            Objects.equals(stepnumber, that.stepnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productiondemandcompanycode, productiondemandcountercode, productiondemandcode, stepnumber);
    }

    @Override
    public String toString() {
        return "ProductiondemandstepId{" +
            "productiondemandcompanycode='" + productiondemandcompanycode + '\'' +
            ", productiondemandcountercode='" + productiondemandcountercode + '\'' +
            ", productiondemandcode='" + productiondemandcode + '\'' +
            ", stepnumber=" + stepnumber +
            '}';
    }

    public ProductiondemandstepId() {
    }

    public ProductiondemandstepId(String productiondemandcompanycode, String productiondemandcountercode, String productiondemandcode, Integer stepnumber) {
        this.productiondemandcompanycode = productiondemandcompanycode;
        this.productiondemandcountercode = productiondemandcountercode;
        this.productiondemandcode = productiondemandcode;
        this.stepnumber = stepnumber;
    }
}

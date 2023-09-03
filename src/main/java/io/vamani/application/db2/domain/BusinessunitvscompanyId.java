package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class BusinessunitvscompanyId implements Serializable {
    private String businessunitcode;
    private String factorycode;

    @Column(name = "businessunitcode", nullable = true, length = 3)
    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    @Column(name = "factorycode", nullable = true, length = 3)
    public String getFactorycode() {
        return factorycode;
    }

    public void setFactorycode(String factorycode) {
        this.factorycode = factorycode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BusinessunitvscompanyId that = (BusinessunitvscompanyId) o;
        return Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(factorycode, that.factorycode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(businessunitcode, factorycode);
    }
}

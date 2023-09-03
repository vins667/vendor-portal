package io.vamani.application.db2.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class ViewDitaxglmappingId implements Serializable {
    @Column(name = "COMPANYCODE")
    private String companycode;

    @Column(name = "EVENTCODE")
    private String eventcode;

    @Column(name = "TAXCODE")
    private String taxcode;

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getEventcode() {
        return eventcode;
    }

    public void setEventcode(String eventcode) {
        this.eventcode = eventcode;
    }

    public String getTaxcode() {
        return taxcode;
    }

    public void setTaxcode(String taxcode) {
        this.taxcode = taxcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewDitaxglmappingId that = (ViewDitaxglmappingId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(eventcode, that.eventcode) && Objects.equals(taxcode, that.taxcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, eventcode, taxcode);
    }
}

package io.vamani.application.db2.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "factory")
public class Factory implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "DIVISIONCODE")
    private String divisioncode;

    @Id
    @Column(name = "CODE")
    private String factCode;

    @Column(name = "LONGDESCRIPTION")
    private String factDescription;

    @Column(name = "SEARCHDESCRIPTION")
    private String searchdescription;

    @Column(name = "STATECODE")
    private String statecode;

    @Column(name = "ADDRESSLINE1")
    private String addressline1;

    @Column(name = "ADDRESSLINE2")
    private String addressline2;

    public String getFactCode() {
        return factCode;
    }

    public void setFactCode(String factCode) {
        this.factCode = factCode;
    }

    public String getFactDescription() {
        return factDescription;
    }

    public void setFactDescription(String factDescription) {
        this.factDescription = factDescription;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDivisioncode() {
        return divisioncode;
    }

    public void setDivisioncode(String divisioncode) {
        this.divisioncode = divisioncode;
    }

    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    public String getStatecode() {
        return statecode;
    }

    public void setStatecode(String statecode) {
        this.statecode = statecode;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }
}

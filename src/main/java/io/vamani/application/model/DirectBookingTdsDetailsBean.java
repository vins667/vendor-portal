package io.vamani.application.model;

import io.vamani.application.domain.DirectBookingEntry;

import java.io.Serializable;
import java.util.Objects;

public class DirectBookingTdsDetailsBean implements Serializable {
    private Long id;

    private String tdsDesc;

    private String tdsCode;

    private String tdsTaxCode;

    private String tdsPercDesc;

    private Double tdsPerc;

    private Boolean tdsApplicable;

    private DirectBookingEntry directBookingEntry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTdsDesc() {
        return tdsDesc;
    }

    public void setTdsDesc(String tdsDesc) {
        this.tdsDesc = tdsDesc;
    }

    public String getTdsCode() {
        return tdsCode;
    }

    public void setTdsCode(String tdsCode) {
        this.tdsCode = tdsCode;
    }

    public String getTdsTaxCode() {
        return tdsTaxCode;
    }

    public void setTdsTaxCode(String tdsTaxCode) {
        this.tdsTaxCode = tdsTaxCode;
    }

    public String getTdsPercDesc() {
        return tdsPercDesc;
    }

    public void setTdsPercDesc(String tdsPercDesc) {
        this.tdsPercDesc = tdsPercDesc;
    }

    public Double getTdsPerc() {
        return tdsPerc;
    }

    public void setTdsPerc(Double tdsPerc) {
        this.tdsPerc = tdsPerc;
    }

    public Boolean getTdsApplicable() {
        return tdsApplicable;
    }

    public void setTdsApplicable(Boolean tdsApplicable) {
        this.tdsApplicable = tdsApplicable;
    }

    public DirectBookingEntry getDirectBookingEntry() {
        return directBookingEntry;
    }

    public void setDirectBookingEntry(DirectBookingEntry directBookingEntry) {
        this.directBookingEntry = directBookingEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectBookingTdsDetailsBean that = (DirectBookingTdsDetailsBean) o;
        return Objects.equals(id, that.id) && Objects.equals(tdsDesc, that.tdsDesc) && Objects.equals(tdsCode, that.tdsCode) && Objects.equals(tdsTaxCode, that.tdsTaxCode) && Objects.equals(tdsPercDesc, that.tdsPercDesc) && Objects.equals(tdsPerc, that.tdsPerc) && Objects.equals(tdsApplicable, that.tdsApplicable) && Objects.equals(directBookingEntry, that.directBookingEntry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tdsDesc, tdsCode, tdsTaxCode, tdsPercDesc, tdsPerc, tdsApplicable, directBookingEntry);
    }
}

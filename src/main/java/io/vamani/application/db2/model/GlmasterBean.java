package io.vamani.application.db2.model;

import io.vamani.application.db2.domain.GlmasterId;

public class GlmasterBean {
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

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    public String getGltype() {
        return gltype;
    }

    public void setGltype(String gltype) {
        this.gltype = gltype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChartofaccountcode() {
        return chartofaccountcode;
    }

    public void setChartofaccountcode(String chartofaccountcode) {
        this.chartofaccountcode = chartofaccountcode;
    }
}

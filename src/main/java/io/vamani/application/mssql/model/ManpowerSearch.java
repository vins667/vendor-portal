package io.vamani.application.mssql.model;

import io.vamani.application.db2.domain.Resources;
import io.vamani.application.db2.model.ResourcesBean;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class ManpowerSearch implements Serializable {
    private String deptCode;
    private String factoryCode;
    private Instant dateFrom;
    private String type;
    private List<ResourcesBean> resourcesBeans;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getFactoryCode() {
        return factoryCode;
    }

    public void setFactoryCode(String factoryCode) {
        this.factoryCode = factoryCode;
    }

    public Instant getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Instant dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ResourcesBean> getResourcesBeans() {
        return resourcesBeans;
    }

    public void setResourcesBeans(List<ResourcesBean> resourcesBeans) {
        this.resourcesBeans = resourcesBeans;
    }
}

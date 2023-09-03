package io.vamani.application.db2.model;

import io.vamani.application.db2.domain.ResourcesId;

import javax.persistence.EmbeddedId;

public class ResourcesBean {
    private Long id;
    private String code;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private Long resourceAllocate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public Long getResourceAllocate() {
        return resourceAllocate;
    }

    public void setResourceAllocate(Long resourceAllocate) {
        this.resourceAllocate = resourceAllocate;
    }
}

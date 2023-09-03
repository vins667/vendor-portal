package io.vamani.application.db2.model;

import java.io.Serializable;

public class SizesBean implements Serializable {
    private String sizeCode;
    private Integer sequence;

    public String getSizeCode() {
        return sizeCode;
    }

    public void setSizeCode(String sizeCode) {
        this.sizeCode = sizeCode;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public SizesBean() {
    }

    public SizesBean(String sizeCode, Integer sequence) {
        this.sizeCode = sizeCode;
        this.sequence = sequence;
    }
}

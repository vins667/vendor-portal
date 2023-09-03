package io.vamani.application.mssql.model;

public class HierarchyUnit {
    private String type;
    private String value;
    private String desc;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public HierarchyUnit() {
    }

    public HierarchyUnit(String type, String value, String desc) {
        this.type = type;
        this.value = value;
        this.desc = desc;
    }
}

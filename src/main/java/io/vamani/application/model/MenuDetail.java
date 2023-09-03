package io.vamani.application.model;

import java.util.List;
import java.util.Objects;

public class MenuDetail {
    private Long id;
    private String link;
    private String label;
    private String icon;
    private String toolTip;
    private String mobileClass;
    private List<String> type;
    private List<MenuDetail> subItem;
    private boolean isCollapsed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public List<MenuDetail> getSubItem() {
        return subItem;
    }

    public void setSubItem(List<MenuDetail> subItem) {
        this.subItem = subItem;
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }

    public void setCollapsed(boolean collapsed) {
        isCollapsed = collapsed;
    }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public String getMobileClass() {
        return mobileClass;
    }

    public void setMobileClass(String mobileClass) {
        this.mobileClass = mobileClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuDetail)) return false;
        MenuDetail that = (MenuDetail) o;
        return isCollapsed() == that.isCollapsed() &&
            Objects.equals(getId(), that.getId()) &&
            Objects.equals(getLink(), that.getLink()) &&
            Objects.equals(getLabel(), that.getLabel()) &&
            Objects.equals(getIcon(), that.getIcon()) &&
            Objects.equals(getType(), that.getType()) &&
            Objects.equals(getSubItem(), that.getSubItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLink(), getLabel(), getIcon(), getType(), getSubItem(), isCollapsed());
    }

    @Override
    public String toString() {
        return "MenuDetail{" +
            "id=" + id +
            ", link='" + link + '\'' +
            ", label='" + label + '\'' +
            ", icon='" + icon + '\'' +
            ", type=" + type +
            ", subItem=" + subItem +
            ", isCollapsed=" + isCollapsed +
            '}';
    }
}

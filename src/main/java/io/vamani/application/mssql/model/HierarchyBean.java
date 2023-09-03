package io.vamani.application.mssql.model;

import java.util.List;

public class HierarchyBean {
    private String cardNo;
    private String name;
    private String imageUrl;
    private String area;
    private String profileUrl;
    private String office;
    private String tags;
    private boolean loggedUser;
    private HierarchyUnit unit;
    private String positionName;
    private List<HierarchyBean> children;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(boolean loggedUser) {
        this.loggedUser = loggedUser;
    }

    public HierarchyUnit getUnit() {
        return unit;
    }

    public void setUnit(HierarchyUnit unit) {
        this.unit = unit;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public List<HierarchyBean> getChildren() {
        return children;
    }

    public void setChildren(List<HierarchyBean> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "HierarchyBean{" +
            "name='" + name + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            ", area='" + area + '\'' +
            ", profileUrl='" + profileUrl + '\'' +
            ", office='" + office + '\'' +
            ", tags='" + tags + '\'' +
            ", isLoggedUser=" + loggedUser +
            ", unit=" + unit +
            ", positionName='" + positionName + '\'' +
            ", children=" + children +
            '}';
    }
}

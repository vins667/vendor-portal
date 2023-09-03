package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A MenuMaster.
 */
@Entity
@Table(name = "menu_master")
public class MenuMaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="menuMasterSeq", sequenceName="menu_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="menuMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "menu_label", length = 100, nullable = false)
    private String menuLabel;

    @NotNull
    @Size(max = 50)
    @Column(name = "menu_icon", length = 50, nullable = false)
    private String menuIcon;

    @NotNull
    @Size(max = 100)
    @Column(name = "menu_link", length = 100, nullable = false)
    private String menuLink;

    @NotNull
    @Column(name = "folder", nullable = false)
    private Boolean folder;

    @NotNull
    @Column(name = "collapsed", nullable = false)
    private Boolean collapsed;

    @Column(name = "folder_id")
    private Long folderId;

    @Column(name = "tool_tip")
    private String toolTip;

    @NotNull
    @Column(name = "parent", nullable = false)
    private Boolean parent;

    @Column(name = "order_by")
    private Long orderBy;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Column(name = "mobile_access")
    private String mobileAccess;

    @Size(max = 200)
    @Column(name = "mobile_class", length = 200)
    private String mobileClass;

    @Size(max = 50)
    @Column(name = "qlik_type", length = 50)
    private String qlikType;

    @Size(max = 255)
    @Column(name = "qlik_url", length = 255)
    private String qlikUrl;

    @Size(max = 50)
    @Column(name = "qlik_label", length = 50)
    private String qlikLabel;

    @Size(max = 255)
    @Column(name = "qlik_url_two", length = 255)
    private String qlikUrlTwo;

    @Size(max = 50)
    @Column(name = "qlik_label_two", length = 50)
    private String qlikLabelTwo;

    @Size(max = 255)
    @Column(name = "qlik_url_three", length = 255)
    private String qlikUrlThree;

    @Size(max = 50)
    @Column(name = "qlik_label_three", length = 50)
    private String qlikLabelThree;

    @Size(max = 255)
    @Column(name = "qlik_url_four", length = 255)
    private String qlikUrlFour;

    @Size(max = 50)
    @Column(name = "qlik_label_four", length = 50)
    private String qlikLabelFour;

    @Size(max = 255)
    @Column(name = "qlik_url_five", length = 255)
    private String qlikUrlFive;

    @Size(max = 50)
    @Column(name = "qlik_label_five", length = 50)
    private String qlikLabelFive;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuLabel() {
        return menuLabel;
    }

    public MenuMaster menuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
        return this;
    }

    public void setMenuLabel(String menuLabel) {
        this.menuLabel = menuLabel;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public MenuMaster menuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuLink() {
        return menuLink;
    }

    public MenuMaster menuLink(String menuLink) {
        this.menuLink = menuLink;
        return this;
    }

    public void setMenuLink(String menuLink) {
        this.menuLink = menuLink;
    }

    public Boolean isFolder() {
        return folder;
    }

    public MenuMaster folder(Boolean folder) {
        this.folder = folder;
        return this;
    }

    public void setFolder(Boolean folder) {
        this.folder = folder;
    }

    public Boolean isCollapsed() {
        return collapsed;
    }

    public MenuMaster collapsed(Boolean collapsed) {
        this.collapsed = collapsed;
        return this;
    }

    public void setCollapsed(Boolean collapsed) {
        this.collapsed = collapsed;
    }

    public Long getFolderId() {
        return folderId;
    }

    public MenuMaster folderId(Long folderId) {
        this.folderId = folderId;
        return this;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Boolean isParent() {
        return parent;
    }

    public MenuMaster parent(Boolean parent) {
        this.parent = parent;
        return this;
    }

    public void setParent(Boolean parent) {
        this.parent = parent;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MenuMaster createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public MenuMaster createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Long getOrderBy() { return orderBy; }

    public void setOrderBy(Long orderBy) { this.orderBy = orderBy; }

    public String getToolTip() {
        return toolTip;
    }

    public void setToolTip(String toolTip) {
        this.toolTip = toolTip;
    }

    public String getMobileAccess() {
        return mobileAccess;
    }

    public void setMobileAccess(String mobileAccess) {
        this.mobileAccess = mobileAccess;
    }

    public String getMobileClass() {
        return mobileClass;
    }

    public void setMobileClass(String mobileClass) {
        this.mobileClass = mobileClass;
    }

    public String getQlikType() {
        return qlikType;
    }

    public void setQlikType(String qlikType) {
        this.qlikType = qlikType;
    }

    public String getQlikUrl() {
        return qlikUrl;
    }

    public void setQlikUrl(String qlikUrl) {
        this.qlikUrl = qlikUrl;
    }

    public String getQlikLabel() {
        return qlikLabel;
    }

    public void setQlikLabel(String qlikLabel) {
        this.qlikLabel = qlikLabel;
    }

    public String getQlikUrlTwo() {
        return qlikUrlTwo;
    }

    public void setQlikUrlTwo(String qlikUrlTwo) {
        this.qlikUrlTwo = qlikUrlTwo;
    }

    public String getQlikLabelTwo() {
        return qlikLabelTwo;
    }

    public void setQlikLabelTwo(String qlikLabelTwo) {
        this.qlikLabelTwo = qlikLabelTwo;
    }

    public String getQlikUrlThree() {
        return qlikUrlThree;
    }

    public void setQlikUrlThree(String qlikUrlThree) {
        this.qlikUrlThree = qlikUrlThree;
    }

    public String getQlikLabelThree() {
        return qlikLabelThree;
    }

    public void setQlikLabelThree(String qlikLabelThree) {
        this.qlikLabelThree = qlikLabelThree;
    }

    public String getQlikUrlFour() {
        return qlikUrlFour;
    }

    public void setQlikUrlFour(String qlikUrlFour) {
        this.qlikUrlFour = qlikUrlFour;
    }

    public String getQlikLabelFour() {
        return qlikLabelFour;
    }

    public void setQlikLabelFour(String qlikLabelFour) {
        this.qlikLabelFour = qlikLabelFour;
    }

    public String getQlikUrlFive() {
        return qlikUrlFive;
    }

    public void setQlikUrlFive(String qlikUrlFive) {
        this.qlikUrlFive = qlikUrlFive;
    }

    public String getQlikLabelFive() {
        return qlikLabelFive;
    }

    public void setQlikLabelFive(String qlikLabelFive) {
        this.qlikLabelFive = qlikLabelFive;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MenuMaster menuMaster = (MenuMaster) o;
        if (menuMaster.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuMaster.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuMaster{" +
            "id=" + getId() +
            ", menuLabel='" + getMenuLabel() + "'" +
            ", menuIcon='" + getMenuIcon() + "'" +
            ", menuLink='" + getMenuLink() + "'" +
            ", folder='" + isFolder() + "'" +
            ", collapsed='" + isCollapsed() + "'" +
            ", folderId=" + getFolderId() +
            ", parent='" + isParent() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            "}";
    }
}

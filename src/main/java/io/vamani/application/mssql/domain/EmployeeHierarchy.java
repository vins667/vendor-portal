package io.vamani.application.mssql.domain;

import java.util.List;

public class EmployeeHierarchy {
    private String parentCardNo;
    private String parentName;
    private String parentDesignation;
    private String parentDepartment;
    private String parentContact;
    private String parentImage;
    private String cardNo;
    private String name;
    private String designation;
    private String department;
    private String contact;
    private String image;

    private List<EmployeeHierarchy> children;

    public String getParentCardNo() {
        return parentCardNo;
    }

    public void setParentCardNo(String parentCardNo) {
        this.parentCardNo = parentCardNo;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentDesignation() {
        return parentDesignation;
    }

    public void setParentDesignation(String parentDesignation) {
        this.parentDesignation = parentDesignation;
    }

    public String getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(String parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public String getParentContact() {
        return parentContact;
    }

    public void setParentContact(String parentContact) {
        this.parentContact = parentContact;
    }

    public String getParentImage() {
        return parentImage;
    }

    public void setParentImage(String parentImage) {
        this.parentImage = parentImage;
    }

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<EmployeeHierarchy> getChildren() {
        return children;
    }

    public void setChildren(List<EmployeeHierarchy> children) {
        this.children = children;
    }
}

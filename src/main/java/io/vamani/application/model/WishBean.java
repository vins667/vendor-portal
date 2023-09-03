package io.vamani.application.model;

import io.vamani.application.mssql.domain.EmployeeView;

import java.io.Serializable;

public class WishBean implements Serializable {
    private String messageType;
    private String celebrationMessageText;
    private EmployeeView employeeView;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getCelebrationMessageText() {
        return celebrationMessageText;
    }

    public void setCelebrationMessageText(String celebrationMessageText) {
        this.celebrationMessageText = celebrationMessageText;
    }

    public EmployeeView getEmployeeView() {
        return employeeView;
    }

    public void setEmployeeView(EmployeeView employeeView) {
        this.employeeView = employeeView;
    }
}

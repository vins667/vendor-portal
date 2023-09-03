package io.vamani.application.mssql.model;



import java.io.Serializable;
import java.util.Objects;

public class EmployeeLogin implements Serializable {
    private String login;
    private String name;
    private String phone;
    private Boolean exist;
    private String errorMessage;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeLogin that = (EmployeeLogin) o;
        return Objects.equals(login, that.login) &&
            Objects.equals(name, that.name) &&
            Objects.equals(phone, that.phone) &&
            Objects.equals(exist, that.exist) &&
            Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, name, phone, exist, errorMessage);
    }

    @Override
    public String toString() {
        return "EmployeeLogin{" +
            "login='" + login + '\'' +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", exist=" + exist +
            ", errorMessage='" + errorMessage + '\'' +
            '}';
    }
}


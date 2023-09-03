package io.vamani.application.model;

import java.io.Serializable;
import java.util.Objects;

public class MessageBean implements Serializable {
    private Boolean exist;
    private String errorMessage;

    public MessageBean() {
    }

    public MessageBean(Boolean exist, String errorMessage) {
        this.exist = exist;
        this.errorMessage = errorMessage;
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
        MessageBean that = (MessageBean) o;
        return Objects.equals(exist, that.exist) &&
            Objects.equals(errorMessage, that.errorMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exist, errorMessage);
    }

    @Override
    public String toString() {
        return "MessageBean{" +
            "exist=" + exist +
            ", errorMessage='" + errorMessage + '\'' +
            '}';
    }
}

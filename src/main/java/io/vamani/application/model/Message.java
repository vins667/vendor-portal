package io.vamani.application.model;

public class Message {
    private String msg;
    private String type;

    private Boolean exist;
    private String errorMessage;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Message() {
    }

    public Message(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Message(String msg, String type, Boolean exist, String errorMessage) {
        this.msg = msg;
        this.type = type;
        this.exist = exist;
        this.errorMessage = errorMessage;
    }
}

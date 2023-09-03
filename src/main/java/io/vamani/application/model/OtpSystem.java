package io.vamani.application.model;

public class OtpSystem {
    private String mobileNumber;
    private String message;

    public OtpSystem() {
    }

    public OtpSystem(String mobileNumber, String message) {
        this.mobileNumber = mobileNumber;
        this.message = message;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

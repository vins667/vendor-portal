package io.vamani.application.firebase;

public class FirebaseSystem {
    private String deviceId;
    private String type;
    private String status;
    private String body;

    public FirebaseSystem(String deviceId, String type, String status, String body) {
        this.deviceId = deviceId;
        this.type = type;
        this.status = status;
        this.body = body;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

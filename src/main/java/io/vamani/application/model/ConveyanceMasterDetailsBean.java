package io.vamani.application.model;

import io.vamani.application.util.CustomMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class ConveyanceMasterDetailsBean {
    private Long id;
    private Integer tripStart;
    private Integer tripEnd;
    private Double miscAmount;
    private String fromLocation;
    private String toLocation;
    private String reason;
    private CustomMultipartFile multipartFile;
    private String attachFile;
    private String attachDisplayFile;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTripStart() {
        return tripStart;
    }

    public void setTripStart(Integer tripStart) {
        this.tripStart = tripStart;
    }

    public Integer getTripEnd() {
        return tripEnd;
    }

    public void setTripEnd(Integer tripEnd) {
        this.tripEnd = tripEnd;
    }

    public Double getMiscAmount() {
        return miscAmount;
    }

    public void setMiscAmount(Double miscAmount) {
        this.miscAmount = miscAmount;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public CustomMultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(CustomMultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public String getAttachFile() {
        return attachFile;
    }

    public void setAttachFile(String attachFile) {
        this.attachFile = attachFile;
    }

    public String getAttachDisplayFile() {
        return attachDisplayFile;
    }

    public void setAttachDisplayFile(String attachDisplayFile) {
        this.attachDisplayFile = attachDisplayFile;
    }
}

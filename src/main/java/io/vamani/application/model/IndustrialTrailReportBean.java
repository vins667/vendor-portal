package io.vamani.application.model;

import java.io.Serializable;

public class IndustrialTrailReportBean implements Serializable {

    private Long id;
    private String name;
    private String fatherName;
    private String splMachineKnowledge;
    private String splMachine;
    private String machineName;
    private Boolean snls;
    private Boolean dnls;
    private Boolean ol;
    private String operations;
    private String achieveRating;
    private String result;
    private String timeTaken;
    private String grade;
    private String remarks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getSplMachine() {
        return splMachine;
    }

    public void setSplMachine(String splMachine) {
        this.splMachine = splMachine;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public Boolean getSnls() {
        return snls;
    }

    public void setSnls(Boolean snls) {
        this.snls = snls;
    }

    public Boolean getDnls() {
        return dnls;
    }

    public void setDnls(Boolean dnls) {
        this.dnls = dnls;
    }

    public Boolean getOl() {
        return ol;
    }

    public void setOl(Boolean ol) {
        this.ol = ol;
    }

    public String getOperations() {
        return operations;
    }

    public void setOperations(String operations) {
        this.operations = operations;
    }

    public String getAchieveRating() {
        return achieveRating;
    }

    public void setAchieveRating(String achieveRating) {
        this.achieveRating = achieveRating;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSplMachineKnowledge() {
        return splMachineKnowledge;
    }

    public void setSplMachineKnowledge(String splMachineKnowledge) {
        this.splMachineKnowledge = splMachineKnowledge;
    }
}

package io.vamani.application.model;

import io.vamani.application.domain.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class WorkerJoiningBean {

    private Long id;

    private String name;

    private String guardianName;

    private String motherName;

    private String mobileNo;

    private String esiNo;

    private String uanNo;

    private String permanentAddress;

    private String localAddress;

    private String supervisorName;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private String maritalStatus;

    private LocalDate joinDate;

    private String aadharNo;

    private LocalDate dob;

    private String panNo;

    private String bankBranch;

    private String bankAccNo;

    private String gender;

    private String grade;

    private Double ctc;

    private String subdeptId;

    private String email;

    private String catCode;

    private String flCode;

    private String foodCode;

    private String gCode;

    private String secCode;

    private String sftCode;

    private String salMode;

    private String punch;

    private String ot;

    private String sftType;

    private String swCode;

    private String wCode;

    private String cardNo;

    private String payCode;

    private String status;

    private boolean allowEntry;

    private DesignationMaster designationMaster;

    private DepartmentMaster departmentMaster;

    private WorkerRecruitment workerRecruitment;

    private BankMaster bankMaster;

    private WorkerWorkFlowBean workerWorkFlowBean;

    private List<WorkerFamilyDetails> workerFamilyDetails;

    private List<WorkerJobsDetails> workerJobsDetails;

    private List<WorkerLanguageDetails> workerLanguageDetails;

    private List<WorkerNominationDetails> workerNominationDetails;

    private List<WorkerAddressDetails> workerAddressDetails;

    private List<WorkerEducationDetails> workerEducationDetails;

    private List<WorkerReferenceDetails> workerReferenceDetails;

    private List<WorkerDocumentDetails> workerDocumentDetails;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public WorkerJoiningBean name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public WorkerJoiningBean guardianName(String guardianName) {
        this.guardianName = guardianName;
        return this;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getMotherName() {
        return motherName;
    }

    public WorkerJoiningBean motherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public WorkerJoiningBean mobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEsiNo() {
        return esiNo;
    }

    public WorkerJoiningBean esiNo(String esiNo) {
        this.esiNo = esiNo;
        return this;
    }

    public void setEsiNo(String esiNo) {
        this.esiNo = esiNo;
    }

    public String getUanNo() {
        return uanNo;
    }

    public WorkerJoiningBean uanNo(String uanNo) {
        this.uanNo = uanNo;
        return this;
    }

    public void setUanNo(String uanNo) {
        this.uanNo = uanNo;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public WorkerJoiningBean permanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
        return this;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public WorkerJoiningBean localAddress(String localAddress) {
        this.localAddress = localAddress;
        return this;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public WorkerJoiningBean supervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
        return this;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerJoiningBean createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerJoiningBean createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public WorkerJoiningBean lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public WorkerJoiningBean lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public WorkerJoiningBean maritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public WorkerJoiningBean joinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
        return this;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public DesignationMaster getDesignationMaster() {
        return designationMaster;
    }

    public WorkerJoiningBean designationMaster(DesignationMaster designationMaster) {
        this.designationMaster = designationMaster;
        return this;
    }

    public void setDesignationMaster(DesignationMaster designationMaster) {
        this.designationMaster = designationMaster;
    }

    public DepartmentMaster getDepartmentMaster() {
        return departmentMaster;
    }

    public WorkerJoiningBean departmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
        return this;
    }

    public void setDepartmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
    }

    public WorkerRecruitment getWorkerRecruitment() {
        return workerRecruitment;
    }

    public WorkerJoiningBean workerRecruitment(WorkerRecruitment workerRecruitment) {
        this.workerRecruitment = workerRecruitment;
        return this;
    }

    public void setWorkerRecruitment(WorkerRecruitment workerRecruitment) {
        this.workerRecruitment = workerRecruitment;
    }

    public List<WorkerFamilyDetails> getWorkerFamilyDetails() {
        return workerFamilyDetails;
    }

    public void setWorkerFamilyDetails(List<WorkerFamilyDetails> workerFamilyDetails) {
        this.workerFamilyDetails = workerFamilyDetails;
    }

    public List<WorkerJobsDetails> getWorkerJobsDetails() {
        return workerJobsDetails;
    }

    public void setWorkerJobsDetails(List<WorkerJobsDetails> workerJobsDetails) {
        this.workerJobsDetails = workerJobsDetails;
    }

    public List<WorkerLanguageDetails> getWorkerLanguageDetails() {
        return workerLanguageDetails;
    }

    public void setWorkerLanguageDetails(List<WorkerLanguageDetails> workerLanguageDetails) {
        this.workerLanguageDetails = workerLanguageDetails;
    }

    public List<WorkerNominationDetails> getWorkerNominationDetails() {
        return workerNominationDetails;
    }

    public void setWorkerNominationDetails(List<WorkerNominationDetails> workerNominationDetails) {
        this.workerNominationDetails = workerNominationDetails;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BankMaster getBankMaster() {
        return bankMaster;
    }

    public void setBankMaster(BankMaster bankMaster) {
        this.bankMaster = bankMaster;
    }

    public String getSftType() {
        return sftType;
    }

    public void setSftType(String sftType) {
        this.sftType = sftType;
    }

    public List<WorkerAddressDetails> getWorkerAddressDetails() {
        return workerAddressDetails;
    }

    public void setWorkerAddressDetails(List<WorkerAddressDetails> workerAddressDetails) {
        this.workerAddressDetails = workerAddressDetails;
    }

    public List<WorkerEducationDetails> getWorkerEducationDetails() {
        return workerEducationDetails;
    }

    public void setWorkerEducationDetails(List<WorkerEducationDetails> workerEducationDetails) {
        this.workerEducationDetails = workerEducationDetails;
    }

    public List<WorkerReferenceDetails> getWorkerReferenceDetails() {
        return workerReferenceDetails;
    }

    public void setWorkerReferenceDetails(List<WorkerReferenceDetails> workerReferenceDetails) {
        this.workerReferenceDetails = workerReferenceDetails;
    }

    public List<WorkerDocumentDetails> getWorkerDocumentDetails() {
        return workerDocumentDetails;
    }

    public void setWorkerDocumentDetails(List<WorkerDocumentDetails> workerDocumentDetails) {
        this.workerDocumentDetails = workerDocumentDetails;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
        this.ctc = ctc;
    }

    public String getSubdeptId() {
        return subdeptId;
    }

    public void setSubdeptId(String subdeptId) {
        this.subdeptId = subdeptId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCatCode() {
        return catCode;
    }

    public void setCatCode(String catCode) {
        this.catCode = catCode;
    }

    public String getFlCode() {
        return flCode;
    }

    public void setFlCode(String flCode) {
        this.flCode = flCode;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public String getgCode() {
        return gCode;
    }

    public void setgCode(String gCode) {
        this.gCode = gCode;
    }

    public String getSecCode() {
        return secCode;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getSftCode() {
        return sftCode;
    }

    public void setSftCode(String sftCode) {
        this.sftCode = sftCode;
    }

    public String getSalMode() {
        return salMode;
    }

    public void setSalMode(String salMode) {
        this.salMode = salMode;
    }

    public String getPunch() {
        return punch;
    }

    public void setPunch(String punch) {
        this.punch = punch;
    }

    public String getOt() {
        return ot;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getSwCode() {
        return swCode;
    }

    public void setSwCode(String swCode) {
        this.swCode = swCode;
    }

    public String getwCode() {
        return wCode;
    }

    public void setwCode(String wCode) {
        this.wCode = wCode;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAllowEntry() {
        return allowEntry;
    }

    public void setAllowEntry(boolean allowEntry) {
        this.allowEntry = allowEntry;
    }

    public WorkerWorkFlowBean getWorkerWorkFlowBean() {
        return workerWorkFlowBean;
    }

    public void setWorkerWorkFlowBean(WorkerWorkFlowBean workerWorkFlowBean) {
        this.workerWorkFlowBean = workerWorkFlowBean;
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
        WorkerJoiningBean workerJoining = (WorkerJoiningBean) o;
        if (workerJoining.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerJoining.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerJoiningBean{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", guardianName='" + guardianName + '\'' +
            ", motherName='" + motherName + '\'' +
            ", mobileNo='" + mobileNo + '\'' +
            ", esiNo='" + esiNo + '\'' +
            ", uanNo='" + uanNo + '\'' +
            ", permanentAddress='" + permanentAddress + '\'' +
            ", localAddress='" + localAddress + '\'' +
            ", supervisorName='" + supervisorName + '\'' +
            ", createdBy='" + createdBy + '\'' +
            ", createdDate=" + createdDate +
            ", lastUpdatedBy='" + lastUpdatedBy + '\'' +
            ", lastUpdatedDate=" + lastUpdatedDate +
            ", maritalStatus='" + maritalStatus + '\'' +
            ", joinDate=" + joinDate +
            ", aadharNo='" + aadharNo + '\'' +
            ", dob=" + dob +
            ", panNo='" + panNo + '\'' +
            ", bankBranch='" + bankBranch + '\'' +
            ", bankAccNo='" + bankAccNo + '\'' +
            ", gender='" + gender + '\'' +
            ", grade='" + grade + '\'' +
            ", ctc=" + ctc +
            ", subdeptId='" + subdeptId + '\'' +
            ", email='" + email + '\'' +
            ", catCode='" + catCode + '\'' +
            ", flCode='" + flCode + '\'' +
            ", foodCode='" + foodCode + '\'' +
            ", gCode='" + gCode + '\'' +
            ", secCode='" + secCode + '\'' +
            ", designationMaster=" + designationMaster +
            ", departmentMaster=" + departmentMaster +
            ", workerRecruitment=" + workerRecruitment +
            ", bankMaster=" + bankMaster +
            ", workerFamilyDetails=" + workerFamilyDetails +
            ", workerJobsDetails=" + workerJobsDetails +
            ", workerLanguageDetails=" + workerLanguageDetails +
            ", workerNominationDetails=" + workerNominationDetails +
            ", workerAddressDetails=" + workerAddressDetails +
            ", workerEducationDetails=" + workerEducationDetails +
            ", workerReferenceDetails=" + workerReferenceDetails +
            ", workerDocumentDetails=" + workerDocumentDetails +
            '}';
    }
}

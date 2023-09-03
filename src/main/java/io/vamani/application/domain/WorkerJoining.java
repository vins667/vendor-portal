package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A WorkerJoining.
 */
@Entity
@Table(name = "worker_joining")
public class WorkerJoining implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerJoiningSeq", sequenceName="worker_joining_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerJoiningSeq")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "guardian_name", length = 100, nullable = false)
    private String guardianName;

    @NotNull
    @Size(max = 100)
    @Column(name = "mother_name", length = 100, nullable = false)
    private String motherName;

    @Size(max = 15)
    @Column(name = "mobile_no", length = 15)
    private String mobileNo;

    @Size(max = 20)
    @Column(name = "esi_no", length = 20)
    private String esiNo;

    @Size(max = 20)
    @Column(name = "uan_no", length = 20)
    private String uanNo;

    @Size(max = 500)
    @Column(name = "permanent_address", length = 500)
    private String permanentAddress;

    @Size(max = 500)
    @Column(name = "local_address", length = 500)
    private String localAddress;

    @Size(max = 100)
    @Column(name = "supervisor_name", length = 100)
    private String supervisorName;

    @Size(max = 50)
    @Column(name = "created_by", length = 50)
    private String createdBy;

    @Column(name = "created_date")
    private Instant createdDate;

    @Size(max = 50)
    @Column(name = "last_updated_by", length = 50)
    private String lastUpdatedBy;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    @NotNull
    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate;

    @Size(max = 50)
    @Column(name = "aadhar_no", length = 50)
    private String aadharNo;

    @Column(name = "dob")
    private LocalDate dob;

    @Size(max = 50)
    @Column(name = "pan_no", length = 50)
    private String panNo;

    @Size(max = 200)
    @Column(name = "bank_branch", length = 200)
    private String bankBranch;

    @Size(max = 20)
    @Column(name = "bank_acc_no", length = 20)
    private String bankAccNo;

    @Size(max = 5)
    @Column(name = "grade", length = 1)
    private String grade;

    @Column(name = "ctc")
    private Double ctc;

    @Size(max = 1)
    @Column(name = "gender", length = 1)
    private String gender;

    @Size(max = 1)
    @Column(name = "marital_status", length = 1)
    private String maritalStatus;

    @Column(name = "subdept_id")
    private String subdeptId;

    @Column(name = "email")
    private String email;;

    @Column(name = "cat_code")
    private String catCode;

    @Column(name = "fl_code")
    private String flCode;

    @Column(name = "food_code")
    private String foodCode;

    @Column(name = "g_code")
    private String gCode;

    @Column(name = "sec_code")
    private String secCode;

    @Column(name = "sft_code")
    private String sftCode;

    @Column(name = "sal_mode")
    private String salMode;

    @Column(name = "punch")
    private String punch;

    @Column(name = "ot")
    private String ot;

    @Column(name = "sft_type")
    private String sftType;

    @Column(name = "sw_code")
    private String swCode;

    @Column(name = "w_code")
    private String wCode;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "pay_code")
    private String payCode;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "designation_master_id")
    @JsonIgnoreProperties("")
    private DesignationMaster designationMaster;

    @ManyToOne
    @JoinColumn(name = "department_master_id")
    @JsonIgnoreProperties("")
    private DepartmentMaster departmentMaster;

    @ManyToOne
    @JoinColumn(name = "worker_recruitment_id")
    @JsonIgnoreProperties("")
    private WorkerRecruitment workerRecruitment;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "bank_master_id")
    @JsonIgnoreProperties("")
    private BankMaster bankMaster;

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

    public WorkerJoining name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public WorkerJoining guardianName(String guardianName) {
        this.guardianName = guardianName;
        return this;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getMotherName() {
        return motherName;
    }

    public WorkerJoining motherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public WorkerJoining mobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEsiNo() {
        return esiNo;
    }

    public WorkerJoining esiNo(String esiNo) {
        this.esiNo = esiNo;
        return this;
    }

    public void setEsiNo(String esiNo) {
        this.esiNo = esiNo;
    }

    public String getUanNo() {
        return uanNo;
    }

    public WorkerJoining uanNo(String uanNo) {
        this.uanNo = uanNo;
        return this;
    }

    public void setUanNo(String uanNo) {
        this.uanNo = uanNo;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public WorkerJoining permanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
        return this;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public WorkerJoining localAddress(String localAddress) {
        this.localAddress = localAddress;
        return this;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public WorkerJoining supervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
        return this;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public WorkerJoining createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public WorkerJoining createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public WorkerJoining lastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
        return this;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public WorkerJoining lastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
        return this;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public WorkerJoining joinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
        return this;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public WorkerJoining aadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
        return this;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public LocalDate getDob() {
        return dob;
    }

    public WorkerJoining dob(LocalDate dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPanNo() {
        return panNo;
    }

    public WorkerJoining panNo(String panNo) {
        this.panNo = panNo;
        return this;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public WorkerJoining bankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
        return this;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getBankAccNo() {
        return bankAccNo;
    }

    public WorkerJoining bankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
        return this;
    }

    public void setBankAccNo(String bankAccNo) {
        this.bankAccNo = bankAccNo;
    }

    public String getGender() {
        return gender;
    }

    public WorkerJoining gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public WorkerJoining maritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
        return this;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public DesignationMaster getDesignationMaster() {
        return designationMaster;
    }

    public WorkerJoining designationMaster(DesignationMaster designationMaster) {
        this.designationMaster = designationMaster;
        return this;
    }

    public void setDesignationMaster(DesignationMaster designationMaster) {
        this.designationMaster = designationMaster;
    }

    public DepartmentMaster getDepartmentMaster() {
        return departmentMaster;
    }

    public WorkerJoining departmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
        return this;
    }

    public void setDepartmentMaster(DepartmentMaster departmentMaster) {
        this.departmentMaster = departmentMaster;
    }

    public WorkerRecruitment getWorkerRecruitment() {
        return workerRecruitment;
    }

    public WorkerJoining workerRecruitment(WorkerRecruitment workerRecruitment) {
        this.workerRecruitment = workerRecruitment;
        return this;
    }

    public void setWorkerRecruitment(WorkerRecruitment workerRecruitment) {
        this.workerRecruitment = workerRecruitment;
    }

    public BankMaster getBankMaster() {
        return bankMaster;
    }

    public WorkerJoining bankMaster(BankMaster bankMaster) {
        this.bankMaster = bankMaster;
        return this;
    }

    public void setBankMaster(BankMaster bankMaster) {
        this.bankMaster = bankMaster;
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

    public String getSftType() {
        return sftType;
    }

    public void setSftType(String sftType) {
        this.sftType = sftType;
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
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WorkerJoining workerJoining = (WorkerJoining) o;
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
        return "WorkerJoining{" +
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
            ", joinDate=" + joinDate +
            ", aadharNo='" + aadharNo + '\'' +
            ", dob=" + dob +
            ", panNo='" + panNo + '\'' +
            ", bankBranch='" + bankBranch + '\'' +
            ", bankAccNo='" + bankAccNo + '\'' +
            ", grade='" + grade + '\'' +
            ", ctc=" + ctc +
            ", gender='" + gender + '\'' +
            ", maritalStatus='" + maritalStatus + '\'' +
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
            '}';
    }
}

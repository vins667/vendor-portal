package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WorkerEducationDetails.
 */
@Entity
@Table(name = "worker_education_details")
public class WorkerEducationDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="workerEducationDetailsSeq", sequenceName="worker_education_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="workerEducationDetailsSeq")
    private Long id;

    @Size(max = 20)
    @Column(name = "duration", length = 20)
    private String duration;

    @Size(max = 6)
    @Column(name = "passing_year", length = 6)
    private String passingYear;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "education_master_id")
    @JsonIgnoreProperties("")
    private EducationMaster educationMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "education_type_master_id")
    @JsonIgnoreProperties("")
    private EducationTypeMaster educationTypeMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "institute_master_id")
    @JsonIgnoreProperties("")
    private InstituteMaster instituteMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "recruitment_country_master_id")
    @JsonIgnoreProperties("")
    private RecruitmentCountryMaster recruitmentCountryMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "recruitment_state_master_id")
    @JsonIgnoreProperties("")
    private RecruitmentStateMaster recruitmentStateMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "recruitment_district_id")
    @JsonIgnoreProperties("")
    private RecruitmentDistrict recruitmentDistrict;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "recruitment_city_master_id")
    @JsonIgnoreProperties("")
    private RecruitmentCityMaster recruitmentCityMaster;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "worker_joining_id")
    @JsonIgnoreProperties("")
    private WorkerJoining workerJoining;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public WorkerEducationDetails duration(String duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public WorkerEducationDetails passingYear(String passingYear) {
        this.passingYear = passingYear;
        return this;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public EducationMaster getEducationMaster() {
        return educationMaster;
    }

    public WorkerEducationDetails educationMaster(EducationMaster educationMaster) {
        this.educationMaster = educationMaster;
        return this;
    }

    public void setEducationMaster(EducationMaster educationMaster) {
        this.educationMaster = educationMaster;
    }

    public EducationTypeMaster getEducationTypeMaster() {
        return educationTypeMaster;
    }

    public WorkerEducationDetails educationTypeMaster(EducationTypeMaster educationTypeMaster) {
        this.educationTypeMaster = educationTypeMaster;
        return this;
    }

    public void setEducationTypeMaster(EducationTypeMaster educationTypeMaster) {
        this.educationTypeMaster = educationTypeMaster;
    }

    public InstituteMaster getInstituteMaster() {
        return instituteMaster;
    }

    public WorkerEducationDetails instituteMaster(InstituteMaster instituteMaster) {
        this.instituteMaster = instituteMaster;
        return this;
    }

    public void setInstituteMaster(InstituteMaster instituteMaster) {
        this.instituteMaster = instituteMaster;
    }

    public RecruitmentCountryMaster getRecruitmentCountryMaster() {
        return recruitmentCountryMaster;
    }

    public WorkerEducationDetails recruitmentCountryMaster(RecruitmentCountryMaster recruitmentCountryMaster) {
        this.recruitmentCountryMaster = recruitmentCountryMaster;
        return this;
    }

    public void setRecruitmentCountryMaster(RecruitmentCountryMaster recruitmentCountryMaster) {
        this.recruitmentCountryMaster = recruitmentCountryMaster;
    }

    public RecruitmentStateMaster getRecruitmentStateMaster() {
        return recruitmentStateMaster;
    }

    public WorkerEducationDetails recruitmentStateMaster(RecruitmentStateMaster recruitmentStateMaster) {
        this.recruitmentStateMaster = recruitmentStateMaster;
        return this;
    }

    public void setRecruitmentStateMaster(RecruitmentStateMaster recruitmentStateMaster) {
        this.recruitmentStateMaster = recruitmentStateMaster;
    }

    public RecruitmentDistrict getRecruitmentDistrict() {
        return recruitmentDistrict;
    }

    public WorkerEducationDetails recruitmentDistrict(RecruitmentDistrict recruitmentDistrict) {
        this.recruitmentDistrict = recruitmentDistrict;
        return this;
    }

    public void setRecruitmentDistrict(RecruitmentDistrict recruitmentDistrict) {
        this.recruitmentDistrict = recruitmentDistrict;
    }

    public RecruitmentCityMaster getRecruitmentCityMaster() {
        return recruitmentCityMaster;
    }

    public WorkerEducationDetails recruitmentCityMaster(RecruitmentCityMaster recruitmentCityMaster) {
        this.recruitmentCityMaster = recruitmentCityMaster;
        return this;
    }

    public void setRecruitmentCityMaster(RecruitmentCityMaster recruitmentCityMaster) {
        this.recruitmentCityMaster = recruitmentCityMaster;
    }

    public WorkerJoining getWorkerJoining() {
        return workerJoining;
    }

    public WorkerEducationDetails workerJoining(WorkerJoining workerJoining) {
        this.workerJoining = workerJoining;
        return this;
    }

    public void setWorkerJoining(WorkerJoining workerJoining) {
        this.workerJoining = workerJoining;
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
        WorkerEducationDetails workerEducationDetails = (WorkerEducationDetails) o;
        if (workerEducationDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), workerEducationDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WorkerEducationDetails{" +
            "id=" + getId() +
            ", duration='" + getDuration() + "'" +
            ", passingYear='" + getPassingYear() + "'" +
            "}";
    }
}

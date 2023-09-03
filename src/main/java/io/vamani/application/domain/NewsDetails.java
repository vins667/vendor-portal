package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A NewsDetails.
 */
@Entity
@Table(name = "news_details")
public class NewsDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="newsDetailsSeq", sequenceName="news_details_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="newsDetailsSeq")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "news_title", length = 255, nullable = false)
    private String newsTitle;

    @NotNull
    @Column(name = "display_days", nullable = false)
    private Integer displayDays;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotNull
    @Size(max = 1)
    @Column(name = "flag", length = 1, nullable = false)
    private String flag;

    @NotNull
    @Column(name = "mail_flag", nullable = false)
    private Boolean mailFlag;

    @NotNull
    @Column(name = "notification_flag", nullable = false)
    private Boolean notificationFlag;
    
    @Column(name = "short_closed_by")
    private String shortClosedBy;

    @NotNull
    @Size(max = 50)
    @Column(name = "created_by", length = 50, nullable = false)
    private String createdBy;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private Instant createdDate;

    @Column(name = "approved_by")
    private String approvedBy;

    @Column(name = "approved_date")
    private Instant approvedDate;

    @Size(max = 50)
    @Column(name = "emp_code", length = 50)
    private String empCode;

    @Size(max = 250)
    @Column(name = "emp_name", length = 250)
    private String empName;

    @Size(max = 250)
    @Column(name = "image_url", length = 250)
    private String imageUrl;

    @OneToMany(mappedBy = "newsDetails")
    private Set<NewsDetailsBody> newsDetailsBodies = new HashSet<>();

    @OneToMany(mappedBy = "newsDetails")
    private Set<NewsDetailsAttach> newsDetailsAttaches = new HashSet<>();

    @ManyToMany
    @NotNull
    @JoinTable(name = "news_details_factory_master",
        joinColumns = @JoinColumn(name = "news_details_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "factory_masters_id", referencedColumnName = "id"))
    private Set<FactoryMaster> factoryMasters = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "news_master_id")
    private NewsMaster newsMaster;
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public NewsDetails newsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
        return this;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public Integer getDisplayDays() {
        return displayDays;
    }

    public NewsDetails displayDays(Integer displayDays) {
        this.displayDays = displayDays;
        return this;
    }

    public void setDisplayDays(Integer displayDays) {
        this.displayDays = displayDays;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public NewsDetails endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getFlag() {
        return flag;
    }

    public NewsDetails flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Boolean isMailFlag() {
        return mailFlag;
    }

    public NewsDetails mailFlag(Boolean mailFlag) {
        this.mailFlag = mailFlag;
        return this;
    }

    public void setMailFlag(Boolean mailFlag) {
        this.mailFlag = mailFlag;
    }

    public Boolean isNotificationFlag() {
        return notificationFlag;
    }

    public NewsDetails notificationFlag(Boolean notificationFlag) {
        this.notificationFlag = notificationFlag;
        return this;
    }

    public void setNotificationFlag(Boolean notificationFlag) {
        this.notificationFlag = notificationFlag;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public NewsDetails createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public NewsDetails createdDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public NewsDetails approvedBy(String approvedBy) {
        this.approvedBy = approvedBy;
        return this;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Instant getApprovedDate() {
        return approvedDate;
    }

    public NewsDetails approvedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
        return this;
    }

    public void setApprovedDate(Instant approvedDate) {
        this.approvedDate = approvedDate;
    }public String getEmpCode() {
        return empCode;
    }

    public NewsDetails empCode(String empCode) {
        this.empCode = empCode;
        return this;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public NewsDetails empName(String empName) {
        this.empName = empName;
        return this;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public NewsDetails imageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<NewsDetailsBody> getNewsDetailsBodies() {
        return newsDetailsBodies;
    }

    public NewsDetails newsDetailsBodies(Set<NewsDetailsBody> newsDetailsBodies) {
        this.newsDetailsBodies = newsDetailsBodies;
        return this;
    }

    public NewsDetails addNewsDetailsBody(NewsDetailsBody newsDetailsBody) {
        this.newsDetailsBodies.add(newsDetailsBody);
        newsDetailsBody.setNewsDetails(this);
        return this;
    }

    public NewsDetails removeNewsDetailsBody(NewsDetailsBody newsDetailsBody) {
        this.newsDetailsBodies.remove(newsDetailsBody);
        newsDetailsBody.setNewsDetails(null);
        return this;
    }

    public void setNewsDetailsBodies(Set<NewsDetailsBody> newsDetailsBodies) {
        this.newsDetailsBodies = newsDetailsBodies;
    }

    public Set<NewsDetailsAttach> getNewsDetailsAttaches() {
        return newsDetailsAttaches;
    }

    public NewsDetails newsDetailsAttaches(Set<NewsDetailsAttach> newsDetailsAttaches) {
        this.newsDetailsAttaches = newsDetailsAttaches;
        return this;
    }

    public NewsDetails addNewsDetailsAttach(NewsDetailsAttach newsDetailsAttach) {
        this.newsDetailsAttaches.add(newsDetailsAttach);
        newsDetailsAttach.setNewsDetails(this);
        return this;
    }

    public NewsDetails removeNewsDetailsAttach(NewsDetailsAttach newsDetailsAttach) {
        this.newsDetailsAttaches.remove(newsDetailsAttach);
        newsDetailsAttach.setNewsDetails(null);
        return this;
    }

    public void setNewsDetailsAttaches(Set<NewsDetailsAttach> newsDetailsAttaches) {
        this.newsDetailsAttaches = newsDetailsAttaches;
    }

    public Set<FactoryMaster> getFactoryMasters() {
        return factoryMasters;
    }

    public NewsDetails factoryMasters(Set<FactoryMaster> factoryMasters) {
        this.factoryMasters = factoryMasters;
        return this;
    }

    public void setFactoryMasters(Set<FactoryMaster> factoryMasters) {
        this.factoryMasters = factoryMasters;
    }


    public NewsMaster getNewsMaster() {
        return newsMaster;
    }

    public NewsDetails newsMaster(NewsMaster newsMaster) {
        this.newsMaster = newsMaster;
        return this;
    }

    public void setNewsMaster(NewsMaster newsMaster) {
        this.newsMaster = newsMaster;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public String getShortClosedBy() {
		return shortClosedBy;
	}

	public void setShortClosedBy(String shortClosedBy) {
		this.shortClosedBy = shortClosedBy;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NewsDetails newsDetails = (NewsDetails) o;
        if (newsDetails.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), newsDetails.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NewsDetails{" +
            "id=" + getId() +
            ", newsTitle='" + getNewsTitle() + "'" +
            ", displayDays=" + getDisplayDays() +
            ", endDate='" + getEndDate() + "'" +
            ", flag='" + getFlag() + "'" +
            ", mailFlag='" + isMailFlag() + "'" +
            ", notificationFlag='" + isNotificationFlag() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", approvedBy='" + getApprovedBy() + "'" +
            ", approvedDate='" + getApprovedDate() + "'" +
            ", empCode='" + getEmpCode() + "'" +
            ", empName='" + getEmpName() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            "}";
    }
}

package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

/**
 * A VcutMainEntryMaster.
 */
@Entity
@Table(name = "vcut_main_entry_master")
public class VcutMainEntryMasterEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="vcutMainEntryMasterSeq", sequenceName="vcut_main_entry_master_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="vcutMainEntryMasterSeq")
    private Long id;

    @NotNull
    @Size(max = 3)
    @Column(name = "entry_type", length = 3, nullable = false)
    private String entryType;

    @NotNull
    @Column(name = "entry_time", nullable = false)
    private Instant entryTime;

    @Size(max = 50)
    @Column(name = "entry_by", length = 50)
    private String entryBy;

    @Size(max = 50)
    @Column(name = "rectified_by", length = 50)
    private String rectifiedBy;

    @Column(name = "rectified_date")
    private Instant rectifiedDate;

    @Size(max = 50)
    @Column(name = "sticker_number", length = 50)
    private String stickerNumber;

    @Column(name = "vcut_style_plan_upload_id")
    private Long vcutStylePlanUploadId;

    @Column(name = "vcut_operation_master_id")
    private Long vcutOperationMasterId;

    @Column(name = "cut_plan_bundle_details_id")
    private Long cutPlanBundleDetailsId;

    @Column(name = "cut_plan_bundle_id")
    private Long cutPlanBundleId;

    @Column(name = "final_operation")
    private String finalOperation;

    @Size(max = 1)
    @Column(name = "post_flag", length = 1)
    private String postFlag;

    @Column(name = "post_date")
    private Instant postDate;

    /*@OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "vcut_main_entry_master_id", referencedColumnName = "id")
    private List<VcutMainEntryIssueDetails> vcutMainEntryIssueDetails;*/

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntryType() {
        return entryType;
    }

    public VcutMainEntryMasterEntity entryType(String entryType) {
        this.entryType = entryType;
        return this;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }

    public Instant getEntryTime() {
        return entryTime;
    }

    public VcutMainEntryMasterEntity entryTime(Instant entryTime) {
        this.entryTime = entryTime;
        return this;
    }

    public void setEntryTime(Instant entryTime) {
        this.entryTime = entryTime;
    }

    public String getEntryBy() {
        return entryBy;
    }

    public VcutMainEntryMasterEntity entryBy(String entryBy) {
        this.entryBy = entryBy;
        return this;
    }

    public void setEntryBy(String entryBy) {
        this.entryBy = entryBy;
    }

    public String getRectifiedBy() {
        return rectifiedBy;
    }

    public VcutMainEntryMasterEntity rectifiedBy(String rectifiedBy) {
        this.rectifiedBy = rectifiedBy;
        return this;
    }

    public void setRectifiedBy(String rectifiedBy) {
        this.rectifiedBy = rectifiedBy;
    }

    public Instant getRectifiedDate() {
        return rectifiedDate;
    }

    public VcutMainEntryMasterEntity rectifiedDate(Instant rectifiedDate) {
        this.rectifiedDate = rectifiedDate;
        return this;
    }

    public void setRectifiedDate(Instant rectifiedDate) {
        this.rectifiedDate = rectifiedDate;
    }

    public Long getVcutStylePlanUploadId() {
        return vcutStylePlanUploadId;
    }

    public VcutMainEntryMasterEntity vcutStylePlanUploadId(Long vcutStylePlanUploadId) {
        this.vcutStylePlanUploadId = vcutStylePlanUploadId;
        return this;
    }

    public void setVcutStylePlanUploadId(Long vcutStylePlanUploadId) {
        this.vcutStylePlanUploadId = vcutStylePlanUploadId;
    }

    public Long getVcutOperationMasterId() {
        return vcutOperationMasterId;
    }

    public VcutMainEntryMasterEntity vcutOperationMasterId(Long vcutOperationMasterId) {
        this.vcutOperationMasterId = vcutOperationMasterId;
        return this;
    }

    public void setVcutOperationMasterId(Long vcutOperationMasterId) {
        this.vcutOperationMasterId = vcutOperationMasterId;
    }

    /*public List<VcutMainEntryIssueDetails> getVcutMainEntryIssueDetails() {
        return vcutMainEntryIssueDetails;
    }

    public void setVcutMainEntryIssueDetails(List<VcutMainEntryIssueDetails> vcutMainEntryIssueDetails) {
        this.vcutMainEntryIssueDetails = vcutMainEntryIssueDetails;
    }*/

    public String getStickerNumber() {
        return stickerNumber;
    }

    public void setStickerNumber(String stickerNumber) {
        this.stickerNumber = stickerNumber;
    }

    public Long getCutPlanBundleDetailsId() {
        return cutPlanBundleDetailsId;
    }

    public void setCutPlanBundleDetailsId(Long cutPlanBundleDetailsId) {
        this.cutPlanBundleDetailsId = cutPlanBundleDetailsId;
    }

    public Long getCutPlanBundleId() {
        return cutPlanBundleId;
    }

    public void setCutPlanBundleId(Long cutPlanBundleId) {
        this.cutPlanBundleId = cutPlanBundleId;
    }

    public String getFinalOperation() {
        return finalOperation;
    }

    public void setFinalOperation(String finalOperation) {
        this.finalOperation = finalOperation;
    }

    public String getPostFlag() {
        return postFlag;
    }

    public void setPostFlag(String postFlag) {
        this.postFlag = postFlag;
    }

    public Instant getPostDate() {
        return postDate;
    }

    public void setPostDate(Instant postDate) {
        this.postDate = postDate;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VcutMainEntryMasterEntity)) {
            return false;
        }
        return id != null && id.equals(((VcutMainEntryMasterEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VcutMainEntryMasterEntity{" +
            "id=" + getId() +
            ", entryType='" + getEntryType() + "'" +
            ", entryTime='" + getEntryTime() + "'" +
            ", entryBy='" + getEntryBy() + "'" +
            ", rectifiedBy='" + getRectifiedBy() + "'" +
            ", rectifiedDate='" + getRectifiedDate() + "'" +
            "}";
    }
}

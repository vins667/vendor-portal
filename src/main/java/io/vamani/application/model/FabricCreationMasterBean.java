package io.vamani.application.model;

import io.vamani.application.domain.FabricCreationContentDetails;
import io.vamani.application.domain.FabricCreationWarpDetails;
import io.vamani.application.domain.FabricCreationWeftDetails;
import io.vamani.application.domain.FabricSubstractMaster;
import io.vamani.application.domain.FabricSubstractDetails;
import io.vamani.application.domain.FabricSplFinishMaster;
import io.vamani.application.domain.FabricOthersMaster;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class FabricCreationMasterBean implements Serializable {
    private Long id;

    private String code;

    private String description;

    private Double epi;


    private Double ppi;

    private String oth;

    private String status;

    private String createdBy;

    private Instant createdDate;

    private String lastUpdatedBy;

    private Instant lastUpdatedDate;

    private FabricSubstractMaster fabricSubstractMaster;

    private FabricSubstractDetails fabricSubstractDetails;

    private FabricSplFinishMaster fabricSplFinishMaster;

    private FabricOthersMaster fabricOthersMaster;

    private Set<FabricCreationWeftDetails> fabricCreationWeftDetails = new HashSet<>();

    private Set<FabricCreationWarpDetails> fabricCreationWarpDetails = new HashSet<>();

    private Set<FabricCreationContentDetails> fabricCreationContentDetails = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getEpi() {
        return epi;
    }

    public void setEpi(Double epi) {
        this.epi = epi;
    }

    public Double getPpi() {
        return ppi;
    }

    public void setPpi(Double ppi) {
        this.ppi = ppi;
    }

    public String getOth() {
        return oth;
    }

    public void setOth(String oth) {
        this.oth = oth;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public FabricSubstractMaster getFabricSubstractMaster() {
        return fabricSubstractMaster;
    }

    public void setFabricSubstractMaster(FabricSubstractMaster fabricSubstractMaster) {
        this.fabricSubstractMaster = fabricSubstractMaster;
    }

    public FabricSubstractDetails getFabricSubstractDetails() {
        return fabricSubstractDetails;
    }

    public void setFabricSubstractDetails(FabricSubstractDetails fabricSubstractDetails) {
        this.fabricSubstractDetails = fabricSubstractDetails;
    }

    public FabricSplFinishMaster getFabricSplFinishMaster() {
        return fabricSplFinishMaster;
    }

    public void setFabricSplFinishMaster(FabricSplFinishMaster fabricSplFinishMaster) {
        this.fabricSplFinishMaster = fabricSplFinishMaster;
    }

    public FabricOthersMaster getFabricOthersMaster() {
        return fabricOthersMaster;
    }

    public void setFabricOthersMaster(FabricOthersMaster fabricOthersMaster) {
        this.fabricOthersMaster = fabricOthersMaster;
    }

    public Set<FabricCreationWeftDetails> getFabricCreationWeftDetails() {
        return fabricCreationWeftDetails;
    }

    public void setFabricCreationWeftDetails(Set<FabricCreationWeftDetails> fabricCreationWeftDetails) {
        this.fabricCreationWeftDetails = fabricCreationWeftDetails;
    }

    public Set<FabricCreationWarpDetails> getFabricCreationWarpDetails() {
        return fabricCreationWarpDetails;
    }

    public void setFabricCreationWarpDetails(Set<FabricCreationWarpDetails> fabricCreationWarpDetails) {
        this.fabricCreationWarpDetails = fabricCreationWarpDetails;
    }

    public Set<FabricCreationContentDetails> getFabricCreationContentDetails() {
        return fabricCreationContentDetails;
    }

    public void setFabricCreationContentDetails(Set<FabricCreationContentDetails> fabricCreationContentDetails) {
        this.fabricCreationContentDetails = fabricCreationContentDetails;
    }
}

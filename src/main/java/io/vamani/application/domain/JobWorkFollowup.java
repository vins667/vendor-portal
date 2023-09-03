package io.vamani.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A JobWorkFollowup.
 */
@Entity
@Table(name = "job_work_followup")
public class JobWorkFollowup implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "jobWorkFollowupSeq", sequenceName = "job_work_followup_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "jobWorkFollowupSeq")
    private Long id;


    @Size(max = 20)
    @Column(name = "jobworkcode", length = 20, nullable = true)
    private String jobworkcode;


    @Size(max = 200)
    @Column(name = "jobworkname", length = 200, nullable = true)
    private String jobworkname;


    @Size(max = 20)
    @Column(name = "buyercode", length = 20, nullable = true)
    private String buyercode;


    @Size(max = 200)
    @Column(name = "buyername", length = 200, nullable = true)
    private String buyername;


    @Size(max = 50)
    @Column(name = "responsiblepersoncode_01", length = 50, nullable = true)
    private String responsiblepersoncode01;


    @Size(max = 200)
    @Column(name = "responsiblepersonname_01", length = 200, nullable = true)
    private String responsiblepersonname01;


    @Size(max = 100)
    @Column(name = "responsiblepersonmail_01", length = 100, nullable = true)
    private String responsiblepersonmail01;


    @Size(max = 50)
    @Column(name = "responsiblepersoncode_02", length = 50, nullable = true)
    private String responsiblepersoncode02;


    @Size(max = 200)
    @Column(name = "responsiblepersonname_02", length = 200, nullable = true)
    private String responsiblepersonname02;


    @Size(max = 100)
    @Column(name = "responsiblepersonmail_02", length = 100, nullable = true)
    private String responsiblepersonmail02;


    @Size(max = 50)
    @Column(name = "responsiblepersoncode_03", length = 50, nullable = true)
    private String responsiblepersoncode03;


    @Size(max = 200)
    @Column(name = "responsiblepersonname_03", length = 200, nullable = true)
    private String responsiblepersonname03;


    @Size(max = 100)
    @Column(name = "responsiblepersonmail_03", length = 100, nullable = true)
    private String responsiblepersonmail03;


    @Size(max = 50)
    @Column(name = "level_01_reminderpersoncode_01", length = 50, nullable = true)
    private String level01reminderpersoncode01;


    @Size(max = 200)
    @Column(name = "level_01_reminderpersonname_01", length = 200, nullable = true)
    private String level01reminderpersonname01;


    @Size(max = 100)
    @Column(name = "level_01_reminderpersonmail_01", length = 100, nullable = true)
    private String level01reminderpersonmail01;


    @Size(max = 50)
    @Column(name = "level_01_reminderpersoncode_02", length = 50, nullable = true)
    private String level01reminderpersoncode02;


    @Size(max = 200)
    @Column(name = "level_01_reminderpersonname_02", length = 200, nullable = true)
    private String level01reminderpersonname02;


    @Size(max = 100)
    @Column(name = "level_01_reminderpersonmail_02", length = 100, nullable = true)
    private String level01reminderpersonmail02;


    @Size(max = 50)
    @Column(name = "level_01_reminderpersoncode_03", length = 50, nullable = true)
    private String level01reminderpersoncode03;


    @Size(max = 200)
    @Column(name = "level_01_reminderpersonname_03", length = 200, nullable = true)
    private String level01reminderpersonname03;


    @Size(max = 100)
    @Column(name = "level_01_reminderpersonmail_03", length = 100, nullable = true)
    private String level01reminderpersonmail03;


    @Size(max = 50)
    @Column(name = "level_02_reminderpersoncode_01", length = 50, nullable = true)
    private String level02reminderpersoncode01;


    @Size(max = 200)
    @Column(name = "level_02_reminderpersonname_01", length = 200, nullable = true)
    private String level02reminderpersonname01;


    @Size(max = 100)
    @Column(name = "level_02_reminderpersonmail_01", length = 100, nullable = true)
    private String level02reminderpersonmail01;


    @Size(max = 50)
    @Column(name = "level_02_reminderpersoncode_02", length = 50, nullable = true)
    private String level02reminderpersoncode02;


    @Size(max = 200)
    @Column(name = "level_02_reminderpersonname_02", length = 200, nullable = true)
    private String level02reminderpersonname02;


    @Size(max = 100)
    @Column(name = "level_02_reminderpersonmail_02", length = 100, nullable = true)
    private String level02reminderpersonmail02;


    @Size(max = 50)
    @Column(name = "level_02_reminderpersoncode_03", length = 50, nullable = true)
    private String level02reminderpersoncode03;


    @Size(max = 200)
    @Column(name = "level_02_reminderpersonname_03", length = 200, nullable = true)
    private String level02reminderpersonname03;


    @Size(max = 100)
    @Column(name = "level_02_reminderpersonmail_03", length = 100, nullable = true)
    private String level02reminderpersonmail03;


    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobworkcode() {
        return jobworkcode;
    }

    public JobWorkFollowup jobworkcode(String jobworkcode) {
        this.jobworkcode = jobworkcode;
        return this;
    }

    public void setJobworkcode(String jobworkcode) {

        this.jobworkcode = jobworkcode != null ? jobworkcode.toUpperCase() : jobworkcode;
    }

    public String getJobworkname() {
        return jobworkname;
    }

    public JobWorkFollowup jobworkname(String jobworkname) {
        this.jobworkname = jobworkname;
        return this;
    }

    public void setJobworkname(String jobworkname) {

        this.jobworkname = jobworkname != null ? jobworkname.toUpperCase() : jobworkname;
    }

    public String getBuyercode() {
        return buyercode;
    }

    public JobWorkFollowup buyercode(String buyercode) {
        this.buyercode = buyercode;
        return this;
    }

    public void setBuyercode(String buyercode) {
        this.buyercode = buyercode != null ? buyercode.toUpperCase() : buyercode;
    }

    public String getBuyername() {
        return buyername;
    }

    public JobWorkFollowup buyername(String buyername) {
        this.buyername = buyername;
        return this;
    }

    public void setBuyername(String buyername) {

        this.buyername = buyername != null ? buyername.toUpperCase() : buyername;
    }

    public String getResponsiblepersoncode01() {
        return responsiblepersoncode01;
    }

    public JobWorkFollowup responsiblepersoncode01(String responsiblepersoncode01) {
        this.responsiblepersoncode01 = responsiblepersoncode01;
        return this;
    }

    public void setResponsiblepersoncode01(String responsiblepersoncode01) {
        this.responsiblepersoncode01 = responsiblepersoncode01 != null ? responsiblepersoncode01.toUpperCase() : responsiblepersoncode01;
    }

    public String getResponsiblepersonname01() {
        return responsiblepersonname01;
    }

    public JobWorkFollowup responsiblepersonname01(String responsiblepersonname01) {
        this.responsiblepersonname01 = responsiblepersonname01;
        return this;
    }

    public void setResponsiblepersonname01(String responsiblepersonname01) {
        this.responsiblepersonname01 = responsiblepersonname01;
    }

    public String getResponsiblepersoncode02() {
        return responsiblepersoncode02;
    }

    public JobWorkFollowup responsiblepersoncode02(String responsiblepersoncode02) {
        this.responsiblepersoncode02 = responsiblepersoncode02;
        return this;
    }

    public void setResponsiblepersoncode02(String responsiblepersoncode02) {
        this.responsiblepersoncode02 = responsiblepersoncode02 != null ? responsiblepersoncode02.toUpperCase() : responsiblepersoncode02;
    }

    public String getResponsiblepersonname02() {
        return responsiblepersonname02;
    }

    public JobWorkFollowup responsiblepersonname02(String responsiblepersonname02) {
        this.responsiblepersonname02 = responsiblepersonname02;
        return this;
    }

    public void setResponsiblepersonname02(String responsiblepersonname02) {
        this.responsiblepersonname02 = responsiblepersonname02;
    }

    public String getResponsiblepersoncode03() {
        return responsiblepersoncode03;
    }

    public JobWorkFollowup responsiblepersoncode03(String responsiblepersoncode03) {
        this.responsiblepersoncode03 = responsiblepersoncode03;
        return this;
    }

    public void setResponsiblepersoncode03(String responsiblepersoncode03) {
        this.responsiblepersoncode03 = responsiblepersoncode03;
    }

    public String getResponsiblepersonname03() {
        return responsiblepersonname03;
    }

    public JobWorkFollowup responsiblepersonname03(String responsiblepersonname03) {
        this.responsiblepersonname03 = responsiblepersonname03;
        return this;
    }

    public void setResponsiblepersonname03(String responsiblepersonname03) {
        this.responsiblepersonname03 = responsiblepersonname03;
    }

    public String getLevel01reminderpersoncode01() {
        return level01reminderpersoncode01;
    }

    public JobWorkFollowup level01reminderpersoncode01(String level01reminderpersoncode01) {
        this.level01reminderpersoncode01 = level01reminderpersoncode01;
        return this;
    }

    public void setLevel01reminderpersoncode01(String level01reminderpersoncode01) {
        this.level01reminderpersoncode01 = level01reminderpersoncode01;
    }

    public String getLevel01reminderpersonname01() {
        return level01reminderpersonname01;
    }

    public JobWorkFollowup level01reminderpersonname01(String level01reminderpersonname01) {
        this.level01reminderpersonname01 = level01reminderpersonname01;
        return this;
    }

    public void setLevel01reminderpersonname01(String level01reminderpersonname01) {
        this.level01reminderpersonname01 = level01reminderpersonname01;
    }

    public String getLevel01reminderpersoncode02() {
        return level01reminderpersoncode02;
    }

    public JobWorkFollowup level01reminderpersoncode02(String level01reminderpersoncode02) {
        this.level01reminderpersoncode02 = level01reminderpersoncode02;
        return this;
    }

    public void setLevel01reminderpersoncode02(String level01reminderpersoncode02) {
        this.level01reminderpersoncode02 = level01reminderpersoncode02;
    }

    public String getLevel01reminderpersonname02() {
        return level01reminderpersonname02;
    }

    public JobWorkFollowup level01reminderpersonname02(String level01reminderpersonname02) {
        this.level01reminderpersonname02 = level01reminderpersonname02;
        return this;
    }

    public void setLevel01reminderpersonname02(String level01reminderpersonname02) {
        this.level01reminderpersonname02 = level01reminderpersonname02;
    }

    public String getLevel01reminderpersoncode03() {
        return level01reminderpersoncode03;
    }

    public JobWorkFollowup level01reminderpersoncode03(String level01reminderpersoncode03) {
        this.level01reminderpersoncode03 = level01reminderpersoncode03;
        return this;
    }

    public void setLevel01reminderpersoncode03(String level01reminderpersoncode03) {
        this.level01reminderpersoncode03 = level01reminderpersoncode03;
    }

    public String getLevel01reminderpersonname03() {
        return level01reminderpersonname03;
    }

    public JobWorkFollowup level01reminderpersonname03(String level01reminderpersonname03) {
        this.level01reminderpersonname03 = level01reminderpersonname03;
        return this;
    }

    public void setLevel01reminderpersonname03(String level01reminderpersonname03) {
        this.level01reminderpersonname03 = level01reminderpersonname03;
    }

    public String getLevel02reminderpersoncode01() {
        return level02reminderpersoncode01;
    }

    public JobWorkFollowup level02reminderpersoncode01(String level02reminderpersoncode01) {
        this.level02reminderpersoncode01 = level02reminderpersoncode01;
        return this;
    }

    public void setLevel02reminderpersoncode01(String level02reminderpersoncode01) {
        this.level02reminderpersoncode01 = level02reminderpersoncode01;
    }

    public String getLevel02reminderpersonname01() {
        return level02reminderpersonname01;
    }

    public JobWorkFollowup level02reminderpersonname01(String level02reminderpersonname01) {
        this.level02reminderpersonname01 = level02reminderpersonname01;
        return this;
    }

    public void setLevel02reminderpersonname01(String level02reminderpersonname01) {
        this.level02reminderpersonname01 = level02reminderpersonname01;
    }

    public String getLevel02reminderpersoncode02() {
        return level02reminderpersoncode02;
    }

    public JobWorkFollowup level02reminderpersoncode02(String level02reminderpersoncode02) {
        this.level02reminderpersoncode02 = level02reminderpersoncode02;
        return this;
    }

    public void setLevel02reminderpersoncode02(String level02reminderpersoncode02) {
        this.level02reminderpersoncode02 = level02reminderpersoncode02;
    }

    public String getLevel02reminderpersonname02() {
        return level02reminderpersonname02;
    }

    public JobWorkFollowup level02reminderpersonname02(String level02reminderpersonname02) {
        this.level02reminderpersonname02 = level02reminderpersonname02;
        return this;
    }

    public void setLevel02reminderpersonname02(String level02reminderpersonname02) {
        this.level02reminderpersonname02 = level02reminderpersonname02;
    }

    public String getLevel02reminderpersoncode03() {
        return level02reminderpersoncode03;
    }

    public JobWorkFollowup level02reminderpersoncode03(String level02reminderpersoncode03) {
        this.level02reminderpersoncode03 = level02reminderpersoncode03;
        return this;
    }

    public void setLevel02reminderpersoncode03(String level02reminderpersoncode03) {
        this.level02reminderpersoncode03 = level02reminderpersoncode03;
    }

    public String getLevel02reminderpersonname03() {
        return level02reminderpersonname03;
    }

    public JobWorkFollowup level02reminderpersonname03(String level02reminderpersonname03) {
        this.level02reminderpersonname03 = level02reminderpersonname03;
        return this;
    }

    public void setLevel02reminderpersonname03(String level02reminderpersonname03) {
        this.level02reminderpersonname03 = level02reminderpersonname03;
    }

    public String getResponsiblepersonmail01() {
        return responsiblepersonmail01;
    }

    public void setResponsiblepersonmail01(String responsiblepersonmail01) {
        this.responsiblepersonmail01 = responsiblepersonmail01;
    }

    public String getResponsiblepersonmail02() {
        return responsiblepersonmail02;
    }

    public void setResponsiblepersonmail02(String responsiblepersonmail02) {
        this.responsiblepersonmail02 = responsiblepersonmail02;
    }

    public String getResponsiblepersonmail03() {
        return responsiblepersonmail03;
    }

    public void setResponsiblepersonmail03(String responsiblepersonmail03) {
        this.responsiblepersonmail03 = responsiblepersonmail03;
    }

    public String getLevel01reminderpersonmail01() {
        return level01reminderpersonmail01;
    }

    public void setLevel01reminderpersonmail01(String level01reminderpersonmail01) {
        this.level01reminderpersonmail01 = level01reminderpersonmail01;
    }

    public String getLevel01reminderpersonmail02() {
        return level01reminderpersonmail02;
    }

    public void setLevel01reminderpersonmail02(String level01reminderpersonmail02) {
        this.level01reminderpersonmail02 = level01reminderpersonmail02;
    }

    public String getLevel01reminderpersonmail03() {
        return level01reminderpersonmail03;
    }

    public void setLevel01reminderpersonmail03(String level01reminderpersonmail03) {
        this.level01reminderpersonmail03 = level01reminderpersonmail03;
    }

    public String getLevel02reminderpersonmail01() {
        return level02reminderpersonmail01;
    }

    public void setLevel02reminderpersonmail01(String level02reminderpersonmail01) {
        this.level02reminderpersonmail01 = level02reminderpersonmail01;
    }

    public String getLevel02reminderpersonmail02() {
        return level02reminderpersonmail02;
    }

    public void setLevel02reminderpersonmail02(String level02reminderpersonmail02) {
        this.level02reminderpersonmail02 = level02reminderpersonmail02;
    }

    public String getLevel02reminderpersonmail03() {
        return level02reminderpersonmail03;
    }

    public void setLevel02reminderpersonmail03(String level02reminderpersonmail03) {
        this.level02reminderpersonmail03 = level02reminderpersonmail03;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobWorkFollowup)) {
            return false;
        }
        return id != null && id.equals(((JobWorkFollowup) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "JobWorkFollowup{" +
            "id=" + getId() +
            ", jobworkcode='" + getJobworkcode() + "'" +
            ", jobworkname='" + getJobworkname() + "'" +
            ", buyercode='" + getBuyercode() + "'" +
            ", buyername='" + getBuyername() + "'" +
            ", responsiblepersoncode01='" + getResponsiblepersoncode01() + "'" +
            ", responsiblepersonname01='" + getResponsiblepersonname01() + "'" +
            ", responsiblepersoncode02='" + getResponsiblepersoncode02() + "'" +
            ", responsiblepersonname02='" + getResponsiblepersonname02() + "'" +
            ", responsiblepersoncode03='" + getResponsiblepersoncode03() + "'" +
            ", responsiblepersonname03='" + getResponsiblepersonname03() + "'" +
            ", level01reminderpersoncode01='" + getLevel01reminderpersoncode01() + "'" +
            ", level01reminderpersonname01='" + getLevel01reminderpersonname01() + "'" +
            ", level01reminderpersoncode02='" + getLevel01reminderpersoncode02() + "'" +
            ", level01reminderpersonname02='" + getLevel01reminderpersonname02() + "'" +
            ", level01reminderpersoncode03='" + getLevel01reminderpersoncode03() + "'" +
            ", level01reminderpersonname03='" + getLevel01reminderpersonname03() + "'" +
            ", level02reminderpersoncode01='" + getLevel02reminderpersoncode01() + "'" +
            ", level02reminderpersonname01='" + getLevel02reminderpersonname01() + "'" +
            ", level02reminderpersoncode02='" + getLevel02reminderpersoncode02() + "'" +
            ", level02reminderpersonname02='" + getLevel02reminderpersonname02() + "'" +
            ", level02reminderpersoncode03='" + getLevel02reminderpersoncode03() + "'" +
            ", level02reminderpersonname03='" + getLevel02reminderpersonname03() + "'" +
            "}";
    }
}

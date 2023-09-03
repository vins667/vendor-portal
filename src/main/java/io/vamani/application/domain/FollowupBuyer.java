package io.vamani.application.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A FollowupBuyer.
 */
@Entity
@Table(name = "followup_buyer")
public class FollowupBuyer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "followupbuterSeq", sequenceName = "followup_buyer_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "followupbuterSeq")
    private Long id;

    @NotNull
    @Size(max = 20)
    @Column(name = "buyercode", length = 20, nullable = false)
    private String buyercode;

    @NotNull
    @Size(max = 200)
    @Column(name = "buyername", length = 200, nullable = false)
    private String buyername;

    @NotNull
    @Size(max = 1)
    @Column(name = "flag", length = 1, nullable = false)
    private String flag;


    @Size(max = 50)
    @Column(name = "createdby", length = 50)
    private String createdby;

    @Column(name = "createddate")
    private Instant createddate;


    @Size(max = 50)
    @Column(name = "updatedby", length = 50)
    private String updatedby;

    @Column(name = "updateddate")
    private Instant updateddate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyercode() {
        return buyercode;
    }

    public FollowupBuyer buyercode(String buyercode) {
        this.buyercode = buyercode;
        return this;
    }

    public void setBuyercode(String buyercode) {
        this.buyercode = buyercode != null ? buyercode.toUpperCase() : buyercode;
    }

    public String getBuyername() {
        return buyername;
    }

    public FollowupBuyer buyername(String buyername) {
        this.buyername = buyername;
        return this;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername != null ? buyername.toUpperCase() : buyername;
    }

    public String getFlag() {
        return flag;
    }

    public FollowupBuyer flag(String flag) {
        this.flag = flag;
        return this;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCreatedby() {
        return createdby;
    }

    public FollowupBuyer createdby(String createdby) {
        this.createdby = createdby;
        return this;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public Instant getCreateddate() {
        return createddate;
    }

    public FollowupBuyer createddate(Instant createddate) {
        this.createddate = createddate;
        return this;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public FollowupBuyer updatedby(String updatedby) {
        this.updatedby = updatedby;
        return this;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Instant getUpdateddate() {
        return updateddate;
    }

    public FollowupBuyer updateddate(Instant updateddate) {
        this.updateddate = updateddate;
        return this;
    }

    public void setUpdateddate(Instant updateddate) {
        this.updateddate = updateddate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FollowupBuyer)) {
            return false;
        }
        return id != null && id.equals(((FollowupBuyer) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FollowupBuyer{" +
            "id=" + getId() +
            ", buyercode='" + getBuyercode() + "'" +
            ", buyername='" + getBuyername() + "'" +
            ", flag='" + getFlag() + "'" +
            ", createdby='" + getCreatedby() + "'" +
            ", createddate='" + getCreateddate() + "'" +
            ", updatedby='" + getUpdatedby() + "'" +
            ", updateddate='" + getUpdateddate() + "'" +
            "}";
    }
}

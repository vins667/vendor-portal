package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name="operation")
public class Operation {
    @EmbeddedId
    private OperationId id;
    private String longdescription;
    private String shortdescription;
    private String searchdescription;
    private String externalreservationtype;
    private Short handlechildbundle;
    private Short dyelothandled;
    private String lossincreasemanagement1;
    private String lossincreasemanagement2;
    private String lossincreasemanagement3;
    private String lossincreasemanagement4;
    private String lossincreasemanagement5;
    private String lossincreasemanagement6;
    private String lossincreasemanagement7;
    private String lossincreasemanagement8;
    private String timemanagement1;
    private String linkedtime1;
    private Short specificuomtime1;
    private String timemanagement2;
    private String linkedtime2;
    private Short specificuomtime2;
    private String timemanagement3;
    private String linkedtime3;
    private Short specificuomtime3;
    private String timemanagement4;
    private String linkedtime4;
    private Short specificuomtime4;
    private String timemanagement5;
    private String linkedtime5;
    private Short specificuomtime5;
    private Timestamp creationdatetime;
    private String creationuser;
    private Timestamp lastupdatedatetime;
    private String lastupdateuser;
    private Long absuniqueid;

    public OperationId getId() {
        return id;
    }

    public void setId(OperationId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LONGDESCRIPTION", nullable = false, length = 100)
    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    @Basic
    @Column(name = "SHORTDESCRIPTION", nullable = true, length = 40)
    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    @Basic
    @Column(name = "SEARCHDESCRIPTION", nullable = true, length = 60)
    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    @Basic
    @Column(name = "EXTERNALRESERVATIONTYPE", nullable = true, length = 2)
    public String getExternalreservationtype() {
        return externalreservationtype;
    }

    public void setExternalreservationtype(String externalreservationtype) {
        this.externalreservationtype = externalreservationtype;
    }

    @Basic
    @Column(name = "HANDLECHILDBUNDLE", nullable = false)
    public Short getHandlechildbundle() {
        return handlechildbundle;
    }

    public void setHandlechildbundle(Short handlechildbundle) {
        this.handlechildbundle = handlechildbundle;
    }

    @Basic
    @Column(name = "DYELOTHANDLED", nullable = false)
    public Short getDyelothandled() {
        return dyelothandled;
    }

    public void setDyelothandled(Short dyelothandled) {
        this.dyelothandled = dyelothandled;
    }

    @Basic
    @Column(name = "LOSSINCREASEMANAGEMENT1", nullable = false, length = 2)
    public String getLossincreasemanagement1() {
        return lossincreasemanagement1;
    }

    public void setLossincreasemanagement1(String lossincreasemanagement1) {
        this.lossincreasemanagement1 = lossincreasemanagement1;
    }

    @Basic
    @Column(name = "LOSSINCREASEMANAGEMENT2", nullable = false, length = 2)
    public String getLossincreasemanagement2() {
        return lossincreasemanagement2;
    }

    public void setLossincreasemanagement2(String lossincreasemanagement2) {
        this.lossincreasemanagement2 = lossincreasemanagement2;
    }

    @Basic
    @Column(name = "LOSSINCREASEMANAGEMENT3", nullable = false, length = 2)
    public String getLossincreasemanagement3() {
        return lossincreasemanagement3;
    }

    public void setLossincreasemanagement3(String lossincreasemanagement3) {
        this.lossincreasemanagement3 = lossincreasemanagement3;
    }

    @Basic
    @Column(name = "LOSSINCREASEMANAGEMENT4", nullable = false, length = 2)
    public String getLossincreasemanagement4() {
        return lossincreasemanagement4;
    }

    public void setLossincreasemanagement4(String lossincreasemanagement4) {
        this.lossincreasemanagement4 = lossincreasemanagement4;
    }

    @Basic
    @Column(name = "LOSSINCREASEMANAGEMENT5", nullable = false, length = 2)
    public String getLossincreasemanagement5() {
        return lossincreasemanagement5;
    }

    public void setLossincreasemanagement5(String lossincreasemanagement5) {
        this.lossincreasemanagement5 = lossincreasemanagement5;
    }

    @Basic
    @Column(name = "LOSSINCREASEMANAGEMENT6", nullable = false, length = 2)
    public String getLossincreasemanagement6() {
        return lossincreasemanagement6;
    }

    public void setLossincreasemanagement6(String lossincreasemanagement6) {
        this.lossincreasemanagement6 = lossincreasemanagement6;
    }

    @Basic
    @Column(name = "LOSSINCREASEMANAGEMENT7", nullable = false, length = 2)
    public String getLossincreasemanagement7() {
        return lossincreasemanagement7;
    }

    public void setLossincreasemanagement7(String lossincreasemanagement7) {
        this.lossincreasemanagement7 = lossincreasemanagement7;
    }

    @Basic
    @Column(name = "LOSSINCREASEMANAGEMENT8", nullable = false, length = 2)
    public String getLossincreasemanagement8() {
        return lossincreasemanagement8;
    }

    public void setLossincreasemanagement8(String lossincreasemanagement8) {
        this.lossincreasemanagement8 = lossincreasemanagement8;
    }

    @Basic
    @Column(name = "TIMEMANAGEMENT1", nullable = false, length = 2)
    public String getTimemanagement1() {
        return timemanagement1;
    }

    public void setTimemanagement1(String timemanagement1) {
        this.timemanagement1 = timemanagement1;
    }

    @Basic
    @Column(name = "LINKEDTIME1", nullable = true, length = 2)
    public String getLinkedtime1() {
        return linkedtime1;
    }

    public void setLinkedtime1(String linkedtime1) {
        this.linkedtime1 = linkedtime1;
    }

    @Basic
    @Column(name = "SPECIFICUOMTIME1", nullable = false)
    public Short getSpecificuomtime1() {
        return specificuomtime1;
    }

    public void setSpecificuomtime1(Short specificuomtime1) {
        this.specificuomtime1 = specificuomtime1;
    }

    @Basic
    @Column(name = "TIMEMANAGEMENT2", nullable = false, length = 2)
    public String getTimemanagement2() {
        return timemanagement2;
    }

    public void setTimemanagement2(String timemanagement2) {
        this.timemanagement2 = timemanagement2;
    }

    @Basic
    @Column(name = "LINKEDTIME2", nullable = true, length = 2)
    public String getLinkedtime2() {
        return linkedtime2;
    }

    public void setLinkedtime2(String linkedtime2) {
        this.linkedtime2 = linkedtime2;
    }

    @Basic
    @Column(name = "SPECIFICUOMTIME2", nullable = false)
    public Short getSpecificuomtime2() {
        return specificuomtime2;
    }

    public void setSpecificuomtime2(Short specificuomtime2) {
        this.specificuomtime2 = specificuomtime2;
    }

    @Basic
    @Column(name = "TIMEMANAGEMENT3", nullable = false, length = 2)
    public String getTimemanagement3() {
        return timemanagement3;
    }

    public void setTimemanagement3(String timemanagement3) {
        this.timemanagement3 = timemanagement3;
    }

    @Basic
    @Column(name = "LINKEDTIME3", nullable = true, length = 2)
    public String getLinkedtime3() {
        return linkedtime3;
    }

    public void setLinkedtime3(String linkedtime3) {
        this.linkedtime3 = linkedtime3;
    }

    @Basic
    @Column(name = "SPECIFICUOMTIME3", nullable = false)
    public Short getSpecificuomtime3() {
        return specificuomtime3;
    }

    public void setSpecificuomtime3(Short specificuomtime3) {
        this.specificuomtime3 = specificuomtime3;
    }

    @Basic
    @Column(name = "TIMEMANAGEMENT4", nullable = false, length = 2)
    public String getTimemanagement4() {
        return timemanagement4;
    }

    public void setTimemanagement4(String timemanagement4) {
        this.timemanagement4 = timemanagement4;
    }

    @Basic
    @Column(name = "LINKEDTIME4", nullable = true, length = 2)
    public String getLinkedtime4() {
        return linkedtime4;
    }

    public void setLinkedtime4(String linkedtime4) {
        this.linkedtime4 = linkedtime4;
    }

    @Basic
    @Column(name = "SPECIFICUOMTIME4", nullable = false)
    public Short getSpecificuomtime4() {
        return specificuomtime4;
    }

    public void setSpecificuomtime4(Short specificuomtime4) {
        this.specificuomtime4 = specificuomtime4;
    }

    @Basic
    @Column(name = "TIMEMANAGEMENT5", nullable = false, length = 2)
    public String getTimemanagement5() {
        return timemanagement5;
    }

    public void setTimemanagement5(String timemanagement5) {
        this.timemanagement5 = timemanagement5;
    }

    @Basic
    @Column(name = "LINKEDTIME5", nullable = true, length = 2)
    public String getLinkedtime5() {
        return linkedtime5;
    }

    public void setLinkedtime5(String linkedtime5) {
        this.linkedtime5 = linkedtime5;
    }

    @Basic
    @Column(name = "SPECIFICUOMTIME5", nullable = false)
    public Short getSpecificuomtime5() {
        return specificuomtime5;
    }

    public void setSpecificuomtime5(Short specificuomtime5) {
        this.specificuomtime5 = specificuomtime5;
    }

    @Basic
    @Column(name = "CREATIONDATETIME", nullable = true)
    public Timestamp getCreationdatetime() {
        return creationdatetime;
    }

    public void setCreationdatetime(Timestamp creationdatetime) {
        this.creationdatetime = creationdatetime;
    }

    @Basic
    @Column(name = "CREATIONUSER", nullable = true, length = 25)
    public String getCreationuser() {
        return creationuser;
    }

    public void setCreationuser(String creationuser) {
        this.creationuser = creationuser;
    }

    @Basic
    @Column(name = "LASTUPDATEDATETIME", nullable = true)
    public Timestamp getLastupdatedatetime() {
        return lastupdatedatetime;
    }

    public void setLastupdatedatetime(Timestamp lastupdatedatetime) {
        this.lastupdatedatetime = lastupdatedatetime;
    }

    @Basic
    @Column(name = "LASTUPDATEUSER", nullable = true, length = 25)
    public String getLastupdateuser() {
        return lastupdateuser;
    }

    public void setLastupdateuser(String lastupdateuser) {
        this.lastupdateuser = lastupdateuser;
    }

    @Basic
    @Column(name = "ABSUNIQUEID", nullable = false)
    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(id, operation.id) &&
            Objects.equals(longdescription, operation.longdescription) &&
            Objects.equals(shortdescription, operation.shortdescription) &&
            Objects.equals(searchdescription, operation.searchdescription) &&
            Objects.equals(externalreservationtype, operation.externalreservationtype) &&
            Objects.equals(handlechildbundle, operation.handlechildbundle) &&
            Objects.equals(dyelothandled, operation.dyelothandled) &&
            Objects.equals(lossincreasemanagement1, operation.lossincreasemanagement1) &&
            Objects.equals(lossincreasemanagement2, operation.lossincreasemanagement2) &&
            Objects.equals(lossincreasemanagement3, operation.lossincreasemanagement3) &&
            Objects.equals(lossincreasemanagement4, operation.lossincreasemanagement4) &&
            Objects.equals(lossincreasemanagement5, operation.lossincreasemanagement5) &&
            Objects.equals(lossincreasemanagement6, operation.lossincreasemanagement6) &&
            Objects.equals(lossincreasemanagement7, operation.lossincreasemanagement7) &&
            Objects.equals(lossincreasemanagement8, operation.lossincreasemanagement8) &&
            Objects.equals(timemanagement1, operation.timemanagement1) &&
            Objects.equals(linkedtime1, operation.linkedtime1) &&
            Objects.equals(specificuomtime1, operation.specificuomtime1) &&
            Objects.equals(timemanagement2, operation.timemanagement2) &&
            Objects.equals(linkedtime2, operation.linkedtime2) &&
            Objects.equals(specificuomtime2, operation.specificuomtime2) &&
            Objects.equals(timemanagement3, operation.timemanagement3) &&
            Objects.equals(linkedtime3, operation.linkedtime3) &&
            Objects.equals(specificuomtime3, operation.specificuomtime3) &&
            Objects.equals(timemanagement4, operation.timemanagement4) &&
            Objects.equals(linkedtime4, operation.linkedtime4) &&
            Objects.equals(specificuomtime4, operation.specificuomtime4) &&
            Objects.equals(timemanagement5, operation.timemanagement5) &&
            Objects.equals(linkedtime5, operation.linkedtime5) &&
            Objects.equals(specificuomtime5, operation.specificuomtime5) &&
            Objects.equals(creationdatetime, operation.creationdatetime) &&
            Objects.equals(creationuser, operation.creationuser) &&
            Objects.equals(lastupdatedatetime, operation.lastupdatedatetime) &&
            Objects.equals(lastupdateuser, operation.lastupdateuser) &&
            Objects.equals(absuniqueid, operation.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longdescription, shortdescription, searchdescription, externalreservationtype, handlechildbundle, dyelothandled, lossincreasemanagement1, lossincreasemanagement2, lossincreasemanagement3, lossincreasemanagement4, lossincreasemanagement5, lossincreasemanagement6, lossincreasemanagement7, lossincreasemanagement8, timemanagement1, linkedtime1, specificuomtime1, timemanagement2, linkedtime2, specificuomtime2, timemanagement3, linkedtime3, specificuomtime3, timemanagement4, linkedtime4, specificuomtime4, timemanagement5, linkedtime5, specificuomtime5, creationdatetime, creationuser, lastupdatedatetime, lastupdateuser, absuniqueid);
    }
}

package io.vamani.application.mobile;

import io.vamani.application.domain.CompOffMaster;
import io.vamani.application.domain.HolidayMaster;

import java.util.List;
import java.util.Objects;

public class CompOffMobileBean {
    private Boolean exist;

    private String errorMessage;

    private List<HolidayMaster> holidayMasters;

    private List<CompOffMobile> compOffMasters;

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<HolidayMaster> getHolidayMasters() {
        return holidayMasters;
    }

    public void setHolidayMasters(List<HolidayMaster> holidayMasters) {
        this.holidayMasters = holidayMasters;
    }

    public List<CompOffMobile> getCompOffMasters() {
        return compOffMasters;
    }

    public void setCompOffMasters(List<CompOffMobile> compOffMasters) {
        this.compOffMasters = compOffMasters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompOffMobileBean that = (CompOffMobileBean) o;
        return Objects.equals(exist, that.exist) &&
            Objects.equals(errorMessage, that.errorMessage) &&
            Objects.equals(holidayMasters, that.holidayMasters) &&
            Objects.equals(compOffMasters, that.compOffMasters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exist, errorMessage, holidayMasters, compOffMasters);
    }

    @Override
    public String toString() {
        return "CompOffMobileBean{" +
            "exist=" + exist +
            ", errorMessage='" + errorMessage + '\'' +
            ", holidayMasters=" + holidayMasters +
            ", compOffMasters=" + compOffMasters +
            '}';
    }
}

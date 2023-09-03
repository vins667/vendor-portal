package io.vamani.application.model;
import java.time.Instant;
import java.util.List;

public class MMRMasterBean {
    private Long id;
    private Instant monthYear;
    private String factory;
    private Boolean exist;
    private List<MMRDepartmentBean> mmrDepartmentBean;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Instant getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(Instant monthYear) {
        this.monthYear = monthYear;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }

    public List<MMRDepartmentBean> getMmrDepartmentBean() {
        return mmrDepartmentBean;
    }

    public void setMmrDepartmentBean(List<MMRDepartmentBean> mmrDepartmentBean) {
        this.mmrDepartmentBean = mmrDepartmentBean;
    }

    @Override
    public String toString() {
        return "MMRMasterBean [id=" + id + ", monthYear=" + monthYear + ", factory=" + factory + ", mmrDepartmentBean="
            + mmrDepartmentBean + "]";
    }


}

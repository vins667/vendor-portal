package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Objects;

public class JobProfileSearch implements Serializable {
    private String department;
    private String designation;
    private String status;
    private Pageable page;
    private int size;
    private int pageNo;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Pageable getPage() {
        return page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobProfileSearch)) return false;
        JobProfileSearch that = (JobProfileSearch) o;
        return getSize() == that.getSize() &&
            getPageNo() == that.getPageNo() &&
            Objects.equals(getDepartment(), that.getDepartment()) &&
            Objects.equals(getDesignation(), that.getDesignation()) &&
            Objects.equals(getStatus(), that.getStatus()) &&
            Objects.equals(getPage(), that.getPage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartment(), getDesignation(), getStatus(), getPage(), getSize(), getPageNo());
    }

    @Override
    public String toString() {
        return "JobProfileSearch{" +
            "department='" + department + '\'' +
            ", designation='" + designation + '\'' +
            ", status='" + status + '\'' +
            ", page=" + page +
            ", size=" + size +
            ", pageNo=" + pageNo +
            '}';
    }
}

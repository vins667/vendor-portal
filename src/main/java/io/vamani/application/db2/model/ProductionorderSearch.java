package io.vamani.application.db2.model;

import org.springframework.data.domain.Pageable;

import java.util.Objects;

public class ProductionorderSearch {
    private String companycode;
    private String countercode;
    private String code;
    private String operationcode;
    private String requesttype;
    private Pageable page;
    private int size;
    private int pageNo;

    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    public String getCountercode() {
        return countercode;
    }

    public void setCountercode(String countercode) {
        this.countercode = countercode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code != null ? code.toUpperCase().trim() : code;
    }

    public String getOperationcode() {
        return operationcode;
    }

    public void setOperationcode(String operationcode) {
        this.operationcode = operationcode;
    }

    public String getRequesttype() {
        return requesttype;
    }

    public void setRequesttype(String requesttype) {
        this.requesttype = requesttype;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductionorderSearch that = (ProductionorderSearch) o;
        return size == that.size &&
            pageNo == that.pageNo &&
            Objects.equals(companycode, that.companycode) &&
            Objects.equals(code, that.code) &&
            Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, code, page, size, pageNo);
    }

    @Override
    public String toString() {
        return "ProductionorderSearch{" +
            "companycode='" + companycode + '\'' +
            ", code='" + code + '\'' +
            ", page=" + page +
            ", size=" + size +
            ", pageNo=" + pageNo +
            '}';
    }
}

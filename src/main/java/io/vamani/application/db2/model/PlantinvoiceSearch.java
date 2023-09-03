package io.vamani.application.db2.model;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Objects;

public class PlantinvoiceSearch implements Serializable {
    private String code;
    private String suppliercode;
    private Pageable page;
    private int size;
    private int pageNo;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
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
        PlantinvoiceSearch that = (PlantinvoiceSearch) o;
        return size == that.size && pageNo == that.pageNo && Objects.equals(code, that.code) && Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, page, size, pageNo);
    }
}

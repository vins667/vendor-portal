package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

public class SalesOrderClosingSearch {
    private String status;
    private String code;
    private Double shippedPercentage;
    private Pageable page;
    private String sort;
    private String sortType;
    private int size;
    private int pageNo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getShippedPercentage() {
        return shippedPercentage;
    }

    public void setShippedPercentage(Double shippedPercentage) {
        this.shippedPercentage = shippedPercentage;
    }

    public Pageable getPage() {
        return page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
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
}

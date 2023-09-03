package io.vamani.application.model;


import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public class BankRealisationCertificateUploadSearch {
    private LocalDate brcDate;
    private LocalDate brcDateTo;
    private String brcNo;
    private String sort;
    private String sortType;
    private Pageable page;
    private int size;
    private int pageNo;

    public LocalDate getBrcDate() {
        return brcDate;
    }

    public void setBrcDate(LocalDate brcDate) {
        this.brcDate = brcDate;
    }

    public LocalDate getBrcDateTo() {
        return brcDateTo;
    }

    public void setBrcDateTo(LocalDate brcDateTo) {
        this.brcDateTo = brcDateTo;
    }

    public String getBrcNo() {
        return brcNo;
    }

    public void setBrcNo(String brcNo) {
        this.brcNo = brcNo;
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
}

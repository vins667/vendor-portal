package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.time.Instant;

public class PaymentRequestFormSearch implements Serializable {
    private Long id;
    private String company;
    private String division;
    private String businessunit;
    private String findocumentcode;
    private Instant bookingDateFrom;
    private Instant bookingDateTo;
    private Instant billDate;
    private String customersuppliercode;
    private String billNo;
    private String flag;
    private Pageable page;
    private String sort;
    private String sortType;
    private int size;
    private int pageNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getBusinessunit() {
        return businessunit;
    }

    public void setBusinessunit(String businessunit) {
        this.businessunit = businessunit;
    }

    public String getFindocumentcode() {
        return findocumentcode;
    }

    public void setFindocumentcode(String findocumentcode) {
        this.findocumentcode = findocumentcode;
    }

    public Instant getBookingDateFrom() {
        return bookingDateFrom;
    }

    public void setBookingDateFrom(Instant bookingDateFrom) {
        this.bookingDateFrom = bookingDateFrom;
    }

    public Instant getBookingDateTo() {
        return bookingDateTo;
    }

    public void setBookingDateTo(Instant bookingDateTo) {
        this.bookingDateTo = bookingDateTo;
    }

    public Instant getBillDate() {
        return billDate;
    }

    public void setBillDate(Instant billDate) {
        this.billDate = billDate;
    }

    public String getCustomersuppliercode() {
        return customersuppliercode;
    }

    public void setCustomersuppliercode(String customersuppliercode) {
        this.customersuppliercode = customersuppliercode;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

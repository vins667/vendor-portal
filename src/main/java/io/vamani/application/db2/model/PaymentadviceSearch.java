package io.vamani.application.db2.model;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class PaymentadviceSearch implements Serializable {
    private String status;
    private String chequenumber;
    private String supplier;
    private String dateType;
    private Instant dateFrom;
    private Instant dateTo;
    private Pageable page;
    private int size;
    private int pageNo;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getChequenumber() {
        return chequenumber;
    }

    public void setChequenumber(String chequenumber) {
        this.chequenumber = chequenumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public Instant getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Instant dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Instant getDateTo() {
        return dateTo;
    }

    public void setDateTo(Instant dateTo) {
        this.dateTo = dateTo;
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
        PaymentadviceSearch that = (PaymentadviceSearch) o;
        return size == that.size && pageNo == that.pageNo && Objects.equals(chequenumber, that.chequenumber) && Objects.equals(dateFrom, that.dateFrom) && Objects.equals(dateTo, that.dateTo) && Objects.equals(page, that.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chequenumber, dateFrom, dateTo, page, size, pageNo);
    }
}

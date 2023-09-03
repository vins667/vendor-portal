package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class BillRegisterSearch implements Serializable {
    private String flag;
    private String billType;
    private String dateType;
    private String invoiceCode;
    private Instant invoiceDateFrom;
    private Instant invoiceDateTo;
    private String supplierName;
    private Pageable page;
    private String sort;
    private String sortType;
    private List<Master> selectedItems;
    private int size;
    private int pageNo;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Instant getInvoiceDateFrom() {
        return invoiceDateFrom;
    }

    public void setInvoiceDateFrom(Instant invoiceDateFrom) {
        this.invoiceDateFrom = invoiceDateFrom;
    }

    public Instant getInvoiceDateTo() {
        return invoiceDateTo;
    }

    public void setInvoiceDateTo(Instant invoiceDateTo) {
        this.invoiceDateTo = invoiceDateTo;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
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

    public List<Master> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<Master> selectedItems) {
        this.selectedItems = selectedItems;
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

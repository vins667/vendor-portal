package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

public class TdsDeclarationSearch {
    private String status;
    private String name;
    private String cardNo;
    private String year;
    private Pageable page;
    private int size;
    private int pageNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

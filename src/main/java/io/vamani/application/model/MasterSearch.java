package io.vamani.application.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Pageable;

public class MasterSearch implements Serializable {
    private Long id;
    private String companyCode;
    private String businessunitcode;
    private String finyearcode;
    private String code;
    private String description;
    private Pageable page;
    private String sort;
    private String sortType;
    private int size;

    private String reportType;
    private String templateType;

    private List<String> parameters1;

    private List<String> parameters2;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    private String paraString1;

    private BigDecimal paraNumber1;
    private int pageNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    public String getFinyearcode() {
        return finyearcode;
    }

    public void setFinyearcode(String finyearcode) {
        this.finyearcode = finyearcode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public List<String> getParameters1() {
        return parameters1;
    }

    public void setParameters1(List<String> parameters1) {
        this.parameters1 = parameters1;
    }

    public List<String> getParameters2() {
        return parameters2;
    }

    public void setParameters2(List<String> parameters2) {
        this.parameters2 = parameters2;
    }

    public String getParaString1() {
        return paraString1;
    }

    public void setParaString1(String paraString1) {
        this.paraString1 = paraString1;
    }

    public BigDecimal getParaNumber1() {
        return paraNumber1;
    }

    public void setParaNumber1(BigDecimal paraNumber1) {
        this.paraNumber1 = paraNumber1;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }
}

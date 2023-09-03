package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FindocumentId implements Serializable {
    private String companycode;
    private String businessunitcode;
    private Integer financialyearcode;
    private String documenttemplatecode;
    private String code;
    private String statisticalgroupcode;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "BUSINESSUNITCODE", nullable = false, length = 10)
    @Id
    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    @Column(name = "FINANCIALYEARCODE", nullable = false, precision = 0)
    @Id
    public Integer getFinancialyearcode() {
        return financialyearcode;
    }

    public void setFinancialyearcode(Integer financialyearcode) {
        this.financialyearcode = financialyearcode;
    }

    @Column(name = "DOCUMENTTEMPLATECODE", nullable = false, length = 3)
    @Id
    public String getDocumenttemplatecode() {
        return documenttemplatecode;
    }

    public void setDocumenttemplatecode(String documenttemplatecode) {
        this.documenttemplatecode = documenttemplatecode;
    }

    @Column(name = "CODE", nullable = false, length = 15)
    @Id
    public String getCode() {
        return code != null ? code.trim() : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "STATISTICALGROUPCODE", nullable = false, length = 6)
    @Id
    public String getStatisticalgroupcode() {
        return statisticalgroupcode;
    }

    public void setStatisticalgroupcode(String statisticalgroupcode) {
        this.statisticalgroupcode = statisticalgroupcode;
    }

    public FindocumentId() {
    }

    public FindocumentId(String companycode, String businessunitcode, Integer financialyearcode, String documenttemplatecode, String code, String statisticalgroupcode) {
        this.companycode = companycode;
        this.businessunitcode = businessunitcode;
        this.financialyearcode = financialyearcode;
        this.documenttemplatecode = documenttemplatecode;
        this.code = code;
        this.statisticalgroupcode = statisticalgroupcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FindocumentId that = (FindocumentId) o;
        return Objects.equals(companycode, that.companycode) && Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(financialyearcode, that.financialyearcode) && Objects.equals(documenttemplatecode, that.documenttemplatecode) && Objects.equals(code, that.code) && Objects.equals(statisticalgroupcode, that.statisticalgroupcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, businessunitcode, financialyearcode, documenttemplatecode, code, statisticalgroupcode);
    }
}

package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class FinopendocumentsId implements Serializable {
    private String companycode;
    private String businessunitcode;
    private int financialyearcode;
    private String documenttemplatecode;
    private String statisticalgroupcode;
    private String code;
    private int linenumber;

    @Column(name = "COMPANYCODE")
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "BUSINESSUNITCODE")
    @Id
    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    @Column(name = "FINANCIALYEARCODE")
    @Id
    public int getFinancialyearcode() {
        return financialyearcode;
    }

    public void setFinancialyearcode(int financialyearcode) {
        this.financialyearcode = financialyearcode;
    }

    @Column(name = "DOCUMENTTEMPLATECODE")
    @Id
    public String getDocumenttemplatecode() {
        return documenttemplatecode;
    }

    public void setDocumenttemplatecode(String documenttemplatecode) {
        this.documenttemplatecode = documenttemplatecode;
    }

    @Column(name = "STATISTICALGROUPCODE")
    @Id
    public String getStatisticalgroupcode() {
        return statisticalgroupcode;
    }

    public void setStatisticalgroupcode(String statisticalgroupcode) {
        this.statisticalgroupcode = statisticalgroupcode;
    }

    @Column(name = "CODE")
    @Id
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "LINENUMBER")
    @Id
    public int getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(int linenumber) {
        this.linenumber = linenumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinopendocumentsId that = (FinopendocumentsId) o;
        return financialyearcode == that.financialyearcode && linenumber == that.linenumber && Objects.equals(companycode, that.companycode) && Objects.equals(businessunitcode, that.businessunitcode) && Objects.equals(documenttemplatecode, that.documenttemplatecode) && Objects.equals(statisticalgroupcode, that.statisticalgroupcode) && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, businessunitcode, financialyearcode, documenttemplatecode, statisticalgroupcode, code, linenumber);
    }
}

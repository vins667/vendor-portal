package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ViewfindocumentId implements Serializable {
    private String companycode;
    private String documenttemplatecode;
    private String findocumentcode;
    private int financialyearcode;
    private String businessunitcode;
    private int linenumber;

    @Column(name = "FINDOCUMENTCOMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "DOCUMENTTEMPLATECODE", nullable = false, length = 3)
    @Id
    public String getDocumenttemplatecode() {
        return documenttemplatecode;
    }

    public void setDocumenttemplatecode(String documenttemplatecode) {
        this.documenttemplatecode = documenttemplatecode;
    }

    @Column(name = "FINDOCUMENTCODE", nullable = false, length = 15)
    @Id
    public String getFindocumentcode() {
        return findocumentcode;
    }

    public void setFindocumentcode(String findocumentcode) {
        this.findocumentcode = findocumentcode;
    }

    @Column(name = "FINANCIALYEARCODE", nullable = false, precision = 0)
    @Id
    public int getFinancialyearcode() {
        return financialyearcode;
    }

    public void setFinancialyearcode(int financialyearcode) {
        this.financialyearcode = financialyearcode;
    }

    @Column(name = "BUSINESSUNITCODE", nullable = false, length = 10)
    @Id
    public String getBusinessunitcode() {
        return businessunitcode;
    }

    public void setBusinessunitcode(String businessunitcode) {
        this.businessunitcode = businessunitcode;
    }

    @Column(name = "LINENUMBER", nullable = false, precision = 0)
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
        ViewfindocumentId that = (ViewfindocumentId) o;
        return financialyearcode == that.financialyearcode && linenumber == that.linenumber && Objects.equals(companycode, that.companycode) && Objects.equals(documenttemplatecode, that.documenttemplatecode) && Objects.equals(findocumentcode, that.findocumentcode) && Objects.equals(businessunitcode, that.businessunitcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, documenttemplatecode, findocumentcode, financialyearcode, businessunitcode, linenumber);
    }
}

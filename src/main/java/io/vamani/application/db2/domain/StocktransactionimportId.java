package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class StocktransactionimportId implements Serializable {
    private String companycode;
    private String transactionnumber;
    private Integer transactiondetailnumber;

    @Column(name = "COMPANYCODE", nullable = false, length = 3)
    @Id
    public String getCompanycode() {
        return companycode;
    }

    public void setCompanycode(String companycode) {
        this.companycode = companycode;
    }

    @Column(name = "TRANSACTIONNUMBER", nullable = false, length = 15)
    @Id
    public String getTransactionnumber() {
        return transactionnumber;
    }

    public void setTransactionnumber(String transactionnumber) {
        this.transactionnumber = transactionnumber;
    }

    @Column(name = "TRANSACTIONDETAILNUMBER", nullable = false)
    @Id
    public Integer getTransactiondetailnumber() {
        return transactiondetailnumber;
    }

    public void setTransactiondetailnumber(Integer transactiondetailnumber) {
        this.transactiondetailnumber = transactiondetailnumber;
    }

    public StocktransactionimportId() {
    }

    public StocktransactionimportId(String companycode, String transactionnumber, Integer transactiondetailnumber) {
        this.companycode = companycode;
        this.transactionnumber = transactionnumber;
        this.transactiondetailnumber = transactiondetailnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StocktransactionimportId that = (StocktransactionimportId) o;
        return Objects.equals(companycode, that.companycode) &&
            Objects.equals(transactionnumber, that.transactionnumber) &&
            Objects.equals(transactiondetailnumber, that.transactiondetailnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companycode, transactionnumber, transactiondetailnumber);
    }
}

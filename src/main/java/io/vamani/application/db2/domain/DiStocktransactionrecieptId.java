package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DiStocktransactionrecieptId implements Serializable {
    private String transactionnumber;
    private Integer transactiondetailnumber;
    private String issuetransactionnumber;
    private Integer issuetransactiondetailnumber;

    @Column(name = "TRANSACTIONNUMBER", nullable = false, length = 15)
    public String getTransactionnumber() {
        return transactionnumber;
    }

    public void setTransactionnumber(String transactionnumber) {
        this.transactionnumber = transactionnumber;
    }

    @Column(name = "TRANSACTIONDETAILNUMBER", nullable = false)
    public Integer getTransactiondetailnumber() {
        return transactiondetailnumber;
    }

    public void setTransactiondetailnumber(Integer transactiondetailnumber) {
        this.transactiondetailnumber = transactiondetailnumber;
    }

    @Column(name = "ISSUETRANSACTIONNUMBER", nullable = false, length = 15)
    public String getIssuetransactionnumber() {
        return issuetransactionnumber;
    }

    public void setIssuetransactionnumber(String issuetransactionnumber) {
        this.issuetransactionnumber = issuetransactionnumber;
    }

    @Column(name = "ISSUETRANSACTIONDETAILNUMBER", nullable = false)
    public Integer getIssuetransactiondetailnumber() {
        return issuetransactiondetailnumber;
    }

    public void setIssuetransactiondetailnumber(Integer issuetransactiondetailnumber) {
        this.issuetransactiondetailnumber = issuetransactiondetailnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiStocktransactionrecieptId id = (DiStocktransactionrecieptId) o;
        return Objects.equals(transactionnumber, id.transactionnumber) && Objects.equals(transactiondetailnumber, id.transactiondetailnumber) && Objects.equals(issuetransactionnumber, id.issuetransactionnumber) && Objects.equals(issuetransactiondetailnumber, id.issuetransactiondetailnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionnumber, transactiondetailnumber, issuetransactionnumber, issuetransactiondetailnumber);
    }
}

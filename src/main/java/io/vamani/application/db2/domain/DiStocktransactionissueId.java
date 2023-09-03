package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class DiStocktransactionissueId implements Serializable {
    private String transactionnumber;
    private int transactiondetailnumber;

    @Column(name = "TRANSACTIONNUMBER")
    @Id
    public String getTransactionnumber() {
        return transactionnumber;
    }

    public void setTransactionnumber(String transactionnumber) {
        this.transactionnumber = transactionnumber;
    }

    @Column(name = "TRANSACTIONDETAILNUMBER")
    @Id
    public int getTransactiondetailnumber() {
        return transactiondetailnumber;
    }

    public void setTransactiondetailnumber(int transactiondetailnumber) {
        this.transactiondetailnumber = transactiondetailnumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiStocktransactionissueId that = (DiStocktransactionissueId) o;
        return transactiondetailnumber == that.transactiondetailnumber && Objects.equals(transactionnumber, that.transactionnumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionnumber, transactiondetailnumber);
    }
}

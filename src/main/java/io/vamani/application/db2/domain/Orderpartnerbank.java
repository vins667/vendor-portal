package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ORDERPARTNERBANK")
public class Orderpartnerbank {
    @EmbeddedId
    private OrderpartnerbankId id;
    @Basic
    @Column(name = "BANKBANKCOUNTRYCODE")
    private String bankbankcountrycode;
    @Basic
    @Column(name = "BANKCODE")
    private String bankcode;
    @Basic
    @Column(name = "BANKBRANCHCODE")
    private String bankbranchcode;
    @Basic
    @Column(name = "EXTERNALBANKCODE")
    private String externalbankcode;
    @Basic
    @Column(name = "CINCODE")
    private String cincode;
    @Basic
    @Column(name = "CURRENTACCOUNTID")
    private String currentaccountid;
    @Basic
    @Column(name = "CURRENCYCODE")
    private String currencycode;
    @Basic
    @Column(name = "ACCOUNTOWNER")
    private String accountowner;
    @Basic
    @Column(name = "BBAN")
    private String bban;
    @Basic
    @Column(name = "BIC")
    private String bic;
    @Basic
    @Column(name = "IBAN")
    private String iban;
    @Basic
    @Column(name = "PRIORITY")
    private int priority;
    @Basic
    @Column(name = "DIRECTDEBIT")
    private boolean directdebit;
    @Basic
    @Column(name = "ABSUNIQUEID")
    private Long absuniqueid;

    public OrderpartnerbankId getId() {
        return id;
    }

    public void setId(OrderpartnerbankId id) {
        this.id = id;
    }

    public String getBankbankcountrycode() {
        return bankbankcountrycode;
    }

    public void setBankbankcountrycode(String bankbankcountrycode) {
        this.bankbankcountrycode = bankbankcountrycode;
    }

    public String getBankcode() {
        return bankcode;
    }

    public void setBankcode(String bankcode) {
        this.bankcode = bankcode;
    }

    public String getBankbranchcode() {
        return bankbranchcode;
    }

    public void setBankbranchcode(String bankbranchcode) {
        this.bankbranchcode = bankbranchcode;
    }

    public String getExternalbankcode() {
        return externalbankcode;
    }

    public void setExternalbankcode(String externalbankcode) {
        this.externalbankcode = externalbankcode;
    }

    public String getCincode() {
        return cincode;
    }

    public void setCincode(String cincode) {
        this.cincode = cincode;
    }

    public String getCurrentaccountid() {
        return currentaccountid;
    }

    public void setCurrentaccountid(String currentaccountid) {
        this.currentaccountid = currentaccountid;
    }

    public String getCurrencycode() {
        return currencycode;
    }

    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode;
    }

    public String getAccountowner() {
        return accountowner;
    }

    public void setAccountowner(String accountowner) {
        this.accountowner = accountowner;
    }

    public String getBban() {
        return bban;
    }

    public void setBban(String bban) {
        this.bban = bban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isDirectdebit() {
        return directdebit;
    }

    public void setDirectdebit(boolean directdebit) {
        this.directdebit = directdebit;
    }

    public Long getAbsuniqueid() {
        return absuniqueid;
    }

    public void setAbsuniqueid(Long absuniqueid) {
        this.absuniqueid = absuniqueid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orderpartnerbank that = (Orderpartnerbank) o;
        return priority == that.priority && directdebit == that.directdebit && Objects.equals(id, that.id) && Objects.equals(bankbankcountrycode, that.bankbankcountrycode) && Objects.equals(bankcode, that.bankcode) && Objects.equals(bankbranchcode, that.bankbranchcode) && Objects.equals(externalbankcode, that.externalbankcode) && Objects.equals(cincode, that.cincode) && Objects.equals(currentaccountid, that.currentaccountid) && Objects.equals(currencycode, that.currencycode) && Objects.equals(accountowner, that.accountowner) && Objects.equals(bban, that.bban) && Objects.equals(bic, that.bic) && Objects.equals(iban, that.iban) && Objects.equals(absuniqueid, that.absuniqueid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bankbankcountrycode, bankcode, bankbranchcode, externalbankcode, cincode, currentaccountid, currencycode, accountowner, bban, bic, iban, priority, directdebit, absuniqueid);
    }
}

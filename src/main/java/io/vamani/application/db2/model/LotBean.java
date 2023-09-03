package io.vamani.application.db2.model;

import io.vamani.application.db2.domain.Balance;

import java.math.BigDecimal;
import java.util.List;

public class LotBean {
    private String lotcode;
    private Long noPlies;
    private Double endBits;
    private BigDecimal lotquantity;
    private BigDecimal selecedquantity;
    private List<BalanceBean> balances;

    public String getLotcode() {
        return lotcode;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    public Long getNoPlies() {
        return noPlies;
    }

    public void setNoPlies(Long noPlies) {
        this.noPlies = noPlies;
    }

    public Double getEndBits() {
        return endBits;
    }

    public void setEndBits(Double endBits) {
        this.endBits = endBits;
    }

    public BigDecimal getLotquantity() {
        return lotquantity;
    }

    public void setLotquantity(BigDecimal lotquantity) {
        this.lotquantity = lotquantity;
    }

    public BigDecimal getSelecedquantity() {
        return selecedquantity;
    }

    public void setSelecedquantity(BigDecimal selecedquantity) {
        this.selecedquantity = selecedquantity;
    }

    public List<BalanceBean> getBalances() {
        return balances;
    }

    public void setBalances(List<BalanceBean> balances) {
        this.balances = balances;
    }
}

package io.vamani.application.db2.model;

import java.math.BigDecimal;
import java.util.List;

public class MarkerBean {
    private Long markerId;
    private String markercode;
    private String lotcode;
    private Long noRolls;
    private Long noPlies;
    private Double markerLength;
    private Double endBits;
    private BigDecimal lotquantity;
    private BigDecimal selecedquantity;
    private Boolean suggested;
    private Boolean highlight;
    private List<BalanceBean> balances;
    private List<LotBean> lotBeans;

    public Long getMarkerId() {
        return markerId;
    }

    public void setMarkerId(Long markerId) {
        this.markerId = markerId;
    }

    public String getMarkercode() {
        return markercode;
    }

    public void setMarkercode(String markercode) {
        this.markercode = markercode;
    }

    public String getLotcode() {
        return lotcode;
    }

    public void setLotcode(String lotcode) {
        this.lotcode = lotcode;
    }

    public Long getNoRolls() {
        return noRolls;
    }

    public void setNoRolls(Long noRolls) {
        this.noRolls = noRolls;
    }

    public Long getNoPlies() {
        return noPlies;
    }

    public void setNoPlies(Long noPlies) {
        this.noPlies = noPlies;
    }

    public Double getMarkerLength() {
        return markerLength;
    }

    public void setMarkerLength(Double markerLength) {
        this.markerLength = markerLength;
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

    public List<LotBean> getLotBeans() {
        return lotBeans;
    }

    public void setLotBeans(List<LotBean> lotBeans) {
        this.lotBeans = lotBeans;
    }

    public Boolean getSuggested() {
        return suggested;
    }

    public void setSuggested(Boolean suggested) {
        this.suggested = suggested;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }
}

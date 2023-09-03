package io.vamani.application.db2.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "DI_STOCKTRANSACTIONRECIEPT")
public class DiStocktransactionreciept {
    @EmbeddedId
    private DiStocktransactionrecieptId id;
    private Date transactiondate;
    private Time transactiontime;
    private String countercode;
    private String code;
    private String productionordercode;
    private String extcountercode;
    private String extcode;
    private String extorderline;
    private String itemtypecompanycode;
    private String itemtypecode;
    private String decosubcode01;
    private String decosubcode02;
    private String decosubcode03;
    private String decosubcode04;
    private String decosubcode05;
    private String decosubcode06;
    private String decosubcode07;
    private String decosubcode08;
    private String decosubcode09;
    private String decosubcode10;
    private String userprimaryuomcode;
    private BigDecimal userprimaryquantity;
    private Instant createddate;

    public DiStocktransactionrecieptId getId() {
        return id;
    }

    public void setId(DiStocktransactionrecieptId id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TRANSACTIONDATE", nullable = false)
    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    @Basic
    @Column(name = "TRANSACTIONTIME", nullable = false)
    public Time getTransactiontime() {
        return transactiontime;
    }

    public void setTransactiontime(Time transactiontime) {
        this.transactiontime = transactiontime;
    }

    @Basic
    @Column(name = "COUNTERCODE", nullable = true, length = 8)
    public String getCountercode() {
        return countercode;
    }

    public void setCountercode(String countercode) {
        this.countercode = countercode;
    }

    @Basic
    @Column(name = "CODE", nullable = true, length = 15)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "PRODUCTIONORDERCODE", nullable = true, length = 15)
    public String getProductionordercode() {
        return productionordercode;
    }

    public void setProductionordercode(String productionordercode) {
        this.productionordercode = productionordercode;
    }

    @Basic
    @Column(name = "EXTCOUNTERCODE", nullable = true, length = 8)
    public String getExtcountercode() {
        return extcountercode;
    }

    public void setExtcountercode(String extcountercode) {
        this.extcountercode = extcountercode;
    }

    @Basic
    @Column(name = "EXTCODE", nullable = true, length = 15)
    public String getExtcode() {
        return extcode;
    }

    public void setExtcode(String extcode) {
        this.extcode = extcode;
    }

    @Basic
    @Column(name = "EXTORDERLINE", nullable = true, length = 5)
    public String getExtorderline() {
        return extorderline;
    }

    public void setExtorderline(String extorderline) {
        this.extorderline = extorderline;
    }

    @Basic
    @Column(name = "ITEMTYPECOMPANYCODE", nullable = true, length = 3)
    public String getItemtypecompanycode() {
        return itemtypecompanycode;
    }

    public void setItemtypecompanycode(String itemtypecompanycode) {
        this.itemtypecompanycode = itemtypecompanycode;
    }

    @Basic
    @Column(name = "ITEMTYPECODE", nullable = true, length = 3)
    public String getItemtypecode() {
        return itemtypecode;
    }

    public void setItemtypecode(String itemtypecode) {
        this.itemtypecode = itemtypecode;
    }

    @Basic
    @Column(name = "DECOSUBCODE01", nullable = true, length = 20)
    public String getDecosubcode01() {
        return decosubcode01;
    }

    public void setDecosubcode01(String decosubcode01) {
        this.decosubcode01 = decosubcode01;
    }

    @Basic
    @Column(name = "DECOSUBCODE02", nullable = true, length = 10)
    public String getDecosubcode02() {
        return decosubcode02;
    }

    public void setDecosubcode02(String decosubcode02) {
        this.decosubcode02 = decosubcode02;
    }

    @Basic
    @Column(name = "DECOSUBCODE03", nullable = true, length = 10)
    public String getDecosubcode03() {
        return decosubcode03;
    }

    public void setDecosubcode03(String decosubcode03) {
        this.decosubcode03 = decosubcode03;
    }

    @Basic
    @Column(name = "DECOSUBCODE04", nullable = true, length = 10)
    public String getDecosubcode04() {
        return decosubcode04;
    }

    public void setDecosubcode04(String decosubcode04) {
        this.decosubcode04 = decosubcode04;
    }

    @Basic
    @Column(name = "DECOSUBCODE05", nullable = true, length = 10)
    public String getDecosubcode05() {
        return decosubcode05;
    }

    public void setDecosubcode05(String decosubcode05) {
        this.decosubcode05 = decosubcode05;
    }

    @Basic
    @Column(name = "DECOSUBCODE06", nullable = true, length = 10)
    public String getDecosubcode06() {
        return decosubcode06;
    }

    public void setDecosubcode06(String decosubcode06) {
        this.decosubcode06 = decosubcode06;
    }

    @Basic
    @Column(name = "DECOSUBCODE07", nullable = true, length = 10)
    public String getDecosubcode07() {
        return decosubcode07;
    }

    public void setDecosubcode07(String decosubcode07) {
        this.decosubcode07 = decosubcode07;
    }

    @Basic
    @Column(name = "DECOSUBCODE08", nullable = true, length = 10)
    public String getDecosubcode08() {
        return decosubcode08;
    }

    public void setDecosubcode08(String decosubcode08) {
        this.decosubcode08 = decosubcode08;
    }

    @Basic
    @Column(name = "DECOSUBCODE09", nullable = true, length = 10)
    public String getDecosubcode09() {
        return decosubcode09;
    }

    public void setDecosubcode09(String decosubcode09) {
        this.decosubcode09 = decosubcode09;
    }

    @Basic
    @Column(name = "DECOSUBCODE10", nullable = true, length = 10)
    public String getDecosubcode10() {
        return decosubcode10;
    }

    public void setDecosubcode10(String decosubcode10) {
        this.decosubcode10 = decosubcode10;
    }

    @Basic
    @Column(name = "USERPRIMARYUOMCODE", nullable = true, length = 3)
    public String getUserprimaryuomcode() {
        return userprimaryuomcode;
    }

    public void setUserprimaryuomcode(String userprimaryuomcode) {
        this.userprimaryuomcode = userprimaryuomcode;
    }

    @Basic
    @Column(name = "USERPRIMARYQUANTITY", nullable = true, precision = 5)
    public BigDecimal getUserprimaryquantity() {
        return userprimaryquantity;
    }

    public void setUserprimaryquantity(BigDecimal userprimaryquantity) {
        this.userprimaryquantity = userprimaryquantity;
    }

    @Basic
    @Column(name = "CREATEDDATE")
    public Instant getCreateddate() {
        return createddate;
    }

    public void setCreateddate(Instant createddate) {
        this.createddate = createddate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiStocktransactionreciept that = (DiStocktransactionreciept) o;
        return Objects.equals(id, that.id) && Objects.equals(transactiondate, that.transactiondate) && Objects.equals(transactiontime, that.transactiontime) && Objects.equals(countercode, that.countercode) && Objects.equals(code, that.code) && Objects.equals(productionordercode, that.productionordercode) && Objects.equals(extcountercode, that.extcountercode) && Objects.equals(extcode, that.extcode) && Objects.equals(extorderline, that.extorderline) && Objects.equals(itemtypecompanycode, that.itemtypecompanycode) && Objects.equals(itemtypecode, that.itemtypecode) && Objects.equals(decosubcode01, that.decosubcode01) && Objects.equals(decosubcode02, that.decosubcode02) && Objects.equals(decosubcode03, that.decosubcode03) && Objects.equals(decosubcode04, that.decosubcode04) && Objects.equals(decosubcode05, that.decosubcode05) && Objects.equals(decosubcode06, that.decosubcode06) && Objects.equals(decosubcode07, that.decosubcode07) && Objects.equals(decosubcode08, that.decosubcode08) && Objects.equals(decosubcode09, that.decosubcode09) && Objects.equals(decosubcode10, that.decosubcode10) && Objects.equals(userprimaryuomcode, that.userprimaryuomcode) && Objects.equals(userprimaryquantity, that.userprimaryquantity) && Objects.equals(createddate, that.createddate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transactiondate, transactiontime, countercode, code, productionordercode, extcountercode, extcode, extorderline, itemtypecompanycode, itemtypecode, decosubcode01, decosubcode02, decosubcode03, decosubcode04, decosubcode05, decosubcode06, decosubcode07, decosubcode08, decosubcode09, decosubcode10, userprimaryuomcode, userprimaryquantity, createddate);
    }
}

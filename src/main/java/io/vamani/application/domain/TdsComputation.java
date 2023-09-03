package io.vamani.application.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * A TdsComputation.
 */
@Entity
@Table(name = "tds_computation")
public class TdsComputation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="tdsComputationSeq", sequenceName="tds_computation_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.AUTO, generator="tdsComputationSeq")
    private Long id;

    @NotNull
    @Size(max = 6)
    @Column(name = "financial_year", length = 6, nullable = false)
    private String financialYear;

    @NotNull
    @Size(max = 50)
    @Column(name = "card_no", length = 50, nullable = false)
    private String cardNo;

    @NotNull
    @Size(max = 200)
    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Size(max = 200)
    @Column(name = "earn_label_1", length = 200)
    private String earnLabel1;

    @Column(name = "earn_amount_1")
    private Double earnAmount1;

    @Size(max = 200)
    @Column(name = "earn_label_2", length = 200)
    private String earnLabel2;

    @Column(name = "earn_amount_2")
    private Double earnAmount2;

    @Size(max = 200)
    @Column(name = "earn_label_3", length = 200)
    private String earnLabel3;

    @Column(name = "earn_amount_3")
    private Double earnAmount3;

    @Size(max = 200)
    @Column(name = "earn_label_4", length = 200)
    private String earnLabel4;

    @Column(name = "earn_amount_4")
    private Double earnAmount4;

    @Size(max = 200)
    @Column(name = "earn_label_5", length = 200)
    private String earnLabel5;

    @Column(name = "earn_amount_5")
    private Double earnAmount5;

    @Size(max = 200)
    @Column(name = "earn_label_6", length = 200)
    private String earnLabel6;

    @Column(name = "earn_amount_6")
    private Double earnAmount6;

    @Size(max = 200)
    @Column(name = "earn_label_7", length = 200)
    private String earnLabel7;

    @Column(name = "earn_amount_7")
    private Double earnAmount7;

    @Size(max = 200)
    @Column(name = "earn_label_8", length = 200)
    private String earnLabel8;

    @Column(name = "earn_amount_8")
    private Double earnAmount8;

    @Size(max = 200)
    @Column(name = "earn_label_9", length = 200)
    private String earnLabel9;

    @Column(name = "earn_amount_9")
    private Double earnAmount9;

    @Size(max = 200)
    @Column(name = "earn_label_10", length = 200)
    private String earnLabel10;

    @Column(name = "earn_amount_10")
    private Double earnAmount10;

    @Column(name = "previous_employer_amount")
    private BigDecimal previousEmployerAmount;

    @Column(name = "previous_employer_tds_deduction")
    private BigDecimal previousEmployerTdsDeduction;

    @Column(name = "incentive_amount")
    private BigDecimal incentiveAmount;

    @Column(name = "earn_total")
    private Double earnTotal;

    @Column(name = "rent_declare")
    private Double rentDeclare;

    @Column(name = "rent_exempt")
    private Double rentExempt;

    @Column(name = "standard_deduction")
    private Double standardDeduction;

    @Column(name = "standard_total")
    private Double standardTotal;

    @Column(name = "deduct_code_1")
    private String deductCode1;

    @Column(name = "deduct_code_2")
    private String deductCode2;

    @Column(name = "deduct_code_3")
    private String deductCode3;

    @Column(name = "deduct_code_4")
    private String deductCode4;

    @Column(name = "deduct_code_5")
    private String deductCode5;

    @Column(name = "deduct_code_6")
    private String deductCode6;

    @Column(name = "deduct_code_7")
    private String deductCode7;

    @Column(name = "deduct_code_8")
    private String deductCode8;

    @Column(name = "deduct_code_9")
    private String deductCode9;

    @Column(name = "deduct_code_10")
    private String deductCode10;

    @Column(name = "deduct_code_11")
    private String deductCode11;

    @Column(name = "deduct_code_12")
    private String deductCode12;

    @Column(name = "deduct_code_13")
    private String deductCode13;

    @Column(name = "deduct_code_14")
    private String deductCode14;

    @Column(name = "deduct_code_15")
    private String deductCode15;

    @Column(name = "deduct_code_16")
    private String deductCode16;

    @Column(name = "deduct_code_17")
    private String deductCode17;

    @Column(name = "deduct_code_18")
    private String deductCode18;

    @Column(name = "deduct_code_19")
    private String deductCode19;

    @Column(name = "deduct_code_20")
    private String deductCode20;

    @Column(name = "deduct_amount_1")
    private Double deductAmount1;

    @Column(name = "deduct_amount_2")
    private Double deductAmount2;

    @Column(name = "deduct_amount_3")
    private Double deductAmount3;

    @Column(name = "deduct_amount_4")
    private Double deductAmount4;

    @Column(name = "deduct_amount_5")
    private Double deductAmount5;

    @Column(name = "deduct_amount_6")
    private Double deductAmount6;

    @Column(name = "deduct_amount_7")
    private Double deductAmount7;

    @Column(name = "deduct_amount_8")
    private Double deductAmount8;

    @Column(name = "deduct_amount_9")
    private Double deductAmount9;

    @Column(name = "deduct_amount_10")
    private Double deductAmount10;

    @Column(name = "deduct_amount_11")
    private Double deductAmount11;

    @Column(name = "deduct_amount_12")
    private Double deductAmount12;

    @Column(name = "deduct_amount_13")
    private Double deductAmount13;

    @Column(name = "deduct_amount_14")
    private Double deductAmount14;

    @Column(name = "deduct_amount_15")
    private Double deductAmount15;

    @Column(name = "deduct_amount_16")
    private Double deductAmount16;

    @Column(name = "deduct_amount_17")
    private Double deductAmount17;

    @Column(name = "deduct_amount_18")
    private Double deductAmount18;

    @Column(name = "deduct_amount_19")
    private Double deductAmount19;

    @Column(name = "deduct_amount_20")
    private Double deductAmount20;

    @Column(name = "total_tax_income")
    private Double totalTaxIncome;

    @Column(name = "tax_value")
    private Double taxValue;

    @Column(name = "cess_value")
    private Double cessValue;

    @Column(name = "tax_deduct_value")
    private Double taxDeductValue;

    @Column(name = "balance_tax_value")
    private Double balanceTaxValue;

    @Column(name = "total_tax_liability")
    private Double totalTaxLiability;

    @Column(name = "pending_month")
    private Integer pendingMonth;

    @Column(name = "process_date")
    private Instant processDate;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public TdsComputation financialYear(String financialYear) {
        this.financialYear = financialYear;
        return this;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public String getCardNo() {
        return cardNo;
    }

    public TdsComputation cardNo(String cardNo) {
        this.cardNo = cardNo;
        return this;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public TdsComputation name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEarnLabel1() {
        return earnLabel1;
    }

    public TdsComputation earnLabel1(String earnLabel1) {
        this.earnLabel1 = earnLabel1;
        return this;
    }

    public void setEarnLabel1(String earnLabel1) {
        this.earnLabel1 = earnLabel1;
    }

    public Double getEarnAmount1() {
        return earnAmount1;
    }

    public TdsComputation earnAmount1(Double earnAmount1) {
        this.earnAmount1 = earnAmount1;
        return this;
    }

    public void setEarnAmount1(Double earnAmount1) {
        this.earnAmount1 = earnAmount1;
    }

    public String getEarnLabel2() {
        return earnLabel2;
    }

    public TdsComputation earnLabel2(String earnLabel2) {
        this.earnLabel2 = earnLabel2;
        return this;
    }

    public void setEarnLabel2(String earnLabel2) {
        this.earnLabel2 = earnLabel2;
    }

    public Double getEarnAmount2() {
        return earnAmount2;
    }

    public TdsComputation earnAmount2(Double earnAmount2) {
        this.earnAmount2 = earnAmount2;
        return this;
    }

    public void setEarnAmount2(Double earnAmount2) {
        this.earnAmount2 = earnAmount2;
    }

    public String getEarnLabel3() {
        return earnLabel3;
    }

    public TdsComputation earnLabel3(String earnLabel3) {
        this.earnLabel3 = earnLabel3;
        return this;
    }

    public void setEarnLabel3(String earnLabel3) {
        this.earnLabel3 = earnLabel3;
    }

    public Double getEarnAmount3() {
        return earnAmount3;
    }

    public TdsComputation earnAmount3(Double earnAmount3) {
        this.earnAmount3 = earnAmount3;
        return this;
    }

    public void setEarnAmount3(Double earnAmount3) {
        this.earnAmount3 = earnAmount3;
    }

    public String getEarnLabel4() {
        return earnLabel4;
    }

    public TdsComputation earnLabel4(String earnLabel4) {
        this.earnLabel4 = earnLabel4;
        return this;
    }

    public void setEarnLabel4(String earnLabel4) {
        this.earnLabel4 = earnLabel4;
    }

    public Double getEarnAmount4() {
        return earnAmount4;
    }

    public TdsComputation earnAmount4(Double earnAmount4) {
        this.earnAmount4 = earnAmount4;
        return this;
    }

    public void setEarnAmount4(Double earnAmount4) {
        this.earnAmount4 = earnAmount4;
    }

    public String getEarnLabel5() {
        return earnLabel5;
    }

    public TdsComputation earnLabel5(String earnLabel5) {
        this.earnLabel5 = earnLabel5;
        return this;
    }

    public void setEarnLabel5(String earnLabel5) {
        this.earnLabel5 = earnLabel5;
    }

    public Double getEarnAmount5() {
        return earnAmount5;
    }

    public TdsComputation earnAmount5(Double earnAmount5) {
        this.earnAmount5 = earnAmount5;
        return this;
    }

    public void setEarnAmount5(Double earnAmount5) {
        this.earnAmount5 = earnAmount5;
    }

    public String getEarnLabel6() {
        return earnLabel6;
    }

    public TdsComputation earnLabel6(String earnLabel6) {
        this.earnLabel6 = earnLabel6;
        return this;
    }

    public void setEarnLabel6(String earnLabel6) {
        this.earnLabel6 = earnLabel6;
    }

    public Double getEarnAmount6() {
        return earnAmount6;
    }

    public TdsComputation earnAmount6(Double earnAmount6) {
        this.earnAmount6 = earnAmount6;
        return this;
    }

    public void setEarnAmount6(Double earnAmount6) {
        this.earnAmount6 = earnAmount6;
    }

    public String getEarnLabel7() {
        return earnLabel7;
    }

    public TdsComputation earnLabel7(String earnLabel7) {
        this.earnLabel7 = earnLabel7;
        return this;
    }

    public void setEarnLabel7(String earnLabel7) {
        this.earnLabel7 = earnLabel7;
    }

    public Double getEarnAmount7() {
        return earnAmount7;
    }

    public TdsComputation earnAmount7(Double earnAmount7) {
        this.earnAmount7 = earnAmount7;
        return this;
    }

    public void setEarnAmount7(Double earnAmount7) {
        this.earnAmount7 = earnAmount7;
    }

    public String getEarnLabel8() {
        return earnLabel8;
    }

    public TdsComputation earnLabel8(String earnLabel8) {
        this.earnLabel8 = earnLabel8;
        return this;
    }

    public void setEarnLabel8(String earnLabel8) {
        this.earnLabel8 = earnLabel8;
    }

    public Double getEarnAmount8() {
        return earnAmount8;
    }

    public TdsComputation earnAmount8(Double earnAmount8) {
        this.earnAmount8 = earnAmount8;
        return this;
    }

    public void setEarnAmount8(Double earnAmount8) {
        this.earnAmount8 = earnAmount8;
    }

    public String getEarnLabel9() {
        return earnLabel9;
    }

    public TdsComputation earnLabel9(String earnLabel9) {
        this.earnLabel9 = earnLabel9;
        return this;
    }

    public void setEarnLabel9(String earnLabel9) {
        this.earnLabel9 = earnLabel9;
    }

    public Double getEarnAmount9() {
        return earnAmount9;
    }

    public TdsComputation earnAmount9(Double earnAmount9) {
        this.earnAmount9 = earnAmount9;
        return this;
    }

    public void setEarnAmount9(Double earnAmount9) {
        this.earnAmount9 = earnAmount9;
    }

    public String getEarnLabel10() {
        return earnLabel10;
    }

    public TdsComputation earnLabel10(String earnLabel10) {
        this.earnLabel10 = earnLabel10;
        return this;
    }

    public void setEarnLabel10(String earnLabel10) {
        this.earnLabel10 = earnLabel10;
    }

    public Double getEarnAmount10() {
        return earnAmount10;
    }

    public TdsComputation earnAmount10(Double earnAmount10) {
        this.earnAmount10 = earnAmount10;
        return this;
    }

    public void setEarnAmount10(Double earnAmount10) {
        this.earnAmount10 = earnAmount10;
    }

    public BigDecimal getPreviousEmployerAmount() {
        return previousEmployerAmount;
    }

    public void setPreviousEmployerAmount(BigDecimal previousEmployerAmount) {
        this.previousEmployerAmount = previousEmployerAmount;
    }

    public BigDecimal getIncentiveAmount() {
        return incentiveAmount;
    }

    public void setIncentiveAmount(BigDecimal incentiveAmount) {
        this.incentiveAmount = incentiveAmount;
    }

    public Double getEarnTotal() {
        return earnTotal;
    }

    public TdsComputation earnTotal(Double earnTotal) {
        this.earnTotal = earnTotal;
        return this;
    }

    public void setEarnTotal(Double earnTotal) {
        this.earnTotal = earnTotal;
    }

    public Double getRentDeclare() {
        return rentDeclare;
    }

    public TdsComputation rentDeclare(Double rentDeclare) {
        this.rentDeclare = rentDeclare;
        return this;
    }

    public void setRentDeclare(Double rentDeclare) {
        this.rentDeclare = rentDeclare;
    }

    public Double getRentExempt() {
        return rentExempt;
    }

    public TdsComputation rentExempt(Double rentExempt) {
        this.rentExempt = rentExempt;
        return this;
    }

    public void setRentExempt(Double rentExempt) {
        this.rentExempt = rentExempt;
    }

    public Double getStandardDeduction() {
        return standardDeduction;
    }

    public void setStandardDeduction(Double standardDeduction) {
        this.standardDeduction = standardDeduction;
    }

    public Double getStandardTotal() {
        return standardTotal;
    }

    public void setStandardTotal(Double standardTotal) {
        this.standardTotal = standardTotal;
    }

    public String getDeductCode1() {
        return deductCode1;
    }

    public void setDeductCode1(String deductCode1) {
        this.deductCode1 = deductCode1;
    }

    public String getDeductCode2() {
        return deductCode2;
    }

    public void setDeductCode2(String deductCode2) {
        this.deductCode2 = deductCode2;
    }

    public String getDeductCode3() {
        return deductCode3;
    }

    public void setDeductCode3(String deductCode3) {
        this.deductCode3 = deductCode3;
    }

    public String getDeductCode4() {
        return deductCode4;
    }

    public void setDeductCode4(String deductCode4) {
        this.deductCode4 = deductCode4;
    }

    public String getDeductCode5() {
        return deductCode5;
    }

    public void setDeductCode5(String deductCode5) {
        this.deductCode5 = deductCode5;
    }

    public String getDeductCode6() {
        return deductCode6;
    }

    public void setDeductCode6(String deductCode6) {
        this.deductCode6 = deductCode6;
    }

    public String getDeductCode7() {
        return deductCode7;
    }

    public void setDeductCode7(String deductCode7) {
        this.deductCode7 = deductCode7;
    }

    public String getDeductCode8() {
        return deductCode8;
    }

    public void setDeductCode8(String deductCode8) {
        this.deductCode8 = deductCode8;
    }

    public String getDeductCode9() {
        return deductCode9;
    }

    public void setDeductCode9(String deductCode9) {
        this.deductCode9 = deductCode9;
    }

    public String getDeductCode10() {
        return deductCode10;
    }

    public void setDeductCode10(String deductCode10) {
        this.deductCode10 = deductCode10;
    }

    public String getDeductCode11() {
        return deductCode11;
    }

    public void setDeductCode11(String deductCode11) {
        this.deductCode11 = deductCode11;
    }

    public String getDeductCode12() {
        return deductCode12;
    }

    public void setDeductCode12(String deductCode12) {
        this.deductCode12 = deductCode12;
    }

    public String getDeductCode13() {
        return deductCode13;
    }

    public void setDeductCode13(String deductCode13) {
        this.deductCode13 = deductCode13;
    }

    public String getDeductCode14() {
        return deductCode14;
    }

    public void setDeductCode14(String deductCode14) {
        this.deductCode14 = deductCode14;
    }

    public String getDeductCode15() {
        return deductCode15;
    }

    public void setDeductCode15(String deductCode15) {
        this.deductCode15 = deductCode15;
    }

    public String getDeductCode16() {
        return deductCode16;
    }

    public void setDeductCode16(String deductCode16) {
        this.deductCode16 = deductCode16;
    }

    public String getDeductCode17() {
        return deductCode17;
    }

    public void setDeductCode17(String deductCode17) {
        this.deductCode17 = deductCode17;
    }

    public String getDeductCode18() {
        return deductCode18;
    }

    public void setDeductCode18(String deductCode18) {
        this.deductCode18 = deductCode18;
    }

    public String getDeductCode19() {
        return deductCode19;
    }

    public void setDeductCode19(String deductCode19) {
        this.deductCode19 = deductCode19;
    }

    public String getDeductCode20() {
        return deductCode20;
    }

    public void setDeductCode20(String deductCode20) {
        this.deductCode20 = deductCode20;
    }

    public Double getDeductAmount1() {
        return deductAmount1;
    }

    public void setDeductAmount1(Double deductAmount1) {
        this.deductAmount1 = deductAmount1;
    }

    public Double getDeductAmount2() {
        return deductAmount2;
    }

    public void setDeductAmount2(Double deductAmount2) {
        this.deductAmount2 = deductAmount2;
    }

    public Double getDeductAmount3() {
        return deductAmount3;
    }

    public void setDeductAmount3(Double deductAmount3) {
        this.deductAmount3 = deductAmount3;
    }

    public Double getDeductAmount4() {
        return deductAmount4;
    }

    public void setDeductAmount4(Double deductAmount4) {
        this.deductAmount4 = deductAmount4;
    }

    public Double getDeductAmount5() {
        return deductAmount5;
    }

    public void setDeductAmount5(Double deductAmount5) {
        this.deductAmount5 = deductAmount5;
    }

    public Double getDeductAmount6() {
        return deductAmount6;
    }

    public void setDeductAmount6(Double deductAmount6) {
        this.deductAmount6 = deductAmount6;
    }

    public Double getDeductAmount7() {
        return deductAmount7;
    }

    public void setDeductAmount7(Double deductAmount7) {
        this.deductAmount7 = deductAmount7;
    }

    public Double getDeductAmount8() {
        return deductAmount8;
    }

    public void setDeductAmount8(Double deductAmount8) {
        this.deductAmount8 = deductAmount8;
    }

    public Double getDeductAmount9() {
        return deductAmount9;
    }

    public void setDeductAmount9(Double deductAmount9) {
        this.deductAmount9 = deductAmount9;
    }

    public Double getDeductAmount10() {
        return deductAmount10;
    }

    public void setDeductAmount10(Double deductAmount10) {
        this.deductAmount10 = deductAmount10;
    }

    public Double getDeductAmount11() {
        return deductAmount11;
    }

    public void setDeductAmount11(Double deductAmount11) {
        this.deductAmount11 = deductAmount11;
    }

    public Double getDeductAmount12() {
        return deductAmount12;
    }

    public void setDeductAmount12(Double deductAmount12) {
        this.deductAmount12 = deductAmount12;
    }

    public Double getDeductAmount13() {
        return deductAmount13;
    }

    public void setDeductAmount13(Double deductAmount13) {
        this.deductAmount13 = deductAmount13;
    }

    public Double getDeductAmount14() {
        return deductAmount14;
    }

    public void setDeductAmount14(Double deductAmount14) {
        this.deductAmount14 = deductAmount14;
    }

    public Double getDeductAmount15() {
        return deductAmount15;
    }

    public void setDeductAmount15(Double deductAmount15) {
        this.deductAmount15 = deductAmount15;
    }

    public Double getDeductAmount16() {
        return deductAmount16;
    }

    public void setDeductAmount16(Double deductAmount16) {
        this.deductAmount16 = deductAmount16;
    }

    public Double getDeductAmount17() {
        return deductAmount17;
    }

    public void setDeductAmount17(Double deductAmount17) {
        this.deductAmount17 = deductAmount17;
    }

    public Double getDeductAmount18() {
        return deductAmount18;
    }

    public void setDeductAmount18(Double deductAmount18) {
        this.deductAmount18 = deductAmount18;
    }

    public Double getDeductAmount19() {
        return deductAmount19;
    }

    public void setDeductAmount19(Double deductAmount19) {
        this.deductAmount19 = deductAmount19;
    }

    public Double getDeductAmount20() {
        return deductAmount20;
    }

    public void setDeductAmount20(Double deductAmount20) {
        this.deductAmount20 = deductAmount20;
    }

    public Double getTotalTaxIncome() {
        return totalTaxIncome;
    }

    public void setTotalTaxIncome(Double totalTaxIncome) {
        this.totalTaxIncome = totalTaxIncome;
    }

    public Double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(Double taxValue) {
        this.taxValue = taxValue;
    }

    public Double getCessValue() {
        return cessValue;
    }

    public void setCessValue(Double cessValue) {
        this.cessValue = cessValue;
    }

    public Double getTaxDeductValue() {
        return taxDeductValue;
    }

    public void setTaxDeductValue(Double taxDeductValue) {
        this.taxDeductValue = taxDeductValue;
    }

    public Double getBalanceTaxValue() {
        return balanceTaxValue;
    }

    public void setBalanceTaxValue(Double balanceTaxValue) {
        this.balanceTaxValue = balanceTaxValue;
    }

    public Double getTotalTaxLiability() {
        return totalTaxLiability;
    }

    public void setTotalTaxLiability(Double totalTaxLiability) {
        this.totalTaxLiability = totalTaxLiability;
    }

    public Integer getPendingMonth() {
        return pendingMonth;
    }

    public void setPendingMonth(Integer pendingMonth) {
        this.pendingMonth = pendingMonth;
    }

    public Instant getProcessDate() {
        return processDate;
    }

    public void setProcessDate(Instant processDate) {
        this.processDate = processDate;
    }

    public BigDecimal getPreviousEmployerTdsDeduction() {
        return previousEmployerTdsDeduction;
    }

    public void setPreviousEmployerTdsDeduction(BigDecimal previousEmployerTdsDeduction) {
        this.previousEmployerTdsDeduction = previousEmployerTdsDeduction;
    }
// jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TdsComputation tdsComputation = (TdsComputation) o;
        if (tdsComputation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tdsComputation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TdsComputation{" +
            "id=" + getId() +
            ", financialYear='" + getFinancialYear() + "'" +
            ", cardNo='" + getCardNo() + "'" +
            ", earnLabel1='" + getEarnLabel1() + "'" +
            ", earnAmount1=" + getEarnAmount1() +
            ", earnLabel2='" + getEarnLabel2() + "'" +
            ", earnAmount2=" + getEarnAmount2() +
            ", earnLabel3='" + getEarnLabel3() + "'" +
            ", earnAmount3=" + getEarnAmount3() +
            ", earnLabel4='" + getEarnLabel4() + "'" +
            ", earnAmount4=" + getEarnAmount4() +
            ", earnLabel5='" + getEarnLabel5() + "'" +
            ", earnAmount5=" + getEarnAmount5() +
            ", earnLabel6='" + getEarnLabel6() + "'" +
            ", earnAmount6=" + getEarnAmount6() +
            ", earnLabel7='" + getEarnLabel7() + "'" +
            ", earnAmount7=" + getEarnAmount7() +
            ", earnLabel8='" + getEarnLabel8() + "'" +
            ", earnAmount8=" + getEarnAmount8() +
            ", earnLabel9='" + getEarnLabel9() + "'" +
            ", earnAmount9=" + getEarnAmount9() +
            ", earnLabel10='" + getEarnLabel10() + "'" +
            ", earnAmount10=" + getEarnAmount10() +
            "}";
    }
}

package io.vamani.application.mssql.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Objects;

public class SalaryReportBean implements Serializable {
    private String cardNo;
    private String name;
    private String fName;
    private String desCodeDesc;
    private String depCodeDesc;
    private Timestamp doj;
    private String bankName;
    private String bankNo;
    private String pfNo;
    private String esiNo;
    private String uan;
    private String payMode;
    private String pan;
    private String adhNo;
    private String subCodeDesc;
    private String subCodeAddress;
    private String monthYear;

    private String all1;
    private String all2;
    private String all3;
    private String all4;
    private String all5;
    private String all6;
    private String all7;
    private String all8;
    private String all9;
    private String all10;

    private double rat1;
    private double rat2;
    private double rat3;
    private double rat4;
    private double rat5;
    private double rat6;
    private double rat7;
    private double rat8;
    private double rat9;
    private double rat10;

    private double earn1;
    private double earn2;
    private double earn3;
    private double earn4;
    private double earn5;
    private double earn6;
    private double earn7;
    private double earn8;
    private double earn9;
    private double earn10;

    private double arr1;
    private double arr2;
    private double arr3;
    private double arr4;
    private double arr5;
    private double arr6;
    private double arr7;
    private double arr8;
    private double arr9;
    private double arr10;

    private String dall1;
    private String dall2;
    private String dall3;
    private String dall4;
    private String dall5;
    private String dall6;

    private double ded1;
    private double ded2;
    private double ded3;
    private double ded4;
    private double ded5;
    private double ded6;

    private double ptotSal;
    private double totSal;
    private double arrAmt;
    private double totDed;
    private double netSal;

	private double wday;
	private double el;
	private double cl;
	private double sl;
	private double ml;
	private double coff;
	private double pday;
	private double wf;
	private double hd;
	private double es;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getDesCodeDesc() {
        return desCodeDesc;
    }

    public void setDesCodeDesc(String desCodeDesc) {
        this.desCodeDesc = desCodeDesc;
    }

    public String getDepCodeDesc() {
        return depCodeDesc;
    }

    public void setDepCodeDesc(String depCodeDesc) {
        this.depCodeDesc = depCodeDesc;
    }

    public Timestamp getDoj() {
        return doj;
    }

    public void setDoj(Timestamp doj) {
        this.doj = doj;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getPfNo() {
        return pfNo;
    }

    public void setPfNo(String pfNo) {
        this.pfNo = pfNo;
    }

    public String getEsiNo() {
        return esiNo;
    }

    public void setEsiNo(String esiNo) {
        this.esiNo = esiNo;
    }

    public String getUan() {
        return uan;
    }

    public void setUan(String uan) {
        this.uan = uan;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getAdhNo() {
        return adhNo;
    }

    public void setAdhNo(String adhNo) {
        this.adhNo = adhNo;
    }

    public String getSubCodeDesc() {
        return subCodeDesc;
    }

    public void setSubCodeDesc(String subCodeDesc) {
        this.subCodeDesc = subCodeDesc;
    }

    public String getSubCodeAddress() {
        return subCodeAddress;
    }

    public void setSubCodeAddress(String subCodeAddress) {
        this.subCodeAddress = subCodeAddress;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }

    public String getAll1() {
        return all1;
    }

    public void setAll1(String all1) {
        this.all1 = all1;
    }

    public String getAll2() {
        return all2;
    }

    public void setAll2(String all2) {
        this.all2 = all2;
    }

    public String getAll3() {
        return all3;
    }

    public void setAll3(String all3) {
        this.all3 = all3;
    }

    public String getAll4() {
        return all4;
    }

    public void setAll4(String all4) {
        this.all4 = all4;
    }

    public String getAll5() {
        return all5;
    }

    public void setAll5(String all5) {
        this.all5 = all5;
    }

    public String getAll6() {
        return all6;
    }

    public void setAll6(String all6) {
        this.all6 = all6;
    }

    public double getRat1() {
        return rat1;
    }

    public void setRat1(double rat1) {
        this.rat1 = rat1;
    }

    public double getRat2() {
        return rat2;
    }

    public void setRat2(double rat2) {
        this.rat2 = rat2;
    }

    public double getRat3() {
        return rat3;
    }

    public void setRat3(double rat3) {
        this.rat3 = rat3;
    }

    public double getRat4() {
        return rat4;
    }

    public void setRat4(double rat4) {
        this.rat4 = rat4;
    }

    public double getRat5() {
        return rat5;
    }

    public void setRat5(double rat5) {
        this.rat5 = rat5;
    }

    public double getRat6() {
        return rat6;
    }

    public void setRat6(double rat6) {
        this.rat6 = rat6;
    }

    public double getEarn1() {
        return earn1;
    }

    public void setEarn1(double earn1) {
        this.earn1 = earn1;
    }

    public double getEarn2() {
        return earn2;
    }

    public void setEarn2(double earn2) {
        this.earn2 = earn2;
    }

    public double getEarn3() {
        return earn3;
    }

    public void setEarn3(double earn3) {
        this.earn3 = earn3;
    }

    public double getEarn4() {
        return earn4;
    }

    public void setEarn4(double earn4) {
        this.earn4 = earn4;
    }

    public double getEarn5() {
        return earn5;
    }

    public void setEarn5(double earn5) {
        this.earn5 = earn5;
    }

    public double getEarn6() {
        return earn6;
    }

    public void setEarn6(double earn6) {
        this.earn6 = earn6;
    }

    public String getDall1() {
        return dall1;
    }

    public void setDall1(String dall1) {
        this.dall1 = dall1;
    }

    public String getDall2() {
        return dall2;
    }

    public void setDall2(String dall2) {
        this.dall2 = dall2;
    }

    public String getDall3() {
        return dall3;
    }

    public void setDall3(String dall3) {
        this.dall3 = dall3;
    }

    public String getDall4() {
        return dall4;
    }

    public void setDall4(String dall4) {
        this.dall4 = dall4;
    }

    public String getDall5() {
        return dall5;
    }

    public void setDall5(String dall5) {
        this.dall5 = dall5;
    }

    public String getDall6() {
        return dall6;
    }

    public void setDall6(String dall6) {
        this.dall6 = dall6;
    }

    public double getDed1() {
        return ded1;
    }

    public void setDed1(double ded1) {
        this.ded1 = ded1;
    }

    public double getDed2() {
        return ded2;
    }

    public void setDed2(double ded2) {
        this.ded2 = ded2;
    }

    public double getDed3() {
        return ded3;
    }

    public void setDed3(double ded3) {
        this.ded3 = ded3;
    }

    public double getDed4() {
        return ded4;
    }

    public void setDed4(double ded4) {
        this.ded4 = ded4;
    }

    public double getDed5() {
        return ded5;
    }

    public void setDed5(double ded5) {
        this.ded5 = ded5;
    }

    public double getDed6() {
        return ded6;
    }

    public void setDed6(double ded6) {
        this.ded6 = ded6;
    }

    public double getPtotSal() {
        return ptotSal;
    }

    public void setPtotSal(double ptotSal) {
        this.ptotSal = ptotSal;
    }

    public double getTotSal() {
        return totSal;
    }

    public void setTotSal(double totSal) {
        this.totSal = totSal;
    }

    public double getTotDed() {
        return totDed;
    }

    public void setTotDed(double totDed) {
        this.totDed = totDed;
    }

    public double getNetSal() {
        return netSal;
    }

    public void setNetSal(double netSal) {
        this.netSal = netSal;
    }

    public double getArr1() {
        return arr1;
    }

    public void setArr1(double arr1) {
        this.arr1 = arr1;
    }

    public double getArr2() {
        return arr2;
    }

    public void setArr2(double arr2) {
        this.arr2 = arr2;
    }

    public double getArr3() {
        return arr3;
    }

    public void setArr3(double arr3) {
        this.arr3 = arr3;
    }

    public double getArr4() {
        return arr4;
    }

    public void setArr4(double arr4) {
        this.arr4 = arr4;
    }

    public double getArr5() {
        return arr5;
    }

    public void setArr5(double arr5) {
        this.arr5 = arr5;
    }

    public double getArr6() {
        return arr6;
    }

    public void setArr6(double arr6) {
        this.arr6 = arr6;
    }

    public double getArrAmt() {
        return arrAmt;
    }

    public void setArrAmt(double arrAmt) {
        this.arrAmt = arrAmt;
    }

    public double getWday() {
        return wday;
    }

    public void setWday(double wday) {
        this.wday = wday;
    }

    public double getEl() {
        return el;
    }

    public void setEl(double el) {
        this.el = el;
    }

    public double getCl() {
        return cl;
    }

    public void setCl(double cl) {
        this.cl = cl;
    }

    public double getSl() {
        return sl;
    }

    public void setSl(double sl) {
        this.sl = sl;
    }

    public double getMl() {
        return ml;
    }

    public void setMl(double ml) {
        this.ml = ml;
    }

    public double getCoff() {
        return coff;
    }

    public void setCoff(double coff) {
        this.coff = coff;
    }

    public double getPday() {
        return pday;
    }

    public void setPday(double pday) {
        this.pday = pday;
    }

    public double getWf() {
        return wf;
    }

    public void setWf(double wf) {
        this.wf = wf;
    }

    public double getHd() {
        return hd;
    }

    public void setHd(double hd) {
        this.hd = hd;
    }

    public double getEs() {
        return es;
    }

    public void setEs(double es) {
        this.es = es;
    }

    public String getAll7() {
        return all7;
    }

    public void setAll7(String all7) {
        this.all7 = all7;
    }

    public double getRat7() {
        return rat7;
    }

    public void setRat7(double rat7) {
        this.rat7 = rat7;
    }

    public double getEarn7() {
        return earn7;
    }

    public void setEarn7(double earn7) {
        this.earn7 = earn7;
    }

    public double getArr7() {
        return arr7;
    }

    public void setArr7(double arr7) {
        this.arr7 = arr7;
    }

    public String getAll8() {
        return all8;
    }

    public void setAll8(String all8) {
        this.all8 = all8;
    }

    public String getAll9() {
        return all9;
    }

    public void setAll9(String all9) {
        this.all9 = all9;
    }

    public String getAll10() {
        return all10;
    }

    public void setAll10(String all10) {
        this.all10 = all10;
    }

    public double getRat8() {
        return rat8;
    }

    public void setRat8(double rat8) {
        this.rat8 = rat8;
    }

    public double getRat9() {
        return rat9;
    }

    public void setRat9(double rat9) {
        this.rat9 = rat9;
    }

    public double getRat10() {
        return rat10;
    }

    public void setRat10(double rat10) {
        this.rat10 = rat10;
    }

    public double getEarn8() {
        return earn8;
    }

    public void setEarn8(double earn8) {
        this.earn8 = earn8;
    }

    public double getEarn9() {
        return earn9;
    }

    public void setEarn9(double earn9) {
        this.earn9 = earn9;
    }

    public double getEarn10() {
        return earn10;
    }

    public void setEarn10(double earn10) {
        this.earn10 = earn10;
    }

    public double getArr8() {
        return arr8;
    }

    public void setArr8(double arr8) {
        this.arr8 = arr8;
    }

    public double getArr9() {
        return arr9;
    }

    public void setArr9(double arr9) {
        this.arr9 = arr9;
    }

    public double getArr10() {
        return arr10;
    }

    public void setArr10(double arr10) {
        this.arr10 = arr10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalaryReportBean)) return false;
        SalaryReportBean that = (SalaryReportBean) o;
        return Double.compare(that.getRat1(), getRat1()) == 0 &&
            Double.compare(that.getRat2(), getRat2()) == 0 &&
            Double.compare(that.getRat3(), getRat3()) == 0 &&
            Double.compare(that.getRat4(), getRat4()) == 0 &&
            Double.compare(that.getRat5(), getRat5()) == 0 &&
            Double.compare(that.getRat6(), getRat6()) == 0 &&
            Double.compare(that.getEarn1(), getEarn1()) == 0 &&
            Double.compare(that.getEarn2(), getEarn2()) == 0 &&
            Double.compare(that.getEarn3(), getEarn3()) == 0 &&
            Double.compare(that.getEarn4(), getEarn4()) == 0 &&
            Double.compare(that.getEarn5(), getEarn5()) == 0 &&
            Double.compare(that.getEarn6(), getEarn6()) == 0 &&
            Double.compare(that.getDed1(), getDed1()) == 0 &&
            Double.compare(that.getDed2(), getDed2()) == 0 &&
            Double.compare(that.getDed3(), getDed3()) == 0 &&
            Double.compare(that.getDed4(), getDed4()) == 0 &&
            Double.compare(that.getDed5(), getDed5()) == 0 &&
            Double.compare(that.getDed6(), getDed6()) == 0 &&
            Double.compare(that.getPtotSal(), getPtotSal()) == 0 &&
            Double.compare(that.getTotSal(), getTotSal()) == 0 &&
            Double.compare(that.getTotDed(), getTotDed()) == 0 &&
            Double.compare(that.getNetSal(), getNetSal()) == 0 &&
            getCardNo().equals(that.getCardNo()) &&
            getName().equals(that.getName()) &&
            getfName().equals(that.getfName()) &&
            getDesCodeDesc().equals(that.getDesCodeDesc()) &&
            getDepCodeDesc().equals(that.getDepCodeDesc()) &&
            getDoj().equals(that.getDoj()) &&
            getBankName().equals(that.getBankName()) &&
            getBankNo().equals(that.getBankNo()) &&
            getPfNo().equals(that.getPfNo()) &&
            getEsiNo().equals(that.getEsiNo()) &&
            getUan().equals(that.getUan()) &&
            getPayMode().equals(that.getPayMode()) &&
            getPan().equals(that.getPan()) &&
            getAdhNo().equals(that.getAdhNo()) &&
            getSubCodeDesc().equals(that.getSubCodeDesc()) &&
            getSubCodeAddress().equals(that.getSubCodeAddress()) &&
            getMonthYear().equals(that.getMonthYear()) &&
            getAll1().equals(that.getAll1()) &&
            getAll2().equals(that.getAll2()) &&
            getAll3().equals(that.getAll3()) &&
            getAll4().equals(that.getAll4()) &&
            getAll5().equals(that.getAll5()) &&
            getAll6().equals(that.getAll6()) &&
            getDall1().equals(that.getDall1()) &&
            getDall2().equals(that.getDall2()) &&
            getDall3().equals(that.getDall3()) &&
            getDall4().equals(that.getDall4()) &&
            getDall5().equals(that.getDall5()) &&
            getDall6().equals(that.getDall6());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNo(), getName(), getfName(), getDesCodeDesc(), getDepCodeDesc(), getDoj(), getBankName(), getBankNo(), getPfNo(), getEsiNo(), getUan(), getPayMode(), getPan(), getAdhNo(), getSubCodeDesc(), getSubCodeAddress(), getMonthYear(), getAll1(), getAll2(), getAll3(), getAll4(), getAll5(), getAll6(), getRat1(), getRat2(), getRat3(), getRat4(), getRat5(), getRat6(), getEarn1(), getEarn2(), getEarn3(), getEarn4(), getEarn5(), getEarn6(), getDall1(), getDall2(), getDall3(), getDall4(), getDall5(), getDall6(), getDed1(), getDed2(), getDed3(), getDed4(), getDed5(), getDed6(), getPtotSal(), getTotSal(), getTotDed(), getNetSal());
    }

    @Override
    public String toString() {
        return "SalaryReportBean{" +
            "cardNo='" + cardNo + '\'' +
            ", name='" + name + '\'' +
            ", fName='" + fName + '\'' +
            ", desCodeDesc='" + desCodeDesc + '\'' +
            ", depCodeDesc='" + depCodeDesc + '\'' +
            ", doj=" + doj +
            ", bankName='" + bankName + '\'' +
            ", bankNo='" + bankNo + '\'' +
            ", pfNo='" + pfNo + '\'' +
            ", esiNo='" + esiNo + '\'' +
            ", uan='" + uan + '\'' +
            ", payMode='" + payMode + '\'' +
            ", pan='" + pan + '\'' +
            ", adhNo='" + adhNo + '\'' +
            ", subCodeDesc='" + subCodeDesc + '\'' +
            ", subCodeAddress='" + subCodeAddress + '\'' +
            ", monthYear='" + monthYear + '\'' +
            ", all1='" + all1 + '\'' +
            ", all2='" + all2 + '\'' +
            ", all3='" + all3 + '\'' +
            ", all4='" + all4 + '\'' +
            ", all5='" + all5 + '\'' +
            ", all6='" + all6 + '\'' +
            ", rat1=" + rat1 +
            ", rat2=" + rat2 +
            ", rat3=" + rat3 +
            ", rat4=" + rat4 +
            ", rat5=" + rat5 +
            ", rat6=" + rat6 +
            ", earn1=" + earn1 +
            ", earn2=" + earn2 +
            ", earn3=" + earn3 +
            ", earn4=" + earn4 +
            ", earn5=" + earn5 +
            ", earn6=" + earn6 +
            ", dall1='" + dall1 + '\'' +
            ", dall2='" + dall2 + '\'' +
            ", dall3='" + dall3 + '\'' +
            ", dall4='" + dall4 + '\'' +
            ", dall5='" + dall5 + '\'' +
            ", dall6='" + dall6 + '\'' +
            ", ded1=" + ded1 +
            ", ded2=" + ded2 +
            ", ded3=" + ded3 +
            ", ded4=" + ded4 +
            ", ded5=" + ded5 +
            ", ded6=" + ded6 +
            ", ptotSal=" + ptotSal +
            ", totSal=" + totSal +
            ", totDed=" + totDed +
            ", netSal=" + netSal +
            '}';
    }
}

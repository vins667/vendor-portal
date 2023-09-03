package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "monthly_fin_view")
public class MonthlyFinance {

    @EmbeddedId
    private MonthlyFinanceId id;

    @Column(name = "year")
    private Integer year;

    @Column(name = "month")
    private Integer month;

    @Column(name = "basic", nullable = true, precision = 0)
    private Double basic;

    @Column(name = "hra", nullable = true, precision = 0)
    private Double hra;

    @Column(name = "da", nullable = true, precision = 0)
    private Double da;

    @Column(name = "ca", nullable = true, precision = 0)
    private Double ca;

    @Column(name = "oa", nullable = true, precision = 0)
    private Double oa;

    @Column(name = "empl_epf", nullable = true, precision = 0)
    private Double emplEpf;

    @Column(name = "empl_fpf", nullable = true, precision = 0)
    private Double emplFpf;

    @Column(name = "empr_epf", nullable = true, precision = 0)
    private Double emprEpf;

    @Column(name = "empr_fpf", nullable = true, precision = 0)
    private Double emprFpf;

    @Column(name = "empl_esi", nullable = true, precision = 0)
    private Double emplEsi;

    @Column(name = "empr_esi", nullable = true, precision = 0)
    private Double emprEsi;

    @Column(name = "loan", nullable = true, precision = 0)
    private Double loan;

    @Column(name = "tot_sal", nullable = true, precision = 0)
    private Double totSal;

    @Column(name = "tot_ded", nullable = true, precision = 0)
    private Double totDed;

    @Column(name = "net_sal", nullable = true, precision = 0)
    private Double netSal;

    @Column(name = "basic1", nullable = true, precision = 0)
    private Double basic1;

    @Column(name = "basic2", nullable = true, precision = 0)
    private Double basic2;

    @Column(name = "day_no", nullable = true, precision = 0)
    private Double dayNo;

    @Column(name = "oth_ded", nullable = true, precision = 0)
    private Double othDed;

    @Column(name = "freze", nullable = true)
    private Short freze;

    @Column(name = "b_cl", nullable = true, precision = 0)
    private Double bCl;

    @Column(name = "b_el", nullable = true, precision = 0)
    private Double bEl;

    @Column(name = "b_pl", nullable = true, precision = 0)
    private Double bPl;

    @Column(name = "b_ml", nullable = true, precision = 0)
    private Double bMl;

    @Column(name = "b_ol", nullable = true, precision = 0)
    private Double bOl;

    @Column(name = "b_sl", nullable = true, precision = 0)
    private Double bSl;

    @Column(name = "o_bada", nullable = true, precision = 0)
    private Double oBada;

    @Column(name = "o_hra", nullable = true, precision = 0)
    private Double oHra;

    @Column(name = "o_ca", nullable = true, precision = 0)
    private Double oCa;

    @Column(name = "o_oa", nullable = true, precision = 0)
    private Double oOa;

    @Column(name = "tds", nullable = true, precision = 0)
    private Double tds;

    @Column(name = "arr_amt", nullable = true, precision = 0)
    private Double arrAmt;

    @Column(name = "incentive", nullable = true, precision = 0)
    private Double incentive;

    @Column(name = "arr_amta", nullable = true, precision = 0)
    private Double arrAmta;

    @Column(name = "incentivea", nullable = true, precision = 0)
    private Double incentivea;

    @Column(name = "sno", nullable = true)
    private Short sno;

    @Column(name = "esicut", nullable = true)
    private Short esicut;

    @Column(name = "otamt", nullable = true, precision = 0)
    private Double otamt;

    @Column(name = "arr_day", nullable = true, precision = 0)
    private Double arrDay;

    @Column(name = "arrm_day", nullable = true, precision = 0)
    private Double arrmDay;

    @Column(name = "pfcut", nullable = true)
    private Short pfcut;

    @Column(name = "fpfcut", nullable = true)
    private Short fpfcut;

    @Column(name = "basic3", nullable = true, precision = 0)
    private Double basic3;

    @Column(name = "adsal", nullable = true, precision = 0)
    private Double adsal;

    @Column(name = "ot_basic", nullable = true, precision = 0)
    private Double otBasic;

    @Column(name = "ot_hr", nullable = true, precision = 0)
    private Double otHr;

    @Column(name = "ot_amt", nullable = true, precision = 0)
    private Double otAmt;

    @Column(name = "ot_esi1", nullable = true, precision = 0)
    private Double otEsi1;

    @Column(name = "ot_esi2", nullable = true, precision = 0)
    private Double otEsi2;

    @Column(name = "el_amt", nullable = true, precision = 0)
    private Double elAmt;

    @Column(name = "cl_amt", nullable = true, precision = 0)
    private Double clAmt;

    @Column(name = "oth_ded1", nullable = true, precision = 0)
    private Double othDed1;

    @Column(name = "ptot_sal", nullable = true, precision = 0)
    private Double ptotSal;

    @Column(name = "loanbal", nullable = true, precision = 0)
    private Double loanbal;

    @Column(name = "o_da", nullable = true, precision = 0)
    private Double oDa;

    @Column(name = "o_ex", nullable = true, precision = 0)
    private Double oEx;

    @Column(name = "ex", nullable = true, precision = 0)
    private Double ex;

    @Column(name = "inc_p", nullable = true, precision = 0)
    private Double incP;

    @Column(name = "inc_am", nullable = true, precision = 0)
    private Double incAm;

    @Column(name = "all1", nullable = true, length = 50)
    private String all1;

    @Column(name = "all2", nullable = true, length = 50)
    private String all2;

    @Column(name = "all3", nullable = true, length = 50)
    private String all3;

    @Column(name = "all4", nullable = true, length = 50)
    private String all4;

    @Column(name = "all5", nullable = true, length = 50)
    private String all5;

    @Column(name = "all6", nullable = true, length = 50)
    private String all6;

    @Column(name = "all7", nullable = true, length = 50)
    private String all7;

    @Column(name = "all8", nullable = true, length = 50)
    private String all8;

    @Column(name = "all9", nullable = true, length = 50)
    private String all9;

    @Column(name = "all10", nullable = true, length = 50)
    private String all10;

    @Column(name = "all11", nullable = true, length = 50)
    private String all11;

    @Column(name = "all12", nullable = true, length = 50)
    private String all12;

    @Column(name = "rat1", nullable = true, precision = 0)
    private Double rat1;

    @Column(name = "rat2", nullable = true, precision = 0)
    private Double rat2;

    @Column(name = "rat3", nullable = true, precision = 0)
    private Double rat3;

    @Column(name = "rat4", nullable = true, precision = 0)
    private Double rat4;

    @Column(name = "rat5", nullable = true, precision = 0)
    private Double rat5;

    @Column(name = "rat6", nullable = true, precision = 0)
    private Double rat6;

    @Column(name = "rat7", nullable = true, precision = 0)
    private Double rat7;

    @Column(name = "rat8", nullable = true, precision = 0)
    private Double rat8;

    @Column(name = "rat9", nullable = true, precision = 0)
    private Double rat9;

    @Column(name = "rat10", nullable = true, precision = 0)
    private Double rat10;

    @Column(name = "rat11", nullable = true, precision = 0)
    private Double rat11;

    @Column(name = "rat12", nullable = true, precision = 0)
    private Double rat12;

    @Column(name = "earn1", nullable = true, precision = 0)
    private Double earn1;

    @Column(name = "earn2", nullable = true, precision = 0)
    private Double earn2;

    @Column(name = "earn3", nullable = true, precision = 0)
    private Double earn3;

    @Column(name = "earn4", nullable = true, precision = 0)
    private Double earn4;

    @Column(name = "earn5", nullable = true, precision = 0)
    private Double earn5;

    @Column(name = "earn6", nullable = true, precision = 0)
    private Double earn6;

    @Column(name = "earn7", nullable = true, precision = 0)
    private Double earn7;

    @Column(name = "earn8", nullable = true, precision = 0)
    private Double earn8;

    @Column(name = "earn9", nullable = true, precision = 0)
    private Double earn9;

    @Column(name = "earn10", nullable = true, precision = 0)
    private Double earn10;

    @Column(name = "earn11", nullable = true, precision = 0)
    private Double earn11;

    @Column(name = "earn12", nullable = true, precision = 0)
    private Double earn12;

    @Column(name = "arr1", nullable = true, precision = 0)
    private Double arr1;

    @Column(name = "arr2", nullable = true, precision = 0)
    private Double arr2;

    @Column(name = "arr3", nullable = true, precision = 0)
    private Double arr3;

    @Column(name = "arr4", nullable = true, precision = 0)
    private Double arr4;

    @Column(name = "arr5", nullable = true, precision = 0)
    private Double arr5;

    @Column(name = "arr6", nullable = true, precision = 0)
    private Double arr6;

    @Column(name = "arr7", nullable = true, precision = 0)
    private Double arr7;

    @Column(name = "arr8", nullable = true, precision = 0)
    private Double arr8;

    @Column(name = "arr9", nullable = true, precision = 0)
    private Double arr9;

    @Column(name = "arr10", nullable = true, precision = 0)
    private Double arr10;

    @Column(name = "arr11", nullable = true, precision = 0)
    private Double arr11;

    @Column(name = "arr12", nullable = true, precision = 0)
    private Double arr12;

    @Column(name = "dall1", nullable = true, length = 50)
    private String dall1;

    @Column(name = "dall2", nullable = true, length = 50)
    private String dall2;

    @Column(name = "dall3", nullable = true, length = 50)
    private String dall3;

    @Column(name = "dall4", nullable = true, length = 50)
    private String dall4;

    @Column(name = "dall5", nullable = true, length = 50)
    private String dall5;

    @Column(name = "dall6", nullable = true, length = 50)
    private String dall6;

    @Column(name = "dall7", nullable = true, length = 50)
    private String dall7;

    @Column(name = "dall8", nullable = true, length = 50)
    private String dall8;

    @Column(name = "ded1", nullable = true, precision = 0)
    private Double ded1;

    @Column(name = "ded2", nullable = true, precision = 0)
    private Double ded2;

    @Column(name = "ded3", nullable = true, precision = 0)
    private Double ded3;

    @Column(name = "ded4", nullable = true, precision = 0)
    private Double ded4;

    @Column(name = "ded5", nullable = true, precision = 0)
    private Double ded5;

    @Column(name = "ded6", nullable = true, precision = 0)
    private Double ded6;

    @Column(name = "ded7", nullable = true, precision = 0)
    private Double ded7;

    @Column(name = "ded8", nullable = true, precision = 0)
    private Double ded8;

    @Column(name = "rem1", nullable = true, precision = 0)
    private Double rem1;

    @Column(name = "rem2", nullable = true, precision = 0)
    private Double rem2;

    @Column(name = "rem3", nullable = true, precision = 0)
    private Double rem3;

    @Column(name = "rem4", nullable = true, precision = 0)
    private Double rem4;

    @Column(name = "rem5", nullable = true, precision = 0)
    private Double rem5;

    @Column(name = "iot_amt", nullable = true, precision = 0)
    private Double iotAmt;

    @Column(name = "iot_esi1", nullable = true, precision = 0)
    private Double iotEsi1;

    @Column(name = "iot_esi2", nullable = true, precision = 0)
    private Double iotEsi2;

    @Column(name = "bus", nullable = true, precision = 0)
    private Double bus;

    @Column(name = "ot_hra", nullable = true, precision = 0)
    private Double otHra;

    @Column(name = "ot_amta", nullable = true, precision = 0)
    private Double otAmta;

    @Column(name = "loan_adjust", nullable = true, precision = 0)
    private Double loanAdjust;

    @Column(name = "ded_adj", nullable = true, precision = 0)
    private Double dedAdj;

    @Column(name = "hall1", nullable = true, length = 30)
    private String hall1;

    @Column(name = "hall2", nullable = true, length = 30)
    private String hall2;

    @Column(name = "hall3", nullable = true, length = 30)
    private String hall3;

    @Column(name = "hall4", nullable = true, length = 30)
    private String hall4;

    @Column(name = "hall5", nullable = true, length = 30)
    private String hall5;

    @Column(name = "hall6", nullable = true, length = 30)
    private String hall6;

    @Column(name = "hall7", nullable = true, length = 30)
    private String hall7;

    @Column(name = "hall8", nullable = true, length = 30)
    private String hall8;

    @Column(name = "hall9", nullable = true, length = 30)
    private String hall9;

    @Column(name = "hall10", nullable = true, length = 30)
    private String hall10;

    @Column(name = "hdall1", nullable = true, length = 30)
    private String hdall1;

    @Column(name = "hdall2", nullable = true, length = 30)
    private String hdall2;

    @Column(name = "hdall3", nullable = true, length = 30)
    private String hdall3;

    @Column(name = "hdall4", nullable = true, length = 30)
    private String hdall4;

    @Column(name = "hdall5", nullable = true, length = 30)
    private String hdall5;

    @Column(name = "hdall6", nullable = true, length = 30)
    private String hdall6;

    @Column(name = "hdall7", nullable = true, length = 30)
    private String hdall7;

    @Column(name = "hdall8", nullable = true, length = 30)
    private String hdall8;

    @Column(name = "hdall9", nullable = true, length = 30)
    private String hdall9;

    @Column(name = "grs_15day", nullable = true, precision = 0)
    private Double grs15Day;

    @Column(name = "grs_30day", nullable = true, precision = 0)
    private Double grs30Day;

    @Column(name = "pf_15day", nullable = true, precision = 0)
    private Double pf15Day;

    @Column(name = "pf_30day", nullable = true, precision = 0)
    private Double pf30Day;

    @Column(name = "esi_15day", nullable = true, precision = 0)
    private Double esi15Day;

    @Column(name = "esi_30day", nullable = true, precision = 0)
    private Double esi30Day;

    @Column(name = "ot1_arr_hrs", nullable = true, precision = 0)
    private Double ot1ArrHrs;

    @Column(name = "ot1_arr_amt", nullable = true, precision = 0)
    private Double ot1ArrAmt;

    @Column(name = "ot2_arr_hrs", nullable = true, precision = 0)
    private Double ot2ArrHrs;

    @Column(name = "ot2_arr_amt", nullable = true, precision = 0)
    private Double ot2ArrAmt;

    @Column(name = "ot3_arr_hrs", nullable = true, precision = 0)
    private Double ot3ArrHrs;

    @Column(name = "ot3_arr_amt", nullable = true, precision = 0)
    private Double ot3ArrAmt;

    @Column(name = "sun_hrs", nullable = true, precision = 0)
    private Double sunHrs;

    @Column(name = "sun_amt", nullable = true, precision = 0)
    private Double sunAmt;

    @Column(name = "hr_2", nullable = true, precision = 0)
    private Double hr2;

    @Column(name = "amt_2", nullable = true, precision = 0)
    private Double amt2;

    @Column(name = "hr_2_BAL", nullable = true, precision = 0)
    private Double hr2Bal;

    @Column(name = "amt_2_BAL", nullable = true, precision = 0)
    private Double amt2Bal;

    @Column(name = "ESI_HR_2", nullable = true, precision = 0)
    private Double esiHr2;

    @Column(name = "ot_hr_446_2hr_arr", nullable = true, precision = 0)
    private Double otHr4462HrArr;

    @Column(name = "ot_hr_446_2hr_arr_amt", nullable = true, precision = 0)
    private Double otHr4462HrArrAmt;

    @Column(name = "ded_export1", nullable = true, precision = 0)
    private Double dedExport1;

    @Column(name = "ded_export2", nullable = true, precision = 0)
    private Double dedExport2;

    @Column(name = "ded_export3", nullable = true, precision = 0)
    private Double dedExport3;

    @Column(name = "ded_export4", nullable = true, precision = 0)
    private Double dedExport4;

    @Column(name = "ded_export5", nullable = true, precision = 0)
    private Double dedExport5;

    @Column(name = "ded_export6", nullable = true, precision = 0)
    private Double dedExport6;

    @Column(name = "ded_export7", nullable = true, precision = 0)
    private Double dedExport7;

    @Column(name = "ded_export8", nullable = true, precision = 0)
    private Double dedExport8;

    @Column(name = "ded_export9", nullable = true, precision = 0)
    private Double dedExport9;

    @Column(name = "ded_export10", nullable = true, precision = 0)
    private Double dedExport10;

    @Column(name = "ded_export11", nullable = true, precision = 0)
    private Double dedExport11;

    @Column(name = "ded_export12", nullable = true, precision = 0)
    private Double dedExport12;

    @Column(name = "ded_export13", nullable = true, precision = 0)
    private Double dedExport13;

    @Column(name = "ded_export14", nullable = true, precision = 0)
    private Double dedExport14;

    @Column(name = "ded_export15", nullable = true, precision = 0)
    private Double dedExport15;

    @Column(name = "ded_export16", nullable = true, precision = 0)
    private Double dedExport16;

    @Column(name = "ded_export17", nullable = true, precision = 0)
    private Double dedExport17;

    @Column(name = "ded_export18", nullable = true, precision = 0)
    private Double dedExport18;

    @Column(name = "ded_export19", nullable = true, precision = 0)
    private Double dedExport19;

    @Column(name = "ded_export20", nullable = true, precision = 0)
    private Double dedExport20;

    @Column(name = "gwr1_esi1", nullable = true, precision = 0)
    private Double gwr1Esi1;

    @Column(name = "gwr2_esi1", nullable = true, precision = 0)
    private Double gwr2Esi1;

    public MonthlyFinanceId getId() {
        return id;
    }

    public void setId(MonthlyFinanceId id) {
        this.id = id;
    }

    public Double getBasic() {
        return basic;
    }

    public void setBasic(Double basic) {
        this.basic = basic;
    }

    public Double getHra() {
        return hra;
    }

    public void setHra(Double hra) {
        this.hra = hra;
    }

    public Double getDa() {
        return da;
    }

    public void setDa(Double da) {
        this.da = da;
    }

    public Double getCa() {
        return ca;
    }

    public void setCa(Double ca) {
        this.ca = ca;
    }

    public Double getOa() {
        return oa;
    }

    public void setOa(Double oa) {
        this.oa = oa;
    }

    public Double getEmplEpf() {
        return emplEpf;
    }

    public void setEmplEpf(Double emplEpf) {
        this.emplEpf = emplEpf;
    }

    public Double getEmplFpf() {
        return emplFpf;
    }

    public void setEmplFpf(Double emplFpf) {
        this.emplFpf = emplFpf;
    }

    public Double getEmprEpf() {
        return emprEpf;
    }

    public void setEmprEpf(Double emprEpf) {
        this.emprEpf = emprEpf;
    }

    public Double getEmprFpf() {
        return emprFpf;
    }

    public void setEmprFpf(Double emprFpf) {
        this.emprFpf = emprFpf;
    }

    public Double getEmplEsi() {
        return emplEsi;
    }

    public void setEmplEsi(Double emplEsi) {
        this.emplEsi = emplEsi;
    }

    public Double getEmprEsi() {
        return emprEsi;
    }

    public void setEmprEsi(Double emprEsi) {
        this.emprEsi = emprEsi;
    }

    public Double getLoan() {
        return loan;
    }

    public void setLoan(Double loan) {
        this.loan = loan;
    }

    public Double getTotSal() {
        return totSal;
    }

    public void setTotSal(Double totSal) {
        this.totSal = totSal;
    }

    public Double getTotDed() {
        return totDed;
    }

    public void setTotDed(Double totDed) {
        this.totDed = totDed;
    }

    public Double getNetSal() {
        return netSal;
    }

    public void setNetSal(Double netSal) {
        this.netSal = netSal;
    }

    public Double getBasic1() {
        return basic1;
    }

    public void setBasic1(Double basic1) {
        this.basic1 = basic1;
    }

    public Double getBasic2() {
        return basic2;
    }

    public void setBasic2(Double basic2) {
        this.basic2 = basic2;
    }

    public Double getDayNo() {
        return dayNo;
    }

    public void setDayNo(Double dayNo) {
        this.dayNo = dayNo;
    }

    public Double getOthDed() {
        return othDed;
    }

    public void setOthDed(Double othDed) {
        this.othDed = othDed;
    }

    public Short getFreze() {
        return freze;
    }

    public void setFreze(Short freze) {
        this.freze = freze;
    }

    public Double getbCl() {
        return bCl;
    }

    public void setbCl(Double bCl) {
        this.bCl = bCl;
    }

    public Double getbEl() {
        return bEl;
    }

    public void setbEl(Double bEl) {
        this.bEl = bEl;
    }

    public Double getbPl() {
        return bPl;
    }

    public void setbPl(Double bPl) {
        this.bPl = bPl;
    }

    public Double getbMl() {
        return bMl;
    }

    public void setbMl(Double bMl) {
        this.bMl = bMl;
    }

    public Double getbOl() {
        return bOl;
    }

    public void setbOl(Double bOl) {
        this.bOl = bOl;
    }

    public Double getbSl() {
        return bSl;
    }

    public void setbSl(Double bSl) {
        this.bSl = bSl;
    }

    public Double getoBada() {
        return oBada;
    }

    public void setoBada(Double oBada) {
        this.oBada = oBada;
    }

    public Double getoHra() {
        return oHra;
    }

    public void setoHra(Double oHra) {
        this.oHra = oHra;
    }

    public Double getoCa() {
        return oCa;
    }

    public void setoCa(Double oCa) {
        this.oCa = oCa;
    }

    public Double getoOa() {
        return oOa;
    }

    public void setoOa(Double oOa) {
        this.oOa = oOa;
    }

    public Double getTds() {
        return tds;
    }

    public void setTds(Double tds) {
        this.tds = tds;
    }

    public Double getArrAmt() {
        return arrAmt;
    }

    public void setArrAmt(Double arrAmt) {
        this.arrAmt = arrAmt;
    }

    public Double getIncentive() {
        return incentive;
    }

    public void setIncentive(Double incentive) {
        this.incentive = incentive;
    }

    public Double getArrAmta() {
        return arrAmta;
    }

    public void setArrAmta(Double arrAmta) {
        this.arrAmta = arrAmta;
    }

    public Double getIncentivea() {
        return incentivea;
    }

    public void setIncentivea(Double incentivea) {
        this.incentivea = incentivea;
    }

    public Short getSno() {
        return sno;
    }

    public void setSno(Short sno) {
        this.sno = sno;
    }

    public Short getEsicut() {
        return esicut;
    }

    public void setEsicut(Short esicut) {
        this.esicut = esicut;
    }

    public Double getOtamt() {
        return otamt;
    }

    public void setOtamt(Double otamt) {
        this.otamt = otamt;
    }

    public Double getArrDay() {
        return arrDay;
    }

    public void setArrDay(Double arrDay) {
        this.arrDay = arrDay;
    }

    public Double getArrmDay() {
        return arrmDay;
    }

    public void setArrmDay(Double arrmDay) {
        this.arrmDay = arrmDay;
    }

    public Short getPfcut() {
        return pfcut;
    }

    public void setPfcut(Short pfcut) {
        this.pfcut = pfcut;
    }

    public Short getFpfcut() {
        return fpfcut;
    }

    public void setFpfcut(Short fpfcut) {
        this.fpfcut = fpfcut;
    }

    public Double getBasic3() {
        return basic3;
    }

    public void setBasic3(Double basic3) {
        this.basic3 = basic3;
    }

    public Double getAdsal() {
        return adsal;
    }

    public void setAdsal(Double adsal) {
        this.adsal = adsal;
    }

    public Double getOtBasic() {
        return otBasic;
    }

    public void setOtBasic(Double otBasic) {
        this.otBasic = otBasic;
    }

    public Double getOtHr() {
        return otHr;
    }

    public void setOtHr(Double otHr) {
        this.otHr = otHr;
    }

    public Double getOtAmt() {
        return otAmt;
    }

    public void setOtAmt(Double otAmt) {
        this.otAmt = otAmt;
    }

    public Double getOtEsi1() {
        return otEsi1;
    }

    public void setOtEsi1(Double otEsi1) {
        this.otEsi1 = otEsi1;
    }

    public Double getOtEsi2() {
        return otEsi2;
    }

    public void setOtEsi2(Double otEsi2) {
        this.otEsi2 = otEsi2;
    }

    public Double getElAmt() {
        return elAmt;
    }

    public void setElAmt(Double elAmt) {
        this.elAmt = elAmt;
    }

    public Double getClAmt() {
        return clAmt;
    }

    public void setClAmt(Double clAmt) {
        this.clAmt = clAmt;
    }

    public Double getOthDed1() {
        return othDed1;
    }

    public void setOthDed1(Double othDed1) {
        this.othDed1 = othDed1;
    }

    public Double getPtotSal() {
        return ptotSal;
    }

    public void setPtotSal(Double ptotSal) {
        this.ptotSal = ptotSal;
    }

    public Double getLoanbal() {
        return loanbal;
    }

    public void setLoanbal(Double loanbal) {
        this.loanbal = loanbal;
    }

    public Double getoDa() {
        return oDa;
    }

    public void setoDa(Double oDa) {
        this.oDa = oDa;
    }

    public Double getoEx() {
        return oEx;
    }

    public void setoEx(Double oEx) {
        this.oEx = oEx;
    }

    public Double getEx() {
        return ex;
    }

    public void setEx(Double ex) {
        this.ex = ex;
    }

    public Double getIncP() {
        return incP;
    }

    public void setIncP(Double incP) {
        this.incP = incP;
    }


    public Double getIncAm() {
        return incAm;
    }

    public void setIncAm(Double incAm) {
        this.incAm = incAm;
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


    public String getAll7() {
        return all7;
    }

    public void setAll7(String all7) {
        this.all7 = all7;
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


    public String getAll11() {
        return all11;
    }

    public void setAll11(String all11) {
        this.all11 = all11;
    }


    public String getAll12() {
        return all12;
    }

    public void setAll12(String all12) {
        this.all12 = all12;
    }


    public Double getRat1() {
        return rat1;
    }

    public void setRat1(Double rat1) {
        this.rat1 = rat1;
    }


    public Double getRat2() {
        return rat2;
    }

    public void setRat2(Double rat2) {
        this.rat2 = rat2;
    }

    public Double getRat3() {
        return rat3;
    }

    public void setRat3(Double rat3) {
        this.rat3 = rat3;
    }

    public Double getRat4() {
        return rat4;
    }

    public void setRat4(Double rat4) {
        this.rat4 = rat4;
    }


    public Double getRat5() {
        return rat5;
    }

    public void setRat5(Double rat5) {
        this.rat5 = rat5;
    }


    public Double getRat6() {
        return rat6;
    }

    public void setRat6(Double rat6) {
        this.rat6 = rat6;
    }


    public Double getRat7() {
        return rat7;
    }

    public void setRat7(Double rat7) {
        this.rat7 = rat7;
    }


    public Double getRat8() {
        return rat8;
    }

    public void setRat8(Double rat8) {
        this.rat8 = rat8;
    }


    public Double getRat9() {
        return rat9;
    }

    public void setRat9(Double rat9) {
        this.rat9 = rat9;
    }


    public Double getRat10() {
        return rat10;
    }

    public void setRat10(Double rat10) {
        this.rat10 = rat10;
    }


    public Double getRat11() {
        return rat11;
    }

    public void setRat11(Double rat11) {
        this.rat11 = rat11;
    }


    public Double getRat12() {
        return rat12;
    }

    public void setRat12(Double rat12) {
        this.rat12 = rat12;
    }


    public Double getEarn1() {
        return earn1;
    }

    public void setEarn1(Double earn1) {
        this.earn1 = earn1;
    }


    public Double getEarn2() {
        return earn2;
    }

    public void setEarn2(Double earn2) {
        this.earn2 = earn2;
    }

    public Double getEarn3() {
        return earn3;
    }

    public void setEarn3(Double earn3) {
        this.earn3 = earn3;
    }
    
    public Double getEarn4() {
        return earn4;
    }

    public void setEarn4(Double earn4) {
        this.earn4 = earn4;
    }

    public Double getEarn5() {
        return earn5;
    }

    public void setEarn5(Double earn5) {
        this.earn5 = earn5;
    }

    public Double getEarn6() {
        return earn6;
    }

    public void setEarn6(Double earn6) {
        this.earn6 = earn6;
    }

    public Double getEarn7() {
        return earn7;
    }

    public void setEarn7(Double earn7) {
        this.earn7 = earn7;
    }

    public Double getEarn8() {
        return earn8;
    }

    public void setEarn8(Double earn8) {
        this.earn8 = earn8;
    }

    public Double getEarn9() {
        return earn9;
    }

    public void setEarn9(Double earn9) {
        this.earn9 = earn9;
    }


    public Double getEarn10() {
        return earn10;
    }

    public void setEarn10(Double earn10) {
        this.earn10 = earn10;
    }


    public Double getEarn11() {
        return earn11;
    }

    public void setEarn11(Double earn11) {
        this.earn11 = earn11;
    }

    public Double getEarn12() {
        return earn12;
    }

    public void setEarn12(Double earn12) {
        this.earn12 = earn12;
    }

    public Double getArr1() {
        return arr1;
    }

    public void setArr1(Double arr1) {
        this.arr1 = arr1;
    }

    public Double getArr2() {
        return arr2;
    }

    public void setArr2(Double arr2) {
        this.arr2 = arr2;
    }

    public Double getArr3() {
        return arr3;
    }

    public void setArr3(Double arr3) {
        this.arr3 = arr3;
    }

    public Double getArr4() {
        return arr4;
    }

    public void setArr4(Double arr4) {
        this.arr4 = arr4;
    }

    public Double getArr5() {
        return arr5;
    }

    public void setArr5(Double arr5) {
        this.arr5 = arr5;
    }

    public Double getArr6() {
        return arr6;
    }

    public void setArr6(Double arr6) {
        this.arr6 = arr6;
    }

    public Double getArr7() {
        return arr7;
    }

    public void setArr7(Double arr7) {
        this.arr7 = arr7;
    }

    public Double getArr8() {
        return arr8;
    }

    public void setArr8(Double arr8) {
        this.arr8 = arr8;
    }

    public Double getArr9() {
        return arr9;
    }

    public void setArr9(Double arr9) {
        this.arr9 = arr9;
    }


    public Double getArr10() {
        return arr10;
    }

    public void setArr10(Double arr10) {
        this.arr10 = arr10;
    }


    public Double getArr11() {
        return arr11;
    }

    public void setArr11(Double arr11) {
        this.arr11 = arr11;
    }


    public Double getArr12() {
        return arr12;
    }

    public void setArr12(Double arr12) {
        this.arr12 = arr12;
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

    public String getDall7() {
        return dall7;
    }

    public void setDall7(String dall7) {
        this.dall7 = dall7;
    }

    public String getDall8() {
        return dall8;
    }

    public void setDall8(String dall8) {
        this.dall8 = dall8;
    }

    public Double getDed1() {
        return ded1;
    }

    public void setDed1(Double ded1) {
        this.ded1 = ded1;
    }

    public Double getDed2() {
        return ded2;
    }

    public void setDed2(Double ded2) {
        this.ded2 = ded2;
    }

    public Double getDed3() {
        return ded3;
    }

    public void setDed3(Double ded3) {
        this.ded3 = ded3;
    }

    public Double getDed4() {
        return ded4;
    }

    public void setDed4(Double ded4) {
        this.ded4 = ded4;
    }

    public Double getDed5() {
        return ded5;
    }

    public void setDed5(Double ded5) {
        this.ded5 = ded5;
    }

    public Double getDed6() {
        return ded6;
    }

    public void setDed6(Double ded6) {
        this.ded6 = ded6;
    }

    public Double getDed7() {
        return ded7;
    }

    public void setDed7(Double ded7) {
        this.ded7 = ded7;
    }

    public Double getDed8() {
        return ded8;
    }

    public void setDed8(Double ded8) {
        this.ded8 = ded8;
    }


    public Double getRem1() {
        return rem1;
    }

    public void setRem1(Double rem1) {
        this.rem1 = rem1;
    }


    public Double getRem2() {
        return rem2;
    }

    public void setRem2(Double rem2) {
        this.rem2 = rem2;
    }


    public Double getRem3() {
        return rem3;
    }

    public void setRem3(Double rem3) {
        this.rem3 = rem3;
    }

    public Double getRem4() {
        return rem4;
    }

    public void setRem4(Double rem4) {
        this.rem4 = rem4;
    }

    public Double getRem5() {
        return rem5;
    }

    public void setRem5(Double rem5) {
        this.rem5 = rem5;
    }

    public Double getIotAmt() {
        return iotAmt;
    }

    public void setIotAmt(Double iotAmt) {
        this.iotAmt = iotAmt;
    }

    public Double getIotEsi1() {
        return iotEsi1;
    }

    public void setIotEsi1(Double iotEsi1) {
        this.iotEsi1 = iotEsi1;
    }

    public Double getIotEsi2() {
        return iotEsi2;
    }

    public void setIotEsi2(Double iotEsi2) {
        this.iotEsi2 = iotEsi2;
    }

    public Double getBus() {
        return bus;
    }

    public void setBus(Double bus) {
        this.bus = bus;
    }

    public Double getOtHra() {
        return otHra;
    }

    public void setOtHra(Double otHra) {
        this.otHra = otHra;
    }

    public Double getOtAmta() {
        return otAmta;
    }

    public void setOtAmta(Double otAmta) {
        this.otAmta = otAmta;
    }

    public Double getLoanAdjust() {
        return loanAdjust;
    }

    public void setLoanAdjust(Double loanAdjust) {
        this.loanAdjust = loanAdjust;
    }

    public Double getDedAdj() {
        return dedAdj;
    }

    public void setDedAdj(Double dedAdj) {
        this.dedAdj = dedAdj;
    }

    public String getHall1() {
        return hall1;
    }

    public void setHall1(String hall1) {
        this.hall1 = hall1;
    }

    public String getHall2() {
        return hall2;
    }

    public void setHall2(String hall2) {
        this.hall2 = hall2;
    }

    public String getHall3() {
        return hall3;
    }

    public void setHall3(String hall3) {
        this.hall3 = hall3;
    }

    public String getHall4() {
        return hall4;
    }

    public void setHall4(String hall4) {
        this.hall4 = hall4;
    }

    public String getHall5() {
        return hall5;
    }

    public void setHall5(String hall5) {
        this.hall5 = hall5;
    }

    public String getHall6() {
        return hall6;
    }

    public void setHall6(String hall6) {
        this.hall6 = hall6;
    }

    public String getHall7() {
        return hall7;
    }

    public void setHall7(String hall7) {
        this.hall7 = hall7;
    }

    public String getHall8() {
        return hall8;
    }

    public void setHall8(String hall8) {
        this.hall8 = hall8;
    }

    public String getHall9() {
        return hall9;
    }

    public void setHall9(String hall9) {
        this.hall9 = hall9;
    }

    public String getHall10() {
        return hall10;
    }

    public void setHall10(String hall10) {
        this.hall10 = hall10;
    }

    public String getHdall1() {
        return hdall1;
    }

    public void setHdall1(String hdall1) {
        this.hdall1 = hdall1;
    }

    public String getHdall2() {
        return hdall2;
    }

    public void setHdall2(String hdall2) {
        this.hdall2 = hdall2;
    }

    public String getHdall3() {
        return hdall3;
    }

    public void setHdall3(String hdall3) {
        this.hdall3 = hdall3;
    }

    public String getHdall4() {
        return hdall4;
    }

    public void setHdall4(String hdall4) {
        this.hdall4 = hdall4;
    }

    public String getHdall5() {
        return hdall5;
    }

    public void setHdall5(String hdall5) {
        this.hdall5 = hdall5;
    }

    public String getHdall6() {
        return hdall6;
    }

    public void setHdall6(String hdall6) {
        this.hdall6 = hdall6;
    }

    public String getHdall7() {
        return hdall7;
    }

    public void setHdall7(String hdall7) {
        this.hdall7 = hdall7;
    }


    public String getHdall8() {
        return hdall8;
    }

    public void setHdall8(String hdall8) {
        this.hdall8 = hdall8;
    }


    public String getHdall9() {
        return hdall9;
    }

    public void setHdall9(String hdall9) {
        this.hdall9 = hdall9;
    }


    public Double getGrs15Day() {
        return grs15Day;
    }

    public void setGrs15Day(Double grs15Day) {
        this.grs15Day = grs15Day;
    }


    public Double getGrs30Day() {
        return grs30Day;
    }

    public void setGrs30Day(Double grs30Day) {
        this.grs30Day = grs30Day;
    }

    public Double getPf15Day() {
        return pf15Day;
    }

    public void setPf15Day(Double pf15Day) {
        this.pf15Day = pf15Day;
    }


    public Double getPf30Day() {
        return pf30Day;
    }

    public void setPf30Day(Double pf30Day) {
        this.pf30Day = pf30Day;
    }


    public Double getEsi15Day() {
        return esi15Day;
    }

    public void setEsi15Day(Double esi15Day) {
        this.esi15Day = esi15Day;
    }


    public Double getEsi30Day() {
        return esi30Day;
    }

    public void setEsi30Day(Double esi30Day) {
        this.esi30Day = esi30Day;
    }


    public Double getOt1ArrHrs() {
        return ot1ArrHrs;
    }

    public void setOt1ArrHrs(Double ot1ArrHrs) {
        this.ot1ArrHrs = ot1ArrHrs;
    }


    public Double getOt1ArrAmt() {
        return ot1ArrAmt;
    }

    public void setOt1ArrAmt(Double ot1ArrAmt) {
        this.ot1ArrAmt = ot1ArrAmt;
    }


    public Double getOt2ArrHrs() {
        return ot2ArrHrs;
    }

    public void setOt2ArrHrs(Double ot2ArrHrs) {
        this.ot2ArrHrs = ot2ArrHrs;
    }


    public Double getOt2ArrAmt() {
        return ot2ArrAmt;
    }

    public void setOt2ArrAmt(Double ot2ArrAmt) {
        this.ot2ArrAmt = ot2ArrAmt;
    }


    public Double getOt3ArrHrs() {
        return ot3ArrHrs;
    }

    public void setOt3ArrHrs(Double ot3ArrHrs) {
        this.ot3ArrHrs = ot3ArrHrs;
    }


    public Double getOt3ArrAmt() {
        return ot3ArrAmt;
    }

    public void setOt3ArrAmt(Double ot3ArrAmt) {
        this.ot3ArrAmt = ot3ArrAmt;
    }


    public Double getSunHrs() {
        return sunHrs;
    }

    public void setSunHrs(Double sunHrs) {
        this.sunHrs = sunHrs;
    }


    public Double getSunAmt() {
        return sunAmt;
    }

    public void setSunAmt(Double sunAmt) {
        this.sunAmt = sunAmt;
    }


    public Double getHr2() {
        return hr2;
    }

    public void setHr2(Double hr2) {
        this.hr2 = hr2;
    }


    public Double getAmt2() {
        return amt2;
    }

    public void setAmt2(Double amt2) {
        this.amt2 = amt2;
    }


    public Double getHr2Bal() {
        return hr2Bal;
    }

    public void setHr2Bal(Double hr2Bal) {
        this.hr2Bal = hr2Bal;
    }


    public Double getAmt2Bal() {
        return amt2Bal;
    }

    public void setAmt2Bal(Double amt2Bal) {
        this.amt2Bal = amt2Bal;
    }


    public Double getEsiHr2() {
        return esiHr2;
    }

    public void setEsiHr2(Double esiHr2) {
        this.esiHr2 = esiHr2;
    }


    public Double getOtHr4462HrArr() {
        return otHr4462HrArr;
    }

    public void setOtHr4462HrArr(Double otHr4462HrArr) {
        this.otHr4462HrArr = otHr4462HrArr;
    }


    public Double getOtHr4462HrArrAmt() {
        return otHr4462HrArrAmt;
    }

    public void setOtHr4462HrArrAmt(Double otHr4462HrArrAmt) {
        this.otHr4462HrArrAmt = otHr4462HrArrAmt;
    }


    public Double getDedExport1() {
        return dedExport1;
    }

    public void setDedExport1(Double dedExport1) {
        this.dedExport1 = dedExport1;
    }


    public Double getDedExport2() {
        return dedExport2;
    }

    public void setDedExport2(Double dedExport2) {
        this.dedExport2 = dedExport2;
    }


    public Double getDedExport3() {
        return dedExport3;
    }

    public void setDedExport3(Double dedExport3) {
        this.dedExport3 = dedExport3;
    }

    
    public Double getDedExport4() {
        return dedExport4;
    }

    public void setDedExport4(Double dedExport4) {
        this.dedExport4 = dedExport4;
    }


    public Double getDedExport5() {
        return dedExport5;
    }

    public void setDedExport5(Double dedExport5) {
        this.dedExport5 = dedExport5;
    }


    public Double getDedExport6() {
        return dedExport6;
    }

    public void setDedExport6(Double dedExport6) {
        this.dedExport6 = dedExport6;
    }


    public Double getDedExport7() {
        return dedExport7;
    }

    public void setDedExport7(Double dedExport7) {
        this.dedExport7 = dedExport7;
    }


    public Double getDedExport8() {
        return dedExport8;
    }

    public void setDedExport8(Double dedExport8) {
        this.dedExport8 = dedExport8;
    }


    public Double getDedExport9() {
        return dedExport9;
    }

    public void setDedExport9(Double dedExport9) {
        this.dedExport9 = dedExport9;
    }


    public Double getDedExport10() {
        return dedExport10;
    }

    public void setDedExport10(Double dedExport10) {
        this.dedExport10 = dedExport10;
    }


    public Double getDedExport11() {
        return dedExport11;
    }

    public void setDedExport11(Double dedExport11) {
        this.dedExport11 = dedExport11;
    }


    public Double getDedExport12() {
        return dedExport12;
    }

    public void setDedExport12(Double dedExport12) {
        this.dedExport12 = dedExport12;
    }


    public Double getDedExport13() {
        return dedExport13;
    }

    public void setDedExport13(Double dedExport13) {
        this.dedExport13 = dedExport13;
    }


    public Double getDedExport14() {
        return dedExport14;
    }

    public void setDedExport14(Double dedExport14) {
        this.dedExport14 = dedExport14;
    }


    public Double getDedExport15() {
        return dedExport15;
    }

    public void setDedExport15(Double dedExport15) {
        this.dedExport15 = dedExport15;
    }


    public Double getDedExport16() {
        return dedExport16;
    }

    public void setDedExport16(Double dedExport16) {
        this.dedExport16 = dedExport16;
    }


    public Double getDedExport17() {
        return dedExport17;
    }

    public void setDedExport17(Double dedExport17) {
        this.dedExport17 = dedExport17;
    }


    public Double getDedExport18() {
        return dedExport18;
    }

    public void setDedExport18(Double dedExport18) {
        this.dedExport18 = dedExport18;
    }


    public Double getDedExport19() {
        return dedExport19;
    }

    public void setDedExport19(Double dedExport19) {
        this.dedExport19 = dedExport19;
    }


    public Double getDedExport20() {
        return dedExport20;
    }

    public void setDedExport20(Double dedExport20) {
        this.dedExport20 = dedExport20;
    }


    public Double getGwr1Esi1() {
        return gwr1Esi1;
    }

    public void setGwr1Esi1(Double gwr1Esi1) {
        this.gwr1Esi1 = gwr1Esi1;
    }


    public Double getGwr2Esi1() {
        return gwr2Esi1;
    }

    public void setGwr2Esi1(Double gwr2Esi1) {
        this.gwr2Esi1 = gwr2Esi1;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyFinance monthly = (MonthlyFinance) o;
        return Objects.equals(getId().getEmpCode(), getId().getEmpCode()) &&
                Objects.equals(getId().getMonthNo(), getId().getMonthNo()) &&
                Objects.equals(basic, monthly.basic) &&
                Objects.equals(hra, monthly.hra) &&
                Objects.equals(da, monthly.da) &&
                Objects.equals(ca, monthly.ca) &&
                Objects.equals(oa, monthly.oa) &&
                Objects.equals(emplEpf, monthly.emplEpf) &&
                Objects.equals(emplFpf, monthly.emplFpf) &&
                Objects.equals(emprEpf, monthly.emprEpf) &&
                Objects.equals(emprFpf, monthly.emprFpf) &&
                Objects.equals(emplEsi, monthly.emplEsi) &&
                Objects.equals(emprEsi, monthly.emprEsi) &&
                Objects.equals(loan, monthly.loan) &&
                Objects.equals(totSal, monthly.totSal) &&
                Objects.equals(totDed, monthly.totDed) &&
                Objects.equals(netSal, monthly.netSal) &&
                Objects.equals(basic1, monthly.basic1) &&
                Objects.equals(basic2, monthly.basic2) &&
                Objects.equals(dayNo, monthly.dayNo) &&
                Objects.equals(othDed, monthly.othDed) &&
                Objects.equals(freze, monthly.freze) &&
                Objects.equals(bCl, monthly.bCl) &&
                Objects.equals(bEl, monthly.bEl) &&
                Objects.equals(bPl, monthly.bPl) &&
                Objects.equals(bMl, monthly.bMl) &&
                Objects.equals(bOl, monthly.bOl) &&
                Objects.equals(bSl, monthly.bSl) &&
                Objects.equals(oBada, monthly.oBada) &&
                Objects.equals(oHra, monthly.oHra) &&
                Objects.equals(oCa, monthly.oCa) &&
                Objects.equals(oOa, monthly.oOa) &&
                Objects.equals(tds, monthly.tds) &&
                Objects.equals(arrAmt, monthly.arrAmt) &&
                Objects.equals(incentive, monthly.incentive) &&
                Objects.equals(arrAmta, monthly.arrAmta) &&
                Objects.equals(incentivea, monthly.incentivea) &&
                Objects.equals(sno, monthly.sno) &&
                Objects.equals(esicut, monthly.esicut) &&
                Objects.equals(otamt, monthly.otamt) &&
                Objects.equals(arrDay, monthly.arrDay) &&
                Objects.equals(arrmDay, monthly.arrmDay) &&
                Objects.equals(pfcut, monthly.pfcut) &&
                Objects.equals(fpfcut, monthly.fpfcut) &&
                Objects.equals(basic3, monthly.basic3) &&
                Objects.equals(adsal, monthly.adsal) &&
                Objects.equals(otBasic, monthly.otBasic) &&
                Objects.equals(otHr, monthly.otHr) &&
                Objects.equals(otAmt, monthly.otAmt) &&
                Objects.equals(otEsi1, monthly.otEsi1) &&
                Objects.equals(otEsi2, monthly.otEsi2) &&
                Objects.equals(elAmt, monthly.elAmt) &&
                Objects.equals(clAmt, monthly.clAmt) &&
                Objects.equals(othDed1, monthly.othDed1) &&
                Objects.equals(ptotSal, monthly.ptotSal) &&
                Objects.equals(loanbal, monthly.loanbal) &&
                Objects.equals(oDa, monthly.oDa) &&
                Objects.equals(oEx, monthly.oEx) &&
                Objects.equals(ex, monthly.ex) &&
                Objects.equals(incP, monthly.incP) &&
                Objects.equals(incAm, monthly.incAm) &&
                Objects.equals(all1, monthly.all1) &&
                Objects.equals(all2, monthly.all2) &&
                Objects.equals(all3, monthly.all3) &&
                Objects.equals(all4, monthly.all4) &&
                Objects.equals(all5, monthly.all5) &&
                Objects.equals(all6, monthly.all6) &&
                Objects.equals(all7, monthly.all7) &&
                Objects.equals(all8, monthly.all8) &&
                Objects.equals(all9, monthly.all9) &&
                Objects.equals(all10, monthly.all10) &&
                Objects.equals(all11, monthly.all11) &&
                Objects.equals(all12, monthly.all12) &&
                Objects.equals(rat1, monthly.rat1) &&
                Objects.equals(rat2, monthly.rat2) &&
                Objects.equals(rat3, monthly.rat3) &&
                Objects.equals(rat4, monthly.rat4) &&
                Objects.equals(rat5, monthly.rat5) &&
                Objects.equals(rat6, monthly.rat6) &&
                Objects.equals(rat7, monthly.rat7) &&
                Objects.equals(rat8, monthly.rat8) &&
                Objects.equals(rat9, monthly.rat9) &&
                Objects.equals(rat10, monthly.rat10) &&
                Objects.equals(rat11, monthly.rat11) &&
                Objects.equals(rat12, monthly.rat12) &&
                Objects.equals(earn1, monthly.earn1) &&
                Objects.equals(earn2, monthly.earn2) &&
                Objects.equals(earn3, monthly.earn3) &&
                Objects.equals(earn4, monthly.earn4) &&
                Objects.equals(earn5, monthly.earn5) &&
                Objects.equals(earn6, monthly.earn6) &&
                Objects.equals(earn7, monthly.earn7) &&
                Objects.equals(earn8, monthly.earn8) &&
                Objects.equals(earn9, monthly.earn9) &&
                Objects.equals(earn10, monthly.earn10) &&
                Objects.equals(earn11, monthly.earn11) &&
                Objects.equals(earn12, monthly.earn12) &&
                Objects.equals(arr1, monthly.arr1) &&
                Objects.equals(arr2, monthly.arr2) &&
                Objects.equals(arr3, monthly.arr3) &&
                Objects.equals(arr4, monthly.arr4) &&
                Objects.equals(arr5, monthly.arr5) &&
                Objects.equals(arr6, monthly.arr6) &&
                Objects.equals(arr7, monthly.arr7) &&
                Objects.equals(arr8, monthly.arr8) &&
                Objects.equals(arr9, monthly.arr9) &&
                Objects.equals(arr10, monthly.arr10) &&
                Objects.equals(arr11, monthly.arr11) &&
                Objects.equals(arr12, monthly.arr12) &&
                Objects.equals(dall1, monthly.dall1) &&
                Objects.equals(dall2, monthly.dall2) &&
                Objects.equals(dall3, monthly.dall3) &&
                Objects.equals(dall4, monthly.dall4) &&
                Objects.equals(dall5, monthly.dall5) &&
                Objects.equals(dall6, monthly.dall6) &&
                Objects.equals(dall7, monthly.dall7) &&
                Objects.equals(dall8, monthly.dall8) &&
                Objects.equals(ded1, monthly.ded1) &&
                Objects.equals(ded2, monthly.ded2) &&
                Objects.equals(ded3, monthly.ded3) &&
                Objects.equals(ded4, monthly.ded4) &&
                Objects.equals(ded5, monthly.ded5) &&
                Objects.equals(ded6, monthly.ded6) &&
                Objects.equals(ded7, monthly.ded7) &&
                Objects.equals(ded8, monthly.ded8) &&
                Objects.equals(rem1, monthly.rem1) &&
                Objects.equals(rem2, monthly.rem2) &&
                Objects.equals(rem3, monthly.rem3) &&
                Objects.equals(rem4, monthly.rem4) &&
                Objects.equals(rem5, monthly.rem5) &&
                Objects.equals(iotAmt, monthly.iotAmt) &&
                Objects.equals(iotEsi1, monthly.iotEsi1) &&
                Objects.equals(iotEsi2, monthly.iotEsi2) &&
                Objects.equals(bus, monthly.bus) &&
                Objects.equals(otHra, monthly.otHra) &&
                Objects.equals(otAmta, monthly.otAmta) &&
                Objects.equals(loanAdjust, monthly.loanAdjust) &&
                Objects.equals(dedAdj, monthly.dedAdj) &&
                Objects.equals(hall1, monthly.hall1) &&
                Objects.equals(hall2, monthly.hall2) &&
                Objects.equals(hall3, monthly.hall3) &&
                Objects.equals(hall4, monthly.hall4) &&
                Objects.equals(hall5, monthly.hall5) &&
                Objects.equals(hall6, monthly.hall6) &&
                Objects.equals(hall7, monthly.hall7) &&
                Objects.equals(hall8, monthly.hall8) &&
                Objects.equals(hall9, monthly.hall9) &&
                Objects.equals(hall10, monthly.hall10) &&
                Objects.equals(hdall1, monthly.hdall1) &&
                Objects.equals(hdall2, monthly.hdall2) &&
                Objects.equals(hdall3, monthly.hdall3) &&
                Objects.equals(hdall4, monthly.hdall4) &&
                Objects.equals(hdall5, monthly.hdall5) &&
                Objects.equals(hdall6, monthly.hdall6) &&
                Objects.equals(hdall7, monthly.hdall7) &&
                Objects.equals(hdall8, monthly.hdall8) &&
                Objects.equals(hdall9, monthly.hdall9) &&
                Objects.equals(grs15Day, monthly.grs15Day) &&
                Objects.equals(grs30Day, monthly.grs30Day) &&
                Objects.equals(pf15Day, monthly.pf15Day) &&
                Objects.equals(pf30Day, monthly.pf30Day) &&
                Objects.equals(esi15Day, monthly.esi15Day) &&
                Objects.equals(esi30Day, monthly.esi30Day) &&
                Objects.equals(ot1ArrHrs, monthly.ot1ArrHrs) &&
                Objects.equals(ot1ArrAmt, monthly.ot1ArrAmt) &&
                Objects.equals(ot2ArrHrs, monthly.ot2ArrHrs) &&
                Objects.equals(ot2ArrAmt, monthly.ot2ArrAmt) &&
                Objects.equals(ot3ArrHrs, monthly.ot3ArrHrs) &&
                Objects.equals(ot3ArrAmt, monthly.ot3ArrAmt) &&
                Objects.equals(sunHrs, monthly.sunHrs) &&
                Objects.equals(sunAmt, monthly.sunAmt) &&
                Objects.equals(hr2, monthly.hr2) &&
                Objects.equals(amt2, monthly.amt2) &&
                Objects.equals(hr2Bal, monthly.hr2Bal) &&
                Objects.equals(amt2Bal, monthly.amt2Bal) &&
                Objects.equals(esiHr2, monthly.esiHr2) &&
                Objects.equals(otHr4462HrArr, monthly.otHr4462HrArr) &&
                Objects.equals(otHr4462HrArrAmt, monthly.otHr4462HrArrAmt) &&
                Objects.equals(dedExport1, monthly.dedExport1) &&
                Objects.equals(dedExport2, monthly.dedExport2) &&
                Objects.equals(dedExport3, monthly.dedExport3) &&
                Objects.equals(dedExport4, monthly.dedExport4) &&
                Objects.equals(dedExport5, monthly.dedExport5) &&
                Objects.equals(dedExport6, monthly.dedExport6) &&
                Objects.equals(dedExport7, monthly.dedExport7) &&
                Objects.equals(dedExport8, monthly.dedExport8) &&
                Objects.equals(dedExport9, monthly.dedExport9) &&
                Objects.equals(dedExport10, monthly.dedExport10) &&
                Objects.equals(dedExport11, monthly.dedExport11) &&
                Objects.equals(dedExport12, monthly.dedExport12) &&
                Objects.equals(dedExport13, monthly.dedExport13) &&
                Objects.equals(dedExport14, monthly.dedExport14) &&
                Objects.equals(dedExport15, monthly.dedExport15) &&
                Objects.equals(dedExport16, monthly.dedExport16) &&
                Objects.equals(dedExport17, monthly.dedExport17) &&
                Objects.equals(dedExport18, monthly.dedExport18) &&
                Objects.equals(dedExport19, monthly.dedExport19) &&
                Objects.equals(dedExport20, monthly.dedExport20) &&
                Objects.equals(gwr1Esi1, monthly.gwr1Esi1) &&
                Objects.equals(gwr2Esi1, monthly.gwr2Esi1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId().getEmpCode(), getId().getMonthNo(), basic, hra, da, ca, oa, emplEpf, emplFpf, emprEpf, emprFpf, emplEsi, emprEsi, loan, totSal, totDed, netSal, basic1, basic2, dayNo, othDed, freze, bCl, bEl, bPl, bMl, bOl, bSl, oBada, oHra, oCa, oOa, tds, arrAmt, incentive, arrAmta, incentivea, sno, esicut, otamt, arrDay, arrmDay, pfcut, fpfcut, basic3, adsal, otBasic, otHr, otAmt, otEsi1, otEsi2, elAmt, clAmt, othDed1, ptotSal, loanbal, oDa, oEx, ex, incP, incAm, all1, all2, all3, all4, all5, all6, all7, all8, all9, all10, all11, all12, rat1, rat2, rat3, rat4, rat5, rat6, rat7, rat8, rat9, rat10, rat11, rat12, earn1, earn2, earn3, earn4, earn5, earn6, earn7, earn8, earn9, earn10, earn11, earn12, arr1, arr2, arr3, arr4, arr5, arr6, arr7, arr8, arr9, arr10, arr11, arr12, dall1, dall2, dall3, dall4, dall5, dall6, dall7, dall8, ded1, ded2, ded3, ded4, ded5, ded6, ded7, ded8, rem1, rem2, rem3, rem4, rem5, iotAmt, iotEsi1, iotEsi2, bus, otHra, otAmta, loanAdjust, dedAdj, hall1, hall2, hall3, hall4, hall5, hall6, hall7, hall8, hall9, hall10, hdall1, hdall2, hdall3, hdall4, hdall5, hdall6, hdall7, hdall8, hdall9, grs15Day, grs30Day, pf15Day, pf30Day, esi15Day, esi30Day, ot1ArrHrs, ot1ArrAmt, ot2ArrHrs, ot2ArrAmt, ot3ArrHrs, ot3ArrAmt, sunHrs, sunAmt, hr2, amt2, hr2Bal, amt2Bal, esiHr2, otHr4462HrArr, otHr4462HrArrAmt, dedExport1, dedExport2, dedExport3, dedExport4, dedExport5, dedExport6, dedExport7, dedExport8, dedExport9, dedExport10, dedExport11, dedExport12, dedExport13, dedExport14, dedExport15, dedExport16, dedExport17, dedExport18, dedExport19, dedExport20, gwr1Esi1, gwr2Esi1);
    }
}

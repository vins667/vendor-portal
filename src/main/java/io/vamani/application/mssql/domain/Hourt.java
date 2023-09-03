package io.vamani.application.mssql.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hourt")
public class Hourt {
    @EmbeddedId
    private HourtId id;

    @Column(name = "thr", nullable = true, precision = 0)
    private Double thr;

    @Column(name = "tothr", nullable = true, precision = 0)
    private Double tothr;

    @Column(name = "whr1", nullable = true, precision = 0)
    private Double whr1;

    @Column(name = "whr2", nullable = true, precision = 0)
    private Double whr2;

    @Column(name = "whr3", nullable = true, precision = 0)
    private Double whr3;

    @Column(name = "whr4", nullable = true, precision = 0)
    private Double whr4;

    @Column(name = "whr5", nullable = true, precision = 0)
    private Double whr5;

    @Column(name = "whr6", nullable = true, precision = 0)
    private Double whr6;

    @Column(name = "whr7", nullable = true, precision = 0)
    private Double whr7;

    @Column(name = "whr8", nullable = true, precision = 0)
    private Double whr8;

    @Column(name = "whr9", nullable = true, precision = 0)
    private Double whr9;

    @Column(name = "whr10", nullable = true, precision = 0)
    private Double whr10;

    @Column(name = "whr11", nullable = true, precision = 0)
    private Double whr11;

    @Column(name = "whr12", nullable = true, precision = 0)
    private Double whr12;

    @Column(name = "whr13", nullable = true, precision = 0)
    private Double whr13;

    @Column(name = "whr14", nullable = true, precision = 0)
    private Double whr14;

    @Column(name = "whr15", nullable = true, precision = 0)
    private Double whr15;

    @Column(name = "whr16", nullable = true, precision = 0)
    private Double whr16;

    @Column(name = "whr17", nullable = true, precision = 0)
    private Double whr17;

    @Column(name = "whr18", nullable = true, precision = 0)
    private Double whr18;

    @Column(name = "whr19", nullable = true, precision = 0)
    private Double whr19;

    @Column(name = "whr20", nullable = true, precision = 0)
    private Double whr20;

    @Column(name = "whr21", nullable = true, precision = 0)
    private Double whr21;

    @Column(name = "whr22", nullable = true, precision = 0)
    private Double whr22;

    @Column(name = "whr23", nullable = true, precision = 0)
    private Double whr23;

    @Column(name = "whr24", nullable = true, precision = 0)
    private Double whr24;

    @Column(name = "whr25", nullable = true, precision = 0)
    private Double whr25;

    @Column(name = "whr26", nullable = true, precision = 0)
    private Double whr26;

    @Column(name = "whr27", nullable = true, precision = 0)
    private Double whr27;

    @Column(name = "whr28", nullable = true, precision = 0)
    private Double whr28;

    @Column(name = "whr29", nullable = true, precision = 0)
    private Double whr29;

    @Column(name = "whr30", nullable = true, precision = 0)
    private Double whr30;

    @Column(name = "whr31", nullable = true, precision = 0)
    private Double whr31;

    @Column(name = "ohr1", nullable = true, precision = 0)
    private Double ohr1;

    @Column(name = "ohr2", nullable = true, precision = 0)
    private Double ohr2;

    @Column(name = "ohr3", nullable = true, precision = 0)
    private Double ohr3;

    @Column(name = "ohr4", nullable = true, precision = 0)
    private Double ohr4;

    @Column(name = "ohr5", nullable = true, precision = 0)
    private Double ohr5;

    @Column(name = "ohr6", nullable = true, precision = 0)
    private Double ohr6;

    @Column(name = "ohr7", nullable = true, precision = 0)
    private Double ohr7;

    @Column(name = "ohr8", nullable = true, precision = 0)
    private Double ohr8;

    @Column(name = "ohr9", nullable = true, precision = 0)
    private Double ohr9;

    @Column(name = "ohr10", nullable = true, precision = 0)
    private Double ohr10;

    @Column(name = "ohr11", nullable = true, precision = 0)
    private Double ohr11;

    @Column(name = "ohr12", nullable = true, precision = 0)
    private Double ohr12;

    @Column(name = "ohr13", nullable = true, precision = 0)
    private Double ohr13;

    @Column(name = "ohr14", nullable = true, precision = 0)
    private Double ohr14;

    @Column(name = "ohr15", nullable = true, precision = 0)
    private Double ohr15;

    @Column(name = "ohr16", nullable = true, precision = 0)
    private Double ohr16;

    @Column(name = "ohr17", nullable = true, precision = 0)
    private Double ohr17;

    @Column(name = "ohr18", nullable = true, precision = 0)
    private Double ohr18;

    @Column(name = "ohr19", nullable = true, precision = 0)
    private Double ohr19;

    @Column(name = "ohr20", nullable = true, precision = 0)
    private Double ohr20;

    @Column(name = "ohr21", nullable = true, precision = 0)
    private Double ohr21;

    @Column(name = "ohr22", nullable = true, precision = 0)
    private Double ohr22;

    @Column(name = "ohr23", nullable = true, precision = 0)
    private Double ohr23;

    @Column(name = "ohr24", nullable = true, precision = 0)
    private Double ohr24;

    @Column(name = "ohr25", nullable = true, precision = 0)
    private Double ohr25;

    @Column(name = "ohr26", nullable = true, precision = 0)
    private Double ohr26;

    @Column(name = "ohr27", nullable = true, precision = 0)
    private Double ohr27;

    @Column(name = "ohr28", nullable = true, precision = 0)
    private Double ohr28;

    @Column(name = "ohr29", nullable = true, precision = 0)
    private Double ohr29;

    @Column(name = "ohr30", nullable = true, precision = 0)
    private Double ohr30;

    @Column(name = "ohr31", nullable = true, precision = 0)
    private Double ohr31;

    @Column(name = "b1", nullable = true, precision = 0)
    private Double b1;

    @Column(name = "b2", nullable = true, precision = 0)
    private Double b2;

    @Column(name = "b3", nullable = true, precision = 0)
    private Double b3;

    @Column(name = "b4", nullable = true, precision = 0)
    private Double b4;

    @Column(name = "b5", nullable = true, precision = 0)
    private Double b5;

    @Column(name = "b6", nullable = true, precision = 0)
    private Double b6;

    @Column(name = "b7", nullable = true, precision = 0)
    private Double b7;

    @Column(name = "b8", nullable = true, precision = 0)
    private Double b8;

    @Column(name = "b9", nullable = true, precision = 0)
    private Double b9;

    @Column(name = "b10", nullable = true, precision = 0)
    private Double b10;

    @Column(name = "b11", nullable = true, precision = 0)
    private Double b11;

    @Column(name = "b12", nullable = true, precision = 0)
    private Double b12;

    @Column(name = "b13", nullable = true, precision = 0)
    private Double b13;

    @Column(name = "b14", nullable = true, precision = 0)
    private Double b14;

    @Column(name = "b15", nullable = true, precision = 0)
    private Double b15;

    @Column(name = "b16", nullable = true, precision = 0)
    private Double b16;

    @Column(name = "b17", nullable = true, precision = 0)
    private Double b17;

    @Column(name = "b18", nullable = true, precision = 0)
    private Double b18;

    @Column(name = "b19", nullable = true, precision = 0)
    private Double b19;

    @Column(name = "b20", nullable = true, precision = 0)
    private Double b20;

    @Column(name = "b21", nullable = true, precision = 0)
    private Double b21;

    @Column(name = "b22", nullable = true, precision = 0)
    private Double b22;

    @Column(name = "b23", nullable = true, precision = 0)
    private Double b23;

    @Column(name = "b24", nullable = true, precision = 0)
    private Double b24;

    @Column(name = "b25", nullable = true, precision = 0)
    private Double b25;

    @Column(name = "b26", nullable = true, precision = 0)
    private Double b26;

    @Column(name = "b27", nullable = true, precision = 0)
    private Double b27;

    @Column(name = "b28", nullable = true, precision = 0)
    private Double b28;

    @Column(name = "b29", nullable = true, precision = 0)
    private Double b29;

    @Column(name = "b30", nullable = true, precision = 0)
    private Double b30;

    @Column(name = "b31", nullable = true, precision = 0)
    private Double b31;

    @Column(name = "d1", nullable = true, precision = 0)
    private Double d1;

    @Column(name = "d2", nullable = true, precision = 0)
    private Double d2;

    @Column(name = "d3", nullable = true, precision = 0)
    private Double d3;

    @Column(name = "d4", nullable = true, precision = 0)
    private Double d4;

    @Column(name = "d5", nullable = true, precision = 0)
    private Double d5;

    @Column(name = "d6", nullable = true, precision = 0)
    private Double d6;

    @Column(name = "d7", nullable = true, precision = 0)
    private Double d7;

    @Column(name = "d8", nullable = true, precision = 0)
    private Double d8;

    @Column(name = "d9", nullable = true, precision = 0)
    private Double d9;

    @Column(name = "d10", nullable = true, precision = 0)
    private Double d10;

    @Column(name = "d11", nullable = true, precision = 0)
    private Double d11;

    @Column(name = "d12", nullable = true, precision = 0)
    private Double d12;

    @Column(name = "d13", nullable = true, precision = 0)
    private Double d13;

    @Column(name = "d14", nullable = true, precision = 0)
    private Double d14;

    @Column(name = "d15", nullable = true, precision = 0)
    private Double d15;

    @Column(name = "d16", nullable = true, precision = 0)
    private Double d16;

    @Column(name = "d17", nullable = true, precision = 0)
    private Double d17;

    @Column(name = "d18", nullable = true, precision = 0)
    private Double d18;

    @Column(name = "d19", nullable = true, precision = 0)
    private Double d19;

    @Column(name = "d20", nullable = true, precision = 0)
    private Double d20;

    @Column(name = "d21", nullable = true, precision = 0)
    private Double d21;

    @Column(name = "d22", nullable = true, precision = 0)
    private Double d22;

    @Column(name = "d23", nullable = true, precision = 0)
    private Double d23;

    @Column(name = "d24", nullable = true, precision = 0)
    private Double d24;

    @Column(name = "d25", nullable = true, precision = 0)
    private Double d25;

    @Column(name = "d26", nullable = true, precision = 0)
    private Double d26;

    @Column(name = "d27", nullable = true, precision = 0)
    private Double d27;

    @Column(name = "d28", nullable = true, precision = 0)
    private Double d28;

    @Column(name = "d29", nullable = true, precision = 0)
    private Double d29;

    @Column(name = "d30", nullable = true, precision = 0)
    private Double d30;

    @Column(name = "d31", nullable = true, precision = 0)
    private Double d31;

    @Column(name = "lshr", nullable = true, precision = 0)
    private Double lshr;

    @Column(name = "dhr", nullable = true, precision = 0)
    private Double dhr;

    @Column(name = "l1", nullable = true, precision = 0)
    private Double l1;

    @Column(name = "l2", nullable = true, precision = 0)
    private Double l2;

    @Column(name = "l3", nullable = true, precision = 0)
    private Double l3;

    @Column(name = "l4", nullable = true, precision = 0)
    private Double l4;

    @Column(name = "l5", nullable = true, precision = 0)
    private Double l5;

    @Column(name = "l6", nullable = true, precision = 0)
    private Double l6;

    @Column(name = "l7", nullable = true, precision = 0)
    private Double l7;

    @Column(name = "l8", nullable = true, precision = 0)
    private Double l8;

    @Column(name = "l9", nullable = true, precision = 0)
    private Double l9;

    @Column(name = "l10", nullable = true, precision = 0)
    private Double l10;

    @Column(name = "l11", nullable = true, precision = 0)
    private Double l11;

    @Column(name = "l12", nullable = true, precision = 0)
    private Double l12;

    @Column(name = "l13", nullable = true, precision = 0)
    private Double l13;

    @Column(name = "l14", nullable = true, precision = 0)
    private Double l14;

    @Column(name = "l15", nullable = true, precision = 0)
    private Double l15;

    @Column(name = "l16", nullable = true, precision = 0)
    private Double l16;

    @Column(name = "l17", nullable = true, precision = 0)
    private Double l17;

    @Column(name = "l18", nullable = true, precision = 0)
    private Double l18;

    @Column(name = "l19", nullable = true, precision = 0)
    private Double l19;

    @Column(name = "l20", nullable = true, precision = 0)
    private Double l20;

    @Column(name = "l21", nullable = true, precision = 0)
    private Double l21;

    @Column(name = "l22", nullable = true, precision = 0)
    private Double l22;

    @Column(name = "l23", nullable = true, precision = 0)
    private Double l23;

    @Column(name = "l24", nullable = true, precision = 0)
    private Double l24;

    @Column(name = "l25", nullable = true, precision = 0)
    private Double l25;

    @Column(name = "l26", nullable = true, precision = 0)
    private Double l26;

    @Column(name = "l27", nullable = true, precision = 0)
    private Double l27;

    @Column(name = "l28", nullable = true, precision = 0)
    private Double l28;

    @Column(name = "l29", nullable = true, precision = 0)
    private Double l29;

    @Column(name = "l30", nullable = true, precision = 0)
    private Double l30;

    @Column(name = "l31", nullable = true, precision = 0)
    private Double l31;

    @Column(name = "otamt", nullable = true, precision = 0)
    private Double otamt;

    @Column(name = "lv1", nullable = true, length = 7)
    private String lv1;

    @Column(name = "lv2", nullable = true, length = 7)
    private String lv2;

    @Column(name = "lv3", nullable = true, length = 7)
    private String lv3;

    @Column(name = "lv4", nullable = true, length = 7)
    private String lv4;

    @Column(name = "lv5", nullable = true, length = 7)
    private String lv5;

    @Column(name = "lv6", nullable = true, length = 7)
    private String lv6;

    @Column(name = "lv7", nullable = true, length = 7)
    private String lv7;

    @Column(name = "lv8", nullable = true, length = 7)
    private String lv8;

    @Column(name = "lv9", nullable = true, length = 7)
    private String lv9;

    @Column(name = "lv10", nullable = true, length = 7)
    private String lv10;

    @Column(name = "lv11", nullable = true, length = 7)
    private String lv11;

    @Column(name = "lv12", nullable = true, length = 7)
    private String lv12;

    @Column(name = "lv13", nullable = true, length = 7)
    private String lv13;

    @Column(name = "lv14", nullable = true, length = 7)
    private String lv14;

    @Column(name = "lv15", nullable = true, length = 7)
    private String lv15;

    @Column(name = "lv16", nullable = true, length = 7)
    private String lv16;

    @Column(name = "lv17", nullable = true, length = 7)
    private String lv17;

    @Column(name = "lv18", nullable = true, length = 7)
    private String lv18;

    @Column(name = "lv19", nullable = true, length = 7)
    private String lv19;

    @Column(name = "lv20", nullable = true, length = 7)
    private String lv20;

    @Column(name = "lv21", nullable = true, length = 7)
    private String lv21;

    @Column(name = "lv22", nullable = true, length = 7)
    private String lv22;

    @Column(name = "lv23", nullable = true, length = 7)
    private String lv23;

    @Column(name = "lv24", nullable = true, length = 7)
    private String lv24;

    @Column(name = "lv25", nullable = true, length = 7)
    private String lv25;

    @Column(name = "lv26", nullable = true, length = 7)
    private String lv26;

    @Column(name = "lv27", nullable = true, length = 7)
    private String lv27;

    @Column(name = "lv28", nullable = true, length = 7)
    private String lv28;

    @Column(name = "lv29", nullable = true, length = 7)
    private String lv29;

    @Column(name = "lv30", nullable = true, length = 7)
    private String lv30;

    @Column(name = "lv31", nullable = true, length = 7)
    private String lv31;

    @Column(name = "mot_hr", nullable = true, precision = 0)
    private Double motHr;

    @Column(name = "wk_hr", nullable = true, precision = 0)
    private Double wkHr;

    @Column(name = "tm1", nullable = true, length = 5)
    private String tm1;

    @Column(name = "tm2", nullable = true, length = 5)
    private String tm2;

    @Column(name = "tm3", nullable = true, length = 5)
    private String tm3;

    @Column(name = "tm4", nullable = true, length = 5)
    private String tm4;

    @Column(name = "tm5", nullable = true, length = 5)
    private String tm5;

    @Column(name = "tm6", nullable = true, length = 5)
    private String tm6;

    @Column(name = "tm7", nullable = true, length = 5)
    private String tm7;

    @Column(name = "tm8", nullable = true, length = 5)
    private String tm8;

    @Column(name = "tm9", nullable = true, length = 5)
    private String tm9;

    @Column(name = "tm10", nullable = true, length = 5)
    private String tm10;

    @Column(name = "tm11", nullable = true, length = 5)
    private String tm11;

    @Column(name = "tm12", nullable = true, length = 5)
    private String tm12;

    @Column(name = "tm13", nullable = true, length = 5)
    private String tm13;

    @Column(name = "tm14", nullable = true, length = 5)
    private String tm14;

    @Column(name = "tm15", nullable = true, length = 5)
    private String tm15;

    @Column(name = "tm16", nullable = true, length = 5)
    private String tm16;

    @Column(name = "tm17", nullable = true, length = 5)
    private String tm17;

    @Column(name = "tm18", nullable = true, length = 5)
    private String tm18;

    @Column(name = "tm19", nullable = true, length = 5)
    private String tm19;

    @Column(name = "tm20", nullable = true, length = 5)
    private String tm20;

    @Column(name = "tm21", nullable = true, length = 5)
    private String tm21;

    @Column(name = "tm22", nullable = true, length = 5)
    private String tm22;

    @Column(name = "tm23", nullable = true, length = 5)
    private String tm23;

    @Column(name = "tm24", nullable = true, length = 5)
    private String tm24;

    @Column(name = "tm25", nullable = true, length = 5)
    private String tm25;

    @Column(name = "tm26", nullable = true, length = 5)
    private String tm26;

    @Column(name = "tm27", nullable = true, length = 5)
    private String tm27;

    @Column(name = "tm28", nullable = true, length = 5)
    private String tm28;

    @Column(name = "tm29", nullable = true, length = 5)
    private String tm29;

    @Column(name = "tm30", nullable = true, length = 5)
    private String tm30;

    @Column(name = "tm31", nullable = true, length = 5)
    private String tm31;

    @Column(name = "sf1", nullable = true, length = 3)
    private String sf1;

    @Column(name = "sf2", nullable = true, length = 3)
    private String sf2;

    @Column(name = "sf3", nullable = true, length = 3)
    private String sf3;

    @Column(name = "sf4", nullable = true, length = 3)
    private String sf4;

    @Column(name = "sf5", nullable = true, length = 3)
    private String sf5;

    @Column(name = "sf6", nullable = true, length = 3)
    private String sf6;

    @Column(name = "sf7", nullable = true, length = 3)
    private String sf7;

    @Column(name = "sf8", nullable = true, length = 3)
    private String sf8;

    @Column(name = "sf9", nullable = true, length = 3)
    private String sf9;

    @Column(name = "sf10", nullable = true, length = 3)
    private String sf10;

    @Column(name = "sf11", nullable = true, length = 3)
    private String sf11;

    @Column(name = "sf12", nullable = true, length = 3)
    private String sf12;

    @Column(name = "sf13", nullable = true, length = 3)
    private String sf13;

    @Column(name = "sf14", nullable = true, length = 3)
    private String sf14;

    @Column(name = "sf15", nullable = true, length = 3)
    private String sf15;

    @Column(name = "sf16", nullable = true, length = 3)
    private String sf16;

    @Column(name = "sf17", nullable = true, length = 3)
    private String sf17;

    @Column(name = "sf18", nullable = true, length = 3)
    private String sf18;

    @Column(name = "sf19", nullable = true, length = 3)
    private String sf19;

    @Column(name = "sf20", nullable = true, length = 3)
    private String sf20;

    @Column(name = "sf21", nullable = true, length = 3)
    private String sf21;

    @Column(name = "sf22", nullable = true, length = 3)
    private String sf22;

    @Column(name = "sf23", nullable = true, length = 3)
    private String sf23;

    @Column(name = "sf24", nullable = true, length = 3)
    private String sf24;

    @Column(name = "sf25", nullable = true, length = 3)
    private String sf25;

    @Column(name = "sf26", nullable = true, length = 3)
    private String sf26;

    @Column(name = "sf27", nullable = true, length = 3)
    private String sf27;

    @Column(name = "sf28", nullable = true, length = 3)
    private String sf28;

    @Column(name = "sf29", nullable = true, length = 3)
    private String sf29;

    @Column(name = "sf30", nullable = true, length = 3)
    private String sf30;

    @Column(name = "sf31", nullable = true, length = 3)
    private String sf31;

    @Column(name = "d_att", nullable = true, precision = 0)
    private Double dAtt;

    @Column(name = "a_int", nullable = true, precision = 0)
    private Double aInt;

    @Column(name = "s_int", nullable = true, precision = 0)
    private Double sInt;

    @Column(name = "food", nullable = true, precision = 0)
    private Double food;

    @Column(name = "wk1", nullable = true)
    private Short wk1;

    @Column(name = "wk2", nullable = true)
    private Short wk2;

    @Column(name = "wk3", nullable = true)
    private Short wk3;

    @Column(name = "wk4", nullable = true)
    private Short wk4;

    @Column(name = "wk5", nullable = true)
    private Short wk5;

    @Column(name = "wk6", nullable = true)
    private Short wk6;

    @Column(name = "wk7", nullable = true)
    private Short wk7;

    @Column(name = "wk8", nullable = true)
    private Short wk8;

    @Column(name = "wk9", nullable = true)
    private Short wk9;

    @Column(name = "wk10", nullable = true)
    private Short wk10;

    @Column(name = "wk11", nullable = true)
    private Short wk11;

    @Column(name = "wk12", nullable = true)
    private Short wk12;

    @Column(name = "wk13", nullable = true)
    private Short wk13;

    @Column(name = "wk14", nullable = true)
    private Short wk14;

    @Column(name = "wk15", nullable = true)
    private Short wk15;

    @Column(name = "wk16", nullable = true)
    private Short wk16;

    @Column(name = "wk17", nullable = true)
    private Short wk17;

    @Column(name = "wk18", nullable = true)
    private Short wk18;

    @Column(name = "wk19", nullable = true)
    private Short wk19;

    @Column(name = "wk20", nullable = true)
    private Short wk20;

    @Column(name = "wk21", nullable = true)
    private Short wk21;

    @Column(name = "wk22", nullable = true)
    private Short wk22;

    @Column(name = "wk23", nullable = true)
    private Short wk23;

    @Column(name = "wk24", nullable = true)
    private Short wk24;

    @Column(name = "wk25", nullable = true)
    private Short wk25;

    @Column(name = "wk26", nullable = true)
    private Short wk26;

    @Column(name = "wk27", nullable = true)
    private Short wk27;

    @Column(name = "wk28", nullable = true)
    private Short wk28;

    @Column(name = "wk29", nullable = true)
    private Short wk29;

    @Column(name = "wk30", nullable = true)
    private Short wk30;

    @Column(name = "wk31", nullable = true)
    private Short wk31;

    @Column(name = "foodamt", nullable = true, precision = 0)
    private Double foodamt;

    @Column(name = "teaamt", nullable = true, precision = 0)
    private Double teaamt;

    @Column(name = "incentive", nullable = true, precision = 0)
    private Double incentive;

    @Column(name = "ot_amount", nullable = true, precision = 0)
    private Double otAmount;

    @Column(name = "ded_hr", nullable = true, precision = 0)
    private Double dedHr;

    @Column(name = "ded_amt", nullable = true, precision = 0)
    private Double dedAmt;

    @Column(name = "c_wday", nullable = true, precision = 0)
    private Double cWday;

    @Column(name = "man_food", nullable = true, precision = 0)
    private Double manFood;

    @Column(name = "fst_ot", nullable = true, precision = 0)
    private Double fstOt;

    @Column(name = "snd_ot", nullable = true, precision = 0)
    private Double sndOt;

    @Column(name = "fst_15", nullable = true, precision = 0)
    private Double fst15;

    @Column(name = "snd_15", nullable = true, precision = 0)
    private Double snd15;

    @Column(name = "fst_15hr", nullable = true, precision = 0)
    private Double fst15Hr;

    @Column(name = "snd_15hr", nullable = true, precision = 0)

    private Double snd15Hr;

    @Column(name = "hr_16", nullable = true, precision = 0)
    private Double hr16;

    @Column(name = "amt_16", nullable = true, precision = 0)
    private Double amt16;

    @Column(name = "hr_bal", nullable = true, precision = 0)
    private Double hrBal;

    @Column(name = "amt_bal", nullable = true, precision = 0)
    private Double amtBal;

    @Column(name = "hr_sun", nullable = true, precision = 0)
    private Double hrSun;

    @Column(name = "amt_sun", nullable = true, precision = 0)
    private Double amtSun;

    @Column(name = "food_no", nullable = true, precision = 0)
    private Double foodNo;

    @Column(name = "foodamt1", nullable = true, precision = 0)
    private Double foodamt1;

    @Column(name = "food_no1", nullable = true, precision = 0)
    private Double foodNo1;

    @Column(name = "food_sun", nullable = true, precision = 0)
    private Double foodSun;

    @Column(name = "m_no1", nullable = true, length = 10)
    private String mNo1;

    @Column(name = "m_no2", nullable = true, length = 10)
    private String mNo2;

    @Column(name = "m_no3", nullable = true, length = 10)
    private String mNo3;

    @Column(name = "m_no4", nullable = true, length = 10)
    private String mNo4;

    @Column(name = "m_no5", nullable = true, length = 10)
    private String mNo5;

    @Column(name = "m_no6", nullable = true, length = 10)
    private String mNo6;

    @Column(name = "m_no7", nullable = true, length = 10)
    private String mNo7;

    @Column(name = "m_no8", nullable = true, length = 10)
    private String mNo8;

    @Column(name = "m_no9", nullable = true, length = 10)
    private String mNo9;

    @Column(name = "m_no10", nullable = true, length = 10)
    private String mNo10;

    @Column(name = "m_no11", nullable = true, length = 10)
    private String mNo11;

    @Column(name = "m_no12", nullable = true, length = 10)
    private String mNo12;

    @Column(name = "m_no13", nullable = true, length = 10)
    private String mNo13;

    @Column(name = "m_no14", nullable = true, length = 10)
    private String mNo14;

    @Column(name = "m_no15", nullable = true, length = 10)
    private String mNo15;

    @Column(name = "m_no16", nullable = true, length = 10)
    private String mNo16;

    @Column(name = "m_no17", nullable = true, length = 10)
    private String mNo17;

    @Column(name = "m_no18", nullable = true, length = 10)
    private String mNo18;

    @Column(name = "m_no19", nullable = true, length = 10)
    private String mNo19;

    @Column(name = "m_no20", nullable = true, length = 10)
    private String mNo20;

    @Column(name = "m_no21", nullable = true, length = 10)
    private String mNo21;

    @Column(name = "m_no22", nullable = true, length = 10)
    private String mNo22;

    @Column(name = "m_no23", nullable = true, length = 10)
    private String mNo23;

    @Column(name = "m_no24", nullable = true, length = 10)
    private String mNo24;

    @Column(name = "m_no25", nullable = true, length = 10)
    private String mNo25;

    @Column(name = "m_no26", nullable = true, length = 10)
    private String mNo26;

    @Column(name = "m_no27", nullable = true, length = 10)
    private String mNo27;

    @Column(name = "m_no28", nullable = true, length = 10)
    private String mNo28;

    @Column(name = "m_no29", nullable = true, length = 10)
    private String mNo29;

    @Column(name = "m_no30", nullable = true, length = 10)
    private String mNo30;

    @Column(name = "m_no31", nullable = true, length = 10)
    private String mNo31;

    @Column(name = "hr_2", nullable = true, precision = 0)
    private Double hr2;

    @Column(name = "amt_2", nullable = true, precision = 0)
    private Double amt2;

    @Column(name = "hr_2_bal", nullable = true, precision = 0)
    private Double hr2Bal;

    @Column(name = "amt_2_bal", nullable = true, precision = 0)
    private Double amt2Bal;

    @Column(name = "gwr1_hrs", nullable = true, precision = 0)
    private Double gwr1Hrs;

    @Column(name = "gwr1_amt", nullable = true, precision = 0)
    private Double gwr1Amt;

    @Column(name = "gwr2_hrs", nullable = true, precision = 0)
    private Double gwr2Hrs;

    @Column(name = "gwr2_amt", nullable = true, precision = 0)
    private Double gwr2Amt;

    public HourtId getId() {
        return id;
    }

    public void setId(HourtId id) {
        this.id = id;
    }

    public Double getThr() {
        return thr;
    }

    public void setThr(Double thr) {
        this.thr = thr;
    }

    public Double getTothr() {
        return tothr;
    }

    public void setTothr(Double tothr) {
        this.tothr = tothr;
    }

    public Double getWhr1() {
        return whr1;
    }

    public void setWhr1(Double whr1) {
        this.whr1 = whr1;
    }

    public Double getWhr2() {
        return whr2;
    }

    public void setWhr2(Double whr2) {
        this.whr2 = whr2;
    }

    public Double getWhr3() {
        return whr3;
    }

    public void setWhr3(Double whr3) {
        this.whr3 = whr3;
    }

    public Double getWhr4() {
        return whr4;
    }

    public void setWhr4(Double whr4) {
        this.whr4 = whr4;
    }

    public Double getWhr5() {
        return whr5;
    }

    public void setWhr5(Double whr5) {
        this.whr5 = whr5;
    }

    public Double getWhr6() {
        return whr6;
    }

    public void setWhr6(Double whr6) {
        this.whr6 = whr6;
    }

    public Double getWhr7() {
        return whr7;
    }

    public void setWhr7(Double whr7) {
        this.whr7 = whr7;
    }

    public Double getWhr8() {
        return whr8;
    }

    public void setWhr8(Double whr8) {
        this.whr8 = whr8;
    }

    public Double getWhr9() {
        return whr9;
    }

    public void setWhr9(Double whr9) {
        this.whr9 = whr9;
    }

    public Double getWhr10() {
        return whr10;
    }

    public void setWhr10(Double whr10) {
        this.whr10 = whr10;
    }

    public Double getWhr11() {
        return whr11;
    }

    public void setWhr11(Double whr11) {
        this.whr11 = whr11;
    }

    public Double getWhr12() {
        return whr12;
    }

    public void setWhr12(Double whr12) {
        this.whr12 = whr12;
    }

    public Double getWhr13() {
        return whr13;
    }

    public void setWhr13(Double whr13) {
        this.whr13 = whr13;
    }

    public Double getWhr14() {
        return whr14;
    }

    public void setWhr14(Double whr14) {
        this.whr14 = whr14;
    }

    public Double getWhr15() {
        return whr15;
    }

    public void setWhr15(Double whr15) {
        this.whr15 = whr15;
    }

    public Double getWhr16() {
        return whr16;
    }

    public void setWhr16(Double whr16) {
        this.whr16 = whr16;
    }

    public Double getWhr17() {
        return whr17;
    }

    public void setWhr17(Double whr17) {
        this.whr17 = whr17;
    }

    public Double getWhr18() {
        return whr18;
    }

    public void setWhr18(Double whr18) {
        this.whr18 = whr18;
    }

    public Double getWhr19() {
        return whr19;
    }

    public void setWhr19(Double whr19) {
        this.whr19 = whr19;
    }

    public Double getWhr20() {
        return whr20;
    }

    public void setWhr20(Double whr20) {
        this.whr20 = whr20;
    }

    public Double getWhr21() {
        return whr21;
    }

    public void setWhr21(Double whr21) {
        this.whr21 = whr21;
    }

    public Double getWhr22() {
        return whr22;
    }

    public void setWhr22(Double whr22) {
        this.whr22 = whr22;
    }

    public Double getWhr23() {
        return whr23;
    }

    public void setWhr23(Double whr23) {
        this.whr23 = whr23;
    }

    public Double getWhr24() {
        return whr24;
    }

    public void setWhr24(Double whr24) {
        this.whr24 = whr24;
    }

    public Double getWhr25() {
        return whr25;
    }

    public void setWhr25(Double whr25) {
        this.whr25 = whr25;
    }

    public Double getWhr26() {
        return whr26;
    }

    public void setWhr26(Double whr26) {
        this.whr26 = whr26;
    }

    public Double getWhr27() {
        return whr27;
    }

    public void setWhr27(Double whr27) {
        this.whr27 = whr27;
    }

    public Double getWhr28() {
        return whr28;
    }

    public void setWhr28(Double whr28) {
        this.whr28 = whr28;
    }

    public Double getWhr29() {
        return whr29;
    }

    public void setWhr29(Double whr29) {
        this.whr29 = whr29;
    }

    public Double getWhr30() {
        return whr30;
    }

    public void setWhr30(Double whr30) {
        this.whr30 = whr30;
    }

    public Double getWhr31() {
        return whr31;
    }

    public void setWhr31(Double whr31) {
        this.whr31 = whr31;
    }

    public Double getOhr1() {
        return ohr1;
    }

    public void setOhr1(Double ohr1) {
        this.ohr1 = ohr1;
    }

    public Double getOhr2() {
        return ohr2;
    }

    public void setOhr2(Double ohr2) {
        this.ohr2 = ohr2;
    }

    public Double getOhr3() {
        return ohr3;
    }

    public void setOhr3(Double ohr3) {
        this.ohr3 = ohr3;
    }

    public Double getOhr4() {
        return ohr4;
    }

    public void setOhr4(Double ohr4) {
        this.ohr4 = ohr4;
    }

    public Double getOhr5() {
        return ohr5;
    }

    public void setOhr5(Double ohr5) {
        this.ohr5 = ohr5;
    }

    public Double getOhr6() {
        return ohr6;
    }

    public void setOhr6(Double ohr6) {
        this.ohr6 = ohr6;
    }

    public Double getOhr7() {
        return ohr7;
    }

    public void setOhr7(Double ohr7) {
        this.ohr7 = ohr7;
    }

    public Double getOhr8() {
        return ohr8;
    }

    public void setOhr8(Double ohr8) {
        this.ohr8 = ohr8;
    }

    public Double getOhr9() {
        return ohr9;
    }

    public void setOhr9(Double ohr9) {
        this.ohr9 = ohr9;
    }

    public Double getOhr10() {
        return ohr10;
    }

    public void setOhr10(Double ohr10) {
        this.ohr10 = ohr10;
    }

    public Double getOhr11() {
        return ohr11;
    }

    public void setOhr11(Double ohr11) {
        this.ohr11 = ohr11;
    }

    public Double getOhr12() {
        return ohr12;
    }

    public void setOhr12(Double ohr12) {
        this.ohr12 = ohr12;
    }

    public Double getOhr13() {
        return ohr13;
    }

    public void setOhr13(Double ohr13) {
        this.ohr13 = ohr13;
    }

    public Double getOhr14() {
        return ohr14;
    }

    public void setOhr14(Double ohr14) {
        this.ohr14 = ohr14;
    }

    public Double getOhr15() {
        return ohr15;
    }

    public void setOhr15(Double ohr15) {
        this.ohr15 = ohr15;
    }

    public Double getOhr16() {
        return ohr16;
    }

    public void setOhr16(Double ohr16) {
        this.ohr16 = ohr16;
    }

    public Double getOhr17() {
        return ohr17;
    }

    public void setOhr17(Double ohr17) {
        this.ohr17 = ohr17;
    }

    public Double getOhr18() {
        return ohr18;
    }

    public void setOhr18(Double ohr18) {
        this.ohr18 = ohr18;
    }

    public Double getOhr19() {
        return ohr19;
    }

    public void setOhr19(Double ohr19) {
        this.ohr19 = ohr19;
    }

    public Double getOhr20() {
        return ohr20;
    }

    public void setOhr20(Double ohr20) {
        this.ohr20 = ohr20;
    }

    public Double getOhr21() {
        return ohr21;
    }

    public void setOhr21(Double ohr21) {
        this.ohr21 = ohr21;
    }

    public Double getOhr22() {
        return ohr22;
    }

    public void setOhr22(Double ohr22) {
        this.ohr22 = ohr22;
    }

    public Double getOhr23() {
        return ohr23;
    }

    public void setOhr23(Double ohr23) {
        this.ohr23 = ohr23;
    }

    public Double getOhr24() {
        return ohr24;
    }

    public void setOhr24(Double ohr24) {
        this.ohr24 = ohr24;
    }

    public Double getOhr25() {
        return ohr25;
    }

    public void setOhr25(Double ohr25) {
        this.ohr25 = ohr25;
    }

    public Double getOhr26() {
        return ohr26;
    }

    public void setOhr26(Double ohr26) {
        this.ohr26 = ohr26;
    }

    public Double getOhr27() {
        return ohr27;
    }

    public void setOhr27(Double ohr27) {
        this.ohr27 = ohr27;
    }

    public Double getOhr28() {
        return ohr28;
    }

    public void setOhr28(Double ohr28) {
        this.ohr28 = ohr28;
    }

    public Double getOhr29() {
        return ohr29;
    }

    public void setOhr29(Double ohr29) {
        this.ohr29 = ohr29;
    }

    public Double getOhr30() {
        return ohr30;
    }

    public void setOhr30(Double ohr30) {
        this.ohr30 = ohr30;
    }

    public Double getOhr31() {
        return ohr31;
    }

    public void setOhr31(Double ohr31) {
        this.ohr31 = ohr31;
    }

    public Double getB1() {
        return b1;
    }

    public void setB1(Double b1) {
        this.b1 = b1;
    }

    public Double getB2() {
        return b2;
    }

    public void setB2(Double b2) {
        this.b2 = b2;
    }

    public Double getB3() {
        return b3;
    }

    public void setB3(Double b3) {
        this.b3 = b3;
    }

    public Double getB4() {
        return b4;
    }

    public void setB4(Double b4) {
        this.b4 = b4;
    }

    public Double getB5() {
        return b5;
    }

    public void setB5(Double b5) {
        this.b5 = b5;
    }

    public Double getB6() {
        return b6;
    }

    public void setB6(Double b6) {
        this.b6 = b6;
    }

    public Double getB7() {
        return b7;
    }

    public void setB7(Double b7) {
        this.b7 = b7;
    }

    public Double getB8() {
        return b8;
    }

    public void setB8(Double b8) {
        this.b8 = b8;
    }

    public Double getB9() {
        return b9;
    }

    public void setB9(Double b9) {
        this.b9 = b9;
    }

    public Double getB10() {
        return b10;
    }

    public void setB10(Double b10) {
        this.b10 = b10;
    }

    public Double getB11() {
        return b11;
    }

    public void setB11(Double b11) {
        this.b11 = b11;
    }

    public Double getB12() {
        return b12;
    }

    public void setB12(Double b12) {
        this.b12 = b12;
    }

    public Double getB13() {
        return b13;
    }

    public void setB13(Double b13) {
        this.b13 = b13;
    }

    public Double getB14() {
        return b14;
    }

    public void setB14(Double b14) {
        this.b14 = b14;
    }

    public Double getB15() {
        return b15;
    }

    public void setB15(Double b15) {
        this.b15 = b15;
    }

    public Double getB16() {
        return b16;
    }

    public void setB16(Double b16) {
        this.b16 = b16;
    }

    public Double getB17() {
        return b17;
    }

    public void setB17(Double b17) {
        this.b17 = b17;
    }

    public Double getB18() {
        return b18;
    }

    public void setB18(Double b18) {
        this.b18 = b18;
    }

    public Double getB19() {
        return b19;
    }

    public void setB19(Double b19) {
        this.b19 = b19;
    }

    public Double getB20() {
        return b20;
    }

    public void setB20(Double b20) {
        this.b20 = b20;
    }

    public Double getB21() {
        return b21;
    }

    public void setB21(Double b21) {
        this.b21 = b21;
    }

    public Double getB22() {
        return b22;
    }

    public void setB22(Double b22) {
        this.b22 = b22;
    }

    public Double getB23() {
        return b23;
    }

    public void setB23(Double b23) {
        this.b23 = b23;
    }

    public Double getB24() {
        return b24;
    }

    public void setB24(Double b24) {
        this.b24 = b24;
    }

    public Double getB25() {
        return b25;
    }

    public void setB25(Double b25) {
        this.b25 = b25;
    }

    public Double getB26() {
        return b26;
    }

    public void setB26(Double b26) {
        this.b26 = b26;
    }

    public Double getB27() {
        return b27;
    }

    public void setB27(Double b27) {
        this.b27 = b27;
    }

    public Double getB28() {
        return b28;
    }

    public void setB28(Double b28) {
        this.b28 = b28;
    }

    public Double getB29() {
        return b29;
    }

    public void setB29(Double b29) {
        this.b29 = b29;
    }

    public Double getB30() {
        return b30;
    }

    public void setB30(Double b30) {
        this.b30 = b30;
    }

    public Double getB31() {
        return b31;
    }

    public void setB31(Double b31) {
        this.b31 = b31;
    }

    public Double getD1() {
        return d1;
    }

    public void setD1(Double d1) {
        this.d1 = d1;
    }

    public Double getD2() {
        return d2;
    }

    public void setD2(Double d2) {
        this.d2 = d2;
    }

    public Double getD3() {
        return d3;
    }

    public void setD3(Double d3) {
        this.d3 = d3;
    }

    public Double getD4() {
        return d4;
    }

    public void setD4(Double d4) {
        this.d4 = d4;
    }

    public Double getD5() {
        return d5;
    }

    public void setD5(Double d5) {
        this.d5 = d5;
    }

    public Double getD6() {
        return d6;
    }

    public void setD6(Double d6) {
        this.d6 = d6;
    }

    public Double getD7() {
        return d7;
    }

    public void setD7(Double d7) {
        this.d7 = d7;
    }

    public Double getD8() {
        return d8;
    }

    public void setD8(Double d8) {
        this.d8 = d8;
    }

    public Double getD9() {
        return d9;
    }

    public void setD9(Double d9) {
        this.d9 = d9;
    }

    public Double getD10() {
        return d10;
    }

    public void setD10(Double d10) {
        this.d10 = d10;
    }

    public Double getD11() {
        return d11;
    }

    public void setD11(Double d11) {
        this.d11 = d11;
    }

    public Double getD12() {
        return d12;
    }

    public void setD12(Double d12) {
        this.d12 = d12;
    }

    public Double getD13() {
        return d13;
    }

    public void setD13(Double d13) {
        this.d13 = d13;
    }

    public Double getD14() {
        return d14;
    }

    public void setD14(Double d14) {
        this.d14 = d14;
    }

    public Double getD15() {
        return d15;
    }

    public void setD15(Double d15) {
        this.d15 = d15;
    }

    public Double getD16() {
        return d16;
    }

    public void setD16(Double d16) {
        this.d16 = d16;
    }

    public Double getD17() {
        return d17;
    }

    public void setD17(Double d17) {
        this.d17 = d17;
    }

    public Double getD18() {
        return d18;
    }

    public void setD18(Double d18) {
        this.d18 = d18;
    }

    public Double getD19() {
        return d19;
    }

    public void setD19(Double d19) {
        this.d19 = d19;
    }

    public Double getD20() {
        return d20;
    }

    public void setD20(Double d20) {
        this.d20 = d20;
    }

    public Double getD21() {
        return d21;
    }

    public void setD21(Double d21) {
        this.d21 = d21;
    }

    public Double getD22() {
        return d22;
    }

    public void setD22(Double d22) {
        this.d22 = d22;
    }

    public Double getD23() {
        return d23;
    }

    public void setD23(Double d23) {
        this.d23 = d23;
    }

    public Double getD24() {
        return d24;
    }

    public void setD24(Double d24) {
        this.d24 = d24;
    }

    public Double getD25() {
        return d25;
    }

    public void setD25(Double d25) {
        this.d25 = d25;
    }

    public Double getD26() {
        return d26;
    }

    public void setD26(Double d26) {
        this.d26 = d26;
    }

    public Double getD27() {
        return d27;
    }

    public void setD27(Double d27) {
        this.d27 = d27;
    }

    public Double getD28() {
        return d28;
    }

    public void setD28(Double d28) {
        this.d28 = d28;
    }

    public Double getD29() {
        return d29;
    }

    public void setD29(Double d29) {
        this.d29 = d29;
    }

    public Double getD30() {
        return d30;
    }

    public void setD30(Double d30) {
        this.d30 = d30;
    }

    public Double getD31() {
        return d31;
    }

    public void setD31(Double d31) {
        this.d31 = d31;
    }

    public Double getLshr() {
        return lshr;
    }

    public void setLshr(Double lshr) {
        this.lshr = lshr;
    }

    public Double getDhr() {
        return dhr;
    }

    public void setDhr(Double dhr) {
        this.dhr = dhr;
    }

    public Double getL1() {
        return l1;
    }

    public void setL1(Double l1) {
        this.l1 = l1;
    }

    public Double getL2() {
        return l2;
    }

    public void setL2(Double l2) {
        this.l2 = l2;
    }

    public Double getL3() {
        return l3;
    }

    public void setL3(Double l3) {
        this.l3 = l3;
    }

    public Double getL4() {
        return l4;
    }

    public void setL4(Double l4) {
        this.l4 = l4;
    }

    public Double getL5() {
        return l5;
    }

    public void setL5(Double l5) {
        this.l5 = l5;
    }

    public Double getL6() {
        return l6;
    }

    public void setL6(Double l6) {
        this.l6 = l6;
    }

    public Double getL7() {
        return l7;
    }

    public void setL7(Double l7) {
        this.l7 = l7;
    }

    public Double getL8() {
        return l8;
    }

    public void setL8(Double l8) {
        this.l8 = l8;
    }

    public Double getL9() {
        return l9;
    }

    public void setL9(Double l9) {
        this.l9 = l9;
    }

    public Double getL10() {
        return l10;
    }

    public void setL10(Double l10) {
        this.l10 = l10;
    }

    public Double getL11() {
        return l11;
    }

    public void setL11(Double l11) {
        this.l11 = l11;
    }

    public Double getL12() {
        return l12;
    }

    public void setL12(Double l12) {
        this.l12 = l12;
    }

    public Double getL13() {
        return l13;
    }

    public void setL13(Double l13) {
        this.l13 = l13;
    }

    public Double getL14() {
        return l14;
    }

    public void setL14(Double l14) {
        this.l14 = l14;
    }

    public Double getL15() {
        return l15;
    }

    public void setL15(Double l15) {
        this.l15 = l15;
    }

    public Double getL16() {
        return l16;
    }

    public void setL16(Double l16) {
        this.l16 = l16;
    }

    public Double getL17() {
        return l17;
    }

    public void setL17(Double l17) {
        this.l17 = l17;
    }

    public Double getL18() {
        return l18;
    }

    public void setL18(Double l18) {
        this.l18 = l18;
    }

    public Double getL19() {
        return l19;
    }

    public void setL19(Double l19) {
        this.l19 = l19;
    }

    public Double getL20() {
        return l20;
    }

    public void setL20(Double l20) {
        this.l20 = l20;
    }

    public Double getL21() {
        return l21;
    }

    public void setL21(Double l21) {
        this.l21 = l21;
    }

    public Double getL22() {
        return l22;
    }

    public void setL22(Double l22) {
        this.l22 = l22;
    }

    public Double getL23() {
        return l23;
    }

    public void setL23(Double l23) {
        this.l23 = l23;
    }

    public Double getL24() {
        return l24;
    }

    public void setL24(Double l24) {
        this.l24 = l24;
    }

    public Double getL25() {
        return l25;
    }

    public void setL25(Double l25) {
        this.l25 = l25;
    }

    public Double getL26() {
        return l26;
    }

    public void setL26(Double l26) {
        this.l26 = l26;
    }

    public Double getL27() {
        return l27;
    }

    public void setL27(Double l27) {
        this.l27 = l27;
    }

    public Double getL28() {
        return l28;
    }

    public void setL28(Double l28) {
        this.l28 = l28;
    }

    public Double getL29() {
        return l29;
    }

    public void setL29(Double l29) {
        this.l29 = l29;
    }

    public Double getL30() {
        return l30;
    }

    public void setL30(Double l30) {
        this.l30 = l30;
    }

    public Double getL31() {
        return l31;
    }

    public void setL31(Double l31) {
        this.l31 = l31;
    }

    public Double getOtamt() {
        return otamt;
    }

    public void setOtamt(Double otamt) {
        this.otamt = otamt;
    }

    public String getLv1() {
        return lv1;
    }

    public void setLv1(String lv1) {
        this.lv1 = lv1;
    }

    public String getLv2() {
        return lv2;
    }

    public void setLv2(String lv2) {
        this.lv2 = lv2;
    }

    public String getLv3() {
        return lv3;
    }

    public void setLv3(String lv3) {
        this.lv3 = lv3;
    }

    public String getLv4() {
        return lv4;
    }

    public void setLv4(String lv4) {
        this.lv4 = lv4;
    }

    public String getLv5() {
        return lv5;
    }

    public void setLv5(String lv5) {
        this.lv5 = lv5;
    }

    public String getLv6() {
        return lv6;
    }

    public void setLv6(String lv6) {
        this.lv6 = lv6;
    }

    public String getLv7() {
        return lv7;
    }

    public void setLv7(String lv7) {
        this.lv7 = lv7;
    }

    public String getLv8() {
        return lv8;
    }

    public void setLv8(String lv8) {
        this.lv8 = lv8;
    }

    public String getLv9() {
        return lv9;
    }

    public void setLv9(String lv9) {
        this.lv9 = lv9;
    }

    public String getLv10() {
        return lv10;
    }

    public void setLv10(String lv10) {
        this.lv10 = lv10;
    }

    public String getLv11() {
        return lv11;
    }

    public void setLv11(String lv11) {
        this.lv11 = lv11;
    }

    public String getLv12() {
        return lv12;
    }

    public void setLv12(String lv12) {
        this.lv12 = lv12;
    }

    public String getLv13() {
        return lv13;
    }

    public void setLv13(String lv13) {
        this.lv13 = lv13;
    }

    public String getLv14() {
        return lv14;
    }

    public void setLv14(String lv14) {
        this.lv14 = lv14;
    }

    public String getLv15() {
        return lv15;
    }

    public void setLv15(String lv15) {
        this.lv15 = lv15;
    }

    public String getLv16() {
        return lv16;
    }

    public void setLv16(String lv16) {
        this.lv16 = lv16;
    }

    public String getLv17() {
        return lv17;
    }

    public void setLv17(String lv17) {
        this.lv17 = lv17;
    }

    public String getLv18() {
        return lv18;
    }

    public void setLv18(String lv18) {
        this.lv18 = lv18;
    }

    public String getLv19() {
        return lv19;
    }

    public void setLv19(String lv19) {
        this.lv19 = lv19;
    }

    public String getLv20() {
        return lv20;
    }

    public void setLv20(String lv20) {
        this.lv20 = lv20;
    }

    public String getLv21() {
        return lv21;
    }

    public void setLv21(String lv21) {
        this.lv21 = lv21;
    }

    public String getLv22() {
        return lv22;
    }

    public void setLv22(String lv22) {
        this.lv22 = lv22;
    }

    public String getLv23() {
        return lv23;
    }

    public void setLv23(String lv23) {
        this.lv23 = lv23;
    }

    public String getLv24() {
        return lv24;
    }

    public void setLv24(String lv24) {
        this.lv24 = lv24;
    }

    public String getLv25() {
        return lv25;
    }

    public void setLv25(String lv25) {
        this.lv25 = lv25;
    }

    public String getLv26() {
        return lv26;
    }

    public void setLv26(String lv26) {
        this.lv26 = lv26;
    }

    public String getLv27() {
        return lv27;
    }

    public void setLv27(String lv27) {
        this.lv27 = lv27;
    }

    public String getLv28() {
        return lv28;
    }

    public void setLv28(String lv28) {
        this.lv28 = lv28;
    }

    public String getLv29() {
        return lv29;
    }

    public void setLv29(String lv29) {
        this.lv29 = lv29;
    }

    public String getLv30() {
        return lv30;
    }

    public void setLv30(String lv30) {
        this.lv30 = lv30;
    }

    public String getLv31() {
        return lv31;
    }

    public void setLv31(String lv31) {
        this.lv31 = lv31;
    }

    public Double getMotHr() {
        return motHr;
    }

    public void setMotHr(Double motHr) {
        this.motHr = motHr;
    }

    public Double getWkHr() {
        return wkHr;
    }

    public void setWkHr(Double wkHr) {
        this.wkHr = wkHr;
    }

    public String getTm1() {
        return tm1;
    }

    public void setTm1(String tm1) {
        this.tm1 = tm1;
    }

    public String getTm2() {
        return tm2;
    }

    public void setTm2(String tm2) {
        this.tm2 = tm2;
    }

    public String getTm3() {
        return tm3;
    }

    public void setTm3(String tm3) {
        this.tm3 = tm3;
    }

    public String getTm4() {
        return tm4;
    }

    public void setTm4(String tm4) {
        this.tm4 = tm4;
    }

    public String getTm5() {
        return tm5;
    }

    public void setTm5(String tm5) {
        this.tm5 = tm5;
    }

    public String getTm6() {
        return tm6;
    }

    public void setTm6(String tm6) {
        this.tm6 = tm6;
    }

    public String getTm7() {
        return tm7;
    }

    public void setTm7(String tm7) {
        this.tm7 = tm7;
    }

    public String getTm8() {
        return tm8;
    }

    public void setTm8(String tm8) {
        this.tm8 = tm8;
    }

    public String getTm9() {
        return tm9;
    }

    public void setTm9(String tm9) {
        this.tm9 = tm9;
    }

    public String getTm10() {
        return tm10;
    }

    public void setTm10(String tm10) {
        this.tm10 = tm10;
    }

    public String getTm11() {
        return tm11;
    }

    public void setTm11(String tm11) {
        this.tm11 = tm11;
    }

    public String getTm12() {
        return tm12;
    }

    public void setTm12(String tm12) {
        this.tm12 = tm12;
    }

    public String getTm13() {
        return tm13;
    }

    public void setTm13(String tm13) {
        this.tm13 = tm13;
    }

    public String getTm14() {
        return tm14;
    }

    public void setTm14(String tm14) {
        this.tm14 = tm14;
    }

    public String getTm15() {
        return tm15;
    }

    public void setTm15(String tm15) {
        this.tm15 = tm15;
    }

    public String getTm16() {
        return tm16;
    }

    public void setTm16(String tm16) {
        this.tm16 = tm16;
    }

    public String getTm17() {
        return tm17;
    }

    public void setTm17(String tm17) {
        this.tm17 = tm17;
    }

    public String getTm18() {
        return tm18;
    }

    public void setTm18(String tm18) {
        this.tm18 = tm18;
    }

    public String getTm19() {
        return tm19;
    }

    public void setTm19(String tm19) {
        this.tm19 = tm19;
    }

    public String getTm20() {
        return tm20;
    }

    public void setTm20(String tm20) {
        this.tm20 = tm20;
    }

    public String getTm21() {
        return tm21;
    }

    public void setTm21(String tm21) {
        this.tm21 = tm21;
    }

    public String getTm22() {
        return tm22;
    }

    public void setTm22(String tm22) {
        this.tm22 = tm22;
    }

    public String getTm23() {
        return tm23;
    }

    public void setTm23(String tm23) {
        this.tm23 = tm23;
    }

    public String getTm24() {
        return tm24;
    }

    public void setTm24(String tm24) {
        this.tm24 = tm24;
    }

    public String getTm25() {
        return tm25;
    }

    public void setTm25(String tm25) {
        this.tm25 = tm25;
    }

    public String getTm26() {
        return tm26;
    }

    public void setTm26(String tm26) {
        this.tm26 = tm26;
    }

    public String getTm27() {
        return tm27;
    }

    public void setTm27(String tm27) {
        this.tm27 = tm27;
    }

    public String getTm28() {
        return tm28;
    }

    public void setTm28(String tm28) {
        this.tm28 = tm28;
    }

    public String getTm29() {
        return tm29;
    }

    public void setTm29(String tm29) {
        this.tm29 = tm29;
    }

    public String getTm30() {
        return tm30;
    }

    public void setTm30(String tm30) {
        this.tm30 = tm30;
    }

    public String getTm31() {
        return tm31;
    }

    public void setTm31(String tm31) {
        this.tm31 = tm31;
    }

    public String getSf1() {
        return sf1;
    }

    public void setSf1(String sf1) {
        this.sf1 = sf1;
    }

    public String getSf2() {
        return sf2;
    }

    public void setSf2(String sf2) {
        this.sf2 = sf2;
    }

    public String getSf3() {
        return sf3;
    }

    public void setSf3(String sf3) {
        this.sf3 = sf3;
    }

    public String getSf4() {
        return sf4;
    }

    public void setSf4(String sf4) {
        this.sf4 = sf4;
    }

    public String getSf5() {
        return sf5;
    }

    public void setSf5(String sf5) {
        this.sf5 = sf5;
    }

    public String getSf6() {
        return sf6;
    }

    public void setSf6(String sf6) {
        this.sf6 = sf6;
    }

    public String getSf7() {
        return sf7;
    }

    public void setSf7(String sf7) {
        this.sf7 = sf7;
    }

    public String getSf8() {
        return sf8;
    }

    public void setSf8(String sf8) {
        this.sf8 = sf8;
    }

    public String getSf9() {
        return sf9;
    }

    public void setSf9(String sf9) {
        this.sf9 = sf9;
    }

    public String getSf10() {
        return sf10;
    }

    public void setSf10(String sf10) {
        this.sf10 = sf10;
    }

    public String getSf11() {
        return sf11;
    }

    public void setSf11(String sf11) {
        this.sf11 = sf11;
    }

    public String getSf12() {
        return sf12;
    }

    public void setSf12(String sf12) {
        this.sf12 = sf12;
    }

    public String getSf13() {
        return sf13;
    }

    public void setSf13(String sf13) {
        this.sf13 = sf13;
    }

    public String getSf14() {
        return sf14;
    }

    public void setSf14(String sf14) {
        this.sf14 = sf14;
    }

    public String getSf15() {
        return sf15;
    }

    public void setSf15(String sf15) {
        this.sf15 = sf15;
    }

    public String getSf16() {
        return sf16;
    }

    public void setSf16(String sf16) {
        this.sf16 = sf16;
    }

    public String getSf17() {
        return sf17;
    }

    public void setSf17(String sf17) {
        this.sf17 = sf17;
    }

    public String getSf18() {
        return sf18;
    }

    public void setSf18(String sf18) {
        this.sf18 = sf18;
    }

    public String getSf19() {
        return sf19;
    }

    public void setSf19(String sf19) {
        this.sf19 = sf19;
    }

    public String getSf20() {
        return sf20;
    }

    public void setSf20(String sf20) {
        this.sf20 = sf20;
    }

    public String getSf21() {
        return sf21;
    }

    public void setSf21(String sf21) {
        this.sf21 = sf21;
    }

    public String getSf22() {
        return sf22;
    }

    public void setSf22(String sf22) {
        this.sf22 = sf22;
    }

    public String getSf23() {
        return sf23;
    }

    public void setSf23(String sf23) {
        this.sf23 = sf23;
    }

    public String getSf24() {
        return sf24;
    }

    public void setSf24(String sf24) {
        this.sf24 = sf24;
    }

    public String getSf25() {
        return sf25;
    }

    public void setSf25(String sf25) {
        this.sf25 = sf25;
    }

    public String getSf26() {
        return sf26;
    }

    public void setSf26(String sf26) {
        this.sf26 = sf26;
    }

    public String getSf27() {
        return sf27;
    }

    public void setSf27(String sf27) {
        this.sf27 = sf27;
    }

    public String getSf28() {
        return sf28;
    }

    public void setSf28(String sf28) {
        this.sf28 = sf28;
    }

    public String getSf29() {
        return sf29;
    }

    public void setSf29(String sf29) {
        this.sf29 = sf29;
    }

    public String getSf30() {
        return sf30;
    }

    public void setSf30(String sf30) {
        this.sf30 = sf30;
    }

    public String getSf31() {
        return sf31;
    }

    public void setSf31(String sf31) {
        this.sf31 = sf31;
    }

    public Double getdAtt() {
        return dAtt;
    }

    public void setdAtt(Double dAtt) {
        this.dAtt = dAtt;
    }

    public Double getaInt() {
        return aInt;
    }

    public void setaInt(Double aInt) {
        this.aInt = aInt;
    }

    public Double getsInt() {
        return sInt;
    }

    public void setsInt(Double sInt) {
        this.sInt = sInt;
    }

    public Double getFood() {
        return food;
    }

    public void setFood(Double food) {
        this.food = food;
    }

    public Short getWk1() {
        return wk1;
    }

    public void setWk1(Short wk1) {
        this.wk1 = wk1;
    }

    public Short getWk2() {
        return wk2;
    }

    public void setWk2(Short wk2) {
        this.wk2 = wk2;
    }

    public Short getWk3() {
        return wk3;
    }

    public void setWk3(Short wk3) {
        this.wk3 = wk3;
    }

    public Short getWk4() {
        return wk4;
    }

    public void setWk4(Short wk4) {
        this.wk4 = wk4;
    }

    public Short getWk5() {
        return wk5;
    }

    public void setWk5(Short wk5) {
        this.wk5 = wk5;
    }

    public Short getWk6() {
        return wk6;
    }

    public void setWk6(Short wk6) {
        this.wk6 = wk6;
    }

    public Short getWk7() {
        return wk7;
    }

    public void setWk7(Short wk7) {
        this.wk7 = wk7;
    }

    public Short getWk8() {
        return wk8;
    }

    public void setWk8(Short wk8) {
        this.wk8 = wk8;
    }

    public Short getWk9() {
        return wk9;
    }

    public void setWk9(Short wk9) {
        this.wk9 = wk9;
    }

    public Short getWk10() {
        return wk10;
    }

    public void setWk10(Short wk10) {
        this.wk10 = wk10;
    }

    public Short getWk11() {
        return wk11;
    }

    public void setWk11(Short wk11) {
        this.wk11 = wk11;
    }

    public Short getWk12() {
        return wk12;
    }

    public void setWk12(Short wk12) {
        this.wk12 = wk12;
    }

    public Short getWk13() {
        return wk13;
    }

    public void setWk13(Short wk13) {
        this.wk13 = wk13;
    }

    public Short getWk14() {
        return wk14;
    }

    public void setWk14(Short wk14) {
        this.wk14 = wk14;
    }

    public Short getWk15() {
        return wk15;
    }

    public void setWk15(Short wk15) {
        this.wk15 = wk15;
    }

    public Short getWk16() {
        return wk16;
    }

    public void setWk16(Short wk16) {
        this.wk16 = wk16;
    }

    public Short getWk17() {
        return wk17;
    }

    public void setWk17(Short wk17) {
        this.wk17 = wk17;
    }

    public Short getWk18() {
        return wk18;
    }

    public void setWk18(Short wk18) {
        this.wk18 = wk18;
    }

    public Short getWk19() {
        return wk19;
    }

    public void setWk19(Short wk19) {
        this.wk19 = wk19;
    }

    public Short getWk20() {
        return wk20;
    }

    public void setWk20(Short wk20) {
        this.wk20 = wk20;
    }

    public Short getWk21() {
        return wk21;
    }

    public void setWk21(Short wk21) {
        this.wk21 = wk21;
    }

    public Short getWk22() {
        return wk22;
    }

    public void setWk22(Short wk22) {
        this.wk22 = wk22;
    }

    public Short getWk23() {
        return wk23;
    }

    public void setWk23(Short wk23) {
        this.wk23 = wk23;
    }

    public Short getWk24() {
        return wk24;
    }

    public void setWk24(Short wk24) {
        this.wk24 = wk24;
    }

    public Short getWk25() {
        return wk25;
    }

    public void setWk25(Short wk25) {
        this.wk25 = wk25;
    }

    public Short getWk26() {
        return wk26;
    }

    public void setWk26(Short wk26) {
        this.wk26 = wk26;
    }

    public Short getWk27() {
        return wk27;
    }

    public void setWk27(Short wk27) {
        this.wk27 = wk27;
    }

    public Short getWk28() {
        return wk28;
    }

    public void setWk28(Short wk28) {
        this.wk28 = wk28;
    }

    public Short getWk29() {
        return wk29;
    }

    public void setWk29(Short wk29) {
        this.wk29 = wk29;
    }

    public Short getWk30() {
        return wk30;
    }

    public void setWk30(Short wk30) {
        this.wk30 = wk30;
    }

    public Short getWk31() {
        return wk31;
    }

    public void setWk31(Short wk31) {
        this.wk31 = wk31;
    }

    public Double getFoodamt() {
        return foodamt;
    }

    public void setFoodamt(Double foodamt) {
        this.foodamt = foodamt;
    }

    public Double getTeaamt() {
        return teaamt;
    }

    public void setTeaamt(Double teaamt) {
        this.teaamt = teaamt;
    }

    public Double getIncentive() {
        return incentive;
    }

    public void setIncentive(Double incentive) {
        this.incentive = incentive;
    }

    public Double getOtAmount() {
        return otAmount;
    }

    public void setOtAmount(Double otAmount) {
        this.otAmount = otAmount;
    }

    public Double getDedHr() {
        return dedHr;
    }

    public void setDedHr(Double dedHr) {
        this.dedHr = dedHr;
    }

    public Double getDedAmt() {
        return dedAmt;
    }

    public void setDedAmt(Double dedAmt) {
        this.dedAmt = dedAmt;
    }

    public Double getcWday() {
        return cWday;
    }

    public void setcWday(Double cWday) {
        this.cWday = cWday;
    }

    public Double getManFood() {
        return manFood;
    }

    public void setManFood(Double manFood) {
        this.manFood = manFood;
    }

    public Double getFstOt() {
        return fstOt;
    }

    public void setFstOt(Double fstOt) {
        this.fstOt = fstOt;
    }

    public Double getSndOt() {
        return sndOt;
    }

    public void setSndOt(Double sndOt) {
        this.sndOt = sndOt;
    }

    public Double getFst15() {
        return fst15;
    }

    public void setFst15(Double fst15) {
        this.fst15 = fst15;
    }

    public Double getSnd15() {
        return snd15;
    }

    public void setSnd15(Double snd15) {
        this.snd15 = snd15;
    }

    public Double getFst15Hr() {
        return fst15Hr;
    }

    public void setFst15Hr(Double fst15Hr) {
        this.fst15Hr = fst15Hr;
    }

    public Double getSnd15Hr() {
        return snd15Hr;
    }

    public void setSnd15Hr(Double snd15Hr) {
        this.snd15Hr = snd15Hr;
    }

    public Double getHr16() {
        return hr16;
    }

    public void setHr16(Double hr16) {
        this.hr16 = hr16;
    }

    public Double getAmt16() {
        return amt16;
    }

    public void setAmt16(Double amt16) {
        this.amt16 = amt16;
    }

    public Double getHrBal() {
        return hrBal;
    }

    public void setHrBal(Double hrBal) {
        this.hrBal = hrBal;
    }

    public Double getAmtBal() {
        return amtBal;
    }

    public void setAmtBal(Double amtBal) {
        this.amtBal = amtBal;
    }

    public Double getHrSun() {
        return hrSun;
    }

    public void setHrSun(Double hrSun) {
        this.hrSun = hrSun;
    }

    public Double getAmtSun() {
        return amtSun;
    }

    public void setAmtSun(Double amtSun) {
        this.amtSun = amtSun;
    }

    public Double getFoodNo() {
        return foodNo;
    }

    public void setFoodNo(Double foodNo) {
        this.foodNo = foodNo;
    }

    public Double getFoodamt1() {
        return foodamt1;
    }

    public void setFoodamt1(Double foodamt1) {
        this.foodamt1 = foodamt1;
    }

    public Double getFoodNo1() {
        return foodNo1;
    }

    public void setFoodNo1(Double foodNo1) {
        this.foodNo1 = foodNo1;
    }

    public Double getFoodSun() {
        return foodSun;
    }

    public void setFoodSun(Double foodSun) {
        this.foodSun = foodSun;
    }

    public String getmNo1() {
        return mNo1;
    }

    public void setmNo1(String mNo1) {
        this.mNo1 = mNo1;
    }

    public String getmNo2() {
        return mNo2;
    }

    public void setmNo2(String mNo2) {
        this.mNo2 = mNo2;
    }

    public String getmNo3() {
        return mNo3;
    }

    public void setmNo3(String mNo3) {
        this.mNo3 = mNo3;
    }

    public String getmNo4() {
        return mNo4;
    }

    public void setmNo4(String mNo4) {
        this.mNo4 = mNo4;
    }

    public String getmNo5() {
        return mNo5;
    }

    public void setmNo5(String mNo5) {
        this.mNo5 = mNo5;
    }

    public String getmNo6() {
        return mNo6;
    }

    public void setmNo6(String mNo6) {
        this.mNo6 = mNo6;
    }

    public String getmNo7() {
        return mNo7;
    }

    public void setmNo7(String mNo7) {
        this.mNo7 = mNo7;
    }

    public String getmNo8() {
        return mNo8;
    }

    public void setmNo8(String mNo8) {
        this.mNo8 = mNo8;
    }

    public String getmNo9() {
        return mNo9;
    }

    public void setmNo9(String mNo9) {
        this.mNo9 = mNo9;
    }

    public String getmNo10() {
        return mNo10;
    }

    public void setmNo10(String mNo10) {
        this.mNo10 = mNo10;
    }

    public String getmNo11() {
        return mNo11;
    }

    public void setmNo11(String mNo11) {
        this.mNo11 = mNo11;
    }

    public String getmNo12() {
        return mNo12;
    }

    public void setmNo12(String mNo12) {
        this.mNo12 = mNo12;
    }

    public String getmNo13() {
        return mNo13;
    }

    public void setmNo13(String mNo13) {
        this.mNo13 = mNo13;
    }

    public String getmNo14() {
        return mNo14;
    }

    public void setmNo14(String mNo14) {
        this.mNo14 = mNo14;
    }

    public String getmNo15() {
        return mNo15;
    }

    public void setmNo15(String mNo15) {
        this.mNo15 = mNo15;
    }

    public String getmNo16() {
        return mNo16;
    }

    public void setmNo16(String mNo16) {
        this.mNo16 = mNo16;
    }

    public String getmNo17() {
        return mNo17;
    }

    public void setmNo17(String mNo17) {
        this.mNo17 = mNo17;
    }

    public String getmNo18() {
        return mNo18;
    }

    public void setmNo18(String mNo18) {
        this.mNo18 = mNo18;
    }

    public String getmNo19() {
        return mNo19;
    }

    public void setmNo19(String mNo19) {
        this.mNo19 = mNo19;
    }

    public String getmNo20() {
        return mNo20;
    }

    public void setmNo20(String mNo20) {
        this.mNo20 = mNo20;
    }

    public String getmNo21() {
        return mNo21;
    }

    public void setmNo21(String mNo21) {
        this.mNo21 = mNo21;
    }

    public String getmNo22() {
        return mNo22;
    }

    public void setmNo22(String mNo22) {
        this.mNo22 = mNo22;
    }

    public String getmNo23() {
        return mNo23;
    }

    public void setmNo23(String mNo23) {
        this.mNo23 = mNo23;
    }

    public String getmNo24() {
        return mNo24;
    }

    public void setmNo24(String mNo24) {
        this.mNo24 = mNo24;
    }

    public String getmNo25() {
        return mNo25;
    }

    public void setmNo25(String mNo25) {
        this.mNo25 = mNo25;
    }

    public String getmNo26() {
        return mNo26;
    }

    public void setmNo26(String mNo26) {
        this.mNo26 = mNo26;
    }

    public String getmNo27() {
        return mNo27;
    }

    public void setmNo27(String mNo27) {
        this.mNo27 = mNo27;
    }

    public String getmNo28() {
        return mNo28;
    }

    public void setmNo28(String mNo28) {
        this.mNo28 = mNo28;
    }

    public String getmNo29() {
        return mNo29;
    }

    public void setmNo29(String mNo29) {
        this.mNo29 = mNo29;
    }

    public String getmNo30() {
        return mNo30;
    }

    public void setmNo30(String mNo30) {
        this.mNo30 = mNo30;
    }

    public String getmNo31() {
        return mNo31;
    }

    public void setmNo31(String mNo31) {
        this.mNo31 = mNo31;
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

    public Double getGwr1Hrs() {
        return gwr1Hrs;
    }

    public void setGwr1Hrs(Double gwr1Hrs) {
        this.gwr1Hrs = gwr1Hrs;
    }

    public Double getGwr1Amt() {
        return gwr1Amt;
    }

    public void setGwr1Amt(Double gwr1Amt) {
        this.gwr1Amt = gwr1Amt;
    }

    public Double getGwr2Hrs() {
        return gwr2Hrs;
    }

    public void setGwr2Hrs(Double gwr2Hrs) {
        this.gwr2Hrs = gwr2Hrs;
    }

    public Double getGwr2Amt() {
        return gwr2Amt;
    }

    public void setGwr2Amt(Double gwr2Amt) {
        this.gwr2Amt = gwr2Amt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hourt hourt = (Hourt) o;
        return Objects.equals(id, hourt.id) &&
            Objects.equals(thr, hourt.thr) &&
            Objects.equals(tothr, hourt.tothr) &&
            Objects.equals(whr1, hourt.whr1) &&
            Objects.equals(whr2, hourt.whr2) &&
            Objects.equals(whr3, hourt.whr3) &&
            Objects.equals(whr4, hourt.whr4) &&
            Objects.equals(whr5, hourt.whr5) &&
            Objects.equals(whr6, hourt.whr6) &&
            Objects.equals(whr7, hourt.whr7) &&
            Objects.equals(whr8, hourt.whr8) &&
            Objects.equals(whr9, hourt.whr9) &&
            Objects.equals(whr10, hourt.whr10) &&
            Objects.equals(whr11, hourt.whr11) &&
            Objects.equals(whr12, hourt.whr12) &&
            Objects.equals(whr13, hourt.whr13) &&
            Objects.equals(whr14, hourt.whr14) &&
            Objects.equals(whr15, hourt.whr15) &&
            Objects.equals(whr16, hourt.whr16) &&
            Objects.equals(whr17, hourt.whr17) &&
            Objects.equals(whr18, hourt.whr18) &&
            Objects.equals(whr19, hourt.whr19) &&
            Objects.equals(whr20, hourt.whr20) &&
            Objects.equals(whr21, hourt.whr21) &&
            Objects.equals(whr22, hourt.whr22) &&
            Objects.equals(whr23, hourt.whr23) &&
            Objects.equals(whr24, hourt.whr24) &&
            Objects.equals(whr25, hourt.whr25) &&
            Objects.equals(whr26, hourt.whr26) &&
            Objects.equals(whr27, hourt.whr27) &&
            Objects.equals(whr28, hourt.whr28) &&
            Objects.equals(whr29, hourt.whr29) &&
            Objects.equals(whr30, hourt.whr30) &&
            Objects.equals(whr31, hourt.whr31) &&
            Objects.equals(ohr1, hourt.ohr1) &&
            Objects.equals(ohr2, hourt.ohr2) &&
            Objects.equals(ohr3, hourt.ohr3) &&
            Objects.equals(ohr4, hourt.ohr4) &&
            Objects.equals(ohr5, hourt.ohr5) &&
            Objects.equals(ohr6, hourt.ohr6) &&
            Objects.equals(ohr7, hourt.ohr7) &&
            Objects.equals(ohr8, hourt.ohr8) &&
            Objects.equals(ohr9, hourt.ohr9) &&
            Objects.equals(ohr10, hourt.ohr10) &&
            Objects.equals(ohr11, hourt.ohr11) &&
            Objects.equals(ohr12, hourt.ohr12) &&
            Objects.equals(ohr13, hourt.ohr13) &&
            Objects.equals(ohr14, hourt.ohr14) &&
            Objects.equals(ohr15, hourt.ohr15) &&
            Objects.equals(ohr16, hourt.ohr16) &&
            Objects.equals(ohr17, hourt.ohr17) &&
            Objects.equals(ohr18, hourt.ohr18) &&
            Objects.equals(ohr19, hourt.ohr19) &&
            Objects.equals(ohr20, hourt.ohr20) &&
            Objects.equals(ohr21, hourt.ohr21) &&
            Objects.equals(ohr22, hourt.ohr22) &&
            Objects.equals(ohr23, hourt.ohr23) &&
            Objects.equals(ohr24, hourt.ohr24) &&
            Objects.equals(ohr25, hourt.ohr25) &&
            Objects.equals(ohr26, hourt.ohr26) &&
            Objects.equals(ohr27, hourt.ohr27) &&
            Objects.equals(ohr28, hourt.ohr28) &&
            Objects.equals(ohr29, hourt.ohr29) &&
            Objects.equals(ohr30, hourt.ohr30) &&
            Objects.equals(ohr31, hourt.ohr31) &&
            Objects.equals(b1, hourt.b1) &&
            Objects.equals(b2, hourt.b2) &&
            Objects.equals(b3, hourt.b3) &&
            Objects.equals(b4, hourt.b4) &&
            Objects.equals(b5, hourt.b5) &&
            Objects.equals(b6, hourt.b6) &&
            Objects.equals(b7, hourt.b7) &&
            Objects.equals(b8, hourt.b8) &&
            Objects.equals(b9, hourt.b9) &&
            Objects.equals(b10, hourt.b10) &&
            Objects.equals(b11, hourt.b11) &&
            Objects.equals(b12, hourt.b12) &&
            Objects.equals(b13, hourt.b13) &&
            Objects.equals(b14, hourt.b14) &&
            Objects.equals(b15, hourt.b15) &&
            Objects.equals(b16, hourt.b16) &&
            Objects.equals(b17, hourt.b17) &&
            Objects.equals(b18, hourt.b18) &&
            Objects.equals(b19, hourt.b19) &&
            Objects.equals(b20, hourt.b20) &&
            Objects.equals(b21, hourt.b21) &&
            Objects.equals(b22, hourt.b22) &&
            Objects.equals(b23, hourt.b23) &&
            Objects.equals(b24, hourt.b24) &&
            Objects.equals(b25, hourt.b25) &&
            Objects.equals(b26, hourt.b26) &&
            Objects.equals(b27, hourt.b27) &&
            Objects.equals(b28, hourt.b28) &&
            Objects.equals(b29, hourt.b29) &&
            Objects.equals(b30, hourt.b30) &&
            Objects.equals(b31, hourt.b31) &&
            Objects.equals(d1, hourt.d1) &&
            Objects.equals(d2, hourt.d2) &&
            Objects.equals(d3, hourt.d3) &&
            Objects.equals(d4, hourt.d4) &&
            Objects.equals(d5, hourt.d5) &&
            Objects.equals(d6, hourt.d6) &&
            Objects.equals(d7, hourt.d7) &&
            Objects.equals(d8, hourt.d8) &&
            Objects.equals(d9, hourt.d9) &&
            Objects.equals(d10, hourt.d10) &&
            Objects.equals(d11, hourt.d11) &&
            Objects.equals(d12, hourt.d12) &&
            Objects.equals(d13, hourt.d13) &&
            Objects.equals(d14, hourt.d14) &&
            Objects.equals(d15, hourt.d15) &&
            Objects.equals(d16, hourt.d16) &&
            Objects.equals(d17, hourt.d17) &&
            Objects.equals(d18, hourt.d18) &&
            Objects.equals(d19, hourt.d19) &&
            Objects.equals(d20, hourt.d20) &&
            Objects.equals(d21, hourt.d21) &&
            Objects.equals(d22, hourt.d22) &&
            Objects.equals(d23, hourt.d23) &&
            Objects.equals(d24, hourt.d24) &&
            Objects.equals(d25, hourt.d25) &&
            Objects.equals(d26, hourt.d26) &&
            Objects.equals(d27, hourt.d27) &&
            Objects.equals(d28, hourt.d28) &&
            Objects.equals(d29, hourt.d29) &&
            Objects.equals(d30, hourt.d30) &&
            Objects.equals(d31, hourt.d31) &&
            Objects.equals(lshr, hourt.lshr) &&
            Objects.equals(dhr, hourt.dhr) &&
            Objects.equals(l1, hourt.l1) &&
            Objects.equals(l2, hourt.l2) &&
            Objects.equals(l3, hourt.l3) &&
            Objects.equals(l4, hourt.l4) &&
            Objects.equals(l5, hourt.l5) &&
            Objects.equals(l6, hourt.l6) &&
            Objects.equals(l7, hourt.l7) &&
            Objects.equals(l8, hourt.l8) &&
            Objects.equals(l9, hourt.l9) &&
            Objects.equals(l10, hourt.l10) &&
            Objects.equals(l11, hourt.l11) &&
            Objects.equals(l12, hourt.l12) &&
            Objects.equals(l13, hourt.l13) &&
            Objects.equals(l14, hourt.l14) &&
            Objects.equals(l15, hourt.l15) &&
            Objects.equals(l16, hourt.l16) &&
            Objects.equals(l17, hourt.l17) &&
            Objects.equals(l18, hourt.l18) &&
            Objects.equals(l19, hourt.l19) &&
            Objects.equals(l20, hourt.l20) &&
            Objects.equals(l21, hourt.l21) &&
            Objects.equals(l22, hourt.l22) &&
            Objects.equals(l23, hourt.l23) &&
            Objects.equals(l24, hourt.l24) &&
            Objects.equals(l25, hourt.l25) &&
            Objects.equals(l26, hourt.l26) &&
            Objects.equals(l27, hourt.l27) &&
            Objects.equals(l28, hourt.l28) &&
            Objects.equals(l29, hourt.l29) &&
            Objects.equals(l30, hourt.l30) &&
            Objects.equals(l31, hourt.l31) &&
            Objects.equals(otamt, hourt.otamt) &&
            Objects.equals(lv1, hourt.lv1) &&
            Objects.equals(lv2, hourt.lv2) &&
            Objects.equals(lv3, hourt.lv3) &&
            Objects.equals(lv4, hourt.lv4) &&
            Objects.equals(lv5, hourt.lv5) &&
            Objects.equals(lv6, hourt.lv6) &&
            Objects.equals(lv7, hourt.lv7) &&
            Objects.equals(lv8, hourt.lv8) &&
            Objects.equals(lv9, hourt.lv9) &&
            Objects.equals(lv10, hourt.lv10) &&
            Objects.equals(lv11, hourt.lv11) &&
            Objects.equals(lv12, hourt.lv12) &&
            Objects.equals(lv13, hourt.lv13) &&
            Objects.equals(lv14, hourt.lv14) &&
            Objects.equals(lv15, hourt.lv15) &&
            Objects.equals(lv16, hourt.lv16) &&
            Objects.equals(lv17, hourt.lv17) &&
            Objects.equals(lv18, hourt.lv18) &&
            Objects.equals(lv19, hourt.lv19) &&
            Objects.equals(lv20, hourt.lv20) &&
            Objects.equals(lv21, hourt.lv21) &&
            Objects.equals(lv22, hourt.lv22) &&
            Objects.equals(lv23, hourt.lv23) &&
            Objects.equals(lv24, hourt.lv24) &&
            Objects.equals(lv25, hourt.lv25) &&
            Objects.equals(lv26, hourt.lv26) &&
            Objects.equals(lv27, hourt.lv27) &&
            Objects.equals(lv28, hourt.lv28) &&
            Objects.equals(lv29, hourt.lv29) &&
            Objects.equals(lv30, hourt.lv30) &&
            Objects.equals(lv31, hourt.lv31) &&
            Objects.equals(motHr, hourt.motHr) &&
            Objects.equals(wkHr, hourt.wkHr) &&
            Objects.equals(tm1, hourt.tm1) &&
            Objects.equals(tm2, hourt.tm2) &&
            Objects.equals(tm3, hourt.tm3) &&
            Objects.equals(tm4, hourt.tm4) &&
            Objects.equals(tm5, hourt.tm5) &&
            Objects.equals(tm6, hourt.tm6) &&
            Objects.equals(tm7, hourt.tm7) &&
            Objects.equals(tm8, hourt.tm8) &&
            Objects.equals(tm9, hourt.tm9) &&
            Objects.equals(tm10, hourt.tm10) &&
            Objects.equals(tm11, hourt.tm11) &&
            Objects.equals(tm12, hourt.tm12) &&
            Objects.equals(tm13, hourt.tm13) &&
            Objects.equals(tm14, hourt.tm14) &&
            Objects.equals(tm15, hourt.tm15) &&
            Objects.equals(tm16, hourt.tm16) &&
            Objects.equals(tm17, hourt.tm17) &&
            Objects.equals(tm18, hourt.tm18) &&
            Objects.equals(tm19, hourt.tm19) &&
            Objects.equals(tm20, hourt.tm20) &&
            Objects.equals(tm21, hourt.tm21) &&
            Objects.equals(tm22, hourt.tm22) &&
            Objects.equals(tm23, hourt.tm23) &&
            Objects.equals(tm24, hourt.tm24) &&
            Objects.equals(tm25, hourt.tm25) &&
            Objects.equals(tm26, hourt.tm26) &&
            Objects.equals(tm27, hourt.tm27) &&
            Objects.equals(tm28, hourt.tm28) &&
            Objects.equals(tm29, hourt.tm29) &&
            Objects.equals(tm30, hourt.tm30) &&
            Objects.equals(tm31, hourt.tm31) &&
            Objects.equals(sf1, hourt.sf1) &&
            Objects.equals(sf2, hourt.sf2) &&
            Objects.equals(sf3, hourt.sf3) &&
            Objects.equals(sf4, hourt.sf4) &&
            Objects.equals(sf5, hourt.sf5) &&
            Objects.equals(sf6, hourt.sf6) &&
            Objects.equals(sf7, hourt.sf7) &&
            Objects.equals(sf8, hourt.sf8) &&
            Objects.equals(sf9, hourt.sf9) &&
            Objects.equals(sf10, hourt.sf10) &&
            Objects.equals(sf11, hourt.sf11) &&
            Objects.equals(sf12, hourt.sf12) &&
            Objects.equals(sf13, hourt.sf13) &&
            Objects.equals(sf14, hourt.sf14) &&
            Objects.equals(sf15, hourt.sf15) &&
            Objects.equals(sf16, hourt.sf16) &&
            Objects.equals(sf17, hourt.sf17) &&
            Objects.equals(sf18, hourt.sf18) &&
            Objects.equals(sf19, hourt.sf19) &&
            Objects.equals(sf20, hourt.sf20) &&
            Objects.equals(sf21, hourt.sf21) &&
            Objects.equals(sf22, hourt.sf22) &&
            Objects.equals(sf23, hourt.sf23) &&
            Objects.equals(sf24, hourt.sf24) &&
            Objects.equals(sf25, hourt.sf25) &&
            Objects.equals(sf26, hourt.sf26) &&
            Objects.equals(sf27, hourt.sf27) &&
            Objects.equals(sf28, hourt.sf28) &&
            Objects.equals(sf29, hourt.sf29) &&
            Objects.equals(sf30, hourt.sf30) &&
            Objects.equals(sf31, hourt.sf31) &&
            Objects.equals(dAtt, hourt.dAtt) &&
            Objects.equals(aInt, hourt.aInt) &&
            Objects.equals(sInt, hourt.sInt) &&
            Objects.equals(food, hourt.food) &&
            Objects.equals(wk1, hourt.wk1) &&
            Objects.equals(wk2, hourt.wk2) &&
            Objects.equals(wk3, hourt.wk3) &&
            Objects.equals(wk4, hourt.wk4) &&
            Objects.equals(wk5, hourt.wk5) &&
            Objects.equals(wk6, hourt.wk6) &&
            Objects.equals(wk7, hourt.wk7) &&
            Objects.equals(wk8, hourt.wk8) &&
            Objects.equals(wk9, hourt.wk9) &&
            Objects.equals(wk10, hourt.wk10) &&
            Objects.equals(wk11, hourt.wk11) &&
            Objects.equals(wk12, hourt.wk12) &&
            Objects.equals(wk13, hourt.wk13) &&
            Objects.equals(wk14, hourt.wk14) &&
            Objects.equals(wk15, hourt.wk15) &&
            Objects.equals(wk16, hourt.wk16) &&
            Objects.equals(wk17, hourt.wk17) &&
            Objects.equals(wk18, hourt.wk18) &&
            Objects.equals(wk19, hourt.wk19) &&
            Objects.equals(wk20, hourt.wk20) &&
            Objects.equals(wk21, hourt.wk21) &&
            Objects.equals(wk22, hourt.wk22) &&
            Objects.equals(wk23, hourt.wk23) &&
            Objects.equals(wk24, hourt.wk24) &&
            Objects.equals(wk25, hourt.wk25) &&
            Objects.equals(wk26, hourt.wk26) &&
            Objects.equals(wk27, hourt.wk27) &&
            Objects.equals(wk28, hourt.wk28) &&
            Objects.equals(wk29, hourt.wk29) &&
            Objects.equals(wk30, hourt.wk30) &&
            Objects.equals(wk31, hourt.wk31) &&
            Objects.equals(foodamt, hourt.foodamt) &&
            Objects.equals(teaamt, hourt.teaamt) &&
            Objects.equals(incentive, hourt.incentive) &&
            Objects.equals(otAmount, hourt.otAmount) &&
            Objects.equals(dedHr, hourt.dedHr) &&
            Objects.equals(dedAmt, hourt.dedAmt) &&
            Objects.equals(cWday, hourt.cWday) &&
            Objects.equals(manFood, hourt.manFood) &&
            Objects.equals(fstOt, hourt.fstOt) &&
            Objects.equals(sndOt, hourt.sndOt) &&
            Objects.equals(fst15, hourt.fst15) &&
            Objects.equals(snd15, hourt.snd15) &&
            Objects.equals(fst15Hr, hourt.fst15Hr) &&
            Objects.equals(snd15Hr, hourt.snd15Hr) &&
            Objects.equals(hr16, hourt.hr16) &&
            Objects.equals(amt16, hourt.amt16) &&
            Objects.equals(hrBal, hourt.hrBal) &&
            Objects.equals(amtBal, hourt.amtBal) &&
            Objects.equals(hrSun, hourt.hrSun) &&
            Objects.equals(amtSun, hourt.amtSun) &&
            Objects.equals(foodNo, hourt.foodNo) &&
            Objects.equals(foodamt1, hourt.foodamt1) &&
            Objects.equals(foodNo1, hourt.foodNo1) &&
            Objects.equals(foodSun, hourt.foodSun) &&
            Objects.equals(mNo1, hourt.mNo1) &&
            Objects.equals(mNo2, hourt.mNo2) &&
            Objects.equals(mNo3, hourt.mNo3) &&
            Objects.equals(mNo4, hourt.mNo4) &&
            Objects.equals(mNo5, hourt.mNo5) &&
            Objects.equals(mNo6, hourt.mNo6) &&
            Objects.equals(mNo7, hourt.mNo7) &&
            Objects.equals(mNo8, hourt.mNo8) &&
            Objects.equals(mNo9, hourt.mNo9) &&
            Objects.equals(mNo10, hourt.mNo10) &&
            Objects.equals(mNo11, hourt.mNo11) &&
            Objects.equals(mNo12, hourt.mNo12) &&
            Objects.equals(mNo13, hourt.mNo13) &&
            Objects.equals(mNo14, hourt.mNo14) &&
            Objects.equals(mNo15, hourt.mNo15) &&
            Objects.equals(mNo16, hourt.mNo16) &&
            Objects.equals(mNo17, hourt.mNo17) &&
            Objects.equals(mNo18, hourt.mNo18) &&
            Objects.equals(mNo19, hourt.mNo19) &&
            Objects.equals(mNo20, hourt.mNo20) &&
            Objects.equals(mNo21, hourt.mNo21) &&
            Objects.equals(mNo22, hourt.mNo22) &&
            Objects.equals(mNo23, hourt.mNo23) &&
            Objects.equals(mNo24, hourt.mNo24) &&
            Objects.equals(mNo25, hourt.mNo25) &&
            Objects.equals(mNo26, hourt.mNo26) &&
            Objects.equals(mNo27, hourt.mNo27) &&
            Objects.equals(mNo28, hourt.mNo28) &&
            Objects.equals(mNo29, hourt.mNo29) &&
            Objects.equals(mNo30, hourt.mNo30) &&
            Objects.equals(mNo31, hourt.mNo31) &&
            Objects.equals(hr2, hourt.hr2) &&
            Objects.equals(amt2, hourt.amt2) &&
            Objects.equals(hr2Bal, hourt.hr2Bal) &&
            Objects.equals(amt2Bal, hourt.amt2Bal) &&
            Objects.equals(gwr1Hrs, hourt.gwr1Hrs) &&
            Objects.equals(gwr1Amt, hourt.gwr1Amt) &&
            Objects.equals(gwr2Hrs, hourt.gwr2Hrs) &&
            Objects.equals(gwr2Amt, hourt.gwr2Amt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, thr, tothr, whr1, whr2, whr3, whr4, whr5, whr6, whr7, whr8, whr9, whr10, whr11, whr12, whr13, whr14, whr15, whr16, whr17, whr18, whr19, whr20, whr21, whr22, whr23, whr24, whr25, whr26, whr27, whr28, whr29, whr30, whr31, ohr1, ohr2, ohr3, ohr4, ohr5, ohr6, ohr7, ohr8, ohr9, ohr10, ohr11, ohr12, ohr13, ohr14, ohr15, ohr16, ohr17, ohr18, ohr19, ohr20, ohr21, ohr22, ohr23, ohr24, ohr25, ohr26, ohr27, ohr28, ohr29, ohr30, ohr31, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30, b31, d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25, d26, d27, d28, d29, d30, d31, lshr, dhr, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16, l17, l18, l19, l20, l21, l22, l23, l24, l25, l26, l27, l28, l29, l30, l31, otamt, lv1, lv2, lv3, lv4, lv5, lv6, lv7, lv8, lv9, lv10, lv11, lv12, lv13, lv14, lv15, lv16, lv17, lv18, lv19, lv20, lv21, lv22, lv23, lv24, lv25, lv26, lv27, lv28, lv29, lv30, lv31, motHr, wkHr, tm1, tm2, tm3, tm4, tm5, tm6, tm7, tm8, tm9, tm10, tm11, tm12, tm13, tm14, tm15, tm16, tm17, tm18, tm19, tm20, tm21, tm22, tm23, tm24, tm25, tm26, tm27, tm28, tm29, tm30, tm31, sf1, sf2, sf3, sf4, sf5, sf6, sf7, sf8, sf9, sf10, sf11, sf12, sf13, sf14, sf15, sf16, sf17, sf18, sf19, sf20, sf21, sf22, sf23, sf24, sf25, sf26, sf27, sf28, sf29, sf30, sf31, dAtt, aInt, sInt, food, wk1, wk2, wk3, wk4, wk5, wk6, wk7, wk8, wk9, wk10, wk11, wk12, wk13, wk14, wk15, wk16, wk17, wk18, wk19, wk20, wk21, wk22, wk23, wk24, wk25, wk26, wk27, wk28, wk29, wk30, wk31, foodamt, teaamt, incentive, otAmount, dedHr, dedAmt, cWday, manFood, fstOt, sndOt, fst15, snd15, fst15Hr, snd15Hr, hr16, amt16, hrBal, amtBal, hrSun, amtSun, foodNo, foodamt1, foodNo1, foodSun, mNo1, mNo2, mNo3, mNo4, mNo5, mNo6, mNo7, mNo8, mNo9, mNo10, mNo11, mNo12, mNo13, mNo14, mNo15, mNo16, mNo17, mNo18, mNo19, mNo20, mNo21, mNo22, mNo23, mNo24, mNo25, mNo26, mNo27, mNo28, mNo29, mNo30, mNo31, hr2, amt2, hr2Bal, amt2Bal, gwr1Hrs, gwr1Amt, gwr2Hrs, gwr2Amt);
    }

    @Override
    public String toString() {
        return "Hourt{" +
            "id=" + id +
            ", thr=" + thr +
            ", tothr=" + tothr +
            ", whr1=" + whr1 +
            ", whr2=" + whr2 +
            ", whr3=" + whr3 +
            ", whr4=" + whr4 +
            ", whr5=" + whr5 +
            ", whr6=" + whr6 +
            ", whr7=" + whr7 +
            ", whr8=" + whr8 +
            ", whr9=" + whr9 +
            ", whr10=" + whr10 +
            ", whr11=" + whr11 +
            ", whr12=" + whr12 +
            ", whr13=" + whr13 +
            ", whr14=" + whr14 +
            ", whr15=" + whr15 +
            ", whr16=" + whr16 +
            ", whr17=" + whr17 +
            ", whr18=" + whr18 +
            ", whr19=" + whr19 +
            ", whr20=" + whr20 +
            ", whr21=" + whr21 +
            ", whr22=" + whr22 +
            ", whr23=" + whr23 +
            ", whr24=" + whr24 +
            ", whr25=" + whr25 +
            ", whr26=" + whr26 +
            ", whr27=" + whr27 +
            ", whr28=" + whr28 +
            ", whr29=" + whr29 +
            ", whr30=" + whr30 +
            ", whr31=" + whr31 +
            ", ohr1=" + ohr1 +
            ", ohr2=" + ohr2 +
            ", ohr3=" + ohr3 +
            ", ohr4=" + ohr4 +
            ", ohr5=" + ohr5 +
            ", ohr6=" + ohr6 +
            ", ohr7=" + ohr7 +
            ", ohr8=" + ohr8 +
            ", ohr9=" + ohr9 +
            ", ohr10=" + ohr10 +
            ", ohr11=" + ohr11 +
            ", ohr12=" + ohr12 +
            ", ohr13=" + ohr13 +
            ", ohr14=" + ohr14 +
            ", ohr15=" + ohr15 +
            ", ohr16=" + ohr16 +
            ", ohr17=" + ohr17 +
            ", ohr18=" + ohr18 +
            ", ohr19=" + ohr19 +
            ", ohr20=" + ohr20 +
            ", ohr21=" + ohr21 +
            ", ohr22=" + ohr22 +
            ", ohr23=" + ohr23 +
            ", ohr24=" + ohr24 +
            ", ohr25=" + ohr25 +
            ", ohr26=" + ohr26 +
            ", ohr27=" + ohr27 +
            ", ohr28=" + ohr28 +
            ", ohr29=" + ohr29 +
            ", ohr30=" + ohr30 +
            ", ohr31=" + ohr31 +
            ", b1=" + b1 +
            ", b2=" + b2 +
            ", b3=" + b3 +
            ", b4=" + b4 +
            ", b5=" + b5 +
            ", b6=" + b6 +
            ", b7=" + b7 +
            ", b8=" + b8 +
            ", b9=" + b9 +
            ", b10=" + b10 +
            ", b11=" + b11 +
            ", b12=" + b12 +
            ", b13=" + b13 +
            ", b14=" + b14 +
            ", b15=" + b15 +
            ", b16=" + b16 +
            ", b17=" + b17 +
            ", b18=" + b18 +
            ", b19=" + b19 +
            ", b20=" + b20 +
            ", b21=" + b21 +
            ", b22=" + b22 +
            ", b23=" + b23 +
            ", b24=" + b24 +
            ", b25=" + b25 +
            ", b26=" + b26 +
            ", b27=" + b27 +
            ", b28=" + b28 +
            ", b29=" + b29 +
            ", b30=" + b30 +
            ", b31=" + b31 +
            ", d1=" + d1 +
            ", d2=" + d2 +
            ", d3=" + d3 +
            ", d4=" + d4 +
            ", d5=" + d5 +
            ", d6=" + d6 +
            ", d7=" + d7 +
            ", d8=" + d8 +
            ", d9=" + d9 +
            ", d10=" + d10 +
            ", d11=" + d11 +
            ", d12=" + d12 +
            ", d13=" + d13 +
            ", d14=" + d14 +
            ", d15=" + d15 +
            ", d16=" + d16 +
            ", d17=" + d17 +
            ", d18=" + d18 +
            ", d19=" + d19 +
            ", d20=" + d20 +
            ", d21=" + d21 +
            ", d22=" + d22 +
            ", d23=" + d23 +
            ", d24=" + d24 +
            ", d25=" + d25 +
            ", d26=" + d26 +
            ", d27=" + d27 +
            ", d28=" + d28 +
            ", d29=" + d29 +
            ", d30=" + d30 +
            ", d31=" + d31 +
            ", lshr=" + lshr +
            ", dhr=" + dhr +
            ", l1=" + l1 +
            ", l2=" + l2 +
            ", l3=" + l3 +
            ", l4=" + l4 +
            ", l5=" + l5 +
            ", l6=" + l6 +
            ", l7=" + l7 +
            ", l8=" + l8 +
            ", l9=" + l9 +
            ", l10=" + l10 +
            ", l11=" + l11 +
            ", l12=" + l12 +
            ", l13=" + l13 +
            ", l14=" + l14 +
            ", l15=" + l15 +
            ", l16=" + l16 +
            ", l17=" + l17 +
            ", l18=" + l18 +
            ", l19=" + l19 +
            ", l20=" + l20 +
            ", l21=" + l21 +
            ", l22=" + l22 +
            ", l23=" + l23 +
            ", l24=" + l24 +
            ", l25=" + l25 +
            ", l26=" + l26 +
            ", l27=" + l27 +
            ", l28=" + l28 +
            ", l29=" + l29 +
            ", l30=" + l30 +
            ", l31=" + l31 +
            ", otamt=" + otamt +
            ", lv1='" + lv1 + '\'' +
            ", lv2='" + lv2 + '\'' +
            ", lv3='" + lv3 + '\'' +
            ", lv4='" + lv4 + '\'' +
            ", lv5='" + lv5 + '\'' +
            ", lv6='" + lv6 + '\'' +
            ", lv7='" + lv7 + '\'' +
            ", lv8='" + lv8 + '\'' +
            ", lv9='" + lv9 + '\'' +
            ", lv10='" + lv10 + '\'' +
            ", lv11='" + lv11 + '\'' +
            ", lv12='" + lv12 + '\'' +
            ", lv13='" + lv13 + '\'' +
            ", lv14='" + lv14 + '\'' +
            ", lv15='" + lv15 + '\'' +
            ", lv16='" + lv16 + '\'' +
            ", lv17='" + lv17 + '\'' +
            ", lv18='" + lv18 + '\'' +
            ", lv19='" + lv19 + '\'' +
            ", lv20='" + lv20 + '\'' +
            ", lv21='" + lv21 + '\'' +
            ", lv22='" + lv22 + '\'' +
            ", lv23='" + lv23 + '\'' +
            ", lv24='" + lv24 + '\'' +
            ", lv25='" + lv25 + '\'' +
            ", lv26='" + lv26 + '\'' +
            ", lv27='" + lv27 + '\'' +
            ", lv28='" + lv28 + '\'' +
            ", lv29='" + lv29 + '\'' +
            ", lv30='" + lv30 + '\'' +
            ", lv31='" + lv31 + '\'' +
            ", motHr=" + motHr +
            ", wkHr=" + wkHr +
            ", tm1='" + tm1 + '\'' +
            ", tm2='" + tm2 + '\'' +
            ", tm3='" + tm3 + '\'' +
            ", tm4='" + tm4 + '\'' +
            ", tm5='" + tm5 + '\'' +
            ", tm6='" + tm6 + '\'' +
            ", tm7='" + tm7 + '\'' +
            ", tm8='" + tm8 + '\'' +
            ", tm9='" + tm9 + '\'' +
            ", tm10='" + tm10 + '\'' +
            ", tm11='" + tm11 + '\'' +
            ", tm12='" + tm12 + '\'' +
            ", tm13='" + tm13 + '\'' +
            ", tm14='" + tm14 + '\'' +
            ", tm15='" + tm15 + '\'' +
            ", tm16='" + tm16 + '\'' +
            ", tm17='" + tm17 + '\'' +
            ", tm18='" + tm18 + '\'' +
            ", tm19='" + tm19 + '\'' +
            ", tm20='" + tm20 + '\'' +
            ", tm21='" + tm21 + '\'' +
            ", tm22='" + tm22 + '\'' +
            ", tm23='" + tm23 + '\'' +
            ", tm24='" + tm24 + '\'' +
            ", tm25='" + tm25 + '\'' +
            ", tm26='" + tm26 + '\'' +
            ", tm27='" + tm27 + '\'' +
            ", tm28='" + tm28 + '\'' +
            ", tm29='" + tm29 + '\'' +
            ", tm30='" + tm30 + '\'' +
            ", tm31='" + tm31 + '\'' +
            ", sf1='" + sf1 + '\'' +
            ", sf2='" + sf2 + '\'' +
            ", sf3='" + sf3 + '\'' +
            ", sf4='" + sf4 + '\'' +
            ", sf5='" + sf5 + '\'' +
            ", sf6='" + sf6 + '\'' +
            ", sf7='" + sf7 + '\'' +
            ", sf8='" + sf8 + '\'' +
            ", sf9='" + sf9 + '\'' +
            ", sf10='" + sf10 + '\'' +
            ", sf11='" + sf11 + '\'' +
            ", sf12='" + sf12 + '\'' +
            ", sf13='" + sf13 + '\'' +
            ", sf14='" + sf14 + '\'' +
            ", sf15='" + sf15 + '\'' +
            ", sf16='" + sf16 + '\'' +
            ", sf17='" + sf17 + '\'' +
            ", sf18='" + sf18 + '\'' +
            ", sf19='" + sf19 + '\'' +
            ", sf20='" + sf20 + '\'' +
            ", sf21='" + sf21 + '\'' +
            ", sf22='" + sf22 + '\'' +
            ", sf23='" + sf23 + '\'' +
            ", sf24='" + sf24 + '\'' +
            ", sf25='" + sf25 + '\'' +
            ", sf26='" + sf26 + '\'' +
            ", sf27='" + sf27 + '\'' +
            ", sf28='" + sf28 + '\'' +
            ", sf29='" + sf29 + '\'' +
            ", sf30='" + sf30 + '\'' +
            ", sf31='" + sf31 + '\'' +
            ", dAtt=" + dAtt +
            ", aInt=" + aInt +
            ", sInt=" + sInt +
            ", food=" + food +
            ", wk1=" + wk1 +
            ", wk2=" + wk2 +
            ", wk3=" + wk3 +
            ", wk4=" + wk4 +
            ", wk5=" + wk5 +
            ", wk6=" + wk6 +
            ", wk7=" + wk7 +
            ", wk8=" + wk8 +
            ", wk9=" + wk9 +
            ", wk10=" + wk10 +
            ", wk11=" + wk11 +
            ", wk12=" + wk12 +
            ", wk13=" + wk13 +
            ", wk14=" + wk14 +
            ", wk15=" + wk15 +
            ", wk16=" + wk16 +
            ", wk17=" + wk17 +
            ", wk18=" + wk18 +
            ", wk19=" + wk19 +
            ", wk20=" + wk20 +
            ", wk21=" + wk21 +
            ", wk22=" + wk22 +
            ", wk23=" + wk23 +
            ", wk24=" + wk24 +
            ", wk25=" + wk25 +
            ", wk26=" + wk26 +
            ", wk27=" + wk27 +
            ", wk28=" + wk28 +
            ", wk29=" + wk29 +
            ", wk30=" + wk30 +
            ", wk31=" + wk31 +
            ", foodamt=" + foodamt +
            ", teaamt=" + teaamt +
            ", incentive=" + incentive +
            ", otAmount=" + otAmount +
            ", dedHr=" + dedHr +
            ", dedAmt=" + dedAmt +
            ", cWday=" + cWday +
            ", manFood=" + manFood +
            ", fstOt=" + fstOt +
            ", sndOt=" + sndOt +
            ", fst15=" + fst15 +
            ", snd15=" + snd15 +
            ", fst15Hr=" + fst15Hr +
            ", snd15Hr=" + snd15Hr +
            ", hr16=" + hr16 +
            ", amt16=" + amt16 +
            ", hrBal=" + hrBal +
            ", amtBal=" + amtBal +
            ", hrSun=" + hrSun +
            ", amtSun=" + amtSun +
            ", foodNo=" + foodNo +
            ", foodamt1=" + foodamt1 +
            ", foodNo1=" + foodNo1 +
            ", foodSun=" + foodSun +
            ", mNo1='" + mNo1 + '\'' +
            ", mNo2='" + mNo2 + '\'' +
            ", mNo3='" + mNo3 + '\'' +
            ", mNo4='" + mNo4 + '\'' +
            ", mNo5='" + mNo5 + '\'' +
            ", mNo6='" + mNo6 + '\'' +
            ", mNo7='" + mNo7 + '\'' +
            ", mNo8='" + mNo8 + '\'' +
            ", mNo9='" + mNo9 + '\'' +
            ", mNo10='" + mNo10 + '\'' +
            ", mNo11='" + mNo11 + '\'' +
            ", mNo12='" + mNo12 + '\'' +
            ", mNo13='" + mNo13 + '\'' +
            ", mNo14='" + mNo14 + '\'' +
            ", mNo15='" + mNo15 + '\'' +
            ", mNo16='" + mNo16 + '\'' +
            ", mNo17='" + mNo17 + '\'' +
            ", mNo18='" + mNo18 + '\'' +
            ", mNo19='" + mNo19 + '\'' +
            ", mNo20='" + mNo20 + '\'' +
            ", mNo21='" + mNo21 + '\'' +
            ", mNo22='" + mNo22 + '\'' +
            ", mNo23='" + mNo23 + '\'' +
            ", mNo24='" + mNo24 + '\'' +
            ", mNo25='" + mNo25 + '\'' +
            ", mNo26='" + mNo26 + '\'' +
            ", mNo27='" + mNo27 + '\'' +
            ", mNo28='" + mNo28 + '\'' +
            ", mNo29='" + mNo29 + '\'' +
            ", mNo30='" + mNo30 + '\'' +
            ", mNo31='" + mNo31 + '\'' +
            ", hr2=" + hr2 +
            ", amt2=" + amt2 +
            ", hr2Bal=" + hr2Bal +
            ", amt2Bal=" + amt2Bal +
            ", gwr1Hrs=" + gwr1Hrs +
            ", gwr1Amt=" + gwr1Amt +
            ", gwr2Hrs=" + gwr2Hrs +
            ", gwr2Amt=" + gwr2Amt +
            '}';
    }
}

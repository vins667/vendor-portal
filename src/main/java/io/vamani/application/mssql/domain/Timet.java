package io.vamani.application.mssql.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "timet_view")
public class Timet {
    private TimetId timetId;
    private String it1;
    private String it2;
    private String it3;
    private String it4;
    private String it5;
    private String it6;
    private String it7;
    private String it8;
    private String it9;
    private String it10;
    private String it11;
    private String it12;
    private String it13;
    private String it14;
    private String it15;
    private String it16;
    private String it17;
    private String it18;
    private String it19;
    private String it20;
    private String it21;
    private String it22;
    private String it23;
    private String it24;
    private String it25;
    private String it26;
    private String it27;
    private String it28;
    private String it29;
    private String it30;
    private String it31;
    private String ot1;
    private String ot2;
    private String ot3;
    private String ot4;
    private String ot5;
    private String ot6;
    private String ot7;
    private String ot8;
    private String ot9;
    private String ot10;
    private String ot11;
    private String ot12;
    private String ot13;
    private String ot14;
    private String ot15;
    private String ot16;
    private String ot17;
    private String ot18;
    private String ot19;
    private String ot20;
    private String ot21;
    private String ot22;
    private String ot23;
    private String ot24;
    private String ot25;
    private String ot26;
    private String ot27;
    private String ot28;
    private String ot29;
    private String ot30;
    private String ot31;
    private String mit1;
    private String mit2;
    private String mit3;
    private String mit4;
    private String mit5;
    private String mit6;
    private String mit7;
    private String mit8;
    private String mit9;
    private String mit10;
    private String mit11;
    private String mit12;
    private String mit13;
    private String mit14;
    private String mit15;
    private String mit16;
    private String mit17;
    private String mit18;
    private String mit19;
    private String mit20;
    private String mit21;
    private String mit22;
    private String mit23;
    private String mit24;
    private String mit25;
    private String mit26;
    private String mit27;
    private String mit28;
    private String mit29;
    private String mit30;
    private String mit31;
    private String mot1;
    private String mot2;
    private String mot3;
    private String mot4;
    private String mot5;
    private String mot6;
    private String mot7;
    private String mot8;
    private String mot9;
    private String mot10;
    private String mot11;
    private String mot12;
    private String mot13;
    private String mot14;
    private String mot15;
    private String mot16;
    private String mot17;
    private String mot18;
    private String mot19;
    private String mot20;
    private String mot21;
    private String mot22;
    private String mot23;
    private String mot24;
    private String mot25;
    private String mot26;
    private String mot27;
    private String mot28;
    private String mot29;
    private String mot30;
    private String mot31;
    private String pit1;
    private String pit2;
    private String pit3;
    private String pit4;
    private String pit5;
    private String pit6;
    private String pit7;
    private String pit8;
    private String pit9;
    private String pit10;
    private String pit11;
    private String pit12;
    private String pit13;
    private String pit14;
    private String pit15;
    private String pit16;
    private String pit17;
    private String pit18;
    private String pit19;
    private String pit20;
    private String pit21;
    private String pit22;
    private String pit23;
    private String pit24;
    private String pit25;
    private String pit26;
    private String pit27;
    private String pit28;
    private String pit29;
    private String pit30;
    private String pit31;
    private String pot1;
    private String pot2;
    private String pot3;
    private String pot4;
    private String pot5;
    private String pot6;
    private String pot7;
    private String pot8;
    private String pot9;
    private String pot10;
    private String pot11;
    private String pot12;
    private String pot13;
    private String pot14;
    private String pot15;
    private String pot16;
    private String pot17;
    private String pot18;
    private String pot19;
    private String pot20;
    private String pot21;
    private String pot22;
    private String pot23;
    private String pot24;
    private String pot25;
    private String pot26;
    private String pot27;
    private String pot28;
    private String pot29;
    private String pot30;
    private String pot31;
    private String inDt1;
    private String outDt1;
    private String inDt2;
    private String outDt2;
    private String inDt3;
    private String outDt3;
    private String inDt4;
    private String outDt4;
    private String inDt5;
    private String outDt5;
    private String inDt6;
    private String outDt6;
    private String inDt7;
    private String outDt7;
    private String inDt8;
    private String outDt8;
    private String inDt9;
    private String outDt9;
    private String inDt10;
    private String outDt10;
    private String inDt11;
    private String outDt11;
    private String inDt12;
    private String outDt12;
    private String inDt13;
    private String outDt13;
    private String inDt14;
    private String outDt14;
    private String inDt15;
    private String outDt15;
    private String inDt16;
    private String outDt16;
    private String inDt17;
    private String outDt17;
    private String inDt18;
    private String outDt18;
    private String inDt19;
    private String outDt19;
    private String inDt20;
    private String outDt20;
    private String inDt21;
    private String outDt21;
    private String inDt22;
    private String outDt22;
    private String inDt23;
    private String outDt23;
    private String inDt24;
    private String outDt24;
    private String inDt25;
    private String outDt25;
    private String inDt26;
    private String outDt26;
    private String inDt27;
    private String outDt27;
    private String inDt28;
    private String outDt28;
    private String inDt29;
    private String outDt29;
    private String inDt30;
    private String outDt30;
    private String inDt31;
    private String outDt31;
    private String mTxt1;
    private String mTxt2;
    private String mTxt3;
    private String mTxt4;
    private String mTxt5;
    private String mTxt6;
    private String mTxt7;
    private String mTxt8;
    private String mTxt9;
    private String mTxt10;
    private String mTxt11;
    private String mTxt12;
    private String mTxt13;
    private String mTxt14;
    private String mTxt15;
    private String mTxt16;
    private String mTxt17;
    private String mTxt18;
    private String mTxt19;
    private String mTxt20;
    private String mTxt21;
    private String mTxt22;
    private String mTxt23;
    private String mTxt24;
    private String mTxt25;
    private String mTxt26;
    private String mTxt27;
    private String mTxt28;
    private String mTxt29;
    private String mTxt30;
    private String mTxt31;

    @EmbeddedId
    public TimetId getTimetId() {
        return timetId;
    }

    public void setTimetId(TimetId timetId) {
        this.timetId = timetId;
    }

    @Column(name = "it1")
    public String getIt1() {
        return it1;
    }

    public void setIt1(String it1) {
        this.it1 = it1;
    }


    @Column(name = "it2")
    public String getIt2() {
        return it2;
    }

    public void setIt2(String it2) {
        this.it2 = it2;
    }


    @Column(name = "it3")
    public String getIt3() {
        return it3;
    }

    public void setIt3(String it3) {
        this.it3 = it3;
    }


    @Column(name = "it4")
    public String getIt4() {
        return it4;
    }

    public void setIt4(String it4) {
        this.it4 = it4;
    }


    @Column(name = "it5")
    public String getIt5() {
        return it5;
    }

    public void setIt5(String it5) {
        this.it5 = it5;
    }


    @Column(name = "it6")
    public String getIt6() {
        return it6;
    }

    public void setIt6(String it6) {
        this.it6 = it6;
    }


    @Column(name = "it7")
    public String getIt7() {
        return it7;
    }

    public void setIt7(String it7) {
        this.it7 = it7;
    }


    @Column(name = "it8")
    public String getIt8() {
        return it8;
    }

    public void setIt8(String it8) {
        this.it8 = it8;
    }


    @Column(name = "it9")
    public String getIt9() {
        return it9;
    }

    public void setIt9(String it9) {
        this.it9 = it9;
    }


    @Column(name = "it10")
    public String getIt10() {
        return it10;
    }

    public void setIt10(String it10) {
        this.it10 = it10;
    }


    @Column(name = "it11")
    public String getIt11() {
        return it11;
    }

    public void setIt11(String it11) {
        this.it11 = it11;
    }


    @Column(name = "it12")
    public String getIt12() {
        return it12;
    }

    public void setIt12(String it12) {
        this.it12 = it12;
    }


    @Column(name = "it13")
    public String getIt13() {
        return it13;
    }

    public void setIt13(String it13) {
        this.it13 = it13;
    }


    @Column(name = "it14")
    public String getIt14() {
        return it14;
    }

    public void setIt14(String it14) {
        this.it14 = it14;
    }


    @Column(name = "it15")
    public String getIt15() {
        return it15;
    }

    public void setIt15(String it15) {
        this.it15 = it15;
    }


    @Column(name = "it16")
    public String getIt16() {
        return it16;
    }

    public void setIt16(String it16) {
        this.it16 = it16;
    }


    @Column(name = "it17")
    public String getIt17() {
        return it17;
    }

    public void setIt17(String it17) {
        this.it17 = it17;
    }


    @Column(name = "it18")
    public String getIt18() {
        return it18;
    }

    public void setIt18(String it18) {
        this.it18 = it18;
    }


    @Column(name = "it19")
    public String getIt19() {
        return it19;
    }

    public void setIt19(String it19) {
        this.it19 = it19;
    }


    @Column(name = "it20")
    public String getIt20() {
        return it20;
    }

    public void setIt20(String it20) {
        this.it20 = it20;
    }


    @Column(name = "it21")
    public String getIt21() {
        return it21;
    }

    public void setIt21(String it21) {
        this.it21 = it21;
    }


    @Column(name = "it22")
    public String getIt22() {
        return it22;
    }

    public void setIt22(String it22) {
        this.it22 = it22;
    }


    @Column(name = "it23")
    public String getIt23() {
        return it23;
    }

    public void setIt23(String it23) {
        this.it23 = it23;
    }


    @Column(name = "it24")
    public String getIt24() {
        return it24;
    }

    public void setIt24(String it24) {
        this.it24 = it24;
    }


    @Column(name = "it25")
    public String getIt25() {
        return it25;
    }

    public void setIt25(String it25) {
        this.it25 = it25;
    }


    @Column(name = "it26")
    public String getIt26() {
        return it26;
    }

    public void setIt26(String it26) {
        this.it26 = it26;
    }


    @Column(name = "it27")
    public String getIt27() {
        return it27;
    }

    public void setIt27(String it27) {
        this.it27 = it27;
    }


    @Column(name = "it28")
    public String getIt28() {
        return it28;
    }

    public void setIt28(String it28) {
        this.it28 = it28;
    }


    @Column(name = "it29")
    public String getIt29() {
        return it29;
    }

    public void setIt29(String it29) {
        this.it29 = it29;
    }


    @Column(name = "it30")
    public String getIt30() {
        return it30;
    }

    public void setIt30(String it30) {
        this.it30 = it30;
    }


    @Column(name = "it31")
    public String getIt31() {
        return it31;
    }

    public void setIt31(String it31) {
        this.it31 = it31;
    }


    @Column(name = "ot1")
    public String getOt1() {
        return ot1;
    }

    public void setOt1(String ot1) {
        this.ot1 = ot1;
    }


    @Column(name = "ot2")
    public String getOt2() {
        return ot2;
    }

    public void setOt2(String ot2) {
        this.ot2 = ot2;
    }


    @Column(name = "ot3")
    public String getOt3() {
        return ot3;
    }

    public void setOt3(String ot3) {
        this.ot3 = ot3;
    }


    @Column(name = "ot4")
    public String getOt4() {
        return ot4;
    }

    public void setOt4(String ot4) {
        this.ot4 = ot4;
    }


    @Column(name = "ot5")
    public String getOt5() {
        return ot5;
    }

    public void setOt5(String ot5) {
        this.ot5 = ot5;
    }


    @Column(name = "ot6")
    public String getOt6() {
        return ot6;
    }

    public void setOt6(String ot6) {
        this.ot6 = ot6;
    }


    @Column(name = "ot7")
    public String getOt7() {
        return ot7;
    }

    public void setOt7(String ot7) {
        this.ot7 = ot7;
    }


    @Column(name = "ot8")
    public String getOt8() {
        return ot8;
    }

    public void setOt8(String ot8) {
        this.ot8 = ot8;
    }


    @Column(name = "ot9")
    public String getOt9() {
        return ot9;
    }

    public void setOt9(String ot9) {
        this.ot9 = ot9;
    }


    @Column(name = "ot10")
    public String getOt10() {
        return ot10;
    }

    public void setOt10(String ot10) {
        this.ot10 = ot10;
    }


    @Column(name = "ot11")
    public String getOt11() {
        return ot11;
    }

    public void setOt11(String ot11) {
        this.ot11 = ot11;
    }


    @Column(name = "ot12")
    public String getOt12() {
        return ot12;
    }

    public void setOt12(String ot12) {
        this.ot12 = ot12;
    }


    @Column(name = "ot13")
    public String getOt13() {
        return ot13;
    }

    public void setOt13(String ot13) {
        this.ot13 = ot13;
    }


    @Column(name = "ot14")
    public String getOt14() {
        return ot14;
    }

    public void setOt14(String ot14) {
        this.ot14 = ot14;
    }


    @Column(name = "ot15")
    public String getOt15() {
        return ot15;
    }

    public void setOt15(String ot15) {
        this.ot15 = ot15;
    }


    @Column(name = "ot16")
    public String getOt16() {
        return ot16;
    }

    public void setOt16(String ot16) {
        this.ot16 = ot16;
    }


    @Column(name = "ot17")
    public String getOt17() {
        return ot17;
    }

    public void setOt17(String ot17) {
        this.ot17 = ot17;
    }


    @Column(name = "ot18")
    public String getOt18() {
        return ot18;
    }

    public void setOt18(String ot18) {
        this.ot18 = ot18;
    }


    @Column(name = "ot19")
    public String getOt19() {
        return ot19;
    }

    public void setOt19(String ot19) {
        this.ot19 = ot19;
    }


    @Column(name = "ot20")
    public String getOt20() {
        return ot20;
    }

    public void setOt20(String ot20) {
        this.ot20 = ot20;
    }


    @Column(name = "ot21")
    public String getOt21() {
        return ot21;
    }

    public void setOt21(String ot21) {
        this.ot21 = ot21;
    }


    @Column(name = "ot22")
    public String getOt22() {
        return ot22;
    }

    public void setOt22(String ot22) {
        this.ot22 = ot22;
    }


    @Column(name = "ot23")
    public String getOt23() {
        return ot23;
    }

    public void setOt23(String ot23) {
        this.ot23 = ot23;
    }


    @Column(name = "ot24")
    public String getOt24() {
        return ot24;
    }

    public void setOt24(String ot24) {
        this.ot24 = ot24;
    }


    @Column(name = "ot25")
    public String getOt25() {
        return ot25;
    }

    public void setOt25(String ot25) {
        this.ot25 = ot25;
    }


    @Column(name = "ot26")
    public String getOt26() {
        return ot26;
    }

    public void setOt26(String ot26) {
        this.ot26 = ot26;
    }


    @Column(name = "ot27")
    public String getOt27() {
        return ot27;
    }

    public void setOt27(String ot27) {
        this.ot27 = ot27;
    }


    @Column(name = "ot28")
    public String getOt28() {
        return ot28;
    }

    public void setOt28(String ot28) {
        this.ot28 = ot28;
    }


    @Column(name = "ot29")
    public String getOt29() {
        return ot29;
    }

    public void setOt29(String ot29) {
        this.ot29 = ot29;
    }


    @Column(name = "ot30")
    public String getOt30() {
        return ot30;
    }

    public void setOt30(String ot30) {
        this.ot30 = ot30;
    }


    @Column(name = "ot31")
    public String getOt31() {
        return ot31;
    }

    public void setOt31(String ot31) {
        this.ot31 = ot31;
    }


    @Column(name = "mit1")
    public String getMit1() {
        return mit1;
    }

    public void setMit1(String mit1) {
        this.mit1 = mit1;
    }


    @Column(name = "mit2")
    public String getMit2() {
        return mit2;
    }

    public void setMit2(String mit2) {
        this.mit2 = mit2;
    }


    @Column(name = "mit3")
    public String getMit3() {
        return mit3;
    }

    public void setMit3(String mit3) {
        this.mit3 = mit3;
    }


    @Column(name = "mit4")
    public String getMit4() {
        return mit4;
    }

    public void setMit4(String mit4) {
        this.mit4 = mit4;
    }


    @Column(name = "mit5")
    public String getMit5() {
        return mit5;
    }

    public void setMit5(String mit5) {
        this.mit5 = mit5;
    }


    @Column(name = "mit6")
    public String getMit6() {
        return mit6;
    }

    public void setMit6(String mit6) {
        this.mit6 = mit6;
    }


    @Column(name = "mit7")
    public String getMit7() {
        return mit7;
    }

    public void setMit7(String mit7) {
        this.mit7 = mit7;
    }


    @Column(name = "mit8")
    public String getMit8() {
        return mit8;
    }

    public void setMit8(String mit8) {
        this.mit8 = mit8;
    }


    @Column(name = "mit9")
    public String getMit9() {
        return mit9;
    }

    public void setMit9(String mit9) {
        this.mit9 = mit9;
    }


    @Column(name = "mit10")
    public String getMit10() {
        return mit10;
    }

    public void setMit10(String mit10) {
        this.mit10 = mit10;
    }


    @Column(name = "mit11")
    public String getMit11() {
        return mit11;
    }

    public void setMit11(String mit11) {
        this.mit11 = mit11;
    }


    @Column(name = "mit12")
    public String getMit12() {
        return mit12;
    }

    public void setMit12(String mit12) {
        this.mit12 = mit12;
    }


    @Column(name = "mit13")
    public String getMit13() {
        return mit13;
    }

    public void setMit13(String mit13) {
        this.mit13 = mit13;
    }


    @Column(name = "mit14")
    public String getMit14() {
        return mit14;
    }

    public void setMit14(String mit14) {
        this.mit14 = mit14;
    }


    @Column(name = "mit15")
    public String getMit15() {
        return mit15;
    }

    public void setMit15(String mit15) {
        this.mit15 = mit15;
    }


    @Column(name = "mit16")
    public String getMit16() {
        return mit16;
    }

    public void setMit16(String mit16) {
        this.mit16 = mit16;
    }


    @Column(name = "mit17")
    public String getMit17() {
        return mit17;
    }

    public void setMit17(String mit17) {
        this.mit17 = mit17;
    }


    @Column(name = "mit18")
    public String getMit18() {
        return mit18;
    }

    public void setMit18(String mit18) {
        this.mit18 = mit18;
    }


    @Column(name = "mit19")
    public String getMit19() {
        return mit19;
    }

    public void setMit19(String mit19) {
        this.mit19 = mit19;
    }


    @Column(name = "mit20")
    public String getMit20() {
        return mit20;
    }

    public void setMit20(String mit20) {
        this.mit20 = mit20;
    }


    @Column(name = "mit21")
    public String getMit21() {
        return mit21;
    }

    public void setMit21(String mit21) {
        this.mit21 = mit21;
    }


    @Column(name = "mit22")
    public String getMit22() {
        return mit22;
    }

    public void setMit22(String mit22) {
        this.mit22 = mit22;
    }


    @Column(name = "mit23")
    public String getMit23() {
        return mit23;
    }

    public void setMit23(String mit23) {
        this.mit23 = mit23;
    }


    @Column(name = "mit24")
    public String getMit24() {
        return mit24;
    }

    public void setMit24(String mit24) {
        this.mit24 = mit24;
    }


    @Column(name = "mit25")
    public String getMit25() {
        return mit25;
    }

    public void setMit25(String mit25) {
        this.mit25 = mit25;
    }


    @Column(name = "mit26")
    public String getMit26() {
        return mit26;
    }

    public void setMit26(String mit26) {
        this.mit26 = mit26;
    }


    @Column(name = "mit27")
    public String getMit27() {
        return mit27;
    }

    public void setMit27(String mit27) {
        this.mit27 = mit27;
    }


    @Column(name = "mit28")
    public String getMit28() {
        return mit28;
    }

    public void setMit28(String mit28) {
        this.mit28 = mit28;
    }


    @Column(name = "mit29")
    public String getMit29() {
        return mit29;
    }

    public void setMit29(String mit29) {
        this.mit29 = mit29;
    }


    @Column(name = "mit30")
    public String getMit30() {
        return mit30;
    }

    public void setMit30(String mit30) {
        this.mit30 = mit30;
    }


    @Column(name = "mit31")
    public String getMit31() {
        return mit31;
    }

    public void setMit31(String mit31) {
        this.mit31 = mit31;
    }


    @Column(name = "mot1")
    public String getMot1() {
        return mot1;
    }

    public void setMot1(String mot1) {
        this.mot1 = mot1;
    }


    @Column(name = "mot2")
    public String getMot2() {
        return mot2;
    }

    public void setMot2(String mot2) {
        this.mot2 = mot2;
    }


    @Column(name = "mot3")
    public String getMot3() {
        return mot3;
    }

    public void setMot3(String mot3) {
        this.mot3 = mot3;
    }


    @Column(name = "mot4")
    public String getMot4() {
        return mot4;
    }

    public void setMot4(String mot4) {
        this.mot4 = mot4;
    }


    @Column(name = "mot5")
    public String getMot5() {
        return mot5;
    }

    public void setMot5(String mot5) {
        this.mot5 = mot5;
    }


    @Column(name = "mot6")
    public String getMot6() {
        return mot6;
    }

    public void setMot6(String mot6) {
        this.mot6 = mot6;
    }


    @Column(name = "mot7")
    public String getMot7() {
        return mot7;
    }

    public void setMot7(String mot7) {
        this.mot7 = mot7;
    }


    @Column(name = "mot8")
    public String getMot8() {
        return mot8;
    }

    public void setMot8(String mot8) {
        this.mot8 = mot8;
    }


    @Column(name = "mot9")
    public String getMot9() {
        return mot9;
    }

    public void setMot9(String mot9) {
        this.mot9 = mot9;
    }


    @Column(name = "mot10")
    public String getMot10() {
        return mot10;
    }

    public void setMot10(String mot10) {
        this.mot10 = mot10;
    }


    @Column(name = "mot11")
    public String getMot11() {
        return mot11;
    }

    public void setMot11(String mot11) {
        this.mot11 = mot11;
    }


    @Column(name = "mot12")
    public String getMot12() {
        return mot12;
    }

    public void setMot12(String mot12) {
        this.mot12 = mot12;
    }


    @Column(name = "mot13")
    public String getMot13() {
        return mot13;
    }

    public void setMot13(String mot13) {
        this.mot13 = mot13;
    }


    @Column(name = "mot14")
    public String getMot14() {
        return mot14;
    }

    public void setMot14(String mot14) {
        this.mot14 = mot14;
    }


    @Column(name = "mot15")
    public String getMot15() {
        return mot15;
    }

    public void setMot15(String mot15) {
        this.mot15 = mot15;
    }


    @Column(name = "mot16")
    public String getMot16() {
        return mot16;
    }

    public void setMot16(String mot16) {
        this.mot16 = mot16;
    }


    @Column(name = "mot17")
    public String getMot17() {
        return mot17;
    }

    public void setMot17(String mot17) {
        this.mot17 = mot17;
    }


    @Column(name = "mot18")
    public String getMot18() {
        return mot18;
    }

    public void setMot18(String mot18) {
        this.mot18 = mot18;
    }


    @Column(name = "mot19")
    public String getMot19() {
        return mot19;
    }

    public void setMot19(String mot19) {
        this.mot19 = mot19;
    }


    @Column(name = "mot20")
    public String getMot20() {
        return mot20;
    }

    public void setMot20(String mot20) {
        this.mot20 = mot20;
    }


    @Column(name = "mot21")
    public String getMot21() {
        return mot21;
    }

    public void setMot21(String mot21) {
        this.mot21 = mot21;
    }


    @Column(name = "mot22")
    public String getMot22() {
        return mot22;
    }

    public void setMot22(String mot22) {
        this.mot22 = mot22;
    }


    @Column(name = "mot23")
    public String getMot23() {
        return mot23;
    }

    public void setMot23(String mot23) {
        this.mot23 = mot23;
    }


    @Column(name = "mot24")
    public String getMot24() {
        return mot24;
    }

    public void setMot24(String mot24) {
        this.mot24 = mot24;
    }


    @Column(name = "mot25")
    public String getMot25() {
        return mot25;
    }

    public void setMot25(String mot25) {
        this.mot25 = mot25;
    }


    @Column(name = "mot26")
    public String getMot26() {
        return mot26;
    }

    public void setMot26(String mot26) {
        this.mot26 = mot26;
    }


    @Column(name = "mot27")
    public String getMot27() {
        return mot27;
    }

    public void setMot27(String mot27) {
        this.mot27 = mot27;
    }


    @Column(name = "mot28")
    public String getMot28() {
        return mot28;
    }

    public void setMot28(String mot28) {
        this.mot28 = mot28;
    }


    @Column(name = "mot29")
    public String getMot29() {
        return mot29;
    }

    public void setMot29(String mot29) {
        this.mot29 = mot29;
    }


    @Column(name = "mot30")
    public String getMot30() {
        return mot30;
    }

    public void setMot30(String mot30) {
        this.mot30 = mot30;
    }


    @Column(name = "mot31")
    public String getMot31() {
        return mot31;
    }

    public void setMot31(String mot31) {
        this.mot31 = mot31;
    }


    @Column(name = "pit1")
    public String getPit1() {
        return pit1;
    }

    public void setPit1(String pit1) {
        this.pit1 = pit1;
    }


    @Column(name = "pit2")
    public String getPit2() {
        return pit2;
    }

    public void setPit2(String pit2) {
        this.pit2 = pit2;
    }


    @Column(name = "pit3")
    public String getPit3() {
        return pit3;
    }

    public void setPit3(String pit3) {
        this.pit3 = pit3;
    }


    @Column(name = "pit4")
    public String getPit4() {
        return pit4;
    }

    public void setPit4(String pit4) {
        this.pit4 = pit4;
    }


    @Column(name = "pit5")
    public String getPit5() {
        return pit5;
    }

    public void setPit5(String pit5) {
        this.pit5 = pit5;
    }


    @Column(name = "pit6")
    public String getPit6() {
        return pit6;
    }

    public void setPit6(String pit6) {
        this.pit6 = pit6;
    }


    @Column(name = "pit7")
    public String getPit7() {
        return pit7;
    }

    public void setPit7(String pit7) {
        this.pit7 = pit7;
    }


    @Column(name = "pit8")
    public String getPit8() {
        return pit8;
    }

    public void setPit8(String pit8) {
        this.pit8 = pit8;
    }


    @Column(name = "pit9")
    public String getPit9() {
        return pit9;
    }

    public void setPit9(String pit9) {
        this.pit9 = pit9;
    }


    @Column(name = "pit10")
    public String getPit10() {
        return pit10;
    }

    public void setPit10(String pit10) {
        this.pit10 = pit10;
    }


    @Column(name = "pit11")
    public String getPit11() {
        return pit11;
    }

    public void setPit11(String pit11) {
        this.pit11 = pit11;
    }


    @Column(name = "pit12")
    public String getPit12() {
        return pit12;
    }

    public void setPit12(String pit12) {
        this.pit12 = pit12;
    }


    @Column(name = "pit13")
    public String getPit13() {
        return pit13;
    }

    public void setPit13(String pit13) {
        this.pit13 = pit13;
    }


    @Column(name = "pit14")
    public String getPit14() {
        return pit14;
    }

    public void setPit14(String pit14) {
        this.pit14 = pit14;
    }


    @Column(name = "pit15")
    public String getPit15() {
        return pit15;
    }

    public void setPit15(String pit15) {
        this.pit15 = pit15;
    }


    @Column(name = "pit16")
    public String getPit16() {
        return pit16;
    }

    public void setPit16(String pit16) {
        this.pit16 = pit16;
    }


    @Column(name = "pit17")
    public String getPit17() {
        return pit17;
    }

    public void setPit17(String pit17) {
        this.pit17 = pit17;
    }


    @Column(name = "pit18")
    public String getPit18() {
        return pit18;
    }

    public void setPit18(String pit18) {
        this.pit18 = pit18;
    }


    @Column(name = "pit19")
    public String getPit19() {
        return pit19;
    }

    public void setPit19(String pit19) {
        this.pit19 = pit19;
    }


    @Column(name = "pit20")
    public String getPit20() {
        return pit20;
    }

    public void setPit20(String pit20) {
        this.pit20 = pit20;
    }


    @Column(name = "pit21")
    public String getPit21() {
        return pit21;
    }

    public void setPit21(String pit21) {
        this.pit21 = pit21;
    }


    @Column(name = "pit22")
    public String getPit22() {
        return pit22;
    }

    public void setPit22(String pit22) {
        this.pit22 = pit22;
    }


    @Column(name = "pit23")
    public String getPit23() {
        return pit23;
    }

    public void setPit23(String pit23) {
        this.pit23 = pit23;
    }


    @Column(name = "pit24")
    public String getPit24() {
        return pit24;
    }

    public void setPit24(String pit24) {
        this.pit24 = pit24;
    }


    @Column(name = "pit25")
    public String getPit25() {
        return pit25;
    }

    public void setPit25(String pit25) {
        this.pit25 = pit25;
    }


    @Column(name = "pit26")
    public String getPit26() {
        return pit26;
    }

    public void setPit26(String pit26) {
        this.pit26 = pit26;
    }


    @Column(name = "pit27")
    public String getPit27() {
        return pit27;
    }

    public void setPit27(String pit27) {
        this.pit27 = pit27;
    }


    @Column(name = "pit28")
    public String getPit28() {
        return pit28;
    }

    public void setPit28(String pit28) {
        this.pit28 = pit28;
    }


    @Column(name = "pit29")
    public String getPit29() {
        return pit29;
    }

    public void setPit29(String pit29) {
        this.pit29 = pit29;
    }


    @Column(name = "pit30")
    public String getPit30() {
        return pit30;
    }

    public void setPit30(String pit30) {
        this.pit30 = pit30;
    }


    @Column(name = "pit31")
    public String getPit31() {
        return pit31;
    }

    public void setPit31(String pit31) {
        this.pit31 = pit31;
    }


    @Column(name = "pot1")
    public String getPot1() {
        return pot1;
    }

    public void setPot1(String pot1) {
        this.pot1 = pot1;
    }


    @Column(name = "pot2")
    public String getPot2() {
        return pot2;
    }

    public void setPot2(String pot2) {
        this.pot2 = pot2;
    }


    @Column(name = "pot3")
    public String getPot3() {
        return pot3;
    }

    public void setPot3(String pot3) {
        this.pot3 = pot3;
    }


    @Column(name = "pot4")
    public String getPot4() {
        return pot4;
    }

    public void setPot4(String pot4) {
        this.pot4 = pot4;
    }


    @Column(name = "pot5")
    public String getPot5() {
        return pot5;
    }

    public void setPot5(String pot5) {
        this.pot5 = pot5;
    }


    @Column(name = "pot6")
    public String getPot6() {
        return pot6;
    }

    public void setPot6(String pot6) {
        this.pot6 = pot6;
    }


    @Column(name = "pot7")
    public String getPot7() {
        return pot7;
    }

    public void setPot7(String pot7) {
        this.pot7 = pot7;
    }


    @Column(name = "pot8")
    public String getPot8() {
        return pot8;
    }

    public void setPot8(String pot8) {
        this.pot8 = pot8;
    }


    @Column(name = "pot9")
    public String getPot9() {
        return pot9;
    }

    public void setPot9(String pot9) {
        this.pot9 = pot9;
    }


    @Column(name = "pot10")
    public String getPot10() {
        return pot10;
    }

    public void setPot10(String pot10) {
        this.pot10 = pot10;
    }


    @Column(name = "pot11")
    public String getPot11() {
        return pot11;
    }

    public void setPot11(String pot11) {
        this.pot11 = pot11;
    }


    @Column(name = "pot12")
    public String getPot12() {
        return pot12;
    }

    public void setPot12(String pot12) {
        this.pot12 = pot12;
    }


    @Column(name = "pot13")
    public String getPot13() {
        return pot13;
    }

    public void setPot13(String pot13) {
        this.pot13 = pot13;
    }


    @Column(name = "pot14")
    public String getPot14() {
        return pot14;
    }

    public void setPot14(String pot14) {
        this.pot14 = pot14;
    }


    @Column(name = "pot15")
    public String getPot15() {
        return pot15;
    }

    public void setPot15(String pot15) {
        this.pot15 = pot15;
    }


    @Column(name = "pot16")
    public String getPot16() {
        return pot16;
    }

    public void setPot16(String pot16) {
        this.pot16 = pot16;
    }


    @Column(name = "pot17")
    public String getPot17() {
        return pot17;
    }

    public void setPot17(String pot17) {
        this.pot17 = pot17;
    }


    @Column(name = "pot18")
    public String getPot18() {
        return pot18;
    }

    public void setPot18(String pot18) {
        this.pot18 = pot18;
    }


    @Column(name = "pot19")
    public String getPot19() {
        return pot19;
    }

    public void setPot19(String pot19) {
        this.pot19 = pot19;
    }


    @Column(name = "pot20")
    public String getPot20() {
        return pot20;
    }

    public void setPot20(String pot20) {
        this.pot20 = pot20;
    }


    @Column(name = "pot21")
    public String getPot21() {
        return pot21;
    }

    public void setPot21(String pot21) {
        this.pot21 = pot21;
    }


    @Column(name = "pot22")
    public String getPot22() {
        return pot22;
    }

    public void setPot22(String pot22) {
        this.pot22 = pot22;
    }


    @Column(name = "pot23")
    public String getPot23() {
        return pot23;
    }

    public void setPot23(String pot23) {
        this.pot23 = pot23;
    }


    @Column(name = "pot24")
    public String getPot24() {
        return pot24;
    }

    public void setPot24(String pot24) {
        this.pot24 = pot24;
    }


    @Column(name = "pot25")
    public String getPot25() {
        return pot25;
    }

    public void setPot25(String pot25) {
        this.pot25 = pot25;
    }


    @Column(name = "pot26")
    public String getPot26() {
        return pot26;
    }

    public void setPot26(String pot26) {
        this.pot26 = pot26;
    }


    @Column(name = "pot27")
    public String getPot27() {
        return pot27;
    }

    public void setPot27(String pot27) {
        this.pot27 = pot27;
    }


    @Column(name = "pot28")
    public String getPot28() {
        return pot28;
    }

    public void setPot28(String pot28) {
        this.pot28 = pot28;
    }


    @Column(name = "pot29")
    public String getPot29() {
        return pot29;
    }

    public void setPot29(String pot29) {
        this.pot29 = pot29;
    }


    @Column(name = "pot30")
    public String getPot30() {
        return pot30;
    }

    public void setPot30(String pot30) {
        this.pot30 = pot30;
    }


    @Column(name = "pot31")
    public String getPot31() {
        return pot31;
    }

    public void setPot31(String pot31) {
        this.pot31 = pot31;
    }


    @Column(name = "in_dt1")
    public String getInDt1() {
        return inDt1;
    }

    public void setInDt1(String inDt1) {
        this.inDt1 = inDt1;
    }


    @Column(name = "out_dt1")
    public String getOutDt1() {
        return outDt1;
    }

    public void setOutDt1(String outDt1) {
        this.outDt1 = outDt1;
    }


    @Column(name = "in_dt2")
    public String getInDt2() {
        return inDt2;
    }

    public void setInDt2(String inDt2) {
        this.inDt2 = inDt2;
    }


    @Column(name = "out_dt2")
    public String getOutDt2() {
        return outDt2;
    }

    public void setOutDt2(String outDt2) {
        this.outDt2 = outDt2;
    }


    @Column(name = "in_dt3")
    public String getInDt3() {
        return inDt3;
    }

    public void setInDt3(String inDt3) {
        this.inDt3 = inDt3;
    }


    @Column(name = "out_dt3")
    public String getOutDt3() {
        return outDt3;
    }

    public void setOutDt3(String outDt3) {
        this.outDt3 = outDt3;
    }


    @Column(name = "in_dt4")
    public String getInDt4() {
        return inDt4;
    }

    public void setInDt4(String inDt4) {
        this.inDt4 = inDt4;
    }


    @Column(name = "out_dt4")
    public String getOutDt4() {
        return outDt4;
    }

    public void setOutDt4(String outDt4) {
        this.outDt4 = outDt4;
    }


    @Column(name = "in_dt5")
    public String getInDt5() {
        return inDt5;
    }

    public void setInDt5(String inDt5) {
        this.inDt5 = inDt5;
    }


    @Column(name = "out_dt5")
    public String getOutDt5() {
        return outDt5;
    }

    public void setOutDt5(String outDt5) {
        this.outDt5 = outDt5;
    }


    @Column(name = "in_dt6")
    public String getInDt6() {
        return inDt6;
    }

    public void setInDt6(String inDt6) {
        this.inDt6 = inDt6;
    }


    @Column(name = "out_dt6")
    public String getOutDt6() {
        return outDt6;
    }

    public void setOutDt6(String outDt6) {
        this.outDt6 = outDt6;
    }


    @Column(name = "in_dt7")
    public String getInDt7() {
        return inDt7;
    }

    public void setInDt7(String inDt7) {
        this.inDt7 = inDt7;
    }


    @Column(name = "out_dt7")
    public String getOutDt7() {
        return outDt7;
    }

    public void setOutDt7(String outDt7) {
        this.outDt7 = outDt7;
    }


    @Column(name = "in_dt8")
    public String getInDt8() {
        return inDt8;
    }

    public void setInDt8(String inDt8) {
        this.inDt8 = inDt8;
    }


    @Column(name = "out_dt8")
    public String getOutDt8() {
        return outDt8;
    }

    public void setOutDt8(String outDt8) {
        this.outDt8 = outDt8;
    }


    @Column(name = "in_dt9")
    public String getInDt9() {
        return inDt9;
    }

    public void setInDt9(String inDt9) {
        this.inDt9 = inDt9;
    }


    @Column(name = "out_dt9")
    public String getOutDt9() {
        return outDt9;
    }

    public void setOutDt9(String outDt9) {
        this.outDt9 = outDt9;
    }


    @Column(name = "in_dt10")
    public String getInDt10() {
        return inDt10;
    }

    public void setInDt10(String inDt10) {
        this.inDt10 = inDt10;
    }


    @Column(name = "out_dt10")
    public String getOutDt10() {
        return outDt10;
    }

    public void setOutDt10(String outDt10) {
        this.outDt10 = outDt10;
    }


    @Column(name = "in_dt11")
    public String getInDt11() {
        return inDt11;
    }

    public void setInDt11(String inDt11) {
        this.inDt11 = inDt11;
    }


    @Column(name = "out_dt11")
    public String getOutDt11() {
        return outDt11;
    }

    public void setOutDt11(String outDt11) {
        this.outDt11 = outDt11;
    }


    @Column(name = "in_dt12")
    public String getInDt12() {
        return inDt12;
    }

    public void setInDt12(String inDt12) {
        this.inDt12 = inDt12;
    }


    @Column(name = "out_dt12")
    public String getOutDt12() {
        return outDt12;
    }

    public void setOutDt12(String outDt12) {
        this.outDt12 = outDt12;
    }


    @Column(name = "in_dt13")
    public String getInDt13() {
        return inDt13;
    }

    public void setInDt13(String inDt13) {
        this.inDt13 = inDt13;
    }


    @Column(name = "out_dt13")
    public String getOutDt13() {
        return outDt13;
    }

    public void setOutDt13(String outDt13) {
        this.outDt13 = outDt13;
    }


    @Column(name = "in_dt14")
    public String getInDt14() {
        return inDt14;
    }

    public void setInDt14(String inDt14) {
        this.inDt14 = inDt14;
    }


    @Column(name = "out_dt14")
    public String getOutDt14() {
        return outDt14;
    }

    public void setOutDt14(String outDt14) {
        this.outDt14 = outDt14;
    }


    @Column(name = "in_dt15")
    public String getInDt15() {
        return inDt15;
    }

    public void setInDt15(String inDt15) {
        this.inDt15 = inDt15;
    }


    @Column(name = "out_dt15")
    public String getOutDt15() {
        return outDt15;
    }

    public void setOutDt15(String outDt15) {
        this.outDt15 = outDt15;
    }


    @Column(name = "in_dt16")
    public String getInDt16() {
        return inDt16;
    }

    public void setInDt16(String inDt16) {
        this.inDt16 = inDt16;
    }


    @Column(name = "out_dt16")
    public String getOutDt16() {
        return outDt16;
    }

    public void setOutDt16(String outDt16) {
        this.outDt16 = outDt16;
    }


    @Column(name = "in_dt17")
    public String getInDt17() {
        return inDt17;
    }

    public void setInDt17(String inDt17) {
        this.inDt17 = inDt17;
    }


    @Column(name = "out_dt17")
    public String getOutDt17() {
        return outDt17;
    }

    public void setOutDt17(String outDt17) {
        this.outDt17 = outDt17;
    }


    @Column(name = "in_dt18")
    public String getInDt18() {
        return inDt18;
    }

    public void setInDt18(String inDt18) {
        this.inDt18 = inDt18;
    }


    @Column(name = "out_dt18")
    public String getOutDt18() {
        return outDt18;
    }

    public void setOutDt18(String outDt18) {
        this.outDt18 = outDt18;
    }


    @Column(name = "in_dt19")
    public String getInDt19() {
        return inDt19;
    }

    public void setInDt19(String inDt19) {
        this.inDt19 = inDt19;
    }


    @Column(name = "out_dt19")
    public String getOutDt19() {
        return outDt19;
    }

    public void setOutDt19(String outDt19) {
        this.outDt19 = outDt19;
    }


    @Column(name = "in_dt20")
    public String getInDt20() {
        return inDt20;
    }

    public void setInDt20(String inDt20) {
        this.inDt20 = inDt20;
    }


    @Column(name = "out_dt20")
    public String getOutDt20() {
        return outDt20;
    }

    public void setOutDt20(String outDt20) {
        this.outDt20 = outDt20;
    }


    @Column(name = "in_dt21")
    public String getInDt21() {
        return inDt21;
    }

    public void setInDt21(String inDt21) {
        this.inDt21 = inDt21;
    }


    @Column(name = "out_dt21")
    public String getOutDt21() {
        return outDt21;
    }

    public void setOutDt21(String outDt21) {
        this.outDt21 = outDt21;
    }


    @Column(name = "in_dt22")
    public String getInDt22() {
        return inDt22;
    }

    public void setInDt22(String inDt22) {
        this.inDt22 = inDt22;
    }


    @Column(name = "out_dt22")
    public String getOutDt22() {
        return outDt22;
    }

    public void setOutDt22(String outDt22) {
        this.outDt22 = outDt22;
    }


    @Column(name = "in_dt23")
    public String getInDt23() {
        return inDt23;
    }

    public void setInDt23(String inDt23) {
        this.inDt23 = inDt23;
    }


    @Column(name = "out_dt23")
    public String getOutDt23() {
        return outDt23;
    }

    public void setOutDt23(String outDt23) {
        this.outDt23 = outDt23;
    }


    @Column(name = "in_dt24")
    public String getInDt24() {
        return inDt24;
    }

    public void setInDt24(String inDt24) {
        this.inDt24 = inDt24;
    }


    @Column(name = "out_dt24")
    public String getOutDt24() {
        return outDt24;
    }

    public void setOutDt24(String outDt24) {
        this.outDt24 = outDt24;
    }


    @Column(name = "in_dt25")
    public String getInDt25() {
        return inDt25;
    }

    public void setInDt25(String inDt25) {
        this.inDt25 = inDt25;
    }


    @Column(name = "out_dt25")
    public String getOutDt25() {
        return outDt25;
    }

    public void setOutDt25(String outDt25) {
        this.outDt25 = outDt25;
    }


    @Column(name = "in_dt26")
    public String getInDt26() {
        return inDt26;
    }

    public void setInDt26(String inDt26) {
        this.inDt26 = inDt26;
    }


    @Column(name = "out_dt26")
    public String getOutDt26() {
        return outDt26;
    }

    public void setOutDt26(String outDt26) {
        this.outDt26 = outDt26;
    }


    @Column(name = "in_dt27")
    public String getInDt27() {
        return inDt27;
    }

    public void setInDt27(String inDt27) {
        this.inDt27 = inDt27;
    }


    @Column(name = "out_dt27")
    public String getOutDt27() {
        return outDt27;
    }

    public void setOutDt27(String outDt27) {
        this.outDt27 = outDt27;
    }


    @Column(name = "in_dt28")
    public String getInDt28() {
        return inDt28;
    }

    public void setInDt28(String inDt28) {
        this.inDt28 = inDt28;
    }


    @Column(name = "out_dt28")
    public String getOutDt28() {
        return outDt28;
    }

    public void setOutDt28(String outDt28) {
        this.outDt28 = outDt28;
    }


    @Column(name = "in_dt29")
    public String getInDt29() {
        return inDt29;
    }

    public void setInDt29(String inDt29) {
        this.inDt29 = inDt29;
    }


    @Column(name = "out_dt29")
    public String getOutDt29() {
        return outDt29;
    }

    public void setOutDt29(String outDt29) {
        this.outDt29 = outDt29;
    }


    @Column(name = "in_dt30")
    public String getInDt30() {
        return inDt30;
    }

    public void setInDt30(String inDt30) {
        this.inDt30 = inDt30;
    }


    @Column(name = "out_dt30")
    public String getOutDt30() {
        return outDt30;
    }

    public void setOutDt30(String outDt30) {
        this.outDt30 = outDt30;
    }


    @Column(name = "in_dt31")
    public String getInDt31() {
        return inDt31;
    }

    public void setInDt31(String inDt31) {
        this.inDt31 = inDt31;
    }


    @Column(name = "out_dt31")
    public String getOutDt31() {
        return outDt31;
    }

    public void setOutDt31(String outDt31) {
        this.outDt31 = outDt31;
    }


    @Column(name = "m_txt1")
    public String getmTxt1() {
        return mTxt1;
    }

    public void setmTxt1(String mTxt1) {
        this.mTxt1 = mTxt1;
    }


    @Column(name = "m_txt2")
    public String getmTxt2() {
        return mTxt2;
    }

    public void setmTxt2(String mTxt2) {
        this.mTxt2 = mTxt2;
    }


    @Column(name = "m_txt3")
    public String getmTxt3() {
        return mTxt3;
    }

    public void setmTxt3(String mTxt3) {
        this.mTxt3 = mTxt3;
    }


    @Column(name = "m_txt4")
    public String getmTxt4() {
        return mTxt4;
    }

    public void setmTxt4(String mTxt4) {
        this.mTxt4 = mTxt4;
    }


    @Column(name = "m_txt5")
    public String getmTxt5() {
        return mTxt5;
    }

    public void setmTxt5(String mTxt5) {
        this.mTxt5 = mTxt5;
    }


    @Column(name = "m_txt6")
    public String getmTxt6() {
        return mTxt6;
    }

    public void setmTxt6(String mTxt6) {
        this.mTxt6 = mTxt6;
    }


    @Column(name = "m_txt7")
    public String getmTxt7() {
        return mTxt7;
    }

    public void setmTxt7(String mTxt7) {
        this.mTxt7 = mTxt7;
    }


    @Column(name = "m_txt8")
    public String getmTxt8() {
        return mTxt8;
    }

    public void setmTxt8(String mTxt8) {
        this.mTxt8 = mTxt8;
    }


    @Column(name = "m_txt9")
    public String getmTxt9() {
        return mTxt9;
    }

    public void setmTxt9(String mTxt9) {
        this.mTxt9 = mTxt9;
    }


    @Column(name = "m_txt10")
    public String getmTxt10() {
        return mTxt10;
    }

    public void setmTxt10(String mTxt10) {
        this.mTxt10 = mTxt10;
    }


    @Column(name = "m_txt11")
    public String getmTxt11() {
        return mTxt11;
    }

    public void setmTxt11(String mTxt11) {
        this.mTxt11 = mTxt11;
    }


    @Column(name = "m_txt12")
    public String getmTxt12() {
        return mTxt12;
    }

    public void setmTxt12(String mTxt12) {
        this.mTxt12 = mTxt12;
    }


    @Column(name = "m_txt13")
    public String getmTxt13() {
        return mTxt13;
    }

    public void setmTxt13(String mTxt13) {
        this.mTxt13 = mTxt13;
    }


    @Column(name = "m_txt14")
    public String getmTxt14() {
        return mTxt14;
    }

    public void setmTxt14(String mTxt14) {
        this.mTxt14 = mTxt14;
    }


    @Column(name = "m_txt15")
    public String getmTxt15() {
        return mTxt15;
    }

    public void setmTxt15(String mTxt15) {
        this.mTxt15 = mTxt15;
    }


    @Column(name = "m_txt16")
    public String getmTxt16() {
        return mTxt16;
    }

    public void setmTxt16(String mTxt16) {
        this.mTxt16 = mTxt16;
    }


    @Column(name = "m_txt17")
    public String getmTxt17() {
        return mTxt17;
    }

    public void setmTxt17(String mTxt17) {
        this.mTxt17 = mTxt17;
    }


    @Column(name = "m_txt18")
    public String getmTxt18() {
        return mTxt18;
    }

    public void setmTxt18(String mTxt18) {
        this.mTxt18 = mTxt18;
    }


    @Column(name = "m_txt19")
    public String getmTxt19() {
        return mTxt19;
    }

    public void setmTxt19(String mTxt19) {
        this.mTxt19 = mTxt19;
    }


    @Column(name = "m_txt20")
    public String getmTxt20() {
        return mTxt20;
    }

    public void setmTxt20(String mTxt20) {
        this.mTxt20 = mTxt20;
    }


    @Column(name = "m_txt21")
    public String getmTxt21() {
        return mTxt21;
    }

    public void setmTxt21(String mTxt21) {
        this.mTxt21 = mTxt21;
    }


    @Column(name = "m_txt22")
    public String getmTxt22() {
        return mTxt22;
    }

    public void setmTxt22(String mTxt22) {
        this.mTxt22 = mTxt22;
    }


    @Column(name = "m_txt23")
    public String getmTxt23() {
        return mTxt23;
    }

    public void setmTxt23(String mTxt23) {
        this.mTxt23 = mTxt23;
    }


    @Column(name = "m_txt24")
    public String getmTxt24() {
        return mTxt24;
    }

    public void setmTxt24(String mTxt24) {
        this.mTxt24 = mTxt24;
    }


    @Column(name = "m_txt25")
    public String getmTxt25() {
        return mTxt25;
    }

    public void setmTxt25(String mTxt25) {
        this.mTxt25 = mTxt25;
    }


    @Column(name = "m_txt26")
    public String getmTxt26() {
        return mTxt26;
    }

    public void setmTxt26(String mTxt26) {
        this.mTxt26 = mTxt26;
    }


    @Column(name = "m_txt27")
    public String getmTxt27() {
        return mTxt27;
    }

    public void setmTxt27(String mTxt27) {
        this.mTxt27 = mTxt27;
    }


    @Column(name = "m_txt28")
    public String getmTxt28() {
        return mTxt28;
    }

    public void setmTxt28(String mTxt28) {
        this.mTxt28 = mTxt28;
    }


    @Column(name = "m_txt29")
    public String getmTxt29() {
        return mTxt29;
    }

    public void setmTxt29(String mTxt29) {
        this.mTxt29 = mTxt29;
    }


    @Column(name = "m_txt30")
    public String getmTxt30() {
        return mTxt30;
    }

    public void setmTxt30(String mTxt30) {
        this.mTxt30 = mTxt30;
    }


    @Column(name = "m_txt31")
    public String getmTxt31() {
        return mTxt31;
    }

    public void setmTxt31(String mTxt31) {
        this.mTxt31 = mTxt31;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timet timet = (Timet) o;
        return Objects.equals(timetId, timet.timetId.getEmpCode()) &&
            Objects.equals(timetId, timet.timetId.getMonthNo()) &&
            Objects.equals(it1, timet.it1) &&
            Objects.equals(it2, timet.it2) &&
            Objects.equals(it3, timet.it3) &&
            Objects.equals(it4, timet.it4) &&
            Objects.equals(it5, timet.it5) &&
            Objects.equals(it6, timet.it6) &&
            Objects.equals(it7, timet.it7) &&
            Objects.equals(it8, timet.it8) &&
            Objects.equals(it9, timet.it9) &&
            Objects.equals(it10, timet.it10) &&
            Objects.equals(it11, timet.it11) &&
            Objects.equals(it12, timet.it12) &&
            Objects.equals(it13, timet.it13) &&
            Objects.equals(it14, timet.it14) &&
            Objects.equals(it15, timet.it15) &&
            Objects.equals(it16, timet.it16) &&
            Objects.equals(it17, timet.it17) &&
            Objects.equals(it18, timet.it18) &&
            Objects.equals(it19, timet.it19) &&
            Objects.equals(it20, timet.it20) &&
            Objects.equals(it21, timet.it21) &&
            Objects.equals(it22, timet.it22) &&
            Objects.equals(it23, timet.it23) &&
            Objects.equals(it24, timet.it24) &&
            Objects.equals(it25, timet.it25) &&
            Objects.equals(it26, timet.it26) &&
            Objects.equals(it27, timet.it27) &&
            Objects.equals(it28, timet.it28) &&
            Objects.equals(it29, timet.it29) &&
            Objects.equals(it30, timet.it30) &&
            Objects.equals(it31, timet.it31) &&
            Objects.equals(ot1, timet.ot1) &&
            Objects.equals(ot2, timet.ot2) &&
            Objects.equals(ot3, timet.ot3) &&
            Objects.equals(ot4, timet.ot4) &&
            Objects.equals(ot5, timet.ot5) &&
            Objects.equals(ot6, timet.ot6) &&
            Objects.equals(ot7, timet.ot7) &&
            Objects.equals(ot8, timet.ot8) &&
            Objects.equals(ot9, timet.ot9) &&
            Objects.equals(ot10, timet.ot10) &&
            Objects.equals(ot11, timet.ot11) &&
            Objects.equals(ot12, timet.ot12) &&
            Objects.equals(ot13, timet.ot13) &&
            Objects.equals(ot14, timet.ot14) &&
            Objects.equals(ot15, timet.ot15) &&
            Objects.equals(ot16, timet.ot16) &&
            Objects.equals(ot17, timet.ot17) &&
            Objects.equals(ot18, timet.ot18) &&
            Objects.equals(ot19, timet.ot19) &&
            Objects.equals(ot20, timet.ot20) &&
            Objects.equals(ot21, timet.ot21) &&
            Objects.equals(ot22, timet.ot22) &&
            Objects.equals(ot23, timet.ot23) &&
            Objects.equals(ot24, timet.ot24) &&
            Objects.equals(ot25, timet.ot25) &&
            Objects.equals(ot26, timet.ot26) &&
            Objects.equals(ot27, timet.ot27) &&
            Objects.equals(ot28, timet.ot28) &&
            Objects.equals(ot29, timet.ot29) &&
            Objects.equals(ot30, timet.ot30) &&
            Objects.equals(ot31, timet.ot31) &&
            Objects.equals(mit1, timet.mit1) &&
            Objects.equals(mit2, timet.mit2) &&
            Objects.equals(mit3, timet.mit3) &&
            Objects.equals(mit4, timet.mit4) &&
            Objects.equals(mit5, timet.mit5) &&
            Objects.equals(mit6, timet.mit6) &&
            Objects.equals(mit7, timet.mit7) &&
            Objects.equals(mit8, timet.mit8) &&
            Objects.equals(mit9, timet.mit9) &&
            Objects.equals(mit10, timet.mit10) &&
            Objects.equals(mit11, timet.mit11) &&
            Objects.equals(mit12, timet.mit12) &&
            Objects.equals(mit13, timet.mit13) &&
            Objects.equals(mit14, timet.mit14) &&
            Objects.equals(mit15, timet.mit15) &&
            Objects.equals(mit16, timet.mit16) &&
            Objects.equals(mit17, timet.mit17) &&
            Objects.equals(mit18, timet.mit18) &&
            Objects.equals(mit19, timet.mit19) &&
            Objects.equals(mit20, timet.mit20) &&
            Objects.equals(mit21, timet.mit21) &&
            Objects.equals(mit22, timet.mit22) &&
            Objects.equals(mit23, timet.mit23) &&
            Objects.equals(mit24, timet.mit24) &&
            Objects.equals(mit25, timet.mit25) &&
            Objects.equals(mit26, timet.mit26) &&
            Objects.equals(mit27, timet.mit27) &&
            Objects.equals(mit28, timet.mit28) &&
            Objects.equals(mit29, timet.mit29) &&
            Objects.equals(mit30, timet.mit30) &&
            Objects.equals(mit31, timet.mit31) &&
            Objects.equals(mot1, timet.mot1) &&
            Objects.equals(mot2, timet.mot2) &&
            Objects.equals(mot3, timet.mot3) &&
            Objects.equals(mot4, timet.mot4) &&
            Objects.equals(mot5, timet.mot5) &&
            Objects.equals(mot6, timet.mot6) &&
            Objects.equals(mot7, timet.mot7) &&
            Objects.equals(mot8, timet.mot8) &&
            Objects.equals(mot9, timet.mot9) &&
            Objects.equals(mot10, timet.mot10) &&
            Objects.equals(mot11, timet.mot11) &&
            Objects.equals(mot12, timet.mot12) &&
            Objects.equals(mot13, timet.mot13) &&
            Objects.equals(mot14, timet.mot14) &&
            Objects.equals(mot15, timet.mot15) &&
            Objects.equals(mot16, timet.mot16) &&
            Objects.equals(mot17, timet.mot17) &&
            Objects.equals(mot18, timet.mot18) &&
            Objects.equals(mot19, timet.mot19) &&
            Objects.equals(mot20, timet.mot20) &&
            Objects.equals(mot21, timet.mot21) &&
            Objects.equals(mot22, timet.mot22) &&
            Objects.equals(mot23, timet.mot23) &&
            Objects.equals(mot24, timet.mot24) &&
            Objects.equals(mot25, timet.mot25) &&
            Objects.equals(mot26, timet.mot26) &&
            Objects.equals(mot27, timet.mot27) &&
            Objects.equals(mot28, timet.mot28) &&
            Objects.equals(mot29, timet.mot29) &&
            Objects.equals(mot30, timet.mot30) &&
            Objects.equals(mot31, timet.mot31) &&
            Objects.equals(pit1, timet.pit1) &&
            Objects.equals(pit2, timet.pit2) &&
            Objects.equals(pit3, timet.pit3) &&
            Objects.equals(pit4, timet.pit4) &&
            Objects.equals(pit5, timet.pit5) &&
            Objects.equals(pit6, timet.pit6) &&
            Objects.equals(pit7, timet.pit7) &&
            Objects.equals(pit8, timet.pit8) &&
            Objects.equals(pit9, timet.pit9) &&
            Objects.equals(pit10, timet.pit10) &&
            Objects.equals(pit11, timet.pit11) &&
            Objects.equals(pit12, timet.pit12) &&
            Objects.equals(pit13, timet.pit13) &&
            Objects.equals(pit14, timet.pit14) &&
            Objects.equals(pit15, timet.pit15) &&
            Objects.equals(pit16, timet.pit16) &&
            Objects.equals(pit17, timet.pit17) &&
            Objects.equals(pit18, timet.pit18) &&
            Objects.equals(pit19, timet.pit19) &&
            Objects.equals(pit20, timet.pit20) &&
            Objects.equals(pit21, timet.pit21) &&
            Objects.equals(pit22, timet.pit22) &&
            Objects.equals(pit23, timet.pit23) &&
            Objects.equals(pit24, timet.pit24) &&
            Objects.equals(pit25, timet.pit25) &&
            Objects.equals(pit26, timet.pit26) &&
            Objects.equals(pit27, timet.pit27) &&
            Objects.equals(pit28, timet.pit28) &&
            Objects.equals(pit29, timet.pit29) &&
            Objects.equals(pit30, timet.pit30) &&
            Objects.equals(pit31, timet.pit31) &&
            Objects.equals(pot1, timet.pot1) &&
            Objects.equals(pot2, timet.pot2) &&
            Objects.equals(pot3, timet.pot3) &&
            Objects.equals(pot4, timet.pot4) &&
            Objects.equals(pot5, timet.pot5) &&
            Objects.equals(pot6, timet.pot6) &&
            Objects.equals(pot7, timet.pot7) &&
            Objects.equals(pot8, timet.pot8) &&
            Objects.equals(pot9, timet.pot9) &&
            Objects.equals(pot10, timet.pot10) &&
            Objects.equals(pot11, timet.pot11) &&
            Objects.equals(pot12, timet.pot12) &&
            Objects.equals(pot13, timet.pot13) &&
            Objects.equals(pot14, timet.pot14) &&
            Objects.equals(pot15, timet.pot15) &&
            Objects.equals(pot16, timet.pot16) &&
            Objects.equals(pot17, timet.pot17) &&
            Objects.equals(pot18, timet.pot18) &&
            Objects.equals(pot19, timet.pot19) &&
            Objects.equals(pot20, timet.pot20) &&
            Objects.equals(pot21, timet.pot21) &&
            Objects.equals(pot22, timet.pot22) &&
            Objects.equals(pot23, timet.pot23) &&
            Objects.equals(pot24, timet.pot24) &&
            Objects.equals(pot25, timet.pot25) &&
            Objects.equals(pot26, timet.pot26) &&
            Objects.equals(pot27, timet.pot27) &&
            Objects.equals(pot28, timet.pot28) &&
            Objects.equals(pot29, timet.pot29) &&
            Objects.equals(pot30, timet.pot30) &&
            Objects.equals(pot31, timet.pot31) &&
            Objects.equals(inDt1, timet.inDt1) &&
            Objects.equals(outDt1, timet.outDt1) &&
            Objects.equals(inDt2, timet.inDt2) &&
            Objects.equals(outDt2, timet.outDt2) &&
            Objects.equals(inDt3, timet.inDt3) &&
            Objects.equals(outDt3, timet.outDt3) &&
            Objects.equals(inDt4, timet.inDt4) &&
            Objects.equals(outDt4, timet.outDt4) &&
            Objects.equals(inDt5, timet.inDt5) &&
            Objects.equals(outDt5, timet.outDt5) &&
            Objects.equals(inDt6, timet.inDt6) &&
            Objects.equals(outDt6, timet.outDt6) &&
            Objects.equals(inDt7, timet.inDt7) &&
            Objects.equals(outDt7, timet.outDt7) &&
            Objects.equals(inDt8, timet.inDt8) &&
            Objects.equals(outDt8, timet.outDt8) &&
            Objects.equals(inDt9, timet.inDt9) &&
            Objects.equals(outDt9, timet.outDt9) &&
            Objects.equals(inDt10, timet.inDt10) &&
            Objects.equals(outDt10, timet.outDt10) &&
            Objects.equals(inDt11, timet.inDt11) &&
            Objects.equals(outDt11, timet.outDt11) &&
            Objects.equals(inDt12, timet.inDt12) &&
            Objects.equals(outDt12, timet.outDt12) &&
            Objects.equals(inDt13, timet.inDt13) &&
            Objects.equals(outDt13, timet.outDt13) &&
            Objects.equals(inDt14, timet.inDt14) &&
            Objects.equals(outDt14, timet.outDt14) &&
            Objects.equals(inDt15, timet.inDt15) &&
            Objects.equals(outDt15, timet.outDt15) &&
            Objects.equals(inDt16, timet.inDt16) &&
            Objects.equals(outDt16, timet.outDt16) &&
            Objects.equals(inDt17, timet.inDt17) &&
            Objects.equals(outDt17, timet.outDt17) &&
            Objects.equals(inDt18, timet.inDt18) &&
            Objects.equals(outDt18, timet.outDt18) &&
            Objects.equals(inDt19, timet.inDt19) &&
            Objects.equals(outDt19, timet.outDt19) &&
            Objects.equals(inDt20, timet.inDt20) &&
            Objects.equals(outDt20, timet.outDt20) &&
            Objects.equals(inDt21, timet.inDt21) &&
            Objects.equals(outDt21, timet.outDt21) &&
            Objects.equals(inDt22, timet.inDt22) &&
            Objects.equals(outDt22, timet.outDt22) &&
            Objects.equals(inDt23, timet.inDt23) &&
            Objects.equals(outDt23, timet.outDt23) &&
            Objects.equals(inDt24, timet.inDt24) &&
            Objects.equals(outDt24, timet.outDt24) &&
            Objects.equals(inDt25, timet.inDt25) &&
            Objects.equals(outDt25, timet.outDt25) &&
            Objects.equals(inDt26, timet.inDt26) &&
            Objects.equals(outDt26, timet.outDt26) &&
            Objects.equals(inDt27, timet.inDt27) &&
            Objects.equals(outDt27, timet.outDt27) &&
            Objects.equals(inDt28, timet.inDt28) &&
            Objects.equals(outDt28, timet.outDt28) &&
            Objects.equals(inDt29, timet.inDt29) &&
            Objects.equals(outDt29, timet.outDt29) &&
            Objects.equals(inDt30, timet.inDt30) &&
            Objects.equals(outDt30, timet.outDt30) &&
            Objects.equals(inDt31, timet.inDt31) &&
            Objects.equals(outDt31, timet.outDt31) &&
            Objects.equals(mTxt1, timet.mTxt1) &&
            Objects.equals(mTxt2, timet.mTxt2) &&
            Objects.equals(mTxt3, timet.mTxt3) &&
            Objects.equals(mTxt4, timet.mTxt4) &&
            Objects.equals(mTxt5, timet.mTxt5) &&
            Objects.equals(mTxt6, timet.mTxt6) &&
            Objects.equals(mTxt7, timet.mTxt7) &&
            Objects.equals(mTxt8, timet.mTxt8) &&
            Objects.equals(mTxt9, timet.mTxt9) &&
            Objects.equals(mTxt10, timet.mTxt10) &&
            Objects.equals(mTxt11, timet.mTxt11) &&
            Objects.equals(mTxt12, timet.mTxt12) &&
            Objects.equals(mTxt13, timet.mTxt13) &&
            Objects.equals(mTxt14, timet.mTxt14) &&
            Objects.equals(mTxt15, timet.mTxt15) &&
            Objects.equals(mTxt16, timet.mTxt16) &&
            Objects.equals(mTxt17, timet.mTxt17) &&
            Objects.equals(mTxt18, timet.mTxt18) &&
            Objects.equals(mTxt19, timet.mTxt19) &&
            Objects.equals(mTxt20, timet.mTxt20) &&
            Objects.equals(mTxt21, timet.mTxt21) &&
            Objects.equals(mTxt22, timet.mTxt22) &&
            Objects.equals(mTxt23, timet.mTxt23) &&
            Objects.equals(mTxt24, timet.mTxt24) &&
            Objects.equals(mTxt25, timet.mTxt25) &&
            Objects.equals(mTxt26, timet.mTxt26) &&
            Objects.equals(mTxt27, timet.mTxt27) &&
            Objects.equals(mTxt28, timet.mTxt28) &&
            Objects.equals(mTxt29, timet.mTxt29) &&
            Objects.equals(mTxt30, timet.mTxt30) &&
            Objects.equals(mTxt31, timet.mTxt31);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timetId.getEmpCode(), timetId.getMonthNo(), it1, it2, it3, it4, it5, it6, it7, it8, it9, it10, it11, it12, it13, it14, it15, it16, it17, it18, it19, it20, it21, it22, it23, it24, it25, it26, it27, it28, it29, it30, it31, ot1, ot2, ot3, ot4, ot5, ot6, ot7, ot8, ot9, ot10, ot11, ot12, ot13, ot14, ot15, ot16, ot17, ot18, ot19, ot20, ot21, ot22, ot23, ot24, ot25, ot26, ot27, ot28, ot29, ot30, ot31, mit1, mit2, mit3, mit4, mit5, mit6, mit7, mit8, mit9, mit10, mit11, mit12, mit13, mit14, mit15, mit16, mit17, mit18, mit19, mit20, mit21, mit22, mit23, mit24, mit25, mit26, mit27, mit28, mit29, mit30, mit31, mot1, mot2, mot3, mot4, mot5, mot6, mot7, mot8, mot9, mot10, mot11, mot12, mot13, mot14, mot15, mot16, mot17, mot18, mot19, mot20, mot21, mot22, mot23, mot24, mot25, mot26, mot27, mot28, mot29, mot30, mot31, pit1, pit2, pit3, pit4, pit5, pit6, pit7, pit8, pit9, pit10, pit11, pit12, pit13, pit14, pit15, pit16, pit17, pit18, pit19, pit20, pit21, pit22, pit23, pit24, pit25, pit26, pit27, pit28, pit29, pit30, pit31, pot1, pot2, pot3, pot4, pot5, pot6, pot7, pot8, pot9, pot10, pot11, pot12, pot13, pot14, pot15, pot16, pot17, pot18, pot19, pot20, pot21, pot22, pot23, pot24, pot25, pot26, pot27, pot28, pot29, pot30, pot31, inDt1, outDt1, inDt2, outDt2, inDt3, outDt3, inDt4, outDt4, inDt5, outDt5, inDt6, outDt6, inDt7, outDt7, inDt8, outDt8, inDt9, outDt9, inDt10, outDt10, inDt11, outDt11, inDt12, outDt12, inDt13, outDt13, inDt14, outDt14, inDt15, outDt15, inDt16, outDt16, inDt17, outDt17, inDt18, outDt18, inDt19, outDt19, inDt20, outDt20, inDt21, outDt21, inDt22, outDt22, inDt23, outDt23, inDt24, outDt24, inDt25, outDt25, inDt26, outDt26, inDt27, outDt27, inDt28, outDt28, inDt29, outDt29, inDt30, outDt30, inDt31, outDt31, mTxt1, mTxt2, mTxt3, mTxt4, mTxt5, mTxt6, mTxt7, mTxt8, mTxt9, mTxt10, mTxt11, mTxt12, mTxt13, mTxt14, mTxt15, mTxt16, mTxt17, mTxt18, mTxt19, mTxt20, mTxt21, mTxt22, mTxt23, mTxt24, mTxt25, mTxt26, mTxt27, mTxt28, mTxt29, mTxt30, mTxt31);
    }
}

package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "stocktransactiontemplate")
public class Stocktransactiontemplate implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "longdescription")
    private String longdescription;

    @Column(name = "stocktransactiontype")
    private String stocktransactiontype;

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getLongdescription() {
        return longdescription;
    }
    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public String getStocktransactiontype() {
        return stocktransactiontype;
    }

    public void setStocktransactiontype(String stocktransactiontype) {
        this.stocktransactiontype = stocktransactiontype;
    }
}

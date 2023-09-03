package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "currency")
public class Currency implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "CODE")
    private String code;

    @Column(name = "LONGDESCRIPTION")
    private String longdescription;

    @Column(name = "SEARCHDESCRIPTION")
    private String searchdescription;

    @Column(name = "SHORTDESCRIPTION")
    private String shortdescription;

    public String getCode() {
        return code != null ? code.trim() : code;
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

    public String getSearchdescription() {
        return searchdescription;
    }

    public void setSearchdescription(String searchdescription) {
        this.searchdescription = searchdescription;
    }

    public String getShortdescription() {
        return shortdescription;
    }

    public void setShortdescription(String shortdescription) {
        this.shortdescription = shortdescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(code, currency.code) && Objects.equals(longdescription, currency.longdescription) && Objects.equals(searchdescription, currency.searchdescription) && Objects.equals(shortdescription, currency.shortdescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, longdescription, searchdescription, shortdescription);
    }
}

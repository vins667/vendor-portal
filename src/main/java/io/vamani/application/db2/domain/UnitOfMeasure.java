package io.vamani.application.db2.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "unitofmeasure")
public class UnitOfMeasure {

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "longdescription")
    private String longdescription;

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((longdescription == null) ? 0 : longdescription.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        UnitOfMeasure other = (UnitOfMeasure) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (longdescription == null) {
            if (other.longdescription != null)
                return false;
        } else if (!longdescription.equals(other.longdescription))
            return false;
        return true;
    }


}

package io.vamani.application.db2.domain;

import javax.persistence.*;

@Entity
@Table(name = "businessunitvscompany")
public class Businessunitvscompany {
    @EmbeddedId
    private BusinessunitvscompanyId id;

    public BusinessunitvscompanyId getId() {
        return id;
    }

    public void setId(BusinessunitvscompanyId id) {
        this.id = id;
    }
}

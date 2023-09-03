package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "vcut_main_entry_allow")
public class VcutMainEntryAllow {

    @Id
    private Long id;

    @Column(name = "minute_update")
    private Long minuteUpdate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMinuteUpdate() {
        return minuteUpdate;
    }

    public void setMinuteUpdate(Long minuteUpdate) {
        this.minuteUpdate = minuteUpdate;
    }
}

package io.vamani.application.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "event_access")
public class EventAccess implements Serializable {

    @EmbeddedId
    private EventAccessId id;

    @Column(name = "access_name")
    private String accessName;

    @Column(name = "flag")
    private String flag;

    public EventAccessId getId() {
        return id;
    }

    public void setId(EventAccessId id) {
        this.id = id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getAccessName() { return accessName; }

    public void setAccessName(String accessName) { this.accessName = accessName; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventAccess that = (EventAccess) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(flag, that.flag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flag);
    }

    @Override
    public String toString() {
        return "EventAccess{" +
            "id=" + id +
            ", flag='" + flag + '\'' +
            '}';
    }
}

package io.vamani.application.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "factory_merge")
public class FactoryMerge implements Serializable {
    @EmbeddedId
    private FactoryMergeId id;

    public FactoryMergeId getId() {
        return id;
    }

    public void setId(FactoryMergeId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactoryMerge that = (FactoryMerge) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "FactoryMerge{" +
            "id=" + id +
            '}';
    }
}

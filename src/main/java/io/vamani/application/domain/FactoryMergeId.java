package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class FactoryMergeId implements Serializable {
    @Column(name ="factory")
    private String factory;

    @Column(name ="factory1")
    private String factory1;

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getFactory1() {
        return factory1;
    }

    public void setFactory1(String factory1) {
        this.factory1 = factory1;
    }

    @Override
    public String toString() {
        return "FactoryMergeId{" +
            "factory='" + factory + '\'' +
            ", factory1='" + factory1 + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactoryMergeId that = (FactoryMergeId) o;
        return Objects.equals(factory, that.factory) &&
            Objects.equals(factory1, that.factory1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(factory, factory1);
    }
}

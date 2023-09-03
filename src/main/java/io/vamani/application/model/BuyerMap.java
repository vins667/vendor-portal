package io.vamani.application.model;

import java.io.Serializable;
import java.util.Objects;

public class BuyerMap implements Serializable {
    private String key;
    private Boolean value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public BuyerMap(String key, Boolean value) {
        this.key = key;
        this.value = value;
    }

    public BuyerMap() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuyerMap buyerMap = (BuyerMap) o;
        return Objects.equals(key, buyerMap.key) &&
            Objects.equals(value, buyerMap.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    @Override
    public String toString() {
        return "BuyerMap{" +
            "key='" + key + '\'' +
            ", value='" + value + '\'' +
            '}';
    }
}

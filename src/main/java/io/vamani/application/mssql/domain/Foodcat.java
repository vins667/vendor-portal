package io.vamani.application.mssql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "foodcat_view")
public class Foodcat implements Serializable {
    @Id
    @Column(name = "food_code")
    private Long foodCode;

    @Column(name = "food_desc")
    private String foodDesc;

    public Long getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(Long foodCode) {
        this.foodCode = foodCode;
    }

    public String getFoodDesc() {
        return foodDesc;
    }

    public void setFoodDesc(String foodDesc) {
        this.foodDesc = foodDesc;
    }

    @Override
    public String toString() {
        return "Foodcat{" +
            "foodCode=" + foodCode +
            ", foodDesc='" + foodDesc + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Foodcat foodcat = (Foodcat) o;
        return Objects.equals(foodCode, foodcat.foodCode) &&
            Objects.equals(foodDesc, foodcat.foodDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(foodCode, foodDesc);
    }
}

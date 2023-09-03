package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "jhi_user_plant")
public class UserPlant implements Serializable {
    @EmbeddedId
    private UserPlantId id;

    @Column(name = "plant_description")
    private String plantDescription;

    public UserPlantId getId() {
        return id;
    }

    public void setId(UserPlantId id) {
        this.id = id;
    }

    public String getPlantDescription() {
        return plantDescription;
    }

    public void setPlantDescription(String plantDescription) {
        this.plantDescription = plantDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPlant userPlant = (UserPlant) o;
        return Objects.equals(id, userPlant.id) && Objects.equals(plantDescription, userPlant.plantDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, plantDescription);
    }
}

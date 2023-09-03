package io.vamani.application.domain;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class UserPlantId implements Serializable {
    @Column(name = "login")
    private String login;

    @Column(name = "plant_code")
    private String plantCode;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public UserPlantId() {
    }

    public UserPlantId(String login, String plantCode) {
        this.login = login;
        this.plantCode = plantCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPlantId that = (UserPlantId) o;
        return Objects.equals(login, that.login) && Objects.equals(plantCode, that.plantCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, plantCode);
    }
}

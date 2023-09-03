package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "vcut_factory_access")
public class VcutFactoryAccess implements Serializable {
    @EmbeddedId
    private VcutFactoryAccessId id;

    @Column(name = "app_type")
    private String appType;

    public VcutFactoryAccessId getId() {
        return id;
    }

    public void setId(VcutFactoryAccessId id) {
        this.id = id;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VcutFactoryAccess that = (VcutFactoryAccess) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(appType, that.appType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appType);
    }

    @Override
    public String toString() {
        return "VcutFactoryAccess{" +
            "id=" + id +
            ", appType='" + appType + '\'' +
            '}';
    }
}

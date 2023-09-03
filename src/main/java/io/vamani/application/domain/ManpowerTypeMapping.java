package io.vamani.application.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * A ManpowerTypeMapping.
 */
@Entity
@Table(name = "manpower_type_mapping")
public class ManpowerTypeMapping implements Serializable {
    @EmbeddedId
    private ManpowerTypeMappingId id;

    @Column(name = "dept_desc", length = 20, nullable = false)
    private String deptDesc;

    public ManpowerTypeMappingId getId() {
        return id;
    }

    public void setId(ManpowerTypeMappingId id) {
        this.id = id;
    }

    public String getDeptDesc() {
        return deptDesc;
    }

    public void setDeptDesc(String deptDesc) {
        this.deptDesc = deptDesc;
    }
}

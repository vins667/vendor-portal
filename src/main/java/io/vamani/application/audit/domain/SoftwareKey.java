package io.vamani.application.audit.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "software_key")
@Data
public class SoftwareKey implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "system_id")
  private Integer systemId;

  @Column(name = "current", nullable = false)
  private String current = "y";

  @Column(name = "first_seen", nullable = false)
  private Date firstSeen;

  @Column(name = "last_seen", nullable = false)
  private Date lastSeen;

  @Column(name = "name", nullable = false)
  private String name = "";

  @Column(name = "string", nullable = false)
  private String string = "";

  @Column(name = "rel", nullable = false)
  private String rel = "";

  @Column(name = "edition", nullable = false)
  private String edition = "";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public Date getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(Date firstSeen) {
        this.firstSeen = firstSeen;
    }

    public Date getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(Date lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SoftwareKey that = (SoftwareKey) o;
        return Objects.equals(id, that.id) &&
            Objects.equals(systemId, that.systemId) &&
            Objects.equals(current, that.current) &&
            Objects.equals(firstSeen, that.firstSeen) &&
            Objects.equals(lastSeen, that.lastSeen) &&
            Objects.equals(name, that.name) &&
            Objects.equals(string, that.string) &&
            Objects.equals(rel, that.rel) &&
            Objects.equals(edition, that.edition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, systemId, current, firstSeen, lastSeen, name, string, rel, edition);
    }

    @Override
    public String toString() {
        return "SoftwareKey{" +
            "id=" + id +
            ", systemId=" + systemId +
            ", current='" + current + '\'' +
            ", firstSeen=" + firstSeen +
            ", lastSeen=" + lastSeen +
            ", name='" + name + '\'' +
            ", string='" + string + '\'' +
            ", rel='" + rel + '\'' +
            ", edition='" + edition + '\'' +
            '}';
    }
}

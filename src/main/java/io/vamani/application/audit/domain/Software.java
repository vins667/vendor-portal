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
@Data
@Table(name = "software")
public class Software implements Serializable {
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

  @Column(name = "version", nullable = false)
  private String version = "";

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "location", nullable = false)
  private String location = "";

  @Column(name = "uninstall", nullable = false)
  private String uninstall;

  @Column(name = "install_date", nullable = false)
  private String installDate = "";

  @Column(name = "installed_by", nullable = false)
  private String installedBy = "";

  @Column(name = "installed_on", nullable = false)
  private Date installedOn;

  @Column(name = "publisher", nullable = false)
  private String publisher = "";

  @Column(name = "install_source", nullable = false)
  private String installSource = "";

  @Column(name = "system_component", nullable = false)
  private String systemComponent = "";

  @Column(name = "url", nullable = false)
  private String url = "";

  @Column(name = "email", nullable = false)
  private String email = "";

  @Column(name = "type", nullable = false)
  private String type = "";

  @Column(name = "version_padded", nullable = false)
  private String versionPadded;

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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUninstall() {
        return uninstall;
    }

    public void setUninstall(String uninstall) {
        this.uninstall = uninstall;
    }

    public String getInstallDate() {
        return installDate;
    }

    public void setInstallDate(String installDate) {
        this.installDate = installDate;
    }

    public String getInstalledBy() {
        return installedBy;
    }

    public void setInstalledBy(String installedBy) {
        this.installedBy = installedBy;
    }

    public Date getInstalledOn() {
        return installedOn;
    }

    public void setInstalledOn(Date installedOn) {
        this.installedOn = installedOn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getInstallSource() {
        return installSource;
    }

    public void setInstallSource(String installSource) {
        this.installSource = installSource;
    }

    public String getSystemComponent() {
        return systemComponent;
    }

    public void setSystemComponent(String systemComponent) {
        this.systemComponent = systemComponent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersionPadded() {
        return versionPadded;
    }

    public void setVersionPadded(String versionPadded) {
        this.versionPadded = versionPadded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Software software = (Software) o;
        return Objects.equals(id, software.id) &&
            Objects.equals(systemId, software.systemId) &&
            Objects.equals(current, software.current) &&
            Objects.equals(firstSeen, software.firstSeen) &&
            Objects.equals(lastSeen, software.lastSeen) &&
            Objects.equals(name, software.name) &&
            Objects.equals(version, software.version) &&
            Objects.equals(description, software.description) &&
            Objects.equals(location, software.location) &&
            Objects.equals(uninstall, software.uninstall) &&
            Objects.equals(installDate, software.installDate) &&
            Objects.equals(installedBy, software.installedBy) &&
            Objects.equals(installedOn, software.installedOn) &&
            Objects.equals(publisher, software.publisher) &&
            Objects.equals(installSource, software.installSource) &&
            Objects.equals(systemComponent, software.systemComponent) &&
            Objects.equals(url, software.url) &&
            Objects.equals(email, software.email) &&
            Objects.equals(type, software.type) &&
            Objects.equals(versionPadded, software.versionPadded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, systemId, current, firstSeen, lastSeen, name, version, description, location, uninstall, installDate, installedBy, installedOn, publisher, installSource, systemComponent, url, email, type, versionPadded);
    }

    @Override
    public String toString() {
        return "Software{" +
            "id=" + id +
            ", systemId=" + systemId +
            ", current='" + current + '\'' +
            ", firstSeen=" + firstSeen +
            ", lastSeen=" + lastSeen +
            ", name='" + name + '\'' +
            ", version='" + version + '\'' +
            ", description='" + description + '\'' +
            ", location='" + location + '\'' +
            ", uninstall='" + uninstall + '\'' +
            ", installDate='" + installDate + '\'' +
            ", installedBy='" + installedBy + '\'' +
            ", installedOn=" + installedOn +
            ", publisher='" + publisher + '\'' +
            ", installSource='" + installSource + '\'' +
            ", systemComponent='" + systemComponent + '\'' +
            ", url='" + url + '\'' +
            ", email='" + email + '\'' +
            ", type='" + type + '\'' +
            ", versionPadded='" + versionPadded + '\'' +
            '}';
    }
}

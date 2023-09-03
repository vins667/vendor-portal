package io.vamani.application.audit.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "disk")
@Entity
@Data
public class Disk implements Serializable {
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

  @Column(name = "manufacturer", nullable = false)
  private String manufacturer = "";

  @Column(name = "model", nullable = false)
  private String model = "";

  @Column(name = "serial", nullable = false)
  private String serial = "";

  @Column(name = "device", nullable = false)
  private String device = "";

  @Column(name = "caption", nullable = false)
  private String caption = "";

  @Column(name = "hard_drive_index", nullable = false)
  private String hardDriveIndex = "";

  @Column(name = "interface_type", nullable = false)
  private String interfaceType = "";

  @Column(name = "partition_count", nullable = false)
  private Integer partitionCount = 0;

  @Column(name = "scsi_bus", nullable = false)
  private String scsiBus = "";

  @Column(name = "scsi_logical_unit", nullable = false)
  private String scsiLogicalUnit = "";

  @Column(name = "scsi_port", nullable = false)
  private String scsiPort = "";

  @Column(name = "size", nullable = false)
  private Integer size = 0;

  @Column(name = "status", nullable = false)
  private String status = "";

  @Column(name = "firmware", nullable = false)
  private String firmware = "";

  @Column(name = "model_family", nullable = false)
  private String modelFamily = "";

  
}
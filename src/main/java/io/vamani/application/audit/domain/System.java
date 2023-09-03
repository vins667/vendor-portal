package io.vamani.application.audit.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "system")
public class System implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  @Column(name = "uuid", nullable = false)
  private String uuid = "";

  @Column(name = "name", nullable = false)
  private String name = "";

  @Column(name = "ip", nullable = false)
  private String ip = "";

  @Column(name = "hostname", nullable = false)
  private String hostname = "";

  @Column(name = "dns_hostname", nullable = false)
  private String dnsHostname = "";

  @Column(name = "domain", nullable = false)
  private String domain = "";

  @Column(name = "dns_domain", nullable = false)
  private String dnsDomain = "";

  @Column(name = "dbus_identifier", nullable = false)
  private String dbusIdentifier = "";

  @Column(name = "fqdn", nullable = false)
  private String fqdn;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "type", nullable = false)
  private String type = "unknown";

  @Column(name = "comments", nullable = false)
  private String comments;

  @Column(name = "icon", nullable = false)
  private String icon = "";

  @Column(name = "os_group", nullable = false)
  private String osGroup = "";

  @Column(name = "os_family", nullable = false)
  private String osFamily = "";

  @Column(name = "os_name", nullable = false)
  private String osName = "";

  @Column(name = "os_version", nullable = false)
  private String osVersion = "";

  @Column(name = "attached_system_id")
  private Integer attachedSystemId;

  @Column(name = "manufacturer", nullable = false)
  private String manufacturer = "";

  @Column(name = "model", nullable = false)
  private String model = "";

  @Column(name = "serial", nullable = false)
  private String serial = "";

  @Column(name = "uptime", nullable = false)
  private String uptime = "";

  @Column(name = "form_factor", nullable = false)
  private String formFactor = "";

  @Column(name = "os_bit", nullable = false)
  private Integer osBit = 0;

  @Column(name = "memory_count", nullable = false)
  private Integer memoryCount = 0;

  @Column(name = "processor_count", nullable = false)
  private Integer processorCount = 0;

  @Column(name = "storage_count", nullable = false)
  private Integer storageCount = 0;

  @Column(name = "os_installation_date", nullable = false)
  private Date osInstallationDate;

  @Column(name = "printer_port_name", nullable = false)
  private String printerPortName = "";

  @Column(name = "printer_shared", nullable = false)
  private String printerShared = "";

  @Column(name = "printer_shared_name", nullable = false)
  private String printerSharedName = "";

  @Column(name = "printer_color", nullable = false)
  private String printerColor = "";

  @Column(name = "printer_duplex", nullable = false)
  private String printerDuplex = "";

  @Column(name = "status", nullable = false)
  private String status = "production";

  @Column(name = "environment", nullable = false)
  private String environment = "production";

  @Column(name = "function", nullable = false)
  private String function = "";

  @Column(name = "owner", nullable = false)
  private String owner = "";

  @Column(name = "org_id", nullable = false)
  private Integer orgId = 1;

  @Column(name = "location_id", nullable = false)
  private Integer locationId = 1;

  @Column(name = "location_level", nullable = false)
  private String locationLevel = "";

  @Column(name = "location_suite", nullable = false)
  private String locationSuite = "";

  @Column(name = "location_room", nullable = false)
  private String locationRoom = "";

  @Column(name = "location_rack", nullable = false)
  private String locationRack = "";

  @Column(name = "location_rack_position", nullable = false)
  private String locationRackPosition = "";

  @Column(name = "location_rack_size", nullable = false)
  private Integer locationRackSize = 0;

  @Column(name = "location_latitude", nullable = false)
  private Float locationLatitude;

  @Column(name = "location_longitude", nullable = false)
  private Float locationLongitude;

  @Column(name = "asset_number", nullable = false)
  private String assetNumber = "";

  @Column(name = "asset_tag", nullable = false)
  private String assetTag;

  @Column(name = "vm_server_name", nullable = false)
  private String vmServerName = "";

  @Column(name = "vm_system_id")
  private Integer vmSystemId;

  @Column(name = "vm_group", nullable = false)
  private String vmGroup = "";

  @Column(name = "cluster_name", nullable = false)
  private String clusterName = "";

  @Column(name = "cluster_type", nullable = false)
  private String clusterType = "";

  @Column(name = "invoice_id")
  private Integer invoiceId;

  @Column(name = "purchase_invoice", nullable = false)
  private String purchaseInvoice = "";

  @Column(name = "purchase_order_number", nullable = false)
  private String purchaseOrderNumber = "";

  @Column(name = "purchase_cost_center", nullable = false)
  private String purchaseCostCenter = "";

  @Column(name = "purchase_vendor", nullable = false)
  private String purchaseVendor = "";

  @Column(name = "purchase_date", nullable = false)
  private Date purchaseDate;

  @Column(name = "purchase_service_contract_number", nullable = false)
  private String purchaseServiceContractNumber = "";

  @Column(name = "lease_expiry_date", nullable = false)
  private Date leaseExpiryDate;

  @Column(name = "purchase_amount", nullable = false)
  private String purchaseAmount = "";

  @Column(name = "warranty_duration", nullable = false)
  private Integer warrantyDuration = 0;

  @Column(name = "warranty_expires", nullable = false)
  private Date warrantyExpires;

  @Column(name = "warranty_type", nullable = false)
  private String warrantyType = "";

  @Column(name = "end_of_life", nullable = false)
  private Date endOfLife;

  @Column(name = "end_of_service", nullable = false)
  private Date endOfService;

  @Column(name = "switch_system_id")
  private Integer switchSystemId;

  @Column(name = "switch_port", nullable = false)
  private Integer switchPort = 0;

  @Column(name = "patch_panel", nullable = false)
  private String patchPanel = "";

  @Column(name = "patch_panel_port", nullable = false)
  private Integer patchPanelPort = 0;

  @Column(name = "wall_port", nullable = false)
  private String wallPort = "";

  @Column(name = "contact_name", nullable = false)
  private String contactName = "";

  @Column(name = "service_number", nullable = false)
  private String serviceNumber = "";

  @Column(name = "service_provider", nullable = false)
  private String serviceProvider = "";

  @Column(name = "service_type", nullable = false)
  private String serviceType = "";

  @Column(name = "service_plan", nullable = false)
  private String servicePlan = "";

  @Column(name = "service_network", nullable = false)
  private String serviceNetwork = "";

  @Column(name = "unlock_pin", nullable = false)
  private String unlockPin = "";

  @Column(name = "serial_imei", nullable = false)
  private String serialImei = "";

  @Column(name = "serial_sim", nullable = false)
  private String serialSim = "";

  @Column(name = "nmis_group", nullable = false)
  private String nmisGroup = "";

  @Column(name = "nmis_name", nullable = false)
  private String nmisName = "";

  @Column(name = "nmis_role", nullable = false)
  private String nmisRole = "";

  @Column(name = "nmis_manage", nullable = false)
  private String nmisManage = "n";

  @Column(name = "nmis_notes", nullable = false)
  private String nmisNotes;

  @Column(name = "nmis_business_service", nullable = false)
  private String nmisBusinessService = "";

  @Column(name = "oae_manage", nullable = false)
  private String oaeManage = "y";

  @Column(name = "snmp_oid", nullable = false)
  private String snmpOid;

  @Column(name = "sysDescr", nullable = false)
  private String sysDescr;

  @Column(name = "sysObjectID", nullable = false)
  private String sysObjectID = "";

  @Column(name = "sysUpTime", nullable = false)
  private String sysUpTime = "";

  @Column(name = "sysContact", nullable = false)
  private String sysContact = "";

  @Column(name = "sysName", nullable = false)
  private String sysName = "";

  @Column(name = "sysLocation", nullable = false)
  private String sysLocation = "";

  @Column(name = "first_seen", nullable = false)
  private Date firstSeen;

  @Column(name = "last_seen", nullable = false)
  private Date lastSeen;

  @Column(name = "last_seen_by", nullable = false)
  private String lastSeenBy = "";

  @Column(name = "last_user", nullable = false)
  private String lastUser = "";

  @Column(name = "omk_uuid", nullable = false)
  private String omkUuid;

  @Column(name = "collector_uuid", nullable = false)
  private String collectorUuid;

  @Column(name = "credentials", nullable = false)
  private String credentials;

  @Column(name = "cloud_id")
  private Integer cloudId;

  @Column(name = "instance_provider", nullable = false)
  private String instanceProvider = "";

  @Column(name = "instance_ident", nullable = false)
  private String instanceIdent = "";

  @Column(name = "instance_type", nullable = false)
  private String instanceType = "";

  @Column(name = "instance_state", nullable = false)
  private String instanceState = "";

  @Column(name = "instance_reservation_ident", nullable = false)
  private String instanceReservationIdent = "";

  @Column(name = "instance_tags", nullable = false)
  private String instanceTags;

  @Column(name = "instance_options", nullable = false)
  private String instanceOptions;

  @Column(name = "discovery_id")
  private Integer discoveryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDnsHostname() {
        return dnsHostname;
    }

    public void setDnsHostname(String dnsHostname) {
        this.dnsHostname = dnsHostname;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDnsDomain() {
        return dnsDomain;
    }

    public void setDnsDomain(String dnsDomain) {
        this.dnsDomain = dnsDomain;
    }

    public String getDbusIdentifier() {
        return dbusIdentifier;
    }

    public void setDbusIdentifier(String dbusIdentifier) {
        this.dbusIdentifier = dbusIdentifier;
    }

    public String getFqdn() {
        return fqdn;
    }

    public void setFqdn(String fqdn) {
        this.fqdn = fqdn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getOsGroup() {
        return osGroup;
    }

    public void setOsGroup(String osGroup) {
        this.osGroup = osGroup;
    }

    public String getOsFamily() {
        return osFamily;
    }

    public void setOsFamily(String osFamily) {
        this.osFamily = osFamily;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public Integer getAttachedSystemId() {
        return attachedSystemId;
    }

    public void setAttachedSystemId(Integer attachedSystemId) {
        this.attachedSystemId = attachedSystemId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public Integer getOsBit() {
        return osBit;
    }

    public void setOsBit(Integer osBit) {
        this.osBit = osBit;
    }

    public Integer getMemoryCount() {
        return memoryCount;
    }

    public void setMemoryCount(Integer memoryCount) {
        this.memoryCount = memoryCount;
    }

    public Integer getProcessorCount() {
        return processorCount;
    }

    public void setProcessorCount(Integer processorCount) {
        this.processorCount = processorCount;
    }

    public Integer getStorageCount() {
        return storageCount;
    }

    public void setStorageCount(Integer storageCount) {
        this.storageCount = storageCount;
    }

    public Date getOsInstallationDate() {
        return osInstallationDate;
    }

    public void setOsInstallationDate(Date osInstallationDate) {
        this.osInstallationDate = osInstallationDate;
    }

    public String getPrinterPortName() {
        return printerPortName;
    }

    public void setPrinterPortName(String printerPortName) {
        this.printerPortName = printerPortName;
    }

    public String getPrinterShared() {
        return printerShared;
    }

    public void setPrinterShared(String printerShared) {
        this.printerShared = printerShared;
    }

    public String getPrinterSharedName() {
        return printerSharedName;
    }

    public void setPrinterSharedName(String printerSharedName) {
        this.printerSharedName = printerSharedName;
    }

    public String getPrinterColor() {
        return printerColor;
    }

    public void setPrinterColor(String printerColor) {
        this.printerColor = printerColor;
    }

    public String getPrinterDuplex() {
        return printerDuplex;
    }

    public void setPrinterDuplex(String printerDuplex) {
        this.printerDuplex = printerDuplex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getLocationLevel() {
        return locationLevel;
    }

    public void setLocationLevel(String locationLevel) {
        this.locationLevel = locationLevel;
    }

    public String getLocationSuite() {
        return locationSuite;
    }

    public void setLocationSuite(String locationSuite) {
        this.locationSuite = locationSuite;
    }

    public String getLocationRoom() {
        return locationRoom;
    }

    public void setLocationRoom(String locationRoom) {
        this.locationRoom = locationRoom;
    }

    public String getLocationRack() {
        return locationRack;
    }

    public void setLocationRack(String locationRack) {
        this.locationRack = locationRack;
    }

    public String getLocationRackPosition() {
        return locationRackPosition;
    }

    public void setLocationRackPosition(String locationRackPosition) {
        this.locationRackPosition = locationRackPosition;
    }

    public Integer getLocationRackSize() {
        return locationRackSize;
    }

    public void setLocationRackSize(Integer locationRackSize) {
        this.locationRackSize = locationRackSize;
    }

    public Float getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(Float locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public Float getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(Float locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getVmServerName() {
        return vmServerName;
    }

    public void setVmServerName(String vmServerName) {
        this.vmServerName = vmServerName;
    }

    public Integer getVmSystemId() {
        return vmSystemId;
    }

    public void setVmSystemId(Integer vmSystemId) {
        this.vmSystemId = vmSystemId;
    }

    public String getVmGroup() {
        return vmGroup;
    }

    public void setVmGroup(String vmGroup) {
        this.vmGroup = vmGroup;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getClusterType() {
        return clusterType;
    }

    public void setClusterType(String clusterType) {
        this.clusterType = clusterType;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPurchaseInvoice() {
        return purchaseInvoice;
    }

    public void setPurchaseInvoice(String purchaseInvoice) {
        this.purchaseInvoice = purchaseInvoice;
    }

    public String getPurchaseOrderNumber() {
        return purchaseOrderNumber;
    }

    public void setPurchaseOrderNumber(String purchaseOrderNumber) {
        this.purchaseOrderNumber = purchaseOrderNumber;
    }

    public String getPurchaseCostCenter() {
        return purchaseCostCenter;
    }

    public void setPurchaseCostCenter(String purchaseCostCenter) {
        this.purchaseCostCenter = purchaseCostCenter;
    }

    public String getPurchaseVendor() {
        return purchaseVendor;
    }

    public void setPurchaseVendor(String purchaseVendor) {
        this.purchaseVendor = purchaseVendor;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseServiceContractNumber() {
        return purchaseServiceContractNumber;
    }

    public void setPurchaseServiceContractNumber(String purchaseServiceContractNumber) {
        this.purchaseServiceContractNumber = purchaseServiceContractNumber;
    }

    public Date getLeaseExpiryDate() {
        return leaseExpiryDate;
    }

    public void setLeaseExpiryDate(Date leaseExpiryDate) {
        this.leaseExpiryDate = leaseExpiryDate;
    }

    public String getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(String purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public Integer getWarrantyDuration() {
        return warrantyDuration;
    }

    public void setWarrantyDuration(Integer warrantyDuration) {
        this.warrantyDuration = warrantyDuration;
    }

    public Date getWarrantyExpires() {
        return warrantyExpires;
    }

    public void setWarrantyExpires(Date warrantyExpires) {
        this.warrantyExpires = warrantyExpires;
    }

    public String getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
    }

    public Date getEndOfLife() {
        return endOfLife;
    }

    public void setEndOfLife(Date endOfLife) {
        this.endOfLife = endOfLife;
    }

    public Date getEndOfService() {
        return endOfService;
    }

    public void setEndOfService(Date endOfService) {
        this.endOfService = endOfService;
    }

    public Integer getSwitchSystemId() {
        return switchSystemId;
    }

    public void setSwitchSystemId(Integer switchSystemId) {
        this.switchSystemId = switchSystemId;
    }

    public Integer getSwitchPort() {
        return switchPort;
    }

    public void setSwitchPort(Integer switchPort) {
        this.switchPort = switchPort;
    }

    public String getPatchPanel() {
        return patchPanel;
    }

    public void setPatchPanel(String patchPanel) {
        this.patchPanel = patchPanel;
    }

    public Integer getPatchPanelPort() {
        return patchPanelPort;
    }

    public void setPatchPanelPort(Integer patchPanelPort) {
        this.patchPanelPort = patchPanelPort;
    }

    public String getWallPort() {
        return wallPort;
    }

    public void setWallPort(String wallPort) {
        this.wallPort = wallPort;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getServiceNumber() {
        return serviceNumber;
    }

    public void setServiceNumber(String serviceNumber) {
        this.serviceNumber = serviceNumber;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServicePlan() {
        return servicePlan;
    }

    public void setServicePlan(String servicePlan) {
        this.servicePlan = servicePlan;
    }

    public String getServiceNetwork() {
        return serviceNetwork;
    }

    public void setServiceNetwork(String serviceNetwork) {
        this.serviceNetwork = serviceNetwork;
    }

    public String getUnlockPin() {
        return unlockPin;
    }

    public void setUnlockPin(String unlockPin) {
        this.unlockPin = unlockPin;
    }

    public String getSerialImei() {
        return serialImei;
    }

    public void setSerialImei(String serialImei) {
        this.serialImei = serialImei;
    }

    public String getSerialSim() {
        return serialSim;
    }

    public void setSerialSim(String serialSim) {
        this.serialSim = serialSim;
    }

    public String getNmisGroup() {
        return nmisGroup;
    }

    public void setNmisGroup(String nmisGroup) {
        this.nmisGroup = nmisGroup;
    }

    public String getNmisName() {
        return nmisName;
    }

    public void setNmisName(String nmisName) {
        this.nmisName = nmisName;
    }

    public String getNmisRole() {
        return nmisRole;
    }

    public void setNmisRole(String nmisRole) {
        this.nmisRole = nmisRole;
    }

    public String getNmisManage() {
        return nmisManage;
    }

    public void setNmisManage(String nmisManage) {
        this.nmisManage = nmisManage;
    }

    public String getNmisNotes() {
        return nmisNotes;
    }

    public void setNmisNotes(String nmisNotes) {
        this.nmisNotes = nmisNotes;
    }

    public String getNmisBusinessService() {
        return nmisBusinessService;
    }

    public void setNmisBusinessService(String nmisBusinessService) {
        this.nmisBusinessService = nmisBusinessService;
    }

    public String getOaeManage() {
        return oaeManage;
    }

    public void setOaeManage(String oaeManage) {
        this.oaeManage = oaeManage;
    }

    public String getSnmpOid() {
        return snmpOid;
    }

    public void setSnmpOid(String snmpOid) {
        this.snmpOid = snmpOid;
    }

    public String getSysDescr() {
        return sysDescr;
    }

    public void setSysDescr(String sysDescr) {
        this.sysDescr = sysDescr;
    }

    public String getSysObjectID() {
        return sysObjectID;
    }

    public void setSysObjectID(String sysObjectID) {
        this.sysObjectID = sysObjectID;
    }

    public String getSysUpTime() {
        return sysUpTime;
    }

    public void setSysUpTime(String sysUpTime) {
        this.sysUpTime = sysUpTime;
    }

    public String getSysContact() {
        return sysContact;
    }

    public void setSysContact(String sysContact) {
        this.sysContact = sysContact;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysLocation() {
        return sysLocation;
    }

    public void setSysLocation(String sysLocation) {
        this.sysLocation = sysLocation;
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

    public String getLastSeenBy() {
        return lastSeenBy;
    }

    public void setLastSeenBy(String lastSeenBy) {
        this.lastSeenBy = lastSeenBy;
    }

    public String getLastUser() {
        return lastUser;
    }

    public void setLastUser(String lastUser) {
        this.lastUser = lastUser;
    }

    public String getOmkUuid() {
        return omkUuid;
    }

    public void setOmkUuid(String omkUuid) {
        this.omkUuid = omkUuid;
    }

    public String getCollectorUuid() {
        return collectorUuid;
    }

    public void setCollectorUuid(String collectorUuid) {
        this.collectorUuid = collectorUuid;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public Integer getCloudId() {
        return cloudId;
    }

    public void setCloudId(Integer cloudId) {
        this.cloudId = cloudId;
    }

    public String getInstanceProvider() {
        return instanceProvider;
    }

    public void setInstanceProvider(String instanceProvider) {
        this.instanceProvider = instanceProvider;
    }

    public String getInstanceIdent() {
        return instanceIdent;
    }

    public void setInstanceIdent(String instanceIdent) {
        this.instanceIdent = instanceIdent;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(String instanceType) {
        this.instanceType = instanceType;
    }

    public String getInstanceState() {
        return instanceState;
    }

    public void setInstanceState(String instanceState) {
        this.instanceState = instanceState;
    }

    public String getInstanceReservationIdent() {
        return instanceReservationIdent;
    }

    public void setInstanceReservationIdent(String instanceReservationIdent) {
        this.instanceReservationIdent = instanceReservationIdent;
    }

    public String getInstanceTags() {
        return instanceTags;
    }

    public void setInstanceTags(String instanceTags) {
        this.instanceTags = instanceTags;
    }

    public String getInstanceOptions() {
        return instanceOptions;
    }

    public void setInstanceOptions(String instanceOptions) {
        this.instanceOptions = instanceOptions;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public void setDiscoveryId(Integer discoveryId) {
        this.discoveryId = discoveryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        System system = (System) o;
        return Objects.equals(id, system.id) &&
            Objects.equals(uuid, system.uuid) &&
            Objects.equals(name, system.name) &&
            Objects.equals(ip, system.ip) &&
            Objects.equals(hostname, system.hostname) &&
            Objects.equals(dnsHostname, system.dnsHostname) &&
            Objects.equals(domain, system.domain) &&
            Objects.equals(dnsDomain, system.dnsDomain) &&
            Objects.equals(dbusIdentifier, system.dbusIdentifier) &&
            Objects.equals(fqdn, system.fqdn) &&
            Objects.equals(description, system.description) &&
            Objects.equals(type, system.type) &&
            Objects.equals(comments, system.comments) &&
            Objects.equals(icon, system.icon) &&
            Objects.equals(osGroup, system.osGroup) &&
            Objects.equals(osFamily, system.osFamily) &&
            Objects.equals(osName, system.osName) &&
            Objects.equals(osVersion, system.osVersion) &&
            Objects.equals(attachedSystemId, system.attachedSystemId) &&
            Objects.equals(manufacturer, system.manufacturer) &&
            Objects.equals(model, system.model) &&
            Objects.equals(serial, system.serial) &&
            Objects.equals(uptime, system.uptime) &&
            Objects.equals(formFactor, system.formFactor) &&
            Objects.equals(osBit, system.osBit) &&
            Objects.equals(memoryCount, system.memoryCount) &&
            Objects.equals(processorCount, system.processorCount) &&
            Objects.equals(storageCount, system.storageCount) &&
            Objects.equals(osInstallationDate, system.osInstallationDate) &&
            Objects.equals(printerPortName, system.printerPortName) &&
            Objects.equals(printerShared, system.printerShared) &&
            Objects.equals(printerSharedName, system.printerSharedName) &&
            Objects.equals(printerColor, system.printerColor) &&
            Objects.equals(printerDuplex, system.printerDuplex) &&
            Objects.equals(status, system.status) &&
            Objects.equals(environment, system.environment) &&
            Objects.equals(function, system.function) &&
            Objects.equals(owner, system.owner) &&
            Objects.equals(orgId, system.orgId) &&
            Objects.equals(locationId, system.locationId) &&
            Objects.equals(locationLevel, system.locationLevel) &&
            Objects.equals(locationSuite, system.locationSuite) &&
            Objects.equals(locationRoom, system.locationRoom) &&
            Objects.equals(locationRack, system.locationRack) &&
            Objects.equals(locationRackPosition, system.locationRackPosition) &&
            Objects.equals(locationRackSize, system.locationRackSize) &&
            Objects.equals(locationLatitude, system.locationLatitude) &&
            Objects.equals(locationLongitude, system.locationLongitude) &&
            Objects.equals(assetNumber, system.assetNumber) &&
            Objects.equals(assetTag, system.assetTag) &&
            Objects.equals(vmServerName, system.vmServerName) &&
            Objects.equals(vmSystemId, system.vmSystemId) &&
            Objects.equals(vmGroup, system.vmGroup) &&
            Objects.equals(clusterName, system.clusterName) &&
            Objects.equals(clusterType, system.clusterType) &&
            Objects.equals(invoiceId, system.invoiceId) &&
            Objects.equals(purchaseInvoice, system.purchaseInvoice) &&
            Objects.equals(purchaseOrderNumber, system.purchaseOrderNumber) &&
            Objects.equals(purchaseCostCenter, system.purchaseCostCenter) &&
            Objects.equals(purchaseVendor, system.purchaseVendor) &&
            Objects.equals(purchaseDate, system.purchaseDate) &&
            Objects.equals(purchaseServiceContractNumber, system.purchaseServiceContractNumber) &&
            Objects.equals(leaseExpiryDate, system.leaseExpiryDate) &&
            Objects.equals(purchaseAmount, system.purchaseAmount) &&
            Objects.equals(warrantyDuration, system.warrantyDuration) &&
            Objects.equals(warrantyExpires, system.warrantyExpires) &&
            Objects.equals(warrantyType, system.warrantyType) &&
            Objects.equals(endOfLife, system.endOfLife) &&
            Objects.equals(endOfService, system.endOfService) &&
            Objects.equals(switchSystemId, system.switchSystemId) &&
            Objects.equals(switchPort, system.switchPort) &&
            Objects.equals(patchPanel, system.patchPanel) &&
            Objects.equals(patchPanelPort, system.patchPanelPort) &&
            Objects.equals(wallPort, system.wallPort) &&
            Objects.equals(contactName, system.contactName) &&
            Objects.equals(serviceNumber, system.serviceNumber) &&
            Objects.equals(serviceProvider, system.serviceProvider) &&
            Objects.equals(serviceType, system.serviceType) &&
            Objects.equals(servicePlan, system.servicePlan) &&
            Objects.equals(serviceNetwork, system.serviceNetwork) &&
            Objects.equals(unlockPin, system.unlockPin) &&
            Objects.equals(serialImei, system.serialImei) &&
            Objects.equals(serialSim, system.serialSim) &&
            Objects.equals(nmisGroup, system.nmisGroup) &&
            Objects.equals(nmisName, system.nmisName) &&
            Objects.equals(nmisRole, system.nmisRole) &&
            Objects.equals(nmisManage, system.nmisManage) &&
            Objects.equals(nmisNotes, system.nmisNotes) &&
            Objects.equals(nmisBusinessService, system.nmisBusinessService) &&
            Objects.equals(oaeManage, system.oaeManage) &&
            Objects.equals(snmpOid, system.snmpOid) &&
            Objects.equals(sysDescr, system.sysDescr) &&
            Objects.equals(sysObjectID, system.sysObjectID) &&
            Objects.equals(sysUpTime, system.sysUpTime) &&
            Objects.equals(sysContact, system.sysContact) &&
            Objects.equals(sysName, system.sysName) &&
            Objects.equals(sysLocation, system.sysLocation) &&
            Objects.equals(firstSeen, system.firstSeen) &&
            Objects.equals(lastSeen, system.lastSeen) &&
            Objects.equals(lastSeenBy, system.lastSeenBy) &&
            Objects.equals(lastUser, system.lastUser) &&
            Objects.equals(omkUuid, system.omkUuid) &&
            Objects.equals(collectorUuid, system.collectorUuid) &&
            Objects.equals(credentials, system.credentials) &&
            Objects.equals(cloudId, system.cloudId) &&
            Objects.equals(instanceProvider, system.instanceProvider) &&
            Objects.equals(instanceIdent, system.instanceIdent) &&
            Objects.equals(instanceType, system.instanceType) &&
            Objects.equals(instanceState, system.instanceState) &&
            Objects.equals(instanceReservationIdent, system.instanceReservationIdent) &&
            Objects.equals(instanceTags, system.instanceTags) &&
            Objects.equals(instanceOptions, system.instanceOptions) &&
            Objects.equals(discoveryId, system.discoveryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, name, ip, hostname, dnsHostname, domain, dnsDomain, dbusIdentifier, fqdn, description, type, comments, icon, osGroup, osFamily, osName, osVersion, attachedSystemId, manufacturer, model, serial, uptime, formFactor, osBit, memoryCount, processorCount, storageCount, osInstallationDate, printerPortName, printerShared, printerSharedName, printerColor, printerDuplex, status, environment, function, owner, orgId, locationId, locationLevel, locationSuite, locationRoom, locationRack, locationRackPosition, locationRackSize, locationLatitude, locationLongitude, assetNumber, assetTag, vmServerName, vmSystemId, vmGroup, clusterName, clusterType, invoiceId, purchaseInvoice, purchaseOrderNumber, purchaseCostCenter, purchaseVendor, purchaseDate, purchaseServiceContractNumber, leaseExpiryDate, purchaseAmount, warrantyDuration, warrantyExpires, warrantyType, endOfLife, endOfService, switchSystemId, switchPort, patchPanel, patchPanelPort, wallPort, contactName, serviceNumber, serviceProvider, serviceType, servicePlan, serviceNetwork, unlockPin, serialImei, serialSim, nmisGroup, nmisName, nmisRole, nmisManage, nmisNotes, nmisBusinessService, oaeManage, snmpOid, sysDescr, sysObjectID, sysUpTime, sysContact, sysName, sysLocation, firstSeen, lastSeen, lastSeenBy, lastUser, omkUuid, collectorUuid, credentials, cloudId, instanceProvider, instanceIdent, instanceType, instanceState, instanceReservationIdent, instanceTags, instanceOptions, discoveryId);
    }

    @Override
    public String toString() {
        return "System{" +
            "id=" + id +
            ", uuid='" + uuid + '\'' +
            ", name='" + name + '\'' +
            ", ip='" + ip + '\'' +
            ", hostname='" + hostname + '\'' +
            ", dnsHostname='" + dnsHostname + '\'' +
            ", domain='" + domain + '\'' +
            ", dnsDomain='" + dnsDomain + '\'' +
            ", dbusIdentifier='" + dbusIdentifier + '\'' +
            ", fqdn='" + fqdn + '\'' +
            ", description='" + description + '\'' +
            ", type='" + type + '\'' +
            ", comments='" + comments + '\'' +
            ", icon='" + icon + '\'' +
            ", osGroup='" + osGroup + '\'' +
            ", osFamily='" + osFamily + '\'' +
            ", osName='" + osName + '\'' +
            ", osVersion='" + osVersion + '\'' +
            ", attachedSystemId=" + attachedSystemId +
            ", manufacturer='" + manufacturer + '\'' +
            ", model='" + model + '\'' +
            ", serial='" + serial + '\'' +
            ", uptime='" + uptime + '\'' +
            ", formFactor='" + formFactor + '\'' +
            ", osBit=" + osBit +
            ", memoryCount=" + memoryCount +
            ", processorCount=" + processorCount +
            ", storageCount=" + storageCount +
            ", osInstallationDate=" + osInstallationDate +
            ", printerPortName='" + printerPortName + '\'' +
            ", printerShared='" + printerShared + '\'' +
            ", printerSharedName='" + printerSharedName + '\'' +
            ", printerColor='" + printerColor + '\'' +
            ", printerDuplex='" + printerDuplex + '\'' +
            ", status='" + status + '\'' +
            ", environment='" + environment + '\'' +
            ", function='" + function + '\'' +
            ", owner='" + owner + '\'' +
            ", orgId=" + orgId +
            ", locationId=" + locationId +
            ", locationLevel='" + locationLevel + '\'' +
            ", locationSuite='" + locationSuite + '\'' +
            ", locationRoom='" + locationRoom + '\'' +
            ", locationRack='" + locationRack + '\'' +
            ", locationRackPosition='" + locationRackPosition + '\'' +
            ", locationRackSize=" + locationRackSize +
            ", locationLatitude=" + locationLatitude +
            ", locationLongitude=" + locationLongitude +
            ", assetNumber='" + assetNumber + '\'' +
            ", assetTag='" + assetTag + '\'' +
            ", vmServerName='" + vmServerName + '\'' +
            ", vmSystemId=" + vmSystemId +
            ", vmGroup='" + vmGroup + '\'' +
            ", clusterName='" + clusterName + '\'' +
            ", clusterType='" + clusterType + '\'' +
            ", invoiceId=" + invoiceId +
            ", purchaseInvoice='" + purchaseInvoice + '\'' +
            ", purchaseOrderNumber='" + purchaseOrderNumber + '\'' +
            ", purchaseCostCenter='" + purchaseCostCenter + '\'' +
            ", purchaseVendor='" + purchaseVendor + '\'' +
            ", purchaseDate=" + purchaseDate +
            ", purchaseServiceContractNumber='" + purchaseServiceContractNumber + '\'' +
            ", leaseExpiryDate=" + leaseExpiryDate +
            ", purchaseAmount='" + purchaseAmount + '\'' +
            ", warrantyDuration=" + warrantyDuration +
            ", warrantyExpires=" + warrantyExpires +
            ", warrantyType='" + warrantyType + '\'' +
            ", endOfLife=" + endOfLife +
            ", endOfService=" + endOfService +
            ", switchSystemId=" + switchSystemId +
            ", switchPort=" + switchPort +
            ", patchPanel='" + patchPanel + '\'' +
            ", patchPanelPort=" + patchPanelPort +
            ", wallPort='" + wallPort + '\'' +
            ", contactName='" + contactName + '\'' +
            ", serviceNumber='" + serviceNumber + '\'' +
            ", serviceProvider='" + serviceProvider + '\'' +
            ", serviceType='" + serviceType + '\'' +
            ", servicePlan='" + servicePlan + '\'' +
            ", serviceNetwork='" + serviceNetwork + '\'' +
            ", unlockPin='" + unlockPin + '\'' +
            ", serialImei='" + serialImei + '\'' +
            ", serialSim='" + serialSim + '\'' +
            ", nmisGroup='" + nmisGroup + '\'' +
            ", nmisName='" + nmisName + '\'' +
            ", nmisRole='" + nmisRole + '\'' +
            ", nmisManage='" + nmisManage + '\'' +
            ", nmisNotes='" + nmisNotes + '\'' +
            ", nmisBusinessService='" + nmisBusinessService + '\'' +
            ", oaeManage='" + oaeManage + '\'' +
            ", snmpOid='" + snmpOid + '\'' +
            ", sysDescr='" + sysDescr + '\'' +
            ", sysObjectID='" + sysObjectID + '\'' +
            ", sysUpTime='" + sysUpTime + '\'' +
            ", sysContact='" + sysContact + '\'' +
            ", sysName='" + sysName + '\'' +
            ", sysLocation='" + sysLocation + '\'' +
            ", firstSeen=" + firstSeen +
            ", lastSeen=" + lastSeen +
            ", lastSeenBy='" + lastSeenBy + '\'' +
            ", lastUser='" + lastUser + '\'' +
            ", omkUuid='" + omkUuid + '\'' +
            ", collectorUuid='" + collectorUuid + '\'' +
            ", credentials='" + credentials + '\'' +
            ", cloudId=" + cloudId +
            ", instanceProvider='" + instanceProvider + '\'' +
            ", instanceIdent='" + instanceIdent + '\'' +
            ", instanceType='" + instanceType + '\'' +
            ", instanceState='" + instanceState + '\'' +
            ", instanceReservationIdent='" + instanceReservationIdent + '\'' +
            ", instanceTags='" + instanceTags + '\'' +
            ", instanceOptions='" + instanceOptions + '\'' +
            ", discoveryId=" + discoveryId +
            '}';
    }
}

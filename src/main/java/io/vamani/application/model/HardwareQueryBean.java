package io.vamani.application.model;

import java.io.Serializable;

/**
 * A HardwareQuery.
 */
public class HardwareQueryBean implements Serializable {
    private String assetCode;
    private String ipAddress;
    private String hardDisk;
    private String memory;
    private String osName;
	public String getAssetCode() {
		return assetCode;
	}
	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getHardDisk() {
		return hardDisk;
	}
	public void setHardDisk(String hardDisk) {
		this.hardDisk = hardDisk;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getOsName() {
		return osName;
	}
	public void setOsName(String osName) {
		this.osName = osName;
	}
    
    
}

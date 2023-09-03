package io.vamani.application.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "stitchapi", ignoreUnknownFields = false)
public class StitchApiProperties {
	private String apiUrl;
	private String stickKey;
	private String base64EncodedSignature;
	private String contentType;
	private String stitchCron;
	
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	public String getStickKey() {
		return stickKey;
	}
	public void setStickKey(String stickKey) {
		this.stickKey = stickKey;
	}
	public String getBase64EncodedSignature() {
		return base64EncodedSignature;
	}
	public void setBase64EncodedSignature(String base64EncodedSignature) {
		this.base64EncodedSignature = base64EncodedSignature;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getStitchCron() {
		return stitchCron;
	}
	public void setStitchCron(String stitchCron) {
		this.stitchCron = stitchCron;
	}
	
}

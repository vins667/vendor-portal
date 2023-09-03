package io.vamani.application.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Vamaniportal.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {
    private String uploadPath;
    private String url;
    private String vendorPortalUrl;
    private String contentPath;
    private String templatePath;
    private String imagePath;
    private String directBillRoundGL;
    private String directBillRoundValue;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVendorPortalUrl() {
        return vendorPortalUrl;
    }

    public void setVendorPortalUrl(String vendorPortalUrl) {
        this.vendorPortalUrl = vendorPortalUrl;
    }

    public String getContentPath() {
        return contentPath;
    }

    public void setContentPath(String contentPath) {
        this.contentPath = contentPath;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDirectBillRoundGL() {
        return directBillRoundGL;
    }

    public void setDirectBillRoundGL(String directBillRoundGL) {
        this.directBillRoundGL = directBillRoundGL;
    }

    public String getDirectBillRoundValue() {
        return directBillRoundValue;
    }

    public void setDirectBillRoundValue(String directBillRoundValue) {
        this.directBillRoundValue = directBillRoundValue;
    }
}

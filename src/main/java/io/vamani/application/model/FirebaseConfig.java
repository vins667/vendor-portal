package io.vamani.application.model;

public class FirebaseConfig {
    private String url;
    private String key;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FirebaseConfig(String url, String key) {
        this.url = url;
        this.key = key;
    }

    @Override
    public String toString() {
        return "FirebaseConfig{" +
            "url='" + url + '\'' +
            ", key='" + key + '\'' +
            '}';
    }
}

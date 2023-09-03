package io.vamani.application.model;

public class CutPlanBundleMatrixBreakup {
    private String bundle;
    private Double bundlePcs;
    private String startSequence;
    private String endSequence;

    public String getBundle() {
        return bundle;
    }

    public void setBundle(String bundle) {
        this.bundle = bundle;
    }

    public Double getBundlePcs() {
        return bundlePcs;
    }

    public void setBundlePcs(Double bundlePcs) {
        this.bundlePcs = bundlePcs;
    }

    public String getStartSequence() {
        return startSequence;
    }

    public void setStartSequence(String startSequence) {
        this.startSequence = startSequence;
    }

    public String getEndSequence() {
        return endSequence;
    }

    public void setEndSequence(String endSequence) {
        this.endSequence = endSequence;
    }
}

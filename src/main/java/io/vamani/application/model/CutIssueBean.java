package io.vamani.application.model;

import java.io.Serializable;
import java.util.List;

public class CutIssueBean implements Serializable {
    private String style;
    private List<Master> colors;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<Master> getColors() {
        return colors;
    }

    public void setColors(List<Master> colors) {
        this.colors = colors;
    }
}

package io.vamani.application.model;

import java.util.List;

public class TreeViewItem {
    private String text;
    private Long value;
    private Boolean checked;
    private List<TreeViewItem> children;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Boolean isChecked() {
        return checked;
    }

    public void getChecked(Boolean checked) {
        this.checked = checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<TreeViewItem> getChildren() {
        return children;
    }

    public void setChildren(List<TreeViewItem> children) {
        this.children = children;
    }
}

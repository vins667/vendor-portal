package io.vamani.application.model;

import java.util.List;

public class MenuSave {
    private String cardNo;
    private List<Long> menus;

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public List<Long> getMenus() {
        return menus;
    }

    public void setMenus(List<Long> menus) {
        this.menus = menus;
    }
}

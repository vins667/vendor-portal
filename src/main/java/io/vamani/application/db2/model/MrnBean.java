package io.vamani.application.db2.model;

import java.io.Serializable;

public class MrnBean implements Serializable {
    private String tariffcode;
    private String itemtypeaficode;
    private String itemcode;
    private String itemdescription;
    private String uom;

    public String getTariffcode() {
        return tariffcode;
    }

    public void setTariffcode(String tariffcode) {
        this.tariffcode = tariffcode;
    }

    public String getItemtypeaficode() {
        return itemtypeaficode;
    }

    public void setItemtypeaficode(String itemtypeaficode) {
        this.itemtypeaficode = itemtypeaficode;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemdescription() {
        return itemdescription;
    }

    public void setItemdescription(String itemdescription) {
        this.itemdescription = itemdescription;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }
}

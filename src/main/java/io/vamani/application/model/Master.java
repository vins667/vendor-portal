package io.vamani.application.model;

import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.Objects;

public class Master implements Serializable {
    private String id;
    private String desc;
    private String name;
    private String code;
    private String btnType;
    private String plantCode;
    private String destination;
    private String destinationDesc;
    private String buyer;
    private String buyerName;
    private String itemType;
    private String itemName;
    private Long planId;
    private Long key;
    private Pageable page;
    private int size;
    private int pageNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBtnType() {
        return btnType;
    }

    public void setBtnType(String btnType) {
        this.btnType = btnType;
    }

    public String getPlantCode() {
        return plantCode;
    }

    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationDesc() {
        return destinationDesc;
    }

    public void setDestinationDesc(String destinationDesc) {
        this.destinationDesc = destinationDesc;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Pageable getPage() {
        return page;
    }

    public void setPage(Pageable page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Master() {
    }

    public Master(String desc) {
        this.desc = desc;
    }

    public Master(String id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Master master = (Master) o;
        return size == master.size && pageNo == master.pageNo && Objects.equals(id, master.id) && Objects.equals(desc, master.desc) && Objects.equals(name, master.name) && Objects.equals(code, master.code) && Objects.equals(btnType, master.btnType) && Objects.equals(plantCode, master.plantCode) && Objects.equals(destination, master.destination) && Objects.equals(destinationDesc, master.destinationDesc) && Objects.equals(buyer, master.buyer) && Objects.equals(buyerName, master.buyerName) && Objects.equals(itemType, master.itemType) && Objects.equals(itemName, master.itemName) && Objects.equals(key, master.key) && Objects.equals(page, master.page);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, desc, name, code, btnType, plantCode, destination, destinationDesc, buyer, buyerName, itemType, itemName, key, page, size, pageNo);
    }
}

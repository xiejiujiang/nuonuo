package com.example.nuonuo.entity.hongren.chukufahuomix;

public class OrderLine {
    private String orderLineNo;
    private String sourceOrderCode;
    private String subSourceOrderCode;
    private String ownerCode;
    private String itemCode;
    private String itemId;
    private String inventoryType;
    private String itemName;
    private int planQty;
    private double retailPrice;
    private double actualPrice;
    private String productDate;
    private String expireDate;
    private ExtendProps extendProps;

    public String getOrderLineNo() {
        return orderLineNo;
    }

    public void setOrderLineNo(String orderLineNo) {
        this.orderLineNo = orderLineNo;
    }

    public String getSourceOrderCode() {
        return sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
        this.sourceOrderCode = sourceOrderCode;
    }

    public String getSubSourceOrderCode() {
        return subSourceOrderCode;
    }

    public void setSubSourceOrderCode(String subSourceOrderCode) {
        this.subSourceOrderCode = subSourceOrderCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPlanQty() {
        return planQty;
    }

    public void setPlanQty(int planQty) {
        this.planQty = planQty;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getProductDate() {
        return productDate;
    }

    public void setProductDate(String productDate) {
        this.productDate = productDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public ExtendProps getExtendProps() {
        return extendProps;
    }

    public void setExtendProps(ExtendProps extendProps) {
        this.extendProps = extendProps;
    }
}

package com.example.nuonuo.entity.Meituan.PeisongFaHuo;

public class DeliveryItemDetailDTO {
    private String rowId;
    private String sourceRowId;
    private String goodsId;
    private String unitId;
    private String goodsVersion;
    private String warehouseId;
    private String deliveryAmount;
    private String batchNum;
    private String remark;
    private int giftType;
    private Boolean isAdditional;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getSourceRowId() {
        return sourceRowId;
    }

    public void setSourceRowId(String sourceRowId) {
        this.sourceRowId = sourceRowId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getGoodsVersion() {
        return goodsVersion;
    }

    public void setGoodsVersion(String goodsVersion) {
        this.goodsVersion = goodsVersion;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(String deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGiftType() {
        return giftType;
    }

    public void setGiftType(int giftType) {
        this.giftType = giftType;
    }

    public Boolean getAdditional() {
        return isAdditional;
    }

    public void setAdditional(Boolean additional) {
        isAdditional = additional;
    }
}

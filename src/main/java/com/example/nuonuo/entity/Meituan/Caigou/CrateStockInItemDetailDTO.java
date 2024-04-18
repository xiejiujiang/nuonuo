package com.example.nuonuo.entity.Meituan.Caigou;

public class CrateStockInItemDetailDTO {
    private int seqId;
    private String goodsId;
    private String goodsCode;
    private String warehouseId;
    private String warehouseCode;
    private String bizUnitId;
    private String bizUnitCode;
    private String bizUnitAmount;
    private String batchNum;
    private String productionDate;
    private String money;
    private String taxRate;
    private String bizUnitPrice;
    private String giftTag;
    private String remark;

    public int getSeqId() {
        return seqId;
    }

    public void setSeqId(int seqId) {
        this.seqId = seqId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getBizUnitId() {
        return bizUnitId;
    }

    public void setBizUnitId(String bizUnitId) {
        this.bizUnitId = bizUnitId;
    }

    public String getBizUnitCode() {
        return bizUnitCode;
    }

    public void setBizUnitCode(String bizUnitCode) {
        this.bizUnitCode = bizUnitCode;
    }

    public String getBizUnitAmount() {
        return bizUnitAmount;
    }

    public void setBizUnitAmount(String bizUnitAmount) {
        this.bizUnitAmount = bizUnitAmount;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getBizUnitPrice() {
        return bizUnitPrice;
    }

    public void setBizUnitPrice(String bizUnitPrice) {
        this.bizUnitPrice = bizUnitPrice;
    }

    public String getGiftTag() {
        return giftTag;
    }

    public void setGiftTag(String giftTag) {
        this.giftTag = giftTag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "CrateStockInItemDetailDTO{" +
                "seqId=" + seqId +
                ", goodsId='" + goodsId + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", warehouseId='" + warehouseId + '\'' +
                ", warehouseCode='" + warehouseCode + '\'' +
                ", bizUnitId='" + bizUnitId + '\'' +
                ", bizUnitCode='" + bizUnitCode + '\'' +
                ", bizUnitAmount='" + bizUnitAmount + '\'' +
                ", batchNum='" + batchNum + '\'' +
                ", productionDate='" + productionDate + '\'' +
                ", money='" + money + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", bizUnitPrice='" + bizUnitPrice + '\'' +
                ", giftTag='" + giftTag + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

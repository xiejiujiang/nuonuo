package com.example.nuonuo.entity.Meituan.Chaidan;

public class SpiltDeliveryOrderDetailDTO {
    private String id;
    private String goodsId;
    private String splitAmount;
    private boolean isSplitAll;
    private String splitRemark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSplitAmount() {
        return splitAmount;
    }

    public void setSplitAmount(String splitAmount) {
        this.splitAmount = splitAmount;
    }

    public boolean isSplitAll() {
        return isSplitAll;
    }

    public void setSplitAll(boolean splitAll) {
        isSplitAll = splitAll;
    }

    public String getSplitRemark() {
        return splitRemark;
    }

    public void setSplitRemark(String splitRemark) {
        this.splitRemark = splitRemark;
    }
}

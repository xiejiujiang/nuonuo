package com.example.nuonuo.entity.mida.chukureturn;

import com.example.nuonuo.entity.mida.dddetail.OrderItemBatchBo;

import java.util.List;

public class MobileEcOrderItemBo {
    private String orderItemId;
    private String skuId;
    private String itemCode;
    private String skuName;
    private String unitName;
    private int qty;
    private int orderQty;
    private String actualPriceSubTotal;
    private List<OrderItemBatchBo> batchs;

    public String getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public String getActualPriceSubTotal() {
        return actualPriceSubTotal;
    }

    public void setActualPriceSubTotal(String actualPriceSubTotal) {
        this.actualPriceSubTotal = actualPriceSubTotal;
    }

    public List<OrderItemBatchBo> getBatchs() {
        return batchs;
    }

    public void setBatchs(List<OrderItemBatchBo> batchs) {
        this.batchs = batchs;
    }

    @Override
    public String toString() {
        return "MobileEcOrderItemBo{" +
                "orderItemId='" + orderItemId + '\'' +
                ", skuId='" + skuId + '\'' +
                ", skuName='" + skuName + '\'' +
                ", unitName='" + unitName + '\'' +
                ", qty=" + qty +
                ", orderQty=" + orderQty +
                ", actualPriceSubTotal='" + actualPriceSubTotal + '\'' +
                ", batchs=" + batchs +
                '}';
    }
}

package com.example.nuonuo.entity.mida.rukureturn;

import com.example.nuonuo.entity.mida.batchcukundtail.WarehouseStockBatchBo;

import java.util.List;

public class WarehouseStockBo {
    private String stockId;
    private int availableQty;
    private String inboundQty;//本次入库数量
    private String orderReservedQty;
    private String transReservedQty;
    private String notForSaleReservedQty;
    private String warehouseCode;
    private String skuId;
    private String skuName;
    private String status;
    private List<WarehouseStockBatchBo> batchList;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public int getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(int availableQty) {
        this.availableQty = availableQty;
    }

    public String getInboundQty() {
        return inboundQty;
    }

    public void setInboundQty(String inboundQty) {
        this.inboundQty = inboundQty;
    }

    public String getOrderReservedQty() {
        return orderReservedQty;
    }

    public void setOrderReservedQty(String orderReservedQty) {
        this.orderReservedQty = orderReservedQty;
    }

    public String getTransReservedQty() {
        return transReservedQty;
    }

    public void setTransReservedQty(String transReservedQty) {
        this.transReservedQty = transReservedQty;
    }

    public String getNotForSaleReservedQty() {
        return notForSaleReservedQty;
    }

    public void setNotForSaleReservedQty(String notForSaleReservedQty) {
        this.notForSaleReservedQty = notForSaleReservedQty;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<WarehouseStockBatchBo> getBatchList() {
        return batchList;
    }

    public void setBatchList(List<WarehouseStockBatchBo> batchList) {
        this.batchList = batchList;
    }
}

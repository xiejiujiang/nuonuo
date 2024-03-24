package com.example.nuonuo.entity.mida.batchcukundtail;

public class BatchSkuCukun {
    /**
     * 可销售库存
     */
    private long availableQty;
    private WarehouseStockBatchBo[] batchList;
    /**
     * 不可销售锁定量
     */
    private String notForSaleReservedQty;
    /**
     * 订单锁定量
     */
    private String orderReservedQty;
    /**
     * skuid
     */
    private String skuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 商品状态，OFF_SHELF:下架;TMP_OFF_SHELF:临时下架;ON_SHELF:上架;HIDE_SHELF:隐藏
     */
    private String status;
    /**
     * 库存id
     */
    private String stockId;
    /**
     * 调拨锁定量
     */
    private String transReservedQty;
    /**
     * 存放仓库
     */
    private String warehouseCode;

    public long getAvailableQty() { return availableQty; }
    public void setAvailableQty(long value) { this.availableQty = value; }

    public WarehouseStockBatchBo[] getBatchList() { return batchList; }
    public void setBatchList(WarehouseStockBatchBo[] value) { this.batchList = value; }

    public String getNotForSaleReservedQty() { return notForSaleReservedQty; }
    public void setNotForSaleReservedQty(String value) { this.notForSaleReservedQty = value; }

    public String getOrderReservedQty() { return orderReservedQty; }
    public void setOrderReservedQty(String value) { this.orderReservedQty = value; }

    public String getSkuId() { return skuId; }
    public void setSkuId(String value) { this.skuId = value; }

    public String getSkuName() { return skuName; }
    public void setSkuName(String value) { this.skuName = value; }

    public String getStatus() { return status; }
    public void setStatus(String value) { this.status = value; }

    public String getStockId() { return stockId; }
    public void setStockId(String value) { this.stockId = value; }

    public String getTransReservedQty() { return transReservedQty; }
    public void setTransReservedQty(String value) { this.transReservedQty = value; }

    public String getWarehouseCode() { return warehouseCode; }
    public void setWarehouseCode(String value) { this.warehouseCode = value; }
}
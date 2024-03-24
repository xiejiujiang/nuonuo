package com.example.nuonuo.entity.mida.batchcukundtail;

public class WarehouseStockBatchBo {
    /**
     * 可用库存
     */
    private String availableQty;
    /**
     * 批次id
     */
    private String id;
    /**
     * 入库时间
     */
    private String inboundAt;
    /**
     * 生产日期
     */
    private String producedAt;
    /**
     * 保质期
     */
    private String shelfLifeDays;

    public String getAvailableQty() { return availableQty; }
    public void setAvailableQty(String value) { this.availableQty = value; }

    public String getid() { return id; }
    public void setid(String value) { this.id = value; }

    public String getInboundAt() { return inboundAt; }
    public void setInboundAt(String value) { this.inboundAt = value; }

    public String getProducedAt() { return producedAt; }
    public void setProducedAt(String value) { this.producedAt = value; }

    public String getShelfLifeDays() { return shelfLifeDays; }
    public void setShelfLifeDays(String value) { this.shelfLifeDays = value; }
}

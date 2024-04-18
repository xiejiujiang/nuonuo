package com.example.nuonuo.entity.mida.dddetail;

public class OrderItemBatchBo {
    /**
     * 过期时间
     */
    private String expiredAt;
    /**
     * 生产日期
     */
    private String producedAt;
    /**
     * 实发数量
     */
    private String qty;
    /**
     * 保质期天数
     */
    private String shelfLifeDays;
    /**
     * 批次id
     */
    private String stockBatchId;

    public String getExpiredAt() { return expiredAt; }
    public void setExpiredAt(String value) { this.expiredAt = value; }

    public String getProducedAt() { return producedAt; }
    public void setProducedAt(String value) { this.producedAt = value; }

    public String getQty() { return qty; }
    public void setQty(String value) { this.qty = value; }

    public String getShelfLifeDays() { return shelfLifeDays; }
    public void setShelfLifeDays(String value) { this.shelfLifeDays = value; }

    public String getStockBatchId() { return stockBatchId; }
    public void setStockBatchId(String value) { this.stockBatchId = value; }

    @Override
    public String toString() {
        return "OrderItemBatchBo{" +
                "expiredAt='" + expiredAt + '\'' +
                ", producedAt='" + producedAt + '\'' +
                ", qty='" + qty + '\'' +
                ", shelfLifeDays='" + shelfLifeDays + '\'' +
                ", stockBatchId='" + stockBatchId + '\'' +
                '}';
    }
}

package com.example.nuonuo.entity.hongren.rukuqr;

public class EntryOrder {
    private String entryOrderCode;//入库单编码, string (50) , 必填
    private String ownerCode;//货主编码, string (50)
    private String warehouseCode;//仓库编码, string (50)，必填
    private String entryOrderId;//仓储系统入库单 ID, string (50) , 条件必填
    private String entryOrderType;//入库单类型 ，SCRK=生产入库，LYRK=领用入库，CCRK=残次品入库，CGRK=采购入库, DBRK=调拨入库, QTRK=其他入库，B2BRK=B2B 入库
    private String outBizCode;//外部业务编码, 消息 ID, 用于去重, ISV 对于同一请求，分配一个唯一性的编码。用来保证因为网络等原因导致重复传输，请求不会被重复处理, ,必填
    private int confirmType;//支持出入库单多次收货, int，多次收货后确认时，0 表示入库单最终状态确认；1 表示入库单中间状态确认；每次入库传入的数量为增量，特殊情况，同一入库单，如果先收到 0，后又收到 1，允许修改收货的数量。
    private String status;//入库单状态, string (50) , 必填 (NEW-未开始处理, ACCEPT-仓库接单 ,PARTFULFILLED-部分收货完成, FULFILLED-收货完成, EXCEPTION-异常, CANCELED-取消, CLOSED-关闭, REJECT-拒单, CANCELEDFAIL-取消失败) , (只传英文编码)
    private String operateTime;//操作时间, string (19) , YYYY-MM-DD HH:MM:SS，(当status=FULFILLED, operateTime 为实收时间)
    private String remark;//备注, string (500)

    public String getEntryOrderCode() {
        return entryOrderCode;
    }

    public void setEntryOrderCode(String entryOrderCode) {
        this.entryOrderCode = entryOrderCode;
    }

    public String getOwnerCode() {
        return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
        this.ownerCode = ownerCode;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }

    public String getEntryOrderId() {
        return entryOrderId;
    }

    public void setEntryOrderId(String entryOrderId) {
        this.entryOrderId = entryOrderId;
    }

    public String getEntryOrderType() {
        return entryOrderType;
    }

    public void setEntryOrderType(String entryOrderType) {
        this.entryOrderType = entryOrderType;
    }

    public String getOutBizCode() {
        return outBizCode;
    }

    public void setOutBizCode(String outBizCode) {
        this.outBizCode = outBizCode;
    }

    public int getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(int confirmType) {
        this.confirmType = confirmType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

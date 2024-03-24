package com.example.nuonuo.entity.tiancai;

public class TcchukuReturnDetail {

    private String dtId; //单据明细id  必传
    private int outBusAmount;//出库数量  必传
    private String batchCode;//非必传
    private String makeDate;//非必传

    public String getDtId() {
        return dtId;
    }

    public void setDtId(String dtId) {
        this.dtId = dtId;
    }

    public int getOutBusAmount() {
        return outBusAmount;
    }

    public void setOutBusAmount(int outBusAmount) {
        this.outBusAmount = outBusAmount;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public void setBatchCode(String batchCode) {
        this.batchCode = batchCode;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }
}

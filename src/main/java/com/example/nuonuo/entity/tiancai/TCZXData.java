package com.example.nuonuo.entity.tiancai;

public class TCZXData {
    private int EnableFlag;//启用标志：0：停用；1：启用
    private String rdcID;
    private String rdcName;
    private String rdcCode;
    private String Phone;
    private String ADDR;
    private String parentid;
    private String parentCode;
    private String parentName;
    private String modifyDate;

    public int getEnableFlag() {
        return EnableFlag;
    }

    public void setEnableFlag(int enableFlag) {
        EnableFlag = enableFlag;
    }

    public String getRdcID() {
        return rdcID;
    }

    public void setRdcID(String rdcID) {
        this.rdcID = rdcID;
    }

    public String getRdcName() {
        return rdcName;
    }

    public void setRdcName(String rdcName) {
        this.rdcName = rdcName;
    }

    public String getRdcCode() {
        return rdcCode;
    }

    public void setRdcCode(String rdcCode) {
        this.rdcCode = rdcCode;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getADDR() {
        return ADDR;
    }

    public void setADDR(String ADDR) {
        this.ADDR = ADDR;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }
}

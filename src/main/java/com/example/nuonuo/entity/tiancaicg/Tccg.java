package com.example.nuonuo.entity.tiancaicg;

import java.util.List;

public class Tccg {
    private String username;
    private String password;
    private String ent;
    private String messageId;
    private String busDate;
    private String sourceId;
    private int autoAuditFlag;
    private String centerId;
    private String systemId;
    private String supplierId;
    private String remark;
    private String sourceName;
    private List<TccgDetail> detail;
    private String busUserId;
    private String arrivalDate;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnt() {
        return ent;
    }

    public void setEnt(String ent) {
        this.ent = ent;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getBusDate() {
        return busDate;
    }

    public void setBusDate(String busDate) {
        this.busDate = busDate;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getAutoAuditFlag() {
        return autoAuditFlag;
    }

    public void setAutoAuditFlag(int autoAuditFlag) {
        this.autoAuditFlag = autoAuditFlag;
    }

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public List<TccgDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<TccgDetail> detail) {
        this.detail = detail;
    }

    public String getBusUserId() {
        return busUserId;
    }

    public void setBusUserId(String busUserId) {
        this.busUserId = busUserId;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}

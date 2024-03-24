package com.example.nuonuo.entity.tiancai;

import com.example.nuonuo.entity.tiancaicg.TccgDetail;

import java.util.List;

public class TcchukuReturn {

    private String username;
    private String password;
    private String ent;
    private String messageId;
    private int autoAuditFlag;
    private String cldBillId; //单据id
    private List<TcchukuReturnDetail> detail;

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

    public int getAutoAuditFlag() {
        return autoAuditFlag;
    }

    public void setAutoAuditFlag(int autoAuditFlag) {
        this.autoAuditFlag = autoAuditFlag;
    }

    public String getCldBillId() {
        return cldBillId;
    }

    public void setCldBillId(String cldBillId) {
        this.cldBillId = cldBillId;
    }

    public List<TcchukuReturnDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<TcchukuReturnDetail> detail) {
        this.detail = detail;
    }
}

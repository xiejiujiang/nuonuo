package com.example.nuonuo.entity.tiancaichukureturn;

import java.util.List;

public class TcZXCHUKUReturn {
    private Long id;
    private String messageId;
    private String billId;
    private String billNo;
    private String orderType;
    private String sourceOrderCode;
    private String orderDate;
    private String receiverInfoname;
    private String receiverInfomobile;
    private String receiverprovince;
    private String receivercity;
    private String receiverInfodetailAddress;
    private String remark;
    private String money;
    private String noTaxMoney;
    private String tax;
    private List<TcZXCHUKUReturnDetail> detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getSourceOrderCode() {
        return sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
        this.sourceOrderCode = sourceOrderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getReceiverInfoname() {
        return receiverInfoname;
    }

    public void setReceiverInfoname(String receiverInfoname) {
        this.receiverInfoname = receiverInfoname;
    }

    public String getReceiverInfomobile() {
        return receiverInfomobile;
    }

    public void setReceiverInfomobile(String receiverInfomobile) {
        this.receiverInfomobile = receiverInfomobile;
    }

    public String getReceiverprovince() {
        return receiverprovince;
    }

    public void setReceiverprovince(String receiverprovince) {
        this.receiverprovince = receiverprovince;
    }

    public String getReceivercity() {
        return receivercity;
    }

    public void setReceivercity(String receivercity) {
        this.receivercity = receivercity;
    }

    public String getReceiverInfodetailAddress() {
        return receiverInfodetailAddress;
    }

    public void setReceiverInfodetailAddress(String receiverInfodetailAddress) {
        this.receiverInfodetailAddress = receiverInfodetailAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<TcZXCHUKUReturnDetail> getDetail() {
        return detail;
    }

    public void setDetail(List<TcZXCHUKUReturnDetail> detail) {
        this.detail = detail;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNoTaxMoney() {
        return noTaxMoney;
    }

    public void setNoTaxMoney(String noTaxMoney) {
        this.noTaxMoney = noTaxMoney;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }
}

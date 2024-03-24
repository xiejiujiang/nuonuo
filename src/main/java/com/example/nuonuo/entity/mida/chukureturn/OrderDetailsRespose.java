package com.example.nuonuo.entity.mida.chukureturn;

import java.util.List;

public class OrderDetailsRespose {
    private String orderId;
    private String orderNo;
    private String order_status;
    private String createdAt;
    private String receiver;
    private String phoneNum;
    private String paidActual;
    private String address;
    private String driverName;
    private String driverPhone;
    private List<MobileEcOrderItemBo> items;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPaidActual() {
        return paidActual;
    }

    public void setPaidActual(String paidActual) {
        this.paidActual = paidActual;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public List<MobileEcOrderItemBo> getItems() {
        return items;
    }

    public void setItems(List<MobileEcOrderItemBo> items) {
        this.items = items;
    }
}

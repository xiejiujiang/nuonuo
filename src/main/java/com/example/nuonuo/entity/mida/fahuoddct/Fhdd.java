package com.example.nuonuo.entity.mida.fahuoddct;

import com.example.nuonuo.entity.mida.chukureturn.MobileEcOrderItemBo;
import com.example.nuonuo.entity.mida.dddetail.OrderStatus;

public class Fhdd {
    /**
     * 地址
     */
    private String address;
    /**
     * 创建时间，订单的创建时间
     */
    private String createdAt;
    /**
     * 司机姓名
     */
    private String driverName;
    /**
     * 司机电话
     */
    private String driverPhone;
    private MobileEcOrderItemBo[] items;
    /**
     * 订单状态
     */
    private OrderStatus orderStatus;
    /**
     * 订单id，平台生成的订单id，具有唯一性，建议调用方存储
     */
    private String orderId;
    /**
     * 订单号，平台生成的订单号.性能不及orderId,建议通过orderid进行交互
     */
    private String orderNo;
    /**
     * 订单金额
     */
    private String paidActual;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 收货人
     */
    private String receiver;

    public String getAddress() { return address; }
    public void setAddress(String value) { this.address = value; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String value) { this.createdAt = value; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String value) { this.driverName = value; }

    public String getDriverPhone() { return driverPhone; }
    public void setDriverPhone(String value) { this.driverPhone = value; }

    public MobileEcOrderItemBo[] getItems() { return items; }
    public void setItems(MobileEcOrderItemBo[] value) { this.items = value; }

    public OrderStatus getOrderStatus() { return orderStatus; }
    public void setOrderStatus(OrderStatus value) { this.orderStatus = value; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String value) { this.orderId = value; }

    public String getOrderNo() { return orderNo; }
    public void setOrderNo(String value) { this.orderNo = value; }

    public String getPaidActual() { return paidActual; }
    public void setPaidActual(String value) { this.paidActual = value; }

    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String value) { this.phoneNum = value; }

    public String getReceiver() { return receiver; }
    public void setReceiver(String value) { this.receiver = value; }
}

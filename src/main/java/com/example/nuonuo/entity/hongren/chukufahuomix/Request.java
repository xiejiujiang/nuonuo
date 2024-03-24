package com.example.nuonuo.entity.hongren.chukufahuomix;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request {

    private DeliveryOrder deliveryOrder;
    private OrderLines orderLines;

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public OrderLines getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(OrderLines orderLines) {
        this.orderLines = orderLines;
    }
}

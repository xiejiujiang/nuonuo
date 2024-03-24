package com.example.nuonuo.entity.hongren.chukuqr;

import com.example.nuonuo.entity.hongren.rukuqr.OrderLines;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Request {

    private DeliveryOrder deliveryOrder;
    private List<Package> packages;
    private OrderLines orderLines;

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public OrderLines getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(OrderLines orderLines) {
        this.orderLines = orderLines;
    }
}

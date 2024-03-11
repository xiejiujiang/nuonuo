package com.example.nuonuo.entity.hongren.rukufahuo;

import com.example.nuonuo.entity.hongren.rukufahuo.DeliveryOrder;
import com.example.nuonuo.entity.hongren.rukufahuo.OrderLine;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class RFRequest {
    private DeliveryOrder deliveryOrder;
    private List<OrderLine> orderLines;

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}

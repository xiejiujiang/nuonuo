package com.example.nuonuo.entity.hongren.rukuqr;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Request {

    private EntryOrder entryOrder;
    private OrderLines orderLines;

    public EntryOrder getEntryOrder() {
        return entryOrder;
    }

    public void setEntryOrder(EntryOrder entryOrder) {
        this.entryOrder = entryOrder;
    }

    public OrderLines getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(OrderLines orderLines) {
        this.orderLines = orderLines;
    }
}

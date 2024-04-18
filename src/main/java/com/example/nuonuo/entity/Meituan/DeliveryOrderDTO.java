package com.example.nuonuo.entity.Meituan;

import java.util.List;

public class DeliveryOrderDTO {
    private DeliveryOrderItemDTO item;
    private List<DeliveryOrderItemDetailDTO> details;

    public DeliveryOrderItemDTO getItem() {
        return item;
    }

    public void setItem(DeliveryOrderItemDTO item) {
        this.item = item;
    }

    public List<DeliveryOrderItemDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<DeliveryOrderItemDetailDTO> details) {
        this.details = details;
    }
}

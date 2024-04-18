package com.example.nuonuo.entity.Meituan.Chaidan;

import java.util.List;

public class MeituanPeisongChaidanDTO {
    private Long orgId;
    private String itemSn;
    private int version;
    private List<SpiltDeliveryOrderDetailDTO> details;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getItemSn() {
        return itemSn;
    }

    public void setItemSn(String itemSn) {
        this.itemSn = itemSn;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public List<SpiltDeliveryOrderDetailDTO> getDetails() {
        return details;
    }

    public void setDetails(List<SpiltDeliveryOrderDetailDTO> details) {
        this.details = details;
    }
}

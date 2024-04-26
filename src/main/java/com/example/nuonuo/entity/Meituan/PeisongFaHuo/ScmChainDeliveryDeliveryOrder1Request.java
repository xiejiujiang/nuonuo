package com.example.nuonuo.entity.Meituan.PeisongFaHuo;

import com.example.nuonuo.entity.Meituan.OrgAddressOpenDTO;
import java.util.List;

public class ScmChainDeliveryDeliveryOrder1Request {

    private Long orgId;
    private String itemSn;
    private Long deliveryTime;
    private Long expectDeliveryTime;
    private Long expectReceiveTime;
    private String remark;
    private OrgAddressOpenDTO orgAddress;
    private List<DeliveryItemDetailDTO> deliveryRows;

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

    public Long getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Long getExpectDeliveryTime() {
        return expectDeliveryTime;
    }

    public void setExpectDeliveryTime(Long expectDeliveryTime) {
        this.expectDeliveryTime = expectDeliveryTime;
    }

    public Long getExpectReceiveTime() {
        return expectReceiveTime;
    }

    public void setExpectReceiveTime(Long expectReceiveTime) {
        this.expectReceiveTime = expectReceiveTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public OrgAddressOpenDTO getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(OrgAddressOpenDTO orgAddress) {
        this.orgAddress = orgAddress;
    }

    public List<DeliveryItemDetailDTO> getDeliveryRows() {
        return deliveryRows;
    }

    public void setDeliveryRows(List<DeliveryItemDetailDTO> deliveryRows) {
        this.deliveryRows = deliveryRows;
    }
}

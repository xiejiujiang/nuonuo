package com.example.nuonuo.entity.Meituan;

import java.util.List;

public class DeliveryOrderItemDTO {
    private Long rootOrgId;
    private String itemSn;
    private Long orderTime;
    private Long expectTime;
    private Long deliverTime;
    private Long createTime;
    private Long modifyTime;
    private OrgOpenDTO demandOrg;
    private OrgOpenDTO supplyOrg;
    private OpenOppositeOrgDTO deliverOrg;
    private OrgOpenDTO receiveOrg;
    private OrgAddressOpenDTO receiverInfo;
    private String relationPurchaseSn;
    private List<String> sourceSns;
    private List<String> downstreamStockOutSn;
    private String generateReceiveOrderSn;
    private String money;
    private String noTaxMoney;
    private String tax;
    private Long creator;
    private Integer status;
    private String remark;
    private Integer bizModel;
    private Integer version;
    private Integer deleteStatus;
    private Long expectArriveTime;

    public Long getRootOrgId() {
        return rootOrgId;
    }

    public void setRootOrgId(Long rootOrgId) {
        this.rootOrgId = rootOrgId;
    }

    public String getItemSn() {
        return itemSn;
    }

    public void setItemSn(String itemSn) {
        this.itemSn = itemSn;
    }

    public Long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Long orderTime) {
        this.orderTime = orderTime;
    }

    public Long getExpectTime() {
        return expectTime;
    }

    public void setExpectTime(Long expectTime) {
        this.expectTime = expectTime;
    }

    public Long getDeliverTime() {
        return deliverTime;
    }

    public void setDeliverTime(Long deliverTime) {
        this.deliverTime = deliverTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Long modifyTime) {
        this.modifyTime = modifyTime;
    }

    public OrgOpenDTO getDemandOrg() {
        return demandOrg;
    }

    public void setDemandOrg(OrgOpenDTO demandOrg) {
        this.demandOrg = demandOrg;
    }

    public OrgOpenDTO getSupplyOrg() {
        return supplyOrg;
    }

    public void setSupplyOrg(OrgOpenDTO supplyOrg) {
        this.supplyOrg = supplyOrg;
    }

    public OpenOppositeOrgDTO getDeliverOrg() {
        return deliverOrg;
    }

    public void setDeliverOrg(OpenOppositeOrgDTO deliverOrg) {
        this.deliverOrg = deliverOrg;
    }

    public OrgOpenDTO getReceiveOrg() {
        return receiveOrg;
    }

    public void setReceiveOrg(OrgOpenDTO receiveOrg) {
        this.receiveOrg = receiveOrg;
    }

    public OrgAddressOpenDTO getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(OrgAddressOpenDTO receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

    public String getRelationPurchaseSn() {
        return relationPurchaseSn;
    }

    public void setRelationPurchaseSn(String relationPurchaseSn) {
        this.relationPurchaseSn = relationPurchaseSn;
    }

    public List<String> getSourceSns() {
        return sourceSns;
    }

    public void setSourceSns(List<String> sourceSns) {
        this.sourceSns = sourceSns;
    }

    public List<String> getDownstreamStockOutSn() {
        return downstreamStockOutSn;
    }

    public void setDownstreamStockOutSn(List<String> downstreamStockOutSn) {
        this.downstreamStockOutSn = downstreamStockOutSn;
    }

    public String getGenerateReceiveOrderSn() {
        return generateReceiveOrderSn;
    }

    public void setGenerateReceiveOrderSn(String generateReceiveOrderSn) {
        this.generateReceiveOrderSn = generateReceiveOrderSn;
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

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getBizModel() {
        return bizModel;
    }

    public void setBizModel(Integer bizModel) {
        this.bizModel = bizModel;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Long getExpectArriveTime() {
        return expectArriveTime;
    }

    public void setExpectArriveTime(Long expectArriveTime) {
        this.expectArriveTime = expectArriveTime;
    }
}

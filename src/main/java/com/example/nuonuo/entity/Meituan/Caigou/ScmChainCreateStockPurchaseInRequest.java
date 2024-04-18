package com.example.nuonuo.entity.Meituan.Caigou;

import com.alibaba.fastjson.JSONArray;

import java.util.List;

public class ScmChainCreateStockPurchaseInRequest {
    private Long orgId;
    private Long bizTime;
    private String supplierId;
    private String supplierCode;
    private String thirdRelatedSn;
    private String remark;
    private List<CrateStockInItemDetailDTO> crateStockInItemDetailDTOs;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getBizTime() {
        return bizTime;
    }

    public void setBizTime(Long bizTime) {
        this.bizTime = bizTime;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getThirdRelatedSn() {
        return thirdRelatedSn;
    }

    public void setThirdRelatedSn(String thirdRelatedSn) {
        this.thirdRelatedSn = thirdRelatedSn;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<CrateStockInItemDetailDTO> getCrateStockInItemDetailDTOs() {
        return crateStockInItemDetailDTOs;
    }

    public void setCrateStockInItemDetailDTOs(List<CrateStockInItemDetailDTO> crateStockInItemDetailDTOs) {
        this.crateStockInItemDetailDTOs = crateStockInItemDetailDTOs;
    }

    @Override
    public String toString() {
        return "ScmChainCreateStockPurchaseInRequest{" +
                "orgId=" + orgId +
                ", bizTime=" + bizTime +
                ", supplierId='" + supplierId + '\'' +
                ", supplierCode='" + supplierCode + '\'' +
                ", thirdRelatedSn='" + thirdRelatedSn + '\'' +
                ", remark='" + remark + '\'' +
                ", crateStockInItemDetailDTOs=" + JSONArray.toJSONString(crateStockInItemDetailDTOs) +
                '}';
    }
}

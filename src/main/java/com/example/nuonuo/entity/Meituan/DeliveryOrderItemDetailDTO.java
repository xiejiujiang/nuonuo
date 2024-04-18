package com.example.nuonuo.entity.Meituan;

public class DeliveryOrderItemDetailDTO {
    private String id;
    private Integer seqId;
    private GoodsOpenDTO goodsInfo;
    private WarehouseOpenDTO deliverWarehouseInfo;
    private UnitOpenDTO baseUnit;
    private UnitOpenDTO bizUnit;
    private MultiUnitAmountOpenDTO demandAmount;
    private MultiUnitAmountOpenDTO receiveOrderAmount;
    private MultiUnitAmountOpenDTO deliverAmount;
    private MultiUnitAmountOpenDTO receiveAmount;
    private MultiUnitAmountOpenDTO diffAmount;
    private MultiUnitAmountOpenDTO refundAmount;
    private MultiUnitAmountOpenDTO acceptDeliverDiffAmount;
    private String demandPrice;

    private String demandNoTaxPrice;

    private String baseUnitPrice;

    private String deliverMoney;

    private String deliverNoTaxMoney;

    private String demandMoney;

    private String receiveMoney;

    private String refundMoney;

    private String acceptDeliverDiffMoney;

    private String taxRate;

    private String tax;

    private String remark;

    private String originalPrice;

    private String originalMoney;

    private String noTaxOriginalPrice;

    private String noTaxOriginalMoney;

    private String campaignPromotionMoney;

    private String manualPromotionMoney;

    private String actuallyPayPrice;

    private String couponPromotionMoney;

    private String grantPaymentPromotionMoney;

    private String promotedPrice;

    private String promotedMoney;

    private String noTaxPromotedPrice;

    private String noTaxPromotedMoney;

    private String physicalBatchNum;

    private String description;

    private Long productionDate;

    private int shelfLife;

    private Long expirationDate;

    private int giftType;

    private String diffApplyDeliveryAmount;

    private String diffApplyDeliveryMoney;

    private String acceptMoney;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSeqId() {
        return seqId;
    }

    public void setSeqId(Integer seqId) {
        this.seqId = seqId;
    }

    public GoodsOpenDTO getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsOpenDTO goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public WarehouseOpenDTO getDeliverWarehouseInfo() {
        return deliverWarehouseInfo;
    }

    public void setDeliverWarehouseInfo(WarehouseOpenDTO deliverWarehouseInfo) {
        this.deliverWarehouseInfo = deliverWarehouseInfo;
    }

    public UnitOpenDTO getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(UnitOpenDTO baseUnit) {
        this.baseUnit = baseUnit;
    }

    public UnitOpenDTO getBizUnit() {
        return bizUnit;
    }

    public void setBizUnit(UnitOpenDTO bizUnit) {
        this.bizUnit = bizUnit;
    }

    public MultiUnitAmountOpenDTO getDemandAmount() {
        return demandAmount;
    }

    public void setDemandAmount(MultiUnitAmountOpenDTO demandAmount) {
        this.demandAmount = demandAmount;
    }

    public MultiUnitAmountOpenDTO getReceiveOrderAmount() {
        return receiveOrderAmount;
    }

    public void setReceiveOrderAmount(MultiUnitAmountOpenDTO receiveOrderAmount) {
        this.receiveOrderAmount = receiveOrderAmount;
    }

    public MultiUnitAmountOpenDTO getDeliverAmount() {
        return deliverAmount;
    }

    public void setDeliverAmount(MultiUnitAmountOpenDTO deliverAmount) {
        this.deliverAmount = deliverAmount;
    }

    public MultiUnitAmountOpenDTO getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(MultiUnitAmountOpenDTO receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public MultiUnitAmountOpenDTO getDiffAmount() {
        return diffAmount;
    }

    public void setDiffAmount(MultiUnitAmountOpenDTO diffAmount) {
        this.diffAmount = diffAmount;
    }

    public MultiUnitAmountOpenDTO getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(MultiUnitAmountOpenDTO refundAmount) {
        this.refundAmount = refundAmount;
    }

    public MultiUnitAmountOpenDTO getAcceptDeliverDiffAmount() {
        return acceptDeliverDiffAmount;
    }

    public void setAcceptDeliverDiffAmount(MultiUnitAmountOpenDTO acceptDeliverDiffAmount) {
        this.acceptDeliverDiffAmount = acceptDeliverDiffAmount;
    }

    public String getDemandPrice() {
        return demandPrice;
    }

    public void setDemandPrice(String demandPrice) {
        this.demandPrice = demandPrice;
    }

    public String getDemandNoTaxPrice() {
        return demandNoTaxPrice;
    }

    public void setDemandNoTaxPrice(String demandNoTaxPrice) {
        this.demandNoTaxPrice = demandNoTaxPrice;
    }

    public String getBaseUnitPrice() {
        return baseUnitPrice;
    }

    public void setBaseUnitPrice(String baseUnitPrice) {
        this.baseUnitPrice = baseUnitPrice;
    }

    public String getDeliverMoney() {
        return deliverMoney;
    }

    public void setDeliverMoney(String deliverMoney) {
        this.deliverMoney = deliverMoney;
    }

    public String getDeliverNoTaxMoney() {
        return deliverNoTaxMoney;
    }

    public void setDeliverNoTaxMoney(String deliverNoTaxMoney) {
        this.deliverNoTaxMoney = deliverNoTaxMoney;
    }

    public String getDemandMoney() {
        return demandMoney;
    }

    public void setDemandMoney(String demandMoney) {
        this.demandMoney = demandMoney;
    }

    public String getReceiveMoney() {
        return receiveMoney;
    }

    public void setReceiveMoney(String receiveMoney) {
        this.receiveMoney = receiveMoney;
    }

    public String getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(String refundMoney) {
        this.refundMoney = refundMoney;
    }

    public String getAcceptDeliverDiffMoney() {
        return acceptDeliverDiffMoney;
    }

    public void setAcceptDeliverDiffMoney(String acceptDeliverDiffMoney) {
        this.acceptDeliverDiffMoney = acceptDeliverDiffMoney;
    }

    public String getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(String taxRate) {
        this.taxRate = taxRate;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getOriginalMoney() {
        return originalMoney;
    }

    public void setOriginalMoney(String originalMoney) {
        this.originalMoney = originalMoney;
    }

    public String getNoTaxOriginalPrice() {
        return noTaxOriginalPrice;
    }

    public void setNoTaxOriginalPrice(String noTaxOriginalPrice) {
        this.noTaxOriginalPrice = noTaxOriginalPrice;
    }

    public String getNoTaxOriginalMoney() {
        return noTaxOriginalMoney;
    }

    public void setNoTaxOriginalMoney(String noTaxOriginalMoney) {
        this.noTaxOriginalMoney = noTaxOriginalMoney;
    }

    public String getCampaignPromotionMoney() {
        return campaignPromotionMoney;
    }

    public void setCampaignPromotionMoney(String campaignPromotionMoney) {
        this.campaignPromotionMoney = campaignPromotionMoney;
    }

    public String getManualPromotionMoney() {
        return manualPromotionMoney;
    }

    public void setManualPromotionMoney(String manualPromotionMoney) {
        this.manualPromotionMoney = manualPromotionMoney;
    }

    public String getActuallyPayPrice() {
        return actuallyPayPrice;
    }

    public void setActuallyPayPrice(String actuallyPayPrice) {
        this.actuallyPayPrice = actuallyPayPrice;
    }

    public String getCouponPromotionMoney() {
        return couponPromotionMoney;
    }

    public void setCouponPromotionMoney(String couponPromotionMoney) {
        this.couponPromotionMoney = couponPromotionMoney;
    }

    public String getGrantPaymentPromotionMoney() {
        return grantPaymentPromotionMoney;
    }

    public void setGrantPaymentPromotionMoney(String grantPaymentPromotionMoney) {
        this.grantPaymentPromotionMoney = grantPaymentPromotionMoney;
    }

    public String getPromotedPrice() {
        return promotedPrice;
    }

    public void setPromotedPrice(String promotedPrice) {
        this.promotedPrice = promotedPrice;
    }

    public String getPromotedMoney() {
        return promotedMoney;
    }

    public void setPromotedMoney(String promotedMoney) {
        this.promotedMoney = promotedMoney;
    }

    public String getNoTaxPromotedPrice() {
        return noTaxPromotedPrice;
    }

    public void setNoTaxPromotedPrice(String noTaxPromotedPrice) {
        this.noTaxPromotedPrice = noTaxPromotedPrice;
    }

    public String getNoTaxPromotedMoney() {
        return noTaxPromotedMoney;
    }

    public void setNoTaxPromotedMoney(String noTaxPromotedMoney) {
        this.noTaxPromotedMoney = noTaxPromotedMoney;
    }

    public String getPhysicalBatchNum() {
        return physicalBatchNum;
    }

    public void setPhysicalBatchNum(String physicalBatchNum) {
        this.physicalBatchNum = physicalBatchNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Long productionDate) {
        this.productionDate = productionDate;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getGiftType() {
        return giftType;
    }

    public void setGiftType(int giftType) {
        this.giftType = giftType;
    }

    public String getDiffApplyDeliveryAmount() {
        return diffApplyDeliveryAmount;
    }

    public void setDiffApplyDeliveryAmount(String diffApplyDeliveryAmount) {
        this.diffApplyDeliveryAmount = diffApplyDeliveryAmount;
    }

    public String getDiffApplyDeliveryMoney() {
        return diffApplyDeliveryMoney;
    }

    public void setDiffApplyDeliveryMoney(String diffApplyDeliveryMoney) {
        this.diffApplyDeliveryMoney = diffApplyDeliveryMoney;
    }

    public String getAcceptMoney() {
        return acceptMoney;
    }

    public void setAcceptMoney(String acceptMoney) {
        this.acceptMoney = acceptMoney;
    }
}

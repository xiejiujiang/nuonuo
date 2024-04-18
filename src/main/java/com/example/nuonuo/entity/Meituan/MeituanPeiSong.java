package com.example.nuonuo.entity.Meituan;

import com.alibaba.fastjson.JSONObject;

import java.net.URLDecoder;

public class MeituanPeiSong {
    private Long id;
    private Long rootOrgId;
    private String pushEventId;
    private int opType;
    private Long seqId;
    private Boolean largeMessageTag;
    private DeliveryOrderDTO bizData;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRootOrgId() {
        return rootOrgId;
    }

    public void setRootOrgId(Long rootOrgId) {
        this.rootOrgId = rootOrgId;
    }

    public String getPushEventId() {
        return pushEventId;
    }

    public void setPushEventId(String pushEventId) {
        this.pushEventId = pushEventId;
    }

    public int getOpType() {
        return opType;
    }

    public void setOpType(int opType) {
        this.opType = opType;
    }

    public Long getSeqId() {
        return seqId;
    }

    public void setSeqId(Long seqId) {
        this.seqId = seqId;
    }

    public Boolean getLargeMessageTag() {
        return largeMessageTag;
    }

    public void setLargeMessageTag(Boolean largeMessageTag) {
        this.largeMessageTag = largeMessageTag;
    }

    public DeliveryOrderDTO getBizData() {
        return bizData;
    }

    public void setBizData(DeliveryOrderDTO bizData) {
        this.bizData = bizData;
    }

    public static void main(String[] args) throws Exception{
        String ss = "opBizCode=2038410&msgType=1810095&developerId=112274&businessId=18&sign=394df9bae098053d51d5bc0d0e9d4c73a540393c&msgId=6682335631432874887&message=%7B%22rootOrgId%22%3A2058118%2C%22pushEventId%22%3A%22C0SJpe3b7SF2Y8ureIcdPQ%3D%3D%22%2C%22bizId%22%3A%22PS2404050008%22%2C%22opType%22%3A3%2C%22seqId%22%3A200%2C%22bizData%22%3A%7B%22item%22%3A%7B%22rootOrgId%22%3A2058118%2C%22itemSn%22%3A%22PS2404050008%22%2C%22orderTime%22%3A1712246401000%2C%22expectTime%22%3A1712332801000%2C%22deliverTime%22%3A1712419201000%2C%22createTime%22%3A1712322818000%2C%22modifyTime%22%3A1712455530000%2C%22demandOrg%22%3A%7B%22orgId%22%3A2240203%2C%22rootOrgId%22%3A2058118%2C%22code%22%3A%22MD00006%22%2C%22name%22%3A%22%E4%B8%87%E8%80%81%E5%A4%B4%E5%87%A0%E6%A0%B7%E8%8F%9C%EF%BC%88%E4%B9%9D%E7%9C%BC%E6%A1%A5%E5%BA%97%EF%BC%89%22%7D%2C%22supplyOrg%22%3A%7B%22orgId%22%3A2038410%2C%22rootOrgId%22%3A2058118%2C%22code%22%3A%22PS00001%22%2C%22name%22%3A%22%E6%80%BB%E9%83%A8%E9%85%8D%E9%80%81%E4%B8%AD%E5%BF%83%22%7D%2C%22deliverOrg%22%3A%7B%22type%22%3A%7B%22id%22%3A2%2C%22name%22%3A%22%E7%BB%84%E7%BB%87%E6%9C%BA%E6%9E%84%22%7D%2C%22code%22%3A%22PS00001%22%2C%22name%22%3A%22%E6%80%BB%E9%83%A8%E9%85%8D%E9%80%81%E4%B8%AD%E5%BF%83%22%7D%2C%22receiveOrg%22%3A%7B%22orgId%22%3A2240203%2C%22rootOrgId%22%3A2058118%2C%22code%22%3A%22MD00006%22%2C%22name%22%3A%22%E4%B8%87%E8%80%81%E5%A4%B4%E5%87%A0%E6%A0%B7%E8%8F%9C%EF%BC%88%E4%B9%9D%E7%9C%BC%E6%A1%A5%E5%BA%97%EF%BC%89%22%7D%2C%22receiverInfo%22%3A%7B%22contactName%22%3A%22%E9%BB%84%E5%BB%BA%E6%9E%97%22%2C%22address%22%3A%22%E5%9B%9B%E5%B7%9D%E7%9C%81%E6%88%90%E9%83%BD%E5%B8%82%E9%94%A6%E6%B1%9F%E5%8C%BA%E5%9B%9B%E5%B7%9D%E7%9C%81%E6%88%90%E9%83%BD%E5%B8%82%E9%94%A6%E6%B1%9F%E5%8C%BA%E4%B8%80%E7%8E%AF%E8%B7%AF%E4%B8%9C5%E6%AE%B5101%E9%99%842%E5%8F%B7%EF%BC%8C%E4%B8%87%E8%80%81%E5%A4%B4%E5%87%A0%E6%A0%B7%E8%8F%9C%22%7D%2C%22sourceSns%22%3A%5B%22DH2404050008%22%5D%2C%22downstreamStockOutSn%22%3A%5B%22PFCK2404070001%22%5D%2C%22generateReceiveOrderSns%22%3A%5B%22SH2404070001%22%5D%2C%22money%22%3A%221396.00%22%2C%22noTaxMoney%22%3A%221396.00%22%2C%22tax%22%3A%220.00%22%2C%22creator%22%3A1%2C%22status%22%3A304%2C%22remark%22%3A%22%22%2C%22bizModel%22%3A1%2C%22version%22%3A2%2C%22deleteStatus%22%3A0%2C%22expectArriveTime%22%3A1712332801000%7D%2C%22details%22%3A%5B%7B%22id%22%3A%223W65J%2FNNM%2FVZoYr3HiyDUtACIq5XS8mUqa2WcOiQ0yA%3D%22%2C%22seqId%22%3A0%2C%22goodsInfo%22%3A%7B%22id%22%3A%22zqkIkOABdqFBfH7UuAPFZw%3D%3D%22%2C%22code%22%3A%22ZBWP0186%22%2C%22name%22%3A%22%E8%B0%83%E7%90%86%E5%85%94%E4%B8%81%EF%BC%88%E4%B8%87%E8%80%81%E5%A4%B4%E7%94%A8%EF%BC%89%22%2C%22category%22%3A%7B%22id%22%3A%22IU3JcX2FudbZAR%2BYKMnEXw%3D%3D%22%2C%22code%22%3A%22ZBLB014%22%2C%22name%22%3A%22%E4%B8%87%E8%80%81%E5%A4%B4%E5%AE%9A%E9%A3%9F%E6%9D%90%22%7D%2C%22spec%22%3A%22300g*40%E8%A2%8B%22%2C%22baseUnit%22%3A%7B%22id%22%3A%22b%2B7w71A4%2BpqHKGfX7paheg%3D%3D%22%2C%22code%22%3A%22ZBDW004%22%2C%22name%22%3A%22%E4%BB%B6%22%7D%2C%22units%22%3A%5B%7B%22id%22%3A%22b%2B7w71A4%2BpqHKGfX7paheg%3D%3D%22%2C%22code%22%3A%22ZBDW004%22%2C%22name%22%3A%22%E4%BB%B6%22%7D%5D%2C%22version%22%3A3000%7D%2C%22deliverWarehouseInfo%22%3A%7B%22id%22%3A%22zXpC%2BFQX7cM5O1%2FOeBiiZg%3D%3D%22%2C%22code%22%3A%22ZBCK03%22%2C%22name%22%3A%22%E6%A6%95%E5%B7%9D%E9%85%8D%E9%80%81%E4%BB%93%E5%BA%93%22%7D%2C%22baseUnit%22%3A%7B%22id%22%3A%22b%2B7w71A4%2BpqHKGfX7paheg%3D%3D%22%2C%22code%22%3A%22ZBDW004%22%2C%22name%22%3A%22%E4%BB%B6%22%7D%2C%22bizUnit%22%3A%7B%22id%22%3A%22b%2B7w71A4%2BpqHKGfX7paheg%3D%3D%22%2C%22code%22%3A%22ZBDW004%22%2C%22name%22%3A%22%E4%BB%B6%22%7D%2C%22demandAmount%22%3A%7B%22baseUnitAmount%22%3A%222%22%2C%22bizUnitAmount%22%3A%222%22%7D%2C%22receiveOrderAmount%22%3A%7B%22baseUnitAmount%22%3A%222%22%2C%22bizUnitAmount%22%3A%222%22%7D%2C%22deliverAmount%22%3A%7B%22baseUnitAmount%22%3A%222%22%2C%22bizUnitAmount%22%3A%222%22%7D%2C%22demandPrice%22%3A%22698%22%2C%22demandNoTaxPrice%22%3A%22698%22%2C%22baseUnitPrice%22%3A%22698%22%2C%22deliverMoney%22%3A%221396.00%22%2C%22deliverNoTaxMoney%22%3A%221396.00%22%2C%22demandMoney%22%3A%221396.00%22%2C%22taxRate%22%3A%220%22%2C%22tax%22%3A%220.00%22%2C%22remark%22%3A%22%22%2C%22originalPrice%22%3A%22698%22%2C%22originalMoney%22%3A%221396.00%22%2C%22noTaxOriginalPrice%22%3A%22698%22%2C%22noTaxOriginalMoney%22%3A%221396.00%22%2C%22campaignPromotionMoney%22%3A%220.00%22%2C%22manualPromotionMoney%22%3A%220.00%22%2C%22actuallyPayPrice%22%3A%22698%22%2C%22couponPromotionMoney%22%3A%220.00%22%2C%22grantPaymentPromotionMoney%22%3A%220.00%22%2C%22promotedPrice%22%3A%22698%22%2C%22promotedMoney%22%3A%221396.00%22%2C%22noTaxPromotedPrice%22%3A%22698%22%2C%22noTaxPromotedMoney%22%3A%221396.00%22%2C%22physicalBatchNum%22%3A%22SYSTEM%22%2C%22giftType%22%3A2%2C%22diffApplyDeliveryAmount%22%3A%220%22%2C%22diffApplyDeliveryMoney%22%3A%220.00%22%7D%5D%7D%2C%22largeMessageTag%22%3Afalse%7D&timestamp=1712455530";
        String decodedString = URLDecoder.decode(ss, "UTF-8");
        System.out.println(decodedString);
        JSONObject job = JSONObject.parseObject(decodedString);
        String message = job.getString("message");
        MeituanPeiSong meituanPeiSong = JSONObject.parseObject(message,MeituanPeiSong.class);
        System.out.println(meituanPeiSong.getBizData().getDetails().size());
    }
}

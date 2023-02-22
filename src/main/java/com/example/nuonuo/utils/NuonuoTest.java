package com.example.nuonuo.utils;

import nuonuo.open.sdk.NNOpenSDK;

import java.util.UUID;

public class NuonuoTest {

    public static void main(String[] args) {
        NNOpenSDK sdk = NNOpenSDK.getIntance();
        String taxnum = "23***789"; // 授权企业税号
        String appKey = "Hn***XL";
        String appSecret = "F65***65F";
        String method = "nuonuo.OpeMplatform.requestBillingNew"; // API方法名
        String token = "2d484e**************pdui"; // 访问令牌
        String content = "{\n" +
                "  \"order\": {\n" +
                "    \"terminalNumber\": \"\",\n" +
                "    \"listFlag\": \"0\",\n" +
                "    \"pushMode\": \"1\",\n" +
                "    \"managerCardNo\": \"\",\n" +
                "    \"invoiceBuildingInfo\": {\n" +
                "      \"buildingAddress\": \"浙江省杭州市西湖区\",\n" +
                "      \"crossCityFlag\": \"0\",\n" +
                "      \"itemName\": \"宇宙城\",\n" +
                "      \"landVatItemNo\": \"\",\n" +
                "      \"detailedAddress\": \"\"\n" +
                "    },\n" +
                "    \"departmentId\": \"9F7E9439CA8B4C60A2FFF3EA3290B088\",\n" +
                "    \"paperInvoiceType\": \"\",\n" +
                "    \"checker\": \"王五\",\n" +
                "    \"invoiceNumEnd\": \"\",\n" +
                "    \"payee\": \"李四\",\n" +
                "    \"invoiceTravellerTransport\": [\n" +
                "      {\n" +
                "        \"travellerCardType\": \"\",\n" +
                "        \"arrivePlace\": \"\",\n" +
                "        \"travelDate\": \"2023-01-01\",\n" +
                "        \"travelPlace\": \"\",\n" +
                "        \"vehicleLevel\": \"经济舱\",\n" +
                "        \"travellerCardNo\": \"\",\n" +
                "        \"traveller\": \"张三\",\n" +
                "        \"vehicleType\": \"1\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"buyerAddress\": \"杭州市\",\n" +
                "    \"invoiceType\": \"1\",\n" +
                "    \"specificFactor\": \"0\",\n" +
                "    \"orderNo\": \"201701053332079312313\",\n" +
                "    \"machineCode\": \"123456789123\",\n" +
                "    \"vehicleFlag\": \"1\",\n" +
                "    \"invoiceCode\": \"\",\n" +
                "    \"buyerPhone\": \"15858585858\",\n" +
                "    \"surveyAnswerType\": \"\",\n" +
                "    \"invoiceDetail\": [\n" +
                "      {\n" +
                "        \"specType\": \"y460\",\n" +
                "        \"dField1\": \"\",\n" +
                "        \"taxExcludedAmount\": \"0.88\",\n" +
                "        \"invoiceLineProperty\": \"0\",\n" +
                "        \"favouredPolicyName\": \"0\",\n" +
                "        \"dField3\": \"\",\n" +
                "        \"dField2\": \"\",\n" +
                "        \"dField5\": \"\",\n" +
                "        \"num\": \"\",\n" +
                "        \"dField4\": \"\",\n" +
                "        \"withTaxFlag\": \"1\",\n" +
                "        \"tax\": \"0.12\",\n" +
                "        \"favouredPolicyFlag\": \"0\",\n" +
                "        \"taxRate\": \"0.13\",\n" +
                "        \"unit\": \"台\",\n" +
                "        \"deduction\": \"0\",\n" +
                "        \"price\": \"\",\n" +
                "        \"zeroRateFlag\": \"0\",\n" +
                "        \"goodsCode\": \"1090511030000000000\",\n" +
                "        \"selfCode\": \"130005426000000000\",\n" +
                "        \"goodsName\": \"电脑\",\n" +
                "        \"taxIncludedAmount\": \"1\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"buyerTel\": \"0571-88888888\",\n" +
                "    \"nextInvoiceNum\": \"\",\n" +
                "    \"clerkId\": \"\",\n" +
                "    \"remark\": \"备注信息\",\n" +
                "    \"managerCardType\": \"201\",\n" +
                "    \"buyerTaxNum\": \"339901999999198\",\n" +
                "    \"invoiceLine\": \"p\",\n" +
                "    \"secondHandCarInfo\": {\n" +
                "      \"organizeType\": \"1\",\n" +
                "      \"vehicleManagementName\": \"杭州\",\n" +
                "      \"sellerPhone\": \"13888888888\",\n" +
                "      \"sellerName\": \"张三\",\n" +
                "      \"brandModel\": \"宝马3系\",\n" +
                "      \"vehicleCode\": \"LHGK43284342384234\",\n" +
                "      \"licenseNumber\": \"浙A12345\",\n" +
                "      \"registerCertNo\": \"330022123321\",\n" +
                "      \"sellerAddress\": \"杭州文一路888号\",\n" +
                "      \"vehicleType\": \"轿车\",\n" +
                "      \"intactCerNum\": \"\",\n" +
                "      \"sellerTaxnum\": \"330100199001010000\"\n" +
                "    },\n" +
                "    \"buyerManagerName\": \"张三\",\n" +
                "    \"redReason\": \"1\",\n" +
                "    \"email\": \"test@xx.com\",\n" +
                "    \"salerAccount\": \"\",\n" +
                "    \"salerTel\": \"0571-77777777\",\n" +
                "    \"callBackUrl\": \"http:127.0.0.1/invoice/callback/\",\n" +
                "    \"additionalElementList\": [\n" +
                "      {\n" +
                "        \"elementValue\": \"信息值\",\n" +
                "        \"elementType\": \"信息类型\",\n" +
                "        \"elementName\": \"信息名称\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"billInfoNo\": \"1403011904008472\",\n" +
                "    \"vehicleInfo\": {\n" +
                "      \"taxOfficeCode\": \"13399000\",\n" +
                "      \"manufacturerName\": \"华晨宝马汽车生产有限公司\",\n" +
                "      \"importCerNum\": \"\",\n" +
                "      \"certificate\": \"WDL042613263551\",\n" +
                "      \"engineNum\": \"10111011111\",\n" +
                "      \"taxOfficeName\": \"杭州税务\",\n" +
                "      \"brandModel\": \"宝马3系\",\n" +
                "      \"productOrigin\": \"北京\",\n" +
                "      \"vehicleCode\": \"LHGK43284342384234\",\n" +
                "      \"maxCapacity\": \"5\",\n" +
                "      \"intactCerNum\": \"\",\n" +
                "      \"tonnage\": \"2\",\n" +
                "      \"insOddNum\": \"\",\n" +
                "      \"idNumOrgCode\": \"9114010034683511XD\",\n" +
                "      \"vehicleType\": \"轿车\"\n" +
                "    },\n" +
                "    \"buyerName\": \"企业名称/个人\",\n" +
                "    \"invoiceDate\": \"2022-01-13 12:30:00\",\n" +
                "    \"invoiceNum\": \"\",\n" +
                "    \"hiddenBmbbbh\": \"0\",\n" +
                "    \"salerAddress\": \"\",\n" +
                "    \"clerk\": \"张三\",\n" +
                "    \"invoiceGoodsTransports\": [\n" +
                "      {\n" +
                "        \"origin\": \"上海\",\n" +
                "        \"transportTool\": \"1\",\n" +
                "        \"transportToolNum\": \"浙A12345\",\n" +
                "        \"destination\": \"北京\",\n" +
                "        \"goodsName\": \"零配件\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"bField1\": \"\",\n" +
                "    \"buyerAccount\": \"中国工商银行 111111111111\",\n" +
                "    \"nextInvoiceCode\": \"\",\n" +
                "    \"extensionNumber\": \"0\",\n" +
                "    \"bField3\": \"\",\n" +
                "    \"additionalElementName\": \"测试模版\",\n" +
                "    \"salerTaxNum\": \"339901999999199\",\n" +
                "    \"bField2\": \"\",\n" +
                "    \"forceFlag\": \"0\",\n" +
                "    \"realPropertyRentInfo\": {\n" +
                "      \"realPropertyAddress\": \"浙江省杭州市西湖区\",\n" +
                "      \"crossCityFlag\": \"0\",\n" +
                "      \"realPropertyCertificate\": \"\",\n" +
                "      \"unit\": \"2\",\n" +
                "      \"rentEndDate\": \"2023-01-30\",\n" +
                "      \"detailAddress\": \"文一西路XXXX号\",\n" +
                "      \"rentStartDate\": \"2023-01-01\"\n" +
                "    },\n" +
                "    \"listName\": \"详见销货清单\",\n" +
                "    \"proxyInvoiceFlag\": \"0\"\n" +
                "  }\n" +
                "}";
        String url = "https://sdk.nuonuo.com/open/v1/services"; // SDK请求地址
        String senid = UUID.randomUUID().toString().replace("-", ""); // 唯一标识，32位随机码，无需修改，保持默认即可
        String result = sdk.sendPostSyncRequest(url, senid, appKey, appSecret, token, taxnum, method, content);
        System.out.println(result);
    }
}

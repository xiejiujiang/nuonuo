package com.example.nuonuo.utils;

import nuonuo.open.sdk.NNOpenSDK;

import java.util.UUID;

public class NuonuoTest {

    //  票 又分 ： 个人 或 公式
    // 普票： 只需要一些基本信息： 企业名称  税号 等
    // 专票： 只能是 一般纳税人 才能 给他开 专票。，专票 必须要填 账号，地址，税号，企业名称。等全部信息
    // 收票方  可以  使用 诺诺网的模糊查询，快速填入开票信息，
    public static String CommitNuonuo(String appKey,String appSecret,String taxnum,String token,String content){
        NNOpenSDK sdk = NNOpenSDK.getIntance();
        //String taxnum = "91310117MA1J567H32"; // 授权企业税号  91310117MA1J567H32  睿肄博，   测试：339901999999199
        //String appKey = "27470641";
        //String appSecret = "509A83CA7A3B4A3C";
        String method = "nuonuo.OpeMplatform.requestBillingNew"; // API方法名
        //正式环境	https://sdk.nuonuo.com/open/v1/services
        //沙箱环境	https://sandbox.nuonuocs.cn/open/v1/services

        String url = "https://sdk.nuonuo.com/open/v1/services"; // SDK请求地址
        String senid = UUID.randomUUID().toString().replace("-", ""); // 唯一标识，32位随机码，无需修改，保持默认即可
        String result = sdk.sendPostSyncRequest(url, senid, appKey, appSecret, token, taxnum, method, content);
        return result;
    }


    public static String getToken(){
        NNOpenSDK sdk = NNOpenSDK.getIntance();
        String token = sdk.getMerchantToken("27470641","509A83CA7A3B4A3C");
        System.out.println("token == " + token);
        return token;
    }

    public static String getToken(String appKey,String appSecret){
        NNOpenSDK sdk = NNOpenSDK.getIntance();
        String token = sdk.getMerchantToken(appKey,appSecret);
        System.out.println("appKey : " + appKey + " 对应的token == " + token);
        return token;
    }


    public static void main(String[] args) {
        //getToken();
        NNOpenSDK sdk = NNOpenSDK.getIntance();
        String taxnum = "91310117MA1J567H32"; // 授权企业税号  91310117MA1J567H32  睿肄博，   测试：339901999999199
        String appKey = "27470641";
        String appSecret = "509A83CA7A3B4A3C";
        String method = "nuonuo.OpeMplatform.requestBillingNew"; // API方法名
        String token = "179f29c1a92185b319a69c0ihnktfiss"; // 访问令牌 ????

        String content = "{\n" +
                "  \"order\": {\n" +
                "    \"pushMode\": \"0\",\n" +
                "    \"invoiceType\": \"1\",\n" +
                "    \"orderNo\": \"re-retail-test-0005\",\n" +
                "    \"vehicleFlag\": \"0\",\n" +
                "    \"buyerPhone\": \"15828574775\",\n" +
                "    \"buyerTel\": \"15828574775\",\n" +
                "    \"remark\": \"备注信息\",\n" +
                "    \"invoiceLine\": \"p\",\n" +
                "    \"email\": \"634482891@qq.com\",\n" +
                "    \"salerTel\": \"15828574775\",\n" +
                "    \"callBackUrl\": \"http:127.0.0.1/invoice/callback/\",\n" +
                "    \"buyerName\": \"江哥\",\n" +
                "    \"invoiceDate\": \"2022-03-02 09:30:00\",\n" +
                "    \"salerAddress\": \"四川省成都市科华北路力宝大厦\",\n" +
                "    \"clerk\": \"谢玖江\",\n" +
                "    \"additionalElementName\": \"测试模版\",\n" +
                "    \"salerTaxNum\": \"91310117MA1J567H32\",\n" +
                "    \"invoiceDetail\": [\n" +
                "      {\n" +
                "        \"num\": \"1\",\n" +
                "        \"withTaxFlag\": \"1\",\n" +
                "        \"taxRate\": \"0.13\",\n" +
                "        \"unit\": \"台\",\n" +
                "        \"price\": \"1\",\n" +
                //"        \"goodsCode\": \"TEST001\",\n" +
                "        \"goodsName\": \"华为笔记本电脑3\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"num\": \"1\",\n" +
                "        \"withTaxFlag\": \"1\",\n" +
                "        \"taxRate\": \"0.13\",\n" +
                "        \"unit\": \"台\",\n" +
                "        \"price\": \"2\",\n" +
                //"        \"goodsCode\": \"TEST002\",\n" +
                "        \"goodsName\": \"华为笔记本电脑4\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        //正式环境	https://sdk.nuonuo.com/open/v1/services
        //沙箱环境	https://sandbox.nuonuocs.cn/open/v1/services

        String url = "https://sdk.nuonuo.com/open/v1/services"; // SDK请求地址
        String senid = UUID.randomUUID().toString().replace("-", ""); // 唯一标识，32位随机码，无需修改，保持默认即可
        String result = sdk.sendPostSyncRequest(url, senid, appKey, appSecret, token, taxnum, method, content);
        System.out.println(result);
    }
}
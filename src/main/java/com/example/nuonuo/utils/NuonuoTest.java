package com.example.nuonuo.utils;

import com.alibaba.fastjson.JSONObject;
import nuonuo.open.sdk.NNOpenSDK;

import java.util.*;

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


    //组装成一个String,用于请求诺税通
    public static String getContentString(Map<String,Object>  params, List<Map<String,Object>> invenlist){
        Map<String,Object> order = new HashMap<String,Object>();
        Map<String,Object> orderdetail = new HashMap<String,Object>();
        orderdetail.put("pushMode","0");// 推送方式：-1,不推送;0,邮箱;1,手机（默认）;2,邮箱、手机
        orderdetail.put("invoiceType","1");// 开票类型：1:蓝票;2:红票 （数电票冲红请对接数电快捷冲红接口）
        orderdetail.put("orderNo",params.get("code").toString());// 订单号（每个企业唯一）
        orderdetail.put("buyerPhone",params.get("buytel").toString());//购方手机（pushMode为1或2时，此项为必填，同时受企业资质是否必填控制）
        orderdetail.put("buyerTel",params.get("buytel").toString());//购方电话（购方地址+电话总共不超100字符；二手车销售统一发票时必填）
        //orderdetail.put("remark",params.get("memo").toString());

        if(params.get("invoicetype") == null){//说明是个人开票，那就开个人的电子普票
            orderdetail.put("invoiceLine","p");
        }else{
            //公司开票  1 普票   ，   2 专票
            if("1".equals(params.get("invoicetype").toString())){
                orderdetail.put("invoiceLine","p");
            }else{
                orderdetail.put("invoiceLine","s");
                orderdetail.put("buyerTaxNum",params.get("companytaxnum").toString());
                orderdetail.put("buyerAddress",params.get("companyaddress").toString());
                orderdetail.put("buyerAccount",params.get("companybankname").toString() + " " + params.get("companybanknum").toString());
            }
        }

        //发票种类：
        // p,普通发票(电票)(默认);
        // c,普通发票(纸票);
        // s,专用发票;
        // e,收购发票(电票);
        // f,收购发票(纸质);
        // r,普通发票(卷式);
        // b,增值税电子专用发票;
        // j,机动车销售统一发票;
        // u,二手车销售统一发票;
        // bs:电子发票(增值税专用发票)-即数电专票(电子),
        // pc:电子发票(普通发票)-即数电普票(电子),
        // es:数电纸质发票(增值税专用发票)-即数电专票(纸质);
        // ec:数电纸质发票(普通发票)-即数电普票(纸质)


        orderdetail.put("email",params.get("mail").toString());
        orderdetail.put("salerTel","54900272");//销方电话（在诺税通saas工作台配置过的可以不传，以传入的为准）
        orderdetail.put("callBackUrl","http://39.101.182.84:8899/nuonuo/token/noticefp");
        orderdetail.put("buyerName",params.get("buyname").toString());//购方名称
        orderdetail.put("invoiceDate",params.get("voucherdate").toString());//订单时间
        //orderdetail.put("salerAddress","上海市松江区九亭镇伴亭路488号4幢5层510室");
        orderdetail.put("clerk","管理员");//开票员（数电票时需要传入和开票登录账号对应的开票员姓名）
        orderdetail.put("salerTaxNum",params.get("taxnum").toString()); // 授权企业税号

        List<Map<String,Object>> invoiceDetail = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> invenMap : invenlist){
            Map<String,Object> invoice = new HashMap<String,Object>();
            invoice.put("num",invenMap.get("quantity").toString());
            invoice.put("withTaxFlag","1");
            invoice.put("taxRate","0.13");
            invoice.put("unit",invenMap.get("unitname").toString());
            invoice.put("price",invenMap.get("taxamount").toString());//零售单明细的含税金额
            invoice.put("goodsCode",invenMap.get("ssflbm").toString());//税收分类编码
            invoice.put("goodsName",invenMap.get("goodsname").toString());//商品名称
            invoiceDetail.add(invoice);
        }
        orderdetail.put("invoiceDetail",invoiceDetail);
        order.put("order",orderdetail);
        JSONObject job = new JSONObject(order);
        return  job.toJSONString();
    }

    public static void main(String[] args) {
        getToken();
        /*NNOpenSDK sdk = NNOpenSDK.getIntance();
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
                "        \"goodsCode\": \"TEST001\",\n" +
                "        \"goodsName\": \"华为笔记本电脑3\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"num\": \"1\",\n" +
                "        \"withTaxFlag\": \"1\",\n" +
                "        \"taxRate\": \"0.13\",\n" +
                "        \"unit\": \"台\",\n" +
                "        \"price\": \"2\",\n" +
                "        \"goodsCode\": \"TEST002\",\n" +
                "        \"goodsName\": \"华为笔记本电脑4\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";

        System.out.println(content);
        //正式环境	https://sdk.nuonuo.com/open/v1/services
        //沙箱环境	https://sandbox.nuonuocs.cn/open/v1/services

        String url = "https://sdk.nuonuo.com/open/v1/services"; // SDK请求地址
        String senid = UUID.randomUUID().toString().replace("-", ""); // 唯一标识，32位随机码，无需修改，保持默认即可
        String result = sdk.sendPostSyncRequest(url, senid, appKey, appSecret, token, taxnum, method, content);
        System.out.println(result);*/
    }
}
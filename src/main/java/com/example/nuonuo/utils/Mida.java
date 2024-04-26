package com.example.nuonuo.utils;


import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.Meituan.MeituanPeiSong;
import com.jiujin.scm.open.sdk.StringUtils;
import com.jiujin.scm.open.sdk.bo.CreatedOrderBo;
import com.jiujin.scm.open.sdk.client.MidaOpenClient;
import com.jiujin.scm.open.sdk.client.OpenClient;
import com.jiujin.scm.open.sdk.request.CreatedOrderRequest;
import com.jiujin.scm.open.sdk.response.GetBatchOrderResponse;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Mida {

    //正式环境	https://open.cangaunwuyou.com/router/rest
    //测试环境	https://dev-scm.canguanwuyou.com/router/rest

    public static String url = "https://dev-scm.canguanwuyou.com/router/rest";
    public static String appKey = "7AMDRzbgKAfTNhgbwU7vCXTMBv90m5eM";
    public static String secret = "84IKqxInlKa9KrXnfNAF82sGWSlcEGPWacAZuA9mtKcxBq8ZJNkdKB7FTD5I3dE5";
    public static OpenClient client = new MidaOpenClient(url, appKey, secret);

    public static void main(String[] args) throws Exception{

        //组装请求参数
        Map<String, String> sortedParams = new HashMap<String, String>();
        sortedParams.put("app_key",Mida.appKey);
        sortedParams.put("method","mida.order.create");
        sortedParams.put("sign_method","md5");
        sortedParams.put("version","1.0.0");
        sortedParams.put("biz_data","{\"address\":\"四川省成都市锦江区罗妈砂锅九眼桥店一环路东五段46号附 18号\",\"customerOrderNo\":\"PS2404190066\",\"orderItems\":[{\"itemCode\":\"ZBWP0142\",\"qty\":1}]}");
        //sortedParams.put("format","json");
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        sortedParams.put("timestamp",timestamp);
        String sign = Mida.signTopRequest(sortedParams,Mida.secret,"MD5");
        sortedParams.put("sign",sign);
        System.out.println("sign == " + sign);
        String mifhddreStr = HttpClient.MeiTuansendPostRequest(Mida.url,sortedParams);
        System.out.println("mifhddreStr == " + mifhddreStr);


        /*String decodeXmlStr = "{\"rootOrgId\":2058118,\"pushEventId\":\"ppLj9sxIhmquobX8HYmVGQ==\",\"bizId\":\"PS2404190066\",\"opType\":1,\"seqId\":100,\"bizData\":{\"item\":{\"rootOrgId\":2058118,\"itemSn\":\"PS2404190066\",\"orderTime\":1713456001000,\"expectTime\":1713542401000,\"createTime\":1713506703000,\"modifyTime\":1713506703000,\"demandOrg\":{\"orgId\":2036590,\"rootOrgId\":2058118,\"code\":\"MD00001\",\"name\":\"【成都直营】九眼桥店\"},\"supplyOrg\":{\"orgId\":2038410,\"rootOrgId\":2058118,\"code\":\"PS00001\",\"name\":\"总部配送中心\"},\"deliverOrg\":{\"type\":{\"id\":2,\"name\":\"组织机构\"},\"code\":\"PS00001\",\"name\":\"总部配送中心\"},\"receiveOrg\":{\"orgId\":2036590,\"rootOrgId\":2058118,\"code\":\"MD00001\",\"name\":\"【成都直营】九眼桥店\"},\"receiverInfo\":{\"contactName\":\"吴润芝\",\"address\":\"四川省成都市锦江区罗妈砂锅九眼桥店一环路东五段46号附 18号\"},\"sourceSns\":[\"DH2404190006\"],\"generateReceiveOrderSns\":[],\"money\":\"2375.00\",\"noTaxMoney\":\"2375.00\",\"tax\":\"0.00\",\"creator\":1,\"status\":301,\"remark\":\"\",\"bizModel\":1,\"version\":1,\"deleteStatus\":0,\"expectArriveTime\":1713542401000},\"details\":[{\"id\":\"nU6uUUD5hZ4 //rXXIXMVQ 0zcZDps/V2DTpG8dUX c=\",\"seqId\":0,\"goodsInfo\":{\"id\":\"2BBBDIyhXGjGtWbx5k5xCg==\",\"code\":\"ZBWP0142\",\"name\":\"麻婆豆腐酱料\",\"category\":{\"id\":\"ug/ikjfIY1y4nV3AyFyrmw==\",\"code\":\"ZBLB011\",\"name\":\"罗妈底料酱料\"},\"spec\":\"1KG*15袋/件\",\"baseUnit\":{\"id\":\"r6tkdvl5JgNp42YIXSJpzg==\",\"code\":\"ZBDW007\",\"name\":\"袋\"},\"units\":[{\"id\":\"r6tkdvl5JgNp42YIXSJpzg==\",\"code\":\"ZBDW007\",\"name\":\"袋\"},{\"id\":\"b 7w71A4 pqHKGfX7paheg==\",\"code\":\"ZBDW004\",\"name\":\"件\"}],\"version\":3002},\"deliverWarehouseInfo\":{\"id\":\"AcZ4Z3fgvpn6hQ8XP9SZcg==\",\"code\":\"ZBCK02\",\"name\":\"成都配送仓\"},\"baseUnit\":{\"id\":\"r6tkdvl5JgNp42YIXSJpzg==\",\"code\":\"ZBDW007\",\"name\":\"袋\"},\"bizUnit\":{\"id\":\"b 7w71A4 pqHKGfX7paheg==\",\"code\":\"ZBDW004\",\"name\":\"件\"},\"demandAmount\":{\"baseUnitAmount\":\"75\",\"bizUnitAmount\":\"5\"},\"receiveOrderAmount\":{\"baseUnitAmount\":\"75\",\"bizUnitAmount\":\"5\"},\"deliverAmount\":{\"baseUnitAmount\":\"75\",\"bizUnitAmount\":\"5\"},\"demandPrice\":\"475\",\"demandNoTaxPrice\":\"475\",\"baseUnitPrice\":\"31.67\",\"deliverMoney\":\"2375.00\",\"deliverNoTaxMoney\":\"2375.00\",\"demandMoney\":\"2375.00\",\"taxRate\":\"0\",\"tax\":\"0.00\",\"remark\":\"\",\"originalPrice\":\"475\",\"originalMoney\":\"2375.00\",\"noTaxOriginalPrice\":\"475\",\"noTaxOriginalMoney\":\"2375.00\",\"campaignPromotionMoney\":\"0.00\",\"manualPromotionMoney\":\"0.00\",\"actuallyPayPrice\":\"475\",\"couponPromotionMoney\":\"0.00\",\"grantPaymentPromotionMoney\":\"0.00\",\"promotedPrice\":\"475\",\"promotedMoney\":\"2375.00\",\"noTaxPromotedPrice\":\"475\",\"noTaxPromotedMoney\":\"2375.00\",\"giftType\":2}]},\"largeMessageTag\":false}";
        MeituanPeiSong meituanPeiSong = JSONObject.parseObject(decodeXmlStr,MeituanPeiSong.class);
        CreatedOrderBo orderInfo = new CreatedOrderBo();
        String address = meituanPeiSong.getBizData().getItem().getReceiverInfo().getAddress();
        orderInfo.setAddress(address);
        orderInfo.setCustomerOrderNo(meituanPeiSong.getBizData().getItem().getItemSn());
        List<CreatedOrderBo.OrderItem> orderItems = new ArrayList<CreatedOrderBo.OrderItem>();
        CreatedOrderBo.OrderItem orderItem2 = new CreatedOrderBo.OrderItem();
        orderItem2.setItemCode(meituanPeiSong.getBizData().getDetails().get(0).getGoodsInfo().getCode());//ZBWP0145  ZBWP0142
        orderItem2.setQty(1);
        orderItems.add(orderItem2);
        orderInfo.setOrderItems(orderItems);
        CreatedOrderRequest requestt = new CreatedOrderRequest();
        requestt.setOrderBo(orderInfo);
        GetBatchOrderResponse responsee = Mida.client.execute(requestt);
        String responseBody = responsee.getBody();
        String getReqUrl = responsee.getReqUrl();
        System.out.println("????--------------- getReqUrl： " + getReqUrl);
        System.out.println("????--------------- responseBody： " + responseBody);*/


        // 错 https://dev-scm.canguanwuyou.com/router/rest?app_key=7AMDRzbgKAfTNhgbwU7vCXTMBv90m5eM&method=mida.order.create&sign_method=md5&sign=A0CDBCD3A91F453104D27F1AE095935F&biz_data={"address":"四川省成都市锦江区罗妈砂锅九眼桥店一环路东五段46号附 18号","customerOrderNo":"PS2404190066","orderItems":[{"itemCode":"ZBWP0142","qty":1}]}&version=1.0.0&timestamp=2024-04-19 17:44:46

        // 对 https://dev-scm.canguanwuyou.com/router/rest?app_key=7AMDRzbgKAfTNhgbwU7vCXTMBv90m5eM&method=mida.order.create&sign_method=md5&sign=EB19858C70952292FD925E639FCA24B1&biz_data={"address":"四川省成都市锦江区罗妈砂锅九眼桥店一环路东五段46号附 18号","customerOrderNo":"PS2404190066","orderItems":[{"itemCode":"ZBWP0142","qty":1}]}&version=1.0.0&timestamp=2024-04-19 17:44:13
        // 对 https://dev-scm.canguanwuyou.com/router/rest?app_key=7AMDRzbgKAfTNhgbwU7vCXTMBv90m5eM&method=mida.order.create&sign_method=md5&sign=34C11F763371D634A448F504E1F0C973&biz_data={"address":"四川省成都市锦江区罗妈砂锅九眼桥店一环路东五段46号附 18号","customerOrderNo":"PS2404190066","orderItems":[{"itemCode":"ZBWP0142","qty":1}]}&version=1.0.0&timestamp=2024-04-19 17:54:53
    }

    public static String signTopRequest(Map<String, String> sortedParams, String secret, String signMethod) {
        //第一步: 参数排序
        List<String> keys = new ArrayList<>(sortedParams.keySet());
        Collections.sort(keys);

        //第二步:把所有参数名和参数值串在一起
        StringBuilder query = new StringBuilder();
        query.append(secret);
        for (String key : keys) {
            String value = sortedParams.get(key);
            if (StringUtils.areNotEmpty(key, value)) {
                query.append(key).append(value);
            }
        }
        query.append(secret);
        //第三步: MD5加密  Constants.SIGN_METHOD_HMAC.equals(signMethod)
        if ("MD5".equals(signMethod) || "md5".equals(signMethod)) {
            return encryptMD5(query.toString());
        }
        return null;
    }

    public static String encryptMD5(String data) {
        try {
            // 拿到MD转换器
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = data.getBytes("UTF-8");
            messageDigest.update(inputByteArray);

            byte[] resultByteArray = messageDigest.digest();

            return byteArrayToHex(resultByteArray);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String byteArrayToHex(byte[] byteArray) {
        // 初始化一个字符数组，用来存放每个16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        char[] resultCharArray = new char[byteArray.length * 2];

        int index = 0;
        // 遍历字节数组，通过位运算，转换成字符放到字符数据中
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }
}
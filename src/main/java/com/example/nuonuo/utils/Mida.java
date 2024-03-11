package com.example.nuonuo.utils;


import com.jiujin.scm.open.sdk.StringUtils;
import com.jiujin.scm.open.sdk.bo.CreatedOrderBo;
import com.jiujin.scm.open.sdk.client.MidaOpenClient;
import com.jiujin.scm.open.sdk.client.OpenClient;
import com.jiujin.scm.open.sdk.request.CreatedOrderRequest;
import com.jiujin.scm.open.sdk.response.GetBatchOrderResponse;
import org.springframework.util.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Mida {
    //正式环境	https://open.cangaunwuyou.com/router/rest
    //测试环境	https://qa-scm.canguanwuyou.com/router/rest

    static String url = "https://qa-scm.canguanwuyou.com/router/rest";
    static String appKey = "123";
    static String secret = "345";
    static OpenClient client = new MidaOpenClient(url, appKey, secret);

    public static void main(String[] args) {

        //创建发货单
        CreatedOrderRequest request = new CreatedOrderRequest();
        CreatedOrderBo orderInfo = new CreatedOrderBo();
        orderInfo.setCustomerOrderNo("CD001111111");
        orderInfo.setAddress("天府一街369号");
        List<CreatedOrderBo.OrderItem> items = new ArrayList<>();
        CreatedOrderBo.OrderItem item = new CreatedOrderBo.OrderItem();
        item.setQty(10);
        item.setSkuId("1694591762138746882");
        items.add(item);
        orderInfo.setOrderItems(items);
        request.setOrderBo(orderInfo);
        GetBatchOrderResponse response = client.execute(request);
        System.out.println("response == " + response.toString());
        Assert.notNull(response.getPayload());

        // 获取订单详情
        //GetOrderInfoRequest request = new GetOrderInfoRequest();
        //OrderInfo orderInfo = new OrderInfo();
        //orderInfo.setOrderId(1760957982058590209L);
        //request.setOrderInfo(orderInfo);
        //GetOrderInfoResponse response = client.execute(request);
        //Assert.assertNotNull(response.getPayload());
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
        if ("MD5".equals(signMethod)) {
            return encryptMD5(query.toString());
        }
        return null;
    }

    private static String encryptMD5(String data) {
        try {
            // 拿到MD转换器
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = data.getBytes();
            messageDigest.update(inputByteArray);

            byte[] resultByteArray = messageDigest.digest();

            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String byteArrayToHex(byte[] byteArray) {
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
package com.example.nuonuo.utils;


import com.jiujin.scm.open.sdk.StringUtils;
import com.jiujin.scm.open.sdk.bo.CreatedOrderBo;
import com.jiujin.scm.open.sdk.client.MidaOpenClient;
import com.jiujin.scm.open.sdk.client.OpenClient;
import com.jiujin.scm.open.sdk.request.CreatedOrderRequest;
import com.jiujin.scm.open.sdk.response.GetBatchOrderResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Mida {

    //正式环境	https://open.cangaunwuyou.com/router/rest
    //测试环境	https://dev-scm.canguanwuyou.com/router/rest

    public static String url = "https://dev-scm.canguanwuyou.com/router/rest";
    public static String appKey = "7AMDRzbgKAfTNhgbwU7vCXTMBv90m5eM";
    public static String secret = "84IKqxInlKa9KrXnfNAF82sGWSlcEGPWacAZuA9mtKcxBq8ZJNkdKB7FTD5I3dE5";
    public static OpenClient client = new MidaOpenClient(url, appKey, secret);

    public static void main(String[] args) throws Exception{

        //创建发货单
        CreatedOrderBo orderInfo = new CreatedOrderBo();
        String address = "四川省成都市锦江区罗妈砂锅九眼桥店一环路东五段46号附 18号";
        orderInfo.setAddress(address);
        orderInfo.setCustomerOrderNo("PS2404100005");
        List<CreatedOrderBo.OrderItem> orderItems = new ArrayList<CreatedOrderBo.OrderItem>();

        CreatedOrderBo.OrderItem orderItem1 = new CreatedOrderBo.OrderItem();
        orderItem1.setItemCode("ZBWP0137");
        orderItem1.setQty(456);
        orderItems.add(orderItem1);

        CreatedOrderBo.OrderItem orderItem2 = new CreatedOrderBo.OrderItem();
        orderItem2.setItemCode("ZBWP0142");
        orderItem2.setQty(123);
        orderItems.add(orderItem2);

        orderInfo.setOrderItems(orderItems);
        CreatedOrderRequest request = new CreatedOrderRequest();
        request.setOrderBo(orderInfo);
        GetBatchOrderResponse response = Mida.client.execute(request);
        String responseBody = response.getBody();
        System.out.println("--------------- 米大的订单下推后返回结果是： " + responseBody);

        // 获取订单详情
        /*GetOrderInfoRequest request = new GetOrderInfoRequest();
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(1760957982058590209L);
        request.setOrderInfo(orderInfo);
        GetOrderInfoResponse response = client.execute(request);
        String responseBody = response.getBody();
        System.out.println("responseBody == " + responseBody);*/


        // 米大 获取库存详情
        /*GetSkuStockInfoRequest request = new GetSkuStockInfoRequest();
        SkuStockInfo skuStockInfo = new SkuStockInfo();
        skuStockInfo.setWarehouseCode("CD01");
        skuStockInfo.setSkuId("1694591762138746882");
        request.setStockInfo(skuStockInfo);
        GetSkuStockInfoResponse response = client.execute(request);
        String responseBody = response.getBody();
        System.out.println("responseBody == " + responseBody);*/

        //批量根据skuid 获取 库存详情
        //GetBatchSkuStockRequest request = new GetBatchSkuStockRequest();
        //BatchSkuStockInfo batchSkuStockInfo = new BatchSkuStockInfo();



        /*String message = "[{\"stockId\":\"1694594374825234433\",\"availableQty\":1078,\"orderReservedQty\":20,\"transReservedQty\":1,\"notForSaleReservedQty\":0,\"warehouseCode\":\"CD01\",\"skuId\":\"1694591762138746882\",\"skuName\":\"四川口口脆\",\"status\":\"ON_SHELF\",\"batchList\":null}]";
        List<WarehouseStockBo> midaruku = JSONObject.parseArray(message,WarehouseStockBo.class);
        System.out.println(midaruku.size());
        System.out.println(midaruku.get(0).getSkuName());*/

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
package com.example.nuonuo.utils;

import com.alibaba.fastjson.JSONObject;
import com.meituan.sdk.DefaultMeituanClient;
import com.meituan.sdk.MeituanClient;
import com.meituan.sdk.MeituanResponse;
import com.meituan.sdk.internal.exceptions.MtSdkException;
import com.meituan.sdk.model.klOpen.goods.goodsPageSku.GoodsPageSkuRequest;
import com.meituan.sdk.model.resv2.order.queryOrder.QueryOrderRequest;
import com.meituan.sdk.model.tuangouNg.coupon.couponQueryLocalListByDate.CouponQueryLocalListByDateRequest;
import com.meituan.sdk.model.tuangouNg.coupon.couponQueryLocalListByDate.CouponQueryLocalListByDateResponse;
import com.meituan.sdk.model.tuangouNg.coupon.couponQueryLocalListByDate.EOrders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MeiTuanUtil {

    // 以接口[门店本地验券历史](https://developer.meituan.com/docs/api/tuangou-coupon-queryLocalListByDate) 为例，
    // JDK提供了类***CouponQueryLocalListByDateRequest***来封装请求，您调用此接口的代码可以参考：
    public static void main(String[] args) throws Exception{

        /*String signKey = "wybkd1o9lsh99ttx";//你的signkey，注意替换
        Map<String,String> param = new HashMap();
        param.put("developerId","112274");
        param.put("businessId","18");
        String timestamp = "1712043215";//+(System.currentTimeMillis()/1000);
        param.put("timestamp",timestamp);
        System.out.println("timestamp == " + timestamp);
        param.put("charset","UTF-8");
        String sign = "8fea2970947bc45f156d4b5d63521e5acd52f365";//SignUtil.getSign(signKey,param);
        System.out.println("sign == " + sign);*/
        //授权链接DEMO  商户管理员 才可以授权。
        //https://open-erp.meituan.com/general/auth?developerId=112274&timestamp=1712043215&charset=UTF-8&businessId=18&sign=8fea2970947bc45f156d4b5d63521e5acd52f365


        //拿到code后，先算出 sign，再 POST 请求 获取 token !
        /*String code = "fc91db641572ff7fe666dcb92196bf35";
        param.put("code",code);
        param.put("grantType","authorization_code");
        String Tokensign = SignUtil.getSign(signKey,param);
        param.put("sign",Tokensign);
        String url = "https://api-open-cater.meituan.com/oauth/token";
        String res = HttpClient.MeiTuansendPostRequest(url,param);
        JSONObject jobres = JSONObject.parseObject(res);
        String data = jobres.getString("data");
        String accessToken = JSONObject.parseObject(data).getString("accessToken");
        String refreshToken = JSONObject.parseObject(data).getString("refreshToken");
        String expireIn = JSONObject.parseObject(data).getString("expireIn");
        System.out.println("accessToken == " + accessToken); //  30 天
        System.out.println("refreshToken == " + refreshToken);
        System.out.println("expireIn == " + expireIn);*/


        /*Map<String,String> parma = new HashMap();
        String signKey = "wybkd1o9lsh99ttx";
        String timestamp = ""+(System.currentTimeMillis()/1000);
        parma.put("timestamp",timestamp);
        parma.put("scope","all");
        parma.put("developerId","112274");
        parma.put("charset","UTF-8");
        parma.put("businessId","18");
        parma.put("grantType","refresh_token");
        String oldrefreshtoken = "7e34e3463901eafa8241d9b7316d48a097f8c5525121be1df2f8be26a269a0fb9f23a51f90030e89e5822a9f4c963a9c1d2647234142a8cf657d66f9b1375ac4";
        parma.put("refreshToken",oldrefreshtoken);
        String sign = SignUtil.getSign(signKey,parma);
        parma.put("sign",sign);
        String url = "https://api-open-cater.meituan.com/oauth/refresh";
        String res = HttpClient.MeiTuansendPostRequest(url,parma);
        JSONObject jobres = JSONObject.parseObject(res);
        String data = jobres.getString("data");
        System.out.println("data == " + data);
        String accessToken = JSONObject.parseObject(data).getString("accessToken");
        System.out.println("accessToken == " + accessToken);
        String refreshToken = JSONObject.parseObject(data).getString("refreshToken");
        System.out.println("refreshToken == " + refreshToken);*/


        //测试调用美团的普通接口
        Map<String, String> signMap = new HashMap<String, String>();
        //String url = "https://api-open-cater.meituan.com/rms/scmplus/inventory/api/v1/chain/warehouse/list";
        String url = "https://api-open-cater.meituan.com/rms/scmplus/organization/api/v1/chain/organization/query";
        signMap.put("businessId","18");
        signMap.put("charset","utf-8");
        signMap.put("developerId","112274");
        signMap.put("timestamp",""+(System.currentTimeMillis()/1000));
        signMap.put("version","2");
        //signMap.put("biz","");//2058118   2038410
        signMap.put("biz","{\"orgId\":2058118,\"page\":{\"pageNo\":1,\"pageSize\":1,\"totalCount\":1000,\"totalPageSize\":1000}}");
        signMap.put("appAuthToken","V2-46a4b3ed23a6472608073aea082d382e7f65f22daf459c0ae3019d77d0609ab08d2c3a14b18180e4d6bed32232ac149b8e1e199640febf38819f2814d611b1fd");
        String sign = SignUtil.getSign("wybkd1o9lsh99ttx",signMap);
        signMap.put("sign",sign);
        String result = HttpClient.MeiTuansendPostRequest(url,signMap);
        System.out.println("result == " + result);

        /*Long developerId = 112274L;
        String signKey = "wybkd1o9lsh99ttx";
        String appAuthToken = "xxxxxxxxxxxxx";
        //构造meituanClient，推荐使用单例方式，一个develoepr只使用一个实例
        MeituanClient meituanClient = DefaultMeituanClient.builder(developerId, signKey).build();
        CouponQueryLocalListByDateRequest request = new CouponQueryLocalListByDateRequest();
        request.setDate("2020-12-15");
        request.setOffset(0);
        request.setLimit(10);
        try {
            //发起接口调用
            MeituanResponse<CouponQueryLocalListByDateResponse> response = meituanClient.invokeApi(request, appAuthToken);
            //判断是调用是否成功
            if (response.isSuccess()) {
                //调用成功，通过getData获取接口响应数据
                CouponQueryLocalListByDateResponse localListByDateResponse = response.getData();
                List<EOrders> eOrders = localListByDateResponse.getEOrders();
                for(EOrders eOrder: eOrders) {
                    System.out.println(eOrder);
                }
            } else {
                //调用失败，通过getCode和getMsg获取错误码和错误描述
                System.out.println(response.getCode());
                System.out.println(response.getMsg());
            }
        } catch (MtSdkException e) {
            e.printStackTrace();
        }*/

    }
}

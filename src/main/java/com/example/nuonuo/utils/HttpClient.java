package com.example.nuonuo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.tiancai.TcchukuReturn;
import com.example.nuonuo.entity.tiancaicg.Tccg;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class HttpClient {

    public final static String openurl = "https://openapi.chanjet.com";

    public final static String EAuthorization = "bearer pQK-zHustWIG2svK6yGVw3GU4xugwj0O9Joja03uoGjvBABPWJNekdrU1CL36_wIfgTKwNm9JBg3dxnpZNLq871g2FPNgg5LHKAEUqOgzaKxVgNNIfSVTglyyIU6OyLvhSZvV8H8UPjaHFZOvH-AvYv2gJknFSIdy_R5Io6iqhRa-o_T_nWVfPNUv_f6iM-UddTPFW9m5-4o3-boN3UbZ_vHvTQ5rPplkzUCGN9aQJSdNnyzVEyjeC-ntdR71epQb_3mopNDkMJ8wUFD-bQWrtGnn9NulAcSYaMyv3iY5Q4o0T_-e03eVwfBCtbue6wfeIsPumgo3u1SgHpomnjENc3uRJeBQnriF2FtJNzmtL0.eyJ0aWQiOiIwMjcxNTZiYy1iOGNmLTRkNzItYmFjNC0wZDcyMGQ4ZmIwNDkifQ==";

    public static void main(String[] args) throws Exception{
        /*Map<String,String> params = new HashMap<String,String>();
        params.put("ticket","z7sctvS7Nd%2bP7iE4x0DU34trNfgZ0fXr8XLu6QiUmtN5cYbpnzvaJCNeuI2NAmza3%2bI4Z6bO5y7TVSZDl5zwljOeUr6G8mX%2fFrRHZso%2fRyhnXAtC6KsQxt88GSTJFPkVsIyyTBW8EBdK0kIPSfejOwt4PzwwHxusz9QDnfiPOALiTAMeq3OHpiYrtukGs7pmyLpcU6cPUeO%2bLheHvdl1Hv60enF2kPkdLsh7IE4tMSvlu7TGiC7eJx2f10ciEcvIAZatQxibMH0ViUDC5tuqkPa0E207p62yE7mrqcKieAaVGQMKxvRw5A6f2ULjg9at28NmfuBqvhhJYPRR8M3z%2fmzzRBLD0y%2b1XTyjutkLaJVcmmnH2tapl34kdscuw8adhTEJp%2fiHzOY4RQME0YFOQ2hqOAcgRrLh14N16dYODVQXs3aghnO173NulihX6R3m");
        params.put("tenantId","027156bc-b8cf-4d72-bac4-0d720d8fb049");

        Ekanya ekanya = new Ekanya("z7sctvS7Nd%2bP7iE4x0DU34trNfgZ0fXr8XLu6QiUmtN5cYbpnzvaJCNeuI2NAmza3%2bI4Z6bO5y7TVSZDl5zwljOeUr6G8mX%2fFrRHZso%2fRyhnXAtC6KsQxt88GSTJFPkVsIyyTBW8EBdK0kIPSfejOwt4PzwwHxusz9QDnfiPOALiTAMeq3OHpiYrtukGs7pmyLpcU6cPUeO%2bLheHvdl1Hv60enF2kPkdLsh7IE4tMSvlu7TGiC7eJx2f10ciEcvIAZatQxibMH0ViUDC5tuqkPa0E207p62yE7mrqcKieAaVGQMKxvRw5A6f2ULjg9at28NmfuBqvhhJYPRR8M3z%2fmzzRBLD0y%2b1XTyjutkLaJVcmmnH2tapl34kdscuw8adhTEJp%2fiHzOY4RQME0YFOQ2hqOAcgRrLh14N16dYODVQXs3aghnO173NulihX6R3m","027156bc-b8cf-4d72-bac4-0d720d8fb049");
        String result = doPostTestTwo("https://openapi-gw.linkedcare.cn/public/v1/auth/token",ekanya);
        System.out.println("result == " + result);
        JSONObject job = JSONObject.parseObject(result);
        System.out.println("token == " + job.get("token").toString());*/

        /*Map<String,String> parma = new HashMap<String,String>();
        parma.put("officeId","138");
        String Authorization = "bearer WNrAbg29qqr7f_wx-gl8fAJ0M5VSSOs8cm_jMf-Ix3TIz0h7IbyM7tDAY_SqU5xDlCjYOt_wBzMDRiLQapgSpQMwzFfjQ8UoIvMfFCGfDWjNgbyakNKE_dpipEZtfgoCp8Ap_O_LjuE1jEzqySYhxJB7w3fUpLispfU0b1rpENY_qkvbjSvOBCt1W135bgBixX9ylTRBhnQRTpldn0-Yrptdvn86Jp8U4sc-9lRzzHVvXFkNrUUPQq-_bYtPnET6RYX2JW6OosLcqSfLCvprsye9lxzF-5KazTAR_8sUbayXVqUVjljU9XUCEP__eHDM81hn9Xw4w1nx7TI46ZMbg4AYxSW-03GDVArievMaM1A.eyJ0aWQiOiIwMjcxNTZiYy1iOGNmLTRkNzItYmFjNC0wZDcyMGQ4ZmIwNDkifQ==";
        //public/v2/billing/payment-method-config/by-office
        String s1 = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/billing/payment-method-config/by-office", parma,Authorization);
        System.out.println("s1 == " + s1);*/


        /*Map<String,String> parma = new HashMap<String,String>();
        parma.put("officeId","125"); // 138  104  125
        //String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        parma.put("startTime","2023-09-10");//当日
        parma.put("endTime","2023-09-10");//当日
        String Authorization = HttpClient.EAuthorization;
        String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/crm/patient/query/window", parma,Authorization);
        System.out.println("result == " + result);

        //将这个json字符串转换成java对象，方便进行数据 处理！
        Euser euser = JSON.parseObject(result, Euser.class);*/


        /*Map<String,String> parma = new HashMap<String,String>();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        parma.put("startTime","2023-09-01");//当日
        parma.put("endTime","2023-09-11");//当日
        parma.put("officeId","104"); // 138 125  104
        String Authorization = HttpClient.EAuthorization;
        String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/billing/bill-deduction/flow", parma,Authorization);
        System.out.println("res == " + result);
        ESaleRoot eSaleRoot = JSON.parseObject(result, ESaleRoot.class);*/


        //查询 E看牙 员工档案信息
        /*Map<String,String> parma = new HashMap<String,String>();
        parma.put("providerId","2845"); // 138 125  104
        String Authorization = HttpClient.EAuthorization;
        String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/pms/provider/query/by-id", parma,Authorization);
        System.out.println("res == " + result);*/


        //查询 E看牙 指定患者档案信息
        /*Map<String,String> parma = new HashMap<String,String>();
        parma.put("patientId","1468182");
        String Authorization = HttpClient.EAuthorization;
        String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/crm/patient/query/by-id", parma,Authorization);
        System.out.println("res == " + result);*/

        /*Map<String,String> parma = new HashMap<String,String>();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        parma.put("officeId","104");
        parma.put("patientId","1315477");
        String Authorization = HttpClient.EAuthorization;
        String result = HttpClient.doGeturlparams("https://openapi-gw.linkedcare.cn/public/v2/gift/stored-value-card-transaction/query/by-patient", parma,Authorization);
        System.out.println("result == " + result);*/

        /*TTTT ttt = new TTTT();
        ttt.setOfficeId("104");
        String[] paymentIds = {"2787883"};
        ttt.setOfficeId("104");
        ttt.setPaymentIds(paymentIds);
        String Authorization = HttpClient.EAuthorization;
        String result = HttpClient.doPostEkanya("https://openapi-gw.linkedcare.cn/public/v2/billing/payment/by-ids", ttt,Authorization);
        System.out.println("result == " + result);*/
    }

    /**
     * GET---无参测试
     *
     * @date
     */

    public static void  doGetNoparam() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet("http://localhost:12345/");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            System.out.println("连接失败"+e.getMessage());
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * GET---有参测试 (方式一:手动在url后面加上参数)
     *
     * @date
     */
    public static String doGeturlparams(String url, Map<String,String> params,String Authorization)throws IOException {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        CloseableHttpResponse response = null;
        String result = "";
        try {
            StringBuilder ss = new StringBuilder("");
            for (String keys : params.keySet()) {
                String values = params.get(keys);
                // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
                ss.append(keys+"=").append(values).append("&");
            }
            String paramsstr = ss.substring(0,ss.length()-1);
            // 创建Get请求
            System.out.println("请求地址是： "+ url + "?" + paramsstr );
            HttpGet httpGet = new HttpGet(url + "?" + paramsstr);
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(50000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(50000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(50000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();
            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);
            if(Authorization != null && !"".equals(Authorization) && !"null".equals(Authorization)){
                httpGet.setHeader("Authorization", Authorization);
            }

            
            httpGet.setHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)");


            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                //System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                result = EntityUtils.toString(responseEntity);
                //System.out.println("响应内容为:" + result);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            result = "访问异常！！！";
        } finally {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
        }
        return result;
    }

    /**
     * GET---有参测试 (方式二:将参数放入键值对类中,再放入URI中,从而通过URI得到HttpGet实例)
     *
     * @date
     */
    public void doGetTestWayTwo() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("name", "&"));
            params.add(new BasicNameValuePair("age", "18"));
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("http").setHost("localhost")
                    .setPort(12345).setPath("/doGetControllerTwo")
                    .setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        // 创建Get请求
        HttpGet httpGet = new HttpGet(uri);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * POST---无参测试
     *
     * @date
     */
    public void doPostTestOne(String url) {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * POST---有参测试(普通参数)
     *
     * @date
     */
    public static String doPostTestFour(String url,Map<String,String> map) {
        String result = "";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 参数
        StringBuilder params = new StringBuilder();
        try {
            // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
            params.append("json=").append(URLEncoder.encode(map.get("json"), "utf-8"));
            //params.append("&");
            //params.append("json=admin");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        // 创建Post请求
        System.out.println("请求 URL == " + url+ "&" + params);
        HttpPost httpPost = new HttpPost(url + "&" + params);
        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                result = EntityUtils.toString(responseEntity);
                System.out.println("响应内容为:" + result);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * POST---有参测试(对象参数)
     *
     * @date
     */
    public static String doPostTestTwo(String url, Tccg tccg) {
        String reslut = "不应该返回这个哦！";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        // 我这里利用阿里的fastjson，将Object转换为json字符串;(需要导入com.alibaba.fastjson.JSON包)
        String jsonString = JSONObject.toJSONString(tccg);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                //System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                reslut = EntityUtils.toString(responseEntity);
                //System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                return reslut;
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return "error!!!!";
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  reslut;
        }
    }

    public static String doPostTestThree(String url, TcchukuReturn tcchukuReturn) {
        String reslut = "不应该返回这个哦！";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        // 我这里利用阿里的fastjson，将Object转换为json字符串;(需要导入com.alibaba.fastjson.JSON包)
        String jsonString = JSONObject.toJSONString(tcchukuReturn);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                //System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                reslut = EntityUtils.toString(responseEntity);
                //System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                return reslut;
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return "error!!!!";
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  reslut;
        }
    }

    /**
     * POST---XML
     *
     * @date
     */
    public static String doPostXMLTEST(String url, Map<String,String> map,String xmlbody) throws Exception{
        String result = "";
        try {
            URL apiUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/xml");

            connection.setRequestProperty("app_key", map.get("app_key"));
            connection.setRequestProperty("customerId", map.get("customerId"));
            connection.setRequestProperty("format", map.get("format"));
            connection.setRequestProperty("method", map.get("method"));
            connection.setRequestProperty("partner_id", map.get("partner_id"));
            connection.setRequestProperty("sign_method", map.get("sign_method"));
            connection.setRequestProperty("timestamp", map.get("timestamp"));
            connection.setRequestProperty("v", map.get("v"));
            connection.setRequestProperty("sign", map.get("sign"));

            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(xmlbody.getBytes());
            outputStream.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            result = response.toString();
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            result = "天财订单下发给弘人WMS进行发货出库 失败！！！";
        }
        return result;
    }


    /**
     *
     * 发送文件
     *
     * multipart/form-data传递文件(及相关信息)
     *
     * 注:如果想要灵活方便的传输文件的话，
     *    除了引入org.apache.httpcomponents基本的httpclient依赖外
     *    再额外引入org.apache.httpcomponents的httpmime依赖。
     *    追注:即便不引入httpmime依赖，也是能传输文件的，不过功能不够强大。
     *
     */

    public void test4() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:12345/file");
        CloseableHttpResponse response = null;
        try {
            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            // 第一个文件
            String filesKey = "files";
            File file1 = new File("C:\\Users\\JustryDeng\\Desktop\\back.jpg");
            multipartEntityBuilder.addBinaryBody(filesKey, file1);
            // 第二个文件(多个文件的话，使用同一个key就行，后端用数组或集合进行接收即可)
            File file2 = new File("C:\\Users\\JustryDeng\\Desktop\\头像.jpg");
            // 防止服务端收到的文件名乱码。 我们这里可以先将文件名URLEncode，然后服务端拿到文件名时在URLDecode。就能避免乱码问题。
            // 文件名其实是放在请求头的Content-Disposition里面进行传输的，如其值为form-data; name="files"; filename="头像.jpg"
            multipartEntityBuilder.addBinaryBody(filesKey, file2, ContentType.DEFAULT_BINARY, URLEncoder.encode(file2.getName(), "utf-8"));
            // 其它参数(注:自定义contentType，设置UTF-8是为了防止服务端拿到的参数出现乱码)
            ContentType contentType = ContentType.create("text/plain", StandardCharsets.UTF_8);
            multipartEntityBuilder.addTextBody("name", "邓沙利文", contentType);
            multipartEntityBuilder.addTextBody("age", "25", contentType);

            HttpEntity httpEntity = multipartEntityBuilder.build();
            httpPost.setEntity(httpEntity);

            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println("HTTPS响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("HTTPS响应内容长度为:" + responseEntity.getContentLength());
                // 主动设置编码，来防止响应乱码
                String responseStr = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                System.out.println("HTTPS响应内容为:" + responseStr);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *
     * 发送流
     *
     */
    public void test5() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://localhost:12345/is?name=邓沙利文");
        CloseableHttpResponse response = null;
        try {
            InputStream is = new ByteArrayInputStream("流啊流~".getBytes());
            InputStreamEntity ise = new InputStreamEntity(is);
            httpPost.setEntity(ise);

            response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            System.out.println("HTTPS响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("HTTPS响应内容长度为:" + responseEntity.getContentLength());
                // 主动设置编码，来防止响应乱码
                String responseStr = EntityUtils.toString(responseEntity, StandardCharsets.UTF_8);
                System.out.println("HTTPS响应内容为:" + responseStr);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**  畅捷通的 API， POST请求 */
    public static String HttpPost(String Url,String json,String appKey,String AppSecret,String Token) throws Exception{
        String result = "";
        URL realUrl = new URL(openurl+Url);
        URLConnection conn = realUrl.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection)conn;
        conn.setRequestProperty("user-agent","Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("appKey", appKey);
        conn.setRequestProperty("AppSecret", AppSecret);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("openToken", Token);
        /*if(!Authorization.equals("")){
            conn.setRequestProperty("Authorization",Authorization);
        }*/
        conn.setDoOutput(true);
        conn.setDoInput(true);
        //PrintWriter out = new PrintWriter(conn.getOutputStream());
        PrintWriter out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
        out.print(json);
        out.flush();

        int statecode = httpURLConnection.getResponseCode();
        BufferedReader in = null; //new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        if(statecode == 200){
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8" ));
        }else{
            in = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream(),"UTF-8"));
        }
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        out.close();
        in.close();
        return result;
    }




    //E看牙的部分查询接口是需要 通过 POST 去查询的。这个ttt 只是为了组装参数！
    public static String doPostEkanya(String url, Object ttt,String Authorization) {
        String reslut = "不应该返回这个哦！";
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Post请求
        HttpPost httpPost = new HttpPost(url);
        // 我这里利用阿里的fastjson，将Object转换为json字符串;(需要导入com.alibaba.fastjson.JSON包)
        String jsonString = JSONObject.toJSONString(ttt);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        httpPost.setHeader("Authorization", Authorization);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                //System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                reslut = EntityUtils.toString(responseEntity);
                //System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
                return reslut;
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
            return "error!!!!";
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  reslut;
        }
    }
}

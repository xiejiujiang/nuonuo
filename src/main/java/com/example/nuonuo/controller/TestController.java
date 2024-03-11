package com.example.nuonuo.controller;

import com.example.nuonuo.utils.HttpClient;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;
import java.io.IOException;

import com.example.nuonuo.utils.Md5;
import com.example.nuonuo.utils.ObjectToXmlConverter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/testcc")
public class TestController {

    public static void main(String[] args) throws Exception{

        try {
            /*String url = "http://yj.test.fxscm.net:1215/cldpoint/getShop.do";
            Map<String,String> params = new HashMap<String,String>();
            params.put("ent","ENTa4cm");
            params.put("username","admin");
            params.put("password","0000");
            String result = HttpClient.doGeturlparams(url,params,"");
            System.out.println("result == " + result);*/

            //http://c-wms.iask.in:8765/api/edi/qimen/service?method=item.synchronize&timestamp=[2015-02-01 00:00:00] &format=xml&app_key=[appkey]&v=1.0&sign=[xxxxxxxxxxxxxxxxxxxxxx]&sign_method=md5&customerId=[customerId]

            com.example.nuonuo.entity.hongren.rukufahuo.RFRequest request = new com.example.nuonuo.entity.hongren.rukufahuo.RFRequest();
            String body = ObjectToXmlConverter.convertObjectToXml(request);
            System.out.println("body == " + body);
            String result = "";
            Map<String,String> params = new HashMap<String,String>();
            String secret = "36255923b94a5f667519a30524637e9c";
            params.put("secret",secret);
            params.put("app_key","YJLF");
            params.put("customerId","YJLF");
            params.put("format","xml");
            params.put("method","mixorder.create");
            params.put("partner_id","");
            params.put("sign_method","md5");
            params.put("timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            params.put("v","2.0");
            String sign = Md5.md5(secret+"app_key"+params.get("app_key")+"customerId"+params.get("customerId")+"format"+params.get("format")+"method"+params.get("method")
                    +"partner_id"+params.get("partner_id")+"sign_method"+params.get("sign_method")+"timestamp"+params.get("timestamp")+"v"+params.get("v")+body+secret);
            params.put("sign",sign.toUpperCase());//大写！
            try {
                result = HttpClient.doPostXMLTEST("http://c-api.hr-network.cn/api/edi/qimen/service",params,body);
            }catch (Exception e){
                result = "天财订单下发给弘人WMS进行发货出库 失败！！！";
            }
            System.out.println("result == " + result);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

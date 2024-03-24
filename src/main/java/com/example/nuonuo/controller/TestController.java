package com.example.nuonuo.controller;

import com.example.nuonuo.entity.tiancaicg.Tccg;
import com.example.nuonuo.entity.tiancaicg.TccgDetail;
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
            Tccg tccg = new Tccg();
            tccg.setUsername("admin");
            tccg.setPassword("0000");
            tccg.setEnt("ENTa5ob");
            tccg.setMessageId(""+System.currentTimeMillis());
            tccg.setBusDate("2024-03-15 12:30:11");
            tccg.setSourceId("123111234");
            tccg.setAutoAuditFlag(0);
            tccg.setCenterId("402887698e3ad5f9018e3b7910530068");
            tccg.setSystemId("123");
            tccg.setSourceName("测试");
            tccg.setBusUserId("admin");
            tccg.setArrivalDate("2024-03-15 02:30:11");
            tccg.setSupplierId("supplierzcghsorgan");
            List<TccgDetail> detail = new ArrayList<TccgDetail>();
            TccgDetail tccgDetail1 = new TccgDetail();
            tccgDetail1.setInStoreMoney(10);
            tccgDetail1.setGiftFlag(false);
            tccgDetail1.setInBusAmount(1);
            tccgDetail1.setBatchCode("1234");
            tccgDetail1.setIncludeTaxMoney(10);
            tccgDetail1.setTaxes(0);
            tccgDetail1.setRemark("xxxxx");
            tccgDetail1.setInStoreId("402887698e3ad5f9018e3b794789006b");
            tccgDetail1.setTaxMoney(0);
            tccgDetail1.setItemId("402887698e3ad5f9018e3b7a0ff9006c");
            tccgDetail1.setBusUnitPrice(10);
            tccgDetail1.setSortNum(1);
            tccgDetail1.setMakeDate("2024-03-15 02:30:11");
            tccgDetail1.setIncludeTaxUnitPrice(10);
            detail.add(tccgDetail1);
            tccg.setDetail(detail);

            String url = "http://yj.test.fxscm.net:1215/cldpoint/api/yj/1.0/create_zxcgrk.do";
            String res = HttpClient.doPostTestTwo(url,tccg);
            System.out.println("result == " + res);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}

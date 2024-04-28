package com.example.nuonuo.controller;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.SAsubscribe.SACsubJsonRootBean;
import com.example.nuonuo.entity.Meituan.MeituanPeiSong;
import com.example.nuonuo.entity.Meituan.PeisongFaHuo.DeliveryItemDetailDTO;
import com.example.nuonuo.entity.Meituan.PeisongFaHuo.ScmChainDeliveryDeliveryOrder1Request;
import com.example.nuonuo.entity.Skd;
import com.example.nuonuo.entity.mida.chukureturn.OrderDetailsRespose;
import com.example.nuonuo.entity.mida.rukureturn.InboundStockInfo;
import com.example.nuonuo.entity.mida.rukureturn.WarehouseStockBo;
import com.example.nuonuo.entity.shoukd.*;
import com.example.nuonuo.entity.tiancaichukureturn.TcZXCHUKUReturn;
import com.example.nuonuo.mapper.orderMapper;
import com.example.nuonuo.mapper.zhongtaiMapper;
import com.example.nuonuo.saentity.JsonRootBean;
import com.example.nuonuo.saentity.SaleDeliveryDetails;
import com.example.nuonuo.service.TokenService;
import com.example.nuonuo.utils.*;
import com.google.gson.Gson;
import com.jiujin.scm.open.sdk.bo.CreatedOrderBo;
import com.jiujin.scm.open.sdk.client.MidaOpenClient;
import com.jiujin.scm.open.sdk.client.OpenClient;
import com.jiujin.scm.open.sdk.request.CreatedOrderRequest;
import com.jiujin.scm.open.sdk.response.GetBatchOrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@Controller
@RequestMapping(value = "/token")
public class TokenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private orderMapper orderMapper;

    @Autowired
    private com.example.nuonuo.mapper.zhongtaiMapper zhongtaiMapper;

    @Autowired
    private TokenService tokenService;

    //这个里面 主要 用来 接受 code ,刷新 token ，更新对应的数据库
    @RequestMapping(value="/recode", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String recode(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("------------------- 美团 回调地址 访问成功 ！ -------------------");
        String code = request.getParameter("code");
        //String sign = request.getParameter("sign");
        //String developerId = request.getParameter("developerId");
        //String businessId = request.getParameter("businessId");
        //第一次授权后，会有这个code,立刻调用 一次 授权码换token接口 ，拿到完整的 token 相关信息，并写入数据库。
        //3月17日思考： 暂时不用接口来访问，直接在线访问后 拿到第一次的数据，并 复制 填入数据库表中接口（后续定时任务来更新）
        String ss = orderMapper.getDBtest();
        if( ss != null && !"".equals(ss)){//说明数据库链接成功！
            LOGGER.info("------------------- 数据库链接成功 ,code == " + ss);
        }
        LOGGER.info("------------------- 美团 回调地址 code : " + code);
        return code;
    }


    //T+ 的 消息订阅的接口。
    @RequestMapping(value="/ticket", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String reticket(HttpServletRequest request, HttpServletResponse response) {
        try{
            InputStreamReader reader=new InputStreamReader(request.getInputStream(),"utf-8");
            BufferedReader buffer=new BufferedReader(reader);
            String params=buffer.readLine();
            JSONObject jsonObject = JSONObject.parseObject(params);
            String encryptMsg = jsonObject.getString("encryptMsg");
            String destr = AESUtils.aesDecrypt(encryptMsg,"123456789012345x");
            // {"id":"AC1C04B100013301500B4A9B012DB2EC","appKey":"A9A9WH1i","appId":"58","msgType":"SaleDelivery_Audit","time":"1649994072443","bizContent":{"externalCode":"","voucherID":"23","voucherDate":"2022/4/15 0:00:00","voucherCode":"SA-2022-04-0011"},"orgId":"90015999132","requestId":"86231b63-f0c2-4de1-86e9-70557ba9cd62"}
            JSONObject job = JSONObject.parseObject(destr);
            String appKey = job.getString("appKey");
            //根据 appKey 查询对应的 token 和
            Map<String,String> tmap = orderMapper.getDBAllOrgListByappKey(appKey);
            LOGGER.info("------------------- 正式消息接收地址，包含 ticket，消息订阅，具体是："+job.getString("msgType")+" -------------------");
            // 销售发票保存
            if("SaleInvoice_Create".equals(job.getString("msgType"))){
                SACsubJsonRootBean jrb =  job.toJavaObject(SACsubJsonRootBean.class);
                String xxfpcode = jrb.getBizContent().getVoucherCode();
                //调用审核按钮
                String xxfpcodeJson = "{\n" +
                        "\t\"param\": {\n" +
                        "\t\t\"voucherCode\": \""+xxfpcode+"\"\n" +
                        "\t}\n" +
                        "}";
                String xsfpres = HttpClient.HttpPost("/tplus/api/v2/SaleInvoiceOpenApi/Audit",
                        xxfpcodeJson,
                        appKey,
                        tmap.get("AppSecret").toString(),
                        tmap.get("access_token").toString());
                LOGGER.info("-------------------销售发票 : "+ xxfpcode + "的审核结果是：" + xsfpres);
                JSONObject xsfpresjob = JSONObject.parseObject(xsfpres);
                String xsfpresjobcode = xsfpresjob.getString("code");
                if("999".equals(xsfpresjobcode)){//如果审核失败了就再来一次。。。。
                    String xsfpres2 = HttpClient.HttpPost("/tplus/api/v2/SaleInvoiceOpenApi/Audit",
                            xxfpcodeJson,
                            appKey,
                            tmap.get("AppSecret").toString(),
                            tmap.get("access_token").toString());
                    LOGGER.info("-------------------销售发票 : "+ xxfpcode + "的第2次审核结果是：" + xsfpres2);
                }
            }

            // 销货单审核
            if("SaleDelivery_Audit".equals(job.getString("msgType"))){

                SACsubJsonRootBean jrb =  job.toJavaObject(SACsubJsonRootBean.class);
                String vourcherCode = jrb.getBizContent().getVoucherCode();
                LOGGER.info("-------------------销货单：" + vourcherCode + "审核信息收到，马上调用销货单查询接口-------------------");
                //根据销货单的单号，查询对应销货单明细信息
                String json = "{\n" +
                        "\t\"param\": {\n" +
                        "\t\t\"voucherCode\":\""+vourcherCode+"\"\n" +
                        "\t}\n" +
                        "}";
                //LOGGER.info("-------------------查询销售单明细的JSON：" + json + "-------------------");
                String saDetailreslut = HttpClient.HttpPost("/tplus/api/v2/SaleDeliveryOpenApi/GetVoucherDTO",
                        json,
                        appKey,
                        tmap.get("AppSecret").toString(),
                        tmap.get("access_token").toString());
                //LOGGER.info("-------------------销货单：" + vourcherCode + "查询结果为：" + saDetailreslut);
                JSONObject sajob;
                JsonRootBean saentity;
                try{
                    sajob = JSONObject.parseObject(saDetailreslut);
                    saentity =  sajob.toJavaObject(JsonRootBean.class);
                }catch (Exception e){
                    saDetailreslut = HttpClient.HttpPost("/tplus/api/v2/SaleDeliveryOpenApi/GetVoucherDTO",
                            json,
                            appKey,
                            tmap.get("AppSecret").toString(),
                            tmap.get("access_token").toString());
                    sajob = JSONObject.parseObject(saDetailreslut);
                    saentity =  sajob.toJavaObject(JsonRootBean.class);
                }
                //解析这个 saentity 销货单明细 信息，再获取对应的 仓库，批号，自由项 信息
                String TransVehicleInfo = saentity.getData().getTransVehicleInfo();//车辆信息
                LOGGER.info("TransVehicleInfo == " + TransVehicleInfo);
                String DispatchAddress = saentity.getData().getAddress();//送货地址
                LOGGER.info("DispatchAddress == " + DispatchAddress);
                List<SaleDeliveryDetails> sadetaillist = saentity.getData().getSaleDeliveryDetails();
                // 想了 当天  还是 通过SQL 来 获取吧
                String inventoryCodes = "(";
                for(SaleDeliveryDetails SaleDeliveryDetail : sadetaillist){
                    //获取到 每一个 商品明细
                    String inventoryCode = SaleDeliveryDetail.getInventory().getCode();
                    inventoryCodes = inventoryCodes + "'"+ inventoryCode + "',";
                }
                inventoryCodes = inventoryCodes + ")";
                inventoryCodes = inventoryCodes.replace(",)",")");

                // 把 查库存 和  出库 做成了单线程！
                synchronized(this) {
                    List<Map<String, Object>> sacklist = orderMapper.getCkByInventyCodes(inventoryCodes, saentity.getData().getCode());
                    //通过 销货单对象 saentity 以及 对应的 sacklist 库存明细 组装一个 销售出库单的 JSON
                    String sackjson = MapToJson.getSaCkJson(saentity, sacklist);
                    LOGGER.info("-------------------创建销售出库单的JSON：" + sackjson + "-------------------");
                    String sackreslut = HttpClient.HttpPost("/tplus/api/v2/SaleDispatchOpenApi/Create",
                            sackjson,
                            appKey,
                            tmap.get("AppSecret").toString(),
                            tmap.get("access_token").toString());
                    LOGGER.info("-------------------创建销售出库单的结果是：" + sackreslut);
                    //如果成功了。审核一下？ sackreslut 里面有code哦！
                    JSONObject sackreslutjob;
                    String sackreslutdata;
                    JSONObject sackreslutdatajob;
                    String xsckdcode;
                    try {
                        sackreslutjob = JSONObject.parseObject(sackreslut);
                        sackreslutdata = sackreslutjob.getString("data");
                        sackreslutdatajob = JSONObject.parseObject(sackreslutdata);
                        xsckdcode = sackreslutdatajob.getString("Code");
                    } catch (Exception e) {
                        sackreslut = HttpClient.HttpPost("/tplus/api/v2/SaleDispatchOpenApi/Create",
                                sackjson,
                                appKey,
                                tmap.get("AppSecret").toString(),
                                tmap.get("access_token").toString());
                        sackreslutjob = JSONObject.parseObject(sackreslut);
                        sackreslutdata = sackreslutjob.getString("data");
                        sackreslutdatajob = JSONObject.parseObject(sackreslutdata);
                        xsckdcode = sackreslutdatajob.getString("Code");
                    }
                    String xsckdcodeJson = "{\n" +
                            "\t\"param\": {\n" +
                            "\t\t\"voucherCode\": \"" + xsckdcode + "\"\n" +
                            "\t}\n" +
                            "}";
                    String xcres = HttpClient.HttpPost("/tplus/api/v2/SaleDispatchOpenApi/Audit",
                            xsckdcodeJson,
                            appKey,
                            tmap.get("AppSecret").toString(),
                            tmap.get("access_token").toString());
                    LOGGER.info("-------------------创建销售出库单的审核结果是：" + xcres);
                    //审核之后，SQL 更新下 xsckdcode 这张出库单的表尾的 审核日期！ 跟 关联的 那个 销货单的 审核日期 一致！
                    JSONObject xcresjob = JSONObject.parseObject(xcres);//审核结果
                    if ("0".equals(xcresjob.getString("code"))) {//审核成功!
                        orderMapper.updateXSCKAUDATE(xsckdcode, vourcherCode);
                        //成功之后，把 这个 销售出库单上的 车辆信息（TransVehicleInfo） 和送货地址（DispatchAddress） 更新下
                        Map<String,String> parss = new HashMap<String,String>();
                        parss.put("xsckdcode",xsckdcode);
                        parss.put("TransVehicleInfo",TransVehicleInfo);
                        parss.put("DispatchAddress",DispatchAddress);
                        orderMapper.updateXSCKParas(parss);
                    }
                }
            }
        }catch (Exception e){
            //e.printStackTrace();
        }
        return "{ \"result\":\"success\" }";
    }


    // 已 弃 用！！！！  被上面挨着这个 替代了 。
    //系统管理里面  的 消息订阅验证接口，官方已经 弃用了。 所以把代码注销掉。
    @RequestMapping(value="/dy2kai", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String dy2kai(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        String echostr = request.getParameter("echostr");
        try{
            /*String nonce = request.getParameter("nonce");
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");

            InputStreamReader reader=new InputStreamReader(request.getInputStream(),"utf-8");
            BufferedReader buffer=new BufferedReader(reader);
            String params=buffer.readLine();
            //LOGGER.info("请求参数: "+params);
            JSONObject jsonObject = JSONObject.parseObject(params);

            String OrgId = jsonObject.getString("OrgId");
            String appKey = "90014098149".equals(OrgId)?"zbqafNDm":"b"; // 90014098149 是 0001 账套，就是国杀——1 那个 ，否则就取另外一个账套
            Map<String,String> tmap = orderMapper.getDBAllOrgListByappKey(appKey);

            String vourcherCode = jsonObject.getString("Code");
            //LOGGER.info("管理员的二开消息订阅Code =============== " + code);
            //LOGGER.info("当前操作，0 保存，1 审核，2 弃审，3 删除，4 取消中止，5 中止");
            String state = jsonObject.getString("SendState");
            //LOGGER.info("SendState =============== " + state);
            //String OrgId = jsonObject.getString("OrgId");
            //LOGGER.info("OrgId =============== " + OrgId);
            //如果是销货单，并且是 审核 条件，
            if(state.equals("1")){
                //销货单的单号  vourcherCode
                LOGGER.info("-------------------销货单：" + vourcherCode + "审核信息收到，马上调用销货单查询接口-------------------");
                //根据销货单的单号，查询对应销货单明细信息
                String json = "{\n" +
                        "\t\"param\": {\n" +
                        "\t\t\"voucherCode\":\""+vourcherCode+"\"\n" +
                        "\t}\n" +
                        "}";
                LOGGER.info("-------------------查询销售单明细的JSON：" + json + "-------------------");
                String saDetailreslut = HttpClient.HttpPost("/tplus/api/v2/SaleDeliveryOpenApi/GetVoucherDTO",
                        json,
                        appKey,
                        tmap.get("AppSecret").toString(),
                        tmap.get("access_token").toString());
                LOGGER.info("-------------------销货单：" + vourcherCode + "查询结果为：" + saDetailreslut);
                JSONObject sajob = JSONObject.parseObject(saDetailreslut);
                JsonRootBean saentity =  sajob.toJavaObject(JsonRootBean.class);
                //解析这个 saentity 销货单明细 信息，再获取对应的 仓库，批号，自由项 信息
                List<SaleDeliveryDetails> sadetaillist = saentity.getData().getSaleDeliveryDetails();
                // 想了 当天  还是 通过SQL 来 获取吧
                String inventoryCodes = "(";
                for(SaleDeliveryDetails SaleDeliveryDetail : sadetaillist){
                    //获取到 每一个 商品明细
                    String inventoryCode = SaleDeliveryDetail.getInventory().getCode();
                    inventoryCodes = inventoryCodes + "'"+ inventoryCode + "',";
                }
                inventoryCodes = inventoryCodes + ")";
                inventoryCodes = inventoryCodes.replace(",)",")");
                List<Map<String,Object>> sacklist = orderMapper.getCkByInventyCodes(inventoryCodes,saentity.getData().getCode());

                *//*String sajson = "{\"param\": {\"Inventory\":" + JSONObject.toJSONString(sajsonlist)
                        + ",\"GroupInfo\": {\"Brand\": false,\"Warehouse\": true,\"IsBatch\": true,\"IsProductionDate\": false,\"InvLocation\": false,\"IsExpiryDate\": false,\"Inventory\": true,\"InvProperty\": false}}}";
                //再调用现存量查询接口
                LOGGER.info("-------------------请求查询存货现存量的参数是：" + sajson);
                String ckinventoryreslut = HttpClient.HttpPost("/tplus/api/v2/currentStock/Query",
                        sajson,
                        appKey,
                        tmap.get("AppSecret").toString(),
                        tmap.get("access_token").toString());
                LOGGER.info("-----------请求查询存货现存量的查询结果为：" + ckinventoryreslut);
                //用List
                List<Kcjson> sacklist = JSONObject.parseArray(ckinventoryreslut,Kcjson.class);*//*


                //通过 销货单对象 saentity 以及 对应的 sacklist 库存明细 组装一个 销售出库单的 JSON
                String sackjson = MapToJson.getSaCkJson(saentity,sacklist);
                LOGGER.info("-------------------创建销售出库单的JSON：" + sackjson + "-------------------");
                String sackreslut = HttpClient.HttpPost("/tplus/api/v2/SaleDispatchOpenApi/Create",
                        sackjson,
                        appKey,
                        tmap.get("AppSecret").toString(),
                        tmap.get("access_token").toString());
                LOGGER.info("-------------------创建销售出库单的结果是：" + sackreslut);
                //如果成功了。审核一下？ sackreslut 里面有code哦！
                JSONObject sackreslutjob = JSONObject.parseObject(sackreslut);
                String sackreslutdata = sackreslutjob.getString("data");
                JSONObject sackreslutdatajob = JSONObject.parseObject(sackreslutdata);
                String xsckdcode = sackreslutdatajob.getString("Code");
                String xsckdcodeJson = "{\n" +
                        "\t\"param\": {\n" +
                        "\t\t\"voucherCode\": \""+xsckdcode+"\"\n" +
                        "\t}\n" +
                        "}";
                String xcres = HttpClient.HttpPost("/tplus/api/v2/SaleDispatchOpenApi/Audit",
                        xsckdcodeJson,
                        appKey,
                        tmap.get("AppSecret").toString(),
                        tmap.get("access_token").toString());
                LOGGER.info("-------------------创建销售出库单的审核结果是：" + xcres);

                //通过 销货单对象 saentity 再组装一个销售发票的JSON哦！
                // 判断 此销货单 没有对应的销售发票，再创建
                if(orderMapper.getXSFPbYCODE(vourcherCode) == 0){
                    String safpjson = MapToJson.getSaFPJson(saentity);
                    LOGGER.info("-------------------创建销售发票的JSON：" + safpjson + "-------------------");
                    String safpreslut = HttpClient.HttpPost("/tplus/api/v2/SaleInvoiceOpenApi/Create",
                            safpjson,
                            appKey,
                            tmap.get("AppSecret").toString(),
                            tmap.get("access_token").toString());
                    LOGGER.info("-------------------创建销售发票的结果是：" + safpreslut);
                    //如果成功了。审核一下？ safpreslut 里面有code哦！
                    JSONObject safpreslutjob = JSONObject.parseObject(safpreslut);
                    String safpreslutdata = safpreslutjob.getString("data");
                    JSONObject safpreslutdatajob = JSONObject.parseObject(safpreslutdata);
                    String xxfpcode = safpreslutdatajob.getString("Code");
                    String xxfpcodeJson = "{\n" +
                            "\t\"param\": {\n" +
                            "\t\t\"voucherCode\": \""+xxfpcode+"\"\n" +
                            "\t}\n" +
                            "}";
                    String xsfpres = HttpClient.HttpPost("/tplus/api/v2/SaleInvoiceOpenApi/Audit",
                            xxfpcodeJson,
                            appKey,
                            tmap.get("AppSecret").toString(),
                            tmap.get("access_token").toString());
                    LOGGER.info("-------------------创建销售发票的审核结果是：" + xsfpres);
                }
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }
        return echostr;
    }


    // ------------------------------------------------  以下是业务接口 -----------------------------------------------------//

    //测试JSP页面配置是否成功
    @RequestMapping(value="/testmodel", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView testmodel(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("openRecode");
        return mav;
    }

    //提交开票信息 到  诺诺网 ， 并返回结果
    @RequestMapping(value="/commitNuonuo", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getDistricntKC(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ModelAndView mav = new ModelAndView("result");
        //谁有值，页面上就选择的谁
        String committypeperson = request.getParameter("committypeperson");
        String committypecompany = request.getParameter("committypecompany");
        String code = request.getParameter("code");//零售单的单号
        LOGGER.info("--------------------- 零售单：" + code + "准备开票了！！！ ---------------------"  );
        mav.setViewName("result");
        if(code == null || "".equals(code) || !code.contains("LS")){ // 此单已经开过票。或者 正在开票中？
            mav.setViewName("fail");
            return  mav;
        }
        try {
            Map<String,Object> taxMap = orderMapper.getRedetailMap(code);
            String appKey = taxMap.get("AppKey").toString();
            String appSecret = taxMap.get("AppSecret").toString();
            String taxnum = taxMap.get("taxnum").toString();
            String token = taxMap.get("token").toString();

            List<Map<String,Object>> invenlist = orderMapper.getRetailDetailListByCode(code);

            // -------------------- 个人 ---------------------------//
            String personname = request.getParameter("personname");
            String personmail = request.getParameter("personmail");
            String personmobile = request.getParameter("personmobile");

            // -------------------- 公司 ----------------------------//
            String companyname = request.getParameter("companyname");
            String invoicetype = request.getParameter("invoicetype");//普票 1   专票 2
            String companytaxnum = request.getParameter("companytaxnum");
            String companyaddress = request.getParameter("companyaddress");
            String companyphone = request.getParameter("companyphone");
            String companybankname = request.getParameter("companybankname");
            String companybanknum = request.getParameter("companybanknum");
            String companymail = request.getParameter("companymail");
            String companymobile = request.getParameter("companymobile");

            // content 需要自行组装处理。
            String content = "";
            if(committypeperson != null && !"".equals(committypeperson)){
                taxMap.put("buyname",personname);
                taxMap.put("mail",personmail);
                taxMap.put("buytel",personmobile);
                content = NuonuoTest.getContentString(taxMap,invenlist);
            }

            if(committypecompany != null && !"".equals(committypecompany)){
                taxMap.put("buyname",companyname);
                taxMap.put("invoicetype",invoicetype);// 1普，2专
                //公司 开  专票 用    ？   那开 公司 普票呢？
                taxMap.put("companytaxnum",companytaxnum);
                taxMap.put("companyaddress",companyaddress);
                taxMap.put("companyphone",companyphone);
                taxMap.put("companybankname",companybankname);
                taxMap.put("companybanknum",companybanknum);
                taxMap.put("mail",companymail);
                taxMap.put("buytel",companymobile);
                content = NuonuoTest.getContentString(taxMap,invenlist);
            }
            LOGGER.info("============= code : " + code + " 对应的诺诺请求参数是：" + content);
            String result = NuonuoTest.CommitNuonuo(appKey,appSecret,taxnum,token,content);
            LOGGER.info("============= code : " + code + " 对应的诺诺开票返回是：" + result);
            JSONObject jre = JSONObject.parseObject(result);
            if("E0000".equals(jre.getString("code"))){
                //提交成功！（不是开票成功！）
                String invoiceSerialNum = jre.getString("invoiceSerialNum");
                LOGGER.info("-------------------  零售单：" + code + " 的开票结果是成功，对应的发票流水号是：" + invoiceSerialNum);
                //orderMapper.updateRetailKPBycode(code);//如果成功，备注 多个 "Y" ，并增加一个 开票成功的标志（自定义字段）
                mav.setViewName("result");
            }else{
                //提交失败！
                String describe = jre.getString("describe");
                LOGGER.info("-------------------  零售单：" + code + " 的开票结果是失败，对应的原因是：" + describe);
                mav.setViewName("fail");
            }
        }catch (Exception e){
            e.printStackTrace();
            mav.setViewName("fail");
            return  mav;
        }
        return  mav;
    }


    //开票回传地址，回传发票信息地址（开票完成、开票失败）
    @RequestMapping(value="/noticefp", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String noticefp(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("------------------- 回传发票信息地址（开票完成、开票失败） -------------------");

        return "";
    }

    // --------------------------------------------------------------------------------------------//

    //确认无误后，点击确认，进入 周经理的excel 导入页面
    @RequestMapping(value="/goExcel", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView goCommit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        LOGGER.info("-------------------1  确认无误后，点击确认，进入 周经理的excel 导入页面  ----------------------");
        mav.setViewName("excel");
        return mav;
    }


    // 周经理-导入excel 解析数据，生成 收款单 核销 销售发票明细
    @RequestMapping(value="/createSK", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String createSK(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("-------------------  提交了excel表格到后台，马上解析数据！  ----------------------");
        String daoruRes = "";
        InputStream inputStream = file.getInputStream();
        ExcelListener listener = new ExcelListener();
        ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
        //已经做好的 普天T+名称匹配表是 从 第一个sheet 的 第一行 开始获取数据
        com.alibaba.excel.metadata.Sheet sheet = new Sheet(1,2,Skd.class);
        excelReader.read(sheet);
        List<Skd> ptlist = new ArrayList<Skd>();//最终用来写入的数据
        List<Object> list = listener.getDatas();//当前从上传的excel中获取的数据
        int excelHangNumbers = list.size();//导入的excel 总共 多少 行！
        for(Object oo : list){
            Skd skd = (Skd)oo;
            ptlist.add(skd);
        }
        //拿到所有数据的这个  ptlist  然后匹配发票明细。折扣金额
        Map<String,String> jsonMap = new HashMap<String,String>();
        int dataErrorNumbers = 0; //没有发票的行数
        for(Skd skd : ptlist){
            String mobile = skd.getMobile();//导入人的手机号，最后一列！
            String drjq = skd.getShoukdate();//收款日期作为单据日期
            String kehucode = skd.getJskehucode();//结算客户编码
            String sacode = skd.getSacode();//销货单单号
            //String departmentCode = skd.getDepartmentCode();
            //String clearkCode = skd.getClearkCode();
            String saAmount = skd.getSaleamount();
            String hexiaoAmount = skd.getHexiaoamount();
            String zherangAmount = skd.getZherangamount();
            String jsCode = skd.getJscode();
            String bankName = skd.getBankname();
            String skAmount = skd.getShoukamount();
            String beizhu = skd.getShoukmemo();
            String inventorycode = skd.getInventorycode();//商品编码
            String ProductionDate = skd.getProductionate();//实际上 填的 是批号哈，SQL也改了的。
            String factsaleamount = skd.getFactsaleamount();//实际销售金额
            String weiyi = sacode+","+kehucode+drjq+","+mobile;//因为 同一个客户的同一个日期的同一个制单人，  一定只对应一个收款单！
            LOGGER.info("sacode == " + sacode + ",inventorycode == " + inventorycode + ",ProductionDate == " + ProductionDate);
            Map<String,Object> fapMap = new HashMap<String,Object>();
            if(ProductionDate != null && !"".equals(ProductionDate)){
                fapMap = orderMapper.getfapiaoMapBySA(sacode,inventorycode,ProductionDate,factsaleamount);
            }else{//说明 此行 是 费用类的商品，就没有批号！
                fapMap = orderMapper.getfapiaoMapBySANoBath(sacode,inventorycode,factsaleamount);
            }
            if(fapMap == null || fapMap.get("code") == null){//说明没有生成发票
                dataErrorNumbers = dataErrorNumbers + 1;
                daoruRes = daoruRes + "销货单号："+sacode +" 没有对应发票，核销失败！</br>";
            }else{//说明有发票
                //LOGGER.info("xxx === " + mobile+drjq+kehucode+sacode+saAmount+hexiaoAmount+zherangAmount+jsCode+bankName+skAmount+beizhu+weiyi);
                if(jsonMap.get(weiyi) == null || "".equals(jsonMap.get(weiyi))){
                    SKRoot skRoot = new SKRoot();
                    Dto dtoo = new Dto();
                    dtoo.setVoucherDate(drjq);
                    dtoo.setExternalCode(Md5.md5(weiyi+System.currentTimeMillis()));
                    Partner patt = new Partner();
                    patt.setCode(kehucode);
                    dtoo.setPartner(patt);
                    BusiType bty = new BusiType();
                    bty.setCode("33");//普通收款
                    dtoo.setBusiType(bty);
                    VoucherState voucherState = new VoucherState();
                    voucherState.setCode("01");//自动审核通过
                    dtoo.setVoucherState(voucherState);
                    dtoo.setExchangeRate(1);
                    dtoo.setIsReceiveFlag(true);//收款单
                    if(zherangAmount != null && !"".equals(zherangAmount) && Float.valueOf(zherangAmount) != 0){
                        dtoo.setOrigAllowances(zherangAmount);//折让金额
                    }
                    dtoo.setIsPartCancel(true);//明细核销指定结款金额

                    ArapMultiSettleDetails arapMultiSettleDetails = new ArapMultiSettleDetails();
                    SettleStyle settleStyle = new SettleStyle();
                    settleStyle.setCode(jsCode);
                    arapMultiSettleDetails.setSettleStyle(settleStyle);
                    BankAccount bankAccount = new BankAccount();
                    bankAccount.setName(bankName);
                    arapMultiSettleDetails.setBankAccount(bankAccount);
                    arapMultiSettleDetails.setOrigAmount(Float.valueOf(skAmount));
                    arapMultiSettleDetails.setMemo(beizhu);
                    List<ArapMultiSettleDetails> la = new ArrayList<ArapMultiSettleDetails>();
                    la.add(arapMultiSettleDetails);
                    dtoo.setArapMultiSettleDetails(la);

                    Details details = new Details();
                    VoucherType voucherType = new VoucherType();
                    voucherType.setCode("SA05");//销售发票！
                    details.setVoucherType(voucherType);
                    details.setVoucherCode(fapMap.get("code").toString());// 对应 销售发票的 编号
                    details.setVoucherDetailID(Integer.valueOf(fapMap.get("detailId").toString()));// 对应 销售发票的 明细 主键 ID
                    details.setOrigCurrentAmount(Float.valueOf(skAmount)+Float.valueOf(zherangAmount));//核销金额 = 收款金额 + 折让金额
                    if(zherangAmount != null && !"".equals(zherangAmount) && Float.valueOf(zherangAmount) != 0){
                        details.setOrigAllowancesAmount(zherangAmount);
                    }
                    List<Details> ldd = new ArrayList<Details>();
                    ldd.add(details);
                    dtoo.setDetails(ldd);

                    skRoot.setDto(dtoo);
                    Gson gson = new Gson();
                    String json = gson.toJson(skRoot);//组装好的JSONString
                    jsonMap.put(weiyi,json);
                }else{
                    String json = jsonMap.get(weiyi);//已经存入的JSON
                    SKRoot skRoot1 = JSONObject.parseObject(json,SKRoot.class);
                    Float skr1old = 0f;
                    if(skRoot1.getDto() == null || skRoot1.getDto().getOrigAllowances() == null){
                        skr1old = 0f;
                    }else{
                        skr1old = Float.valueOf(skRoot1.getDto().getOrigAllowances());
                    }
                    Float zherangAmount1 = skr1old+Float.valueOf(zherangAmount);
                    if(zherangAmount1 != null && !"".equals(zherangAmount1) && Float.valueOf(zherangAmount1) != 0){
                        skRoot1.getDto().setOrigAllowances(""+zherangAmount1);
                    }
                    ArapMultiSettleDetails arapMultiSettleDetails1 = new ArapMultiSettleDetails();
                    SettleStyle settleStyle1 = new SettleStyle();
                    settleStyle1.setCode(jsCode);
                    arapMultiSettleDetails1.setSettleStyle(settleStyle1);
                    BankAccount bankAccount1 = new BankAccount();
                    bankAccount1.setName(bankName);
                    arapMultiSettleDetails1.setBankAccount(bankAccount1);
                    arapMultiSettleDetails1.setOrigAmount(Float.valueOf(skAmount));
                    arapMultiSettleDetails1.setMemo(beizhu);
                    skRoot1.getDto().getArapMultiSettleDetails().add(arapMultiSettleDetails1);
                    Details details1 = new Details();
                    VoucherType voucherType1 = new VoucherType();
                    voucherType1.setCode("SA05");//销售发票！
                    details1.setVoucherType(voucherType1);
                    details1.setVoucherCode(fapMap.get("code").toString());// 对应 销售发票的 编号
                    details1.setVoucherDetailID(Integer.valueOf(fapMap.get("detailId").toString()));// 对应 销售发票的 明细 主键 ID
                    details1.setOrigCurrentAmount(Float.valueOf(skAmount)+Float.valueOf(zherangAmount));//核销金额 = 收款金额 + 折让金额
                    //details1.setOrigCurrentAmount(Float.valueOf(hexiaoAmount));//核销金额
                    if(zherangAmount != null && !"".equals(zherangAmount) && Float.valueOf(zherangAmount) != 0){
                        details1.setOrigAllowancesAmount(zherangAmount);
                    }
                    skRoot1.getDto().getDetails().add(details1);
                    Gson gson = new Gson();
                    String json1 = gson.toJson(skRoot1);//组装好的JSONString
                    jsonMap.put(weiyi,json1);
                }
            }
        }

        //周经理 调用收款单API接口
        //循环迭代 jsonMap
        Iterator<Map.Entry<String, String>> iterator = jsonMap.entrySet().iterator();
        int safailNumbers = 0;
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String weiyi = entry.getKey();
            String sacode = weiyi.substring(0,weiyi.indexOf(","));
            String mobile = weiyi.substring(weiyi.lastIndexOf(",")+1,weiyi.length());
            //String appKey = "fwXAfa0q";//写死！因为 一个账套内  只有一个！！  fwXAfa0q  002 9997 ////  JI5L5ZZe 001 9998
            Map<String,String> tmap = orderMapper.getDBAllOrgListByappKeyMobile(mobile);
            String appKey = tmap.get("AppKey").toString();
            String value = entry.getValue();
            LOGGER.info("---- 调用收款单的JSON == " + value);
            String rrr = HttpClient.HttpPost("/tplus/api/v2/ReceivePaymentVoucherOpenApi/NewCreate",
                    value,
                    appKey,
                    tmap.get("AppSecret").toString(),
                    tmap.get("access_token").toString());
            LOGGER.info("---- 调用收款单的JSON的结果是 == " + rrr);
            JSONObject rrrr = JSONObject.parseObject(rrr);
            String recode = rrrr.getString("code");
            if(!"0".equals(recode)){//说明创建失败了！
                safailNumbers = safailNumbers + 1;
                daoruRes = daoruRes + "销货单单号："+sacode + " 失败的原因是："+ rrrr.getString("message") + "</br>";
            }
        }
        if("".equals(daoruRes)){
            return "导入成功！ 请刷新后查询收款单！";
        }else{
            return daoruRes + "\n" + "总共失败了 " + safailNumbers + " 单";
        }
    }


    // --------------------------------  天财 回调  出库单未审核发单（统配+外销） ------------------------------//
    @RequestMapping(value="/tiancai", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String tiancai(HttpServletRequest request, HttpServletResponse response){
        String res = "{\"code\":\"0000\",\"message\":\"success\"}";
        try {
            StringBuilder xmlData = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                xmlData.append(line);
            }
            LOGGER.info("--------------- 天财推送中心出库单（统配+外销）信息：" + xmlData.toString());
            TcZXCHUKUReturn tcZXCHUKUReturn = JSONObject.parseObject(xmlData.toString(),TcZXCHUKUReturn.class);
            //下发给弘人WMS进行 发货出库
            tokenService.addWMSfahuochukuddByTCdd(tcZXCHUKUReturn);
        } catch (Exception e) {
            e.printStackTrace();
            res = "{\"code\":\"9001\",\"message\":\"接收异常，需要重新推送！\"}";
        }
        return res;
    }

    // --------------------------------  弘人WMS  回调------------------------------//
    @RequestMapping(value="/hongren", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String hongren(HttpServletRequest request, HttpServletResponse response) {
        String res = "";
        try {
            String method = request.getParameter("method");// 不同业务  传入的是 不同的 method
            String timestamp = request.getParameter("timestamp");
            String format = request.getParameter("format");
            String app_key = request.getParameter("app_key");
            String sign = request.getParameter("sign");
            String customerId = request.getParameter("customerId");
            //以此验证 获取的 url参数和body参数 是否正确，后续可能涉及到 加密验证合法性。
            StringBuilder xmlData = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                xmlData.append(line);
            }
            if("entryorder.confirm".equals(method)){//入库确认！
                LOGGER.info("------------ 弘人推送的入库信息是：" + xmlData.toString());
                com.example.nuonuo.entity.hongren.rukuqr.Request hongrenruku = XmlToObjectConverter.convertXmlToObject(xmlData.toString(), com.example.nuonuo.entity.hongren.rukuqr.Request.class);
                tokenService.addHongrenRuKuToTCMT(hongrenruku);
            }
            if("stockout.confirm".equals(method)){//出库确认！
                LOGGER.info("------------ 弘人推送的出库信息是：" + xmlData.toString());
                com.example.nuonuo.entity.hongren.chukuqr.Request hongrenchuku = XmlToObjectConverter.convertXmlToObject(xmlData.toString(), com.example.nuonuo.entity.hongren.chukuqr.Request.class);
                tokenService.addHongrenChuKuToTCMT(hongrenchuku);
            }
            res = "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><flag>success</flag><code>0000</code><message>接收成功</message></response>";
        } catch (Exception e) {
            e.printStackTrace();
            res = "<?xml version=\"1.0\" encoding=\"utf-8\"?><response><flag>failure</flag><code>4001</code><message>接收失败，请重试</message></response>";
        }
        return res;
    }

    // --------------------------------  米大WMS  回调------------------------------//
    @RequestMapping(value="/mida", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String mida(HttpServletRequest request, HttpServletResponse response) {
        String res = "";
        try {
            StringBuilder xmlData = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                xmlData.append(line);
            }
            JSONObject job = JSONObject.parseObject(xmlData.toString());
            String sign = job.getString("sign");
            String message_type = job.getString("message_type");
            String app_key = job.getString("app_key");
            String message = job.getString("message");
            if("ORDER_CHANGE".equals(message_type)){//ORDER_CHANGE:订单信息变更,就是米大出库发货
                LOGGER.info("------------ 米大推送的出库信息是：" + message);
                OrderDetailsRespose midachukureturn = JSONObject.parseObject(message,OrderDetailsRespose.class);
                tokenService.addMiDaChuKuToTCMT(midachukureturn);
            }
            if("STOCK_INBOUND".equals(message_type)){//STOCK_INBOUND:库存入库
                LOGGER.info("------------ 米大推送的入库信息是：" + message);
                InboundStockInfo midaruku = JSONObject.parseObject(message, InboundStockInfo.class);
                tokenService.addMiDaRuKuToTCMT(midaruku);
            }
            res = "{\"code\":\"0000\",\"message\":\"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            res = "{\"code\":\"9001\",\"message\":\"接收异常，需要重新推送！\"}";
        }
        return res;
    }


    // --------------------------------  美团 配送单 回调------------------------------//
    @RequestMapping(value="/meituandy", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String meituanDy(HttpServletRequest request, HttpServletResponse response) {
        String res = "";
        try {
            String reqMessage = request.getParameter("message");
            StringBuilder xmlData = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                xmlData.append(line);
            }
            LOGGER.info("--------------- 美团推送配送单信息，此消息用于测试配送拆单，后续需要注释掉:" + reqMessage);
            //String decodeXmlStr = URLDecoder.decode(reqMessage, "UTF-8");
            MeituanPeiSong meituanPeiSong = JSONObject.parseObject(reqMessage,MeituanPeiSong.class);
            int optype = meituanPeiSong.getOpType();// 1-创建、2-编辑、3-发货、4-撤销发货、5-删除、6-重推
            if(optype == 1){
                LOGGER.info("--------------- 美团推送配送单新增信息:" + reqMessage);
                tokenService.addWMSfahuochukuddByMTdd(meituanPeiSong);
            }
            res = "{\"code\":\"0000\",\"message\":\"success\"}";
        } catch (Exception e) {
            e.printStackTrace();
            res = "{\"code\":\"9001\",\"message\":\"接收异常，需要重新推送！\"}";
        }
        return res;
    }
}
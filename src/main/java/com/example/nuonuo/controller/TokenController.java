package com.example.nuonuo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.SAsubscribe.SACsubJsonRootBean;
import com.example.nuonuo.entity.Kcjson.Kcjson;
import com.example.nuonuo.mapper.orderMapper;
import com.example.nuonuo.saentity.JsonRootBean;
import com.example.nuonuo.saentity.SaleDeliveryDetails;
import com.example.nuonuo.service.BasicService;
import com.example.nuonuo.utils.*;
import lombok.Synchronized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/token")
public class TokenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private orderMapper orderMapper;

    //这个里面 主要 用来 接受 code ,刷新 token ，更新对应的数据库
    @RequestMapping(value="/recode", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String recode(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("------------------- 正式OAuth回调地址 -------------------");
        String code = request.getParameter("code");
        //第一次授权后，会有这个code,立刻调用 一次 授权码换token接口 ，拿到完整的 token 相关信息，并写入数据库。
        //3月17日思考： 暂时不用接口来访问，直接在线访问后 拿到第一次的数据，并 复制 填入数据库表中接口（后续定时任务来更新）
        String ss = orderMapper.getDBtest();
        LOGGER.info("DB-TEST ---- 访问成功！----- " + ss );
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
                    }
                }

                // ------------------------------------------- 发票换到上面了 ----------------------------------------------//

                //通过 销货单对象 saentity 再组装一个销售发票的JSON哦！
                // 判断 此销货单 没有对应的销售发票，再创建
                /*if(orderMapper.getXSFPbYCODE(vourcherCode) == 0){
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
                    //审核之后，SQL 更新下 xxfpcode 这张销售发票的表尾的 审核日期！ 跟 关联的 那个 销货单的 审核日期 一致！
                    JSONObject xsfpresjob = JSONObject.parseObject(xsfpres);//审核结果
                    if("0".equals(xsfpresjob.getString("code"))){//审核成功!
                        orderMapper.updateXSFPAUDATE(xxfpcode,vourcherCode);
                    }
                }else{
                    //否则就是 已经存在 销售发票了，那就审核一下就行了
                    String xtfpcode = orderMapper.getXSFPbYCODECODE(vourcherCode);//查出来的
                    String xxfpcodeJson = "{\n" +
                            "\t\"param\": {\n" +
                            "\t\t\"voucherCode\": \""+xtfpcode+"\"\n" +
                            "\t}\n" +
                            "}";
                    String xsfpres = HttpClient.HttpPost("/tplus/api/v2/SaleInvoiceOpenApi/Audit",
                            xxfpcodeJson,
                            appKey,
                            tmap.get("AppSecret").toString(),
                            tmap.get("access_token").toString());
                    LOGGER.info("-------------------审核系统自己的销售发票的审核结果是：" + xsfpres);
                    //审核之后，SQL 更新下 xxfpcode 这张销售发票的表尾的 审核日期！ 跟 关联的 那个 销货单的 审核日期 一致！
                    JSONObject xsfpresjob = JSONObject.parseObject(xsfpres);//审核结果
                    if("0".equals(xsfpresjob.getString("code"))){//审核成功!
                        orderMapper.updateXSFPAUDATE(xtfpcode,vourcherCode);
                    }
                }*/
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
            //LOGGER.error("请求参数: "+params);
            JSONObject jsonObject = JSONObject.parseObject(params);

            String OrgId = jsonObject.getString("OrgId");
            String appKey = "90014098149".equals(OrgId)?"zbqafNDm":"b"; // 90014098149 是 0001 账套，就是国杀——1 那个 ，否则就取另外一个账套
            Map<String,String> tmap = orderMapper.getDBAllOrgListByappKey(appKey);

            String vourcherCode = jsonObject.getString("Code");
            //LOGGER.error("管理员的二开消息订阅Code =============== " + code);
            //LOGGER.error("当前操作，0 保存，1 审核，2 弃审，3 删除，4 取消中止，5 中止");
            String state = jsonObject.getString("SendState");
            //LOGGER.error("SendState =============== " + state);
            //String OrgId = jsonObject.getString("OrgId");
            //LOGGER.error("OrgId =============== " + OrgId);
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

    //扫码后，打开的单据信息确认页面。
    @RequestMapping(value="/openRecode", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView openRecode(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        LOGGER.info("-------------------  扫码后，打开的单据信息确认页面  ----------------------");
        String code = request.getParameter("code"); // 零售单的单号
        Map<String,Object> redetailMap = orderMapper.getRedetailMap(code);

        /*Map<String,Object> redetailMap = new HashMap<String,Object>();
        redetailMap.put("storename","上海九亭店");
        redetailMap.put("code","RE-LS-0322");
        redetailMap.put("voucherdate","2023-03-22 09:32:14");
        redetailMap.put("amount","6677.00");*/

        mav.addObject("redetailMap",redetailMap);
        mav.setViewName("openRecode");
        return mav;
    }

    //确认无误后，点击确认，进入 开票信息填写页面！
    @RequestMapping(value="/goCommit", method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView goCommit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        String code = request.getParameter("code"); // 零售单的单号
        LOGGER.info("-------------------  确认无误后，点击确认，进入 开票信息填写页面  ----------------------");
        mav.addObject("code",code);
        mav.setViewName("form");
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
}
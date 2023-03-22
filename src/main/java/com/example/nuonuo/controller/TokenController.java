package com.example.nuonuo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.SAsubscribe.SACsubJsonRootBean;
import com.example.nuonuo.mapper.orderMapper;
import com.example.nuonuo.service.BasicService;
import com.example.nuonuo.utils.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/token")
public class TokenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private BasicService basicService ;

    @Autowired
    private orderMapper orderMapper;

    //这个里面 主要 用来 接受 code ,刷新 token ，更新对应的数据库
    @RequestMapping(value="/recode", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String recode(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("------------------- 正式OAuth回调地址 -------------------");
        String code = request.getParameter("code");
        //第一次授权后，会有这个code,立刻调用 一次 授权码换token接口 ，拿到完整的 token 相关信息，并写入数据库。
        //3月17日思考： 暂时不用接口来访问，直接在线访问后 拿到第一次的数据，并 复制 填入数据库表中接口（后续定时任务来更新）
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
            LOGGER.info("------------------- 正式消息接收地址，包含 ticket，消息订阅，具体是："+job.getString("msgType")+" -------------------");
            // 采购入库单审核 订阅
            if("PurchaseReceiveVoucher_Audit".equals(job.getString("msgType"))){
                SACsubJsonRootBean jrb =  job.toJavaObject(SACsubJsonRootBean.class);
                String vourcherCode = jrb.getBizContent().getVoucherCode();
                LOGGER.info("-------------------采购入库单：" + vourcherCode + "审核信息收到，马上进行处理-------------------");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "{ \"result\":\"success\" }";
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
            if(personname != null && !"".equals(personname)){
                taxMap.put("buyname",personname);
                taxMap.put("mail",personmail);
                taxMap.put("buytel",personmobile);
                content = NuonuoTest.getContentString(taxMap,invenlist);
            }

            if(companyname != null && !"".equals(companyname)){
                taxMap.put("buyname",companyname);
                taxMap.put("invoicetype",invoicetype);
                //公司 开  专票 用
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
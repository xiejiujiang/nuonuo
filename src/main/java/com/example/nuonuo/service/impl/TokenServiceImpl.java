package com.example.nuonuo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.Meituan.Caigou.CrateStockInItemDetailDTO;
import com.example.nuonuo.entity.Meituan.Caigou.ScmChainCreateStockPurchaseInRequest;
import com.example.nuonuo.entity.Meituan.Chaidan.MeituanPeisongChaidanDTO;
import com.example.nuonuo.entity.Meituan.Chaidan.SpiltDeliveryOrderDetailDTO;
import com.example.nuonuo.entity.Meituan.DeliveryOrderItemDetailDTO;
import com.example.nuonuo.entity.Meituan.MeituanPeiSong;
import com.example.nuonuo.entity.Meituan.PeisongFaHuo.DeliveryItemDetailDTO;
import com.example.nuonuo.entity.Meituan.PeisongFaHuo.ScmChainDeliveryDeliveryOrder1Request;
import com.example.nuonuo.entity.hongren.chukufahuomix.*;
import com.example.nuonuo.entity.hongren.chukure.HrchukuReturn;
import com.example.nuonuo.entity.hongren.wmsreturn.HongrenWMSddAfter;
import com.example.nuonuo.entity.mida.batchcukundtail.BatchSkuCukun;
import com.example.nuonuo.entity.mida.chukureturn.OrderDetailsRespose;
import com.example.nuonuo.entity.mida.dddetail.DDdetail;
import com.example.nuonuo.entity.mida.fahuoddct.Fhdd;
import com.example.nuonuo.entity.mida.kucun.Midack;
import com.example.nuonuo.entity.mida.rukureturn.InboundStockInfo;
import com.example.nuonuo.entity.mida.rukureturn.WarehouseStockBo;
import com.example.nuonuo.entity.tiancai.*;
import com.example.nuonuo.entity.tiancaicg.Tccg;
import com.example.nuonuo.entity.tiancaicg.TccgDetail;
import com.example.nuonuo.entity.tiancaichukureturn.TcZXCHUKUReturn;
import com.example.nuonuo.entity.tiancaichukureturn.TcZXCHUKUReturnDetail;
import com.example.nuonuo.entity.tiancaiqitadd.TcWXZXresult;
import com.example.nuonuo.mapper.orderMapper;
import com.example.nuonuo.mapper.zhongtaiMapper;
import com.example.nuonuo.service.TokenService;
import com.example.nuonuo.utils.*;
import com.jiujin.scm.open.sdk.StringUtils;
import com.jiujin.scm.open.sdk.bo.BatchSkuStockInfo;
import com.jiujin.scm.open.sdk.bo.CreatedOrderBo;
import com.jiujin.scm.open.sdk.bo.OrderInfo;
import com.jiujin.scm.open.sdk.bo.SkuStockInfo;
import com.jiujin.scm.open.sdk.request.CreatedOrderRequest;
import com.jiujin.scm.open.sdk.request.GetBatchSkuStockRequest;
import com.jiujin.scm.open.sdk.request.GetOrderInfoRequest;
import com.jiujin.scm.open.sdk.request.GetSkuStockInfoRequest;
import com.jiujin.scm.open.sdk.response.GetBatchOrderResponse;
import com.jiujin.scm.open.sdk.response.GetBatchSkuStockInfoResponse;
import com.jiujin.scm.open.sdk.response.GetOrderInfoResponse;
import com.jiujin.scm.open.sdk.response.GetSkuStockInfoResponse;
import com.meituan.sdk.DefaultMeituanClient;
import com.meituan.sdk.MeituanClient;
import com.meituan.sdk.internal.exceptions.MtSdkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    private static final String tiancaiurl = "http://yj.test.fxscm.net:1215/cldpoint/";//这个是test的

    private static final String tiancaient = "ENTa5ob";

    private static final String tiancaiadmin = "admin";

    private static final String tiancaipassword = "0000";

    @Autowired
    private orderMapper orderMapper;

    @Autowired
    private zhongtaiMapper zhongtaiMapper;

    @Override
    public String refreshTToken(){
        try {
            List<Map<String,String>> orgList = orderMapper.getDBTAllOrgList();
            if(orgList != null && orgList.size() != 0){
                for(Map<String,String> org : orgList){
                    Map<String,String> parma = new HashMap<String,String>();
                    parma.put("grantType","refresh_token");
                    parma.put("appKey",org.get("AppKey"));
                    String oldrefreshtoken = org.get("refresh_token");
                    parma.put("refreshToken",oldrefreshtoken);
                    String result = HttpClient.doGeturlparams("https://openapi.chanjet.com/auth/refreshToken", parma,"");
                    //将返回的 result 解析出来，写回数据库！,并一定更新 最后的 更新时间 ,其实 只有 refresh_token,token,和 更新时间会变。
                    JSONObject jso = JSONObject.parseObject(result);
                    if("200".equals(jso.get("code").toString())){//调用成功，更新数据库！
                        JSONObject detail = JSONObject.parseObject(jso.get("result").toString());
                        String access_token = detail.get("access_token").toString();
                        String refresh_token = detail.get("refresh_token").toString();
                        String org_id = detail.get("org_id").toString();
                        Map<String,String> updateMap = new HashMap<String,String>();
                        updateMap.put("org_id",org_id);
                        updateMap.put("refresh_token",refresh_token);
                        updateMap.put("access_token",access_token);
                        updateMap.put("oldrefreshtoken",oldrefreshtoken);
                        orderMapper.updateTOrgToken(updateMap);
                        LOGGER.error("------------ 成功 更新了一次数据库 T token-------------------- " );
                    }else{
                        LOGGER.error("----------------更新失败，检擦！！！---------------------- " + org.get("org_id").toString());
                    }
                }
            }
        }catch (Exception e){
            //e.printStackTrace();
            //如果出异常，就再来一次试试
            try{
                List<Map<String,String>> orgList = orderMapper.getDBTAllOrgList();
                if(orgList != null && orgList.size() != 0){
                    for(Map<String,String> org : orgList){
                        Map<String,String> parma = new HashMap<String,String>();
                        parma.put("grantType","refresh_token");
                        parma.put("appKey",org.get("AppKey"));
                        String oldrefreshtoken = org.get("refresh_token");
                        parma.put("refreshToken",oldrefreshtoken);
                        String result = HttpClient.doGeturlparams("https://openapi.chanjet.com/auth/refreshToken", parma,"");
                        //将返回的 result 解析出来，写回数据库！,并一定更新 最后的 更新时间 ,其实 只有 refresh_token,token,和 更新时间会变。
                        JSONObject jso = JSONObject.parseObject(result);
                        if("200".equals(jso.get("code").toString())){//调用成功，更新数据库！
                            JSONObject detail = JSONObject.parseObject(jso.get("result").toString());
                            String access_token = detail.get("access_token").toString();
                            String refresh_token = detail.get("refresh_token").toString();
                            String org_id = detail.get("org_id").toString();
                            Map<String,String> updateMap = new HashMap<String,String>();
                            updateMap.put("org_id",org_id);
                            updateMap.put("refresh_token",refresh_token);
                            updateMap.put("access_token",access_token);
                            updateMap.put("oldrefreshtoken",oldrefreshtoken);
                            orderMapper.updateTOrgToken(updateMap);
                            LOGGER.error("------------ 成功 更新了一次数据库 T token-------------------- " );
                        }else{
                            LOGGER.error("----------------更新失败，检擦！！！---------------------- " + org.get("org_id").toString());
                        }
                    }
                }
            }catch (Exception ex){
                LOGGER.error("---------------- T+ token更新 两次都TM失败了，检查一下看看！ ---------------------- " );
                ex.printStackTrace();
            }
        }finally {
            return "success";
        }
    }

    @Override
    public String refreshMeiTuanToken(){
        try {
            List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
            if(orgList != null && orgList.size() != 0){
                for(Map<String,String> org : orgList){
                    Map<String,String> parma = new HashMap<String,String>();
                    String signKey = org.get("signKey");
                    String timestamp = ""+(System.currentTimeMillis()/1000);
                    parma.put("timestamp",timestamp);
                    parma.put("scope","all");
                    parma.put("developerId",org.get("developerId"));
                    parma.put("charset","UTF-8");
                    parma.put("businessId","18");
                    parma.put("grantType","refresh_token");
                    String oldrefreshtoken = org.get("refreshToken");
                    parma.put("refreshToken",oldrefreshtoken);
                    String sign = SignUtil.getSign(signKey,parma);
                    parma.put("sign",sign);
                    String url = "https://api-open-cater.meituan.com/oauth/refresh";
                    String res = HttpClient.MeiTuansendPostRequest(url,parma);
                    JSONObject jobres = JSONObject.parseObject(res);
                    String data = jobres.getString("data");
                    String accessToken = JSONObject.parseObject(data).getString("accessToken");
                    String refreshToken = JSONObject.parseObject(data).getString("refreshToken");
                    Map<String,String>  updateMap = new HashMap<String,String>();
                    updateMap.put("signKey",signKey);
                    updateMap.put("developerId",org.get("developerId"));
                    updateMap.put("accessToken",accessToken);
                    updateMap.put("refreshToken",refreshToken);
                    orderMapper.updateOrgToken(updateMap);
                    LOGGER.error("------------ 成功 更新了一次数据库 美团 token-------------------- " );
                }
            }
        }catch (Exception e){
            //e.printStackTrace();
            //如果出异常，就再来一次试试
            try{
                List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
                if(orgList != null && orgList.size() != 0){
                    for(Map<String,String> org : orgList){
                        Map<String,String> parma = new HashMap<String,String>();
                        String signKey = org.get("signKey");
                        String timestamp = ""+(System.currentTimeMillis()/1000);
                        parma.put("timestamp",timestamp);
                        parma.put("scope","all");
                        parma.put("developerId",org.get("developerId"));
                        parma.put("charset","UTF-8");
                        parma.put("businessId","18");
                        parma.put("grantType","refresh_token");
                        String oldrefreshtoken = org.get("refresh_token");
                        parma.put("refreshToken",oldrefreshtoken);
                        String sign = SignUtil.getSign(signKey,parma);
                        parma.put("sign",sign);
                        String url = "https://api-open-cater.meituan.com/oauth/refresh";
                        String res = HttpClient.MeiTuansendPostRequest(url,parma);
                        JSONObject jobres = JSONObject.parseObject(res);
                        String data = jobres.getString("data");
                        String accessToken = JSONObject.parseObject(data).getString("accessToken");
                        String refreshToken = JSONObject.parseObject(data).getString("refreshToken");
                        Map<String,String>  updateMap = new HashMap<String,String>();
                        updateMap.put("signKey",signKey);
                        updateMap.put("developerId",org.get("developerId"));
                        updateMap.put("accessToken",accessToken);
                        updateMap.put("refreshToken",refreshToken);
                        orderMapper.updateOrgToken(updateMap);
                        LOGGER.error("------------ 成功 更新了一次数据库 美团 token-------------------- " );
                    }
                }
            }catch (Exception ex){
                LOGGER.error("---------------- 美团 token更新 两次都TM失败了，检查一下看看！ ---------------------- " );
                ex.printStackTrace();
            }
        }finally {
            return "success";
        }
    }


    //------------------------------------------- 天财 -----------------------------------------------------//
    //获取 天财 门店报货单的 明细列表  yyyy-MM-dd
    //此接口 废弃！  不宰调用！
    @Override
    public TcBHDresult getTCMDBHDList(String shopid, String busdate) {
        TcBHDresult mdbhdresult;
        String url =  tiancaiurl+"getStorebill4MDBD.do";
        Map<String,String> params = new HashMap<String,String>();
        params.put("ent",tiancaient);
        params.put("username",tiancaiadmin);
        params.put("password",tiancaipassword);
        params.put("busdate",busdate);
        params.put("shopid",shopid);
        try {
            String res = HttpClient.doGeturlparams(url,params,"");// result 这是一个JSON字符串！
            mdbhdresult = JSONObject.parseObject(res, TcBHDresult.class);//门店报货单
        }catch (IOException e){
            LOGGER.error("获取天财门店报货单接口出错！确认参数和访问地址！！！");
            mdbhdresult = null;
        }
        //----------------根据  仓库+配送中心 下发 给 米大------------------------
        for(TCBHDData tcbhdData :  mdbhdresult.getData()){
            CreatedOrderBo orderInfo = new CreatedOrderBo();
            orderInfo.setCustomerOrderNo(tcbhdData.getInStoreCode());
            String shopAddress = getTCMDList().getData().get(0).getADDR();
            orderInfo.setAddress("天府一街369号");//门店报货单的地址？？？ 就是 门店的地址吗？
            List<CreatedOrderBo.OrderItem> items = new ArrayList<>();
            CreatedOrderBo.OrderItem item = new CreatedOrderBo.OrderItem();
            item.setQty(tcbhdData.getInBusAmount());//数量
            item.setItemCode(tcbhdData.getItemCode());//商品编号
            items.add(item);
            orderInfo.setOrderItems(items);
            Fhdd midafhdd = addMiDaFaHuoChuku(orderInfo);//请求米大  创建订单发货
        }
        //----------------根据  仓库+配送中心 下发 给 弘人-------------------------
        Request req = new Request();
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setDeliveryOrderCode("出库单号, string (50) , 必填");
        deliveryOrder.setOrderType("PTCK");//发货单(JYCK=一般交易出库单, HHCK=换货出库单, BFCK=补发出库单,PTCK=普通出库单，DBCK=调拨出库 ，B2BCK=B2B 出库，SCCK=生产出库，CGTH=采购退货出库单,XNCK=虚拟出库单)
        deliveryOrder.setWarehouseCode("");
        req.setDeliveryOrder(deliveryOrder);
        List<OrderLine> listorderLineMix = new ArrayList<OrderLine>();
        for(TCBHDData tcbhdData :  mdbhdresult.getData()){


            OrderLine oo = new OrderLine();
            //oo.set
            listorderLineMix.add(oo);
        }
        OrderLines orderLines = new OrderLines();
        orderLines.setOrderLine(listorderLineMix);
        req.setOrderLines(orderLines);
        String hongrenre = addHongrenFaHuoChuku(req);
        return mdbhdresult;
    }


    //获取 天财 中心统配出库单 0430_0020_0100 / 中心外销出库单 0430_0020_0300
    @Override
    public TcWXZXresult getTcWXZXresultList(String shopid, String billTypeId, String auditDate){
        TcWXZXresult tcWXZXresult;
        String url =  tiancaiurl+"getDsstorebill.do";
        Map<String,String> params = new HashMap<String,String>();
        params.put("ent",tiancaient);
        params.put("username",tiancaiadmin);
        params.put("password",tiancaipassword);
        params.put("billTypeId",billTypeId);// 只需要 统配出库 / 外销出库
        params.put("shopid",shopid);
        params.put("auditDate",auditDate);
        try {
            String res = HttpClient.doGeturlparams(url,params,"");
            tcWXZXresult = JSONObject.parseObject(res, TcWXZXresult.class);
        }catch (IOException e){
            LOGGER.error("获取天财 中心统配出库单/ 中心外销出库单 / 外销订单 ！确认参数和访问地址！！！");
            tcWXZXresult = null;
        }
        //根据  仓库+配送中心 下发 给 弘人  还是  米大



        return tcWXZXresult; //帅哥！ 相同billNo 是一个单子哦！
    }

    //新增 天财 采购入库单
    @Override
    public String addTCpurcharseOrder(Tccg tccg){
        String url =  tiancaiurl+"api/yj/1.0/create_zxcgrk.do";
        String res = HttpClient.doPostTestTwo(url,tccg);
        return res;
    }

    //WMS发货后，回传 实发 数量给天财的 中心出库单(统配出和外销出回传数量的接口)
    @Override
    public String updateTCchukuReturn(TcchukuReturn tcchukuReturn){
        String url =  tiancaiurl+"/api/yj/1.0/updateAmount.do";
        String res = HttpClient.doPostTestThree(url,tcchukuReturn);
        return res;
    }

    //获取 天财 门店列表
    @Override
    public TcMDresult getTCMDList() {
        TcMDresult mdbhdresult;
        String url = tiancaiurl+"getShop.do";
        Map<String,String> params = new HashMap<String,String>();
        params.put("ent",tiancaient);
        params.put("username",tiancaiadmin);
        params.put("password",tiancaipassword);
        try {
            String res = HttpClient.doGeturlparams(url,params,"");// result 这是一个JSON字符串！
            mdbhdresult = JSONObject.parseObject(res, TcMDresult.class);//门店
        }catch (IOException e){
            LOGGER.error("获取天财 门店 接口出错！确认参数和访问地址！！！");
            mdbhdresult = null;
        }
        return mdbhdresult;
    }

    @Override
    public TcZXresult getTCZXList() {
        TcZXresult mdbhdresult;
        String url = tiancaiurl+"getRdc.do";
        Map<String,String> params = new HashMap<String,String>();
        params.put("ent",tiancaient);
        params.put("username",tiancaiadmin);
        params.put("password",tiancaipassword);
        try {
            String res = HttpClient.doGeturlparams(url,params,"");// result 这是一个JSON字符串！
            mdbhdresult = JSONObject.parseObject(res, TcZXresult.class);//配送中心
        }catch (IOException e){
            LOGGER.error("获取天财 配送中心 接口出错！确认参数和访问地址！！！");
            mdbhdresult = null;
        }
        return mdbhdresult;
    }


    @Override
    public TcCKresult getTCCKList() {
        TcCKresult mdbhdresult;
        String url = tiancaiurl+"getOrganStore.do";
        Map<String,String> params = new HashMap<String,String>();
        params.put("ent",tiancaient);
        params.put("username",tiancaiadmin);
        params.put("password",tiancaipassword);
        try {
            String res = HttpClient.doGeturlparams(url,params,"");// result 这是一个JSON字符串！
            mdbhdresult = JSONObject.parseObject(res, TcCKresult.class);//仓库
        }catch (IOException e){
            LOGGER.error("获取天财 仓库 接口出错！确认参数和访问地址！！！");
            mdbhdresult = null;
        }
        return mdbhdresult;
    }

    @Override
    public String addWMSfahuochukuddByTCdd(TcZXCHUKUReturn tcZXCHUKUReturn)throws Exception{
        ////如果此时，数据库中，这个 tcZXCHUKUReturn.getSourceOrderCode() 存在不？
        int ct = zhongtaiMapper.getCTByTCSourceCode(tcZXCHUKUReturn.getSourceOrderCode());

        //先记录 天财的订单 信息 到数据库
        zhongtaiMapper.insertTCdd(tcZXCHUKUReturn);
        zhongtaiMapper.insertTCddDetail(tcZXCHUKUReturn.getId(),tcZXCHUKUReturn.getDetail());

        //先下发米大WMS的发货订单吧，因为米大的最简单！   但是 天财的单子  不会 下发给 米大 啊！
        /*for(TcZXCHUKUReturnDetail tcZXCHUKUReturnDetail : tcZXCHUKUReturn.getDetail()){
            CreatedOrderBo orderInfo = new CreatedOrderBo();
            orderInfo.setAddress(tcZXCHUKUReturn.getReceiverInfodetailAddress());
            orderInfo.setCustomerOrderNo(tcZXCHUKUReturn.getBillNo());
            orderInfo.setRemarks(tcZXCHUKUReturn.getRemark());
            orderInfo.setWarehouseCode(tcZXCHUKUReturnDetail.getWarehouseCode());
            List<CreatedOrderBo.OrderItem> orderItems = new ArrayList<CreatedOrderBo.OrderItem>();
            CreatedOrderBo.OrderItem orderItem = new CreatedOrderBo.OrderItem();
            orderItem.setItemCode(tcZXCHUKUReturnDetail.getItemCode());
            orderItem.setQty(Integer.valueOf(tcZXCHUKUReturnDetail.getOutBusAmount()));
            orderItems.add(orderItem);
            orderInfo.setOrderItems(orderItems);
            Fhdd mifhddreStr = addMiDaFaHuoChuku(orderInfo);
            // 记录结果
            LOGGER.error("--------------天财出库单下发给米大进行发货出库的结果是： " + mifhddreStr);
        }*/

        //后 下发到 弘人WMS的发货出库订单
        List<Map<String,Object>> dddetailList = zhongtaiMapper.getDDDetailListByddId(""+tcZXCHUKUReturn.getId());//查出这个订单所有明细
        for(int i = 0 ; i < dddetailList.size(); i ++ ){
            Map<String,Object> tcddDetail = dddetailList.get(i);
            String dd_detail_id = tcddDetail.get("dd_detail_id").toString();
            Request hongrenFHDD = new Request();
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            String deliveryOrderCode = "tc-"+tcZXCHUKUReturn.getBillNo() + "-" + i;
            tcddDetail.put("deliveryOrderCode",deliveryOrderCode);
            deliveryOrder.setDeliveryOrderCode(URLEncoder.encode(deliveryOrderCode,"UTF-8"));//出库单号
            deliveryOrder.setOrderType("B2BCK");//B2BCK出库
            //deliveryOrder.setWarehouseCode(tcddDetail.get("warehouseCode").toString());//对应在弘人的仓库
            deliveryOrder.setWarehouseCode("YJLF");
            deliveryOrder.setSourcePlatformCode("OTHERS");
            deliveryOrder.setSourcePlatformName(URLEncoder.encode("天财","UTF-8"));
            deliveryOrder.setCreateTime(new SimpleDateFormat("yyyy-MM-dd~~~HH:mm:ss").format(new Date()));
            deliveryOrder.setServiceCode("NCWLJH");
            deliveryOrder.setOperateTime(new SimpleDateFormat("yyyy-MM-dd~~~HH:mm:ss").format(new Date()));
            deliveryOrder.setShopNick(URLEncoder.encode(tcZXCHUKUReturn.getReceiverInfoname(),"UTF-8"));
            deliveryOrder.setLogisticsCode("OTHER");
            SenderInfo senderInfo = new SenderInfo();
            senderInfo.setName(URLEncoder.encode("天财发货仓库","UTF-8"));
            senderInfo.setMobile("13000000000");//
            senderInfo.setProvince(URLEncoder.encode("四川省","UTF-8"));
            senderInfo.setCity(URLEncoder.encode("成都市","UTF-8"));
            senderInfo.setDetailAddress(URLEncoder.encode("青白江区1号","UTF-8"));
            deliveryOrder.setSenderInfo(senderInfo);
            ReceiverInfo receiverInfo = new ReceiverInfo();
            receiverInfo.setName(URLEncoder.encode(tcZXCHUKUReturn.getReceiverInfoname(),"UTF-8"));
            receiverInfo.setMobile(tcZXCHUKUReturn.getReceiverInfomobile());
            receiverInfo.setProvince(URLEncoder.encode(tcZXCHUKUReturn.getReceiverprovince(),"UTF-8"));
            receiverInfo.setCity(URLEncoder.encode(tcZXCHUKUReturn.getReceivercity(),"UTF-8"));
            receiverInfo.setDetailAddress(URLEncoder.encode(tcZXCHUKUReturn.getReceiverInfodetailAddress(),"UTF-8"));
            deliveryOrder.setReceiverInfo(receiverInfo);
            hongrenFHDD.setDeliveryOrder(deliveryOrder);
            OrderLines orderLines = new OrderLines();
            List<OrderLine> orderLinelist = new ArrayList<OrderLine>();
            OrderLine orderLine = new OrderLine();
            //orderLine.setOrderLineNo(""+);//行号
            orderLine.setOwnerCode("YJLF");//货主编码？ 这玩意是固定的吗？
            orderLine.setItemCode(tcddDetail.get("itemCode").toString());
            orderLine.setPlanQty(Integer.valueOf(tcddDetail.get("outNumbers").toString()));
            //价格 应该不用 传给WMS 啊
            //orderLine.setActualPrice(1.0);// 姚老师说 正在开发啊，后面会加上的
            orderLine.setActualPrice(Double.valueOf(tcddDetail.get("demandPrice").toString()));
            orderLinelist.add(orderLine);
            orderLines.setOrderLine(orderLinelist);
            hongrenFHDD.setOrderLines(orderLines);
            if(ct == 0){
                String hongrenreStr = addHongrenFaHuoChuku(hongrenFHDD);//下发弘人WMS出库
                hongrenreStr = new String(hongrenreStr.getBytes("GBK"), "UTF-8");
                LOGGER.error("--------------天财出库单下发给弘人进行发货出库的结果是：" +hongrenreStr);
                if(hongrenreStr.contains("success")){
                    // 记录结果
                    HongrenWMSddAfter hongrenWMSddAfter = new HongrenWMSddAfter();
                    hongrenWMSddAfter.setDdid(dd_detail_id);
                    hongrenWMSddAfter.setDeliveryOrderCode(tcddDetail.get("deliveryOrderCode").toString());
                    zhongtaiMapper.insertHongrenWMSddAfter(hongrenWMSddAfter);
                    Long ddidd = hongrenWMSddAfter.getId();
                    // 记录对应明细
                    tcddDetail.put("ddidd",ddidd);
                    zhongtaiMapper.insertHongrenWMSddAfterDetail(tcddDetail);
                }else{
                    //下发给弘人的发货订单失败了！！
                    LOGGER.error("--------------天财出库单下发给弘人进行发货出库 失败了，对应的订单明细ID： " + dd_detail_id);
                }
            }else{//只记录，不下发！
                // 记录结果
                LOGGER.error("--------------只记录，不下发！----------------" );
                HongrenWMSddAfter hongrenWMSddAfter = new HongrenWMSddAfter();
                hongrenWMSddAfter.setDdid(dd_detail_id);
                hongrenWMSddAfter.setDeliveryOrderCode(tcddDetail.get("deliveryOrderCode").toString());
                zhongtaiMapper.insertHongrenWMSddAfter(hongrenWMSddAfter);
                Long ddidd = hongrenWMSddAfter.getId();
                // 记录对应明细
                tcddDetail.put("ddidd",ddidd);
                zhongtaiMapper.insertHongrenWMSddAfterDetail(tcddDetail);
            }
        }
        return "";
    }

    //------------------------------------------- 弘人 -----------------------------------------------------//
    @Override
    public String addHongrenFaHuoChuku(Request req) {
        String result;
        Map<String,String> params = new HashMap<String,String>();
        String secret = "36255923b94a5f667519a30524637e9c";
        String body = ObjectToXmlConverter.convertObjectToXml(req);
        body = body.replaceAll(" ","");
        body = body.replaceAll("~~~"," ");
        body = body.replaceAll("\\n","");
        body = body.substring(52,body.length());
        LOGGER.error("--------------天财出库单下发给弘人 body 是： " + body);
        params.put("secret",secret);
        params.put("app_key","YJLF");
        params.put("customerId","YJLF");
        params.put("format","xml");
        params.put("method","mixorder.create");
        //params.put("partner_id","");
        params.put("sign_method","md5");
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            params.put("timestamp",timestamp);
            params.put("v","2.0");
            String md5re = secret+"app_key"+params.get("app_key")+"customerId"+params.get("customerId")+"format"+params.get("format")+"method"+params.get("method")+"sign_method"+params.get("sign_method")+"timestamp"+params.get("timestamp")+"v"+params.get("v")+body+secret;
            //LOGGER.error("--------------天财出库单下发给弘人 md5re 是： " + md5re);
            String sign = Md5.md5(md5re);
            params.put("sign",sign.toUpperCase());//大写！
            String urltimestamp = URLEncoder.encode(timestamp,"UTF-8");
            String url = "http://vpn-nj.hr-network.cn:3185/api/edi/qimen/service?app_key="+params.get("app_key")+"&sign="+params.get("sign")
                    +"&method="+params.get("method")+"&timestamp="+urltimestamp+"&sign_method="+params.get("sign_method")
                    +"&v="+params.get("v")+"&format="+params.get("format")+"&customerId="+params.get("customerId");
            result = HttpClient.doPostXMLTEST(url,params,body);
        }catch (Exception e){
            result = "订单下发给弘人WMS进行发货出库 失败！！！";
        }
        return result;
    }

    @Override
    public String addHongrenRuKuToTCMT(String xml)throws Exception{
        //把弘人反馈的入库数据 转换 成一个 JAVA 对象 ，然后把此对象  hongrenruku  通过 天财 的采购入库单 写入 ，返回 结果！
        com.example.nuonuo.entity.hongren.rukuqr.Request hongrenruku = XmlToObjectConverter.convertXmlToObject(xml, com.example.nuonuo.entity.hongren.rukuqr.Request.class);
        List<Map<String,String>> rukuList = new ArrayList<Map<String,String>>();//用来记录弘人的的入库DB
        for(com.example.nuonuo.entity.hongren.rukuqr.OrderLine orderLine : hongrenruku.getOrderLines().getOrderLine()){
            Map<String,String> itemMap = zhongtaiMapper.getItemMapByMIDA(orderLine.getItemCode());
            Tccg tccg = new Tccg();
            tccg.setUsername(tiancaiadmin);
            tccg.setPassword(tiancaipassword);
            tccg.setEnt(tiancaient);
            tccg.setMessageId("HONGREN"+System.currentTimeMillis());
            tccg.setBusDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            tccg.setSourceId("001");//001 是弘人。002是米大
            tccg.setAutoAuditFlag(0);
            tccg.setCenterId("402887698e3ad5f9018e3b7910530068");//查询正式环境后，修改 写死就行，中心 一般不会改的。 因为 客户就2个配送中心，并且跟 仓库编号是绑定了的，目前，hongren就是 重庆中心的
            tccg.setSystemId("001");
            tccg.setSourceName("弘人");
            tccg.setBusUserId("admin");
            tccg.setArrivalDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            //tccg.setSupplierId(hongrenruku.getEntryOrder().getSupplierCode()) ;// 供货商  这个是对的哈，要放开，测试才用下面这个
            tccg.setSupplierId("0973") ; //0971	芭夯食品开发有限公司  0973	成都甘师傅食品有限公司
            List<TccgDetail> detail = new ArrayList<TccgDetail>();
            TccgDetail tccgDetail = new TccgDetail();
            //tccgDetail.setInStoreId(hongrenruku.getEntryOrder().getWarehouseCode()); //这个地方 应该 取匹配 仓库对照表 的数据 ，对应的 天财系统的仓库
            tccgDetail.setInStoreId("CK001");//402887698e3ad5f9018e3b794789006b  CK001
            tccgDetail.setItemId(orderLine.getItemCode());
            tccgDetail.setUnitId(itemMap.get("tcItemUnitCode").toString());
            tccgDetail.setInBusAmount(orderLine.getActualQty());
            tccgDetail.setBusUnitPrice(Float.valueOf(itemMap.get("chengbenjia").toString()));//不含税 单价
            tccgDetail.setInStoreMoney( Float.valueOf(orderLine.getActualQty()) * Float.valueOf(itemMap.get("chengbenjia"))   );//不含税 金额
            tccgDetail.setTaxes(0);//税率
            //tccgDetail.setGiftFlag(false);// 赠品标志 0 否 1 是
            tccgDetail.setMakeDate(orderLine.getProductDate());
            tccgDetail.setBatchCode(orderLine.getProductDate());//批次号 就用 生产日期
            tccgDetail.setRemark(hongrenruku.getEntryOrder().getRemark());//备注！
            detail.add(tccgDetail);
            tccg.setDetail(detail);
            String tcres = addTCpurcharseOrder(tccg);
            LOGGER.error("------- 弘人的入库反馈信息同步天财入库的结果是：" + tcres);

            Map<String,String> rukuMapDB = new HashMap<String,String>();
            rukuMapDB.put("sourceType","hongren");
            rukuMapDB.put("warehouseCode",hongrenruku.getEntryOrder().getWarehouseCode());
            rukuMapDB.put("itemCode",orderLine.getItemCode());
            rukuMapDB.put("supplierCode",itemMap.get("tcSupplierCode"));
            rukuMapDB.put("supplierName",itemMap.get("supplierName"));
            rukuMapDB.put("actualQty",""+orderLine.getActualQty());
            rukuMapDB.put("price",itemMap.get("chengbenjia"));
            rukuMapDB.put("taxPrice",itemMap.get("chengbenjia"));
            rukuMapDB.put("tax","0");
            rukuMapDB.put("amount",Float.valueOf(itemMap.get("chengbenjia").toString())*Float.valueOf(orderLine.getActualQty())+"");
            rukuMapDB.put("taxAmont",Float.valueOf(itemMap.get("chengbenjia").toString())*Float.valueOf(orderLine.getActualQty())+"");
            rukuList.add(rukuMapDB);
        }
        zhongtaiMapper.addRukuDetailByWMS(rukuList);//这句话是用来写入数据库的！！

        //然后把此对象  hongrenruku  通过 美团 的采购入库单 写入 ，返回 结果！
        /*for(int i = 0; i < hongrenruku.getOrderLines().getOrderLine().size(); i ++){
            com.example.nuonuo.entity.hongren.rukuqr.OrderLine warehouseStockBo = hongrenruku.getOrderLines().getOrderLine().get(i);
            Map<String,String> itemMap = zhongtaiMapper.getItemMapByMIDA(warehouseStockBo.getItemCode());
            //然后把此对象  hongrenruku  通过 美团 的采购入库单 写入 ，返回 结果！
            ScmChainCreateStockPurchaseInRequest meituanCg = new ScmChainCreateStockPurchaseInRequest();
            meituanCg.setOrgId(2038410L);//配送中心id
            meituanCg.setBizTime(System.currentTimeMillis());
            meituanCg.setSupplierCode(itemMap.get("mtSupplierCode"));
            List<CrateStockInItemDetailDTO> crateStockInItemDetailDTOs = new ArrayList<CrateStockInItemDetailDTO>();
            CrateStockInItemDetailDTO csto = new CrateStockInItemDetailDTO();
            csto.setSeqId(i+1);
            csto.setGoodsCode(warehouseStockBo.getItemCode());
            csto.setBizUnitCode(itemMap.get("mtItemUnitCode"));//美团的此商品对应的 单位 编号
            csto.setWarehouseCode("ZBCK02");//这里必须要用美团这边的仓库编码 ，不能用米大的  米大在美团里面只对应了一个仓库！
            csto.setBizUnitAmount(""+warehouseStockBo.getActualQty());
            String dateStr = warehouseStockBo.getProductDate();
            Long datel = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr).getTime();
            csto.setProductionDate(""+datel);
            csto.setBatchNum(warehouseStockBo.getBatchCode());
            csto.setMoney(""+Float.valueOf(warehouseStockBo.getActualQty())*Float.valueOf(itemMap.get("chengbenjia")));//含税金额
            csto.setTaxRate("0");//税率 0-1
            csto.setBizUnitPrice(itemMap.get("chengbenjia"));//采购单位单价
            crateStockInItemDetailDTOs.add(csto);
            meituanCg.setCrateStockInItemDetailDTOs(crateStockInItemDetailDTOs);
            List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
            String appAuthToken = orgList.get(0).get("accessToken").toString();
            String meituanRes = addMeituanRuku(meituanCg,"wybkd1o9lsh99ttx","112274",appAuthToken);
            LOGGER.error("------- 米大的入库反馈信息同步美团入库的结果是：" + meituanRes);
        }*/
        return "";
    }

    @Override
    public String addHongrenChuKuToTCMT(String xml){
        //把弘人反馈的 出库确认数据 转成一个JAVA 对象
        com.example.nuonuo.entity.hongren.chukuqr.Request hongrenchuku = XmlToObjectConverter.convertXmlToObject(xml, com.example.nuonuo.entity.hongren.chukuqr.Request.class);

        //1先更新上游系统， 2再更新中台数据库

        //美团 配送反写
        //ScmChainDeliveryDeliveryOrder1Request mtdv = new ScmChainDeliveryDeliveryOrder1Request();
        // 其实这个地方 考验的 是  数据库的 表关系 设计
        // 1. 记录 美团的配送单 信息 。2 记录 下发给米大后的 返回信息  3. 米大出库后，根据 米大的 发货订单关系信息， 再反推到 对于的 美团 配送单 上面！
        //List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
        //String appAuthToken = orgList.get(0).get("accessToken").toString();
        ///String mTres = addMeituanPeisongFaHuo(mtdv, "wybkd1o9lsh99ttx", "112274", appAuthToken);

        //天财 出库反写
        Map<String,Object> tcddMap = zhongtaiMapper.getTCDDdetailByHongRenChukuReturn(hongrenchuku.getOrderLines().getOrderLine().get(0).getItemCode(),hongrenchuku.getDeliveryOrder().getDeliveryOrderCode());
        TcchukuReturn tcchukuReturn = new TcchukuReturn();
        tcchukuReturn.setUsername(tiancaiadmin);
        tcchukuReturn.setPassword(tiancaipassword);
        tcchukuReturn.setEnt(tiancaient);
        tcchukuReturn.setAutoAuditFlag(0);
        tcchukuReturn.setMessageId("HONGREN"+System.currentTimeMillis());
        tcchukuReturn.setCldBillId(tcddMap.get("voucherId").toString());
        List<TcchukuReturnDetail> listdetail = new ArrayList<TcchukuReturnDetail>();
        for(com.example.nuonuo.entity.hongren.rukuqr.OrderLine orderLine : hongrenchuku.getOrderLines().getOrderLine()){
            TcchukuReturnDetail tcchukuReturnDetail = new TcchukuReturnDetail();
            tcchukuReturnDetail.setDtId(tcddMap.get("detailId").toString());
            tcchukuReturnDetail.setOutBusAmount(Integer.valueOf(orderLine.getActualQty()));
            listdetail.add(tcchukuReturnDetail);
        }
        tcchukuReturn.setDetail(listdetail);
        String tCres = updateTCchukuReturn(tcchukuReturn);
        LOGGER.error("------- 调用天财出库确认反写的结果是：" + tCres);
        if(tCres.contains("true")){
            //更新下 下发给弘人后的发货订单明细里面的 实发数量 2.4 表 - 实发数量为空的表
            zhongtaiMapper.updateWMSDDDetailByHongren(hongrenchuku.getOrderLines().getOrderLine().get(0).getItemCode(),hongrenchuku.getDeliveryOrder().getDeliveryOrderCode(),hongrenchuku.getOrderLines().getOrderLine().get(0).getActualQty());
        }
        return "";
    }

    //------------------------------------------- 米大-----------------------------------------------------//
    @Override
    public Fhdd addMiDaFaHuoChuku(CreatedOrderBo orderInfo){
        CreatedOrderRequest request = new CreatedOrderRequest();
        request.setOrderBo(orderInfo);
        GetBatchOrderResponse response = Mida.client.execute(request);
        String responseBody = response.getBody();
        LOGGER.info("--------------- 米大的订单下推后返回结果是： " + responseBody);
        Fhdd fhdd = JSONObject.parseObject(responseBody,Fhdd.class);
        return fhdd;
    }

    @Override
    public Midack getMiDakucun(SkuStockInfo skuStockInfo){
        GetSkuStockInfoRequest request = new GetSkuStockInfoRequest();
        request.setStockInfo(skuStockInfo);
        GetSkuStockInfoResponse response = Mida.client.execute(request);
        String responseBody = response.getBody();
        Midack midack = JSONObject.parseObject(responseBody,Midack.class);
        return midack;
    }

    @Override
    public DDdetail getMiDaDDdetail(OrderInfo orderInfo){
        GetOrderInfoRequest request = new GetOrderInfoRequest();
        request.setOrderInfo(orderInfo);
        GetOrderInfoResponse response = Mida.client.execute(request);
        String responseBody = response.getBody();
        DDdetail dddetail = JSONObject.parseObject(responseBody,DDdetail.class);
        return dddetail;
    }

    @Override
    public BatchSkuCukun getBatchSkuStockInfo(BatchSkuStockInfo batchSkuStockInfo){
        GetBatchSkuStockRequest request = new GetBatchSkuStockRequest();
        request.setBatchSkuStockInfo(batchSkuStockInfo);
        GetBatchSkuStockInfoResponse response = Mida.client.execute(request);
        String responseBody = response.getBody();
        BatchSkuCukun batchSkuCukun = JSONObject.parseObject(responseBody,BatchSkuCukun.class);
        return batchSkuCukun;
    }

    @Override
    public String addMiDaChuKuToTCMT(OrderDetailsRespose midachukureturn)throws Exception{
        // 其实这个地方 考验的 是  数据库的 表关系 设计
        // 1. 记录 美团的配送单 信息 。2 记录 下发给米大后的 返回信息  3. 米大出库后，根据 米大的 发货订单关系信息， 再反推到 对于的 美团 配送单 上面！
        //更新下 下发给米大的发货订单明细里面的 实发数量
        zhongtaiMapper.updateWMSDDDetailByMida(midachukureturn.getOrderNo(),midachukureturn.getItems().get(0).getItemCode(),midachukureturn.getItems().get(0).getQty());
        Map<String,Object> mtdetailMap = zhongtaiMapper.getMTddDetailByMIDA(midachukureturn.getOrderNo(),midachukureturn.getItems().get(0).getItemCode());
        //这里姚判断下，如果应发 > 实发，就需要 调用  配送单发货 + 配送单拆弹 两个接口。
        // 否则 就只调用  配送单发货的 接口。
        if(Float.valueOf(mtdetailMap.get("planQty").toString()) == Float.valueOf(midachukureturn.getItems().get(0).getQty()) ){
            //把米大的出库数据，反推给  美团的配送单 （不会反写到天财的单子上，因为 天财的单子 只可能会下发给弘人，不会下发给米大！）
            ScmChainDeliveryDeliveryOrder1Request mtdv = new ScmChainDeliveryDeliveryOrder1Request();
            mtdv.setOrgId(2038410L);
            mtdv.setItemSn(mtdetailMap.get("voucherCode").toString());
            //mtdv.setVersion(2);
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Long datel = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr).getTime();
            mtdv.setDeliveryTime(datel);
            mtdv.setExpectDeliveryTime(datel);
            mtdv.setExpectReceiveTime(datel);
            List<DeliveryItemDetailDTO> listDe = new ArrayList<DeliveryItemDetailDTO>();
            DeliveryItemDetailDTO delivo = new DeliveryItemDetailDTO();
            delivo.setGoodsId(mtdetailMap.get("itemId").toString());
            delivo.setUnitId(mtdetailMap.get("unitId").toString());
            delivo.setWarehouseId(mtdetailMap.get("warehouseId").toString());
            delivo.setDeliveryAmount(""+midachukureturn.getItems().get(0).getQty());
            listDe.add(delivo);
            mtdv.setDeliveryRows(listDe);
            List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
            String appAuthToken = orgList.get(0).get("accessToken").toString();
            String mTres = addMeituanPeisongFaHuo(mtdv, "wybkd1o9lsh99ttx", "112274", appAuthToken);
            return mTres;
        }
        if(Float.valueOf(mtdetailMap.get("planQty").toString()) > Float.valueOf(midachukureturn.getItems().get(0).getQty()) ){
            // 先发 再拆？ 还是 先拆再发啊？  我写一个拆单，如果需要 配送发货单，上面的复制下即可！
            MeituanPeisongChaidanDTO chaidanDTO = new MeituanPeisongChaidanDTO();
            chaidanDTO.setOrgId(2038410L);
            chaidanDTO.setItemSn(mtdetailMap.get("voucherCode").toString());
            List<SpiltDeliveryOrderDetailDTO> details = new ArrayList<SpiltDeliveryOrderDetailDTO>();
            SpiltDeliveryOrderDetailDTO deliveryOrderDetailDTO = new SpiltDeliveryOrderDetailDTO();
            deliveryOrderDetailDTO.setGoodsId(mtdetailMap.get("itemId").toString());
            deliveryOrderDetailDTO.setId(mtdetailMap.get("detailId").toString());
            deliveryOrderDetailDTO.setSplitAmount(""+(Float.valueOf(mtdetailMap.get("planQty").toString())-Float.valueOf(midachukureturn.getItems().get(0).getQty())));
            deliveryOrderDetailDTO.setSplitAll(false);//是否全拆？？？ 啥意思啊 ！
            details.add(deliveryOrderDetailDTO);
            chaidanDTO.setDetails(details);
            List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
            String appAuthToken = orgList.get(0).get("accessToken").toString();
            String mTres = addMeituanPeisongChaiDan(chaidanDTO, "wybkd1o9lsh99ttx", "112274", appAuthToken);
        }
        return "";
    }

    @Override
    public String addMiDaRuKuToTCMT(InboundStockInfo midaruku)throws Exception{
        //然后把此对象  midaruku  通过 天财 的采购入库单 写入 ，返回 结果！
        List<Map<String,String>> rukuList = new ArrayList<Map<String,String>>();//用来记录入库DB的
        for(WarehouseStockBo warehouseStockBo : midaruku.getStockDetailBos()){
            Map<String,String> itemMap = zhongtaiMapper.getItemMapByMIDA(warehouseStockBo.getItemCode());
            Tccg tccg = new Tccg();
            tccg.setUsername(tiancaiadmin);
            tccg.setPassword(tiancaipassword);
            tccg.setEnt(tiancaient);
            tccg.setMessageId("MIDA"+System.currentTimeMillis());
            tccg.setBusDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            tccg.setSourceId("002");//001 是弘人。002是米大
            tccg.setAutoAuditFlag(0);
            tccg.setCenterId("402887698e3ad5f9018e3b7910530068");//查询正式环境后，修改 写死就行，中心 一般不会改的。 因为 客户就2个配送中心，并且跟 仓库编号是绑定了的，目前，hongren就是 重庆中心的
            tccg.setSystemId("001");
            tccg.setSourceName("米大");
            tccg.setBusUserId("admin");
            tccg.setArrivalDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            tccg.setSupplierId(midaruku.getSupplierCode()) ;// 供货商  这个是对的哈，要放开，测试才用下面这个
            //tccg.setSupplierId("0973") ; //0971	芭夯食品开发有限公司  0973	成都甘师傅食品有限公司
            List<TccgDetail> detail = new ArrayList<TccgDetail>();
            TccgDetail tccgDetail = new TccgDetail();
            //tccgDetail.setInStoreId(hongrenruku.getEntryOrder().getWarehouseCode()); //这个地方 应该 取匹配 仓库对照表 的数据 ，对应的 天财系统的仓库
            tccgDetail.setInStoreId("CK001");//402887698e3ad5f9018e3b794789006b  CK001
            tccgDetail.setItemId(warehouseStockBo.getItemCode());
            tccgDetail.setUnitId(itemMap.get("tcItemUnitCode").toString());
            tccgDetail.setInBusAmount(Integer.valueOf(warehouseStockBo.getInboundQty()));
            tccgDetail.setBusUnitPrice(Float.valueOf(itemMap.get("chengbenjia").toString()));//不含税 单价
            tccgDetail.setInStoreMoney( Float.valueOf(warehouseStockBo.getInboundQty()) * Float.valueOf(itemMap.get("chengbenjia"))   );//不含税 金额
            tccgDetail.setTaxes(0);//税率
            //tccgDetail.setGiftFlag(false);// 赠品标志 0 否 1 是
            tccgDetail.setMakeDate(warehouseStockBo.getBatchList().get(0).getProducedAt());
            tccgDetail.setBatchCode(warehouseStockBo.getBatchList().get(0).getProducedAt());//批次号 就用 生产日期
            //tccgDetail.setRemark(warehouseStockBo.get);//备注！
            detail.add(tccgDetail);
            tccg.setDetail(detail);
            String tcres = addTCpurcharseOrder(tccg);
            LOGGER.error("------- 米大的入库反馈信息同步天财入库的结果是：" + tcres);

            Map<String,String> rukuMapDB = new HashMap<String,String>();
            rukuMapDB.put("sourceType","mida");
            rukuMapDB.put("warehouseCode",warehouseStockBo.getWarehouseCode());
            rukuMapDB.put("itemCode",warehouseStockBo.getItemCode());
            rukuMapDB.put("supplierCode",midaruku.getSupplierCode());
            rukuMapDB.put("supplierName",midaruku.getSupplierName());
            rukuMapDB.put("actualQty",""+warehouseStockBo.getInboundQty());
            rukuMapDB.put("price",itemMap.get("chengbenjia").toString());
            rukuMapDB.put("taxPrice",itemMap.get("chengbenjia").toString());
            rukuMapDB.put("tax","0");
            rukuMapDB.put("amount",Float.valueOf(itemMap.get("chengbenjia").toString())*Float.valueOf(warehouseStockBo.getInboundQty())+"");
            rukuMapDB.put("taxAmont",Float.valueOf(itemMap.get("chengbenjia").toString())*Float.valueOf(warehouseStockBo.getInboundQty())+"");
            rukuList.add(rukuMapDB);
        }
        zhongtaiMapper.addRukuDetailByWMS(rukuList);//这句话是用来写入数据库的！！

        // ------------------ 美团入库 ------------------------ //
        /*for(int i = 0; i < midaruku.getStockDetailBos().size(); i ++){
            WarehouseStockBo warehouseStockBo = midaruku.getStockDetailBos().get(i);
            Map<String,String> itemMap = zhongtaiMapper.getItemMapByMIDA(warehouseStockBo.getItemCode());
            //然后把此对象  hongrenruku  通过 美团 的采购入库单 写入 ，返回 结果！
            ScmChainCreateStockPurchaseInRequest meituanCg = new ScmChainCreateStockPurchaseInRequest();
            meituanCg.setOrgId(2038410L);//配送中心id
            meituanCg.setBizTime(System.currentTimeMillis());
            meituanCg.setSupplierCode(itemMap.get("mtSupplierCode"));
            List<CrateStockInItemDetailDTO> crateStockInItemDetailDTOs = new ArrayList<CrateStockInItemDetailDTO>();
            CrateStockInItemDetailDTO csto = new CrateStockInItemDetailDTO();
            csto.setSeqId(i+1);
            csto.setGoodsCode(warehouseStockBo.getItemCode());
            csto.setBizUnitCode(itemMap.get("mtItemUnitCode"));//美团的此商品对应的 单位 编号
            csto.setWarehouseCode("ZBCK02");//这里必须要用美团这边的仓库编码 ，不能用米大的  米大在美团里面只对应了一个仓库！
            csto.setBizUnitAmount(warehouseStockBo.getInboundQty());
            String dateStr = warehouseStockBo.getBatchList().get(0).getProducedAt();
            Long datel = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr).getTime();
            csto.setProductionDate(""+datel);
            csto.setBatchNum(warehouseStockBo.getBatchList().get(0).getid());
            csto.setMoney(""+Float.valueOf(warehouseStockBo.getInboundQty())*Float.valueOf(itemMap.get("chengbenjia")));//含税金额
            csto.setTaxRate("0");//税率 0-1
            csto.setBizUnitPrice(itemMap.get("chengbenjia"));//采购单位单价
            crateStockInItemDetailDTOs.add(csto);
            meituanCg.setCrateStockInItemDetailDTOs(crateStockInItemDetailDTOs);
            List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
            String appAuthToken = orgList.get(0).get("accessToken").toString();
            String meituanRes = addMeituanRuku(meituanCg,"wybkd1o9lsh99ttx","112274",appAuthToken);
            LOGGER.error("------- 米大的入库反馈信息同步美团入库的结果是：" + meituanRes);
        }*/
        return "";
    }

    //------------------------------------------- 美团-----------------------------------------------------//
    @Override
    public String addMeituanRuku(ScmChainCreateStockPurchaseInRequest cg,String signKey,String developerId,String appAuthToken){
        String url = "https://api-open-cater.meituan.com/rms/scmplus/inventory/api/v1/chain/stockPurchaseIn/create";//采购入库
        String result = "";
        try{
            Map<String, String> signMap = new HashMap<String, String>();
            signMap.put("appAuthToken",appAuthToken);
            signMap.put("businessId","18");
            signMap.put("charset","utf-8");
            signMap.put("developerId",developerId);
            signMap.put("timestamp",""+(System.currentTimeMillis()/1000));
            signMap.put("version","2");
            signMap.put("biz",JSONObject.toJSONString(cg));
            LOGGER.error("------- 调用美团的采购入库单的参数biz：" + JSONObject.toJSONString(cg));
            String sign = SignUtil.getSign(signKey,signMap);
            signMap.put("sign",sign);
            result = HttpClient.MeiTuansendPostRequest(url,signMap);
            result = new String(result.getBytes("GBK"), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
            result = "------------------ 美团采购入库的代码异常，检查serviceImpl ------------------";
        }
        return result;
    }

    @Override
    public String addMeituanPeisongFaHuo(ScmChainDeliveryDeliveryOrder1Request dv, String signKey, String developerId, String appAuthToken){
        String url = "https://api-open-cater.meituan.com/rms/scmplus/distribution/api/v1/chain/deliveryorder/delivery";//配送发货
        String result = "";
        try {
            Map<String, String> signMap = new HashMap<String, String>();
            signMap.put("appAuthToken",appAuthToken);
            signMap.put("businessId","18");
            signMap.put("charset","utf-8");
            signMap.put("developerId",developerId);
            signMap.put("timestamp",""+(System.currentTimeMillis()/1000));
            signMap.put("version","2");
            signMap.put("biz",JSONObject.toJSONString(dv));
            String sign = SignUtil.getSign(signKey,signMap);
            signMap.put("sign",sign);
            result = HttpClient.MeiTuansendPostRequest(url,signMap);
            result = new String(result.getBytes("GBK"), "UTF-8");
            LOGGER.info("------------调用美团的配送发货单 结果：" + result);
        }catch (Exception e){
            e.printStackTrace();
            result = "------------------ 美团配送发货的代码异常，检查serviceImpl ------------------";
        }
        return result;
    }


    @Override
    public String addMeituanPeisongChaiDan(MeituanPeisongChaidanDTO chaidanDTO, String signKey, String developerId, String appAuthToken){
        String url = "https://api-open-cater.meituan.com/rms/scmplus/distribution/api/v1/chain/deliveryorder/spilt";//配送拆单
        String result = "";
        try {
            Map<String, String> signMap = new HashMap<String, String>();
            signMap.put("appAuthToken",appAuthToken);
            signMap.put("businessId","18");
            signMap.put("charset","utf-8");
            signMap.put("developerId",developerId);
            signMap.put("timestamp",""+(System.currentTimeMillis()/1000));
            signMap.put("version","2");
            signMap.put("biz",JSONObject.toJSONString(chaidanDTO));
            String sign = SignUtil.getSign(signKey,signMap);
            signMap.put("sign",sign);
            result = HttpClient.MeiTuansendPostRequest(url,signMap);
            result = new String(result.getBytes("GBK"), "UTF-8");
            LOGGER.info("------------调用美团的配送单拆单 结果：" + result);
        }catch (Exception e){
            e.printStackTrace();
            result = "------------------ 调用美团的配送单拆单代码异常，检查serviceImpl ------------------";
        }
        return result;
    }

    @Override
    public String addWMSfahuochukuddByMTdd(MeituanPeiSong meituanPeiSong)throws Exception{
        //1. 先记录美团的配送单信息到数据库！
        zhongtaiMapper.insertMTdd(meituanPeiSong);
        Long ddid = meituanPeiSong.getId();
        zhongtaiMapper.insertMTddDetail(ddid,meituanPeiSong.getBizData().getDetails());

        //2. 要根据仓库来判断，不一定都要发。

        //先下发米大WMS的发货订单吧，因为米大的最简单！
        List<Map<String,Object>> dddetailList = zhongtaiMapper.getDDDetailListByddId(""+ddid);
        for(int i = 0; i <  dddetailList.size(); i ++ ){
            Map<String,Object> meituanPeisongDetail = dddetailList.get(i);
            String dd_detail_id = meituanPeisongDetail.get("dd_detail_id").toString();
            CreatedOrderBo orderInfo = new CreatedOrderBo();
            orderInfo.setAddress(meituanPeiSong.getBizData().getItem().getReceiverInfo().getAddress());
            orderInfo.setCustomerOrderNo(meituanPeiSong.getBizData().getItem().getItemSn());
            orderInfo.setRemarks(meituanPeiSong.getBizData().getItem().getRemark());
            //orderInfo.setWarehouseCode(meituanPeisongDetail.get("warehouseCode").toString());  米大 可以 不用传仓库
            List<CreatedOrderBo.OrderItem> orderItems = new ArrayList<CreatedOrderBo.OrderItem>();
            CreatedOrderBo.OrderItem orderItem = new CreatedOrderBo.OrderItem();
            orderItem.setItemCode(meituanPeisongDetail.get("itemCode").toString());
            //orderItem.setItemCode(meituanPeisongDetail.getGoodsInfo().getCode());
            orderItem.setQty(Integer.valueOf(meituanPeisongDetail.get("outNumbers").toString()));
            //orderItem.setQty(Integer.valueOf(meituanPeisongDetail.getReceiveOrderAmount().getBizUnitAmount()));
            orderItems.add(orderItem);
            orderInfo.setOrderItems(orderItems);
            Fhdd mifhddreStr = addMiDaFaHuoChuku(orderInfo);
            LOGGER.error("--------------美团配送单下发给米大进行发货出库的结果是： " + mifhddreStr);

            // 记录米大的发货订单 结果
            zhongtaiMapper.insertMidaWMSddAfter(dd_detail_id,mifhddreStr);
            zhongtaiMapper.insertMidaWMSddAfterDetail(mifhddreStr.getId(),mifhddreStr.getItems());
        }

        //后 下发到 弘人WMS的发货出库订单
        //for(DeliveryOrderItemDetailDTO meituanPeisongDetail : meituanPeiSong.getBizData().getDetails()){
        /*for(Map<String,Object> meituanPeisongDetail : dddetailList){
            String dd_detail_id = meituanPeisongDetail.get("dd_detail_id").toString();

            Request hongrenFHDD = new Request();
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            String deliveryOrderCode = "mt-"+meituanPeiSong.getBizData().getItem().getItemSn();
            meituanPeisongDetail.put("deliveryOrderCode",deliveryOrderCode);
            deliveryOrder.setDeliveryOrderCode(URLEncoder.encode(deliveryOrderCode,"UTF-8"));//出库单号
            deliveryOrder.setOrderType("B2BCK");//B2BCK出库
            //deliveryOrder.setWarehouseCode(tcddDetail.get("warehouseCode").toString());//对应在弘人的仓库
            deliveryOrder.setWarehouseCode("YJLF");
            deliveryOrder.setSourcePlatformCode("OTHERS");
            deliveryOrder.setSourcePlatformName(URLEncoder.encode("美团","UTF-8"));
            deliveryOrder.setCreateTime(new SimpleDateFormat("yyyy-MM-dd~~~HH:mm:ss").format(new Date()));
            deliveryOrder.setServiceCode("NCWLJH");
            deliveryOrder.setOperateTime(new SimpleDateFormat("yyyy-MM-dd~~~HH:mm:ss").format(new Date()));
            deliveryOrder.setShopNick(URLEncoder.encode(meituanPeiSong.getBizData().getItem().getReceiveOrg().getName(),"UTF-8"));
            deliveryOrder.setLogisticsCode("OTHER");
            SenderInfo senderInfo = new SenderInfo();
            senderInfo.setName(URLEncoder.encode("aaa","UTF-8"));//
            senderInfo.setMobile("13000000000");//
            senderInfo.setProvince(URLEncoder.encode("四川省","UTF-8"));//
            senderInfo.setCity(URLEncoder.encode("成都市","UTF-8"));//
            senderInfo.setDetailAddress(URLEncoder.encode("青白江区1号","UTF-8"));//
            deliveryOrder.setSenderInfo(senderInfo);
            ReceiverInfo receiverInfo = new ReceiverInfo();
            receiverInfo.setName(URLEncoder.encode(meituanPeiSong.getBizData().getItem().getReceiverInfo().getContactName(),"UTF-8"));//
            receiverInfo.setMobile(meituanPeiSong.getBizData().getItem().getReceiverInfo().getPhone());//
            receiverInfo.setProvince(URLEncoder.encode("四川省","UTF-8"));//
            //receiverInfo.setProvince(URLEncoder.encode(tcZXCHUKUReturn.getReceiverprovince(),"UTF-8"));//
            receiverInfo.setCity(URLEncoder.encode("成都市","UTF-8"));//
            //receiverInfo.setCity(URLEncoder.encode(tcZXCHUKUReturn.getReceivercity(),"UTF-8"));//
            receiverInfo.setDetailAddress(URLEncoder.encode(meituanPeiSong.getBizData().getItem().getReceiverInfo().getAddress(),"UTF-8"));//
            deliveryOrder.setReceiverInfo(receiverInfo);
            hongrenFHDD.setDeliveryOrder(deliveryOrder);
            OrderLines orderLines = new OrderLines();
            List<OrderLine> orderLinelist = new ArrayList<OrderLine>();
            OrderLine orderLine = new OrderLine();
            //orderLine.setOrderLineNo(""+);//行号
            orderLine.setOwnerCode("YJLF");//货主编码？ 这玩意是固定的吗？
            orderLine.setItemCode(meituanPeisongDetail.get("itemCode").toString());
            orderLine.setPlanQty(Integer.valueOf(meituanPeisongDetail.get("outNumbers").toString()));
            //价格 应该不用 传给WMS 啊
            orderLine.setActualPrice(Double.valueOf(meituanPeisongDetail.get("demandPrice").toString()));
            orderLinelist.add(orderLine);
            orderLines.setOrderLine(orderLinelist);
            hongrenFHDD.setOrderLines(orderLines);
            String hongrenreStr = addHongrenFaHuoChuku(hongrenFHDD);//下发弘人WMS出库
            hongrenreStr = new String(hongrenreStr.getBytes("GBK"), "UTF-8");
            LOGGER.error("--------------美团配送单下发给弘人进行发货出库的结果是： " + hongrenreStr);
            if(hongrenreStr.contains("success")){
                // 记录结果
                HongrenWMSddAfter hongrenWMSddAfter = new HongrenWMSddAfter();
                hongrenWMSddAfter.setDdid(dd_detail_id);
                hongrenWMSddAfter.setDeliveryOrderCode(meituanPeisongDetail.get("deliveryOrderCode").toString());
                zhongtaiMapper.insertHongrenWMSddAfter(hongrenWMSddAfter);
                Long ddidd = hongrenWMSddAfter.getId();
                // 记录对应明细
                meituanPeisongDetail.put("ddidd",ddidd);
                zhongtaiMapper.insertHongrenWMSddAfterDetail(meituanPeisongDetail);
            }else{
                //下发给弘人的发货订单失败了！！
            }
        }*/
        return "";
    }
}


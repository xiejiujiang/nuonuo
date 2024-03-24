package com.example.nuonuo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.hongren.chukufahuomix.DeliveryOrder;
import com.example.nuonuo.entity.hongren.chukufahuomix.OrderLine;
import com.example.nuonuo.entity.hongren.chukufahuomix.OrderLines;
import com.example.nuonuo.entity.hongren.chukufahuomix.Request;
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
import com.example.nuonuo.entity.tiancaiqitadd.TcWXZXresult;
import com.example.nuonuo.mapper.orderMapper;
import com.example.nuonuo.service.TokenService;
import com.example.nuonuo.utils.*;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

    public String refreshToken(){
        try {
            List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
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
                        orderMapper.updateOrgToken(updateMap);
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
                List<Map<String,String>> orgList = orderMapper.getDBAllOrgList();
                if(orgList != null && orgList.size() != 0){
                    for(Map<String,String> org : orgList){
                        Map<String,String> parma = new HashMap<String,String>();
                        parma.put("grantType","refresh_token");
                        parma.put("appKey",org.get("AppKey"));
                        parma.put("refreshToken",org.get("refresh_token"));
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
                            orderMapper.updateOrgToken(updateMap);
                            LOGGER.error("------------ 成功 更新了一次数据库 T token-------------------- " );
                        }else{
                            LOGGER.error("----------------更新失败，检擦！！！---------------------- " + org.get("org_id").toString());
                        }
                    }
                }
            }catch (Exception ex){
                LOGGER.error("----------------T+ token更新 再来一次都TM失败了，检查一下看看！ ---------------------- " );
                ex.printStackTrace();
            }
        }finally {
            return "success";
        }
    }

    //获取E看牙的token
    @Override
    public String refreshEToken() {
        return "success";
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
            item.setSkuId(tcbhdData.getItemId());//商品ID？（ tcbhdData.getItemCode() ）
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

    //------------------------------------------- 弘人 -----------------------------------------------------//
    @Override
    public String addHongrenFaHuoChuku(Request req) {
        String result;
        Map<String,String> params = new HashMap<String,String>();
        String secret = "36255923b94a5f667519a30524637e9c";
        String body = ObjectToXmlConverter.convertObjectToXml(req);
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
            result = HttpClient.doPostXMLTEST("http://localhost:9998/nuonuo/token/testhongren?app_key="+params.get("app_key")+"&sign="+params.get("sign")
                            +"&method="+params.get("method")+"&timestamp="+params.get("timestamp")+"&sign_method="+params.get("sign_method")
                            +"&v="+params.get("v")+"&format="+params.get("format"),
                    params,body);
        }catch (Exception e){
            result = "订单下发给弘人WMS进行发货出库 失败！！！";
        }
        return result;
    }

    @Override
    public String addHongrenRuKuToTCMT(String xml){
        //把弘人反馈的入库数据 转换 成一个 JAVA 对象 ，然后把此对象  hongrenruku  通过 天财 的采购入库单 写入 ，返回 结果！
        com.example.nuonuo.entity.hongren.rukuqr.Request hongrenruku = XmlToObjectConverter.convertXmlToObject(xml, com.example.nuonuo.entity.hongren.rukuqr.Request.class);
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
        tccg.setSupplierId("supplierzcghsorgan");// 供货商
        List<TccgDetail> detail = new ArrayList<TccgDetail>();
        for(com.example.nuonuo.entity.hongren.rukuqr.OrderLine orderLine : hongrenruku.getOrderLines().getOrderLine()){
            TccgDetail tccgDetail = new TccgDetail();
            tccgDetail.setInStoreId(hongrenruku.getEntryOrder().getWarehouseCode());
            tccgDetail.setItemId(orderLine.getItemId());
            tccgDetail.setInBusAmount(orderLine.getActualQty());
            tccgDetail.setBusUnitPrice(10);//不含税 单价
            tccgDetail.setInStoreMoney(10);//不含税 金额
            tccgDetail.setTaxes(0);//税率
            tccgDetail.setGiftFlag(false);// 赠品标志 0 否 1 是
            tccgDetail.setMakeDate(orderLine.getProductDate());
            tccgDetail.setBatchCode(orderLine.getProductDate());//批次号 就用 生产日期
            tccgDetail.setRemark(hongrenruku.getEntryOrder().getRemark());//备注！
            detail.add(tccgDetail);
        }
        String tcres = addTCpurcharseOrder(tccg);

        //然后把此对象  hongrenruku  通过 美团 的采购入库单 写入 ，返回 结果！

        return "";
    }

    @Override
    public String addHongrenChuKuToTCMT(String xml){
        //把弘人反馈的 出库确认数据 转成一个JAVA 对象
        com.example.nuonuo.entity.hongren.chukuqr.Request hongrenchuku = XmlToObjectConverter.convertXmlToObject(xml, com.example.nuonuo.entity.hongren.chukuqr.Request.class);
        //然后把此对象  hongrenchuku  通过 天财的中心出库单反写 / 美团的配送单反写

        return "";
    }

    //------------------------------------------- 米大-----------------------------------------------------//
    @Override
    public Fhdd addMiDaFaHuoChuku(CreatedOrderBo orderInfo){
        CreatedOrderRequest request = new CreatedOrderRequest();
        request.setOrderBo(orderInfo);
        GetBatchOrderResponse response = Mida.client.execute(request);
        String responseBody = response.getBody();
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
    public String addMiDaChuKuToTCMT(List<OrderDetailsRespose> midachukureturn){
        //把米大的出库数据，反推给 天财的中心出库单反写 / 美团的配送单反写

        return "";
    }

    @Override
    public String addMiDaRuKuToTCMT(InboundStockInfo midaruku){
        //然后把此对象  hongrenruku  通过 天财 的采购入库单 写入 ，返回 结果！
        Tccg tccg = new Tccg();
        tccg.setUsername(tiancaiadmin);
        tccg.setPassword(tiancaipassword);
        tccg.setEnt(tiancaient);
        tccg.setMessageId("MIDA"+System.currentTimeMillis());//由于米大的入库回传时，无此字段，所以 自定义了。当前行秒数
        tccg.setBusDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        tccg.setSourceId("002");//001 是弘人。002是米大
        tccg.setAutoAuditFlag(0);
        tccg.setCenterId("402887698e3ad5f9018e3b7910530068");//查询正式环境后，修改 写死就行，中心 一般不会改的。 因为 客户就2个配送中心，并且跟 仓库编号是绑定了的，目前，米大就是 重庆中心的
        tccg.setSystemId("002");
        tccg.setSourceName("米大");
        tccg.setBusUserId("admin");
        tccg.setArrivalDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        tccg.setSupplierId(midaruku.getSupplierCode());// 供货商
        List<TccgDetail> detail = new ArrayList<TccgDetail>();
        for(WarehouseStockBo warehouseStockBo : midaruku.getStockDetailBos()){
            TccgDetail tccgDetail = new TccgDetail();
            tccgDetail.setInStoreId(warehouseStockBo.getWarehouseCode());
            tccgDetail.setItemId(warehouseStockBo.getSkuId());
            tccgDetail.setInBusAmount(Integer.valueOf(warehouseStockBo.getInboundQty()));
            tccgDetail.setBusUnitPrice(10);//不含税 单价
            tccgDetail.setInStoreMoney(10);//不含税 金额
            tccgDetail.setTaxes(0);//税率
            tccgDetail.setGiftFlag(false);// 赠品标志 0 否 1 是
            tccgDetail.setMakeDate(warehouseStockBo.getBatchList().get(0).getProducedAt());
            tccgDetail.setBatchCode(warehouseStockBo.getBatchList().get(0).getProducedAt());//批次号 就用 生产日期吗
            //tccgDetail.setRemark("xxxxx");//备注！
            detail.add(tccgDetail);
        }
        tccg.setDetail(detail);
        String tcres = addTCpurcharseOrder(tccg);//同步到天财的采购入库单！
        //然后把此对象  hongrenruku  通过 美团 的采购入库单 写入 ，返回 结果！

        return "";
    }

}

package com.example.nuonuo.service;


import com.example.nuonuo.entity.hongren.chukufahuomix.Request;
import com.example.nuonuo.entity.mida.batchcukundtail.BatchSkuCukun;
import com.example.nuonuo.entity.mida.chukureturn.OrderDetailsRespose;
import com.example.nuonuo.entity.mida.dddetail.DDdetail;
import com.example.nuonuo.entity.mida.fahuoddct.Fhdd;
import com.example.nuonuo.entity.mida.kucun.Midack;
import com.example.nuonuo.entity.mida.rukureturn.InboundStockInfo;
import com.example.nuonuo.entity.tiancai.*;
import com.example.nuonuo.entity.tiancaicg.Tccg;
import com.example.nuonuo.entity.tiancaiqitadd.TcWXZXresult;
import com.jiujin.scm.open.sdk.bo.BatchSkuStockInfo;
import com.jiujin.scm.open.sdk.bo.CreatedOrderBo;
import com.jiujin.scm.open.sdk.bo.OrderInfo;
import com.jiujin.scm.open.sdk.bo.SkuStockInfo;

import java.util.List;

public interface TokenService {

    public String refreshToken();

    public String refreshEToken();

    // --------------------------- 天财接口 --------------------------------- //

    //获取 天财 门店报货单的 明细列表  yyyy-MM-dd
    public TcBHDresult getTCMDBHDList(String shopid, String busdate);

    //获取 天财 外销订单/ 中心统配出库单 0430_0020_0100 / 中心外销出库单 0430_0020_0300
    public TcWXZXresult getTcWXZXresultList(String shopid, String billTypeId,String auditDate);

    //新增 天财 采购入库单
    public String addTCpurcharseOrder(Tccg tccg);

    //获取 天财 门店  明细列表
    public TcMDresult getTCMDList();

    //获取 天财 中心  明细列表
    public TcZXresult getTCZXList();

    //获取 天财 仓库  明细列表
    public TcCKresult getTCCKList();

    //调用 天财 采购入库单 创建接口，同步库存信息！
    //public void addTcRUKU(Request HongRenRUKU);

    //WMS发货后，回传 实发 数量给天财的 中心出库单(统配出和外销出回传数量的接口)
    public String updateTCchukuReturn(TcchukuReturn tcchukuReturn);


    // --------------------------- 弘人WMS接口 --------------------------------- //
    //组装成 弘人WMS 的 出库发货mix 参数对象后，再调用接口 进行 发货出库mix
    public String addHongrenFaHuoChuku(Request req);

    public String addHongrenRuKuToTCMT(String xml);//根据弘人WMS返回的入库数据 进行入库（天财/美团？）

    public String addHongrenChuKuToTCMT(String xml);//根据弘人WMS返回的出库确认数据，反写到 天财 中心出库单？ 美团 配送单？

    //--------------------------- 米大WMS接口 --------------------------------- //
    //发货订单创建接口
    public Fhdd addMiDaFaHuoChuku(CreatedOrderBo orderInfo);

    //库存查询接口
    public Midack getMiDakucun(SkuStockInfo skuStockInfo);

    //获取订单详情接口
    public DDdetail getMiDaDDdetail(OrderInfo orderInfo);

    //批量根据skuId获取库存信息
    public BatchSkuCukun getBatchSkuStockInfo(BatchSkuStockInfo batchSkuStockInfo);

    public String addMiDaChuKuToTCMT(List<OrderDetailsRespose> midachukureturn);

    public String addMiDaRuKuToTCMT(InboundStockInfo midaruku);
}

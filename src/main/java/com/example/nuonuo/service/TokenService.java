package com.example.nuonuo.service;


import com.example.nuonuo.entity.Meituan.Caigou.ScmChainCreateStockPurchaseInRequest;
import com.example.nuonuo.entity.Meituan.Chaidan.MeituanPeisongChaidanDTO;
import com.example.nuonuo.entity.Meituan.MeituanPeiSong;
import com.example.nuonuo.entity.Meituan.PeisongFaHuo.ScmChainDeliveryDeliveryOrder1Request;
import com.example.nuonuo.entity.hongren.chukufahuomix.Request;
import com.example.nuonuo.entity.mida.batchcukundtail.BatchSkuCukun;
import com.example.nuonuo.entity.mida.chukureturn.OrderDetailsRespose;
import com.example.nuonuo.entity.mida.dddetail.DDdetail;
import com.example.nuonuo.entity.mida.fahuoddct.Fhdd;
import com.example.nuonuo.entity.mida.fahuoddct.MidaFhdd;
import com.example.nuonuo.entity.mida.kucun.Midack;
import com.example.nuonuo.entity.mida.rukureturn.InboundStockInfo;
import com.example.nuonuo.entity.tiancai.*;
import com.example.nuonuo.entity.tiancaicg.Tccg;
import com.example.nuonuo.entity.tiancaichukureturn.TcZXCHUKUReturn;
import com.example.nuonuo.entity.tiancaiqitadd.TcWXZXresult;
import com.jiujin.scm.open.sdk.bo.BatchSkuStockInfo;
import com.jiujin.scm.open.sdk.bo.CreatedOrderBo;
import com.jiujin.scm.open.sdk.bo.OrderInfo;
import com.jiujin.scm.open.sdk.bo.SkuStockInfo;

import java.util.List;
import java.util.Map;

public interface TokenService {

    public String refreshTToken();

    public String refreshMeiTuanToken();

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

    //根据 天财反馈到中台的 中心出库单（统配+外销）进行 下发WMS发货出库
    public void addWMSfahuochukuddByTCdd(TcZXCHUKUReturn tcZXCHUKUReturn)throws Exception;

    // --------------------------- 弘人WMS接口 --------------------------------- //
    //组装成 弘人WMS 的 出库发货mix 参数对象后，再调用接口 进行 发货出库mix
    public String addHongrenFaHuoChuku(Request req);

    //根据弘人WMS返回的入库数据 进行入库（天财/美团？）
    public void addHongrenRuKuToTCMT(com.example.nuonuo.entity.hongren.rukuqr.Request hongrenruku);

    //根据弘人WMS返回的出库确认数据，反写到 天财 中心出库单？ 美团 配送单？
    public void addHongrenChuKuToTCMT(com.example.nuonuo.entity.hongren.chukuqr.Request hongrenchuku)throws Exception;

    //--------------------------- 米大WMS接口 --------------------------------- //
    //发货订单创建接口
    public MidaFhdd addMiDaFaHuoChuku(CreatedOrderBo orderInfo);

    //库存查询接口
    public Midack getMiDakucun(SkuStockInfo skuStockInfo);

    //获取订单详情接口
    public DDdetail getMiDaDDdetail(OrderInfo orderInfo);

    //批量根据skuId获取库存信息
    public BatchSkuCukun getBatchSkuStockInfo(BatchSkuStockInfo batchSkuStockInfo);

    public void addMiDaChuKuToTCMT(OrderDetailsRespose midachukureturn)throws Exception;

    public void addMiDaRuKuToTCMT(InboundStockInfo midaruku);

    //--------------------------- 美团接口 --------------------------------- //
    //总部-采购入库：https://developer.meituan.com/docs/api/rms-scmplus-inventory-api-v1-chain-stockPurchaseIn-create
    public String addMeituanRuku(ScmChainCreateStockPurchaseInRequest cg, String signKey,String developerId, String appAuthToken);

    //配送发货单：https://developer.meituan.com/docs/api/rms-scmplus-distribution-api-v1-chain-deliveryorder-delivery
    public String addMeituanPeisongFaHuo(ScmChainDeliveryDeliveryOrder1Request dv, String signKey, String developerId, String appAuthToken);

    //根据 美团反馈到中台的 配送单 进行 下发WMS发货出库
    public void addWMSfahuochukuddByMTdd(MeituanPeiSong meituanPeiSong);

    public String addMeituanPeisongChaiDan(MeituanPeisongChaidanDTO chaidanDTO, String signKey, String developerId, String appAuthToken);
}

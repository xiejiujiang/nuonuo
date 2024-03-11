package com.example.nuonuo.service;


import com.example.nuonuo.entity.tiancai.TcBHDresult;
import com.example.nuonuo.entity.tiancai.TcCKresult;
import com.example.nuonuo.entity.tiancai.TcMDresult;
import com.example.nuonuo.entity.tiancai.TcZXresult;

public interface TokenService {

    public String refreshToken();

    public String refreshEToken();

    // --------------------------- 天财接口 --------------------------------- //

    //获取 天财 门店报货单的 明细列表  yyyy-MM-dd
    public TcBHDresult getTCMDBHDList(String shopid, String busdate);

    //获取 天财 门店  明细列表
    public TcMDresult getTCMDList();

    //获取 天财 中心  明细列表
    public TcZXresult getTCZXList();

    //获取 天财 仓库  明细列表
    public TcCKresult getTCCKList();

    //调用 天财 采购入库单 创建接口，同步库存信息！
    //public void addTcRUKU(Request HongRenRUKU);


    // --------------------------- 弘人WMS接口 --------------------------------- //
    public String addHongrenDDByTcDD(Object oo);//根据天财订单下发至 弘人WMS进行出库发货


}

package com.example.nuonuo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface orderMapper {

    String getDBtest();

    //返回数据库中 XXX_code_token 所有记录的 企业信息
    List<Map<String,String>> getDBTAllOrgList();

    //返回数据库中 meituan_code_token 所有记录的 企业信息
    List<Map<String,String>> getDBAllOrgList();

    //根据 appKey 返回数据库中 XXX_code_token 所有记录的 企业信息
    Map<String,String> getDBAllOrgListByappKey(String appKey);

    Map<String,String> getDBAllOrgListByappKeyMobile(String mobile);

    String getSKVourcherStateByCode(String code);

    //调用refreshtoken 后更新 美团 数据库
    void updateOrgToken(Map<String,String> updateMap);

    //调用refreshtoken 后更 T+ 新数据库
    void updateTOrgToken(Map<String,String> updateMap);

    void updateEOrgToken(Map<String,String> updateMap);

    Map<String,Object> getRedetailMap(String code);

    List<Map<String,Object>> getAllTaxList();

    void updateTokenByAppKey(String appKey,String newToken);

    List<Map<String,Object>>  getRetailDetailListByCode(String code);

    //根据appkey 获取 当前的 token
    public String getTokenByAppKey(@Param("AppKey") String AppKey);

    //订单订单编号 查询 此销售订单对应的商品明细，以及表头上的
    List<Map<String,Object>> getTSaListByCode(String Tsacode);

    //订单订单编号 查询 此生产加工单对应的商品明细，以及表头上的
    List<Map<String,Object>> getTscListByCode(String Tsccode);

    Map<String,Object>  getTdeparmtClerkByMobile(String name);

    String getEtoken();

    Map<String,Object> getTMapByOfficeId(String officeid);

    Map<String,Object> getTinventoryByName(String name);

    List<Map<String,Object>> getCkByInventyCodes(@Param("inventoryCodes")String inventoryCodes,@Param("sacode")String sacode);

    void updateXSCKAUDATE(@Param("xsckdcode")String xsckdcode,@Param("vourcherCode")String vourcherCode);

    void updateXSFPAUDATE(@Param("xxfpcode")String xxfpcode,@Param("vourcherCode")String vourcherCode);

    Integer getXSFPbYCODE(String vourcherCode);

    String getXSFPbYCODECODE(String vourcherCode);

    Map<String,Object> getfapiaoMapBySA(@Param("sacode")String sacode,@Param("inventorycode")String inventorycode,@Param("ProductionDate")String ProductionDate,@Param("factsaleamount")String factsaleamount);

    Map<String,Object> getfapiaoMapBySANoBath(@Param("sacode")String sacode,@Param("inventorycode")String inventorycode,@Param("factsaleamount")String factsaleamount);

    void updateXSCKParas(Map<String,String> parss);
}

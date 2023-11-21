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
    List<Map<String,String>> getDBAllOrgList();

    //根据 appKey 返回数据库中 XXX_code_token 所有记录的 企业信息
    Map<String,String> getDBAllOrgListByappKey(String appKey);

    //调用refreshtoken 后更新数据库
    void updateOrgToken(Map<String,String> updateMap);

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

    Integer getXSFPbYCODE(String vourcherCode);
}

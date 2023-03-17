package com.example.nuonuo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface orderMapper {

    Map<String,Object>  getDistinctTaxByRetaiCode(String code);

    Map<String,Object> getRedetailMap(String code);

    List<Map<String,Object>> getAllTaxList();

    void updateTokenByAppKey(String appKey,String newToken);

    List<Map<String,Object>>  getRetailDetailListByCode(String code);
}

package com.example.nuonuo.mapper;

import com.example.nuonuo.entity.Meituan.DeliveryOrderItemDetailDTO;
import com.example.nuonuo.entity.Meituan.MeituanPeiSong;
import com.example.nuonuo.entity.hongren.chukure.HrchukuReturn;
import com.example.nuonuo.entity.hongren.wmsreturn.HongrenWMSddAfter;
import com.example.nuonuo.entity.mida.chukureturn.MobileEcOrderItemBo;
import com.example.nuonuo.entity.mida.chukureturn.OrderDetailsRespose;
import com.example.nuonuo.entity.mida.fahuoddct.Fhdd;
import com.example.nuonuo.entity.tiancaichukureturn.TcZXCHUKUReturn;
import com.example.nuonuo.entity.tiancaichukureturn.TcZXCHUKUReturnDetail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface zhongtaiMapper {

    //------------------------------- 记录 WMS 的 入库 数据 ----------------------------- //
    void addRukuDetailByWMS(List<Map<String,String>> rukuList);

    Integer insertMTdd(MeituanPeiSong meituanPeiSong);

    void insertMTddDetail(Long ddid,List<DeliveryOrderItemDetailDTO> list);

    Long insertTCdd(TcZXCHUKUReturn tcZXCHUKUReturn);

    void insertTCddDetail(Long ddid, List<TcZXCHUKUReturnDetail> list);

    Integer insertHongrenWMSddAfter(HongrenWMSddAfter hongrenWMSddAfter);

    void insertHongrenWMSddAfterDetail(Map<String,Object> tcddDetail);

    void updateWMSDDDetailByHongren(String itemCode,String deliveryOrderCode,int actualQty);

    void updateWMSDDDetailByMida(String orderNo,String itemCode,Integer qty);

    Integer insertMidaWMSddAfter(String ddid,Fhdd mifhddreStr);

    void insertMidaWMSddAfterDetail(Long ddid,List<MobileEcOrderItemBo> list);

    Map<String,String> getItemMapByMIDA(String itemCode);

    void updateTCItemMap(String itemCode,String unitId);

    List<Map<String,Object>> getDDDetailListByddId(String ddid);

    Map<String,Object> getTCDDdetailByHongRenChukuReturn(String itemCode,String deliveryOrderCode);

    Integer getCTByTCSourceCode(String scode);

    Map<String,Object> getMTddDetailByMIDA(String orderNo,String itemCode);

}

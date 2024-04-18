package com.example.nuonuo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.tiancai.TcMDresult;
import com.example.nuonuo.mapper.zhongtaiMapper;
import com.example.nuonuo.utils.HttpClient;
import com.google.gson.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/shougong")
public class ShougongController {

    private static final String tiancaiurl = "http://yj.test.fxscm.net:1215/cldpoint/";//这个是test的

    private static final String tiancaient = "ENTa5ob";

    private static final String tiancaiadmin = "admin";

    private static final String tiancaipassword = "0000";

    private static final Logger LOGGER = LoggerFactory.getLogger(ShougongController.class);

    @Autowired
    private com.example.nuonuo.mapper.zhongtaiMapper zhongtaiMapper;

    //这个 只是 用来 手工 请求 一些 接口，获取 特定，更新 部分数据用的
    @RequestMapping(value="/updateTCitem", method = {RequestMethod.GET,RequestMethod.POST})
    public @ResponseBody String recode(HttpServletRequest request, HttpServletResponse response) {
        try {
            String url = tiancaiurl+"getItemNew.do";//先查出所有的 品项
            Map<String,String> params = new HashMap<String,String>();
            params.put("ent",tiancaient);
            params.put("username",tiancaiadmin);
            params.put("password",tiancaipassword);
            String res = HttpClient.doGeturlparams(url,params,"");// result 这是一个JSON字符串！
            JSONObject job = JSONObject.parseObject(res);
            String itemListStr = job.getString("data");//所有的商品信息
            List<Map> itemList = JSONObject.parseArray(itemListStr,Map.class);

            String urll = tiancaiurl+"getItemUnitWithFlag.do";//先查出所有的 品项单位
            String ress = HttpClient.doGeturlparams(urll,params,"");// result 这是一个JSON字符串！
            JSONObject jobb = JSONObject.parseObject(ress);
            String itemUnitListStr = jobb.getString("data");//所有的商品单位信息
            List<Map> itemUnitList = JSONObject.parseArray(itemUnitListStr,Map.class);

            for(Map itemMap : itemList){
                String itemCode = itemMap.get("itemCode").toString();
                String itemId1 = itemMap.get("itemId").toString();
                String itemName = itemMap.get("itemName").toString();
                String item1UnitName = itemMap.get("reportUnit").toString();
                LOGGER.info("商品： " + itemCode + ", " + itemName + ", itemId1 = " + itemId1);
                for(Map itemUnitMap : itemUnitList){
                    String itemId2 = itemUnitMap.get("itemId").toString();
                    String itemUnitID = itemUnitMap.get("itemUnitID").toString();
                    String itemUnitName = itemUnitMap.get("itemUnitName").toString();
                    if(itemId2.equals(itemId1)){
                        zhongtaiMapper.updateTCItemMap(itemCode,itemUnitID);
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        LOGGER.error("-------------------  ");
        return "";
    }

}

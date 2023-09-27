package com.example.nuonuo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.Ekanya.Esale.ESaleRoot;
import com.example.nuonuo.entity.Ekanya.Euser.Euser;
import com.example.nuonuo.entity.Ekanya.Eyuangong.Eyuangong;
import com.example.nuonuo.utils.HttpClient;
import com.example.nuonuo.utils.MapToJson;

import java.util.Map;

public interface BasicService {

    public Euser getEUserInfo(String officeId);

    public Euser getEUserInfoById(String patientId);

    public String createWLDW(Euser euser,Map<String,Object> params);

    public ESaleRoot getEplanDetail(String officeId);

    public String createSaorderDetail(ESaleRoot eSaleRoot,Map<String, Object> params);

    public String createSCorderDetail(String Tsacode,Map<String,Object> params);

    public String getGXHBOrderparamsJson(String Tsacode,Map<String, Object> params);

    public String getCLCKOrderparamsJson(String Tsacode,Map<String, Object> params);

    public String getCCPRKOrderparamsJson(Map<String, Object> params);

    public String getSASOrderparamsJson(String Tsacode,Map<String, Object> params);

    public JSONObject getEyuangong(String id);
}
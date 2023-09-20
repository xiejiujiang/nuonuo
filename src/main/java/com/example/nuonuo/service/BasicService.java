package com.example.nuonuo.service;

import com.example.nuonuo.entity.Ekanya.Esale.ESaleRoot;
import com.example.nuonuo.entity.Ekanya.Euser.Euser;
import com.example.nuonuo.entity.Ekanya.Eyuangong.Eyuangong;
import com.example.nuonuo.utils.HttpClient;
import com.example.nuonuo.utils.MapToJson;

import java.util.Map;

public interface BasicService {

    public Euser getEUserInfo(String officeId);

    public String createWLDW(Euser euser,Map<String,Object> params);

    public ESaleRoot getEplanDetail(String officeId);

    public String createSaorderDetail(ESaleRoot eSaleRoot,Map<String, Object> params);

    public String createSCorderDetail(Map<String, Object> params);

    public String getCLCKOrderparamsJson(Map<String, Object> params);

    public String getCCPRKOrderparamsJson(Map<String, Object> params);

    public String getSASOrderparamsJson(Map<String, Object> params);

    public Eyuangong getEyuangong(String id);
}
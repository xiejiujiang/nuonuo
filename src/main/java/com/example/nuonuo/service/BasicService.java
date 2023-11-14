package com.example.nuonuo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.Ekanya.EpatienCZ.EpatienCZ;
import com.example.nuonuo.entity.Ekanya.Esale.ESaleRoot;
import com.example.nuonuo.entity.Ekanya.EsingleUser.EuserSingle;
import com.example.nuonuo.entity.Ekanya.Euser.Euser;
import com.example.nuonuo.entity.Ekanya.Eyuangong.Eyuangong;
import com.example.nuonuo.entity.Ekanya.EyuyueByID.EyuyueByID;
import com.example.nuonuo.entity.Ekanya.EzhangdanByOfficeIdANDTIME.EzhangdanByOfficeIdANDTIME;
import com.example.nuonuo.entity.Ekanya.EzhifuDetailByID.EzhifuDetailByID;
import com.example.nuonuo.entity.Ekanya.EzhifudanByOfficeIdANDTIME.EzhifuData;
import com.example.nuonuo.entity.Ekanya.EzhifudanByOfficeIdANDTIME.EzhifudanByOfficeIdANDTIME;
import com.example.nuonuo.utils.HttpClient;
import com.example.nuonuo.utils.MapToJson;

import java.util.List;
import java.util.Map;

public interface BasicService {

    public Euser getEUserInfo(String officeId,String startTime, String endTime);

    public EuserSingle getEUserInfoByPatientId(String patientId);

    public String createWLDW(Euser euser,Map<String,Object> params);

    public ESaleRoot getEplanDetail(String officeId,String startTime, String endTime);

    public String createSaorderDetail(Map<String,List<EzhifuData>> ezfMap, Map<String, Object> params);

    public String createSCorderDetail(String Tsacode,Map<String,Object> params);

    public String getGXHBOrderparamsJson(String Tsacode,Map<String, Object> params);

    //public String getCLCKOrderparamsJson(String Tsacode,Map<String, Object> params);

    //public String getCCPRKOrderparamsJson(Map<String, Object> params);

    public String getSASOrderparamsJson(String Tsacode,Map<String, Object> params);

    public JSONObject getEyuangong(String id);

    public EpatienCZ getEpatientCZK(String officeId, String patientId);

    public String createTSKByEpatientCZK(EpatienCZ epatienCZ,Map<String, Object> params);


    //--------------------------------------账单-------》 支付单------》 支付明细---------------------------------//

    public EzhangdanByOfficeIdANDTIME getEzhangdanByOfficeIdANDTIME(String officeId, String startTime, String endTime);

    public EzhifudanByOfficeIdANDTIME getEzhifudanByOfficeIdANDTIME(String officeId, String startTime, String endTime);

    public EzhifuDetailByID getEzhifudetailByID(String id);

    public EyuyueByID getEyuyueByIDByID(String id);

}
package com.example.nuonuo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.Ekanya.Euser.Data;
import com.example.nuonuo.entity.Ekanya.Euser.Euser;
import com.example.nuonuo.entity.Ekanya.EzhifuDetailByID.EzhifuDetailData;
import com.example.nuonuo.entity.Ekanya.EzhifudanByOfficeIdANDTIME.EzhifuData;
import com.example.nuonuo.entity.Ekanya.EzhifudanByOfficeIdANDTIME.EzhifuItems;
import com.example.nuonuo.entity.Kcjson.Kcjson;
import com.example.nuonuo.saentity.JsonRootBean;
import com.example.nuonuo.saentity.SaleDeliveryDetails;
import com.example.nuonuo.service.BasicService;
import com.example.nuonuo.service.impl.BasicServiceImpl;
import com.google.gson.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

@lombok.Data
public class MapToJson {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapToJson.class);

    @Autowired
    private BasicService basicService;

    public static String getXMStrByMap(Map<String,String> param){
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> xm = new HashMap<String,Object>();
        xm.put("Code",param.get("code"));
        xm.put("Name",param.get("name"));
        Map<String,Object> ProjectClass = new HashMap<String,Object>();
        ProjectClass.put("Code",param.get("projectclass"));
        xm.put("ProjectClass",ProjectClass);
        dto.put("dto",xm);
        String json = "";//JSONObject.toJSONString(dto);
        return json;
    }


    // 这是一个模板，创建销货单的 请求参数 body 的 模板，其他API 可以参考
    public static String getSAparamsJson(){
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> sa = new HashMap<String,Object>();
        Map<String,Object> Department = new HashMap<String,Object>();
        Department.put("Code","HY");//部门编码
        sa.put("Department",Department);
        Map<String,Object> Clerk = new HashMap<String,Object>();
        Clerk.put("Code","CD-030");//业务员编码
        sa.put("Clerk",Clerk);
        sa.put("VoucherDate","2022-03-15");//单据日期
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）
        Map<String,Object> Customer = new HashMap<String,Object>();
        Customer.put("Code","CD-030");//客户编码
        sa.put("Customer",Customer);
        Map<String,Object> SettleCustomer = new HashMap<String,Object>();
        SettleCustomer.put("Code","CD-030");//结算客户编码（一般等同于 客户编码）
        sa.put("SettleCustomer",SettleCustomer);
        Map<String,Object> BusinessType = new HashMap<String,Object>();
        BusinessType.put("Code","15");//业务类型编码，15–普通销售；16–销售退货
        sa.put("BusinessType",BusinessType);
        Map<String,Object> InvoiceType = new HashMap<String,Object>();
        InvoiceType.put("Code","01");//票据类型，枚举类型；00--普通发票，01--专用发票，02–收据；为空时，默认按收据处理
        sa.put("InvoiceType",InvoiceType);
        Map<String,Object> Warehouse = new HashMap<String,Object>();
        Warehouse.put("Code","0101010101");//表头上的 仓库编码
        sa.put("Warehouse",Warehouse);
        Map<String,Object> ReciveType = new HashMap<String,Object>();
        ReciveType.put("Code","76");//收款方式，枚举类型；00--限期收款，01--全额订金，02--全额现结，03--月结，04--分期收款，05--其它；
        sa.put("ReciveType",ReciveType);
        Map<String,Object> RdStyle = new HashMap<String,Object>();
        RdStyle.put("Code","201");//出库类别，RdStyleDTO对象，默认为“线上销售”类别； 具体值 我是查的数据库。
        sa.put("RdStyle",RdStyle);
        sa.put("Memo","这是 我的 备注内容，请注意查看9！");//备注
        List<Map<String,Object>> SaleDeliveryDetailsList = new ArrayList<Map<String,Object>>();
        Map<String,Object> DetailM1 = new HashMap<String,Object>();
        Map<String,Object> DetailM1Warehouse = new HashMap<String,Object>();
        DetailM1Warehouse.put("code","0101010101");//明细1 的 仓库编码
        DetailM1.put("Warehouse",DetailM1Warehouse);
        Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
        DetailM1Inventory.put("code","HW01-NB-13 S-EMD-W76-YSL");//明细1 的 存货编码
        DetailM1.put("Inventory",DetailM1Inventory);
        Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
        DetailM1Unit.put("Name","台");//明细1 的 存货计量单位
        DetailM1.put("Unit",DetailM1Unit);
        //DetailM1.put("Batch","？？？？？？？？？？？？？？？？？？？");//批号
        DetailM1.put("Quantity","3");//明细1 的 数量
        DetailM1.put("TaxRate","13");//明细1 的 税率
        DetailM1.put("OrigTaxPrice","6666.00");//明细1 的 含税单价(实际上 在传入 来源单据之后，只会用销售订单 上的 单价？？？)

        /*Map<String,Object> SNObject = new HashMap<String,Object>();
        List<Map<String,Object>> SnAccountDetails = new ArrayList<Map<String,Object>>();
        Map<String,Object> snmap1 = new HashMap<String,Object>();
        snmap1.put("SNCode","999006");
        SnAccountDetails.add(snmap1);
        Map<String,Object> snmap2 = new HashMap<String,Object>();
        snmap2.put("SNCode","999004");
        SnAccountDetails.add(snmap2);
        Map<String,Object> snmap3 = new HashMap<String,Object>();
        snmap3.put("SNCode","999001");
        SnAccountDetails.add(snmap3);
        SNObject.put("SnAccountDetails",SnAccountDetails);
        DetailM1.put("SNObject",SNObject);*/

        DetailM1.put("idsourcevouchertype","43");//明细1 的 来源单据类型ID
        DetailM1.put("sourceVoucherCode","SO-2022-03-0006");//明细1 的 来源单据单据编号
        DetailM1.put("sourceVoucherDetailId","9");//明细1 的 来源单据单据对应的明细行ID
        SaleDeliveryDetailsList.add(DetailM1);

        // 表 明细里面的 第二行
        Map<String,Object> DetailM2 = new HashMap<String,Object>();
        Map<String,Object> DetailM2Warehouse = new HashMap<String,Object>();
        DetailM2Warehouse.put("code","0101010101");//明细2 的 仓库编码
        DetailM2.put("Warehouse",DetailM2Warehouse);
        Map<String,Object> DetailM2Inventory = new HashMap<String,Object>();
        DetailM2Inventory.put("code","test1231");//明细2 的 存货编码
        DetailM2.put("Inventory",DetailM2Inventory);
        Map<String,Object> DetailM2Unit = new HashMap<String,Object>();
        DetailM2Unit.put("Name","个");//明细2 的 存货计量单位
        DetailM2.put("Unit",DetailM2Unit);
        //DetailM2.put("Batch","？？？？？？？？？？？？？？？？？？？");//批号
        DetailM2.put("Quantity","3");//明细2 的 数量
        DetailM2.put("TaxRate","13");//明细2 的 税率
        DetailM2.put("OrigTaxAmount","9999.00");//明细2 的 含税金额(实际上 在传入 来源单据之后，只会用销售订单 上的 金额)
        DetailM2.put("idsourcevouchertype","43");//明细2 的 来源单据类型ID
        DetailM2.put("sourceVoucherCode","SO-2022-03-0004");//明细2 的 来源单据单据编号
        DetailM2.put("sourceVoucherDetailId","8");//明细2 的 来源单据单据对应的明细行ID
        SaleDeliveryDetailsList.add(DetailM2);

        sa.put("SaleDeliveryDetails",SaleDeliveryDetailsList);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);

        return js;
    }



    //参照上面的。把零售单的数据 拼成 一个 销货单的 DTO
    public static String getSAJsonByRetailData(List<Map<String,Object>> dataList,List<Map<String,Object>> settleList,String sacode)
    {
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> sa = new HashMap<String,Object>();

        sa.put("Code",sacode);//指定销售订单号

        Map<String,Object> Clerk = new HashMap<String,Object>();
        Clerk.put("Code","APL-9999");//业务员编码  APL-9999  业务员1  财务部
        sa.put("Clerk",Clerk);

        Map<String,Object> Department = new HashMap<String,Object>();
        //Department.put("Code","CWB");//部门编码  CWB  财务部
        Department.put("Code",dataList.get(0).get("departmentCode"));//部门编码
        sa.put("Department",Department);

        String VoucherDate = dataList.get(0).get("VoucherDate").toString();
        sa.put("VoucherDate",VoucherDate);//单据日期
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）

        Map<String,Object> Customer = new HashMap<String,Object>();
        Customer.put("Code","LS001");//客户编码  都用 零售客户
        sa.put("Customer",Customer);
        Map<String,Object> SettleCustomer = new HashMap<String,Object>();
        SettleCustomer.put("Code","LS001");//结算客户编码（一般等同于 客户编码）
        sa.put("SettleCustomer",SettleCustomer);

        Map<String,Object> InvoiceType = new HashMap<String,Object>();
        InvoiceType.put("Code","01");//票据类型，枚举类型；00--普通发票，01--专用发票，02–收据；为空时，默认按收据处理
        sa.put("InvoiceType",InvoiceType);
        Map<String,Object> Warehouse = new HashMap<String,Object>();
        Warehouse.put("Code", dataList.get(0).get("warehouseCode").toString());//表头上的 仓库编码
        sa.put("Warehouse",Warehouse);
        Map<String,Object> ReciveType = new HashMap<String,Object>();
        ReciveType.put("Code","02");//收款方式，枚举类型；00--限期收款，01--全额订金，02--全额现结，03--月结，04--分期收款，05--其它；
        sa.put("ReciveType",ReciveType);// A账都是现结
        Map<String,Object> RdStyle = new HashMap<String,Object>();
        RdStyle.put("Code","201");//出库类别，RdStyleDTO对象，默认为“线上销售”类别； 具体值 我是查的数据库。  201
        sa.put("RdStyle",RdStyle);
        Map<String,Object> BusinessType = new HashMap<String,Object>();
        if("34".equals(dataList.get(0).get("idbusitype").toString())){
            BusinessType.put("Code","15");//业务类型编码，15–普通销售；16–销售退货
        }else{
            BusinessType.put("Code","16");//业务类型编码，15–普通销售；16–销售退货
        }
        sa.put("BusinessType",BusinessType);
        //sa.put("Memo","这一单是根据红旗返回的差异自动生成的，请注意区别 ！");//备注
        List<Map<String,Object>> SaleDeliveryDetailsList = new ArrayList<Map<String,Object>>();
        Float totaltaxamount = 0f;
        for(Map<String,Object> retailmap :dataList){
            String taxamount = retailmap.get("taxamount").toString();
            totaltaxamount = totaltaxamount + Float.valueOf(taxamount);
            Map<String,Object> DetailM = new HashMap<String,Object>();
            Map<String,Object> DetailMWarehouse = new HashMap<String,Object>();
            //明细1 的 仓库编码,这里不好取，但是可以用表头的（因为每一个销货单 只 对应了一个 仓库）
            DetailMWarehouse.put("code",Warehouse.get("Code"));
            DetailM.put("Warehouse",DetailMWarehouse);
            Map<String,Object> DetailMInventory = new HashMap<String,Object>();
            DetailMInventory.put("code",retailmap.get("inventoryCode").toString());//明细1 的 存货编码
            DetailM.put("Inventory",DetailMInventory);
            Map<String,Object> DetailMUnit = new HashMap<String,Object>();
            DetailMUnit.put("Name",retailmap.get("unitName").toString());// 使用 对应 原始销货单上这个商品的计量单位
            DetailM.put("Unit",DetailMUnit);
            //DetailM.put("Batch","？？？？？？？？？？？？？？？？？？？");//批号
            DetailM.put("Quantity", retailmap.get("quantity").toString());//返回的差异数量  送货 - 实收 = 差异
            DetailM.put("TaxRate","13");//明细1 的 税率
            DetailM.put("OrigTaxPrice",retailmap.get("taxprice").toString());//明细1 的 含税单价
            //DetailM.put("idsourcevouchertype","43");//明细1 的 来源单据类型ID
            //如果要跟 销售订单 关联，则需要传入 下面两个参数。
            //DetailM.put("sourceVoucherCode","SO-2022-03-0006");//明细1 的 来源单据单据编号
            //DetailM.put("sourceVoucherDetailId","9");//明细1 的 来源单据单据对应的明细行ID
            SaleDeliveryDetailsList.add(DetailM);
        }
        sa.put("OrigSettleAmount",""+totaltaxamount);// 如果选择了全额现结，就必须录入 现结金额
        List<Map<String,Object>> SaleDeliverySettlements = new ArrayList<Map<String,Object>>();
        for(Map<String,Object>  map : settleList){//此单的 结算方式 明细
            Map<String,Object> settleMap = new HashMap<String,Object>();
            settleMap.put("origAmount",map.get("amount").toString()); //金额
            Map<String,Object> SettleStyle = new HashMap<String,Object>();
            Map<String,Object> BankAccount = new HashMap<String,Object>();
            if("996".equals(map.get("code").toString())){ //08上的代金券
                SettleStyle.put("Code","9996");//结算方式编码
                BankAccount.put("Name","代金券");//账号名称
                settleMap.put("SettleStyle",SettleStyle);
                settleMap.put("BankAccount",BankAccount);
                SaleDeliverySettlements.add(settleMap);
            }
            if("94".equals(map.get("code").toString())){ // 08上的 储值卡
                SettleStyle.put("Code","941");//结算方式编码
                BankAccount.put("Name","会员储值");//账号名称
                settleMap.put("SettleStyle",SettleStyle);
                settleMap.put("BankAccount",BankAccount);
                SaleDeliverySettlements.add(settleMap);
            }
            if("99".equals(map.get("code").toString())){ // 08上的 积分抵现
                SettleStyle.put("Code","991");//结算方式编码
                BankAccount.put("Name","积分抵现");//账号名称
                settleMap.put("SettleStyle",SettleStyle);
                settleMap.put("BankAccount",BankAccount);
                SaleDeliverySettlements.add(settleMap);
            }
            if(!"996".equals(map.get("code").toString()) && !"94".equals(map.get("code").toString())
               && !"99".equals(map.get("code").toString())){
                SettleStyle.put("Code",map.get("code").toString());//结算方式编码
                BankAccount.put("Name",map.get("name").toString());//账号名称
                settleMap.put("SettleStyle",SettleStyle);
                settleMap.put("BankAccount",BankAccount);
                SaleDeliverySettlements.add(settleMap);
            }
        }
        sa.put("SaleDeliverySettlements",SaleDeliverySettlements);
        sa.put("SaleDeliveryDetails",SaleDeliveryDetailsList);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);
        System.out.println("js======" + js);
        return js;
    }

    //创建 客户档案
    public static List<String> getInComeString(Euser euser){
        List<String> list = new ArrayList<>();
        for(Data data : euser.getData()){
            Map<String,Object> mapstr = new HashMap<String,Object>();
            Map<String,Object> mm = new HashMap<>();

            mm.put("Code",data.getPrivateId());//病历号
            mm.put("Name",data.getName());//姓名

            Map<String,Object> PartnerType = new HashMap<>();
            PartnerType.put("Code","01");
            mm.put("PartnerType",PartnerType);
            Map<String,Object> PartnerClass = new HashMap<>();
            PartnerClass.put("Code","0101");
            mm.put("PartnerClass",PartnerClass);//个人客户

            List<Map<String,Object>> PartnerAddreList = new ArrayList<Map<String,Object>>();
            Map<String,Object> PartnerAddresDTOs = new HashMap<>();
            PartnerAddresDTOs.put("ShipmentAddress",data.getHomeAddress());
            PartnerAddresDTOs.put("MobilePhone",data.getMobile());
            PartnerAddreList.add(PartnerAddresDTOs);
            mm.put("PartnerAddresDTOs",PartnerAddreList);

            List<String> DynamicPropertyKeyslist = new ArrayList<>();
            DynamicPropertyKeyslist.add("priuserdefnvc3");
            DynamicPropertyKeyslist.add("priuserdefnvc4");
            DynamicPropertyKeyslist.add("priuserdefnvc5");
            DynamicPropertyKeyslist.add("priuserdefnvc6");
            DynamicPropertyKeyslist.add("priuserdefnvc7");
            DynamicPropertyKeyslist.add("priuserdefnvc8");
            mm.put("DynamicPropertyKeys",DynamicPropertyKeyslist);

            List<String> DynamicPropertyValueslist = new ArrayList<>();
            DynamicPropertyValueslist.add(""+( "1".equals(data.getSex())?"男":"女" ));
            try{
                //算一下年龄？
                Calendar now = Calendar.getInstance();
                Calendar birthday = Calendar.getInstance();
                String birthStr = data.getBirth();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date dd = sdf.parse(birthStr);
                birthday.setTime(dd);
                int age = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
                DynamicPropertyValueslist.add(""+age);
            }catch (Exception e){
                DynamicPropertyValueslist.add("未知");
            }
            DynamicPropertyValueslist.add(data.getIdentityNumber());
            DynamicPropertyValueslist.add(data.getSource().getSourceLevel1());
            DynamicPropertyValueslist.add("".equalsIgnoreCase(data.getSource().getSourceLevel2())?"无":data.getSource().getSourceLevel2());
            DynamicPropertyValueslist.add("".equalsIgnoreCase(data.getSource().getSourceLevel3())?"无":data.getSource().getSourceLevel3());
            mm.put("DynamicPropertyValues",DynamicPropertyValueslist);

            mapstr.put("dto",mm);
            String str =  JSONObject.toJSONString(mapstr);
            list.add(str);
        }
        return list;
    }

    //创建销售订单的 请求参数 body 的 模板
    public static List<String> getSAOrderparamsJson(Map<String,List<EzhifuData>> ezfMap,Map<String,Object> docMap){
        List<String> jsonList = new ArrayList<String>();
        //最后 迭代这个 edatamap，每一个客户（患者），生成一个 销售订单的JSON。所以返回的也应该是一个JSON List
        //开始 迭代上面 这个 整理好的 MAP
        Set entrySet = ezfMap.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()){
            Object oo = iterator.next();
            Map.Entry entry = (Map.Entry)oo;
            String patientId = entry.getKey().toString(); // key 这个病人
            List<EzhifuData> ezhifuDatalist = (List<EzhifuData>)entry.getValue(); // value 这个病人 对应的 所有 今日的支付项目

            //在这个方法内，一个客户 patientId 就对应了 所有的 （包含了 存货，定金金额，收款方式 等） 组合的LIST
            Map<String,Object> dto = new HashMap<String,Object>();
            Map<String,Object> sa = new HashMap<String,Object>();

            //通过 患者档案可以查到 对应的 consultant  咨询师(以及咨询师 对应的 部门)
            Map<String,Object> Department = new HashMap<String,Object>();
            Department.put("Code",((Map)docMap.get(patientId+"-cle")).get("departMentCode").toString());//部门编码
            sa.put("Department",Department);
            Map<String,Object> Clerk = new HashMap<String,Object>();
            Clerk.put("Code",((Map)docMap.get(patientId+"-cle")).get("clerkCode").toString());//业务员编码 （咨询师）
            sa.put("Clerk",Clerk);

            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            sa.put("VoucherDate",today);//单据日期
            sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）

            Map<String,Object> Customer = new HashMap<String,Object>();
            // 通过 病人id patientId 找到 病历号 privateId
            String privateId = docMap.get(patientId+"-privateId").toString();
            Customer.put("Code",privateId);
            sa.put("Customer",Customer);

            /*Map<String,Object> SettleCustomer = new HashMap<String,Object>();
            SettleCustomer.put("Code","CD-030");//结算客户编码（一般等同于 客户编码）
            sa.put("SettleCustomer",SettleCustomer);*/

            Map<String,Object> BusinessType = new HashMap<String,Object>();
            BusinessType.put("Code","15");//业务类型编码，15–普通销售；16–销售退货
            sa.put("BusinessType",BusinessType);

            /*Map<String,Object> InvoiceType = new HashMap<String,Object>();
            InvoiceType.put("Code","01");//票据类型，枚举类型；00--普通发票，01--专用发票，02–收据；为空时，默认按收据处理
            sa.put("InvoiceType",InvoiceType);
            Map<String,Object> Warehouse = new HashMap<String,Object>();
            Warehouse.put("Code","0101010101");//表头上的 仓库编码
            sa.put("Warehouse",Warehouse);*/
            Map<String,Object> ReciveType = new HashMap<String,Object>();
            ReciveType.put("Code","01");//收款方式，枚举类型；00--限期收款，01--全额订金，02--全额现结，03--月结，04--分期收款，05--其它；
            sa.put("ReciveType",ReciveType);

            //表头上的 就诊类型 字符公用自定义项2
            List<String> DynamicPropertyKeyList = new ArrayList<String>();
            DynamicPropertyKeyList.add("pubuserdefnvc2");
            sa.put("DynamicPropertyKeys",DynamicPropertyKeyList);

            List<String> DynamicPropertyValuesList = new ArrayList<String>();
            DynamicPropertyValuesList.add(docMap.get(patientId+"-jzlx").toString());//     初诊/复产新/复诊/复查/临时（挂号的时候会体现）
            sa.put("DynamicPropertyValues",DynamicPropertyValuesList);

            //定金收款
            Float ff = 0f;
            String free = "";
            String freeAmount = "";
            List<Map<String,Object>> SubscriptionsList = new ArrayList<Map<String,Object>>();
            for(EzhifuData ezhifuData : ezhifuDatalist){
                Map<String,Object> Subscriptions = new HashMap<String,Object>();
                for(EzhifuDetailData ezhifuDatadetail : ezhifuData.getEzhifuDatadetail()){
                    Subscriptions.put("origAmount",ezhifuDatadetail.getAmount());//金额
                    Map<String,Object> SettleStyle = new HashMap<String,Object>();
                    String settleType = ezhifuDatadetail.getPaymentMethodName();
                    if(settleType.contains("免单")){
                        free = settleType;
                        freeAmount = ""+ezhifuDatadetail.getAmount();//金额
                    }
                    //LOGGER.info("settleType ============================ " + settleType);
                    SettleStyle.put("Code","997");// 转账？
                    Subscriptions.put("SettleStyle",SettleStyle);
                    Map<String,Object> BankAccount = new HashMap<String,Object>();
                    BankAccount.put("Name",settleType);// 账号名称， 客户已修改和T+一致
                    Subscriptions.put("BankAccount",BankAccount);
                    ff = ff + Float.valueOf(ezhifuDatadetail.getAmount());
                    SubscriptionsList.add(Subscriptions);
                }
            }
            sa.put("Subscriptions",SubscriptionsList);//定金明细
            //定金金额 不传试试？
            // sa.put("OrigEarnestMoney",""+ff);

            /*Map<String,Object> RdStyle = new HashMap<String,Object>();
            RdStyle.put("Code","201");//出库类别，RdStyleDTO对象，默认为“线上销售”类别； 具体值 我是查的数据库。
            sa.put("RdStyle",RdStyle);*/

            sa.put("Memo","此单通过API 自动同步生成！");//备注

            //订单明细
            List<Map<String,Object>> SaleDeliveryDetailsList = new ArrayList<Map<String,Object>>();
            for(EzhifuData ezhifuData : ezhifuDatalist){
                for(EzhifuItems items : ezhifuData.getItems()){
                    Map<String,Object> DetailM1 = new HashMap<String,Object>();
                    Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
                    DetailM1Inventory.put("Code",items.getTinventoryCode());//明细1 的 存货编码啊！
                    DetailM1.put("Inventory",DetailM1Inventory);
                    Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
                    DetailM1Unit.put("Name",items.getTinventoryUnitName());//明细1 的 存货 计量单位名称
                    DetailM1.put("Unit",DetailM1Unit);
                    DetailM1.put("Quantity",items.getCount());//明细1 的 数量
                    //DetailM1.put("TaxRate","13");//明细1 的 税率
                    DetailM1.put("OrigTaxPrice",items.getPrice());//明细1 的 含税单价
                    DetailM1.put("DiscountRate",items.getActualAmount()/(items.getCount() * items.getPrice()));//明细1 的 折扣率
                    /*DetailM1.put("idsourcevouchertype","43");//明细1 的 来源单据类型ID
                    DetailM1.put("sourceVoucherCode","SO-2022-03-0006");//明细1 的 来源单据单据编号
                    DetailM1.put("sourceVoucherDetailId","9");//明细1 的 来源单据单据对应的明细行ID*/
                    SaleDeliveryDetailsList.add(DetailM1);
                }
            }
            if(free.contains("免单")){
                Map<String,Object> DetailM2 = new HashMap<String,Object>();
                //????
                SaleDeliveryDetailsList.add(DetailM2);
            }
            sa.put("SaleOrderDetails",SaleDeliveryDetailsList);
            dto.put("dto",sa);
            String js = JSONObject.toJSONString(dto);
            jsonList.add(js);
        }
        return jsonList;
    }


    // 通过 销售订单号  创建 生产加工单 的 请求参数 body 的 模板
    public static String getSCOrderparamsJson(List<Map<String,Object>> Tsalist){
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> sa = new HashMap<String,Object>();

        Map<String,Object> Department = new HashMap<String,Object>();
        Department.put("Code",Tsalist.get(0).get("departmentcode").toString());//部门编码
        sa.put("Department",Department);

        Map<String,Object> Person = new HashMap<String,Object>();
        Person.put("Code",Tsalist.get(0).get("personcode").toString());//业务员编码
        sa.put("Person",Person);

        sa.put("VoucherDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//单据日期
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）

        Map<String,Object> Customer = new HashMap<String,Object>();
        Customer.put("Code",Tsalist.get(0).get("partnercode").toString());//客户编码
        sa.put("Customer",Customer);

        Map<String,Object> BusinessType = new HashMap<String,Object>();
        BusinessType.put("Code","15");// 这TM 是加工类型哈！
        sa.put("BusinessType",BusinessType);
        sa.put("Memo","这是 自动的 备注内容，请注意查看！");//备注

        List<Map<String,Object>> ManufactureOrderDetails = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> tmap : Tsalist){
            Map<String,Object> DetailM1 = new HashMap<String,Object>();

            /*Map<String,Object> DetailM1Warehouse = new HashMap<String,Object>();
            DetailM1Warehouse.put("code","0101010101");//预入仓库
            DetailM1.put("Warehouse",DetailM1Warehouse);*/

            Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
            DetailM1Inventory.put("Code",tmap.get("inventoryCode").toString());//明细1 的 产成品编码
            DetailM1.put("Inventory",DetailM1Inventory);

            Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
            DetailM1Unit.put("Name",tmap.get("unitname").toString());//明细1 的 存货计量单位
            DetailM1.put("Unit",DetailM1Unit);

            DetailM1.put("Quantity",tmap.get("quantity").toString());//明细1 的 数量
            DetailM1.put("idsourcevouchertype","43");//明细1 的 来源单据类型ID
            DetailM1.put("SourceVoucherDetailId",tmap.get("sourceDetailId").toString());//明细1 的 来源单据单据对应的明细行ID
            ManufactureOrderDetails.add(DetailM1);
        }
        sa.put("ManufactureOrderDetails",ManufactureOrderDetails);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);

        return js;
    }

    //创建 工序汇报单 的 请求参数 body 的 模板(关联 某个 生产加工单/明细)
    public static String getGXHBOrderparamsJson(List<Map<String,Object>> Tsclist){
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> sa = new HashMap<String,Object>();

        sa.put("IsAutoGenerateRecieveVoucher",true);//末工序自动入库

        /*Map<String,Object> Department = new HashMap<String,Object>();
        Department.put("Code","HY");//部门编码
        sa.put("Department",Department);
        Map<String,Object> Person = new HashMap<String,Object>();
        Person.put("Code","CD-030");//业务员编码
        sa.put("Person",Person);*/

        sa.put("VoucherDate", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//单据日期
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）
        sa.put("IdSourceVoucherType","69");

        Map<String,Object> BusiType = new HashMap<String,Object>();
        BusiType.put("Code","PO01");// 业务类型编码 PO01:自制汇报
        sa.put("BusiType",BusiType);

        sa.put("Memo","这是 我的 备注内容，请注意查看9！");//备注

        List<Map<String,Object>> ManufactureReportDetails = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> tscmap : Tsclist){
            Map<String,Object> DetailM1 = new HashMap<String,Object>();
            DetailM1.put("idsourceVoucherType","69");//来源单据类型ID
            DetailM1.put("SourceVoucherId",tscmap.get("iddetail").toString());//来源单据ID
            DetailM1.put("SourceVoucherDetailId",tscmap.get("iddetail").toString());//来源单据明细ID

            /*Map<String,Object> DetailM1Warehouse = new HashMap<String,Object>();
            DetailM1Warehouse.put("code","0101010101");//预入仓库
            DetailM1.put("Warehouse",DetailM1Warehouse);*/

            Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
            DetailM1Inventory.put("code",tscmap.get("inventoryCode").toString());//明细1 的 产成品编码
            DetailM1.put("Inventory",DetailM1Inventory);

            Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
            DetailM1Unit.put("Name",tscmap.get("unitname").toString());//明细1 的 存货计量单位
            DetailM1.put("Unit",DetailM1Unit);

            /*Map<String,Object> Routing = new HashMap<String,Object>();
            Routing.put("Code","");//工艺编码
            DetailM1.put("Routing",Routing);

            Map<String,Object> Process = new HashMap<String,Object>();
            Process.put("Code","");//工序编码
            DetailM1.put("Process",Process);

            Map<String,Object> WorkShop = new HashMap<String,Object>();
            WorkShop.put("Code","");//车间编码
            DetailM1.put("WorkShop",WorkShop);*/


            ManufactureReportDetails.add(DetailM1);
        }
        sa.put("ManufactureReportDetails",ManufactureReportDetails);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);
        return js;
    }

    //创建 材料出库单 的 请求参数 body 的 模板(关联 某个 生产加工单/明细)
    public static String getCLCKOrderparamsJson(List<Map<String,Object>> Tsclist){
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> sa = new HashMap<String,Object>();
        Map<String,Object> Department = new HashMap<String,Object>();
        Department.put("Code","HY");//部门编码
        sa.put("Department",Department);
        Map<String,Object> Clerk = new HashMap<String,Object>();
        Clerk.put("Code","CD-030");//业务员编码
        sa.put("Clerk",Clerk);

        sa.put("VoucherDate","2022-03-15");//单据日期
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）
        sa.put("IdSourceVoucherType","69");

        Map<String,Object> VoucherType = new HashMap<String,Object>();
        VoucherType.put("Code","ST1022");
        sa.put("VoucherType",VoucherType);

        Map<String,Object> BusiType = new HashMap<String,Object>();
        BusiType.put("Code","67");// 业务类型
        sa.put("BusiType",BusiType);

        Map<String,Object> RdStyle = new HashMap<String,Object>();
        RdStyle.put("Code","102");
        sa.put("RdStyle",RdStyle);

        sa.put("Memo","这是 我的 备注内容，请注意查看9！");//备注

        List<Map<String,Object>> RDRecordDetails = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> tscmap : Tsclist){
            Map<String,Object> DetailM1 = new HashMap<String,Object>();
            DetailM1.put("idsourceVoucherType","69");//来源单据类型ID
            DetailM1.put("SourceVoucherDetailId",tscmap.get("iddetail").toString());//来源单据明细ID

            /*Map<String,Object> DetailM1Warehouse = new HashMap<String,Object>();
            DetailM1Warehouse.put("code","0101010101");//预入仓库
            DetailM1.put("Warehouse",DetailM1Warehouse);*/

            Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
            DetailM1Inventory.put("code",tscmap.get("inventoryCode").toString());//明细1 的 产成品编码
            DetailM1.put("Inventory",DetailM1Inventory);

            Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
            DetailM1Unit.put("Name",tscmap.get("unitname").toString());//明细1 的 存货计量单位
            DetailM1.put("Unit",DetailM1Unit);

            DetailM1.put("BaseQuantity",tscmap.get("quanity").toString());//明细1 的 数量
            DetailM1.put("Amount",tscmap.get("Amount").toString());//明细1 的 金额，可以从订单那里获取
            RDRecordDetails.add(DetailM1);
        }
        sa.put("RDRecordDetails",RDRecordDetails);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);
        return js;
    }


    //创建 产成品入库单 的 请求参数 body 的 模板
    public static String getCCPRKOrderparamsJson(Map<String,Object> mm){
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> sa = new HashMap<String,Object>();
        Map<String,Object> Department = new HashMap<String,Object>();
        Department.put("Code","HY");//部门编码
        sa.put("Department",Department);
        Map<String,Object> Clerk = new HashMap<String,Object>();
        Clerk.put("Code","CD-030");//业务员编码
        sa.put("Clerk",Clerk);
        sa.put("VoucherDate","2022-03-15");//单据日期
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）


        Map<String,Object> RdStyle = new HashMap<String,Object>();
        RdStyle.put("Code","？？？");//出库类别编码
        sa.put("RdStyle",RdStyle);

        Map<String,Object> BusiType = new HashMap<String,Object>();
        BusiType.put("Code","15");// 业务类型
        sa.put("BusiType",BusiType);

        Map<String,Object> DetailM1Warehouse = new HashMap<String,Object>();
        DetailM1Warehouse.put("code","0101010101");//预入仓库
        sa.put("Warehouse",DetailM1Warehouse);

        sa.put("Memo","这是 我的 备注内容，请注意查看9！");//备注

        List<Map<String,Object>> RDRecordDetails = new ArrayList<Map<String,Object>>();
        Map<String,Object> DetailM1 = new HashMap<String,Object>();
        DetailM1.put("Code","1");//行号 , 可以不传

        Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
        DetailM1Inventory.put("code","HW01-NB-13 S-EMD-W76-YSL");//明细1 的 产成品编码
        DetailM1.put("Inventory",DetailM1Inventory);

        DetailM1.put("BaseQuantity","123");//数量

        RDRecordDetails.add(DetailM1);
        sa.put("RDRecordDetails",RDRecordDetails);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);

        return js;
    }


    //创建销货单的 请求参数 body 的 模板(根据销售订单明细（表头+明细一起的）)
    public static String getSASOrderparamsJson(List<Map<String,Object>> Tsalist){
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> sa = new HashMap<String,Object>();
        Map<String,Object> Department = new HashMap<String,Object>();
        Department.put("Code",Tsalist.get(0).get("departmentcode").toString());//部门编码
        sa.put("Department",Department);
        Map<String,Object> Clerk = new HashMap<String,Object>();
        Clerk.put("Code",Tsalist.get(0).get("personcode").toString());//业务员编码
        sa.put("Clerk",Clerk);
        sa.put("VoucherDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//单据日期
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）
        Map<String,Object> Customer = new HashMap<String,Object>();
        Customer.put("Code",Tsalist.get(0).get("partnercode").toString());//客户编码
        sa.put("Customer",Customer);
        /*Map<String,Object> SettleCustomer = new HashMap<String,Object>();
        SettleCustomer.put("Code","CD-030");//结算客户编码（一般等同于 客户编码）
        sa.put("SettleCustomer",SettleCustomer);*/
        Map<String,Object> BusinessType = new HashMap<String,Object>();
        BusinessType.put("Code","15");//业务类型编码，15–普通销售；16–销售退货
        sa.put("BusinessType",BusinessType);
        Map<String,Object> InvoiceType = new HashMap<String,Object>();
        InvoiceType.put("Code","01");//票据类型，枚举类型；00--普通发票，01--专用发票，02–收据；为空时，默认按收据处理
        sa.put("InvoiceType",InvoiceType);
        /*Map<String,Object> Warehouse = new HashMap<String,Object>();
        Warehouse.put("Code","0101010101");//表头上的 仓库编码
        sa.put("Warehouse",Warehouse);*/
        Map<String,Object> ReciveType = new HashMap<String,Object>();
        ReciveType.put("Code","05");//收款方式，枚举类型；00--限期收款，01--全额订金，02--全额现结，03--月结，04--分期收款，05--其它；
        sa.put("ReciveType",ReciveType);


        Map<String,Object> RdStyle = new HashMap<String,Object>();
        RdStyle.put("Code","201");//出库类别，RdStyleDTO对象，默认为“线上销售”类别； 具体值 我是查的数据库。
        sa.put("RdStyle",RdStyle);
        sa.put("Memo","这是 我的 备注内容，请注意查看9！");//备注

        List<Map<String,Object>> SaleDeliveryDetails = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> mm : Tsalist){
            Map<String,Object> DetailM1 = new HashMap<String,Object>();
            /*Map<String,Object> DetailM1Warehouse = new HashMap<String,Object>();
            DetailM1Warehouse.put("code",mm.get("warehouseCode").toString());//明细1 的 仓库编码
            DetailM1.put("Warehouse",DetailM1Warehouse);*/
            Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
            DetailM1Inventory.put("Code",mm.get("inventoryCode").toString());//明细1 的 存货编码
            DetailM1.put("Inventory",DetailM1Inventory);
            Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
            DetailM1Unit.put("Name",mm.get("unitname").toString());//明细1 的 存货计量单位
            DetailM1.put("Unit",DetailM1Unit);
            //DetailM1.put("Batch","？？？？？？？？？？？？？？？？？？？");//批号
            DetailM1.put("Quantity",mm.get("quantity").toString());//明细1 的 数量
            /*DetailM1.put("TaxRate","13");//明细1 的 税率*/
            DetailM1.put("OrigTaxPrice",mm.get("origTaxPrice").toString());//明细1 的 含税单价(实际上 在传入 来源单据之后，只会用销售订单 上的 单价 )
            DetailM1.put("idsourcevouchertype","43");//明细1 的 来源单据类型ID  这里是销售订单
            DetailM1.put("sourceVoucherCode",mm.get("code").toString());//明细1 的 来源单据单据编号
            DetailM1.put("sourceVoucherDetailId",mm.get("sourceDetailId").toString());//明细1 的 来源单据单据对应的明细行ID
            SaleDeliveryDetails.add(DetailM1);
        }
        sa.put("SaleDeliveryDetails",SaleDeliveryDetails);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);

        return js;
    }

    //根据整理好后的 充值list 返回 一个 标准的预收款 json
    public static String getTskJSON(List<Map<String,Object>> czList){
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> sa = new HashMap<String,Object>();

        sa.put("VoucherDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        Map<String,Object> BusinessType = new HashMap<String,Object>();
        BusinessType.put("Code","34");
        sa.put("BusinessType",BusinessType);

        Map<String,Object> Partner = new HashMap<String,Object>();
        Partner.put("Code",czList.get(0).get("partnerCode").toString());
        sa.put("Partner",Partner);

        sa.put("Memo",czList.get(0).get("notes").toString());
        sa.put("IsReceiveFlag",true);

        List<Map<String,Object>> ArapMultiSettleDetails = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> czmap : czList){
            Map<String,Object> tmap = new HashMap<String,Object>();
            tmap.put("SettleStyle",czmap.get("SettleStyle").toString());
            tmap.put("BankAccount",czmap.get("BankAccount").toString());
            tmap.put("OrigAmount",czmap.get("amount").toString());
            ArapMultiSettleDetails.add(tmap);
        }
        sa.put("ArapMultiSettleDetails",ArapMultiSettleDetails);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);
        return js;
    }


    //销售出库单 json
    public static String getSaCkJson(JsonRootBean saentity,List<Map<String,Object>> sacklist){
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> sa = new HashMap<String,Object>();
        /*Map<String,Object> Department = new HashMap<String,Object>();
        Department.put("Code",saentity.getData().getDepartment().getCode());//部门编码
        sa.put("Department",Department);
        Map<String,Object> Clerk = new HashMap<String,Object>();
        Clerk.put("Code",saentity.getData().getClerk().getCode());//业务员编码
        sa.put("Clerk",Clerk);*/
        //单据日期 要用 销货单 表尾的 审核日期！
        String saaudate = new SimpleDateFormat("yyyy-MM-dd").format(saentity.getData().getAuditedDate());
        sa.put("VoucherDate",saaudate);
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）
        Map<String,Object> Partner = new HashMap<String,Object>();
        Partner.put("Code",saentity.getData().getCustomer().getCode());//客户编码
        sa.put("Partner",Partner);
        Map<String,Object> SettleCustomer = new HashMap<String,Object>();
        SettleCustomer.put("Code",saentity.getData().getCustomer().getCode());//客户编码
        sa.put("SettleCustomer",SettleCustomer);
        Map<String,Object> BusinessType = new HashMap<String,Object>();
        BusinessType.put("Code",saentity.getData().getBusinessType().getCode());//业务类型编码，15–普通销售；16–销售退货
        sa.put("BusiType",BusinessType);
        /*Map<String,Object> InvoiceType = new HashMap<String,Object>();
        InvoiceType.put("Code",saentity.getData().getInvoiceType().getCode());//票据类型，枚举类型；00--普通发票，01--专用发票，02–收据；为空时，默认按收据处理
        sa.put("InvoiceType",InvoiceType);*/

        Map<String,Object> RdStyle = new HashMap<String,Object>();
        RdStyle.put("Code","201");//出库类别，销售出库 是 201，查了数据库的。
        sa.put("RdStyle",RdStyle);
        sa.put("Memo",saentity.getData().getMemo());//备注

        //出库明细的参数
        List<Map<String,Object>> RDRecordDetails = new ArrayList<Map<String,Object>>();
        for(int idx = 0 ; idx < saentity.getData().getSaleDeliveryDetails().size(); idx ++){
            com.example.nuonuo.saentity.SaleDeliveryDetails mm  = saentity.getData().getSaleDeliveryDetails().get(idx);
            String currentinventoryCode = mm.getInventory().getCode();//当前的 销售的 明细1 的 存货编码
            String saleQuantity = mm.getQuantity();//销售量
            String UnitExchangeRate = mm.getUnitExchangeRate(); // 销货单明细 商品的 单位 换算率（客户只有俩，一主一辅）
            if(UnitExchangeRate == null || "".equals(UnitExchangeRate) || "null".equals(UnitExchangeRate)){
                UnitExchangeRate = "1";
            }
            //判断库存！
            for(Map<String,Object> mapp : sacklist){
                String ckinventoryCode = mapp.get("ckinventoryCode").toString();
                // 0 代表销售的时候 用的 辅单位， 1 代表销售的时候用的是主单位
                String isMainUnit = mapp.get("saleunitid").toString().equals(mapp.get("idbaseunit").toString())?"1":"0";
                String AvailableQuantity = mapp.get("AvailableQuantity").toString();//库存里面的 可用量  主单位 ！！！
                String isBatch = mapp.get("isbatch").toString();// 0 没批号 没自由项，就是 原材料 ； 1 有批号 有自由项，非原材料

                //找到这个存货 以及 对应的 数量 是否 在可用量 范围内 并且 ！ 批号 是最小的 才行！！！
                if(currentinventoryCode.equals(ckinventoryCode) && Float.valueOf(AvailableQuantity) >= 1){

                    if("0".equals(isMainUnit)){
                        AvailableQuantity = ""+(Float.valueOf(AvailableQuantity)/Float.valueOf(UnitExchangeRate)) ;//通过 主单位 数量 除以 换算率 得到 辅单位数量
                    }
                    if(Float.valueOf(AvailableQuantity) >= 1){
                        //这样是因为当现存量是小数时，就只能获取 整数部分
                        if(AvailableQuantity.contains(".")){
                            AvailableQuantity = AvailableQuantity.substring(0,AvailableQuantity.indexOf("."));
                        }
                        String Batch = "";//批号
                        if(mapp.get("Batch") != null && !"".equals(mapp.get("Batch").toString()) && !"null".equals(mapp.get("Batch").toString())){
                            Batch = mapp.get("Batch").toString();
                        }
                        String WarehouseCode = mapp.get("WarehouseCode").toString();//仓库编码

                        String freeitem0Value = "";//自由项1 对应的 值
                        if(mapp.get("freeitem0Value") != null && !"".equals(mapp.get("freeitem0Value").toString()) && !"null".equals(mapp.get("freeitem0Value").toString())){
                            freeitem0Value = mapp.get("freeitem0Value").toString();
                        }
                        if(Float.valueOf(AvailableQuantity) >= Float.valueOf(saleQuantity)){//说明 这个 批号的 存货是 足够的，那就直接卖呗！
                            Map<String,Object> DetailM1 = new HashMap<String,Object>();
                            Map<String,Object> DetailM1Warehouse = new HashMap<String,Object>();
                            DetailM1Warehouse.put("Code",WarehouseCode);//明细1 的 仓库编码
                            DetailM1.put("Warehouse",DetailM1Warehouse);
                            if("1".equals(isBatch)){
                                DetailM1.put("Batch",Batch);//批号
                                List<String> DynamicPropertyKeyslist = new ArrayList<>();
                                DynamicPropertyKeyslist.add("freeitem0");//自由项1
                                DetailM1.put("DynamicPropertyKeys",DynamicPropertyKeyslist);
                                List<String> DynamicPropertyValueslist = new ArrayList<>();
                                DynamicPropertyValueslist.add(freeitem0Value);//自由项1 对应的 值
                                DetailM1.put("DynamicPropertyValues",DynamicPropertyValueslist);
                            }

                            Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
                            DetailM1Inventory.put("Code",currentinventoryCode);//明细1 的 存货编码
                            DetailM1.put("Inventory",DetailM1Inventory);

                            Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
                            DetailM1Unit.put("Name",mm.getUnit().getName());//明细1 的 存货计量单位
                            DetailM1.put("Unit",DetailM1Unit);

                            DetailM1.put("BaseQuantity",saleQuantity);//明细1 的 数量
                            /*DetailM1.put("TaxRate","13");//明细1 的 税率*/
                            /*DetailM1.put("OrigTaxPrice",mm.get("origTaxPrice").toString());//明细1 的 含税单价(实际上 在传入 来源单据之后，只会用销售订单 上的 单价 )*/

                            DetailM1.put("idsourcevouchertype","104");//来源 销货单
                            DetailM1.put("sourceVoucherCode",saentity.getData().getCode());//明细1 的 来源单据单据编号
                            DetailM1.put("sourceVoucherDetailId",mm.getID());//明细1 的 来源单据单据对应的明细行ID
                            RDRecordDetails.add(DetailM1);

                            //String AvailableQuantity = mapp.get("AvailableQuantity").toString();//库存里面的 可用量  主单位 ！！！
                            mapp.put("AvailableQuantity",""+(Float.valueOf(AvailableQuantity)-Float.valueOf(saleQuantity)));
                            break;
                        }else{//不够  AvailableQuantity 小于 saleQuantity

                            Map<String,Object> DetailM1 = new HashMap<String,Object>();
                            Map<String,Object> DetailM1Warehouse = new HashMap<String,Object>();
                            DetailM1Warehouse.put("Code",WarehouseCode);//明细1 的 仓库编码
                            DetailM1.put("Warehouse",DetailM1Warehouse);
                            if("1".equals(isBatch)){
                                DetailM1.put("Batch",Batch);//批号
                                List<String> DynamicPropertyKeyslist = new ArrayList<>();
                                DynamicPropertyKeyslist.add("freeitem0");//自由项1
                                DetailM1.put("DynamicPropertyKeys",DynamicPropertyKeyslist);
                                List<String> DynamicPropertyValueslist = new ArrayList<>();
                                DynamicPropertyValueslist.add(freeitem0Value);//自由项1 对应的 值
                                DetailM1.put("DynamicPropertyValues",DynamicPropertyValueslist);
                            }

                            Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
                            DetailM1Inventory.put("Code",currentinventoryCode);//明细1 的 存货编码
                            DetailM1.put("Inventory",DetailM1Inventory);

                            Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
                            DetailM1Unit.put("Name",mm.getUnit().getName());//明细1 的 存货计量单位
                            DetailM1.put("Unit",DetailM1Unit);

                            DetailM1.put("BaseQuantity",AvailableQuantity);//明细1 的 数量
                            /*DetailM1.put("TaxRate","13");//明细1 的 税率*/
                            /*DetailM1.put("OrigTaxPrice",mm.get("origTaxPrice").toString());//明细1 的 含税单价(实际上 在传入 来源单据之后，只会用销售订单 上的 单价 )*/

                            DetailM1.put("idsourcevouchertype","104");//来源 销货单
                            DetailM1.put("sourceVoucherCode",saentity.getData().getCode());//明细1 的 来源单据单据编号
                            DetailM1.put("sourceVoucherDetailId",mm.getID());//明细1 的 来源单据单据对应的明细行ID
                            RDRecordDetails.add(DetailM1);

                            saleQuantity = ""+(Float.valueOf(saleQuantity) - Float.valueOf(AvailableQuantity));//相差的数量
                            //当前 库存量 map的 可用量 已经用完了！！
                            mapp.put("AvailableQuantity","0.0");
                        }
                    }
                }
            }
        }
        sa.put("RDRecordDetails",RDRecordDetails);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);
        return js;
    }


    //销售发票json
    public static String getSaFPJson(JsonRootBean saentity){
        Map<String,Object> dto = new HashMap<String,Object>();
        Map<String,Object> sa = new HashMap<String,Object>();
        /*Map<String,Object> Department = new HashMap<String,Object>();
        Department.put("Code",saentity.getData().getDepartment().getCode());//部门编码
        sa.put("Department",Department);
        Map<String,Object> Clerk = new HashMap<String,Object>();
        Clerk.put("Code",saentity.getData().getClerk().getCode());//业务员编码
        sa.put("Clerk",Clerk);*/

        //sa.put("VoucherDate",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));//单据日期
        //单据日期 要用 销货单 表尾的 审核日期！
        String saaudate = new SimpleDateFormat("yyyy-MM-dd").format(saentity.getData().getAuditedDate());
        sa.put("VoucherDate",saaudate);
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）

        Map<String,Object> Customer = new HashMap<String,Object>();
        Customer.put("Code",saentity.getData().getCustomer().getCode());//客户编码
        sa.put("Customer",Customer);

        Map<String,Object> BusinessType = new HashMap<String,Object>();
        BusinessType.put("Code",saentity.getData().getBusinessType().getCode());//业务类型编码，15–普通销售；16–销售退货
        sa.put("BusinessType",BusinessType);
        Map<String,Object> InvoiceType = new HashMap<String,Object>();
        InvoiceType.put("Code",saentity.getData().getInvoiceType().getCode());//票据类型，枚举类型；00--普通发票，01--专用发票，02–收据；为空时，默认按收据处理
        sa.put("InvoiceType",InvoiceType);

        Map<String,Object> ReciveType = new HashMap<String,Object>();
        ReciveType.put("Code",saentity.getData().getReciveType().getCode());//收款方式，枚举类型；00--限期收款，01--全额订金，02--全额现结，03--月结，04--分期收款，05--其它；
        sa.put("ReciveType",ReciveType);

        //sa.put("Memo","自动生成发票");//备注

        List<Map<String,Object>> SaleDeliveryDetails = new ArrayList<Map<String,Object>>();
        for(com.example.nuonuo.saentity.SaleDeliveryDetails mm : saentity.getData().getSaleDeliveryDetails()){
            Map<String,Object> DetailM1 = new HashMap<String,Object>();

            Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
            DetailM1Inventory.put("Code",mm.getInventory().getCode());//明细1 的 存货编码
            DetailM1.put("Inventory",DetailM1Inventory);

            Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
            DetailM1Unit.put("Name",mm.getUnit().getName());//明细1 的 存货计量单位
            DetailM1.put("Unit",DetailM1Unit);

            DetailM1.put("Quantity",mm.getQuantity());//明细1 的 数量
            DetailM1.put("OrigTaxPrice",mm.getOrigTaxPrice());//明细1 的 含税单价(实际上 在传入 来源单据之后，只会用销售订单 上的 单价 )
            DetailM1.put("idsourcevouchertype","104");//明细1 的 来源单据类型ID  这里是销货单
            DetailM1.put("sourceVoucherDetailId",mm.getID());//明细1 的 来源单据单据对应的明细行ID
            SaleDeliveryDetails.add(DetailM1);
        }
        sa.put("SaleInvoiceDetails",SaleDeliveryDetails);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);
        return js;
    }
}

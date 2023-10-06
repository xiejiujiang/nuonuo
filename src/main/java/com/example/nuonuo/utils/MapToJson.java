package com.example.nuonuo.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.nuonuo.entity.Ekanya.Esale.ESaleData;
import com.example.nuonuo.entity.Ekanya.Esale.ESaleRoot;
import com.example.nuonuo.entity.Ekanya.Esale.PaymentItems;
import com.example.nuonuo.entity.Ekanya.Euser.Data;
import com.example.nuonuo.entity.Ekanya.Euser.Euser;
import com.example.nuonuo.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

@lombok.Data
public class MapToJson {

    public static void main(String[] args) throws Exception{
        Calendar now = Calendar.getInstance();
        Calendar birthday = Calendar.getInstance();

        String s = "1989-09-16T00:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dd = sdf.parse(s);
        birthday.setTime(dd);

        int age = now.get(Calendar.YEAR) - birthday.get(Calendar.YEAR);
        System.out.println("age == " + age);
    }

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
        String json = JSONObject.toJSONString(dto);
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
            DynamicPropertyValueslist.add(data.getSource().getSourceLevel2());
            DynamicPropertyValueslist.add(data.getSource().getSourceLevel3());
            mm.put("DynamicPropertyValues",DynamicPropertyValueslist);

            mapstr.put("dto",mm);
            String str =  JSONObject.toJSONString(mapstr);
            list.add(str);
        }
        return list;
    }

    //创建销售订单的 请求参数 body 的 模板
    public static List<String> getSAOrderparamsJson(Map<String,Object> mm, Map<String,Object> edatamap,Map<String,Object> docMap){
        List<String> jsonList = new ArrayList<String>();
        //最后 迭代这个 edatamap，每一个客户（患者），生成一个 销售订单的JSON。所以返回的也应该是一个JSON List
        while (edatamap.keySet().iterator().hasNext()){
            String patientId = edatamap.keySet().iterator().next(); // patientId
            List<ESaleData> esalelist = (List<ESaleData>)edatamap.get(patientId);
            //在这个方法内，一个客户 patientId 就对应了 所有的 ESaleData（包含了 存货，定金金额，收款方式 等） 组合的LIST

            String doctorid = ""+esalelist.get(0).getDoctorId();//医生ID
            //调用 员工查询 接口 可以获取 医生名称(已经写了serviceImpl)

            Map<String,Object> dto = new HashMap<String,Object>();
            Map<String,Object> sa = new HashMap<String,Object>();

            Map<String,Object> Department = new HashMap<String,Object>();
            Department.put("Code",((Map)docMap.get(patientId+"-cle")).get("departMentCode").toString());//部门编码
            sa.put("Department",Department);
            Map<String,Object> Clerk = new HashMap<String,Object>();
            //basicService.getEUserInfoById(patientId).getData().get(0).getConsultant();
            Clerk.put("Code",((Map)docMap.get(patientId+"-cle")).get("clerkCode").toString());//业务员编码 （咨询师）  通过 患者档案可以查到 对应的 consultant  咨询师(以及咨询师 对应的 部门)
            sa.put("Clerk",Clerk);

            String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            sa.put("VoucherDate",today);//单据日期
            sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）

            Map<String,Object> Customer = new HashMap<String,Object>();
            Customer.put("Code",patientId);//客户编码
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

            //定金
            Float ff = 0f;
            Map<String,Object> Subscriptions = new HashMap<String,Object>();
            for(ESaleData eSaleData : esalelist){
                for(PaymentItems paymentItems : eSaleData.getPaymentItems()){
                    Subscriptions.put("origAmount",paymentItems.getAmount());//金额
                    Map<String,Object> SettleStyle = new HashMap<String,Object>();
                    SettleStyle.put("Code","结算方式编码");// paymentItems.get?
                    Subscriptions.put("SettleStyle",SettleStyle);
                    Map<String,Object> BankAccount = new HashMap<String,Object>();
                    BankAccount.put("Code","银行账号编码");// paymentItems.get?
                    Subscriptions.put("BankAccount",BankAccount);
                    ff = ff + Float.valueOf(paymentItems.getAmount());
                }
            }
            sa.put("Subscriptions",Subscriptions);//定金明细
            //定金金额
            sa.put("OrigEarnestMoney",""+ff);

            /*Map<String,Object> RdStyle = new HashMap<String,Object>();
            RdStyle.put("Code","201");//出库类别，RdStyleDTO对象，默认为“线上销售”类别； 具体值 我是查的数据库。
            sa.put("RdStyle",RdStyle);*/

            sa.put("Memo","此单通过API 自动同步生成！");//备注

            //订单明细
            List<Map<String,Object>> SaleDeliveryDetailsList = new ArrayList<Map<String,Object>>();
            for(ESaleData eSaleData : esalelist){
                Map<String,Object> DetailM1 = new HashMap<String,Object>();
                Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
                DetailM1Inventory.put("name",eSaleData.getItemName());//明细1 的 存货名称/但是这里好像是只能编码啊！
                DetailM1.put("Inventory",DetailM1Inventory);
                /*Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
                DetailM1Unit.put("Name","台");//明细1 的 存货计量单位
                DetailM1.put("Unit",DetailM1Unit);*/
                DetailM1.put("Quantity","1");//明细1 的 数量
                //DetailM1.put("TaxRate","13");//明细1 的 税率
                DetailM1.put("OrigTaxPrice",eSaleData.getStepAmount());//明细1 的 含税单价
                /*DetailM1.put("idsourcevouchertype","43");//明细1 的 来源单据类型ID
                DetailM1.put("sourceVoucherCode","SO-2022-03-0006");//明细1 的 来源单据单据编号
                DetailM1.put("sourceVoucherDetailId","9");//明细1 的 来源单据单据对应的明细行ID*/
                SaleDeliveryDetailsList.add(DetailM1);
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
        Department.put("Code","HY");//部门编码
        sa.put("Department",Department);
        Map<String,Object> Person = new HashMap<String,Object>();
        Person.put("Code","CD-030");//业务员编码
        sa.put("Person",Person);
        sa.put("VoucherDate","2022-03-15");//单据日期
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）

        Map<String,Object> Customer = new HashMap<String,Object>();
        Customer.put("Code","CD-030");//客户编码
        sa.put("Customer",Customer);

        Map<String,Object> BusinessType = new HashMap<String,Object>();
        BusinessType.put("Code","15");// 这TM 是加工类型哈！
        sa.put("BusinessType",BusinessType);
        sa.put("Memo","这是 我的 备注内容，请注意查看9！");//备注

        List<Map<String,Object>> ManufactureOrderDetails = new ArrayList<Map<String,Object>>();
        for(Map<String,Object> tmap : Tsalist){
            Map<String,Object> DetailM1 = new HashMap<String,Object>();
            Map<String,Object> DetailM1Warehouse = new HashMap<String,Object>();
            DetailM1Warehouse.put("code","0101010101");//预入仓库
            DetailM1.put("Warehouse",DetailM1Warehouse);

            Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
            DetailM1Inventory.put("code",tmap.get("inventoryCode").toString());//明细1 的 产成品编码
            DetailM1.put("Inventory",DetailM1Inventory);

            Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
            DetailM1Unit.put("Name",tmap.get("unitname").toString());//明细1 的 存货计量单位
            DetailM1.put("Unit",DetailM1Unit);

            DetailM1.put("Quantity",tmap.get("quanity").toString());//明细1 的 数量
            DetailM1.put("idsourcevouchertype","43");//明细1 的 来源单据类型ID
            DetailM1.put("SourceVoucherDetailId",tmap.get("iddetail").toString());//明细1 的 来源单据单据对应的明细行ID
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

        Map<String,Object> Department = new HashMap<String,Object>();
        Department.put("Code","HY");//部门编码
        sa.put("Department",Department);
        Map<String,Object> Person = new HashMap<String,Object>();
        Person.put("Code","CD-030");//业务员编码
        sa.put("Person",Person);

        sa.put("VoucherDate","2022-03-15");//单据日期
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）
        sa.put("IdSourceVoucherType","69");

        Map<String,Object> BusiType = new HashMap<String,Object>();
        BusiType.put("Code","PO01");// 业务类型编码 PO01:自制汇报
        sa.put("BusiType",BusiType);

        sa.put("Memo","这是 我的 备注内容，请注意查看9！");//备注

        List<Map<String,Object>> RDRecordDetails = new ArrayList<Map<String,Object>>();
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

            Map<String,Object> Routing = new HashMap<String,Object>();
            Routing.put("Code","");//工艺编码
            DetailM1.put("Routing",Routing);

            Map<String,Object> Process = new HashMap<String,Object>();
            Process.put("Code","");//工序编码
            DetailM1.put("Process",Process);

            Map<String,Object> WorkShop = new HashMap<String,Object>();
            WorkShop.put("Code","");//车间编码
            DetailM1.put("WorkShop",WorkShop);


            RDRecordDetails.add(DetailM1);
        }
        sa.put("RDRecordDetails",RDRecordDetails);
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
        Department.put("Code",Tsalist.get(0).get("departName").toString());//部门编码
        sa.put("Department",Department);
        Map<String,Object> Clerk = new HashMap<String,Object>();
        Clerk.put("Code",Tsalist.get(0).get("clerkCode").toString());//业务员编码
        sa.put("Clerk",Clerk);
        sa.put("VoucherDate",Tsalist.get(0).get("vourcherDate").toString());//单据日期
        sa.put("ExternalCode",Md5.md5("XJJ"+System.currentTimeMillis()));//外部订单号，不可以重复（MD5，建议记录）
        Map<String,Object> Customer = new HashMap<String,Object>();
        Customer.put("Code",Tsalist.get(0).get("parterCode").toString());//客户编码
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
            Map<String,Object> DetailM1Warehouse = new HashMap<String,Object>();
            DetailM1Warehouse.put("code",mm.get("warehouseCode").toString());//明细1 的 仓库编码
            DetailM1.put("Warehouse",DetailM1Warehouse);
            Map<String,Object> DetailM1Inventory = new HashMap<String,Object>();
            DetailM1Inventory.put("code",mm.get("inventoryCode").toString());//明细1 的 存货编码
            DetailM1.put("Inventory",DetailM1Inventory);
            Map<String,Object> DetailM1Unit = new HashMap<String,Object>();
            DetailM1Unit.put("Name",mm.get("unitName").toString());//明细1 的 存货计量单位
            DetailM1.put("Unit",DetailM1Unit);
            //DetailM1.put("Batch","？？？？？？？？？？？？？？？？？？？");//批号
            DetailM1.put("Quantity",mm.get("quanity").toString());//明细1 的 数量
            /*DetailM1.put("TaxRate","13");//明细1 的 税率*/
            DetailM1.put("OrigTaxPrice","6666.00");//明细1 的 含税单价(实际上 在传入 来源单据之后，只会用销售订单 上的 单价？？？)
            DetailM1.put("idsourcevouchertype","43");//明细1 的 来源单据类型ID  这里是销售订单
            DetailM1.put("sourceVoucherCode",mm.get("sourceCode").toString());//明细1 的 来源单据单据编号
            DetailM1.put("sourceVoucherDetailId",mm.get("sourceDetailId").toString());//明细1 的 来源单据单据对应的明细行ID
            SaleDeliveryDetails.add(DetailM1);
        }
        sa.put("SaleDeliveryDetails",SaleDeliveryDetails);
        dto.put("dto",sa);
        String js = JSONObject.toJSONString(dto);

        return js;
    }
}

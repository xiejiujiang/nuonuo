package com.example.nuonuo.entity.Ekanya.EzhifuDetailByID;

import com.example.nuonuo.entity.Ekanya.Euser.Consultant;
import com.example.nuonuo.entity.Ekanya.Euser.Doctor;
import com.example.nuonuo.entity.Ekanya.Euser.Nurse;

public class EzhifuDetailData {

    private int paymentMethodId;

    private int paymentId;

    private int paymentChargeItemId;

    private int billChargeItemId;

    private int chargeItemId;

    private String itemSubCategory;

    private int paymentStepId;

    private int stepId;

    private String feeType;

    private String paymentMethodName;

    private int amount;

    private int extraAmount;

    private String payScene;

    private String chargeOrderCreatedTime;

    private String chargeOrderUpdatedTime;

    private Doctor doctor;

    private Nurse nurse;

    private Consultant consultant;

    private String onlineConsultant;

    private int scenario;

    private int productId;

    private String productCode;

    private int officeId;

    private int id;

    private String tenantId;

    private boolean isInactive;

    private String timestamp;

    public void setPaymentMethodId(int paymentMethodId){
        this.paymentMethodId = paymentMethodId;
    }
    public int getPaymentMethodId(){
        return this.paymentMethodId;
    }
    public void setPaymentId(int paymentId){
        this.paymentId = paymentId;
    }
    public int getPaymentId(){
        return this.paymentId;
    }
    public void setPaymentChargeItemId(int paymentChargeItemId){
        this.paymentChargeItemId = paymentChargeItemId;
    }
    public int getPaymentChargeItemId(){
        return this.paymentChargeItemId;
    }
    public void setBillChargeItemId(int billChargeItemId){
        this.billChargeItemId = billChargeItemId;
    }
    public int getBillChargeItemId(){
        return this.billChargeItemId;
    }
    public void setChargeItemId(int chargeItemId){
        this.chargeItemId = chargeItemId;
    }
    public int getChargeItemId(){
        return this.chargeItemId;
    }
    public void setItemSubCategory(String itemSubCategory){
        this.itemSubCategory = itemSubCategory;
    }
    public String getItemSubCategory(){
        return this.itemSubCategory;
    }
    public void setPaymentStepId(int paymentStepId){
        this.paymentStepId = paymentStepId;
    }
    public int getPaymentStepId(){
        return this.paymentStepId;
    }
    public void setStepId(int stepId){
        this.stepId = stepId;
    }
    public int getStepId(){
        return this.stepId;
    }
    public void setFeeType(String feeType){
        this.feeType = feeType;
    }
    public String getFeeType(){
        return this.feeType;
    }
    public void setPaymentMethodName(String paymentMethodName){
        this.paymentMethodName = paymentMethodName;
    }
    public String getPaymentMethodName(){
        return this.paymentMethodName;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount(){
        return this.amount;
    }
    public void setExtraAmount(int extraAmount){
        this.extraAmount = extraAmount;
    }
    public int getExtraAmount(){
        return this.extraAmount;
    }
    public void setPayScene(String payScene){
        this.payScene = payScene;
    }
    public String getPayScene(){
        return this.payScene;
    }
    public void setChargeOrderCreatedTime(String chargeOrderCreatedTime){
        this.chargeOrderCreatedTime = chargeOrderCreatedTime;
    }
    public String getChargeOrderCreatedTime(){
        return this.chargeOrderCreatedTime;
    }
    public void setChargeOrderUpdatedTime(String chargeOrderUpdatedTime){
        this.chargeOrderUpdatedTime = chargeOrderUpdatedTime;
    }
    public String getChargeOrderUpdatedTime(){
        return this.chargeOrderUpdatedTime;
    }
    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }
    public Doctor getDoctor(){
        return this.doctor;
    }
    public void setNurse(Nurse nurse){
        this.nurse = nurse;
    }
    public Nurse getNurse(){
        return this.nurse;
    }
    public void setConsultant(Consultant consultant){
        this.consultant = consultant;
    }
    public Consultant getConsultant(){
        return this.consultant;
    }
    public void setOnlineConsultant(String onlineConsultant){
        this.onlineConsultant = onlineConsultant;
    }
    public String getOnlineConsultant(){
        return this.onlineConsultant;
    }
    public void setScenario(int scenario){
        this.scenario = scenario;
    }
    public int getScenario(){
        return this.scenario;
    }
    public void setProductId(int productId){
        this.productId = productId;
    }
    public int getProductId(){
        return this.productId;
    }
    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
    public String getProductCode(){
        return this.productCode;
    }
    public void setOfficeId(int officeId){
        this.officeId = officeId;
    }
    public int getOfficeId(){
        return this.officeId;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setTenantId(String tenantId){
        this.tenantId = tenantId;
    }
    public String getTenantId(){
        return this.tenantId;
    }
    public void setIsInactive(boolean isInactive){
        this.isInactive = isInactive;
    }
    public boolean getIsInactive(){
        return this.isInactive;
    }
    public void setTimestamp(String timestamp){
        this.timestamp = timestamp;
    }
    public String getTimestamp(){
        return this.timestamp;
    }
}

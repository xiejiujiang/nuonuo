package com.example.nuonuo.entity.Ekanya.Esale;

import java.util.List;

public class ESaleData {
    private int deductionLogId;

    private int billId;

    private int payOfficeId;

    private int patientId;

    private int paymentChargeItemId;

    private int billChargeItemId;

    private int chargeItemId;

    private String chargeItemCode;

    private int billStepId;

    private int stepId;

    private String stepName;

    private int stepAmount;

    private int doctorId;

    private int nurseId;

    private int extraProviderId;

    private int manHour;

    private String action;

    private String itemName;

    private String itemSubCategory;

    private String itemSubCategoryCode;

    private int targetBillId;

    private List<PaymentItems> paymentItems;

    private String itemCategory;

    private String itemSuperCategory;

    private String recordCreatedTime;

    private int officeId;

    private int id;

    private String tenantId;

    private boolean isInactive;

    private String timestamp;

    public void setDeductionLogId(int deductionLogId){
        this.deductionLogId = deductionLogId;
    }
    public int getDeductionLogId(){
        return this.deductionLogId;
    }
    public void setBillId(int billId){
        this.billId = billId;
    }
    public int getBillId(){
        return this.billId;
    }
    public void setPayOfficeId(int payOfficeId){
        this.payOfficeId = payOfficeId;
    }
    public int getPayOfficeId(){
        return this.payOfficeId;
    }
    public void setPatientId(int patientId){
        this.patientId = patientId;
    }
    public int getPatientId(){
        return this.patientId;
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
    public void setChargeItemCode(String chargeItemCode){
        this.chargeItemCode = chargeItemCode;
    }
    public String getChargeItemCode(){
        return this.chargeItemCode;
    }
    public void setBillStepId(int billStepId){
        this.billStepId = billStepId;
    }
    public int getBillStepId(){
        return this.billStepId;
    }
    public void setStepId(int stepId){
        this.stepId = stepId;
    }
    public int getStepId(){
        return this.stepId;
    }
    public void setStepName(String stepName){
        this.stepName = stepName;
    }
    public String getStepName(){
        return this.stepName;
    }
    public void setStepAmount(int stepAmount){
        this.stepAmount = stepAmount;
    }
    public int getStepAmount(){
        return this.stepAmount;
    }
    public void setDoctorId(int doctorId){
        this.doctorId = doctorId;
    }
    public int getDoctorId(){
        return this.doctorId;
    }
    public void setNurseId(int nurseId){
        this.nurseId = nurseId;
    }
    public int getNurseId(){
        return this.nurseId;
    }
    public void setExtraProviderId(int extraProviderId){
        this.extraProviderId = extraProviderId;
    }
    public int getExtraProviderId(){
        return this.extraProviderId;
    }
    public void setManHour(int manHour){
        this.manHour = manHour;
    }
    public int getManHour(){
        return this.manHour;
    }
    public void setAction(String action){
        this.action = action;
    }
    public String getAction(){
        return this.action;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public String getItemName(){
        return this.itemName;
    }
    public void setItemSubCategory(String itemSubCategory){
        this.itemSubCategory = itemSubCategory;
    }
    public String getItemSubCategory(){
        return this.itemSubCategory;
    }
    public void setItemSubCategoryCode(String itemSubCategoryCode){
        this.itemSubCategoryCode = itemSubCategoryCode;
    }
    public String getItemSubCategoryCode(){
        return this.itemSubCategoryCode;
    }
    public void setTargetBillId(int targetBillId){
        this.targetBillId = targetBillId;
    }
    public int getTargetBillId(){
        return this.targetBillId;
    }
    public void setPaymentItems(List<PaymentItems> paymentItems){
        this.paymentItems = paymentItems;
    }
    public List<PaymentItems> getPaymentItems(){
        return this.paymentItems;
    }
    public void setItemCategory(String itemCategory){
        this.itemCategory = itemCategory;
    }
    public String getItemCategory(){
        return this.itemCategory;
    }
    public void setItemSuperCategory(String itemSuperCategory){
        this.itemSuperCategory = itemSuperCategory;
    }
    public String getItemSuperCategory(){
        return this.itemSuperCategory;
    }
    public void setRecordCreatedTime(String recordCreatedTime){
        this.recordCreatedTime = recordCreatedTime;
    }
    public String getRecordCreatedTime(){
        return this.recordCreatedTime;
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

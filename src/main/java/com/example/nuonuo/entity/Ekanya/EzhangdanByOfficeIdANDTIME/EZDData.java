package com.example.nuonuo.entity.Ekanya.EzhangdanByOfficeIdANDTIME;

import java.util.List;

public class EZDData {
    private int billId;

    private int membershipCardId;

    private List<String> discountPlan;

    private List<EZDItems> items;

    private List<String> vouchers;

    private int billType;

    private String billTypeName;

    private int patientId;

    private int appointmentId;

    private String date;

    private int totalAmount;

    private int afterDiscountAmount;

    private int discountAmount;

    private String billNo;

    private int officeId;

    private int id;

    private String tenantId;

    private boolean isInactive;

    private String timestamp;

    public void setBillId(int billId){
        this.billId = billId;
    }
    public int getBillId(){
        return this.billId;
    }
    public void setMembershipCardId(int membershipCardId){
        this.membershipCardId = membershipCardId;
    }
    public int getMembershipCardId(){
        return this.membershipCardId;
    }
    public void setDiscountPlan(List<String> discountPlan){
        this.discountPlan = discountPlan;
    }
    public List<String> getDiscountPlan(){
        return this.discountPlan;
    }
    public void setItems(List<EZDItems> items){
        this.items = items;
    }
    public List<EZDItems> getItems(){
        return this.items;
    }
    public void setVouchers(List<String> vouchers){
        this.vouchers = vouchers;
    }
    public List<String> getVouchers(){
        return this.vouchers;
    }
    public void setBillType(int billType){
        this.billType = billType;
    }
    public int getBillType(){
        return this.billType;
    }
    public void setBillTypeName(String billTypeName){
        this.billTypeName = billTypeName;
    }
    public String getBillTypeName(){
        return this.billTypeName;
    }
    public void setPatientId(int patientId){
        this.patientId = patientId;
    }
    public int getPatientId(){
        return this.patientId;
    }
    public void setAppointmentId(int appointmentId){
        this.appointmentId = appointmentId;
    }
    public int getAppointmentId(){
        return this.appointmentId;
    }
    public void setDate(String date){
        this.date = date;
    }
    public String getDate(){
        return this.date;
    }
    public void setTotalAmount(int totalAmount){
        this.totalAmount = totalAmount;
    }
    public int getTotalAmount(){
        return this.totalAmount;
    }
    public void setAfterDiscountAmount(int afterDiscountAmount){
        this.afterDiscountAmount = afterDiscountAmount;
    }
    public int getAfterDiscountAmount(){
        return this.afterDiscountAmount;
    }
    public void setDiscountAmount(int discountAmount){
        this.discountAmount = discountAmount;
    }
    public int getDiscountAmount(){
        return this.discountAmount;
    }
    public void setBillNo(String billNo){
        this.billNo = billNo;
    }
    public String getBillNo(){
        return this.billNo;
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

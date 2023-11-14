package com.example.nuonuo.entity.Ekanya.EpatienCZ;

public class EpatienCZData {

    private int type;

    private String typeName;

    private String payType;

    private int amount;

    private String notes;

    private int balance;

    private int principalBalance;

    private int giftBalance;

    private int depositBalance;

    private int principalAmount;

    private int giftAmount;

    private int depositAmount;

    private TargetPatient targetPatient;

    private Operator operator;

    private int chargeOrderId;

    private String reason;

    private int officeId;

    private int id;

    private String tenantId;

    private boolean isInactive;

    private String timestamp;

    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setTypeName(String typeName){
        this.typeName = typeName;
    }
    public String getTypeName(){
        return this.typeName;
    }
    public void setPayType(String payType){
        this.payType = payType;
    }
    public String getPayType(){
        return this.payType;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount(){
        return this.amount;
    }
    public void setNotes(String notes){
        this.notes = notes;
    }
    public String getNotes(){
        return this.notes;
    }
    public void setBalance(int balance){
        this.balance = balance;
    }
    public int getBalance(){
        return this.balance;
    }
    public void setPrincipalBalance(int principalBalance){
        this.principalBalance = principalBalance;
    }
    public int getPrincipalBalance(){
        return this.principalBalance;
    }
    public void setGiftBalance(int giftBalance){
        this.giftBalance = giftBalance;
    }
    public int getGiftBalance(){
        return this.giftBalance;
    }
    public void setDepositBalance(int depositBalance){
        this.depositBalance = depositBalance;
    }
    public int getDepositBalance(){
        return this.depositBalance;
    }
    public void setPrincipalAmount(int principalAmount){
        this.principalAmount = principalAmount;
    }
    public int getPrincipalAmount(){
        return this.principalAmount;
    }
    public void setGiftAmount(int giftAmount){
        this.giftAmount = giftAmount;
    }
    public int getGiftAmount(){
        return this.giftAmount;
    }
    public void setDepositAmount(int depositAmount){
        this.depositAmount = depositAmount;
    }
    public int getDepositAmount(){
        return this.depositAmount;
    }
    public void setTargetPatient(TargetPatient targetPatient){
        this.targetPatient = targetPatient;
    }
    public TargetPatient getTargetPatient(){
        return this.targetPatient;
    }
    public void setOperator(Operator operator){
        this.operator = operator;
    }
    public Operator getOperator(){
        return this.operator;
    }
    public void setChargeOrderId(int chargeOrderId){
        this.chargeOrderId = chargeOrderId;
    }
    public int getChargeOrderId(){
        return this.chargeOrderId;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return this.reason;
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

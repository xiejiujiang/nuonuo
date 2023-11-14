package com.example.nuonuo.entity.Ekanya.EzhangdanByOfficeIdANDTIME;

import java.util.List;

public class EZDItems {
    private int afterDiscountAmount;

    private List<String> discountPlan;

    private List<String> deductionSteps;

    private int groupIndex;

    private int paymentChargeItemId;

    private int billChargeItemId;

    private int chargeItemId;

    private boolean isDeduction;

    private int itemType;

    private String itemName;

    private String itemCategory;

    private String itemSuperCategory;

    private int count;

    private String unit;

    private int price;

    private int productId;

    private String itemCode;

    private String procedureCode;

    public void setAfterDiscountAmount(int afterDiscountAmount){
        this.afterDiscountAmount = afterDiscountAmount;
    }
    public int getAfterDiscountAmount(){
        return this.afterDiscountAmount;
    }
    public void setDiscountPlan(List<String> discountPlan){
        this.discountPlan = discountPlan;
    }
    public List<String> getDiscountPlan(){
        return this.discountPlan;
    }
    public void setDeductionSteps(List<String> deductionSteps){
        this.deductionSteps = deductionSteps;
    }
    public List<String> getDeductionSteps(){
        return this.deductionSteps;
    }
    public void setGroupIndex(int groupIndex){
        this.groupIndex = groupIndex;
    }
    public int getGroupIndex(){
        return this.groupIndex;
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
    public void setIsDeduction(boolean isDeduction){
        this.isDeduction = isDeduction;
    }
    public boolean getIsDeduction(){
        return this.isDeduction;
    }
    public void setItemType(int itemType){
        this.itemType = itemType;
    }
    public int getItemType(){
        return this.itemType;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public String getItemName(){
        return this.itemName;
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
    public void setCount(int count){
        this.count = count;
    }
    public int getCount(){
        return this.count;
    }
    public void setUnit(String unit){
        this.unit = unit;
    }
    public String getUnit(){
        return this.unit;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return this.price;
    }
    public void setProductId(int productId){
        this.productId = productId;
    }
    public int getProductId(){
        return this.productId;
    }
    public void setItemCode(String itemCode){
        this.itemCode = itemCode;
    }
    public String getItemCode(){
        return this.itemCode;
    }
    public void setProcedureCode(String procedureCode){
        this.procedureCode = procedureCode;
    }
    public String getProcedureCode(){
        return this.procedureCode;
    }
}

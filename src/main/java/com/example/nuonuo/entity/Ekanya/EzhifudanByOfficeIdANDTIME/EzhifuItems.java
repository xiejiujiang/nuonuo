package com.example.nuonuo.entity.Ekanya.EzhifudanByOfficeIdANDTIME;

import com.example.nuonuo.entity.Ekanya.Euser.Consultant;
import com.example.nuonuo.entity.Ekanya.Euser.Doctor;
import com.example.nuonuo.entity.Ekanya.Euser.Nurse;

import java.util.List;

public class EzhifuItems {
    private int actualAmount;

    private int afterItemDiscountAmount;

    private int afterDiscountAmount;

    private int discountAmount;

    private int overdue;

    private String itemSubCategory;

    private String chargeItemCode;

    private String fdiToothCodes;

    private String productCode;

    private List<String> deductionSteps;

    private Doctor doctor;

    private Nurse nurse;

    private Consultant consultant;

    private String onlineConsultant;

    private String orderOwner;

    private String executeDepartment;

    private String seller;

    private String chargePackageName;

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

    private String TinventoryCode;

    private String TinventoryUnitName;

    public void setActualAmount(int actualAmount){
        this.actualAmount = actualAmount;
    }
    public int getActualAmount(){
        return this.actualAmount;
    }
    public void setAfterItemDiscountAmount(int afterItemDiscountAmount){
        this.afterItemDiscountAmount = afterItemDiscountAmount;
    }
    public int getAfterItemDiscountAmount(){
        return this.afterItemDiscountAmount;
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
    public void setOverdue(int overdue){
        this.overdue = overdue;
    }
    public int getOverdue(){
        return this.overdue;
    }
    public void setItemSubCategory(String itemSubCategory){
        this.itemSubCategory = itemSubCategory;
    }
    public String getItemSubCategory(){
        return this.itemSubCategory;
    }
    public void setChargeItemCode(String chargeItemCode){
        this.chargeItemCode = chargeItemCode;
    }
    public String getChargeItemCode(){
        return this.chargeItemCode;
    }
    public void setFdiToothCodes(String fdiToothCodes){
        this.fdiToothCodes = fdiToothCodes;
    }
    public String getFdiToothCodes(){
        return this.fdiToothCodes;
    }
    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
    public String getProductCode(){
        return this.productCode;
    }
    public void setDeductionSteps(List<String> deductionSteps){
        this.deductionSteps = deductionSteps;
    }
    public List<String> getDeductionSteps(){
        return this.deductionSteps;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Consultant getConsultant() {
        return consultant;
    }

    public void setConsultant(Consultant consultant) {
        this.consultant = consultant;
    }

    public void setOnlineConsultant(String onlineConsultant){
        this.onlineConsultant = onlineConsultant;
    }
    public String getOnlineConsultant(){
        return this.onlineConsultant;
    }
    public void setOrderOwner(String orderOwner){
        this.orderOwner = orderOwner;
    }
    public String getOrderOwner(){
        return this.orderOwner;
    }
    public void setExecuteDepartment(String executeDepartment){
        this.executeDepartment = executeDepartment;
    }
    public String getExecuteDepartment(){
        return this.executeDepartment;
    }
    public void setSeller(String seller){
        this.seller = seller;
    }
    public String getSeller(){
        return this.seller;
    }
    public void setChargePackageName(String chargePackageName){
        this.chargePackageName = chargePackageName;
    }
    public String getChargePackageName(){
        return this.chargePackageName;
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

    public String getTinventoryCode() {
        return TinventoryCode;
    }

    public void setTinventoryCode(String tinventoryCode) {
        TinventoryCode = tinventoryCode;
    }

    public String getTinventoryUnitName() {
        return TinventoryUnitName;
    }

    public void setTinventoryUnitName(String tinventoryUnitName) {
        TinventoryUnitName = tinventoryUnitName;
    }
}

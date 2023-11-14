package com.example.nuonuo.entity.Ekanya.EzhifudanByOfficeIdANDTIME;

import com.example.nuonuo.entity.Ekanya.EzhifuDetailByID.EzhifuDetailData;

import java.util.List;

public class EzhifuData {
    private int deductionIncomePrice;

    private int incomePrice;

    private int giftCertificateIncomePrice;

    private List<EzhifuItems> items;

    private int paymentId;

    private String paymentNo;

    private int billId;

    private String billTime;

    private int patientId;

    private int appointmentId;

    private String date;

    private int actualAmount;

    private int overdue;

    private int advanceAmount;

    private int payeeId;

    private String operationReason;

    private String feeType;

    private String scenario;

    private List<String> integrationPayment;

    private String payChannel;

    private String payChannelOrderNo;

    private int payChannelAmount;

    private String note;

    private String chargeOrderCreatedTime;

    private String chargeOrderUpdatedTime;

    private List<EzhifFreeFields> freeFields;

    private int officeId;

    private int id;

    private String tenantId;

    private boolean isInactive;

    private String timestamp;

    private String TinventoryCode;

    private String TinventoryUnitName;

    private List<EzhifuDetailData> ezhifuDatadetail;

    public void setDeductionIncomePrice(int deductionIncomePrice){
        this.deductionIncomePrice = deductionIncomePrice;
    }
    public int getDeductionIncomePrice(){
        return this.deductionIncomePrice;
    }
    public void setIncomePrice(int incomePrice){
        this.incomePrice = incomePrice;
    }
    public int getIncomePrice(){
        return this.incomePrice;
    }
    public void setGiftCertificateIncomePrice(int giftCertificateIncomePrice){
        this.giftCertificateIncomePrice = giftCertificateIncomePrice;
    }
    public int getGiftCertificateIncomePrice(){
        return this.giftCertificateIncomePrice;
    }
    public void setItems(List<EzhifuItems> items){
        this.items = items;
    }
    public List<EzhifuItems> getItems(){
        return this.items;
    }
    public void setPaymentId(int paymentId){
        this.paymentId = paymentId;
    }
    public int getPaymentId(){
        return this.paymentId;
    }
    public void setPaymentNo(String paymentNo){
        this.paymentNo = paymentNo;
    }
    public String getPaymentNo(){
        return this.paymentNo;
    }
    public void setBillId(int billId){
        this.billId = billId;
    }
    public int getBillId(){
        return this.billId;
    }
    public void setBillTime(String billTime){
        this.billTime = billTime;
    }
    public String getBillTime(){
        return this.billTime;
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
    public void setActualAmount(int actualAmount){
        this.actualAmount = actualAmount;
    }
    public int getActualAmount(){
        return this.actualAmount;
    }
    public void setOverdue(int overdue){
        this.overdue = overdue;
    }
    public int getOverdue(){
        return this.overdue;
    }
    public void setAdvanceAmount(int advanceAmount){
        this.advanceAmount = advanceAmount;
    }
    public int getAdvanceAmount(){
        return this.advanceAmount;
    }
    public void setPayeeId(int payeeId){
        this.payeeId = payeeId;
    }
    public int getPayeeId(){
        return this.payeeId;
    }
    public void setOperationReason(String operationReason){
        this.operationReason = operationReason;
    }
    public String getOperationReason(){
        return this.operationReason;
    }
    public void setFeeType(String feeType){
        this.feeType = feeType;
    }
    public String getFeeType(){
        return this.feeType;
    }
    public void setScenario(String scenario){
        this.scenario = scenario;
    }
    public String getScenario(){
        return this.scenario;
    }
    public void setIntegrationPayment(List<String> integrationPayment){
        this.integrationPayment = integrationPayment;
    }
    public List<String> getIntegrationPayment(){
        return this.integrationPayment;
    }
    public void setPayChannel(String payChannel){
        this.payChannel = payChannel;
    }
    public String getPayChannel(){
        return this.payChannel;
    }
    public void setPayChannelOrderNo(String payChannelOrderNo){
        this.payChannelOrderNo = payChannelOrderNo;
    }
    public String getPayChannelOrderNo(){
        return this.payChannelOrderNo;
    }
    public void setPayChannelAmount(int payChannelAmount){
        this.payChannelAmount = payChannelAmount;
    }
    public int getPayChannelAmount(){
        return this.payChannelAmount;
    }
    public void setNote(String note){
        this.note = note;
    }
    public String getNote(){
        return this.note;
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
    public void setFreeFields(List<EzhifFreeFields> freeFields){
        this.freeFields = freeFields;
    }
    public List<EzhifFreeFields> getFreeFields(){
        return this.freeFields;
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

    public List<EzhifuDetailData> getEzhifuDatadetail() {
        return ezhifuDatadetail;
    }

    public void setEzhifuDatadetail(List<EzhifuDetailData> ezhifuDatadetail) {
        this.ezhifuDatadetail = ezhifuDatadetail;
    }
}

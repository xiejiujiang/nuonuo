package com.example.nuonuo.entity.shoukd;

import java.util.List;

public class Dto {
    private String ExternalCode;

    private String VoucherDate;

    private Partner Partner;

    private Person Person;

    private Department Department;

    private VoucherState VoucherState;

    private Currency Currency;

    private BusiType BusiType;

    private int ExchangeRate;

    private String Memo;

    private String OrigAllowances;//折让金额

    private boolean IsReceiveFlag;

    private boolean IsPartCancel;

    private List<ArapMultiSettleDetails> ArapMultiSettleDetails;

    private List<Details> Details;

    public void setExternalCode(String ExternalCode){
        this.ExternalCode = ExternalCode;
    }
    public String getExternalCode(){
        return this.ExternalCode;
    }
    public void setVoucherDate(String VoucherDate){
        this.VoucherDate = VoucherDate;
    }
    public String getVoucherDate(){
        return this.VoucherDate;
    }
    public void setPartner(Partner Partner){
        this.Partner = Partner;
    }
    public Partner getPartner(){
        return this.Partner;
    }
    public void setPerson(Person Person){
        this.Person = Person;
    }
    public Person getPerson(){
        return this.Person;
    }
    public void setDepartment(Department Department){
        this.Department = Department;
    }
    public Department getDepartment(){
        return this.Department;
    }

    public void setVoucherState(VoucherState VoucherState){
        this.VoucherState = VoucherState;
    }
    public VoucherState getVoucherState(){
        return this.VoucherState;
    }

    public void setCurrency(Currency Currency){
        this.Currency = Currency;
    }
    public Currency getCurrency(){
        return this.Currency;
    }
    public void setBusiType(BusiType BusiType){
        this.BusiType = BusiType;
    }
    public BusiType getBusiType(){
        return this.BusiType;
    }
    public void setExchangeRate(int ExchangeRate){
        this.ExchangeRate = ExchangeRate;
    }
    public int getExchangeRate(){
        return this.ExchangeRate;
    }
    public void setMemo(String Memo){
        this.Memo = Memo;
    }
    public String getMemo(){
        return this.Memo;
    }

    public String getOrigAllowances() {
        return OrigAllowances;
    }

    public void setOrigAllowances(String origAllowances) {
        OrigAllowances = origAllowances;
    }

    public void setIsReceiveFlag(boolean IsReceiveFlag){
        this.IsReceiveFlag = IsReceiveFlag;
    }
    public boolean getIsReceiveFlag(){
        return this.IsReceiveFlag;
    }
    public void setIsPartCancel(boolean IsPartCancel){
        this.IsPartCancel = IsPartCancel;
    }
    public boolean getIsPartCancel(){
        return this.IsPartCancel;
    }
    public void setArapMultiSettleDetails(List<ArapMultiSettleDetails> ArapMultiSettleDetails){
        this.ArapMultiSettleDetails = ArapMultiSettleDetails;
    }
    public List<ArapMultiSettleDetails> getArapMultiSettleDetails(){
        return this.ArapMultiSettleDetails;
    }
    public void setDetails(List<Details> Details){
        this.Details = Details;
    }
    public List<Details> getDetails(){
        return this.Details;
    }




}

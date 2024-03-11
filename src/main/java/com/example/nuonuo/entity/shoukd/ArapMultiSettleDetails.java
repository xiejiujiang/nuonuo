package com.example.nuonuo.entity.shoukd;

public class ArapMultiSettleDetails {

    private SettleStyle SettleStyle;

    private BankAccount BankAccount;

    private Float OrigAmount;

    private String Memo;

    public void setSettleStyle(SettleStyle SettleStyle){
        this.SettleStyle = SettleStyle;
    }
    public SettleStyle getSettleStyle(){
        return this.SettleStyle;
    }
    public void setBankAccount(BankAccount BankAccount){
        this.BankAccount = BankAccount;
    }
    public BankAccount getBankAccount(){
        return this.BankAccount;
    }
    public void setOrigAmount(Float OrigAmount){
        this.OrigAmount = OrigAmount;
    }
    public Float getOrigAmount(){
        return this.OrigAmount;
    }

    public String getMemo() {
        return Memo;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }
}

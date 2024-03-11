package com.example.nuonuo.entity.shoukd;

public class Details {
    private String VoucherCode;

    private VoucherType VoucherType;

    private int VoucherDetailID;

    private Float OrigCurrentAmount;

    private String OrigAllowancesAmount;

    public void setVoucherCode(String VoucherCode){
        this.VoucherCode = VoucherCode;
    }
    public String getVoucherCode(){
        return this.VoucherCode;
    }
    public void setVoucherType(VoucherType VoucherType){
        this.VoucherType = VoucherType;
    }
    public VoucherType getVoucherType(){
        return this.VoucherType;
    }
    public void setVoucherDetailID(int VoucherDetailID){
        this.VoucherDetailID = VoucherDetailID;
    }
    public int getVoucherDetailID(){
        return this.VoucherDetailID;
    }
    public void setOrigCurrentAmount(Float OrigCurrentAmount){
        this.OrigCurrentAmount = OrigCurrentAmount;
    }
    public Float getOrigCurrentAmount(){
        return this.OrigCurrentAmount;
    }

    public String getOrigAllowancesAmount() {
        return OrigAllowancesAmount;
    }

    public void setOrigAllowancesAmount(String origAllowancesAmount) {
        OrigAllowancesAmount = origAllowancesAmount;
    }
}

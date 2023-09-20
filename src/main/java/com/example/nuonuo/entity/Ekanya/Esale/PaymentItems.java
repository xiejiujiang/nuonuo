package com.example.nuonuo.entity.Ekanya.Esale;

public class PaymentItems {
    private int id;

    private int paymentStepId;

    private String paymentMethodName;

    private int amount;

    private int extraAmount;

    private int scenario;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setPaymentStepId(int paymentStepId){
        this.paymentStepId = paymentStepId;
    }
    public int getPaymentStepId(){
        return this.paymentStepId;
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
    public void setScenario(int scenario){
        this.scenario = scenario;
    }
    public int getScenario(){
        return this.scenario;
    }
}

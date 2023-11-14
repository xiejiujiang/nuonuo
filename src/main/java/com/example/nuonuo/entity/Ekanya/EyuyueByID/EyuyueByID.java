package com.example.nuonuo.entity.Ekanya.EyuyueByID;

public class EyuyueByID {

    private EyuyueData data;

    private boolean success;

    private String error;

    public void setData(EyuyueData data){
        this.data = data;
    }
    public EyuyueData getData(){
        return this.data;
    }
    public void setSuccess(boolean success){
        this.success = success;
    }
    public boolean getSuccess(){
        return this.success;
    }
    public void setError(String error){
        this.error = error;
    }
    public String getError(){
        return this.error;
    }
}

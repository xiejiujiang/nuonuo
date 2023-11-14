package com.example.nuonuo.entity.Ekanya.EsingleUser;

public class Error {
    private int code;

    private String message;

    private String debugMessage;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return this.message;
    }
    public void setDebugMessage(String debugMessage){
        this.debugMessage = debugMessage;
    }
    public String getDebugMessage(){
        return this.debugMessage;
    }
}

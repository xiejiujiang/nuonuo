package com.example.nuonuo.entity.tiancai;

import java.util.List;

public class TcCKresult {
    private String msg;

    private String code;

    private List<TCCKData> data;

    private String state;

    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setData(List<TCCKData> data){
        this.data = data;
    }
    public List<TCCKData> getData(){
        return this.data;
    }
    public void setState(String state){
        this.state = state;
    }
    public String getState(){
        return this.state;
    }
}

package com.example.nuonuo.entity.mida.fahuoddct;

import java.util.List;

public class MidaFhdd {

    private String errCode;
    private String errMsg;
    private String errResolveMethod;
    private String timestamp;
    private List<Fhdd> payload;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getErrResolveMethod() {
        return errResolveMethod;
    }

    public void setErrResolveMethod(String errResolveMethod) {
        this.errResolveMethod = errResolveMethod;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<Fhdd> getPayload() {
        return payload;
    }

    public void setPayload(List<Fhdd> payload) {
        this.payload = payload;
    }
}

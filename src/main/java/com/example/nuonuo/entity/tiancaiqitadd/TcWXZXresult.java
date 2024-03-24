package com.example.nuonuo.entity.tiancaiqitadd;

import java.util.List;

public class TcWXZXresult {
    private String msg;

    private String code;

    private List<TcWXZXdetail> data;

    private String state;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<TcWXZXdetail> getData() {
        return data;
    }

    public void setData(List<TcWXZXdetail> data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

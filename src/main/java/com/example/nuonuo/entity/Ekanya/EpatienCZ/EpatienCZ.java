package com.example.nuonuo.entity.Ekanya.EpatienCZ;

import java.util.List;

public class EpatienCZ {

    private int totalCount;

    private int pageIndex;

    private int pageCount;

    private List<EpatienCZData> data;

    private boolean success;

    private String error;

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }
    public int getTotalCount(){
        return this.totalCount;
    }
    public void setPageIndex(int pageIndex){
        this.pageIndex = pageIndex;
    }
    public int getPageIndex(){
        return this.pageIndex;
    }
    public void setPageCount(int pageCount){
        this.pageCount = pageCount;
    }
    public int getPageCount(){
        return this.pageCount;
    }
    public void setData(List<EpatienCZData> data){
        this.data = data;
    }
    public List<EpatienCZData> getData(){
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

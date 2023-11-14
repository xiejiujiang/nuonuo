package com.example.nuonuo.entity.Ekanya.EzhifuDetailByID;

import java.util.List;

public class EzhifuDetailByID {

    private int totalCount;

    private int pageIndex;

    private int pageCount;

    private List<EzhifuDetailData> data;

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
    public void setData(List<EzhifuDetailData> data){
        this.data = data;
    }
    public List<EzhifuDetailData> getData(){
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

package com.example.nuonuo.entity.Ekanya.Euser;

public class Source {
    private String sourceLevel1;

    private String sourceLevel2;

    private String sourceLevel3;

    private int refereeId;

    private String refereeMobileOrCode;

    public void setSourceLevel1(String sourceLevel1){
        this.sourceLevel1 = sourceLevel1;
    }
    public String getSourceLevel1(){
        return this.sourceLevel1;
    }
    public void setSourceLevel2(String sourceLevel2){
        this.sourceLevel2 = sourceLevel2;
    }
    public String getSourceLevel2(){
        return this.sourceLevel2;
    }
    public void setSourceLevel3(String sourceLevel3){
        this.sourceLevel3 = sourceLevel3;
    }
    public String getSourceLevel3(){
        return this.sourceLevel3;
    }
    public void setRefereeId(int refereeId){
        this.refereeId = refereeId;
    }
    public int getRefereeId(){
        return this.refereeId;
    }
    public void setRefereeMobileOrCode(String refereeMobileOrCode){
        this.refereeMobileOrCode = refereeMobileOrCode;
    }
    public String getRefereeMobileOrCode(){
        return this.refereeMobileOrCode;
    }
}

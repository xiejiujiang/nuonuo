package com.example.nuonuo.entity.Ekanya.Euser;

public class Telephones {
    private String number;

    private String kind;

    private String type;

    private int displayOrder;

    public void setNumber(String number){
        this.number = number;
    }
    public String getNumber(){
        return this.number;
    }
    public void setKind(String kind){
        this.kind = kind;
    }
    public String getKind(){
        return this.kind;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setDisplayOrder(int displayOrder){
        this.displayOrder = displayOrder;
    }
    public int getDisplayOrder(){
        return this.displayOrder;
    }
}

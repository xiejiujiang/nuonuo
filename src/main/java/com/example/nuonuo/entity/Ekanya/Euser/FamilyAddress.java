package com.example.nuonuo.entity.Ekanya.Euser;

public class FamilyAddress {
    private int id;

    private int ownerType;

    private int ownerId;

    private int addressType;

    private String province;

    private String city;

    private String district;

    private String postalCode;

    private String content;

    private String contactName;

    private String contactPhoneNumber;

    private String mData;

    private int officeId;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setOwnerType(int ownerType){
        this.ownerType = ownerType;
    }
    public int getOwnerType(){
        return this.ownerType;
    }
    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }
    public int getOwnerId(){
        return this.ownerId;
    }
    public void setAddressType(int addressType){
        this.addressType = addressType;
    }
    public int getAddressType(){
        return this.addressType;
    }
    public void setProvince(String province){
        this.province = province;
    }
    public String getProvince(){
        return this.province;
    }
    public void setCity(String city){
        this.city = city;
    }
    public String getCity(){
        return this.city;
    }
    public void setDistrict(String district){
        this.district = district;
    }
    public String getDistrict(){
        return this.district;
    }
    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public String getPostalCode(){
        return this.postalCode;
    }
    public void setContent(String content){
        this.content = content;
    }
    public String getContent(){
        return this.content;
    }
    public void setContactName(String contactName){
        this.contactName = contactName;
    }
    public String getContactName(){
        return this.contactName;
    }
    public void setContactPhoneNumber(String contactPhoneNumber){
        this.contactPhoneNumber = contactPhoneNumber;
    }
    public String getContactPhoneNumber(){
        return this.contactPhoneNumber;
    }
    public void setMData(String mData){
        this.mData = mData;
    }
    public String getMData(){
        return this.mData;
    }
    public void setOfficeId(int officeId){
        this.officeId = officeId;
    }
    public int getOfficeId(){
        return this.officeId;
    }
}

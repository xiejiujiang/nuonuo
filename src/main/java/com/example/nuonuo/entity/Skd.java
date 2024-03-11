package com.example.nuonuo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class Skd extends BaseRowModel {
    @ExcelProperty(index = 0)
    private String ssqd;//所属渠道

    @ExcelProperty(index = 1)
    private String clerkname;//业务员

    @ExcelProperty(index = 2)
    private String djrq;//订单日期

    @ExcelProperty(index = 3)
    private String saordercode;//销售订单号

    @ExcelProperty(index = 4)
    private String saledate;//销售日期

    @ExcelProperty(index = 5)
    private String sacode;//销货单单号

    @ExcelProperty(index = 6)
    private String ddmemo;//订单备注

    @ExcelProperty(index = 7)
    private String kehucode;

    @ExcelProperty(index = 8)
    private String kehuname;

    @ExcelProperty(index = 9)
    private String jskehucode;

    @ExcelProperty(index = 10)
    private String jskehuname;

    @ExcelProperty(index = 11)
    private String inventorycode;

    @ExcelProperty(index = 12)
    private String inventoryname;

    @ExcelProperty(index = 13)
    private String fudanwei;

    @ExcelProperty(index = 14)
    private String zhudanwei;

    @ExcelProperty(index = 15)
    private String salenumbersfu;

    @ExcelProperty(index = 16)
    private String salenumberszhu;

    @ExcelProperty(index = 17)
    private String saleprice;

    @ExcelProperty(index = 18)
    private String saleamount;

    @ExcelProperty(index = 19)
    private String tuinumberfu;

    @ExcelProperty(index = 20)
    private String tuinumberzhu;

    @ExcelProperty(index = 21)
    private String tuiamount;

    @ExcelProperty(index = 22)
    private String factsalenumberfu;

    @ExcelProperty(index = 23)
    private String factsalenumberzhu;

    @ExcelProperty(index = 24)
    private String factsaleamount;

    @ExcelProperty(index = 25)
    private String hexiaoamount;

    @ExcelProperty(index = 26)
    private String zherangamount;

    @ExcelProperty(index = 27)
    private String shoukdate;

    @ExcelProperty(index = 28)
    private String shoukamount;

    @ExcelProperty(index = 29)
    private String jscode;

    @ExcelProperty(index = 30)
    private String jsname;

    @ExcelProperty(index = 31)
    private String bankname;

    @ExcelProperty(index = 32)
    private String shoukmemo;

    @ExcelProperty(index = 33)
    private String mobile;

    public String getSsqd() {
        return ssqd;
    }

    public void setSsqd(String ssqd) {
        this.ssqd = ssqd;
    }

    public String getClerkname() {
        return clerkname;
    }

    public void setClerkname(String clerkname) {
        this.clerkname = clerkname;
    }

    public String getDjrq() {
        return djrq;
    }

    public void setDjrq(String djrq) {
        this.djrq = djrq;
    }

    public String getSaordercode() {
        return saordercode;
    }

    public void setSaordercode(String saordercode) {
        this.saordercode = saordercode;
    }

    public String getSaledate() {
        return saledate;
    }

    public void setSaledate(String saledate) {
        this.saledate = saledate;
    }

    public String getSacode() {
        return sacode;
    }

    public void setSacode(String sacode) {
        this.sacode = sacode;
    }

    public String getDdmemo() {
        return ddmemo;
    }

    public void setDdmemo(String ddmemo) {
        this.ddmemo = ddmemo;
    }

    public String getKehucode() {
        return kehucode;
    }

    public void setKehucode(String kehucode) {
        this.kehucode = kehucode;
    }

    public String getKehuname() {
        return kehuname;
    }

    public void setKehuname(String kehuname) {
        this.kehuname = kehuname;
    }

    public String getJskehucode() {
        return jskehucode;
    }

    public void setJskehucode(String jskehucode) {
        this.jskehucode = jskehucode;
    }

    public String getJskehuname() {
        return jskehuname;
    }

    public void setJskehuname(String jskehuname) {
        this.jskehuname = jskehuname;
    }

    public String getInventorycode() {
        return inventorycode;
    }

    public void setInventorycode(String inventorycode) {
        this.inventorycode = inventorycode;
    }

    public String getInventoryname() {
        return inventoryname;
    }

    public void setInventoryname(String inventoryname) {
        this.inventoryname = inventoryname;
    }

    public String getFudanwei() {
        return fudanwei;
    }

    public void setFudanwei(String fudanwei) {
        this.fudanwei = fudanwei;
    }

    public String getZhudanwei() {
        return zhudanwei;
    }

    public void setZhudanwei(String zhudanwei) {
        this.zhudanwei = zhudanwei;
    }

    public String getSalenumbersfu() {
        return salenumbersfu;
    }

    public void setSalenumbersfu(String salenumbersfu) {
        this.salenumbersfu = salenumbersfu;
    }

    public String getSalenumberszhu() {
        return salenumberszhu;
    }

    public void setSalenumberszhu(String salenumberszhu) {
        this.salenumberszhu = salenumberszhu;
    }

    public String getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(String saleprice) {
        this.saleprice = saleprice;
    }

    public String getSaleamount() {
        return saleamount;
    }

    public void setSaleamount(String saleamount) {
        this.saleamount = saleamount;
    }

    public String getTuinumberfu() {
        return tuinumberfu;
    }

    public void setTuinumberfu(String tuinumberfu) {
        this.tuinumberfu = tuinumberfu;
    }

    public String getTuinumberzhu() {
        return tuinumberzhu;
    }

    public void setTuinumberzhu(String tuinumberzhu) {
        this.tuinumberzhu = tuinumberzhu;
    }

    public String getTuiamount() {
        return tuiamount;
    }

    public void setTuiamount(String tuiamount) {
        this.tuiamount = tuiamount;
    }

    public String getFactsalenumberfu() {
        return factsalenumberfu;
    }

    public void setFactsalenumberfu(String factsalenumberfu) {
        this.factsalenumberfu = factsalenumberfu;
    }

    public String getFactsalenumberzhu() {
        return factsalenumberzhu;
    }

    public void setFactsalenumberzhu(String factsalenumberzhu) {
        this.factsalenumberzhu = factsalenumberzhu;
    }

    public String getFactsaleamount() {
        return factsaleamount;
    }

    public void setFactsaleamount(String factsaleamount) {
        this.factsaleamount = factsaleamount;
    }

    public String getHexiaoamount() {
        return hexiaoamount;
    }

    public void setHexiaoamount(String hexiaoamount) {
        this.hexiaoamount = hexiaoamount;
    }

    public String getZherangamount() {
        return zherangamount;
    }

    public void setZherangamount(String zherangamount) {
        this.zherangamount = zherangamount;
    }

    public String getShoukdate() {
        return shoukdate;
    }

    public void setShoukdate(String shoukdate) {
        this.shoukdate = shoukdate;
    }

    public String getShoukamount() {
        return shoukamount;
    }

    public void setShoukamount(String shoukamount) {
        this.shoukamount = shoukamount;
    }

    public String getJscode() {
        return jscode;
    }

    public void setJscode(String jscode) {
        this.jscode = jscode;
    }

    public String getJsname() {
        return jsname;
    }

    public void setJsname(String jsname) {
        this.jsname = jsname;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getShoukmemo() {
        return shoukmemo;
    }

    public void setShoukmemo(String shoukmemo) {
        this.shoukmemo = shoukmemo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "Skd{" +
                "ssqd='" + ssqd + '\'' +
                ", clerkname='" + clerkname + '\'' +
                ", djrq='" + djrq + '\'' +
                ", saordercode='" + saordercode + '\'' +
                ", saledate='" + saledate + '\'' +
                ", sacode='" + sacode + '\'' +
                ", ddmemo='" + ddmemo + '\'' +
                ", kehucode='" + kehucode + '\'' +
                ", kehuname='" + kehuname + '\'' +
                ", jskehucode='" + jskehucode + '\'' +
                ", jskehuname='" + jskehuname + '\'' +
                ", inventorycode='" + inventorycode + '\'' +
                ", inventoryname='" + inventoryname + '\'' +
                ", fudanwei='" + fudanwei + '\'' +
                ", zhudanwei='" + zhudanwei + '\'' +
                ", salenumbersfu='" + salenumbersfu + '\'' +
                ", salenumberszhu='" + salenumberszhu + '\'' +
                ", saleprice='" + saleprice + '\'' +
                ", saleamount='" + saleamount + '\'' +
                ", tuinumberfu='" + tuinumberfu + '\'' +
                ", tuinumberzhu='" + tuinumberzhu + '\'' +
                ", tuiamount='" + tuiamount + '\'' +
                ", factsalenumberfu='" + factsalenumberfu + '\'' +
                ", factsalenumberzhu='" + factsalenumberzhu + '\'' +
                ", factsaleamount='" + factsaleamount + '\'' +
                ", hexiaoamount='" + hexiaoamount + '\'' +
                ", zherangamount='" + zherangamount + '\'' +
                ", shoukdate='" + shoukdate + '\'' +
                ", shoukamount='" + shoukamount + '\'' +
                ", jscode='" + jscode + '\'' +
                ", jsname='" + jsname + '\'' +
                ", bankname='" + bankname + '\'' +
                ", shoukmemo='" + shoukmemo + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
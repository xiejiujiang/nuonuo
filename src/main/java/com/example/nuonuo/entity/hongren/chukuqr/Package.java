package com.example.nuonuo.entity.hongren.chukuqr;

import java.util.List;

public class Package {

    private String logisticsName;
    private String logisticsCode;
    private String expressCode;
    private String packageCode;
    private String weight;
    private String volume;
    private List<Item> items;
    private ExtendProps extendProps;

    public String getLogisticsName() {
        return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
        this.logisticsName = logisticsName;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public String getPackageCode() {
        return packageCode;
    }

    public void setPackageCode(String packageCode) {
        this.packageCode = packageCode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public ExtendProps getExtendProps() {
        return extendProps;
    }

    public void setExtendProps(ExtendProps extendProps) {
        this.extendProps = extendProps;
    }
}

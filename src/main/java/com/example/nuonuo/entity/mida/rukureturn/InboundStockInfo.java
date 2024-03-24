package com.example.nuonuo.entity.mida.rukureturn;

import java.util.List;

public class InboundStockInfo {
    private String supplierCode;
    private String supplierName;
    private List<WarehouseStockBo> stockDetailBos;


    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<WarehouseStockBo> getStockDetailBos() {
        return stockDetailBos;
    }

    public void setStockDetailBos(List<WarehouseStockBo> stockDetailBos) {
        this.stockDetailBos = stockDetailBos;
    }
}

package com.example.nuonuo.entity.Meituan;

import java.util.List;

public class GoodsOpenDTO {
    private String id;

    private String code;

    private String name;

    private CategoryOpenDTO category;

    private String brand;

    private String spec;

    private UnitOpenDTO baseUnit;

    private List<UnitOpenDTO> units;

    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryOpenDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryOpenDTO category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public UnitOpenDTO getBaseUnit() {
        return baseUnit;
    }

    public void setBaseUnit(UnitOpenDTO baseUnit) {
        this.baseUnit = baseUnit;
    }

    public List<UnitOpenDTO> getUnits() {
        return units;
    }

    public void setUnits(List<UnitOpenDTO> units) {
        this.units = units;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}

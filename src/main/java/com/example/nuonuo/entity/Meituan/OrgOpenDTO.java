package com.example.nuonuo.entity.Meituan;

public class OrgOpenDTO {
    private Long orgId;

    private Long rootOrgId;

    private String code;

    private String name;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getRootOrgId() {
        return rootOrgId;
    }

    public void setRootOrgId(Long rootOrgId) {
        this.rootOrgId = rootOrgId;
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
}

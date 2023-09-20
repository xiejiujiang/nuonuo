package com.example.nuonuo.utils;

public class Ekanya {
    private String ticket;
    private String tenantId;

    public Ekanya(String ticket, String tenantId) {
        this.ticket = ticket;
        this.tenantId = tenantId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
}
